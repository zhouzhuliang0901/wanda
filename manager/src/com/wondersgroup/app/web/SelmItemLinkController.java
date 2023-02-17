package com.wondersgroup.app.web;

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

import com.wondersgroup.app.bean.SelmItemLink;
import com.wondersgroup.app.service.SelmItemLinkService;
import com.wondersgroup.serverApply.bean.SelmServerItem;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 类别关联事项 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmItemLinkController {

	@RequestMapping("/app/selmItemLink/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmItemLink.ST_ITEM_ID
		String stItemId = wrapper.getParameter(SelmItemLink.ST_ITEM_ID);
		// SelmItemLink.ST_ITEM_TYPE_ID
		String stItemTypeId = wrapper.getParameter(SelmItemLink.ST_ITEM_TYPE_ID);
		if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
			SelmItemLink selmItemLink = selmItemLinkService.get(stItemId, stItemTypeId);
			req.setAttribute(SelmItemLink.SELM_ITEM_LINK, selmItemLink);
		}
		return new ModelAndView("/selmItemLink/edit.jsp");
	}

	@RequestMapping("/app/selmItemLink/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmItemLink.ST_ITEM_ID
		String stItemId = wrapper.getParameter(SelmItemLink.ST_ITEM_ID);
		// SelmItemLink.ST_ITEM_TYPE_ID
		String stItemTypeId = wrapper.getParameter(SelmItemLink.ST_ITEM_TYPE_ID);
		SelmItemLink selmItemLink = selmItemLinkService.get(stItemId, stItemTypeId);
		req.setAttribute(SelmItemLink.SELM_ITEM_LINK, selmItemLink);
		return new ModelAndView("/selmItemLink/info.jsp");
	}

	@RequestMapping("/app/selmItemLink/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmItemLinkService.list(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/app/selmItemLink/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("绑定事项移出失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmItemLinkService.removeList(httpReqRes);
			result.success().setMsg("绑定事项移出成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/app/selmItemLink/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmItemLink selmItemLink = selmItemLinkService.saveOrUpdate(httpReqRes);
			if (selmItemLink != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	/**
	 * 查询组别没有绑定的事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/app/selmItemLink/itemNoLinkList.do")
	public void itemNoLinkList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmItemLinkService.itemNoLinkList(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 查询组别含有的事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/app/selmItemLink/itemLinkList.do")
	public void itemLinkList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmItemLinkService.itemLinkList(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	@Autowired
	private SelmItemLinkService selmItemLinkService;

}
