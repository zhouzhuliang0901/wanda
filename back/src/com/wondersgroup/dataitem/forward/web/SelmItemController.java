package com.wondersgroup.dataitem.forward.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.dataitem.forward.web.service.SelmItemService;


import tw.ecosystem.reindeer.web.HttpReqRes;

@Controller
public class SelmItemController {
	@RequestMapping("/selmItem/selmItem/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmItemService.itemList(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 httpReqRes.writeJsonP(obj.toString());
	}
	@Autowired
	private SelmItemService selmItemService;
}
