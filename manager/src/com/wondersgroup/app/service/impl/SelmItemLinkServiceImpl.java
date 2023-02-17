package com.wondersgroup.app.service.impl;

import java.io.IOException;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.annotation.PostConstruct;

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
import com.wondersgroup.app.bean.SelmItemLink;
import com.wondersgroup.app.bean.SelmItemType;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.app.dao.SelmItemLinkDao;
import com.wondersgroup.app.service.SelmItemLinkService;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;

/**
 * 类别关联事项业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmItemLinkServiceImpl implements SelmItemLinkService {

	/**
	 * 根据主键 {@link SelmItemLink#ST_ITEM_ID} {@link SelmItemLink#ST_ITEM_TYPE_ID}获取类别关联事项
	 * 
	 * @param stItemId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_ID}
	 * @param stItemTypeId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_TYPE_ID}
	 * @return 类别关联事项实例
	 */
	@Override
	public SelmItemLink get(String stItemId, String stItemTypeId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stItemTypeId).isEmpty())
			throw new NullPointerException("Parameter stItemTypeId cannot be null.");
		return selmItemLinkDao.get(stItemId, stItemTypeId);
	}

	/**
	 * 查询类别关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 类别关联事项列表
	 */
	@Override
	public PaginationArrayList<SelmItemLink> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmItemLink.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmItemLinkDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmItemLink#ST_ITEM_ID} {@link SelmItemLink#ST_ITEM_TYPE_ID}删除类别关联事项
	 * 
	 * @param stItemId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_ID}
	 * @param stItemTypeId
	 *            类别关联事项主键 {@link SelmItemLink#ST_ITEM_TYPE_ID}
	 */
	@Override
	public void remove(String stItemId, String stItemTypeId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stItemTypeId).isEmpty())
			throw new NullPointerException("Parameter stItemTypeId cannot be null.");
		selmItemLinkDao.delete(stItemId, stItemTypeId);
	}

	/**
	 * 保存或更新类别关联事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 类别关联事项实例
	 */
	@Override
	public SelmItemLink saveOrUpdate(HttpReqRes httpReqRes) {
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		SelmItemLink newSelmItemLink = new SelmItemLink();
		if(stItemIdList==null){
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stItemTypeId = httpReqRes.getRequest().getParameter(
						"stItemTypeId");
				SelmItemLink oldSelmItemLink = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
					oldSelmItemLink = selmItemLinkDao.get(stItemId, stItemTypeId);
				}
				if (oldSelmItemLink == null) {// new
					//SelmServerItem newSelmServerItem = new SelmServerItem();
					httpReqRes.toBean(newSelmItemLink);
					newSelmItemLink.setStItemId(stItemId);
					newSelmItemLink.setStItemTypeId(stItemTypeId);
					selmItemLinkDao.add(newSelmItemLink);
					return newSelmItemLink;
				} 
		}else{
			for (String stItemId : stItemIdList) {
				//System.out.println(stItemId);
				String stItemTypeId = httpReqRes.getRequest().getParameter(
						"stItemTypeId");
				SelmItemLink oldSelmItemLink = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
					oldSelmItemLink = selmItemLinkDao.get(stItemId, stItemTypeId);
				}
				if (oldSelmItemLink == null) {// new
					httpReqRes.toBean(newSelmItemLink);
					newSelmItemLink.setStItemId(stItemId);
					newSelmItemLink.setStItemTypeId(stItemTypeId);
					selmItemLinkDao.add(newSelmItemLink);
				}
			}
		}
		return newSelmItemLink;
	}
	
	/**
	 * 查询类别关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 类别关联事项列表
	 */
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stMainName = httpReqRes.getParameter("stMainName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_TYPE_NAME", Condition.OT_LIKE,
					stMainName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NO " + orderType.toUpperCase();
			} else if ("stMainName".equals(orderName)) {
				suffix = "ORDER BY ST_MAIN_NAME " + orderType.toUpperCase();
			} else if ("nmSort".equals(orderName)) {
				suffix = "ORDER BY NM_SORT " + orderType.toUpperCase();
			}
		}
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
		
		PaginationArrayList<SelmItemLink> list = selmItemLinkDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list,
							SelmItemLink.class)).getString("total");
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
	
	
	/**
	 * 查询组别没有绑定的事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@Override
	public JSONObject itemNoLinkList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stItemTypeId = httpReqRes.getParameter("stItemTypeId");
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		String stOrganName = httpReqRes.getParameter("stOrganId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stItemTypeId != null && !StringUtils.trim(stItemTypeId).isEmpty()) {
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_ITEM_TYPE_ID", Condition.OT_EQUAL,
					stItemTypeId));
			List<SelmItemLink> query = selmItemLinkDao.query(cond, null);
			for (SelmItemLink selmItemLink : query) {
				String stItemId = selmItemLink.getStItemId();
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_UNEQUAL,
						stItemId));
			}
			
		}
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (stOrganName != null && !StringUtils.trim(stOrganName).isEmpty()) {
			Conditions condOrgan = Conditions.newOrConditions();
			condOrgan.add(new Condition("ST_ORGAN_NAME", Condition.OT_LIKE,
					stOrganName));
			List<SmsOrgan> smsOrganList = smsOrganDao.query(condOrgan, null);
			for (SmsOrgan smsOrgan : smsOrganList) {
				condOrgan = Conditions.newAndConditions();
				condOrgan.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL,
						smsOrgan.getStOrganId()));
			}
			conds.add(condOrgan);
		}
		conds.add(new Condition("ST_EXT1", Condition.OT_UNEQUAL,
				"江苏"));
		conds.add(new Condition("ST_EXT1", Condition.OT_UNEQUAL,
				"安徽"));
		conds.add(new Condition("ST_EXT1", Condition.OT_UNEQUAL,
				"浙江"));
		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NO " + orderType.toUpperCase();
			} else if ("stMainName".equals(orderName)) {
				suffix = "ORDER BY ST_MAIN_NAME " + orderType.toUpperCase();
			} else if ("stOrganId".equals(orderName)) {
				suffix = "ORDER BY ST_ORGAN_ID " + orderType.toUpperCase();
			}
		}
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
		
		PaginationArrayList<SelmItem> list = selmItemDao.query(conds, suffix, pageSize, currentPage);
		for (SelmItem selmItem : list) {
			String stOrgan = selmItem.getStOrganId();
			if(stOrgan != null){
				SmsOrgan smsOrgan = smsOrganDao.get(stOrgan);
				selmItem.setStOrganId(smsOrgan.getStOrganName());
			}
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list,
							SelmItem.class)).getString("total");
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
	
	/**
	 * 查询组别含有的事项
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@Override
	public JSONObject itemLinkList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stItemTypeId = httpReqRes.getParameter("stItemTypeId");
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		String stOrganName = httpReqRes.getParameter("stOrganId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stItemTypeId != null && !StringUtils.trim(stItemTypeId).isEmpty()) {
			conds.add(new Condition("SIL.ST_ITEM_TYPE_ID", Condition.OT_EQUAL, stItemTypeId));
		}
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			conds.add(new Condition("SI.ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("SI.ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("SI.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("SI.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (stOrganName != null && !StringUtils.trim(stOrganName).isEmpty()) {
			Conditions condOrgan = Conditions.newOrConditions();
			condOrgan.add(new Condition("ST_ORGAN_NAME", Condition.OT_LIKE,
					stOrganName));
			List<SmsOrgan> smsOrganList = smsOrganDao.query(condOrgan, null);
			for (SmsOrgan smsOrgan : smsOrganList) {
				condOrgan = Conditions.newAndConditions();
				condOrgan.add(new Condition("SI.ST_ORGAN_ID", Condition.OT_EQUAL,
						smsOrgan.getStOrganId()));
			}
			conds.add(condOrgan);
		}
		String suffix = "ORDER BY SI.NM_SORT";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY SI.ST_ITEM_NO " + orderType.toUpperCase();
			} else if ("stMainName".equals(orderName)) {
				suffix = "ORDER BY SI.ST_MAIN_NAME " + orderType.toUpperCase();
			} else if ("stOrganId".equals(orderName)) {
				suffix = "ORDER BY SI.ST_ORGAN_ID " + orderType.toUpperCase();
			}
		}
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
		
		PaginationArrayList<SelmItem> list = selmItemDao.selmTypeLinkList(conds, suffix, pageSize, currentPage);
		for (SelmItem selmItem : list) {
			String stOrgan = selmItem.getStOrganId();
			SmsOrgan smsOrgan = smsOrganDao.get(stOrgan);
			selmItem.setStOrganId(smsOrgan.getStOrganName());
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list,
							SelmItem.class)).getString("total");
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
	
	
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		String stItemTypeId = httpReqRes.getRequest()
				.getParameter("stItemTypeId");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		if (stItemIdList == null) {
			String stItemId = httpReqRes.getRequest()
					.getParameter("stItemId");
			if (stItemTypeId != null
					&& !StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
				conds.add(new Condition("ST_ITEM_TYPE_ID", Condition.OT_EQUAL, stItemTypeId));
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, stItemId));
				selmItemLinkDao.delete(conds);
				return;
			} else {
				throw new NullPointerException("分组关联ID不能为空");
			}
		}
		for (String itemId : stItemIdList) {
			conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_ITEM_TYPE_ID", Condition.OT_EQUAL, stItemTypeId));
			conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, itemId));
			selmItemLinkDao.delete(conds);
		}
		
	}

	@Autowired
	private SelmItemLinkDao selmItemLinkDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private SmsOrganDao smsOrganDao;

}
