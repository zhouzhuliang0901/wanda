package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacUapplyBasic;
import com.wondersgroup.wdf.dao.UacUapplyBasicDao;
import com.wondersgroup.wdf.service.UacUapplyBasicService;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import java.util.UUID;

/**
 * 综合受理一表式业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyBasicServiceImpl implements UacUapplyBasicService {

	/**
	 * 根据主键 {@link UacUapplyBasic#ST_APPLY_ID}获取综合受理一表式
	 * 
	 * @param stApplyId
	 *            综合受理一表式主键 {@link UacUapplyBasic#ST_APPLY_ID}
	 * @return 综合受理一表式实例
	 */
	@Override
	public UacUapplyBasic get(String stApplyId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		return uacUapplyBasicDao.get(stApplyId);
	}

	/**
	 * 查询综合受理一表式列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理一表式列表
	 */
	@Override
	public PaginationArrayList<UacUapplyBasic> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyBasic.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyBasicDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUapplyBasic#ST_APPLY_ID}删除综合受理一表式
	 * 
	 * @param stApplyId
	 *            综合受理一表式主键 {@link UacUapplyBasic#ST_APPLY_ID}
	 */
	@Override
	public void remove(String stApplyId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		uacUapplyBasicDao.delete(stApplyId);
	}

	/**
	 * 保存或更新综合受理一表式
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合受理一表式实例
	 */
	@Override
	public UacUapplyBasic saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyBasic.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUapplyBasic.ST_APPLY_ID);
		UacUapplyBasic oldUacUapplyBasic = null;
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			oldUacUapplyBasic = uacUapplyBasicDao.get(stApplyId);
		}
		if (oldUacUapplyBasic == null) {// new
			UacUapplyBasic newUacUapplyBasic = (UacUapplyBasic) t4r.toBean(UacUapplyBasic.class);
			newUacUapplyBasic.setStApplyId(UUID.randomUUID().toString());
			uacUapplyBasicDao.add(newUacUapplyBasic);
			return newUacUapplyBasic;
		}else {// update
			oldUacUapplyBasic = (UacUapplyBasic) t4r.toBean(oldUacUapplyBasic, UacUapplyBasic.class);
			uacUapplyBasicDao.update(oldUacUapplyBasic);
			return oldUacUapplyBasic;
		}
	}

	@Autowired
	private UacUapplyBasicDao uacUapplyBasicDao;

}
