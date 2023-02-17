package com.wondersgroup.selmAssist.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.selmAssist.bean.SelmDeviceAssist;
import com.wondersgroup.serverApply.bean.SelmServerItem;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 设备关联人员业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmDeviceAssistService {

	/**
	 * 根据主键 {@link SelmDeviceAssist#ST_ASSIST_ID} {@link SelmDeviceAssist#ST_DEVICE_ID}获取设备关联人员
	 * 
	 * @param stAssistId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_ASSIST_ID}
	 * @param stDeviceId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_DEVICE_ID}
	 * @return 设备关联人员实例
	 */
	SelmDeviceAssist get(String stAssistId, String stDeviceId);

	/**
	 * 查询设备关联人员列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备关联人员列表
	 */
	JSONObject list(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link SelmDeviceAssist#ST_ASSIST_ID} {@link SelmDeviceAssist#ST_DEVICE_ID}删除设备关联人员
	 * 
	 * @param stAssistId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_ASSIST_ID}
	 * @param stDeviceId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_DEVICE_ID}
	 */
	void removeList(HttpReqRes httpReqRes);

	/**
	 * 保存或更新设备关联人员
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备关联人员实例
	 */

	SelmDeviceAssist saveOrUpdate(HttpReqRes httpReqRes);

	


	

	

}
