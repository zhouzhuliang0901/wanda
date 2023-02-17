package com.wondersgroup.statistics.service.impl;

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

import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.statistics.bean.SelmClientStatDay;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;
import com.wondersgroup.statistics.dao.SelmClientStatDayDao;
import com.wondersgroup.statistics.service.SelmClientStatDayService;

/**
 * 终端业务统计（按天）业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmClientStatDayServiceImpl implements SelmClientStatDayService {

	/**
	 * 根据主键 {@link SelmClientStatDay#ST_STATISTICS_ID} {@link SelmClientStatDay#ST_DATE} {@link SelmClientStatDay#ST_MACHINE_ID}获取终端业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_DATE}
	 * @param stMachineId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_MACHINE_ID}
	 * @return 终端业务统计（按天）实例
	 */
	@Override
	public SelmClientStatDay get(String stStatisticsId, String stDate, String stMachineId) {
		if (StringUtils.trimToEmpty(stStatisticsId).isEmpty())
			throw new NullPointerException("Parameter stStatisticsId cannot be null.");
		if (StringUtils.trimToEmpty(stDate).isEmpty())
			throw new NullPointerException("Parameter stDate cannot be null.");
		if (StringUtils.trimToEmpty(stMachineId).isEmpty())
			throw new NullPointerException("Parameter stMachineId cannot be null.");
		return selmClientStatDayDao.get(stStatisticsId, stDate, stMachineId);
	}

	/**
	 * 查询终端业务统计（按天）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 终端业务统计（按天）列表
	 */
	@Override
	public PaginationArrayList<SelmClientStatDay> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmClientStatDay.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmClientStatDayDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmClientStatDay#ST_STATISTICS_ID} {@link SelmClientStatDay#ST_DATE} {@link SelmClientStatDay#ST_MACHINE_ID}删除终端业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_DATE}
	 * @param stMachineId
	 *            终端业务统计（按天）主键 {@link SelmClientStatDay#ST_MACHINE_ID}
	 */
	@Override
	public void remove(String stStatisticsId, String stDate, String stMachineId) {
		if (StringUtils.trimToEmpty(stStatisticsId).isEmpty())
			throw new NullPointerException("Parameter stStatisticsId cannot be null.");
		if (StringUtils.trimToEmpty(stDate).isEmpty())
			throw new NullPointerException("Parameter stDate cannot be null.");
		if (StringUtils.trimToEmpty(stMachineId).isEmpty())
			throw new NullPointerException("Parameter stMachineId cannot be null.");
		selmClientStatDayDao.delete(stStatisticsId, stDate, stMachineId);
	}

	/**
	 * 保存或更新终端业务统计（按天）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 终端业务统计（按天）实例
	 */
	@Override
	public SelmClientStatDay saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmClientStatDay.ST_STATISTICS_ID
		String stStatisticsId = wrapper.getParameter(SelmClientStatDay.ST_STATISTICS_ID);
		// SelmClientStatDay.ST_DATE
		String stDate = wrapper.getParameter(SelmClientStatDay.ST_DATE);
		// SelmClientStatDay.ST_MACHINE_ID
		String stMachineId = wrapper.getParameter(SelmClientStatDay.ST_MACHINE_ID);
		SelmClientStatDay oldSelmClientStatDay = null;
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty() && !StringUtils.trimToEmpty(stDate).isEmpty() && !StringUtils.trimToEmpty(stMachineId).isEmpty()) {
			oldSelmClientStatDay = selmClientStatDayDao.get(stStatisticsId, stDate, stMachineId);
		}
		if (oldSelmClientStatDay == null) {// new
			SelmClientStatDay newSelmClientStatDay = (SelmClientStatDay) t4r.toBean(SelmClientStatDay.class);
			newSelmClientStatDay.setStStatisticsId(UUID.randomUUID().toString());
			newSelmClientStatDay.setStDate(UUID.randomUUID().toString());
			newSelmClientStatDay.setStMachineId(UUID.randomUUID().toString());
			selmClientStatDayDao.add(newSelmClientStatDay);
			return newSelmClientStatDay;
		}else {// update
			oldSelmClientStatDay = (SelmClientStatDay) t4r.toBean(oldSelmClientStatDay, SelmClientStatDay.class);
			selmClientStatDayDao.update(oldSelmClientStatDay);
			return oldSelmClientStatDay;
		}
	}


	
	/**
	 * 终端业务统计（按天）列表
	 */
	@Override
	public JSONObject selmClientStatDayList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		
		String stStatisticsId = httpReqRes.getParameter("stStatisticsId");
		String stDate = httpReqRes.getParameter("stDate");
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stStatisticsId != null && !StringUtils.trim(stStatisticsId).isEmpty()) {
			conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE, stStatisticsId));
		}
		if (stDate != null && !StringUtils.trim(stDate).isEmpty()) {
			conds.add(new Condition("ST_DATE", Condition.OT_LIKE,
					stDate));
		}
		if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE,
					stMachineId));
		}
		
		conds.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL,
				null));
		
		String suffix = "ORDER BY DT_TIME";
		if (orderName != null) {
			if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT "+ orderType.toUpperCase();
			}else if ("stMachineId".equals(orderName)) {
				suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
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
		List<SelmClientStatDay> selmClientStatDayList = selmClientStatDayDao.query(conds, suffix, pageSize, currentPage);
		//每天点击量之和
		int count = 0;
		for (SelmClientStatDay selmClientStatDay : selmClientStatDayList) {
			count = count + selmClientStatDay.getNmCount().intValue();
		}
		// count为0，表示没有，不添加“总点击量”
		if (count != 0) {
			BigDecimal amount = new BigDecimal(count);
			SelmClientStatDay info = new SelmClientStatDay();
			info.setNmCount(amount);
			info.setStMachineId("总点击量");
			info.setStStatisticsId("总点击量");
			selmClientStatDayList.add(info);
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmClientStatDayList,
							InfopubDeviceInfoExt.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmClientStatDayList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmClientStatDayList);
		return returnObj;
	}

	
	/**
	 * 终端业务统计（按天）列表（外设）
	 */
	@Override
	public JSONObject odeviceClientStatDayList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		
		String stStatisticsId = httpReqRes.getParameter("stStatisticsId");
		String stDate = httpReqRes.getParameter("stDate");
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stStatisticsId != null && !StringUtils.trim(stStatisticsId).isEmpty()) {
			conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE, stStatisticsId));
		}
		if (stDate != null && !StringUtils.trim(stDate).isEmpty()) {
			conds.add(new Condition("ST_DATE", Condition.OT_LIKE,
					stDate));
		}
		if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE,
					stMachineId));
		}
		
		conds.add(new Condition("NM_COUNT", Condition.OT_EQUAL,
				null));
		
		String suffix = "ORDER BY DT_TIME";
		if (orderName != null) {
			if ("stMachineId".equals(orderName)) {
				suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
			}else if ("nmSuccess".equals(orderName)) {
				suffix = "ORDER BY NM_SUCCESS " + orderType.toUpperCase();
			}else if ("nmFaild".equals(orderName)) {
				suffix = "ORDER BY NM_FAILD " + orderType.toUpperCase();
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
		List<SelmClientStatDay> selmClientStatDayList = selmClientStatDayDao.query(conds, suffix, pageSize, currentPage);
		//每天点击量之和
		int scount = 0;
		for (SelmClientStatDay selmClientStatDay : selmClientStatDayList) {
			scount = scount + selmClientStatDay.getNmSuccess().intValue();
		}
		int fcount = 0;
		for (SelmClientStatDay selmClientStatDay : selmClientStatDayList) {
			fcount = fcount + selmClientStatDay.getNmFaild().intValue();
		}
		// count为0，表示没有，不添加“总点击量”
		if (scount != 0 || fcount != 0) {
			BigDecimal samount = new BigDecimal(scount);
			BigDecimal famount = new BigDecimal(fcount);
			SelmClientStatDay info = new SelmClientStatDay();
			info.setNmSuccess(samount);
			info.setNmFaild(famount);
			info.setStMachineId("总点击量");
			info.setStStatisticsId("总点击量");
			selmClientStatDayList.add(info);
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmClientStatDayList,
							InfopubDeviceInfoExt.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmClientStatDayList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmClientStatDayList);
		return returnObj;
	}
	
	
	
	@Autowired
	private SelmClientStatDayDao selmClientStatDayDao;

	
}
