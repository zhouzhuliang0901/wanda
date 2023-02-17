package com.wondersgroup.selfapi.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.util.TextUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.founder.api.FZTokenApi;
import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.CompressedImgUtil;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.HuidaoUtil;
import com.wondersgroup.common.utils.MessyCodeUtil;
import com.wondersgroup.common.utils.ReadImgUtil;
import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.dataitem.forward.web.dao.SelmItemDao;
import com.wondersgroup.selfapi.dao.SelfUsingHistoryDao;
import com.wondersgroup.selfapi.util.AESUtil;
import com.wondersgroup.selfapi.util.ConvertAudioUtil;
import com.wondersgroup.selfapi.util.xfyunUtil.APIUtil;
import com.wondersgroup.selfapi.util.xfyunUtil.ApiResultDto;
import com.wondersgroup.selfapi.util.xfyunUtil.SliceIdGenerator;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

@Controller
public class WorkPlatformController {
	
	/*********************************
	 * 个人登录
	 *********************************/
	/**
	 * 两照对比获取tokenSNO
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getTokenSNO.do")
	public void getTokenSNO(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		long a = System.currentTimeMillis();
		MultipartHttpServletRequest multipartRequest = null;
		if (req instanceof MultipartHttpServletRequest) {
			multipartRequest = (MultipartHttpServletRequest) (req);
		}
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		String certStartTime = req.getParameter("certStartTime");
		String certEndTime = req.getParameter("certEndTime");
		if(StringUtils.isNotEmpty(certEndTime)){
			certEndTime = URLDecoder.decode(certEndTime, "utf-8");
		}
		if("长期".equals(certEndTime)){
			certEndTime = "长期有效";
		}
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		String machineId = req.getParameter("machineId");
		System.out.println("两照对比设备："+machineId+"----比对姓名："+name+"----比对证件号："+idCard);
		if(MessyCodeUtil.isMessyCode(name) ){// || MessyCodeUtil.isLessUseWord(name)
			throw new RuntimeException("Param 'name' is messy code!");
		}
		
		byte[] faceFileItem = null;
		byte[] CopyIDFileItem = null;

		String facePhoto = req.getParameter("facePhoto");
		String CopyIDPhoto = req.getParameter("copyIDPhoto");
		String oldImgPath = WorkPlatformController.class.getResource("")
				.getPath();
		oldImgPath = oldImgPath + "/template/oldImg"+idCard+".jpg";
		String newImgPath = WorkPlatformController.class.getResource("")
				.getPath() + "/template/newImg"+idCard+".jpg";
		if (StringUtils.isBlank(facePhoto)) {
			System.out.println("传的文件的二进制流");
			List<MultipartFile> files = multipartRequest.getFiles("facePhoto");
			faceFileItem = files.get(0).getBytes();
			long fileSize = files.get(0).getSize();
			System.out.println("人脸照片文件大小：" + files.get(0).getSize());
			if (faceFileItem != null && fileSize < 102400) {
				System.out.println("-----------拍照图片："
						+ files.get(0).getOriginalFilename() + "--"
						+ files.get(0).getContentType());
				facePhoto = Base64Util.encode(faceFileItem);
			} else {
				// 大于100K压缩图片
				FileUtil.getFileFromBytes(faceFileItem, oldImgPath);
				// 压缩图片
				CompressedImgUtil.CompressPic(oldImgPath, newImgPath, 100);
				facePhoto = ReadImgUtil.imageToBase64(newImgPath);
			}
		} else {
			File file = FileUtil.getFileFromBytes(Base64Util.decode(facePhoto),
					oldImgPath);
			long fileSize = file.length();
			if (fileSize > 102400) {
				CompressedImgUtil.CompressPic(oldImgPath, newImgPath, 100);
				facePhoto = ReadImgUtil.imageToBase64(newImgPath);
			}
		}
		if (StringUtils.isBlank(CopyIDPhoto)) {
			System.out.println("传的文件的二进制流");
			List<MultipartFile> files = multipartRequest
					.getFiles("copyIDPhoto");
			CopyIDFileItem = files.get(0).getBytes();

			if (CopyIDFileItem != null) {
				System.out.println("-----------身份证芯片图："
						+ files.get(0).getOriginalFilename() + "--"
						+ files.get(0).getContentType());
				CopyIDPhoto = Base64Util.encode(CopyIDFileItem);
			}
		}
		File oldFileile = new File(oldImgPath);
		File newFileile = new File(newImgPath);
		if (newFileile.exists()) {
			newFileile.delete();
		}
		if (oldFileile.exists()) {
			oldFileile.delete();
		}

		JSONObject tokenSNOInfo = new JSONObject();
		try {
			tokenSNOInfo = HuidaoUtil.compare2photoLoginGetTokenSNO(name,
					idCard, facePhoto, CopyIDPhoto, certStartTime, certEndTime);
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		JSONObject jsonResult = new JSONObject();
		if (tokenSNOInfo.optInt("verify") == 1) {
			String tokenSNO = tokenSNOInfo.getString("tokenSNO");
			if (StringUtils.isEmpty(tokenSNO)) {
				// 一网通办未注册中级及以上实名用户
				jsonResult.put("SUCCESS", false);
				jsonResult.put("verify", tokenSNOInfo.optInt("verify"));
				jsonResult.put("tokenSNO", "");
				jsonResult.put("codeSNO", tokenSNOInfo.optString("codeSNO"));
				jsonResult.put("phone", tokenSNOInfo.optString("phone"));
				jsonResult.put("msg", "比对成功,一网通办未注册或为初级用户");
			} else {
				jsonResult.put("SUCCESS", true);
				jsonResult.put("verify", tokenSNOInfo.optInt("verify"));
				jsonResult.put("tokenSNO", tokenSNOInfo.optString("tokenSNO"));
				jsonResult.put("msg", "比对成功");
			}
		} else {
			jsonResult.put("SUCCESS", false);
			jsonResult.put("verify", tokenSNOInfo.optInt("verify"));
			jsonResult.put("tokenSNO", "");
			jsonResult.put("msg", "比对失败");
		}
		long b = System.currentTimeMillis();
		System.out.println("花费时间：" + (double) (b - a) / 1000 + "秒");
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/**
	 * 比对成功后，根据tokenSNO获取access_token
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getAccessToken.do")
	public void getAccessToken(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		JSONObject jsonResult = new JSONObject();
		try {
			String accessToken = HuidaoUtil.getAccessToken(tokenSNO);
			jsonResult.put("SUCCESS", true);
			jsonResult.put("accessToken", accessToken);
		} catch (Exception e) {
			jsonResult.put("SUCCESS", false);
			jsonResult.put("accessToken", "");
			Log.debug(e.getMessage());
		}
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/*********************************
	 * 法人登录
	 *********************************/
	/**
	 * 法人登录(ukey登录/电子营业执照登录)获取tokenSNO
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getTokenSNOForCorporation.do")
	public void getTokenSNOForCorporation(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String creditCode = req.getParameter("creditCode");
		String companyName = req.getParameter("companyName");
		String use_type = req.getParameter("use_type");
		String caCode = req.getParameter("caCode");
		if(StringUtils.isNotEmpty(companyName)){
			companyName = URLDecoder.decode(companyName, "utf-8");
		}
		
//		creditCode = "001220661696456000";
//		companyName = "测试法人一证通";
//		use_type = "1";
//		caCode = "202@XY001220661696456000";
		
		JSONObject tokenSNOInfo = new JSONObject();
		tokenSNOInfo = HuidaoUtil.getTokenSNOForCorporation(creditCode,
				companyName, use_type, caCode);
		JSONObject jsonResult = new JSONObject();
		if(tokenSNOInfo != null){
			boolean encrypted = tokenSNOInfo.optBoolean("encrypted");
			if(!encrypted){
				String error_message = tokenSNOInfo.optJSONObject("biz_response").optString("msg");
				error_message = URLDecoder.decode(error_message, "utf-8");
				jsonResult.put("SUCCESS", false);
				jsonResult.put("verify", "");
				jsonResult.put("tokenSNO", "");
				jsonResult.put("msg", error_message);
			} else {
				JSONObject biz_response = tokenSNOInfo.optJSONObject("biz_response");
				int verify = biz_response.optInt("verify");
				String msg = biz_response.optString("msg");
				if(1 == verify){
					String tokenSNO = biz_response.optString("tokenSNO");
					jsonResult.put("SUCCESS", true);
					jsonResult.put("verify", verify);
					jsonResult.put("tokenSNO", tokenSNO);
					jsonResult.put("msg", msg);
				} else {
					jsonResult.put("SUCCESS", false);
					jsonResult.put("verify", verify);
					jsonResult.put("tokenSNO", "");
					jsonResult.put("msg", msg);
				}
			}
		} else {
			jsonResult.put("SUCCESS", false);
			jsonResult.put("verify", "");
			jsonResult.put("tokenSNO", "");
			jsonResult.put("msg", "法人登录获取tokenSNO接口异常！");
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/**
	 * 获取授权
	 * @return
	 */
	private String getAuthorization() {
		String Authorization = "";
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "979b5900-1791-11ec-b7c8-3bddb603b231";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?key=c3327b76-198e-48ff-8aa4-e3c25184c34d";
		Authorization = HttpUtil.doGet(head, url, null);

		return Authorization;
	}
	
