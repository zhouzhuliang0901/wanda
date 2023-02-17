package com.wondersgroup.dataitem.item312001372000.utils;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

public class buildXml {
	
	public static String buildInformationListXml(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		String select = jsonObject.getString("selectV");
		try {
			select = new String(select.getBytes("utf-8"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String xml = "<soapenv:Envelope\r\n" + 
				"    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n" + 
				"    xmlns:tem=\"http://tempuri.org/\">\r\n" + 
				"    <soapenv:Header/>\r\n" + 
				"    <soapenv:Body>\r\n" + 
				"        <tem:GetDZZLTable>\r\n" + 
				"            <tem:key>31085455-b5e4-47bd-8d64-b2bdbab88d11</tem:key>\r\n" + 
				"            <tem:selectV>"+select+"</tem:selectV>\r\n" + 
				"            <tem:beginNum>"+jsonObject.getString("beginNum")+"</tem:beginNum>\r\n" + 
				"            <tem:countNum>"+jsonObject.getString("countNum")+"</tem:countNum>\r\n" + 
				"        </tem:GetDZZLTable>\r\n" + 
				"    </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		return xml;
	}
	
	public static String buildInformationDetailXml(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <tem:GetDZZLInfo>\r\n" + 
				"         <tem:key>31085455-b5e4-47bd-8d64-b2bdbab88d11</tem:key>\r\n" + 
				"         <tem:codeV>"+jsonObject.getString("codeV")+"</tem:codeV>\r\n" + 
				"      </tem:GetDZZLInfo>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		return xml;
	}
	
	public static String buildInformationPdfXml(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <tem:GetDZZLPDF>\r\n" + 
				"         <tem:key>31085455-b5e4-47bd-8d64-b2bdbab88d11</tem:key>\r\n" + 
				"         <tem:codeV>"+jsonObject.getString("codeV")+"</tem:codeV>         \r\n" + 
				"         <tem:codePDF>"+jsonObject.getString("codePDF")+"</tem:codePDF>\r\n" + 
				"         <tem:numV>"+jsonObject.getString("numV")+"</tem:numV>\r\n" + 
				"      </tem:GetDZZLPDF>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		return xml;
	}
}
