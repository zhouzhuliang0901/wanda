package com.wondersgroup.wdf.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.dao.UacUapplyFeedback;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 综合办件反馈业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUapplyFeedbackService {

	/**
	 * 根据主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}获取综合办件反馈
	 * 
	 * @param stFeedbackId
	 *            综合办件反馈主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}
	 * @return 综合办件反馈实例
	 */
	UacUapplyFeedback get(String stFeedbackId);

	/**
	 * 查询综合办件反馈列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合办件反馈列表
	 */
	PaginationArrayList<UacUapplyFeedback> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}删除综合办件反馈
	 * 
	 * @param stFeedbackId
	 *            综合办件反馈主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}
	 */
	void remove(String stFeedbackId);

	/**
	 * 保存或更新综合办件反馈
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合办件反馈实例
	 */
	UacUapplyFeedback saveOrUpdate(RequestWrapper wrapper);

}
