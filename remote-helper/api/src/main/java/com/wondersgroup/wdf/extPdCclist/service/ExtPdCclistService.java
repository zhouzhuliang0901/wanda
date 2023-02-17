package com.wondersgroup.wdf.extPdCclist.service;

import java.math.*;
import java.sql.*;
import java.util.List;

import com.wondersgroup.wdf.extPdCclist.dao.ExtPdCclist;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 子证归集表业务接口
 * 
 * @author scalffold
 * 
 */
public interface ExtPdCclistService {

	/**
	 * 根据主键 {@link ExtPdCclist#ST_CCLIST_ID}获取子证归集表
	 * 
	 * @param stCclistId
	 *            子证归集表主键 {@link ExtPdCclist#ST_CCLIST_ID}
	 * @return 子证归集表实例
	 */
	ExtPdCclist get(String stCclistId);

	/**
	 * 查询子证归集表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 子证归集表列表
	 */
	PaginationArrayList<ExtPdCclist> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link ExtPdCclist#ST_CCLIST_ID}删除子证归集表
	 * 
	 * @param stCclistIdList
	 *            子证归集表主键 {@link ExtPdCclist#ST_CCLIST_ID}
	 */
	void remove(String[] stCclistIdList);

	/**
	 * 保存或更新子证归集表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 子证归集表实例
	 */
	ExtPdCclist saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 根据条件查询子证归集表列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 子证归集表列表
	 */
	List<ExtPdCclist> select(RequestWrapper wrapper);

}
