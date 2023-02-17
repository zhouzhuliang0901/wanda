package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.ItemsLinkItemTwo;
import com.wondersgroup.wdf.dao.UacItemsLinkTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 主题服务事项关联业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemsLinkServiceTwo {

	/**
	 * 根据主键 {@link UacItemsLinkTwo#ST_ITEMS_ID} {@link UacItemsLinkTwo#ST_ITEM_ID}获取主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEM_ID}
	 * @return 主题服务事项关联实例
	 */
	UacItemsLinkTwo get(String stItemsId, String stItemId);

	/**
	 * 查询主题服务事项关联列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题服务事项关联列表
	 */
	PaginationArrayList<UacItemsLinkTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemsLinkTwo#ST_ITEMS_ID} {@link UacItemsLinkTwo#ST_ITEM_ID}删除主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEM_ID}
	 */
	void remove(String[] stItemsId, String[] stItemId);

	/**
	 * 保存或更新主题服务事项关联
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 主题服务事项关联实例
	 */
	UacItemsLinkTwo save(RequestWrapper wrapper);

	UacItemsLinkTwo saveitem(RequestWrapper wrapper);

	PaginationArrayList<ItemsLinkItemTwo> select(RequestWrapper wrapper);

	PaginationArrayList<ItemsLinkItemTwo> queryByStItemId(RequestWrapper wrapper);

	PaginationArrayList<ItemsLinkItemTwo> queryByStItemsId(RequestWrapper wrapper);

}
