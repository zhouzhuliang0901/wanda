package com.wondersgroup.wdf.service.impl;

import java.util.*;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.wdf.dao.UacItemGroupLinkTwo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.wondersgroup.wdf.dao.UacUserGroupLinkTwo;
import com.wondersgroup.wdf.dao.UacUserGroupLinkTwoDao;
import com.wondersgroup.wdf.service.UacUserGroupLinkTwoService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组关联人员业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUserGroupLinkTwoServiceImpl implements UacUserGroupLinkTwoService {

	/**
	 * 根据主键 {@link UacUserGroupLinkTwo#ST_USER_ID} {@link UacUserGroupLinkTwo#ST_GROUP_ID}获取组关联人员
	 * 
	 * @param stUserId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_USER_ID}
	 * @param stGroupId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_GROUP_ID}
	 * @return 组关联人员实例
	 */
	@Override
	public UacUserGroupLinkTwo get(String stUserId, String stGroupId) {
		if (StringUtils.trimToEmpty(stUserId).isEmpty())
			throw new NullPointerException("Parameter stUserId cannot be null.");
		if (StringUtils.trimToEmpty(stGroupId).isEmpty())
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		return uacUserGroupLinkTwoDao.get(stUserId, stGroupId);
	}

	/**
	 * 查询组关联人员列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 组关联人员列表
	 */
	@Override
	public PaginationArrayList<UacUserGroupLinkTwo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUserGroupLinkTwo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUserGroupLinkTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUserGroupLinkTwo#ST_USER_ID} {@link UacUserGroupLinkTwo#ST_GROUP_ID}删除组关联人员
	 * 
	 * @param stUserId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_USER_ID}
	 * @param stGroupId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_GROUP_ID}
	 */
	@Override
	public void remove(String[] stUserId, String[] stGroupId) {
		if (stUserId.length == 0)
			throw new NullPointerException("Parameter stUserId cannot be null.");
		if (stGroupId.length == 0)
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		uacUserGroupLinkTwoDao.delete(stUserId, stGroupId);
	}

	/**
	 * 保存或更新组关联人员
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 组关联人员实例
	 */
	@Override
	public UacUserGroupLinkTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUserGroupLinkTTwo.ST_USER_ID
		String stUserId = wrapper.getParameter(UacUserGroupLinkTwo.ST_USER_ID);
		// UacUserGroupLinkTTwo.ST_GROUP_ID
		String stGroupId = wrapper.getParameter(UacUserGroupLinkTwo.ST_GROUP_ID);
		UacUserGroupLinkTwo oldUacUserGroupLinkTwo = null;
		if (!StringUtils.trimToEmpty(stUserId).isEmpty() && !StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			oldUacUserGroupLinkTwo = uacUserGroupLinkTwoDao.get(stUserId, stGroupId);
		}
		if (oldUacUserGroupLinkTwo == null) {// new
			UacUserGroupLinkTwo newUacUserGroupLinkTwo = (UacUserGroupLinkTwo) t4r.toBean(UacUserGroupLinkTwo.class);
			newUacUserGroupLinkTwo.setStUserId(UUID.randomUUID().toString());
			newUacUserGroupLinkTwo.setStGroupId(UUID.randomUUID().toString());
			uacUserGroupLinkTwoDao.add(newUacUserGroupLinkTwo);
			return newUacUserGroupLinkTwo;
		}else {// update
			oldUacUserGroupLinkTwo = (UacUserGroupLinkTwo) t4r.toBean(oldUacUserGroupLinkTwo, UacUserGroupLinkTwo.class);
			uacUserGroupLinkTwoDao.update(oldUacUserGroupLinkTwo);
			return oldUacUserGroupLinkTwo;
		}
	}

	/**
	 * 根据一个事项组id保存多个组关联人员
	 */
	@Override
	public UacUserGroupLinkTwo saveitem(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		//多个人员id
		String[] stUserId = wrapper.getParameterValues("ST_USER_ID[]");
		//序号
		//String nmOrder = wrapper.getParameter(UacUserGroupLinkTwo.NM_ORDER);
		//事项组id
		String stGroupId = wrapper.getParameter("ST_GROUP_ID");
		String stAreaId = wrapper.getParameter("ST_AREA_ID");
		UacUserGroupLinkTwo uacUserGroupLinkTwo = new UacUserGroupLinkTwo();
		if(stUserId.length != 0){
			for (String s : stUserId) {
				SmsUser smsUser = smsUserDao.get(s);
				smsUser.setStAreaId(stAreaId);
				uacUserGroupLinkTwo.setStUserId(s);
				/*uacItemsLinkTwo.setNmOrder(new BigDecimal(nmOrder));
				uacItemsLinkTwo.setNmMust(new BigDecimal(nmMust));*/
				uacUserGroupLinkTwo.setStGroupId(stGroupId);
				smsUserDao.update(smsUser);
				uacUserGroupLinkTwoDao.add(uacUserGroupLinkTwo);
			}
		}
		return null;
	}

	@Autowired
	private UacUserGroupLinkTwoDao uacUserGroupLinkTwoDao;
	@Autowired
	private SmsUserDao smsUserDao;

}
