package com.wondersgroup.selmAssist.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.selmAssist.bean.SelmAssist;
import com.wondersgroup.serverApply.bean.SelmServerItem;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 设备辅助人员业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmAssistService {

	/**
	 * 根据主键 {@link SelmAssist#ST_ASSIST_ID}获取设备辅助人员
	 * 
	 * @param stAssistId
	 *            设备辅助人员主键 {@link SelmAssist#ST_ASSIST_ID}
	 * @return 设备辅助人员实例
	 */
	SelmAssist get(String stAssistId);

	/**
	 * 查询设备辅助人员列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备辅助人员列表
	 */
	JSONObject list(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link SelmAssist#ST_ASSIST_ID}删除设备辅助人员
	 * 
	 * @param stAssistId
	 *            设备辅助人员主键 {@link SelmAssist#ST_ASSIST_ID}
	 */
	void removeList(HttpReqRes httpReqRes);

	/**
	 * 保存或更新设备辅助人员
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备辅助人员实例
	 */


	SelmAssist saveOrUpdate(RequestWrapper wrapper);


	

}
