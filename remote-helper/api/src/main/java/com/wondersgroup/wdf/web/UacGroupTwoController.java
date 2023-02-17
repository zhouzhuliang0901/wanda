package com.wondersgroup.wdf.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.wdf.dao.UacGroupTwo;
import com.wondersgroup.wdf.service.UacGroupTwoService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项组 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacGroupTwoController {


	@RequestMapping("/wdf/uacGroupTwo/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacGroupTwo> list = uacGroupTwoService.query(wrapper);
			result.setData(JsonUtils.toJson(list, UacGroupTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacGroupTwo/remove")
	public WdfResult logicDelete(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] stGroupId = wrapper.getParameterValues("ST_GROUP_ID[]");
			uacGroupTwoService.logicDelete(stGroupId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacGroupTwo/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacGroupTwo uacGroupTwo = uacGroupTwoService.saveOrUpdate(wrapper);
			if (uacGroupTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacGroupTwoService uacGroupTwoService;

}
