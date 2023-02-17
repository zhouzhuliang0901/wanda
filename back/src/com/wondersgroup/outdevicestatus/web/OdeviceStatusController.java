package com.wondersgroup.outdevicestatus.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.outdevicestatus.bean.InfopubOdeviceStatus;
import com.wondersgroup.outdevicestatus.service.OdeviceStatusService;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;



@Controller
public class OdeviceStatusController {
	
	@Autowired
	private OdeviceStatusService odeviceStatusService;

	/**
	 * 外设调用的接口
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/outdevicestatus/odeviceStatus/save.do")
	public void odeviceStatusSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus infopubOdeviceStatus = null;
		try {
			infopubOdeviceStatus = odeviceStatusService.outDeviceStatusSave(httpReqRes);
			if (infopubOdeviceStatus != null){
				//result.success().setCode("200");
				//result.success().setSuccess(true);
				result.success().setData(infopubOdeviceStatus.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * 查看外设状态(通过设备id和外设标识)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/outdevicestatus/odeviceStatus/info.do")
	public void odeviceStatusInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		Result result = Result.getResult();
		result.setMsg("获取失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus infopubOdeviceStatus = null;
		JSONObject json = new JSONObject();
		try {
			infopubOdeviceStatus = odeviceStatusService.getOdeviceStatus(httpReqRes);
			if (infopubOdeviceStatus != null) {
				json.put("data", infopubOdeviceStatus);
				result.success().setData(json.toString());
				//result.setCode("300");
				result.success().setMsg("获取成功");
				//result.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		httpReqRes.writeJsonP(result);

	}
	
	
	/**
	 * 查看外设状态(通过设备id)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/outdevicestatus/odeviceStatus/list.do")
	public void odeviceStatusInfoList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		List<InfopubOdeviceStatus> list = null;
		try {
			list = odeviceStatusService.getOdeviceStatusByDeviceId(httpReqRes);
			JSONArray array = new JSONArray();
			Iterator<InfopubOdeviceStatus> iter = list.iterator();
			while (iter.hasNext()) {
				InfopubOdeviceStatus status = iter.next();
				array.add(status);
			}
			result.setData(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);

	}

	
	/**
	 * 外设状态信息编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/edit.do")
	public ModelAndView odeviceStatusEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus infopubOdeviceStatus = odeviceStatusService
				.getOdeviceStatus(httpReqRes);
		req.setAttribute(InfopubOdeviceStatus.INFOPUB_ODEVICE_STATUS, infopubOdeviceStatus);
		return new ModelAndView("/infopub/odeviceStatus/edit.jsp");
	}
	
	

	/**
	 * 外设状态删除
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/outdevicestatus/odeviceStatus/remove.do")
	public void odeviceStatusRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("外设状态信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			odeviceStatusService.odeviceStatusRemove(httpReqRes);
			result.success().setMsg("外设状态信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
}
