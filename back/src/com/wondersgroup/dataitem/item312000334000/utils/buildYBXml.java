package com.wondersgroup.dataitem.item312000334000.utils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.XML;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class buildYBXml {
	
	public static String buildxml(String code,String json) {
		JSONObject obj = JSONObject.parseObject(json);
		String data = builderData(code.toLowerCase().trim(), obj);
//		System.out.println(data);
		byte[] bytes = null;
		try {
			bytes = data.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		data = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:com=\"http://common.cmc.wondersgroup.com\">\r\n" + 
				"<soapenv:Header/>\r\n" + 
				"<soapenv:Body>\r\n" + 
				"   <com:service soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\r\n" + 
				"      <flag xsi:type=\"xsd:byte\">1</flag>\r\n" + 
				"      <message xsi:type=\"soapenc:base64Binary\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+Base64.encode(bytes)+"</message>\r\n" + 
				"   </com:service>\r\n" + 
				"</soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		return data;
	}
	
	private static String builderData(String code,JSONObject map) {
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<msg>");
		buildHead(sb, code);
		buildBody(sb, map);
		sb.append("</msg>");
		return sb.toString();
	}
	
	private static void buildHead(StringBuffer sb, String code) {
		sb.append("<head>");
		sb.append("<version>1.0</version>");
		sb.append("<ref>0000000001</ref>");
		sb.append("<code>");
		sb.append(code);
		sb.append("</code>");
		sb.append("<time>");
		sb.append(System.currentTimeMillis());
		sb.append("</time>");
		sb.append("<ext/>");
		sb.append("<sign/>");
		sb.append("<affairno>38996dc9-0d89-4f58-b3df-62cd68226ea9</affairno>"); // 办件编号
		sb.append("<suid>000000000000000</suid>"); // 网厅编码
		sb.append("<src>");
		sb.append(buildOrganDesc());
		sb.append("</src>");
		sb.append("<dest>");
		sb.append(buildOrganDesc());
		sb.append("</dest>");
		sb.append("<rst>");
		sb.append("<syscode>");
		sb.append("FF");
		sb.append("</syscode>");
		sb.append("<buscode>");
		sb.append("一网通办");
		sb.append("</buscode>");
		sb.append("<errmsg/>");
		sb.append("</rst>");
		sb.append("</head>");
	}
	
	private static String buildOrganDesc() {
		return String.format("<code>%s</code><uid>%s</uid><uname>%s</uname><uexpinf/>", "ZX0000013", "13545", "一网通办");
	}
	

	/**
	 * 构建xml请求体
	 * @param sb
	 * @param
	 * @return
	 */
	private static void buildBody(StringBuffer sb, JSONObject map) {
		sb.append("<body>");
		String params = buildXmlParams(map);
		if (StringUtils.isNotEmpty(params)) {
			sb.append(params);
		}
		sb.append("</body>");
	}

	/**
	 * 构建xml格式的参数列表
	 * 
	 * @param map
	 * @return
	 */
	private static String buildXmlParams(JSONObject map) {
		if (!CollectionUtils.isEmpty(map)) {
			String params = "";
			Set<Entry<String, Object>> set = map.entrySet();
			Iterator<Entry<String, Object>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				String key = entry.getKey().toLowerCase().trim();
				Object v = entry.getValue();
				if (v instanceof String) {
					String value = (String) entry.getValue();
					params += StringUtils.isBlank(value) ? "<" + key + "/>"
							: "<" + key + ">" + entry.getValue() + "</" + key + ">";
				} else if(v instanceof Integer) {
					params += "<" + key + ">" + entry.getValue() + "</" + key + ">";
				}

			}
			return params;
		}
		return null;
	}
	
	public static String dealXml(String result) {
		result = dealResult(result,"serviceResponse","serviceReturn");
		return result;
		
	}
	 private static String dealResult(String result, String algorithmTag, String responseTag)  {
	        result = StringEscapeUtils.unescapeXml(result);
	        if("OutData".equals(responseTag)){
	            result = result.replaceAll("\\?xml", "\\?lmx");
	        }

	        Document document = null;

	        try {
	            document = DocumentHelper.parseText(result);
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }

	        Element root = document.getRootElement();
	        Element Body = root.element("Body");
	        Element signDataResponse = Body.element(algorithmTag);
	        Element serviceReturn = signDataResponse.element(responseTag);

//	        JSONObject rstJson = new JSONObject();
	        String value = serviceReturn.getText();
	        value = dealOutData(value);
	        return value;


	    }
	 private static String dealOutData(String val) {
	        String str = "";
	        byte[] bytes = Base64.decode(val);
	        try{
	        val = new String(bytes,"utf-8").replaceAll("\\u0001", "");
	        }catch (UnsupportedEncodingException e){
	            e.printStackTrace();
	        }
	        try {
	            Document document = DocumentHelper.parseText(val);
	            org.json.JSONObject parseJSON = XML.toJSONObject(document.asXML());
	            str = parseJSON.toString();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	        return str;
	    }
}







