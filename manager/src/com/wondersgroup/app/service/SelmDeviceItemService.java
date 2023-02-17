package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.app.bean.SelmDeviceItem;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 设备关联事项业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmDeviceItemService {

	/**
	 * 根据主键 {@link SelmDeviceItem#ST_ITEM_ID} {@link SelmDeviceItem#ST_DEVICE_ID}获取设备关联事项
	 * 
	 * @param stItemId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_ITEM_ID}
	 * @param stDeviceId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_DEVICE_ID}
	 * @return 设备关联事项实例
	 */
	SelmDeviceItem get(String stItemId, String stDeviceId);

	/**
	 * 查询设备关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备关联事项列表
	 */
	PaginationArrayList<SelmDeviceItem> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmDeviceItem#ST_ITEM_ID} {@link SelmDeviceItem#ST_DEVICE_ID}删除设备关联事项
	 * 
	 * @param stItemId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_ITEM_ID}
	 * @param stDeviceId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_DEVICE_ID}
	 */
	/*void remove(String stItemId, String stDeviceId);*/

	/**
	 * 保存或更新设备关联事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备关联事项实例
	 */
	/*SelmDeviceItem saveOrUpdate(RequestWrapper wrapper);*/

	SelmDeviceItem saveOrUpdate(HttpReqRes httpReqRes);

	void remove(HttpReqRes httpReqRes);

}
