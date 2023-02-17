package com.wondersgroup.dataitem.item259192115322.utils;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import reindeer.base.utils.HttpClientUtils;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.util.SSLClient;

@SuppressWarnings("deprecation")
public class GovUtil {
	
	// 测试
	private static String ipUrl = "http://jyh.beta.easttone.com:8010";

	private static GovUtil thisInstance = null;
	private static HttpClient httpClient = null;

	
	private GovUtil() {
		if(ipUrl.startsWith("https://")){
			PoolingClientConnectionManager pm = new PoolingClientConnectionManager();
			pm.setMaxTotal(200);
			pm.setDefaultMaxPerRoute(100);
			httpClient = new SSLClient(pm);
		} else {
			httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
		}
		httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
	}

	public static GovUtil getInstance() {
		if (thisInstance == null) {
			thisInstance = new GovUtil();
		}
		return thisInstance;
	}
	
	public static String login(String appKey, String appSecret){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("appKey", appKey);
		json.put("appSecret", appSecret);
		// 测试
		String url = ipUrl + "/api/auth/vclient/login";
		HttpPost method = new HttpPost(url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---返回的json字符串---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static String channelPage(String token, String modelId){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("displayFlag", "1");
		json.put("modelId", modelId);
//		json.put("orderBy", "");
		json.put("pageNo", 1);
		json.put("pageSize", Integer.MAX_VALUE/2);
//		json.put("parentId", "");
		json.put("recursion", false);
		// 测试
		String url = ipUrl +"/api/pack/channel/page";
		HttpPost method = new HttpPost(url);
		System.out.println("获取分页列表请求地址："+url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.addHeader("siteId", "0001");
			method.addHeader("token", token);
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			System.out.println("获取分页列表请求参数："+json.toString());
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---获取分页列表请求结果---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static String channelList(String token, String modelId, String parentId){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("displayFlag", "1");
		json.put("modelId", modelId);
		json.put("orderBy", "");
		json.put("parentId", parentId);
		json.put("recursion", false);
		// 测试
		String url = ipUrl + "/api/pack/channel/list";
		HttpPost method = new HttpPost(url);
		System.out.println("获取列表请求地址："+url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.addHeader("siteId", "0001");
			method.addHeader("token", token);
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			System.out.println("获取列表请求参数："+json.toString());
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---获取列表请求结果---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static String channelGet(String token, String id){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("id", id);
		// 测试
		String url = ipUrl + "/api/pack/channel/get";
		HttpPost method = new HttpPost(url);
		System.out.println("获取对象请求地址："+url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.addHeader("siteId", "0001");
			method.addHeader("token", token);
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			System.out.println("获取对象请求参数："+json.toString());
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---获取对象请求结结果---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static String channelTree(String token, String modelId, String parentId){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("displayFlag", "1");
		json.put("modelId", modelId);
		json.put("orderBy", "");
		json.put("parentId", parentId);
		json.put("recursion", false);
		// 测试
		String url = ipUrl + "/api/pack/content/channel/tree";
		HttpPost method = new HttpPost(url);
		System.out.println("获取栏目树请求地址："+url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.addHeader("siteId", "0001");
			method.addHeader("token", token);
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			System.out.println("获取栏目树请求参数："+json.toString());
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---获取栏目树请求结果---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static String contentPage(String token, String channelId, String modelId, String orderField, String order){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("channelId", channelId);
		json.put("masterStatus", "0");
		json.put("modelId", modelId);
		json.put("orderBy", orderField+"_"+order);
		json.put("pageNo", 1);
		json.put("pageSize", 500);
		json.put("priority", 0);
		json.put("recursion", false);
//		json.put("releaseStatus", "3");
//		json.put("reportStatus", "3");
//		json.put("", "");
//		json.put("", "");
//		json.put("", "");
		// 测试
		String url = ipUrl + "/api/pack/content/page";
		HttpPost method = new HttpPost(url);
		System.out.println("获取cms稿件列表请求地址："+url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.addHeader("siteId", "0001");
			method.addHeader("token", token);
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			System.out.println("获取cms稿件列表参数："+json.toString());
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---获取cms稿件列表请求结果---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static String contentGet(String token, String id){
		GovUtil.getInstance();
		String str = "";
		JSONObject json = new JSONObject();
		json.put("id", id);
		// 测试
		String url = ipUrl + "/api/pack/content/get";
		HttpPost method = new HttpPost(url);
		System.out.println("获取cms稿件详情请求地址："+url);
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .build();
		   method.setConfig(config);
		   
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			method.addHeader("siteId", "0001");
			method.addHeader("token", token);
			StringEntity se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			System.out.println("获取cms稿件详情请求参数："+json.toString());
			HttpResponse result = httpClient.execute(method);
			System.out.println("---访问结果---"+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---获取cms稿件详情请求结果---" + str);
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败------"+e);
		} finally {
			method.completed();
		}
		return str;
	}
}
