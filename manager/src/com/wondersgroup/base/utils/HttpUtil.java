package com.wondersgroup.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

@SuppressWarnings("deprecation")
@Component
public class HttpUtil {
	
public static HttpUtil httpUtil;
	
    @PostConstruct
    public void init() {
    	httpUtil = this;
    }
	
	/*@Autowired
	private ExceptionDao exceptionDao;*/
	
	private static HttpUtil thisInstance = null;
	private static CloseableHttpClient closeableHttpClient = null;

	private HttpUtil() {
		closeableHttpClient = PooledHttpUitl.getCloseableHttp(3000, 1500);
	}

	public static HttpUtil getInstance() {
		if (thisInstance == null) {
			thisInstance = new HttpUtil();
		}
		return thisInstance;
	}
	
	public static List<NameValuePair> convertMapToPair(Map<String, String> params) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return pairs;
	}
	
	/**
	 * ?????????????????????????????????body??????
	 * @param head
	 * @param paramString
	 * @return
	 */
	public static String doPost(Map<String, String> head, String paramString, String contentType) {
		HttpUtil.getInstance();
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);	   
	    // JDK8???????????????Map????????????
		//       if(null != head && 0 < head.size()) {
		//           head.forEach((k, v) -> {
		//               httpPost.addHeader(k , v);
		//           });
		//       }
	    // ????????????
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        httpPost.addHeader("Content-type", contentType);
        
        // ??????????????????????????????
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        httpPost.setEntity(se);
        
        CloseableHttpResponse response = null;
        try{
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
	    		} else {
	    			closeableHttpClient = HttpClients.createDefault();
	    		}
        	}
        	response = closeableHttpClient.execute(httpPost);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // ????????????????????????????????????String??????
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	body="error";
        	Log.debug("???????????????"+e);
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//        System.out.println(body);
		return body;
	}
	
	/**
	 * form-data
	 * @param head
	 * @param reqEntity
	 * @return
	 */
	public static String doPost(Map<String, String> head, HttpEntity reqEntity) {
		HttpUtil.getInstance();
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
        
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
//        httpPost.addHeader("Content-type","multipart/form-data");
        if(reqEntity != null){
        	httpPost.setEntity(reqEntity);
        }
        
        CloseableHttpResponse response = null;
        
        try{
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
	    		} else {
	    			closeableHttpClient = HttpClients.createDefault();
	    		}
        	}
        	response = closeableHttpClient.execute(httpPost);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // ????????????????????????????????????String??????
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("???????????????"+e);
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	/**
	 * ?????????????????????????????????body??????,???????????????
	 * @param head
	 * @param paramString
	 * @param contentType
	 * @return
	 */
	public static byte[] doPostForFile(Map<String, String> head, String paramString, String contentType) {
		HttpUtil.getInstance();
		InputStream in = null;
		byte[] bytes = null;
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
//  	   	RequestConfig config = RequestConfig.custom().setConnectTimeout(15000) //??????????????????
//               .setConnectionRequestTimeout(1000) //??????????????????????????????????????????
//               .setSocketTimeout(10000) //???????????????????????????
//               .build();
//  	   	httpPost.setConfig(config);
		
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        // ??????????????????????????????
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        httpPost.setEntity(se);
        
        CloseableHttpResponse response = null;
        try{
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
	    		} else {
	    			closeableHttpClient = HttpClients.createDefault();
	    		}
        	}
        	response = closeableHttpClient.execute(httpPost);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	in = entity.getContent();
            	ByteArrayOutputStream bos = new ByteArrayOutputStream();
            	byte[] buff = new byte[100];
            	int rc = 0;
            	while((rc = in.read(buff, 0, 100)) > 0){
            		bos.write(buff, 0, rc);
            	}
            	bytes = bos.toByteArray();
            }
        } catch (Exception e) {
        	Log.debug("???????????????"+e);
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
	
	/**
	 * ???????????????????????????
	 * @param bytes
	 * @param head
	 * @param params
	 * @return
	 */
	public static String sendMultipartFilePost(byte[] bytes, Map<String, String> head,
			Map<String, Object> params,MultipartFile fileItem){
		HttpUtil.getInstance();
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
// 	    RequestConfig config = RequestConfig.custom().setConnectTimeout(15000) //??????????????????
//               .setConnectionRequestTimeout(1000) //??????????????????????????????????????????
//               .setSocketTimeout(10000) //???????????????????????????
//               .build();
//	    httpPost.setConfig(config);
		
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        InputStream is = new ByteArrayInputStream(bytes);
        builder.addBinaryBody("FileData", is, ContentType.MULTIPART_FORM_DATA, fileItem != null ? fileItem.getOriginalFilename() : "????????????.png");

        //??????????????????
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getValue() == null)
                continue;
            builder.addTextBody(entry.getKey(), entry.getValue().toString(), contentType);
        }
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
		try {
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
	    		} else {
	    			closeableHttpClient = HttpClients.createDefault();
	    		}
        	}
        	response = closeableHttpClient.execute(httpPost);
	        HttpEntity responseEntity = response.getEntity();
	        if(responseEntity != null){
	            body = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
	        }
		} catch (Exception e) {
			Log.debug("????????????"+e);
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	public static String doGet(Map<String, String> head, String url, String method){
		HttpUtil.getInstance();
		String body = "";
        HttpGet get = new HttpGet(url);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            get.addHeader(entry.getKey() , entry.getValue());
        }
        
        CloseableHttpResponse response = null;
        try{
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
	    		} else {
	    			closeableHttpClient = HttpClients.createDefault();
	    		}
        	}
        	response = closeableHttpClient.execute(get);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // ????????????????????????????????????String??????
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("????????????"+e);
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        System.out.println(body);
		return body;
	}
	
	/**
	 * ??????APP_ID???API_ID??????????????????
	 * @param appId
	 * @param appName
	 * @return
	 */
	public static String getSignature(String appName) {
		String APP_KEY = RdConfig.get("reindeer.huidao.appKey."+RdConfig.get("reindeer.huidao.environment"));
		String APP_ID = RdConfig.get("reindeer.huidao.appId."+RdConfig.get("reindeer.huidao.environment"));
		String key = APP_KEY.replace("-", "").trim();
		String signature = "";
		
		String stringtosign = APP_ID+ appName+ System.currentTimeMillis()/1000;
		try {
	        byte[] raw = key.getBytes("utf-8");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"??????/??????/????????????"
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	        byte[] encrypted = cipher.doFinal(stringtosign.getBytes("utf-8"));
	        signature = Base64Util.encode(encrypted);
		} catch (Exception e) {
			System.out.println("error:"+e.getMessage());
		}
		return signature;
	}
	
	public static String getAccessToken(String userName, String idenetNo, String mobile){
		HttpUtil.getInstance();
		String body = "";
        HttpPost httpPost = new HttpPost(RdConfig.get("reindeer.huidao.url.test")+"?idCard="+idenetNo+"&name="+userName+"&mobile="+mobile);
		
        String appName = "da067561-7096-4ade-b61e-ea154c50040e";
        String signature = getSignature(appName);
        Map<String, String> head = setHttpHeard(signature, appName);
        
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        //httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        CloseableHttpResponse response = null;
        try{
        	if(closeableHttpClient == null){
        		closeableHttpClient = HttpClients.createDefault();
        	}
        	response = closeableHttpClient.execute(httpPost);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // ????????????????????????????????????String??????
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("???????????????"+e);
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        System.out.println(body);
		return body;
	}
	
	/**
	 * ??????accessToken??????????????????
	 * @param accessToken
	 * @return
	 */
	public static String getUserInfoByAccesstoken(String accessToken){
		HttpUtil.getInstance();
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url+"?access_token="+accessToken);
        System.out.println(url+"?access_token="+accessToken);
        
        String appName ="";
        if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
        	appName = "6bfa95b4-4fec-4d76-89bd-485b7ba97c35";
        } else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
        	appName = "6e9809b6-b9dc-49d3-9a6b-8792cd5d6db8";
        }
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        
        CloseableHttpResponse response = null;
        try{
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
        		} else {
        			closeableHttpClient = HttpClients.createDefault();
        		}
        	}
        	response = closeableHttpClient.execute(httpPost);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // ????????????????????????????????????String??????
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("????????????");
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	public static void main(String[] args) throws Exception {		
		String signature = getSignature("dd25e3ab-ced3-4a7c-8736-54f1a1bf985f");
		
        Map<String, String> head = setHttpHeard(signature,"dd25e3ab-ced3-4a7c-8736-54f1a1bf985f");
        
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
        }
	}
	
	/**
	 * ???????????????????????????
	 * @param signature
	 * @param appName
	 * @return
	 */
	public static Map<String, String> setHttpHeard(String signature, String appName){
		Map<String, String> head = new HashMap<String, String>();
		head.put("signature", signature);
		head.put("apiname", appName);
		head.put("appid", RdConfig.get("reindeer.huidao.appId."+RdConfig.get("reindeer.huidao.environment")));
		return head;
	}
	
	/**
	 * ??????????????????????????????????????????????????????
	 * @param itemNo
	 * @return
	 */
	public static String getApplyNo(String itemCode) {
		HttpUtil.getInstance();
		String result = "";
		String data = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
        
        String appName ="";
        if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
        	appName = "2f34d2df-43b7-46e1-94fc-c0114e6f573b";
        } else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
        	appName = "f950f6db-d504-4918-b3a3-4b7572fdcced";
        }
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        // ??????????????????????????????
        StringEntity se = new StringEntity("{\"itemCode\":\""+itemCode+"\"}","utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8"));
        httpPost.setEntity(se);
        
        CloseableHttpResponse response = null;
        try{
        	if(closeableHttpClient == null){
        		if(url.startsWith("https://")){
        			closeableHttpClient= getHttpsClient();
        		} else {
        			closeableHttpClient = HttpClients.createDefault();
        		}
        	}
        	response = closeableHttpClient.execute(httpPost);
            // ??????????????????
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // ????????????????????????????????????String??????
            	result = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("???????????????"+e.getMessage());
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        System.out.println(result);
        if(StringUtils.isNotEmpty(result)){
            JSONObject json = JSONObject.fromObject(result);
            data = json.optString("data");
        }
		return data;
	}
	
	
	/**
	 * CloseableHttpClient??????https????????????????????????
	 * @return
	 */
	public static CloseableHttpClient getHttpsClient() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);
        // ??????????????????????????????????????????????????????
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // ??????????????????
            TrustStrategy anyTrustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            registryBuilder.register("https", sslSF);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        
        
        // ?????????????????????
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        // ???????????????
        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }
	
	public static OkHttpClient getUnsafeOkHttpClient() {
       OkHttpClient httpClient = null;
       try {
    	   // Create a trust manager that does not validate certificate chains
           final TrustManager[] trustAllCerts = new TrustManager[]{
                   new X509TrustManager() {
                       @Override
                       public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                       }
                       @Override
                       public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                       }
                       @Override
                       public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                           return new java.security.cert.X509Certificate[]{};
                       }
                   }
           };

           // Install the all-trusting trust manager
           final SSLContext sslContext = SSLContext.getInstance("SSL");
           sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
           // Create an ssl socket factory with our all-trusting manager
           final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();	            
           httpClient = new OkHttpClient.Builder()
	           // ?????????  ?????????????????????????????? ???????????? ???????????????5??????   ????????????  cpu???16???  ????????????16???
	           .connectionPool(new ConnectionPool(16, 5, TimeUnit.MINUTES))
	           // ???curl??????  ?????????????????????  ???10S??????
	           .connectTimeout(10, TimeUnit.SECONDS)
	           // ??????????????????
	           .sslSocketFactory(sslSocketFactory)
	           .hostnameVerifier(new HostnameVerifier() {
	                @Override
	                public boolean verify(String hostname, SSLSession session) {
	                    return true;
	                }
	           })
	           .retryOnConnectionFailure(true)
	           .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
	    return httpClient;
	}
	
	/*public static void addExceptionInfo(StackTraceElement stackTraceElement,
			String exceptionCause, String url, String param, String reqMethod){
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setStId(UUID.randomUUID().toString());
		exceptionInfo.setStExceptionMethod(stackTraceElement.getMethodName());
		exceptionInfo.setStExceptionPackage(stackTraceElement.getClassName());
		exceptionInfo.setStExceptionCause(exceptionCause);
		exceptionInfo.setStExceptionLine(new BigDecimal(stackTraceElement.getLineNumber()));
		exceptionInfo.setStExceptionFile(stackTraceElement.getFileName());		
		exceptionInfo.setDtExceptionTime(new Timestamp(System.currentTimeMillis()));
		exceptionInfo.setStRequestUrl(url);
		exceptionInfo.setStRequestMethod(reqMethod);
		exceptionInfo.setStRequestParam(param);
		
		httpUtil.exceptionDao.saveExceptionInfo(exceptionInfo);
	}*/
	
	
}
