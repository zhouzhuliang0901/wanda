package com.wondersgroup.dataitem.item259192115322.web;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item259192115322.dao.PublicGovInfoDao;

@Controller
@SuppressWarnings("unused")
public class PublicGovInfoController {
	
	// 政务公开数据接口身份令牌
	private static String token;
	
	@Autowired
	private PublicGovInfoDao publicGovInfoDao;
	
	/**
	 * 获取栏目结构
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicGovInfo/getchannelTree.do")
	public void getchannelTree(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
//		importChannel("");
		// 普通稿件（0001-173a8f59416d46ec8b5a49131ae3948b）；政务稿件（0001-b222e5de87cd4950931d6a8dd25cc66d）
		String modelId = req.getParameter("modelId");
		String channelId = req.getParameter("channelId");
		String appName = "26f6f372-8c97-4512-8507-0a9a9740eee6";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("token", token);
		head.put("siteId", "0001");
		
		JSONObject json = new JSONObject();
		json.put("displayFlag", "1");
//		json.put("modelId", modelId);
		json.put("orderBy", "");
		json.put("parentId", channelId);
		json.put("recursion", false);
		System.out.println("获取栏目树参数："+json.toString());
		
		String result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			int code = JSONObject.fromObject(result).optInt("code");
			if(code != 0){
				Log.debug("令牌无效或过期，重新获取");
				token = getToken();
				head.put("token", token);
				result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
			}
		} catch (Exception e) {}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 获取稿件列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicGovInfo/getContentPage.do")
	public void getContentPage(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String channelId = req.getParameter("channelId");
		// 普通稿件（0001-173a8f59416d46ec8b5a49131ae3948b）；政务稿件（0001-b222e5de87cd4950931d6a8dd25cc66d）
		String modelId = req.getParameter("modelId");
		// display（发布日期）；create（创建日期）；top_display（置顶-发布日期）；top_create（置顶-创建日期）
		String orderField = req.getParameter("orderField");
		// asc（升序）；desc（降序）
		String order = req.getParameter("order");
		String recursion = req.getParameter("recursion");
		String appName = "09cefc9d-7cf4-4244-ad19-25744dffaa67";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("token", token);
		head.put("siteId", "0001");
		
		JSONObject json = new JSONObject();
		json.put("channelId", channelId);
//		json.put("masterStatus", "0");
//		json.put("modelId", modelId);
		json.put("orderBy", orderField+"_"+order);
		json.put("pageNo", 1);
		json.put("pageSize", 500);
		if(StringUtils.isNotEmpty(recursion)){
			json.put("recursion", Boolean.valueOf(recursion));
		} else {
			json.put("recursion", false);
		}
		json.put("createUserId", null);
		json.put("updateUserId", null);
		json.put("title ", "");
		
		System.out.println("获取cms稿件列表参数："+json.toString());
		String result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			int code = JSONObject.fromObject(result).optInt("code");
			if(code != 0){
				Log.debug("令牌无效或过期，重新获取");
				token = getToken();
				head.put("token", token);
				result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
			}
		} catch (Exception e) {}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 获取稿件详情
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/publicGovInfo/getContentDetail.do")
	public void getContentDetail(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String contentId = req.getParameter("contentId");
		String appName = "bef20fd7-7068-4fb6-abe3-f032b5c56426";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("token", token);
		head.put("siteId", "0001");
		
		JSONObject json = new JSONObject();
		json.put("id", contentId);
		String result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			int code = JSONObject.fromObject(result).optInt("code");
			if(code != 0){
				Log.debug("令牌无效或过期，重新获取");
				token = getToken();
				head.put("token", token);
				result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
			}
		} catch (Exception e) {}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	private static String getToken(){
		String appKey = "zzzdapi_sh";
		String appSecret = "cebf9e5ec5094bb0a4f27d26ebebfa7b";
		String appName = "10efa8a4-3506-4508-a8ea-547b0604c6d1";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("appKey", appKey);
		json.put("appSecret", appSecret);
		String result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			token = JSONObject.fromObject(result).optJSONObject("data").optString("token");
		} catch (Exception e) {
			Log.debug("政务公开获取身份令牌失败------"+e.getMessage());
			token = "";
		}
		return token;
	}
	
	@PostConstruct
	public void init() {
		token = getToken();
		System.out.println("政务公开获取身份令牌------"+token);
	}
	
	private void importChannel(String channelId){
		// 普通稿件（0001-173a8f59416d46ec8b5a49131ae3948b）；政务稿件（0001-b222e5de87cd4950931d6a8dd25cc66d）
		String appName = "26f6f372-8c97-4512-8507-0a9a9740eee6";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("token", token);
		head.put("siteId", "0001");
		
		JSONObject json = new JSONObject();
		json.put("displayFlag", "1");
		json.put("modelId", "0001-173a8f59416d46ec8b5a49131ae3948b");
		json.put("orderBy", "");
		json.put("parentId", channelId);
		json.put("recursion", false);
		
		String result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
		try{
			int code = JSONObject.fromObject(result).optInt("code");
			if(code != 0){
				Log.debug("令牌无效或过期，重新获取");
				token = getToken();
				head.put("token", token);
				result = HttpUtil.doPost(head, json.toString(), "application/json;charset=utf-8");
			}
			JSONArray arry = JSONObject.fromObject(result).optJSONArray("data");
			for(int iLoop = 0;iLoop<arry.size();iLoop++){
				JSONObject obj = arry.getJSONObject(iLoop);
				int a = publicGovInfoDao.addChannel(obj);
				System.out.println(obj.optString("name")+"=============="+a);
				if(obj.optBoolean("hasChild")){
					channelId = obj.optString("id");
					importChannel(channelId);
				}
			}
		} catch (Exception e) {
			Log.debug(e);
		}
	}
}
