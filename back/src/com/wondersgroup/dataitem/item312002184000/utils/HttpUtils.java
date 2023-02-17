package com.wondersgroup.dataitem.item312002184000.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.HttpUtil;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import tw.ecosystem.reindeer.config.RdConfig;

public class HttpUtils {
	
	public static String doGet(Map<String,String>head,String json)  {
		String body = "";
		OkHttpClient okHttpClient;
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		if(url.startsWith("https://")){
			okHttpClient= HttpUtil.getUnsafeOkHttpClient();
		} else {
			okHttpClient = new OkHttpClient();
		}
		Request.Builder reqBuild = new Request.Builder();
	    HttpUrl.Builder urlBuilder =HttpUrl.parse(url)
		                                    .newBuilder();
	    Map<String, Object> params = JSONObject.parseObject(json);
	    for (Entry<String, Object> entry : params.entrySet()) {
	    	urlBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString());
	    }
	    for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            reqBuild.addHeader(entry.getKey() , entry.getValue());
        }
	    reqBuild.url(urlBuilder.build());
	    Request request = reqBuild.build();
	    Response response = null;
	    try {
			response = okHttpClient.newCall(request).execute();
			body = response.body().string();
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if(response != null){
				response.body().close();
			}
		}
	    
        System.out.println(body);
		return body;
    }
	
	
	@SuppressWarnings("rawtypes")
	public static String post(Map<String, String> head,String json,String contentType) 
	{
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		OkHttpClient okHttpClient;
		if(url.startsWith("https://")){
			okHttpClient= HttpUtil.getUnsafeOkHttpClient();
		} else {
			okHttpClient = new OkHttpClient();
		}
		FormBody.Builder builder = new FormBody.Builder();
	    RequestBody requestBody = null;
	    
	    Map params = JSONObject.parseObject(json);
	    Iterator var5 = params.keySet().iterator();
	    while(var5.hasNext()) {
	    	String key = (String)var5.next();
	        builder.add(key, params.get(key).toString() + "");
	    }
	    Iterator var6 = head.keySet().iterator();

	    Request.Builder url1 = (new Request.Builder()).url(url);

        while(var6.hasNext()) {
            String key = (String)var6.next();
            System.out.println(key+head.get(key));
            url1.addHeader( key,head.get(key)+"");
        }
        requestBody = builder.build();
        Request request = url1.post(requestBody).build();
        Response response = null; 
        String result = null;
		try {
			response = okHttpClient.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(response != null){
				response.body().close();
			}
		}
		return result;
	}
	 
}