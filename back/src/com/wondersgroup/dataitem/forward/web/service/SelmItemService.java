package com.wondersgroup.dataitem.forward.web.service;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;

import net.sf.json.JSONObject;

import tw.ecosystem.reindeer.web.HttpReqRes;

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
	SelmItem get(String itemCode);

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

	JSONObject itemList(String itemCode);

}
