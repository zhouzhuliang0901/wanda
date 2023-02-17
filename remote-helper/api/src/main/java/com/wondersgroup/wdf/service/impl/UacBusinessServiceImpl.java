package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacBusiness;
import com.wondersgroup.wdf.dao.UacBusinessDao;
import com.wondersgroup.wdf.service.UacBusinessService;
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
 * 企业信息表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacBusinessServiceImpl implements UacBusinessService {

	/**
	 * 根据主键 {@link UacBusiness#ST_BUSINESS_ID}获取企业信息表
	 * 
	 * @param stBusinessId
	 *            企业信息表主键 {@link UacBusiness#ST_BUSINESS_ID}
	 * @return 企业信息表实例
	 */
	@Override
	public UacBusiness get(String stBusinessId) {
		if (StringUtils.trimToEmpty(stBusinessId).isEmpty())
			throw new NullPointerException("Parameter stBusinessId cannot be null.");
		return uacBusinessDao.get(stBusinessId);
	}

	/**
	 * 查询企业信息表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 企业信息表列表
	 */
	@Override
	public PaginationArrayList<UacBusiness> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacBusiness.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacBusinessDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacBusiness#ST_BUSINESS_ID}删除企业信息表
	 * 
	 * @param stBusinessId
	 *            企业信息表主键 {@link UacBusiness#ST_BUSINESS_ID}
	 */
	@Override
	public void remove(String stBusinessId) {
		if (StringUtils.trimToEmpty(stBusinessId).isEmpty())
			throw new NullPointerException("Parameter stBusinessId cannot be null.");
		uacBusinessDao.delete(stBusinessId);
	}

	/**
	 * 保存或更新企业信息表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 企业信息表实例
	 */
	@Override
	public UacBusiness saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacBusiness.ST_BUSINESS_ID
		String stBusinessId = wrapper.getParameter(UacBusiness.ST_BUSINESS_ID);
		UacBusiness oldUacBusiness = null;
		if (!StringUtils.trimToEmpty(stBusinessId).isEmpty()) {
			oldUacBusiness = uacBusinessDao.get(stBusinessId);
		}
		if (oldUacBusiness == null) {// new
			UacBusiness newUacBusiness = (UacBusiness) t4r.toBean(UacBusiness.class);
			newUacBusiness.setStBusinessId(UUID.randomUUID().toString());
			uacBusinessDao.add(newUacBusiness);
			return newUacBusiness;
		}else {// update
			oldUacBusiness = (UacBusiness) t4r.toBean(oldUacBusiness, UacBusiness.class);
			uacBusinessDao.update(oldUacBusiness);
			return oldUacBusiness;
		}
	}

	@Autowired
	private UacBusinessDao uacBusinessDao;

}
