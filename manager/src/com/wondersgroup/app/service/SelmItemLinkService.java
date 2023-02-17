package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.app.bean.SelmItemLink;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 类别关联事项业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmItemLinkService {

	/**
	 * 根据主键 {@link SelmItemLink#ST_ITEM_ID} {@link SelmItemLink#ST_ITEM_TYPE_ID}获取类别关联事项
	 * 
	 * @param stItemId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_ID}
	 * @param stItemTypeId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_TYPE_ID}
	 * @return 类别关联事项实例
	 */
	SelmItemLink get(String stItemId, String stItemTypeId);

	/**
	 * 查询类别关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 类别关联事项列表
	 */
	PaginationArrayList<SelmItemLink> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmItemLink#ST_ITEM_ID} {@link SelmItemLink#ST_ITEM_TYPE_ID}删除类别关联事项
	 * 
	 * @param stItemId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_ID}
	 * @param stItemTypeId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_TYPE_ID}
	 */
	void remove(String stItemId, String stItemTypeId);

	/**
	 * 保存或更新类别关联事项
	 * 
	 * @param httpReqRes
	 *            提交参数
	 * @return 类别关联事项实例
	 */
	SelmItemLink saveOrUpdate(HttpReqRes httpReqRes);

	JSONObject list(HttpReqRes httpReqRes);

	JSONObject itemNoLinkList(HttpReqRes httpReqRes);

	JSONObject itemLinkList(HttpReqRes httpReqRes);

	void removeList(HttpReqRes httpReqRes);

}
