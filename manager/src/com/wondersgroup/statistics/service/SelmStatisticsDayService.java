package com.wondersgroup.statistics.service;

import java.math.*;
import java.sql.*;
import java.util.List;

import net.sf.json.JSONObject;

import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 业务统计（按天）业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmStatisticsDayService {

	/**
	 * 根据主键 {@link SelmStatisticsDay#ST_STATISTICS_ID} {@link SelmStatisticsDay#ST_DATE}获取业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_DATE}
	 * @return 业务统计（按天）实例
	 */
	SelmStatisticsDay get(String stStatisticsId, String stDate);

	/**
	 * 查询业务统计（按天）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 业务统计（按天）列表
	 */
	//PaginationArrayList<SelmStatisticsDay> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmStatisticsDay#ST_STATISTICS_ID} {@link SelmStatisticsDay#ST_DATE}删除业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_DATE}
	 */
	void remove(String stStatisticsId, String stDate);
	
	void remove(HttpReqRes httpReqRes);

	/**
	 * 保存或更新业务统计（按天）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 业务统计（按天）实例
	 */
	SelmStatisticsDay saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 查询业务统计（按天）列表
	 * 方法描述：
	 * @param httpReqRes
	 * @return
	 */
	JSONObject statisticsList(HttpReqRes httpReqRes);

	
	/**
	 * 查询业务统计（按天）列表(外设)
	 * 方法描述：
	 * @param httpReqRes
	 * @return
	 */
	JSONObject odeviceStatisticsList(HttpReqRes httpReqRes);

	/**
	 * 查询业务统计（按天）列表(分类)
	 * 方法描述：
	 * @param httpReqRes
	 * @return
	 */
	JSONObject typeStatisticsList(HttpReqRes httpReqRes);

	
	/**
	 * 查询业务统计（按天）列表(分组)
	 * @param httpReqRes
	 * @return
	 */
	JSONObject suListGroupDay(HttpReqRes httpReqRes);

	/**
	 * 查询业务统计（按天）列表(项目区域)
	 * @param httpReqRes
	 * @return
	 */
	JSONObject suListItemDay(HttpReqRes httpReqRes);

	JSONObject addressStatisticsList(HttpReqRes httpReqRes);

	JSONObject streetStatisticsList(HttpReqRes httpReqRes);

}
