package com.wondersgroup.app.web;

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

import com.wondersgroup.app.bean.Oauth2ClientItem;
import com.wondersgroup.app.service.Oauth2ClientItemService;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 授权事项 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class Oauth2ClientItemController {

	@RequestMapping("/app/oauth2ClientItem/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// Oauth2ClientItem.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2ClientItem.ST_OAUTH2_ID);
		// Oauth2ClientItem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(Oauth2ClientItem.ST_ITEM_ID);
		if (!StringUtils.trimToEmpty(stOauth2Id).isEmpty() && !StringUtils.trimToEmpty(stItemId).isEmpty()) {
			Oauth2ClientItem oauth2ClientItem = oauth2ClientItemService.get(stOauth2Id, stItemId);
			req.setAttribute(Oauth2ClientItem.OAUTH2_CLIENT_ITEM, oauth2ClientItem);
		}
		return new ModelAndView("/oauth2ClientItem/edit.jsp");
	}

	@RequestMapping("/app/oauth2ClientItem/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// Oauth2ClientItem.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2ClientItem.ST_OAUTH2_ID);
		// Oauth2ClientItem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(Oauth2ClientItem.ST_ITEM_ID);
		Oauth2ClientItem oauth2ClientItem = oauth2ClientItemService.get(stOauth2Id, stItemId);
		req.setAttribute(Oauth2ClientItem.OAUTH2_CLIENT_ITEM, oauth2ClientItem);
		return new ModelAndView("/oauth2ClientItem/info.jsp");
	}

	@RequestMapping("/app/oauth2ClientItem/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<Oauth2ClientItem> list = oauth2ClientItemService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, Oauth2ClientItem.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/app/oauth2ClientItem/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("授权事项删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// Oauth2ClientItem.ST_OAUTH2_ID
			String stOauth2Id = wrapper.getParameter(Oauth2ClientItem.ST_OAUTH2_ID);
			// Oauth2ClientItem.ST_ITEM_ID
			String stItemId = wrapper.getParameter(Oauth2ClientItem.ST_ITEM_ID);
			oauth2ClientItemService.remove(stOauth2Id, stItemId);
			result = ExtAjaxReturnMessage.success("授权事项删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/app/oauth2ClientItem/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("授权事项保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			Oauth2ClientItem oauth2ClientItem = oauth2ClientItemService.saveOrUpdate(wrapper);
			if (oauth2ClientItem != null)
				result = ExtAjaxReturnMessage.success("授权事项保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private Oauth2ClientItemService oauth2ClientItemService;

}
