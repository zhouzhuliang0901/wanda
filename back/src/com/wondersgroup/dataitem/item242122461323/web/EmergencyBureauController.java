package com.wondersgroup.dataitem.item242122461323.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class EmergencyBureauController {
	
	@RequestMapping("/selfapi/emergencyBureau/queryInfo.do")
	public void queryDangerousChemicalsInfo(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		// 违禁措施：7-全市禁止；8-工业区禁止部分；0-中心城限制和控制部分
		String measures = req.getParameter("measures");
		// 化学品名称关键字
		String chemicalsName = req.getParameter("chemicalsName");
		// 单位名称
		String companyName = req.getParameter("companyName");
		// 证书编号
		String registrationNumber = req.getParameter("registrationNumber");
		// 建设项目名称
		String documentName = req.getParameter("documentName");
		String region = req.getParameter("region");
		// 业务类型
		String method = req.getParameter("method");
		if(StringUtils.isNotEmpty(chemicalsName)){
			chemicalsName = URLDecoder.decode(chemicalsName, "utf-8");
		}
		if(StringUtils.isNotEmpty(companyName)){
			companyName = URLDecoder.decode(companyName, "utf-8");
		}
		if(StringUtils.isNotEmpty(registrationNumber)){
			registrationNumber = URLDecoder.decode(registrationNumber, "utf-8");
		}
		if(StringUtils.isNotEmpty(documentName)){
			documentName = URLDecoder.decode(documentName, "utf-8");
		}
		JSONObject obj = new JSONObject();
		
		String appName = "";
		if("queryWhpml".equals(method)){
			appName = "5410a235-de29-4578-9851-b658b6ed01d6";
			obj.put("column1", measures);
			obj.put("name", chemicalsName);
//			obj.put("column1", "7");
//			obj.put("name", "八氯");
		} else if("queryWhscxk".equals(method)){
			appName = "0ee63e89-88c2-4099-b1ac-b0f2b6dd186d";
			obj.put("companyName", companyName);
			obj.put("registrationNumber", registrationNumber);
//			obj.put("companyName", "巴斯夫");
//			obj.put("registrationNumber", "");
		} else if("queryWhjyxk".equals(method)){
			appName = "1a87449e-581f-4d97-991c-260491fbaa9d";
			obj.put("companyName", companyName);
			obj.put("registrationNumber", registrationNumber);
//			obj.put("companyName", "巴斯夫");
//			obj.put("registrationNumber", "");
		} else if("queryWhjsxm".equals(method)){
			appName = "efb2ac1f-4ecb-4629-8765-b71e8184293e";
			obj.put("documentName", documentName);
//			obj.put("documentName", "建设");
		} else if("queryPjjg".equals(method)){
			appName = "7f838df6-ba96-4690-8833-128db52ca4d6";
			obj.put("companyName", companyName);
			obj.put("registrationNumber", registrationNumber);
//			obj.put("companyName", "建设");
//			obj.put("registrationNumber", "");
		} else if("queryjcjy".equals(method)){
			appName = "82f0d9ba-8395-4d78-a2db-11aa2a8ec909";
			obj.put("companyName", companyName);
			obj.put("registrationNumber", registrationNumber);
//			obj.put("companyName", "上海");
//			obj.put("registrationNumber", "");
		} else if("querpxjg".equals(method)){
			appName = "60868711-608f-4b14-a1fc-9acc91384241";
			obj.put("companyName", companyName);
			obj.put("region", region);
//			obj.put("companyName", "建科");
//			obj.put("region", "310112");
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", method);
		params.put("params", obj.toString());
		
        String result = okHttp(appName, signature, params);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	private static String okHttp(String appName, String signature, Map<String, String> params){
		String str = "";
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));		
		OkHttpClient client = null;
		if(url.startsWith("https://")){
			client= HttpUtil.getUnsafeOkHttpClient();
		} else {
			client = new OkHttpClient();
		}
		Iterator<String> it = params.keySet().iterator();
		Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+"----"+params.get(key));
			build.addFormDataPart(key, params.get(key));
		}
		RequestBody body = build.build();
		Request request = new Request.Builder()
		.url(url)
		.header("signature", signature)
		.header("apiname", appName)
		.header("appid", RdConfig.get("reindeer.huidao.appId."+RdConfig.get("reindeer.huidao.environment")))
		.post(body)
		.build();
		
		Call call = client.newCall(request);
		Response response = null;
		try {
			response = call.execute();
			str = URLDecoder.decode(response.body().string(), "utf-8");
			System.out.println("应急局接口返回数据："+str);
		} catch (IOException e) {
			Log.debug(e);
			Log.debug("访问失败");
		} finally {
			if(response != null){
				response.body().close();
			}
		}
		return str;
	}
}
