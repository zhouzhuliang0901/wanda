package com.wondersgroup.dataitem.item255522146200.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item255522146200.utils.WithdrawalOfProvidentFundUtil;
import com.wondersgroup.dataitem.item382711997735.utils.CSJutil;
import com.wondersgroup.dataitem.item382711997735.utils.LoginUtil;

/**
 * 长三角事项-公积金提取
 * 
 * @author wanda
 * 
 */
@Controller
public class WithdrawalOfProvidentFundController {

	/**
	 * (一码双通道)生成二维码
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/creatCode.do")
	public void creatCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String idCard = req.getParameter("idCard");
		String name = req.getParameter("name");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}

		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "0937cbc0-46d6-11ec-8875-1b026ef80755";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "9c15e4c0-46dd-11ec-92db-0f489cca772b";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = LoginUtil.getAuthorization();
		head.put("UniTrust-AppId", "85c783ba82aa4b5b9c6ed70f7d5c3e67");// product
		// head.put("UniTrust-AppId", "e56b243185c34b40b731bf4210597a4a"); //
		// test
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("name", name);
		param.put("idNo", idCard);
		param.put("transactionId", UUID.randomUUID().toString());
		param.put("idNoType", "1");
		param.put("faceMode", "2");
		String postResult = HttpUtil.doPost(head, param.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}

	/**
	 * (一码双通道)获取二维码扫描人脸识别结果
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/getCodeResult.do")
	public void getCodeResult(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String qrCodeId = req.getParameter("qrCodeId");

		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "bf542070-46d6-11ec-8875-1b026ef80755";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "c21685b0-46df-11ec-92db-0f489cca772b";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = LoginUtil.getAuthorization();
		head.put("UniTrust-AppId", "85c783ba82aa4b5b9c6ed70f7d5c3e67");// product
		// head.put("UniTrust-AppId", "e56b243185c34b40b731bf4210597a4a"); //
		// test
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("qrCodeId", qrCodeId);
		
		// 轮询获取扫码结果
		int count = 0;
		String postResult = "";
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			postResult = HttpUtil.doPost(head, param.toString(),
					"application/json;charset=utf-8");
			int status = 0;
			boolean b = false;
			try{
				JSONObject json = JSONObject.fromObject(postResult);
				status = json.optInt("status");
				b = json.optJSONObject("result").optBoolean("authResult");
			} catch (Exception e) {
				Log.debug(e.getMessage());
			}
			
			count++;
			if (count >= 3) {
				// 轮询3次超时处理
				System.out.println("获取扫码结果超时！");
				break;
			} else {
				if(200 == status && b){
					break;
				}
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}

	/**
	 * 申请人信息检验（婚姻校验、户籍校验）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/checkInfo.do")
	public void checkInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		String marriageStatue = req.getParameter("marriageStatue");
		String hj = req.getParameter("hj");
		String spouseName = req.getParameter("spouseName");
		String spouseIdCard = req.getParameter("spouseIdCard");
		String spouseHj = req.getParameter("spouseHj");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		if (StringUtils.isNotEmpty(hj)) {
			hj = URLDecoder.decode(hj, "utf-8");
		}
		if (StringUtils.isNotEmpty(spouseName)) {
			spouseName = URLDecoder.decode(spouseName, "utf-8");
		}
		if (StringUtils.isNotEmpty(spouseHj)) {
			spouseHj = URLDecoder.decode(spouseHj, "utf-8");
		}
		name = name==null?"":name;
		spouseName = spouseName==null?"":spouseName;
		String applyId = UUID.randomUUID().toString();
		// 婚姻校验
		String hyAppName = "676627f0-ae69-11ec-890f-9130cf5ba4d4";
		String hySignature = HttpUtil.getSignature(hyAppName);
		Map<String, String> hyHead = HttpUtil.setHttpHeard(hySignature,
				hyAppName);
		String hyUrl = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?applyId="
				+ applyId
				+ "&name="+URLEncoder.encode(URLEncoder.encode(name, "utf-8"), "utf-8")
				+ "&card_no="
				+ idCard
				+ "&marriage_statue="
				+ marriageStatue
				+ "&spouse_name="+URLEncoder.encode(URLEncoder.encode(spouseName, "utf-8"), "utf-8")
				+ "&spouse_card_no=" + spouseIdCard;
		String hyResult = WithdrawalOfProvidentFundUtil.send(hyHead, hyUrl);
		System.out.println("婚姻校验结果："+hyResult);
		// 户籍校验
		String hjAppName = "26098fd0-ae6a-11ec-890f-9130cf5ba4d4";
		String hjSignature = HttpUtil.getSignature(hjAppName);
		Map<String, String> hjHead = HttpUtil.setHttpHeard(hjSignature,
				hjAppName);
		String hjUrl = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?applyId="
				+ applyId
				+ "&name="+URLEncoder.encode(URLEncoder.encode(name, "utf-8"), "utf-8")
				+ "&card_no="
				+ idCard
				+ "&hj="+hj
				+ "&marriage_statue="
				+ marriageStatue
				+ "&spouse_name="+URLEncoder.encode(URLEncoder.encode(spouseName, "utf-8"), "utf-8")
				+ "&spouse_card_no=" + spouseIdCard
				+ "&spouse_hj="+spouseHj;
		String hjResult = WithdrawalOfProvidentFundUtil.send(hjHead, hjUrl);
		System.out.println("户籍校验结果:"+hjResult);
		
		JSONObject result = new JSONObject();
		JSONObject hyResultJson = JSONObject.fromObject(hyResult);
		JSONObject hjResultJson = JSONObject.fromObject(hjResult);
		result.put("applyId", applyId);
		result.put("hyResult", hyResultJson);
		result.put("hjResult", hjResultJson);
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}

	/**
	 * 提取申请人信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/submitApplicantsAndHousingInfo.do")
	public void submitApplicantsAndHousingInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String applyId = req.getParameter("applyId");
		String personList = req.getParameter("personList");
		String houseInfo = req.getParameter("houseInfo");
		JSONArray personListJson = new JSONArray();
		JSONObject houseInfoJson = new JSONObject();
		System.out.println("personList："+personList);
		System.out.println("houseInfo："+houseInfo);
		if (StringUtils.isNotEmpty(personList)) {
			personList = URLDecoder.decode(personList, "utf-8");
			personListJson = JSONArray.fromObject(personList);
		}
		if (StringUtils.isNotEmpty(houseInfo)) {
			houseInfo = URLDecoder.decode(houseInfo, "utf-8");
			houseInfoJson = JSONObject.fromObject(houseInfo);
		}
		String appName = "da5a41b0-ae69-11ec-890f-9130cf5ba4d4";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject param = new JSONObject();
		param.put("applyId", applyId);
		param.put("personList", personListJson);
		param.put("houseInfo", houseInfoJson);
		System.out.println("提取申请人信息参数："+param.toString());
		String postResult = HttpUtil.doPost(head, param.toString(),
				"application/json;charset=utf-8");
		try {
			JSONObject resultJson = JSONObject.fromObject(postResult);
			resultJson.put("applyId", applyId);
			postResult = resultJson.toString();
		} catch (Exception e) {
			Log.debug(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}

	/**
	 * 获取购房核查项purchaseVerificationItems（获取核查项前先校验人员信息）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/getPurchaseVerificationItems.do")
	public void getPurchaseVerificationItems(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String applyId = req.getParameter("applyId");
		// 已婚：10；未婚：20
		String idMarried = req.getParameter("idMarried");
		String hj = req.getParameter("hj");

		// 缴存地校验人员信息
		String jyAppName = "45a4fe10-ae6a-11ec-890f-9130cf5ba4d4";
		String jySignature = HttpUtil.getSignature(jyAppName);
		Map<String, String> jyHead = HttpUtil.setHttpHeard(jySignature,
				jyAppName);
		String jyCode = "";
		// 本人校验
//		JSONObject brjyParam = new JSONObject();
//		brjyParam.put("applyId", applyId);
//		brjyParam.put("relationship", "1");
//		String brjyResult = HttpUtil.doPost(jyHead, brjyParam.toString(),
//				"application/json;charset=utf-8");
		String brjyUrl = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId+"&relationship=1";
		String brjyResult = WithdrawalOfProvidentFundUtil.send(jyHead, brjyUrl);
		String brjyCode = "";
		try {
			JSONObject brjyResultJson = JSONObject.fromObject(brjyResult);
			brjyCode = brjyResultJson.optString("code");
		} catch (Exception e) {
			brjyCode = "101";
		}
		// 判断是否需要校验配偶
		String pojyCode = "";
		if("10".equals(idMarried) && !"其他".equals(hj)){
//			JSONObject pojyParam = new JSONObject();
//			pojyParam.put("applyId", applyId);
//			pojyParam.put("relationship", "2");
//			String pojyResult = HttpUtil.doPost(jyHead, pojyParam.toString(),
//					"application/json;charset=utf-8");
			String pojyUrl = RdConfig.get("reindeer.huidao.url."
					+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId+"&relationship=2";
			String pojyResult = WithdrawalOfProvidentFundUtil.send(jyHead, pojyUrl);
			try {
				JSONObject brjyResultJson = JSONObject.fromObject(pojyResult);
				pojyCode = brjyResultJson.optString("code");
			} catch (Exception e) {
				pojyCode = "101";
			}
			if("101".equals(pojyCode) || "101".equals(brjyCode)){
				jyCode = "101";
			} else if("200".equals(pojyCode) && "200".equals(brjyCode)){
				jyCode = "200";
			} else {
				jyCode = "0";
			}
		} else {
			jyCode = brjyCode;
		}
		JSONObject json = new JSONObject();
		if ("200".equals(jyCode)) {
			// 获取购房核查项
			String hcxAppName = "";
			if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
				hcxAppName = "8195f2f0-c074-11ec-890f-9130cf5ba4d4";
			} else if ("product"
					.equals(RdConfig.get("reindeer.huidao.environment"))) {
				hcxAppName = "45a4fe10-ae6a-11ec-890f-9130cf5ba4d4";
			}
			String hcxSignature = HttpUtil.getSignature(hcxAppName);
			Map<String, String> hcxHead = HttpUtil.setHttpHeard(hcxSignature,
					hcxAppName);
//			JSONObject hcxParam = new JSONObject();
//			hcxParam.put("applyId", applyId);
//			String hcxResult = HttpUtil.doPost(hcxHead, hcxParam.toString(),
//					"application/json;charset=utf-8");
			String hcxUrl = RdConfig.get("reindeer.huidao.url."
					+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId;
			String hcxResult = WithdrawalOfProvidentFundUtil.send(hcxHead, hcxUrl);
			try {
				JSONObject hcxJson = JSONObject.fromObject(hcxResult);
				json.put("code", jyCode);
				json.put("applyId", applyId);
				json.put("msg", "SUCCESS");
				json.put("purchaseVerificationItems",
						hcxJson.optString("purchaseVerificationItems"));
			} catch (Exception e) {
				json.put("code", "201");
				json.put("applyId", applyId);
				json.put("msg", "获取购房核查项接口异常！");
				json.put("purchaseVerificationItems", "");
			}
		} else if ("101".equals(jyCode)) {
			json.put("code", jyCode);
			json.put("applyId", applyId);
			json.put("msg", "缴存地人员信息校验接口异常！");
			json.put("purchaseVerificationItems", "");
		} else {
			json.put("code", "0");
			json.put("applyId", applyId);
			json.put("msg", "缴存地人员信息校验不通过！");
			json.put("purchaseVerificationItems", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}

	/**
	 * 核验房屋信息并获取房屋信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/checkHouseInfo.do")
	public void checkHouseInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String applyId = req.getParameter("applyId");
		String purchaseVerificationItems = req
				.getParameter("purchaseVerificationItems");
		String propertyCertificateNum = req
				.getParameter("propertyCertificateNum");
		String contractNum = req.getParameter("contractNum");
		if (StringUtils.isNotEmpty(propertyCertificateNum)) {
			propertyCertificateNum = URLDecoder.decode(propertyCertificateNum,
					"utf-8");
		}
		// 核验房屋信息
		String hyAppName = "59861a90-ae6a-11ec-890f-9130cf5ba4d4";
		String hySignature = HttpUtil.getSignature(hyAppName);
		Map<String, String> hyHead = HttpUtil.setHttpHeard(hySignature,
				hyAppName);
//		JSONObject hyParam = new JSONObject();
//		hyParam.put("applyId", applyId);
//		hyParam.put("purchaseVerificationItems", purchaseVerificationItems);
//		hyParam.put("propertyCertificateNum", propertyCertificateNum);
//		hyParam.put("contractNum", contractNum);
//		String hyResult = HttpUtil.doPost(hyHead, hyParam.toString(),
//				"application/json;charset=utf-8");
		
		String hyUrl = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId
				+ "&purchaseVerificationItems="+purchaseVerificationItems
				+ "&propertyCertificateNum="+propertyCertificateNum
				+ "&contractNum="+contractNum;
		String hyResult = WithdrawalOfProvidentFundUtil.send(hyHead, hyUrl);
		String hyCode = "";
		JSONObject json = new JSONObject();
		try {
			JSONObject hyResultJson = JSONObject.fromObject(hyResult);
			hyCode = hyResultJson.optString("code");
		} catch (Exception e) {
			hyCode = "101";
		}
		if ("200".equals(hyCode)) {
			// 获取房屋信息
			String fwAppName = "31f036d0-ae6c-11ec-890f-9130cf5ba4d4";
			String fwSignature = HttpUtil.getSignature(fwAppName);
			Map<String, String> fwHead = HttpUtil.setHttpHeard(fwSignature,
					fwAppName);
//			JSONObject fwParam = new JSONObject();
//			fwParam.put("applyId", applyId);
//			fwParam.put("propertyCertificateNum", propertyCertificateNum);
//			fwParam.put("contractNum", contractNum);
//			String fwResult = HttpUtil.doPost(fwHead, fwParam.toString(),
//					"application/json;charset=utf-8");
			String fwUrl = RdConfig.get("reindeer.huidao.url."
					+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId
					+ "&propertyCertificateNum="+propertyCertificateNum
					+ "&contractNum="+contractNum;
			String fwResult = WithdrawalOfProvidentFundUtil.send(fwHead, fwUrl);
			try {
				JSONObject fwJson = JSONObject.fromObject(fwResult);
				System.out.println("房屋信息"+fwJson.toString());
				json.put("code", hyCode);
				json.put("applyId", applyId);
				json.put("msg", "SUCCESS");
				json.put("contractInfo", fwJson.optJSONObject("data").optJSONObject("contractInfo"));
				json.put("certifyCateInfo",
						fwJson.optJSONObject("data").optJSONObject("certifyCateInfo"));
			} catch (Exception e) {
				json.put("code", "201");
				json.put("applyId", applyId);
				json.put("msg", "获取房屋信息接口异常！");
			}
		} else if ("101".equals(hyCode)) {
			json.put("code", hyCode);
			json.put("applyId", applyId);
			json.put("msg", "房屋信息核验接口异常！");
		} else {
			json.put("code", "0");
			json.put("applyId", applyId);
			json.put("msg", "房屋信息核验不通过！");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}

	/**
	 * 获取银行卡信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/getBankInfo.do")
	public void getBankInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String applyId = req.getParameter("applyId");
		String appName = "57172b80-ae6c-11ec-890f-9130cf5ba4d4";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		JSONObject param = new JSONObject();
//		param.put("applyId", applyId);
//		String postResult = HttpUtil.doPost(head, param.toString(),
//				"application/json;charset=utf-8");
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId;
		String result = WithdrawalOfProvidentFundUtil.send(head, url);
		try {
			JSONObject resultJson = JSONObject.fromObject(result);
			resultJson.put("applyId", applyId);
			result = resultJson.toString();
		} catch (Exception e) {
			Log.debug(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 获取提取金额信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/showExtractBalanceData.do")
	public void showExtractBalanceData(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String applyId = req.getParameter("applyId");
		String accountInfo = req.getParameter("accountInfo");
		if (StringUtils.isNotEmpty(accountInfo)) {
			accountInfo = URLDecoder.decode(accountInfo, "utf-8");
		}

		// 缴存地核验信息反馈提取金额
		String hyAppName = "7c146d90-ae6b-11ec-890f-9130cf5ba4d4";
		String hySignature = HttpUtil.getSignature(hyAppName);
		Map<String, String> hyHead = HttpUtil.setHttpHeard(hySignature,
				hyAppName);
//		JSONObject hyParam = new JSONObject();
//		hyParam.put("applyId", applyId);
//		hyParam.put("accountInfo", accountInfo);
//		String hyResult = HttpUtil.doPost(hyHead, hyParam.toString(),
//				"application/json;charset=utf-8");
		String hyUrl = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId
				+ "&accountInfo="+accountInfo;
		String hyResult = WithdrawalOfProvidentFundUtil.send(hyHead, hyUrl);
		String hyCode = "";
		try {
			JSONObject hyResultJson = JSONObject.fromObject(hyResult);
			hyCode = hyResultJson.optString("code");
		} catch (Exception e) {
			hyCode = "101";
		}
		JSONObject json = new JSONObject();
		if ("200".equals(hyCode)) {
			// 获取提取金额信息
			String tqjeAppName = "6e3e9960-ae6c-11ec-890f-9130cf5ba4d4";
			String tqjeSignature = HttpUtil.getSignature(tqjeAppName);
			Map<String, String> tqjeHead = HttpUtil.setHttpHeard(tqjeSignature,
					tqjeAppName);
//			JSONObject tqjeParam = new JSONObject();
//			tqjeParam.put("applyId", applyId);
//			String tqjeResult = HttpUtil.doPost(tqjeHead, tqjeParam.toString(),
//					"application/json;charset=utf-8");
			String tqjeUrl = RdConfig.get("reindeer.huidao.url."
					+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId;
			String tqjeResult = WithdrawalOfProvidentFundUtil.send(tqjeHead, tqjeUrl);
			try {
				JSONObject tqieJson = JSONObject.fromObject(tqjeResult);
				json.put("code", hyCode);
				json.put("applyId", applyId);
				json.put("msg", "SUCCESS");
				json.put("extractUpperLimit",
						tqieJson.optInt("extractUpperLimit"));
				json.put("accountList",
						tqieJson.optJSONArray("accountList"));
			} catch (Exception e) {
				json.put("code", "201");
				json.put("applyId", applyId);
				json.put("msg", "获取提取金额信息接口异常！");
			}
		} else if ("101".equals(hyCode)) {
			json.put("code", hyCode);
			json.put("applyId", applyId);
			json.put("msg", "缴存地核验信息反馈提取金额接口异常！");
		} else {
			json.put("code", "0");
			json.put("applyId", applyId);
			json.put("msg", "缴存地核验信息反馈提取金额不通过！");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}

	/**
	 * 缴存地接收全部信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/submitAllInfo.do")
	public void submitAllInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String applyId = req.getParameter("applyId");
		String extractAccountInfo = req.getParameter("extractAccountInfo");
		String providentFundAccountInfo = req.getParameter("providentFundAccountInfo");
		if (StringUtils.isNotEmpty(extractAccountInfo)) {
			extractAccountInfo = URLDecoder.decode(extractAccountInfo, "utf-8");
		}
		String applyNo = CSJutil.getCSJApplyNo("CSJ312002583000");
		String appName = "11ad16e0-ae6c-11ec-890f-9130cf5ba4d4";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		JSONObject param = new JSONObject();
//		param.put("applyId", applyId);
//		param.put("applyNo", applyNo);
//		param.put("extractAccountInfo",
//				JSONArray.fromObject(extractAccountInfo));
//		param.put("providentFundAccountInfo",
//				JSONArray.fromObject(providentFundAccountInfo));
//		String postResult = HttpUtil.doPost(head, param.toString(),
//				"application/json;charset=utf-8");
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))+"?applyId="+applyId
				+ "&applyNo="+applyNo
				+ "&extractAccountInfo="+JSONArray.fromObject(extractAccountInfo).toString()
				+ "&providentFundAccountInfo="+JSONArray.fromObject(providentFundAccountInfo).toString();
		String result = WithdrawalOfProvidentFundUtil.send(head, url);
		try {
			JSONObject resultJson = JSONObject.fromObject(result);
			resultJson.put("applyId", applyId);
			result = resultJson.toString();
		} catch (Exception e) {
			Log.debug(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 获取省市区字典
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/withdrawalOfProvidentFund/dictionary.do")
	public void dictionary(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 上海市(310000);江苏省(320000);浙江省(330000);安徽省(340000)
		String code = req.getParameter("code");
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "9631f290-c146-11ec-890f-9130cf5ba4d4";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "320d1640-c147-11ec-8d2d-c77fd338b4a7";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("rootCode", "area");
		paramMap.put("rootCode", "csjssqxzqh");
		paramMap.put("code", code);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
}
