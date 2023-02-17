package com.wondersgroup.serverApply.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.serverApply.bean.SelmServerItem;



import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 服务关联事项业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmServerItemService {

	/**
	 * 根据主键 {@link SelmServerItem#ST_LINKS_ID}获取服务关联事项
	 * 
	 * @param stLinksId
	 *            服务关联事项主键 {@link SelmServerItem#ST_LINKS_ID}
	 * @return 服务关联事项实例
	 */
	SelmServerItem get(String stLinksId);

	/**
	 * 查询服务关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务关联事项列表
	 */
	PaginationArrayList<SelmServerItem> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmServerItem#ST_LINKS_ID}删除服务关联事项
	 * 
	 * @param stLinksId
	 *            服务关联事项主键 {@link SelmServerItem#ST_LINKS_ID}
	 */
	void remove(String stLinksId);

	SelmServerItem saveOrUpdate(HttpReqRes httpReqRes);

	JSONObject list(HttpReqRes httpReqRes);

	SelmServerItem CheckSave(HttpReqRes httpReqRes);

	void removeList(HttpReqRes httpReqRes);
	
	JSONObject nodeviceOCItemlist(HttpReqRes httpReqRes);
	
	JSONObject deviceoauth2ClientItemlist(HttpReqRes httpReqRes);
	
	int saveOrUpdatePower(HttpReqRes httpReqRes);

	void removePower(HttpReqRes httpReqRes);
	
	int saveOrUpdatePowerByGroup(HttpReqRes httpReqRes);

	SelmServerItem getSelmServerItem(HttpReqRes httpReqRes);

	/**
	 * 保存或更新服务关联事项
	 * 
	 * @param httpReqRes
	 *            提交参数
	 * @return 服务关联事项实例
	 */

}
