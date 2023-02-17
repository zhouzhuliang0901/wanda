package com.wondersgroup.wdf.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.wondersgroup.wdf.dao.*;
import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLink;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.wdf.service.UacItemsLinkServiceTwo;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 主题服务事项关联业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemsLinkServiceTwoImpl implements UacItemsLinkServiceTwo {

	/**
	 * 根据主键 {@link UacItemsLinkTwo#ST_ITEMS_ID} {@link UacItemsLinkTwo#ST_ITEM_ID}获取主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEM_ID}
	 * @return 主题服务事项关联实例
	 */
	@Override
	public UacItemsLinkTwo get(String stItemsId, String stItemId) {
		if (StringUtils.trimToEmpty(stItemsId).isEmpty())
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		return uacItemsLinkTwoDao.get(stItemsId, stItemId);
	}

	/**
	 * 查询主题服务事项关联列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题服务事项关联列表
	 */
	@Override
	public PaginationArrayList<UacItemsLinkTwo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemsLinkTwo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemsLinkTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemsLinkTwo#ST_ITEMS_ID} {@link UacItemsLinkTwo#ST_ITEM_ID}删除主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLinkTwo#ST_ITEM_ID}
	 */
	@Override
	public void remove(String[] stItemsId, String[] stItemId) {
		if (stItemsId.length == 0)
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		if (stItemId.length == 0)
			throw new NullPointerException("Parameter stItemId cannot be null.");
		uacItemsLinkTwoDao.delete(stItemsId,stItemId);
	}

	/**
	 * 根据一个事项id保存多个主题主题服务事项关联
	 */
	@Override
	public UacItemsLinkTwo save(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		// 事项id
		String stItemId = wrapper.getParameter(UacItemsLinkTwo.ST_ITEM_ID);
		/*//是否必须
		String nmMust = wrapper.getParameter(UacItemsLinkTwo.NM_MUST);
		//序号
		String nmOrder = wrapper.getParameter(UacItemsLinkTwo.NM_ORDER);*/
		//多个主题id
		String[] stItemsId = wrapper.getParameterValues("ST_ITEMS_ID[]");
		UacItemsLinkTwo uacItemsLinkTwo = new UacItemsLinkTwo();
		if(stItemsId.length != 0){
			for (String s : stItemsId) {
				uacItemsLinkTwo.setStItemId(stItemId);
				/*uacItemsLinkTwo.setNmOrder(new BigDecimal(nmOrder));
				uacItemsLinkTwo.setNmMust(new BigDecimal(nmMust));*/
				uacItemsLinkTwo.setStItemsId(s);
				uacItemsLinkTwoDao.add(uacItemsLinkTwo);
			}
		}
		return null;
	}

	@Override
	public PaginationArrayList<ItemsLinkItemTwo> select(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		// 事项类型
		String stType = wrapper.getParameter("ST_TYPE");
		if (stType != null) {
			if (!StringUtils.trim(stType).isEmpty()) {
				conds.add(new Condition("uii.ST_TYPE", Condition.OT_EQUAL,
						stType));
			}
		}
		// 事项名称
		String stItemName = wrapper.getParameter("ST_ITEM_NAME");
		if (stItemName != null) {
			if (!StringUtils.trim(stItemName).isEmpty()) {
				conds.add(new Condition("uii.ST_ITEM_NAME", Condition.OT_LIKE,
						stItemName));
			}
		}
		// 证照编号
		String stEcertCode = wrapper.getParameter("ST_ECERT_CODE");
		if (stEcertCode != null) {
			if (!StringUtils.trim(stEcertCode).isEmpty()) {
				conds.add(new Condition("uii.ST_ECERT_CODE", Condition.OT_EQUAL,
						stEcertCode));
			}
		}
		// 证照名称
		String stEcertName = wrapper.getParameter("ST_ECERT_NAME");
		if (stEcertName != null) {
			if (!StringUtils.trim(stEcertName).isEmpty()) {
				conds.add(new Condition("uii.ST_ECERT_NAME", Condition.OT_LIKE,
						stEcertName));
			}
		}
		// 所属部门
		String stDepartName = wrapper.getParameter("ST_DEPART_NAME");
		if (stDepartName != null) {
			if (!StringUtils.trim(stDepartName).isEmpty()) {
				conds.add(new Condition("uii.ST_DEPART_NAME", Condition.OT_EQUAL,
						stDepartName));
			}
		}
		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return itemsLinkItemTwoDao.query(conds,pageSize, currentPage);
	}

	@Override
	public PaginationArrayList<ItemsLinkItemTwo> queryByStItemId(RequestWrapper wrapper) {
		String stItemId = wrapper.getParameter("ST_ITEM_ID");
		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return itemsLinkItemTwoDao.queryByStItemId(stItemId,pageSize, currentPage);
	}

	@Override
	public PaginationArrayList<ItemsLinkItemTwo> queryByStItemsId(RequestWrapper wrapper) {
		String stItemsId = wrapper.getParameter("ST_ITEMS_ID");
		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return itemsLinkItemTwoDao.queryByStItemsId(stItemsId,pageSize, currentPage);
	}

	/**
	 * 根据一个主题id保存多个事项主题服务事项关联
	 */
	@Override
	public UacItemsLinkTwo saveitem(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		//多个事项id
		String[] stItemId = wrapper.getParameterValues("ST_ITEM_ID[]");
		/*//是否必须
		String nmMust = wrapper.getParameter(UacItemsLinkTwo.NM_MUST);
		//序号
		String nmOrder = wrapper.getParameter(UacItemsLinkTwo.NM_ORDER);*/
		//主题id
		String stItemsId = wrapper.getParameter("ST_ITEMS_ID");
		UacItemsLinkTwo uacItemsLinkTwo = new UacItemsLinkTwo();
		if(stItemId.length != 0){
			for (String s : stItemId) {
				uacItemsLinkTwo.setStItemId(s);
				/*uacItemsLinkTwo.setNmOrder(new BigDecimal(nmOrder));
				uacItemsLinkTwo.setNmMust(new BigDecimal(nmMust));*/
				uacItemsLinkTwo.setStItemsId(stItemsId);
				uacItemsLinkTwoDao.add(uacItemsLinkTwo);
			}
		}
		return null;
	}

	@Autowired
	private UacItemsLinkTwoDao uacItemsLinkTwoDao;
	@Autowired
	private ItemsLinkItemTwoDao itemsLinkItemTwoDao;

}
