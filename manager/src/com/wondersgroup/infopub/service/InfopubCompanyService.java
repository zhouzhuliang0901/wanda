package com.wondersgroup.infopub.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.infopub.bean.InfopubCompany;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 设备厂商业务接口
 * 
 * @author scalffold
 * 
 */
public interface InfopubCompanyService {

	/**
	 * 根据主键 {@link InfopubCompany#ST_COMPANY_ID}获取设备厂商
	 * 
	 * @param stCompanyId
	 *            设备厂商主键 {@link InfopubCompany#ST_COMPANY_ID}
	 * @return 设备厂商实例
	 */
	InfopubCompany get(String stCompanyId);

	/**
	 * 查询设备厂商列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备厂商列表
	 */
	JSONObject companyList(HttpReqRes httpReqRes);

	/**
	 * 根据主键 {@link InfopubCompany#ST_COMPANY_ID}删除设备厂商
	 * 
	 * @param stCompanyId
	 *            设备厂商主键 {@link InfopubCompany#ST_COMPANY_ID}
	 */
	void remove(String stCompanyId);

	/**
	 * 保存或更新设备厂商
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备厂商实例
	 */
	InfopubCompany saveOrUpdate(RequestWrapper wrapper);

	void removeList(HttpReqRes httpReqRes);

	JSONObject CompWithType(HttpReqRes httpReqRes);


}
