package com.wondersgroup.selfapi.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;

import com.wondersgroup.selfapi.bean.NetreservationDayTime;
import com.wondersgroup.selfapi.bean.PlaceNetreservationItem;
import com.wondersgroup.selfapi.bean.ReservationResult;

import reindeer.base.netwarnitem.until.NetWarnIngItemUtil;
import reindeer.base.utils.AciHelper;
import reindeer.base.utils.NetDayRule;
import reindeer.base.utils.NetReservationRule;

import reindeer.base.ws.smsServer.ServiceInstance;
import wfc.service.config.Config;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

@Repository
public class PlaceNetReservationDao {

	// @Autowired
	// private SmsService smsService;

	@Autowired
	private NetWarnIngItemUtil netWarnIngItemUtil;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	private Connection con = null;

	public PlaceNetReservationDao() {
	}

	public PlaceNetReservationDao(Connection con) {
		this.con = con;
	}

	public NetReservationRule getNetWeekRuleList(String itemNo, String placeId) {
		String sql = " select * from NET_ITEM_RESERVATION n join NET_RULE_LIST n2 on n.ST_LIST_ID = n2.ST_LIST_ID "
				+ " join NET_WEEK_RULE n1 on n2.ST_LIST_ID = n1.ST_LIST_ID where n.ST_ITEM_CODE = ? and n.ST_PLACE_ID = ? "
				+ " order by NM_WEEK_DAY ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { itemNo, placeId });
		} else {
			rs = SQL.execute(con, sql, new Object[] { itemNo, placeId });
		}
		NetReservationRule n = null;
		while (rs.next()) {
			if (n == null) {
				n = new NetReservationRule();
				n.setStReservationEarlyDay(rs
						.getBigDecimal("ST_RESERVATION_EARLY_DAY"));
				n.setStReservationDay(rs.getBigDecimal("ST_RESERVATION_DAY"));
				n.setStItemRuleId(rs.getOriginalString("ST_LIST_ID"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 2) {
				n.setNmMonday(rs.getBigDecimal("NM_RULE"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 3) {
				n.setNmTuesday(rs.getBigDecimal("NM_RULE"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 4) {
				n.setNmWednesday(rs.getBigDecimal("NM_RULE"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 5) {
				n.setNmThursday(rs.getBigDecimal("NM_RULE"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 6) {
				n.setNmFriday(rs.getBigDecimal("NM_RULE"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 7) {
				n.setNmSaturday(rs.getBigDecimal("NM_RULE"));
			}
			if (rs.getBigDecimal("NM_WEEK_DAY").intValue() == 1) {
				n.setNmSunday(rs.getBigDecimal("NM_RULE"));
			}

		}
		return n;
	}

	/**
	 * 获取天预约规则中的预约日期（包括可预约和不可预约）
	 */
	public List<NetDayRule> getWorkDay(Date startDate, Date endDate,
			String listId) {
		try {
			startDate = sdf.parse(sdf.format(startDate));
			endDate = sdf.parse(sdf.format(endDate));
		} catch (ParseException e) {
			Log.debug(e);
		}
		String sql = "SELECT * FROM NET_DAY_RULE WHERE DT_DAY >= ? AND DT_DAY <= ? AND ST_LIST_ID = ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql,
					new Object[] { new Timestamp(startDate.getTime()),
							new Timestamp(endDate.getTime()), listId });
		} else {
			rs = SQL.execute(con, sql,
					new Object[] { new Timestamp(startDate.getTime()),
							new Timestamp(endDate.getTime()), listId });
		}
		List<NetDayRule> list = new ArrayList<NetDayRule>();
		while (rs.next()) {
			NetDayRule n = new NetDayRule();
			n.setStDay(rs.getOriginalString("ST_DAY"));
			n.setNmRule(rs.getBigDecimal("NM_RULE"));
			list.add(n);
		}
		return list;
	}

	/**
	 * 根据事项编码、办理点ID 、所选的可以预约的天数获取具体时间段
	 * 
	 */
	public List<NetreservationDayTime> getPlaceReservationDayTimeAndCountByItemNo(
			String itemNo, String placeId, String date) {
		List<NetreservationDayTime> netreservationDayTimeAndCountList = new ArrayList<NetreservationDayTime>();
		// String sql = "select * from NET_DAY_RULE where ST_DAY = ? ";
		String sql = "SELECT * FROM NET_ITEM_RESERVATION n join NET_RULE_LIST n1 ON n.ST_LIST_ID = n1.ST_LIST_ID "
				+ "JOIN NET_DAY_RULE n2 ON n1.ST_LIST_ID = n2.ST_LIST_ID "
				+ "WHERE n2.ST_DAY = ? AND n.ST_ITEM_CODE = ? AND n.ST_PLACE_ID = ? ";
		RecordSet rs1;
		rs1 = SQL.execute(sql, new Object[] { date, itemNo, placeId });
		if (rs1.TOTAL_RECORD_COUNT > 0) {
			String sql1 = "SELECT * FROM NET_ITEM_RESERVATION n join NET_RULE_LIST n1 ON n.ST_LIST_ID = n1.ST_LIST_ID "
					+ "JOIN NET_DAY_RULE n2 ON n1.ST_LIST_ID = n2.ST_LIST_ID "
					+ "JOIN NET_RESERVATION_RULE_DETAIL n3 ON n2.ST_DAY_RULE_ID = n3.ST_RULE_ID "
					+ "JOIN NET_RESERVATION_COUNT n4 ON n3.ST_DETAIL_ID = n4.ST_DETAIL_ID "
					+ "WHERE n3.ST_TABLE = 'NET_DAY_RULE' AND n.ST_ITEM_CODE = ? AND n.ST_PLACE_ID = ? "
					+ "AND n2.ST_DAY = ? AND n4.DT_RESERVATION = ? ";
			Timestamp time = null;
			try {
				Date d = sdf.parse(date);
				time = new Timestamp(d.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			RecordSet rs2 = SQL.execute(sql1, new Object[] { itemNo, placeId,
					date, time });
			while (rs2.next()) {
				NetreservationDayTime netreservationDayTime = new NetreservationDayTime();
				netreservationDayTime.setStDetailId(rs2
						.getOriginalString("ST_DETAIL_ID"));
				netreservationDayTime.setStartTime(rs2
						.getOriginalString("ST_START_TIME"));
				netreservationDayTime.setEndTime(rs2
						.getOriginalString("ST_END_TIME"));
				netreservationDayTime.setStRuleId(rs2
						.getOriginalString("ST_RULE_ID"));
				netreservationDayTime.setStShow(rs2
						.getOriginalString("ST_SHOW"));
				BigDecimal nmTotalCount = rs2.getBigDecimal("NM_TOTAL_COUNT");
				if (nmTotalCount == null) {
					nmTotalCount = new BigDecimal(
							AciHelper.getReservationNmTotalCount());
				}
				netreservationDayTime.setTotalCount(nmTotalCount.intValue());
				netreservationDayTime
						.setSurplusCount(nmTotalCount.intValue()
								- rs2.getBigDecimal("NM_COUNT").intValue() >= 0 ? nmTotalCount
								.intValue()
								- rs2.getBigDecimal("NM_COUNT").intValue() : 0);
				netreservationDayTimeAndCountList.add(netreservationDayTime);
			}
		} else {
			String sql1 = "SELECT * FROM NET_ITEM_RESERVATION n join NET_RULE_LIST n1 ON n.ST_LIST_ID = n1.ST_LIST_ID "
					+ "JOIN NET_WEEK_RULE n2 ON n1.ST_LIST_ID = n2.ST_LIST_ID "
					+ "JOIN NET_RESERVATION_RULE_DETAIL n3 ON n2.ST_WEEK_RULE_ID = n3.ST_RULE_ID "
					+ "JOIN NET_RESERVATION_COUNT n4 ON n3.ST_DETAIL_ID = n4.ST_DETAIL_ID "
					+ "WHERE n3.ST_TABLE = 'NET_WEEK_RULE' AND n.ST_ITEM_CODE = ? AND n.ST_PLACE_ID = ? "
					+ "AND n2.NM_WEEK_DAY = ? AND n4.DT_RESERVATION = ? ";
			Timestamp time = null;
			int weekday = -1;
			try {
				Date d = sdf.parse(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				weekday = cal.get(Calendar.DAY_OF_WEEK);
				time = new Timestamp(d.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			RecordSet rs2 = SQL.execute(sql1, new Object[] { itemNo, placeId,
					weekday, time });
			while (rs2.next()) {
				NetreservationDayTime netreservationDayTime = new NetreservationDayTime();
				netreservationDayTime.setStDetailId(rs2
						.getOriginalString("ST_DETAIL_ID"));
				netreservationDayTime.setStartTime(rs2
						.getOriginalString("ST_START_TIME"));
				netreservationDayTime.setEndTime(rs2
						.getOriginalString("ST_END_TIME"));
				netreservationDayTime.setStRuleId(rs2
						.getOriginalString("ST_RULE_ID"));
				netreservationDayTime.setStShow(rs2
						.getOriginalString("ST_SHOW"));
				BigDecimal nmTotalCount = rs2.getBigDecimal("NM_TOTAL_COUNT");
				if (nmTotalCount == null) {
					nmTotalCount = new BigDecimal(
							AciHelper.getReservationNmTotalCount());
				}
				netreservationDayTime.setTotalCount(nmTotalCount.intValue());
				netreservationDayTime
						.setSurplusCount(nmTotalCount.intValue()
								- rs2.getBigDecimal("NM_COUNT").intValue() >= 0 ? nmTotalCount
								.intValue()
								- rs2.getBigDecimal("NM_COUNT").intValue() : 0);
				netreservationDayTimeAndCountList.add(netreservationDayTime);
			}

		}
		// 这个事项在可预约日期时间内（具体时间段内）没有人预约的情况
		if (netreservationDayTimeAndCountList.size() == 0) {
			return getPlaceReservationDayTimesByItemNo(itemNo, placeId, date);
		}
		// 这个事项在可预约日期时间内（具体时间段内）得到的具体的时间段少于这个事项全部的时间字段
		List<NetreservationDayTime> netreservationDayTimeList = getPlaceReservationDayTimesByItemNo(
				itemNo, placeId, date);
		if (netreservationDayTimeAndCountList.size() < netreservationDayTimeList
				.size()) {
			// 将查询到的数据放进map集合中
			Map<String, NetreservationDayTime> map = new HashMap<String, NetreservationDayTime>();
			for (NetreservationDayTime n : netreservationDayTimeAndCountList) {
				map.put(n.getStDetailId(), n);
			}
			// 将可预约日期中的某个时间段没有人预约的话也要将其显示出来
			for (NetreservationDayTime n : netreservationDayTimeList) {
				if (map.get(n.getStDetailId()) == null) {
					netreservationDayTimeAndCountList.add(n);
				}
			}
		}
		return netreservationDayTimeAndCountList;
	}

	/**
	 * 根据事项编码和办理点的ID获取具体时间段
	 */
	public List<NetreservationDayTime> getPlaceReservationDayTimesByItemNo(
			String itemNo, String placeId, String date) {
		List<NetreservationDayTime> netreservationDayTimeAndCountList = new ArrayList<NetreservationDayTime>();
		String sql = "SELECT * FROM NET_ITEM_RESERVATION n join NET_RULE_LIST n1 ON n.ST_LIST_ID = n1.ST_LIST_ID "
				+ "JOIN NET_DAY_RULE n2 ON n1.ST_LIST_ID = n2.ST_LIST_ID "
				+ "WHERE n2.ST_DAY = ? AND n.ST_ITEM_CODE = ? AND n.ST_PLACE_ID = ? ";
		RecordSet rs1;
		rs1 = SQL.execute(sql, new Object[] { date, itemNo, placeId });
		if (rs1.TOTAL_RECORD_COUNT > 0) {
			String sql1 = "SELECT * FROM NET_ITEM_RESERVATION n join NET_RULE_LIST n1 ON n.ST_LIST_ID = n1.ST_LIST_ID "
					+ "JOIN NET_DAY_RULE n2 ON n1.ST_LIST_ID = n2.ST_LIST_ID "
					+ "JOIN NET_RESERVATION_RULE_DETAIL n3 ON n2.ST_DAY_RULE_ID = n3.ST_RULE_ID "
					+ "WHERE n3.ST_TABLE = 'NET_DAY_RULE' AND n.ST_ITEM_CODE = ? AND n.ST_PLACE_ID = ? "
					+ "AND n2.ST_DAY = ? ";
			RecordSet rs2 = SQL.execute(sql1, new Object[] { itemNo, placeId,
					date });
			while (rs2.next()) {
				NetreservationDayTime netreservationDayTime = new NetreservationDayTime();
				netreservationDayTime.setStDetailId(rs2
						.getOriginalString("ST_DETAIL_ID"));
				netreservationDayTime.setStartTime(rs2
						.getOriginalString("ST_START_TIME"));
				netreservationDayTime.setEndTime(rs2
						.getOriginalString("ST_END_TIME"));
				netreservationDayTime.setStRuleId(rs2
						.getOriginalString("ST_RULE_ID"));
				netreservationDayTime.setStShow(rs2
						.getOriginalString("ST_SHOW"));
				BigDecimal nmTotalCount = rs2.getBigDecimal("NM_TOTAL_COUNT");
				if (nmTotalCount == null) {
					nmTotalCount = new BigDecimal(
							AciHelper.getReservationNmTotalCount());
				}
				netreservationDayTime.setTotalCount(nmTotalCount.intValue());
				// 将剩余可预约人数也设为总预约人数
				netreservationDayTime.setSurplusCount(nmTotalCount.intValue());
				netreservationDayTimeAndCountList.add(netreservationDayTime);
			}
		} else {
			int weekday = -1;
			try {
				Date d = sdf.parse(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				weekday = cal.get(Calendar.DAY_OF_WEEK);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			String sql1 = "SELECT * FROM NET_ITEM_RESERVATION n join NET_RULE_LIST n1 ON n.ST_LIST_ID = n1.ST_LIST_ID "
					+ "JOIN NET_WEEK_RULE n2 ON n1.ST_LIST_ID = n2.ST_LIST_ID "
					+ "JOIN NET_RESERVATION_RULE_DETAIL n3 ON n2.ST_WEEK_RULE_ID = n3.ST_RULE_ID "
					+ "WHERE n3.ST_TABLE = 'NET_WEEK_RULE' AND n.ST_ITEM_CODE = ? AND n.ST_PLACE_ID = ? "
					+ "AND n2.NM_WEEK_DAY = ? ";
			RecordSet rs2 = SQL.execute(sql1, new Object[] { itemNo, placeId,
					weekday });
			while (rs2.next()) {
				NetreservationDayTime netreservationDayTime = new NetreservationDayTime();
				netreservationDayTime.setStDetailId(rs2
						.getOriginalString("ST_DETAIL_ID"));
				netreservationDayTime.setStartTime(rs2
						.getOriginalString("ST_START_TIME"));
				netreservationDayTime.setEndTime(rs2
						.getOriginalString("ST_END_TIME"));
				netreservationDayTime.setStRuleId(rs2
						.getOriginalString("ST_RULE_ID"));
				netreservationDayTime.setStShow(rs2
						.getOriginalString("ST_SHOW"));
				BigDecimal nmTotalCount = rs2.getBigDecimal("NM_TOTAL_COUNT");
				if (nmTotalCount == null) {
					nmTotalCount = new BigDecimal(
							AciHelper.getReservationNmTotalCount());
				}
				netreservationDayTime.setTotalCount(nmTotalCount.intValue());
				// 将剩余可预约人数也设为总预约人数
				netreservationDayTime.setSurplusCount(nmTotalCount.intValue());
				netreservationDayTimeAndCountList.add(netreservationDayTime);
			}
		}
		return netreservationDayTimeAndCountList.size() > 0 ? netreservationDayTimeAndCountList
				: null;
	}

	/**
	 * 开始预约
	 * 
	 * @param itemNo
	 * @param placeId
	 * @param date
	 * @param detailId
	 * @param userId
	 * @param userName
	 * @param mobile
	 * @param identityType
	 *            证件类型
	 * @param certNo
	 * @param reservationSource
	 * @param business
	 *            统一审批编码
	 * @param unit
	 *            企业名称
	 * @param unified
	 *            统一社会信用代码
	 * @return
	 */
	public ReservationResult savePlaceReservationInfo(String itemNo,
			String placeId, String date, String detailId, String userId,
			String userName, String mobile, String identityType, String certNo,
			String reservationSource, String business, String unit,
			String unified) {
		ReservationResult result = new ReservationResult();
		// 如果输入的关键信息为空的话则显示预约失败请重新预约
		if (StringUtils.isEmpty(itemNo) || StringUtils.isEmpty(date)
				|| StringUtils.isEmpty(detailId) || StringUtils.isEmpty(certNo)
				|| StringUtils.isEmpty(placeId)) {
			result.setSuccess(false);
			result.setErrorCode(ReservationResult.ERROR_CODE_1);
			result.setValue(ReservationResult.ERROR_CODE_1_VALUE);
			return result;
		}
		// 预约信息表主键
		String reservationId = UUID.randomUUID().toString();
		// 预约号
		String reservationNo = autoNumber();
		result.setReservationNo(reservationNo);
		// 操作预约时间
		Timestamp dtOperation = new Timestamp(System.currentTimeMillis());
		Integer identityType1 = null;
		Integer reservationSource1 = null;

		try {
			// 证件号类型
			identityType1 = Integer.parseInt(identityType);
			// 预约来源
			reservationSource1 = Integer.parseInt(reservationSource);
		} catch (NumberFormatException e) {
			Log.debug(e);
			Log.debug("类型转换异常");
			result.setSuccess(false);
			result.setErrorCode(ReservationResult.ERROR_CODE_1);
			result.setValue(ReservationResult.ERROR_CODE_1_VALUE);
			return result;
		}
		// 判断事项是否可以预约
		PlaceNetreservationItem item = getPlaceNetreservationItemByNoAndPlaceId(
				itemNo, placeId);
		if (item == null) {
			result.setSuccess(false);
			result.setErrorCode(ReservationResult.ERROR_CODE_1);
			result.setValue(ReservationResult.ERROR_CODE_1_VALUE);
			return result;
		}
		// 通过时间详细Id获取具体时间段和显示的时间
		String dateSql = "SELECT ST_START_TIME,ST_END_TIME,ST_SHOW "
				+ "FROM NET_RESERVATION_RULE_DETAIL WHERE ST_DETAIL_ID = ? ";
		String startTime = "";
		String endTime = "";
		String stShow = "";
		RecordSet dateRs = SQL.execute(dateSql, new Object[] { detailId });
		if (dateRs.next()) {
			startTime = dateRs.getOriginalString("ST_START_TIME");
			endTime = dateRs.getOriginalString("ST_END_TIME");
			stShow = dateRs.getOriginalString("ST_SHOW");
		}
		// 详细时间段
		if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
			result.setSuccess(false);
			result.setErrorCode(ReservationResult.ERROR_CODE_1);
			result.setValue(ReservationResult.ERROR_CODE_1_VALUE);
			return result;
		}
		// 判断预约操作时间在可预约的时间段内 dtOperation当时预约的时间
		try {
			if (dtOperation.getTime() > sdfDate.parse(date + " " + endTime)
					.getTime()) {
				result.setSuccess(false);
				result.setErrorCode(ReservationResult.ERROR_CODE_1);
				result.setValue(ReservationResult.ERROR_CODE_1_VALUE);
				return result;
			}
		} catch (ParseException e3) {
			Log.debug("时间类型转换异常");
			Log.error(e3);
		}
		// 判断要预约的这个详细时间段内是否还可以在预约(剩余预约人数是否为0)
		Integer surplusCount = getPlaceSurplusCount(detailId, date);
		if (surplusCount <= 0) {
			result.setSuccess(false);
			result.setErrorCode(ReservationResult.ERROR_CODE_2);
			result.setValue(ReservationResult.ERROR_CODE_2_VALUE);
			return result;
		}
		// 通过事项编码获取事项的相关信息和部门的信息
		String itemSql = "SELECT DISTINCT w.ST_ITEM_ID , n.ST_ITEM_NAME,n.NM_ORGAN_NODE_ID,c.NAME,c.CODE ,w2.ST_GROUP_CODE,w2.NM_TOP  FROM NET_ITEM_RESERVATION n "
				+ " LEFT JOIN CS_ORGAN_NODE c ON n.NM_ORGAN_NODE_ID = c.ID "
				+ " LEFT JOIN WINDOW_ITEM_INFO w  ON n.ST_ITEM_CODE = w.ST_ITEM_NO "
				+ " LEFT JOIN WINDOW_GROUP_ITEM w1 on w.ST_ITEM_ID = w1.ST_ITEM_ID"
				+ " LEFT JOIN WINDOW_GROUP_INFO w2 on w1.ST_GROUP_ID = w2.ST_GROUP_ID "
				+ " WHERE n.ST_ITEM_CODE = ? ORDER BY w2.ST_GROUP_CODE ";
		RecordSet itemRs = SQL.execute(itemSql, new Object[] { itemNo });
		String organName = "";
		Integer organNodeId = null;
		String organCode = "";
		String itemName = "";
		String itemId = "";
		String groupCode = "";
		int count1 = 0;
		Log.debug("----查询到的总记录条数---" + itemRs.TOTAL_RECORD_COUNT);
		while (itemRs.next()) {
			count1++;
			BigDecimal nmTop = itemRs.getBigDecimal("NM_TOP");
			if (nmTop == null) {
				nmTop = new BigDecimal(0);
			}
			if (nmTop.intValue() == 1) {
				itemName = itemRs.getOriginalString("ST_ITEM_NAME");
				BigDecimal organNode = itemRs.getBigDecimal("NM_ORGAN_NODE_ID");
				if (organNode != null) {
					organNodeId = organNode.intValue();
				}
				// 部门名称
				organName = itemRs.getOriginalString("NAME");
				// 部门编码
				organCode = itemRs.getOriginalString("CODE");
				// 事项主键ID
				itemId = itemRs.getOriginalString("ST_ITEM_ID");
				// 组别code
				groupCode = itemRs.getOriginalString("ST_GROUP_CODE");
				break;
			}
			if (count1 == itemRs.TOTAL_RECORD_COUNT) {
				itemName = itemRs.getOriginalString("ST_ITEM_NAME");
				BigDecimal organNode = itemRs.getBigDecimal("NM_ORGAN_NODE_ID");
				if (organNode != null) {
					organNodeId = organNode.intValue();
				}
				// 部门名称
				organName = itemRs.getOriginalString("NAME");
				// 部门编码
				organCode = itemRs.getOriginalString("CODE");
				// 事项主键ID
				itemId = itemRs.getOriginalString("ST_ITEM_ID");
				// 组别code
				groupCode = itemRs.getOriginalString("ST_GROUP_CODE");
			}
		}
		Log.debug("----事项的名称---" + itemName);

		// 判断预约当天的最多预约2件 新的方法
		Conditions conds = Conditions.newAndConditions();
		String nowDate = sdf.format(new Date());
		try {
			Date d = sdfDate.parse(nowDate + " 23:59:59");
			conds.add(new Condition("DT_OPERATION", Condition.OT_LESS_EQUAL,
					new Timestamp(d.getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			Date d = sdfDate.parse(nowDate + " 00:00:00");
			conds.add(new Condition("DT_OPERATION", Condition.OT_GREATER_EQUAL,
					new Timestamp(d.getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		conds.add(new Condition("ST_IDENTITY_NO", Condition.OT_EQUAL, certNo));
		// 每天最多可以预约的事项数量
		RecordSet totalRs = SQL.query("NET_RESERVATION_INFO", "COUNT(*) count",
				conds, null);
		// 每天预约的某个事项最多可以预约的数量
		RecordSet totalItemRs = SQL.query("NET_RESERVATION_INFO",
				"COUNT(*) count", conds, null);
		// 判断预约当天最多预约事项的数量
		Integer count2 = 0;
		if (totalRs.next()) {
			count2 = totalRs.getBigDecimal("count").intValue();
			Log.debug(nowDate + "总共预约了" + count2 + "件办件");
			// 最大预约量
			String maxReservationCount = Config.get("maxReservationCount");
			if (StringUtils.isEmpty(maxReservationCount)) {
				maxReservationCount = "10";
			}
			if (count2 >= Integer.valueOf(maxReservationCount)) {
				result.setSuccess(false);
				result.setErrorCode(ReservationResult.ERROR_CODE_3);
				result.setValue(ReservationResult.ERROR_CODE_3_VALUE);
				return result;
			}
		}
		// 判断某天某个事项最多可预约的数量
		Integer count = 0;
		if (totalItemRs.next()) {
			count = totalItemRs.getBigDecimal("count").intValue();
			Log.debug(nowDate + "总共预约了事项编码" + itemNo + "---" + count + "件办件");
			// 最大预约量
			String maxItemReservationCount = Config
					.get("maxItemReservationCount");
			if (StringUtils.isEmpty(maxItemReservationCount)) {
				maxItemReservationCount = "2";
			}
			if (count >= Integer.valueOf(maxItemReservationCount)) {
				result.setSuccess(false);
				result.setErrorCode("5");
				result.setValue("此事项已达到当天的最大预约量");
				return result;
			}
		}
		// 判断一个人在预约的某天只能对一个事项预约一次
		Conditions conds1 = Conditions.newAndConditions();
		conds1.add(new Condition("ST_IDENTITY_NO", Condition.OT_EQUAL, certNo));
		conds1.add(new Condition("ST_ITEM_NO", Condition.OT_EQUAL, itemNo));
		try {
			Date endDate = sdfDate.parse(date + " 23:59:59");
			Date startDate = sdfDate.parse(date + " 00:00:00");
			conds1.add(new Condition("DT_RESERVATION_START",
					Condition.OT_GREATER_EQUAL, new Timestamp(startDate
							.getTime())));
			conds1.add(new Condition("DT_RESERVATION_START",
					Condition.OT_LESS_EQUAL, new Timestamp(endDate.getTime())));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		// 每天预约的某个事项最多可以预约的数量
		RecordSet totalItemResRs = SQL.query("NET_RESERVATION_INFO",
				"COUNT(*) count", conds1, null);
		// 判断预约当天某个事项最多可预约的数量
		Integer count3 = 0;
		if (totalItemResRs.next()) {
			count3 = totalItemResRs.getBigDecimal("count").intValue();
			Log.debug(date + "总共预约了事项编码" + itemNo + "--" + count3 + "件办件");
			// 最大预约量
			String maxItemReservationResDateCount = Config
					.get("maxItemReservationResDateCount");
			if (StringUtils.isEmpty(maxItemReservationResDateCount)) {
				maxItemReservationResDateCount = "500";
			}
			if (count3 >= Integer.valueOf(maxItemReservationResDateCount)) {
				result.setSuccess(false);
				result.setErrorCode("6");
				result.setValue("此事项已达到预约当天最大预约量");
				return result;
			}
		}
		// 新增代码
		Timestamp start = null;
		Timestamp end = null;
		String str = date + " " + startTime;
		String str1 = date + " " + endTime;
		try {
			Date d = sdfDate.parse(str);
			Date d1 = sdfDate.parse(str1);
			start = new Timestamp(d.getTime());
			end = new Timestamp(d1.getTime());
		} catch (ParseException e1) {
			Log.debug("日期格式转换失败");
			Log.debug(e1);
		}
		// 保存预约的信息
		String saveSql = "insert into NET_RESERVATION_INFO("
				+ "ST_RESERVATION_ID,ST_RESERVATION_NO,ST_ITEM_ID,ST_ITEM_NAME,ST_GROUP_CODE,"
				+ "DT_OPERATION,DT_RESERVATION_START,DT_RESERVATION_END,"
				+ "ST_USER_ID,ST_USER_NAME,ST_MOBILE,NM_IDENTITY_TYPE,ST_IDENTITY_NO,"
				+ "NM_REMOVED,ST_DETAIL_ID,NM_DATA_SOURCE,NM_ORGAN_NODE_ID,"
				+ "ST_ORGAN_NAME,ST_ORGAN_CODE,ST_SHOW,ST_EXT1,ST_EXT2,ST_BUSINESS_NO,"
				+ "ST_ITEM_NO,ST_HALL_INFO,ST_UNIT,ST_UNIFIED) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { reservationId, reservationNo, itemId, itemName,
				groupCode, dtOperation, start, end, userId, userName, mobile,
				// 数值类型
				identityType1, certNo, 0, detailId,
				// 数值类型
				reservationSource1,
				// 数值类型
				organNodeId, organName, organCode, stShow, null, null,
				business, itemNo, placeId, unit, unified };
		// 保存预约信息
		SQL.execute(saveSql, obj);
		// 新增代碼 已预约量+1
		Conditions cond = Conditions.newAndConditions();
		cond.add(new Condition("ST_DETAIL_ID", Condition.OT_EQUAL, detailId));
		// cond.add(new Condition("DT_RESERVATION",Condition.OT_EQUAL,date));
		try {
			Date d = sdf.parse(date);
			cond.add(new Condition("DT_RESERVATION", Condition.OT_EQUAL,
					new Timestamp(d.getTime())));
		} catch (ParseException e) {
			Log.debug("预约日期转化失败");
			Log.debug(e);
		}
		RecordSet r = SQL.query("NET_RESERVATION_COUNT", "ST_COUNT_ID", cond,
				null);
		String countId = "";
		if (r.next()) {
			countId = r.getOriginalString("ST_COUNT_ID");
		}
		if (r.TOTAL_RECORD_COUNT > 0) {
			String addsql = " UPDATE NET_RESERVATION_COUNT SET NM_COUNT = NM_COUNT + 1 "
					+ " WHERE ST_COUNT_ID = ? ";
			SQL.execute(addsql, new Object[] { countId });
		} else {
			// 已预约数量表中没有当天还没有人预约
			String insertSql = "insert into NET_RESERVATION_COUNT(ST_COUNT_ID,ST_DETAIL_ID,ST_ITEM_ID,ST_ITEM_NAME,NM_COUNT,"
					+ "DT_RESERVATION,ST_DESC,ST_EXT1,ST_EXT2) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String stCountId = UUID.randomUUID().toString();

			// 新增的代码
			Timestamp first = null;
			String str2 = date + " " + "00:00:00";
			try {
				Date d2 = sdfDate.parse(str2);
				first = new Timestamp(d2.getTime());
			} catch (ParseException e1) {
				Log.debug("日期格式转换失败");
				Log.debug(e1);
			}

			Object[] obj1 = { stCountId, detailId, itemId, itemName, 1, first,
					"", null, null };
			RecordSet insertRs = SQL.execute(insertSql, obj1);
			if (insertRs.TOTAL_RECORD_COUNT > 0) {
				Log.debug("新增预约成功（时间日期段内首次）");
			} else {
				Log.debug("新增预约失败（时间日期段内首次）");
			}
		}
		// 预约成功发送短信
		String hallAddress = getHallAddress(groupCode);
		String placeName = getPlaceName(placeId);
		try {
			String modle = Config.get("server.SmsService.module");
			String content = "";
			if ("jingan".equals(modle)) {
				// 静安
				// 预约成功短信内容
				content = "预约号:" + reservationNo + ",成功预约" + date + "("
						+ startTime.substring(0, 5) + "-"
						+ endTime.substring(0, 5) + ")《" + itemName
						+ "》，超过预约时段不保留号源，保留短信以资凭证，转发短信无效。";
				if (!"".equals(placeName)) {
					String hallName = "";
					if (!StringUtils.isEmpty(hallAddress)) {
						hallName = "(" + hallAddress + ")";
					}
					content = content + "请在预约时段内到" + placeName + hallName
							+ "取号办理。";
				}
			} else if ("putuo".equals(modle)) {
				// 普陀
				String hallName = "";
				if (!"".equals(placeName)) {
					if (!StringUtils.isEmpty(hallAddress)) {
						hallName = "(" + hallAddress + ")";
					}
				}
				content = "您的预约已成功，预约号:"
						+ reservationNo
						+ ",时间:"
						+ date
						+ "("
						+ stShow
						+ "),地址:"
						+ placeName
						+ hallName
						+ ",事项:《"
						+ itemName
						+ "》，办理时（请携带本人身份证）及相关办理材料按期办理。请保留短信，以资凭证，超过预约时段不保留号源，转发短信无效。";
			} else if ("pudong".equals(modle)) {
				// 浦东
				content = "您的预约已成功，预约号为:" + reservationNo + ",预约时间为:" + date
						+ stShow + "(" + startTime + "~" + endTime + "),预约地址为:"
						+ placeName + ",预约事项为:" + itemName + ",预约人身份证号:"
						+ certNo
						+ "。请确保预约信息与提交材料完全一致,否则无法办理业务,中心预约取号时间为8:45-16:30。";
				netWarnIngItemUtil.sendWarnItemMessage(itemNo, itemName, date,
						userName, mobile, reservationNo, unit, unified);
			} else {
				// 预约成功短信内容
				content = "预约号:" + reservationNo + ",成功预约" + date + "("
						+ startTime + "-" + endTime + ")《" + itemName
						+ "》,超过预约时段不保留号源。保留短信,以资凭证,转发短信无效。";
				if (!"".equals(placeName)) {
					String hallName = "";
					if (!StringUtils.isEmpty(hallAddress)) {
						hallName = "(" + hallAddress + ")";
					}
					content = content + "请按时到" + placeName + hallName
							+ "去办理相关事项。";
				}
			}
			Log.debug(content);
			if ("chenggong".equals(Config.get("server.SmsService.module"))) {
				/**
				 * 呈贡前置机外部接口调用模式：调用外部短信接口
				 */
				reindeer.base.ws.smsServer.SmsService server = new ServiceInstance()
						.getSmsServiceImplPort();
				// 登陆平台获得sessionId
				String sessionId = server.login(
						Config.get("server.SmsService.smsUser"),
						Config.get("server.SmsService.smsPassword"));
				// 发送短信
				server.sendMessage(sessionId, mobile, content);
				// 登出
				server.logoff(sessionId);
			} else {
				// // 通用模式：调用内部方法
				// smsService.sendMessage(mobile, content);
			}
		} catch (Exception e) {
			Log.debug(e);
			Log.debug("发送短信失败");
		}
		result.setSuccess(true);
		result.setErrorCode(ReservationResult.SUCCESS_CODE_0);
		result.setValue(reservationNo);
		return result;
	}

	/**
	 * 根据办理点的ID获取办理点的名称
	 * 
	 * @param groupCode
	 * @return
	 */
	// 根据组别的CODE获取办理事项的地点
	private String getPlaceName(String placeId) {
		String placeName = "";
		String sql = "SELECT ST_DEPARTNAME FROM ZHALL_PLACE_INFO WHERE ST_DEPARTID = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { placeId });
		if (rs.next()) {
			placeName = rs.getOriginalString("ST_DEPARTNAME");
		}
		return placeName;
	}

	/**
	 * 根据组别CODE获取办理大厅的地址
	 * 
	 * @param groupCode
	 * @return
	 */
	// 根据组别的CODE获取办理事项的地点
	private String getHallAddress(String groupCode) {
		String hallAddress = "";
		String sql = "SELECT w1.ST_FULL_NAME FROM WINDOW_GROUP_INFO w "
				+ " LEFT JOIN WINDOW_HALL w1 ON w.ST_HALL_ID = w1.ST_HALL_ID "
				+ "WHERE w.ST_GROUP_CODE = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { groupCode });
		if (rs.next()) {
			hallAddress = rs.getOriginalString("ST_FULL_NAME");
		}
		return hallAddress;
	}

	/**
	 * 根据预约时间详细ID、所选的可以预约的天数获取剩余预约人数
	 * 
	 * @param detailId
	 * @param date
	 * @return
	 */
	private Integer getPlaceSurplusCount(String detailId, String date) {
		int count = 0;
		// 方法1
		String tableName = " ( SELECT a.ST_DETAIL_ID,a.NM_TOTAL_COUNT,a.ST_SHOW,b.NM_COUNT,b.DT_RESERVATION "
				+ "FROM NET_RESERVATION_RULE_DETAIL a "
				+ "JOIN NET_RESERVATION_COUNT b ON a.ST_DETAIL_ID = b.ST_DETAIL_ID ) c ";
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DETAIL_ID", Condition.OT_EQUAL, detailId));
		// conds.add(new Condition("DT_RESERVATION",Condition.OT_EQUAL,date));

		try {
			Date d = sdf.parse(date);
			conds.add(new Condition("DT_RESERVATION", Condition.OT_EQUAL,
					new Timestamp(d.getTime())));
		} catch (ParseException e) {
			Log.debug("预约日期转化失败");
			Log.debug(e);
		}

		RecordSet rs;
		if (con == null) {
			rs = SQL.query(tableName, "*", conds, null);
		} else {
			rs = SQL.query(con, tableName, "*", conds, null);
		}
		while (rs.next()) {
			BigDecimal nmTotalCount = rs.getBigDecimal("NM_TOTAL_COUNT");
			if (nmTotalCount == null) {
				nmTotalCount = new BigDecimal(
						AciHelper.getReservationNmTotalCount());
			}
			count = nmTotalCount.intValue()
					- rs.getBigDecimal("NM_COUNT").intValue();
			return count >= 0 ? count : 0;
		}
		// 如果结果查询不到则说明 --预约天数时间段内还没有人预约--则显示最大可预约人数
		String querySql = "SELECT NM_TOTAL_COUNT FROM NET_RESERVATION_RULE_DETAIL WHERE ST_DETAIL_ID = ? ";
		RecordSet queryRs;
		queryRs = SQL.execute(querySql, new Object[] { detailId });
		while (queryRs.next()) {
			BigDecimal allTotalCount = queryRs.getBigDecimal("NM_TOTAL_COUNT");
			if (allTotalCount == null) {
				allTotalCount = new BigDecimal(
						AciHelper.getReservationNmTotalCount());
			}
			count = allTotalCount.intValue();
		}
		return count;
	}

	/**
	 * 根据事项编码和辦理點ID判断事项是否可以预约
	 * 
	 * @param itemNo
	 * @param placeId
	 * @return
	 */
	private PlaceNetreservationItem getPlaceNetreservationItemByNoAndPlaceId(
			String itemNo, String placeId) {
		String sql = " SELECT  *  FROM  NET_ITEM_RESERVATION  WHERE ST_ITEM_CODE = ?  AND  ST_PLACE_ID = ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { itemNo, placeId });
		} else {
			rs = SQL.execute(con, sql, new Object[] { itemNo, placeId });
		}
		PlaceNetreservationItem placeNetreservationItem = null;
		while (rs.next()) {
			// 通过事项名称可以查询到数据
			if (placeNetreservationItem == null) {
				placeNetreservationItem = new PlaceNetreservationItem();
				placeNetreservationItem.setItemName(rs
						.getOriginalString("ST_ITEM_NAME"));
				placeNetreservationItem.setItemNo(rs
						.getOriginalString("ST_ITEM_CODE"));
			}
		}
		return placeNetreservationItem;
	}

	/**
	 * 生成预约号 生成8位数字的预约号(5位随机数+3位随机日期数)
	 * 
	 * @return
	 */
	private String autoNumber() {
		Calendar calendar = Calendar.getInstance();
		String rd = String.valueOf(Math.random()).substring(2).substring(0, 5);
		String time = "" + calendar.getTime().getTime();
		String str = time.substring(10, time.length());
		String no = rd + str;
		String sql = "select st_reservation_no from NET_RESERVATION_INFO where st_reservation_no = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { no });
		if (rs.next()) {
			return autoNumber();
		}
		return no;
	}

	/**
	 * 方法描述：根据预约号和身份证号取消预约
	 * 
	 * @param certNo
	 * @param reservationNo
	 * @return
	 */
	public String cancelReservationByCertNoAndReNo(String certNo,
			String reservationNo) {
		if (StringUtils.isEmpty(certNo) || StringUtils.isEmpty(reservationNo)) {
			return "N";
		}
		Date d = new Date();
		Timestamp tomorrowTime = null;
		String cancelReLimitStr = Config.get("cancelReLimit");
		if (StringUtils.isEmpty(cancelReLimitStr)) {
			cancelReLimitStr = "1";
		}
		Integer cancelReLimit = Integer.valueOf(cancelReLimitStr);
		try {
			Date tomorrow = sdf.parse(sdf
					.format(getDateAfter(d, cancelReLimit)));
			tomorrowTime = new Timestamp(tomorrow.getTime());
		} catch (ParseException e) {

		}
		String sql = "UPDATE NET_RESERVATION_INFO SET NM_REMOVED = 2 WHERE ST_RESERVATION_NO = ? AND (ST_IDENTITY_NO = ? OR ST_MOBILE = ? ) AND NM_REMOVED = 0 AND DT_RESERVATION_START >= ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { reservationNo, certNo, certNo,
					tomorrowTime });
		} else {
			rs = SQL.execute(con, sql, new Object[] { reservationNo, certNo,
					certNo, tomorrowTime });
		}
		return rs.TOTAL_RECORD_COUNT > 0 ? "Y" : "N";
	}

	/**
	 * 方法描述：取消预约后减少某段时间详细段和可以预约日期内的已预约数量
	 * 
	 * @param certNo
	 * @param reservationNo
	 * @return
	 */
	public String decreaseNmCountByCancelReservation(String certNo,
			String reservationNo) {
		String searchSql = "SELECT ST_DETAIL_ID,DT_RESERVATION_START FROM NET_RESERVATION_INFO WHERE ST_RESERVATION_NO = ? AND ( ST_IDENTITY_NO = ? OR ST_MOBILE = ? ) ";
		RecordSet SearchRs = SQL.execute(searchSql, new Object[] {
				reservationNo, certNo, certNo });
		String detailId = "";
		String date = "";
		if (SearchRs.next()) {
			detailId = SearchRs.getOriginalString("ST_DETAIL_ID");
			Timestamp dateRs = SearchRs.getTimestamp("DT_RESERVATION_START");
			date = sdf.format(new Date(dateRs.getTime()));
		}
		String sql = " UPDATE NET_RESERVATION_COUNT SET NM_COUNT = NM_COUNT - 1 "
				+ " WHERE ST_DETAIL_ID = ? AND DT_RESERVATION = ? ";
		RecordSet decreaseRs;
		// 新增的代码
		Timestamp time = null;
		try {
			Date d = sdf.parse(date);
			time = new Timestamp(d.getTime());
		} catch (Exception e) {
			Log.debug("日期格式转换错误");
			Log.debug(e);
		}
		if (con == null) {
			decreaseRs = SQL.execute(sql, new Object[] { detailId, time });
		} else {
			decreaseRs = SQL.execute(con, sql, new Object[] { detailId, time });
		}
		Log.debug(decreaseRs.TOTAL_RECORD_COUNT);
		// 已预约量-1失败 返回"N"取消预约失败 默认取消成功
		String result = "Y";
		if (decreaseRs.TOTAL_RECORD_COUNT < 1) {
			result = "N";
		}
		// 如果已预约量-1 成功后发送取消预约短信
		String strSQL = "SELECT ST_ITEM_NAME,DT_RESERVATION_START,ST_MOBILE FROM NET_RESERVATION_INFO WHERE ST_RESERVATION_NO = ? and ( ST_IDENTITY_NO = ? OR ST_MOBILE = ? ) ";
		RecordSet rs = SQL.execute(strSQL, new Object[] { reservationNo,
				certNo, certNo });
		if (rs.next()) {
			String stItemName = rs.getOriginalString("ST_ITEM_NAME");
			String stMobile = rs.getOriginalString("ST_MOBILE");

			String modle = Config.get("server.SmsService.module");
			String content = "";
			if ("pudong".equals(modle)) {
				// 浦东短信
				content = "您的预约号为:" + reservationNo + "的预约,已成功取消预约。";
				// 发送短信 浦东
				// String sendSql = "insert into SMS_OUTBOX VALUES(?,?,?,?,?)";
				// RecordSet sendRs = SQL.execute(sendSql, new Object[] {
				// UUID.randomUUID().toString(), "", stMobile, content,
				// new Timestamp(System.currentTimeMillis()) });
				// if (sendRs.TOTAL_RECORD_COUNT > 0) {
				// Log.debug("插入取消预约短信内容成功");
				// }
			} else {
				content = "成功取消" + date + "(" + stItemName + ")。";
			}
			Log.debug(content);
			try {
				// 通用模式：内部调用
				// smsService.sendMessage(stMobile, content);

				/**
				 * 呈贡前置机外部接口调用模式模式调用短信接口
				 */
				reindeer.base.ws.smsServer.SmsService server = new ServiceInstance()
						.getSmsServiceImplPort();
				// 登陆平台获得sessionId
				String sessionId = server.login(
						Config.get("server.SmsService.username"),
						Config.get("server.SmsService.password"));
				// 发送短信
				server.sendMessage(sessionId, stMobile, content);
				// 登出
				server.logoff(sessionId);
			} catch (Exception e) {
				Log.debug(e);
				Log.debug("发送短信失败");
			}
		}
		return result;
	}

	/**
	 * 方法描述：获取几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		// 将日期的部分加上增加的可预约的天数
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

}
