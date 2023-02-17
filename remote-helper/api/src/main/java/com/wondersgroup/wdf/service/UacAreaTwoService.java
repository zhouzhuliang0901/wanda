package com.wondersgroup.wdf.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.dao.UacAreaTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 区域办理点业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacAreaTwoService {

	/**
	 * 根据主键 {@link UacAreaTwo#ST_AREA_ID}获取区域办理点
	 * 
	 * @param stAreaId
	 *            区域办理点主键 {@link UacAreaTwo#ST_AREA_ID}
	 * @return 区域办理点实例
	 */
	UacAreaTwo get(String stAreaId);

	/**
	 * 查询区域办理点列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 区域办理点列表
	 */
	PaginationArrayList<UacAreaTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacAreaTwo#ST_AREA_ID}删除区域办理点
	 * 
	 * @param stAreaId
	 *            区域办理点主键 {@link UacAreaTwo#ST_AREA_ID}
	 */
	void remove(String stAreaId);

	/**
	 * 保存或更新区域办理点
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 区域办理点实例
	 */
	UacAreaTwo saveOrUpdate(RequestWrapper wrapper);

}
