package com.wondersgroup.selmAssist.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.selmAssist.bean.SelmDeviceAssist;
import com.wondersgroup.selmAssist.service.SelmDeviceAssistService;
import com.wondersgroup.serverApply.bean.SelmServerItem;



import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 设备关联人员 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmDeviceAssistController {

	@RequestMapping("/selmAssist/selmDeviceAssist/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceAssist.ST_ASSIST_ID
		String stAssistId = wrapper.getParameter(SelmDeviceAssist.ST_ASSIST_ID);
		// SelmDeviceAssist.ST_DEVICE_ID
		String stDeviceId = wrapper.getParameter(SelmDeviceAssist.ST_DEVICE_ID);
		if (!StringUtils.trimToEmpty(stAssistId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			SelmDeviceAssist selmDeviceAssist = selmDeviceAssistService.get(stAssistId, stDeviceId);
			req.setAttribute(SelmDeviceAssist.SELM_DEVICE_ASSIST, selmDeviceAssist);
		}
		return new ModelAndView("/selmDeviceAssist/edit.jsp");
	}

	@RequestMapping("/selmAssist/selmDeviceAssist/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceAssist.ST_ASSIST_ID
		String stAssistId = wrapper.getParameter(SelmDeviceAssist.ST_ASSIST_ID);
		// SelmDeviceAssist.ST_DEVICE_ID
		String stDeviceId = wrapper.getParameter(SelmDeviceAssist.ST_DEVICE_ID);
		SelmDeviceAssist selmDeviceAssist = selmDeviceAssistService.get(stAssistId, stDeviceId);
		req.setAttribute(SelmDeviceAssist.SELM_DEVICE_ASSIST, selmDeviceAssist);
		return new ModelAndView("/selmDeviceAssist/info.jsp");
	}

	@RequestMapping("/selmAssist/selmDeviceAssist/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeviceAssistService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	

	@RequestMapping("/selmAssist/selmDeviceAssist/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("设备移出失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceAssistService.removeList(httpReqRes);
			result.success().setMsg("设备信息移出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/selmAssist/selmDeviceAssist/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmDeviceAssist selmDeviceAssist = selmDeviceAssistService.saveOrUpdate(httpReqRes);
			if (selmDeviceAssist != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@Autowired
	private SelmDeviceAssistService selmDeviceAssistService;

}
