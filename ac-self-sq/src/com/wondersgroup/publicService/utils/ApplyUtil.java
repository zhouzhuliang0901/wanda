package com.wondersgroup.publicService.utils;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;

public class ApplyUtil {
	
	public static String dealApplyParameter(String itemCode, String targetTypeName, String targetName,
			String targetNo, String userId, String username, String licenseNo, String mobile,
			String content, String bldCode) {
		
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject info = new JSONObject();
		JSONObject licenseType = new JSONObject();
		JSONObject targetType = new JSONObject();
		
		targetType.put("name", targetTypeName);
		if("个人".equals(targetTypeName)){
			targetType.put("value", "a");
		} else if("法人".equals(targetTypeName)){
			targetType.put("value", "b");
		}
		
		licenseType.put("value", "a");
		licenseType.put("name", "身份证");
		
		info.put("username", username);
		info.put("licenseType", licenseType);
		info.put("targetType", targetType);
		info.put("licenseNo", licenseNo);
		info.put("applyReason", "");
		info.put("targetName", targetName);
		info.put("targetNo", targetNo);
		info.put("LEGAL_PERS_NAME", "");
		info.put("CONTACT_NAME", "");
		info.put("mobile", mobile);
		info.put("content",content);
		
		data.put("userId",userId);
		data.put("departCode",bldCode);
		data.put("itemCode",itemCode);
		data.put("info",info);
		
		json.put("data", data);
		return json.toString();
	}
	
	/**
	 * 获取事项办理点
	 * @param itemCodes
	 * @param departCode
	 * @return
	 */
	public static String getItemApplyPlace(String itemCodes, String departCode){

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "59c7c2bc-889b-4a46-826d-91b313c692b2";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "1cad7c69-2d49-4783-9926-4520bb30dd58";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject obj = new JSONObject();
		obj.put("itemCodes", itemCodes);

		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, obj.toString(), contentType);
		Log.debug("事项"+itemCodes+"的办理点："+result);
		
		JSONObject json = JSONObject.fromObject(result);
		JSONArray arr = json.optJSONArray("data");
		
		String bldCode = "";
		for (int iLoop = 0; iLoop < arr.size(); iLoop++) {
			JSONObject element = arr.optJSONObject(iLoop);
			String regionCode = element.optString("regionCode");
			bldCode = element.optString("bldCode");
			if(StringUtils.isNotEmpty(regionCode) 
					&& bldCode.contains(departCode)){
				bldCode = element.optString("bldCode");
				break;
			}
		}
		return bldCode;
	}
}
