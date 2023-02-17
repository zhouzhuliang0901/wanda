package com.wondersgroup.statistics.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.statistics.bean.AnalyticsVisitedDetail;


import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 网站统计详细业务接口
 * 
 * @author scalffold
 * 
 */
public interface AnalyticsVisitedDetailService {

	/**
	 * 根据主键 {@link AnalyticsVisitedDetail#ST_DETAIL_ID}获取网站统计详细
	 * 
	 * @param stDetailId
	 *            网站统计详细主键 {@link AnalyticsVisitedDetail#ST_DETAIL_ID}
	 * @return 网站统计详细实例
	 */
	AnalyticsVisitedDetail get(String stDetailId);

	/**
	 * 查询网站统计详细列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 网站统计详细列表
	 */
	PaginationArrayList<AnalyticsVisitedDetail> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link AnalyticsVisitedDetail#ST_DETAIL_ID}删除网站统计详细
	 * 
	 * @param stDetailId
	 *            网站统计详细主键 {@link AnalyticsVisitedDetail#ST_DETAIL_ID}
	 */
	void remove(String stDetailId);

	/**
	 * 保存或更新网站统计详细
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 网站统计详细实例
	 */
	AnalyticsVisitedDetail saveOrUpdate(RequestWrapper wrapper);

}
