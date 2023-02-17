package com.wondersgroup.wdf.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;

import com.wondersgroup.wdf.dao.UacUapplyFeedback;
import com.wondersgroup.wdf.dao.UacUapplyFeedbackDao;
import com.wondersgroup.wdf.service.UacUapplyFeedbackService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 综合办件反馈业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyFeedbackServiceImpl implements UacUapplyFeedbackService {

	/**
	 * 根据主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}获取综合办件反馈
	 * 
	 * @param stFeedbackId
	 *            综合办件反馈主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}
	 * @return 综合办件反馈实例
	 */
	@Override
	public UacUapplyFeedback get(String stFeedbackId) {
		if (StringUtils.trimToEmpty(stFeedbackId).isEmpty())
			throw new NullPointerException("Parameter stFeedbackId cannot be null.");
		return uacUapplyFeedbackDao.get(stFeedbackId);
	}

	/**
	 * 查询综合办件反馈列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合办件反馈列表
	 */
	@Override
	public PaginationArrayList<UacUapplyFeedback> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyFeedback.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyFeedbackDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}删除综合办件反馈
	 * 
	 * @param stFeedbackId
	 *            综合办件反馈主键 {@link UacUapplyFeedback#ST_FEEDBACK_ID}
	 */
	@Override
	public void remove(String stFeedbackId) {
		if (StringUtils.trimToEmpty(stFeedbackId).isEmpty())
			throw new NullPointerException("Parameter stFeedbackId cannot be null.");
		uacUapplyFeedbackDao.delete(stFeedbackId);
	}

	/**
	 * 保存或更新综合办件反馈
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合办件反馈实例
	 */
	@Override
	public UacUapplyFeedback saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyFeedback.ST_FEEDBACK_ID
		String stFeedbackId = wrapper.getParameter(UacUapplyFeedback.ST_FEEDBACK_ID);
		UacUapplyFeedback oldUacUapplyFeedback = null;
		if (!StringUtils.trimToEmpty(stFeedbackId).isEmpty()) {
			oldUacUapplyFeedback = uacUapplyFeedbackDao.get(stFeedbackId);
		}
		if (oldUacUapplyFeedback == null) {// new
			UacUapplyFeedback newUacUapplyFeedback = (UacUapplyFeedback) t4r.toBean(UacUapplyFeedback.class);
			newUacUapplyFeedback.setStFeedbackId(UUID.randomUUID().toString());
			uacUapplyFeedbackDao.add(newUacUapplyFeedback);
			return newUacUapplyFeedback;
		}else {// update
			oldUacUapplyFeedback = (UacUapplyFeedback) t4r.toBean(oldUacUapplyFeedback, UacUapplyFeedback.class);
			uacUapplyFeedbackDao.update(oldUacUapplyFeedback);
			return oldUacUapplyFeedback;
		}
	}

	@Autowired
	private UacUapplyFeedbackDao uacUapplyFeedbackDao;

}
