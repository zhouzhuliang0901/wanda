package com.wondersgroup.dataitem.item368903489233.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class IssueOfPassController {
	
	/**
	 * 往来港澳台通行证和签注签发；首次申请/过期申请/换发申请
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/passPermit/applyPassPermit.do")
	public void applyPassPermit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String param = req.getParameter("param");
		if(StringUtils.isNotEmpty(param)){
			param = URLDecoder.decode(param, "utf-8");
		}
		
		// 港澳：本市户籍办理接口：d23278bf-28a5-4562-a367-6a954c1cfa67
		// 港澳：外省市户籍办理接口：8b74e784-6129-4cad-81b5-47cc9ef29df3
		// 台湾：本市户籍办理接口：682a4348-ef8f-407d-9215-51eb261bb938
		// 台湾：外省市户籍办理接口：68fcfa43-48f2-42a4-b42b-afe6e57fc7df
		String appName = req.getParameter("appName");
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param,contentType);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
