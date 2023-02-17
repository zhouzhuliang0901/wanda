package com.wondersgroup.dataitem.item202251999433.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;


@Controller
public class EnterpriseQualificationController {
	
	/**
	 * 上海市建设市场管理信息平台企业资质
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/enterpriseQualification/queryQualification.do")
	public void queryQualification(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String companyName = req.getParameter("companyName");
		String creditCode =  req.getParameter("creditCode");
		if(StringUtils.isNotEmpty(companyName)){
			companyName = URLDecoder.decode(companyName, "utf-8");
		}
		
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "fd2879f0-51a7-11ed-b54c-338cfdbbb120";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "e9719510-5344-11ed-9afd-ff274f79b579";
		}
		String sign = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(sign, appName);
		
		String appkey = "1b5aa885e64a41de8759ea88d92a3bfa";
		String serviceCode = "nXxIoCuUv0PrY773";
		String appSecret = "RIJIJISF";
		String timestamp = Long.toString(System.currentTimeMillis());
		String signature = DigestUtils.sha1Hex(appkey+appSecret+timestamp);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appkey", appkey);
		paramMap.put("serviceCode", serviceCode);
		paramMap.put("signature", signature);
		paramMap.put("timestamp", timestamp);
		paramMap.put("DWMC", companyName);
		paramMap.put("YYZZZCH", creditCode);
		String result = HttpUtil.doPost(head, paramMap);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
