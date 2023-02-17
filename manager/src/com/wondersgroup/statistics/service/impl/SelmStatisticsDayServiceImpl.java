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
import wfc.service.log.Log;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubGroup;
import com.wondersgroup.infopub.bean.InfopubGroupDevice;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.infopub.dao.InfopubGroupDao;
import com.wondersgroup.infopub.dao.InfopubGroupDeviceDao;
import com.wondersgroup.statistics.bean.SelmClientStatDay;
import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;
import com.wondersgroup.statistics.dao.AnalyticsVisitedDetailDao;
import com.wondersgroup.statistics.dao.SelmClientStatDayDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDayDao;
import com.wondersgroup.statistics.service.SelmStatisticsDayService;

/**
 * 业务统计（按天）业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmStatisticsDayServiceImpl implements SelmStatisticsDayService {

	@Autowired
	private SelmStatisticsDayDao selmStatisticsDayDao;
	@Autowired
	private AnalyticsVisitedDetailDao analyticsVisitedDetailDao;
	@Autowired
	private SelmStatisticsDao selmStatisticsDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;

	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private SelmClientStatDayDao selmClientStatDayDao;
	@Autowired
	private InfopubGroupDeviceDao infopubGroupDeviceDao;

	@Autowired
	private InfopubGroupDao infopubGroupDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	@Autowired
	private InfopubAddressDao infopubAddressDao;

	/**
	 * 根据主键 {@link SelmStatisticsDay#ST_STATISTICS_ID}
	 * {@link SelmStatisticsDay#ST_DATE}获取业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_DATE}
	 * @return 业务统计（按天）实例
	 */
	@Override
	public SelmStatisticsDay get(String stStatisticsId, String stDate) {
		if (StringUtils.trimToEmpty(stStatisticsId).isEmpty())
			throw new NullPointerException(
					"Parameter stStatisticsId cannot be null.");
		if (StringUtils.trimToEmpty(stDate).isEmpty())
			throw new NullPointerException("Parameter stDate cannot be null.");
		return selmStatisticsDayDao.get(stStatisticsId, stDate);
	}

	/**
	 * 查询业务统计（按天）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 业务统计（按天）列表
	 */
	/*
	 * @Override public PaginationArrayList<SelmStatisticsDay>
	 * query(RequestWrapper wrapper) { Conditions conds =
	 * Conditions.newAndConditions(); String suffix = StringUtils.EMPTY; int
	 * pageSize = Integer.MAX_VALUE / 2; int currentPage = 1; if (wrapper !=
	 * null) { Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
	 * wrapper); conds = t4r.toConditions(SelmStatisticsDay.class); Page page =
	 * EasyUIHelper.getPage(wrapper); pageSize = page.getPageSize(); currentPage
	 * = page.getCurrentPage(); suffix = page.getSuffix(); } return
	 * selmStatisticsDayDao.query(conds, suffix, pageSize, currentPage); }
	 */

	/**
	 * 根据主键 {@link SelmStatisticsDay#ST_STATISTICS_ID}
	 * {@link SelmStatisticsDay#ST_DATE}删除业务统计（按天）
	 * 
	 * @param stStatisticsId
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_STATISTICS_ID}
	 * @param stDate
	 *            业务统计（按天）主键 {@link SelmStatisticsDay#ST_DATE}
	 */
	@Override
	public void remove(String stStatisticsId, String stDate) {
		if (StringUtils.trimToEmpty(stStatisticsId).isEmpty())
			throw new NullPointerException(
					"Parameter stStatisticsId cannot be null.");
		if (StringUtils.trimToEmpty(stDate).isEmpty())
			throw new NullPointerException("Parameter stDate cannot be null.");
		selmStatisticsDayDao.delete(stStatisticsId, stDate);
	}

	/**
	 * 保存或更新业务统计（按天）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 业务统计（按天）实例
	 */
	@Override
	public SelmStatisticsDay saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmStatisticsDay.ST_STATISTICS_ID
		String stStatisticsId = wrapper
				.getParameter(SelmStatisticsDay.ST_STATISTICS_ID);
		// SelmStatisticsDay.ST_DATE
		String stDate = wrapper.getParameter(SelmStatisticsDay.ST_DATE);
		SelmStatisticsDay oldSelmStatisticsDay = null;
		if (!StringUtils.trimToEmpty(stStatisticsId).isEmpty()
				&& !StringUtils.trimToEmpty(stDate).isEmpty()) {
			oldSelmStatisticsDay = selmStatisticsDayDao.get(stStatisticsId,
					stDate);
		}
		if (oldSelmStatisticsDay == null) {// new
			SelmStatisticsDay newSelmStatisticsDay = (SelmStatisticsDay) t4r
					.toBean(SelmStatisticsDay.class);
			newSelmStatisticsDay
					.setStStatisticsId(UUID.randomUUID().toString());
			newSelmStatisticsDay.setStDate(UUID.randomUUID().toString());
			selmStatisticsDayDao.add(newSelmStatisticsDay);
			return newSelmStatisticsDay;
		} else {// update
			oldSelmStatisticsDay = (SelmStatisticsDay) t4r.toBean(
					oldSelmStatisticsDay, SelmStatisticsDay.class);
			selmStatisticsDayDao.update(oldSelmStatisticsDay);
			return oldSelmStatisticsDay;
		}
	}

	/**
	 * 查询业务统计（按天）列表
	 */
	@Override
	public JSONObject statisticsList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();

		String stStatisticsId = httpReqRes.getParameter("stStatisticsId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stStatisticsId != null
				&& !StringUtils.trim(stStatisticsId).isEmpty()) {
			conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
					stStatisticsId));
		}
		String suffix = "ORDER BY DT_TIME";
		if (orderName != null) {
			if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
			} else if ("stDate".equals(orderName)) {
				suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
			}
		}

		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}

		conds.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL, null));

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
		List<SelmStatisticsDay> selmStatisticsDayList = selmStatisticsDayDao
				.query(conds, suffix, pageSize, currentPage);
		// 每天点击量之和
		int count = 0;
		for (SelmStatisticsDay selmStatisticsDay : selmStatisticsDayList) {
			count = count + selmStatisticsDay.getNmCount().intValue();
		}
		// count为0，表示没有，不添加“总点击量”
		if (count != 0) {
			BigDecimal amount = new BigDecimal(count);
			SelmStatisticsDay statisticsDay = new SelmStatisticsDay();
			statisticsDay.setNmCount(amount);
			statisticsDay.setStDate("总点击量");
			statisticsDay.setStStatisticsId("总点击量");
			selmStatisticsDayList.add(statisticsDay);
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsDayList,
							InfopubDeviceInfoExt.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmStatisticsDayList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsDayList);
		return returnObj;
	}

	/**
	 * 查询业务统计（按天）列表（外设）
	 */
	@Override
	public JSONObject odeviceStatisticsList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();

		String stStatisticsId = httpReqRes.getParameter("stStatisticsId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stStatisticsId != null
				&& !StringUtils.trim(stStatisticsId).isEmpty()) {
			conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
					stStatisticsId));
		}
		String suffix = "ORDER BY DT_TIME";
		if (orderName != null) {
			if ("stDate".equals(orderName)) {
				suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
			} else if ("nmSuccess".equals(orderName)) {
				suffix = "ORDER BY NM_SUCCESS " + orderType.toUpperCase();
			} else if ("nmFaild".equals(orderName)) {
				suffix = "ORDER BY NM_FAILD " + orderType.toUpperCase();
			}
		}

		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		conds.add(new Condition("NM_COUNT", Condition.OT_EQUAL, null));

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
		List<SelmStatisticsDay> selmStatisticsDayList = selmStatisticsDayDao
				.query(conds, suffix, pageSize, currentPage);
		// 每天点击量之和
		int scount = 0;
		for (SelmStatisticsDay selmStatisticsDay : selmStatisticsDayList) {
			scount = scount + selmStatisticsDay.getNmSuccess().intValue();
		}
		int fcount = 0;
		for (SelmStatisticsDay selmStatisticsDay : selmStatisticsDayList) {
			fcount = fcount + selmStatisticsDay.getNmFaild().intValue();
		}
		// count为0，表示没有，不添加“总点击量”
		if (scount != 0 || fcount != 0) {
			BigDecimal samount = new BigDecimal(scount);
			BigDecimal famount = new BigDecimal(fcount);
			SelmStatisticsDay statisticsDay = new SelmStatisticsDay();
			statisticsDay.setNmSuccess(samount);
			statisticsDay.setNmFaild(famount);
			statisticsDay.setStDate("总点击量");
			statisticsDay.setStStatisticsId("总点击量");
			selmStatisticsDayList.add(statisticsDay);
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsDayList,
							InfopubDeviceInfoExt.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmStatisticsDayList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsDayList);
		return returnObj;
	}

	/**
	 * 查询业务统计（按天）列表（分类）
	 */
	@Override
	public JSONObject typeStatisticsList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		List<SelmClientStatDay> selmClientStatDayList = new ArrayList<SelmClientStatDay>();
		
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stTypeId = httpReqRes.getParameter("stStatisticsId");
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");

		Conditions conds = Conditions.newAndConditions();
		if (stTypeId != null && !StringUtils.trim(stTypeId).isEmpty()) {
			conds.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE,
					stTypeId));
		}else {
			throw new NullPointerException("类型ID为空");
		}
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				conds.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						stAreaId));
			}else {
				throw new NullPointerException("AreaId不能为空");
			}
		}
		// 根据设备类型id查找设备id
		List<InfopubDeviceInfo> deviceinfoList = infopubDeviceInfoDao.query(conds, null);
		Conditions cond = Conditions.newOrConditions();
		Conditions con = Conditions.newAndConditions();
		for (int i = 0; i < deviceinfoList.size(); i++) {
			InfopubDeviceInfo info = deviceinfoList.get(i);
			String stDeviceCode = info.getStDeviceCode();
			if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
				cond.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
						stDeviceCode));
			}
			
		}
		con.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL,
				null));
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
			con.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE, stMachineId));
		}
		
		String suffix = "GROUP BY ST_DATE ORDER BY ST_DATE";
		
		if (orderName != null) {
			if ("stMachineId".equals(orderName)) {
				suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
			} else if ("stDate".equals(orderName)) {
				suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
			} else if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
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
		
		if (deviceinfoList.size() > 0) {
			con.add(cond);
			
			selmClientStatDayList = selmClientStatDayDao.queryByDay(con, suffix, pageSize, currentPage);
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmClientStatDayList,
							SelmClientStatDay.class)).getString("total");
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

	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stStatisticsIdList = httpReqRes.getRequest()
				.getParameterValues("stStatisticsId[]");
		if (stStatisticsIdList == null) {
			String stStatisticsId = httpReqRes.getRequest().getParameter(
					"stStatisticsId");
			if (stStatisticsId != null
					&& !StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
				selmStatisticsDayDao.delete(stStatisticsId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		for (String stStatisticsId : stStatisticsIdList) {
			selmStatisticsDayDao.delete(stStatisticsId);
		}

	}

	
	
	
	@Override
	public JSONObject suListGroupDay(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String stGroupId = httpReqRes.getParameter("stStatisticsId");
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stGroupId != null && !StringUtils.trim(stGroupId).isEmpty()) {
			conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,
					stGroupId));
		}else {
			throw new NullPointerException("分组ID不能为空");
		}
		
		List<SelmClientStatDay> arrayList = new ArrayList<SelmClientStatDay>();
		
		Conditions cond = Conditions.newOrConditions();
		
		// 查找设备分组表获取设备id
		List<InfopubGroupDevice> groupDeviceList = infopubGroupDeviceDao.query(conds, null);
		for (int i = 0; i < groupDeviceList.size(); i++) {
			InfopubGroupDevice groupDevice = groupDeviceList.get(i);
			cond.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,
					groupDevice.getStDeviceId()));
			
		}
		
		if (groupDeviceList.size() > 0) {
			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(cond, null);
			
			Conditions cond1 = Conditions.newOrConditions();
			Conditions con = Conditions.newAndConditions();
			
			for (int i = 0; i < deviceList.size(); i++) {
				InfopubDeviceInfo info = deviceList.get(i);
				String stDeviceCode = info.getStDeviceCode();
				if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
					cond1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
							stDeviceCode));
				}
			}
		
			con.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL,
					null));
			
			if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
				con.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(startDate + " 00:00:00")));
			}
			if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
				con.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
			}
			if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
				con.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE, stMachineId));
			}
			
			String suffix = "GROUP BY ST_DATE ORDER BY ST_DATE";
			
			if (orderName != null) {
				if ("stMachineId".equals(orderName)) {
					suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
				} else if ("stDate".equals(orderName)) {
					suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
				} else if ("nmCount".equals(orderName)) {
					suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
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
			
			if (deviceList.size() > 0) {
				con.add(cond1);
				arrayList = selmClientStatDayDao.queryByDay(con, suffix, pageSize, currentPage);
				
			}
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(arrayList,
							SelmClientStatDay.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", arrayList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", arrayList);
		return returnObj;
	}
	
	
	@Override
	public JSONObject suListItemDay(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		List<SelmClientStatDay> selmClientStatDayList = new ArrayList<SelmClientStatDay>();
		
		String stAreaId = httpReqRes.getParameter("stStatisticsId");
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");

		Conditions conds = Conditions.newAndConditions();
		if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
			conds.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
					stAreaId));
		}else {
			throw new NullPointerException("areaID为空");
		}
		// 根据设备类型id查找设备id
		List<InfopubDeviceInfo> deviceinfoList = infopubDeviceInfoDao.query(conds, null);
		Conditions cond = Conditions.newOrConditions();
		Conditions con = Conditions.newAndConditions();
		for (int i = 0; i < deviceinfoList.size(); i++) {
			InfopubDeviceInfo info = deviceinfoList.get(i);
			String stDeviceCode = info.getStDeviceCode();
			if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
				cond.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
						stDeviceCode));
			}
			
		}
		con.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL,
				null));
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
			con.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE, stMachineId));
		}
		
		String suffix = "GROUP BY ST_DATE ORDER BY ST_DATE";
		
		if (orderName != null) {
			if ("stMachineId".equals(orderName)) {
				suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
			} else if ("stDate".equals(orderName)) {
				suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
			} else if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
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
		
		if (deviceinfoList.size() > 0) {
			con.add(cond);
			
			selmClientStatDayList = selmClientStatDayDao.queryByDay(con, suffix, pageSize, currentPage);
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmClientStatDayList,
							SelmClientStatDay.class)).getString("total");
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

	@Override
	public JSONObject addressStatisticsList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		List<SelmClientStatDay> selmClientStatDayList = new ArrayList<SelmClientStatDay>();
		
		String stName = httpReqRes.getParameter("stName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions condaddress = Conditions.newAndConditions();
		Conditions conds = Conditions.newOrConditions();
		condaddress.add(new Condition("ST_DISTRICT", Condition.OT_LIKE,
				stName));
		List<InfopubAddress> addressList = infopubAddressDao.query(
				condaddress, null);
		for (InfopubAddress infopubAddress : addressList) {
			conds.add(new Condition("ST_ADDRESS_ID", Condition.OT_LIKE,
					infopubAddress.getStAddressId()));
		}
		/*Conditions conds = Conditions.newOrConditions();
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			
		}else {
			throw new NullPointerException("类型ID为空");
		}*/
		// 根据设备类型id查找设备id
		List<InfopubDeviceInfo> deviceinfoList = infopubDeviceInfoDao.query(conds, null);
		Conditions cond = Conditions.newOrConditions();
		Conditions con = Conditions.newAndConditions();
		for (int i = 0; i < deviceinfoList.size(); i++) {
			InfopubDeviceInfo info = deviceinfoList.get(i);
			String stDeviceCode = info.getStDeviceCode();
			if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
				cond.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
						stDeviceCode));
			}
			
		}
		con.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL,
				null));
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		String suffix = "GROUP BY ST_DATE ORDER BY ST_DATE";
		
		if (orderName != null) {
			if ("stMachineId".equals(orderName)) {
				suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
			} else if ("stDate".equals(orderName)) {
				suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
			} else if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
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
		
		if (deviceinfoList.size() > 0) {
			con.add(cond);
			
			selmClientStatDayList = selmClientStatDayDao.queryByDay(con, suffix, pageSize, currentPage);
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmClientStatDayList,
							SelmClientStatDay.class)).getString("total");
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
	
	@Override
	public JSONObject streetStatisticsList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		List<SelmClientStatDay> selmClientStatDayList = new ArrayList<SelmClientStatDay>();
		
		String stStreet = httpReqRes.getParameter("stAddressId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions condaddress = Conditions.newAndConditions();
		Conditions conds = Conditions.newOrConditions();
		condaddress.add(new Condition("ST_STREET", Condition.OT_EQUAL,
				stStreet));
		List<InfopubAddress> addressList = infopubAddressDao.query(
				condaddress, null);
		for (InfopubAddress infopubAddress : addressList) {
			conds.add(new Condition("ST_ADDRESS_ID", Condition.OT_LIKE,
					infopubAddress.getStAddressId()));
		}
		/*Conditions conds = Conditions.newOrConditions();
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			
		}else {
			throw new NullPointerException("类型ID为空");
		}*/
		// 根据设备类型id查找设备id
		List<InfopubDeviceInfo> deviceinfoList = infopubDeviceInfoDao.query(conds, null);
		Conditions cond = Conditions.newOrConditions();
		Conditions con = Conditions.newAndConditions();
		for (int i = 0; i < deviceinfoList.size(); i++) {
			InfopubDeviceInfo info = deviceinfoList.get(i);
			String stDeviceCode = info.getStDeviceCode();
			if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
				cond.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
						stDeviceCode));
			}
			
		}
		con.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL,
				null));
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			con.add(new Condition("ST_DATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		String suffix = "GROUP BY ST_DATE ORDER BY ST_DATE";
		
		if (orderName != null) {
			if ("stMachineId".equals(orderName)) {
				suffix = "ORDER BY ST_MACHINE_ID " + orderType.toUpperCase();
			} else if ("stDate".equals(orderName)) {
				suffix = "ORDER BY ST_DATE " + orderType.toUpperCase();
			} else if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
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
		
		if (deviceinfoList.size() > 0) {
			con.add(cond);
			
			selmClientStatDayList = selmClientStatDayDao.queryByDay(con, suffix, pageSize, currentPage);
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmClientStatDayList,
							SelmClientStatDay.class)).getString("total");
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
	
}
