package com.wondersgroup.dataitem.item310150127000.web;

import java.io.IOException;
import java.net.URLDecoder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item310150127000.utils.DateFilter;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class CivilMarriageController {
	
	//民政局结婚预约日期
	@RequestMapping("/selfapi/CivilMarriage/mzReserveDateQueryNew.do")
	public void mzReserveDateQueryNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "da4e39da-88bf-42bc-b004-4685b4568bdb";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b3cde4ec-524c-4f20-850f-025233b4ead6";
		}
			
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
				
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
				
		//过滤节假日和已约满日期
		result = DateFilter.dateFilter(result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
				
	}
			
	//民政局离婚预约日期
	@RequestMapping("/selfapi/CivilMarriage/mzLhReserveDateQueryNew.do")
	public void mzLhReserveDateQueryNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		
		String json =  req.getParameter("json");
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c364d276-d605-462e-85a8-485946eb183c";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a3bb1595-4008-41c4-afcb-f101529bf893";
		}
			
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
				
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
				
		//过滤节假日和已约满日期
		result = DateFilter.dateFilter(result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
				
	}
	
	//民政局结婚预约时间段
	@RequestMapping("/selfapi/CivilMarriage/mzReserveTimeQuery.do")
	public void mzReserveTimeQuery(HttpServletRequest req, 
				HttpServletResponse res) throws IOException {
			
		String json =  req.getParameter("json");
		String appName = "";
			
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "efeb62d2-f28a-4eb6-9cb4-c209be8547f8";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e374a368-7fd7-4e7c-9677-e50f6fe7ea2f";
		}
			
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		
		//过滤节假日和已约满日期
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
		

	//民政局离婚预约时间段
	@RequestMapping("/selfapi/CivilMarriage/mzLhReserveTimeQuery.do")
	public void mzLhReserveTimeQuery(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7e0724b6-094c-49de-abdb-1957936bbcfa";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c764ed15-acf2-45aa-b7cc-7e25acb06fbb";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}		
	
	
	
	//新查询婚姻预约的办件
	@RequestMapping("/selfapi/CivilMarriage/mzMarriageEventObtainNew.do")
	public void mzMarriageEventObtainNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "0a32297b-0c67-4378-9793-97b6c2eb1cf6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "600e15f9-e5a9-4bec-8ad7-436c9c7dc5e6";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//新取消婚姻预约的席位修改办件状态
	@RequestMapping("/selfapi/CivilMarriage/mzMarriageEventObtainUpdateNew.do")
	public void mzMarriageEventObtainUpdateNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ae7cf455-4aec-457d-9f3e-d4e9c1637397";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c9f95c5a-25e0-41f3-9193-e3c1321be6c4";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//新内地结婚登记预约
	@RequestMapping("/selfapi/CivilMarriage/saveMainlandMarriageRegistrationAppointmentNew.do")
	public void saveMainlandMarriageRegistrationAppointmentNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6f17d552-4550-4742-b63b-415357178ddf";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "f00db740-4737-47d7-b085-6ad000d70b3f";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}

	//新内地离婚登记预约
	@RequestMapping("/selfapi/CivilMarriage/saveAppointmentDivorceRegistrationMainlandNew.do")
	public void saveAppointmentDivorceRegistrationMainlandNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "669090d9-08a0-4104-b5c9-178000b638b5";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "2c921f87-8398-46f1-b976-a834b8c2a5ef";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//新涉港、澳、台、华侨和外国人结婚登记
	@RequestMapping("/selfapi/CivilMarriage/saveMarriageRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew.do")
	public void saveMarriageRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "44647741-63c0-4546-b839-8c07c3d2605f";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "44647741-63c0-4546-b839-8c07c3d2605f";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//新涉港、澳、台、华侨和外国人离婚登记
	@RequestMapping("/selfapi/CivilMarriage/saveDivorceRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew.do")
	public void saveDivorceRegistrationInvolvingHongKongMacaoTaiwanOverseasChineseForeignersNew(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "2f805330-1786-472a-a186-3a252599f09a";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "9b3c3103-3a68-4eb5-91ec-6c476c0c13c4";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//获取机构详细信息
	@RequestMapping("/selfapi/CivilMarriage/getOrganDetail.do")
	public void getOrganDetail(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6003693c-99b0-4c6c-9c61-3d61ca87f61e";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3522bb43-6d33-42f7-9f49-e4671c4fe770";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
	    String result = HttpUtil.doPost(head, null);
		result = URLDecoder.decode(result,"utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	// 获取特殊日期备注信息
	@RequestMapping("/selfapi/CivilMarriage/specialMemo.do")
	public void specialMemo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String oraganId = req.getParameter("oraganId");
		String bdate = req.getParameter("bdate");
		String code = req.getParameter("code");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1331ab71-af56-4c4f-a4aa-a8cb0193ce57";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ae82d5a4-9d7f-4407-a7f9-680e70207059";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
//		url = url.substring(0, url.length() - 1);
//		url = url + "?"+"oraganId="+oraganId+"&"+"bdate="+bdate+"&"+"code="+code;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("oraganId", oraganId);
		paramMap.put("bdate", bdate);
		paramMap.put("code", code);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	//公安派出所字典
	@RequestMapping("/selfapi/CivilMarriage/gonganDictionary.do")
	public void gonganDictionary(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
				
		String json =  req.getParameter("json");
		String appName = "";
				
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "49b90053-45cf-4186-a676-2bd1413f3112";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "382fb66f-1767-473b-a838-94cc1e92ddf7";
		}
				
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json,contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
		
	}
	
	
}
