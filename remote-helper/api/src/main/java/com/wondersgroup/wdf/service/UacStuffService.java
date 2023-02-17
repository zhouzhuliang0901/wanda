package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacStuff;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 材料信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacStuffService {

	/**
	 * 根据主键 {@link UacStuff#ST_STUFF_ID}获取材料信息
	 * 
	 * @param stStuffId
	 *            材料信息主键 {@link UacStuff#ST_STUFF_ID}
	 * @return 材料信息实例
	 */
	UacStuff get(String stStuffId);

	/**
	 * 查询材料信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 材料信息列表
	 */
	PaginationArrayList<UacStuff> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacStuff#ST_STUFF_ID}删除材料信息
	 * 
	 * @param stStuffId
	 *            材料信息主键 {@link UacStuff#ST_STUFF_ID}
	 */
	void remove(String stStuffId);

	/**
	 * 保存或更新材料信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 材料信息实例
	 */
	UacStuff saveOrUpdate(RequestWrapper wrapper);

	void logicDelete(String[] stStuffId);

}
