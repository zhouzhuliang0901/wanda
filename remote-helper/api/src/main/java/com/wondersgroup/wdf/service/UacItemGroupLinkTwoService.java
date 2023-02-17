package com.wondersgroup.wdf.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.dao.UacItemGroupLinkTwo;

import com.wondersgroup.wdf.dao.UacItemsLinkTwo;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 组关联事项业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemGroupLinkTwoService {

	/**
	 * 根据主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID} {@link UacItemGroupLinkTwo#ST_GROUP_ID}获取组关联事项
	 * 
	 * @param stItemId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID}
	 * @param stGroupId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_GROUP_ID}
	 * @return 组关联事项实例
	 */
	UacItemGroupLinkTwo get(String stItemId, String stGroupId);

	/**
	 * 查询组关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 组关联事项列表
	 */
	PaginationArrayList<UacItemGroupLinkTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID} {@link UacItemGroupLinkTwo#ST_GROUP_ID}删除组关联事项
	 * 
	 * @param stItemId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID}
	 * @param stGroupId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_GROUP_ID}
	 */
	void remove(String[] stItemId, String[] stGroupId);

	/**
	 * 保存或更新组关联事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 组关联事项实例
	 */
	UacItemGroupLinkTwo saveOrUpdate(RequestWrapper wrapper);

	UacItemGroupLinkTwo saveitem(RequestWrapper wrapper);

}
