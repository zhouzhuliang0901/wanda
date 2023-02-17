package com.wondersgroup.delivery.server.impl;

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

import com.wondersgroup.delivery.bean.SelmDelivery;
import com.wondersgroup.delivery.bean.SelmDeliveryHistory;
import com.wondersgroup.delivery.dao.SelmDeliveryHistoryDao;
import com.wondersgroup.delivery.server.SelmDeliveryHistoryService;

/**
 * 快递柜历史业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmDeliveryHistoryServiceImpl implements SelmDeliveryHistoryService {

	/**
	 * 根据主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}获取快递柜历史
	 * 
	 * @param stDeliveryId
	 *            快递柜历史主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}
	 * @return 快递柜历史实例
	 */
	@Override
	public SelmDeliveryHistory get(String stDeliveryId) {
		if (StringUtils.trimToEmpty(stDeliveryId).isEmpty())
			throw new NullPointerException("Parameter stDeliveryId cannot be null.");
		return selmDeliveryHistoryDao.get(stDeliveryId);
	}

	/**
	 * 查询快递柜历史列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 快递柜历史列表
	 */
	@Override
	public PaginationArrayList<SelmDeliveryHistory> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmDeliveryHistory.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmDeliveryHistoryDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}删除快递柜历史
	 * 
	 * @param stDeliveryId
	 *            快递柜历史主键 {@link SelmDeliveryHistory#ST_DELIVERY_ID}
	 */
	@Override
	public void remove(String stDeliveryId) {
		if (StringUtils.trimToEmpty(stDeliveryId).isEmpty())
			throw new NullPointerException("Parameter stDeliveryId cannot be null.");
		selmDeliveryHistoryDao.delete(stDeliveryId);
	}

	/**
	 * 保存或更新快递柜历史
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 快递柜历史实例
	 */
	@Override
	public SelmDeliveryHistory saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmDeliveryHistory.ST_DELIVERY_ID
		String stDeliveryId = wrapper.getParameter(SelmDeliveryHistory.ST_DELIVERY_ID);
		SelmDeliveryHistory oldSelmDeliveryHistory = null;
		if (!StringUtils.trimToEmpty(stDeliveryId).isEmpty()) {
			oldSelmDeliveryHistory = selmDeliveryHistoryDao.get(stDeliveryId);
		}
		if (oldSelmDeliveryHistory == null) {// new
			SelmDeliveryHistory newSelmDeliveryHistory = (SelmDeliveryHistory) t4r.toBean(SelmDeliveryHistory.class);
			newSelmDeliveryHistory.setStDeliveryId(UUID.randomUUID().toString());
			selmDeliveryHistoryDao.add(newSelmDeliveryHistory);
			return newSelmDeliveryHistory;
		}else {// update
			oldSelmDeliveryHistory = (SelmDeliveryHistory) t4r.toBean(oldSelmDeliveryHistory, SelmDeliveryHistory.class);
			selmDeliveryHistoryDao.update(oldSelmDeliveryHistory);
			return oldSelmDeliveryHistory;
		}
	}


	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String stCabinetNo = httpReqRes.getParameter("stCabinetNo");
		String stReceiverName = httpReqRes.getParameter("stReceiverName");//收件人姓名
		String stReceiverIdcard = httpReqRes.getParameter("stReceiverIdcard");//收件人身份证号
		String stSenderName = httpReqRes.getParameter("stSenderName");//投件人姓名
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty() &&!stMachineId.equals("null")) {
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMachineId));
		}
		if (stCabinetNo != null && !StringUtils.trim(stCabinetNo).isEmpty()) {
			conds.add(new Condition("ST_CABINET_NO", Condition.OT_LIKE, stCabinetNo));
		}
		if (stReceiverName != null && !StringUtils.trim(stReceiverName).isEmpty()) {
			conds.add(new Condition("ST_RECEIVER_NAME", Condition.OT_LIKE, stReceiverName));
		}
		if (stReceiverIdcard != null && !StringUtils.trim(stReceiverIdcard).isEmpty()) {
			conds.add(new Condition("ST_RECEIVER_IDCARD", Condition.OT_LIKE, stReceiverIdcard));
		}
		if (stSenderName != null && !StringUtils.trim(stSenderName).isEmpty()) {
			conds.add(new Condition("ST_SENDER_NAME", Condition.OT_LIKE, stSenderName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY DT_CREATE";
		if (orderName != null) {
			if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			}else if("stCabinetNo".equals(orderName)){
				suffix = "ORDER BY ST_CABINET_NO " + orderType.toUpperCase();
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
		}
		List<SelmDeliveryHistory> selmDeliveryHistoryList = selmDeliveryHistoryDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmDeliveryHistoryList,
							SelmDeliveryHistory.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmDeliveryHistoryList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmDeliveryHistoryList);
		return returnObj;
		
	}
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String[] addressIdList = httpReqRes.getRequest().getParameterValues(
				"stDeliveryId[]");
		if (addressIdList == null) {
			String stAddressId = httpReqRes.getRequest()
					.getParameter("stDeliveryId");
			if (stAddressId != null
					&& !StringUtils.trimToEmpty(stAddressId).isEmpty()) {
				selmDeliveryHistoryDao.delete(stAddressId);
				return;
			} else {
				throw new NullPointerException("快递柜ID不能为空");
			}
		}
		for (String addressId : addressIdList) {
			selmDeliveryHistoryDao.delete(addressId);
		}
		
	}
	
	@Autowired
	private SelmDeliveryHistoryDao selmDeliveryHistoryDao;


	
}
