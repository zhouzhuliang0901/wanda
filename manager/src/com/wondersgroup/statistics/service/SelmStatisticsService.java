package com.wondersgroup.statistics.service;

import java.math.*;
import java.sql.*;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wondersgroup.statistics.bean.SelmStatistics;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 业务表业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmStatisticsService {

	/**
	 * 根据主键 {@link SelmStatistics#ST_STATISTICS_ID}获取业务表
	 * 
	 * @param stStatisticsId
	 *            业务表主键 {@link SelmStatistics#ST_STATISTICS_ID}
	 * @return 业务表实例
	 */
	SelmStatistics get(String stStatisticsId);

	/**
	 * 查询业务表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 业务表列表
	 */
	PaginationArrayList<SelmStatistics> query(RequestWrapper wrapper);
	
	/**
	 * 查询业务表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 业务表列表
	 */
	JSONObject statisticsList(HttpReqRes httpReqRes);
	
	/**
	 * 查询业务表列表(外设)
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 业务表列表
	 */
	JSONObject odeviceStatisticsList(HttpReqRes httpReqRes);


	/**
	 * 查询类型统计列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 业务表列表
	 */
	JSONObject typeStatisticsList(HttpReqRes httpReqRes);
	

	/**
	 * 根据主键 {@link SelmStatistics#ST_STATISTICS_ID}删除业务表
	 * 
	 * @param stStatisticsId
	 *            业务表主键 {@link SelmStatistics#ST_STATISTICS_ID}
	 */
	void remove(HttpReqRes httpReqRes);

	/**
	 * 添加业务表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 业务表实例
	 */
	SelmStatistics add(HttpReqRes httpReqRes);

	/**
	 * 方法描述：更新业务表里的数据
	 * @param httpReqRes
	 * @return
	 */
	void updateStatistics(SelmStatistics selmStatistics);

	/**
	 * 方法描述：更新业务总数
	 * @param httpReqRes
	 * @return
	 */
	//void update(SelmStatistics selmStatistics);

	/**
	 * 查询统计配置列表
	 * @param httpReqRes
	 * @return
	 */
	JSONObject statisticsConfigList(HttpReqRes httpReqRes);

	/**
	 * 更新终端业务统计（按天）业务表
	 * @param selmStatistics
	 */
	//void updateClient(SelmStatistics selmStatistics);


	JSONObject suCount(HttpReqRes httpReqRes);

	JSONObject suCountGroup(HttpReqRes httpReqRes);

	JSONObject suCountItem(HttpReqRes httpReqRes);
	
	
	// 导入历史数据
	//void updateSelmStatistics();
	String updateSelmStatisticsDay();
	String updateSelmClientStatisticsDay();
	String importOdeviceToClientDay();
	String importOdeviceToDay();

	JSONObject addressList(HttpReqRes httpReqRes);

	JSONObject addressTypeList(HttpReqRes httpReqRes);

	JSONObject addresslistTypeDevice(HttpReqRes httpReqRes);

	JSONObject streetlist(HttpReqRes httpReqRes);

	JSONObject streetlistTypeDevice(HttpReqRes httpReqRes);
	
	JSONObject streetTypeDevice(HttpReqRes httpReqRes);
	

	JSONObject streetTypeList(HttpReqRes httpReqRes);

	JSONObject deviceInfoQueryList(HttpReqRes httpReqRes);

	JSONObject areaModuleQuery(HttpReqRes httpReqRes);

	JSONObject visitComItem(HttpReqRes httpReqRes);

	JSONObject itemPeNumber(HttpReqRes httpReqRes);

	JSONObject handleList(HttpReqRes httpReqRes);

	JSONObject itemVisiter(HttpReqRes httpReqRes);

	JSONObject selmSatisfactionInfoList(HttpReqRes httpReqRes);

	JSONObject selmSatisfactionInfo(HttpReqRes httpReqRes);

	JSONObject deviceModuleQuery(HttpReqRes httpReqRes);

	JSONObject areaItemAmountQuery(HttpReqRes httpReqRes);

	JSONObject deviceItemAmountQuery(HttpReqRes httpReqRes);

	JSONObject provinceModuleQuery(HttpReqRes httpReqRes);

	JSONObject provinceItemAmountQuery(HttpReqRes httpReqRes);


}
