package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.utils.AciJsonHelper;
import reindeer.base.utils.RequestWrapper;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;
import com.wondersgroup.selfapi.service.SelfDeclareService;
import com.wondersgroup.selfapi.util.RSAUtils;
import com.wondersgroup.selfapi.util.XhZzsbUtils;

/**
 * 在线申请（线上办理）
 * 
 * @author lenovo
 * 
 */
@RestController
public class OnlineApplyController {

	@Autowired
	private SelfDeclareService selfDeclareService;

	/**
	 * 获取个人和法人的目录列表
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/onlineapply/getItemTheme")
	public void getItemTheme(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		RequestWrapper wrapper = new RequestWrapper(req);
		//String areaCode = wrapper.getParameter("areaCode");
		String type = wrapper.getParameter("type");
		//List<ItemTheme> list = selfDeclareService.getItemTheme(areaCode, type);
		List<ItemTheme> list = selfDeclareService.getItemTheme(type);
		JSONArray array = new JSONArray();
		JSONObject jsonResult = new JSONObject();
		Iterator<ItemTheme> iter = list.iterator();
		while (iter.hasNext()) {
			ItemTheme theme = iter.next();
			JSONObject json = new JSONObject();
			json.put("itemTypeCode", theme.getItemTypeCode());
			json.put("itemTypeName", theme.getItemTypeName());
			array.add(json);
		}
		jsonResult.put("total", array.size());
		jsonResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}

	/**
	 * 查询区下所有的自助申报事项的部门
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/selfapi/onlineapply/getDeptInArea")
	public void getDeptInArea(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		//String areaCode = req.getParameter("areaCode");
		String type = req.getParameter("type");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNoneBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = selfDeclareService.getDeptInArea(pageSize,
				currentPage, areaCode);
		OrganNodeInfoPage page = selfDeclareService.getDeptInArea(pageSize,
				currentPage, type);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());

	}*/

	/**
	 * 查询个人和法人的下级目录信息
	 * (查询个人、法人、部门目录中的事项)
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */

	@RequestMapping("/selfapi/onlineapply/getItemInTheme")
	public void getItemInTheme(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		RequestWrapper wrapper = new RequestWrapper(req);
		String themeCode = wrapper.getParameter("themeCode");
		//String areaCode = wrapper.getParameter("areaCode");
		String type = wrapper.getParameter("type");
		/*List<ItemSet> list = selfDeclareService.getItemInTheme(themeCode,
				areaCode, type);*/
		List<ItemSet> list = selfDeclareService.getItemInTheme(themeCode, type);
		JSONArray array = new JSONArray();
		JSONObject josnResult = new JSONObject();
		Iterator<ItemSet> iter = list.iterator();
		while (iter.hasNext()) {
			ItemSet item = iter.next();
			JSONObject josn = new JSONObject();
			josn.put("stItemNo", item.getStItemNo());
			josn.put("stItemName", item.getStItemName());
			//josn.put("organCode", item.getOrganCode());
			array.add(josn);
		}
		josnResult.put("total", array.size());
		josnResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
	}

	/**
	 * 查询部门下面的所有事项列表
	 * 
	 * @throws IOException
	 */
	/*@RequestMapping("/selfapi/onlineapply/getItemListByOrganCodeForPage")
	public void getItemListByOrganCodeForPage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String organCode = req.getParameter("organCode");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNoneBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNoneBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareService.getItemListByOrganCodeForPage(
				organCode, pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
*/
	/**
	 * 查询根据事项编码获取其下的情形事项
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/onlineapply/getWindowItemStatusList")
	public void getWindowItemStatusList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		//String deptCode = req.getParameter("deptCode");
		String itemNo = req.getParameter("itemNo");

		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNoneBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNoneBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		/*WindowItemStatusPage page = selfDeclareService.getWindowItemStatusList(
				pageSize, currentPage, deptCode, itemNo);*/
		WindowItemStatusPage page = selfDeclareService.getWindowItemStatusList(
				pageSize, currentPage, itemNo);

		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	
	
	
	
	
	
