package com.wondersgroup.dataitem.item310750019000.utils;


import java.util.Iterator;

import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.json.XML;
import org.springframework.util.CollectionUtils;


import com.alibaba.fastjson.JSONObject;


public class handleXml {
	
	public static final String ENCODING = "UTF-8";
	
	
	//拼接body请求
	public static String buildBody(String json,String code)
	{
		
		
		 String bodyXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>" + 
//	        		"<url>http://10.8.205.36:9080/cmc-cics-ld/services/dispatchService</url><flag>0</flag>" +
	        		"<url/><flag>0</flag><subsys>ld</subsys>" +
	        		"<message><![CDATA[" +
	        		"<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head>" +
	        		"<version>1.0</version><ref>20190925090922TEST0000118165262</ref><code>"+code+"</code><src><code>31011712000001</code><uid>3101171000000256</uid>" +
	        		"<uname>蔡洁</uname><uexpinf></uexpinf></src><dest><code>SB</code><uid>0000000003</uid><uname>caij</uname><uexpinf></uexpinf></dest><time>20190925090653</time><ext></ext><sign></sign>" +
	        		"<rst><syscode>FF</syscode><buscode></buscode><errmsg></errmsg></rst><affairno>3101171200000120190925000033</affairno><suid>179020119003097</suid></head>" +
	        		"<body>"+buildData(json)+"</body>" +
	        		"</msg>]]></message>" + "</data>";
		
		return bodyXml;
	}
	
	//拼接body参数
	private static String buildData(String json)
	{
		JSONObject jsonObject = JSONObject.parseObject(json);
		String params = "";
		if (!CollectionUtils.isEmpty(jsonObject)) {
			Set<Entry<String, Object>> set = jsonObject.entrySet();
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
		}
		if (StringUtils.isNotEmpty(params)) {
			return params;
		}
		return null;
	}
	

	public static String xmlToJson(String decodeStr)
	{
		org.json.JSONObject xmlJSONObj = XML.toJSONObject(decodeStr);
		if(!xmlJSONObj.isNull("data")) {
			decodeStr = xmlJSONObj.get("data").toString();
			JSONObject jsonObject = JSONObject.parseObject(decodeStr);
			if(jsonObject.containsKey("response")) {
				decodeStr = jsonObject.getString("response");
				xmlJSONObj = XML.toJSONObject(decodeStr);
				return xmlJSONObj.toString();
			}
		}
        return xmlJSONObj.toString();
	}
	
	
	
}
