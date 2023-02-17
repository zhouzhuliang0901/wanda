package com.wondersgroup.wdf.service.impl;

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

import com.wondersgroup.wdf.dao.UacAreaTwo;
import com.wondersgroup.wdf.dao.UacAreaTwoDao;
import com.wondersgroup.wdf.service.UacAreaTwoService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 区域办理点业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacAreaTwoServiceImpl implements UacAreaTwoService {

	/**
	 * 根据主键 {@link UacAreaTwo#ST_AREA_ID}获取区域办理点
	 * 
	 * @param stAreaId
	 *            区域办理点主键 {@link UacAreaTwo#ST_AREA_ID}
	 * @return 区域办理点实例
	 */
	@Override
	public UacAreaTwo get(String stAreaId) {
		if (StringUtils.trimToEmpty(stAreaId).isEmpty())
			throw new NullPointerException("Parameter stAreaId cannot be null.");
		return uacAreaTwoDao.get(stAreaId);
	}

	/**
	 * 查询区域办理点列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 区域办理点列表
	 */
	@Override
	public PaginationArrayList<UacAreaTwo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacAreaTwo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = "ORDER BY NM_ORDER";;
		}
		return uacAreaTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacAreaTwo#ST_AREA_ID}删除区域办理点
	 * 
	 * @param stAreaId
	 *            区域办理点主键 {@link UacAreaTwo#ST_AREA_ID}
	 */
	@Override
	public void remove(String stAreaId) {
		if (StringUtils.trimToEmpty(stAreaId).isEmpty())
			throw new NullPointerException("Parameter stAreaId cannot be null.");
		uacAreaTwoDao.delete(stAreaId);
	}

	/**
	 * 保存或更新区域办理点
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 区域办理点实例
	 */
	@Override
	public UacAreaTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacAreaTwo.ST_AREA_ID
		String stAreaId = wrapper.getParameter(UacAreaTwo.ST_AREA_ID);
		UacAreaTwo oldUacAreaTwo = null;
		if (!StringUtils.trimToEmpty(stAreaId).isEmpty()) {
			oldUacAreaTwo = uacAreaTwoDao.get(stAreaId);
		}
		if (oldUacAreaTwo == null) {// new
			UacAreaTwo newUacAreaTwo = (UacAreaTwo) t4r.toBean(UacAreaTwo.class);
			newUacAreaTwo.setStAreaId(UUID.randomUUID().toString());
			newUacAreaTwo.setDtCreate(new Timestamp(System.currentTimeMillis()));
			uacAreaTwoDao.add(newUacAreaTwo);
			return newUacAreaTwo;
		}else {// update
			oldUacAreaTwo = (UacAreaTwo) t4r.toBean(oldUacAreaTwo, UacAreaTwo.class);
			oldUacAreaTwo.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			uacAreaTwoDao.update(oldUacAreaTwo);
			return oldUacAreaTwo;
		}
	}

	@Autowired
	private UacAreaTwoDao uacAreaTwoDao;

}
