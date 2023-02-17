package com.wondersgroup.dataitem.item278613284423.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class LegalProfessionController {
	
	/**
	 * 法律执业考试-成绩查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/legalProfession/scoreInquiry.do")
	public void scoreInquiry(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String idCard = req.getParameter("idCard");
		String examYear = req.getParameter("examYear");
		String appName = "250ca81d-e7e4-4c62-b986-4d3d211653ca";
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("identity", idCard);
		paramMap.put("examYear", examYear);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 法律执业考试-资格证查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/legalProfession/qualificationCertificateInquiry.do")
	public void qualificationCertificateInquiry(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String idCard = req.getParameter("idCard");
		String examYear = req.getParameter("examYear");
		String appName = "2fa8d83c-a38b-408c-8a08-7d2a8a1fe100";
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("identity", idCard);
		paramMap.put("examYear", examYear);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 法律执业考试-准考证查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/legalProfession/admissionTicketInquiry.do")
	public void admissionTicketInquiry(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String idCard = req.getParameter("idCard");
		String examYear = req.getParameter("examYear");
		String appName = "c2a15f93-5fa3-45b0-afb5-123f7ad030da";
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("identity", idCard);
		paramMap.put("examYear", examYear);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
