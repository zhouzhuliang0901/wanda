package com.wondersgroup.app.web;

import java.math.*;
import java.net.URLDecoder;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.app.bean.Oauth2Client;
import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.service.SelmItemService;
import com.wondersgroup.base.utils.AciJsonHelper;
import com.wondersgroup.base.utils.HttpUtil;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.service.SmsOrganService;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 事项表 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmItemController {
	
	@Autowired
	private SelmItemService selmItemService;
	@Autowired
	private SmsOrganService smsOrganService;

	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO_EDIT" })
	@RequestMapping("/app/selmItem/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stItemId = wrapper.getParameter(SelmItem.ST_ITEM_ID);
		String type = wrapper.getParameter("TYPE");
		if (!StringUtils.trimToEmpty(stItemId).isEmpty()) {
			SelmItem selmItem = selmItemService.get(stItemId);
			req.setAttribute(SelmItem.SELM_ITEM, selmItem);
		}
		String url = "";
		if("son".equals(type)){
			url="/app/sonItemAdd.jsp";
		}else{
			url="/app/itemedit.jsp";
		}
		return new ModelAndView(url);
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO_INFO" })
	@RequestMapping("/app/selmItem/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmItem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(SelmItem.ST_ITEM_ID);
		SelmItem selmItem = selmItemService.get(stItemId);
		if(selmItem.getStOrganId() != null && !selmItem.getStOrganId().isEmpty()){
			SmsOrgan smsOrgan = smsOrganService.get(selmItem.getStOrganId());
			selmItem.setStOrganId(smsOrgan.getStOrganName());
		}
		req.setAttribute(SelmItem.SELM_ITEM, selmItem);
		return new ModelAndView("/app/iteminfo.jsp");
	}
	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO" })
	@RequestMapping("/app/selmItem/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmItemService.itemList(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	
	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO_REMOVE","ITEM_INFO_BREMOVE" })
	@RequestMapping("/app/selmItem/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("设备信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmItemService.remove(httpReqRes);
			result.success().setMsg("设备信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO_SAVE" })
	@RequestMapping("/app/selmItem/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmItem selmItem = selmItemService.saveOrUpdate(httpReqRes);
			if (selmItem != null){
				result.setSuccess(true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	@RequestMapping("/app/selmItem/sonItemSave.do")
	public void sonItemSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmItem selmItem = selmItemService.sonItemSave(httpReqRes);
			if (selmItem != null){
				result = Result.getSuccessResult();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	/**
	 * 授权
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/selmItem/itemDeviceAdd.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		// SelmStatistics.ST_STATISTICS_ID
		String stItemId = httpReqRes
				.getParameter(SelmItem.ST_ITEM_ID);
		SelmItem selmItem = selmItemService.get(stItemId);
		req.setAttribute(SelmItem.SELM_ITEM, selmItem);
		return new ModelAndView("/app/itemListDevice.jsp");
	}
	
	/**
	  * 事项对应没有的设备
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/selmItem/noOCDevicelist.do")
	 public void noDeviceList(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = selmItemService.noOCDevicelist(httpReqRes);
	   req.setAttribute("obj", obj);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 /**
	  * 事项对应有的设备
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/selmItem/OCDevicelist.do")
	 public void deviceList(HttpServletRequest req, HttpServletResponse res)
			 throws IOException {
		 HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = selmItemService.itemDevicelist(httpReqRes);
			 req.setAttribute("obj", obj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 /**
		 * 读取设备绑定没有的事项
		 * @param req
		 * @param res
		 * @throws IOException
		 * @throws JSONException
		 */
		
		@RequestMapping("/app/selmDeviceItem/sqhDeviceItem.do")
		public void sqhDeviceItem(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			 JSONObject obj = null;
			 try {
				 obj = selmItemService.sqhDeviceItem(httpReqRes);
				 req.setAttribute("obj", obj);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 EasyUIHelper.writeResponse(res, obj.toString());
		}
	
		
		/**
		 * 通过中台调用事项库的事项信息，同步本系统事项编码
		 * @param req
		 * @param res
		 * @throws IOException
		 */
		@RequestMapping("/info/selmItem/getItemByClientgateway.do")
		public void getItemByClientgateway(HttpServletRequest req, 
				HttpServletResponse res) throws IOException{
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			/*
			 * 请求自助终端事项列表数据
			 */
			String appName = "f61ed760-d56d-11eb-9f8a-ad8386b1fddc"; 
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			//参数
			JSONObject param = new JSONObject();
			param.put("accesstoken", "1367187a-fc38-4971-8ba4-5bd606dcc647"); //请求令牌
			param.put("areacode", "");
			param.put("orgcode", "");
			param.put("fromtimestamp", "");
			param.put("limitnum", 1000);
			String paramString = param.toString();
			
			String contentType = "text/plain;charset=utf-8";
			//发送post请求
			String result = HttpUtil.doPost(head, paramString, contentType);
			//System.out.println("自助终端事项列表数据："+result);
			JSONObject resultJosn = JSONObject.fromObject(result);
			
			Set<String> terminalSet = new HashSet<String>(); //自助终端事项ID集合
			
			String lastTimestamp = resultJosn.getJSONObject("custom").optString("lastTimestamp");
			String sumNum = resultJosn.getJSONObject("custom").optString("sumNum");
			int sum = Integer.parseInt(sumNum);
			
			while(terminalSet.size() < sum){
				//System.out.println("参数："+lastTimestamp);
				JSONObject custom = resultJosn.optJSONObject("custom");
				JSONArray infoList = custom.optJSONArray("infoList");
				for(int i = 0; i < infoList.size(); i++){
					terminalSet.add(infoList.getJSONObject(i).optString("rowguid"));
				}
				param = new JSONObject();
				param.put("accesstoken", "1367187a-fc38-4971-8ba4-5bd606dcc647"); //请求令牌
				param.put("areacode", "");
				param.put("orgcode", "");
				param.put("fromtimestamp", lastTimestamp);
				param.put("limitnum", 1000);
				paramString = param.toString();
				result = HttpUtil.doPost(head, paramString, contentType);
				resultJosn = JSONObject.fromObject(result);
				lastTimestamp = resultJosn.getJSONObject("custom").optString("lastTimestamp");
			}
			
			/*
			 * 获取数据库事项
			 */
			List<SelmItem> siList = selmItemService.queryAllItem(httpReqRes);
			
			/*
			 * 请求自助终端事项详情信息数据
			 */
			appName = "1bd268c0-d57b-11eb-9f8a-ad8386b1fddc"; 
			signature = HttpUtil.getSignature(appName);
			head = HttpUtil.setHttpHeard(signature, appName);
			Map<String,String> itemMap = new HashMap<String,String>(); //办事指南事项ID集合
			JSONObject infoData = null;
			JSONArray terminalrelation = null;
			for(String emp : terminalSet){
				param = new JSONObject();
				param.put("accesstoken", "1367187a-fc38-4971-8ba4-5bd606dcc647");
				param.put("rowguid", emp);
				paramString = param.toString();
				result = HttpUtil.doPost(head, paramString, contentType);
				infoData = JSONObject.fromObject(result);
				JSONObject custom = infoData.optJSONObject("custom");
				if(null != custom){
					JSONObject terminal = custom.optJSONObject("auditdiyterminal");
					if(null != terminal){
						String diycode = terminal.optString("diycode");//自助终端事项编码
						if(null != diycode){
							for(SelmItem si : siList){
								String itemCode = si.getStExt3();
								if(diycode.equals(itemCode)){
									terminalrelation = custom.optJSONArray("auditdiyterminalrelation");
									if(terminalrelation.size() > 0){
										JSONObject ter =  terminalrelation.optJSONObject(0);
										String itemguid = ter.optString("itemguid"); //办事指南事项主键
										itemMap.put(itemCode, itemguid);//自助终端事项编码，办事指南事项主键
									}
								}
								
							}
						}
					}
				}
				
				
			}
			
			
			/* 
			 * 请求办事指南单条数据
			 */
			 
			appName = "d1f15aa3-6cf1-4ae2-8e94-03847b008ad6";
			signature = HttpUtil.getSignature(appName);
			head = HttpUtil.setHttpHeard(signature, appName);
			Map<String,String> itemMCode = new HashMap<String,String>();//存放自助终端编码和办事指南编码
			Iterator<Entry<String, String>> iter = itemMap.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<String, String> entry = iter.next();
				String itemTerCode = entry.getKey();
				String itemguid = entry.getValue();
				param = new JSONObject();
				param.put("accesstoken", "f27315e1-ec88-42e3-ae08-796ac4335999");
				param.put("rowguid", itemguid);
				paramString = param.toString();
				result = HttpUtil.doPost(head, paramString, contentType);
				infoData = JSONObject.fromObject(result);
				JSONObject custom = infoData.optJSONObject("custom");
				if(null != custom){
					JSONObject auditfwzn = custom.optJSONObject("auditfwzn");
					if(null != auditfwzn){
						String catalog_code = auditfwzn.optString("catalog_code");//办事指南基本编码
						if(null != catalog_code){
							itemMCode.put(itemTerCode, catalog_code);
						}
					}
					
				}
			}
			
			
			/*
			 *  更新数据库事项编码ST_ITEM_NO
			 */
			 
			Iterator<Entry<String,String>> codeIter = itemMCode.entrySet().iterator();
			Map<String,String> returnMap = new HashMap<String,String>();
			while(codeIter.hasNext()){
				Map.Entry<String,String> code = codeIter.next();
				String stExt3 = code.getKey();
				String stItemNo = code.getValue();
				int i = selmItemService.updateItem(stExt3,stItemNo);
				if(i == 1){
					returnMap.put(stExt3, stItemNo);
				}
			}
			
			
			JSONObject resultObj = new JSONObject();
			resultObj.put("success", true);
			resultObj.put("updatedata", returnMap);
			resultObj.put("updateSum", returnMap.size());
			
			AciJsonHelper.writeJsonPResponse(req, res, resultObj.toString());
		}
		
		/**
		 * 测试中台接口
		 * @param req
		 * @param res
		 * @throws IOException
		 */
		@RequestMapping("/info/selmItem/test.do")
		public void test(HttpServletRequest req, 
				HttpServletResponse res) throws IOException{
			
			String appName = "d1f15aa3-6cf1-4ae2-8e94-03847b008ad6"; 
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			//参数
			JSONObject param = new JSONObject();
			param.put("accesstoken", "f27315e1-ec88-42e3-ae08-796ac4335999"); //请求令牌
			param.put("rowguid", "9b73d196-1b59-49ed-b6b2-0edcd66bfc1a");
			
			String paramString = param.toString();
			
			String contentType = "text/plain;charset=utf-8";
			//发送post请求
			String result = HttpUtil.doPost(head, paramString, contentType);
			//System.out.println("自助终端事项列表数据："+result);
			JSONObject resultJosn = JSONObject.fromObject(result);

			JSONObject resultObj = new JSONObject();
			
			resultObj.put("data", resultJosn);
			
			AciJsonHelper.writeJsonPResponse(req, res, resultObj.toString());
		}
		
		
}
