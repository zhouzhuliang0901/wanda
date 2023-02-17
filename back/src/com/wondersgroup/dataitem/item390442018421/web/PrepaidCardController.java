package com.wondersgroup.dataitem.item390442018421.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class PrepaidCardController {
	
	/**
	 * 预付卡信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/prepaidCard/cardInfoQuery.do")
	public void cardInfoQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String corpName = req.getParameter("corpName");
		String uniqueNo = req.getParameter("uniqueNo");
		if(StringUtils.isNotEmpty(corpName)){
			corpName = URLDecoder.decode(corpName, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b51e424c-a5f1-4859-bb5b-08041a051f37";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "4d72f871-0755-4850-bf40-85f831dc16dc";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("corpName", corpName == null ? "" : corpName);
		json.put("uniqueNo", uniqueNo == null ? "" : uniqueNo);
		json.put("pageNo", 1);
		json.put("pageSize", Integer.MAX_VALUE/2);
		
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("预付卡信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 预付卡查询信息详情
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/prepaidCard/cardDetailInfoQuery.do")
	public void cardDetailInfoQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String uniqueNo = req.getParameter("uniqueNo");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a4f10207-b71f-482d-a855-de07f336aea1";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "752c2bc1-e6cc-40c6-b18c-2431662cb9e2";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("uniqueNo", uniqueNo);
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("预付卡查询信息详情："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 预付卡余额查询
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/prepaidCard/balanceOfCard.do")
	public void balanceOfCard(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String corpName = req.getParameter("corpName");
		String brandGroup = req.getParameter("brandGroup");
		String cardNo = req.getParameter("cardNo");
		String mobile = req.getParameter("mobile");
		if(StringUtils.isNotEmpty(corpName)){
			corpName = URLDecoder.decode(corpName, "utf-8");
		}
//		corpName = "上海徐家汇商城（集团）有限公司";
//		cardNo = "2336666601000727285";
//		mobile = "18356633255";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "9247f7fd-8606-44db-b1a3-ef5e96dcf904";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3b38d86f-f7d0-4ef6-b466-ac454fba2e1f";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("corpName", corpName == null ? "" : corpName);
		json.put("brandGroup", brandGroup == null ? "" : brandGroup);
		json.put("cardNo", cardNo == null ? "" : cardNo);
		json.put("mobile", mobile);
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("预付卡余额查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 预付卡警示名单查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/prepaidCard/warningOfCard.do")
	public void warningOfCard(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String corpName = req.getParameter("corpName");
		// 警告类型（1-黄灯警示；2-红灯违规；3-严重失信）
		String warningType = req.getParameter("warningType");
		if(StringUtils.isNotEmpty(corpName)){
			corpName = URLDecoder.decode(corpName, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6f032d3c-33b7-4c75-b896-7d5f6e421c2a";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e08d0de0-8c04-4fc9-8d90-1108f2cc6a6a";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("corpName", corpName);
		json.put("warningType", warningType);
		json.put("pageNo", 1);
		json.put("pageSize", Integer.MAX_VALUE/2);
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("预付卡警示名单查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
