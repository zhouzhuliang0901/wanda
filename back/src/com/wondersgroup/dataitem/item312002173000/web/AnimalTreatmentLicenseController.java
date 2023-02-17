package com.wondersgroup.dataitem.item312002173000.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 
 * @author wangXW
 *
 */
@Controller
public class AnimalTreatmentLicenseController {
	
	@RequestMapping("/selfapi/AnimalTreatmentLicense/getInformation.do")
	public void getInformation(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		
		String param = "";
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "36572e4a-3cda-4cb3-8f0b-233c8acd82d6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ab9f4fd6-7e81-4a67-b229-ed0f1131b083";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

	    String contentType = "application/json;charset=utf-8";
	    String result = HttpUtil.doPost(head, param,contentType);
	    
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
