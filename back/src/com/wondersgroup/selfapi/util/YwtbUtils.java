package com.wondersgroup.selfapi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.wondersgroup.selfapi.bean.AppApplyInfo;

import wfc.service.config.Config;
import wfc.service.log.Log;

@SuppressWarnings("deprecation")
public class YwtbUtils {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static String urlStr = Config.get("wfc.ywtb.url") == null ? "http://ywtb.sh.gov.cn:18018/ac-product-api"
			: Config.get("wfc.ywtb.url");
	// admin
	private static String username = "xhapply";

	private static String password = "xhapply";

	private static HttpClient httpClient = new DefaultHttpClient();
	
	public static AppApplyInfo getApplyInfoByStApplyNo(String stApplyNo) {
		if (StringUtils.isBlank(stApplyNo)) {
			return null;
		}
		String str = getTocken("", "");
		String access_token = JSONObject.fromObject(str).getString(
				"access_token");
		System.out.println("access_token:" + access_token);
		AppApplyInfo info = null;

		String result = getApplyBase(access_token, stApplyNo);
		com.alibaba.fastjson.JSONObject j = com.alibaba.fastjson.JSONObject
				.parseObject(result);
		if ("200".equals(j.getString("code"))) {
			if ("没有匹配的办件信息".equals(j.getString("msg"))
					|| "".equals(j.getString("data"))
					|| j.getString("data") == null) {
				return null;
			}
			com.alibaba.fastjson.JSONObject obj = j.getJSONArray("data")
					.getJSONObject(0);
			info = new AppApplyInfo();
			info.setStApplyNo(stApplyNo);
			info.setStApplyStr(obj.getString("sbTime"));
			info.setStFinalState(obj.getString("status"));
			info.setStFinalState(obj.getString("onLineStatus"));
			info.setStFinishStr(obj.getString("opTime"));
			info.setStItemName(obj.getString("itemName"));
			info.setStName(obj.getString("username"));
			info.setStOrganName(obj.getString("opDepartName"));
			info.setStUnit(obj.getString("targetName"));
		}
		return info;
	}

	// 获取秘钥（通过用户名和密码）
	public static String getTocken(String userId, String pwd) {
		// XhZzsbUtils.getInstance();
		String message = "";
		HttpPost httpPost = new HttpPost(urlStr + "/oauth2/getToken");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("clientId", username));
		nvps.add(new BasicNameValuePair("clientSecret", password));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			// Log.debug("--" + response.getStatusLine().getStatusCode());
			System.out.println("---返回结果---"
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				message = EntityUtils.toString(entity, "utf-8");
				// Log.debug("返回的数据结果:" + message);
				System.out.println("返回的秘钥数据结果:" + message);
			} else {
				Log.debug("请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
			// httpPost.releaseConnection();
		}
		return message;
	}

	/**
	 * 4.4 获取办件过程完整信息
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getApplyProcess(String access_token, String stApplyNo) {
		// XhZzsbUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("accessToken", access_token);
		obj.put("applyNo", stApplyNo);
		System.out.println("参数:" + obj.toString());

		String url = urlStr + "/uapply/getApplyProcess";
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "utf-8");
			se.setContentType("text/json");
			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---返回结果---"
					+ result.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的json字符串---" + str);
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}

	/**
	 * 3.6.1获取办件基本信息
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getApplyBase(String access_token, String stApplyNo) {
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("accessToken", access_token);
		obj.put("applyNo", stApplyNo);
		System.out.println("参数:" + obj.toString());

		String url = urlStr + "/uapply/getApplyBase";
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "utf-8");
			se.setContentType("text/json");
			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---返回结果---"
					+ result.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的json字符串---" + str);
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}

	public static void main(String[] args) {

		// 获取密钥
		// String str = getTocken("", "");
		// String access_token = JSONObject.fromObject(str).getString(
		// "access_token");
		// System.out.println(access_token);
		getApplyInfoByStApplyNo("751136119600123");

	}

}
