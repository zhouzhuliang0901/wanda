package com.wondersgroup.dataitem.item283852484724.utils;

import java.util.Map;

import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

public class StandardUitl {
	
	public static String getStandard(){
		String standard = "";
		String appraiseCode = "A013";
		String appName = "a91ffe70-6a10-11ec-bebb-a7e38ac68bb1";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?appraiseCode="+appraiseCode;
		standard = HttpUtil.doGet(head, url, "");
		return standard;
	}
}
