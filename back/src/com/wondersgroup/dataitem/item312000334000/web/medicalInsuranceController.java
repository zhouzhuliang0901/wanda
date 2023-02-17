package com.wondersgroup.dataitem.item312000334000.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item312000334000.utils.buildYBXml;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 
 * @author wangXW
 *
 */
@Controller
public class medicalInsuranceController {

	@PostMapping("/selfapi/medicalInsurance/{code}")
	public void medicalInsurance(@PathVariable String code, HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		
		String json = req.getParameter("json");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "045a026b-5d0a-4963-a672-a56052b68872";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1ee83671-3620-486d-b297-1019110a65da";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    head.put("SOAPAction", "");
	    String xml = buildYBXml.buildxml(code, json);
	    
	    String contentType = "text/xml;charset=utf-8";
	    String result = HttpUtil.doPost(head, xml,contentType);
	    result = buildYBXml.dealXml(result);
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	@PostMapping("/selfapi/medicalInsurance/inquiryInsured.do")
	public void inquiryInsured(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		
		String json = req.getParameter("json");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "600287ef-a341-4022-b0cf-3504657ba464";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "600287ef-a341-4022-b0cf-3504657ba464";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	   
	    
	    String contentType = "applciation/json;charset=utf-8";
	    String result = HttpUtil.doPost(head, json,contentType);
	   
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	@PostMapping("/selfapi/medicalInsurance/inquiryBusinessProcessingProgress.do")
	public void inquiryBusinessProcessingProgress(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		
		String json = req.getParameter("json");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "afa4a844-1a2a-4f83-aea5-4040d85d51a4";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "afa4a844-1a2a-4f83-aea5-4040d85d51a4";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	   
	    
	    String contentType = "applciation/json;charset=utf-8";
	    String result = HttpUtil.doPost(head, json,contentType);
	   
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
}
