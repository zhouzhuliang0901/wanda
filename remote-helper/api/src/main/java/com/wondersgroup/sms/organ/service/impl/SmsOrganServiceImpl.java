package com.wondersgroup.sms.organ.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import coral.widget.utils.EasyUIHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;
import com.wondersgroup.sms.organ.service.SmsOrganService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

/**
 * 组织机构表业务实现
 * 
 */
@Service
@Transactional
public class SmsOrganServiceImpl implements SmsOrganService {

	/**
	 * 根据主键 {@link SmsOrgan#ST_ORGAN_ID}获取组织机构表
	 * 
	 * @param stOrganId
	 *            组织机构表主键 {@link SmsOrgan#ST_ORGAN_ID}
	 * @return 组织机构表实例
	 */
	@Override
	public SmsOrgan get(String stOrganId) {
		if (StringUtils.trimToEmpty(stOrganId).isEmpty())
			throw new NullPointerException("Parameter stOrganId cannot be null.");
		return smsOrganDao.get(stOrganId);
	}

	/**
	 * 查询组织机构表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 组织机构表列表
	 */
	@Override
	public PaginationArrayList<SmsOrgan> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// 名称
		String organName = wrapper.getParameter("organName");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		
		// 排列内容
		String orderName = wrapper.getParameter("sort");
		// 排序内容
		String orderType = wrapper.getParameter("order");
		// 排序
		String suffix = "ORDER BY NM_ORDER";
		// 父ID
		String stParentId = wrapper.getParameter("ST_PARENT_ID");
		// 存在的场合
		if (orderName != null) {
			// 机构代码
			if ("stOrganCode".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_CODE "+orderType.toUpperCase();
			// 机构名称
			} else if ("stOrganName".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_NAME "+orderType.toUpperCase();
			// 组织描述
			} else if ("stDesc".equals(orderName)){
				suffix = "ORDER BY ST_URL "+orderType.toUpperCase();
			// 创建时间
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE "+orderType.toUpperCase();
			} 
			Log.info("orderName:"+orderName+"*****orderType:"+orderType);
		}
		// 获取数据
		conds = Conditions.newAndConditions();
		// 机构名称存在的场合
		if (organName != null) {
			if (!StringUtils.trim(organName).isEmpty()) {
				// 机构名称
				conds.add(new Condition("ST_ORGAN_NAME", Condition.OT_LIKE,
						organName));
			}
		}

		// 父ID存在的场合
		if (stParentId != null) {
			if (!StringUtils.trim(stParentId).isEmpty()) {
				conds.add(new Condition("ST_PARENT_ID", Condition.OT_EQUAL,
						stParentId));
			}
		}

		// 开始时间存在的场合
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
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			EasyUIHelper.Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
		}
		/*int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			// 每页长度
			String length = wrapper.getParameter("length");
			// 页面长度
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// 开始页
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// 第一页的场合
				if ( start != 0) {
					// 当前页
					currentPage = Integer.valueOf(start)/pageSize+1;	
				}
			}
		}*/
		return smsOrganDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SmsOrgan#ST_ORGAN_ID}删除组织机构表
	 * 
	 * @param stOrganId
	 *            组织机构表主键 {@link SmsOrgan#ST_ORGAN_ID}
	 */
	@Override
	public void remove(String[] stOrganId) {
		if (stOrganId.length == 0)
			throw new NullPointerException("Parameter stOrganId cannot be null.");
		smsOrganDao.delete(stOrganId);
	}

	/**
	 * 保存或更新组织机构表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 组织机构表实例
	 */
	@Override
	public SmsOrgan saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsOrgan.ST_ORGAN_ID
		String stOrganId = wrapper.getParameter(SmsOrgan.ST_ORGAN_ID);
		SmsOrgan oldSmsOrgan = null;
		if (!StringUtils.trimToEmpty(stOrganId).isEmpty()) {
			oldSmsOrgan = smsOrganDao.get(stOrganId);
		}
		if (oldSmsOrgan == null) {// new
			SmsOrgan newSmsOrgan = (SmsOrgan) t4r.toBean(SmsOrgan.class);
			newSmsOrgan.setStOrganId(UUID.randomUUID().toString());
			// 插入时间
			newSmsOrgan.setDtCreate(new Timestamp(System.currentTimeMillis()));
			smsOrganDao.add(newSmsOrgan);
			return newSmsOrgan;
		}else {// update
			oldSmsOrgan = (SmsOrgan) t4r.toBean(oldSmsOrgan, SmsOrgan.class);
			// 更新时间
			oldSmsOrgan.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			smsOrganDao.update(oldSmsOrgan);
			return oldSmsOrgan;
		}
	}

	@Autowired
	private SmsOrganDao smsOrganDao;

}
