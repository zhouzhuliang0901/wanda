package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.app.bean.Oauth2ClientItem;


import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 授权事项业务接口
 * 
 * @author scalffold
 * 
 */
public interface Oauth2ClientItemService {

	/**
	 * 根据主键 {@link Oauth2ClientItem#ST_OAUTH2_ID} {@link Oauth2ClientItem#ST_ITEM_ID}获取授权事项
	 * 
	 * @param stOauth2Id
	 *            授权事项主键 {@link Oauth2ClientItem#ST_OAUTH2_ID}
	 * @param stItemId
	 *            授权事项主键 {@link Oauth2ClientItem#ST_ITEM_ID}
	 * @return 授权事项实例
	 */
	Oauth2ClientItem get(String stOauth2Id, String stItemId);

	/**
	 * 查询授权事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 授权事项列表
	 */
	PaginationArrayList<Oauth2ClientItem> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link Oauth2ClientItem#ST_OAUTH2_ID} {@link Oauth2ClientItem#ST_ITEM_ID}删除授权事项
	 * 
	 * @param stOauth2Id
	 *            授权事项主键 {@link Oauth2ClientItem#ST_OAUTH2_ID}
	 * @param stItemId
	 *            授权事项主键 {@link Oauth2ClientItem#ST_ITEM_ID}
	 */
	void remove(String stOauth2Id, String stItemId);

	/**
	 * 保存或更新授权事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 授权事项实例
	 */
	Oauth2ClientItem saveOrUpdate(RequestWrapper wrapper);

	Oauth2ClientItem getitem(String stOauth2Id);

}
