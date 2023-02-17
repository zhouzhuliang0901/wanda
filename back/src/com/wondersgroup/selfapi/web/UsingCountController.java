package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.dataitem.item236012130732.bean.RecordBookInfo;
import com.wondersgroup.dataitem.item236012130732.service.RecordBookService;
import com.wondersgroup.selfapi.bean.MachineInfo;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.bean.SelmAttach;
import com.wondersgroup.selfapi.service.SelfUsingHistoryService;

import reindeer.base.utils.AciJsonHelper;
import reindeer.base.utils.RequestWrapper;
import sun.misc.BASE64Decoder;

import wfc.service.config.Config;
import wfc.service.log.Log;

@Controller
public class UsingCountController {
	
	@Autowired
	private SelfUsingHistoryService selfUsingHistoryService;
	
	/**
	 * 保存模块使用记录
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/recordUsingHistory.do")
	public void recordUsingHistory(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String account = req.getParameter("account");
		res.setHeader("Access-Control-Allow-Origin","*");
		if(!"admin".equals(account)){
			res.setStatus(403);
		} else {
			SelfUsingHistory selfUsingHistory = reqToBean(req);
			selfUsingHistory = selfUsingHistoryService.saveOrUpdateSelfUsingHistory(selfUsingHistory);
			if(selfUsingHistory == null){
				AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":\""+false+"\",\"HISTORYID\":\"\"}");
			} else{
				AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":\""+true+"\",\"HISTORYID\":\""+selfUsingHistory.getStHisId()+"\"}");
			}
		}
	}

	/**
	 * 附件上传
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/uploadStuff.do")
	public void uploadStuff(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String fileName = req.getParameter("fileName");
		byte[] byt = null;
		String fileType = req.getParameter("fileType");
		int len = 0;
		
		String file = req.getParameter("file");
		if(StringUtils.isBlank(file)){
			MultipartHttpServletRequest multipartRequest = null;
			if (req instanceof MultipartHttpServletRequest) {
				multipartRequest = (MultipartHttpServletRequest)(req);
				List<MultipartFile> files = multipartRequest.getFiles("file");
				byt = files.get(0).getBytes();
				len = byt.length;
				fileName = files.get(0).getOriginalFilename();
				fileType = files.get(0).getContentType();
			}
		} else {
			fileName = fileName == null ? "附件" : fileName;
			fileType = fileType == null ? "png" : fileType;
			byt = new BASE64Decoder().decodeBuffer(file);
			len = byt.length;
		}
		 
		// 关联主键值
		String linkId = req.getParameter("linkId");
		
		SelmAttach selmAttach = selfUsingHistoryService.uploadStuff(linkId,fileName,fileType,byt,len);
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
	
	/**
	 * 保存办事结果
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/saveApplyResult.do")
	public void saveApplyResult(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 关联主键值
		String linkId = req.getParameter("linkId");
		// 文本内容
		String content = req.getParameter("content");
		if(StringUtils.isNotEmpty(content)){
			content = URLDecoder.decode(content, "utf-8");
		}
		
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
	
	/**
	 * 统计模块使用量
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/countUsingHistory.do")
	public void countUsingHistory(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String start = wrapper.getParameter("start");
		String end = wrapper.getParameter("end");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(start);
			endTime = DateUtils.addDays(sdf.parse(end), 1);
		} catch (ParseException e) {
			Log.debug("时间格式转换异常---"+e.getClass());
		}
		JSONArray arr = selfUsingHistoryService.queryUsingHistory(startTime, endTime);
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	/**
	 * 查询工作台所有历史使用记录
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getAllUsingHistory.do")
	public void getAllUsingHistory(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			if(StringUtils.isEmpty(start)){
				startTime = new Date();
			} else {
				startTime = sdf.parse(start);
			}
			if(StringUtils.isEmpty(end)){
				endTime = new Date();
				endTime = DateUtils.addDays(endTime, 1);
			} else {
				endTime = DateUtils.addDays(sdf.parse(end), 1);
			}
		} catch (ParseException e) {
			Log.debug("时间格式转换异常---"+e.getClass());
		}
		List<SelfUsingHistory> hisList = selfUsingHistoryService.queryUsingHistoryList(startTime, endTime);
		AciJsonHelper.writeJsonPResponse(req, res, JSONArray.fromObject(hisList).toString());
	}
	
	/**
	 * 查询终端所有办件记录（历史使用记录）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getApplyInfo.do")
	public void getApplyInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			if(StringUtils.isEmpty(start)){
				startTime = new Date();
			} else {
				startTime = sdf.parse(start);
			}
			if(StringUtils.isEmpty(end)){
				endTime = new Date();
				endTime = DateUtils.addDays(endTime, 1);
			} else {
				endTime = DateUtils.addDays(sdf.parse(end), 1);
			}
		} catch (ParseException e) {
			Log.debug("时间格式转换异常---"+e.getClass());
		}
		List<SelfUsingHistory> hisList = selfUsingHistoryService.queryUsingHistoryList(startTime, endTime);
		AciJsonHelper.writeJsonPResponse(req, res, JSONArray.fromObject(hisList).toString());
	}
	
	/**
	 * 获取设备相关信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getMachineInfo.do")
	public void getMachineInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String machineMAC = req.getParameter("machineMAC");
		JSONObject result = new JSONObject();
		JSONObject msg = new JSONObject();
		if(StringUtils.isEmpty(machineMAC)){
			result.put("success", false);
			result.put("data", "");
			msg.put("code", "401");
			msg.put("resMsg", "参数 'machineMAC'不能为空！");
			result.put("msg", msg);
		} else {
			try{
				MachineInfo machine = selfUsingHistoryService.getMachineInfo(machineMAC);
				if(machine != null){
					result.put("success", true);
					result.put("data", JSONObject.fromObject(machine).toString());
					msg.put("code", "200");
					msg.put("resMsg", "查询成功！");
					result.put("msg", msg);
				} else {
					result.put("success", false);
					result.put("data", "");
					msg.put("code", "201");
					msg.put("resMsg", "该MAC地址无对应自助终端，请确认MAC地址是否正确！");
					result.put("msg", msg);
				}
			} catch (Exception e) {
				Log.debug(e);
				result.put("success", false);
				result.put("data", "");
				msg.put("code", "500");
				msg.put("resMsg", e.getMessage());
				result.put("msg", msg);
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}
	
	/**
	 * 根据附件ID获取业务结果数据
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getBusinessResultString.do")
	public void getBusinessResultString(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String businessId = wrapper.getParameter("businessId");
		String resultStr = selfUsingHistoryService.getBusinessResultStringById(businessId);
		JSONObject data = new JSONObject();
		data.put("data", "{\"resultStr\":\""+resultStr+"\"}");
		AciJsonHelper.writeJsonPResponse(req, res, data.toString());
	}
	
	/**
	 * 根据附件ID获取附件文件
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getAttchFile.do")
	public void getAttchFile(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String attachId = wrapper.getParameter("attachId");
		byte[] attchFile = selfUsingHistoryService.getAttchFileById(attachId);
		String fileStr = Base64Util.encode(attchFile);
		JSONObject data = new JSONObject();
		data.put("data", "{\"fileStr\":\""+fileStr+"\"}");
		AciJsonHelper.writeJsonPResponse(req, res, data.toString());
	}
	
	/**
	 * 保存健康码出入记录
	 * TODO
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/add.do")
	public void add(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
//		String machineMAC = req.getParameter("machineMAC");
	}
	
	@RequestMapping("/aci/workPlatform/importHisInfoFromRecordBook.do")
	public void importHisInfoFromRecordBook(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		List<RecordBookInfo> bokkList = recordBookService.getAllRecordBookInfo();
		if("1".equals(Config.get("wfc.import.RecordBook"))){
			for(RecordBookInfo book : bokkList){
				SelfUsingHistory info = new SelfUsingHistory();
				info.setStMachineId(getMachineId());
				info.setStModuleName("医保服务");
				info.setStModuleOp("打印");
				info.setStName(book.getName());
				info.setStIdentityNo(book.getIdCard());
				info.setDtCreate(book.getDtCreate());
				info.setStItemName("办理门急诊就医记录册");
				info.setStOpResult("SUCCESS");
				info = selfUsingHistoryService.saveOrUpdateSelfUsingHistory(info);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				JSONObject josn = new JSONObject();
				JSONObject data = new JSONObject();
				data.put("itemName", "办理门急诊就医记录册");
				data.put("transactor", book.getName());
				data.put("bookNo", book.getRecordBookNo());
				data.put("opTime", sdf.format(book.getDtCreate()));
				data.put("opResult", info.getStOpResult());
				josn.put("SUCCESS", "true");
				josn.put("data", data);
				selfUsingHistoryService.saveApplyResult(info.getStHisId(),josn.toString());
			}
			AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":\"true\",\"msg\":\"导入成功\"}");
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, "{\"count\":\""+bokkList.size()+"\"}");
		}
	}
	
	private static String getMachineId() {
		List<String> list = Arrays.asList("linfen01","linfen02");
		int index = new Random().nextInt(list.size());
		return list.get(index);
	}
	
	private SelfUsingHistory reqToBean(HttpServletRequest req) throws UnsupportedEncodingException {
		SelfUsingHistory selfUsingHistory = new SelfUsingHistory();
		selfUsingHistory.setStMachineId(req.getParameter("ST_MACHINE_ID"));
		selfUsingHistory.setStAssistId(req.getParameter("ST_ASSIST_ID"));
		String stModuleName = req.getParameter("ST_MODULE_NAME");
		if(StringUtils.isNotEmpty(stModuleName)){
			stModuleName = URLDecoder.decode(stModuleName, "utf-8");
		}
		selfUsingHistory.setStModuleName(stModuleName);
		String stModuleOp = req.getParameter("ST_MODULE_OP");
		if(StringUtils.isNotEmpty(stModuleOp)){
			stModuleOp = URLDecoder.decode(stModuleOp, "utf-8");
		}
		selfUsingHistory.setStModuleOp(stModuleOp);
		String stItemName = req.getParameter("ST_ITEM_NAME");
		if(StringUtils.isNotEmpty(stItemName)){
			stItemName = URLDecoder.decode(stItemName, "utf-8");
		}
		selfUsingHistory.setStItemName(stItemName);
		selfUsingHistory.setStItemNo(req.getParameter("ST_ITEM_NO"));
		String stName = req.getParameter("ST_NAME");
		if(StringUtils.isNotEmpty(stName)){
			stName = URLDecoder.decode(stName, "utf-8");
//			byte[] bytName = AESUtil.encrypt(stName, RdConfig.get("reindeer.servlet.aes.key"));
//			stName = HexUtil.bytes2Hex(bytName);
		}
		selfUsingHistory.setStName(stName);
		String identityNo = req.getParameter("ST_IDENTITY_NO");
		if(StringUtils.isNotEmpty(identityNo)){
//			byte[] bytIdentityNo = AESUtil.encrypt(identityNo, RdConfig.get("reindeer.servlet.aes.key"));
//			identityNo = HexUtil.bytes2Hex(bytIdentityNo);
		}
		selfUsingHistory.setStIdentityNo(identityNo);
		String mobile = req.getParameter("ST_MOBILE");
		if(StringUtils.isNotEmpty(mobile)){
//			byte[] bytMobile = AESUtil.encrypt(mobile, RdConfig.get("reindeer.servlet.aes.key"));
//			mobile = HexUtil.bytes2Hex(bytMobile);
		}
		selfUsingHistory.setStMobile(mobile);
		selfUsingHistory.setStBusinessNo(req.getParameter("ST_BUSINESS_NO"));
		selfUsingHistory.setStDesc(req.getParameter("ST_DESC"));
		selfUsingHistory.setStOpResult(req.getParameter("ST_OP_RESULT"));
		selfUsingHistory.setStExt1(req.getParameter("ST_EXT1"));
		selfUsingHistory.setStExt2(req.getParameter("ST_EXT2"));
		selfUsingHistory.setStExt3(req.getParameter("ST_EXT3"));
		selfUsingHistory.setStExt4(req.getParameter("ST_EXT4"));
		selfUsingHistory.setStExt5(req.getParameter("ST_EXT5"));
		return selfUsingHistory;
	}

	@Autowired
	private RecordBookService recordBookService;
}
