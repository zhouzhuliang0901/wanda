package com.wondersgroup.dataitem.item3120Q1326000.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 
 * @author wangXW
 *
 */
@Controller
public class CivilServiceAppointmentController {
	
	@RequestMapping("/selfapi/CivilServiceAppointment/getCentreList.do")
	public void getCentreList(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String regionCode = req.getParameter("regionCode");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "5e9fdf5e-6ae2-4fb2-a879-dd3d64e1bac1";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?regionCode="+regionCode;
	
		String result = post(url,head);

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	private static String post(String url,Map<String,String> header) {
		String result = null;
		OkHttpClient client = null;
		if(url.startsWith("https://")){
			client= HttpUtil.getUnsafeOkHttpClient();
		} else {
			client = new OkHttpClient();
		}
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
		  .url(url)
		  .method("POST", body)
		  .addHeader("apiname", header.get("apiname"))
		  .addHeader("signature", header.get("signature"))
		  .addHeader("appid", header.get("appid"))
		  .build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
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
