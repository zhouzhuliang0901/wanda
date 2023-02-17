package com.wondersgroup.common.utils;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

@SuppressWarnings("deprecation")
public class PooledHttpUitl {
	
	public static CloseableHttpClient closeableHttpClient;
	static {
		SSLContextBuilder builder = new SSLContextBuilder();
		SSLConnectionSocketFactory sslsf = null;
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			sslsf = new SSLConnectionSocketFactory(builder.build(),
					NoopHostnameVerifier.INSTANCE);
			// 配置同时支持 HTTP 和 HTPPS
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http",
							PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslsf).build();
			// 初始化连接管理器
			PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);
			/*
			 * 1、MaxtTotal是整个池子的大小； 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；
			 * 比如：MaxtTotal=400 DefaultMaxPerRoute=200
			 * 而我只连接到http://www.abc.com时，到这个主机的并发最多只有200,而不是400；
			 * 而我连接到http://www.bac.com 和 http://www.ccd.com时，
			 * 到每个主机的并发最多只有200；即加起来是400（但不能超过400），所以起作用的设置是DefaultMaxPerRoute。
			 */
			poolConnManager.setMaxTotal(3000);// 同时最多连接数
			poolConnManager.setDefaultMaxPerRoute(1500);// 设置最大路由
			// 初始化httpClient
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(15000)
					.setConnectionRequestTimeout(10000).setSocketTimeout(10000)
					.build();
			closeableHttpClient = HttpClients
					.custom()
					// 设置连接池管理
					.setConnectionManager(poolConnManager)
					.setDefaultRequestConfig(config)
					// 设置重试次数
					.setRetryHandler(
							new DefaultHttpRequestRetryHandler(2, false))
					// 设置共享
					.setConnectionManagerShared(true)
					.build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}
}
