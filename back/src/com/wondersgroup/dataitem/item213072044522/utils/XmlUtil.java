package com.wondersgroup.dataitem.item213072044522.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;

public class XmlUtil {
	
	public static String creatXML(String code, Map<String ,String> map){
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("utf-8");
		Element Req = document.addElement("msg");
		Element head = Req.addElement("head");
		Element body = Req.addElement("body");
		
		head.addElement("version").setText("1.0");
		head.addElement("ref").setText("0000000001");
		head.addElement("code").setText(code);
		head.addElement("time").setText(String.valueOf(System.currentTimeMillis()));
		head.addElement("ext");
		head.addElement("sign");
		head.addElement("affairno").setText(UUID.randomUUID().toString());
		head.addElement("suid").setText("000000000000000");
		
		Element src = head.addElement("src");
		src.addElement("code").setText("ZX0000013");
		src.addElement("uid").setText("13545");
		src.addElement("uname").setText("一网通办");
		src.addElement("uexpinf");
		
		Element dest = head.addElement("dest");
		dest.addElement("code").setText("ZX0000013");
		dest.addElement("uid").setText("13545");
		dest.addElement("uname").setText("一网通办");
		dest.addElement("uexpinf");
		
		Element rst = head.addElement("rst");
		rst.addElement("syscode").setText("FF");
		rst.addElement("buscode").setText("一网通办");
		rst.addElement("errmsg");
		
		// body标签写入业务参数
		Set<Entry<String, String>> set = map.entrySet();
		for(Entry<String, String> e : set){
			body.addElement(e.getKey()).setText(e.getValue() == null ? "" : e.getValue());
		}
		
		System.out.println("医保大接口请求报文的业务XML："+document.asXML());
		byte[] message = null;
		String xmlStr = "";
		try {
			message = document.asXML().getBytes("UTF-8");
			xmlStr = Base64Util.encode(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xmlStr;
	}
	
	public static String parseXml(Document document){
		Element root = document.getRootElement();
		Element xmlBody = root.element("body");
		Element xmlHead = root.element("head");
		List<Element> bodyList = xmlBody.elements();
		List<Element> headList = xmlHead.elements();
		JSONObject json = new JSONObject();
		JSONObject bodyJson = new JSONObject();
		JSONObject headJson = new JSONObject();
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
		for (Iterator<Element> ithead = headList.iterator(); ithead.hasNext();) {
			Element e = (Element) ithead.next();
			String xmlName = e.getName();
			if("rst".equals(xmlName)){
				List<Element> rstList = e.elements();
				JSONObject rstJson = new JSONObject();
				for(Iterator<Element> itRst = rstList.iterator(); itRst.hasNext();){
					Element rst = (Element) itRst.next();
					String rstName = rst.getName();
					String rstValue = rst.getText();
					rstJson.put(rstName, rstValue);
				}
				headJson.put(xmlName, rstJson);
			} else {
				String value = e.getText();
				headJson.put(xmlName, value);
			}
		}
		json.put("head",headJson);
		json.put("body",bodyJson);
		
		return json.toString();
	}
	
	public static String getResult(Map<String, String> head, String xmlStr){
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
        
        try {
        	String resultXml = new String(resultByte,"UTF-8");
        	resultXml = resultXml.substring(1, resultXml.length());
			Document document = DocumentHelper.parseText(resultXml);
			System.out.println(document.asXML());
			result = XmlUtil.parseXml(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
