package com.wondersgroup.dataitem.item209862265922.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.Pdf2pngUtil;
import com.wondersgroup.common.utils.PdfToJpeg;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class EntryAndExitController {
	
	/**
	 * 根据身份证获取该用户的所有出境入境记录
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/exitAndEntry/getExitAndEntryRecord.do")
	public void getExitAndEntryRecord(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		String accessToken = req.getParameter("accessToken");
//		String mobile = wrapper.getParameter("mobile");
//		String userName = wrapper.getParameter("userName");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b972c2b9-0ae8-42e8-abde-4e1cb987adff";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "faae776a-c012-434c-8f42-05600a83363b";
//			appName = "b0610ccb-def1-44c4-b39e-c6426ed8f3d1";
		}
		
		//String accessToken = HttpUtil.getAccessToken(userName, idCard, mobile);
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject paramJson = new JSONObject();
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			paramJson.put("access_token", accessToken);
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			paramJson.put("CERTIFICATENO", idCard);
		}
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,paramJson.toString(),contentType);
		System.out.println("获取该用户的所有出境入境记录结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 出境入境记录申请数据插入
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/exitAndEntry/applyInsertExitAndEntryRecord.do")
	public void applyInsertExitAndEntryRecord(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String STARTDATE = req.getParameter("STARTDATE");// 开始时间
		String ENDDATE = req.getParameter("ENDDATE");// 结束时间
//		String QUERY_ID = wrapper.getParameter("QUERY_ID");
		String USERID = req.getParameter("USERID");
		String idCard = req.getParameter("CERTIFICATENO");
		String USERNAME = req.getParameter("USERNAME");
//		String mobile = wrapper.getParameter("mobile");
		String accessToken = req.getParameter("accessToken");
		if(StringUtils.isNotEmpty(USERNAME)){
			USERNAME = URLDecoder.decode(USERNAME, "utf-8");
		}
		
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7db2f362-ea3f-4dea-b5d6-813a3fa7ce85";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e2ede406-daf6-497e-b96c-cddde4f09d3d";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject paramJson = new JSONObject();
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			paramJson.put("access_token", accessToken);
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			paramJson.put("CERTIFICATENO", idCard);
			paramJson.put("USERNAME", USERNAME);
		}
		paramJson.put("STARTDATE", STARTDATE);
		paramJson.put("ENDDATE", ENDDATE);
		paramJson.put("QUERY_ID", UUID.randomUUID().toString());
		paramJson.put("USERID", USERID);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,paramJson.toString(),contentType);
		System.out.println("出境入境记录申请数据插入结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
		// {"isSucess":true,"msg":"您已申请成功，您的查询结果申请后约两个小时可查看，请耐心等待"}
	}
	
	/**
	 * 出境入境记录制证打印
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/exitAndEntry/printExitAndEntryRecord.do")
	public void printExitAndEntryRecord(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String QUERY_ID = req.getParameter("QUERY_ID");
		String deriveUuid = req.getParameter("deriveUuid");
		String machineId = req.getParameter("machineId");
		String businessCode = req.getParameter("businessCode");//004010320094337
		deriveUuid = deriveUuid.replaceAll("\\+", "-");
		deriveUuid = deriveUuid.replaceAll("=", ",");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "137865de-52d2-4577-a819-d2d6b0671adb";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "4e25ed42-ba8e-434a-897c-2cb2ef9da83b";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("QUERY_ID", QUERY_ID);
		
		String contentType = "application/json;charset=utf-8";
		byte[] byts = HttpUtil.doPostForFile(head,paramJson.toString(),contentType);
		
		String pdfPath = EntryAndExitController.class.getResource("").getPath()+"template/recordPDF.pdf";
		FileUtil.getFileFromBytes(byts, pdfPath);
		String pngPath = EntryAndExitController.class.getResource("").getPath()+"template/recordPNG";
		List<String> list = Pdf2pngUtil.pdf2png(pdfPath, pngPath, "png");
		JSONArray arrResult = new JSONArray();
		for(String path : list){
			JSONObject json = new JSONObject();
			byte[] pngByte = PdfToJpeg.image2byte(path);
			String pngStr = Base64Util.encode(pngByte);
			json.put("png", pngStr);
			arrResult.add(json);
		}
		JSONObject obj = new JSONObject();
		obj.put("pngArr", arrResult);
		obj.put("pdfUrl", "/selfapi/dzzz/showStuffPicForBytes.do?deriveUuid="
				+ deriveUuid+"&sessionId=&machineId="+machineId
				+"&itemName="+URLEncoder.encode("出入境记录证明打印", "UTF-8")+"&itemCode=&businessCode="+businessCode);
		obj.put("base64Url", "/selfapi/dzzz/showStuffPicForBase64.do?deriveUuid="
				+deriveUuid+"&sessionId=&machineId="+machineId
				+"&itemName="+URLEncoder.encode("出入境记录证明打印", "UTF-8")+"&itemCode=&businessCode="+businessCode);
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
}
