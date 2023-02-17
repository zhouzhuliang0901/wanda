package com.wondersgroup.dataitem.item276652591922.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
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
 * @author wangxw
 *
 */
@Controller
public class BankCardIdentificationController {
	
	@RequestMapping("/selfapi/BankCardIdentificationController/getToken.do")
	public void getToken(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String appName = "";
		String appId = "";
		String appkey = "";
			
						
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "08e64167-97ca-4794-9478-a68d1ba2376d";
			appId = "10037e6f70d296cd01712973e77e0005";
			appkey = "5b517b000fa14b1092aec0d6f40b0a76";
				
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "0dd099fc-981a-4d00-a24d-6763ff76e167";
			appId = "8a81c1be6f19b11301712973080d0109";
			appkey = "0574f036263a443b8116b20a4afa50d8";
		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String timestamp = df.format(calendar.getTime());
		String nonce = "10";
		String signature1 = getSha1(appId+timestamp+nonce+appkey);
			
		JSONObject json = new JSONObject();
		json.put("appId", appId);
		json.put("timestamp", timestamp);
		json.put("nonce", nonce);
		json.put("signature", signature1);
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String result = post(head,json.toString());
		AciJsonHelper.writeJsonPResponse(req, res, result);			
	}
	
	@RequestMapping("/selfapi/BankCardIdentificationController/identifyCard.do")
	public void identifyCard(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		
		String accessToken = "OPEN-ACCESS-TOKEN AccessToken="+req.getParameter("accessToken");
		String cardNo = req.getParameter("cardNo");
		
		String appName = "";
		String json = "";		
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e7b5ff9e-7319-4558-b561-147c417f5b82";
			json = "{\"key\":{\"mealType\":\"2\",\"cardNo\":\""+cardNo+"\",\"userId\":\"bb4a55855fbe45bf8cec30a54516a767\",\"orgCusSendID\":\"1e03d5d4d47d42acb8094e14404c5ef8\",\"authFlag\":\"1\",\"comb\":\"kbin01\"}}";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c9f4eb3e-afdf-4f42-bae3-8c1aabc5e6b0";
			json = "{\"key\":{\"mealType\":\"2\",\"cardNo\":\""+cardNo+"\",\"userId\":\"b8f26f400dc04806a3ce4ad98cd230b1\",\"orgCusSendID\":\"1e03d5d4d47d42acb8094e14404c5ef8\",\"authFlag\":\"1\",\"comb\":\"kbin01\"}}";

		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", accessToken);
		
		String result = post(head,json);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	
	public static String getSha1(String str) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
	
	public static String post(Map<String, String> head,String json)
	{
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		OkHttpClient okHttpClient = null;
		if(url.startsWith("https://")){
			okHttpClient= HttpUtil.getUnsafeOkHttpClient();
		} else {
			okHttpClient = new OkHttpClient();
		}
		MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
//        FormBody.Builder builder = new FormBody.Builder();       
        Request.Builder url1 = (new Request.Builder()).url(url);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            url1.addHeader(entry.getKey() , entry.getValue());
        }
        RequestBody requestBody = RequestBody.create(mediaType, json);
        Response response = null;
        String result = null;
        Request request = url1.post(requestBody).build();
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
