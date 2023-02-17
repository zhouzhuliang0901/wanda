package com.wondersgroup.wdf.uacItemInfo.service;

import com.wondersgroup.wdf.uacItemInfo.dao.UacItemInfo;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

import java.util.List;

/**
 * 事项信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemInfoService {

	/**
	 * 查询大事项信息列表
	 *
	 *            查询条件
	 * @return 事项信息列表
	 */
	List<UacItemInfo> queryBig(RequestWrapper wrapper);

	/**
	 * 查询小事项信息列表
	 *
	 *            查询条件
	 * @return 事项信息列表
	 */
	List<UacItemInfo> querySmall(String stItemName);

	/**
	 * 根据主键 {@link UacItemInfo#ST_ITEM_ID}获取事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfo#ST_ITEM_ID}
	 * @return 事项信息实例
	 */
	UacItemInfo get(String stItemId);

	/**
	 * 查询事项信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项信息列表
	 */
	PaginationArrayList<UacItemInfo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemInfo#ST_ITEM_ID}删除事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfo#ST_ITEM_ID}
	 */
	void remove(String stItemId);

	/**
	 * 保存或更新事项信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项信息实例
	 */
	UacItemInfo saveOrUpdate(RequestWrapper wrapper);


}
