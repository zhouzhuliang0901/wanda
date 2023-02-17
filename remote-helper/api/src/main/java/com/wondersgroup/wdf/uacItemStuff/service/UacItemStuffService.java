package com.wondersgroup.wdf.uacItemStuff.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuff;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项材料业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemStuffService {

	/**
	 * 根据主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}获取事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}
	 * @return 事项材料实例
	 */
	UacItemStuff get(String stItemStuffId);

	/**
	 * 查询事项材料列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项材料列表
	 */
	PaginationArrayList<UacItemStuff> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}删除事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}
	 */
	void remove(String stItemStuffId);

	/**
	 * 保存或更新事项材料
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项材料实例
	 */
	UacItemStuff saveOrUpdate(RequestWrapper wrapper);

}
