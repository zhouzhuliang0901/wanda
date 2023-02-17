package com.wondersgroup.wdf.uacCert.service.impl;

import com.wondersgroup.wdf.uacCert.dao.UacCert;
import com.wondersgroup.wdf.uacCert.dao.UacCertDao;
import com.wondersgroup.wdf.uacCert.service.UacCertService;
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

import java.util.List;
import java.util.UUID;

/**
 * 发证信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacCertServiceImpl implements UacCertService {

	/**
	 * 根据主键 {@link UacCert#ST_CERT_ID}获取发证信息
	 * 
	 * @param stCertId
	 *            发证信息主键 {@link UacCert#ST_CERT_ID}
	 * @return 发证信息实例
	 */
	@Override
	public UacCert get(String stCertId) {
		if (StringUtils.trimToEmpty(stCertId).isEmpty())
			throw new NullPointerException("Parameter stCertId cannot be null.");
		return uacCertDao.get(stCertId);
	}

	/**
	 * 查询发证信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 发证信息列表
	 */
	@Override
	public PaginationArrayList<UacCert> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacCert.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacCertDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacCert#ST_CERT_ID}删除发证信息
	 * 
	 * @param stCertId
	 *            发证信息主键 {@link UacCert#ST_CERT_ID}
	 */
	@Override
	public void remove(String stCertId) {
		if (StringUtils.trimToEmpty(stCertId).isEmpty())
			throw new NullPointerException("Parameter stCertId cannot be null.");
		uacCertDao.delete(stCertId);
	}

	/**
	 * 保存或更新发证信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 发证信息实例
	 */
	@Override
	public UacCert saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacCert.ST_CERT_ID
		String stCertId = wrapper.getParameter(UacCert.ST_CERT_ID);
		UacCert oldUacCert = null;
		if (!StringUtils.trimToEmpty(stCertId).isEmpty()) {
			oldUacCert = uacCertDao.get(stCertId);
		}
		if (oldUacCert == null) {// new
			UacCert newUacCert = (UacCert) t4r.toBean(UacCert.class);
			newUacCert.setStCertId(UUID.randomUUID().toString());
			uacCertDao.add(newUacCert);
			return newUacCert;
		}else {// update
			oldUacCert = (UacCert) t4r.toBean(oldUacCert, UacCert.class);
			uacCertDao.update(oldUacCert);
			return oldUacCert;
		}
	}

	@Override
	public UacCert getByApplyId(String stApplyId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(UacCert.ST_APPLY_ID, Condition.OT_EQUAL, stApplyId));
		List<UacCert> list = uacCertDao.query(conds, "");
		return list.size() > 0 ? list.get(0) : null;
	}

	@Autowired
	private UacCertDao uacCertDao;

}
