package com.wondersgroup.wdf.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.wdf.dao.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.wdf.service.UacItemGroupLinkTwoService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 组关联事项 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemGroupLinkTwoController {

	//根据事项组id查事项组事项关联
	@RequestMapping("/wdf/uacItemGroupLinkTwo/queryBystGroupId")
	public WdfResult queryBystGroupId(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			String stGroupId = wrapper.getParameter("ST_GROUP_ID");
			Integer pageSize = wrapper.getParameterInt("rows",10,true);
			Integer currentPage = wrapper.getParameterInt("page",1,true);
			PaginationArrayList<UacItemInfoTwo> list = uacItemInfoTwoDao.queryBystGroupId(stGroupId,pageSize,currentPage);
			result.setData(JsonUtils.toJson(list,UacItemInfoTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}
	@RequestMapping("/wdf/uacItemGroupLinkTwo/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacItemGroupLinkTwo.ST_ITEM_ID
			String[] stItemId = wrapper.getParameterValues("ST_ITEM_ID[]");
			// UacItemGroupLinkTwo.ST_GROUP_ID
			String[] stGroupId = wrapper.getParameterValues("ST_GROUP_ID[]");
			uacItemGroupLinkTwoService.remove(stItemId, stGroupId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 根据一个事项组id保存多个组关联事项
	 */
	@RequestMapping("/wdf/uacItemGroupLinkTwo/saveItem")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemGroupLinkTwo uacItemGroupLinkTwo = uacItemGroupLinkTwoService.saveitem(wrapper);
			if (uacItemGroupLinkTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacItemGroupLinkTwoService uacItemGroupLinkTwoService;
	@Autowired
	private UacItemInfoTwoDao uacItemInfoTwoDao;

}
