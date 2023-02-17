package com.wondersgroup.dataitem.item208443121532.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;

/**
 * 公积金外省市转入本市
 * @author wanda
 *
 */
@Controller
public class AccumulationFundChangeController {
	
	/**
	 * 异地转移接续外地中心查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFundChange/otherProvincesQuery.do")
	public void otherProvincesQuery(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String placeName = req.getParameter("placeName");
		if(StringUtils.isNotEmpty(placeName)){
			placeName = URLDecoder.decode(placeName, "utf-8");
		}
		
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "df423d22-1357-48a0-bf65-8aa7633ed2e2";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "d049c49f-bc69-4499-96ae-6534a43a6756";
		}
		
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String paramStr = " <soapenv:Envelope 	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+" <soap:Header 	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+" </soap:Header>"
					+" <soapenv:Body>"
					+" <impl:GJGL_YDDZH_WDZX_CX 	xmlns:impl=\"http://impl.ws.web.accumulation.capinfo.com/\">"
					+" <!--Optional:-->"
					+" <arg0>"+placeName+"</arg0>"
					+" </impl:GJGL_YDDZH_WDZX_CX>"
					+" </soapenv:Body>"
					+" </soapenv:Envelope>";
        
		String contentType = "text/xml";
		String result = HttpUtil.doPost(head,paramStr,contentType);
		System.out.println("异地转移接续外地中心查询返回结果："+result);
		Document document;
		try{
			document = DocumentHelper.parseText(result);
			result = document.asXML();
			String startStr = result.split("<return>")[1];
			result = startStr.split("</return>")[0];
		} catch (Exception e) {
			Log.debug("返回数据格式异常！");
			result = "";
		}

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 异地转移接续转入服务
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFundChange/applyAccumulationFundChange.do")
	public void applyAccumulationFundChange(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String custAcct = req.getParameter("custAcct");
		String grzh = req.getParameter("grzh");
		String telephone = req.getParameter("telephone");
		String zrzxmc = req.getParameter("zrzxmc");
		String zczxdm = req.getParameter("zczxdm");
		String sourcedwmc = req.getParameter("sourcedwmc");
		String sourcegrzh = req.getParameter("sourcegrzh");
		if(StringUtils.isNotEmpty(zrzxmc)){
			zrzxmc = URLDecoder.decode(zrzxmc, "utf-8");
		}
		if(StringUtils.isNotEmpty(sourcedwmc)){
			sourcedwmc = URLDecoder.decode(sourcedwmc, "utf-8");
		}
		String trace_no = VoucherNo();
		
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "ca5f382a-1a5e-48d1-bdc4-b509e79543c8";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "6a7a50f2-eaf6-4f7c-a15f-834c1337c8f0";
		}
		
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String paramStr = "<soapenv:Envelope 	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" <soap:Header 	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" </soap:Header>"
						+" <soapenv:Body>"
						+" <impl:GJGL_YDDZH_YDSQ_BL xmlns:impl=\"http://impl.ws.web.accumulation.capinfo.com/\">"
						    +" <!--Optional:交易码-->"
						    +" <tx_code>08xxx</tx_code>"
						    +" <!--Optional:交易流水号-->"
						    +" <trace_no>"+trace_no+"</trace_no>"
						    +" <!--Optional:单位帐号-->"
						    +" <cust_acct>"+custAcct+"</cust_acct>"
						    +" <!--Optional:转入个人账号-->"
						    +" <grzh>"+grzh+"</grzh>"
						    +" <!--Optional:业务联系电话-->"
						    +" <telephone>"+telephone+"</telephone>"
						    +" <!--Optional:转入公积金中心名称-->"
						    +" <zr_zxmc>"+zrzxmc+"</zr_zxmc>"
						    +" <!--Optional:转出公积金中心代码-->"
						    +" <zczxdm>"+zczxdm+"</zczxdm>"
						    +" <!--Optional:原工作单位名称-->"
						    +" <source_dwmc>"+sourcedwmc+"</source_dwmc>"
						    +" <!--Optional:原个人住房公积金账号-->"
						    +" <source_grzh>"+sourcegrzh+"</source_grzh>"
						    +" <!--Optional:来源类型-->"
						    +" <source_type>06</source_type>"
						+" </impl:GJGL_YDDZH_YDSQ_BL>"
						+" </soapenv:Body>"
						+" </soapenv:Envelope>"; 
        
        String contentType = "text/xml";
		String result = HttpUtil.doPost(head,paramStr,contentType);
		System.out.println("异地转移接续转入服务返回结果："+result);
		Document document;
		try{
			document = DocumentHelper.parseText(result);
			result = document.asXML();
			String startStr = result.split("<return>")[1];
			result = startStr.split("</return>")[0];
		} catch (Exception e) {
			Log.debug("返回数据格式异常！");
			result = "";
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 异地转移办理进度查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFundChange/queryProcessingProgress.do")
	public void queryProcessingProgress(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String account = req.getParameter("account");
		
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "27d91344-d1e0-4b7a-87a1-57dd0ee9052c";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "1948e2ca-c39a-43c7-8baa-914acc04a46f";
		}
		
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String paramStr = "<soapenv:Envelope 	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" <soap:Header 	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" </soap:Header>"
						+" <soapenv:Body>"
						+" <ws:GJGL_INTERCITY_INFO_QUERY_WS 	xmlns:ws=\"http://ws.web.accumulation.capinfo.com/\">"
						+" <!--Optional:公积金账号-->"
						+" <account>"+account+"</account>"
						+" <traceNo/>"
						+" <sqTime/>"
						+" <ywlx/>"
						+" <status/>"
						+" </ws:GJGL_INTERCITY_INFO_QUERY_WS>"
						+" </soapenv:Body>"
						+" </soapenv:Envelope>";
        String contentType = "text/xml";
  		String result = HttpUtil.doPost(head,paramStr,contentType);
  		System.out.println("异地转移办理进度查询返回结果："+result);
  		Document document;
  		try{
  			document = DocumentHelper.parseText(result);
  			result = document.asXML();
  			String startStr = result.split("<return>")[1];
  			result = startStr.split("</return>")[0];
  		} catch (Exception e) {
  			Log.debug("返回数据格式异常！");
  			result = "";
  		}
      	AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 异地转移接续撤销
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFundChange/revokeApply.do")
	public void revokeApply(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String account = req.getParameter("account");
		String trace_no = VoucherNo();
		
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "bfe7cb4d-0f96-4a71-9085-f6f82433fa6d";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "cd8a8b11-e1f1-4604-ba5b-010f7b110981";
		}
		
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String paramStr = " <soapenv:Envelope 	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+"<soap:Header 	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+"</soap:Header>"
						+"<soapenv:Body>"
						+"<ws:GJGL_ZDZYJX_CX_WS 	xmlns:ws=\"http://ws.web.accumulation.capinfo.com/\">"
						+"<trace_no>"+trace_no+"</trace_no>"
						+"<grzh>"+account+"</grzh>"
						+"<source_type>06</source_type>"
						+"</ws:GJGL_ZDZYJX_CX_WS>"
						+"</soapenv:Body>"
						+"</soapenv:Envelope>";
        String contentType = "text/xml";
  		String result = HttpUtil.doPost(head,paramStr,contentType);
  		System.out.println("异地转移接续撤销返回结果："+result);
  		Document document;
  		try{
  			document = DocumentHelper.parseText(result);
  			result = document.asXML();
  			String startStr = result.split("<return>")[1];
  			result = startStr.split("</return>")[0];
  		} catch (Exception e) {
  			Log.debug("返回数据格式异常！");
  			result = "";
  		}
      	AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	public static String VoucherNo(){
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "3699a912-094c-4b25-a0f3-7e729d4a7eee";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "c234a3af-ffdc-438b-97d7-e38154850d08";
		}
		
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        String paramStr = "<soapenv:Envelope 	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" <soap:Header 	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" </soap:Header>"
						+" <soapenv:Body>"
						+" <impl:GJGL_VOUCHER_NO 	xmlns:impl=\"http://impl.ws.web.accumulation.capinfo.com/\"/>"
						+" </soapenv:Body>"
						+" </soapenv:Envelope>";
        String contentType = "text/xml";
  		String result = HttpUtil.doPost(head,paramStr,contentType);
  		Document document;
  		try{
  			document = DocumentHelper.parseText(result);
  			result = document.asXML();
  			String startStr = result.split("<return>")[1];
  			result = startStr.split("</return>")[0];
  		} catch (Exception e) {
  			Log.debug("返回数据格式异常！");
  			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  			result = sdf.format(new Date())+"90000001";
  		}
  		return result;
	}
	
	private static String test(String xml, JSONObject oldJson){
		Document document;
		JSONObject json = new JSONObject();
		try {
			document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List<Element> rootList = root.elements();
			Iterator<Element> itRoot = rootList.iterator();
			while(itRoot.hasNext()){
				Element e = (Element) itRoot.next();
				String xmlName = e.getName();
				List<Element> eList = e.elements();
				if(eList.size() > 0){
					JSONObject newJson = new JSONObject();
					JSONObject eJson = new JSONObject();
					String str = test(e.asXML(), newJson).toString();
					eJson = JSONObject.fromObject(str);
					System.out.println(eJson);
					eJson.put(xmlName, JSONObject.fromObject(str));
					newJson.putAll(eJson);
					newJson.putAll(oldJson);
					oldJson = eJson;
					json = newJson;
				} else {
					String value = e.getText();
					json.put(xmlName, value);
				}
			}
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		return json.toString();
	}
	
	public static void main(String[] args) {
		File accessTokenFile = new File("C:\\Users\\wanda\\Desktop\\test\\test.xml");
		FileInputStream fis;
		try {
			fis = new FileInputStream(accessTokenFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			String xml = "";
			String getLine;
			while((getLine = in.readLine()) != null){
				xml += getLine;
			}
			fis.close();
			in.close();
			
			System.out.println(test(xml, new JSONObject()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
