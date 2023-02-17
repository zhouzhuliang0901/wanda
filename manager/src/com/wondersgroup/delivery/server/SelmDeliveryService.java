package com.wondersgroup.delivery.server;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.delivery.bean.SelmDelivery;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 快递柜业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmDeliveryService {

	/**
	 * 根据主键 {@link SelmDelivery#ST_DELIVERY_ID}获取快递柜
	 * 
	 * @param stDeliveryId
	 *            快递柜主键 {@link SelmDelivery#ST_DELIVERY_ID}
	 * @return 快递柜实例
	 */
	SelmDelivery get(String stDeliveryId);

	/**
	 * 查询快递柜列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 快递柜列表
	 */
	PaginationArrayList<SelmDelivery> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmDelivery#ST_DELIVERY_ID}删除快递柜
	 * 
	 * @param stDeliveryId
	 *            快递柜主键 {@link SelmDelivery#ST_DELIVERY_ID}
	 */
	void remove(String stDeliveryId);

	/**
	 * 保存或更新快递柜
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 快递柜实例
	 */
	SelmDelivery saveOrUpdate(RequestWrapper wrapper);

	JSONObject list(HttpReqRes httpReqRes);

	void removeList(HttpReqRes httpReqRes);

	SelmDelivery getMacHineId(String getMacHineId);

}
