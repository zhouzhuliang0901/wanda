package com.wondersgroup.statistics.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.util.logging.resources.logging;
import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.tool.helper.LogHelper;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;
import wfc.service.log.Log;
import coral.base.app.AppContext;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jimi.tools.Debug;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubGroup;
import com.wondersgroup.infopub.bean.InfopubGroupDevice;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.bean.InfopubWorkspace;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDaoExt;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.infopub.dao.InfopubGroupDao;
import com.wondersgroup.infopub.dao.InfopubGroupDeviceDao;
import com.wondersgroup.infopub.dao.InfopubOdeviceStatusDao;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;
import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.statistics.bean.AnalyticsVisitedDetail;
import com.wondersgroup.statistics.bean.SelmClientStatDay;
import com.wondersgroup.statistics.bean.SelmSatisfactionInfo;
import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;
import com.wondersgroup.statistics.dao.AnalyticsVisitedDetailDao;
import com.wondersgroup.statistics.dao.SelmClientStatDayDao;
import com.wondersgroup.statistics.dao.SelmSatisfactionInfoDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDayDao;
import com.wondersgroup.statistics.service.SelmStatisticsDayService;
import com.wondersgroup.statistics.service.SelmStatisticsService;

/**
 * ?????????????????????
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmStatisticsServiceImpl implements SelmStatisticsService {

	@Autowired
	private SelmStatisticsDao selmStatisticsDao;

	@Autowired
	private AnalyticsVisitedDetailDao analyticsVisitedDetailDao;

	@Autowired
	private SelmStatisticsDayDao selmStatisticsDayDao;

	@Autowired
	private SelmClientStatDayDao selmClientStatDayDao;

	@Autowired
	private InfopubAreaDao infopubAreaDao;

	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;

	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;

	@Autowired
	private InfopubGroupDao infopubGroupDao;
	
	@Autowired
	private InfopubOdeviceStatusDao infopubOdeviceStatusDao;
	
	@Autowired
	private InfopubDeviceInfoDaoExt infopubDeviceInfoDaoExt;
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private SmsOrganDao smsOrganDao;
	@Autowired
	private SelmSatisfactionInfoDao selmSatisfactionInfoDao;
	@Autowired
	private SmsUserDao smsUserDao;

	/**
	 * ???????????? {@link SelmStatistics#ST_STATISTICS_ID}???????????????
	 * 
	 * @param stStatisticsId
	 *            ??????????????? {@link SelmStatistics#ST_STATISTICS_ID}
	 * @return ???????????????
	 */
	@Override
	public SelmStatistics get(String stStatisticsId) {
		if (StringUtils.trimToEmpty(stStatisticsId).isEmpty())
			throw new NullPointerException(
					"Parameter stStatisticsId cannot be null.");
		return selmStatisticsDao.get(stStatisticsId);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ???????????????
	 */
	@Override
	public PaginationArrayList<SelmStatistics> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmStatistics.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmStatisticsDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * ???????????? {@link SelmStatistics#ST_STATISTICS_ID}???????????????
	 * 
	 * @param stStatisticsId
	 *            ??????????????? {@link SelmStatistics#ST_STATISTICS_ID}
	 */
	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stStatisticsIdList = httpReqRes.getRequest()
				.getParameterValues("stStatisticsId[]");
		if (stStatisticsIdList == null) {
			String stStatisticsId = httpReqRes.getRequest().getParameter(
					"stStatisticsId");
			if (stStatisticsId != null
					&& !StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
				selmStatisticsDao.delete(stStatisticsId);
				return;
			} else {
				throw new NullPointerException("??????ID????????????");
			}
		}
		for (String stStatisticsId : stStatisticsIdList) {
			selmStatisticsDao.delete(stStatisticsId);
		}
	}

	/**
	 * ????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ???????????????
	 */
	@Override
	public SelmStatistics add(HttpReqRes httpReqRes) {
		String stStatisticsId = httpReqRes
				.getParameter(SelmStatistics.ST_STATISTICS_ID);
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session ???????????????????????????");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		SelmStatistics oldSelmStatistics = null;
		if (stStatisticsId != null
				&& !StringUtils.trimToEmpty(stStatisticsId).isEmpty()) {
			oldSelmStatistics = selmStatisticsDao.get(stStatisticsId);
		}

		if (oldSelmStatistics != null) {
			httpReqRes.toBean(oldSelmStatistics);
			selmStatisticsDao.update(oldSelmStatistics);
			return oldSelmStatistics;
		} else {
			SelmStatistics newSelmStatistics = new SelmStatistics();
			httpReqRes.toBean(newSelmStatistics);
			BigDecimal nmOdevice = newSelmStatistics.getNmOdevice();
			if (nmOdevice.intValue() == 1) {
				newSelmStatistics.setStStatisticsId(newSelmStatistics
						.getStNetFlag());
				String stNetFlag = newSelmStatistics.getStNetFlag();
				SelmStatistics statistics = selmStatisticsDao
						.getBystNetFlag(stNetFlag);
				if (statistics != null) {
					return null;
				}
				selmStatisticsDao.add(newSelmStatistics);
				// ???????????????
				updateStatistics(newSelmStatistics);
				return newSelmStatistics;
			} else {
				newSelmStatistics.setStStatisticsId(newSelmStatistics
						.getStNetFlag());
				String stNetFlag = newSelmStatistics.getStNetFlag();
				SelmStatistics statistics = selmStatisticsDao
						.getBystNetFlag(stNetFlag);
				if (statistics != null) {
					return null;
				}
				selmStatisticsDao.add(newSelmStatistics);
				// ???????????????
				updateStatistics(newSelmStatistics);
				/*
				 * // ????????????????????? update(newSelmStatistics); // ????????????????????????????????????
				 * updateClient(newSelmStatistics);
				 */
				return newSelmStatistics;
			}
		}
	}

	/**
	 * ??????????????????
	 */
	public void updateStatistics(SelmStatistics selmStatistics) {
		BigDecimal nmOdevice = selmStatistics.getNmOdevice();
		String stStatisticsId = selmStatistics.getStStatisticsId();
		if (nmOdevice.intValue() == 1) {
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
					stStatisticsId));
			List<SelmStatisticsDay> OdeviceStatisticsDayList = selmStatisticsDayDao
					.query(cond, null);
			// ????????????
			int scount = 0;
			// ????????????
			int fcount = 0;

			for (int j = 0; j < OdeviceStatisticsDayList.size(); j++) {
				SelmStatisticsDay selmStatisticsDay = OdeviceStatisticsDayList
						.get(j);

				BigDecimal nmFaild = selmStatisticsDay.getNmFaild();
				fcount = fcount + nmFaild.intValue();

				BigDecimal nmSuccess = selmStatisticsDay.getNmSuccess();
				scount = scount + nmSuccess.intValue();
			}
			// ??????
			int count = scount + fcount;
			// SelmStatistics info = new SelmStatistics();
			// info.setStStatisticsId(stStatisticsId);
			// info.setStName(stName);
			// info.setStNetFlag(stNetFlag);
			selmStatistics.setNmCount(new BigDecimal(count));
			selmStatisticsDao.update(selmStatistics);
		} else {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
					stStatisticsId));
			List<SelmStatisticsDay> statisticsDayList = selmStatisticsDayDao
					.query(conds, null);
			int count = 0;
			for (int i = 0; i < statisticsDayList.size(); i++) {
				SelmStatisticsDay selmStatisticsDay = statisticsDayList.get(i);
				BigDecimal nmCount = selmStatisticsDay.getNmCount();
				count = count + nmCount.intValue();
			}
			selmStatistics.setNmCount(new BigDecimal(count));
			selmStatisticsDao.update(selmStatistics);
		}
	}

	/**
	 * ????????????????????????
	 */
	@Override
	public JSONObject statisticsConfigList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();

		String stNetFlag = httpReqRes.getParameter("stNetFlag");
		String stNetSubFlag = httpReqRes.getParameter("stNetSubFlag");
		String stName = httpReqRes.getParameter("stName");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stNetFlag != null && !StringUtils.trim(stNetFlag).isEmpty()) {
			conds.add(new Condition("ST_NET_FLAG", Condition.OT_LIKE, stNetFlag));
		}
		if (stNetSubFlag != null && !StringUtils.trim(stNetSubFlag).isEmpty()) {
			conds.add(new Condition("ST_NET_SUB_FLAG", Condition.OT_LIKE,
					stNetSubFlag));
		}
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("ST_NAME", Condition.OT_LIKE, stName));
		}
		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stNetFlag".equals(orderName)) {
				suffix = "ORDER BY ST_NET_FLAG " + orderType.toUpperCase();
			} else if ("stNetSubFlag".equals(orderName)) {
				suffix = "ORDER BY ST_NET_SUB_FLAG " + orderType.toUpperCase();
			} else if ("stName".equals(orderName)) {
				suffix = "ORDER BY ST_NAME " + orderType.toUpperCase();
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmStatistics> selmStatisticsList = selmStatisticsDao.query(
				conds, suffix, pageSize, currentPage);
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsList, SelmStatistics.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsList);
		return returnObj;
	}

	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject statisticsList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();

		String stName = httpReqRes.getParameter("stName");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");

		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("ST_NAME", Condition.OT_LIKE, stName));
		}

		BigDecimal nmOdevice = new BigDecimal(0);
		conds.add(new Condition("NM_ODEVICE", Condition.OT_EQUAL, nmOdevice));

		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stName".equals(orderName)) {
				suffix = "ORDER BY ST_NAME " + orderType.toUpperCase();
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmStatistics> selmStatisticsList = selmStatisticsDao.query(
				conds, suffix, pageSize, currentPage);
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsList, SelmStatistics.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsList);
		return returnObj;
	}

	/**
	 * ??????????????????(??????)
	 */
	@Override
	public JSONObject odeviceStatisticsList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();

		String stName = httpReqRes.getParameter("stName");

		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");

		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("ST_NAME", Condition.OT_LIKE, stName));
		}

		BigDecimal nmOdevice = new BigDecimal(1);
		conds.add(new Condition("NM_ODEVICE", Condition.OT_EQUAL, nmOdevice));

		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stName".equals(orderName)) {
				suffix = "ORDER BY ST_NAME " + orderType.toUpperCase();
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmStatistics> selmStatisticsList = selmStatisticsDao.query(
				conds, suffix, pageSize, currentPage);
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsList, SelmStatistics.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsList);
		return returnObj;
	}

	/**
	 * ????????????????????????
	 */
	@Override
	public JSONObject typeStatisticsList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}

		String stName = httpReqRes.getParameter("stName");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");

		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");

		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stName".equals(orderName)) {
				suffix = "ORDER BY ST_NAME " + orderType.toUpperCase();
			} else if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY NM_COUNT " + orderType.toUpperCase();
			}
		}
		
		Conditions cond1 = Conditions.newAndConditions();
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			cond1.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, stName));
		} else {
			cond1.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL,
					null));
		}
		Conditions condArea = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				condArea.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						stAreaId));
			}else {
				throw new NullPointerException("AreaId????????????");
			}
		}
	
		
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		List<InfopubDeviceType> deviceTypeList = infopubDeviceTypeDao.query(
				cond1, null);
		// int total = 0;
		for (int i = 0; i < deviceTypeList.size(); i++) {
			InfopubDeviceType infopubDeviceType = deviceTypeList.get(i);
			String stTypeId = infopubDeviceType.getStTypeId();
			Conditions cond2 = Conditions.newAndConditions();
			cond2.add(condArea);
			cond2.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL, stTypeId));
			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
					cond2, null);
			int amount = 0;
			for (int j = 0; j < deviceList.size(); j++) {
				InfopubDeviceInfo infopubDeviceInfo = deviceList.get(j);
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE,
						infopubDeviceInfo.getStDeviceCode()));
				conds.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL, null));
				List<SelmClientStatDay> clientList = selmClientStatDayDao
						.query(conds, null);
				int count = 0;
				for (int k = 0; k < clientList.size(); k++) {
					SelmClientStatDay selmClientStatDay = clientList.get(k);
					count = count + selmClientStatDay.getNmCount().intValue();
				}
				amount = amount + count;
			}
			// total = total + amount;
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(infopubDeviceType.getStTypeId());
			selmStatistics.setStName(infopubDeviceType.getStTypeName());
			selmStatistics.setNmCount(new BigDecimal(amount));
			selmStatisticsList.add(selmStatistics);
		}

		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsList, SelmStatistics.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsList);
		return returnObj;
	}

	/**
	 * ?????????????????????
	 */
	/*
	 * @Override public void update(SelmStatistics selmStatistics) {
	 * List<SelmStatisticsDay> list = analyticsVisitedDetailDao
	 * .count(selmStatistics); for (SelmStatisticsDay selmStatisticsDay : list)
	 * { SelmStatisticsDay flag = selmStatisticsDayDao.get(
	 * selmStatisticsDay.getStStatisticsId(), selmStatisticsDay.getStDate()); if
	 * (flag == null) { selmStatisticsDayDao.add(selmStatisticsDay); } else {
	 * selmStatisticsDayDao.update(selmStatisticsDay); } }
	 * 
	 * }
	 */

	/**
	 * ?????????????????????????????????????????????
	 */
	/*
	 * @Override public void updateClient(SelmStatistics selmStatistics) {
	 * Conditions conds = Conditions.newAndConditions(); conds.add(new
	 * Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
	 * selmStatistics.getStStatisticsId())); List<SelmStatisticsDay> list =
	 * selmStatisticsDayDao.query(conds, null); for (SelmStatisticsDay
	 * selmStatisticsDay : list) { List<SelmClientStatDay> selmClientStatDayList
	 * = analyticsVisitedDetailDao .clientCount(selmStatistics,
	 * selmStatisticsDay); for (SelmClientStatDay selmClientStatDay :
	 * selmClientStatDayList) { String stStatisticsId =
	 * selmClientStatDay.getStStatisticsId(); String stDate =
	 * selmClientStatDay.getStDate(); String stMachineId =
	 * selmClientStatDay.getStMachineId(); SelmClientStatDay flag =
	 * selmClientStatDayDao.get( stStatisticsId, stDate, stMachineId); if (flag
	 * == null) { selmClientStatDayDao.add(selmClientStatDay); } else {
	 * selmClientStatDayDao.update(selmClientStatDay); } }
	 * 
	 * }
	 * 
	 * }
	 */

	/**
	 * ?????????????????????
	 * 
	 * ???????????????INFOPUB_DEVICE_TYPE,INFOPUB_DEVICE_INFO,analytics_visited_detail;
	 */

	@Override
	public JSONObject suCount(HttpReqRes httpReqRes) {
		String stTypeName = httpReqRes.getParameter("stTypeName");
		Conditions conds = Conditions.newAndConditions();
		// conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL,
		// null));
		if (stTypeName != null && !StringUtils.trim(stTypeName).isEmpty()) {
			conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
					stTypeName));
		} else {
			conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL,
					null));
		}

		// ?????????????????????????????????id???????????????
		List<InfopubDeviceType> deviceTypeLst = infopubDeviceTypeDao.query(
				conds, null);
		ArrayList<SelmClientStatDay> arrayList = new ArrayList<SelmClientStatDay>();
		// ?????????????????????
		for (InfopubDeviceType infopubDeviceType : deviceTypeLst) {
			String stTypeId = infopubDeviceType.getStTypeId();
			String stName = infopubDeviceType.getStTypeName();
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL, stTypeId));
			// ??????????????????id????????????id
			List<InfopubDeviceInfo> deviceinfoList = infopubDeviceInfoDao
					.query(cond, null);
			int count = 0;
			for (InfopubDeviceInfo infopubDeviceInfo : deviceinfoList) {
				String stDeviceCode = infopubDeviceInfo.getStDeviceCode();
				// ??????????????? ???????????? ?????????ST_DEVICE_CODE ???????????? ST_COOKIE
				int count1 = analyticsVisitedDetailDao.suCount(stDeviceCode);
				count += count1;
			}
			// ?????????????????????????????????
			SelmClientStatDay selmClientStatDay = new SelmClientStatDay();
			selmClientStatDay.setStDate(stName);
			selmClientStatDay.setNmCount(new BigDecimal(count));
			arrayList.add(selmClientStatDay);
		}
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(arrayList, SelmClientStatDay.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", arrayList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", arrayList);
		return returnObj;

	}
	
	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject addressList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stName = httpReqRes.getParameter("stAddressName");
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubArea.getStAreaName()));
			}else {
				throw new NullPointerException("AreaId????????????");
			}
		}else{
			if (stName != null && !StringUtils.trim(stName).isEmpty()) {
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, stName));
			}
		}
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		
			//???????????????????????????
		String suffix="GROUP BY ST_DISTRICT";
		addressListName = infopubAddressDao.queryName(condaddressName, suffix);
		//addressListName = infopubAddressDao.selectAll();
		//List<InfopubAddress> addressListName = infopubAddressDao.selectAll();
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		for (InfopubAddress infopubAddress : addressListName) {
			condaddress= Conditions.newAndConditions();
			condaddress.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubAddress.getStDistrict()));
			 addressList = infopubAddressDao.query(
					condaddress, null);
			int amount = 0;
			//int sum = 0;
			for (InfopubAddress infopubAddress2 : addressList) {
				String stAdderssId = infopubAddress2.getStAddressId();
				Conditions cond2 = Conditions.newAndConditions();
				cond2.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAdderssId));
				List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
						cond2, null);
				//int amount = 0;
				for (int j = 0; j < deviceList.size(); j++) {
					InfopubDeviceInfo infopubDeviceInfo = deviceList.get(j);
					Conditions conds = Conditions.newAndConditions();
					conds.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE,
							infopubDeviceInfo.getStDeviceCode()));
					conds.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL, null));
					List<SelmClientStatDay> clientList = selmClientStatDayDao
							.query(conds, null);
					int count = 0;
					for (int k = 0; k < clientList.size(); k++) {
						SelmClientStatDay selmClientStatDay = clientList.get(k);
						count = count + selmClientStatDay.getNmCount().intValue();
					}
					amount = amount + count;
				}
				//sum = sum + amount;
			}
			SelmStatistics selmStatistics = new SelmStatistics();
			//selmStatistics.setStStatisticsId(stAddressId);
			selmStatistics.setStName(infopubAddress.getStDistrict());
			selmStatistics.setNmCount(new BigDecimal(amount));
			selmStatisticsList.add(selmStatistics);
		}
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsList, SelmStatistics.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("draw", drawInt);
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", selmStatisticsList);
		return returnObj;
	}
	
	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject streetlist(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stStreetsName = httpReqRes.getParameter("stStreetsName");
		String stName = httpReqRes.getParameter("stAddressName");
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubArea.getStAreaName()));
			}else {
				throw new NullPointerException("AreaId????????????");
			}
		}else{
			if (stName != null && !StringUtils.trim(stName).isEmpty()) {
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, stName));
			}
		}
		if (stStreetsName != null && !StringUtils.trim(stStreetsName).isEmpty()) {
			condaddressName.add(new Condition("ST_STREET", Condition.OT_LIKE, stStreetsName));
		}
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		/*int pageSize = Integer.MAX_VALUE / 2;
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
			//???????????????????????????
		
		addressListName = infopubAddressDao.query(condaddressName, null,pageSize,currentPage);*/
		String suffix="GROUP BY ST_STREET";
		addressListName = infopubAddressDao.queryStreet(condaddressName, suffix);
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		for (InfopubAddress infopubAddress : addressListName) {
			condaddress= Conditions.newAndConditions();
			condaddress.add(new Condition("ST_STREET", Condition.OT_LIKE, infopubAddress.getStStreet()));
			 addressList = infopubAddressDao.query(
					condaddress, null);
			int amount = 0;
			//int sum = 0;
			for (InfopubAddress infopubAddress2 : addressList) {
				String stAdderssId = infopubAddress2.getStAddressId();
				Conditions cond2 = Conditions.newAndConditions();
				cond2.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAdderssId));
				List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
						cond2, null);
				//int amount = 0;
				for (int j = 0; j < deviceList.size(); j++) {
					InfopubDeviceInfo infopubDeviceInfo = deviceList.get(j);
					Conditions conds = Conditions.newAndConditions();
					conds.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE,
							infopubDeviceInfo.getStDeviceCode()));
					conds.add(new Condition("NM_COUNT", Condition.OT_UNEQUAL, null));
					List<SelmClientStatDay> clientList = selmClientStatDayDao
							.query(conds, null);
					int count = 0;
					for (int k = 0; k < clientList.size(); k++) {
						SelmClientStatDay selmClientStatDay = clientList.get(k);
						count = count + selmClientStatDay.getNmCount().intValue();
					}
					amount = amount + count;
				}
				//sum = sum + amount;
			}
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStStatisticsId(infopubAddress.getStAddressId());
			//selmStatistics.setStName(infopubAddress.getStCity()+infopubAddress.getStDistrict()+infopubAddress.getStStreet());
			selmStatistics.setStName(infopubAddress.getStStreet());
			selmStatistics.setNmCount(new BigDecimal(amount));
			selmStatisticsList.add(selmStatistics);
		}
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(addressListName, InfopubAddress.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("draw", drawInt);
			//returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", selmStatisticsList);
		return returnObj;
	}


	/**
	 * ?????????????????? ????????????INFOPUB_GROUP???INFOPUB_GROUP_DEVICE???analytics_visited_detail;
	 */

	@Autowired
	private InfopubGroupDeviceDao infopubGroupDeviceDao;

	@Override
	public JSONObject suCountGroup(HttpReqRes httpReqRes) {
		// ????????????
		// String StName = httpReqRes.getParameter("stName");
		// ????????????????????????
		String stGroupName = httpReqRes.getParameter("stGroupName");
		Conditions conds = Conditions.newAndConditions();
		if (stGroupName != null && !StringUtils.trim(stGroupName).isEmpty()) {
			conds.add(new Condition("ST_GROUP_NAME", Condition.OT_LIKE,
					stGroupName));
		}/*
		 * else{ conds.add(new Condition(null, Condition.OT_EQUAL, null)); }
		 */

		// ????????????????????????
		List<InfopubGroup> deviceTypeLst = infopubGroupDao.query(conds, null);
		ArrayList<SelmClientStatDay> arrayListGroup = new ArrayList<SelmClientStatDay>();
		for (InfopubGroup infopubDeviceGroup : deviceTypeLst) {
			String stGroupId = infopubDeviceGroup.getStGroupId();
			String stNameGroup = infopubDeviceGroup.getStGroupName();
			// ?????????????????????????????????????????????id
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
			List<InfopubGroupDevice> deviceGroupList = infopubGroupDeviceDao
					.query(cond, null);
			int count = 0;
			for (InfopubGroupDevice infopubGroupDevice : deviceGroupList) {
				String stDeviceId = infopubGroupDevice.getStDeviceId();
				InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao
						.get(stDeviceId);
				if (deviceInfo == null) {
					continue;
				}
				String stDeviceCode = deviceInfo.getStDeviceCode();
				// ??????????????? ????????????
				int count1 = analyticsVisitedDetailDao.suCount(stDeviceCode);
				count += count1;
			}
			SelmClientStatDay selmClientStatDay = new SelmClientStatDay();
			selmClientStatDay.setStStatisticsId(infopubDeviceGroup.getStGroupId());
			selmClientStatDay.setStDate(stNameGroup);
			selmClientStatDay.setNmCount(new BigDecimal(count));
			arrayListGroup.add(selmClientStatDay);
		}
		// }
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(arrayListGroup, SelmClientStatDay.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", arrayListGroup.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", arrayListGroup);
		return returnObj;

	}

	/**
	 * ?????????????????? ????????????INFOPUB_AREA???INFOPUB_GROUP???INFOPUB_GROUP_DEVICE???
	 * analytics_visited_detail;
	 */
	@Override
	public JSONObject suCountItem(HttpReqRes httpReqRes) {
		String areaName = httpReqRes.getParameter("areaName");
		Conditions cond = Conditions.newAndConditions();
		if (areaName != null && !StringUtils.trim(areaName).isEmpty()) {
			cond.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE, areaName));
		} else {
			cond.add(new Condition(null, Condition.OT_EQUAL, null));
		}
		// ??????????????????????????????
		List<InfopubArea> query = infopubAreaDao.query(cond, null);
		ArrayList<SelmClientStatDay> arrayListGroup = new ArrayList<SelmClientStatDay>();
		for (InfopubArea infopubArea : query) {
			String stAreaId = infopubArea.getStAreaId();
			String stAreaName = infopubArea.getStAreaName();
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
			// ????????????????????????id?????????????????????
			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(conds,
					null);
				int count = 0;
				for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
					String stDeviceCode = infopubDeviceInfo.getStDeviceCode();
					int count1 = analyticsVisitedDetailDao
							.suCount(stDeviceCode);
					count += count1;
				}

				SelmClientStatDay selmClientStatDay = new SelmClientStatDay();
				selmClientStatDay.setStStatisticsId(infopubArea.getStAreaId());
				selmClientStatDay.setStDate(stAreaName);
				selmClientStatDay.setNmCount(new BigDecimal(count));
				arrayListGroup.add(selmClientStatDay);
		}
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(arrayListGroup, SelmClientStatDay.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", arrayListGroup.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", arrayListGroup);
		return returnObj;
	}

	/*
	 * // ???????????????(?????????) public void updateSelmStatistics() { // ?????????????????????????????????
	 * List<SelmStatistics> list = analyticsVisitedDetailDao.query();
	 * Iterator<SelmStatistics> ListIterator = list.iterator(); while
	 * (ListIterator.hasNext()) { SelmStatistics selmStatistics =
	 * ListIterator.next(); String stNetFlag = selmStatistics.getStNetFlag();
	 * 
	 * Conditions conds = Conditions.newAndConditions(); conds.add(new
	 * Condition("ST_NET_FLAG", Condition.OT_LIKE, stNetFlag);
	 * 
	 * // ??????????????????????????????????????????????????????????????????????????? SelmStatistics flag =
	 * selmStatisticsDao.getBystNetFlag(stNetFlag); if (flag == null) {
	 * selmStatistics.setStStatisticsId(UUID.randomUUID().toString());
	 * selmStatistics.setNmOdevice(new BigDecimal(0));
	 * selmStatisticsDao.add(selmStatistics); } else {
	 * selmStatisticsDao.update(selmStatistics); } }
	 * System.out.println("????????????1......"); }
	 */

	// ???????????????????????????
	public String updateSelmStatisticsDay() {
		// ??????????????????
		String suffix = "GROUP BY ST_NET_FLAG";
		List<AnalyticsVisitedDetail> list = analyticsVisitedDetailDao
				.queryStNetFlag(null, suffix);
		Iterator<AnalyticsVisitedDetail> iterator = list.iterator();
		while (iterator.hasNext()) {
			AnalyticsVisitedDetail analyticsVisitedDetail = iterator.next();
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(analyticsVisitedDetail.getStNetFlag());
			// ??????????????????????????????????????????????????????
			List<SelmStatisticsDay> statisticsDayList = analyticsVisitedDetailDao
					.count(selmStatistics);
			Iterator<SelmStatisticsDay> statisticsDayIterator = statisticsDayList
					.iterator();
			while (statisticsDayIterator.hasNext()) {
				// ?????????????????????????????????????????????
				SelmStatisticsDay statisticsDay = statisticsDayIterator.next();
				String stStatisticsId = statisticsDay.getStStatisticsId();
				String stDate = statisticsDay.getStDate();
				SelmStatisticsDay flag = selmStatisticsDayDao.get(
						stStatisticsId, stDate);
				// ??????????????????
				if (flag == null) {
					selmStatisticsDayDao.add(statisticsDay);
				} else {
					/*
					 * statisticsDay.setDtTime(new Timestamp(System
					 * .currentTimeMillis()));
					 */
					selmStatisticsDayDao.update(statisticsDay);
				}
			}
		}
		
		System.out.println("????????????22......");
		return "success";
	}

	// ????????????????????????(??????)()
	public String updateSelmClientStatisticsDay() {
		// ?????????????????????????????????
		/*
		 * List<SelmStatistics> statisticsList = selmStatisticsDao.query(null,
		 * null); Iterator<SelmStatistics> statisticsIterator =
		 * statisticsList.iterator(); while (statisticsIterator.hasNext()) {
		 * SelmStatistics selmStatistics = statisticsIterator.next(); //
		 * ?????????????????????????????????????????????????????? List<SelmStatisticsDay> statisticsDayList =
		 * analyticsVisitedDetailDao .count(selmStatistics);
		 * Iterator<SelmStatisticsDay> statisticsDayIterator = statisticsDayList
		 * .iterator(); while (statisticsDayIterator.hasNext()) {
		 * SelmStatisticsDay statisticsDay = statisticsDayIterator.next(); //
		 * ????????????????????????????????????????????? List<SelmClientStatDay> selmClientStatDayList =
		 * analyticsVisitedDetailDao .clientCount(selmStatistics,
		 * statisticsDay); Iterator<SelmClientStatDay> clientStatDayIterator =
		 * selmClientStatDayList .iterator(); while
		 * (clientStatDayIterator.hasNext()) { SelmClientStatDay
		 * selmClientStatDay = clientStatDayIterator .next(); String
		 * stStatisticsId = selmClientStatDay .getStStatisticsId(); String
		 * stDate = selmClientStatDay.getStDate(); String stMachineId =
		 * selmClientStatDay.getStMachineId(); // ?????????????????????????????? SelmClientStatDay
		 * flag = selmClientStatDayDao.get( stStatisticsId, stDate,
		 * stMachineId); if (flag == null) {
		 * selmClientStatDayDao.add(selmClientStatDay); } else {
		 * selmClientStatDayDao.update(selmClientStatDay); } } } }
		 * System.out.println("????????????3......"); }
		 */
		String suffix = "GROUP BY ST_NET_FLAG";
		List<AnalyticsVisitedDetail> list = analyticsVisitedDetailDao
				.queryStNetFlag(null, suffix);
		Iterator<AnalyticsVisitedDetail> iterator = list.iterator();
		while (iterator.hasNext()) {
			AnalyticsVisitedDetail analyticsVisitedDetail = iterator.next();
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(analyticsVisitedDetail.getStNetFlag());
			// ??????????????????????????????????????????????????????
			List<SelmStatisticsDay> statisticsDayList = analyticsVisitedDetailDao
					.count(selmStatistics);
			Iterator<SelmStatisticsDay> statisticsDayIterator = statisticsDayList
					.iterator();
			while (statisticsDayIterator.hasNext()) {
				SelmStatisticsDay statisticsDay = statisticsDayIterator.next();
				// ?????????????????????????????????????????????
				List<SelmClientStatDay> selmClientStatDayList = analyticsVisitedDetailDao
						.clientCount(selmStatistics, statisticsDay);
				Iterator<SelmClientStatDay> clientStatDayIterator = selmClientStatDayList
						.iterator();
				while (clientStatDayIterator.hasNext()) {
					SelmClientStatDay selmClientStatDay = clientStatDayIterator
							.next();
					String stStatisticsId = selmClientStatDay
							.getStStatisticsId();
					String stDate = selmClientStatDay.getStDate();
					String stMachineId = selmClientStatDay.getStMachineId();
					// ??????????????????????????????
					SelmClientStatDay flag = selmClientStatDayDao.get(
							stStatisticsId, stDate, stMachineId);
					if (flag == null) {
						selmClientStatDayDao.add(selmClientStatDay);
					} else {
						selmClientStatDayDao.update(selmClientStatDay);
					}
				}
			}
		}
		System.out.println("????????????33......");
		return "success";
	}

	// ????????????????????????????????????
	public String importOdeviceToDay(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String stDate = df.format(new Date());

		/*
		 * BigDecimal nmOdevice = new BigDecimal(1); Conditions conds =
		 * Conditions.newAndConditions(); conds.add(new Condition("NM_ODEVICE",
		 * Condition.OT_EQUAL, nmOdevice)); List<SelmStatistics> list =
		 * selmStatisticsDao.query(conds, null); for (int i = 0; i <
		 * list.size(); i++) { SelmStatistics selmStatistics = list.get(i);
		 * String stStatisticsId = selmStatistics.getStStatisticsId();
		 * 
		 * Conditions cond = Conditions.newAndConditions(); cond.add(new
		 * Condition("ST_STATISTICS_ID", Condition.OT_LIKE, stStatisticsId));
		 * cond.add(new Condition("ST_DATE", Condition.OT_LIKE, stDate));
		 * //cond.add(new Condition("NM_COUNT", Condition.OT_EQUAL, null));
		 * //String suffix = "GROUP BY ST_DATE" ;
		 */
		//String str = "GROUP BY ST_DEVICE_ID";
		List<InfopubOdeviceStatus> odeviceList = infopubOdeviceStatusDao.distinct();
		for (int i = 0; i < odeviceList.size(); i++) {
			InfopubOdeviceStatus infopubOdeviceStatus = odeviceList.get(i);
			String stOutDeviceCode = infopubOdeviceStatus.getStOutDeviceCode();
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
					stOutDeviceCode));
			cond.add(new Condition("ST_DATE", Condition.OT_LIKE, stDate));
			// cond.add(new Condition("NM_COUNT", Condition.OT_EQUAL, null));
			// String suffix = "GROUP BY ST_DATE" ;
			List<SelmClientStatDay> odeviceClientStatDayList = selmClientStatDayDao
					.query(cond, null);
			// ??????
			int scount = 0;
			// ??????
			int fcount = 0;
			for (int j = 0; j < odeviceClientStatDayList.size(); j++) {
				SelmClientStatDay selmClientStatDay = odeviceClientStatDayList
						.get(j);
				// ??????
				BigDecimal nmFaild = selmClientStatDay.getNmFaild();
				if (nmFaild != null) {
					// nmFaild.intValue();
					fcount = fcount + nmFaild.intValue();
				}

				// ??????
				BigDecimal nmSuccess = selmClientStatDay.getNmSuccess();
				if (nmSuccess != null) {
					scount = scount + nmSuccess.intValue();
				}
			}
			if (scount != 0 || fcount != 0) {
				SelmStatisticsDay selmStatisticsDay = new SelmStatisticsDay();
				selmStatisticsDay.setStDate(stDate);
				selmStatisticsDay.setNmFaild(new BigDecimal(fcount));
				selmStatisticsDay.setNmSuccess(new BigDecimal(scount));
				selmStatisticsDay.setStStatisticsId(stOutDeviceCode);
				selmStatisticsDay.setDtTime(new Timestamp(System
						.currentTimeMillis()));
				SelmStatisticsDay statisticsDay = selmStatisticsDayDao.get(selmStatisticsDay.getStStatisticsId(), selmStatisticsDay.getStDate());
				if (statisticsDay != null) {
					selmStatisticsDayDao.update(selmStatisticsDay);
				}else {
					selmStatisticsDayDao.add(selmStatisticsDay);
				}
			}
		}
		System.out.println("???????????????????????????????????????????????????");
		return "success";
	}
	
	
	// ?????????client?????????????????????
	public String importOdeviceToClientDay(){
		List<InfopubOdeviceStatus> odeviceList = infopubOdeviceStatusDao.query(
				null, null);
		for (int i = 0; i < odeviceList.size(); i++) {
			InfopubOdeviceStatus infopubOdeviceStatus = odeviceList.get(i);
			String stDeviceMac = infopubOdeviceStatus.getStDeviceId();
			InfopubDeviceInfo deviceInfo = infopubDeviceInfoDaoExt
					.getByMacId(stDeviceMac);
			if (deviceInfo == null) {
				continue;
			}
			// ????????????
			String stDeviceCode = deviceInfo.getStDeviceCode();
			// ??????
			int scount = 0;
			// ??????
			int fcount = 0;
			
			// ???????????????
			BigDecimal nmHisStotal = infopubOdeviceStatus.getNmHisStotal();
			if (nmHisStotal != null) {
				scount = nmHisStotal.intValue();
			}
			// int stotal = nmHisStotal.intValue();
			// scount = stotal;
			// scount +=stotal;
			// ???????????????
			BigDecimal nmHisFtotal = infopubOdeviceStatus.getNmHisFtotal();
			if (nmHisFtotal != null) {
				fcount = nmHisFtotal.intValue();
			}
			// int ftotal = nmHisFtotal.intValue();
			// fcount = ftotal ;
			// ????????????????????????
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String stDate = df.format(new Date());
			SelmClientStatDay selmClientStatDay = new SelmClientStatDay();
			selmClientStatDay.setStDate(stDate);
			selmClientStatDay.setStStatisticsId(infopubOdeviceStatus
					.getStOutDeviceCode());
			if (scount != 0 || fcount != 0) {
				// ????????????
				selmClientStatDay.setNmFaild(new BigDecimal(fcount));
				// ????????????
				selmClientStatDay.setNmSuccess(new BigDecimal(scount));
				selmClientStatDay.setStMachineId(stDeviceCode);
				selmClientStatDay.setDtTime(new Timestamp(System
						.currentTimeMillis()));
				SelmClientStatDay clientStatDay = selmClientStatDayDao.get(selmClientStatDay.getStStatisticsId(), selmClientStatDay.getStDate(), selmClientStatDay.getStMachineId());
				if (clientStatDay != null) {
					selmClientStatDayDao.update(selmClientStatDay);
				}else {
					selmClientStatDayDao.add(selmClientStatDay);
				}
			}
		}
		System.out.println("?????????????????????????????????????????????????????????");
		return "success";
	}

	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject addressTypeList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stName = httpReqRes.getParameter("stAddressName");
		String stItemName = httpReqRes.getParameter("stItemName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions condArea= Conditions.newAndConditions();
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		InfopubArea infopubArea = infopubAreaDao.getName("?????????");
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			condArea.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE, stName));
		}else{
			condArea.add(new Condition("ST_PARENT_AREA_ID", Condition.OT_LIKE, infopubArea.getStAreaId()));
		}
		String suffix="ORDER BY NM_ORDER ";
		if (orderName != null) {
			if ("stName".equals(orderName)) {
				suffix = "ORDER BY NM_ORDER " + orderType.toUpperCase();
			}
		}
		
		List<InfopubArea> infopubAreaName = infopubAreaDao.query(condArea, suffix);
		for (InfopubArea infopubAreaId : infopubAreaName) {
			String stAreaId = infopubAreaId.getStAreaId();
			Conditions cond2 = Conditions.newAndConditions();
			cond2.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
					cond2, null);
			//?????????
			Conditions cond1 = Conditions.newOrConditions();
			int selmCount = 0;
			for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
				String stDeviceCode = infopubDeviceInfo.getStDeviceMac();
				cond1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceCode));
			}
				Conditions conds = Conditions.newAndConditions();
				if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
					conds.add(new Condition("ST_ITEM_NAME", Condition.OT_EQUAL,
							stItemName));
				}
				if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
					conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
							Timestamp.valueOf(startDate + " 00:00:00")));
				}
				if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
					conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
							Timestamp.valueOf(endDate + " 23:59:59")));
				}
				cond1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, "++++++++"));
				conds.add(cond1);
				List<SelmQueryHis> selmQueryList = selmQueryHisDao.query(conds, null);
				int selmNum = selmQueryList.size();
				 selmCount = selmCount + selmNum;
			
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(infopubAreaId.getStAreaName());
			selmStatistics.setNmSort(new BigDecimal(selmCount));//?????????
			selmStatisticsList.add(selmStatistics);
		}
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmStatisticsList, SelmStatistics.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("draw", drawInt);
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", selmStatisticsList);
		
			return returnObj;
	}
	
	/**
	 * ????????????????????????
	 */
	@Override
	public JSONObject streetTypeList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		/*int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}*/
		String stName = httpReqRes.getParameter("stAddressName");//?????????????????????????????????
		String stItemName = httpReqRes.getParameter("stItemName");//?????????
		String addressStreet = httpReqRes.getParameter("addressStreet");//????????????????????????????????????
		String stStreetName = httpReqRes.getParameter("stStreetsName");//???????????????????????????
		String stPermission = httpReqRes.getParameter("stPermission");
		String district = httpReqRes.getParameter("district");
		String user = httpReqRes.getParameter("user");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String typeId = httpReqRes.getParameter("typeId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		//??????
		conds.add(new Condition("IDT.NM_DTYPE", Condition.OT_EQUAL, 0));
		
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_EQUAL, stName));
		}
		if (addressStreet != null && !StringUtils.trim(addressStreet).isEmpty()) {
			conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_EQUAL, addressStreet));
		}
		if (stStreetName != null && !StringUtils.trim(stStreetName).isEmpty()) {
			conds.add(new Condition("IA.ST_STREET", Condition.OT_EQUAL, stStreetName));
		}
		if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
			conds.add(new Condition("SQH.ST_ITEM_NAME", Condition.OT_EQUAL,
					stItemName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("SQH.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("SQH.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("IDT.ST_TYPE_ID", Condition.OT_EQUAL,typeId));
		}
		
		
		Conditions usercon = Conditions.newAndConditions();
		SmsUser smsUser = new SmsUser();
		if (user != null && !StringUtils.trim(user).isEmpty()) {
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			smsUser = smsUserL.get(0);
		}
		
		if("changshang".equals(stPermission)){
			String stUserName = smsUser.getStUserName();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(typeInfoName.indexOf("???")+1, typeInfoName.indexOf("???"));
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}else if("organ".equals(stPermission)){
			String stOrganId = smsUser.getStOrganId();
			if(stOrganId != null && !StringUtils.trim(stOrganId).isEmpty()){
				conds.add(new Condition("IDI.ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
			}
		}
		
		String suffix="GROUP BY IA.ST_STREET";
		
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		List<InfopubAddress> infopubAddressList = infopubAddressDao.streetSelmQueryCount(conds,suffix);
		int numStQueryCount = 0;
		int numZhengWu = 0;
		String stOrganId = smsUser.getStOrganId();
		for (InfopubAddress infopubAddress : infopubAddressList) {
			String stStreet = infopubAddress.getStStreet(); //????????????
			String stQueryCount = infopubAddress.getStLabel(); //???????????????
			conds = Conditions.newAndConditions();
			conds.add(new Condition("IDI.ST_STATE", Condition.OT_UNEQUAL, new BigDecimal(0)));//?????????????????????

			conds.add(new Condition("IA.ST_STREET", Condition.OT_EQUAL, stStreet));
			conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_EQUAL, district));
			if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
				conds.add(new Condition("IDT.ST_TYPE_ID", Condition.OT_EQUAL,typeId));
			}
			conds.add(new Condition("IDT.NM_DTYPE", Condition.OT_EQUAL,0));
			
			if(stOrganId != null && !StringUtils.trim(stOrganId).isEmpty()){
				conds.add(new Condition("IDI.ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
			}
			List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao.streetDeviceType(conds,null);
			String zhengWu="";
			for (InfopubDeviceType infopubDeviceType : infopubDeviceTypeList) {
				zhengWu = infopubDeviceType.getNmDtype().toString();//????????????
			}
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(stStreet);
			selmStatistics.setStExt1(zhengWu);//??????????????????
			selmStatistics.setNmSort(new BigDecimal(stQueryCount));//?????????
			selmStatisticsList.add(selmStatistics);
			numStQueryCount+=Integer.parseInt(stQueryCount);
			numZhengWu+=Integer.parseInt(zhengWu);
		}
		SelmStatistics selmStatistics = new SelmStatistics();
		selmStatistics.setStName("??????");
		selmStatistics.setStExt1(numZhengWu+"");//??????????????????
		selmStatistics.setNmSort(new BigDecimal(numStQueryCount));//?????????
		selmStatisticsList.add(selmStatistics);
		
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubAddressList, InfopubAddress.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmStatisticsList);
	
		return returnObj;
	}

	/**
	 * ???????????????????????????????????????????????????????????????mac???????????????
	 */
	@Override
	public JSONObject deviceInfoQueryList(HttpReqRes httpReqRes) {
		String stStreetName = httpReqRes.getParameter("stStreetName");
		String stDeviceCode = httpReqRes.getParameter("stDeviceCode");
		String stPermission = httpReqRes.getParameter("stPermission");
		String user = httpReqRes.getParameter("user");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String reqType = httpReqRes.getParameter("reqType");
		String typeId = httpReqRes.getParameter("typeId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		
		//???????????????????????????
		Conditions usercon = Conditions.newAndConditions();
		usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
		List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
		SmsUser smsUser = smsUserL.get(0);
		String stUserName = smsUser.getStUserName();
		
		
		Conditions conds = Conditions.newAndConditions();
		if(reqType.equals("ZW")){
			conds.add(new Condition("ia.ST_STREET", Condition.OT_EQUAL, stStreetName));
			conds.add(new Condition("idt.NM_DTYPE", Condition.OT_EQUAL, 0));//??????
			if("changshang".equals(stPermission)){
				Conditions typeInfoConds = Conditions.newAndConditions();
				typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
				List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
				for(InfopubDeviceType emp : typeInfo){
					String typeInfoName = emp.getStTypeName();
					typeInfoName = typeInfoName.substring(typeInfoName.indexOf("???")+1, typeInfoName.indexOf("???"));
					if(stUserName.contains(typeInfoName)){
						conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
					}
				}
			}
		}else if(reqType.equals("YH")){
			if("bank".equals(stPermission)){
				//??????????????????????????????
				Conditions typeInfoConds = Conditions.newAndConditions();
				typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
				List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
				for(InfopubDeviceType emp : typeInfo){
					String typeInfoName = emp.getStTypeName();
					typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("???")+1);
					if(stUserName.contains(typeInfoName)){
						conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
					}
				}
			}
			conds.add(new Condition("ia.ST_DISTRICT", Condition.OT_EQUAL, stStreetName));
			conds.add(new Condition("idt.NM_DTYPE", Condition.OT_EQUAL, 1));//??????
		}
		
		if(null != stDeviceCode && !StringUtils.trim(stDeviceCode).isEmpty()){
			conds.add(new Condition("idi.ST_DEVICE_CODE", Condition.OT_EQUAL, stDeviceCode));
		}
		
		if(null != typeId && !StringUtils.trim(typeId).isEmpty()){
			conds.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL, typeId));
		}
		List<InfopubDeviceInfo> infoDeviceInfoList = infopubDeviceInfoDao.getDeviceWithStreet(conds,null, pageSize, currentPage);
		for (InfopubDeviceInfo infopubDeviceInfo : infoDeviceInfoList) {
			String deviceMac = infopubDeviceInfo.getStDeviceMac();
			conds = Conditions.newAndConditions();
			conds.add(new Condition("sqh.ST_MACHINE_ID", Condition.OT_EQUAL, deviceMac));
			if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
				conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(startDate + " 00:00:00")));
			}
			if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
				conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
			int selmQueryNum = selmQueryHisDao.queryleadCount(conds, null);
			infopubDeviceInfo.setNmDownTry(new BigDecimal(selmQueryNum));//?????????
			
		}
		
		
		
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infoDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", infoDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infoDeviceInfoList);
		return returnObj;
	}

	
	
	@SuppressWarnings("all")
	@Override
	public JSONObject addresslistTypeDevice(HttpReqRes httpReqRes) {
		String staddressName = httpReqRes.getParameter("stAddressName");
		System.out.println(staddressName);
		staddressName = Decode.decode(staddressName, "utf-8");
		System.out.println(staddressName);
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		JSONObject returnObj = new JSONObject();
		JSONObject returnType = new JSONObject();
		JSONObject returnNm = new JSONObject();
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubArea.getStAreaName()));
			}else {
				throw new NullPointerException("AreaId????????????");
			}
		}else{
			if (staddressName != null && !StringUtils.trim(staddressName).isEmpty()) {
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, staddressName));
			}
		}
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		// ???????????????????????????
		String suffix = null;
			if(DB.getDatabaseName()=="MySQL"){
				suffix = "GROUP BY ST_DISTRICT  ORDER BY field(ST_DISTRICT,'?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','????????????') DESC";
			}else{
				suffix = " GROUP BY ST_DISTRICT  ORDER BY  case [ST_DISTRICT] WHEN '?????????' THEN 1 WHEN '?????????' THEN 2 WHEN '?????????' THEN 3 WHEN '?????????' THEN 4 WHEN '?????????' THEN 5 WHEN '?????????' THEN 6 WHEN '?????????' THEN 7 WHEN '?????????' THEN 8 WHEN '?????????' THEN 9 WHEN '?????????' THEN 10 WHEN '?????????' THEN 11 WHEN '?????????' THEN 12 WHEN '?????????' THEN 13 WHEN '?????????' THEN 14 WHEN '?????????' THEN 15 WHEN '????????????' THEN 16 END DESC";
		}

		addressListName = infopubAddressDao.queryName(condaddressName, suffix);
		// ????????????
		Conditions condDevice = Conditions.newAndConditions();
		Conditions conds = Conditions.newAndConditions();
		Conditions condOr = Conditions.newOrConditions();
		Conditions condOrDevice = Conditions.newOrConditions();
		Conditions condNmDtype = Conditions.newAndConditions();
		String suffix1 = "GROUP BY NM_DTYPE";
		condNmDtype.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
				null));
		List<InfopubDeviceType> query = infopubDeviceTypeDao.queryNmDtype(
				condNmDtype, suffix1);
		List listAll = new ArrayList();
		for (InfopubDeviceType infopubDeviceType1 : query) {
			BigDecimal nmDtype = infopubDeviceType1.getNmDtype();
			conds = Conditions.newAndConditions();
			conds.add(new Condition("NM_DTYPE", Condition.OT_LIKE, nmDtype));
			conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
					null));
			List<InfopubDeviceType> InfopubDeviceTypeList = infopubDeviceTypeDao
					.query(conds, null);
				List listObj = new ArrayList();
				for (InfopubAddress infopubAddress : addressListName) {
					condaddress = Conditions.newAndConditions();
					//condDevice = Conditions.newAndConditions();
					condOrDevice = Conditions.newOrConditions();
					condaddress.add(new Condition("ST_DISTRICT",
							Condition.OT_LIKE, infopubAddress.getStDistrict()));
					addressList = infopubAddressDao.query(condaddress, null);
					for (InfopubAddress infopubAddress2 : addressList) {
						String stAddressId = infopubAddress2.getStAddressId();
						condOrDevice.add(new Condition("ST_ADDRESS_ID",
								Condition.OT_LIKE, stAddressId));
					}
					List listType = new ArrayList();
					if(nmDtype.equals(new BigDecimal(0))){
						condDevice.add(condOrDevice);
						condDevice.add(new Condition("IDT.NM_DTYPE",
								Condition.OT_EQUAL, 0));
						List<InfopubDeviceInfo> queryZheng = infopubDeviceInfoDao.queryZheng(condDevice,null);
						condDevice = Conditions.newAndConditions();
						List listCode = new ArrayList();
						for (InfopubDeviceInfo infopubDeviceInfo : queryZheng) {
							listCode.add(infopubDeviceInfo.getStDeviceCode());
						}
						returnObj.put("typeName", "????????????");
						returnObj.put("code", listCode.size());
						//returnObj.put("codeName", listCode);
						listType.add(returnObj);
						returnObj = new JSONObject();
					}else{
						for (InfopubDeviceType infopubDeviceType : InfopubDeviceTypeList) {
							condOr = Conditions.newOrConditions();
							String stName = infopubDeviceType.getStTypeName();
							String stTypeId = infopubDeviceType.getStTypeId();
							condOr.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE,
									stTypeId));
							condDevice.add(condOr);
							condDevice.add(condOrDevice);
							List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
									.query(condDevice, null);
							condDevice = Conditions.newAndConditions();
							List listCode = new ArrayList();
							for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
								listCode.add(infopubDeviceInfo.getStDeviceCode());
							}
							returnObj.put("typeName", stName);
							returnObj.put("code", listCode.size());
							//returnObj.put("codeName", listCode);
							listType.add(returnObj);
							returnObj = new JSONObject();
					}
					}
					returnType.put("area", infopubAddress.getStDistrict());
					returnType.put("type", listType);
					listObj.add(returnType);
					returnType = new JSONObject();
				}
			returnNm.put("nmDtype", nmDtype);
			returnNm.put("nmType", listObj);
			listAll.add(returnNm);
			returnNm = new JSONObject();
		}
		JSONObject returnAll = new JSONObject();
		returnAll.put("data", listAll);
		return returnAll;
	}
	
	/**
	 *  ???????????????????????????????????????????????????????????????????????????????????????
	 */
	@SuppressWarnings("all")
	@Override
	public JSONObject streetlistTypeDevice(HttpReqRes httpReqRes) {
		String staddressName = httpReqRes.getParameter("stAddressName");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stStreetName = httpReqRes.getParameter("stStreetName");
		stStreetName = Decode.decode(stStreetName, "utf-8");
		staddressName = Decode.decode(staddressName, "utf-8");
		staddressName = Decode.decode(staddressName, "utf-8");
		System.out.println(staddressName+"---------");
		JSONObject returnObj = new JSONObject();
		JSONObject returnType = new JSONObject();
		JSONObject returnNm = new JSONObject();
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubArea.getStAreaName()));
			}else {
				throw new NullPointerException("AreaId????????????");
			}
		}else{
			if (!staddressName.equals("null") && !StringUtils.trim(staddressName).isEmpty()) {
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, staddressName));
			}
		}
		
		if (stStreetName != null && !StringUtils.trim(stStreetName).isEmpty()) {
			condaddressName.add(new Condition("ST_STREET", Condition.OT_LIKE, stStreetName));
		}
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		if (staddressName != null && !StringUtils.trim(staddressName).isEmpty()) {
			condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, staddressName));
		}
		// ???????????????????????????
		String suffix = "GROUP BY ST_STREET";
		addressListName = infopubAddressDao.queryStreet(condaddressName, suffix);
		// ????????????
		Conditions condDevice = Conditions.newAndConditions();
		Conditions conds = Conditions.newAndConditions();
		Conditions condOr = Conditions.newOrConditions();
		Conditions condOrDevice = Conditions.newOrConditions();
		Conditions condNmDtype = Conditions.newAndConditions();
		String suffix1 = "GROUP BY NM_DTYPE";
		condNmDtype.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
				null));
		List<InfopubDeviceType> query = infopubDeviceTypeDao.queryNmDtype(
				condNmDtype, suffix1);
		List listAll = new ArrayList();
		for (InfopubDeviceType infopubDeviceType1 : query) {
			BigDecimal nmDtype = infopubDeviceType1.getNmDtype();
			conds = Conditions.newAndConditions();
			conds.add(new Condition("NM_DTYPE", Condition.OT_LIKE, nmDtype));
			conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
					null));
			List<InfopubDeviceType> InfopubDeviceTypeList = infopubDeviceTypeDao
					.query(conds, null);
				List listObj = new ArrayList();
				for (InfopubAddress infopubAddress : addressListName) {
					condaddress = Conditions.newAndConditions();
					condOrDevice = Conditions.newOrConditions();
					condaddress.add(new Condition("ST_STREET",
							Condition.OT_LIKE, infopubAddress.getStStreet()));
					addressList = infopubAddressDao.query(condaddress, null);
					for (InfopubAddress infopubAddress2 : addressList) {
						String stAddressId = infopubAddress2.getStAddressId();
						condOrDevice.add(new Condition("ST_ADDRESS_ID",
								Condition.OT_LIKE, stAddressId));
					}
					List listType = new ArrayList();
					if(nmDtype.equals(new BigDecimal(0))){
						condDevice.add(condOrDevice);
						condDevice.add(new Condition("IDT.NM_DTYPE",
								Condition.OT_EQUAL, 0));
						List<InfopubDeviceInfo> queryZheng = infopubDeviceInfoDao.queryZheng(condDevice,null);
						condDevice = Conditions.newAndConditions();
						List listCode = new ArrayList();
						for (InfopubDeviceInfo infopubDeviceInfo : queryZheng) {
							listCode.add(infopubDeviceInfo.getStDeviceCode());
						}
						returnObj.put("typeName", "????????????");
						returnObj.put("code", listCode.size());
						returnObj.put("codeName", listCode);
						listType.add(returnObj);
						returnObj = new JSONObject();
					}else{
					for (InfopubDeviceType infopubDeviceType : InfopubDeviceTypeList) {
						condOr = Conditions.newOrConditions();
						String stName = infopubDeviceType.getStTypeName();
						String stTypeId = infopubDeviceType.getStTypeId();
						condOr.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE,
								stTypeId));
					condDevice.add(condOr);
					condDevice.add(condOrDevice);
					List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
							.query(condDevice, null);
					condDevice = Conditions.newAndConditions();
					List listCode = new ArrayList();
					for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
						listCode.add(infopubDeviceInfo.getStDeviceCode());
					}
					returnObj.put("typeName", stName);
					returnObj.put("code", listCode.size());
					returnObj.put("codeName", listCode);
					listType.add(returnObj);
					returnObj = new JSONObject();
				}
				}
					returnType.put("area", infopubAddress.getStStreet());
					returnType.put("type", listType);
					listObj.add(returnType);
					returnType = new JSONObject();
				}
			returnNm.put("nmDtype", nmDtype);
			returnNm.put("nmType", listObj);
			listAll.add(returnNm);
			returnNm = new JSONObject();
		}
		JSONObject returnAll = new JSONObject();
		returnAll.put("data", listAll);
		return returnAll;
	}
	
	
	/**
	 * ???????????????????????????????????????
	 */
	@SuppressWarnings("all")
	@Override
	public JSONObject streetTypeDevice(HttpReqRes httpReqRes) {
		String stStreetName = httpReqRes.getParameter("areaName");
		String typeName = httpReqRes.getParameter("typeName");
		//String staddressName = httpReqRes.getParameter("stAddressName");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		//String stStreetName = httpReqRes.getParameter("stStreetName");
		stStreetName = Decode.decode(stStreetName, "utf-8");
		typeName = Decode.decode(typeName,"utf-8");
		//staddressName = Decode.decode(staddressName, "utf-8");
		//System.out.println(staddressName+"---------");
		JSONObject returnObj = new JSONObject();
		JSONObject returnType = new JSONObject();
		JSONObject returnNm = new JSONObject();
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		/*if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubArea.getStAreaName()));
			}else {
				throw new NullPointerException("AreaId????????????");
			}
		}else{
			if (!staddressName.equals("null") && !StringUtils.trim(staddressName).isEmpty()) {
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, staddressName));
			}
		}*/
		
		if (stStreetName != null && !StringUtils.trim(stStreetName).isEmpty()) {
			condaddressName.add(new Condition("ST_STREET", Condition.OT_LIKE, stStreetName));
		}
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		/*if (staddressName != null && !StringUtils.trim(staddressName).isEmpty()) {
			condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, staddressName));
		}*/
		// ???????????????????????????
		String suffix = "GROUP BY ST_STREET";
		addressListName = infopubAddressDao.queryStreet(condaddressName, suffix);
		// ????????????
		Conditions condDevice = Conditions.newAndConditions();
		Conditions conds = Conditions.newAndConditions();
		Conditions condOr = Conditions.newOrConditions();
		Conditions condOrDevice = Conditions.newOrConditions();
		Conditions condNmDtype = Conditions.newAndConditions();
		String suffix1 = "GROUP BY NM_DTYPE";
		condNmDtype.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
				null));
		List<InfopubDeviceType> query = infopubDeviceTypeDao.queryNmDtype(
				condNmDtype, suffix1);
		List listAll = new ArrayList();
		for (InfopubDeviceType infopubDeviceType1 : query) {
			BigDecimal nmDtype = infopubDeviceType1.getNmDtype();
			conds = Conditions.newAndConditions();
			conds.add(new Condition("NM_DTYPE", Condition.OT_LIKE, nmDtype));
			conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
					null));
			List<InfopubDeviceType> InfopubDeviceTypeList = infopubDeviceTypeDao
					.query(conds, null);
				List listObj = new ArrayList();
				for (InfopubAddress infopubAddress : addressListName) {
					condaddress = Conditions.newAndConditions();
					condOrDevice = Conditions.newOrConditions();
					condaddress.add(new Condition("ST_STREET",
							Condition.OT_LIKE, infopubAddress.getStStreet()));
					addressList = infopubAddressDao.query(condaddress, null);
					for (InfopubAddress infopubAddress2 : addressList) {
						String stAddressId = infopubAddress2.getStAddressId();
						condOrDevice.add(new Condition("ST_ADDRESS_ID",
								Condition.OT_LIKE, stAddressId));
					}
					List listType = new ArrayList();
					/*if(nmDtype.equals(new BigDecimal(0))){
						condDevice.add(condOrDevice);
						condDevice.add(new Condition("IDT.NM_DTYPE",
								Condition.OT_EQUAL, 0));
						List<InfopubDeviceInfo> queryZheng = infopubDeviceInfoDao.queryZheng(condDevice,null);
						condDevice = Conditions.newAndConditions();
						List listCode = new ArrayList();
						for (InfopubDeviceInfo infopubDeviceInfo : queryZheng) {
							listCode.add(infopubDeviceInfo.getStDeviceCode());
						}
						returnObj.put("typeName", "????????????");
						returnObj.put("code", listCode.size());
						returnObj.put("codeName", listCode);
						listType.add(returnObj);
						returnObj = new JSONObject();
					}else{*/
					for (InfopubDeviceType infopubDeviceType : InfopubDeviceTypeList) {
						condOr = Conditions.newOrConditions();
						String stName = infopubDeviceType.getStTypeName();
						String stTypeId = infopubDeviceType.getStTypeId();
						condOr.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE,
								stTypeId));
					condDevice.add(condOr);
					condDevice.add(condOrDevice);
					List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
							.query(condDevice, null);
					condDevice = Conditions.newAndConditions();
					List listCode = new ArrayList();
					for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
						listCode.add(infopubDeviceInfo.getStDeviceCode());
					}
					returnObj.put("typeName", stName);
					returnObj.put("code", listCode.size());
					returnObj.put("codeName", listCode);
					listType.add(returnObj);
					returnObj = new JSONObject();
				}
				//}
					returnType.put("area", infopubAddress.getStStreet());
					returnType.put("type", listType);
					listObj.add(returnType);
					returnType = new JSONObject();
				}
			returnNm.put("nmDtype", nmDtype);
			returnNm.put("nmType", listObj);
			listAll.add(returnNm);
			returnNm = new JSONObject();
		}
		JSONObject returnAll = new JSONObject();
		returnAll.put("data", listAll);
		return returnAll;
	}
	
	/**
	 * ????????????????????????????????????
	 */
	@Override
	public JSONObject areaModuleQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stDistictName = httpReqRes.getParameter("stDistictName");//?????????????????????????????????
		String stStreetName = httpReqRes.getParameter("stStreetName");//?????????????????????????????????
		String reqType = httpReqRes.getParameter("reqType");//?????????????????????/??????
		String typeId = httpReqRes.getParameter("typeId");
		String stModuleOp = httpReqRes.getParameter("stModuleOp");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String permission = httpReqRes.getParameter("permission");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		Conditions conds = Conditions.newAndConditions();
		if(reqType.equals("YH")){
			conds.add(new Condition("a.NM_DTYPE", Condition.OT_EQUAL,1));
		}else{
			conds.add(new Condition("a.NM_DTYPE", Condition.OT_EQUAL,0));
		}
		
		//??????
		/*if("organ" ==""){
			
			
		}*/
		if (stDistictName != null && !StringUtils.trim(stDistictName).isEmpty() &&!stDistictName.equals("null") && !"??????".equals(stDistictName)) {
			conds.add(new Condition("a.ST_DISTRICT", Condition.OT_EQUAL, stDistictName));
		}
		if (stStreetName != null && !StringUtils.trim(stStreetName).isEmpty() &&!stStreetName.equals("null") && !"??????".equals(stStreetName)) {
			conds.add(new Condition("a.ST_STREET", Condition.OT_EQUAL, stStreetName));
		}
		if (stModuleOp != null && !StringUtils.trim(stModuleOp).isEmpty()) {
			conds.add(new Condition("a.ST_MODULE_OP", Condition.OT_LIKE,
					stModuleOp));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("a.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("a.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("a.ST_TYPE_ID", Condition.OT_EQUAL,typeId));
		}
		//??????mac???or?????? ????????????????????????????????????
		String suffix = "GROUP BY a.ST_MODULE_OP";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryAddressModuleOp(conds, suffix,pageSize,currentPage);
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(selmQueryHis.getStModuleOp());
			selmStatistics.setNmCount(new BigDecimal(selmQueryHis.getStExt1()));
			selmStatisticsList.add(selmStatistics);
		}
			
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryList, SelmQueryHis.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("draw", drawInt);
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", selmStatisticsList);
		
			return returnObj;
	}
	
	
	/**
	 * ????????????????????????????????????
	 */
	@Override
	public JSONObject provinceModuleQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stCitytName = httpReqRes.getParameter("stCitytName");//?????????????????????????????????
		String stProvinceName = httpReqRes.getParameter("stProvinceName");//?????????????????????????????????
		String reqType = httpReqRes.getParameter("reqType");//?????????????????????/??????
		String stModuleOp = httpReqRes.getParameter("stModuleOp");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String typeId = httpReqRes.getParameter("typeId");
		String permission = httpReqRes.getParameter("permission");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		Conditions conds = Conditions.newAndConditions();
		if(reqType.equals("YH")){
			conds.add(new Condition("a.NM_DTYPE", Condition.OT_EQUAL,1));
		}else{
			conds.add(new Condition("a.NM_DTYPE", Condition.OT_EQUAL,0));
		}
		
		if (stProvinceName != null && !StringUtils.trim(stProvinceName).isEmpty() &&!stProvinceName.equals("null") && !"??????".equals(stProvinceName)) {
			Conditions opconds = Conditions.newAndConditions();
			opconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,stProvinceName));
			if(stProvinceName.equals("?????????")){
				List<String> idList =  infopubAreaDao.getSHIdList(opconds, null);
				conds.add(new Condition("a.ST_AREA_ID", Condition.OT_IN, idList));
			}else{
				List<String> idList =  infopubAreaDao.getOPIdList(opconds, null);
				conds.add(new Condition("a.ST_AREA_ID", Condition.OT_IN, idList));
			}
		}
		if (stCitytName != null && !StringUtils.trim(stCitytName).isEmpty() &&!stCitytName.equals("null") && !"??????".equals(stCitytName)) {
			Conditions opconds = Conditions.newAndConditions();
			opconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,stCitytName));
			List<String> idList =  infopubAreaDao.getSHIdList(opconds, null);
			conds.add(new Condition("a.ST_AREA_ID", Condition.OT_IN, idList));
		}
		if (stModuleOp != null && !StringUtils.trim(stModuleOp).isEmpty()) {
			conds.add(new Condition("a.ST_MODULE_OP", Condition.OT_LIKE,
					stModuleOp));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("a.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("a.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("a.ST_TYPE_ID", Condition.OT_EQUAL,typeId));
		}
		//??????mac???or?????? ????????????????????????????????????
		String suffix = "GROUP BY a.ST_MODULE_OP";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryAddressModuleOp(conds, suffix,pageSize,currentPage);
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(selmQueryHis.getStModuleOp());
			selmStatistics.setNmCount(new BigDecimal(selmQueryHis.getStExt1()));
			selmStatisticsList.add(selmStatistics);
		}
			
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryList, SelmQueryHis.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("draw", drawInt);
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", selmStatisticsList);
		
			return returnObj;
	}

	@Override
	public JSONObject visitComItem(HttpReqRes httpReqRes) {
		String stItemName = httpReqRes.getParameter("stItemName");
		int selmQueryCount = selmQueryHisDao.getSelmQueryCount(stItemName);//?????????
		int analyticsCount = analyticsVisitedDetailDao.getAnalyticsCount(stItemName);//?????????
		//System.out.println("?????????"+analyticsCount);
		//????????? = ????????? >= ????????? ??? ????????????1+20%??? ????????????
		analyticsCount = (int) (selmQueryCount > analyticsCount ? (selmQueryCount+selmQueryCount*0.2) : analyticsCount);
		//System.out.println("?????????"+analyticsCount+"??????"+selmQueryCount);
		// ?????????????????????????????????   
		NumberFormat numberFormat = NumberFormat.getInstance();   
		// ???????????????????????????2???   
		numberFormat.setMaximumFractionDigits(2);   
		String result = numberFormat.format((float)selmQueryCount/(float)analyticsCount*100);
		JSONObject returnObj = new JSONObject();
		returnObj.put("visitor", analyticsCount);
		returnObj.put("transaction", selmQueryCount);
		returnObj.put("data", result+"%");
		return returnObj;
		
	}

	@Override
	public JSONObject itemPeNumber(HttpReqRes httpReqRes) {
		String stItemName = httpReqRes.getParameter("stItemName");
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.itemPeopleNumber(stItemName);
		
		JSONObject jb1 = null;
		List<JSONObject> list1 = new ArrayList<JSONObject>();
		for(SelmQueryHis sqh : selmQueryList){
			jb1 = new JSONObject();
			jb1.put("value",sqh.getStDesc());
			jb1.put("name",sqh.getStIdentityNo());
			list1.add(jb1);	
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("itemName", stItemName);
		returnObj.put("data", list1);
		return returnObj;
	}

	@Override
	public JSONObject handleList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		
		String stMainName = httpReqRes.getParameter("stMainName");
		String startDate = httpReqRes.getParameter("startDate");
		
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName.trim()));
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		
		PaginationArrayList<SelmItem> itemList = selmItemDao.query(conds, suffix, pageSize, currentPage);
		int selmQueryCount = 0;//????????? 
		int analyticsCount = 0;//?????????
		for (SelmItem selmItem : itemList) {
			String stOrganId = selmItem.getStOrganId();
			String stExt1 = selmItem.getStExt1();
			if(stOrganId != null && !stOrganId.isEmpty() && !stExt1.equals("??????")){
				SmsOrgan smsOrgan = smsOrganDao.get(stOrganId);
				selmItem.setStOrganId(smsOrgan.getStOrganName());
			}
			String itemName1 = selmItem.getStMainName();
			if(startDate != null && !StringUtils.trim(startDate).isEmpty()){
				selmQueryCount = selmQueryHisDao.getSelmQueryCount(itemName1,startDate,endDate);//?????????	
			}else{
				selmQueryCount = selmQueryHisDao.getSelmQueryCount(itemName1);//?????????
			}
			if(endDate != null && !StringUtils.trim(endDate).isEmpty()){
				analyticsCount = analyticsVisitedDetailDao.getAnalyticsCount(itemName1,startDate,endDate);//?????????			
			}else{
				analyticsCount = analyticsVisitedDetailDao.getAnalyticsCount(itemName1);
			}
			//????????? = ????????? >= ????????? ??? ????????????1+20%??? ????????????
			analyticsCount = (int) (selmQueryCount > analyticsCount ? (selmQueryCount+selmQueryCount*0.2) : analyticsCount);
			selmItem.setStExt1(String.valueOf(selmQueryCount));//?????????
			selmItem.setStExt2(String.valueOf(analyticsCount));
		}
		
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(itemList,
							SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", itemList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", itemList);
		return returnObj;
	}

	@Override
	public JSONObject itemVisiter(HttpReqRes httpReqRes) {
		String vName = httpReqRes.getParameter("visiterName");
		Conditions conds = Conditions.newAndConditions();
		if (vName != null && !StringUtils.trim(vName).isEmpty()) {
			conds.add(new Condition("ST_NAME", Condition.OT_LIKE, vName.trim()));
		}	
		List<SelmItem> si = selmItemDao.query(null, null);
		/*String itemName = "";
		for(SelmItem emp : si){
			
		}*/
		
		String suffix1 = "GROUP BY ST_ITEM_NAME ORDER BY count(ST_ITEM_NAME) DESC";
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryRate(conds, suffix1);	
		JSONObject jb1 = null;
		List<JSONObject> list1 = new ArrayList<JSONObject>();
		int i = 1;
		for(SelmQueryHis sqh : selmQueryList){
			if(i > 20){
				break;
			}
			jb1 = new JSONObject();
			jb1.put("itemName",sqh.getStItemName());
			jb1.put("count",sqh.getStDesc());
			list1.add(jb1);	
			i++;
		}
		
		JSONObject returnObj = new JSONObject();
		
		
		double rate = 0; //??????
		int itemCount = 0; //????????????
		if (vName != null && !StringUtils.trim(vName).isEmpty()) {
			Conditions con1 = Conditions.newAndConditions();
			con1.add(new Condition("ST_NAME", Condition.OT_LIKE, vName.trim()));
			List<SelmQueryHis> selmQueryList1 = selmQueryHisDao.queryRate1(conds, "ORDER BY DT_CREATE desc");
			itemCount = selmQueryList1.size();
			Timestamp t1 = selmQueryList1.get(0).getDtCreate();
			Timestamp t2 = selmQueryList1.get(itemCount-1).getDtCreate();
			double day = ((t1.getTime() - t2.getTime())/(60*60*24*1000));
			double d1 = itemCount/day;
			BigDecimal bd = new BigDecimal(d1);
			rate = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
			//System.out.println(t1.getTime()+"---"+t2.getTime()+"---"+day+"--"+rate);

		}	

		returnObj.put("itemCount", itemCount);
		returnObj.put("rate", rate);
		returnObj.put("data", list1);
		return returnObj;
		
	}

	@Override
	public JSONObject selmSatisfactionInfoList(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		BigDecimal machine = selmSatisfactionInfoDao.getMachine();
		obj.put("machine", machine);
		BigDecimal app = selmSatisfactionInfoDao.getApp();
		obj.put("app", app);
		BigDecimal operation = selmSatisfactionInfoDao.getOperation();
		obj.put("operation", operation);
		BigDecimal screen = selmSatisfactionInfoDao.getScreen();
		obj.put("screen", screen);
		return obj;
	}

	@Override
	public JSONObject selmSatisfactionInfo(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		
		String suffix = "ORDER BY DT_EVALUATE_TIME DESC";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		
		PaginationArrayList<SelmSatisfactionInfo> selmSatisfactionInfoList = selmSatisfactionInfoDao.query(null, suffix, pageSize, currentPage);
		
		
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmSatisfactionInfoList,
							SelmSatisfactionInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmSatisfactionInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmSatisfactionInfoList);
		return returnObj;
	}

	@Override
	public JSONObject deviceModuleQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String mac = httpReqRes.getParameter("mac");//mac
		String stModuleOp = httpReqRes.getParameter("stModuleOp");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		Conditions conds = Conditions.newAndConditions();
		
		if (mac != null && !StringUtils.trim(mac).isEmpty()) {
			conds.add(new Condition("a.ST_MACHINE_ID", Condition.OT_EQUAL,
					mac));
		}
		if (stModuleOp != null && !StringUtils.trim(stModuleOp).isEmpty()) {
			conds.add(new Condition("a.ST_MODULE_OP", Condition.OT_LIKE,
					stModuleOp));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("a.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("a.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		//??????mac???or?????? ????????????????????????????????????
		String suffix = "GROUP BY a.ST_MODULE_OP";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryDeviceModuleOp(conds, suffix,pageSize,currentPage);
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName(selmQueryHis.getStModuleOp());
			selmStatistics.setNmCount(new BigDecimal(selmQueryHis.getStExt1()));
			selmStatisticsList.add(selmStatistics);
		}
			
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryList, SelmQueryHis.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("draw", drawInt);
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", selmStatisticsList);
		
			return returnObj;
	}

	@Override
	public JSONObject areaItemAmountQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stDistictName = httpReqRes.getParameter("stDistictName");//?????????????????????????????????
		String stStreetName = httpReqRes.getParameter("stStreetName");//?????????????????????????????????
		String reqType = httpReqRes.getParameter("reqType");//?????????????????????/??????
		String typeId = httpReqRes.getParameter("typeId");
		String itemName = httpReqRes.getParameter("itemName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String permission = httpReqRes.getParameter("permission");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		Conditions conds = Conditions.newAndConditions();
		if(reqType.equals("YH")){
			conds.add(new Condition("idt.NM_DTYPE", Condition.OT_EQUAL,1));
		}else{
			conds.add(new Condition("idt.NM_DTYPE", Condition.OT_EQUAL,0));
		}
		
		if (stDistictName != null && !StringUtils.trim(stDistictName).isEmpty() &&!stDistictName.equals("null") && !"??????".equals(stDistictName)) {
			conds.add(new Condition("ia.ST_DISTRICT", Condition.OT_EQUAL, stDistictName));
		}
		if (stStreetName != null && !StringUtils.trim(stStreetName).isEmpty() &&!stStreetName.equals("null") && !"??????".equals(stStreetName)) {
			conds.add(new Condition("ia.ST_STREET", Condition.OT_EQUAL, stStreetName));
		}
		if (itemName != null && !StringUtils.trim(itemName).isEmpty()) {
			conds.add(new Condition("sqh.ST_ITEM_NAME", Condition.OT_LIKE,itemName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL,typeId));
		}
		
		String suffix = "group by sqh.ST_ITEM_NAME order by COUNT(sqh.ST_ITEM_NAME) desc";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryAddressItemAmount(conds, suffix,pageSize,currentPage);
		
		
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryList, SelmQueryHis.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmQueryList);
	
		return returnObj;
	}
	
	@Override
	public JSONObject provinceItemAmountQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String stProvinceName = httpReqRes.getParameter("stProvinceName");//?????????????????????????????????
		String stCitytName = httpReqRes.getParameter("stCitytName");//?????????????????????????????????
		String reqType = httpReqRes.getParameter("reqType");//?????????????????????/??????
		String itemName = httpReqRes.getParameter("itemName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String typeId = httpReqRes.getParameter("typeId");
		String permission = httpReqRes.getParameter("permission");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		Conditions conds = Conditions.newAndConditions();
		if(reqType.equals("YH")){
			conds.add(new Condition("idt.NM_DTYPE", Condition.OT_EQUAL,1));
		}else{
			conds.add(new Condition("idt.NM_DTYPE", Condition.OT_EQUAL,0));
		}
		
		if (stProvinceName != null && !StringUtils.trim(stProvinceName).isEmpty() &&!stProvinceName.equals("null") && !"??????".equals(stProvinceName)) {
			Conditions opconds = Conditions.newAndConditions();
			opconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,stProvinceName));
			if(stProvinceName.equals("?????????")){
				List<String> idList =  infopubAreaDao.getSHIdList(opconds, null);
				conds.add(new Condition("idi.ST_AREA_ID", Condition.OT_IN, idList));
			}else{
				List<String> idList =  infopubAreaDao.getOPIdList(opconds, null);
				conds.add(new Condition("idi.ST_AREA_ID", Condition.OT_IN, idList));
			}
		}
		if (stCitytName != null && !StringUtils.trim(stCitytName).isEmpty() &&!stCitytName.equals("null") && !"??????".equals(stCitytName)) {
			Conditions opconds = Conditions.newAndConditions();
			opconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,stCitytName));
			List<String> idList =  infopubAreaDao.getSHIdList(opconds, null);
			conds.add(new Condition("idi.ST_AREA_ID", Condition.OT_IN, idList));
		}
		if (itemName != null && !StringUtils.trim(itemName).isEmpty()) {
			conds.add(new Condition("sqh.ST_ITEM_NAME", Condition.OT_LIKE,itemName));
		}
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
		conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL,
				Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL,typeId));
		}
		
		String suffix = "group by sqh.ST_ITEM_NAME order by COUNT(sqh.ST_ITEM_NAME) desc";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryAddressItemAmount(conds, suffix,pageSize,currentPage);
		
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryList, SelmQueryHis.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmQueryList);
	
		return returnObj;
	}

	@Override
	public JSONObject deviceItemAmountQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String mac = httpReqRes.getParameter("mac");
		String itemName = httpReqRes.getParameter("itemName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String permission = httpReqRes.getParameter("permission");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		Conditions conds = Conditions.newAndConditions();
		if(mac != null && !StringUtils.trim(mac).isEmpty()){
			conds.add(new Condition("sqh.ST_MACHINE_ID", Condition.OT_EQUAL,mac));
		}
		if (itemName != null && !StringUtils.trim(itemName).isEmpty()) {
			conds.add(new Condition("sqh.ST_ITEM_NAME", Condition.OT_LIKE,itemName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		
		String suffix = "group by sqh.ST_ITEM_NAME order by COUNT(sqh.ST_ITEM_NAME) desc";
		
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
		}
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.queryDeviceItemAmount(conds, suffix,pageSize,currentPage);
		
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryList, SelmQueryHis.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmQueryList);
	
		return returnObj;
	}

	
}
