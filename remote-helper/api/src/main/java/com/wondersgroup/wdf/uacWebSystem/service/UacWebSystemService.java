package com.wondersgroup.wdf.uacWebSystem.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.uacWebSystem.dao.UacWebSystem;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 系统信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacWebSystemService {

	/**
	 * 根据主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}获取系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}
	 * @return 系统信息实例
	 */
	UacWebSystem get(String stWebSystemId);

	/**
	 * 查询系统信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 系统信息列表
	 */
	PaginationArrayList<UacWebSystem> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}删除系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}
	 */
	void remove(String stWebSystemId);

	/**
	 * 保存或更新系统信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 系统信息实例
	 */
	UacWebSystem saveOrUpdate(RequestWrapper wrapper);

}
