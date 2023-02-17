package com.wondersgroup.login.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.CompressedImgUtil;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.MessyCodeUtil;
import com.wondersgroup.common.utils.ReadImgUtil;
import com.wondersgroup.login.utils.HuidaoUtil;

@Controller
public class LoginByCardController {
	
	/**
	 * 两照对比获取tokenSNO
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getTokenSNO.do")
	public void getTokenSNO(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
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
		if(MessyCodeUtil.isMessyCode(name) ){
			throw new RuntimeException("Param 'name' is messy code!");
		}
		
		byte[] faceFileItem = null;
		byte[] CopyIDFileItem = null;

		String facePhoto = req.getParameter("facePhoto");
		String CopyIDPhoto = req.getParameter("copyIDPhoto");
		String oldImgPath = LoginByCardController.class.getResource("")
				.getPath();
		oldImgPath = oldImgPath + "/template/oldImg"+idCard+".jpg";
		String newImgPath = LoginByCardController.class.getResource("")
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
	@RequestMapping("/selfapi/workPlatform/getAccessToken.do")
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
}
