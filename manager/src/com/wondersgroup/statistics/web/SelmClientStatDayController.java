package com.wondersgroup.statistics.web;

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

import com.wondersgroup.statistics.bean.SelmClientStatDay;
import com.wondersgroup.statistics.service.SelmClientStatDayService;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 终端业务统计（按天） web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmClientStatDayController {

	@RequestMapping("/statistics/selmClientStatDay/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmClientStatDay.ST_STATISTICS_ID
		String stStatisticsId = wrapper.getParameter(SelmClientStatDay.ST_STATISTICS_ID);
		// SelmClientStatDay.ST_DATE
		String stDate = wrapper.getParameter(SelmClientStatDay.ST_DATE);
		// SelmClientStatDay.ST_MACHINE_ID
		String stMachineId = wrapper.getParameter(SelmClientStatDay.ST_MACHINE_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty() && !StringUtils.trimToEmpty(stDate).isEmpty() && !StringUtils.trimToEmpty(stMachineId).isEmpty()) {
			SelmClientStatDay selmClientStatDay = selmClientStatDayService.get(stStatisticsId, stDate, stMachineId);
			req.setAttribute(SelmClientStatDay.SELM_CLIENT_STAT_DAY, selmClientStatDay);
		}
		return new ModelAndView("/selmClientStatDay/edit.jsp");
	}

	@RequestMapping("/statistics/selmClientStatDay/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmClientStatDay.ST_STATISTICS_ID
		String stStatisticsId = wrapper.getParameter(SelmClientStatDay.ST_STATISTICS_ID);
		// SelmClientStatDay.ST_DATE
		String stDate = wrapper.getParameter(SelmClientStatDay.ST_DATE);
		// SelmClientStatDay.ST_MACHINE_ID
		String stMachineId = wrapper.getParameter(SelmClientStatDay.ST_MACHINE_ID);
		SelmClientStatDay selmClientStatDay = selmClientStatDayService.get(stStatisticsId, stDate, stMachineId);
		req.setAttribute(SelmClientStatDay.SELM_CLIENT_STAT_DAY, selmClientStatDay);
		return new ModelAndView("/selmClientStatDay/info.jsp");
	}
	
	
	/**
	 * 
	 * 终端业务统计（按天）列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmClientStatDay/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmClientStatDayService.selmClientStatDayList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	
	/**
	 * 
	 * 终端业务统计（按天）列表(外设)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmClientStatDay/listodevice.do")
	public void odevicelist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmClientStatDayService.odeviceClientStatDayList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/statistics/selmClientStatDay/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("终端业务统计（按天）删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// SelmClientStatDay.ST_STATISTICS_ID
			String stStatisticsId = wrapper.getParameter(SelmClientStatDay.ST_STATISTICS_ID);
			// SelmClientStatDay.ST_DATE
			String stDate = wrapper.getParameter(SelmClientStatDay.ST_DATE);
			// SelmClientStatDay.ST_MACHINE_ID
			String stMachineId = wrapper.getParameter(SelmClientStatDay.ST_MACHINE_ID);
			selmClientStatDayService.remove(stStatisticsId, stDate, stMachineId);
			result = ExtAjaxReturnMessage.success("终端业务统计（按天）删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/statistics/selmClientStatDay/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("终端业务统计（按天）保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmClientStatDay selmClientStatDay = selmClientStatDayService.saveOrUpdate(wrapper);
			if (selmClientStatDay != null)
				result = ExtAjaxReturnMessage.success("终端业务统计（按天）保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private SelmClientStatDayService selmClientStatDayService;

}
