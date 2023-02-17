package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacUapplyBasic;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 综合受理一表式业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUapplyBasicService {

	/**
	 * 根据主键 {@link UacUapplyBasic#ST_APPLY_ID}获取综合受理一表式
	 * 
	 * @param stApplyId
	 *            综合受理一表式主键 {@link UacUapplyBasic#ST_APPLY_ID}
	 * @return 综合受理一表式实例
	 */
	UacUapplyBasic get(String stApplyId);

	/**
	 * 查询综合受理一表式列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理一表式列表
	 */
	PaginationArrayList<UacUapplyBasic> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUapplyBasic#ST_APPLY_ID}删除综合受理一表式
	 * 
	 * @param stApplyId
	 *            综合受理一表式主键 {@link UacUapplyBasic#ST_APPLY_ID}
	 */
	void remove(String stApplyId);

	/**
	 * 保存或更新综合受理一表式
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合受理一表式实例
	 */
	UacUapplyBasic saveOrUpdate(RequestWrapper wrapper);

}
