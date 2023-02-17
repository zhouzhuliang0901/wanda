package com.wondersgroup.serverApply.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.serverApply.bean.SelmServerApply;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 服务开通申请业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmServerApplyService {

	/**
	 * 根据主键 {@link SelmServerApply#ST_APPLY_ID}获取服务开通申请
	 * 
	 * @param stApplyId
	 *            服务开通申请主键 {@link SelmServerApply#ST_APPLY_ID}
	 * @return 服务开通申请实例
	 */
	SelmServerApply get(String stApplyId);

	/**
	 * 查询服务开通申请列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务开通申请列表
	 */
	JSONObject list(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link SelmServerApply#ST_APPLY_ID}删除服务开通申请
	 * 
	 * @param stApplyId
	 *            服务开通申请主键 {@link SelmServerApply#ST_APPLY_ID}
	 */
	void remove(String stApplyId);

	/**
	 * 保存或更新服务开通申请
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 服务开通申请实例
	 */
	SelmServerApply saveOrUpdate(RequestWrapper wrapper);

	void removeList(HttpReqRes httpReqRes);

	JSONObject sreverNoItem(HttpReqRes httpReqRes);

	void downLoad(HttpReqRes httpReqRes);

	void saveSubmit(HttpReqRes httpReqRes);

	JSONObject deviceList(HttpReqRes httpReqRes);
	
	JSONObject checkdeviceList(HttpReqRes httpReqRes);
	
	void removeDevice(HttpReqRes httpReqRes);
	
	JSONObject groupItemData(HttpReqRes httpReqRes);
	
	JSONObject applyItemlist(HttpReqRes httpReqRes);

	int saveNoPass(HttpReqRes httpReqRes);

	int savePass(HttpReqRes httpReqRes);

	JSONObject checkRecordsList(HttpReqRes httpReqRes);

	int saveNoPassReason(HttpReqRes httpReqRes);

	int batchPass(HttpReqRes httpReqRes);

	int batchNoPass(HttpReqRes httpReqRes);

	JSONObject checkDeviceWithItem(HttpReqRes httpReqRes);

}
