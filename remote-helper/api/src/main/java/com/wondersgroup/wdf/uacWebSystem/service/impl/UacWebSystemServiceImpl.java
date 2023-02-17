package com.wondersgroup.wdf.uacWebSystem.service.impl;

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

import com.wondersgroup.wdf.uacWebSystem.dao.UacWebSystem;
import com.wondersgroup.wdf.uacWebSystem.dao.UacWebSystemDao;
import com.wondersgroup.wdf.uacWebSystem.service.UacWebSystemService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacWebSystemServiceImpl implements UacWebSystemService {

	/**
	 * 根据主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}获取系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}
	 * @return 系统信息实例
	 */
	@Override
	public UacWebSystem get(String stWebSystemId) {
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		return uacWebSystemDao.get(stWebSystemId);
	}

	/**
	 * 查询系统信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 系统信息列表
	 */
	@Override
	public PaginationArrayList<UacWebSystem> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacWebSystem.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacWebSystemDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}删除系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystem#ST_WEB_SYSTEM_ID}
	 */
	@Override
	public void remove(String stWebSystemId) {
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		uacWebSystemDao.delete(stWebSystemId);
	}

	/**
	 * 保存或更新系统信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 系统信息实例
	 */
	@Override
	public UacWebSystem saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacWebSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacWebSystem.ST_WEB_SYSTEM_ID);
		UacWebSystem oldUacWebSystem = null;
		if (!StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
			oldUacWebSystem = uacWebSystemDao.get(stWebSystemId);
		}
		if (oldUacWebSystem == null) {// new
			UacWebSystem newUacWebSystem = (UacWebSystem) t4r.toBean(UacWebSystem.class);
			newUacWebSystem.setStWebSystemId(UUID.randomUUID().toString());
			uacWebSystemDao.add(newUacWebSystem);
			return newUacWebSystem;
		}else {// update
			oldUacWebSystem = (UacWebSystem) t4r.toBean(oldUacWebSystem, UacWebSystem.class);
			uacWebSystemDao.update(oldUacWebSystem);
			return oldUacWebSystem;
		}
	}

	@Autowired
	private UacWebSystemDao uacWebSystemDao;

}
