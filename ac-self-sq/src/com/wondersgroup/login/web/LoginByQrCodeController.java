package com.wondersgroup.login.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.login.utils.HuidaoUtil;

@Controller
public class LoginByQrCodeController {
	
	/**
	 * 随申码扫码获取tokenSNO
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/loginService/getTokenSNOByQrCode.do")
	public void getTokenSNOByQrCode(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String certQrCode = req.getParameter("certQrCode");
		
		String pos = req.getParameter("pos");
		String use = req.getParameter("use");
		Log.debug("随申码参数："+certQrCode);
		
		if(StringUtils.isEmpty(pos)){
			pos = "万达自助机";
		}
		if(StringUtils.isEmpty(use)){
			use = "万达自助机扫码登录";
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c3b77569-9d14-4715-9176-c28e0748a5db";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "fcd9eea9-e77e-4393-a72e-f069c6f7fe53";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String areaCode = RdConfig.get("reindeer.area.code");
		String uc_appId = RdConfig.get("reindeer.huidao.ucappid."+RdConfig.get("reindeer.huidao.environment"));
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uc_appId", uc_appId);
		paramMap.put("certQrCode", certQrCode);
		paramMap.put("pos", pos);
		paramMap.put("use", use);
		paramMap.put("orgName", areaCode);
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
	    Log.debug("随申码登陆接口："+result);
		JSONObject obj = new JSONObject();
		try{
			obj = JSONObject.fromObject(result);
			boolean encrypted = obj.optBoolean("encrypted");
			if(encrypted){
				String biz_response = obj.optString("biz_response");
				biz_response = HuidaoUtil.decrypt(biz_response,RdConfig.get("reindeer.huidao.ucpidcert."+RdConfig.get("reindeer.huidao.environment")));
				Log.debug("随申码登陆接口解密结果："+URLDecoder.decode(biz_response, "utf-8"));
				obj.put("biz_response", biz_response);
			}
		} catch (Exception e) {
			Log.debug(e);
			obj.put("encrypted", "false");
			obj.put("biz_response", "扫码服务异常");
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
}
