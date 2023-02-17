package com.wondersgroup.sms.login.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

public class SSOUtil {
	
	private static final String SSO_URL = RdConfig.get("sso.url");
	
	public static String ssoValidate(String token, String enc){
		String str = "{}";
		
		CloseableHttpClient client = null;
		String url = SSO_URL+"?appName=ZZYX&token="+token+"&timeout="+300000+"&enc="+enc;
		if(url.startsWith("https://")){
			client= getHttpsClient();
		} else {
			client = HttpClients.createDefault();
		}
        HttpPost httpPost = new HttpPost(url);
		
        httpPost.setHeader("Content-type", "text/html;charset=utf-8");
        
        JSONObject obj = new JSONObject();
        obj.put("appName", "ZZYX");
        obj.put("token", token);
        obj.put("timeout", 300000);
        obj.put("enc", enc);
        
        // 设置参数到请求对象中
        StringEntity se = new StringEntity(obj.toString(),"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "text/html;charset=utf-8"));
        httpPost.setEntity(se);
        
        CloseableHttpResponse response = null;
        try{
            response = client.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
            	str = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug(e);
        	Log.debug("访问失败");
		} finally {
			try {
				if(response != null){
					// 释放链接
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        System.out.println(str);
		
		return str;
	}
	
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
