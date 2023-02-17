package com.wondersgroup.dataitem.item382711997735.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.HuidaoUtil;

public class CSJutil {
	public static String getCSJApplyNo(String itemCode){
		String applyNo = "";
		String appName = "1a872b50-a8f6-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("itemCode", "itemCode");
		String accessToken = getToken();
		json.put("accessToken", accessToken);
		String result = HttpUtil.doPost(head, json.toString(),
				"application/json;charset=utf-8");
		System.out.println("长三角赋码返回："+result);
		try{
			JSONObject resultJson = JSONObject.fromObject(result);
			if(resultJson.optBoolean("isSuccess") 
					&& "200".equals(resultJson.optString("code"))){
				applyNo = resultJson.optJSONObject("data").optString("applyNo");
			}
		} catch (JSONException e) {
			Log.debug("请求结果格式异常："+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applyNo;
	}
	
	public static boolean passApply(String param){
		String appName = "dd16c3c0-a5d0-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String result = HttpUtil.doPost(head, param,
				"application/json;charset=utf-8");
		JSONObject jsonResult = JSONObject.fromObject(result);
		return jsonResult.optBoolean("isSuccess");
	}
	
	public static String getToken(){
		String accessToken = "";
		String appName = "b74943f0-a5d2-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("clientId", "zwdtuser");
		paramMap.put("clientSecret", "zwdtuser");
		paramMap.put("Content-type", "application/x-www-form-urlencoded");
		try{
	        HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
			String result = HttpUtil.doPost(head, reqEntity);
			JSONObject resultJson = JSONObject.fromObject(result);
			accessToken = resultJson.optString("access_token");
		} catch (JSONException e) {
			Log.debug("请求结果格式异常："+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	public static String getUserId(String tokenSNO){
		String userId = "";
		try {
			System.out.println("获取userId："+tokenSNO);
			String accessToken = HuidaoUtil.getAccessToken(tokenSNO);
			if(StringUtils.isNotEmpty(accessToken)){
				String userInfo = HttpUtil.getUserInfoByAccesstoken(accessToken);
				JSONObject userJson = JSONObject.fromObject(userInfo);
				userId = userJson.optString("zwdtsw_user_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	public static void main(String[] args) {
	}
}
