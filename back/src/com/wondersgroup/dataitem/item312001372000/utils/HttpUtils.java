package com.wondersgroup.dataitem.item312001372000.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.wondersgroup.common.utils.HttpUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tw.ecosystem.reindeer.config.RdConfig;

public class HttpUtils {
	@SuppressWarnings("rawtypes")
	public static String post(Map<String, String> head,String xml,String contentType) 
	{
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		OkHttpClient okHttpClient;
		if(url.startsWith("https://")){
			okHttpClient= HttpUtil.getUnsafeOkHttpClient();
		} else {
			okHttpClient = new OkHttpClient();
		}
		
		MediaType mediaType = MediaType.parse(contentType);
		RequestBody body = RequestBody.create(mediaType,xml); 
	    Iterator var6 = head.keySet().iterator();

	    Request.Builder url1 = (new Request.Builder()).url(url).method("POST", body);

        while(var6.hasNext()) {
            String key = (String)var6.next();
            System.out.println(key+head.get(key));
            url1.addHeader( key,head.get(key)+"");
        }
        Request request = url1.build();
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
