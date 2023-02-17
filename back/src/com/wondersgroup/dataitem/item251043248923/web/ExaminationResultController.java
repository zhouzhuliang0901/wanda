package com.wondersgroup.dataitem.item251043248923.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class ExaminationResultController {
	
	/**
	 * 考试类别
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/examinationResult/queryExaminationCategory.do")
	public void queryExaminationCategory(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String appName = "d835eeae-4059-4cca-b773-d72772741bde";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,"",contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 考试年月
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/examinationResult/queryExaminationYear.do")
	public void queryExaminationYear(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String code = req.getParameter("code");
		String appName = "93ceccee-cb2f-4b58-adfa-684475db9c99";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 查询成绩
	 * 返回有（1）/没有（0）
	 * 生成证明
	 * 返回成功（1）/失败（0）
	 * 上海市计算机等级考：31000000000003
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/examinationResult/queryAndCreatCertificate.do")
	public void queryAndCreatCertificate(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String code = req.getParameter("code");
		String year = req.getParameter("year");
		String card = req.getParameter("idCard");// 310112199802105619
		String name = req.getParameter("name");
		String pinyin = req.getParameter("pinyin");
		// query：查询成绩；create：生成证明
		String type = req.getParameter("type");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String appName = "";
		if("query".equals(type)){
			appName = "42136e89-c7f2-4bf5-9cad-29b2a916a783";
		} else if("create".equals(type)){
			appName = "0e12c05c-65b4-4192-8f8d-1d8579e897e7";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("code", code);
		obj.put("year", year);
		obj.put("card", card);
		obj.put("name", name);
		obj.put("pinyin", pinyin);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
