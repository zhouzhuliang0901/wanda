package com.wondersgroup.dataitem.item240662233023.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

/**
 * State Administration for Market Regulation
 * @author XB
 *
 */
@Controller
@SuppressWarnings("unchecked")
public class SAFMRController {
	
	/**
	 * 企业名称状态查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/SAFMR/getCompanyStateInfo.do")
	public void getCompanyStateInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String companyName = req.getParameter("companyName");
		if(StringUtils.isNotEmpty(companyName)){
			companyName = URLDecoder.decode(companyName, "utf-8");
		}
//		companyName = "聆通托育";
		
		String appName = "ebd52366-7f87-4013-a944-325b2c628673";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "text/plain;charset=utf-8";
		String result = HttpUtil.doPost(head, "", contentType);
		System.out.println("企业名称状态查询结果："+result);
		JSONArray arr = JSONArray.fromObject(result);
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isNotEmpty(result)){
			JSONArray allArr = new JSONArray();
			for(int iLoop = 0;iLoop<arr.size();iLoop++){
				JSONArray elementArr = arr.optJSONArray(iLoop);
				Object[] objArr = elementArr.toArray();
				JSONObject obj = new JSONObject();
				obj.put("applyDate", objArr[0]);
				obj.put("applyName", objArr[1]);
				obj.put("applyDept", objArr[2]);
				obj.put("applyResult", objArr[3]);
				allArr.add(obj);
			}
			
			JSONArray resultArr = new JSONArray();
			if(StringUtils.isNotEmpty(companyName)){
				ListIterator<JSONObject> it = allArr.listIterator();
				while(it.hasNext()){
					JSONObject obj = it.next();
					if(obj.optString("applyName").contains(companyName)){
						resultArr.add(obj);
					}
				}
			} else {
				resultArr = allArr;
			}
			resultObj.put("success", true);
			resultObj.put("msg", "请求成功。");
			resultObj.put("data", resultArr);
		} else {
			resultObj.put("success", false);
			resultObj.put("msg", "接口访问失败！");
			resultObj.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, resultObj.toString());
	}
	
	/**
	 * 合同示范文本查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/SAFMR/getContractText.do")
	public void getContractText(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String contractName = req.getParameter("contractName");
		if(StringUtils.isNotEmpty(contractName)){
			contractName = URLDecoder.decode(contractName, "utf-8");
		}
//		contractName = "农村土地";
		
		String appName = "b71f2a05-8461-424b-be01-c5e66eeec1c6";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "text/plain;charset=utf-8";
		String result = HttpUtil.doPost(head, "", contentType);
		System.out.println("合同示范文本查询结果："+result);
		JSONArray arr = JSONArray.fromObject(result);
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isNotEmpty(result)){
			JSONArray allArr = new JSONArray();
			for(int iLoop = 0;iLoop<arr.size();iLoop++){
				JSONArray elementArr = arr.optJSONArray(iLoop);
				Object[] objArr = elementArr.toArray();
				JSONObject obj = new JSONObject();
				obj.put("contractName", objArr[0]);
				obj.put("date", objArr[1]);
				obj.put("url", objArr[2]);
				allArr.add(obj);
			}
			
			JSONArray resultArr = new JSONArray();
			if(StringUtils.isNotEmpty(contractName)){
				ListIterator<JSONObject> it = allArr.listIterator();
				while(it.hasNext()){
					JSONObject obj = it.next();
					if(obj.optString("contractName").contains(contractName)){
						resultArr.add(obj);
					}
				}
			} else {
				resultArr = allArr;
			}
			resultObj.put("success", true);
			resultObj.put("msg", "请求成功。");
			resultObj.put("data", resultArr);
		} else {
			resultObj.put("success", false);
			resultObj.put("msg", "接口访问失败！");
			resultObj.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, resultObj.toString());
	}
	
	/**
	 * 广告发布登记查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/SAFMR/getAdvertisementInfo.do")
	public void getAdvertisementInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String unitName = req.getParameter("unitName");
		if(StringUtils.isNotEmpty(unitName)){
			unitName = URLDecoder.decode(unitName, "utf-8");
		}
//		unitName = "纺织";
		
		String appName = "eeb6a19b-fd65-46b9-bd37-9261b7b62812";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "text/plain;charset=utf-8";
		String result = HttpUtil.doPost(head, "", contentType);
		System.out.println("广告发布登记查询结果："+result);
		JSONArray arr = JSONArray.fromObject(result);
		JSONObject resultObj = new JSONObject();
		if(StringUtils.isNotEmpty(result)){
			JSONArray allArr = new JSONArray();
			for(int iLoop = 0;iLoop<arr.size();iLoop++){
				JSONArray elementArr = arr.optJSONArray(iLoop);
				Object[] objArr = elementArr.toArray();
				JSONObject obj = new JSONObject();
				obj.put("unitName", objArr[0]);
				obj.put("limitStartDate", objArr[1]);
				obj.put("limitEndDate", objArr[2]);
				obj.put("mediumName", objArr[3]);
				obj.put("registerDate", objArr[4]);
				allArr.add(obj);
			}
			
			JSONArray resultArr = new JSONArray();
			if(StringUtils.isNotEmpty(unitName)){
				ListIterator<JSONObject> it = allArr.listIterator();
				while(it.hasNext()){
					JSONObject obj = it.next();
					if(obj.optString("unitName").contains(unitName)){
						resultArr.add(obj);
					}
				}
			} else {
				resultArr = allArr;
			}
			resultObj.put("success", true);
			resultObj.put("msg", "请求成功。");
			resultObj.put("data", resultArr);
		} else {
			resultObj.put("success", false);
			resultObj.put("msg", "接口访问失败！");
			resultObj.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, resultObj.toString());
	}
}
