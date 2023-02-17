package com.wondersgroup.infopub.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 地址表（办理点）业务接口
 * 
 * @author scalffold
 * 
 */
public interface InfopubAddressService {

	/**
	 * 根据主键 {@link InfopubAddress#ST_ADDRESS_ID}获取地址表（办理点）
	 * 
	 * @param stAddressId
	 *            地址表（办理点）主键 {@link InfopubAddress#ST_ADDRESS_ID}
	 * @return 地址表（办理点）实例
	 */
	InfopubAddress get(String stAddressId);

	/**
	 * 查询地址表（办理点）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 地址表（办理点）列表
	 */
	PaginationArrayList<InfopubAddress> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link InfopubAddress#ST_ADDRESS_ID}删除地址表（办理点）
	 * 
	 * @param stAddressId
	 *            地址表（办理点）主键 {@link InfopubAddress#ST_ADDRESS_ID}
	 */
	void remove(String stAddressId);

	/**
	 * 保存或更新地址表（办理点）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 地址表（办理点）实例
	 */
	InfopubAddress saveOrUpdate(RequestWrapper wrapper);

	JSONObject list(HttpReqRes httpReqRes);

	void removeList(HttpReqRes httpReqRes);

	InfopubAddress importAddress(RequestWrapper wrapper);

	JSONObject getAllArea(HttpReqRes httpReqRes);

	JSONObject initAddress(HttpReqRes httpReqRes);

	InfopubArea getArea(String stCity);

}
