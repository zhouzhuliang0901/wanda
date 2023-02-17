package com.wondersgroup.dataitem.item311633244730.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.tongtech.encryptUtil.sm4.Sm4CBCUtils;
import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item311633244730.utils.AESUtil;

@Controller
public class OfflineCodeController {

	/**
	 * 离线码预览
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/offlineCode/priviewOfflineCode.do")
	public void priviewOfflineCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		String name = req.getParameter("name");
		String fileType = req.getParameter("fileType");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		
		String secretKey = "JeF8U9wHFOMfs2Y8";
		String iv = "UISwD9fW6cFh9SNS";
		String plainText = name + "," + idCard;
		String cipherText = Sm4CBCUtils.encryptData_CBC(plainText, secretKey,
				iv);
		System.out.println("cipherText：" + cipherText);
		String yhmd5data = DigestUtils.md5Hex(plainText);
		
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "b0eb8280-d944-11eb-aa41-5ff1d15287bb";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "97168490-eace-11eb-b275-95d14c975843";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("yhxx", cipherText);
		paramJson.put("scene", "govserver01");
		paramJson.put("from", "10");
		paramJson.put("yhmd5data", yhmd5data);
		paramJson.put("fileType", fileType);

		System.out.println("离线码预览接口参数：" + paramJson.toString());
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		try {
			JSONObject josn = JSONObject.fromObject(result);
			String code = josn.optString("code");
			if (!"0".equals(code)) {
				System.out.println("离线码预览接口返回结果：" + result);
			}
		} catch (Exception e) {
			Log.debug("离线码预览返回结果异常：" + e.getCause());
		}
		try {
			JSONObject resultJson = JSONObject.fromObject(result);
			if ("1".equals(fileType)) {
				String code = resultJson.optString("code");
				if ("0".equals(code)) {
					String pdfStr = resultJson.optString("data");
					String fileName = "offlineCodePdf_" + idCard
							+ System.currentTimeMillis() + ".pdf";
					String filePath = OfflineCodeController.class.getResource(
							"").getPath()
							+ "template/" + fileName;
					byte[] bt = Base64Util.decode(pdfStr);
					FileUtil.getFileFromBytes(bt, filePath);
					resultJson.put("fileName", fileName);
					resultJson.put("pdfStr", pdfStr);
				}
				result = resultJson.toString();
			}
		} catch (Exception e) {
			Log.debug(e);
		}
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 生成离线码
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/**
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/offlineCode/creatOfflineCode.do")
	public void creatOfflineCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		long a = System.currentTimeMillis();
		String idCard = req.getParameter("idCard");
		String name = req.getParameter("name");
		// 1：申领；2：补领
		String type = req.getParameter("type");
		// 1:pdf;0:png
		String fileType = req.getParameter("fileType");
		String machineId = req.getParameter("machineId");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		
		idCard = "320721199408210016";
		name = "张子昱";
		type = "1";
		fileType = "0";
		
		String secretKey = "JeF8U9wHFOMfs2Y8";
		String iv = "UISwD9fW6cFh9SNS";
		String plainText = name + "," + idCard;
		String cipherText = Sm4CBCUtils.encryptData_CBC(plainText, secretKey,
				iv);
		System.out.println("cipherText：" + cipherText);
		String yhmd5data = DigestUtils.md5Hex(plainText);

		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "af8af510-c9c2-11eb-8a77-878d89464ae6";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "76b24810-eace-11eb-b275-95d14c975843";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject paramJson = new JSONObject();
		paramJson.put("yhxx", cipherText);
		paramJson.put("scene", "govserver01");
		paramJson.put("from", "10");
		paramJson.put("yhmd5data", yhmd5data);
		paramJson.put("type", type);
		paramJson.put("fileType", fileType);

		System.out.println("离线码接口参数：" + paramJson.toString());
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		try {
			JSONObject josn = JSONObject.fromObject(result);
			String code = josn.optString("code");
			if (!"0".equals(code)) {
				System.out.println("离线码接口返回结果：" + result);
			}
		} catch (Exception e) {
			Log.debug("离线码接口返回结果异常：" + e.getCause());
		}
		long b = System.currentTimeMillis();
		System.out.println("离线码接口请求耗时：" + (double) (b - a) / 1000
				+ "秒---设备MAC：" + machineId);

		try {
			JSONObject resultJson = JSONObject.fromObject(result);
			if ("1".equals(fileType)) {
				String code = resultJson.optString("code");
				if ("0".equals(code)) {
					String pdfStr = resultJson.optString("data");
					String fileName = "offlineCodePdf_" + idCard
							+ System.currentTimeMillis() + ".pdf";
					String filePath = OfflineCodeController.class.getResource(
							"").getPath()
							+ "template/" + fileName;
					byte[] bt = Base64Util.decode(pdfStr);
					FileUtil.getFileFromBytes(bt, filePath);
					resultJson.put("fileName", fileName);
					resultJson.put("pdfStr", pdfStr);
				}
				result = resultJson.toString();
			}
		} catch (Exception e) {
			Log.debug(e);
		}
		long c = System.currentTimeMillis();
		System.out.println("离线码接口请求后文件处理耗时：" + (double) (c - b) / 1000
				+ "秒---设备MAC：" + machineId);

		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 获取离线码pdf文件
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/offlineCode/getOfflineCodePDF.do")
	public void getOfflineCodePDF(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String fileName = req.getParameter("fileName");
		String filePath = OfflineCodeController.class.getResource("").getPath()
				+ "template/" + fileName;
		File pdf = new File(filePath);
		byte[] bytes = FileUtil.getBytesFromFile(pdf);
		pdf.delete();
		OutputStream out = res.getOutputStream();
		res.setContentType("application/pdf");
		out.write(bytes);
		out.close();
	}

	/**
	 * 离线码挂失
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/offlineCode/reportlossOfflineCode.do")
	public void reportlossOfflineCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		String name = req.getParameter("name");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}
		idCard = "320721199408210016";
		name = "张子昱";
		String secretKey = "JeF8U9wHFOMfs2Y8";
		String iv = "UISwD9fW6cFh9SNS";
		String plainText = name + "," + idCard;
		String cipherText = Sm4CBCUtils.encryptData_CBC(plainText, secretKey,
				iv);
		System.out.println("cipherText：" + cipherText);

		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "dbdba2e0-d944-11eb-aa41-5ff1d15287bb";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "00743e00-eacf-11eb-b275-95d14c975843";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject paramJson = new JSONObject();
		paramJson.put("yhxx", cipherText);
		paramJson.put("zjlx", "00");
		paramJson.put("from", "10");

		System.out.println("离线码挂失接口参数：" + paramJson.toString());
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		System.out.println("离线码挂失接口结果：" + result);
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 离线码状态查询
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/offlineCode/queryOfflineCodeStatus.do")
	public void queryOfflineCodeStatus(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		String name = req.getParameter("name");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
		}

		String secretKey = "JeF8U9wHFOMfs2Y8";
		String iv = "UISwD9fW6cFh9SNS";
		String plainText = name + "," + idCard;
		String cipherText = Sm4CBCUtils.encryptData_CBC(plainText, secretKey,
				iv);
		System.out.println("cipherText：" + cipherText);

		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "eb7b8df0-f0d7-11eb-99de-57286fb59fd9";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "4f7f9600-f13e-11eb-a44e-91bae8ea49d4";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject paramJson = new JSONObject();
		paramJson.put("yhxx", cipherText);
		paramJson.put("from", "10");
		paramJson.put("appkey", "20210721mlgkey");

		System.out.println("离线码状态查询接口参数：" + paramJson.toString());
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 扫离线码结果
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/offlineCode/checkOfflineCodeStatus.do")
	public void checkOfflineCodeStatus(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 离线码内容
		String codeUrl = req.getParameter("codeUrl");
		String userId = req.getParameter("userId");
		String userName = req.getParameter("userName");
		String idCard = req.getParameter("idCard");
		String phone = req.getParameter("phone");
		if(StringUtils.isNotEmpty(codeUrl)){
			codeUrl = URLDecoder.decode(codeUrl, "utf-8");
		}
		if(StringUtils.isNotEmpty(userName)){
			userName = URLDecoder.decode(userName, "utf-8");
		}
		
		String uid = getParam(codeUrl,"m");
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "b20b40e0-0bb9-11ec-9ca6-ff6b2def5f54";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		String initVector = "6f7462eedec736ed";
		String key = "f074c0fdbd23c7bd";
		String content = userId + "," + userName + "," + idCard + "," + phone;
		String mw = AESUtil.aesCBCEncrypt(content, "UTF-8", key, initVector);

		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?uid=" + uid + "&mw=" + mw;
		String result = HttpUtil.doGet(head, url, null);
		System.out.println("扫离线码结果返回信息：" + result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	@SuppressWarnings("null")
	private String getParam(String url, String keyWord) {
		String retValue = "";
		try {
			if (url.indexOf('?') != -1) {
				final String contents = url.substring(url.indexOf('?') + 1);
				String[] keyValues = contents.split("&");
				for (int i = 0; i < keyValues.length; i++) {
					String key = keyValues[i].substring(0,
							keyValues[i].indexOf("="));
					String value = keyValues[i].substring(keyValues[i]
							.indexOf("=") + 1);
					if (key.equals(keyWord)) {
						if (value != null || !"".equals(value.trim())) {
							retValue = value;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}
}
