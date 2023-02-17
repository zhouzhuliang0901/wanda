package com.wondersgroup.wdf.uacItemSystem.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.wondersgroup.wdf.uacItemSystem.dao.UacItemSystemDaoExt;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;

import com.wondersgroup.wdf.uacItemSystem.dao.UacItemSystem;
import com.wondersgroup.wdf.uacItemSystem.dao.UacItemSystemDao;
import com.wondersgroup.wdf.uacItemSystem.service.UacItemSystemService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 事项关联系统业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemSystemServiceImpl implements UacItemSystemService {

	/**
	 * 根据主键 {@link UacItemSystem#ST_ITEM_ID} {@link UacItemSystem#ST_WEB_SYSTEM_ID}获取事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_WEB_SYSTEM_ID}
	 * @return 事项关联系统实例
	 */
	@Override
	public UacItemSystem get(String stItemId, String stWebSystemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		return uacItemSystemDao.get(stItemId, stWebSystemId);
	}

	/**
	 * 查询事项关联系统列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项关联系统列表
	 */
	@Override
	public PaginationArrayList<UacItemSystem> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemSystem.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemSystemDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemSystem#ST_ITEM_ID} {@link UacItemSystem#ST_WEB_SYSTEM_ID}删除事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystem#ST_WEB_SYSTEM_ID}
	 */
	@Override
	public void remove(String stItemId, String stWebSystemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		uacItemSystemDao.delete(stItemId, stWebSystemId);
	}

	/**
	 * 保存或更新事项关联系统
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项关联系统实例
	 */
	@Override
	public UacItemSystem saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemSystem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemSystem.ST_ITEM_ID);
		// UacItemSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacItemSystem.ST_WEB_SYSTEM_ID);
		UacItemSystem oldUacItemSystem = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
			oldUacItemSystem = uacItemSystemDao.get(stItemId, stWebSystemId);
		}
		if (oldUacItemSystem == null) {// new
			UacItemSystem newUacItemSystem = (UacItemSystem) t4r.toBean(UacItemSystem.class);
			newUacItemSystem.setStItemId(UUID.randomUUID().toString());
			newUacItemSystem.setStWebSystemId(UUID.randomUUID().toString());
			uacItemSystemDao.add(newUacItemSystem);
			return newUacItemSystem;
		}else {// update
			oldUacItemSystem = (UacItemSystem) t4r.toBean(oldUacItemSystem, UacItemSystem.class);
			uacItemSystemDao.update(oldUacItemSystem);
			return oldUacItemSystem;
		}
	}

	@Override
	public void addWeb(String stItemId, String... str_stWebSystemId) {
		uacItemSystemDaoExt.addWeb(stItemId,str_stWebSystemId);
	}

	@Autowired
	private UacItemSystemDao uacItemSystemDao;

	@Autowired
	private UacItemSystemDaoExt uacItemSystemDaoExt;

}
