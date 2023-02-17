package com.wondersgroup.outdevicestatus.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import reindeer.base.utils.RequestWrapper;
import reindeer.base.utils.Transformer4RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.outdevicestatus.bean.InfopubOdeviceStatus;
import com.wondersgroup.outdevicestatus.service.OdeviceStatusService;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.bean.SelmAttach;
import com.wondersgroup.selfapi.service.SelfUsingHistoryService;

@Controller
public class OdeStatusController {

	@Autowired
	private OdeviceStatusService odeviceStatusService;
	
	@Autowired
	private SelfUsingHistoryService selfUsingHistoryService;

	/**
	 * 外设调用的接口
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/odeStatus/status/save.do")
	public void odeviceStatusSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus infopubOdeviceStatus = null;
		try {
			infopubOdeviceStatus = odeviceStatusService.outDeviceStatusSave(httpReqRes);
			if (infopubOdeviceStatus != null){
				result.success().setData(infopubOdeviceStatus.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	/**
	 * 保存模块使用记录
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/odeStatus/status/recordUsingHistory.do")
	public void recordUsingHistory(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		SelfUsingHistory selfUsingHistory = (SelfUsingHistory) t4r.toBean(SelfUsingHistory.class);
		selfUsingHistory = selfUsingHistoryService.saveOrUpdateSelfUsingHistory(selfUsingHistory);
		if(selfUsingHistory == null){
			AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":\""+false+"\",\"HISTORYID\":\"\"}");
		} else{
			AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":\""+true+"\",\"HISTORYID\":\""+selfUsingHistory.getStHisId()+"\"}");
		}
	}
	/**
	 * 保存办事结果
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/odeStatus/status/saveApplyResult.do")
	public void saveApplyResult(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// 关联主键值
		String linkId = wrapper.getParameter("linkId");
		// 文本内容
		String content = wrapper.getParameter("content");
		
		SelmAttach selmAttach = selfUsingHistoryService.saveApplyResult(linkId,content);
		JSONObject json = new JSONObject();
		if(selmAttach != null){
			json.put("SUCCESS", true);
			json.put("ATTACHID", selmAttach.getStAttachid());
		} else {
			json.put("SUCCESS", false);
			json.put("ATTACHID", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
}
