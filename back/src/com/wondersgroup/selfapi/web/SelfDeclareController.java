package com.wondersgroup.selfapi.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import reindeer.base.utils.AciJsonHelper;
import reindeer.base.utils.RequestWrapper;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.selfapi.bean.ArchivesApplyInfo;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemInfo;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;
import com.wondersgroup.selfapi.service.SelfDeclareService;

@Controller
public class SelfDeclareController {

	@Autowired
	private SelfDeclareService selfDeclareService;

	/*
	 * ================================================ 
	 * 申报服务相关
	 * ================================================
	 */
	/**
	 * 申报事项的办理提交统一接口 appName：申报数据的保存接口的唯一标识，每个事项的接口APIID不一样，由前端传递
	 * paramStr：申报内容的数据
	 * 
	 * @author XB
	 * 
	 */
	@RequestMapping("/selfapi/selfDeclare/saveApply.do")
	public void saveApply(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String paramStr = req.getParameter("paramStr");
		// 每个事项接口的APIID
		String appName = req.getParameter("appName");
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		String itemName = req.getParameter("itemName");
		if (StringUtils.isNotEmpty(paramStr)) {
			paramStr = URLDecoder.decode(paramStr, "utf-8");
		}
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		if (StringUtils.isNotEmpty(itemName)) {
			itemName = URLDecoder.decode(itemName, "utf-8");
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject obj = new JSONObject();
		try {
			JSONObject json = JSONObject.fromObject(paramStr);
			System.out.println("提交统一接口 参数："+json.toString());
			String contentType = "application/json;charset=utf-8";
			String result = HttpUtil.doPost(head, json.toString(), contentType);

			if (!"error".equals(result)) {
				obj.put("success", true);
				obj.put("msg", "");
				obj.put("data", result);

				if (StringUtils.isNotEmpty(result)) {
					try {
						JSONObject apply = JSONObject.fromObject(result);
						boolean success = apply.optBoolean("isSuccess");
						if (success) {
							String applyNo = json.optString("applyNo");
							if(StringUtils.isEmpty(applyNo)){
								applyNo = apply.optJSONObject("data").optString("applyNo");
							}
							selfDeclareService.addArchivesInfo("", applyNo,
									itemName, name, idCard);
						}
					} catch (Exception e) {
						Log.debug("申请人：" + name + "(" + idCard
								+ ")申报信息保存失败，不记录办件。"+e.getMessage());
					}
				}
			} else {
				obj.put("success", false);
				obj.put("msg", "接口服务异常！");
				obj.put("data", "");
			}
		} catch (Exception e) {
			obj.put("success", false);
			obj.put("msg", "参数格式异常！");
			obj.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}

	/**
	 * 获取事项申报信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/getItemStuffs.do")
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

		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, obj.toString(), contentType);

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 获取事项办理点信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/getItemApplyPlace.do")
	public void getItemApplyPlace(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String itemCodes = req.getParameter("itemCodes");
		String areaCode = req.getParameter("regionCode");

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "59c7c2bc-889b-4a46-826d-91b313c692b2";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "1cad7c69-2d49-4783-9926-4520bb30dd58";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject obj = new JSONObject();
		obj.put("itemCodes", itemCodes);

		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, obj.toString(), contentType);

		JSONObject json = JSONObject.fromObject(result);
		JSONArray arr = json.optJSONArray("data");

		JSONArray blArr = new JSONArray();
		for (int iLoop = 0; iLoop < arr.size(); iLoop++) {
			JSONObject element = arr.optJSONObject(iLoop);
			String regionCode = element.optString("regionCode");
			if (regionCode.equals(areaCode)) {
				blArr.add(element);
			}
		}

		AciJsonHelper.writeJsonPResponse(req, res, blArr.toString());
	}

	/**
	 * 材料上传
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/uploadItemStuffs.do")
	public void uploadItemStuffs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// MultipartHttpServletRequest multipartRequest =
		// ((MultipartHttpServletRequest) req);
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
			System.out.println("传的文件的二进制流");
			List<MultipartFile> files = multipartRequest.getFiles("FileData");
			file = files.get(0);
			bytes = file.getBytes();
			System.out.println("-----------" + file.getOriginalFilename()
					+ "--" + files.get(0).getContentType());
		} else {
			// 上传证照的内容（字符串的形式接受）
			System.out.println("传的文件的Base64位字符串");
			if (FileData != null) {
				try {
					sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
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
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 办件材料删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/deleteItemStuffs.do")
	public void deleteItemStuffs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stuffId = req.getParameter("stuffId");

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "be4139a7-f388-48c4-9339-fa8fdf213198";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "03ac2f77-b901-4e61-b517-b6038452e45b";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject json = new JSONObject();
		json.put("stuffId", stuffId);

		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, json.toString(), contentType);

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 事项申报提交
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/submitItem.do")
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

		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, data.toString(), contentType);

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 查询办件记录，根据办件编码（统一审批编码）查询办件结果的证照
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/queryApplyInfo.do")
	public void queryApplyInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String idCard = req.getParameter("idCard");
		String item = req.getParameter("item");
		if (StringUtils.isNotEmpty(item)) {
			item = URLDecoder.decode(item, "utf-8");
		}

		List<ArchivesApplyInfo> list = selfDeclareService
				.queryArchivesInfoByIdentNo(idCard, item, null);
		JSONObject result = new JSONObject();
		if (list.size() > 0) {
			result.put("SUCCESS", "true");
			result.put("MSG", "");
			result.put("applyNo", list.get(0).getStApplyNo());
		} else if (list.size() <= 0) {
			result.put("SUCCESS", "false");
			result.put("MSG", "未查询到成功办理信息！");
			result.put("applyNo", "");
		}

		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}

	/**
	 * 事项单一保存接口
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/saveInfo.do")
	public void saveInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String info = req.getParameter("info");
		if(StringUtils.isNotEmpty(info)){
			info = URLDecoder.decode(info, "utf-8");
		}

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "00f07140-2275-4df2-b25f-d0649c8759f7";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "e54a63ec-1c90-4524-b859-594b7951ffdb";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject json = new JSONObject();
		json.put("data", JSONObject.fromObject(info));

		System.out.println(json.toString());
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, json.toString(), contentType);
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/*
	 * ================================================ 
	 * 申报服务相关
	 * ================================================
	 */

	/*
	 * ================================================ 
	 * 统一受理平台办件库
	 * ================================================
	 */
	/**
	 * 保存办件信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/deal/work/saveApplyInfo.do")
	public void saveApplyInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String itemCode = req.getParameter("itemCode");// 事项编码
		String taskHandleItem = req.getParameter("taskHandleItem");// 33位办理编码
		String itemName = req.getParameter("itemName");// 事项名称
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		String targetType = req.getParameter("targetType");// 办理对象类型
		if(StringUtils.isNotEmpty(targetType)){
			targetType = URLDecoder.decode(targetType, "utf-8");
		}
		String targetName = req.getParameter("targetName");// 办理对象名称
		if(StringUtils.isNotEmpty(targetName)){
			targetName = URLDecoder.decode(targetName, "utf-8");
		}
		String targetNo = req.getParameter("targetNo");// 办理对象证件号
		String userId = req.getParameter("userId");
		String username = req.getParameter("username");
		if(StringUtils.isNotEmpty(username)){
			username = URLDecoder.decode(username, "utf-8");
		}
		String licenseType = req.getParameter("licenseType");
		String licenseNo = req.getParameter("licenseNo");
		String mobile = req.getParameter("mobile");
		String departCode = req.getParameter("departCode");
		String departName = req.getParameter("departName");
		if(StringUtils.isNotEmpty(departName)){
			departName = URLDecoder.decode(departName, "utf-8");
		}
		String content = req.getParameter("content");
		if(StringUtils.isNotEmpty(content)){
			content = URLDecoder.decode(content, "utf-8");
		}
		String districtCode = req.getParameter("districtCode");
		String info = req.getParameter("info");

		String param = dealParam(itemCode, taskHandleItem, itemName,
				targetType, targetName, targetNo, userId, username,
				licenseType, licenseNo, mobile, departCode, departName,
				content, districtCode, info);
		
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "54b3f970-bd01-11eb-8742-b3a71c8ea78a";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "5705dae0-c1f2-11eb-b232-e5d33d6ba187";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, param, contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	private String dealParam(String itemCode, String taskHandleItem,
			String itemName, String targetType, String targetName,
			String targetNo, String userId, String username,
			String licenseType, String licenseNo, String mobile,
			String departCode, String departName, String content,
			String districtCode, String info) {
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String applyNo = HttpUtil.getApplyNo(itemCode);
		JSONObject json = new JSONObject();
		json.put("applyNo", applyNo);
		json.put("itemCode", itemCode);
		json.put("taskHandleItem", taskHandleItem);
		json.put("itemName", itemName);
		json.put("targetType", targetType);
		json.put("targetName", targetName);
		json.put("targetNo", targetNo);
		json.put("userId", userId);
		json.put("username", username);
		json.put("licenseType", licenseType);
		json.put("licenseNo", licenseNo);
		json.put("mobile", mobile);
		json.put("departCode", departCode == null ? "" : departCode);
		json.put("departName", departName == null ? "" : departName);
		json.put("source", "网上申请");
		json.put("content", content);
		String opTime = sdfTime.format(new Date());
		json.put("opTime", opTime);
		json.put("districtCode", districtCode == null ? "" : districtCode);
		JSONObject infoJson = new JSONObject();
		try{
			infoJson = JSONObject.fromObject(info);
			infoJson.put("booking_uuid","");
			infoJson.put("booking_id", applyNo);
			infoJson.put("booking_time", sdfDay.format(new Date()));
			infoJson.put("applydate", sdfDay.format(new Date()));
		} catch (Exception e) {
		}
		json.put("info", infoJson.toString());
		return json.toString();
	}

	/**
	 * 上传办件材料
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/deal/work/uploadApplyStuffs.do")
	public void uploadApplyStuffs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stuffId = req.getParameter("stuffId");
		String applyNo = req.getParameter("applyNo");
		String stuffCode = req.getParameter("stuffCode");
		String stuffName = req.getParameter("stuffName");
		// 0:电子文件;1:引用;2:快递收取纸质材料;3:窗口收取纸质材料;4:网上申报信息;5:数据共享
		String stuffType = req.getParameter("stuffType");
		// 0:首次提交;2:补充材料
		String stuffStatus = req.getParameter("stuffStatus");
		// 材料复用方式
		String stuffSource = req.getParameter("stuffSource");
		byte[] bytes = null;
		MultipartFile file = null;
		String FileData = req.getParameter("file");
		if (StringUtils.isBlank(FileData)) {
			MultipartHttpServletRequest multipartRequest = null;
			if (req instanceof MultipartHttpServletRequest) {
				multipartRequest = ((MultipartHttpServletRequest) req);
			}
			System.out.println("uploadApplyStuffs------传的文件的二进制流");
			List<MultipartFile> files = multipartRequest.getFiles("file");
			file = files.get(0);
			bytes = file.getBytes();
			System.out.println("-----------" + file.getOriginalFilename()
					+ "--" + files.get(0).getContentType());
		} else {
			System.out.println("uploadApplyStuffs------传的文件的Base64位字符串");
			if (FileData != null) {
				try {
					sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
					bytes = decoder.decodeBuffer(FileData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "94413de0-bd02-11eb-8742-b3a71c8ea78a";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "db56d740-c1f2-11eb-b232-e5d33d6ba187";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		InputStream is = new ByteArrayInputStream(bytes);
		builder.addBinaryBody("file", is, ContentType.MULTIPART_FORM_DATA,
				file != null ? file.getOriginalFilename() : "申请材料.png");
		@SuppressWarnings("deprecation")
		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
		builder.addTextBody("stuffId", stuffId==null?"":stuffId, contentType);
		builder.addTextBody("applyNo", applyNo, contentType);
		builder.addTextBody("stuffCode", stuffCode, contentType);
		builder.addTextBody("stuffName", stuffName, contentType);
		builder.addTextBody("stuffType", stuffType, contentType);
		builder.addTextBody("stuffStatus", stuffStatus, contentType);
		builder.addTextBody("stuffSource", stuffSource==null?"":stuffSource, contentType);
		HttpEntity entity = builder.build();
		
		String result = HttpUtil.doPost(head, entity);
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 补充材料上传
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/deal/work/uploadApplySupplementStuffs.do")
	public void uploadApplySupplementStuffs(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String applyNo = req.getParameter("applyNo");
		String stuffCode = req.getParameter("stuffCode");
		String GetNum = req.getParameter("GetNum");
		byte[] bytes = null;
		MultipartFile file = null;
		String FileData = req.getParameter("file");
		if (StringUtils.isBlank(FileData)) {
			MultipartHttpServletRequest multipartRequest = null;
			if (req instanceof MultipartHttpServletRequest) {
				multipartRequest = ((MultipartHttpServletRequest) req);
			}
			System.out.println("uploadApplyStuffs------传的文件的二进制流");
			List<MultipartFile> files = multipartRequest.getFiles("file");
			file = files.get(0);
			bytes = file.getBytes();
			System.out.println("-----------" + file.getOriginalFilename()
					+ "--" + files.get(0).getContentType());
		} else {
			System.out.println("uploadApplyStuffs------传的文件的Base64位字符串");
			if (FileData != null) {
				try {
					sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
					bytes = decoder.decodeBuffer(FileData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "0b27a3cf-e5f6-4e12-a83c-274fb46e963c";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		InputStream is = new ByteArrayInputStream(bytes);
		builder.addBinaryBody("file1", is, ContentType.MULTIPART_FORM_DATA,
				file != null ? file.getOriginalFilename() : "申请材料.png");
		@SuppressWarnings("deprecation")
		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE,
				HTTP.UTF_8);
		builder.addTextBody("applyNo", applyNo, contentType);
		builder.addTextBody("stuffCode", stuffCode, contentType);
		builder.addTextBody("GetNum", GetNum, contentType);

		HttpEntity entity = builder.build();

		String result = HttpUtil.doPost(head, entity);
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 提交申报信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/deal/work/submitApplyInfo.do")
	public void submitApplyInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String applyNo = req.getParameter("applyNo");
		String departCode = req.getParameter("departCode");
		String itemCode = req.getParameter("itemCode");
		
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "e6d64b40-bd02-11eb-8742-b3a71c8ea78a";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "f5a108f0-c1f2-11eb-b232-e5d33d6ba187";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONArray subItemInfo = new JSONArray();
		JSONObject item = new JSONObject();
		JSONObject data = new JSONObject();
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		item.put("itemCode", "0105632000");
		item.put("itemCode", itemCode);
		item.put("departCode", departCode == null ? "" : departCode);
		subItemInfo.add(item);
		data.put("applyNo", applyNo);
		data.put("uapplyNo", "");
		data.put("opTime", sdfTime.format(new Date()));
		data.put("subItemInfo", subItemInfo);
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, data.toString(), contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/*
	 * ================================================ 
	 * 统一受理平台办件库
	 * ================================================
	 */

	/**
	 * 全量导入事项基本信息（按区）（中台服务：办事指南接口） region：所属区域编号（SH00SH、SH00PD......）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemInfo.do")
	public void getItemInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String region = req.getParameter("region");
		String appName = "e709f0ad-e4cc-486d-9f11-2475fee2b68c";

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject data = new JSONObject();
		data.put("region", region);

		String contentType = "application/json;charset=utf-8";
		String result = getResponse(head, data.toString(), contentType);

		try {
			com.alibaba.fastjson.JSONArray itemArr = com.alibaba.fastjson.JSONObject
					.parseObject(result).getJSONArray("items");
			System.out.println("区：" + region + "事项总数：" + itemArr.size());
			for (int iLoop = 0; iLoop < itemArr.size(); iLoop++) {
				SelmZhallItemInfo info = new SelmZhallItemInfo();
				com.alibaba.fastjson.JSONObject item = itemArr
						.getJSONObject(iLoop);
				// String stId = item.getJSONObject("item").getString("ST_ID");
				info.setStId(item.getJSONObject("item").getString("ST_ID"));
				// String itemNo =
				// item.getJSONObject("item").getString("ST_ITEM_ID");
				info.setStItemNo(item.getJSONObject("item").getString(
						"ST_ITEM_ID"));
				String stHandleObject = item.getJSONObject("item").getString(
						"ST_HANDLE_OBJECT");// 申请对象
				// String stItemType =
				// item.getJSONObject("item").getString("ST_ITEM_TYPE");
				info.setStItemType(item.getJSONObject("item").getString(
						"ST_ITEM_TYPE"));
				// String stOrgCode =
				// item.getJSONObject("item").getString("ST_ORG_CODE");
				info.setStOrgCode(item.getJSONObject("item").getString(
						"ST_ORG_CODE"));
				info.setStOrgName(item.getJSONObject("item").getString(
						"ST_ORG_NAME"));
				com.alibaba.fastjson.JSONArray categoryArr = item
						.getJSONArray("category");// 事项分类列表
				// String stItemtenCode =
				// item.getJSONObject("item").getString("ST_TEN_CODE");
				info.setStItemTenCode(item.getJSONObject("item").getString(
						"ST_TEN_CODE"));
				// String stItemTransactName =
				// item.getJSONObject("item").getString("ST_TRANSACT_NAME");
				info.setStTransactName(item.getJSONObject("item").getString(
						"ST_TRANSACT_NAME"));
				String subItem = item.getJSONObject("item").getString(
						"ST_SUBITEM_NAME");
				String stItemName = item.getJSONObject("item").getString(
						"ST_ITEM_NAME");
				if (subItem != null && stItemName != null) {
					if (subItem.equals(stItemName)) {
						info.setStItemName(stItemName);
						info.setNmIsson(0);// 主事项
					} else if (subItem.startsWith(stItemName)) {
						info.setStItemName(subItem);
						info.setNmIsson(1);// 子事项
					} else if (subItem != null && !subItem.equals("")) {
						info.setStItemName(stItemName + "(" + subItem + ")");
						info.setNmIsson(1);// 子事项
					}
				}

				if (StringUtils.isNotEmpty(stHandleObject)) {
					String nmBelong = "";
					String[] handleArr = stHandleObject.split("#");
					for (String str : handleArr) {
						if ("法人".equals(str)) {
							nmBelong += "2";
						} else if ("个人".equals(str)) {
							nmBelong += "1";
						} else if ("其他组织".equals(str)) {
							nmBelong += "3";
						}
					}
					info.setNmBelong(nmBelong);
				}

				String stDicCode = "";
				for (int i = 0; i < categoryArr.size(); i++) {
					stDicCode += categoryArr.getJSONObject(i).getString(
							"ST_CATEGORY_ID")
							+ ",";
				}
				info.setStDicCode(stDicCode);
				info.setDtCreate(new Timestamp(System.currentTimeMillis()));
				info.setStRemove(0);

				List<SelmZhallItemInfo> list = selfDeclareService
						.queryZhallItem(info.getStId());
				if (list.size() <= 0) {
					// 新增
					try {
						selfDeclareService.addZhallItem(info);
					} catch (Exception e) {
						Log.debug(e.getMessage().toString());
						Log.debug("事项：" + info.getStItemName() + "保存失败，ID为："
								+ info.getStId());
					}
				} else {
					// 更新
					try {
						selfDeclareService.updateZhallItem(info);
					} catch (Exception e) {
						Log.debug(e.getMessage().toString());
						Log.debug("事项：" + info.getStItemName() + "更新失败，ID为："
								+ info.getStId());
					}
				}
			}
		} catch (Exception e) {
			Log.debug(e);
		}

		AciJsonHelper.writeJsonPResponse(req, res, "执行结束！");
	}

	/**
	 * 根据事项主键获取办事指南信息（中台服务：办事指南接口）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemDetailByID.do")
	public void getItemDetailByID(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stId = req.getParameter("stId");
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "9dbd50b9-e7a3-44ee-aa9e-3b9a33010bae";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "e35c79a7-6eac-4b4a-b6a7-3401e91fde72";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject data = new JSONObject();
		data.put("stId", stId);

		String contentType = "application/json;charset=utf-8";
		String result = getResponse(head, data.toString(), contentType);

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 输入事项名称关键字搜索事项
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemListByItemNameForPage.do")
	public void getItemListByItemNameForPage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String itemName = req.getParameter("itemName");
		String areaCode = req.getParameter("areaCode");
		if (StringUtils.isNotEmpty(itemName)) {
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		if (StringUtils.isNotEmpty(areaCode)) {
			ItemSetPage page = selfDeclareService.getItemListByItemNameForPage(
					itemName, areaCode, pageSize, currentPage);
			AciJsonHelper.writeJsonPResponse(req, res,
					JSONObject.fromObject(page).toString());
		} else {
			AciJsonHelper.writeJsonPResponse(req, res,
					"Parameter areaCode is null!");
		}
	}

	/**
	 * 获取办事指南的所有事项列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getAllItemListForPage.do")
	public void getAllItemListForPage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareService.getAllItemListForPage(pageSize,
				currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 获取有办事指南的所有部门列表
	 */
	@RequestMapping("/selfapi/declare/getOrganListForDeclarePage.do")
	public void getOrganListForDeclarePage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = selfDeclareService.getOrganListForDeclarePage(
				pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 获取某部门的所有事项列表
	 */
	@RequestMapping("/selfapi/declare/getItemListByOrganCodeForPage.do")
	public void getItemListByOrganIdForPage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String organCode = req.getParameter("organCode");
		String model = req.getParameter("model");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareService.getItemListByOrganCodeForPage(
				model, organCode, pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 根据事项编码获取其下的情形事项
	 */
	@RequestMapping("/selfapi/declare/getWindowItemStatusList.do")
	public void getWindowItemStatusList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String itemNo = req.getParameter("itemNo");
		String deptCode = req.getParameter("deptCode");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		WindowItemStatusPage page = selfDeclareService.getWindowItemStatusList(
				itemNo, pageSize, currentPage, deptCode);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 查询所有行政区
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/declare/getAllAreaInShanghai.do")
	public void getAllAreaInShanghai(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = selfDeclareService.getAllAreaInShanghai(
				pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 查询区下有办事指南事项的部门
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/declare/getDeptInArea.do")
	public void getDeptInArea(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String areaCode = req.getParameter("areaCode");
		String model = req.getParameter("model");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		if (StringUtils.isNotEmpty(areaCode)) {
			OrganNodeInfoPage page = selfDeclareService.getDeptInArea(model,
					pageSize, currentPage, areaCode);
			AciJsonHelper.writeJsonPResponse(req, res,
					JSONObject.fromObject(page).toString());
		} else {
			AciJsonHelper.writeJsonPResponse(req, res,
					"Parameter areaCode is null!");
		}
	}

	/**
	 * 查询市、区下事项主题
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/declare/getItemTheme.do")
	public void getItemTheme(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String areaCode = wrapper.getParameter("areaCode");
		String type = wrapper.getParameter("type");
		String model = req.getParameter("model");
		if (StringUtils.isNotEmpty(areaCode)) {
			List<ItemTheme> list = selfDeclareService.getItemTheme(model,
					areaCode, type);
			JSONArray array = new JSONArray();
			JSONObject josnResult = new JSONObject();
			Iterator<ItemTheme> iter = list.iterator();
			while (iter.hasNext()) {
				ItemTheme theme = iter.next();
				JSONObject josn = new JSONObject();
				josn.put("itemTypeCode", theme.getItemTypeCode());
				josn.put("itemTypeName", theme.getItemTypeName());
				array.add(josn);
			}
			josnResult.put("total", array.size());
			josnResult.put("data", array);
			AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
		} else {
			AciJsonHelper.writeJsonPResponse(req, res,
					"Parameter areaCode is null!");
		}
	}

	/**
	 * 查询市、区主题下的事项
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/declare/getItemInTheme.do")
	public void getItemInTheme(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String themeCode = wrapper.getParameter("themeCode");
		String areaCode = wrapper.getParameter("areaCode");
		String type = wrapper.getParameter("type");
		String model = req.getParameter("model");
		if (StringUtils.isNotEmpty(areaCode)) {
			List<ItemSet> list = selfDeclareService.getItemInTheme(model,
					themeCode, areaCode, type);
			JSONArray array = new JSONArray();
			JSONObject josnResult = new JSONObject();
			Iterator<ItemSet> iter = list.iterator();
			while (iter.hasNext()) {
				ItemSet item = iter.next();
				JSONObject josn = new JSONObject();
				josn.put("stItemNo", item.getStItemNo());
				josn.put("stItemName", item.getStItemName());
				josn.put("organCode", item.getOrganCode());
				array.add(josn);
			}
			josnResult.put("total", array.size());
			josnResult.put("data", array);
			AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
		} else {
			AciJsonHelper.writeJsonPResponse(req, res,
					"Parameter areaCode is null!");
		}
	}

	/**
	 * 查询街道下主题
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/declare/getThemeInStreet.do")
	public void getThemeInStreet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		List<ItemTheme> list = selfDeclareService.getThemeInStreet();
		JSONArray array = new JSONArray();
		JSONObject josnResult = new JSONObject();
		Iterator<ItemTheme> iter = list.iterator();
		while (iter.hasNext()) {
			ItemTheme theme = iter.next();
			JSONObject josn = new JSONObject();
			josn.put("itemTypeCode", theme.getItemTypeCode());
			josn.put("itemTypeName", theme.getItemTypeName());
			array.add(josn);
		}
		josnResult.put("total", array.size());
		josnResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
	}

	private String getResponse(Map<String, String> head, String paramString,
			String contentType) {
		String body = "";
		// CloseableHttpClient client = PooledHttpUitl.getCloseableHttp(640,
		// 320);
		// String url =
		// RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		//
		// HttpPost httpPost = new HttpPost(url);
		// for (Map.Entry<String, String> entry : head.entrySet()) {
		// System.out.println(entry.getKey() + "：" + entry.getValue());
		// httpPost.addHeader(entry.getKey() , entry.getValue());
		// }
		// httpPost.setHeader("Content-type", contentType);

		head.put("Content-type", contentType);
		// 设置参数到请求对象中
		StringEntity se = new StringEntity(paramString, "utf-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));

		body = HttpUtil.doPost(head, se);

		// httpPost.setEntity(se);
		//
		// CloseableHttpResponse response = null;
		// try{
		// if(client == null){
		// if(url.startsWith("https://")){
		// client= HttpUtil.getHttpsClient();
		// } else {
		// client = HttpClients.createDefault();
		// }
		// }
		// response = client.execute(httpPost);
		// // 获取结果实体
		// HttpEntity entity = response.getEntity();
		// if (entity != null) {
		// // 按指定编码转换结果实体为String类型
		// body = EntityUtils.toString(entity, "utf-8");
		// }
		// // 释放链接
		// response.close();
		// } catch (Exception e) {
		// Log.debug("访问失败"+e.getMessage());
		// } finally {
		// try {
		// if(response != null){
		// // 释放链接
		// response.close();
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		return body;
	}

	@SuppressWarnings("unused")
	private String getIncrementInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		String stStart = "2000-01-01 00:00:00";
		String stEnd = sdf.format(new Date());
		String appName = "df11b47a-56f2-475d-967a-720bd2c9a74b";

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject data = new JSONObject();
		data.put("stStart", stStart);
		data.put("stEnd", stEnd);

		String contentType = "application/json;charset=utf-8";
		String result = getResponse(head, data.toString(), contentType);
		return result;
	}
}
