package com.wondersgroup.infopub.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.service.InfopubAddressService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 地址表（办理点） web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class InfopubAddressController {
	
	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_address" })
	@RequestMapping("/infopub/infopubAddress/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infopubAddressService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_address_edit" })
	@RequestMapping("/infopub/infopubAddress/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stAddressId = wrapper.getParameter(InfopubAddress.ST_ADDRESS_ID);
		if (!StringUtils.trimToEmpty(stAddressId).isEmpty()) {
			InfopubAddress infopubAddress = infopubAddressService.get(stAddressId);
			InfopubArea province = infopubAddressService.getArea(infopubAddress.getStCity());
			req.setAttribute(InfopubAddress.INFOPUB_ADDRESS, infopubAddress);
			req.setAttribute(InfopubArea.INFOPUB_AREA, province);
		}
		return new ModelAndView("/infopub/address/edit.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_address_info" })
	@RequestMapping("/infopub/infopubAddress/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// InfopubAddress.ST_ADDRESS_ID
		String stAddressId = wrapper.getParameter(InfopubAddress.ST_ADDRESS_ID);
		InfopubAddress infopubAddress = infopubAddressService.get(stAddressId);
		InfopubArea province = infopubAddressService.getArea(infopubAddress.getStCity());
		req.setAttribute(InfopubAddress.INFOPUB_ADDRESS, infopubAddress);
		req.setAttribute(InfopubArea.INFOPUB_AREA, province);
		return new ModelAndView("/infopub/address/info.jsp");
	}


	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_address_remove","infopub_address_bremove" })
	@RequestMapping("/infopub/infopubAddress/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		
		Result result = Result.getResult();
		result.success().setMsg("信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infopubAddressService.removeList(httpReqRes);
			result.success().setMsg("信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_address_save" })
	@RequestMapping("/infopub/infopubAddress/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("地址表（办理点）保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			InfopubAddress infopubAddress = infopubAddressService.saveOrUpdate(wrapper);
			if (infopubAddress != null)
				result = ExtAjaxReturnMessage.success("地址表（办理点）保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	@RequestMapping("/infopub/infopubAddress/importAddress.do")
	public void importAddress(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("地址表（办理点）导入失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			InfopubAddress infopubAddress = infopubAddressService.importAddress(wrapper);
			if (infopubAddress != null)
				result = ExtAjaxReturnMessage.success("地址表（办理点）导入成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private InfopubAddressService infopubAddressService;

}
