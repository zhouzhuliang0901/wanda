package com.wondersgroup.publicService.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Decoder;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.publicService.utils.ApplyUtil;

@Controller
public class ApplyController {
	
	/**
	 * 保存办件信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicService/saveApply.do")
	public void saveApply(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 关联办理编码
		String tenCode = req.getParameter("tenCode");
		// 事项所属部门编码
		String departCode = req.getParameter("departCode");
		// 办理对象类型 个人和法人
		String targetTypeName = req.getParameter("targetTypeName");
		// 办理对象姓名 企业名称 个人姓名
		String targetName = req.getParameter("targetName");
		// 办理对象编号
		String targetNo = req.getParameter("targetNo");
		String userId = req.getParameter("userId");
		String username = req.getParameter("username");
		String licenseNo = req.getParameter("licenseNo");
		String mobile = req.getParameter("mobile");
		// // 申请内容
		String content = req.getParameter("content");
		if(StringUtils.isNotEmpty(targetTypeName)){
			targetTypeName = URLDecoder.decode(targetTypeName, "utf-8");
		}
		if(StringUtils.isNotEmpty(targetName)){
			targetName = URLDecoder.decode(targetName, "utf-8");
		}
		if(StringUtils.isNotEmpty(username)){
			username = URLDecoder.decode(username, "utf-8");
		}
		if(StringUtils.isNotEmpty(content)){
			content = URLDecoder.decode(content, "utf-8");
		}
		
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "00f07140-2275-4df2-b25f-d0649c8759f7";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "e54a63ec-1c90-4524-b859-594b7951ffdb";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String bldCode = ApplyUtil.getItemApplyPlace(tenCode, departCode);
		String param = ApplyUtil.dealApplyParameter(tenCode, targetTypeName, targetName, targetNo, userId, username, 
				licenseNo, mobile, content, bldCode);
		Log.debug("保存办件信息入参："+param);
		String result = HttpUtil.doPost(head, param, "application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 获取事项申报信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicService/getItemStuffs.do")
	public void getItemStuffs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String itemCode = req.getParameter("itemCode");
		
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "80abcff2-b3ee-4e12-91d1-14ae79eab255";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "290e8d03-e407-476d-9d4e-78b09e1bb1c1";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject obj = new JSONObject();
		obj.put("itemCode", itemCode);
		String result = HttpUtil.doPost(head, obj.toString(), "application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 材料上传
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicService/uploadItemStuffs.do")
	public void uploadItemStuffs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = null;
		if (req instanceof MultipartHttpServletRequest) {
			multipartRequest = ((MultipartHttpServletRequest) req);
		}
		String applyNo = req.getParameter("applyNo");
		String stuffCode = req.getParameter("stuffCode");
		String stuffId = req.getParameter("stuffId");

		byte[] bytes = null;
		MultipartFile file = null;
		String FileData = req.getParameter("FileData");
		if (StringUtils.isBlank(FileData)) {
			List<MultipartFile> files = multipartRequest.getFiles("FileData");
			file = files.get(0);
			bytes = file.getBytes();
			Log.debug("-----------" + file.getOriginalFilename()
					+ "--" + files.get(0).getContentType());
		} else {
			// 上传证照的内容（字符串的形式接受）
			if (FileData != null) {
				try {
					BASE64Decoder decoder = new BASE64Decoder();
					bytes = decoder.decodeBuffer(FileData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "abe4fc19-9896-4b37-b89d-f953b787df92";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "cc364f02-a406-4204-94dc-9cf2497f4b25";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applyNo", applyNo);
		params.put("stuffCode", stuffCode);
		if (StringUtils.isNotEmpty(stuffId)) {
			params.put("stuffId", stuffId);
		}
		
		String result = HttpUtil.sendMultipartFilePost(bytes, head, params,
				file);
//		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 事项申报提交
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicService/submitItem.do")
	public void submitItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String applyNo = req.getParameter("applyNo");

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "8b7be48a-d854-4b1b-96ff-e75b078daebf";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "14b1bbc5-b5f9-455a-9edf-0e8f65107f15";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject data = new JSONObject();
		JSONObject applyNoObj = new JSONObject();
		applyNoObj.put("applyNo", applyNo);
		data.put("data", applyNoObj);
		Log.debug("办件提交参数："+data.toString());
		String result = HttpUtil.doPost(head, data.toString(), "application/json;charset=utf-8");

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
