package com.wondersgroup.selfapi.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.utils.EasyUIHelper;
import reindeer.base.utils.RequestWrapper;

import com.wondersgroup.selfapi.service.MaterialUpService;

/**
 * 线下申请（线下办理） 后台数据对接一网通办
 * 
 * @author lenovo
 * 
 */
@RestController
public class OfflineApplyController {

	@Autowired
	private MaterialUpService materialUpService;

	/**
	 * 获取部门列表
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/organList")
	public void organList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.organList(wrapper);
		String jsonpprifx = req.getParameter("jsonpCallback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 根据部门id（organId）获取事项列表
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/organInfo")
	public void organInfo(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.itemByOrganId(wrapper);
		String jsonpprifx = req.getParameter("jsonpCallback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 获取事项列表(可根据事项名称模糊查询itemName)
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/itemList")
	public void itemList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.queryItem(wrapper);
		String jsonpprifx = req.getParameter("jsonpCallback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 根据事项ID获取情形列表(itemId)
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/itemSituationList")
	public void itemSituationList(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.queryStatusList(wrapper);
		String jsonpprifx = req.getParameter("jsonpCallback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 根据情形ID获取材料列表(statusId)
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/selfapi/offlineapply/itemStuffList")
	public void itemStuffList(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.queryStuffList(wrapper);
		String jsonpprifx = req.getParameter("jsonpCllback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 保存基本信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/save")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.save(wrapper);
		String jsonpprifx = req.getParameter("jsonpCallback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 上传附件（二进制流的形式和base64形式）
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/uploadStuff")
	public void uploadStuff(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		System.out.println("+++++++++++");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Origin", "*");
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.uploadFile(wrapper);
		EasyUIHelper.writeResponse(res, json.toString());
	}

	/**
	 * 办件提交
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/offlineapply/toSubmit")
	public void toSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String result = StringUtils.EMPTY;
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject json = materialUpService.toSubmit(wrapper);
		String jsonpprifx = req.getParameter("jsonpCallback");
		result = jsonpprifx + "(" + json.toString() + ")";
		EasyUIHelper.writeResponse(res, result);
	}
}
