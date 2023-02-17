package com.wondersgroup.wdf.uacItemsLink.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLink;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 主题服务事项关联业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemsLinkService {

	/**
	 * 根据主键 {@link UacItemsLink#ST_ITEMS_ID} {@link UacItemsLink#ST_ITEM_ID}获取主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEM_ID}
	 * @return 主题服务事项关联实例
	 */
	UacItemsLink get(String stItemsId, String stItemId);

	/**
	 * 查询主题服务事项关联列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题服务事项关联列表
	 */
	PaginationArrayList<UacItemsLink> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemsLink#ST_ITEMS_ID} {@link UacItemsLink#ST_ITEM_ID}删除主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEM_ID}
	 */
	void remove(String stItemsId, String stItemId);

	/**
	 * 保存或更新主题服务事项关联
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 主题服务事项关联实例
	 */
	UacItemsLink saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 新增事项+业态关联
	 * @param stItemsId
	 * @param str_stItemId
	 *
	 */
	void addItems(String stItemsId,String...str_stItemId);

}
