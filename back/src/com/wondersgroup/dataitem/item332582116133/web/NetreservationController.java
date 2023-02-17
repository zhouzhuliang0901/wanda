package com.wondersgroup.dataitem.item332582116133.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.log.Log;

/**
 * 统一预约（自助终端）
 * @author wanda
 *
 */
@Controller
public class NetreservationController {
	
	/**
	 * 获取预约办理点
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/netreservation/fetchPlaceList.do")
	public void fetchPlaceList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stParentDepartmentName = req.getParameter("stParentDepartmentName");
		String stOrganType = req.getParameter("stOrganType");
		if(StringUtils.isNotEmpty(stParentDepartmentName)){
			stParentDepartmentName = URLDecoder.decode(stParentDepartmentName, "utf-8");
		}
		if(StringUtils.isNotEmpty(stOrganType)){
			stOrganType = URLDecoder.decode(stOrganType, "utf-8");
		}
//		stParentDepartmentName = "黄浦区";
//		stOrganType = "社区事务受理服务中心";
		String appName = "542f0ae0-cd7c-11eb-9f8a-ad8386b1fddc";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("stParentDepartmentName", stParentDepartmentName);
		json.put("stOrganType", stOrganType);
		
		String str = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			JSONObject resultJson = JSONObject.fromObject(str);
			int code = resultJson.optInt("code");
			if(200 == code){
				JSONArray jsonArray = resultJson.optJSONArray("data");
				JSONArray sortArr = sortJSONArray(jsonArray, "placeName");
				resultJson.put("data", sortArr);
			}
			str = resultJson.toString();
		} catch (Exception e) {
			Log.debug("排序失败："+e.getMessage().toString());
		}
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 获取办理点公告
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/netreservation/getNotice.do")
	public void getNotice(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String placeId = req.getParameter("placeId");
		
		String appName = "e897d070-3179-11ec-9963-8523ae84ab01";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("stWindowId", placeId);
		
		String str = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 1.1.1.查询该办理点下所有事项的归属部门
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/netreservation/getItemOrganByWindowCode.do")
	public void getItemOrganByWindowCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stWindowCode = req.getParameter("stWindowCode");
		String appName = "894daa28-710f-44dc-afa0-78481ddc8663";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("stWindowCode", stWindowCode);
		
		String str = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			JSONObject resultJson = JSONObject.fromObject(str);
			int code = resultJson.optInt("code");
			if(200 == code){
				JSONArray jsonArray = resultJson.optJSONArray("data");
				JSONArray sortArr = sortJSONArray(jsonArray, "stItemOrganName");
				resultJson.put("data", sortArr);
			}
			str = resultJson.toString();
		} catch (Exception e) {
			Log.debug("排序失败："+e.getMessage().toString());
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 1.1.2.根据事项名称查询事项
	 * @param stWindowCode 办理点编号
	 * @param stTermName 预约项名称(如果为空则匹配所有事项，可传入模糊值)
	 * @param stItemOrganCode 预约事项归属部门编号(如果为空则匹配所有归属部门)
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/netreservation/searchItemLikeName.do")
	public void searchItemLikeName(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stWindowCode = req.getParameter("stWindowCode");
		String stTermName = req.getParameter("stTermName");
		String stItemOrganCode = req.getParameter("stItemOrganCode");
		if(StringUtils.isNotEmpty(stTermName)){
			stTermName = URLDecoder.decode(stTermName, "utf-8");
		}
//		stTermName = "参保";
		String appName = "ef22ea7f-c30f-4436-b890-e7ba25d9e41e";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("stWindowCode", stWindowCode);
		json.put("stTermName", stTermName);
		json.put("stItemOrganCode", stItemOrganCode);
		json.put("pageNum", 1);
		json.put("pageSize", Integer.MAX_VALUE/2);
		
		String str = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			JSONObject resultJson = JSONObject.fromObject(str);
			int code = resultJson.optInt("code");
			if(200 == code){
				JSONArray jsonArray = resultJson.optJSONArray("data");
				JSONArray sortArr = sortJSONArray(jsonArray, "stTermName");
				resultJson.put("data", sortArr);
			}
			str = resultJson.toString();
		} catch (Exception e) {
			Log.debug("排序失败："+e.getMessage().toString());
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 1.1.3.查询预约资源
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/netreservation/getResourceList.do")
	public void getResourceList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stWindowCode = req.getParameter("stWindowCode");
		String stTermCode = req.getParameter("stTermCode");
		String stDay = req.getParameter("stDay");
		String appName = "0dd7d015-ea68-4bc2-9ced-a0c34f7311b3";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("stWindowCode", stWindowCode);
		json.put("stTermCode", stTermCode);
		json.put("stDay", stDay);
		
		String str = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 1.2.1.保存预约记录并发送短信提示
	 * @param stUsername  用户姓名
	 * @param stIdCardNo  用户身份证号
	 * @param stTermId  预约项唯一编号
	 * @param stResourceId  预约资源唯一编号
	 * @param stMobile 用户手机号
	 * @param stCorpName  用户代表企业（组织）名称
	 * @param stCorpCreditCode  用户代表企业（组织）统一社会信用代码
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/netreservation/saveRecordAndSendMsg.do")
	public void saveRecordAndSendMsg(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stUsername = req.getParameter("stUsername");
		String stIdCardNo = req.getParameter("stIdCardNo");
		String stTermId = req.getParameter("stTermId");
		String stResourceId = req.getParameter("stResourceId");
		String stMobile = req.getParameter("stMobile");
		String stCorpName = req.getParameter("stCorpName");
		String stCorpCreditCode = req.getParameter("stCorpCreditCode");
		if(StringUtils.isNotEmpty(stUsername)){
			stUsername = URLDecoder.decode(stUsername, "utf-8");
		}
		if(StringUtils.isNotEmpty(stCorpName)){
			stCorpName = URLDecoder.decode(stCorpName, "utf-8");
		}
		
		String appName = "d4a9b4df-7012-4a88-abba-fc7719c75158";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("stUsername", stUsername);
		json.put("stIdCardNo", stIdCardNo);
		json.put("stTermId", stTermId);
		json.put("stResourceId", stResourceId);
		json.put("stMobile", stMobile);
		json.put("stCorpName", stCorpName == null ? "" : stCorpName);
		json.put("stCorpCreditCode", stCorpCreditCode == null ? "" :stCorpCreditCode);
		
		String str = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		System.out.println("预约保存结果："+str);
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	private JSONArray sortJSONArray(JSONArray jsonArray, final String key){
		JSONArray sortJsonArray = new JSONArray();
	    List<JSONObject> jsonValue = new ArrayList<JSONObject>();
	    for (int i = 0; i < jsonArray.size(); i++){
	        jsonValue.add(jsonArray.getJSONObject(i));
	    }
	    Collections.sort(jsonValue, new Comparator<JSONObject>() {
	        @Override
	        public int compare(JSONObject o1, JSONObject o2) {
	            String val1 = o1.optString(key);
	            String val2 = o2.optString(key);
	            return val2.compareTo(val1);
	        }
	    });
	    for(int i = 0; i < jsonArray.size(); i++){
	        sortJsonArray.add(jsonValue.get(i));
	    }
		return sortJsonArray;
	}
}
