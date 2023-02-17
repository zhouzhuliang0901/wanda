package com.wondersgroup.wdf.service.impl;


import com.wondersgroup.wdf.dao.UacAttachLinkDao;
import com.wondersgroup.wdf.dao.UacUapplyAttach;
import com.wondersgroup.wdf.dao.UacUapplyAttachDao;
import com.wondersgroup.wdf.service.UacUapplyAttachService;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import java.util.UUID;

/**
 * 综合材料附件业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyAttachServiceImpl implements UacUapplyAttachService {

	/**
	 * 根据主键 {@link UacUapplyAttach#ST_ATTACH_ID}获取综合材料附件
	 * 
	 * @param stAttachId
	 *            综合材料附件主键 {@link UacUapplyAttach#ST_ATTACH_ID}
	 * @return 综合材料附件实例
	 */
	@Override
	public UacUapplyAttach get(String stAttachId) {
		if (StringUtils.trimToEmpty(stAttachId).isEmpty())
			throw new NullPointerException("Parameter stAttachId cannot be null.");
		return uacUapplyAttachDao.get(stAttachId);
	}

	/**
	 * 查询综合材料附件列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合材料附件列表
	 */
	@Override
	public PaginationArrayList<UacUapplyAttach> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyAttach.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyAttachDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUapplyAttach#ST_ATTACH_ID}删除综合材料附件
	 * 
	 * @param stAttachId
	 *            综合材料附件主键 {@link UacUapplyAttach#ST_ATTACH_ID}
	 */
	@Override
	public void remove(String stAttachId) {
		if (StringUtils.trimToEmpty(stAttachId).isEmpty())
			throw new NullPointerException("Parameter stAttachId cannot be null.");
		uacUapplyAttachDao.delete(stAttachId);
	}

	@Override
	public void remove(String[] stAttachId) {
		if (stAttachId.length == 0)
			throw new NullPointerException("Parameter stMenuId cannot be null.");
		// 删除关联菜单
		for (String attachId : stAttachId) {
			// 菜单不为空的场合
			if (!StringUtils.trimToEmpty(attachId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, attachId));
				// 删除附件关联
				uacUapplyAttachDao.delete(conds);
				// 删除附件
				uacattachlinkdao.delete(conds);
			}
		}
	}

	/**
	 * 保存或更新综合材料附件
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合材料附件实例
	 */
	@Override
	public UacUapplyAttach saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyAttach.ST_ATTACH_ID
		String stAttachId = wrapper.getParameter(UacUapplyAttach.ST_ATTACH_ID);
		UacUapplyAttach oldUacUapplyAttach = null;
		if (!StringUtils.trimToEmpty(stAttachId).isEmpty()) {
			oldUacUapplyAttach = uacUapplyAttachDao.get(stAttachId);
		}
		if (oldUacUapplyAttach == null) {// new
			UacUapplyAttach newUacUapplyAttach = (UacUapplyAttach) t4r.toBean(UacUapplyAttach.class);
			newUacUapplyAttach.setStAttachId(UUID.randomUUID().toString());
			uacUapplyAttachDao.add(newUacUapplyAttach);
			return newUacUapplyAttach;
		}else {// update
			oldUacUapplyAttach = (UacUapplyAttach) t4r.toBean(oldUacUapplyAttach, UacUapplyAttach.class);
			uacUapplyAttachDao.update(oldUacUapplyAttach);
			return oldUacUapplyAttach;
		}
	}

	@Autowired
	private UacUapplyAttachDao uacUapplyAttachDao;

	@Autowired
	private UacAttachLinkDao uacattachlinkdao;

}
