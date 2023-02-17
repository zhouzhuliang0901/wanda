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
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;
import com.wondersgroup.statistics.service.SelmStatisticsDayService;
import com.wondersgroup.statistics.service.SelmStatisticsService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 业务统计（按天） web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmStatisticsDayController {
	
	@RequestMapping("/statistics/selmStatisticsDay/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmStatisticsDay.ST_STATISTICS_ID
		String stStatisticsId = wrapper.getParameter(SelmStatisticsDay.ST_STATISTICS_ID);
		// SelmStatisticsDay.ST_DATE
		String stDate = wrapper.getParameter(SelmStatisticsDay.ST_DATE);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty() && !StringUtils.trimToEmpty(stDate).isEmpty()) {
			SelmStatisticsDay selmStatisticsDay = selmStatisticsDayService.get(stStatisticsId, stDate);
			req.setAttribute(SelmStatisticsDay.SELM_STATISTICS_DAY, selmStatisticsDay);
		}
		return new ModelAndView("/selmStatisticsDay/edit.jsp");
	}

	/**
	 * 方法描述：查看
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmStatisticsDay.ST_STATISTICS_ID
		String stStatisticsId = wrapper.getParameter(SelmStatisticsDay.ST_STATISTICS_ID);
		// SelmStatisticsDay.ST_DATE
		String stDate = wrapper.getParameter(SelmStatisticsDay.ST_DATE);
		SelmStatisticsDay selmStatisticsDay = selmStatisticsDayService.get(stStatisticsId, stDate);
		req.setAttribute(SelmStatisticsDay.SELM_STATISTICS_DAY, selmStatisticsDay);
		return new ModelAndView("/selmStatisticsDay/info.jsp");
	}
	
	
	

	/**
	 * 按天业务表列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsDayService.statisticsList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 按天业务表列表(外设)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/odevicelist.do")
	public void odevicelist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsDayService.odeviceStatisticsList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 按天业务表列表(分类)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/daytypelist.do")
	public void typelist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsDayService.typeStatisticsList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 按天业务表列表(分类)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/dayaddresslist.do")
	public void addresslist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsDayService.addressStatisticsList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 按天业务表列表(区域街道统计)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/daystreetlist.do")
	public void streetlist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsDayService.streetStatisticsList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	
	
	/**
	 * 
	 * 删除
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmStatisticsDayService.remove(httpReqRes);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * 保存
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("业务统计（按天）保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmStatisticsDay selmStatisticsDay = selmStatisticsDayService.saveOrUpdate(wrapper);
			if (selmStatisticsDay != null)
				result = ExtAjaxReturnMessage.success("业务统计（按天）保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	
	
	
	/**
	 * 跳转终端业务统计（按天）页面
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/selmClientStstDayPage.do")
	public ModelAndView clientPageView(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes.getParameter("ST_STATISTICS_ID");
		String stDate = httpReqRes.getParameter("ST_DATE");
		SelmStatisticsDay selmStatisticsDay = selmStatisticsDayService.get(stStatisticsId, stDate);
		req.setAttribute(SelmStatisticsDay.SELM_STATISTICS_DAY, selmStatisticsDay);
		return new ModelAndView("/statistics/clientdaylist.jsp");
	}
	
	/**
	 * 跳转终端业务统计（按天）页面（外设的）
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatisticsDay/clientStstDayOdevice.do")
	public ModelAndView clientOdeviceView(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes.getParameter("ST_STATISTICS_ID");
		String stDate = httpReqRes.getParameter("ST_DATE");
		SelmStatisticsDay selmStatisticsDay = selmStatisticsDayService.get(stStatisticsId, stDate);
		req.setAttribute(SelmStatisticsDay.SELM_STATISTICS_DAY, selmStatisticsDay);
		return new ModelAndView("/statistics/clientdaylistodevice.jsp");
	}
	
	
	
	
	/**
	  * 分组统计按天业务表列表
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/statistics/selmClientStatDay/sulistGroupDay.do")
	 public void sulistGroupDay(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = selmStatisticsDayService.suListGroupDay(httpReqRes);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	
	 
	 /**
	  * 项目区域统计按天业务表列表
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/statistics/selmClientStatDay/sulistItemDay.do")
	 public void sulistItemDay(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = selmStatisticsDayService.suListItemDay(httpReqRes);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }

	@Autowired
	private SelmStatisticsDayService selmStatisticsDayService;

}
