package com.wondersgroup.dataitem.item382711997735.utils;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

public class HouseholdRegisterUtil {
	
	public static String getDictionary(String rootCode, String type){
		String dictionarys = "";
		String appName = "81433ac0-ae7b-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

	     String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
	    		 +"?type="+type+"&rootCode="+rootCode;
	     dictionarys = HttpUtil.doGet(head, url, "GET");
		return dictionarys;
	}
	
	public static JSONArray getData(JSONArray arr){
		JSONArray jsonArr = new JSONArray();
		for(int i = 0;i<arr.size();i++){
			JSONObject e = arr.optJSONObject(i);
			String c = e.optString("c");
			String n = e.optString("n");
			JSONObject o = new JSONObject();
			o.put("c", c);
			o.put("n", n);
			jsonArr.add(o);
		}
		return jsonArr;
	}
	
	public static JSONArray iterateJsonArr(JSONArray parentArr, JSONArray childArr, JSONArray resultArr){
		for(int i = 0;i<childArr.size();i++){
			JSONObject o = childArr.optJSONObject(i);
			String c = o.optString("c");
			for(int j = 0;j<parentArr.size();j++){
				JSONObject e = parentArr.optJSONObject(j);
				String ec = e.optString("c");
				if(c.equals(ec)){
					JSONArray arr = e.optJSONArray("d");
					if(null != arr){
						JSONObject resultJson = new JSONObject();
						JSONArray child =  getData(arr);
//						System.out.println(child.toString());
						resultJson.put("parentCode", c);
						resultJson.put("childArr", child);
						resultArr.add(resultJson);
						iterateJsonArr(arr,child, resultArr);
					}
				}
			}
		}
		return resultArr;
	}
	
	public static void main(String[] args) {
		String dictionarys = getDictionary("yjDepart", "2");
		JSONArray dictionaryArr = JSONObject.fromObject(dictionarys).optJSONArray("data");
		JSONArray arr = getData(dictionaryArr);
		System.out.println(arr);
		System.out.println(iterateJsonArr(dictionaryArr, arr, new JSONArray()));
	}
}
