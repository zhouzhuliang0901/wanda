package com.wondersgroup.app.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;



import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.service.SelmDeviceItemService;
import com.wondersgroup.infopub.bean.InfopubOnoff;

/**
 * 设备关联事项业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmDeviceItemServiceImpl implements SelmDeviceItemService {

	/**
	 * 根据主键 {@link SelmDeviceItem#ST_ITEM_ID} {@link SelmDeviceItem#ST_DEVICE_ID}获取设备关联事项
	 * 
	 * @param stItemId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_ITEM_ID}
	 * @param stDeviceId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_DEVICE_ID}
	 * @return 设备关联事项实例
	 */
	@Override
	public SelmDeviceItem get(String stItemId, String stDeviceId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
			throw new NullPointerException("Parameter stDeviceId cannot be null.");
		return selmDeviceItemDao.get(stItemId, stDeviceId);
	}

	/**
	 * 查询设备关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备关联事项列表
	 */
	@Override
	public PaginationArrayList<SelmDeviceItem> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmDeviceItem.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmDeviceItemDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmDeviceItem#ST_ITEM_ID} {@link SelmDeviceItem#ST_DEVICE_ID}删除设备关联事项
	 * 
	 * @param stItemId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_ITEM_ID}
	 * @param stDeviceId
	 *            设备关联事项主键 {@link SelmDeviceItem#ST_DEVICE_ID}
	 */
	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stDeviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceId[]");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		if(stDeviceIdList==null){
		if (stItemIdList == null) {
			String stItemId = httpReqRes.getRequest().getParameter("stItemId");
			String stDeviceId = httpReqRes.getRequest().getParameter(
					"stDeviceId");
			if (StringUtils.trimToEmpty(stItemId).isEmpty())
				throw new NullPointerException("Parameter stItemId cannot be null.");
			if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
				throw new NullPointerException("Parameter stDeviceId cannot be null.");
				selmDeviceItemDao.delete(stItemId, stDeviceId);
				return;
			}else{
				for (String stItemId : stItemIdList) {
					String stDeviceId = httpReqRes.getRequest().getParameter(
							"stDeviceId");
					selmDeviceItemDao.delete(stItemId, stDeviceId);
				}
			}
		}else{
			for (String stDeviceId : stDeviceIdList) {
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				selmDeviceItemDao.delete(stItemId, stDeviceId);
			}
		}
	}

	
	/*@Override
	public void remove(String stItemId, String stDeviceId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
			throw new NullPointerException("Parameter stDeviceId cannot be null.");
		selmDeviceItemDao.delete(stItemId, stDeviceId);
	}*/

	/**
	 * 保存或更新设备关联事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备关联事项实例
	 */
	/*@Override
	public SelmDeviceItem saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		String stItemId = wrapper.getParameter(SelmDeviceItem.ST_ITEM_ID);
		String[] stDeviceIdList = wrapper.getParameterValues("stDeviceId[]");
		System.out.println(stDeviceIdList+"'''''''''''''''''''''");
		if (stDeviceIdList == null) {
			String stDeviceId = wrapper.getParameter("stDeviceId");
			System.out.println(stDeviceId+"0-----");
			SelmDeviceItem oldSelmDeviceItem = null;
			if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				oldSelmDeviceItem = selmDeviceItemDao.get(stItemId, stDeviceId);
			}
			if (oldSelmDeviceItem == null) {// new
				SelmDeviceItem newSelmDeviceItem = (SelmDeviceItem) t4r.toBean(SelmDeviceItem.class);
				newSelmDeviceItem.setStItemId(stItemId);
				newSelmDeviceItem.setStDeviceId(stDeviceId);
				selmDeviceItemDao.add(newSelmDeviceItem);
				return newSelmDeviceItem;
			}else {// update
				oldSelmDeviceItem = (SelmDeviceItem) t4r.toBean(oldSelmDeviceItem, SelmDeviceItem.class);
				selmDeviceItemDao.update(oldSelmDeviceItem);
				return oldSelmDeviceItem;
			}
		}else{
			for (String stDeviceId : stDeviceIdList) {
				System.out.println(stDeviceId+"----------");
				SelmDeviceItem oldSelmDeviceItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					oldSelmDeviceItem = selmDeviceItemDao.get(stItemId, stDeviceId);
				}
				if (oldSelmDeviceItem == null) {// new
					SelmDeviceItem newSelmDeviceItem = (SelmDeviceItem) t4r.toBean(SelmDeviceItem.class);
					newSelmDeviceItem.setStItemId(stItemId);
					newSelmDeviceItem.setStDeviceId(stDeviceId);
					selmDeviceItemDao.add(newSelmDeviceItem);
					return newSelmDeviceItem;
				}else {// update
					oldSelmDeviceItem = (SelmDeviceItem) t4r.toBean(oldSelmDeviceItem, SelmDeviceItem.class);
					selmDeviceItemDao.update(oldSelmDeviceItem);
					return oldSelmDeviceItem;
				}
			}
		}
		return null;
		
	}*/

	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;

	@Override
	public SelmDeviceItem saveOrUpdate(HttpReqRes httpReqRes) {
		String[] stDeviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceId[]");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		
		if(stItemIdList==null){
		if (stDeviceIdList == null) {
			String stItemId = httpReqRes.getRequest().getParameter("stItemId");
			String stDeviceId = httpReqRes.getRequest().getParameter(
					"stDeviceId");
			SelmDeviceItem oldSelmDeviceItem = null;
			if (!StringUtils.trimToEmpty(stItemId).isEmpty()
					&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				oldSelmDeviceItem = selmDeviceItemDao.get(stItemId, stDeviceId);
			}
			if (oldSelmDeviceItem == null) {// new
				SelmDeviceItem newSelmDeviceItem = new SelmDeviceItem();
				httpReqRes.toBean(newSelmDeviceItem);
				newSelmDeviceItem.setStItemId(stItemId);
				newSelmDeviceItem.setStDeviceId(stDeviceId);
				selmDeviceItemDao.add(newSelmDeviceItem);
				return newSelmDeviceItem;
			} else {// update
				oldSelmDeviceItem = new SelmDeviceItem();
				selmDeviceItemDao.update(oldSelmDeviceItem);
				return oldSelmDeviceItem;
			}
		}else{
			for (String stDeviceId : stDeviceIdList) {
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
			SelmDeviceItem oldSelmDeviceItem = null;
			if (!StringUtils.trimToEmpty(stItemId).isEmpty()
					&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				oldSelmDeviceItem = selmDeviceItemDao.get(stItemId, stDeviceId);
			}
			if (oldSelmDeviceItem == null) {// new
				SelmDeviceItem newSelmDeviceItem = new SelmDeviceItem();
				httpReqRes.toBean(newSelmDeviceItem);
				newSelmDeviceItem.setStItemId(stItemId);
				newSelmDeviceItem.setStDeviceId(stDeviceId);
				selmDeviceItemDao.add(newSelmDeviceItem);
			} else {// update
				oldSelmDeviceItem = new SelmDeviceItem();
				selmDeviceItemDao.update(oldSelmDeviceItem);
				}
			}
		}
		}else{
			for (String stItemId : stItemIdList) {
				String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
				SelmDeviceItem oldSelmDeviceItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
					oldSelmDeviceItem = selmDeviceItemDao.get(stItemId, stDeviceId);
				}
				if (oldSelmDeviceItem == null) {// new
					SelmDeviceItem newSelmDeviceItem = new SelmDeviceItem();
					httpReqRes.toBean(newSelmDeviceItem);
					newSelmDeviceItem.setStItemId(stItemId);
					newSelmDeviceItem.setStDeviceId(stDeviceId);
					selmDeviceItemDao.add(newSelmDeviceItem);
				} else {// update
					oldSelmDeviceItem = new SelmDeviceItem();
					selmDeviceItemDao.update(oldSelmDeviceItem);
					}
			}
		}
		return null;

	}

}
