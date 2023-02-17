package com.wondersgroup.statistics.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.service.SelmStatisticsService;

import coral.widget.utils.EasyUIHelper;

/**
 * 业务表 web层控制器
 * 
 * @author scalffold
 * 
 */
@Controller
public class SelmStatisticsController {

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		// SelmStatistics.ST_STATISTICS_ID
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		SelmStatistics selmStatistics = selmStatisticsService
				.get(stStatisticsId);
		req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		return new ModelAndView("/statistics/info.jsp");
	}

	/**
	 * 查看点击量(按天)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/dayCountInfo.do")
	public ModelAndView daylist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/daylist.jsp");
	}

	/**
	 * 查看点击量(按天)(外设)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/dayCountOdevice.do")
	public ModelAndView dayodevicelist(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/daylistodevice.jsp");
	}
	/**
	 * 查看点击量(按天)(分类)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/devicetypeDay.do")
	public ModelAndView devicetypeDayList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/daylisttype.jsp");
	}
	
	/**
	 * 区域查看点击量(按天)(分类)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/deviceaddressDay.do")
	public ModelAndView devicequyuDayList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes
				.getParameter(SelmStatistics.ST_NAME);
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/daylistaddress.jsp");
	}
	
	/**
	 * 通过区域查看相应街道统计
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressTypeDevice_list_byArea" })*/
	@RequestMapping("/statistics/statistics/addressStreet.do")
	public ModelAndView addressStreet(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes.getParameter("ST_NAME");
		String stDate = httpReqRes.getParameter("ST_EXT1");
		String edDate = httpReqRes.getParameter("ST_EXT2");
		String deviceType = httpReqRes.getParameter("deviceType");
		stName = Decode.decode(stName, "utf-8");
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("ZW");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/street/sulistStreetType.jsp");
	}
	
	/**
	 * 通过省份查看相应城市
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressTypeDevice_list_byArea" })*/
	@RequestMapping("/statistics/statistics/toSelmCityQuery.do")
	public ModelAndView toSelmCityQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes.getParameter("ST_NAME");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stName = Decode.decode(stName, "utf-8");
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmCityQuery.jsp");
	}
	
	/**
	 * 通过城市查看相应区
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressTypeDevice_list_byArea" })*/
	@RequestMapping("/statistics/statistics/toSelmDistrictQuery.do")
	public ModelAndView toSelmDistrictQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes.getParameter("ST_NAME");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stName = Decode.decode(stName, "utf-8");
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmDistrictQuery.jsp");
	}
	
	@RequestMapping("/statistics/statistics/toSelmStreetQuery.do")
	public ModelAndView toSelmStreetQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes.getParameter("ST_NAME");
		String stDate = httpReqRes.getParameter("ST_EXT1");
		String edDate = httpReqRes.getParameter("ST_EXT2");
		stName = Decode.decode(stName, "utf-8");
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmStreetQuery.jsp");
	}
	
	
	/**
	 * 单位统计通过所属区查看相应街道统计
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/streetInfo.do")
	public ModelAndView streetInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes
				.getParameter("areaName");
		stName = Decode.decode(stName, "utf-8");
		System.out.println(stName);
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/street/streetTypeList.jsp");
	}
	
	@RequestMapping("/statistics/statistics/singlestreetInfo.do")
	public ModelAndView singlestreetInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes
				.getParameter("areaName");
		stName = Decode.decode(stName, "utf-8");
		System.out.println(stName);
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/street/singleStreetTypeList.jsp");
	}
	
	/**
	 * 区域街道查看点击量(按天)(分类)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/devicestreetDay.do")
	public ModelAndView devicestreetDay(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes
				.getParameter(SelmStatistics.ST_NAME);
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/street/dayliststreet.jsp");
	}
	
	/**
	 * 查看点击量(按天)(分组)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/groupday.do")
	public ModelAndView groupDayList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/daylistgroup.jsp");
	}
	/**
	 * 查看点击量(按天)(项目区域)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/areaday.do")
	public ModelAndView areaDayList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/daylistarea.jsp");
	}

	/**
	 * 配置列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/configlist.do")
	public void configlist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.statisticsConfigList(httpReqRes);
			// req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * 统计量列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.statisticsList(httpReqRes);
			// req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * 外设调用次数列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/odevicelist.do")
	public void odevicelist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.odeviceStatisticsList(httpReqRes);
			// req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	/**
	 * 分类统计量列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/typelist.do")
	public void typelist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.typeStatisticsList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	/**
	 * 根据区域统计量列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/addresslist.do")
	public void addresslist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.addressList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 根据区域街道统计量列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/streetlist.do")
	public void streetlist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.streetlist(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 办件事项跳转区域办件量
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sys_query_list_byArea" })
	@RequestMapping("/statistics/statistics/itemAreaQuery.do")
	public ModelAndView itemAreaQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmQueryHis.ST_ITEM_NAME);
		String stDate = httpReqRes.getParameter("stDate");
		String edDate = httpReqRes.getParameter("edDate");
		
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/business/itemAreaQuery.jsp");
	}
	
	
	/**
	 * 根据区域统计社会化终端，政务终端，办件量量列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/addresslistType.do")
	public void addresslistType(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.addressTypeList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 区域跳转事项操作名称
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/areaItemModule.do")
	public ModelAndView areaItemModule(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmStatistics.ST_NAME);
		String stDate = httpReqRes.getParameter(SelmStatistics.ST_EXT1);
		String edDate = httpReqRes.getParameter(SelmStatistics.ST_EXT2);
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("ZW");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmModuleQuery.jsp");
	}
	
	
	/**
	 * 区域跳转事项操作名称
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/bProvinceItemModule.do")
	public ModelAndView bProvinceItemModule(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmStatistics.ST_NAME);
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(stItemName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("YH");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmModuleQuery.jsp");
	}
	
	/**
	 * 区域跳转事项操作名称
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/bCityItemModule.do")
	public ModelAndView bCityItemModule(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmStatistics.ST_NAME);
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("YH");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmModuleQuery.jsp");
	}
	
	/**
	 * 银行设备区域跳转事项操作名称
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/bareaItemModule.do")
	public ModelAndView bareaItemModule(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmStatistics.ST_NAME);
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("YH");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmModuleQuery.jsp");
	}
	
	
	/**
	 * 区域跳转事项办理
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/areaItemAmount.do")
	public ModelAndView areaItemAmount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter("area");
		String stDate = httpReqRes.getParameter("startDate");
		String edDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("ZW");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmAmountQuery.jsp");
	}
	
	/**
	 * 区域跳转事项办理
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/bProvinceItemAmount.do")
	public ModelAndView provinceItemAmount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter("area");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(stItemName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("YH");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmAmountQuery.jsp");
	}
	
	/**
	 * 区域跳转事项办理
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/bCityItemAmount.do")
	public ModelAndView cityItemAmount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter("area");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("YH");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmAmountQuery.jsp");
	}
	
	/**
	 * 银行区域跳转事项办理
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/bareaItemAmount.do")
	public ModelAndView bareaItemAmount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter("area");
		String stDate = httpReqRes.getParameter("startDate");
		String edDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stItemName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("YH");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmAmountQuery.jsp");
	}
	
	/**
	 * 街道跳转事项办理
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/streetItemAmount.do")
	public ModelAndView streetItemAmount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String street = httpReqRes.getParameter("street");
		String area = httpReqRes.getParameter("area");
		String stDate = httpReqRes.getParameter("startDate");
		String edDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		street = Decode.decode(street, "utf-8");
		if (!StringUtils.trimToEmpty(street).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(area);
			selmStatistics.setStNetFlag(street);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("ZW");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmAmountQuery.jsp");
	}
	
	
	/**
	 * 设备跳转事项办理
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/deviceItemAmount.do")
	public ModelAndView deviceItemAmount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String mac = httpReqRes.getParameter("mac");
		String stDate = httpReqRes.getParameter("startDate");
		String edDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		mac = Decode.decode(mac, "utf-8");
		if (!StringUtils.trimToEmpty(mac).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(mac);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmDeviceAmountQuery.jsp");
	}
	
	/**
	 * 街道跳转事项操作名称
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/streetItemModule.do")
	public ModelAndView streetItemModule(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmStatistics.ST_NAME);
		String stDate = httpReqRes.getParameter(SelmStatistics.ST_EXT1);
		String edDate = httpReqRes.getParameter(SelmStatistics.ST_EXT2);
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(stItemName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("ZW");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmModuleQuery.jsp");
	}
	
	/**
	 * 设备转事项操作名称
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/deviceItemModule.do")
	public ModelAndView deviceItemModule(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter("ST_NAME");//MAC
		String stDate = httpReqRes.getParameter("ST_EXT1");
		String edDate = httpReqRes.getParameter("ST_EXT2");
		String deviceType = httpReqRes.getParameter("deviceType");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(stItemName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/selmDeviceModuleQuery.jsp");
	}
	
	/**
	 * 区域查询办件量操作名称分类（查询，打印...办理量）
	 * 操作名称数量查询
	 */
	@RequestMapping("/statistics/selmStatistics/areaModuleQuery.do")
	public void areaModuleQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.areaModuleQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 区域查询办件量操作名称分类（查询，打印...办理量）
	 * 操作名称数量查询
	 */
	@RequestMapping("/statistics/selmStatistics/bProvinceModuleQuery.do")
	public void bProvinceModuleQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.provinceModuleQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 区域查询办件量事项分类
	 * 操作名称数量查询
	 */
	@RequestMapping("/statistics/selmStatistics/areaItemAmountQuery.do")
	public void areaItemAmountQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.areaItemAmountQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 区域查询办件量事项分类
	 * 操作名称数量查询
	 */
	@RequestMapping("/statistics/selmStatistics/provinceItemAmountQuery.do")
	public void provinceItemAmountQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.provinceItemAmountQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 设备查询办件量事项分类
	 * 操作名称数量查询
	 */
	@RequestMapping("/statistics/selmStatistics/deviceItemAmountQuery.do")
	public void deviceItemAmountQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.deviceItemAmountQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 设备查询办件量操作名称分类（查询，打印...办理量）
	 * 操作名称数量查询
	 */
	@RequestMapping("/statistics/selmStatistics/deviceModuleQuery.do")
	public void deviceModuleQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.deviceModuleQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	/**
	 * 办件事项跳转区域跳转街道
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sys_query_list_byStreet" })
	@RequestMapping("/statistics/statistics/itemStreetQuery.do")
	public ModelAndView itemStreetQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stAreaName = httpReqRes.getParameter("stName");
		String stItemName = httpReqRes.getParameter("itemName");
		String stDate = httpReqRes.getParameter("stDate");
		String edDate = httpReqRes.getParameter("edDate");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stAreaName);
			selmStatistics.setStExt1(stItemName);
			selmStatistics.setStNetFlag(stDate);//开始时间
			selmStatistics.setStExt2(edDate);//结束时间
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/business/itemStreetQuery.jsp");
	}
	
	/**
	 * 根据事项跳转区域在跳转街道统计办件量量列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/streetlistType.do")
	public void streetlistType(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.streetTypeList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 通过区域查看相应街道统计
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressTypeDevice_list_byStreet" })*/
	@RequestMapping("/statistics/statistics/streetDeviceInfo.do")
	public ModelAndView streetDevice(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes.getParameter("ST_NAME");
		String stDate = httpReqRes.getParameter("ST_EXT1");
		String edDate = httpReqRes.getParameter("ST_EXT2");
		String deviceType = httpReqRes.getParameter("deviceType");
		stName = Decode.decode(stName, "utf-8");
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			selmStatistics.setStExt1(stDate);
			selmStatistics.setStExt2(edDate);
			selmStatistics.setStNetSubFlag(deviceType);
			selmStatistics.setStStatisticsId("ZW");
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/deviceInfoSta/selmDeviceQuery.jsp");
	}
	
	/**
	 * 通过区域查看相应街道统计
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressTypeDevice_list_byStreet" })*/
	@RequestMapping("/statistics/statistics/yhDistrictDeviceInfo.do")
	public ModelAndView yhDistrictDeviceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stName = httpReqRes.getParameter("ST_NAME");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String deviceType = httpReqRes.getParameter("deviceType");
		stName = Decode.decode(stName, "utf-8");
		if (!StringUtils.trimToEmpty(stName).isEmpty()) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stName);
			selmStatistics.setStExt1(startDate);
			selmStatistics.setStExt2(endDate);
			selmStatistics.setStNetSubFlag(deviceType);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/bank/selmDeviceQuery.jsp");
	}
	
	/**
	 * 街道设备查询相关信息（设备编码，设备地址，mac，办件量）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/deviceInfoQueryList.do")
	public void deviceInfoQueryList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.deviceInfoQueryList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 根据区域统计设备类型对应得数量（社会化终端，政务终端）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressType_list" })
	@RequestMapping("/statistics/selmStatistics/addresslistTypeDevice.do")
	public void addresslistTypeDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.addresslistTypeDevice(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		//EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 根据区域街道统计设备类型对应得数量（社会化终端，政务终端）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/streetlistTypeDevice.do")
	public void streetlistTypeDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.streetlistTypeDevice(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		//EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 根据街道查找相应类型的设备
	 * @param req
	 * @param res
	 */
	@RequestMapping("/statistics/selmStatistics/streetTypeDevice.do")
	public void streetTypeDevice(HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try{
			obj = selmStatisticsService.streetTypeDevice(httpReqRes);			
		}catch(Exception e){
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}

	/**
	 * 删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmStatisticsService.remove(httpReqRes);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * 添加更新
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("添加失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		SelmStatistics selmStatistics = null;
		try {
			selmStatistics = selmStatisticsService.add(httpReqRes);
			if (selmStatistics != null)
				result.success().setMsg("添加成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = selmStatisticsService
					.get(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
			System.out.println();
		}
		return new ModelAndView("/statistics/edit.jsp");
	}

	
	/**
	 * 设备分类跳转界面
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/suCountInfo.do")
	public ModelAndView sulist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = selmStatisticsService
					.get(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/sulist.jsp");
	}

	/**
	 * 设备分类统计量列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/suCount.do")
	public void suCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.suCount(httpReqRes);
			req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * 设备分组跳转界面
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/suCountInfoGroup.do")
	public ModelAndView sulistGroup(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = selmStatisticsService
					.get(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/sulistGroup.jsp");
	}

	/**
	 * 设备分组统计量列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/suCountGroup.do")
	public void suCountGroup(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.suCountGroup(httpReqRes);
			req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * 项目信息跳转界面
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/suCountInfoItem.do")
	public ModelAndView sulistItem(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			SelmStatistics selmStatistics = selmStatisticsService
					.get(stStatisticsId);
			req.setAttribute(SelmStatistics.SELM_STATISTICS, selmStatistics);
		}
		return new ModelAndView("/statistics/sulistItem.jsp");
	}

	
	/**
	 * 项目信息统计量列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/statistics/suCountItem.do")
	public void suCountItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.suCountItem(httpReqRes);
			req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@Autowired
	private SelmStatisticsService selmStatisticsService;

	/**
	 * 导入数据
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/import.do")
	public void importData(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.setMsg("添加失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			String str1 = selmStatisticsService.updateSelmStatisticsDay();
			String str2 = selmStatisticsService.updateSelmClientStatisticsDay();
			String str3 = selmStatisticsService.importOdeviceToClientDay();
			String str4 = selmStatisticsService.importOdeviceToDay();
			if (str1 != null && str2 != null && str3 != null && str4 != null) {
				result.success().setMsg("添加成功");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	/**
	 * 事项访问数量和办理成功数量对比
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/visitComItem.do")
	public void visitComItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.visitComItem(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 根据事项查询年龄分布人数
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/itemPeNumber.do")
	public void itemPeNumber(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.itemPeNumber(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	
	/**
	 * 根据用户名查询点击量和服务
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/itemVisiter.do")
	public void itemVisiter(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.itemVisiter(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
		
	}
	
	/**
	 * 办理分析、业务分析事项列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/analysisItemList.do")
	public void handleList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.handleList(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	/**
	 * 满意度评价
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/selmSatisfactionInfoList.do")
	public void selmSatisfactionInfoList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.selmSatisfactionInfoList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
		
	}
	
	/**
	 * 满意度评价明细
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmStatistics/selmSatisfactionInfo.do")
	public void selmSatisfactionInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmStatisticsService.selmSatisfactionInfo(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
		
	}
	
}
