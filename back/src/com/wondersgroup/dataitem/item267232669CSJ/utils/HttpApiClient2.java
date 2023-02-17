package com.wondersgroup.dataitem.item267232669CSJ.utils;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpApiClient2 {

	public static String apiResData(String url, JSONObject jsonObject) {
		// 生产
		url = "http://10.81.16.161:8888/ac-product-api" + url;
		// 测试
//		url = "http://117.184.226.70:8022/ac-product-api" + url;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000)
				.setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(jsonObject.toString(),
					ContentType.create("application/json", "utf-8")));
			HttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return "post failure :caused by-->" + e.getMessage().toString();
		}
	}

	public static String getAccessToken(String client_id, String client_secret) {
		// 生产
		String url = "http://10.81.16.161:8888/ac-product-api/oauth2/getToken";
		// 测试
		// String url =
		// "http://117.184.226.70:8022/ac-product-api/oauth2/getToken";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			httpPost.setEntity(new StringEntity("clientId=" + client_id
					+ "&clientSecret=" + client_secret));
			HttpResponse response = httpClient.execute(httpPost);
			JSONObject jsonObject = JSONObject.fromObject(EntityUtils
					.toString(response.getEntity()));
			return jsonObject.getString("access_token");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
	}
}
