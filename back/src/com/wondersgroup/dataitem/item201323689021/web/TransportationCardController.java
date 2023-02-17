package com.wondersgroup.dataitem.item201323689021.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.CharsetUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class TransportationCardController {
	
	/**
	 * 交通卡余额查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/transportationCard/queryBalance.do")
	public void queryBalance(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String cardno = req.getParameter("cardNo");
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
		Date date = new Date();
		String datetime = sdf.format(date);
		String channel = "21";
		String key = "5KNK9VBmRzc0qrjE";
		
		String token = DigestUtils.md5Hex(channel+key+datetime);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6d50c6d8-c319-4211-89ab-e836ac6fe121";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "9de94100-2385-4cd6-b76b-5decdc06180b";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
        // 设置参数到请求对象中
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(CharsetUtils.get("UTF-8"));
        builder.addPart("cardno", new StringBody(cardno,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("clientIP", new StringBody("10.81.16.56",ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("datetime", new StringBody(datetime,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("channel", new StringBody(channel,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("token", new StringBody(token,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		HttpEntity reqEntity = builder.build();
        
		String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHMMSS");
		Date date = new Date();
		String datetime = sdf.format(date);
		System.out.println(datetime);
		String channel = "21";
		String key = "5KNK9VBmRzc0qrjE";
		
		String token = DigestUtils.md5Hex(channel+key+datetime);
		System.out.println(token);
	}
}
