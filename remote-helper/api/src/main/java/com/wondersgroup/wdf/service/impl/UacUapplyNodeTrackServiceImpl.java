package com.wondersgroup.wdf.service.impl;


import com.wondersgroup.wdf.dao.UacUapplyNodeTrack;
import com.wondersgroup.wdf.dao.UacUapplyNodeTrackDao;
import com.wondersgroup.wdf.service.UacUapplyNodeTrackService;
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
 * 综合办件环节跟踪信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyNodeTrackServiceImpl implements UacUapplyNodeTrackService {

	/**
	 * 根据主键 {@link UacUapplyNodeTrack#ST_NODE_TRACK_ID}获取综合办件环节跟踪信息
	 * 
	 * @param stNodeTrackId
	 *            综合办件环节跟踪信息主键 {@link UacUapplyNodeTrack#ST_NODE_TRACK_ID}
	 * @return 综合办件环节跟踪信息实例
	 */
	@Override
	public UacUapplyNodeTrack get(String stNodeTrackId) {
		if (StringUtils.trimToEmpty(stNodeTrackId).isEmpty()){
			throw new NullPointerException("Parameter stNodeTrackId cannot be null.");
		}
		return uacUapplyNodeTrackDao.get(stNodeTrackId);
	}

	/**
	 * 查询综合办件环节跟踪信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合办件环节跟踪信息列表
	 */
	@Override
	public PaginationArrayList<UacUapplyNodeTrack> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyNodeTrack.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyNodeTrackDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUapplyNodeTrack#ST_NODE_TRACK_ID}删除综合办件环节跟踪信息
	 * 
	 * @param stNodeTrackId
	 *            综合办件环节跟踪信息主键 {@link UacUapplyNodeTrack#ST_NODE_TRACK_ID}
	 */
	@Override
	public void remove(String stNodeTrackId) {
		if (StringUtils.trimToEmpty(stNodeTrackId).isEmpty()) {
			throw new NullPointerException("Parameter stNodeTrackId cannot be null.");
		}
		uacUapplyNodeTrackDao.delete(stNodeTrackId);
	}

	/**
	 * 保存或更新综合办件环节跟踪信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合办件环节跟踪信息实例
	 */
	@Override
	public UacUapplyNodeTrack saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("Parameter wrapper cannot be null.");
		}
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyNodeTrack.ST_NODE_TRACK_ID
		String stNodeTrackId = wrapper.getParameter(UacUapplyNodeTrack.ST_NODE_TRACK_ID);
		UacUapplyNodeTrack oldUacUapplyNodeTrack = null;
		if (!StringUtils.trimToEmpty(stNodeTrackId).isEmpty()) {
			oldUacUapplyNodeTrack = uacUapplyNodeTrackDao.get(stNodeTrackId);
		}
		if (oldUacUapplyNodeTrack == null) {// new
			UacUapplyNodeTrack newUacUapplyNodeTrack = (UacUapplyNodeTrack) t4r.toBean(UacUapplyNodeTrack.class);
			newUacUapplyNodeTrack.setStNodeTrackId(UUID.randomUUID().toString());
			uacUapplyNodeTrackDao.add(newUacUapplyNodeTrack);
			return newUacUapplyNodeTrack;
		}else {// update
			oldUacUapplyNodeTrack = (UacUapplyNodeTrack) t4r.toBean(oldUacUapplyNodeTrack, UacUapplyNodeTrack.class);
			uacUapplyNodeTrackDao.update(oldUacUapplyNodeTrack);
			return oldUacUapplyNodeTrack;
		}
	}

	@Autowired
	private UacUapplyNodeTrackDao uacUapplyNodeTrackDao;

}
