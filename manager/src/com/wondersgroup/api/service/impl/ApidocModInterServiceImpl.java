package com.wondersgroup.api.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.wondersgroup.api.bean.ApidocModInter;
import com.wondersgroup.api.dao.ApidocModInterDao;
import com.wondersgroup.api.service.ApidocModInterService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;

/**
 * 模块关联接口业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class ApidocModInterServiceImpl implements ApidocModInterService {

	/**
	 * 根据主键 {@link ApidocModInter#ST_MODULE_ID} {@link ApidocModInter#ST_INTERFACE_ID}获取模块关联接口
	 * 
	 * @param stModuleId
	 *            模块关联接口主键 {@link ApidocModInter#ST_MODULE_ID}
	 * @param stInterfaceId
	 *            模块关联接口主键 {@link ApidocModInter#ST_INTERFACE_ID}
	 * @return 模块关联接口实例
	 */
	@Override
	public ApidocModInter get(String stModuleId, String stInterfaceId) {
		if (StringUtils.trimToEmpty(stModuleId).isEmpty())
			throw new NullPointerException("Parameter stModuleId cannot be null.");
		if (StringUtils.trimToEmpty(stInterfaceId).isEmpty())
			throw new NullPointerException("Parameter stInterfaceId cannot be null.");
		return apidocModInterDao.get(stModuleId, stInterfaceId);
	}

	/**
	 * 查询模块关联接口列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 模块关联接口列表
	 */
	@Override
	public PaginationArrayList<ApidocModInter> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(ApidocModInter.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return apidocModInterDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link ApidocModInter#ST_MODULE_ID} {@link ApidocModInter#ST_INTERFACE_ID}删除模块关联接口
	 * 
	 * @param stModuleId
	 *            模块关联接口主键 {@link ApidocModInter#ST_MODULE_ID}
	 * @param stInterfaceId
	 *            模块关联接口主键 {@link ApidocModInter#ST_INTERFACE_ID}
	 */
	@Override
	public void remove(String stModuleId, String stInterfaceId) {
		if (StringUtils.trimToEmpty(stModuleId).isEmpty())
			throw new NullPointerException("Parameter stModuleId cannot be null.");
		if (StringUtils.trimToEmpty(stInterfaceId).isEmpty())
			throw new NullPointerException("Parameter stInterfaceId cannot be null.");
		apidocModInterDao.delete(stModuleId, stInterfaceId);
	}

	/**
	 * 保存或更新模块关联接口
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 模块关联接口实例
	 */
	@Override
	public ApidocModInter saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// ApidocModInter.ST_MODULE_ID
		String stModuleId = wrapper.getParameter(ApidocModInter.ST_MODULE_ID);
		// ApidocModInter.ST_INTERFACE_ID
		String stInterfaceId = wrapper.getParameter(ApidocModInter.ST_INTERFACE_ID);
		ApidocModInter oldApidocModInter = null;
		if (!StringUtils.trimToEmpty(stModuleId).isEmpty() && !StringUtils.trimToEmpty(stInterfaceId).isEmpty()) {
			oldApidocModInter = apidocModInterDao.get(stModuleId, stInterfaceId);
		}
		if (oldApidocModInter == null) {// new
			ApidocModInter newApidocModInter = (ApidocModInter) t4r.toBean(ApidocModInter.class);
			newApidocModInter.setStModuleId(UUID.randomUUID().toString());
			newApidocModInter.setStInterfaceId(UUID.randomUUID().toString());
			apidocModInterDao.add(newApidocModInter);
			return newApidocModInter;
		}else {// update
			oldApidocModInter = (ApidocModInter) t4r.toBean(oldApidocModInter, ApidocModInter.class);
			apidocModInterDao.update(oldApidocModInter);
			return oldApidocModInter;
		}
	}

	@Autowired
	private ApidocModInterDao apidocModInterDao;

}
