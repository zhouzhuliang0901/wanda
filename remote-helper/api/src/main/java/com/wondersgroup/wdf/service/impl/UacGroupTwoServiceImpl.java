package com.wondersgroup.wdf.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.wondersgroup.wdf.dao.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;

import com.wondersgroup.wdf.service.UacGroupTwoService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 事项组业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacGroupTwoServiceImpl implements UacGroupTwoService {

	/**
	 * 根据主键 {@link UacGroupTwo#ST_GROUP_ID}获取事项组
	 * 
	 * @param stGroupId
	 *            事项组主键 {@link UacGroupTwo#ST_GROUP_ID}
	 * @return 事项组实例
	 */
	@Override
	public UacGroupTwo get(String stGroupId) {
		if (StringUtils.trimToEmpty(stGroupId).isEmpty())
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		return uacGroupTwoDao.get(stGroupId);
	}

	/**
	 * 查询事项组列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项组列表
	 */
	@Override
	public PaginationArrayList<UacGroupTwo> query(RequestWrapper wrapper) {
		Conditions conds;
		// 区域ID
		String stAreaId = wrapper.getParameter("ST_AREA_ID");
		// 事项组编码
		String stGroupCode = wrapper.getParameter("ST_GROUP_CODE");
		// 事项组名称
		String stGroupName = wrapper.getParameter("ST_GROUP_NAME");
		// 是否删除状态为0
		String nmRemoved = "0";
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		// 排序
		String suffix = "ORDER BY NM_ORDER";
		// 获取数据
		conds = Conditions.newAndConditions();
		if (!StringUtils.trim(nmRemoved).isEmpty()) {
			conds.add(new Condition("NM_REMOVED", Condition.OT_EQUAL,
					nmRemoved));
		}

		if (stAreaId != null) {
			if (!StringUtils.trim(stAreaId).isEmpty()) {
				conds.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL,
						stAreaId));
			}
		}

		if (stGroupCode != null) {
			if (!StringUtils.trim(stGroupCode).isEmpty()) {
				conds.add(new Condition("ST_GROUP_CODE", Condition.OT_EQUAL,
						stGroupCode));
			}
		}

		if (stGroupName != null) {
			if (!StringUtils.trim(stGroupName).isEmpty()) {
				conds.add(new Condition("ST_GROUP_NAME", Condition.OT_LIKE,
						stGroupName));
			}
		}

		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// 开始时间
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						startDate+" 00:00"));
			}
		}

		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						endDate+" 23:59"));
			}
		}

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return uacGroupTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacGroupTwo#ST_GROUP_ID}删除事项组
	 * 
	 * @param stGroupId
	 *            事项组主键 {@link UacGroupTwo#ST_GROUP_ID}
	 */
	@Override
	public void remove(String stGroupId) {
		if (StringUtils.trimToEmpty(stGroupId).isEmpty())
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		uacGroupTwoDao.delete(stGroupId);
	}

	/**
	 * 保存或更新事项组
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项组实例
	 */
	@Override
	public UacGroupTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacGroupTwo.ST_GROUP_ID
		String stGroupId = wrapper.getParameter(UacGroupTwo.ST_GROUP_ID);
		UacGroupTwo oldUacGroupTwo = null;
		if (!StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			oldUacGroupTwo = uacGroupTwoDao.get(stGroupId);
		}
		if (oldUacGroupTwo == null) {// new
			UacGroupTwo newUacGroupTwo = (UacGroupTwo) t4r.toBean(UacGroupTwo.class);
			newUacGroupTwo.setStGroupId(UUID.randomUUID().toString());
			newUacGroupTwo.setDtCreate(new Timestamp(System.currentTimeMillis()));
			newUacGroupTwo.setNmRemoved(new BigDecimal(0));
			uacGroupTwoDao.add(newUacGroupTwo);
			return newUacGroupTwo;
		}else {// update
			oldUacGroupTwo = (UacGroupTwo) t4r.toBean(oldUacGroupTwo, UacGroupTwo.class);
			oldUacGroupTwo.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			uacGroupTwoDao.update(oldUacGroupTwo);
			return oldUacGroupTwo;
		}
	}

	@Override
	public void logicDelete(String[] stGroupId) {
		if (stGroupId.length == 0)
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		uacGroupTwoDao.logicDelete(stGroupId);
		// 删除关联事项
		for (String groupId : stGroupId) {
			if (!org.apache.commons.lang3.StringUtils.trimToEmpty(groupId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, groupId));
				// 删除组关联人员
				uacUserGroupLinkTwoDao.delete(conds);
				//删除组关联事项
				uacItemGroupLinkTwoDao.delete(conds);
			}
		}
	}

	@Autowired
	private UacGroupTwoDao uacGroupTwoDao;
	@Autowired
	private UacUserGroupLinkTwoDao uacUserGroupLinkTwoDao;
	@Autowired
	private UacItemGroupLinkTwoDao uacItemGroupLinkTwoDao;

}
