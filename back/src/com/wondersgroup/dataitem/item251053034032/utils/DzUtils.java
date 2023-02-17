package com.wondersgroup.dataitem.item251053034032.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;

import reindeer.base.utils.HttpClientUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;

import wfc.service.log.Log;
import wfc.service.util.StreamHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.dataitem.item251053034032.web.NewElectronicCertificateController;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selfapi.util.SSLClient;

@SuppressWarnings({ "deprecation", "unused" })
public class DzUtils {
	// private static String ipUrl = "http://10.81.16.19:8080/zzk";

	// private static String ipUrl = "http://10.81.16.12:8022/zzk";

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static String ipUrl = RdConfig.get("cert.zzk");

	private static DzUtils thisInstance = null;
	private static HttpClient httpClient = null;

	private DzUtils() {
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

	public static DzUtils getInstance() {
		if (thisInstance == null) {
			thisInstance = new DzUtils();
		}
		return thisInstance;
	}
	
	public static String getSessionId(String account, String password) {
		DzUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("account", account);
		obj.put("password", password);
		System.out.println("--用户名以及密码用于获取密钥--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/login.do";
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);

			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString());
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
				System.out.println("---返回的json字符串---" + str);
				str = JSONObject.parseObject(str).getString("sessionId");
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
	 * 根据办件编号（统一审批编码）获取证照基本信息
	 * @param sessionId
	 * @param itemCode
	 * @param businessCode
	 * @return
	 */
	public static String getCertBaseDataByBusinessCode(String sessionId, String machinePlace, 
			String machineMAC, String itemCode, String businessCode,String itemName) {
		DzUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--通过统一审批编号查询证照参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/getCertBaseDataByBusinessCode.do";
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);

			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString());
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的json字符串---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity());
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
	
	public static String getCertCatList(String sessionId, String machinePlace, String machineMAC,
			String itemName,String itemCode,String businessCode){
		DzUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--获取全部证照目录的参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/getCertCatList.do";
		HttpPost method = new HttpPost(url);
		try {
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
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的证照目录信息的json字符串---" + str);
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
		return str;
	}
	
	/**
	 * 通过证照目录编码的前半段(catMainCode)查询证照基本信息
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
	public static String getCertInfo(String type, String certNo,String catMainCode, 
			String sessionId,String machinePlace, String machineMAC,String itemName,
			String itemCode,String businessCode) {
		DzUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		String holderType = "";
		if ("0".equals(type)) {
			holderType = "居民身份证";
		} else if ("1".equals(type)) {
			holderType = "统一社会信用代码";
		}
		obj.put("holderType", holderType);
		obj.put("holderCode", certNo);
		if (StringUtils.isNotBlank(catMainCode)) {
			obj.put("catMainCode", catMainCode);
		}
		// obj.put("certCode", "");
		// obj.put("holderName", "");
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		Log.debug("--通过证照目录编码查询证照基本信息的参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/secQueryCertBaseData.do";// 安全用证地址
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
				str = EntityUtils.toString(result.getEntity());
				Log.debug("---返回的证照信息的json字符串---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity());
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
	 * 根据证照唯一编号(certuuid)获取证照基本信息
	 * @param sessionId
	 * @param certuuid
	 * @param machinePlace
	 * @param machineMAC
	 * @param itemName
	 * @param itemCode
	 * @param businessCode
	 * @return
	 */
	public static String getCertBaseData(String sessionId, String certuuid, 
			String machinePlace, String machineMAC,String itemName,
			String itemCode,String businessCode) {
		DzUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("certuuid", certuuid);
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		System.out.println("--根据证照唯一编号获取证照基本信息的参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/secQueryCertBaseData.do";
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
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的证照信息的json字符串---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity());
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

	public static List<String> showPic(String uuid, String page, String sessionId,String machinePlace, String machineMAC,
			String itemName, String itemCode, String businessCode) {
		DzUtils.getInstance();
		List<String> list = new ArrayList<String>();
		String pageCount = "0";
		String file = "";
		InputStream in = null;
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("certUuid", uuid);
		obj.put("n", page);
		
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);

		Log.debug("--获取证照图片的参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/getCertThumbnail.do";
		HttpPost method = new HttpPost(url);
		try {
			// String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse response = httpClient.execute(method);
			Log.debug("---访问结果---"
					+ response.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == 200) {
				pageCount = response.getFirstHeader("pageCount").getValue();
				Log.debug("缩略图集合中总共页数：" + pageCount);
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
				byte[] bytes = StreamHelper.toByteArray(in);
				in.close();
				file = new BASE64Encoder().encode(bytes);
			} else {
				System.out.println("请求失败");
				str = EntityUtils.toString(response.getEntity());
				Log.debug("---error---" + str);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
			method.releaseConnection();
		}
		list.add(pageCount);
		list.add(file);
		return list;
	}

	/**
	 * 派生证照
	 * 
	 * @param sessionId
	 * @param certUuid
	 * @param cause
	 * @return
	 */
	public static String deriveCert(String sessionId, String certUuid,
			String cause, String startDay, String endDay, String machinePlace, String machineMAC,
			String itemName, String itemCode, String businessCode) {
		DzUtils.getInstance();
		String str = "";
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
		
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		
		Log.debug("--获取材料派生证照信息参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/deriveCert.do";
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(30000) //连接超时时间
	                .setConnectionRequestTimeout(3000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(30000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回结果的json字符串---" + str);
				str = JSONObject.parseObject(str).getString("deriveUuid");
			} else {
				String resultstr = EntityUtils.toString(result.getEntity());
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

	public static byte[] getDeriveCertFile(String sessionId, String deriveUuid,
			String machinePlace, String machineMAC,String itemName, String itemCode, String businessCode) {
		DzUtils.getInstance();
		byte[] bytes = null;
		InputStream in = null;
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("sessionId", sessionId);
		obj.put("deriveUuid", deriveUuid);
		
		obj.put("orgName", machinePlace);
		obj.put("username", machineMAC);
		obj.put("itemName", itemName);
		obj.put("itemCode", itemCode);
		obj.put("businessCode", businessCode);
		
		Log.debug("--获取证照派生本的参数--" + obj.toJSONString());
		String url = ipUrl + "/cert/usage/getDeriveCertFile.do";
		HttpPost method = new HttpPost(url);
		try {
			// String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse response = httpClient.execute(method);
			System.out.println("---访问结果---"
					+ response.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
				bytes = StreamHelper.toByteArray(in);
				in.close();
			} else {
				System.out.println("请求失败");
				str = EntityUtils.toString(response.getEntity());
				Log.debug("---error---" + str);
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
	 * 根据证照内部编号获取证照照面数据
	 * @param sessionId
	 * @param certuuid
	 * @return
	 */
	public static String getCertOriginalData(String sessionId, String certuuid,
			String machinePlace,String machineMAC,String itemName, String itemCode, String businessCode) {
		DzUtils.getInstance();
        String body = "";
		JSONObject json = new JSONObject();
    	json.put("sessionId",sessionId);
    	json.put("certUuid",certuuid);
    	
    	json.put("orgName", machinePlace);
    	json.put("username", machineMAC);
    	json.put("itemName", itemName);
    	json.put("itemCode", itemCode);
    	json.put("businessCode", businessCode);
    	
        String url = ipUrl+"/cert/usage/getCertOriginalData.do";
        HttpPost httpPost = new HttpPost(url);
        
        StringEntity se = new StringEntity(json.toString(),"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8"));
        httpPost.setEntity(se);

        System.out.println("请求地址："+url);
        System.out.println("请求参数："+json.toString());
        
        // 指定报文头【Content-type】
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        // 执行请求操作，并拿到结果（同步阻塞）
//        CloseableHttpResponse response;
        HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
	        // 获取结果实体
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            // 按指定编码转换结果实体为String类型
	            body = EntityUtils.toString(entity, "utf-8");
	        }
	        EntityUtils.consume(entity);
		} catch (Exception e) {
			Log.debug("访问接口失败");
		} finally{
			// 释放链接
			httpPost.completed();
			httpPost.releaseConnection();
		}
        System.out.println("证照照面信息："+body);
        return body;
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
        
        Log.debug("身份证信息核验参数："+json.toString());
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
 			Log.debug("---访问结果---"
 					+ result.getStatusLine().getStatusCode());
 			/** 请求发送成功，并得到响应 **/
 			if (result.getStatusLine().getStatusCode() == 200) {
 				/** 读取服务器返回过来的json字符串数据 **/
 				body = EntityUtils.toString(result.getEntity());
 				Log.debug("---返回结果的json字符串---" + body);
 				body = JSONObject.parseObject(body).getString("idCard");
 			} else {
 				String resultstr = EntityUtils.toString(result.getEntity());
 				Log.debug("---error---" + resultstr);
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
	
	/**
	 * 查询被授权证照信息
	 * @param sessionId
	 * @param icensee_user_name
	 * @param licensee_user_idno
	 * @param licensee_user_phone
	 */
	public static String queryeles(String sessionId, String icensee_user_name, String licensee_user_idno, 
			String licensee_user_phone, String machinePlace,String machineMAC,
			String itemName, String itemCode, String businessCode){
		DzUtils.getInstance();
        String body = ""; 
        
        JSONObject json = new JSONObject();
    	json.put("sessionId",sessionId);
    	json.put("licensee_user_name",icensee_user_name);
    	json.put("licensee_user_idno",licensee_user_idno);
    	json.put("licensee_user_phone",licensee_user_phone);
    	
    	json.put("orgName", machinePlace);
    	json.put("username", machineMAC);
    	json.put("itemName", itemName);
    	json.put("itemCode", itemCode);
    	json.put("businessCode", businessCode);
    	
        String url = ipUrl+"/cert/usage/queryeles.do";
        HttpPost method = new HttpPost(url);
        
        System.out.println("查询被授权证照信息参数："+json.toString());
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
 				System.out.println("---被授权证照信息---" + body);
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
	
	/**
	 * 
	 * @param holderType 证件类型
	 * @param holderCode 证件号码
	 * @param catMainCode 证照目录编码
	 * @return
	 */
	public static String queryCertExistence(String holderType,
			String holderCode, String catMainCode, String sessionId,
			String machinePlace, String machineMAC, String itemName,
			String itemCode, String businessCode) {
		DzUtils.getInstance();
        String body = ""; 
		
		if ("0".equals(holderType)) {
			holderType = "居民身份证";
		} else if ("1".equals(holderType)) {
			holderType = "统一社会信用代码";
		}
		JSONObject json = new JSONObject();
		json.put("sessionId", sessionId);
		json.put("catMainCode", catMainCode);
		json.put("certCode", null);
		json.put("holderType", holderType);
		json.put("holderCode", holderCode);
		json.put("holderName", null);
		
    	json.put("orgName", machinePlace);
    	json.put("username", machineMAC);
    	json.put("itemName", itemName);
    	json.put("itemCode", itemCode);
    	json.put("businessCode", businessCode);
        
        String url = ipUrl+"/cert/usage/queryCertExistence.do";
        HttpPost method = new HttpPost(url);
        
        System.out.println("查询持证人所持有证照目录清单参数："+json.toString());
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
 				System.out.println("---持证人所持有证照目录清单---" + body);
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
	
	/**
	 * 打印证照图片，文件转换及合并处理
	 * png转DPF，pdf合并
	 * @param pageCount
	 * @param certUuid
	 * @param sessionId
	 * @param machinePlace
	 * @param machineMAC
	 * @param itemName
	 * @param itemCode
	 * @param businessCode
	 * @throws IOException 
	 */
	public static byte[] printFile(List<String> list, String certUuid,
			String sessionId, String machinePlace, String machineMAC,
			String itemName, String itemCode, String businessCode) throws IOException {
		// TODO Auto-generated method stub
		byte[] bytes = null;
		int pageCount = Integer.valueOf(list.get(0));
		String pngPath = NewElectronicCertificateController.class.getResource(
				"").getPath() + "template/";
		String pdfPath = NewElectronicCertificateController.class.getResource(
				"").getPath() + "template/";
		long time = System.currentTimeMillis();
		String reCertUuid = certUuid.replaceAll("\\/", "-");
		if(pageCount <= 2){
			pngPath += reCertUuid+"_"+time+".png";
			pdfPath += reCertUuid+"_"+time+".pdf";
			String fileStr = list.get(1);
			byte[] fileBytes = new BASE64Decoder().decodeBuffer(fileStr);
			FileUtil.getFileFromBytes(fileBytes, pngPath);
			File pdfFile = Png2PdfUtil.png2pdf(pngPath, pdfPath, 20f, 600f, 800f);
			File pnfFile = new File(pngPath);
			bytes = FileUtil.getBytesFromFile(pdfFile);
			pdfFile.delete();
			pnfFile.delete();
		} else {
			int remainder = pageCount%2;
			int quotient = pageCount/2;
			List<File> listFile = new ArrayList<File>();
			for(int i = 1;i<=quotient;i++){
				String page = (2*(i-1)+1)+","+(2*i);
				String png = pngPath+reCertUuid+"_"+time+"("+i+").png";
				String pdf = pdfPath+reCertUuid+"_"+time+"("+i+").pdf";
				List<String> listStr = showPic(certUuid, page, sessionId, machinePlace, machineMAC,itemName,itemCode,businessCode);
				String pngStr = listStr.get(1);
				if(StringUtils.isNotEmpty(pngStr)){
					byte[] fileBytes = new BASE64Decoder().decodeBuffer(listStr.get(1));
					FileUtil.getFileFromBytes(fileBytes, png);
					File pdfFile = Png2PdfUtil.png2pdf(png, pdf, 20f, 600f, 800f);
					listFile.add(pdfFile);
				}
			}
			if(1 == remainder){
				String page = Integer.toString(pageCount);
				String png = pngPath+reCertUuid+"_"+time+"("+pageCount+").png";
				String pdf = pdfPath+reCertUuid+"_"+time+"("+pageCount+").pdf";
				List<String> listStr = showPic(certUuid, page, sessionId, machinePlace, machineMAC,itemName,itemCode,businessCode);
				String pngStr = listStr.get(1);
				if(StringUtils.isNotEmpty(pngStr)){
					byte[] fileBytes = new BASE64Decoder().decodeBuffer(listStr.get(1));
					FileUtil.getFileFromBytes(fileBytes, png);
					File pdfFile = Png2PdfUtil.png2pdf(png, pdf, 200f, 600f, 400f);
					listFile.add(pdfFile);
				}
			}
			String mergePdfPath = pdfPath + reCertUuid+"_"+time+"_merge.pdf";
			File mergeFile = MergePdfUtil.mergePdf(listFile, mergePdfPath);
			System.out.println("合成后文件大小："+mergeFile.getTotalSpace());
			bytes = FileUtil.getBytesFromFile(mergeFile);
		}
		return bytes;
	}
	
	public static InfopubDeviceInfo getDefaultMachine(){
		InfopubDeviceInfoDao dao = new InfopubDeviceInfoDao();
		InfopubDeviceInfo deviceInfo = dao.getByMacId(RdConfig.get("reindeer.default.mac"));
		return deviceInfo;
	}
	
	public static void main(String[] args) {
	}
}
