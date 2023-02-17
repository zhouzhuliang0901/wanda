package com.wondersgroup.dataitem.item312002184000.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item312002184000.utils.HttpUtils;


import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 
 * @author wangXW
 *
 */
@Controller
public class PropertyInquiryController {
	
	//第三方登录
	@RequestMapping("/selfapi/PropertyInquiry/doLogin.do")
	public void doLogin(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");		
		String appName = "";
		
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6bab342c-20b3-4a95-82d0-b397bd7a08c8";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6bab342c-20b3-4a95-82d0-b397bd7a08c8";
		}
			
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, json,contentType);
	
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//小区基本信息
	@RequestMapping("/selfapi/PropertyInquiry/getCompanyDetails.do")
	public void getCompanyDetails(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
	
		String appName = "";
		
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c14f2e4e-a8e7-4423-80b0-d0d9313c14a2";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c14f2e4e-a8e7-4423-80b0-d0d9313c14a2";
		}
			
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	//物业服务企业信息
	@RequestMapping("/selfapi/PropertyInquiry/getEnterpriseInformation.do")
	public void getEnterpriseInformation(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e0944727-b236-48d4-8a2f-2d9f7af0eb86";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "e0944727-b236-48d4-8a2f-2d9f7af0eb86";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	//物业企业列表
	@RequestMapping("/selfapi/PropertyInquiry/getEnterpriseList.do")
	public void getEnterpriseList(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "beeb7b96-7a3a-487a-8bea-ff5433a37374";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "beeb7b96-7a3a-487a-8bea-ff5433a37374";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
			
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	//物企详细
	@RequestMapping("/selfapi/PropertyInquiry/getEnterpriseDetail.do")
	public void getEnterpriseDetail(HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "566f0e76-8beb-4243-a4dd-5a71a6a886b3";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "566f0e76-8beb-4243-a4dd-5a71a6a886b3";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
			
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	//各区在管小区总数
	@RequestMapping("/selfapi/PropertyInquiry/getTotalCommunity.do")
	public void getTotalCommunity(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "d02ab053-f3a6-457b-ad04-9ce7f2cb8ef8";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "d02ab053-f3a6-457b-ad04-9ce7f2cb8ef8";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
			
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	//获取物业企业或者小区经理的业绩信息
	@RequestMapping("/selfapi/PropertyInquiry/getPerformanceInformation.do")
	public void getPerformanceInformation(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "67769f2f-a13a-44ed-ba5c-91b074770da0";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "67769f2f-a13a-44ed-ba5c-91b074770da0";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
			
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	//物业服务企业失信记分
	@RequestMapping("/selfapi/PropertyInquiry/getDishonestScore.do")
	public void getDishonestScore(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "17d88e33-dd21-44e8-ba86-2c1b36dd9960";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "6a0a078d-0a5d-4bbc-b2a7-969a61c9ba49";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
			
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	// 查询小区列表
	@RequestMapping("/selfapi/PropertyInquiry/getCommunityList.do")
	public void getCommunityList(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String json = req.getParameter("json");
		String Authorization = req.getParameter("Authorization");
			
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "dd77d72a-4694-49d1-aa8e-f1d3ede8c1be";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){				
			appName = "dd77d72a-4694-49d1-aa8e-f1d3ede8c1be";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtils.post(head, json,contentType);	
			
		AciJsonHelper.writeJsonPResponse(req, res, result);
			
	}
	
	
}
