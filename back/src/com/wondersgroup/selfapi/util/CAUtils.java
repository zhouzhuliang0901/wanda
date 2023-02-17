package com.wondersgroup.selfapi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class CAUtils {

	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	public static String send(String ts, String site_code, String appo_num) {
		ts = System.currentTimeMillis() / 1000 + "";
		String sendResult = "";
		Map<String, String> paramMaps = new HashMap<String, String>();
		paramMaps.put("ts", ts);
		// paramMaps.put("appkey", Config.get("pt.ca.appkey"));
		paramMaps.put("site_code", site_code);
		paramMaps.put("appo_num", appo_num);
		String signature = WeiXinUtils.signatureGenerator(paramMaps, Config
				.get("wfc.ca.secretkey"));
		Log.debug("签名signature:" + signature);
		// post请求返回结果
		// 发送的json字符串的数据
		JSONObject jsonResult = null;
		String json = "{\"appkey\":\"" + Config.get("wfc.ca.appkey")
				+ "\",\"ts\":\"" + ts + "\",\"signature\":\"" + signature
				+ "\",\"site_code\":\"" + site_code + "\",\"appo_num\":\""
				+ appo_num + "\"}";
		Log.debug("-----------发送json字符串--------------" + json);
		try {
			// String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
			String url = Config.get("wfc.ca.cancelReservationurl");
			// "http://sheca.app-iot.com/yuyue/api/appointment/verify"
			HttpPost method = new HttpPost(url);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.setHeader("Accept", "application/json");
			StringEntity se = new StringEntity(json);
			// se.setContentType("text/json");
			// se.setContentEncoding(new
			// BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			Log.debug("---访问结果---" + result.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					Log.debug("---返回的json字符串---" + str);
					/** 把json字符串转换成json对象 **/
					// 1
					jsonResult = JSONObject.parseObject(str);
					sendResult = jsonResult.getJSONObject("status").get("code")
							.toString();
					Log.debug("返回的数据：" + sendResult);
				} catch (Exception e) {

				}
			} else {
				String str = "";
				str = EntityUtils.toString(result.getEntity());
				Log.debug("---error---" + str);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		}
		return sendResult;
	}

	public static String sendCancelPTReservation(String ts, String site_code,
			String appo_num) {
		Log.debug("site_code:" + site_code + " appo_num:" + appo_num);
		String message = "";
		HttpPost httpPost = new HttpPost(Config
				.get("wfc.ca.cancelReservationurl"));
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("site_code", site_code));
		nvps.add(new BasicNameValuePair("appo_num", appo_num));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				message = EntityUtils.toString(entity, "utf-8");
				Log.debug("CA证书返回的数据结果:" + message);
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
		String ts = System.currentTimeMillis() / 1000 + "";
		// String ts = (System.currentTimeMillis() / 1000-1000*60*60*2) + "";
		System.out.println(ts);
		send(ts, "PT", "PT596704");
	}

}
