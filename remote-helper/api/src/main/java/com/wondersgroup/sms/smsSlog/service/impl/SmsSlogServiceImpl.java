package com.wondersgroup.sms.smsSlog.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;

import com.wondersgroup.sms.smsSlog.dao.SmsSlog;
import com.wondersgroup.sms.smsSlog.dao.SmsSlogDao;
import com.wondersgroup.sms.smsSlog.service.SmsSlogService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 日志表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SmsSlogServiceImpl implements SmsSlogService {

	/**
	 * 根据主键 {@link SmsSlog#ST_LOG_ID}获取日志表
	 * 
	 * @param stLogId
	 *            日志表主键 {@link SmsSlog#ST_LOG_ID}
	 * @return 日志表实例
	 */
	@Override
	public SmsSlog get(String stLogId) {
		if (StringUtils.trimToEmpty(stLogId).isEmpty())
			throw new NullPointerException("Parameter stLogId cannot be null.");
		return smsSlogDao.get(stLogId);
	}

	/**
	 * 查询日志表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 日志表列表
	 */
	@Override
	public PaginationArrayList<SmsSlog> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SmsSlog.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return smsSlogDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SmsSlog#ST_LOG_ID}删除日志表
	 * 
	 * @param stLogId
	 *            日志表主键 {@link SmsSlog#ST_LOG_ID}
	 */
	@Override
	public void remove(String stLogId) {
		if (StringUtils.trimToEmpty(stLogId).isEmpty())
			throw new NullPointerException("Parameter stLogId cannot be null.");
		smsSlogDao.delete(stLogId);
	}

	/**
	 * 保存或更新日志表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 日志表实例
	 */
	@Override
	public SmsSlog saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsSlog.ST_LOG_ID
		String stLogId = wrapper.getParameter(SmsSlog.ST_LOG_ID);
		SmsSlog oldSmsSlog = null;
		if (!StringUtils.trimToEmpty(stLogId).isEmpty()) {
			oldSmsSlog = smsSlogDao.get(stLogId);
		}
		if (oldSmsSlog == null) {// new
			SmsSlog newSmsSlog = (SmsSlog) t4r.toBean(SmsSlog.class);
			newSmsSlog.setStLogId(UUID.randomUUID().toString());
			smsSlogDao.add(newSmsSlog);
			return newSmsSlog;
		}else {// update
			oldSmsSlog = (SmsSlog) t4r.toBean(oldSmsSlog, SmsSlog.class);
			smsSlogDao.update(oldSmsSlog);
			return oldSmsSlog;
		}
	}

	@Autowired
	private SmsSlogDao smsSlogDao;

}
