package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacItemStuffTwo;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

import java.util.List;

/**
 * 事项材料业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacItemStuffTwoService {

	/**
	 * 根据主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}获取事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}
	 * @return 事项材料实例
	 */
	UacItemStuffTwo get(String stItemStuffId);

	/**
	 * 查询事项材料列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项材料列表
	 */
	PaginationArrayList<UacItemStuffTwo> query(RequestWrapper wrapper);

	/**
	 * 查询事项材料列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 事项材料列表
	 */
	List<UacItemStuffTwo> queryStuff(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}删除事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}
	 */
	void remove(String stItemStuffId);

	/**
	 * 保存或更新事项材料
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项材料实例
	 */
	UacItemStuffTwo saveOrUpdate(RequestWrapper wrapper);

	void logicDelete(String[] stItemStuffId);

}
