package com.wondersgroup.dataitem.item373192667922.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class SamplingMechanismQueryController {
	
	/**
	 * 核酸采样机构查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/samplingMechanism/querySamplingMechanism.do")
	public void querySamplingMechanism(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String area = req.getParameter("area");
		area = area == null ? "" : URLDecoder.decode(area, "utf-8");
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "07424a32-ae85-474a-9f49-e180ca32adc4";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "3a413919-4851-4263-af33-b182338bf8d8";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?area="+area;
		String str = HttpUtil.doGet(head, url, "GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
