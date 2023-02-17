package com.wondersgroup.dataitem.item312462115323.util;

import java.util.Map;

import wfc.service.log.Log;

import net.sf.json.JSONObject;

import com.wondersgroup.common.utils.HttpUtil;

public class TaxBureauUtil {
	
	public static String getAuthorization(){
		String Authorization = "";
		String appName = "d9f1f7f0-1dd0-11ed-9afd-ff274f79b579";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject param = new JSONObject();
		param.put("uniqueID", "SHSW4YWTB");
		param.put("msg", "454wr4qt545qtgrrr888");
		String postResult = HttpUtil.doPost(head, param.toString(),
				"application/json;charset=utf-8");
		System.out.println(postResult);
		try{
			JSONObject json = JSONObject.fromObject(postResult);
			if("0".equals(json.optString("code"))){
				Authorization = json.optJSONObject("data").optString("token");
			}
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		return Authorization;
	}
}
