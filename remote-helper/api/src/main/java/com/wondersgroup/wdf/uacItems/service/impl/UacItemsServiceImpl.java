package com.wondersgroup.wdf.uacItems.service.impl;

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

import com.wondersgroup.wdf.uacItems.dao.UacItems;
import com.wondersgroup.wdf.uacItems.dao.UacItemsDao;
import com.wondersgroup.wdf.uacItems.service.UacItemsService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
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
public class UacItemsServiceImpl implements UacItemsService {

	/**
	 * 根据主键 {@link UacItems#ST_ITEMS_ID}获取主题事项（综合）
	 * 
	 * @param stItemsId
	 *            主题事项（综合）主键 {@link UacItems#ST_ITEMS_ID}
	 * @return 主题事项（综合）实例
	 */
	@Override
	public UacItems get(String stItemsId) {
		if (StringUtils.trimToEmpty(stItemsId).isEmpty())
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		return uacItemsDao.get(stItemsId);
	}

	/**
	 * 查询主题事项（综合）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 主题事项（综合）列表
	 */
	@Override
	public PaginationArrayList<UacItems> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItems.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemsDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItems#ST_ITEMS_ID}删除主题事项（综合）
	 * 
	 * @param stItemsId
	 *            主题事项（综合）主键 {@link UacItems#ST_ITEMS_ID}
	 */
	@Override
	public void remove(String stItemsId) {
		if (StringUtils.trimToEmpty(stItemsId).isEmpty())
			throw new NullPointerException("Parameter stItemsId cannot be null.");
		uacItemsDao.delete(stItemsId);
	}

	/**
	 * 保存或更新主题事项（综合）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 主题事项（综合）实例
	 */
	@Override
	public UacItems saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItems.ST_ITEMS_ID
		String stItemsId = wrapper.getParameter(UacItems.ST_ITEMS_ID);
		UacItems oldUacItems = null;
		if (!StringUtils.trimToEmpty(stItemsId).isEmpty()) {
			oldUacItems = uacItemsDao.get(stItemsId);
		}
		if (oldUacItems == null) {// new
			UacItems newUacItems = (UacItems) t4r.toBean(UacItems.class);
			newUacItems.setStItemsId(UUID.randomUUID().toString());
			uacItemsDao.add(newUacItems);
			return newUacItems;
		}else {// update
			oldUacItems = (UacItems) t4r.toBean(oldUacItems, UacItems.class);
			uacItemsDao.update(oldUacItems);
			return oldUacItems;
		}
	}

	@Autowired
	private UacItemsDao uacItemsDao;

}
