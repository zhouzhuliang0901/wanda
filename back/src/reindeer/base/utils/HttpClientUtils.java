/**
 * Project: Coral
 * Source file: HttpClientUtils.java
 * Create At 2013-10-16 下午03:19:27
 * Create By 龚云
 */
package reindeer.base.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

/**
 * @author 龚云
 * 
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {

	public static HttpClient newPooledHttpClient(int maxTotal, int maxPerRoute) {
		return newPooledHttpClient(maxTotal, maxPerRoute, false, null, -1);
	}

	public static HttpClient newPooledHttpClient(int maxTotal, int maxPerRoute,
			boolean isProxy, String proxyHost, int proxyPort) {
		PoolingClientConnectionManager pm = new PoolingClientConnectionManager();
		pm.setMaxTotal(maxTotal);
		pm.setDefaultMaxPerRoute(maxPerRoute);
		HttpClient client = new DefaultHttpClient(pm);
		if (isProxy) {
			if (proxyPort == -1 || proxyHost.isEmpty())
				throw new IllegalArgumentException(
						"Proxy port or proxy host is illegal. host("
								+ proxyHost + "),port(" + proxyPort + ")");
			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);
		}
		return client;
	}

}
