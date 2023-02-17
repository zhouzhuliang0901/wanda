package com.wondersgroup.wdf.uacItemInfo.service.impl;


import com.wondersgroup.wdf.uacItemInfo.dao.UacItemInfo;
import com.wondersgroup.wdf.uacItemInfo.dao.UacItemInfoDao;
import com.wondersgroup.wdf.uacItemInfo.service.UacItemInfoService;
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
 * 事项信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemInfoServiceImpl implements UacItemInfoService {
	@Override
	public List<UacItemInfo> queryBig(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String st_item_name = wrapper.getParameter("ST_ITEM_NAME");
		String stUserId = wrapper.getParameter("stUserId");
		String suffix ="";
		String fieldName = "uii.*";
		if(!st_item_name.isEmpty()){
		conds.add(new Condition(UacItemInfo.ST_ITEM_NAME,Condition.OT_LIKE,st_item_name));
		suffix = "and uugl.ST_USER_ID = "+"'"+stUserId+"' order by NM_ORDER desc";
		}else {
			suffix = "where uugl.ST_USER_ID = "+"'"+stUserId+"' order by NM_ORDER desc";
//			fieldName = "uii.*";
		}
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemInfo.class);
//			Page page = EasyUIHelper.getPage(wrapper);
//			suffix = page.getSuffix();
		}
		return uacItemInfoDao.queryBig(conds, suffix,fieldName);
	}

	@Override
	public List<UacItemInfo> querySmall(String stItemName) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(UacItemInfo.ST_ITEM_NAME, Condition.OT_EQUAL, stItemName));
		return uacItemInfoDao.querySmall(conds, "");
	}
	/**
	 * 根据主键 {@link UacItemInfo#ST_ITEM_ID}获取事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfo#ST_ITEM_ID}
	 * @return 事项信息实例
	 */
	@Override
	public UacItemInfo get(String stItemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty()){
			throw new NullPointerException("Parameter stItemId cannot be null.");
		}
		return uacItemInfoDao.get(stItemId);
	}

	/**
	 * 查询事项信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项信息列表
	 */
	@Override
	public PaginationArrayList<UacItemInfo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemInfo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemInfoDao.query(conds, suffix, pageSize, currentPage);
	}


	/**
	 * 根据主键 {@link UacItemInfo#ST_ITEM_ID}删除事项信息
	 * 
	 * @param stItemId
	 *            事项信息主键 {@link UacItemInfo#ST_ITEM_ID}
	 */
	@Override
	public void remove(String stItemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		uacItemInfoDao.delete(stItemId);
	}

	/**
	 * 保存或更新事项信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项信息实例
	 */
	@Override
	public UacItemInfo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemInfo.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemInfo.ST_ITEM_ID);
		UacItemInfo oldUacItemInfo = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty()) {
			oldUacItemInfo = uacItemInfoDao.get(stItemId);
		}
		if (oldUacItemInfo == null) {// new
			UacItemInfo newUacItemInfo = (UacItemInfo) t4r.toBean(UacItemInfo.class);
			newUacItemInfo.setStItemId(UUID.randomUUID().toString());
			uacItemInfoDao.add(newUacItemInfo);
			return newUacItemInfo;
		}else {// update
			oldUacItemInfo = (UacItemInfo) t4r.toBean(oldUacItemInfo, UacItemInfo.class);
			uacItemInfoDao.update(oldUacItemInfo);
			return oldUacItemInfo;
		}
	}

	@Autowired
	private UacItemInfoDao uacItemInfoDao;

}
