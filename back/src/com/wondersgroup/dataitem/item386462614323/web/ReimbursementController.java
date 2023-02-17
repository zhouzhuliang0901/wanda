package com.wondersgroup.dataitem.item386462614323.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item213072044522.utils.XmlUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class ReimbursementController {
	
	/**
	 * 零星报销进度查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reimbursement/queryReimbursementStatus.do")
	public void queryReimbursementStatus(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 申请号
		String sqh = req.getParameter("sqh");
//		sqh = "007720042100005";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "045a026b-5d0a-4963-a672-a56052b68872";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1ee83671-3620-486d-b297-1019110a65da";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		Map<String ,String> body = new HashMap<String, String>();
		body.put("sqh", sqh);
		
		String xmlStr = XmlUtil.creatXML("yb0043q1",body);
		String result = XmlUtil.getResult(head,xmlStr);
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
