package com.wondersgroup.dataitem.item312223221232.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class SettleDownController {
	
	/**
	 * 非上海生源高校毕业生落户查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/settleDown/querySettleInfo.do")
	public void querySettleInfo(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String idCard = req.getParameter("idCard");
		String userName = req.getParameter("userName");
		userName = URLDecoder.decode(userName,"utf-8");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a5c0faf2-643a-4e01-988a-fff1d6fbb1d6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a46723f1-218d-47f1-862c-d75a7c8aa4ef";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
//				+"?userID="+idCrd+"&username="+userName;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userID", idCard);
		paramMap.put("username", userName);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
