package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.app.bean.SelmItemType;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项类别业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmItemTypeService {

	/**
	 * 根据主键 {@link SelmItemType#ST_ITEM_TYPE_ID}获取事项类别
	 * 
	 * @param stItemTypeId
	 *            事项类别主键 {@link SelmItemType#ST_ITEM_TYPE_ID}
	 * @return 事项类别实例
	 */
	SelmItemType get(String stItemTypeId);

	/**
	 * 查询事项类别列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项类别列表
	 */
	PaginationArrayList<SelmItemType> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmItemType#ST_ITEM_TYPE_ID}删除事项类别
	 * 
	 * @param stItemTypeId
	 *            事项类别主键 {@link SelmItemType#ST_ITEM_TYPE_ID}
	 */
	void removeList(HttpReqRes httpReqRes);

	/**
	 * 保存或更新事项类别
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项类别实例
	 */
	SelmItemType saveOrUpdate(RequestWrapper wrapper);

	JSONObject list(HttpReqRes httpReqRes);

	

}
