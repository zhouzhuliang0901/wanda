package com.wondersgroup.dataitem.item251513164923.utils;

import java.util.Map;

import net.sf.json.JSONObject;

import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.HuidaoUtil;

public class HouseUtil {
	
	public static String getAuthorization(String tokenSNO){
		String Authorization = "";
		String accessToken = "";
		try {
			accessToken = HuidaoUtil.getAccessToken(tokenSNO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String appName = "6bab342c-20b3-4a95-82d0-b397bd7a08c8";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("access_token_zwdtsw", accessToken);
		String postResult = HttpUtil.doPost(head, jsonParam.toString(), "application/json;charset=utf-8");
		try{
			JSONObject result = JSONObject.fromObject(postResult);
			if("200".equals(result.optString("code"))){
				Authorization = result.optJSONObject("data").optString("content");
			}
		} catch (Exception e) {
			Log.debug("登录结果解析异常："+e.getMessage());
		}
		return Authorization;
	}
}
