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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.delivery.bean.SelmDelivery;
import com.wondersgroup.delivery.bean.SelmDeliveryHistory;
import com.wondersgroup.delivery.server.SelmDeliveryHistoryService;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 快递柜历史 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmDeliveryHistoryController {

	@RequestMapping("/delivery/selmDeliveryHistory/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeliveryHistory.ST_DELIVERY_ID
		String stDeliveryId = wrapper.getParameter(SelmDeliveryHistory.ST_DELIVERY_ID);
		if (!StringUtils.trimToEmpty(stDeliveryId).isEmpty()) {
			SelmDeliveryHistory selmDeliveryHistory = selmDeliveryHistoryService.get(stDeliveryId);
			req.setAttribute(SelmDeliveryHistory.SELM_DELIVERY_HISTORY, selmDeliveryHistory);
		}
		return new ModelAndView("/selmDeliveryHistory/edit.jsp");
	}

	@RequestMapping("/delivery/selmDeliveryHistory/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		
		String stDeliveryId = wrapper.getParameter(SelmDeliveryHistory.ST_DELIVERY_ID);
		SelmDeliveryHistory selmDeliveryHistory = selmDeliveryHistoryService.get(stDeliveryId);
		String senderIdcard =selmDeliveryHistory.getStSenderId();
		String receiverIdcard =selmDeliveryHistory.getStReceiverIdcard();
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
		selmDeliveryHistory.setStSenderId(senderIdNumber);
		selmDeliveryHistory.setStReceiverIdcard(receiverIdNumber);
		req.setAttribute(SelmDeliveryHistory.SELM_DELIVERY_HISTORY, selmDeliveryHistory);
		return new ModelAndView("/selmDelivery/selmDeliveryHis/info.jsp");
	}

	@RequestMapping("/delivery/selmDeliveryHistory/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeliveryHistoryService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/delivery/selmDeliveryHistory/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("快递柜历史信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeliveryHistoryService.removeList(httpReqRes);
			result.success().setMsg("快递柜历史信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/delivery/selmDeliveryHistory/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("快递柜历史保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmDeliveryHistory selmDeliveryHistory = selmDeliveryHistoryService.saveOrUpdate(wrapper);
			if (selmDeliveryHistory != null)
				result = ExtAjaxReturnMessage.success("快递柜历史保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	/**
	 * 包裹入柜跳转到快递柜历史信息
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/delivery/selmDeliveryHistory/deliveryHistoryDevice.do")
	public ModelAndView deliveryDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMachineId = wrapper.getParameter(SelmDeliveryHistory.ST_MACHINE_ID);
		SelmDeliveryHistory selmDeliveryHistory = new SelmDeliveryHistory();
		selmDeliveryHistory.setStMachineId(stMachineId);
		req.setAttribute(SelmDeliveryHistory.SELM_DELIVERY_HISTORY, selmDeliveryHistory);
		return new ModelAndView("/selmDelivery/selmDeliveryHis/list.jsp");
	}

	@Autowired
	private SelmDeliveryHistoryService selmDeliveryHistoryService;

}
