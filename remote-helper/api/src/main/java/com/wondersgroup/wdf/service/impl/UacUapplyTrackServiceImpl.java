package com.wondersgroup.wdf.service.impl;


import com.wondersgroup.wdf.dao.UacUapplyTrack;
import com.wondersgroup.wdf.dao.UacUapplyTrackDao;
import com.wondersgroup.wdf.service.UacUapplyTrackService;
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
 * 综合办件跟踪信息业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyTrackServiceImpl implements UacUapplyTrackService {

	/**
	 * 根据主键 {@link UacUapplyTrack#ST_TRACK_ID}获取综合办件跟踪信息
	 * 
	 * @param stTrackId
	 *            综合办件跟踪信息主键 {@link UacUapplyTrack#ST_TRACK_ID}
	 * @return 综合办件跟踪信息实例
	 */
	@Override
	public UacUapplyTrack get(String stTrackId) {
		if (StringUtils.trimToEmpty(stTrackId).isEmpty())
			throw new NullPointerException("Parameter stTrackId cannot be null.");
		return uacUapplyTrackDao.get(stTrackId);
	}

	/**
	 * 查询综合办件跟踪信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合办件跟踪信息列表
	 */
	@Override
	public PaginationArrayList<UacUapplyTrack> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyTrack.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyTrackDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUapplyTrack#ST_TRACK_ID}删除综合办件跟踪信息
	 * 
	 * @param stTrackId
	 *            综合办件跟踪信息主键 {@link UacUapplyTrack#ST_TRACK_ID}
	 */
	@Override
	public void remove(String stTrackId) {
		if (StringUtils.trimToEmpty(stTrackId).isEmpty())
			throw new NullPointerException("Parameter stTrackId cannot be null.");
		uacUapplyTrackDao.delete(stTrackId);
	}

	/**
	 * 保存或更新综合办件跟踪信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合办件跟踪信息实例
	 */
	@Override
	public UacUapplyTrack saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("Parameter wrapper cannot be null.");
		}
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyTrack.ST_TRACK_ID
		String stTrackId = wrapper.getParameter(UacUapplyTrack.ST_TRACK_ID);
		UacUapplyTrack oldUacUapplyTrack = null;
		if (!StringUtils.trimToEmpty(stTrackId).isEmpty()) {
			oldUacUapplyTrack = uacUapplyTrackDao.get(stTrackId);
		}
		if (oldUacUapplyTrack == null) {// new
			UacUapplyTrack newUacUapplyTrack = (UacUapplyTrack) t4r.toBean(UacUapplyTrack.class);
			newUacUapplyTrack.setStTrackId(UUID.randomUUID().toString());
			uacUapplyTrackDao.add(newUacUapplyTrack);
			return newUacUapplyTrack;
		}else {// update
			oldUacUapplyTrack = (UacUapplyTrack) t4r.toBean(oldUacUapplyTrack, UacUapplyTrack.class);
			uacUapplyTrackDao.update(oldUacUapplyTrack);
			return oldUacUapplyTrack;
		}
	}

	@Autowired
	private UacUapplyTrackDao uacUapplyTrackDao;

}
