package com.wondersgroup.statistics.web;

import java.io.IOException;

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

import com.wondersgroup.statistics.bean.SelmAreaQueryDay;
import com.wondersgroup.statistics.service.SelmAreaQueryDayService;

import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;

/**
 * 区域日办件统计表 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmAreaQueryDayController {

	@RequestMapping("/statistics/selmAreaQueryDay/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAreaQueryDay.ST_AREA_ID
		String stAreaId = wrapper.getParameter(SelmAreaQueryDay.ST_AREA_ID);
		// SelmAreaQueryDay.ST_DAY
		String stDay = wrapper.getParameter(SelmAreaQueryDay.ST_DAY);
		if (!StringUtils.trimToEmpty(stAreaId).isEmpty() && !StringUtils.trimToEmpty(stDay).isEmpty()) {
			SelmAreaQueryDay selmAreaQueryDay = selmAreaQueryDayService.get(stAreaId, stDay);
			req.setAttribute(SelmAreaQueryDay.SELM_AREA_QUERY_DAY, selmAreaQueryDay);
		}
		return new ModelAndView("/selmAreaQueryDay/edit.jsp");
	}

	@RequestMapping("/statistics/selmAreaQueryDay/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAreaQueryDay.ST_AREA_ID
		String stAreaId = wrapper.getParameter(SelmAreaQueryDay.ST_AREA_ID);
		// SelmAreaQueryDay.ST_DAY
		String stDay = wrapper.getParameter(SelmAreaQueryDay.ST_DAY);
		SelmAreaQueryDay selmAreaQueryDay = selmAreaQueryDayService.get(stAreaId, stDay);
		req.setAttribute(SelmAreaQueryDay.SELM_AREA_QUERY_DAY, selmAreaQueryDay);
		return new ModelAndView("/selmAreaQueryDay/info.jsp");
	}

	

	/*@CheckPermissions(roles = { "admin" }, permissions = { "sys_addressTypeDevice_list" })*/
	@RequestMapping("/statistics/selmAreaQueryDay/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAreaQueryDayService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	@RequestMapping("/statistics/selmBProvinceQueryDay/list.do")
	public void plist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAreaQueryDayService.blist(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	@RequestMapping("/statistics/selmBCityQueryDay/list.do")
	public void clist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAreaQueryDayService.clist(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	@RequestMapping("/statistics/selmBDistrictQueryDay/list.do")
	public void dlist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAreaQueryDayService.dlist(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	@RequestMapping("/statistics/selmBStreetQueryDay/list.do")
	public void slist(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAreaQueryDayService.slist(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/statistics/selmAreaQueryDay/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("区域日办件统计表删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// SelmAreaQueryDay.ST_AREA_ID
			String stAreaId = wrapper.getParameter(SelmAreaQueryDay.ST_AREA_ID);
			// SelmAreaQueryDay.ST_DAY
			String stDay = wrapper.getParameter(SelmAreaQueryDay.ST_DAY);
			selmAreaQueryDayService.remove(stAreaId, stDay);
			result = ExtAjaxReturnMessage.success("区域日办件统计表删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/statistics/selmAreaQueryDay/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("区域日办件统计表保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmAreaQueryDay selmAreaQueryDay = selmAreaQueryDayService.saveOrUpdate(wrapper);
			if (selmAreaQueryDay != null)
				result = ExtAjaxReturnMessage.success("区域日办件统计表保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	/**
	 * 统计办件量导入selm_area_query_day
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmAreaQueryDay/updateSaqd.do")
	public void updateSaqd(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		String result = ExtAjaxReturnMessage.toJsonErrorObj("区域日办件统计表保存失败。", "错误",
				null).toString();
		try {
			obj = selmAreaQueryDayService.updateSaqd(httpReqRes);
			if (obj != null)
				result = ExtAjaxReturnMessage.success("区域日办件统计表保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	/**
	 * 根据区域修改统计办件量导入selm_area_query_day
	 * 因区域增加设备，以前的办件量需修改
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/statistics/selmAreaQueryDay/areaUpdateSaqd.do")
	public void areaUpdateSaqd(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		String result = ExtAjaxReturnMessage.toJsonErrorObj("区域日办件统计表修改失败。", "错误",
				null).toString();
		try {
			obj = selmAreaQueryDayService.areaUpdateSaqd(httpReqRes);
			if (obj != null)
				result = ExtAjaxReturnMessage.success("区域日办件统计表修改成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private SelmAreaQueryDayService selmAreaQueryDayService;

}
