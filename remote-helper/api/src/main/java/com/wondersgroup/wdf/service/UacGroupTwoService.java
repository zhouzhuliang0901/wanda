package com.wondersgroup.wdf.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.dao.UacGroupTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项组业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacGroupTwoService {

	/**
	 * 根据主键 {@link UacGroupTwo#ST_GROUP_ID}获取事项组
	 * 
	 * @param stGroupId
	 *            事项组主键 {@link UacGroupTwo#ST_GROUP_ID}
	 * @return 事项组实例
	 */
	UacGroupTwo get(String stGroupId);

	/**
	 * 查询事项组列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项组列表
	 */
	PaginationArrayList<UacGroupTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacGroupTwo#ST_GROUP_ID}删除事项组
	 * 
	 * @param stGroupId
	 *            事项组主键 {@link UacGroupTwo#ST_GROUP_ID}
	 */
	void remove(String stGroupId);

	/**
	 * 保存或更新事项组
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项组实例
	 */
	UacGroupTwo saveOrUpdate(RequestWrapper wrapper);

	void logicDelete(String[] stGroupId);

}
