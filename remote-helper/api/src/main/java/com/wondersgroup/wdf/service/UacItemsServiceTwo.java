package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacItemsTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 主题事项（综合）业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemsServiceTwo {

	/**
	 * 根据主键 {@link UacItemsTwo#ST_ITEMS_ID}获取主题事项（综合）
	 * 
	 * @param stItemsId
	 *            主题事项（综合）主键 {@link UacItemsTwo#ST_ITEMS_ID}
	 * @return 主题事项（综合）实例
	 */
	UacItemsTwo get(String stItemsId);

	/**
	 * 查询主题事项（综合）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题事项（综合）列表
	 */
	PaginationArrayList<UacItemsTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemsTwo#ST_ITEMS_ID}删除主题事项（综合）
	 * 
	 * @param stItemsId
	 *            主题事项（综合）主键 {@link UacItemsTwo#ST_ITEMS_ID}
	 */
	void remove(String[] stItemsId);

	/**
	 * 保存或更新主题事项（综合）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 主题事项（综合）实例
	 */
	UacItemsTwo saveOrUpdate(RequestWrapper wrapper);

	PaginationArrayList<UacItemsTwo> queryNoLink(RequestWrapper wrapper);

	void logicDelete(String[] stItemsId);

}
