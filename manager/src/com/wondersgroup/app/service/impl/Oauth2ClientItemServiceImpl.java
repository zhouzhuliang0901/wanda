package com.wondersgroup.app.service.impl;

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


import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.app.bean.Oauth2ClientItem;
import com.wondersgroup.app.dao.Oauth2ClientItemDao;
import com.wondersgroup.app.service.Oauth2ClientItemService;

/**
 * 授权事项业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class Oauth2ClientItemServiceImpl implements Oauth2ClientItemService {

	/**
	 * 根据主键 {@link Oauth2ClientItem#ST_OAUTH2_ID} {@link Oauth2ClientItem#ST_ITEM_ID}获取授权事项
	 * 
	 * @param stOauth2Id
	 *            授权事项主键 {@link Oauth2ClientItem#ST_OAUTH2_ID}
	 * @param stItemId
	 *            授权事项主键 {@link Oauth2ClientItem#ST_ITEM_ID}
	 * @return 授权事项实例
	 */
	@Override
	public Oauth2ClientItem get(String stOauth2Id, String stItemId) {
		if (StringUtils.trimToEmpty(stOauth2Id).isEmpty())
			throw new NullPointerException("Parameter stOauth2Id cannot be null.");
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		return oauth2ClientItemDao.get(stOauth2Id, stItemId);
	}
	
	@Override
	public Oauth2ClientItem getitem(String stOauth2Id) {
		if (StringUtils.trimToEmpty(stOauth2Id).isEmpty())
			throw new NullPointerException("Parameter stOauth2Id cannot be null.");
		return oauth2ClientItemDao.getitem(stOauth2Id);
	}


	/**
	 * 查询授权事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 授权事项列表
	 */
	@Override
	public PaginationArrayList<Oauth2ClientItem> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(Oauth2ClientItem.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return oauth2ClientItemDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link Oauth2ClientItem#ST_OAUTH2_ID} {@link Oauth2ClientItem#ST_ITEM_ID}删除授权事项
	 * 
	 * @param stOauth2Id
	 *            授权事项主键 {@link Oauth2ClientItem#ST_OAUTH2_ID}
	 * @param stItemId
	 *            授权事项主键 {@link Oauth2ClientItem#ST_ITEM_ID}
	 */
	@Override
	public void remove(String stOauth2Id, String stItemId) {
		if (StringUtils.trimToEmpty(stOauth2Id).isEmpty())
			throw new NullPointerException("Parameter stOauth2Id cannot be null.");
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		oauth2ClientItemDao.delete(stOauth2Id, stItemId);
	}

	/**
	 * 保存或更新授权事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 授权事项实例
	 */
	@Override
	public Oauth2ClientItem saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// Oauth2ClientItem.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2ClientItem.ST_OAUTH2_ID);
		// Oauth2ClientItem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(Oauth2ClientItem.ST_ITEM_ID);
		Oauth2ClientItem oldOauth2ClientItem = null;
		if (!StringUtils.trimToEmpty(stOauth2Id).isEmpty() && !StringUtils.trimToEmpty(stItemId).isEmpty()) {
			oldOauth2ClientItem = oauth2ClientItemDao.get(stOauth2Id, stItemId);
		}
		if (oldOauth2ClientItem == null) {// new
			Oauth2ClientItem newOauth2ClientItem = (Oauth2ClientItem) t4r.toBean(Oauth2ClientItem.class);
			newOauth2ClientItem.setStOauth2Id(stOauth2Id);
			newOauth2ClientItem.setStItemId(stItemId);
			oauth2ClientItemDao.add(newOauth2ClientItem);
			return newOauth2ClientItem;
		}else {// update
			/*oldOauth2ClientItem = (Oauth2ClientItem) t4r.toBean(oldOauth2ClientItem, Oauth2ClientItem.class);
			oauth2ClientItemDao.update(oldOauth2ClientItem);*/
			return oldOauth2ClientItem;
		}
	}

	@Autowired
	private Oauth2ClientItemDao oauth2ClientItemDao;

	
}
