package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class IntelligentSearchController {
	
	/**
	 * 自助机服务搜索
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/intelligentSearch/itemSearch.do")
	public void itemSearch(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String keyWord = req.getParameter("keyWord");
		String deviceMac = req.getParameter("deviceMac");// 00-E2-69-22-27-86
		if(StringUtils.isNotEmpty(keyWord)){
			keyWord = URLDecoder.decode(keyWord, "utf-8");
		} else {
			keyWord = "";
		}
		
		String appName = "4646aaff-66b2-4b0b-be49-ff8ad57930f8";
		String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        url += "?q="+keyWord+"&deviceMac="+deviceMac+"&pageNo=1&pageSize="+Integer.MAX_VALUE/2;
        String result = HttpUtil.doGet(head,url,"GET");
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 自助机热门检索词
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/intelligentSearch/hotItemSearch.do")
	public void hotItemSearch(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String deviceMac = req.getParameter("deviceMac");// 00-E2-69-22-27-86
		
		String appName = "63484437-099a-4d61-a5ba-03ed345b7275";
		String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
        url += "?deviceMac="+deviceMac;
        String result = HttpUtil.doGet(head,url,"GET");
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
