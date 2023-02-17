package com.wondersgroup.selmAssist.web;


import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.selmAssist.bean.SelmAssist;
import com.wondersgroup.selmAssist.service.SelmAssistService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 设备辅助人员 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmAssistController {

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_assist_device_edit" })
	@RequestMapping("/selmAssist/selmAssist/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAssist.ST_ASSIST_ID
		String stAssistId = wrapper.getParameter(SelmAssist.ST_ASSIST_ID);
		if (!StringUtils.trimToEmpty(stAssistId).isEmpty()) {
			SelmAssist selmAssist = selmAssistService.get(stAssistId);
			String idCard = selmAssist.getStAssistIdcard();
			String idNumber=null;
			if (idCard != null && !StringUtils.trim(idCard).isEmpty()) {
				if (idCard.length() == 15){
		             idNumber = idCard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1*************$2");
		         }
		         if (idCard.length() == 18){
		             idNumber = idCard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1****************$2");
		         }
			}
			selmAssist.setStAssistIdcard(idNumber);
			req.setAttribute(SelmAssist.SELM_ASSIST, selmAssist);
		}
		return new ModelAndView("/selmAssist/edit.jsp");
	}

	@RequestMapping("/selmAssist/selmAssist/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAssist.ST_ASSIST_ID
		String stAssistId = wrapper.getParameter(SelmAssist.ST_ASSIST_ID);
		SelmAssist selmAssist = selmAssistService.get(stAssistId);
		req.setAttribute(SelmAssist.SELM_ASSIST, selmAssist);
		return new ModelAndView("/selmAssist/info.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_assist_device" })
	@RequestMapping("/selmAssist/selmAssist/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAssistService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_assist_device_remove" ,"selm_assist_device_bremove"})
	@RequestMapping("/selmAssist/selmAssist/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("设备辅助人员信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmAssistService.removeList(httpReqRes);
			result.success().setMsg("设备辅助人员信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_assist_device_save" })
	@RequestMapping("/selmAssist/selmAssist/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("设备辅助人员保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmAssist selmAssist = selmAssistService.saveOrUpdate(wrapper);
			if (selmAssist != null)
				result = ExtAjaxReturnMessage.success("设备辅助人员保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	@CheckPermissions(roles = { "admin" }, permissions = { "selm_assist_device_info" })
	@RequestMapping("/selmAssist/selmAssist/deviceAssist.do")
	public ModelAndView deviceAssist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAssist.ST_ASSIST_ID
		String stAssistId = wrapper.getParameter(SelmAssist.ST_ASSIST_ID);
		SelmAssist selmAssist = selmAssistService.get(stAssistId);
		req.setAttribute(SelmAssist.SELM_ASSIST, selmAssist);
		return new ModelAndView("/selmAssist/selmAssistDevice.jsp");
	}

	@Autowired
	private SelmAssistService selmAssistService;

}
