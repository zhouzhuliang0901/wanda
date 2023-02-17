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

import com.wondersgroup.wdf.dao.UacAttachLink;
import com.wondersgroup.wdf.dao.UacAttachLinkDao;
import com.wondersgroup.wdf.service.UacAttachLinkService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 电子材料关联附件业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacAttachLinkServiceImpl implements UacAttachLinkService {

	/**
	 * 根据主键 {@link UacAttachLink#ST_ESTUFF_ID} {@link UacAttachLink#ST_ATTACH_ID}获取电子材料关联附件
	 * 
	 * @param stEstuffId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ESTUFF_ID}
	 * @param stAttachId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ATTACH_ID}
	 * @return 电子材料关联附件实例
	 */
	@Override
	public UacAttachLink get(String stEstuffId, String stAttachId) {
		if (StringUtils.trimToEmpty(stEstuffId).isEmpty())
			throw new NullPointerException("Parameter stEstuffId cannot be null.");
		if (StringUtils.trimToEmpty(stAttachId).isEmpty())
			throw new NullPointerException("Parameter stAttachId cannot be null.");
		return uacAttachLinkDao.get(stEstuffId, stAttachId);
	}

	/**
	 * 查询电子材料关联附件列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 电子材料关联附件列表
	 */
	@Override
	public PaginationArrayList<UacAttachLink> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacAttachLink.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacAttachLinkDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacAttachLink#ST_ESTUFF_ID} {@link UacAttachLink#ST_ATTACH_ID}删除电子材料关联附件
	 * 
	 * @param stEstuffId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ESTUFF_ID}
	 * @param stAttachId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ATTACH_ID}
	 */
	@Override
	public void remove(String stEstuffId, String stAttachId) {
		if (StringUtils.trimToEmpty(stEstuffId).isEmpty())
			throw new NullPointerException("Parameter stEstuffId cannot be null.");
		if (StringUtils.trimToEmpty(stAttachId).isEmpty())
			throw new NullPointerException("Parameter stAttachId cannot be null.");
		uacAttachLinkDao.delete(stEstuffId, stAttachId);
	}

	/**
	 * 保存或更新电子材料关联附件
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 电子材料关联附件实例
	 */
	@Override
	public UacAttachLink saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacAttachLink.ST_ESTUFF_ID
		String stEstuffId = wrapper.getParameter(UacAttachLink.ST_ESTUFF_ID);
		// UacAttachLink.ST_ATTACH_ID
		String stAttachId = wrapper.getParameter(UacAttachLink.ST_ATTACH_ID);
		UacAttachLink oldUacAttachLink = null;
		if (!StringUtils.trimToEmpty(stEstuffId).isEmpty() && !StringUtils.trimToEmpty(stAttachId).isEmpty()) {
			oldUacAttachLink = uacAttachLinkDao.get(stEstuffId, stAttachId);
		}
		if (oldUacAttachLink == null) {// new
			UacAttachLink newUacAttachLink = (UacAttachLink) t4r.toBean(UacAttachLink.class);
			newUacAttachLink.setStEstuffId(UUID.randomUUID().toString());
			newUacAttachLink.setStAttachId(UUID.randomUUID().toString());
			uacAttachLinkDao.add(newUacAttachLink);
			return newUacAttachLink;
		}else {// update
			oldUacAttachLink = (UacAttachLink) t4r.toBean(oldUacAttachLink, UacAttachLink.class);
			uacAttachLinkDao.update(oldUacAttachLink);
			return oldUacAttachLink;
		}
	}

	@Autowired
	private UacAttachLinkDao uacAttachLinkDao;

}
