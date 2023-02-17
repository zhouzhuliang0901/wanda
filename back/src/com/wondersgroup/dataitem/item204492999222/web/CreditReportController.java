package com.wondersgroup.dataitem.item204492999222.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.Pdf2pngUtil;
import com.wondersgroup.common.utils.PdfToJpeg;
import com.wondersgroup.dataitem.item204492999222.utils.GmUtil;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

@Controller
public class CreditReportController {
	
	/**
	 * 信用报告查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/creditReport.do")
	public void creditReport(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String SFZH = req.getParameter("SFZH");
		String userName = req.getParameter("userName");
		if(StringUtils.isNotEmpty(userName)){
			userName = URLDecoder.decode(userName, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c208380e-2f9e-4b0b-aa86-d4f05c8d174a";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c208380e-2f9e-4b0b-aa86-d4f05c8d174a";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String paramStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.triman.com\">"
		        +"<soapenv:Header/>"
		        +"<soapenv:Body>"
		           +"<web:queryZrrHonest>"
		              +"<web:in0>"+userName+"</web:in0>"
		              +"<web:in1>"+SFZH+"</web:in1>"
		              +"<web:in2></web:in2>"
		              +"<web:in3>smzy</web:in3>"
		              +"<web:in4>Smzy12#$</web:in4>"
		              +"<web:in5>00</web:in5>"
		           +"</web:queryZrrHonest>"
		        +"</soapenv:Body>"
		     +"</soapenv:Envelope>";
        
        String result = HttpUtil.doPost(head, paramStr, "text/xml");
        
        String[] arr = result.split("<ns1:out>");
        result = arr[1].split("</ns1:out>")[0];
        net.sf.json.JSONObject jsonResult = net.sf.json.JSONObject.fromObject(result);
        String pdfStr = jsonResult.optString("pdf");
        
        byte[] pdfByte = new BASE64Decoder().decodeBuffer(pdfStr);
        
        String pdfPath = RdConfig.get("reindeer.credit.pdf.url")+"\\";
        String fileName = "creditPDF_"+SFZH+"_"+System.currentTimeMillis()+".pdf";
        pdfPath += fileName;
		FileUtil.getFileFromBytes(pdfByte, pdfPath);
		String pngPath = CreditReportController.class.getResource("").getPath()
				+"template/creditPNG"+SFZH+"_"+System.currentTimeMillis()+"_";
		List<String> list = Pdf2pngUtil.pdf2png(pdfPath, pngPath, "jpg");
		JSONArray arrResult = new JSONArray();
		File file = null;
		for(String path : list){
			net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			byte[] pngByte = PdfToJpeg.image2byte(path);
			String pngStr = new BASE64Encoder().encode(pngByte);
			json.put("png", pngStr);
			arrResult.add(json);
			file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		jsonResult.put("pngList", arrResult);
		jsonResult.put("pdf", fileName);
		jsonResult.put("pdfBase64", pdfStr);
		
        AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/**
	 * 查询自然人信息获得查询编号
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/creditReport/getQueyZrrrCxbhThirdParty.do")
	public void getQueyZrrrCxbhThirdParty(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		String cxyt = req.getParameter("cxyt");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		if(StringUtils.isNotEmpty(cxyt)){
			cxyt = URLDecoder.decode(cxyt, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "bf4cc7e0-fb48-11ec-890f-9130cf5ba4d4";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "bf4cc7e0-fb48-11ec-890f-9130cf5ba4d4";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    String iv = GmUtil.getGUID();
	    String nonce = GmUtil.getGUID().toLowerCase();
	    Long timestamp = System.currentTimeMillis();
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("ztmc", name);
	    params.put("ztdm", idCard);
	    params.put("mbbh", "cs10375a087a5740a3b1d85353a9be663c");
	    params.put("cxyt", cxyt);
	    String data = GmUtil.sm4Encrypt(JSONObject.toJSONString(params), GmUtil.secretKey, iv);
	    String signParams = "appKey=" + GmUtil.appKey + "&secretKey=" + GmUtil.secretKey + "&iv=" + iv + 
	    		"&timestamp=" + timestamp + "&nonce=" + nonce + "&data=" + data;
	    String sign = GmUtil.sm3Digest(signParams);
	    
	    HashMap<String, Object> jsonObject= new HashMap<String, Object>();
	    jsonObject.put("appKey", GmUtil.appKey);
	    jsonObject.put("iv", iv);
	    jsonObject.put("timestamp", timestamp.toString());
	    jsonObject.put("nonce", nonce);
	    jsonObject.put("encodedata", data);
	    jsonObject.put("sign", sign);
	    Log.debug("查询自然人信息获得查询编号参数："+JSONObject.toJSONString(jsonObject));
	    
	    String postResult = HttpUtil.doPost(head, JSONObject.toJSONString(jsonObject), 
	    		"application/json;charset=utf-8");
	    Log.debug("查询自然人信息获得查询编号结果："+postResult);
	    JSONObject encryptResult = new JSONObject();
	    try{
	    	encryptResult = (JSONObject) JSONObject.parse(postResult);
	    	if(200 == encryptResult.getInteger("code")){
	    		String encryptString = encryptResult.getString("data");
	    		String decryptResult = GmUtil.sm4Decrypt(encryptString, GmUtil.secretKey, iv);
	    		encryptResult.put("data", decryptResult);
	    	}
	    } catch (Exception e) {
	    	Log.debug(e);
		}
	    AciJsonHelper.writeJsonPResponse(req, res, encryptResult.toString());
	}
	
	/**
	 * 通过查询编号获得信用报告PDF数据
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/creditReport/getQueryXydaCxbhPdf.do")
	public void getQueryXydaCxbhPdf(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String cxbh = req.getParameter("cxbh");
		// String-base64文件；byteArray-文件流
		String fileType = req.getParameter("fileType");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "8f6dd640-fb48-11ec-890f-9130cf5ba4d4";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "8f6dd640-fb48-11ec-890f-9130cf5ba4d4";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    String iv = GmUtil.getGUID();
	    String nonce = GmUtil.getGUID().toLowerCase();
	    Long timestamp = System.currentTimeMillis();
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("cxbh", cxbh);
	    String data = GmUtil.sm4Encrypt(JSONObject.toJSONString(params), GmUtil.secretKey, iv);
	    String signParams = "appKey=" + GmUtil.appKey + "&secretKey=" + GmUtil.secretKey + "&iv=" + iv + 
	    		"&timestamp=" + timestamp + "&nonce=" + nonce + "&data=" + data;
	    String sign = GmUtil.sm3Digest(signParams);
	    
	    HashMap<String, Object> jsonObject= new HashMap<String, Object>();
	    jsonObject.put("appKey", GmUtil.appKey);
	    jsonObject.put("iv", iv);
	    jsonObject.put("timestamp", timestamp.toString());
	    jsonObject.put("nonce", nonce);
	    jsonObject.put("encodedata", data);
	    jsonObject.put("sign", sign);
	    Log.debug("通过查询编号获得信用报告PDF数据参数："+JSONObject.toJSONString(jsonObject));
	    
	    String postResult = HttpUtil.doPost(head, JSONObject.toJSONString(jsonObject), 
	    		"application/json;charset=utf-8");
	    JSONObject encryptResult = new JSONObject();
	    try{
	    	encryptResult = (JSONObject) JSONObject.parse(postResult);
	    	if(200 == encryptResult.getInteger("code")){
	    		String encryptString = encryptResult.getString("data");
	    		if(StringUtils.isNotEmpty(encryptString)){
		    		String decryptResult = GmUtil.sm4Decrypt(encryptString, GmUtil.secretKey, iv);
		    		if(StringUtils.isNotEmpty(fileType)){
			    		JSONObject fileData = dealPDF(decryptResult, cxbh, fileType);
			    		encryptResult.put("data", fileData);
		    		} else {
		    			encryptResult.put("data", decryptResult);
		    		}
	    		}
	    	}
	    } catch (Exception e) {
	    	Log.debug(e);
		}
	    
	    AciJsonHelper.writeJsonPResponse(req, res, encryptResult.toString());
	}
	
	private JSONObject dealPDF(String pdf, String cxbh, String fileType) throws IOException{
		JSONObject jsonResult = new JSONObject();
		byte[] pdfByte = Base64Util.decode(pdf);
        String pdfPath = RdConfig.get("reindeer.credit.pdf.url")+"\\";
        String fileName = "creditPDF_"+cxbh+"_"+System.currentTimeMillis()+".pdf";
        pdfPath += fileName;
		FileUtil.getFileFromBytes(pdfByte, pdfPath);
		String pngPath = CreditReportController.class.getResource("").getPath()
				+"template/creditPNG"+cxbh+"_"+System.currentTimeMillis()+"_";
		List<String> list = Pdf2pngUtil.pdf2png(pdfPath, pngPath, "jpg");
		JSONArray arrResult = new JSONArray();
		File file = null;
		for(String path : list){
			net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			byte[] pngByte = PdfToJpeg.image2byte(path);
			String pngStr = new BASE64Encoder().encode(pngByte);
			json.put("png", pngStr);
			arrResult.add(json);
			file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		jsonResult.put("pngList", arrResult);
		if("byteArray".equals(fileType)){
			jsonResult.put("pdf", fileName);
		} else if("String".equals(fileType)){
			jsonResult.put("pdfBase64", pdf);
		}
		return jsonResult;
	}
}
