package com.wondersgroup.login.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.login.utils.HuidaoUtil;

@Controller
public class LoginForFrController {
	
	/**
	 * 法人登录(ukey登录/电子营业执照登录)获取tokenSNO
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getTokenSNOForCorporation.do")
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
			appName = "406e4e30-0d7c-11ed-9afd-ff274f79b579";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				// test
//				+ "?key=c3327b76-198e-48ff-8aa4-e3c25184c34d";
				// product
				+ "?key=4fe0bebc-ae44-4344-bda4-f51e67a1d822";
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
		String appName = "3807d690-1790-11ec-b7c8-3bddb603b231";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = getAuthorization();
		head.put("Authorization", Authorization);
		JSONObject paramJson = new JSONObject();
		paramJson.put("type", "001");
		paramJson.put("bussinfo", "自助终端法人登陆");
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		Log.debug("生成授权二维码返回结果："+result);
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
		String appName = "749f5010-1790-11ec-b7c8-3bddb603b231";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String Authorization = getAuthorization();
		head.put("Authorization", Authorization);
		JSONObject paramJson = new JSONObject();
		paramJson.put("qrid", qrid);
		paramJson.put("hasOperInfo", "1");
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
				b = System.currentTimeMillis();
				minutes = (int) ((b - a)/(1000 * 60));
			}
		} catch (Exception e) {
		}
		
		Log.debug("授权结果查询返回："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
