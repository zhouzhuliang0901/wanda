package com.wondersgroup.wdf.extPdCclist.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wondersgroup.sms.group.bean.SmsGroup;
import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.role.bean.SmsRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.wdf.extPdCclist.dao.ExtPdCclist;
import com.wondersgroup.wdf.extPdCclist.service.ExtPdCclistService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 子证归集表 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class ExtPdCclistController {

	@RequestMapping("/extPdCclist/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ExtPdCclist.ST_CCLIST_ID
		String stCclistId = wrapper.getParameter(ExtPdCclist.ST_CCLIST_ID);
		if (!StringUtils.trimToEmpty(stCclistId).isEmpty()) {
			ExtPdCclist extPdCclist = extPdCclistService.get(stCclistId);
			req.setAttribute(ExtPdCclist.EXT_PD_CCLIST, extPdCclist);
		}
		return new ModelAndView("/extPdCclist/edit.jsp");
	}

	@RequestMapping("/wdf/extPdCclist/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stCclistId = wrapper.getParameter(ExtPdCclist.ST_CCLIST_ID);
		ExtPdCclist extPdCclist = extPdCclistService.get(stCclistId);
		return WdfResult.getResult().success().setData(JsonUtils.toJson(extPdCclist));
	}

	@RequestMapping("/wdf/extPdCclist/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<ExtPdCclist> list = extPdCclistService.query(wrapper);
			result.setData(JsonUtils.toJson(list, ExtPdCclist.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/extPdCclist/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] stCclistIdList = wrapper.getParameterValues("ST_CCLIST_ID[]");
			extPdCclistService.remove(stCclistIdList);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/extPdCclist/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			ExtPdCclist extPdCclist = extPdCclistService.saveOrUpdate(wrapper);
			if (extPdCclist != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}


	@RequestMapping("/wdf/extPdCclist/exportAll")
	public void download(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		res.setCharacterEncoding("utf-8");
		res.setHeader("Content-disposition", "attachment;filename*=utf-8''");
		ExcelWriter excelWriter = EasyExcel.write(res.getOutputStream(),ExtPdCclist.class).build();
		WriteSheet writeSheet1 = EasyExcel.writerSheet(0,"数据导出").head(ExtPdCclist.class).build();
		RequestWrapper wrapper = new RequestWrapper(req);
		List<ExtPdCclist> list = extPdCclistService.select(wrapper);
		excelWriter.write(list, writeSheet1);
		excelWriter.finish();
	}
	@Autowired
	private ExtPdCclistService extPdCclistService;

}
