package com.wondersgroup.dataitem.item382712525238.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item213072044522.utils.XmlUtil;

/**
 * 长护险申请
 * @author XB
 *
 */
@Controller
public class LongTermInsuranceController {
	
	/**
	 * 长护险申请-个人信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/longTermInsurance/queryPersonInfo.do")
	public void queryPersonInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String zhh = req.getParameter("zhh");
		String bxh = req.getParameter("bxh");
		String xm = req.getParameter("xm");
		String sfzh = req.getParameter("sfzh");
		zhh = "37773961";
		xm = "邹天奇";
		sfzh = "430426199804106174";
		
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
		body.put("bxh", bxh == null ? "" : bxh);
		body.put("xm", xm);
		body.put("sfzh", sfzh);
		
		String xmlStr = XmlUtil.creatXML("yb0061c0",body);
		
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
	 * 长护险申请-街道信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/longTermInsurance/queryStreetInfo.do")
	public void queryStreetInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String qxdm = req.getParameter("qxdm");
		String jdId = req.getParameter("jdId");
		qxdm = "hjqx";
		
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
		body.put("qxdm", qxdm);
		body.put("jd_id", jdId);
		
		String xmlStr = XmlUtil.creatXML("yb0061q4",body);
		
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
        
        try {
        	resultXml = resultXml.substring(1, resultXml.length());
			Document document = DocumentHelper.parseText(resultXml);
			System.out.println(document.asXML());
			result = XmlUtil.parseXml(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 长护险申请-提交
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/longTermInsurance/submitApply.do")
	public void submitApply(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
//		String zhh = req.getParameter("zhh");
//		String zhlx = req.getParameter("zhlx");
//		String xm = req.getParameter("xm");
//		String xb = req.getParameter("xb");
//		String nl = req.getParameter("nl");
//		String sfzh = req.getParameter("sfzh");
//		String sbkh = req.getParameter("sbkh");
//		String hjqx = req.getParameter("hjqx");
//		String hjjd = req.getParameter("hjjd");
//		String hjjw = req.getParameter("hjjw");
//		String hjl = req.getParameter("hjl");
//		String hjn = req.getParameter("hjn");
//		String hjh = req.getParameter("hjh");
//		String hjs = req.getParameter("hjs");
//		String zzqx = req.getParameter("zzqx");
//		String zzjd = req.getParameter("zzjd");
//		String zzjw = req.getParameter("zzjw");
//		String zzl = req.getParameter("zzl");
//		String zzn = req.getParameter("zzn");
//		String zzh = req.getParameter("zzh");
//		String zzs = req.getParameter("zzs");
//		String sxdz = req.getParameter("sxdz");
//		String sxyb = req.getParameter("sxyb");
//		String dhhm = req.getParameter("dhhm");
//		String sjhm = req.getParameter("sjhm");
//		String sqrxm = req.getParameter("sqrxm");
//		String sqrsfzh = req.getParameter("sqrsfzh");
//		String sqrdh = req.getParameter("sqrdh");
//		String sqrsj = req.getParameter("sqrsj");
//		String sqryx = req.getParameter("sqryx");
//		String jtysxm = req.getParameter("jtysxm");
//		String jtyslxdh = req.getParameter("jtyslxdh");
//		String jtysdw = req.getParameter("jtysdw");
//		String sfdb = req.getParameter("sfdb");
//		String sfdsr = req.getParameter("sfdsr");
//		String pglx = req.getParameter("pglx");
//		String xq = req.getParameter("xq");
//		String sd = req.getParameter("sd");
//		String pgzt = req.getParameter("pgzt");
//		String bzlx = req.getParameter("bzlx");
//		String sqh = req.getParameter("sqh");
//		String zdhljg = req.getParameter("zdhljg");
//		String hllx = req.getParameter("hllx");
//		String chxzg = req.getParameter("chxzg");
		
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
//		body.put("qxdm", qxdm);
//		body.put("jd_id", jdId);
		
		String xmlStr = XmlUtil.creatXML("yb0061s1",body);
		
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
        
        try {
        	resultXml = resultXml.substring(1, resultXml.length());
			Document document = DocumentHelper.parseText(resultXml);
			System.out.println(document.asXML());
			result = XmlUtil.parseXml(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
