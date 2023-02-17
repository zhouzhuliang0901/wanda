package com.wondersgroup.dataitem.item325933242236.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class OnlineCarCompanyQueryController {
	
	/**
	 * 网约车平台企业信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/onlineCarCompanyQuery/queryCompanyInfoForOnlineCar.do")
	public void queryCompanyInfoForOnlineCar(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String companyName = req.getParameter("companyName");
		companyName = companyName == null ? "" : URLDecoder.decode(companyName, "utf-8");
		long timestamp = System.currentTimeMillis();
		String appkey = "1b5aa885e64a41de8759ea88d92a3bfa";
		String appsecret = "RIJIJISF";
		String signatureByt = DigestUtils.sha1Hex(appkey+appsecret+timestamp);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "004a5ed0-7bf5-11eb-9bf5-2fbbafe42691";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "2bcd35c7-5809-4f91-860a-909b24425b97";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//	    String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment")) +
//	    		"?appkey=1b5aa885e64a41de8759ea88d92a3bfa&serviceCode=qMqWUpPiboJoGQme" +
//	    		"&signature="+signatureByt+"&timestamp="+timestamp+"&CNNAME="+companyName;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appkey", "1b5aa885e64a41de8759ea88d92a3bfa");
		paramMap.put("serviceCode", "qMqWUpPiboJoGQme");
		paramMap.put("signature", signatureByt);
		paramMap.put("timestamp", Long.toString(timestamp));
		paramMap.put("CNNAME", companyName);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
	    JSONObject json = new JSONObject();
	    try{
		    json = JSONObject.fromObject(result);
		    String code = json.optString("code");
		    if(0 == Integer.valueOf(code)) {
		    	String arrStr = json.optString("data");
		    	JSONArray arr = JSONArray.fromObject(arrStr);
		    	for(int iLoop = 0;iLoop<arr.size();iLoop++){
		    		JSONObject e = arr.getJSONObject(iLoop);
		    		long opendate = e.optLong("opendate");
		    		Date date = new Date(opendate);
		    		e.put("opendate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		    	}
		    	json.put("data", arr);
		    }
	    } catch (Exception e) {}
	    AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
}
