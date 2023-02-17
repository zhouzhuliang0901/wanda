package com.wondersgroup.selfapi.util;

import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import reindeer.base.utils.HttpClientUtils;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;
import wfc.service.util.StreamHelper;

@SuppressWarnings("deprecation")
public class GbECertUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static String ipUrl = RdConfig.get("cert.zzk");
//	private static String ipUrl = "http://zwdtcert.sh.gov.cn:8022/zzktest";

	private static GbECertUtil thisInstance = null;
	private static HttpClient httpClient = null;

	private GbECertUtil() {
		//创建Https请求的HttpClient
		if(RdConfig.get("cert.zzk").startsWith("https://")){
			PoolingClientConnectionManager pm = new PoolingClientConnectionManager();
			pm.setMaxTotal(200);
			pm.setDefaultMaxPerRoute(100);
			httpClient = new SSLClient(pm);
		} else {
			httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
		}
		httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
	}

	public static GbECertUtil getInstance() {
		if (thisInstance == null) {
			thisInstance = new GbECertUtil();
		}
		return thisInstance;
	}
	
	public static String getSessionId(String account, String password) {
		GbECertUtil.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("account", account);
		obj.put("password", password);
		Log.debug("--用户名以及密码用于获取密钥--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/login.do";
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);

			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---返回的json字符串---" + str);
				str = JSONObject.parseObject(str).getString("sessionId");
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.1.获取国办证照类型清单
	 * @return
	 */
	public static String getCertificateType(String sessionId,
			String machinePlace, String machineMAC, String itemName,
			String itemCode, String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--国办证照类型清单参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/gb/getCertificateType.do";
		System.out.println("--国办证照类型清单地址--" + url);
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---国办证照类型清单---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.2.检索国办证照
	 * @param sessionId
	 * @param CertificateHolderCode 持证人编号
	 * @param UseFor 用途
	 * @return
	 */
	public static String retrieval(String sessionId,
			String certificateHolderCode, String certificateType,
			String useFor, String machinePlace, String machineMAC,
			String itemName, String itemCode, String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("CertificateHolderCode", certificateHolderCode);
		obj.put("CertificateType", certificateType);
		obj.put("UseFor", useFor);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		Log.debug("--检索国办证照参数 --" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/gb/secRetrieval.do";
		System.out.println("--检索国办证照地址--" + url);
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			Log.debug("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---检索国办证照---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.3.基于证照标识获取国办证照信息
	 * @param sessionId
	 * @param CertificateID 国办证照的唯一编号
	 * @param UseFor 用途
	 * @return
	 */
	public static String getCertificateDataByCertificateID(String sessionId,
			String certificateID, String useFor, String machinePlace,
			String machineMAC, String itemName, String itemCode,
			String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("CertificateID", certificateID);
		obj.put("UseFor", useFor);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		Log.debug("--基于证照标识获取国办证照信息参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/gb/getCertificateDataByCertificateID.do";
		System.out.println("--基于证照标识获取国办证照信息地址--" + url);
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			Log.debug("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---基于证照标识获取国办证照信息---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.4.基于持证主体获取国办证照信息
	 * @param sessionId
	 * @param CertificateType 证照类型中文名称
	 * @param CertificateNumber 证照照面编号
	 * @param CertificateHolderCode 持证人编号
	 * @param UseFor 用途
	 * @return
	 */
	public static String getCertificateDataByHolder(String sessionId,
			String certificateType, String certificateNumber,
			String certificateHolderCode, String useFor, String machinePlace,
			String machineMAC, String itemName, String itemCode,
			String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("CertificateType", certificateType);
		obj.put("CertificateNumber", certificateNumber);
		obj.put("CertificateHolderCode", certificateHolderCode);
		obj.put("UseFor", useFor);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--基于持证主体获取国办证照信息参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/gb/secGetCertificateDataByHolder.do";
		System.out.println("--基于持证主体获取国办证照信息地址--" + url);
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---基于持证主体获取国办证照信息---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.5.基于证照标识下载国办证照文件
	 * @param sessionId
	 * @param CertificateID 国办证照的唯一编号
	 * @param UseFor 用途
	 * @return
	 */
	public static String getCertificateFileByCertificateID(String sessionId,
			String certificateID, String useFor, String machinePlace,
			String machineMAC, String itemName, String itemCode,
			String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("CertificateID", certificateID);
		obj.put("UseFor", useFor);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		Log.debug("--基于证照标识下载国办证照文件参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/gb/getCertificateFileByCertificateID.do";
		System.out.println("--基于证照标识下载国办证照文件地址--" + url);
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			Log.debug("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---基于证照标识下载国办证照文件---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				Log.debug("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.6.基于持证主体下载国办证照文件
	 * @param sessionId
	 * @param CertificateType 证照类型中文名称
	 * @param CertificateNumber 证照照面编号
	 * @param CertificateHolderCode 持证人编号
	 * @param UseFor 用途
	 * @return
	 */
	public static String getCertificateFileByHolder(String sessionId,
			String certificateType, String certificateNumber,
			String certificateHolderCode, String useFor, String machinePlace,
			String machineMAC, String itemName, String itemCode,
			String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("CertificateType", certificateType);
		obj.put("CertificateNumber", certificateNumber);
		obj.put("CertificateHolderCode", certificateHolderCode);
		obj.put("UseFor", useFor);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--基于持证主体下载国办证照文件参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/gb/secGetCertificateFileByHolder.do";
		System.out.println("--基于持证主体下载国办证照文件地址--" + url);
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---基于持证主体下载国办证照文件---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 3.7.国办证照文件下载
	 * @param sessionId
	 * @param url 下载证照的链接
	 * @param fileFormat 证照文件格式
	 * @return
	 */
	public static byte[] getCertFile(String sessionId, String url,
			String fileFormat, String machinePlace, String machineMAC,
			String itemName, String itemCode, String businessCode){
		GbECertUtil.getInstance();
		byte[] bytes = null;
		InputStream in = null;
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("url", url);
		obj.put("fileFormat", fileFormat);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--国办证照文件下载参数--" + obj.toJSONString());
		String httpUrl = ipUrl + "/cert/usage/gb/getCertFile.do";
		System.out.println("--国办证照文件下载地址--" + httpUrl);
		HttpPost method = new HttpPost(httpUrl);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = result.getEntity();
				in = entity.getContent();
				bytes = StreamHelper.toByteArray(in);
				in.close();
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return bytes;
	}
	
	/**
	 * 3.8.国办证照缩略图下载（文件方式）
	 * @param sessionId
	 * @param certificateType 证照类型中文名称
	 * @param certificateNumber 证照照面编号
	 * @param url 下载证照的链接
	 * @param fileFormat 证照文件格式
	 * @param watermark 图片上水印
	 * @return
	 */
	public static byte[] getThumbnail(String sessionId, String certificateType,
			String certificateNumber, String url, String fileFormat,
			String watermark, String machinePlace, String machineMAC,
			String itemName, String itemCode, String businessCode){
		GbECertUtil.getInstance();
		byte[] bytes = null;
		InputStream in = null;
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("certificateType", certificateType);
		obj.put("certificateNumber", certificateNumber);
		obj.put("url", url);
		obj.put("fileFormat", fileFormat);
		obj.put("watermark", watermark);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--国办证照缩略图下载（文件方式）参数--" + obj.toJSONString());
		String httpUrl = ipUrl + "/cert/usage/gb/getThumbnail.do";
		System.out.println("--国办证照缩略图下载（文件方式）地址--" + httpUrl);
		HttpPost method = new HttpPost(httpUrl);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = result.getEntity();
				in = entity.getContent();
				bytes = StreamHelper.toByteArray(in);
				in.close();
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return bytes;
	}
	
	/**
	 * 3.9.国办证照缩略图下载（JSON方式）
	 * @param sessionId
	 * @param certificateType 证照类型中文名称
	 * @param certificateNumber 证照照面编号
	 * @param url 下载证照的链接
	 * @param fileFormat 证照文件格式
	 * @param watermark 图片上水印
	 * @return
	 */
	public static String getThumbnails(String sessionId,
			String certificateType, String certificateNumber, String url,
			String fileFormat, String watermark, String machinePlace,
			String machineMAC, String itemName, String itemCode,
			String businessCode){
		GbECertUtil.getInstance();
		String str = "";
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("certificateType", certificateType);
		obj.put("certificateNumber", certificateNumber);
		obj.put("url", url);
		obj.put("fileFormat", fileFormat);
		obj.put("watermark", watermark);
		//五要素
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--国办证照缩略图下载（JSON方式）参数--" + obj.toJSONString());
		String httpUrl = ipUrl + "/cert/usage/gb/getThumbnails.do";
		System.out.println("--国办证照缩略图下载（JSON方式）地址--" + httpUrl);
		HttpPost method = new HttpPost(httpUrl);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---国办证照缩略图下载（JSON方式）---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		return str;
	}
	
	/**
	 * 身份证信息核验
	 * @return
	 */
	public static String checkIDCard(String sessionId, String name,String id, 
			String startDay, String endDay, String machinePlace,String machineMAC,
			String itemName, String itemCode, String businessCode){
		DzUtils.getInstance();
        String body = ""; 
        
        JSONObject json = new JSONObject();
    	json.put("sessionId",sessionId);
    	json.put("name",name);
    	json.put("id",id);
    	json.put("startDay",startDay);
    	json.put("endDay",endDay);
    	
    	json.put("orgName", machinePlace);
    	json.put("username", machineMAC);
    	json.put("itemName", itemName);
    	json.put("itemCode", itemCode);
    	json.put("businessCode", businessCode);
    	
        String url = ipUrl+"/cert/usage/checkIDCard.do";
        HttpPost method = new HttpPost(url);
        
        System.out.println("身份证信息核验参数："+json.toString());
        try {
 		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
 	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
 	                .setSocketTimeout(3000) //数据传输的超时时间
 	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
 	                .build();
 		   method.setConfig(config);

 			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
 			StringEntity se = new StringEntity(json.toJSONString(), "UTF-8");
 			se.setContentType("text/json");
 			se.setContentEncoding("UTF-8");
 			method.setEntity(se);
 			HttpResponse result = httpClient.execute(method);
 			System.out.println("---访问结果---"
 					+ result.getStatusLine().getStatusCode());
 			/** 请求发送成功，并得到响应 **/
 			if (result.getStatusLine().getStatusCode() == 200) {
 				/** 读取服务器返回过来的json字符串数据 **/
 				body = EntityUtils.toString(result.getEntity());
 				System.out.println("---返回结果的json字符串---" + body);
 				body = JSONObject.parseObject(body).getString("idCard");
 			} else {
 				String resultstr = EntityUtils.toString(result.getEntity());
 				System.out.println("---error---" + resultstr);
 			}
 		} catch (Exception e) {
 			Log.debug("访问接口失败");
 			Log.debug(e);
 		} finally {
 			method.completed();
 			method.releaseConnection();
 		}
        return body;
	}
	
	public static void main(String[] args) {
	}
}
