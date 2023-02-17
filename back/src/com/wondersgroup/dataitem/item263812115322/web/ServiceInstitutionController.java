package com.wondersgroup.dataitem.item263812115322.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class ServiceInstitutionController {
	
	/**
	 * 服务机构查询-医保诊疗项目约定服务医院查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/serviceInstitution/queryDiagnosisAndTreatmentProject.do")
	public void queryDiagnosisAndTreatmentProject(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 医保约定项目
		String project = req.getParameter("project");
		String area = req.getParameter("area");
		// 机构名称
		String institutionName = req.getParameter("institutionName");
		if(StringUtils.isNotEmpty(institutionName)){
			institutionName = URLDecoder.decode(institutionName, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "55282389-95aa-4779-a1e5-ac9c0f46e3e6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "55282389-95aa-4779-a1e5-ac9c0f46e3e6";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("QXDM", area);
		json.put("FWXM", project);
		json.put("YYMC", institutionName);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("医保诊疗项目约定服务医院查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 服务机构查询-长护险护理机构查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/serviceInstitution/queryNursingInstitutions.do")
	public void queryNursingInstitutions(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 服务形式-1：社区居家照护；2：养老机构照护
		String serviceMode = req.getParameter("serviceMode");
		String area = req.getParameter("area");
		// 机构名称
		String institutionName = req.getParameter("institutionName");
		String address = req.getParameter("address");
		if(StringUtils.isNotEmpty(institutionName)){
			institutionName = URLDecoder.decode(institutionName, "utf-8");
		}
		if(StringUtils.isNotEmpty(address)){
			address = URLDecoder.decode(address, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "588532e3-9307-4c39-8d2d-8064814b7fca";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "588532e3-9307-4c39-8d2d-8064814b7fca";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("FWXS", serviceMode);
		json.put("AREA_ID", area);
		json.put("VARJGMC", institutionName);
		json.put("ADDRESS", address);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("长护险护理机构查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 服务机构查询-长护险评估机构查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/serviceInstitution/queryAssessmentInstitutions.do")
	public void queryAssessmentInstitutions(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 所属区
		String area = req.getParameter("area");
		// 机构名称
		String institutionName = req.getParameter("institutionName");
		// 地址
		String address = req.getParameter("address");
		if(StringUtils.isNotEmpty(institutionName)){
			institutionName = URLDecoder.decode(institutionName, "utf-8");
		}
		if(StringUtils.isNotEmpty(address)){
			address = URLDecoder.decode(address, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a6ae92c6-4936-4c9f-a8be-e0fc0e707df5";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a6ae92c6-4936-4c9f-a8be-e0fc0e707df5";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("AREA_ID", area);
		json.put("VARJGMC", institutionName);
		json.put("ADDRESS", address);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("长护险评估机构查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 服务机构查询-商业保险机构网点信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/serviceInstitution/queryCommercialInsurance.do")
	public void queryCommercialInsurance(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String area = req.getParameter("area");
		// 商业保险机构
		String commercialInstitution = req.getParameter("commercialInstitution");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "0f7966f2-4d4a-4327-ba1e-6de765b5ef07";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "0f7966f2-4d4a-4327-ba1e-6de765b5ef07";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("QXDM", area);
		json.put("COMPCODE", commercialInstitution);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("商业保险机构网点信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 服务机构查询-异地就医医疗机构查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/serviceInstitution/queryHospitalForSeekMedicalInDifferentPlaces.do")
	public void queryHospitalForSeekMedicalInDifferentPlaces(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 医院分类
		String classification = req.getParameter("classification");
		// 医院结算等级
		String hospitalLevel = req.getParameter("settlementLevel");
		String hospitalName = req.getParameter("hospitalName");
		String address = req.getParameter("address");
		if(StringUtils.isNotEmpty(hospitalName)){
			hospitalName = URLDecoder.decode(hospitalName, "utf-8");
		}
		if(StringUtils.isNotEmpty(address)){
			address = URLDecoder.decode(address, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6e9e5160-51b7-4dc0-a146-3daf717f5545";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6e9e5160-51b7-4dc0-a146-3daf717f5545";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("aka101", hospitalLevel);
		json.put("akb023", classification);
		json.put("akb021", hospitalName);
		json.put("aae006", address);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("异地就医医疗机构查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 服务机构查询-医保及相关服务机构查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/serviceInstitution/queryServiceInstitution.do")
	public void queryServiceInstitution(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 服务机构
		String serviceInstitution = req.getParameter("serviceInstitution");
		String area = req.getParameter("area");
		String institutionName = req.getParameter("institutionName");
		String address = req.getParameter("address");
		if(StringUtils.isNotEmpty(institutionName)){
			institutionName = URLDecoder.decode(institutionName, "utf-8");
		}
		if(StringUtils.isNotEmpty(address)){
			address = URLDecoder.decode(address, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "91930817-7f88-4e97-b033-a8d95fa76599";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "91930817-7f88-4e97-b033-a8d95fa76599";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("CXLX", serviceInstitution);
		json.put("AREA_ID", area);
		json.put("JGMC", institutionName);
		json.put("DIZHI", address);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("医保及相关服务机构查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
