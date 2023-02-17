package com.wondersgroup.selfapi.web;

import java.io.IOException;
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
import wfc.service.config.Config;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class SelfDeclareForCSJController {
	
	/**
	 * 长三角申报-材料上传
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclareForCSJ/uploadStuffs.do")
	public void uploadStuffs(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
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
		if (StringUtils.isBlank(FileData) && multipartRequest != null) {
			System.out.println("传的文件的二进制流");
			multipartRequest = ((MultipartHttpServletRequest) req);
			List<MultipartFile> files = multipartRequest.getFiles("FileData");
			file = files.get(0);
			bytes = file.getBytes();
			System.out.println("-----------" + file.getOriginalFilename() + "--"
					+ files.get(0).getContentType());
		} else {
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
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "bb7481c2-2547-43ea-89f1-ac53e6c70f3f";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "88b534ae-581f-4e38-b1f8-dcbd0b568434";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("applyNo", applyNo);
        params.put("stuffCode", stuffCode);
        if(StringUtils.isNotEmpty(stuffId)){
        	params.put("stuffId", stuffId);
        }
        
        String result = HttpUtil.sendMultipartFilePost(bytes,head,params,file);
        res.setHeader("Access-Control-Allow-Origin", "*");
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 长三角申报-提交
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclareForCSJ/submitApply.do")
	public void submitApply(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
    	String applyNo = req.getParameter("applyNo");
    	
    	String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "95bd2cec-589e-4de7-915e-8ee117540bf5";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "5520c4fb-18d7-4dab-a4a9-730d17cf902c";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        JSONObject data = new JSONObject();
        JSONObject applyNoObj = new JSONObject();
        applyNoObj.put("applyNo",applyNo);
        data.put("data", applyNoObj);
        
        System.out.println("长三角申报-提交参数："+data.toString());
        String contentType = "application/json;charset=utf-8";
     	String result = HttpUtil.doPost(head,data.toString(),contentType);
     	
     	AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
