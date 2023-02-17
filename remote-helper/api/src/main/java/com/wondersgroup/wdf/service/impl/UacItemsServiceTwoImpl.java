package com.wondersgroup.wdf.service.impl;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import com.wondersgroup.wdf.dao.UacItemsLinkTwoDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.wdf.dao.UacItemsTwo;
import com.wondersgroup.wdf.dao.UacItemsTwoDao;
import com.wondersgroup.wdf.service.UacItemsServiceTwo;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 主题事项（综合）业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemsServiceTwoImpl implements UacItemsServiceTwo {

	/**
	 * 根据主键 {@link UacItemsTwo#ST_ITEMS_ID}获取主题事项（综合）
	 * 
	 * @param stItemsId
	 *            主题事项（综合）主键 {@link UacItemsTwo#ST_ITEMS_ID}
	 * @return 主题事项（综合）实例
	 */
	@Override
	public UacItemsTwo get(String stItemsId) {
		if (StringUtils.trimToEmpty(stItemsId).isEmpty())
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		return uacItemsTwoDao.get(stItemsId);
	}

	/**
	 * 查询主题事项（综合）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题事项（综合）列表
	 */
	@Override
	public PaginationArrayList<UacItemsTwo> query(RequestWrapper wrapper) {
		Conditions conds;
		// 主题编码
		String stItemsCode = wrapper.getParameter("ST_ITEMS_CODE");
		// 主题名称
		String stItemsName = wrapper.getParameter("ST_ITEMS_NAME");
		// 主题类型
		String stType = wrapper.getParameter("ST_TYPE");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		// 是否删除状态为0
		String nmRemoved = "0";
		// 排序
		String suffix = "ORDER BY NM_ORDER";
		// 获取数据
		conds = Conditions.newAndConditions();
		if (!StringUtils.trim(nmRemoved).isEmpty()) {
			conds.add(new Condition("NM_REMOVED", Condition.OT_EQUAL,
					nmRemoved));
		}

		if (stItemsCode != null) {
			if (!StringUtils.trim(stItemsCode).isEmpty()) {
				conds.add(new Condition("ST_ITEMS_CODE", Condition.OT_EQUAL,
						stItemsCode));
			}
		}

		if (stItemsName != null) {
			if (!StringUtils.trim(stItemsName).isEmpty()) {
				conds.add(new Condition("ST_ITEMS_NAME", Condition.OT_LIKE,
						stItemsName));
			}
		}

		if (stType != null) {
			if (!StringUtils.trim(stType).isEmpty()) {
				conds.add(new Condition("ST_TYPE", Condition.OT_EQUAL,
						stType));
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
		return uacItemsTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemsTwo#ST_ITEMS_ID}删除主题事项（综合）
	 * 
	 * @param stItemsId
	 *            主题事项（综合）主键 {@link UacItemsTwo#ST_ITEMS_ID}
	 */
	@Override
	public void remove(String[] stItemsId) {
		if (stItemsId.length == 0)
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		uacItemsTwoDao.delete(stItemsId);
		// 删除关联事项
		for (String itemsId : stItemsId) {
			if (!org.apache.commons.lang3.StringUtils.trimToEmpty(itemsId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_ITEMS_ID", Condition.OT_EQUAL, itemsId));
				// 删除主题服务事项关联
				uacItemsLinkTwoDao.delete(conds);
			}
		}
	}

	/**
	 * 保存或更新主题事项（综合）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 主题事项（综合）实例
	 */
	@Override
	public UacItemsTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItems.ST_ITEMS_ID
		String stItemsId = wrapper.getParameter(UacItemsTwo.ST_ITEMS_ID);
		UacItemsTwo oldUacItemsTwo = null;
		if (!StringUtils.trimToEmpty(stItemsId).isEmpty()) {
			oldUacItemsTwo = uacItemsTwoDao.get(stItemsId);
		}
		if (oldUacItemsTwo == null) {// new
			UacItemsTwo newUacItemsTwo = (UacItemsTwo) t4r.toBean(UacItemsTwo.class);
			newUacItemsTwo.setStItemsId(UUID.randomUUID().toString());
			newUacItemsTwo.setNmRemoved(new BigDecimal(0));
			newUacItemsTwo.setDtCreate(new Timestamp(System.currentTimeMillis()));
			uacItemsTwoDao.add(newUacItemsTwo);
			return newUacItemsTwo;
		}else {// update
			oldUacItemsTwo = (UacItemsTwo) t4r.toBean(oldUacItemsTwo, UacItemsTwo.class);
			oldUacItemsTwo.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			uacItemsTwoDao.update(oldUacItemsTwo);
			return oldUacItemsTwo;
		}
	}

	@Override
	public PaginationArrayList<UacItemsTwo> queryNoLink(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String stItemID = wrapper.getParameter("ST_ITEM_ID");
		String stItemsName = wrapper.getParameter("ST_ITEMS_NAME");

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return uacItemsTwoDao.queryNoLink(conds, stItemID, stItemsName, pageSize, currentPage);
	}

	@Override
	public void logicDelete(String[] stItemsId) {
		if (stItemsId.length == 0)
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		uacItemsTwoDao.logicDelete(stItemsId);
		// 删除关联事项
		for (String itemsId : stItemsId) {
			if (!org.apache.commons.lang3.StringUtils.trimToEmpty(itemsId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_ITEMS_ID", Condition.OT_EQUAL, itemsId));
				// 删除主题服务事项关联
				uacItemsLinkTwoDao.delete(conds);
			}
		}
	}

	@Autowired
	private UacItemsTwoDao uacItemsTwoDao;
	@Autowired
	private UacItemsLinkTwoDao uacItemsLinkTwoDao;

}
