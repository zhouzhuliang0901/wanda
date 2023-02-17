package com.wondersgroup.statistics.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.statistics.bean.SelmAreaQueryDay;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 区域日办件统计表业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmAreaQueryDayService {

	/**
	 * 根据主键 {@link SelmAreaQueryDay#ST_AREA_ID} {@link SelmAreaQueryDay#ST_DAY}获取区域日办件统计表
	 * 
	 * @param stAreaId
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_AREA_ID}
	 * @param stDay
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_DAY}
	 * @return 区域日办件统计表实例
	 */
	SelmAreaQueryDay get(String stAreaId, String stDay);

	/**
	 * 查询区域日办件统计表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 区域日办件统计表列表
	 */
	PaginationArrayList<SelmAreaQueryDay> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmAreaQueryDay#ST_AREA_ID} {@link SelmAreaQueryDay#ST_DAY}删除区域日办件统计表
	 * 
	 * @param stAreaId
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_AREA_ID}
	 * @param stDay
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_DAY}
	 */
	void remove(String stAreaId, String stDay);

	/**
	 * 保存或更新区域日办件统计表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 区域日办件统计表实例
	 */
	SelmAreaQueryDay saveOrUpdate(RequestWrapper wrapper);

	JSONObject updateSaqd(HttpReqRes httpReqRes);

	JSONObject list(HttpReqRes httpReqRes);

	JSONObject areaUpdateSaqd(HttpReqRes httpReqRes);

	JSONObject blist(HttpReqRes httpReqRes);

	JSONObject clist(HttpReqRes httpReqRes);

	JSONObject dlist(HttpReqRes httpReqRes);

	JSONObject slist(HttpReqRes httpReqRes);

}