	/**
	 * 9、保存办件信息
	 */
	@RequestMapping("/selfapi/onlineapply/saveApply")
	public void saveApply(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 办件编码 
		String applyNo = req.getParameter("applyNo");
		// 事项代码 0103022000-04-1
		String itemCode = req.getParameter("itemCode");
		// 事项名称
		String itemName = req.getParameter("itemName");
		// 办理对象类型 个人和法人
		String targetTypeName = req.getParameter("targetTypeName");
		// 办理对象姓名 企业名称 个人姓名
		String targetName = req.getParameter("targetName");
		// 办理对象编号 个人 身份证 企业 统一社会编码
		String targetNo = req.getParameter("targetNo");
		// 影用户id
		String userId = req.getParameter("userId");
		// 申请人姓名
		String username = req.getParameter("username");
		// 申请人证件类型 'a': '身份证', 'b': '护照', 'c': '军官证'
//		String licenseTypeValue = req.getParameter("licenseTypeValue");
//		String licenseTypeName = req.getParameter("licenseTypeName");
		// 申请人证件编码
		String licenseNo = req.getParameter("licenseNo");
		// 申请人手机号
		String mobile = req.getParameter("mobile");
		// 受理部门  ------不是（办理点编号）
		String departCode = req.getParameter("departCode");
		// 受理部门  ------不是（办理点名称）
		String departName = req.getParameter("departName");
		// 办件来源 网上申请 线下受理
		String source = req.getParameter("source");
		// 申请内容
		String content = req.getParameter("content");
		// 保存时间
		String opTime = req.getParameter("opTime");
		
		String regionCode = "";
		// 办理点编号
		String bldCode = "";
		// 办理点名称
		String bldName = "";
		// 通过办理事项编号获得办理点
		String str = XhZzsbUtils.getTocken("", "");
		String access_token = JSONObject.fromObject(str).getString(
				"access_token");
		System.out.println("------查询办理点并保存办理点信息---------");
		String resultCode = XhZzsbUtils.getItemAreaList(access_token, itemCode);
		net.sf.json.JSONObject resultJson = net.sf.json.JSONObject.fromObject(resultCode);
		net.sf.json.JSONArray resultArr = resultJson.optJSONArray("data");
		for(int iLoop = 0;iLoop<resultArr.size();iLoop++){
			JSONObject json = resultArr.optJSONObject(iLoop);
			regionCode = json.optString("regionCode");
			if(StringUtils.isNotEmpty(regionCode) 
					&& regionCode.substring(4, 6).equals(departCode.substring(4, 6))){
				bldCode = json.optString("bldCode");
				bldName = json.optString("bldName");
				break;
			}
		}
		
//		String applyInfo = dealParameterSaveApply(applyNo, itemCode, itemName,
//				targetType, targetName, targetNo, userId, username,
//				licenseType, licenseNo, mobile, departCode, departName, source,
//				content, opTime, bldCode, bldName);
//		String result = selfDeclareService.saveApply(applyInfo);
		String applyInfo = newDealParameterSaveApply(applyNo, itemCode, itemName,
				targetTypeName, targetName, targetNo, userId, username,
				licenseNo, mobile, departCode, departName, source,
				content, opTime, bldCode, bldName);
		String result = selfDeclareService.saveApply(applyInfo);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	
	
	private String newDealParameterSaveApply(String applyNo, String itemCode,
			String itemName, String targetTypeName, String targetName,
			String targetNo, String userId, String username,
			String licenseNo, String mobile,
			String departCode, String departName, String source,
			String content, String opTime, String bldCode, String bldName) {
		
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject info = new JSONObject();
		JSONObject licenseType = new JSONObject();
		JSONObject targetType = new JSONObject();
		
		targetType.put("name", targetTypeName);
		if("个人".equals(targetTypeName)){
			targetType.put("value", "a");
		} else if("法人".equals(targetTypeName)){
			targetType.put("value", "b");
		}
		
		licenseType.put("value", "a");
		licenseType.put("name", "身份证");
		
		info.put("username", username);
		info.put("licenseType", licenseType);
		info.put("targetType", targetType);
		info.put("licenseNo", licenseNo);
		info.put("applyReason", "");
		info.put("targetName", targetName);
		info.put("targetNo", targetNo);
		info.put("LEGAL_PERS_NAME", "");
		info.put("CONTACT_NAME", "");
		info.put("mobile", mobile);
		
		data.put("userId",userId);
		data.put("departCode",bldCode);
		data.put("itemCode",itemCode);
		data.put("info",info);
		System.out.println("保存办件信息参数：" + data.toString());
		String result = "";
		try {
			System.out.println("加密密钥："+Config.get("wfc.ywtb.public.key"));
			result = RSAUtils.publicEncrypt(data.toString(), RSAUtils.getPublicKey(Config.get("wfc.ywtb.public.key")));
			System.out.println("加密参数："+result);
	        String decodedData = RSAUtils.privateDecrypt(result, RSAUtils.getPrivateKey(Config.get("wfc.ywtb.private.key")));
	        System.out.println("解密后文字: \r\n" + decodedData);
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		
		json.put("data", result);
		return json.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
}
