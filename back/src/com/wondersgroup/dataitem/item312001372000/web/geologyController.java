package com.wondersgroup.dataitem.item312001372000.web;
	
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item312001372000.utils.HttpUtils;
import com.wondersgroup.dataitem.item312001372000.utils.buildXml;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 
 * @author wangXW
 *
 */
@Controller
public class geologyController {
	
	@RequestMapping("/selfapi/geology/getInformationList.do")
	public void getInformation(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		
		String json = req.getParameter("json");
		if(StringUtils.isNotEmpty(json)){
			json = URLDecoder.decode(json, "utf-8");
		}
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7b993117-5cd9-49de-8beb-c99d2ec2c18e";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    head.put("contentType", "text/xml");
	    String xml = buildXml.buildInformationListXml(json);
	    xml =  new String(xml.getBytes("utf-8"),"utf-8");
	    System.out.println(xml);
	    String contentType = "text/xml;charset=utf-8";
	    String result = HttpUtils.post(head,xml,contentType);
	    
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	@RequestMapping("/selfapi/geology/getInformationDetail.do")
	public void getInformationDetail(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		
		String json = req.getParameter("json");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3afad0cf-4448-4976-98af-2bbb9b130ef2";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    head.put("contentType", "text/xml");
	    String xml = buildXml.buildInformationDetailXml(json);
	    xml =  new String(xml.getBytes("utf-8"),"utf-8");
	    System.out.println(xml);
	    String contentType = "text/xml;charset=utf-8";
	    String result = HttpUtils.post(head,xml,contentType);
	    
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	@RequestMapping("/selfapi/geology/getInformationPdf.do")
	public void getInformationPdf(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		
		String json = req.getParameter("json");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3bda0e93-8574-4f5b-ba06-3838cd6b39b6";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    head.put("contentType", "text/xml");
	    String xml = buildXml.buildInformationPdfXml(json);
	    xml =  new String(xml.getBytes("utf-8"),"utf-8");
	    System.out.println(xml);
	    String contentType = "text/xml;charset=utf-8";
	    String result = HttpUtils.post(head,xml,contentType);
	    
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
