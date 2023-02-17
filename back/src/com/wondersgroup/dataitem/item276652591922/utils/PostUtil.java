package com.wondersgroup.dataitem.item276652591922.utils;

import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.druid.util.StringUtils;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.selfapi.util.SSLClient;

import reindeer.base.utils.HttpClientUtils;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

@SuppressWarnings("deprecation")
public class PostUtil {
	
	private static final String APP_ID = RdConfig.get("reindeer.mz.app.id");
	private static final String APP_SECRET = RdConfig.get("reindeer.mz.app.secret");
	private static PostUtil thisInstance = null;
	private static HttpClient httpClient = null;
	
	private PostUtil(){
		if(RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment")).startsWith("https://")){
			PoolingClientConnectionManager pm = new PoolingClientConnectionManager();
			pm.setMaxTotal(200);
			pm.setDefaultMaxPerRoute(100);
			httpClient = new SSLClient(pm);
		} else {
			httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
		}
	}
	
	public static PostUtil getInstance() {
		if (thisInstance == null) {
			thisInstance = new PostUtil();
		}
		return thisInstance;
	}
	
	/**
	 * 民政服务通过中台请求数据的通用请求
	 * @return
	 */
	public static String send(String paramaJson, Map<String, String> head, 
			String contentType){
		// 摘要签名
		long timestamp = System.currentTimeMillis()/1000;
		String originString = APP_ID+ APP_SECRET+ timestamp+ paramaJson;
		String sign = DigestUtils.md5Hex(originString);
		sign = sign.toUpperCase();
		
		// 业务数据AES加密
		String data = "";
		try {
			String key = APP_SECRET.substring(0, 16);
			String iv = APP_SECRET.substring(16);
			data = AES_CBCUtil.encode(key, iv, paramaJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("app_id", APP_ID);
		json.put("timestamp", timestamp);
		json.put("sign", sign);
		json.put("data", data);
		
		System.out.println("社区事务中心民政事项-提交申请信息加密参数："+json.toString());
		String result = sendPost(head, json.toString());
		
		return result;
	}
	
	/**
	 * 处理返回结果
	 * @param result
	 * @return
	 */
	public static String dealResult(String result) {
		String str = "";
		JSONObject json = new JSONObject();
		try{
			json = JSONObject.fromObject(result);
			String code = json.optString("code");
			if(StringUtils.isEmpty(code)){
				json.put("code", "500");
				json.put("code", "系统内部异常");
			} else if("0".equals(code)){
				String data = json.optString("data");
				try {
					String key = APP_SECRET.substring(0, 16);
					String iv = APP_SECRET.substring(16);
					data = AES_CBCUtil.decode(key, iv, data);
					json.put("data", data);
				} catch (Exception e) {
					json.put("code", "-105");
					json.put("msg", "返回业务数据解密失败");
					json.put("sign", null);
					json.put("data", null);
					e.printStackTrace();
				}
				str = json.toString();
			} else {
				str = result;
			}
		} catch (Exception e) {
			json.put("code", "-1");
			json.put("msg", "返回业务数据异常");
			json.put("sign", null);
			json.put("data", null);
			str = json.toString();
			Log.debug(e);
		}
		return str;
	}
	
	/**
	 * 社区受理事项接口用HttpClient执行请求
	 * @param head
	 * @return
	 */
	private static String sendPost(Map<String, String> head, String paramaJson){
		getInstance();
		String str = "";
		
		HttpPost method = new HttpPost(RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment")));
		try {
		   RequestConfig config = RequestConfig.custom().setConnectTimeout(3000) //连接超时时间
	                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
	                .setSocketTimeout(3000) //数据传输的超时时间
	                .setStaleConnectionCheckEnabled(true) //提交请求前测试连接是否可用
	                .build();
		   method.setConfig(config);
		   
	        for (Map.Entry<String, String> entry : head.entrySet()) {
	            System.out.println(entry.getKey() + "：" + entry.getValue());
	            method.addHeader(entry.getKey() , entry.getValue());
	        }
			method.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8");
			StringEntity se = new StringEntity(paramaJson);
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
			} else {
				String resultstr = EntityUtils.toString(result.getEntity(),"utf-8");
				System.out.println("---error---" + resultstr);
			}
		} catch (Exception e) {
			Log.debug("访问接口失败");
			Log.debug(e);
		} finally {
			method.completed();
		}
		return str;
	}
	
	public static void main(String[] args) {
		JSONObject param = new JSONObject();
		param.put("idtype", "1");
		param.put("idno", "429004199312101138");
		
		String signature = HttpUtil.getSignature("4196603f-206d-4694-b4de-d513b5e1a82a");
        Map<String, String> head = HttpUtil.setHttpHeard(signature,"4196603f-206d-4694-b4de-d513b5e1a82a");
        send(param.toString(), head, "");
	}
}
