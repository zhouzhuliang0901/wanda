package com.wondersgroup.wdf.uacItemSystem.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.uacItemSystem.dao.UacItemSystem;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项关联系统业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemSystemService {

	/**
	 * 根据主键 {@link UacItemSystem#ST_ITEM_ID} {@link UacItemSystem#ST_WEB_SYSTEM_ID}获取事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_WEB_SYSTEM_ID}
	 * @return 事项关联系统实例
	 */
	UacItemSystem get(String stItemId, String stWebSystemId);

	/**
	 * 查询事项关联系统列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项关联系统列表
	 */
	PaginationArrayList<UacItemSystem> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemSystem#ST_ITEM_ID} {@link UacItemSystem#ST_WEB_SYSTEM_ID}删除事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_WEB_SYSTEM_ID}
	 */
	void remove(String stItemId, String stWebSystemId);

	/**
	 * 保存或更新事项关联系统
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项关联系统实例
	 */
	UacItemSystem saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 新增事项+系统信息关联
	 * @param stItemId
	 * @param str_stWebSystemId
	 */
	void addWeb(String stItemId,String...str_stWebSystemId);

}
