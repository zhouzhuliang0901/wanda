package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import reindeer.base.utils.RequestWrapper;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.tables.SQItems;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemSq;
import com.wondersgroup.selfapi.bean.SelmZhallItemOrgan;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;
import com.wondersgroup.selfapi.service.SelfDeclareSqService;

/**
 * 办事指南接口，通过“上海市政务服务事项管理系统数据发布接口服务”获取数据
 * @author wanda
 *
 */
@Controller
public class SelfDeclareForSQController {
	
	@Autowired
	private SelfDeclareSqService selfDeclareSqService;
	
	 /**
     * 获取事项列表（中台服务：上海市政务服务事项管理系统数据发布接口服务）
     * 
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/selfDeclare/getItemInfoByXD.do")
    public void getItemInfoByXD(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	// y：获取社区事项；n：获取非社区（市区）事项
    	String flag = req.getParameter("flag");
    	// 市区区划编码
    	String areacode = req.getParameter("areacode");
    	
    	String accesstoken = "f27315e1-ec88-42e3-ae08-796ac4335999";
    	
    	String appNameForList = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			if("y".equals(flag)){
				appNameForList = "a2cca1bc-b4bc-4f36-b7a0-1869181ea54b";
			} else if("n".equals(flag)){
				appNameForList = "7a3de8ad-baeb-4b41-b77b-c3cb78002b13";
			}
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			if("y".equals(flag)){
				appNameForList = "78672147-b8a3-4b4e-af07-355d4608ce0d";
			} else if("n".equals(flag)){
				appNameForList = "3d45bc55-b6cd-4dad-8372-f4cf62869541";
			}
		}
    	String signatureForList = HttpUtil.getSignature(appNameForList);
        Map<String, String> headForList = HttpUtil.setHttpHeard(signatureForList, appNameForList);
        
    	String appNameForInfo = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "966a4c9d-dfa6-4e0c-87bc-53643f5ab3de";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "d1f15aa3-6cf1-4ae2-8e94-03847b008ad6";
		}
    	String signatureForInfo = HttpUtil.getSignature(appNameForInfo);
        Map<String, String> headForInfo = HttpUtil.setHttpHeard(signatureForInfo, appNameForInfo);
        
        String fromtimestamp = "";
        JSONObject data = new JSONObject();
        data.put("accesstoken", accesstoken);
        data.put("qlkind", "");
        if("y".equals(flag)){
        	data.put("arealevel", "");
        } else if("n".equals(flag)){
        	 data.put("areacode", areacode);
             data.put("orgcode", "");
             data.put("businesscode", "");
             data.put("limitnum", "");
        }
        
        String contentType = "application/json;charset=utf-8";
        JSONArray fwznList = new JSONArray();
        do{
        	data.put("fromtimestamp", fromtimestamp);
        	System.out.println("列表入参："+data.toString());
        	String result = getResponse(headForList,data.toString(),contentType);
          	try{
          		JSONObject json = JSONObject.fromObject(result);
          		String code = json.optJSONObject("status").optString("code");
          		if("success".equals(code)){
          			fwznList = json.optJSONObject("custom").optJSONArray("fwznList");
          			int sumNum = json.optJSONObject("custom").optInt("sumNum");
          			System.out.println("事项总数："+sumNum);
          			String lastTimestamp = json.optJSONObject("custom").optString("lastTimestamp");
          			fromtimestamp = lastTimestamp;
          			System.out.println("lastTimestamp："+lastTimestamp);
          			for(int iLoop = 0;iLoop<fwznList.size();iLoop++){
          		        JSONObject infoParam = new JSONObject();
          		        infoParam.put("accesstoken", accesstoken);
          		        infoParam.put("rowguid", fwznList.getJSONObject(iLoop).optString("rowguid"));
          		        String info = getResponse(headForInfo,infoParam.toString(),contentType);
          		        
          		        JSONObject infoJson = JSONObject.fromObject(info);
          		        JSONObject auditfwzn = infoJson.optJSONObject("custom").optJSONObject("auditfwzn");
          		        JSONObject auditfwznextend = infoJson.optJSONObject("custom").optJSONObject("auditfwznextend");
          		        
          		        SelmZhallItemSq itemInfo = new SelmZhallItemSq();
          		        itemInfo.setStId(fwznList.getJSONObject(iLoop).optString("rowguid"));
          		        itemInfo.setStItemNo(auditfwzn.optString("catalog_code"));// 事项代码
          		        itemInfo.setStItemName(auditfwzn.optString("ql_name"));// 事项名称
          		        itemInfo.setStItemType(SQItems.Type.GetName(auditfwzn.optString("ql_kind")));
          		        itemInfo.setStOrgCode(auditfwzn.optString("org_code"));
          		        String orgName = auditfwznextend.optString("bljg");
          		        if(orgName.length() >  50){
          		        	orgName = "";
          		        }
          		        itemInfo.setStOrgName(orgName);
          		        itemInfo.setDtCreate(new Timestamp(System.currentTimeMillis()));
          		        itemInfo.setStRemove(0);
          		        String gerenzt = auditfwznextend.optString("rightclass_gerenzt");
          		        String qiyezt = auditfwznextend.optString("rightclass_qiyezt");
          		        itemInfo.setStDicCodeGr(gerenzt);
          		        itemInfo.setStDicCodeFr(qiyezt);
          		        String nmBelong = "";
          		        String ext2 = "";
          		        if(StringUtils.isNoneEmpty(gerenzt)){
          		        	nmBelong += "1";
          		        	ext2 += gerenzt+",";
          		        }
          		        if(StringUtils.isNoneEmpty(qiyezt)){
          		        	nmBelong += "2";
          		        	ext2 += qiyezt+",";
          		        }
	          		    if("y".equals(flag)){
	          		    	// 社区事项不区分个人法人，故将个人法人主题合并
	          		    	itemInfo.setStExt2(ext2);
	          		    }
          		        itemInfo.setNmBelong(nmBelong);
          		        itemInfo.setStItemTenCode(auditfwznextend.optString("st_ten_code"));
          		        itemInfo.setStTransactName(auditfwznextend.optString("ywqx"));
          		        
          				try{
             				selfDeclareSqService.addZhallItem(itemInfo);
             			} catch (Exception e) {
             				Log.debug("事项："+itemInfo.getStItemName()+"，ID："+itemInfo.getStId()+"，保存失败："+e.getMessage().toString());
        				}
          			}
          		}
          	} catch (Exception e) {
          		Log.debug("运行异常："+e.getMessage().toString());
    		}
        } while(fwznList.size() == 50);
      	AciJsonHelper.writeJsonPResponse(req, res, "事项信息获取完成！");
    }
    
    /**
     * 请求组织机构列表数据（中台服务：上海市政务服务事项管理系统数据发布接口服务）
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/selfDeclare/getOrgListInfo.do")
    public void getOrgListInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String areacode = req.getParameter("areacode");
    	
    	String accesstoken = "f27315e1-ec88-42e3-ae08-796ac4335999";
		
		String appNameForList = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForList = "0dbbb1b4-6337-4229-8129-03fb47cc327c";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForList = "00886b23-01d0-4dfe-bdcf-a3f0d863811e";
		}
		String signatureForList = HttpUtil.getSignature(appNameForList);
        Map<String, String> headForList = HttpUtil.setHttpHeard(signatureForList, appNameForList);
		
		String appNameForInfo = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "e9b9b609-e471-481a-a387-414596991b27";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "0e5d6379-8497-455c-8354-7711e496a2ce";
		}
		String signatureForInfo = HttpUtil.getSignature(appNameForInfo);
        Map<String, String> headForInfo = HttpUtil.setHttpHeard(signatureForInfo, appNameForInfo);
        
        String fromtimestamp = "";
        JSONObject listParam = new JSONObject();
        listParam.put("accesstoken", accesstoken);
        listParam.put("areacode", areacode);
        listParam.put("orgcode", "");
        listParam.put("businesscode", "");
        
        JSONArray ouList = new JSONArray();
        do{
        	listParam.put("fromtimestamp", fromtimestamp);
        	String result = getResponse(headForList,listParam.toString(),"application/json;charset=utf-8");
        	try{
        		JSONObject json = JSONObject.fromObject(result);
          		String code = json.optJSONObject("status").optString("code");
          		if("success".equals(code)){
          			ouList = json.optJSONObject("custom").optJSONArray("ouList");
          			int sumNum = json.optJSONObject("custom").optInt("sumNum");
          			System.out.println("部门列表总数："+sumNum);
          			String lastTimestamp = json.optJSONObject("custom").optString("lastTimestamp");
          			fromtimestamp = lastTimestamp;
          			for(int iLoop = 0;iLoop<ouList.size();iLoop++){
          				String ouguid = ouList.getJSONObject(iLoop).optString("ouguid");
          				JSONObject infoParam = new JSONObject();
          				infoParam.put("accesstoken", accesstoken);
          				infoParam.put("ouguid", ouguid);
          				String info = getResponse(headForInfo,infoParam.toString(),"application/json;charset=utf-8");
//          				System.out.println(ouguid+"--------"+info);
          				SelmZhallItemOrgan organ = new SelmZhallItemOrgan();
          				try{
              				JSONObject infoJson = JSONObject.fromObject(info);
              				String infoCode = infoJson.optJSONObject("status").optString("code");
              				if("success".equals(infoCode)){
              					JSONObject auditorg = infoJson.optJSONObject("custom").optJSONObject("auditorg");
              					organ.setStOuguid(auditorg.optString("ouguid"));
              					organ.setStOrgCode(auditorg.optString("org_code"));
              					organ.setStOuname(auditorg.optString("ouname"));
              					organ.setStShortName(auditorg.optString("oushortname"));
              					organ.setStOucode(auditorg.optString("oucode"));
              					organ.setStShCode(auditorg.optString("sh_code"));
              					organ.setIsOnuse(auditorg.optString("isnouse"));
              					organ.setStDeptLevel(auditorg.optString("dept_level"));
              					organ.setStAreaCode(auditorg.optString("areacode"));
              					organ.setStParentOuguid(auditorg.optString("parentouguid"));
              					
              					selfDeclareSqService.addZhallItemOrgan(organ);
              				} else {
              					Log.debug("部门信息查询失败，部门主键："+ouguid);
              				}
          				} catch (Exception e) {
          					Log.debug("部门信息查询失败，部门主键："+ouguid+"------"+e.getMessage().toString());
    					}
          			}
          		}
        	} catch (Exception e) {
        		Log.debug("部门列表获取失败："+e.getMessage().toString());
    		}
        }while(ouList.size() == 50);
    	
    	AciJsonHelper.writeJsonPResponse(req, res, "部门信息获取完成！");
    }
    
    /**
     * 根据事项主键获取事项的办事指南（中台服务：上海市政务服务事项管理系统数据发布接口服务）
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/selfDeclare/getItemDetailByIDForSQ.do")
    public void getItemDetailByIDForSQ(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String stId = req.getParameter("stId");
    	String accesstoken = "f27315e1-ec88-42e3-ae08-796ac4335999";
    	
    	String appNameForInfo = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "966a4c9d-dfa6-4e0c-87bc-53643f5ab3de";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "d1f15aa3-6cf1-4ae2-8e94-03847b008ad6";
		}
    	String signatureForInfo = HttpUtil.getSignature(appNameForInfo);
        Map<String, String> headForInfo = HttpUtil.setHttpHeard(signatureForInfo, appNameForInfo);
    	
    	JSONObject infoParam = new JSONObject();
	    infoParam.put("accesstoken", accesstoken);
	    infoParam.put("rowguid", stId);
    	String info = getResponse(headForInfo,infoParam.toString(),"application/json;charset=utf-8");
    	
    	try{
    		JSONObject infoJson = JSONObject.fromObject(info);
      		String code = infoJson.optJSONObject("status").optString("code");
      		if("success".equals(code)){
      			JSONObject auditfwzn = infoJson.optJSONObject("custom").optJSONObject("auditfwzn");
      			String orgCode = auditfwzn.optString("org_code");
      			OrganNodeInfo organ = selfDeclareSqService.getOrganByCode(orgCode);
      			auditfwzn.put("org_name", organ.getOrganName());
      			info = infoJson.toString();
      		}
    	} catch (Exception e) {
    		Log.debug(e.getMessage().toString());
		}
    	
    	AciJsonHelper.writeJsonPResponse(req, res, info);
    }
/*==========================================
 * 市区事项
==========================================*/
	/**
	 * 输入事项名称关键字搜索事项
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemListByItemNameForPageBySq.do")
	public void getItemListByItemNameForPageBySq(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String itemName = req.getParameter("itemName");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareSqService.getItemListByItemNameForPage(
				itemName, pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	/**
	 * 获取办事指南的所有事项列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getAllItemListForPageBySq.do")
	public void getAllItemListForPageBySq(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareSqService.getAllItemListForPage(pageSize,
				currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	/**
	 * 获取有办事指南的所有部门列表
	 */
	@RequestMapping("/selfapi/declare/getOrganListForDeclarePageBySq.do")
	public void getOrganListForDeclarePageBySq(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = selfDeclareSqService.getOrganListForDeclarePage(
				pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	/**
	 * 获取某部门的所有事项列表
	 */
	@RequestMapping("/selfapi/declare/getItemListByOrganCodeForPageBySq.do")
	public void getItemListByOrganCodeForPageBySq(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String organCode = req.getParameter("organCode");
		String model = req.getParameter("model");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareSqService.getItemListByOrganCodeForPage(
				model, organCode, pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	/**
	 * 根据事项编码获取其下的情形事项
	 */
	@RequestMapping("/selfapi/declare/getWindowItemStatusListBySq.do")
	public void getWindowItemStatusListBySq(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String itemNo = req.getParameter("itemNo");
		String deptCode = req.getParameter("deptCode");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		WindowItemStatusPage page = selfDeclareSqService.getWindowItemStatusList(
				itemNo, pageSize, currentPage, deptCode);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	/**
	 * 查询市、区下有办事指南事项的部门
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/declare/getDeptInAreaBySq.do")
	public void getDeptInAreaBySq(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		String areaCode = req.getParameter("areaCode");
		String model = req.getParameter("model");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = selfDeclareSqService.getDeptInArea(model,pageSize,currentPage,areaCode);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page).toString());
	}
	
	/**
	 * 查询市、区下事项主题
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("/selfapi/declare/getItemThemeBySq.do")
	public void getItemThemeBySq(HttpServletRequest req, HttpServletResponse res) throws IOException{
		RequestWrapper wrapper = new RequestWrapper(req);
		String areaCode = wrapper.getParameter("areaCode");
		String type = wrapper.getParameter("type");
		String model = req.getParameter("model");
		List<ItemTheme> list = selfDeclareSqService.getItemTheme(model,areaCode,type);
		JSONArray array = new JSONArray();
		JSONObject josnResult = new JSONObject();
		Iterator<ItemTheme> iter = list.iterator();
		while(iter.hasNext()){
			ItemTheme theme = iter.next();
			JSONObject josn = new JSONObject();
			josn.put("itemTypeCode", theme.getItemTypeCode());
			josn.put("itemTypeName", theme.getItemTypeName());
			array.add(josn);
		}
		josnResult.put("total", array.size());
		josnResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
	}
	
	/**
	 * 查询市、区主题下的事项
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("/selfapi/declare/getItemInThemeBySq.do")
	public void getItemInThemeBySq(HttpServletRequest req, HttpServletResponse res) throws IOException{
		RequestWrapper wrapper = new RequestWrapper(req);
		String themeCode = wrapper.getParameter("themeCode");
		String areaCode = wrapper.getParameter("areaCode");
		String type = wrapper.getParameter("type");
		String model = req.getParameter("model");
		List<ItemSet> list = selfDeclareSqService.getItemInTheme(model,themeCode,areaCode,type);
		JSONArray array = new JSONArray();
		JSONObject josnResult = new JSONObject();
		Iterator<ItemSet> iter = list.iterator();
		while(iter.hasNext()){
			ItemSet item = iter.next();
			JSONObject josn = new JSONObject();
			josn.put("stItemNo", item.getStItemNo());
			josn.put("stItemName", item.getStItemName());
			josn.put("organCode", item.getOrganCode());
			array.add(josn);
		}
		josnResult.put("total", array.size());
		josnResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
	}
    
/*==========================================
 * 社区事项
==========================================*/
	
	/**
	 * 查询社区下事项主题
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/declare/getThemeForSq.do")
	public void getThemeForSq(HttpServletRequest req, HttpServletResponse res) throws IOException{
		List<ItemTheme> list = selfDeclareSqService.getThemeForSq();
		JSONArray array = new JSONArray();
		JSONObject josnResult = new JSONObject();
		Iterator<ItemTheme> iter = list.iterator();
		while(iter.hasNext()){
			ItemTheme theme = iter.next();
			JSONObject josn = new JSONObject();
			josn.put("itemTypeCode", theme.getItemTypeCode());
			josn.put("itemTypeName", theme.getItemTypeName());
			array.add(josn);
		}
		josnResult.put("total", array.size());
		josnResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
	}
	
	/**
	 * 查询社区主题下事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/declare/getItemInThemeForSQ.do")
	public void getItemInThemeForSQ(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String themeName = req.getParameter("themeName");
		if(StringUtils.isNotEmpty(themeName)){
			themeName = URLDecoder.decode(themeName, "utf-8");
		}
		List<ItemSet> list = selfDeclareSqService.getItemInThemeForSQ(themeName);
		JSONArray array = new JSONArray();
		JSONObject josnResult = new JSONObject();
		Iterator<ItemSet> iter = list.iterator();
		while(iter.hasNext()){
			ItemSet item = iter.next();
			JSONObject json = new JSONObject();
			json.put("id", item.getStItemId());
			json.put("stItemNo", item.getStItemNo());
			json.put("stItemName", item.getStItemName());
			json.put("organCode", item.getOrganCode());
			array.add(json);
		}
		josnResult.put("total", array.size());
		josnResult.put("data", array);
		AciJsonHelper.writeJsonPResponse(req, res, josnResult.toString());
	}
	
	/**
	 * 获取社区有办事指南事项的所有部门列表
	 */
	@RequestMapping("/selfapi/declare/getOrganListForSQ.do")
	public void getOrganListForSQ(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询的话默认为第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = selfDeclareSqService.getOrganListForSQ(
				pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	/**
	 * 获取社区部门的所有事项列表
	 */
	@RequestMapping("/selfapi/declare/getItemListByOrganIdForSQ.do")
	public void getItemListByOrganIdForSQ(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String organCode = req.getParameter("organCode");
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = selfDeclareSqService.getItemListByOrganIdForSQ(organCode, pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}
	
	private String getResponse(Map<String, String> head, String paramString, String contentType) {
		String body = "";
//		CloseableHttpClient client = PooledHttpUitl.getCloseableHttp(640, 320);
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
//        HttpPost httpPost = new HttpPost(url);
//        
//        for (Map.Entry<String, String> entry : head.entrySet()) {
//            System.out.println(entry.getKey() + "：" + entry.getValue());
//            httpPost.addHeader(entry.getKey() , entry.getValue());
//        }
//        httpPost.setHeader("Content-type", contentType);
		head.put("Content-type", contentType);
        // 设置参数到请求对象中
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        body = HttpUtil.doPost(head, se);
//        httpPost.setEntity(se);
//        
//        CloseableHttpResponse response = null;
//        try{
//        	if(client == null){
//        		if(url.startsWith("https://")){
//        			client= HttpUtil.getHttpsClient();
//        		} else {
//        			client = HttpClients.createDefault();
//        		}
//        	}
//            response = client.execute(httpPost);
//            // 获取结果实体
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                // 按指定编码转换结果实体为String类型
//                body = EntityUtils.toString(entity, "utf-8");
//            }
//            // 释放链接
//            response.close();
//        } catch (Exception e) {
//        	Log.debug("访问失败"+e.getMessage());
//		} finally {
//            try {
//            	if(response != null){
//            		// 释放链接
//            		response.close();
//            	}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return body;
	}
}
