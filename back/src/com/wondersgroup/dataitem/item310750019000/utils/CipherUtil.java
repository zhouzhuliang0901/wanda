package com.wondersgroup.dataitem.item310750019000.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.json.XML;

import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

public class CipherUtil {
	
	/**
	 * 加密
	 * @param data
	 * @return
	 */
	public static String encryptData(String data){
		String str = "";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "31364b17-9ff1-4577-838d-df732d00e2a6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "ae7d0870-8f84-4793-81c9-c5ca317e6c06";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
//	    String path = PensionAdjustmentController.class.getResource("").getPath()+"template/UserCert.cer";
//	    String path = "C:\\Users\\wanda\\Desktop\\自助终端\\“一网通办”自助终端接入事项清单\\人社\\不见面审批\\CA签名\\UserCert.cer";
//	    File file = new File(path);
//	    byte[] byts = FileUtil.getBytesFromFile(file);
//	    String baseStr = Base64Util.encode(byts);
	    //String baseStr = "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";
	    String baseStr = "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";
	    try {
			data = Base64.encodeBase64String(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e1) {
		}
	    String paramString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.security.openapi.sp.custle.com\">" +
	    		"<soapenv:Header/><soapenv:Body><web:encryptByPublicKey><web:in0><![CDATA[" +
	    		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
	    		"<Root>" +
	    		"<AppCode>ywxt</AppCode>" +
	    		"<AppPWD>12345678</AppPWD>" +
	    		"<Request>" +
	    		"<Cert>"+baseStr+"</Cert>" +
	    		"<InData>"+data+"</InData>" +
	    		"<InDataType>Base64</InDataType>" +
	    		"<Alg>RSA</Alg>" +
	    		"</Request>" +
	    		"</Root>]]>" +
	    		"</web:in0></web:encryptByPublicKey></soapenv:Body></soapenv:Envelope>";
	    
		String contentType = "application/xml;charset=utf-8";
		str = HttpUtil.doPost(head, paramString, contentType);
//		str = StringEscapeUtils.unescapeXml(str);
//		Document document;
//		try {
//			document = DocumentHelper.parseText(str);
//			Element root = document.getRootElement();
//			Element Body = root.element("Body");
//			Element encryptByPublicKeyResponse  = Body.element("encryptByPublicKeyResponse");
//			Element out = encryptByPublicKeyResponse.element("out");
//			Element Root = out.element("Root");
//			
//			@SuppressWarnings("unchecked")
//			List<Element> rootList = Root.elements();
//			JSONObject rstJson = new JSONObject();
//			for(Iterator<Element> itRst = rootList.iterator(); itRst.hasNext();){
//				Element rst = (Element) itRst.next();
//				String rstName = rst.getName();
//				String rstValue = rst.getText();
//				if("Response".endsWith(rstName)){
//					Element EncryptData = rst.element("EncryptData");
//					String name = EncryptData.getName();
//					String value = EncryptData.getText();
//					net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
//					obj.put(name, value);
//					rstJson.put(rstName, obj);
//				} else {
//					rstJson.put(rstName, rstValue);
//				}
//			}
//			str = rstJson.toString();
//		} catch (DocumentException e) {
//			str = "";
//		}
		str = dealResult(str,"encryptByPublicKeyResponse","EncryptData");
		if(StringUtils.isNotEmpty(str)){
			System.out.println(str);
		     net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(str);
		     net.sf.json.JSONObject Response = json.optJSONObject("Response");
		     try{
		    	 str = Response.optString("EncryptData");
		     } catch (Exception e) {
		    	 str = "";
			 }
		}
		
		return str;
	}
	
	/**
	 * 签名
	 * @param data
	 * @return
	 */
	public static String signData(String data){
        String str = "";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ad28ec64-1fba-4783-8a84-34eefb358f62";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "f49c2539-2813-471b-97ba-dd899c25d18e";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    String paramString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.security.openapi.sp.custle.com\">" +
	    		"<soapenv:Header/><soapenv:Body><web:signData><web:in0><![CDATA[" +
	    		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
	    		"<Root>" +
	    		"<AppCode>ywxt</AppCode>" +
	    		"<AppPWD>12345678</AppPWD>" +
	    		"<Request>" +
	    		"<ContainerName>Cont2</ContainerName>" +
	    		"<SignAlg>SHA256withRSA</SignAlg>" +
//	    		"<InData>5byg5LiJ6KaB6L+b6KGM562+5ZCN</InData>" +
				"<InData>"+data+"</InData>" +
				"<InDataType>Base64</InDataType>" +
	    		"<TransactionCode>1qwer2</TransactionCode>" +
	    		"</Request>" +
	    		"</Root>]]></web:in0></web:signData></soapenv:Body></soapenv:Envelope>";
	    
		String contentType = "application/xml;charset=utf-8";
		str = HttpUtil.doPost(head, paramString, contentType);
				
		str = dealResult(str,"signDataResponse","SignData");
		
		if(StringUtils.isNotEmpty(str)){
		     net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(str);
		     net.sf.json.JSONObject Response = json.optJSONObject("Response");
		     try{
		    	 str = Response.optString("SignData");
		     } catch (Exception e) {
		    	 str = "";
			 }
		}
		
		return str;
	}
	
	/**
	 * 验签
	 * @param data
	 * @return
	 */
	public static String verifySignData(String InData, String SignData){
		String str = "";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ad28ec64-1fba-4783-8a84-34eefb358f62";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "f49c2539-2813-471b-97ba-dd899c25d18e";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    String baseStr = "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";
	    
	    String paramString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.security.openapi.sp.custle.com\">" +
	    		"<soapenv:Header/><soapenv:Body><web:signData><web:in0><![CDATA[" +
	    		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
	    		"<Root>" +
	    		"<AppCode>ywxt</AppCode>" +
	    		"<AppPWD>12345678</AppPWD>" +
	    		"<Request>" +
	    		"<ContainerName>Cont2</ContainerName>" +
	    		//"<Cert>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</Cert>" +
	    		"<Cert>"+baseStr+"</Cert>" +
	    		"<SignAlg>SHA256withRSA</SignAlg>" +
	    		"<InData>"+InData+"</InData>" +
	    		"<SignData>"+SignData+"</SignData>" +
	    		"<InDataType>Base64</InDataType>" +
	    		"</Request>" +
	    		"</Root>]]>" +
	    		"</web:in0></web:signData></soapenv:Body></soapenv:Envelope>";
		String contentType = "application/xml;charset=utf-8";
		str = HttpUtil.doPost(head, paramString, contentType);
		
		str = StringEscapeUtils.unescapeXml(str);
		
		return  str;
	}
	
	/**
	 * 解密
	 * @param data
	 * @return
	 */
	public static String decryptData(String data){
//		data = data.replaceAll("\"", "");
//		System.out.println(data);
//		data = "ATEAAAAAAHsiaGVhZCI6eyJkYXRlIjoyMDIwMDcxNSwic3lzQ29kZSI6IjAwMDAiLCJyZWYiOiIwMDAwMjAyMDA3MTUwMDAwMDAwMCIsInJzdCI6eyJ0cmFkZUNvZGUiOjMxLCJpbmZvIjoi6K+35rGC5oql5paH5aS05pyJ6K+v77yM5peg5rOV6Kej5p6Q77yM6K+35qOA5p+l5pWw5o2u5qC85byP5oiW5a2X5q616ZW/5bqm5piv5ZCm5q2j56GuIn0sImJ1c0NvZGUiOiIwMDAwMDAwMCIsInJlU25kIjoiTiIsInRyYWRlU3JjIjoiTyIsInNlbmRlciI6IjAwMDAiLCJ0aW1lIjoxNjMxNTAsInZlcnNpb24iOiIxLjAuMCIsInJlY2l2ZXIiOiIwMDAwIn19";
		String str = "";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "8dd491d3-bd33-41aa-a2be-195582106dc4";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "de0e962f-2c53-4028-9d4e-c645e8d576fb";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    String paramString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.security.openapi.sp.custle.com\">" +
	    		"<soapenv:Header/><soapenv:Body><web:decryptByPrivateKey><web:in0><![CDATA[" +
	    		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
	    		"<Root>" +
	    		"<AppCode>ywxt</AppCode>" +
	    		"<AppPWD>12345678</AppPWD>" +
	    		"<Request>" +
	    		"<ContainerName>Cont2</ContainerName>" +
	    		//"<EncryptData>QsdAZgq7sSxBIZFX6OIC1JTG+Fx9WOZKrH5i/A+szsWmO/gvTJw+DB1FaCstgvlE3LjU/zXaEXYcfmJ1PcfYQFZAY42yUbQnojdOEEXH39KlbG5OJZzfFZKZ2+ksK+6oAsagE4GC5JRwEyVmmUc7s0IrOaIzCs+VOIWNyGvIyKmzhNbqE2NJ0g5MKj/EYz9zoGMyrRXUQDMyuH9FmQghbBpbCuYxZ3VeD/ot0QRsU+Es5HrDRRhmbAzwK7SmXwiPrVtvxNhfNLk9NMJigc2dXIAv4mvB68ZZ/juq/PgBJeROpEnIVeFHNkD3x+1nK1NlrgJCiP6gfCOnNZVm4GYggQ==</EncryptData>" +
	    		"<EncryptData>"+data+"</EncryptData>" +
	    		"<InDataType>Base64</InDataType>" +
	    		"<Alg>RSA</Alg>" +
	    		"</Request>" +
	    		"</Root>]]>" +
	    		"</web:in0></web:decryptByPrivateKey></soapenv:Body></soapenv:Envelope>";
		
		String contentType = "application/xml;charset=utf-8";
		str = HttpUtil.doPost(head, paramString, contentType);
		
		return str;
	}
	
	public static String dealResult(String result, String algorithmTag, String responseTag){
		result = StringEscapeUtils.unescapeXml(result);
		if("OutData".equals(responseTag)){
			result = result.replaceAll("\\?xml", "\\?lmx");
		}

		Document document;
		try {
			document = DocumentHelper.parseText(result);
			
			Element root = document.getRootElement();
			Element Body = root.element("Body");
			Element signDataResponse = Body.element(algorithmTag);
			Element out = signDataResponse.element("out");
			Element Root = out.element("Root");
			
			List<Element> rootList = Root.elements();
			JSONObject rstJson = new JSONObject();
			for(Iterator<Element> itRst = rootList.iterator(); itRst.hasNext();){
				Element rst = (Element) itRst.next();
				String rstName = rst.getName();
				String rstValue = rst.getText();
				if("Response".equals(rstName)){
					Element SignData = rst.element(responseTag);
					String name = SignData.getName();
					String value = "";
					if("OutData".equals(responseTag)){
						value = SignData.asXML();
						value = value.replace("<OutData>", "").replace("</OutData>", "").replaceAll("\\?lmx", "\\?xml");
						value = dealOutData(value);
					} else {
						value = SignData.getText();
					}
					net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
					obj.put(name, value);
					rstJson.put(rstName, obj);
				} else {
					rstJson.put(rstName, rstValue);
				}
			}
			return rstJson.toString();
		} catch (DocumentException e) {
			return "";
		}
	}
	
	public static String dealOutData(String val){
		String str = "";
		
		String[] strArr = val.split("\\<\\!\\[CDATA\\[");
		val = strArr[1].split("\\]\\]\\>")[0];
		try {
			Document document = DocumentHelper.parseText(val);
			JSONObject parseJSON = XML.toJSONObject(document.asXML());
			str = parseJSON.toString();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return str;
	}
}
