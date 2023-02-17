package com.wondersgroup.app.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;
import wfc.service.log.Log;


import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.bean.SelmItemType;
import com.wondersgroup.app.dao.SelmItemLinkDao;
import com.wondersgroup.app.dao.SelmItemTypeDao;
import com.wondersgroup.app.service.SelmItemTypeService;
import com.wondersgroup.sms.organ.bean.SmsOrgan;

/**
 * 事项类别业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmItemTypeServiceImpl implements SelmItemTypeService {

	/**
	 * 根据主键 {@link SelmItemType#ST_ITEM_TYPE_ID}获取事项类别
	 * 
	 * @param stItemTypeId
	 *            事项类别主键 {@link SelmItemType#ST_ITEM_TYPE_ID}
	 * @return 事项类别实例
	 */
	@Override
	public SelmItemType get(String stItemTypeId) {
		if (StringUtils.trimToEmpty(stItemTypeId).isEmpty())
			throw new NullPointerException("Parameter stItemTypeId cannot be null.");
		return selmItemTypeDao.get(stItemTypeId);
	}

	/**
	 * 查询事项类别列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项类别列表
	 */
	@Override
	public PaginationArrayList<SelmItemType> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmItemType.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmItemTypeDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmItemType#ST_ITEM_TYPE_ID}删除事项类别
	 * 
	 * @param stItemTypeId
	 *            事项类别主键 {@link SelmItemType#ST_ITEM_TYPE_ID}
	 */
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String[] stItemTypeIdList = httpReqRes.getRequest().getParameterValues(
				"stItemTypeId[]");
		if (stItemTypeIdList == null) {
			String stItemTypeId = httpReqRes.getRequest()
					.getParameter("stItemTypeId");
			if (stItemTypeId != null
					&& !StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
				selmItemTypeDao.delete(stItemTypeId);
				selmItemLinkDao.delete(stItemTypeId);
				return;
			} else {
				throw new NullPointerException("事项分组ID不能为空");
			}
		}
		for (String stItemTypeId : stItemTypeIdList) {
			selmItemTypeDao.delete(stItemTypeId);
			selmItemLinkDao.delete(stItemTypeId);
		}
	}

	/**
	 * 保存或更新事项类别
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项类别实例
	 */
	@Override
	public SelmItemType saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmItemType.ST_ITEM_TYPE_ID
		String stItemTypeId = wrapper.getParameter(SelmItemType.ST_ITEM_TYPE_ID);
		SelmItemType oldSelmItemType = null;
		if (!StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
			oldSelmItemType = selmItemTypeDao.get(stItemTypeId);
		}
		if (oldSelmItemType == null) {// new
			SelmItemType newSelmItemType = (SelmItemType) t4r.toBean(SelmItemType.class);
			newSelmItemType.setStItemTypeId(UUID.randomUUID().toString());
			newSelmItemType.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newSelmItemType.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			selmItemTypeDao.add(newSelmItemType);
			return newSelmItemType;
		}else {// update
			oldSelmItemType = (SelmItemType) t4r.toBean(oldSelmItemType, SelmItemType.class);
			oldSelmItemType.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			selmItemTypeDao.update(oldSelmItemType);
			return oldSelmItemType;
		}
	}
	
	
	/**
	 * 
	 */
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stItemTypeName = httpReqRes.getParameter("stItemTypeName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stItemTypeName != null && !StringUtils.trim(stItemTypeName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_TYPE_NAME", Condition.OT_LIKE,
					stItemTypeName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		/*String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NO " + orderType.toUpperCase();
			} else if ("stMainName".equals(orderName)) {
				suffix = "ORDER BY ST_MAIN_NAME " + orderType.toUpperCase();
			} else if ("nmSort".equals(orderName)) {
				suffix = "ORDER BY NM_SORT " + orderType.toUpperCase();
			}
		}*/
		String suffix = "ORDER BY DT_CREATE DESC";
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (httpReqRes != null) {
			String length = httpReqRes.getParameter("length");
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (httpReqRes.getParameter("start") != null) {
				int start = Integer.valueOf(httpReqRes.getParameter("start"));
				if (start != 0) {
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		
		PaginationArrayList<SelmItemType> list = selmItemTypeDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list,
							SelmItemType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", list.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", list);
		return returnObj;
	}

	@Autowired
	private SelmItemTypeDao selmItemTypeDao;
	
	@Autowired
	private SelmItemLinkDao selmItemLinkDao;

	

	
}
