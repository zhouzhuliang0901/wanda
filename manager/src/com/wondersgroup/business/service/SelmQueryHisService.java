package com.wondersgroup.business.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.bean.SelmQueryHis;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 工作台模块使用历史记录业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmQueryHisService {

	/**
	 * 根据主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}获取工作台模块使用历史记录
	 * 
	 * @param stQueryHisId
	 *            工作台模块使用历史记录主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}
	 * @return 工作台模块使用历史记录实例
	 */
	SelmQueryHis getSelmQueryHis(String stQueryHisId);

	/**
	 * 查询工作台模块使用历史记录列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 工作台模块使用历史记录列表
	 */
	JSONObject queryHisList(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}删除工作台模块使用历史记录
	 * 
	 * @param stQueryHisId
	 *            工作台模块使用历史记录主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}
	 */
	void remove(HttpReqRes httpReqRes);

	/**
	 * 保存或更新工作台模块使用历史记录
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 工作台模块使用历史记录实例
	 */
	SelmQueryHis saveOrUpdate(RequestWrapper wrapper);

	
	/**
	 * 获取关联附件表中的信息
	 * @param httpReqRes
	 */
	SelmAttach getSelmAttach(SelmQueryHis selmQueryHis);

	
	SelmAttach getSelmAttach(String stAttachId);

	JSONObject queryHisListExcel(HttpReqRes httpReqRes);

	JSONObject statistics(HttpReqRes httpReqRes);

	JSONObject statisticsDay(HttpReqRes httpReqRes);

	JSONObject selmQuertTop(HttpReqRes httpReqRes);

	JSONObject areaQueryHis(HttpReqRes httpReqRes);

	JSONObject sqhDeviceItem(HttpReqRes httpReqRes);

	JSONObject unInputDeviceMac(HttpReqRes httpReqRes);

	JSONObject update60(HttpReqRes httpReqRes);


}
