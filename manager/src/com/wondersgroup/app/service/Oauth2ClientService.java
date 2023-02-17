package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.app.bean.Oauth2Client;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * OAUTH2认证客户端业务接口
 * 
 * @author scalffold
 * 
 */
public interface Oauth2ClientService {

	/**
	 * 根据主键 {@link Oauth2Client#ST_OAUTH2_ID}获取OAUTH2认证客户端
	 * 
	 * @param stOauth2Id
	 *            OAUTH2认证客户端主键 {@link Oauth2Client#ST_OAUTH2_ID}
	 * @return OAUTH2认证客户端实例
	 */
	Oauth2Client get(String stOauth2Id);

	/**
	 * 查询OAUTH2认证客户端列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return OAUTH2认证客户端列表
	 */
	PaginationArrayList<Oauth2Client> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link Oauth2Client#ST_OAUTH2_ID}删除OAUTH2认证客户端
	 * 
	 * @param stOauth2Id
	 *            OAUTH2认证客户端主键 {@link Oauth2Client#ST_OAUTH2_ID}
	 */
	void remove(HttpReqRes httpReqRes);

	/**
	 * 保存或更新OAUTH2认证客户端
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return OAUTH2认证客户端实例
	 */
	Oauth2Client saveOrUpdate(RequestWrapper wrapper);

	JSONObject oauth2ClientList(HttpReqRes httpReqRes);

	JSONObject oauth2ClientItemlist(HttpReqRes httpReqRes);

	JSONObject noOCItemlist(HttpReqRes httpReqRes);

	JSONObject oauth2ClientDevicelist(HttpReqRes httpReqRes);

	JSONObject noOCDevicelist(HttpReqRes httpReqRes);
	
	JSONObject nodeviceOCItemlist(HttpReqRes httpReqRes);

	JSONObject deviceoauth2ClientItemlist(HttpReqRes httpReqRes);

}
