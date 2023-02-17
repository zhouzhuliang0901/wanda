package com.wondersgroup.delivery.server;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.delivery.bean.SelmDeliveryHistory;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 快递柜历史业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmDeliveryHistoryService {

	/**
	 * 根据主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}获取快递柜历史
	 * 
	 * @param stDeliveryId
	 *            快递柜历史主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}
	 * @return 快递柜历史实例
	 */
	SelmDeliveryHistory get(String stDeliveryId);

	/**
	 * 查询快递柜历史列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 快递柜历史列表
	 */
	PaginationArrayList<SelmDeliveryHistory> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}删除快递柜历史
	 * 
	 * @param stDeliveryId
	 *            快递柜历史主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}
	 */
	void remove(String stDeliveryId);

	/**
	 * 保存或更新快递柜历史
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 快递柜历史实例
	 */
	SelmDeliveryHistory saveOrUpdate(RequestWrapper wrapper);

	JSONObject list(HttpReqRes httpReqRes);

	void removeList(HttpReqRes httpReqRes);

}
