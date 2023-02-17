package com.wondersgroup.statistics.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.statistics.bean.SelmClientStatDay;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 终端业务统计（按天）业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmClientStatDayService {

	/**
	 * 根据主键 {@link SelmClientStatDay#ST_STATISTICS_ID} {@link SelmClientStatDay#ST_DATE} {@link SelmClientStatDay#ST_MACHINE_ID}获取终端业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_DATE}
	 * @param stMachineId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_MACHINE_ID}
	 * @return 终端业务统计（按天）实例
	 */
	SelmClientStatDay get(String stStatisticsId, String stDate, String stMachineId);

	/**
	 * 查询终端业务统计（按天）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 终端业务统计（按天）列表
	 */
	PaginationArrayList<SelmClientStatDay> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmClientStatDay#ST_STATISTICS_ID} {@link SelmClientStatDay#ST_DATE} {@link SelmClientStatDay#ST_MACHINE_ID}删除终端业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_DATE}
	 * @param stMachineId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_MACHINE_ID}
	 */
	void remove(String stStatisticsId, String stDate, String stMachineId);

	/**
	 * 保存或更新终端业务统计（按天）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 终端业务统计（按天）实例
	 */
	SelmClientStatDay saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 终端业务统计（按天）列表
	 * @param httpReqRes
	 * @return
	 */
	JSONObject selmClientStatDayList(HttpReqRes httpReqRes);

	/**
	 * 终端业务统计（按天）列表(外设)
	 * @param httpReqRes
	 * @return
	 */
	JSONObject odeviceClientStatDayList(HttpReqRes httpReqRes);

}
