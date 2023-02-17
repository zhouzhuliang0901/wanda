package com.wondersgroup.dataitem.forward.web.service.Impl;

import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.dataitem.forward.web.dao.SelmItemDao;
import com.wondersgroup.dataitem.forward.web.service.SelmItemService;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

/**
 * 事项表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmItemServiceImpl implements SelmItemService {

	/**
	 * 根据主键 {@link SelmItem#ST_ITEM_ID}获取事项表
	 * 
	 * @param stItemId
	 *            事项表主键 {@link SelmItem#ST_ITEM_ID}
	 * @return 事项表实例
	 */
	@Override
	public SelmItem get(String itemCode) {
		if (StringUtils.trimToEmpty(itemCode).isEmpty())
			throw new NullPointerException("Parameter itemCode cannot be null.");
		return selmItemDao.get(itemCode);
	}

	
	
	@Override
	public JSONObject itemList(String itemCode) {
		Conditions conds = Conditions.newAndConditions();
		if (itemCode != null && !StringUtils.trim(itemCode).isEmpty()) {
			conds.add(new Condition("ST_TEN_CODE", Condition.OT_EQUAL,
					itemCode));
		}
		List<SelmItem> itemList = selmItemDao.query(conds,null);
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", itemList.size());
		returnObj.put("data", itemList);
		return returnObj;
	}
	/**
	 * 查询事项表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项表列表
	 */
	@Override
	public JSONObject itemList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE,
					stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					startDate + " 00:00"));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					endDate + " 23:59"));
		}
		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NAME " + orderType.toUpperCase();
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
		
		PaginationArrayList<SelmItem> itemList = selmItemDao.query(conds, suffix, pageSize, currentPage);
	
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", itemList.size());
		returnObj.put("data", itemList);
		return returnObj;
	}

	/**
	 * 根据主键 {@link SelmItem#ST_ITEM_ID}删除事项表
	 * 
	 * @param stItemId
	 *            事项表主键 {@link SelmItem#ST_ITEM_ID}
	 */
	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		if (stItemIdList == null) {
			String stItemId = httpReqRes.getRequest()
					.getParameter("stItemId");
			if (stItemId != null && !StringUtils.trimToEmpty(stItemId).isEmpty()) {
				selmItemDao.delete(stItemId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		for (String stItemId : stItemIdList) {
			selmItemDao.delete(stItemId);
		}
	}

	/**
	 * 保存或更新事项表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项表实例
	 */
	@Override
	public SelmItem saveOrUpdate(HttpReqRes httpReqRes) {
		String stItemId = httpReqRes.getParameter(SelmItem.ST_ITEM_ID);
		SelmItem oldSelmItem = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty()) {
			oldSelmItem = selmItemDao.get(stItemId);
		}
		if (oldSelmItem == null) {// new
			SelmItem newSelmItem = new SelmItem();
			httpReqRes.toBean(newSelmItem);
			newSelmItem.setStItemId(UUID.randomUUID().toString());
			selmItemDao.add(newSelmItem);
			return newSelmItem;
		}else {// update
			httpReqRes.toBean(oldSelmItem);
			selmItemDao.update(oldSelmItem);
			return oldSelmItem;
		}
	}

	@Autowired
	private SelmItemDao selmItemDao;

	

}
