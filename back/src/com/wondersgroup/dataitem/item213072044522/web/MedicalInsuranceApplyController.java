package com.wondersgroup.dataitem.item213072044522.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item213072044522.bean.MedicalDictionary;
import com.wondersgroup.dataitem.item213072044522.utils.XmlUtil;

@Controller
public class MedicalInsuranceApplyController {
	
	/**
	 * 医疗卡书面挂失
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/medicalInsuranceApply/reportTheLossOfCard.do")
	public void reportTheLossOfCard(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String zhh = req.getParameter("zhh");// 账户号 35755259
		String kh = req.getParameter("kh");// 医保卡号 6014216974
		String wtrxm = req.getParameter("wtrxm");// 委托人姓名
		String wtrsfzh = req.getParameter("wtrsfzh");// 委托人身份证号
		String mkbz = req.getParameter("mkbz");// 模块标志
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "045a026b-5d0a-4963-a672-a56052b68872";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1ee83671-3620-486d-b297-1019110a65da";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		Map<String ,String> body = new HashMap<String, String>();
		body.put("zhh", zhh);
		body.put("kh", kh);
		body.put("wtrxm", wtrxm);
		body.put("wtrsfzh", wtrsfzh);
		body.put("mkbz", mkbz);
		
		String xmlStr = XmlUtil.creatXML("yb0010s1",body);
		
		String result = XmlUtil.getResult(head,xmlStr);
		System.out.println("医疗卡书面挂失结果："+result);
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 撤销医疗卡书面挂失
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/medicalInsuranceApply/cancellationOfLossReport.do")
	public void cancellationOfLossReport(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String kh = req.getParameter("kh");// 医保卡号
		String knsj = req.getParameter("knsj");// 卡内数据    6014216974120256275150053039
		String wtrxm = req.getParameter("wtrxm");// 委托人姓名
		String wtrsfzh = req.getParameter("wtrsfzh");// 委托人身份证号
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "045a026b-5d0a-4963-a672-a56052b68872";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1ee83671-3620-486d-b297-1019110a65da";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		Map<String ,String> body = new HashMap<String, String>();
		body.put("kh", kh);
		body.put("knsj", knsj);
		body.put("wtrxm", wtrxm);
		body.put("wtrsfzh", wtrsfzh);
		
		String xmlStr = XmlUtil.creatXML("yb0010s2",body);
		
		String result = XmlUtil.getResult(head,xmlStr);
		System.out.println("撤销医疗卡书面挂失结果："+result);
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	@RequestMapping("/selfapi/medicalInsuranceApply/test.do")
	public void test(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// TODO
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "045a026b-5d0a-4963-a672-a56052b68872";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1ee83671-3620-486d-b297-1019110a65da";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		Map<String ,String> body = new HashMap<String, String>();
//		body.put("knsj", "6014216974120256275150053039");
//		body.put("mkbz", "000");
		body.put("sfzh", "310228198808070818");
//		body.put("djnd", "2019");
		
		String xmlStr = XmlUtil.creatXML("yb0013q4",body);
		
		String paramStr = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:com=\"http://common.cmc.wondersgroup.com\">"
				+" <soapenv:Header/>"
				   +" <soapenv:Body>"
				   +" <com:service soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
				      +" <flag xsi:type=\"xsd:byte\" xs:type=\"type:byte\" xmlns:xs=\"http://www.w3.org/2000/XMLSchema-instance\">1</flag>"
				      +" <message xsi:type=\"soapenc:base64Binary\" xs:type=\"type:base64Binary\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xs=\"http://www.w3.org/2000/XMLSchema-instance\">"+xmlStr+"</message>"
				   +" </com:service>"
				   +" </soapenv:Body>"
				+" </soapenv:Envelope>";
		
		String result = HttpUtil.doPost(head,paramStr,"text/xml");
		
		String[] arrStr = result.split("<serviceReturn xsi:type=\"soapenc:base64Binary\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		String base64XmlResult = arrStr[1].split("</serviceReturn>")[0];
        byte[] resultByte = Base64Util.decode(base64XmlResult);
        String resultXml = new String(resultByte,"UTF-8");
        System.out.println("医保大接口返回业务报文："+resultXml);
        
        try {
        	resultXml = resultXml.substring(1, resultXml.length());
			Document document = DocumentHelper.parseText(resultXml);
			result = XmlUtil.parseXml(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保字典查询-通过字典excel表获取
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/medical/getMedicalDictionaries.do")
	public void getMedicalDictionaries(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 0：户口地；1：居住地
		String type = req.getParameter("type");
		String parentKey = req.getParameter("parentKey");
		
		int sheet = Integer.valueOf(type);
		
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(sheet);
		params.setHeadRows(1);
		List<MedicalDictionary> dictionaryList = ExcelImportUtil.importExcel(
				new File(MedicalInsuranceApplyController.class.getResource("").getPath()+"template/医保区划字典.xlsx"),
				MedicalDictionary.class,
				params);
		
		JSONArray arr = new JSONArray();
		for(MedicalDictionary dictionary : dictionaryList){
			JSONObject obj = new JSONObject();
			if(StringUtils.isEmpty(parentKey)){
				if(StringUtils.isNotEmpty(dictionary.getParentKey())){
					continue;
				}
			} else {
				if(StringUtils.isEmpty(dictionary.getParentKey()) || !parentKey.equals(dictionary.getParentKey())){
					continue;
				}
			}
			obj.put("key", dictionary.getKey());
			obj.put("value", dictionary.getValue());
			obj.put("parentkey", dictionary.getParentKey() == null? "" : dictionary.getParentKey());
			arr.add(obj);
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	public static void main(String[] args) {
		String certNo = "ywtbUser#+sAjuldomhgUtyhS0eBS3jfMWM1r8pXZOKxxZDOCvfLehKCRjE8rxNX3s7g02sP+";
		certNo = certNo.replaceAll("\\+", "-");
		certNo = certNo.replaceAll("#", ",");
		System.out.println(certNo);
	}
}
