package com.wondersgroup.dataitem.item259452299600.utils;

import java.util.Map;

import tw.ecosystem.reindeer.config.RdConfig;

import net.sf.json.JSONObject;

import com.wondersgroup.common.utils.HttpUtil;

public class EducationCommissionUtil {
	
	public static String access_token(String serverName){
		String appId = "";
		String userName = "";
		String password = "";
		String appName = "";
		if("cooperationSchool".equals(serverName)){
			appId = "shed-interface-zwhzbx";
			userName = "bigdatacenter";
			password = "WkCM987NVab2zhX";
			appName = "0f9bae01-3ec2-4879-976b-3fcc33dc0ae4";
		} else if("childCareInstitution".equals(serverName)){
			appId = "shed-interface-03tyjg";
			userName = "bigdatacenter";
			password = "WkCM987NVab2zhX";
			appName = "c6640b4c-7732-4ec9-978a-4ef256a9d23c";
		} else if("trainingInstitution".equals(serverName)){
			appId = "shed-interface-cert-pxjg";
			userName = "bigdatacenter";
			password = "WkCM987NVab2zhX";
			appName = "cd522a17-425b-425b-a519-ab6d3b02dcb8";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("appId", appId);
		obj.put("userName", userName);
		obj.put("password", password);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		return result;
	}
	
	public static String getToken(String appName){
		String token = "";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+
				"?appId=saca_31iIEQwV6M&appSecret=Bas2zPJK8A0sQOgcErkz5cQulRQvgYfn";
		String result = HttpUtil.doGet(head, url, "");
		JSONObject json = JSONObject.fromObject(result);
		token = json.optString("access_token");
		return token;
	}
	
	public static void main(String[] args) {
	}
}
