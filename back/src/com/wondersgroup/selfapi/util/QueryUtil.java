package com.wondersgroup.selfapi.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class QueryUtil {
	
	private static HttpClient httpClient = new DefaultHttpClient();

	// 获取办件信息
	public static String getQueryInfo(String name, String stApplyNo) {
		Log.debug("name:" + name);
		try {
			name = new String(name.getBytes("utf-8"), "iso8859-1");
		} catch (Exception e1) {
			Log.debug(e1);
		}
		Log.debug("name:" + name);
		String message = "";
		HttpPost httpPost = new HttpPost(Config.get("queryOnlineUrl"));
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", name));
		nvps.add(new BasicNameValuePair("stApplyNo", stApplyNo));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				message = EntityUtils.toString(entity, "utf-8");
				String result = JSONObject.parseObject(message).getString(
						"result");
				Log.debug("result" + result);
				if ("0".equals(result)) {
					message = "0";
				}
				Log.debug("网厅返回的数据结果:" + message);
			} else {
				Log.debug("请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// httpPost.completed();
			httpPost.releaseConnection();
		}
		return message;
	}

	public static void main(String[] args) {
		String str = QueryUtil.getQueryInfo("ceshi062902", "061534316000002");
		System.out.println(str);
	}

}
