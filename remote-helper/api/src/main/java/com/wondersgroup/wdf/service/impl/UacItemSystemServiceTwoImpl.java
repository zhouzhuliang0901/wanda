package com.wondersgroup.wdf.service.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.wondersgroup.wdf.dao.UacItemSystemTwo;
import com.wondersgroup.wdf.dao.UacItemSystemTwoDao;
import com.wondersgroup.wdf.service.UacItemSystemServiceTwo;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
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
public class UacItemSystemServiceTwoImpl implements UacItemSystemServiceTwo {

	/**
	 * 根据主键 {@link UacItemSystemTwo#ST_ITEM_ID} {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}获取事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}
	 * @return 事项关联系统实例
	 */
	@Override
	public UacItemSystemTwo get(String stItemId, String stWebSystemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		return uacItemSystemTwoDao.get(stItemId, stWebSystemId);
	}

	/**
	 * 查询事项关联系统列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项关联系统列表
	 */
	@Override
	public PaginationArrayList<UacItemSystemTwo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemSystemTwo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemSystemTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemSystemTwo#ST_ITEM_ID} {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}删除事项关联系统
	 * 
	 * @param stItemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_ITEM_ID}
	 * @param stWebSystemId
	 *            事项关联系统主键 {@link UacItemSystemTwo#ST_WEB_SYSTEM_ID}
	 */
	@Override
	public void remove(String stItemId, String stWebSystemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
			throw new NullPointerException("Parameter stWebSystemId cannot be null.");
		uacItemSystemTwoDao.delete(stItemId, stWebSystemId);
	}

	/**
	 * 保存或更新事项关联系统
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项关联系统实例
	 */
	@Override
	public UacItemSystemTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemSystem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemSystemTwo.ST_ITEM_ID);
		// UacItemSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacItemSystemTwo.ST_WEB_SYSTEM_ID);
		UacItemSystemTwo oldUacItemSystemTwo = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
			oldUacItemSystemTwo = uacItemSystemTwoDao.get(stItemId, stWebSystemId);
		}
		if (oldUacItemSystemTwo == null) {// new
			UacItemSystemTwo newUacItemSystemTwo = (UacItemSystemTwo) t4r.toBean(UacItemSystemTwo.class);
			newUacItemSystemTwo.setStItemId(UUID.randomUUID().toString());
			newUacItemSystemTwo.setStWebSystemId(UUID.randomUUID().toString());
			uacItemSystemTwoDao.add(newUacItemSystemTwo);
			return newUacItemSystemTwo;
		}else {// update
			oldUacItemSystemTwo = (UacItemSystemTwo) t4r.toBean(oldUacItemSystemTwo, UacItemSystemTwo.class);
			uacItemSystemTwoDao.update(oldUacItemSystemTwo);
			return oldUacItemSystemTwo;
		}
	}

	@Autowired
	private UacItemSystemTwoDao uacItemSystemTwoDao;

}
