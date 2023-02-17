package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

/**
 * 事项表业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmItemService {

	/**
	 * 根据主键 {@link SelmItem#ST_ITEM_ID}获取事项表
	 * 
	 * @param stItemId
	 *            事项表主键 {@link SelmItem#ST_ITEM_ID}
	 * @return 事项表实例
	 */
	SelmItem get(String stItemId);

	/**
	 * 查询事项表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项表列表
	 */
	JSONObject itemList(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link SelmItem#ST_ITEM_ID}删除事项表
	 * 
	 * @param stItemId
	 *            事项表主键 {@link SelmItem#ST_ITEM_ID}
	 */
	void remove(HttpReqRes httpReqRes);

	/**
	 * 保存或更新事项表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项表实例
	 */
	SelmItem saveOrUpdate(HttpReqRes httpReqRes);

	JSONObject noOCDevicelist(HttpReqRes httpReqRes);


	JSONObject itemDevicelist(HttpReqRes httpReqRes);

	JSONObject sqhDeviceItem(HttpReqRes httpReqRes);

	List<SelmItem> queryAllItem(HttpReqRes httpReqRes);

	int updateItem(String stExt3, String stItemNo);

	SelmItem sonItemSave(HttpReqRes httpReqRes);

}
