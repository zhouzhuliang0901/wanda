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

import tw.ecosystem.reindeer.web.HttpReqRes;

import com.wondersgroup.app.bean.Oauth2Client;
import com.wondersgroup.app.bean.Oauth2ClientDevice;
import com.wondersgroup.app.service.Oauth2ClientDeviceService;
import com.wondersgroup.app.service.Oauth2ClientItemService;
import com.wondersgroup.app.service.Oauth2ClientService;
import com.wondersgroup.app.service.SelmItemService;
import com.wondersgroup.infopub.service.InfoPubService;



import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 客户端关联设备 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class Oauth2ClientDeviceController {

	@RequestMapping("/app/oauth2ClientDevice/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// Oauth2ClientDevice.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2ClientDevice.ST_OAUTH2_ID);
		// Oauth2ClientDevice.ST_DEVICE_ID
		String stDeviceId = wrapper.getParameter(Oauth2ClientDevice.ST_DEVICE_ID);
		if (!StringUtils.trimToEmpty(stOauth2Id).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			Oauth2ClientDevice oauth2ClientDevice = oauth2ClientDeviceService.get(stOauth2Id, stDeviceId);
			req.setAttribute(Oauth2ClientDevice.OAUTH2_CLIENT_DEVICE, oauth2ClientDevice);
		}
		return new ModelAndView("/oauth2ClientDevice/edit.jsp");
	}

	@RequestMapping("/app/oauth2ClientDevice/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// Oauth2ClientDevice.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2ClientDevice.ST_OAUTH2_ID);
		// Oauth2ClientDevice.ST_DEVICE_ID
		String stDeviceId = wrapper.getParameter(Oauth2ClientDevice.ST_DEVICE_ID);
		Oauth2ClientDevice oauth2ClientDevice = oauth2ClientDeviceService.get(stOauth2Id, stDeviceId);
		req.setAttribute(Oauth2ClientDevice.OAUTH2_CLIENT_DEVICE, oauth2ClientDevice);
		return new ModelAndView("/oauth2ClientDevice/info.jsp");
	}

	@RequestMapping("/app/oauth2ClientDevice/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<Oauth2ClientDevice> list = oauth2ClientDeviceService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, Oauth2ClientDevice.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/app/oauth2ClientDevice/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("客户端关联设备删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// Oauth2ClientDevice.ST_OAUTH2_ID
			String stOauth2Id = wrapper.getParameter(Oauth2ClientDevice.ST_OAUTH2_ID);
			// Oauth2ClientDevice.ST_DEVICE_ID
			String stDeviceId = wrapper.getParameter(Oauth2ClientDevice.ST_DEVICE_ID);
			oauth2ClientDeviceService.remove(stOauth2Id, stDeviceId);
			result = ExtAjaxReturnMessage.success("客户端关联设备删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/app/oauth2ClientDevice/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("客户端关联设备保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			Oauth2ClientDevice oauth2ClientDevice = oauth2ClientDeviceService.saveOrUpdate(wrapper);
			if (oauth2ClientDevice != null)
				result = ExtAjaxReturnMessage.success("客户端关联设备保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	/**
	 * 授权
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/oauth2device/add.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		// SelmStatistics.ST_STATISTICS_ID
		String stOauth2Id = httpReqRes
				.getParameter(Oauth2Client.ST_OAUTH2_ID);
		Oauth2Client oauth2Client = oauth2ClientService
				.get(stOauth2Id);
		req.setAttribute(Oauth2Client.OAUTH2_CLIENT, oauth2Client);
		return new ModelAndView("/app/oauth2device.jsp");
	}
	@Autowired
	private Oauth2ClientDeviceService oauth2ClientDeviceService;
	@Autowired
	private Oauth2ClientService oauth2ClientService;
	@Autowired
	private InfoPubService infoPubService;
	@Autowired
	private SelmItemService selmItemService;
	@Autowired
	private Oauth2ClientItemService oauth2ClientItemService;

}
