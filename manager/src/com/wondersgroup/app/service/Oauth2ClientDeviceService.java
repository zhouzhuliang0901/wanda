package com.wondersgroup.app.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.app.bean.Oauth2ClientDevice;


import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 客户端关联设备业务接口
 * 
 * @author scalffold
 * 
 */
public interface Oauth2ClientDeviceService {

	/**
	 * 根据主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID} {@link Oauth2ClientDevice#ST_DEVICE_ID}获取客户端关联设备
	 * 
	 * @param stOauth2Id
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID}
	 * @param stDeviceId
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_DEVICE_ID}
	 * @return 客户端关联设备实例
	 */
	Oauth2ClientDevice get(String stOauth2Id, String stDeviceId);

	/**
	 * 查询客户端关联设备列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 客户端关联设备列表
	 */
	PaginationArrayList<Oauth2ClientDevice> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID} {@link Oauth2ClientDevice#ST_DEVICE_ID}删除客户端关联设备
	 * 
	 * @param stOauth2Id
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID}
	 * @param stDeviceId
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_DEVICE_ID}
	 */
	void remove(String stOauth2Id, String stDeviceId);

	/**
	 * 保存或更新客户端关联设备
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 客户端关联设备实例
	 */
	Oauth2ClientDevice saveOrUpdate(RequestWrapper wrapper);

}
