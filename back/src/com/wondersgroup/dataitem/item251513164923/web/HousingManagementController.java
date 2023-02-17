package com.wondersgroup.dataitem.item251513164923.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item251513164923.utils.HouseUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

/**
 * 房管局事项
 * @author wanda
 *
 */
@Controller
public class HousingManagementController {
	
	/**
	 * 白蚁防治单位自主查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/termiteControlQuery.do")
	public void termiteControlQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String areaName = req.getParameter("areaName");
		if(StringUtils.isNotEmpty(areaName)){
			areaName = URLDecoder.decode(areaName, "utf-8");
		}
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "041af185-6ad0-4d8e-9bd6-f2292c143078";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "041af185-6ad0-4d8e-9bd6-f2292c143078";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("hpb_id", areaName);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	
	/*
	 * 古楼公路348弄12号1402室，面积 107
	 * 金港花园二期
	 * communityId：1805251174077171
	 * unitId：120303032228229
	 * houId：120303032228397
	 * 
	 */
	/**
	 * 小区与房屋列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/getHouseInfo.do")
	public void getHouseInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityName = req.getParameter("communityName");
		if (StringUtils.isNotEmpty(communityName)) {
			communityName = URLDecoder.decode(communityName, "uf-8");
		}
		communityName = communityName == null ? "" : communityName;
		String appName = "e50c1364-8dba-4916-9ce6-d0576ace3956";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?roleId=201503100001017&communityName=" + communityName
				+ "&pageNumber=1&pageSize=" + Integer.MAX_VALUE / 2;
		String getResult = HttpUtil.doGet(head, url, "");
		System.out.println(getResult);
		AciJsonHelper.writeJsonPResponse(req, res, getResult);
	}
	
	/**
	 * 用户选择小区
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/getCommunityId.do")
	public void getCommunityId(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String name = req.getParameter("name");
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "uf-8");
		}
//		name = "金港花园";
		String appName = "a00174e6-b934-4c84-b0fc-fb89a66dd00b";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", name);
		paramMap.put("pageNumber", "1");
		paramMap.put("pageSize", Integer.toString(Integer.MAX_VALUE / 2));
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		
		System.out.println(postResult);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 选择门牌幢
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/getUnitId.do")
	public void getUnitId(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityId = req.getParameter("communityId");
		String unitName = req.getParameter("unitName");
		if (StringUtils.isNotEmpty(unitName)) {
			unitName = URLDecoder.decode(unitName, "uf-8");
		}
//		communityId = "1805251174077171";
//		unitName = "古楼公路348弄12号";
		String appName = "b62c7e9d-08a7-4be3-98d0-14bbb3829c78";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("communityId", communityId);
		paramMap.put("unitName", unitName);
		paramMap.put("pageNumber", "1");
		paramMap.put("pageSize", Integer.toString(Integer.MAX_VALUE / 2));
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		System.out.println(postResult);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 用户选择分户
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/getHouseId.do")
	public void getHouseId(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityId = req.getParameter("communityId");
		String unitId = req.getParameter("unitId");
		String houAddress = req.getParameter("houAddress");
		if (StringUtils.isNotEmpty(houAddress)) {
			houAddress = URLDecoder.decode(houAddress, "uf-8");
		}
//		communityId = "1805251174077171";
//		unitId = "120303032228229";
//		houAddress = "1402室";
		String appName = "cc7f8ef0-03dd-48ca-b5c8-8e6e058fa59a";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("communityId", communityId);
		paramMap.put("unitId",unitId);
		paramMap.put("houAddress",houAddress);
		paramMap.put("pageNumber", "1");
		paramMap.put("pageSize", Integer.toString(Integer.MAX_VALUE / 2));
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		System.out.println(postResult);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 验证用户绑定房屋面积
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/checkHouse.do")
	public void checkHouseArea(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String houId = req.getParameter("houId");
		String area = req.getParameter("area");
//		houId = "120303032228397";
//		area = "107";
		String appName = "68f7dc92-4746-4771-9895-4ebe8bf3fc01";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("houId", houId);
		paramMap.put("area",area);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		System.out.println(postResult);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 添加房屋
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/addHouse.do")
	public void addHouse(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityId = req.getParameter("communityId");
		String unitId = req.getParameter("unitId");
		String houId = req.getParameter("houId");
//		communityId = "1805251174077171";
//		unitId = "120303032228229";
//		houId = "120303032228397";
		String appName = "f0642909-f301-4be0-822a-39cd14fdc2d8";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("communityId", communityId);
		paramMap.put("unitId", unitId);
		paramMap.put("houId", houId);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		String postResult = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 删除用户绑定分户信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/deleteHouse.do")
	public void deleteHouse(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String houId = req.getParameter("houId");
		String phone = req.getParameter("phone");
//		houId = "120303032228397";
//		phone = "13545161135";
		String appName = "6d617721-bd83-436b-ae1f-86aa95734d99";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"))
				+ "?houId="+houId+"&phone=" + phone;
		String getResult = HttpUtil.doGet(head, url, "");
		System.out.println(getResult);
		AciJsonHelper.writeJsonPResponse(req, res, getResult); 
	}
	
	/**
	 * 我的小区查询
	 * 1、小区基本信息（community）
	 * 2、小区管理处信息（managementOffice）
	 * 3、业主大会及业委会信息（ownersCongress）
	 * 4、物业服务企业信息（property）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/getCommunityInfo.do")
	public void getCommunityInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityId = req.getParameter("communityId");
		String communityAppName = "c14f2e4e-a8e7-4423-80b0-d0d9313c14a2";
		String managementOfficeAppName = "c0e20354-6055-4434-b804-f1046c7984eb";
		String ownersCongressAppName = "b940d5fe-818b-4d39-bb25-ecab78e94ad7";
		String propertyAppName = "e0944727-b236-48d4-8a2f-2d9f7af0eb86";
//		communityId = "1805251174077171";
		String communitySignature = HttpUtil.getSignature(communityAppName);
		Map<String, String> communityHead = HttpUtil.setHttpHeard(communitySignature, communityAppName);
		communityHead.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		String managementOfficeSignature = HttpUtil.getSignature(managementOfficeAppName);
		Map<String, String> managementOfficeHead = HttpUtil.setHttpHeard(managementOfficeSignature, managementOfficeAppName);
		managementOfficeHead.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		String ownersCongressSignature = HttpUtil.getSignature(ownersCongressAppName);
		Map<String, String> ownersCongressHead = HttpUtil.setHttpHeard(ownersCongressSignature, ownersCongressAppName);
		ownersCongressHead.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		String propertySignature = HttpUtil.getSignature(propertyAppName);
		Map<String, String> propertyHead = HttpUtil.setHttpHeard(propertySignature, propertyAppName);
		propertyHead.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("communityId", communityId);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		
		String communityResult = HttpUtil.doPost(communityHead, reqEntity);
		String managementOfficeResult = HttpUtil.doPost(managementOfficeHead, reqEntity);
		String ownersCongressResult = HttpUtil.doPost(ownersCongressHead, reqEntity);
		String propertyResult = HttpUtil.doPost(propertyHead, reqEntity);
		
		JSONObject json = new JSONObject();
		json.put("code", 200);
		json.put("msg", "success");
		JSONObject data = new JSONObject();
		try{
			JSONObject communityJson = JSONObject.fromObject(communityResult);
			if("200".equals(communityJson.optString("code"))){
				data.put("community", communityJson.optJSONObject("data").optJSONArray("content"));
			} else {
				data.put("community", "");
			}
		} catch (Exception e) {
			data.put("community", "");
			Log.debug("小区基本信息返回数据异常："+e.getMessage());
		}
		try{
			JSONObject managementOfficeJson = JSONObject.fromObject(managementOfficeResult);
			if("200".equals(managementOfficeJson.optString("code"))){
				data.put("managementOffice", managementOfficeJson.optJSONObject("data").optJSONArray("content"));
			} else {
				data.put("managementOffice", "");
			}
		} catch (Exception e) {
			data.put("managementOffice", "");
			Log.debug("小区管理处信息返回数据异常："+e.getMessage());
		}
		try{
			JSONObject ownersCongressJson = JSONObject.fromObject(ownersCongressResult);
			if("200".equals(ownersCongressJson.optString("code"))){
				data.put("ownersCongress", ownersCongressJson.optJSONObject("data").optJSONArray("content"));
			} else {
				data.put("ownersCongress", "");
			}
		} catch (Exception e) {
			data.put("ownersCongress", "");
			Log.debug("业主大会及业委会信息返回数据异常："+e.getMessage());
		}
		try{
			JSONObject propertyJson = JSONObject.fromObject(propertyResult);
			if("200".equals(propertyJson.optString("code"))){
				data.put("property", propertyJson.optJSONObject("data").optJSONArray("content"));
			} else {
				data.put("property", "");
			}
		} catch (Exception e) {
			data.put("property", "");
			Log.debug("物业服务企业信息返回数据异常："+e.getMessage());
		}
		json.put("data", data);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 业主大会成员
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/getOwnersCongress.do")
	public void getOwnersCongress(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String ownersCongressId = req.getParameter("ownersCongressId");
//		ownersCongressId = "1805251174082940";
		String appName = "65c1ae06-9fb2-4ebd-9b4d-500c5671fb20";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		Map<String, String> directorParamMap = new HashMap<String, String>();
		directorParamMap.put("ownersCongressId", ownersCongressId);
		directorParamMap.put("type", "1");
		Map<String, String> viceDirectorparamMap = new HashMap<String, String>();
		viceDirectorparamMap.put("ownersCongressId", ownersCongressId);
		viceDirectorparamMap.put("type", "3");
		
		HttpEntity directorReqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(directorParamMap), "utf-8");
		String directorResult = HttpUtil.doPost(head, directorReqEntity);
		HttpEntity viceDirectorReqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(viceDirectorparamMap), "utf-8");
		String viceDirectorResult = HttpUtil.doPost(head, viceDirectorReqEntity);
		JSONObject json = new JSONObject();
		json.put("code", 200);
		json.put("msg", "success");
		JSONObject data = new JSONObject();
		try{
			JSONObject directorResultJson = JSONObject.fromObject(directorResult);
			if("200".equals(directorResultJson.optString("code"))){
				JSONArray arr = directorResultJson.optJSONObject("data").optJSONArray("content");
				String name = "";
				for(int i = 0;i<arr.size();i++){
					JSONObject o = arr.optJSONObject(i);
					 name += o.optString("MEMBERNAME")+",";;
				}
				data.put("directorName", name);
			} else {
				data.put("directorName", "");
			}
		} catch (Exception e) {
			data.put("directorName", "");
			Log.debug("业主大会成员-主任查询结果异常："+e.getMessage());
		}
		try{
			JSONObject viceDirectorResultJson = JSONObject.fromObject(viceDirectorResult);
			if("200".equals(viceDirectorResultJson.optString("code"))){
				JSONArray arr = viceDirectorResultJson.optJSONObject("data").optJSONArray("content");
				String name = "";
				for(int i = 0;i<arr.size();i++){
					JSONObject o = arr.optJSONObject(i);
					 name += o.optString("MEMBERNAME")+",";
				}
				data.put("viceDirectorName", name);
			} else {
				data.put("viceDirectorName", "");
			}
		} catch (Exception e) {
			data.put("viceDirectorName", "");
			Log.debug("业主大会成员-副主任查询结果异常："+e.getMessage());
		}
		json.put("data", data);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 公共收益
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/publicBenefits.do")
	public void publicBenefits(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityId = req.getParameter("communityId");
		String appName = "a9b6f1df-fb97-45f7-b431-dece4068aee8";
//		communityId = "1805251174077171";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("communityId", communityId);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		
		String postResult = HttpUtil.doPost(head, reqEntity);
		System.out.println(postResult);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 维修资金账目公布
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/maintenanceFund.do")
	public void maintenanceFund(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String communityId = req.getParameter("communityId");
//		communityId = "1805251174077171";
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "0ec9f302-4a08-4a62-9c30-68500f47442f";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "1c3bcbd5-213b-43ca-b1e8-6e12f4d9cd75";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("communityId", communityId);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		
		String postResult = HttpUtil.doPost(head, reqEntity);
		System.out.println(postResult);
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	/**
	 * 维修资金查询
	 * 维修资金列表、维修资金列表2
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/housingManagement/maintenanceFundDetail.do")
	public void maintenanceFundDetail(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String tokenSNO = req.getParameter("tokenSNO");
		String houId = req.getParameter("houId");
		String flag = req.getParameter("flag");
//		houId = "120303032228397";
		String acctAppName = "050b6c8d-6be0-4440-9b71-33864c7f01b9";
		String acctSignature = HttpUtil.getSignature(acctAppName);
		Map<String, String> acctHead = HttpUtil.setHttpHeard(acctSignature, acctAppName);
		acctHead.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		String listAppName = "95d65f90-1164-4702-a413-dc05cdfa5023";
		String listSignature = HttpUtil.getSignature(listAppName);
		Map<String, String> listHead = HttpUtil.setHttpHeard(listSignature, listAppName);
		listHead.put("Authorization", HouseUtil.getAuthorization(tokenSNO));
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("hou_id", houId);
		paramMap.put("acct_flag", flag);
		HttpEntity reqEntity = new UrlEncodedFormEntity(
				HttpUtil.convertMapToPair(paramMap), "utf-8");
		
		String acctResult = HttpUtil.doPost(acctHead, reqEntity);
		String listResult = HttpUtil.doPost(listHead, reqEntity);
		
		JSONObject json = new JSONObject();
		json.put("code", 200);
		json.put("msg", "success");
		JSONObject data = new JSONObject();
		try{
			JSONObject acctJson = JSONObject.fromObject(acctResult);
			if("200".equals(acctJson.optString("code"))){
				data.put("account", acctJson.optJSONObject("data").optJSONObject("content"));
			} else {
				data.put("account", "");
			}
		} catch (Exception e) {
			data.put("account", "");
			Log.debug("维修资金列表返回数据异常："+e.getMessage());
		}
		try{
			JSONObject listJson = JSONObject.fromObject(listResult);
			if("200".equals(listJson.optString("code"))){
				data.put("list", listJson.optJSONObject("data").optJSONArray("content"));
			} else {
				data.put("list", "");
			}
		} catch (Exception e) {
			data.put("list", "");
			Log.debug("维修资金列表2返回数据异常："+e.getMessage());
		}
		json.put("data", data);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
}
