package com.wondersgroup.publicService.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import wfc.service.log.Log;
import wfc.service.util.StreamHelper;

import com.wondersgroup.common.utils.PooledHttpUitl;
import com.wondersgroup.ext.ParseConfig;

public class ElectronicCertificateUtil {
	
	private static String ipUrl = ParseConfig.get("renideer.zzk");
	
	public static String getSessionId() {
		String sessionId = "";
		JSONObject obj = new JSONObject();
		obj.put("account", ParseConfig.get("renideer.zzk.account"));
		obj.put("password", ParseConfig.get("renideer.zzk.password"));
		Log.debug("--用户名以及密码用于获取密钥--" + obj.toString());
		String url = ipUrl + "/cert/usage/login.do";
		HttpPost method = new HttpPost(url);
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse response = null;
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			response = client.execute(method);
			Log.debug("---访问结果---" + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				String str = EntityUtils
						.toString(response.getEntity(), "utf-8");
				Log.debug("---返回的json字符串---" + str);
				sessionId = JSONObject.fromObject(str).optString("sessionId");
			} else {
				String resultstr = EntityUtils.toString(response.getEntity(),
						"utf-8");
				Log.debug("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败：" + e);
		} finally {
			try {
				if (response != null) {
					// 释放链接
					method.releaseConnection();
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sessionId;
	}
	
	/**
	 * 查询证照基本信息
	 * @param type
	 * @param certNo
	 * @param catMainCode
	 * @param sessionId
	 * @param machinePlace
	 * @param machineMAC
	 * @param itemName
	 * @param itemCode
	 * @param businessCode
	 * @return
	 */
	public static String getCertInfo(String type, String holderCode,
			String catMainCode, String sessionId, String orgName,
			String username, String itemName, String itemCode,
			String businessCode) {
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		String holderType = "";
		if ("0".equals(type)) {
			holderType = "居民身份证";
		} else if ("1".equals(type)) {
			holderType = "统一社会信用代码";
		}
		obj.put("holderType", holderType);
		obj.put("holderCode", holderCode);
		if (StringUtils.isNotBlank(catMainCode)) {
			obj.put("catMainCode", catMainCode);
		}
		obj.put("orgName", orgName);
		obj.put("username", username);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--通过证照目录编码查询证照基本信息的参数--" + obj.toString());
		String url = ipUrl + "/cert/usage/secQueryCertBaseData.do";// 安全用证地址
		HttpPost method = new HttpPost(url);
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse response = null;
		try {

			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			response = client.execute(method);
			Log.debug("---访问结果---" + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				str = EntityUtils.toString(response.getEntity(), "utf-8");
				Log.debug("---返回的证照信息的json字符串---" + str);
			} else {
				String resultstr = EntityUtils.toString(response.getEntity(),
						"utf-8");
				Log.debug("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败：" + e);
		} finally {
			try {
				if (response != null) {
					// 释放链接
					method.releaseConnection();
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * 获取证照缩略图
	 * @param uuid
	 * @param sessionId
	 * @param machinePlace
	 * @param machineMAC
	 * @param itemName
	 * @param itemCode
	 * @param businessCode
	 * @return
	 */
	public static byte[] showPic(String certUuid, String sessionId,String orgName, String username,
			String itemName, String itemCode, String businessCode) {
		byte[] bytes = null;
		InputStream in = null;
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("certUuid", certUuid);
		obj.put("n", "s");
		// obj.put("n", "1-n1");

		obj.put("orgName", orgName);
		obj.put("username", username);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);

		Log.debug("--获取证照图片的参数--" + obj.toString());
		String url = ipUrl + "/cert/usage/getCertThumbnail.do";
		HttpPost method = new HttpPost(url);
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse response = null;
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			response = client.execute(method);
			Log.debug("---访问结果---" + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
				bytes = StreamHelper.toByteArray(in);
				in.close();
			} else {
				str = EntityUtils.toString(response.getEntity(), "utf-8");
				Log.debug("---error---" + str);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败：" + e);
		} finally {
			try {
				if (response != null) {
					// 释放链接
					method.releaseConnection();
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
	
	/**
	 * 派生证照
	 * 
	 * @param sessionId
	 * @param certUuid
	 * @param cause
	 * @return
	 */
	public static String deriveCert(String sessionId, String certUuid, String cause, String startDay, String endDay, 
			String orgName, String username, String itemName, String itemCode, String businessCode) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(cause)) {
			cause = "自助终端打印";
		}
		if (StringUtils.isBlank(startDay)) {
			startDay = sdf.format(new Date());
		}
		if (StringUtils.isBlank(endDay)) {
			endDay = sdf.format(new Date());
		}
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("certUuid", certUuid);
		obj.put("cause", cause);
		obj.put("startDay", startDay);
		obj.put("endDay", endDay);
		
		obj.put("orgName", orgName);
		obj.put("username", username);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		
		Log.debug("--获取材料派生证照信息参数--" + obj.toString());
		String url = ipUrl + "/cert/usage/deriveCert.do";
		HttpPost method = new HttpPost(url);
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse response = null;
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			response = client.execute(method);
			Log.debug("---访问结果---"
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				str = EntityUtils.toString(response.getEntity(), "utf-8");
				Log.debug("---返回结果的json字符串---" + str);
				str = JSONObject.fromObject(str).optString("deriveUuid");
			} else {
				String resultstr = EntityUtils.toString(response.getEntity(), "utf-8");
				Log.debug("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败："+e);
		} finally {
			try {
				if (response != null) {
					// 释放链接
					method.releaseConnection();
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * 获取派生照文件
	 * @param sessionId
	 * @param deriveUuid
	 * @param machinePlace
	 * @param machineMAC
	 * @param itemName
	 * @param itemCode
	 * @param businessCode
	 * @return
	 */
	public static byte[] getDeriveCertFile(String sessionId, String deriveUuid,
			String machinePlace, String machineMAC,String itemName, String itemCode, String businessCode) {
		byte[] bytes = null;
		InputStream in = null;
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("deriveUuid", deriveUuid);
		
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		
		Log.debug("--获取证照派生本的参数--" + obj.toString());
		String url = ipUrl + "/cert/usage/getDeriveCertFile.do";
		HttpPost method = new HttpPost(url);
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse response = null;
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			response = client.execute(method);
			Log.debug("---访问结果---" + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
				bytes = StreamHelper.toByteArray(in);
				in.close();
			} else {
				str = EntityUtils.toString(response.getEntity());
				Log.debug("---error---" + str);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败："+e);
		} finally {
			try {
				if (response != null) {
					// 释放链接
					method.releaseConnection();
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
}
