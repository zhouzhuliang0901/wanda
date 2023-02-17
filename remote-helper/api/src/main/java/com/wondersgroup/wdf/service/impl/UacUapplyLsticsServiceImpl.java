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

import com.wondersgroup.wdf.dao.UacUapplyLstics;
import com.wondersgroup.wdf.dao.UacUapplyLsticsDao;
import com.wondersgroup.wdf.service.UacUapplyLsticsService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 办件关联物流业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyLsticsServiceImpl implements UacUapplyLsticsService {

	/**
	 * 根据主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID} {@link UacUapplyLstics#ST_APPLY_ID}获取办件关联物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID}
	 * @param stApplyId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_APPLY_ID}
	 * @return 办件关联物流实例
	 */
	@Override
	public UacUapplyLstics get(String stUnionLogisticsId, String stApplyId) {
		if (StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty())
			throw new NullPointerException("Parameter stUnionLogisticsId cannot be null.");
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		return uacUapplyLsticsDao.get(stUnionLogisticsId, stApplyId);
	}

	/**
	 * 查询办件关联物流列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 办件关联物流列表
	 */
	@Override
	public PaginationArrayList<UacUapplyLstics> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyLstics.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyLsticsDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID} {@link UacUapplyLstics#ST_APPLY_ID}删除办件关联物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_UNION_LOGISTICS_ID}
	 * @param stApplyId
	 *            办件关联物流主键 {@link UacUapplyLstics#ST_APPLY_ID}
	 */
	@Override
	public void remove(String stUnionLogisticsId, String stApplyId) {
		if (StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty())
			throw new NullPointerException("Parameter stUnionLogisticsId cannot be null.");
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		uacUapplyLsticsDao.delete(stUnionLogisticsId, stApplyId);
	}

	/**
	 * 保存或更新办件关联物流
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 办件关联物流实例
	 */
	@Override
	public UacUapplyLstics saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyLstics.ST_UNION_LOGISTICS_ID
		String stUnionLogisticsId = wrapper.getParameter(UacUapplyLstics.ST_UNION_LOGISTICS_ID);
		// UacUapplyLstics.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUapplyLstics.ST_APPLY_ID);
		UacUapplyLstics oldUacUapplyLstics = null;
		if (!StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty() && !StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			oldUacUapplyLstics = uacUapplyLsticsDao.get(stUnionLogisticsId, stApplyId);
		}
		if (oldUacUapplyLstics == null) {// new
			UacUapplyLstics newUacUapplyLstics = (UacUapplyLstics) t4r.toBean(UacUapplyLstics.class);
			newUacUapplyLstics.setStUnionLogisticsId(UUID.randomUUID().toString());
			newUacUapplyLstics.setStApplyId(UUID.randomUUID().toString());
			uacUapplyLsticsDao.add(newUacUapplyLstics);
			return newUacUapplyLstics;
		}else {// update
			oldUacUapplyLstics = (UacUapplyLstics) t4r.toBean(oldUacUapplyLstics, UacUapplyLstics.class);
			uacUapplyLsticsDao.update(oldUacUapplyLstics);
			return oldUacUapplyLstics;
		}
	}

	@Autowired
	private UacUapplyLsticsDao uacUapplyLsticsDao;

}
