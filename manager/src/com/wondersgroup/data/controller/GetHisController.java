package com.wondersgroup.data.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.wondersgroup.data.bean.OfflineCount;
import com.wondersgroup.data.bean.SqhCount;
import com.wondersgroup.data.dao.GetSQH;

import coral.widget.utils.EasyUIHelper;

@Controller
public class GetHisController {


	@Autowired
	private GetSQH sqh;

	@RequestMapping("/data/query.do")
	public void initSqh(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		SqhCount cou = sqh.getSqhByTime(req, res);
		JSONObject obj = JSONObject.fromObject(cou);
		//System.out.println("1231231--"+obj.toString());
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/data/queryOffline.do")
	public void initSqh2(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		OfflineCount cou = sqh.getOfflineByTime(req, res);
		JSONObject obj = JSONObject.fromObject(cou);
		//System.out.println("1231231--"+obj.toString());
		EasyUIHelper.writeResponse(res, obj.toString());
	}
}
