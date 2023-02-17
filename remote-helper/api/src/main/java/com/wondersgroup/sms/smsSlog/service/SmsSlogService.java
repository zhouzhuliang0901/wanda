package com.wondersgroup.sms.smsSlog.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.sms.smsSlog.dao.SmsSlog;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 日志表业务接口
 * 
 * @author scalffold
 * 
 */
public interface SmsSlogService {

	/**
	 * 根据主键 {@link SmsSlog#ST_LOG_ID}获取日志表
	 * 
	 * @param stLogId
	 *            日志表主键 {@link SmsSlog#ST_LOG_ID}
	 * @return 日志表实例
	 */
	SmsSlog get(String stLogId);

	/**
	 * 查询日志表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 日志表列表
	 */
	PaginationArrayList<SmsSlog> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SmsSlog#ST_LOG_ID}删除日志表
	 * 
	 * @param stLogId
	 *            日志表主键 {@link SmsSlog#ST_LOG_ID}
	 */
	void remove(String stLogId);

	/**
	 * 保存或更新日志表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 日志表实例
	 */
	SmsSlog saveOrUpdate(RequestWrapper wrapper);

}
