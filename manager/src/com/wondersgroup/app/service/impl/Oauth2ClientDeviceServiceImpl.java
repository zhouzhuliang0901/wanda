package com.wondersgroup.app.service.impl;

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



import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.app.bean.Oauth2ClientDevice;
import com.wondersgroup.app.bean.Oauth2ClientItem;
import com.wondersgroup.app.dao.Oauth2ClientDeviceDao;
import com.wondersgroup.app.service.Oauth2ClientDeviceService;

/**
 * 客户端关联设备业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class Oauth2ClientDeviceServiceImpl implements Oauth2ClientDeviceService {

	/**
	 * 根据主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID} {@link Oauth2ClientDevice#ST_DEVICE_ID}获取客户端关联设备
	 * 
	 * @param stOauth2Id
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID}
	 * @param stDeviceId
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_DEVICE_ID}
	 * @return 客户端关联设备实例
	 */
	@Override
	public Oauth2ClientDevice get(String stOauth2Id, String stDeviceId) {
		if (StringUtils.trimToEmpty(stOauth2Id).isEmpty())
			throw new NullPointerException("Parameter stOauth2Id cannot be null.");
		if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
			throw new NullPointerException("Parameter stDeviceId cannot be null.");
		return oauth2ClientDeviceDao.get(stOauth2Id, stDeviceId);
	}

	/**
	 * 查询客户端关联设备列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 客户端关联设备列表
	 */
	@Override
	public PaginationArrayList<Oauth2ClientDevice> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(Oauth2ClientDevice.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return oauth2ClientDeviceDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID} {@link Oauth2ClientDevice#ST_DEVICE_ID}删除客户端关联设备
	 * 
	 * @param stOauth2Id
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_OAUTH2_ID}
	 * @param stDeviceId
	 *            客户端关联设备主键 {@link Oauth2ClientDevice#ST_DEVICE_ID}
	 */
	@Override
	public void remove(String stOauth2Id, String stDeviceId) {
		if (StringUtils.trimToEmpty(stOauth2Id).isEmpty())
			throw new NullPointerException("Parameter stOauth2Id cannot be null.");
		if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
			throw new NullPointerException("Parameter stDeviceId cannot be null.");
		oauth2ClientDeviceDao.delete(stOauth2Id, stDeviceId);
	}

	/**
	 * 保存或更新客户端关联设备
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 客户端关联设备实例
	 */
	@Override
	public Oauth2ClientDevice saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// Oauth2ClientItem.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2ClientDevice.ST_OAUTH2_ID);
		// Oauth2ClientItem.ST_ITEM_ID
		String stDeviceId = wrapper.getParameter(Oauth2ClientDevice.ST_DEVICE_ID);
		Oauth2ClientDevice oldOauth2ClientDevice = null;
		if (!StringUtils.trimToEmpty(stOauth2Id).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			oldOauth2ClientDevice = oauth2ClientDeviceDao.get(stOauth2Id, stDeviceId);
		}
		if (oldOauth2ClientDevice == null) {// new
			Oauth2ClientDevice newOauth2ClientDevice = (Oauth2ClientDevice) t4r.toBean(Oauth2ClientDevice.class);
			newOauth2ClientDevice.setStOauth2Id(stOauth2Id);
			newOauth2ClientDevice.setStDeviceId(stDeviceId);
			oauth2ClientDeviceDao.add(newOauth2ClientDevice);
			return newOauth2ClientDevice;
		}else {// update
			/*oldOauth2ClientItem = (Oauth2ClientItem) t4r.toBean(oldOauth2ClientItem, Oauth2ClientItem.class);
			oauth2ClientItemDao.update(oldOauth2ClientItem);*/
			return oldOauth2ClientDevice;
		}
	}

	@Autowired
	private Oauth2ClientDeviceDao oauth2ClientDeviceDao;

}
