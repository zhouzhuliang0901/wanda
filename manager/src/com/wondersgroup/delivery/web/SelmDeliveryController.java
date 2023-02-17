package com.wondersgroup.delivery.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.delivery.bean.SelmDelivery;
import com.wondersgroup.delivery.server.SelmDeliveryService;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 快递柜 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmDeliveryController {

	@RequestMapping("/delivery/selmDelivery/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDelivery.ST_DELIVERY_ID
		String stDeliveryId = wrapper.getParameter(SelmDelivery.ST_DELIVERY_ID);
		if (!StringUtils.trimToEmpty(stDeliveryId).isEmpty()) {
			SelmDelivery selmDelivery = selmDeliveryService.get(stDeliveryId);
			req.setAttribute(SelmDelivery.SELM_DELIVERY, selmDelivery);
		}
		return new ModelAndView("/selmDelivery/edit.jsp");
	}
	
	@RequestMapping("/delivery/selmDelivery/add.do")
	public ModelAndView deviceEdit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDelivery.ST_DELIVERY_ID
		String stMachineId = wrapper.getParameter(SelmDelivery.ST_MACHINE_ID);
		System.out.println(stMachineId);
		if (!StringUtils.trimToEmpty(stMachineId).isEmpty()) {
			SelmDelivery selmDelivery = new SelmDelivery();
			selmDelivery.setStMachineId(stMachineId);
			req.setAttribute(SelmDelivery.SELM_DELIVERY, selmDelivery);
		}
		return new ModelAndView("/selmDelivery/edit.jsp");
	}

	@RequestMapping("/delivery/selmDelivery/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDelivery.ST_DELIVERY_ID
		String stDeliveryId = wrapper.getParameter(SelmDelivery.ST_DELIVERY_ID);
		SelmDelivery selmDelivery = selmDeliveryService.get(stDeliveryId);
		String senderIdcard =selmDelivery.getStSenderId();
		String receiverIdcard =selmDelivery.getStReceiverIdcard();
		String senderIdNumber=null;
		if (senderIdcard != null && !StringUtils.trim(senderIdcard).isEmpty()) {
			if (senderIdcard.length() == 15){
				senderIdNumber = senderIdcard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1*************$2");
	         }
	         if (senderIdcard.length() == 18){
	        	 senderIdNumber = senderIdcard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1****************$2");
	         }
		}
		String receiverIdNumber=null;
		if (receiverIdcard != null && !StringUtils.trim(receiverIdcard).isEmpty()) {
			if (receiverIdcard.length() == 15){
				receiverIdNumber = receiverIdcard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1*************$2");
	         }
	         if (senderIdcard.length() == 18){
	        	 receiverIdNumber = receiverIdcard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1****************$2");
	         }
		}
		selmDelivery.setStSenderId(senderIdNumber);
		selmDelivery.setStReceiverIdcard(receiverIdNumber);
		req.setAttribute(SelmDelivery.SELM_DELIVERY, selmDelivery);
		return new ModelAndView("/selmDelivery/info.jsp");
	}
	

	@RequestMapping("/delivery/selmDelivery/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeliveryService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/delivery/selmDelivery/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		
		Result result = Result.getResult();
		result.success().setMsg("快递柜信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeliveryService.removeList(httpReqRes);
			result.success().setMsg("快递柜信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/delivery/selmDelivery/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("快递柜保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmDelivery selmDelivery = selmDeliveryService.saveOrUpdate(wrapper);
			if (selmDelivery != null)
				result = ExtAjaxReturnMessage.success("快递柜保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	/**
	 * 包裹入柜跳转到快递柜信息
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/delivery/selmDelivery/deliveryDevice.do")
	public ModelAndView deliveryDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMachineId = wrapper.getParameter(SelmDelivery.ST_MACHINE_ID);
		SelmDelivery selmDelivery = new SelmDelivery();
		selmDelivery.setStMachineId(stMachineId);
		req.setAttribute(SelmDelivery.SELM_DELIVERY, selmDelivery);
		return new ModelAndView("/selmDelivery/list.jsp");
	}

	@Autowired
	private SelmDeliveryService selmDeliveryService;

}
