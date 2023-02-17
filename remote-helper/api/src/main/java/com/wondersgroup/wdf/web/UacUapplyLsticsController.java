package com.wondersgroup.wdf.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.wdf.dao.UacUapplyLstics;
import com.wondersgroup.wdf.service.UacUapplyLsticsService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 办件关联物流 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class UacUapplyLsticsController {

	@RequestMapping("/uacUapplyLstics/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyLstics.ST_UNION_LOGISTICS_ID
		String stUnionLogisticsId = wrapper.getParameter(UacUapplyLstics.ST_UNION_LOGISTICS_ID);
		// UacUapplyLstics.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUapplyLstics.ST_APPLY_ID);
		if (!StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty() && !StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			UacUapplyLstics uacUapplyLstics = uacUapplyLsticsService.get(stUnionLogisticsId, stApplyId);
			req.setAttribute(UacUapplyLstics.UAC_UAPPLY_LSTICS, uacUapplyLstics);
		}
		return new ModelAndView("/uacUapplyLstics/edit.jsp");
	}

	@RequestMapping("/uacUapplyLstics/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyLstics.ST_UNION_LOGISTICS_ID
		String stUnionLogisticsId = wrapper.getParameter(UacUapplyLstics.ST_UNION_LOGISTICS_ID);
		// UacUapplyLstics.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUapplyLstics.ST_APPLY_ID);
		UacUapplyLstics uacUapplyLstics = uacUapplyLsticsService.get(stUnionLogisticsId, stApplyId);
		req.setAttribute(UacUapplyLstics.UAC_UAPPLY_LSTICS, uacUapplyLstics);
		return new ModelAndView("/uacUapplyLstics/info.jsp");
	}

	@RequestMapping("/uacUapplyLstics/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyLstics> list = uacUapplyLsticsService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyLstics.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyLstics/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("办件关联物流删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUapplyLstics.ST_UNION_LOGISTICS_ID
			String stUnionLogisticsId = wrapper.getParameter(UacUapplyLstics.ST_UNION_LOGISTICS_ID);
			// UacUapplyLstics.ST_APPLY_ID
			String stApplyId = wrapper.getParameter(UacUapplyLstics.ST_APPLY_ID);
			uacUapplyLsticsService.remove(stUnionLogisticsId, stApplyId);
			result = ExtAjaxReturnMessage.success("办件关联物流删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyLstics/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("办件关联物流保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUapplyLstics uacUapplyLstics = uacUapplyLsticsService.saveOrUpdate(wrapper);
			if (uacUapplyLstics != null)
				result = ExtAjaxReturnMessage.success("办件关联物流保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacUapplyLsticsService uacUapplyLsticsService;

}
