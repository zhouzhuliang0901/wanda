package com.wondersgroup.wdf.uacItemsLink.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLinkDaoExt;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;

import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLink;
import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLinkDao;
import com.wondersgroup.wdf.uacItemsLink.service.UacItemsLinkService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
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
public class UacItemsLinkServiceImpl implements UacItemsLinkService {

	/**
	 * 根据主键 {@link UacItemsLink#ST_ITEMS_ID} {@link UacItemsLink#ST_ITEM_ID}获取主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEM_ID}
	 * @return 主题服务事项关联实例
	 */
	@Override
	public UacItemsLink get(String stItemsId, String stItemId) {
		if (StringUtils.trimToEmpty(stItemsId).isEmpty())
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		return uacItemsLinkDao.get(stItemsId, stItemId);
	}

	/**
	 * 查询主题服务事项关联列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题服务事项关联列表
	 */
	@Override
	public PaginationArrayList<UacItemsLink> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemsLink.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemsLinkDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemsLink#ST_ITEMS_ID} {@link UacItemsLink#ST_ITEM_ID}删除主题服务事项关联
	 * 
	 * @param stItemsId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEMS_ID}
	 * @param stItemId
	 *            主题服务事项关联主键 {@link UacItemsLink#ST_ITEM_ID}
	 */
	@Override
	public void remove(String stItemsId, String stItemId) {
		if (StringUtils.trimToEmpty(stItemsId).isEmpty())
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		uacItemsLinkDao.delete(stItemsId, stItemId);
	}

	/**
	 * 保存或更新主题服务事项关联
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 主题服务事项关联实例
	 */
	@Override
	public UacItemsLink saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemsLink.ST_ITEMS_ID
		String stItemsId = wrapper.getParameter(UacItemsLink.ST_ITEMS_ID);
		// UacItemsLink.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemsLink.ST_ITEM_ID);
		UacItemsLink oldUacItemsLink = null;
		if (!StringUtils.trimToEmpty(stItemsId).isEmpty() && !StringUtils.trimToEmpty(stItemId).isEmpty()) {
			oldUacItemsLink = uacItemsLinkDao.get(stItemsId, stItemId);
		}
		if (oldUacItemsLink == null) {// new
			UacItemsLink newUacItemsLink = (UacItemsLink) t4r.toBean(UacItemsLink.class);
			newUacItemsLink.setStItemsId(UUID.randomUUID().toString());
			newUacItemsLink.setStItemId(UUID.randomUUID().toString());
			uacItemsLinkDao.add(newUacItemsLink);
			return newUacItemsLink;
		}else {// update
			oldUacItemsLink = (UacItemsLink) t4r.toBean(oldUacItemsLink, UacItemsLink.class);
			uacItemsLinkDao.update(oldUacItemsLink);
			return oldUacItemsLink;
		}
	}

	@Override
	public void addItems(String stItemsId, String... str_stItemId) {
		uacItemsLinkDaoExt.addItems(stItemsId,str_stItemId);
	}

	@Autowired
	private UacItemsLinkDao uacItemsLinkDao;

	@Autowired
	private UacItemsLinkDaoExt uacItemsLinkDaoExt;

}
