package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacStuff;
import com.wondersgroup.wdf.dao.UacStuffDao;
import com.wondersgroup.wdf.service.UacStuffService;
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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * 材料信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacStuffServiceImpl implements UacStuffService {

	/**
	 * 根据主键 {@link UacStuff#ST_STUFF_ID}获取材料信息
	 * 
	 * @param stStuffId
	 *            材料信息主键 {@link UacStuff#ST_STUFF_ID}
	 * @return 材料信息实例
	 */
	@Override
	public UacStuff get(String stStuffId) {
		if (StringUtils.trimToEmpty(stStuffId).isEmpty())
			throw new NullPointerException("Parameter stStuffId cannot be null.");
		return uacStuffDao.get(stStuffId);
	}

	/**
	 * 查询材料信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 材料信息列表
	 */
	@Override
	public PaginationArrayList<UacStuff> query(RequestWrapper wrapper) {
		Conditions conds;
		// 材料代码
		String stStuffCode = wrapper.getParameter("ST_STUFF_CODE");
		// 材料名称
		String stStuffName = wrapper.getParameter("ST_STUFF_NAME");
		// 材料来源
		String stSource = wrapper.getParameter("ST_SOURCE");
		// 对应证照正规名称编号
		String stFormalId = wrapper.getParameter("ST_FORMAL_ID");
		// 是否删除状态为0
		String nmRemoved = "0";
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		// 排序
		String suffix = "ORDER BY DT_CREATE";
		// 获取数据
		conds = Conditions.newAndConditions();
		if (!StringUtils.trim(nmRemoved).isEmpty()) {
			conds.add(new Condition("NM_REMOVED", Condition.OT_EQUAL,
					nmRemoved));
		}

		if (stStuffCode != null) {
			if (!StringUtils.trim(stStuffCode).isEmpty()) {
				conds.add(new Condition("ST_STUFF_CODE", Condition.OT_EQUAL,
						stStuffCode));
			}
		}

		if (stStuffName != null) {
			if (!StringUtils.trim(stStuffName).isEmpty()) {
				conds.add(new Condition("ST_STUFF_NAME", Condition.OT_LIKE,
						stStuffName));
			}
		}

		if (stSource != null) {
			if (!StringUtils.trim(stSource).isEmpty()) {
				conds.add(new Condition("ST_SOURCE", Condition.OT_EQUAL,
						stSource));
			}
		}

		if (stFormalId != null) {
			if (!StringUtils.trim(stFormalId).isEmpty()) {
				conds.add(new Condition("ST_FORMAL_ID", Condition.OT_EQUAL,
						stFormalId));
			}
		}

		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// 开始时间
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						startDate+" 00:00"));
			}
		}

		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						endDate+" 23:59"));
			}
		}

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();

		return uacStuffDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacStuff#ST_STUFF_ID}删除材料信息
	 * 
	 * @param stStuffId
	 *            材料信息主键 {@link UacStuff#ST_STUFF_ID}
	 */
	@Override
	public void remove(String stStuffId) {
		if (StringUtils.trimToEmpty(stStuffId).isEmpty())
			throw new NullPointerException("Parameter stStuffId cannot be null.");
		uacStuffDao.delete(stStuffId);
	}

	/**
	 * 保存或更新材料信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 材料信息实例
	 */
	@Override
	public UacStuff saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacStuff.ST_STUFF_ID
		String stStuffId = wrapper.getParameter(UacStuff.ST_STUFF_ID);
		UacStuff oldUacStuff = null;
		if (!StringUtils.trimToEmpty(stStuffId).isEmpty()) {
			oldUacStuff = uacStuffDao.get(stStuffId);
		}
		if (oldUacStuff == null) {// new
			UacStuff newUacStuff = (UacStuff) t4r.toBean(UacStuff.class);
			newUacStuff.setStStuffId(UUID.randomUUID().toString());
			newUacStuff.setNmRemoved(new BigDecimal(0));
			newUacStuff.setDtCreate(new Timestamp(System.currentTimeMillis()));
			uacStuffDao.add(newUacStuff);
			return newUacStuff;
		}else {// update
			oldUacStuff = (UacStuff) t4r.toBean(oldUacStuff, UacStuff.class);
			oldUacStuff.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			uacStuffDao.update(oldUacStuff);
			return oldUacStuff;
		}
	}

	@Override
	public void logicDelete(String[] stStuffId) {
		if (stStuffId.length == 0)
			throw new NullPointerException("Parameter stStuffId cannot be null.");
		uacStuffDao.logicDelete(stStuffId);

	}

	@Autowired
	private UacStuffDao uacStuffDao;

}
