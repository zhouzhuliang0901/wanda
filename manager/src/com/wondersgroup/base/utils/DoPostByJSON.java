package com.wondersgroup.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;



public class DoPostByJSON {

	public static String doPostByJSONObect(String url, JSONObject args) throws Exception {
		if (StringUtils.isEmpty(url))
			throw new Exception("地址为空");

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity se = new StringEntity(args.toJSONString(), "UTF-8");
		httpPost.setEntity(se);
       
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();

			String text = EntityUtils.toString(httpEntity);

			System.out.print("Content Text :: ");
			System.out.println(text);

			return text;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (httpResponse != null)
					httpResponse.close();
					httpClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
