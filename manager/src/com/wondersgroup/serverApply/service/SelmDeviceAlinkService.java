package com.wondersgroup.serverApply.service;

import java.math.*;
import java.sql.*;
import java.util.List;

import net.sf.json.JSONObject;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;



import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 接入申请关联设备业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmDeviceAlinkService {

	/**
	 * 根据主键 {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID} {@link SelmDeviceAlink#ST_MACHINE_ID}获取接入申请关联设备
	 * 
	 * @param stDeviceApplyId
	 *            接入申请关联设备主键 {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID}
	 * @param stMachineId
	 *            接入申请关联设备主键 {@link SelmDeviceAlink#ST_MACHINE_ID}
	 * @return 接入申请关联设备实例
	 */
	SelmDeviceAlink get(String stDeviceApplyId, String stMachineId);

	/**
	 * 查询接入申请关联设备列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 接入申请关联设备列表
	 */
	JSONObject query(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID} {@link SelmDeviceAlink#ST_MACHINE_ID}删除接入申请关联设备
	 * 
	 * @param stDeviceApplyId
	 *            接入申请关联设备主键 {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID}
	 * @param stMachineId
	 *            接入申请关联设备主键 {@link SelmDeviceAlink#ST_MACHINE_ID}
	 */
	void remove(String stDeviceApplyId, String stMachineId);

	/**
	 * 保存或更新接入申请关联设备
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 接入申请关联设备实例
	 */
	SelmDeviceAlink saveOrUpdate(HttpReqRes httpReqRes);

	SelmDeviceAlink getDeviceAlink(RequestWrapper wrapper);

	InfopubDeviceInfo getDeviceInfo(RequestWrapper wrapper);

	List<SelmDeviceAlink> deviceChangeSaveOrUpdate(RequestWrapper wrapper);

	JSONObject deviceList(HttpReqRes httpReqRes);

	SelmDeviceAlink getDeviceAlinkById(HttpReqRes httpReqRes);

	int saveNoPassReason(HttpReqRes httpReqRes);

}
