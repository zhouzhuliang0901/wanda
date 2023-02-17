package com.wondersgroup.selfapi.task;

import java.sql.Timestamp;
import java.util.Map;


import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.operator.connector.OneMonthOp;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.selfapi.bean.SelmZhallItemInfo;
import com.wondersgroup.selfapi.dao.SelfDeclareDao;
import com.wondersgroup.selfapi.service.SelfDeclareService;

@Component
public class UpdateItemInfoTask implements OneMonthOp{
	
	@Autowired
	private SelfDeclareDao selfDeclareDao;
	@Autowired
	private SelfDeclareService selfDeclareService;
	
	@Override
	public void execute(Timestamp current) {
		System.out.println("=================开始更新事项信息=======================");
		// 清空事项临时表
		selfDeclareDao.deleteItemTemp();
		String arrCode = RdConfig.get("reindeer.update.area");
		String[] codeList = arrCode.split(",");
		for(String areaCode : codeList){
			backupItemToTemp(areaCode);
		}
		System.out.println("=================更新事项信息完成=======================");
	}
	
	private void backupItemToTemp(String areaCode){
		if(StringUtils.isNotEmpty(areaCode)){
			String area = areaCode.substring(areaCode.length()-2, areaCode.length());
			if("SH00SH".equals(areaCode)){
				selfDeclareDao.backupItem("SH");
				selfDeclareDao.deleteItem("SH");
				selfDeclareDao.backupItem("QY");
				selfDeclareDao.deleteItem("QY");
			} else {
				// 将指定区事项备份至临时表
				selfDeclareDao.backupItem(area);
				// 删除指定区的事项
				selfDeclareDao.deleteItem(area);
			}
			// 获取指定区事项
			getItemInfo(areaCode);
		}
	}
	
	private void getItemInfo(String region) {
		String appName = "e709f0ad-e4cc-486d-9f11-2475fee2b68c";

		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject data = new JSONObject();
		data.put("region", region);

		String contentType = "application/json;charset=utf-8";
		head.put("Content-type", contentType);
		// 设置参数到请求对象中
		StringEntity se = new StringEntity(data.toString(), "utf-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));

		String result = HttpUtil.doPost(head, se);

		try {
			com.alibaba.fastjson.JSONArray itemArr = com.alibaba.fastjson.JSONObject
					.parseObject(result).getJSONArray("items");
			System.out.println("区：" + region + "事项总数：" + itemArr.size());
			for (int iLoop = 0; iLoop < itemArr.size(); iLoop++) {
				SelmZhallItemInfo info = new SelmZhallItemInfo();
				com.alibaba.fastjson.JSONObject item = itemArr
						.getJSONObject(iLoop);
				info.setStId(item.getJSONObject("item").getString("ST_ID"));
				info.setStItemNo(item.getJSONObject("item").getString(
						"ST_ITEM_ID"));
				String stHandleObject = item.getJSONObject("item").getString(
						"ST_HANDLE_OBJECT");// 申请对象
				info.setStItemType(item.getJSONObject("item").getString(
						"ST_ITEM_TYPE"));
				info.setStOrgCode(item.getJSONObject("item").getString(
						"ST_ORG_CODE"));
				info.setStOrgName(item.getJSONObject("item").getString(
						"ST_ORG_NAME"));
				com.alibaba.fastjson.JSONArray categoryArr = item
						.getJSONArray("category");// 事项分类列表
				info.setStItemTenCode(item.getJSONObject("item").getString(
						"ST_TEN_CODE"));
				info.setStTransactName(item.getJSONObject("item").getString(
						"ST_TRANSACT_NAME"));
				String subItem = item.getJSONObject("item").getString(
						"ST_SUBITEM_NAME");
				String stItemName = item.getJSONObject("item").getString(
						"ST_ITEM_NAME");
				if (subItem != null && stItemName != null) {
					if (subItem.equals(stItemName)) {
						info.setStItemName(stItemName);
						info.setNmIsson(0);// 主事项
					} else if (subItem.startsWith(stItemName)) {
						info.setStItemName(subItem);
						info.setNmIsson(1);// 子事项
					} else if (subItem != null && !subItem.equals("")) {
						info.setStItemName(stItemName + "(" + subItem + ")");
						info.setNmIsson(1);// 子事项
					}
				}

				if (StringUtils.isNotEmpty(stHandleObject)) {
					String nmBelong = "";
					String[] handleArr = stHandleObject.split("#");
					for (String str : handleArr) {
						if ("法人".equals(str)) {
							nmBelong += "2";
						} else if ("个人".equals(str)) {
							nmBelong += "1";
						} else if ("其他组织".equals(str)) {
							nmBelong += "3";
						}
					}
					info.setNmBelong(nmBelong);
				}

				String stDicCode = "";
				for (int i = 0; i < categoryArr.size(); i++) {
					stDicCode += categoryArr.getJSONObject(i).getString(
							"ST_CATEGORY_ID")
							+ ",";
				}
				info.setStDicCode(stDicCode);
				info.setDtCreate(new Timestamp(System.currentTimeMillis()));
				info.setStRemove(0);
				
				try {
					selfDeclareService.addZhallItem(info);
				} catch (Exception e) {
					Log.debug(e.getMessage().toString());
					Log.debug("事项：" + info.getStItemName() + "保存失败，ID为："
							+ info.getStId());
				}
			}
		} catch (Exception e) {
			Log.debug(e);
		}
	}
}
