package com.wondersgroup.dataitem.item215162608522.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class SameBirthController {
	
	/**
	 * 同日出生人数查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/sameBirth/queryTheSameBirth.do")
	public void queryTheSameBirth(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String csrq = req.getParameter("csrq");
		String xbdm = req.getParameter("xbdm");
		String sid = "s_12310000MB2F042367_3157";
		String appName = "c04b3258-11dc-45cd-845c-78526951482e";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("sid",sid);
		
		JSONObject obj = new JSONObject();
		obj.put("csrq", csrq);
		obj.put("xbdm", xbdm);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
