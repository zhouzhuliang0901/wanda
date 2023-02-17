package com.wondersgroup.wdf.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.wondersgroup.wdf.dao.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.wdf.service.UacItemInfoServiceTwo;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 事项信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemInfoServiceTwoImpl implements UacItemInfoServiceTwo {

	/**
	 * 根据主键 {@link UacItemInfoTwo#ST_ITEM_ID}获取事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfoTwo#ST_ITEM_ID}
	 * @return 事项信息实例
	 */
	@Override
	public UacItemInfoTwo get(String stItemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		return uacItemInfoTwoDao.get(stItemId);
	}

	/**
	 * 查询事项信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项信息列表
	 */
	@Override
	public PaginationArrayList<UacItemInfoTwo> query(RequestWrapper wrapper) {
		Conditions conds;
		// 事项代码
		String stItemCode = wrapper.getParameter("ST_ITEM_CODE");
		// 事项名称
		String stItemName = wrapper.getParameter("ST_ITEM_NAME");
		// 部门名称
		String stDepartName = wrapper.getParameter("ST_DEPART_NAME");
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

		if (stItemCode != null) {
			if (!StringUtils.trim(stItemCode).isEmpty()) {
				conds.add(new Condition("ST_ITEM_CODE", Condition.OT_EQUAL,
						stItemCode));
			}
		}

		if (stItemName != null) {
			if (!StringUtils.trim(stItemName).isEmpty()) {
				conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE,
						stItemName));
			}
		}

		if (stDepartName != null) {
			if (!StringUtils.trim(stDepartName).isEmpty()) {
				conds.add(new Condition("ST_DEPART_NAME", Condition.OT_EQUAL,
						stDepartName));
			}
		}

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return uacItemInfoTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemInfoTwo#ST_ITEM_ID}删除事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfoTwo#ST_ITEM_ID}
	 */
	@Override
	public void remove(String[] stItemId) {
		if (stItemId.length == 0)
			throw new NullPointerException("Parameter stItemId cannot be null.");
		uacItemInfoTwoDao.delete(stItemId);
		// 删除关联事项
		for (String itemId : stItemId) {
			if (!org.apache.commons.lang3.StringUtils.trimToEmpty(itemId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, itemId));
				// 删除事项材料关联
//				uacItemStuffDao.delete(conds);
				// 删除主题服务事项关联
				uacItemsLinkTwoDao.delete(conds);
			}
		}
	}

	/**
	 * 保存或更新事项信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项信息实例
	 */
	@Override
	public UacItemInfoTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemInfo.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemInfoTwo.ST_ITEM_ID);
		UacItemInfoTwo oldUacItemInfoTwo = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty()) {
			oldUacItemInfoTwo = uacItemInfoTwoDao.get(stItemId);
		}
		if (oldUacItemInfoTwo == null) {// new
			UacItemInfoTwo newUacItemInfoTwo = (UacItemInfoTwo) t4r.toBean(UacItemInfoTwo.class);
			newUacItemInfoTwo.setStItemId(UUID.randomUUID().toString());
			newUacItemInfoTwo.setNmRemoved(new BigDecimal(0));
			uacItemInfoTwoDao.add(newUacItemInfoTwo);
			return newUacItemInfoTwo;
		}else {// update
			oldUacItemInfoTwo = (UacItemInfoTwo) t4r.toBean(oldUacItemInfoTwo, UacItemInfoTwo.class);
			uacItemInfoTwoDao.update(oldUacItemInfoTwo);
			return oldUacItemInfoTwo;
		}
	}

	//除去与主题关联的事项列表
	@Override
	public PaginationArrayList<UacItemInfoTwo> queryNoLink(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String stItemsID = wrapper.getParameter("ST_ITEMS_ID");
		String stItemName = wrapper.getParameter("ST_ITEM_NAME");

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return uacItemInfoTwoDao.queryNoLink(conds, stItemsID, stItemName, pageSize, currentPage);
	}

	//除去与事项组关联的事项列表
	@Override
	public PaginationArrayList<UacItemInfoTwo> queryNoGroupLink(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String stGroupId = wrapper.getParameter("ST_GROUP_ID");
		String stItemName = wrapper.getParameter("ST_ITEM_NAME");

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return uacItemInfoTwoDao.queryNoGroupLink(conds, stGroupId, stItemName, pageSize, currentPage);
	}

	@Override
	public void logicDelete(String[] stItemId) {
		if (stItemId.length == 0)
			throw new NullPointerException("Parameter stItemId cannot be null.");
		uacItemInfoTwoDao.logicDelete(stItemId);
		// 删除关联事项
		for (String itemId : stItemId) {
			if (!org.apache.commons.lang3.StringUtils.trimToEmpty(itemId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, itemId));
				// 删除事项材料关联
//				uacItemStuffDao.delete(conds);
				// 删除主题服务事项关联
				uacItemsLinkTwoDao.delete(conds);
				//删除组关联事项
				uacItemGroupLinkTwoDao.delete(conds);
			}
		}
	}


	@Autowired
	private UacItemInfoTwoDao uacItemInfoTwoDao;
	@Autowired
	private UacItemsLinkTwoDao uacItemsLinkTwoDao;
	@Autowired
	private UacItemGroupLinkTwoDao uacItemGroupLinkTwoDao;
	@Autowired
	private UacItemSystemTwoDao uacItemSystemTwoDao;
//	@Autowired
//	private UacItemStuffDao uacItemStuffDao;

}
