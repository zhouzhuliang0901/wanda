package com.wondersgroup.dataitem.item213072044523.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
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
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class MedicalInsuranceInquiryController {

	/**
	 * 参保人个人信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/personalInfoQuery.do")
	public void personalInfoQuery(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String identNo = req.getParameter("indentNo");
		String type = req.getParameter("type");// 医保卡标志   0
		String accessToken = req.getParameter("access_token");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "600287ef-a341-4022-b0cf-3504657ba464";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "600287ef-a341-4022-b0cf-3504657ba464";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("access_token", accessToken);
		json.put("CXTJ", "3");
		json.put("YBKH", "");
		json.put("SBKH", "");
		json.put("SFZH", identNo);
		json.put("YBKBZ", type);
		System.out.println("参保人个人信息查询入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("参保人个人信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 个人城保账户基本信息及累计信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/urbanInsurance.do")
	public void urbanInsurance(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String zhh = req.getParameter("zhh");// 帐户号
		String ndbz = req.getParameter("ndbz");// 年度标志   职保可选：0：当年；1：历年；2：前年；3：大前年；居保可选：0：当年；1：历年；
		String ybbf = req.getParameter("ybbf");// 医保办法   0：职保；1：居保
		String mkbz = req.getParameter("mkbz");// 模块标志
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e83ced7e-ce78-4718-9c02-b2402aa969f6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e83ced7e-ce78-4718-9c02-b2402aa969f6";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("zhh", zhh);
		json.put("ndbz", ndbz);
		json.put("ybbf", ybbf);
		json.put("mkbz", mkbz);
		System.out.println("个人城保账户基本信息及累计信息查询入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("个人城保账户基本信息及累计信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 根据账户号获取医保详细信息
	 * 主要目的是获取参保单位代码，参保单位名称
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/getOrganByAccount.do")
	public void getOrganByAccount(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String zhh = req.getParameter("zhh");// 帐户号
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "815a6e21-e791-4f42-8fec-8c73bb5f1c2a";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "338f7e9f-851c-4fbe-bf2d-0bb29363e370";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("zhh",zhh);
		System.out.println("根据账户号获取医保详细信息入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("根据账户号获取医保详细信息结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 个人居保账户基本信息及累计信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/residentInsurance.do")
	public void residentInsurance(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String ZHH = req.getParameter("ZHH");
		String ND = req.getParameter("ND");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7cb1a199-ec61-48f5-bcb0-22f11d176d23";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7cb1a199-ec61-48f5-bcb0-22f11d176d23";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("ZHH", ZHH);
		json.put("ND", ND);
		System.out.println("个人居保账户基本信息及累计信息查询入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("个人居保账户基本信息及累计信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 综合减负试算
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/comprehensiveLoadReduction.do")
	public void comprehensiveLoadReduction(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String SFZH = req.getParameter("SFZH");
		// 1：在职；2：退休
		String ZTQK = req.getParameter("ZTQK");
		String NDBZ = req.getParameter("NDBZ");
		String ZGGZ = req.getParameter("ZGGZ");
		String accessToken = req.getParameter("access_token");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "14c13963-2244-4877-8d1e-a090a0016aff";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "14c13963-2244-4877-8d1e-a090a0016aff";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("access_token", accessToken);
		json.put("CXTJ", "3");
//		json.put("YBKH", "");
//		json.put("SBKH", "");
		json.put("SFZH", SFZH);
		json.put("YBKBZ", "0");
		json.put("ZTQK", ZTQK);
		json.put("NDBZ", NDBZ);
		json.put("ZGGZ", ZGGZ);
		System.out.println("综合减负试算入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("综合减负试算结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保就医明细费用查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/queryMedicalDetails.do")
	public void queryMedicalDetails(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String ZHH = req.getParameter("ZHH");// 账户号
		String JYLX = req.getParameter("JYLX");// 就医类型 01.门急诊02.内设门急诊03.住院04.急观05.大病06.家床07.购药08.账户购买保险
		String YYMC = req.getParameter("YYMC");// 医院名称
		if(StringUtils.isNotEmpty(YYMC)){
			YYMC = URLDecoder.decode(YYMC, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c6cc781e-1728-47d0-b577-2e80e380088f";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c6cc781e-1728-47d0-b577-2e80e380088f";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("ZHH", ZHH);
		json.put("JYLX", JYLX);
		json.put("YYMC", YYMC);
		System.out.println("医保就医明细费用查询入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("医保就医明细费用查询结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 个人账户清算信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/clearAccount.do")
	public void clearAccount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String ZHH = req.getParameter("ZHH");// 账户号
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "dad92428-cc3a-447c-9ef2-acae557731b9";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "dad92428-cc3a-447c-9ef2-acae557731b9";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("ZHH", ZHH);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("个人账户清算信息查询结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 业务办理进度查询
	 * YWLX：业务类型
	 * 01. 零星报销进度查询
	 * 02. 居保登记缴费进度查询
	 * 03. 帮困登记缴费进度查询
	 * 04. 帮困补助进度查询
	 * 05. 长护险申请进度查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/queryReimbursementProgress.do")
	public void queryReimbursementProgress(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
//		String SFZH = wrapper.getParameter("SFZH");// 310103193807283226
		String SQH = req.getParameter("SQH");// 申请号  0107CH101201808060001
		String YWLX = req.getParameter("YWLX");// 业务类型
		String accessToken = req.getParameter("access_token");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "afa4a844-1a2a-4f83-aea5-4040d85d51a4";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "afa4a844-1a2a-4f83-aea5-4040d85d51a4";
		}
		
//		String accessToken = HttpUtil.getAccessToken(userName, SFZH, mobile);
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("access_token", accessToken);
		json.put("SQH", SQH);
		json.put("YWLX", YWLX);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("业务办理进度查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 参保人基本信息查询,医保卡号、社保卡号查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/queryCardNo.do")
	public void queryCardNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String zhh = req.getParameter("zhh");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "045a026b-5d0a-4963-a672-a56052b68872";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1ee83671-3620-486d-b297-1019110a65da";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
					+" <msg> "
					 +" <head> "
				       +" <version>1.0</version> "
				       +" <ref>0000000001</ref> "
				       +" <code>ybcomq19</code> "
				       +" <time>"+System.currentTimeMillis()+"</time> "
				       +" <ext/> "
				       +" <sign/> "
				       +" <affairno>38996dc9-0d89-4f58-b3df-62cd68226ea9</affairno> "
				       +" <suid>000000000000000</suid> "
				       +" <src> "
				          +" <code>ZX0000013</code> "
				          +" <uid>13545</uid> "
				          +" <uname>一网通办</uname> "
				          +" <uexpinf/> "
				       +" </src> "
				       +" <dest> "
				          +" <code>ZX0000013</code> "
				          +" <uid>13545</uid> "
				          +" <uname>一网通办</uname> "
				          +" <uexpinf/> "
				       +" </dest> "
				       +" <rst> "
				          +" <syscode>FF</syscode> "
				          +" <buscode>一网通办</buscode> "
				          +" <errmsg/> "
				       +" </rst> "
				     +" </head> "
				     +" <body> "
				       +" <mkbz>000</mkbz> "
				       +" <zhh>"+zhh+"</zhh> "
				     +" </body> "
				     +" </msg>";
		
		BASE64Encoder encoder = new BASE64Encoder();
		String xmlStr = encoder.encode(xml.getBytes("UTF-8"));
		
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
		System.out.println("参保人基本信息查询（医保卡号、社保卡号查询）返回结果："+result);
		
		String[] arrStr = result.split("<serviceReturn xsi:type=\"soapenc:base64Binary\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		String base64XmlResult = arrStr[1].split("</serviceReturn>")[0];
        byte[] resultByte = Base64Util.decode(base64XmlResult);
        String resultXml = new String(resultByte,"UTF-8");
        try {
        	resultXml = resultXml.substring(1, resultXml.length());
			Document document = DocumentHelper.parseText(resultXml);
			
			
			Element root = document.getRootElement();
			Element xmlBody = root.element("body");
			List<Element> bodyList = xmlBody.elements();
			JSONObject json = new JSONObject();
			JSONObject bodyJson = new JSONObject();
			for (Iterator<Element> itBody = bodyList.iterator(); itBody.hasNext();) {
				Element e = (Element) itBody.next();
				String xmlName = e.getName();
				if("datas".equals(xmlName)){
					List<Element> datasList = e.elements();
					JSONArray datasArr = new JSONArray();
					for (Iterator<Element> itDatas = datasList.iterator(); itDatas.hasNext();) {
						Element datas = (Element) itDatas.next();
						List<Element> dataList = datas.elements();
						JSONObject dataJson = new JSONObject();
						for(Iterator<Element> itData = dataList.iterator(); itData.hasNext();){
							Element data = (Element) itData.next();
							String dataName = data.getName();
							String dataValue = data.getText();
							dataJson.put(dataName, dataValue);
						}
						datasArr.add(dataJson);
					}
					bodyJson.put(xmlName, datasArr);
				} else {
					String value = e.getText();
					bodyJson.put(xmlName, value);
				}
				
			}
			json.put("body",bodyJson);
			
			result = json.toString();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
