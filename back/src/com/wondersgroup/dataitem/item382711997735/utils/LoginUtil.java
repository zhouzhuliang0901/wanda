package com.wondersgroup.dataitem.item382711997735.utils;

import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PooledHttpUitl;

public class LoginUtil {

	public static String loginByCode(String qrCode) {
		String str = "";
		CloseableHttpClient closeableHttpClient = PooledHttpUitl
				.closeableHttpClient;
		String url = RdConfig.get("cert.zzk")+"/gb/csj/zzj/verifyqrcode.do";// product
//		String url = "http://zwdtcert.sh.gov.cn:8022/zzktest/gb/csj/zzj/verifyqrcode.do";// test
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-type", "application/json;charset=utf-8");
		JSONObject param = new JSONObject();
		JSONObject head = new JSONObject();
		JSONObject data = new JSONObject();
		head.put("accountId", "zhzzzd-zzdy");
		head.put("accessToken", "H3WzHL6jcBc");
//		head.put("accountId", "test");
//		head.put("accessToken", "abcd1234");
		data.put("qrcode", qrCode);
		param.put("head", head);
		param.put("data", data);
		Log.debug("长三角二维码登录参数："+param.toString());
		StringEntity se = new StringEntity(param.toString(), "utf-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json;charset=utf-8"));
		httpPost.setEntity(se);
		CloseableHttpResponse response = null;
		try {
			response = closeableHttpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				str = EntityUtils.toString(entity, "utf-8");
				Log.debug("长三角二维码登录："+str);
			}
		} catch (Exception e) {
			Log.debug("访问失败：" + e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	public static String getAuthorization() {
		String accessToken = "";
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "4654ff20-f689-11eb-ab32-334bd9492b18";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "4654ff20-f689-11eb-ab32-334bd9492b18";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject param = new JSONObject();
		// product
		param.put("appId", "85c783ba82aa4b5b9c6ed70f7d5c3e67");
		param.put("appSecret", "EADE40AED930431C98FAC987AECC1177");
		// test
//		param.put("appId", "e56b243185c34b40b731bf4210597a4a");
//		param.put("appSecret", "E99AC85798FA4424B6F2D66D5FECA649");
		
		String postResult = HttpUtil.doPost(head,param.toString(),"application/json;charset=utf-8");
		try{
			JSONObject jsonResult = JSONObject.fromObject(postResult);
			int status = jsonResult.optInt("status");
			if(200 == status){
				accessToken = jsonResult.optJSONObject("result").optString("accessToken");
			}
		} catch (Exception e) {
			Log.debug("CA获取访问令牌失败："+e);
		}
		return accessToken;
	}
}
