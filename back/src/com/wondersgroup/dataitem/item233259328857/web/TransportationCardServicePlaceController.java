package com.wondersgroup.dataitem.item233259328857.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class TransportationCardServicePlaceController {
	
	/**
	 * 交通卡网点查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/transportationCardServicePlace/queryServicePlace.do")
	public void queryServicePlace(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		// 半径，即附近多少米
		String nearby = req.getParameter("nearby");
		// 关键字检索
		String keyword = req.getParameter("keyword");
		// 区域名,按上海区域
		String areaName = req.getParameter("areaName");
		// 网点类型，多选，以逗号分割。0:充资;1:购卡;2:退卡;3:维修;4:移资;5:停车场;6:加油站;7:过质保期;8:安装办理（ETC安装-新申请安装ETC）;
		// 9:自助充值;10:充值服务（ETC充值-自助充值/人工充值）;11:CVM自助机;12:变更服务（信息变更-卡类变更/信息变更）;
		// 13:维修服务（ETC维修）;14:注销退资（其他业务-注销/退资）;15:挂失补办（其他业务-挂失/补办）
		String serviceType = req.getParameter("serviceType");
		// 卡类型，多选，以逗号分割。0:交通卡;1:旅游卡;2:沪通卡
		String cardType = req.getParameter("cardType");
		if(StringUtils.isNotEmpty(keyword)){
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		if(StringUtils.isNotEmpty(areaName)){
			areaName = URLDecoder.decode(areaName, "utf-8");
		}
		nearby = nearby == null ? "1000" : nearby;
		areaName = areaName == null ? "all" : areaName;
		serviceType = serviceType == null ? "" : serviceType;
		cardType = cardType == null ? "" : cardType;
		keyword = keyword == null ? "" : keyword;
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "59905753-6f2c-4881-b322-305e2f270a11";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "4b5ab123-3379-4650-a8eb-9112a0e5693f";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1");
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
//				+"?isFW=3&shMenu1=0,1,2,3,4,5&shMenu2=0,1,2,3,4,5&nearby="+nearby+"&areaName="+areaName
//				+"&longitude=121&latitude=31&keyword="+keyword
//				+"&serviceType="+serviceType+"&cardType="+cardType+"&currentNum=0&dataNum="+Integer.MAX_VALUE/2;
	    Map<String, String> paramMap = new HashMap<String, String>();
	    paramMap.put("isFW", "3");
	    paramMap.put("shMenu1", "0,1,2,3,4,5");
	    paramMap.put("shMenu2", "0,1,2,3,4,5");
	    paramMap.put("nearby", nearby);
	    paramMap.put("areaName", areaName);
	    paramMap.put("longitude", "121");
	    paramMap.put("latitude", "31");
	    paramMap.put("keyword", keyword);
	    paramMap.put("serviceType", serviceType);
	    paramMap.put("cardType", cardType);
	    paramMap.put("currentNum", "0");
	    paramMap.put("dataNum", Integer.toString(Integer.MAX_VALUE/2));
	    
	    HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
