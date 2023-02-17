package com.wondersgroup.dataitem.item310100284000.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.HuidaoUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

/**
 * 律师执业审核
 * @author XB
 *
 */
@Controller
public class LawyerPracticeController {
	
	/**
	 * 首次申请律师执业审核-专职、兼职
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/lawyerPractice/applyLawyerPracticeForTheFirst.do")
	public void applyLawyerPracticeForTheFirst(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String params = req.getParameter("params");
		params = URLDecoder.decode(params,"utf-8");
		
		String itemCode = "";
		String tokenSNO = "";
		JSONObject obj = new JSONObject();
		try{
			System.out.println("首次申请参数："+params);
			JSONObject json = JSONObject.fromObject(params);
			JSONObject data = json.optJSONObject("data");
			itemCode = data.optString("itemCode");
			tokenSNO = data.optString("accessToken");
			if(StringUtils.isEmpty(tokenSNO)){
				obj.put("success", "false");
				obj.put("msg", "Token is empty.");
			} else {
				String accessToken = HuidaoUtil.getAccessToken(tokenSNO);
				data.put("accessToken", accessToken);
				params = json.toString();
			}
		} catch (Exception e) {
			obj.put("success", "false");
			obj.put("msg", "Illegal parameter format.");
		}
		
		if(StringUtils.isNotEmpty(itemCode) && StringUtils.isNotEmpty(tokenSNO)){
			String environment = RdConfig.get("reindeer.huidao.environment");
			String appName = "";
			// 专职
			if("310100284000-01".equals(itemCode)){
				if("test".equals(environment)){
					appName = "1fca7e7f-fa35-4f21-9ac8-b1caa81ea504";
				} else if("product".equals(environment)){
					appName = "b4fa0a99-ecd9-450d-9c38-44b7ebb9deba";
				}
			}
			// 兼职
			if("310100284000-03".equals(itemCode)){
				if("test".equals(environment)){
					appName = "c2a4fb9d-b910-4214-9e4a-d9de17220f6d";
				} else if("product".equals(environment)){
					appName = "53cbf4b7-072c-4514-9a29-609fe1e45a59";
				}
			}
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
			String contentType = "application/json;charset=utf-8";
			String result = HttpUtil.doPost(head, params, contentType);
			System.out.println("首次申请律师执业审核-专职、兼职:"+result);
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
		}
	}
	
	/**
	 * 重新申请律师执业审核-专职、兼职
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/lawyerPractice/reapplyLawyerPractice.do")
	public void reapplyLawyerPractice(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String params = req.getParameter("params");
		params = URLDecoder.decode(params,"utf-8");
		
		String itemCode = "";
		String tokenSNO = "";
		JSONObject obj = new JSONObject();
		try{
			System.out.println("重新申请："+params);
			JSONObject json = JSONObject.fromObject(params);
			JSONObject data = json.optJSONObject("data");
			itemCode = data.optString("itemCode");
			tokenSNO = data.optString("accessToken");
			if(StringUtils.isEmpty(tokenSNO)){
				obj.put("success", "false");
				obj.put("msg", "Token is empty.");
			} else {
				String accessToken = HuidaoUtil.getAccessToken(tokenSNO);
				data.put("accessToken", accessToken);
				params = json.toString();
			}
		} catch (Exception e) {
			obj.put("success", "false");
			obj.put("msg", "Illegal parameter format.");
		}
		
		if(StringUtils.isNotEmpty(itemCode) && StringUtils.isNotEmpty(tokenSNO)){
			String environment = RdConfig.get("reindeer.huidao.environment");
			String appName = "";
			// 专职
			if("310100284000-08".equals(itemCode)){
				if("test".equals(environment)){
					appName = "497e725b-48ad-4174-b609-73bcdcb4ff43";
				} else if("product".equals(environment)){
					appName = "41307d81-5ceb-4738-9614-63c52342a5af";
				}
			}
			// 兼职
			if("310100284000-07".equals(itemCode)){
				if("test".equals(environment)){
					appName = "dfcfb4f6-20b9-473b-adf1-6eef35e61f44";
				} else if("product".equals(environment)){
					appName = "15b2100d-a2ce-4bd6-857f-322b8f88e511";
				}
			}
			
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
			String contentType = "application/json;charset=utf-8";
			String result = HttpUtil.doPost(head, params, contentType);
			System.out.println("重新申请律师执业审核-专职、兼职："+result);
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
		}
	}
	
	/**
	 * 律师执业审核表单信息提交接口-律师执业机构变更、律师执业证注销、律师专职执业变更兼职执业、律师兼职执业变更专职执业
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/lawyerPractice/saveLawyerInfo.do")
	public void saveLawyerInfo(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String params = req.getParameter("params");
		params = URLDecoder.decode(params,"utf-8");
		
		String itemCode = "";
		String tokenSNO = "";
		JSONObject obj = new JSONObject();
		try{
			System.out.println("律师执业审核表单信息提交接口参数："+params);
			JSONObject json = JSONObject.fromObject(params);
			JSONObject data = json.optJSONObject("data");
			itemCode = data.optString("itemCode");
			tokenSNO = data.optString("accessToken");
			if(StringUtils.isEmpty(tokenSNO)){
				obj.put("success", "false");
				obj.put("msg", "Token is empty.");
			} else {
				String accessToken = HuidaoUtil.getAccessToken(tokenSNO);
				data.put("accessToken", accessToken);
				params = json.toString();
			}
		} catch (Exception e) {
			obj.put("success", "false");
			obj.put("msg", "Illegal parameter format.");
		}
		
		if(StringUtils.isNotEmpty(itemCode) && StringUtils.isNotEmpty(tokenSNO)){
			String environment = RdConfig.get("reindeer.huidao.environment");
			String appName = "";
			// 律师执业机构变更
			if("310100284000-02".equals(itemCode)){
				if("test".equals(environment)){
					appName = "6f9b0b42-ca9e-4b18-b052-bc175f12ad15";
				} else if("product".equals(environment)){
					appName = "4788a6dd-40f1-47ea-85f5-3f7c362f018a";
				}
			}
			// 律师执业证注销
			if("310100284000-04".equals(itemCode)){
				if("test".equals(environment)){
					appName = "09094337-408d-4c80-8cde-367ba54aa02d";
				} else if("product".equals(environment)){
					appName = "ca410219-0c92-46e3-8025-525509fd2819";
				}
			}
			// 律师专职执业变更兼职执业
			if("310100284000-05".equals(itemCode)){
				if("test".equals(environment)){
					appName = "ece8995b-af58-407d-a67f-a71dc60520ad";
				} else if("product".equals(environment)){
					appName = "138375ac-8941-4e4c-af2e-ac223f5fcd2f";
				}
			}
			// 律师兼职执业变更专职执业
			if("310100284000-06".equals(itemCode)){
				if("test".equals(environment)){
					appName = "d1bc98d2-9245-4180-8855-daf3e24cd23b";
				} else if("product".equals(environment)){
					appName = "f5c969c1-9e0d-4922-b0e7-cc99f1b3bce1";
				}
			}
			
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
			String contentType = "application/json;charset=utf-8";
			String result = HttpUtil.doPost(head, params, contentType);
			System.out.println("律师执业审核表单信息提交接口"+result);
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
		}
	}
	
	/**
	 * 三级联动字典-公安字典获取
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/lawyerPractice/queryDictionariesByGA.do")
	public void queryDictionariesByGA(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String rootCode = req.getParameter("rootCode");
		String type = req.getParameter("type");
		String code = req.getParameter("code");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "49b90053-45cf-4186-a676-2bd1413f3112";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "382fb66f-1767-473b-a838-94cc1e92ddf7";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("rootCode", rootCode);
		obj.put("type", type == null ? "0" : type);
		obj.put("code", code == null ? "" : code);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head, obj.toString(), contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