	/**
	 * ⽣成授权⼆维码
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/createFrQrCode.do")
	public void createFrQrCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "3807d690-1790-11ec-b7c8-3bddb603b231";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = getAuthorization();
		head.put("Authorization", Authorization);
		JSONObject paramJson = new JSONObject();
		paramJson.put("type", "001");
		paramJson.put("bussinfo", "自助终端法人登陆");
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		System.out.println("生成授权二维码返回结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 授权结果查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/queryFrQrCode.do")
	public void queryFrQrCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String qrid = req.getParameter("qrid");
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "749f5010-1790-11ec-b7c8-3bddb603b231";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = getAuthorization();
		head.put("Authorization", Authorization);
		JSONObject paramJson = new JSONObject();
		paramJson.put("qrid", qrid);
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		try{
			JSONObject jsonResult = JSONObject.fromObject(result);
			int code = jsonResult.optInt("code");
			long a = System.currentTimeMillis();
			long b = 0;
			int minutes = 0;
			while(0 != code && minutes >= 1){
				Thread.sleep(10000);
				Authorization = getAuthorization();
				head.put("Authorization", Authorization);
				result = HttpUtil.doPost(head, paramJson.toString(),
						"application/json;charset=utf-8");
				jsonResult = JSONObject.fromObject(result);
				code = jsonResult.optInt("code");
//				System.out.println(result);
				b = System.currentTimeMillis();
				minutes = (int) ((b - a)/(1000 * 60));
			}
		} catch (Exception e) {
		}
		
		System.out.println("授权结果查询返回："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 发送短信获取验证码
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/sendMessage.do")
	public void sendMessage(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String codeSNO = req.getParameter("codeSNO");
		String mobile = req.getParameter("mobile");
		try {
			boolean b = HuidaoUtil.sendMessage(mobile, codeSNO);
			AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":" + b
					+ "}");
		} catch (Exception e) {
			AciJsonHelper.writeJsonPResponse(req, res, "{\"SUCCESS\":false}");
		}
	}

	/**
	 * 注册，默认注册高级用户
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/registerUser.do")
	public void registerUser(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String codeSNO = req.getParameter("codeSNO");
		String mobile = req.getParameter("mobile");
		String authCode = req.getParameter("authCode");
		String idCard = req.getParameter("idCard");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String type = req.getParameter("type");
		String certStartTime = req.getParameter("certStartTime");
		String certEndTime = req.getParameter("certEndTime");
		if(StringUtils.isNotEmpty(certEndTime)){
			certEndTime = URLDecoder.decode(certEndTime, "utf-8");
		}
		if("长期".equals(certEndTime)){
			certEndTime = "长期有效";
		}
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}

		JSONObject jsonResult = new JSONObject();
		try {
			JSONObject json = HuidaoUtil.registerUser(codeSNO, mobile,
					authCode, idCard, name, password, type, certStartTime, certEndTime);
			if (json.optBoolean("success")) {
				String tokenSNO = json.optString("tokenSNO");
				jsonResult.put("SUCCESS", json.optBoolean("success"));
				jsonResult.put("tokenSNO", tokenSNO);
				jsonResult.put("msg", json.optString("msg"));
			} else {
				jsonResult.put("SUCCESS", json.optBoolean("success"));
				jsonResult.put("tokenSNO", "");
				jsonResult.put("msg", json.optString("msg"));
			}
		} catch (Exception e) {
			jsonResult.put("SUCCESS", false);
			jsonResult.put("tokenSNO", "");
		}

		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}

	/**
	 * 根据accessToken获取用户信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/getUserInfoByAccessToken.do")
	public void getUserInfoByAccessToken(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String accessToken = req.getParameter("accessToken");
		String userInfo = HttpUtil.getUserInfoByAccesstoken(accessToken);
		String mobile = "";
		String userName = "";
		String certNo = "";
		String userID = "";
		JSONObject obj = new JSONObject();
		System.out.println("用户信息：" + userInfo);
		JSONObject userJson = new JSONObject();
		try {
			userJson = JSONObject.fromObject(userInfo);
			mobile = userJson.optString("zwdtsw_link_phone");
			userName = userJson.optString("zwdtsw_name");
			certNo = userJson.optString("zwdtsw_cert_id");
			userID = userJson.optString("zwdtsw_user_id");

			obj.put("zwdtsw_link_phone", mobile);
			obj.put("zwdtsw_name", userName);
			obj.put("zwdtsw_cert_id", certNo);
			obj.put("zwdtsw_user_id", userID);
		} catch (Exception e) {
			Log.debug("获取用户信息失败" + e.getMessage());
		}
		AciJsonHelper.writeJsonPResponse(req, res, userJson.toString());
	}

	/**
	 * 通过随申码获取个人信息（门禁）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getUserInfoFromQrCode.do")
	public void getUserInfoFromQrCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String certQrCode = req.getParameter("certQrCode");
		String pos = req.getParameter("pos");
		String use = req.getParameter("use");
		System.out.println("随申码门禁登陆：" + certQrCode);
		if (StringUtils.isEmpty(pos)) {
			pos = "门禁";
		}
		if (StringUtils.isEmpty(use)) {
			use = "门禁登录";
		}
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "c3b77569-9d14-4715-9176-c28e0748a5db";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "fcd9eea9-e77e-4393-a72e-f069c6f7fe53";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		String uc_appId = RdConfig.get("reindeer.huidao.ucappid."
				+ RdConfig.get("reindeer.huidao.environment"));
		// String url =
		// RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
		// +"?uc_appId="+uc_appId+"&certQrCode="+certQrCode+"&pos="+pos+"&use="+use;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uc_appId", uc_appId);
		paramMap.put("certQrCode", certQrCode);
		paramMap.put("pos", pos);
		paramMap.put("use", use);

		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String result = HttpUtil.doPost(head, reqEntity);
		JSONObject obj = new JSONObject();
		JSONObject resultJson = new JSONObject();
		String biz_response = "";
		try {
			obj = JSONObject.fromObject(result);
			boolean encrypted = obj.optBoolean("encrypted");
			if (encrypted) {
				biz_response = obj.optString("biz_response");
				biz_response = HuidaoUtil.decrypt(
						biz_response,
						RdConfig.get("reindeer.huidao.ucpidcert."
								+ RdConfig.get("reindeer.huidao.environment")));
			}
		} catch (Exception e) {
			Log.debug(e);
		}
		System.out.println(biz_response);
		if (StringUtils.isEmpty(biz_response)) {
			resultJson.put("success", false);
			resultJson.put("msg", "随申码登陆异常！");
			resultJson.put("data", "");
		} else {
			try {
				JSONObject response = JSONObject.fromObject(biz_response);
				String tokenSNO = response.optString("tokenSNO");
				String accessToken = HuidaoUtil.getAccessToken(tokenSNO);

				String userName = "";
				String certNo = "";
				JSONObject userJson = new JSONObject();
				try {
					String userInfo = HttpUtil
							.getUserInfoByAccesstoken(accessToken);
					userJson = JSONObject.fromObject(userInfo);
					userName = userJson.optString("zwdtsw_name");
					certNo = userJson.optString("zwdtsw_cert_id");
					JSONObject json = new JSONObject();
					json.put("userName", userName);
					json.put("certNo", certNo);

					resultJson.put("success", true);
					resultJson.put("msg", "");
					resultJson.put("data", json);
				} catch (Exception e) {
					Log.debug("获取用户信息失败" + e.getMessage());
					resultJson.put("success", false);
					resultJson.put("msg", "获取用户信息失败！");
					resultJson.put("data", "");
				}
			} catch (Exception e) {
				resultJson.put("success", false);
				resultJson.put("msg", "tokenSNO换取accessToken失败！");
				resultJson.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, resultJson.toString());
	}

	/**
	 * 通过随申码获取个人信息（门禁）-----测试
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getUserInfoFromQrCodeForTest.do")
	public void getUserInfoFromQrCodeForTest(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String certQrCode = req.getParameter("certQrCode");
		String pos = req.getParameter("pos");
		String use = req.getParameter("use");
		System.out.println("随申码门禁登陆：" + certQrCode);
		if (StringUtils.isEmpty(pos)) {
			pos = "门禁";
		}
		if (StringUtils.isEmpty(use)) {
			use = "门禁登录";
		}
		JSONObject resultJson = new JSONObject();
		String biz_response = "";
		if (StringUtils.isEmpty(biz_response)) {
			// resultJson.put("success", false);
			// resultJson.put("msg", "随申码登陆异常！");
			// resultJson.put("data", "");
			JSONObject json = new JSONObject();
			json.put("userName", "陈X");
			json.put("certNo", "310228......");
			resultJson.put("success", true);
			resultJson.put("msg", "");
			resultJson.put("data", json);
		} else {
			try {
				JSONObject response = JSONObject.fromObject(biz_response);
				String tokenSNO = response.optString("tokenSNO");
				String accessToken = HuidaoUtil.getAccessToken(tokenSNO);

				String userName = "";
				String certNo = "";
				JSONObject userJson = new JSONObject();
				try {
					String userInfo = HttpUtil
							.getUserInfoByAccesstoken(accessToken);
					userJson = JSONObject.fromObject(userInfo);
					userName = userJson.optString("zwdtsw_name");
					certNo = userJson.optString("zwdtsw_cert_id");
					JSONObject json = new JSONObject();
					json.put("userName", userName);
					json.put("certNo", certNo);

					resultJson.put("success", true);
					resultJson.put("msg", "");
					resultJson.put("data", json);
				} catch (Exception e) {
					Log.debug("获取用户信息失败" + e.getMessage());
					resultJson.put("success", false);
					resultJson.put("msg", "获取用户信息失败！");
					resultJson.put("data", "");
				}
			} catch (Exception e) {
				resultJson.put("success", false);
				resultJson.put("msg", "tokenSNO换取accessToken失败！");
				resultJson.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, resultJson.toString());
	}

	/**
	 * 讯飞语音转写
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/transformationVoiceToText.do")
	public void transformationVoiceToText(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		long a = System.currentTimeMillis();
		MultipartHttpServletRequest multipartRequest = null;
		if (req instanceof MultipartHttpServletRequest) {
			multipartRequest = (MultipartHttpServletRequest) (req);
		}
		byte[] fileByts = null;
		int resultCode = 0;
		String resultMsg = "";
		String resultData = "";
		JSONObject jsonResult = new JSONObject();

		// 语音文件不能为空
		if (StringUtils.isBlank(req.getParameter("file"))
				&& multipartRequest.getFiles("file").size() <= 0) {
			Log.debug("语音文件不能为空!");
			resultCode = -1;
			resultMsg = "语音文件不能为空!";
			jsonResult.put("resultCode", resultCode);
			jsonResult.put("resultMsg", resultMsg);
			jsonResult.put("resultData", "");
		} else {
			String file = req.getParameter("file");
			if (StringUtils.isBlank(file)) {
				List<MultipartFile> files = multipartRequest.getFiles("file");
				fileByts = files.get(0).getBytes();
				if (fileByts != null) {
					System.out.println("-----------语音文件："
							+ files.get(0).getOriginalFilename() + "--"
							+ files.get(0).getContentType());
				}
			} else {
				fileByts = Base64Util.decode(file);
			}
			String audioPcmFilePath = WorkPlatformController.class.getResource(
					"").getPath();
			audioPcmFilePath = audioPcmFilePath + "template/pcm_"
					+ System.currentTimeMillis() + ".pcm";
			FileUtil.getFileFromBytes(fileByts, audioPcmFilePath);

			String audioMp3FilePath = WorkPlatformController.class.getResource(
					"").getPath();
			audioMp3FilePath = audioMp3FilePath + "template/mp3_"
					+ System.currentTimeMillis() + ".mp3";

			ConvertAudioUtil
					.convertPcmToMP3(audioPcmFilePath, audioMp3FilePath);
			File audio = new File(audioMp3FilePath);
			try {
				FileInputStream fis = new FileInputStream(audio);
				// 预处理
				String taskId = APIUtil.prepare(audio);
				// 分片上传文件
				int len = 0;
				byte[] slice = new byte[APIUtil.SLICE_SICE];
				SliceIdGenerator generator = new SliceIdGenerator();
				while ((len = fis.read(slice)) > 0) {
					// 上传分片
					if (fis.available() == 0) {
						slice = Arrays.copyOfRange(slice, 0, len);
					}
					APIUtil.uploadSlice(taskId, generator.getNextSliceId(),
							slice);
				}
				// 合并文件
				APIUtil.merge(taskId);
				fis.close();
				audio.delete();

				// 轮询获取任务结果
				int count = 0;
				while (true) {
					try {
						System.out.println("sleep a while Zzz");
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ApiResultDto taskProgress = APIUtil.getProgress(taskId);
					if (taskProgress.getOk() == 0) {
						if (taskProgress.getErr_no() != 0) {
							System.out.println("任务失败："
									+ JSON.toJSONString(taskProgress));
						}
						String taskStatus = taskProgress.getData();
						if (JSON.parseObject(taskStatus).getInteger("status") == 9) {
							System.out.println("任务完成！");
							break;
						}
						System.out.println("任务处理中：" + taskStatus);
					} else {
						System.out.println("获取任务进度失败！");
					}
					count++;
					if (count >= 3) {
						// 轮询3次超时处理
						System.out.println("语音转换超时！");
						break;
					}
				}
				// 获取结果
				resultData = APIUtil.getResult(taskId);
				System.out.println("转写结果: " + resultData);
				long b = System.currentTimeMillis();
				System.out.println((b - a) / 1000);
			} catch (SignatureException e) {
				resultCode = -1;
				resultMsg = e.getMessage();
			} catch (FileNotFoundException e1) {
				resultCode = -1;
				resultMsg = e1.getMessage();
			} catch (IOException e1) {
				resultCode = -1;
				resultMsg = e1.getMessage();
			} catch (RuntimeException e1) {
				resultCode = -1;
				resultMsg = e1.getMessage();
			}
			jsonResult.put("resultCode", resultCode);
			jsonResult.put("resultMsg", resultMsg);
			jsonResult.put("resultData", resultData);
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}

	/**
	 * 根据自定义事项编码获取事项信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/queryItemInfo.do")
	public void queryItemInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String itemCode = req.getParameter("itemCode");
		Conditions conds = Conditions.newOrConditions();
		conds.add(new Condition("ST_TEN_CODE", Condition.OT_EQUAL, itemCode));
		SelmItem item = selmItemDao.get(itemCode);
		String organId = item.getStOrganId();
		String organNme = selmItemDao.getOrgan(organId);
		JSONObject json = new JSONObject();
		json.put("stItmeNo", item.getStTenCode());
		json.put("stItmeName", item.getStMainName());
		json.put("stWorktype", item.getStWorkType());
		json.put("organNme", organNme);
		json.put("handleItemCode",
				item.getStExt4() == null ? "" : item.getStExt4());
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 根据事项编码获取办件编号
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getApplyNoByItemNo.do")
	public void getApplyNoByItemNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String itemCode = req.getParameter("itemCode");
		
		String aplyNo = "";
		boolean b = false;
		try{
			aplyNo = HttpUtil.getApplyNo(itemCode);
			b = true;
		} catch (Exception e) {
			aplyNo = "获取办件编码失败！";
			b = false;
		}
		Log.debug("办件编码："+aplyNo);
		JSONObject json = new JSONObject();
		json.put("success",b);
		json.put("aplyNo",aplyNo);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}

	@RequestMapping("/selfapi/workPlatform/updateMessyCode.do")
	public void updateMessyCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String excelPath = WorkPlatformController.class.getResource("")
				.getPath()+ "/template/乱码数据.xlsx";
		String password = "ssbsmy@1028";
		String prefix = excelPath.substring(excelPath.lastIndexOf(".") + 1);
		Workbook workbook = null;
		InputStream inp = new FileInputStream(excelPath);
		
		try{
			if (prefix.toUpperCase().equals("XLS")) {
				org.apache.poi.hssf.record.crypto.Biff8EncryptionKey
						.setCurrentUserPassword(password);
				workbook = WorkbookFactory.create(inp);
				inp.close();
			} else {
				POIFSFileSystem pfs = new POIFSFileSystem(inp);
				inp.close();
				EncryptionInfo encInfo = new EncryptionInfo(pfs);
				Decryptor decryptor = Decryptor.getInstance(encInfo);
				decryptor.verifyPassword(password);
				workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
			}
		}catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
		Map<String, String> map = new HashMap<String, String>();
		Sheet sheet1 = workbook.getSheetAt(0);
		int startRowNum = sheet1.getFirstRowNum();
		int endRowNum = sheet1.getLastRowNum();
		for (int rowNum = startRowNum+1; rowNum <= endRowNum; rowNum++) {
			Row row = sheet1.getRow(rowNum);
			if (row == null)
				continue;
			String name = row.getCell(1).getStringCellValue();
			String idCard = row.getCell(2).getStringCellValue();
			map.put(idCard, name);
		}

		CellStyle style = workbook.createCellStyle();
//		style.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		Font font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		Sheet sheet2 = workbook.getSheetAt(1);
		Row row = sheet2.getRow(0);
		int index = 1;
		for (String key : map.keySet()) {
			String name = map.get(key);
			if (MessyCodeUtil.isMessyCode(name)
					|| MessyCodeUtil.isLessUseWord(name)) {
				row = sheet2.createRow(index);
				row.createCell(0).setCellValue(name);
				row.createCell(1).setCellValue(key);
				String updateName = selfUsingHistoryDao.queryIdentityInfo(key);
				Cell cell = row.createCell(2);
				if(!updateName.equals(name)){
					cell.setCellStyle(style);
				}
				cell.setCellValue(updateName);
				index++;
			}
		}
		res.reset();
		res.setCharacterEncoding("UTF-8");
		res.setHeader("content-type", "application/octet-stream;charset=UTF-8");
		res.setContentType("application/octet-stream;charset=UTF-8");
		res.setHeader("Content-Disposition", "attachment;filename="
				+ java.net.URLEncoder.encode("乱码数据.xlsx", "UTF-8"));
		OutputStream out = res.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}
	
	/**
	 * 生僻字服务授权码接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/**
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getUncommonWordToken.do")
	public void getUncommonWordToken(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String key = "3965027f785dc13e000038f325cfd0fd";
	    String mid = "0fabde";
	    FZTokenApi fzTokenApi = new FZTokenApi();
	    String token = fzTokenApi.getToken(mid, key, "http://183.194.250.229:8080", "srf");
	    System.out.println("获得token:"+ token);
	}
	
	/**
	 * 亲属码核验
	 * 
	 * @param req
	 * @param res 0：家长 1：亲属
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/checkRelativesCode.do")
	public void checkRelativesCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String qrCode = req.getParameter("qrCode");
//		String qrCode = URLEncoder.encode("https://s.sh.gov.cn/390d44ad4494a3518780ed24bbc8061653371078374", "utf-8");
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "e8fbb0f0-c9b3-11eb-8a77-878d89464ae6";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "03e58610-d25c-11eb-9f8a-ad8386b1fddc";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url =  RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?data=" + qrCode + "&from=wandaqsmcheckbb4ee3763c2f05ec&account=govserver01&healthcode=10000000";
		String result = HttpUtil.doGet(head, url, null);
		JSONObject resultJson = new JSONObject();
		try{
			JSONObject json = JSONObject.fromObject(result);
			String data = json.optString("data");
			
			String secret = "7ae9cb96c91f441ccc611ee6d0819aa5";
			String iv = "b643e5c5427e4d6b";
			//解密
			String jmstring = AESUtil.decode(secret, iv, data);
			resultJson.put("success", true);
			resultJson.put("msg", "扫码成功！");
			resultJson.put("data", jmstring);
		} catch (JSONException e) {
			Log.debug(e.getMessage());
			resultJson.put("success", false);
			resultJson.put("msg", "接口返回数据异常！");
			resultJson.put("data", "");
		} catch (Exception e) {
			Log.debug(e);
			resultJson.put("success", false);
			resultJson.put("msg", "解密失败！");
			resultJson.put("data", result);
		}
		AciJsonHelper.writeJsonPResponse(req, res, resultJson.toString());
	}
	
	/**
	 * CA用户认证后，民政数据同步
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/CA/dataSynchronizationToCivilAdministration.do")
	public void dataSynchronizationToCivilAdministration(
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userName = req.getParameter("userName"); // 姓名
		String sex = req.getParameter("sex"); // 性别    1：男；2：女
		String identNo = req.getParameter("identNo"); // 身份证号
		String nation = req.getParameter("nation"); // 名族
		String birthday = req.getParameter("birthday"); // 生日
		String mobile = req.getParameter("mobile"); // 手机号
		String cardCreateDate = req.getParameter("cardCreateDate"); // 发卡日期
		String cardValidity = req.getParameter("cardValidity"); // 卡有效期
		String cardCompany = req.getParameter("cardCompany"); // 发卡机构
		String residenceAddress = req.getParameter("residenceAddress"); // 户籍地址
		String photo = req.getParameter("photo"); // 照片base64
		if(StringUtils.isNotEmpty(userName)){
			userName = URLDecoder.decode(userName, "utf-8");
		}
		if(StringUtils.isNotEmpty(nation)){
			nation = URLDecoder.decode(nation, "utf-8");
		}
		if(StringUtils.isNotEmpty(cardCompany)){
			cardCompany = URLDecoder.decode(cardCompany, "utf-8");
		}
		if(StringUtils.isNotEmpty(residenceAddress)){
			residenceAddress = URLDecoder.decode(residenceAddress, "utf-8");
		}
		
		String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<msg>" +
                "<head>" +
                "<version>1.0</version>" +
                "<ref>20180921152535TEST0000000587840</ref>" +
                "<code>JX0002SMRZ</code>" +
                "<src>"+
                "<code>31012010100001</code>" +
                "<uid>3101200000999999</uid>" +
                "<uname>南闲212</uname>" +
                "<uexpinf></uexpinf>" +
                "</src>" +
                "<dest>" +
                "<code>JXW</code>" +
                "<uid></uid>" +
                "<uname></uname>" +
                "<uexpinf></uexpinf>" +
                "</dest>" +
                "<time>20180921152007</time>" +
                "<ext></ext>" +
                "<sign></sign>" +
                "<rst>" +
                "<syscode></syscode>" +
                "<buscode></buscode>" +
                "<errmsg></errmsg>" +
                "</rst>" +
                "<affairno>3101201010000120180921000094</affairno>" +
                "<suid>203578018000008</suid>" +
                "</head>" +
                //用户信息
                "<body>" +
                "<certType>1</certType>" +
                "<name>"+userName+"</name>" +
                "<sex>"+sex+"</sex>" +
                "<idcard>"+identNo+"</idcard>" +
                "<nation>"+nation+"</nation>" +
                "<birthday>"+birthday+"</birthday>" +
                "<mobile>"+mobile+"</mobile>" +
                "<cardCreateDate>"+cardCreateDate+"</cardCreateDate>" +
                "<cardValidity>"+cardValidity+"</cardValidity>" +
                "<cardCompany>"+cardCompany+"</cardCompany>" +
                "<residenceAddress>"+residenceAddress+"</residenceAddress>" +
                "<photo>"+photo+"</photo>" +
                "<bitzone1>2</bitzone1>" +
                "<affairsmemo></affairsmemo>" +
                "<archives rows=\"1\"><archive>" +
                "<archivesnum>1</archivesnum>" +
                "<archivesname>身份证</archivesname>" +
                "<archivescode>GJ00010001</archivescode>" +
                "</archive></archives>" +
                "</body>" +
                "</msg>";
		
		BASE64Encoder encoder = new BASE64Encoder();
		String param = encoder.encode(request.getBytes("UTF-8"));
		
        String paramStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:civ=\"http://202.96.220.202/sp/services/CivilSysService\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                 + "<civ:service>"
                    + "<civ:flag>1</civ:flag>     <civ:message>"+param+"</civ:message>"
                 + "</civ:service>"
                + "</soapenv:Body>"
              + "</soapenv:Envelope>";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "05621e65-2802-4940-bf67-497873fad998";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "df38c9e4-61dd-4b7b-8d50-edded70caf11";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String postResult = HttpUtil.doPost(head, paramStr, "text/xml");
        String resultXml = "";
        if(postResult.indexOf("<return>") != -1){
            String[] resultArr = postResult.split("<return>");
            resultXml = resultArr[1].split("</return>")[0];
            try {
    			resultXml = new String(Base64Util.decode(resultXml), "UTF-8");
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
        }
        Log.debug("请求返回报文："+resultXml);
        String[] strArr = resultXml.split("<buscode>");
        String result = strArr[1].split("</buscode")[0];
        
        JSONObject json = new JSONObject();
        if("000000".equals(result)){
        	json.put("SUCCESS", "true");
        	json.put("result", result);
        	json.put("MSG", "添加成功");
        } else if("-1200".equals(result)){
        	json.put("SUCCESS", "false");
        	json.put("result", result);
        	json.put("MSG", "用户已存在");
        } else {
        	json.put("SUCCESS", "false");
        	json.put("result", "");
        	json.put("MSG", "返回结果异常");
        }
        
        res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	public static boolean hasRareWords(String source) {
        if (TextUtils.isEmpty(source)) return false;
        String reg0 = "[\r\n\\s]+";
        String reg1 = "[\u0020-\u007E\u00A0-\u00BE\u4e00-\u9fa5\\p{P}\\p{S}]+";

        String filterText = Pattern.compile(reg0).matcher(source).replaceAll("");
        if (TextUtils.isEmpty(filterText)) return false;

        Matcher matcher = Pattern.compile(reg1).matcher(filterText);
        if (matcher.find()) {
            return !matcher.group().equals(filterText);
        }
        return true;
    }
	
	public static void main(String[] args) throws Exception {
		System.out.println(hasRareWords("𦩘"));
	}
	
	@Autowired
	private SelmItemDao selmItemDao;
	
	@Autowired
	private SelfUsingHistoryDao selfUsingHistoryDao;
}
