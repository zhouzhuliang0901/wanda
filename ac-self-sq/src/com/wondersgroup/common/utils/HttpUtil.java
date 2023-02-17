package com.wondersgroup.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

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
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.wondersgroup.ext.ParseConfig;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

@SuppressWarnings("deprecation")
public class HttpUtil {
	
	public static List<NameValuePair> convertMapToPair(Map<String, String> params) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return pairs;
	}
	
	/**
	 * 统一支撑平台接口请求，body传参
	 * @param head
	 * @param paramString
	 * @return
	 */
	public static String doPost(Map<String, String> head, String paramString, String contentType) {
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);	   
	   // JDK8下支持新的Map迭代方式
//       if(null != head && 0 < head.size()) {
//           head.forEach((k, v) -> {
//               httpPost.addHeader(k , v);
//           });
//       }
	    // 头部信息
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        httpPost.addHeader("Content-type", contentType);
        
        // 设置参数到请求对象中
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        httpPost.setEntity(se);
        
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
        try{
        	response = closeableHttpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	body="error";
        	Log.debug("访问失败："+e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		httpPost.releaseConnection();
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	/**
	 * form-data
	 * @param head
	 * @param reqEntity
	 * @return
	 */
	public static String doPost(Map<String, String> head, HttpEntity reqEntity) {
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
        
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
//        httpPost.addHeader("Content-type","multipart/form-data");
        if(reqEntity != null){
        	httpPost.setEntity(reqEntity);
        }
        
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
        try{
        	response = closeableHttpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("访问失败："+e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		httpPost.releaseConnection();
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	/**
	 * 统一支撑平台接口请求，body传参,获取文件流
	 * @param head
	 * @param paramString
	 * @param contentType
	 * @return
	 */
	public static byte[] doPostForFile(Map<String, String> head, String paramString, String contentType) {
		InputStream in = null;
		byte[] bytes = null;
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
		
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        // 设置参数到请求对象中
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        httpPost.setEntity(se);
        
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
        try{
        	response = closeableHttpClient.execute(httpPost);
            // 获取结果实体
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
        	Log.debug("访问失败："+e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		httpPost.releaseConnection();
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
	
	/**
	 * 申报服务，材料上传
	 * @param bytes
	 * @param head
	 * @param params
	 * @return
	 */
	public static String sendMultipartFilePost(byte[] bytes, Map<String, String> head,
			Map<String, Object> params,MultipartFile fileItem){
		String body = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(url);
		
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        InputStream is = new ByteArrayInputStream(bytes);
        builder.addBinaryBody("FileData", is, ContentType.MULTIPART_FORM_DATA, fileItem != null ? fileItem.getOriginalFilename() : "申请材料.png");

        //解决中文乱码
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if(entry.getValue() == null)
                continue;
            builder.addTextBody(entry.getKey(), entry.getValue().toString(), contentType);
        }
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
		try {
        	response = closeableHttpClient.execute(httpPost);
	        HttpEntity responseEntity = response.getEntity();
	        if(responseEntity != null){
	            body = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
	        }
		} catch (Exception e) {
			Log.debug("访问失败"+e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		httpPost.releaseConnection();
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	public static String doGet(Map<String, String> head, String url, String method){
		String body = "";
        HttpGet get = new HttpGet(url);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            get.addHeader(entry.getKey() , entry.getValue());
        }
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
        try{
        	response = closeableHttpClient.execute(get);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("访问失败"+e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		get.releaseConnection();
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
	 * 根据APP_ID、API_ID获取加密签名
	 * @param appId
	 * @param appName
	 * @return
	 */
	public static String getSignature(String appName) {
		String APP_KEY = ParseConfig.get("reindeer.huidao.appKey");
		String APP_ID = ParseConfig.get("reindeer.huidao.appId");
		String key = APP_KEY.replace("-", "").trim();
		String signature = "";
		
		String stringtosign = APP_ID+ appName+ System.currentTimeMillis()/1000;
		try {
	        byte[] raw = key.getBytes("utf-8");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	        byte[] encrypted = cipher.doFinal(stringtosign.getBytes("utf-8"));
	        signature = Base64Util.encode(encrypted);
		} catch (Exception e) {
			System.out.println("error:"+e.getMessage());
		}
		return signature;
	}
	
	/**
	 * 通过accessToken获取用户信息
	 * @param accessToken
	 * @return
	 */
	public static String getUserInfoByAccesstoken(String accessToken){
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
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
        try{
        	response = closeableHttpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("访问失败");
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		httpPost.releaseConnection();
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	/**
	 * 设置请求头验证参数
	 * @param signature
	 * @param appName
	 * @return
	 */
	public static Map<String, String> setHttpHeard(String signature, String appName){
		Map<String, String> head = new HashMap<String, String>();
		head.put("signature", signature);
		head.put("apiname", appName);
		head.put("appid", ParseConfig.get("reindeer.huidao.appId"));
		return head;
	}
	
	/**
	 * CloseableHttpClient创建https请求，不验证证书
	 * @return
	 */
	public static CloseableHttpClient getHttpsClient() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);
        // 指定信任密钥存储对象和连接套接字工厂
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // 信任任何链接
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
        
        
        // 设置连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        // 构建客户端
        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }
}
