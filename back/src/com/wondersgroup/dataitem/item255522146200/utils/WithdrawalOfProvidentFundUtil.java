package com.wondersgroup.dataitem.item255522146200.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import wfc.service.log.Log;

import com.wondersgroup.common.utils.PooledHttpUitl;

public class WithdrawalOfProvidentFundUtil {
	
	public static String send(Map<String, String> head, String url){
		String str = "";
		CloseableHttpClient closeableHttpClient = PooledHttpUitl
				.closeableHttpClient;
		HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
		CloseableHttpResponse response = null;
		try {
			response = closeableHttpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				str = EntityUtils.toString(entity, "utf-8");
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
}
