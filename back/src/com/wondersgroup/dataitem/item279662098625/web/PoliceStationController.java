package com.wondersgroup.dataitem.item279662098625.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class PoliceStationController {
	
	/**
	 * 分局字典
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/policeStation/subBureauDictionary.do")
	public void subBureauDictionary(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String appName = "7f7ed9a6-2fe6-4001-95fa-9a86d96deca2";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 派出所字典
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/policeStation/policeStationDictionary.do")
	public void policeStationDictionary(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String policeDepartment = req.getParameter("policeDepartment");
		String appName = "b64273d5-dd29-498a-9a67-8b217ef4bd34";
		policeDepartment = URLDecoder.decode(policeDepartment, "utf-8");
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?policeDepartment="+policeDepartment;
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 属地派出所查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/policeStation/queryTerritoryOfStation.do")
	public void queryTerritoryOfStation(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String policeDepartment = req.getParameter("policeDepartment");
		String policeStation = req.getParameter("policeStation");
		String work = req.getParameter("work");
		String appName = "e5f2a850-09d8-41a3-a210-95bd55442dc2";
		
		if(StringUtils.isNotEmpty(policeDepartment)){
			policeDepartment = URLDecoder.decode(policeDepartment, "utf-8");
		} else {
			policeDepartment = "";
		}
		if(StringUtils.isNotEmpty(policeStation)){
			policeStation = URLDecoder.decode(policeStation, "utf-8");
		} else {
			policeStation = "";
		}
		if(StringUtils.isNotEmpty(work)){
			work = URLDecoder.decode(work, "utf-8");
		} else {
			work = "";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?policeDepartment="+policeDepartment+"&policeStation="+policeStation+"&work="+work;
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
