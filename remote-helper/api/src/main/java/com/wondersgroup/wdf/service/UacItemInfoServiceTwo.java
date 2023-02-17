package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacItemInfoTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemInfoServiceTwo {

	/**
	 * 根据主键 {@link UacItemInfoTwo#ST_ITEM_ID}获取事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfoTwo#ST_ITEM_ID}
	 * @return 事项信息实例
	 */
	UacItemInfoTwo get(String stItemId);

	/**
	 * 查询事项信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项信息列表
	 */
	PaginationArrayList<UacItemInfoTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemInfoTwo#ST_ITEM_ID}删除事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfoTwo#ST_ITEM_ID}
	 */
	void remove(String[] stItemId);

	/**
	 * 保存或更新事项信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项信息实例
	 */
	UacItemInfoTwo saveOrUpdate(RequestWrapper wrapper);

	PaginationArrayList<UacItemInfoTwo> queryNoLink(RequestWrapper wrapper);

	PaginationArrayList<UacItemInfoTwo> queryNoGroupLink(RequestWrapper wrapper);

	void logicDelete(String[] stItemId);

}
