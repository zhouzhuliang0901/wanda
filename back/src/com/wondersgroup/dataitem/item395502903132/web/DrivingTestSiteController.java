package com.wondersgroup.dataitem.item395502903132.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class DrivingTestSiteController {
	
	/**
	 * 驾照考点查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/drivingTestSite/queryDrivingTestSite.do")
	public void queryDrivingTestSite(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "afcd686b-e2b7-4454-8768-4a003c55ccdf";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "24d9e21d-bb1a-478d-ad36-51ed0a4fd000";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
