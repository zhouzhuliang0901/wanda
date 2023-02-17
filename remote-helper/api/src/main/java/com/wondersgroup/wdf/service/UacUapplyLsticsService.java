package com.wondersgroup.wdf.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.dao.UacUapplyLstics;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 办件关联物流业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUapplyLsticsService {

	/**
	 * 根据主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID} {@link UacUapplyLstics#ST_APPLY_ID}获取办件关联物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID}
	 * @param stApplyId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_APPLY_ID}
	 * @return 办件关联物流实例
	 */
	UacUapplyLstics get(String stUnionLogisticsId, String stApplyId);

	/**
	 * 查询办件关联物流列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 办件关联物流列表
	 */
	PaginationArrayList<UacUapplyLstics> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID} {@link UacUapplyLstics#ST_APPLY_ID}删除办件关联物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID}
	 * @param stApplyId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_APPLY_ID}
	 */
	void remove(String stUnionLogisticsId, String stApplyId);

	/**
	 * 保存或更新办件关联物流
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 办件关联物流实例
	 */
	UacUapplyLstics saveOrUpdate(RequestWrapper wrapper);

}
