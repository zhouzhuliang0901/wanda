package com.wondersgroup.dataitem.item382711997735.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item382711997735.utils.CSJutil;
import com.wondersgroup.dataitem.item382711997735.utils.DifLoanUtil;
import com.wondersgroup.dataitem.item382711997735.utils.HouseholdRegisterUtil;
import com.wondersgroup.dataitem.item382711997735.utils.LoginUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 长三角事项
 * 
 * @author wanda
 * 
 */
@Controller
public class ChangjiangDeltaController {

	/**
	 * 长三角事项详情及跳转地址
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/queryItemDetail.do")
	public void queryItemDetail(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String itemName = req.getParameter("itemName");

		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "2a6626ca-24d5-445a-a492-67b028939464";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "b0ef0091-a2f3-453f-8788-ff33af0a5d42";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String itemId = "";
		if ("domesticCompanies".equals(itemName)) {
			itemId = "da63a02b-d4eb-4811-93b0-778b824fd46c";
		} else if ("marriageRegistration".equals(itemName)) {
			itemId = "fe927af7-652b-4337-9c46-44043c1f255d";
		} else if ("medicalInDifferentPlaces".equals(itemName)) {
			itemId = "d8d116de-e633-4c20-8490-aced4b1758ec";
		}
		JSONObject json = new JSONObject();
		json.put("stId", itemId);
		String result = HttpUtil.doPost(head, json.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 办件库对接-保存接口
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String accessToken = CSJutil.getToken();
		String applyNo = req.getParameter("applyNo");
		String itemCode = req.getParameter("itemCode");
		String taskHandleItem = req.getParameter("taskHandleItem");
		String itemName = req.getParameter("itemName");
		if (StringUtils.isNotEmpty(itemName)) {
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		// 办理对象类型，取值：个人和企业
		String targetType = req.getParameter("targetType");
		if (StringUtils.isNotEmpty(targetType)) {
			targetType = URLDecoder.decode(targetType, "utf-8");
		}
		String targetName = req.getParameter("targetName");
		if (StringUtils.isNotEmpty(targetName)) {
			targetName = URLDecoder.decode(targetName, "utf-8");
		}
		String targetNo = req.getParameter("targetNo");
		String tokenSNO = req.getParameter("tokenSNO");
		String userId = CSJutil.getUserId(tokenSNO);
		String username = req.getParameter("username");
		if (StringUtils.isNotEmpty(username)) {
			username = URLDecoder.decode(username, "utf-8");
		}
		// 申请人证件类型
		String licenseType = req.getParameter("licenseType");
		String licenseNo = req.getParameter("licenseNo");
		String mobile = req.getParameter("mobile");
		// 办件来源，6：万达自助机
		// 7：卓凡自助机8：建行自助机9：新典自助机10：金赋自助机11：东方通12：iptv（智能电视）13：iptv（有线电视）
		String source = req.getParameter("source");
		// 办理项类型：新办、变更、延续、补正、注销、转让、撤销、其它
		String submitType = req.getParameter("submitType");
		if (StringUtils.isNotEmpty(submitType)) {
			submitType = URLDecoder.decode(submitType, "utf-8");
		}
		String info = req.getParameter("info");
		if (StringUtils.isNotEmpty(info)) {
			info = URLDecoder.decode(info, "utf-8");
		}
		String districtCode = req.getParameter("districtCode");

		String appName = "dd1e8bf0-a5d0-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		// JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("accessToken", accessToken);
		data.put("applyNo", applyNo);
		data.put("itemCode", itemCode);
		data.put("taskHandleItem", taskHandleItem);
		data.put("itemName", itemName);
		data.put("targetType", targetType);
		data.put("targetName", targetName);
		data.put("targetNo", targetNo);
		data.put("userId", userId);
		data.put("username", username);
		data.put("licenseType", licenseType);
		data.put("licenseNo", licenseNo);
		data.put("mobile", mobile);
		data.put("departCode", "310000000");
		data.put("departName", "上海市");
		data.put("source", source);
		data.put("opTime", sdf.format(new Date()));
		data.put("submitType", submitType);
		data.put("info", info);
		data.put("districtCode", districtCode);
		// json.put("data", data);
		System.out.println("通用保存参数：" + data.toString());
		String result = HttpUtil.doPost(head, data.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 办件库对接-提交接口
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/submit.do")
	public void submit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String applyNo = req.getParameter("applyNo");
		String accessToken = CSJutil.getToken();
		String appName = "dd1bccd0-a5d0-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		// JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("accessToken", accessToken);
		data.put("applyNo", applyNo);
		data.put("uapplyNo", "");
		data.put("subItemInfo", "");
		data.put("opTime", sdf.format(new Date()));
		// json.put("data", data);
		String result = HttpUtil.doPost(head, data.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 通用上传材料接口
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/uploadItemStuffs.do")
	public void uploadItemStuffs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String applyNo = req.getParameter("applyNo");
		String stuffCode = req.getParameter("stuffCode");
		String stuffId = req.getParameter("stuffId");
		byte[] bytes = null;
		MultipartFile file = null;
		String FileData = req.getParameter("FileData");
		if (StringUtils.isBlank(FileData)) {
			MultipartHttpServletRequest multipartRequest = null;
			if (req instanceof MultipartHttpServletRequest) {
				multipartRequest = ((MultipartHttpServletRequest) req);
			}
			List<MultipartFile> files = multipartRequest.getFiles("FileData");
			file = files.get(0);
			bytes = file.getBytes();
			System.out.println("长三角通用上传材料接口-----------"
					+ file.getOriginalFilename() + "--"
					+ files.get(0).getContentType());
		} else {
			if (FileData != null) {
				try {
					sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
					bytes = decoder.decodeBuffer(FileData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String appName = "3df81920-a665-11ec-8d2d-c77fd338b4a7";
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
	 * 个人申请出具异地贷款缴存使用证明-是否具有开证明条件接口
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/canOpenDifLoanProof.do")
	public void canOpenDifLoanProof(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		String appName = "2857aa50-a8df-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("xingMing", name);
		json.put("zjhm", idCard);
		json.put("zjlx", "111");
		String result = HttpUtil.doPost(head, json.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 个人申请出具异地贷款缴存使用证明-公积金申请接口
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/applyDifPlaceLoan.do")
	public void applyDifPlaceLoan(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		// 贷款地中心名称
		String dkdzxmc = req.getParameter("dkdzxmc");
		// 贷款地省份
		String dkdsf = req.getParameter("dkdsf");
		// 贷款地城市
		String dkdcs = req.getParameter("dkdcs");
		// 缴存地省份
		String jcdsf = req.getParameter("jcdsf");
		// 缴存地城市
		String jcdcs = req.getParameter("jcdcs");
		// 缴存地中心名称
		String jcdzxmc = req.getParameter("jcdzxmc");
		String dkdzxdm = "";
		String jcdzxdm = "";
		String ywbh = CSJutil.getCSJApplyNo("312050534000");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		if (StringUtils.isNotEmpty(dkdzxmc)) {
			dkdzxmc = URLDecoder.decode(dkdzxmc, "utf-8");
			dkdzxdm = DifLoanUtil.getZxdm(dkdzxmc);
		}
		if (StringUtils.isNotEmpty(dkdsf)) {
			dkdsf = URLDecoder.decode(dkdsf, "utf-8");
		}
		if (StringUtils.isNotEmpty(dkdcs)) {
			dkdcs = URLDecoder.decode(dkdcs, "utf-8");
		}
		if (StringUtils.isNotEmpty(jcdsf)) {
			jcdsf = URLDecoder.decode(jcdsf, "utf-8");
		}
		if (StringUtils.isNotEmpty(jcdcs)) {
			jcdcs = URLDecoder.decode(jcdcs, "utf-8");
		}
		if (StringUtils.isNotEmpty(jcdzxmc)) {
			jcdzxmc = URLDecoder.decode(jcdzxmc, "utf-8");
			jcdzxdm = DifLoanUtil.getZxdm(jcdzxmc);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat passApplySdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String appName = "65943d70-a8df-11ec-8d2d-c77fd338b4a7";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("zjlx", "111");
		json.put("zjhm", idCard);
		json.put("xingMing", name);
		json.put("ywbh", "CSJ" + ywbh);
		json.put("dkdzxmc", dkdzxmc);
		json.put("dkdzxdm", dkdzxdm);
		json.put("sqsj", sdf.format(new Date()));
		json.put("dkdsf", dkdsf);
		json.put("dkdcs", dkdcs);
		json.put("jcdsf", jcdsf);
		json.put("jcdzxmc", jcdzxmc);
		json.put("jcdzxdm", jcdzxdm);
		json.put("jcdcs", jcdcs);
		json.put("departCode", "310000000");
		json.put("departName", "上海市");
		String result = HttpUtil.doPost(head, json.toString(),
				"application/json;charset=utf-8");
		JSONObject applyResult = JSONObject.fromObject(result);
		String resultCode = applyResult.optString("resultCode");
		if ("200".equals(resultCode)) {
			JSONObject paramJson = new JSONObject();
			String accessToken = CSJutil.getToken();
			paramJson.put("accessToken", accessToken);
			paramJson.put("applyNo", "CSJ" + ywbh);
			paramJson.put("stsjfs", "网上收件");
			paramJson.put("result", "通过");
			paramJson.put("suggestion", "预审通过");
			paramJson.put("opDepartCode", jcdzxdm);
			paramJson.put("opDepartName", jcdzxmc);
			paramJson.put("opUserId", "");
			paramJson.put("opUsername", name);
			paramJson.put("opTime", passApplySdf.format(new Date()));
			CSJutil.passApply(paramJson.toString());
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 长三角户籍证明字典项
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/getDinctionary.do")
	public void getDinctionary(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String rootCode = req.getParameter("rootCode");
		String parentCode = req.getParameter("parentCode");
		String dictionarys = HouseholdRegisterUtil.getDictionary(rootCode, "2");
		JSONArray dictionaryArr = JSONObject.fromObject(dictionarys)
				.optJSONArray("data");
		if (StringUtils.isEmpty(parentCode)) {
			JSONArray arr = HouseholdRegisterUtil.getData(dictionaryArr);
			AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
		} else {
			JSONArray arr = HouseholdRegisterUtil.getData(dictionaryArr);
			JSONArray iterateArr = HouseholdRegisterUtil.iterateJsonArr(
					dictionaryArr, arr, new JSONArray());
			JSONArray resultArr = new JSONArray();
			for (int i = 0; i < iterateArr.size(); i++) {
				JSONObject json = iterateArr.optJSONObject(i);
				String code = json.optString("parentCode");
				if (code.equals(parentCode)) {
					resultArr = json.optJSONArray("childArr");
				}
			}
			AciJsonHelper.writeJsonPResponse(req, res, resultArr.toString());
		}
	}

	/**
	 * 长三角身份证登录（CA人脸两照比对）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/loginByIdCardForCSJ.do")
	public void loginByIdCardForCSJ(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 人像照片
		String personImg = req.getParameter("personImg");
		// 比对照片
		String otherImg = req.getParameter("otherImg");
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "6550d750-0ae8-11ec-9ca6-ff6b2def5f54";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "0fc205f0-104e-11ec-bbfc-cb132a2116fd";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = LoginUtil.getAuthorization();
		head.put("UniTrust-AppId", "85c783ba82aa4b5b9c6ed70f7d5c3e67");// product
//		head.put("UniTrust-AppId", "e56b243185c34b40b731bf4210597a4a"); // test
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("personImg", personImg);
		param.put("otherImg", otherImg);
		String postResult = HttpUtil.doPost(head, param.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}

	/**
	 * 长三角二维码登录
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/changjiangDelta/loginByQrCodeForCSJ.do")
	public void loginByQrCodeForCSJ(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String qrCode = req.getParameter("qrCode");
		if (StringUtils.isNotEmpty(qrCode)) {
			qrCode = URLDecoder.decode(qrCode, "utf-8");
		}
		String str = LoginUtil.loginByCode(qrCode);
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
