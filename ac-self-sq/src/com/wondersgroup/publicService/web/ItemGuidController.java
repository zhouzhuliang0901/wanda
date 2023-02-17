package com.wondersgroup.publicService.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.publicService.bean.OrganNodeInfo;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;
import com.wondersgroup.publicService.service.SelfDeclareService;
import com.wondersgroup.publicService.utils.ItemGuidUtil;

@Controller
public class ItemGuidController {

	@Autowired
	private SelfDeclareService selfDeclareService;
	/**
	 * 全量导入事项基本信息（按区）（中台服务：办事指南接口） region：所属区域编号（SH00SH、SH00PD......）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemInfo.do")
	public void getItemInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String region = req.getParameter("region");
		String appName = "";
        if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
        	appName = "6bfa95b4-4fec-4d76-89bd-485b7ba97c35";
        } else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
        	appName = "e709f0ad-e4cc-486d-9f11-2475fee2b68c";
        }
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject data = new JSONObject();
		data.put("region", region);
		
		String result = ItemGuidUtil.getInfo(head, data.toString(), "application/json;charset=utf-8");
		try {
			List<SelmZhallItemInfo> itemList =  ItemGuidUtil.exportExcelItemId();
			List<String> listId = new ArrayList<String>();
			for(SelmZhallItemInfo item : itemList){
				listId.add(item.getStId());
			}
			JSONObject jsonResult = JSONObject.fromObject(result);
			JSONArray itemArr = jsonResult.getJSONArray("items");
			Log.debug("区：" + region + "事项总数：" + itemArr.size());
			for (int iLoop = 0; iLoop < itemArr.size(); iLoop++) {
				SelmZhallItemInfo info = new SelmZhallItemInfo();
				JSONObject item = itemArr.getJSONObject(iLoop);
				String id = item.optJSONObject("item").optString("ST_ID");
//				if(listId.contains(id)){
					info.setStId(id);
					info.setStItemNo(item.optJSONObject("item").optString(
							"ST_ITEM_ID"));
					String stHandleObject = item.optJSONObject("item").optString(
							"ST_HANDLE_OBJECT");// 申请对象
					info.setNmBelong(stHandleObject);
					info.setStItemType(item.optJSONObject("item").optString(
							"ST_ITEM_TYPE"));
					info.setStOrgCode(item.optJSONObject("item").optString(
							"ST_ORG_CODE"));
					info.setStOrgName(item.optJSONObject("item").optString(
							"ST_ORG_NAME"));
					JSONArray categoryArr = item.optJSONArray("category");// 事项分类列表
					info.setStItemTenCode(item.optJSONObject("item").optString(
							"ST_TEN_CODE"));
					
					String stTransactName = item.optJSONObject("item").optString(
							"ST_TRANSACT_NAME");
					if("无".equals(stTransactName)){
						stTransactName = "";
					}
					info.setStTransactName(stTransactName);
					String subItem = item.optJSONObject("item").optString(
							"ST_SUBITEM_NAME");
					info.setStSubItemName(subItem);
					String stItemName = item.optJSONObject("item").optString(
							"ST_ITEM_NAME");
					info.setStItemName(stItemName);
					
					String stDicCode = "";
					for (int i = 0; i < categoryArr.size(); i++) {
						stDicCode += categoryArr.optJSONObject(i).optString(
								"ST_CATEGORY_ID")
								+ ",";
					}
					
					JSONArray enBodys =item.optJSONArray("enBodys");
					if(enBodys.size() > 0){
						String itemCode =  enBodys.getJSONObject(0).optString("ST_CODE");
						info.setStItemCode(itemCode);
					}
					
					info.setStDicCode(stDicCode);
					info.setDtCreate(new Timestamp(System.currentTimeMillis()));
					info.setStRemove(0);

					List<SelmZhallItemInfo> list = selfDeclareService
							.queryZhallItem(info.getStId());
					if (list.size() <= 0) {
						// 新增
						try {
							selfDeclareService.addZhallItem(info);
						} catch (Exception e) {
							Log.debug(e.getMessage().toString());
							Log.debug("事项：" + info.getStItemName() + "保存失败，ID为："
									+ info.getStId());
						}
					} else {
						// 更新
						try {
							selfDeclareService.updateZhallItem(info);
						} catch (Exception e) {
							Log.debug(e.getMessage().toString());
							Log.debug("事项：" + info.getStItemName() + "更新失败，ID为："
									+ info.getStId());
						}
					}
//				}
			}
		} catch (Exception e) {
			Log.debug(e);
		}

		AciJsonHelper.writeJsonPResponse(req, res, "执行结束！");
	}
	
	/**
	 * 区级公共服务事项按各区提交表格导入事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/importItem.do")
	public void importItem(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String areaCode = RdConfig.get("reindeer.area.code");
		ImportParams params1 = new ImportParams();
		params1.setStartSheetIndex(0);
		params1.setHeadRows(1);
		List<SelmZhallItemInfo> itemList = ExcelImportUtil.importExcel(
				new File(ItemGuidController.class.getResource("").getPath()+"template/区级公共服务事项-"+areaCode+".xlsx"),
				SelmZhallItemInfo.class,
				params1);
		for(SelmZhallItemInfo item : itemList){
			item.setDtCreate(new Timestamp(System.currentTimeMillis()));
			item.setStRemove(0);
			if(StringUtils.isEmpty(item.getStTransactName())){
				item.setStTransactName("");
			}

			List<SelmZhallItemInfo> list = selfDeclareService
					.queryZhallItem(item.getStId());
			if (list.size() <= 0) {
				// 新增
				try {
					selfDeclareService.addZhallItem(item);
				} catch (Exception e) {
					Log.debug(e.getMessage().toString());
					Log.debug("事项：" + item.getStItemName() + "保存失败，ID为："
							+ item.getStId());
				}
			} else {
				// 更新
				try {
					selfDeclareService.updateZhallItem(item);
				} catch (Exception e) {
					Log.debug(e.getMessage().toString());
					Log.debug("事项：" + item.getStItemName() + "更新失败，ID为："
							+ item.getStId());
				}
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, "执行结束！");
	}
	
	/**
	 * 根据事项主键获取办事指南信息（中台服务：办事指南接口）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemDetailByID.do")
	public void getItemDetailByID(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stId = req.getParameter("stId");
		String appName = "";
		if ("test".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "9dbd50b9-e7a3-44ee-aa9e-3b9a33010bae";
		} else if ("product".equals(Config.get("reindeer.huidao.environment"))) {
			appName = "e35c79a7-6eac-4b4a-b6a7-3401e91fde72";
		}

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject data = new JSONObject();
		data.put("stId", stId);

		String contentType = "application/json;charset=utf-8";
		String result = ItemGuidUtil.getInfo(head, data.toString(), contentType);

		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 根据事项名称模糊搜索事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemByItemName.do")
	public void getItemByItemName(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String itemName = req.getParameter("itemName");
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		JSONArray arr = new JSONArray();
		List<SelmZhallItemInfo> list = selfDeclareService.getItemByItemName(itemName);
		for(SelmZhallItemInfo item : list ){
			JSONObject o = new JSONObject();
			o.put("stItemName", item.getStItemName());
			o.put("stSubItemName", item.getStSubItemName());
			o.put("stItemNo", item.getStItemNo());
			o.put("nmBelong", item.getNmBelong());
			arr.add(o);
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	/**
	 * 查询所有事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getAllItem.do")
	public void getAllItem(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		List<SelmZhallItemInfo> list = selfDeclareService.getallItem();
		JSONArray arr = new JSONArray();
		for(SelmZhallItemInfo item : list ){
			JSONObject o = new JSONObject();
			o.put("stItemName", item.getStItemName());
			o.put("stSubItemName", item.getStSubItemName());
			o.put("stItemNo", item.getStItemNo());
			o.put("nmBelong", item.getNmBelong());
			arr.add(o);
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	/**
	 * 查询事项的所属部门
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getAllOrgan.do")
	public void getAllOrgan(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		List<OrganNodeInfo> list = selfDeclareService.getAllOrgan();
		AciJsonHelper.writeJsonPResponse(req, res, JSONArray.fromObject(list).toString());
	}
	
	/**
	 * 查询所属部门下的事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemByOrgan.do")
	public void getItemByOrgan(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String organCode = req.getParameter("organCode");
		String itemName = req.getParameter("itemName");
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		List<SelmZhallItemInfo> list = selfDeclareService.getItemByOrgan(organCode, itemName);
		JSONArray arr = new JSONArray();
		for(SelmZhallItemInfo item : list ){
			JSONObject o = new JSONObject();
			o.put("stItemName", item.getStItemName());
			o.put("stSubItemName", item.getStSubItemName());
			o.put("stItemNo", item.getStItemNo());
			o.put("nmBelong", item.getNmBelong());
			arr.add(o);
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	public static void main(String[] args) {
		int a = 2;
		String s = String.format("%05d", a);
		System.out.println(s);
	}
	
	/**
	 * 根据事项基本编码查询事项的业务情形和办理编码
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/selfDeclare/getItemTransactName.do")
	public void getItemTransactName(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stItemNo = req.getParameter("stItemNo");
		List<SelmZhallItemInfo> list = selfDeclareService.getItemTransactName(stItemNo);
		JSONArray arr = new JSONArray();
		for(SelmZhallItemInfo item : list ){
			JSONObject o = new JSONObject();
			o.put("stId", item.getStId());
			o.put("stItemName", item.getStItemName());
			o.put("stSubItemName", item.getStSubItemName());
			o.put("stItemNo", item.getStItemNo());
			o.put("stItemCode", item.getStItemCode());
			o.put("nmBelong", item.getNmBelong());
			o.put("stOrgName", item.getStOrgName());
			o.put("stOrgCode", item.getStOrgCode());
			o.put("stItemTenCode", item.getStItemTenCode());
			o.put("stTransactName", item.getStTransactName());
			arr.add(o);
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
}
