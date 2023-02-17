package com.wondersgroup.wdf.uacItemStuff.service.impl;

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

import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuff;
import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuffDao;
import com.wondersgroup.wdf.uacItemStuff.service.UacItemStuffService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 事项材料业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemStuffServiceImpl implements UacItemStuffService {

	/**
	 * 根据主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}获取事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}
	 * @return 事项材料实例
	 */
	@Override
	public UacItemStuff get(String stItemStuffId) {
		if (StringUtils.trimToEmpty(stItemStuffId).isEmpty())
			throw new NullPointerException("Parameter stItemStuffId cannot be null.");
		return uacItemStuffDao.get(stItemStuffId);
	}

	/**
	 * 查询事项材料列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项材料列表
	 */
	@Override
	public PaginationArrayList<UacItemStuff> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemStuff.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemStuffDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}删除事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuff#ST_ITEM_STUFF_ID}
	 */
	@Override
	public void remove(String stItemStuffId) {
		if (StringUtils.trimToEmpty(stItemStuffId).isEmpty())
			throw new NullPointerException("Parameter stItemStuffId cannot be null.");
		uacItemStuffDao.delete(stItemStuffId);
	}

	/**
	 * 保存或更新事项材料
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项材料实例
	 */
	@Override
	public UacItemStuff saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemStuff.ST_ITEM_STUFF_ID
		String stItemStuffId = wrapper.getParameter(UacItemStuff.ST_ITEM_STUFF_ID);
		UacItemStuff oldUacItemStuff = null;
		if (!StringUtils.trimToEmpty(stItemStuffId).isEmpty()) {
			oldUacItemStuff = uacItemStuffDao.get(stItemStuffId);
		}
		if (oldUacItemStuff == null) {// new
			UacItemStuff newUacItemStuff = (UacItemStuff) t4r.toBean(UacItemStuff.class);
			newUacItemStuff.setStItemStuffId(UUID.randomUUID().toString());
			uacItemStuffDao.add(newUacItemStuff);
			return newUacItemStuff;
		}else {// update
			oldUacItemStuff = (UacItemStuff) t4r.toBean(oldUacItemStuff, UacItemStuff.class);
			uacItemStuffDao.update(oldUacItemStuff);
			return oldUacItemStuff;
		}
	}

	@Autowired
	private UacItemStuffDao uacItemStuffDao;

}
