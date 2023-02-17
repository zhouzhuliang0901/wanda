package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacItemSystemTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项关联系统业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemSystemServiceTwo {

	/**
	 * 根据主键 {@link UacItemSystemTwo#ST_ITEM_ID} {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}获取事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}
	 * @return 事项关联系统实例
	 */
	UacItemSystemTwo get(String stItemId, String stWebSystemId);

	/**
	 * 查询事项关联系统列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项关联系统列表
	 */
	PaginationArrayList<UacItemSystemTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemSystemTwo#ST_ITEM_ID} {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}删除事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}
	 */
	void remove(String stItemId, String stWebSystemId);

	/**
	 * 保存或更新事项关联系统
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项关联系统实例
	 */
	UacItemSystemTwo saveOrUpdate(RequestWrapper wrapper);

}
