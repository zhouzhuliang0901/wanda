package com.wondersgroup.wdf.service.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.wondersgroup.wdf.dao.UacWebSystemTwo;
import com.wondersgroup.wdf.dao.UacWebSystemTwoDao;
import com.wondersgroup.wdf.service.UacWebSystemServiceTwo;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
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
public class UacWebSystemServiceTwoImpl implements UacWebSystemServiceTwo {

	/**
	 * 根据主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}获取系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}
	 * @return 系统信息实例
	 */
	@Override
	public UacWebSystemTwo get(String stWebSystemId) {
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		return uacWebSystemTwoDao.get(stWebSystemId);
	}

	/**
	 * 查询系统信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 系统信息列表
	 */
	@Override
	public PaginationArrayList<UacWebSystemTwo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacWebSystemTwo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacWebSystemTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}删除系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}
	 */
	@Override
	public void remove(String stWebSystemId) {
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		uacWebSystemTwoDao.delete(stWebSystemId);
	}

	/**
	 * 保存或更新系统信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 系统信息实例
	 */
	@Override
	public UacWebSystemTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacWebSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacWebSystemTwo.ST_WEB_SYSTEM_ID);
		UacWebSystemTwo oldUacWebSystemTwo = null;
		if (!StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
			oldUacWebSystemTwo = uacWebSystemTwoDao.get(stWebSystemId);
		}
		if (oldUacWebSystemTwo == null) {// new
			UacWebSystemTwo newUacWebSystemTwo = (UacWebSystemTwo) t4r.toBean(UacWebSystemTwo.class);
			newUacWebSystemTwo.setStWebSystemId(UUID.randomUUID().toString());
			uacWebSystemTwoDao.add(newUacWebSystemTwo);
			return newUacWebSystemTwo;
		}else {// update
			oldUacWebSystemTwo = (UacWebSystemTwo) t4r.toBean(oldUacWebSystemTwo, UacWebSystemTwo.class);
			uacWebSystemTwoDao.update(oldUacWebSystemTwo);
			return oldUacWebSystemTwo;
		}
	}

	@Autowired
	private UacWebSystemTwoDao uacWebSystemTwoDao;

}
