package com.wondersgroup.dataitem.item3120W0382000.web;

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
public class MeasurementController {
	
	/**
	 * 社会公用计量标准查询、二级注册计量师查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/measuring/queryMeasurementStandard.do")
	public void queryMeasurementStandard(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 持证者证件编号（身份证号和统一社会信用代码）
		String holderCode = req.getParameter("holderCode");
		String certCode = req.getParameter("certCode");
		// 社会公用计量标准证书：310100748000100；二级注册计量师资格证书：310191352736400
		String catMainCode = req.getParameter("catMainCode");
//		holderCode = "330682198211233016";
//		catMainCode = "310191352736400";
//		certCode = "16Q8Z3052";
		
		String appName = "bab827a1-8592-4ca2-b295-1296af5a19e0";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		System.out.println("计量相关证照查询证照目录编号："+catMainCode);
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
//				+"?holderCode="+holderCode+"&certCode="+certCode+"&catMainCode="+catMainCode;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("holderCode", holderCode);
		paramMap.put("certCode", certCode);
		paramMap.put("catMainCode", catMainCode);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 计量标准考评员查询、法定计量检定机构考评员查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/measuring/queryAssessor.do")
	public void queryAssessor(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String unit = req.getParameter("unit");
		// 1：计量标准考评员查询；2：法定计量检定机构考评员查询
		String type = req.getParameter("type");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		if(StringUtils.isNotEmpty(unit)){
			unit = URLDecoder.decode(unit, "utf-8");
		}
		
		String appName = "";
		if("1".equals(type)){
			if(StringUtils.isEmpty(unit) || StringUtils.isEmpty(name)){
				appName = "0a01d8f3-4bcf-48fd-916f-95ed4fa5da7f";
			} else {
				appName = "48c7c71d-2cc0-45ec-bc75-9f20472794bf";
			}
		} else if("2".equals(type)){
			if(StringUtils.isEmpty(unit) || StringUtils.isEmpty(name)){
				appName = "e7f35f0a-e9ea-4c6a-89f5-a6610a203bfb";
			} else {
				appName = "8eb5892e-70ae-4c4d-9b6a-5aa1a7bd9114";
			}
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?name="+name+"&unit="+unit;
		String result = HttpUtil.doGet(head, url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
