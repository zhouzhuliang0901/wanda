package com.wondersgroup.selfapi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import reindeer.base.utils.NetDayRule;
import reindeer.base.utils.NetReservationRule;
import reindeer.base.utils.NetreservationDayTimeUtil;

import wfc.service.config.Config;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.NetReservationBlacklist;
import com.wondersgroup.selfapi.bean.NetReservationVo;
import com.wondersgroup.selfapi.bean.NetreservationDayTime;
import com.wondersgroup.selfapi.bean.ReservationResult;
import com.wondersgroup.selfapi.bean.ReservationVoPage;
import com.wondersgroup.selfapi.dao.NetReservationBlacklistDao;
import com.wondersgroup.selfapi.dao.NetReservationDao;
import com.wondersgroup.selfapi.dao.PlaceNetReservationDao;
import com.wondersgroup.selfapi.service.PlaceNetReservationService;
import com.wondersgroup.selfapi.util.CancelReservationForBlackUtil;
import com.wondersgroup.selfapi.util.SendCancelReservationMsg;


@Service
@Transactional
@WebService(endpointInterface = "com.wondersgroup.selfapi.service.PlaceNetReservationService", targetNamespace = "http://www.xuhui.gov.cn/placeNetReservationService", serviceName = "PlaceServiceInstance")
public class PlaceNetReservationServiceImpl implements PlaceNetReservationService{
	
	
	@Autowired
	private PlaceNetReservationDao placeNetReservationDao;
	@Autowired
	private NetReservationDao netReservationDao;
	@Autowired
	private SendCancelReservationMsg sendCancelReservationMsg;
	@Autowired
	private CancelReservationForBlackUtil cancelReservationForBlackUtil;
	@Autowired
	private NetReservationBlacklistDao netReservationBlacklistDao;
	
//	@Autowired
//	private SystemUserSessionManager sessionManager;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 根据事项编码查询可预约天数
	 */
//	@Override
//	public List<String> getPlaceReservationDays(String sessionId,
//			String itemNo, String placeId) {
//		User user = sessionManager.getUser(sessionId);
//		Log.debug("--登陆名--" + user.getName());
//		if (StringUtils.isEmpty(itemNo)) {
//			return null;
//		}
//		return getPlaceReservationDays(itemNo, placeId);
//	}
	/**
	 *根据事项编码查询可预约的天数
	 */
	@Override
	@WebMethod(exclude = true)
	public List<String> getPlaceReservationDays(String itemNo, String placeId) {
		// 用来存储预约的天数
				List<String> ReservationDay = new ArrayList<String>();
				NetReservationRule netReservationRule = placeNetReservationDao
						.getNetWeekRuleList(itemNo, placeId);
				if (netReservationRule == null) {
					Log.debug("预约规则没有设置," + "事项编码为:" + itemNo + "(办理点ID为：" + placeId
							+ ")");

					return null;
				}
				ReservationDay = getAllPlaceReservationDaysByItemNo(netReservationRule);
				return ReservationDay.size() > 0 ? ReservationDay : null;
			}
	
	/**
	 * 根据事项编码 获取可预约天数
	 * 
	 * @param i
	 * @param ReservationDay
	 * @return
	 */
	public List<String> getAllPlaceReservationDaysByItemNo(NetReservationRule i) {
		int stReservationEarlyDay = i.getStReservationEarlyDay().intValue();
		int stReservationDay = i.getStReservationDay().intValue() - 1;
		Date date = new Date();
		Date startDate = getDateAfter(date, stReservationEarlyDay);
		Date endDate = getDateAfter(startDate, stReservationDay);
		Log.debug("----开始预约时间---" + sdf.format(startDate));
		Log.debug("----结束预约时间---" + sdf.format(endDate));
		// 获取天规则预约表中的预约日期
		List<NetDayRule> allWorkDay = placeNetReservationDao.getWorkDay(
				startDate, endDate, i.getStItemRuleId());
		List<String> result = new ArrayList<String>();
		Log.debug("预约时间内的天预约日期：" + Arrays.toString(allWorkDay.toArray()));
		Log.debug("-获取按照周预约规则的中可预约日期-");
		List<String> allReservationDay = getDatePeriod(startDate, i);
		Log.debug("--预约日期内可预约的日期--"
				+ Arrays.toString(allReservationDay.toArray()));
		for (String temp : allReservationDay) {
			boolean flags = true;
			for (NetDayRule r : allWorkDay) {
				if (temp.equals(r.getStDay())) {
					if (r.getNmRule().intValue() == 1) {
						result.add(temp);
					} else if (r.getNmRule().intValue() == 0) {

					}
					flags = false;
				}
			}
			if (flags) {
				result.add(temp);
			}
		}
		for (NetDayRule r : allWorkDay) {
			if (!allReservationDay.contains(r.getStDay())) {
				if (r.getNmRule().intValue() == 1) {
					result.add(r.getStDay());
				}
			}
		}
		Collections.sort(result);
		Log.debug("事项可预约的天数结果：" + Arrays.toString(result.toArray()));
		return result;
	}
	
	/**
	 * 获取几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		// 将日期的部分加上增加的可预约的天数
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	
	/**
	 * 获取一段日期内的日期列表，获取这段日期内可以预约的日期（事项Id）
	 * 
	 * @param date
	 * @param days
	 * @param stSunday
	 * @param stMonday
	 * @param stTuesday
	 * @param stWednesday
	 * @param stThursday
	 * @param stFriday
	 * @param stSaturday
	 * @return
	 */
	private static List<String> getDatePeriod(Date date,
			NetReservationRule itemRule) {
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int stSunday = -1, stMonday = -1, stTuesday = -1, stWednesday = -1, stThursday = -1, stFriday = -1, stSaturday = -1;
		BigDecimal nmSunday = itemRule.getNmSunday();
		BigDecimal nmMonday = itemRule.getNmMonday();
		BigDecimal nmTuesday = itemRule.getNmTuesday();
		BigDecimal nmWednesday = itemRule.getNmWednesday();
		BigDecimal nmThursday = itemRule.getNmThursday();
		BigDecimal nmFriday = itemRule.getNmFriday();
		BigDecimal nmSaturday = itemRule.getNmSaturday();
		if (nmSunday != null)
			stSunday = nmSunday.intValue();
		if (nmMonday != null)
			stMonday = nmMonday.intValue();
		if (nmTuesday != null)
			stTuesday = nmTuesday.intValue();
		if (nmWednesday != null)
			stWednesday = nmWednesday.intValue();
		if (nmThursday != null)
			stThursday = nmThursday.intValue();
		if (nmFriday != null)
			stFriday = nmFriday.intValue();
		if (nmSaturday != null)
			stSaturday = nmSaturday.intValue();
		for (int i = 0; i < itemRule.getStReservationDay().intValue(); i++) {
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, i);
			boolean isWorkDay = isWorkDay(cal, stSunday, stMonday, stTuesday,
					stWednesday, stThursday, stFriday, stSaturday);
			if (isWorkDay) {
				list.add(sdf.format(cal.getTime()));
			}
		}
		return list;
	}
	
	/**
	 * 判断某一天是否可以预约
	 * 
	 * @param c
	 * @param stSunday
	 * @param stMonday
	 * @param stTuesday
	 * @param stWednesday
	 * @param stThursday
	 * @param stFriday
	 * @param stSaturday
	 * @return
	 */
	private static boolean isWorkDay(Calendar c, Integer stSunday,
			Integer stMonday, Integer stTuesday, Integer stWednesday,
			Integer stThursday, Integer stFriday, Integer stSaturday) {
		// 开始为默认不可以预约
		boolean flag = false;
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		// 1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
		if (1 == stSunday && 1 == weekday) {
			flag = true;
		} else if (1 == stMonday && 2 == weekday) {
			flag = true;
		} else if (1 == stTuesday && 3 == weekday) {
			flag = true;
		} else if (1 == stWednesday && 4 == weekday) {
			flag = true;
		} else if (1 == stThursday && 5 == weekday) {
			flag = true;
		} else if (1 == stFriday && 6 == weekday) {
			flag = true;
		} else if (1 == stSaturday && 7 == weekday) {
			flag = true;
		}
		return flag;

	}
	
	/**
	 * 获取事项的预约天数内具体的时间段(包括可预约的剩余数)
	 */
	@Override
	@WebMethod(exclude = true)
	public List<NetreservationDayTime> getPlaceReservationTimeAndCount(
			String date, String itemNo, String placeId) {
		List<NetreservationDayTime> netreservationDayTimes = 
				placeNetReservationDao.getPlaceReservationDayTimeAndCountByItemNo(itemNo,placeId,date);
		if(netreservationDayTimes == null){
			Log.debug("没有具体时间段的显示:"+"事项编码:"+itemNo+"办理地点:"+placeId+"日期:"+date);
		}
		//排序
		if(netreservationDayTimes !=null){
			netreservationDayTimes = NetreservationDayTimeUtil
					.get(netreservationDayTimes);
		}
		
		return netreservationDayTimes;
	}
	
	/**
	 * 保存预约
	 */
	@Override
	@WebMethod(exclude = true)
	public ReservationResult savePlaceReservationInfo(String itemNo,
			String placeId, String date, String detailId, String userId,
			String userName, String mobile, String identityType, String certNo,
			String reservationSource, String business, String unit,
			String unified) {
		Log.info("事项ID:" + itemNo + "办理点ID:" + placeId + "预约日期:" + date
				+ "时间详细主键:" + detailId + "用户ID:" + userId + "用户名:" + userName
				+ "手机号:" + mobile + "证件号:" + certNo + "证件类型:" + identityType
				+ "预约来源:" + reservationSource + "统一审批编码:" + business + "企业名称:"
				+ unit + "统一社会信用代码:" + unified);
		//判断预约用户是不是在黑名单中
		//0 为通过手机   1 为通过证件号
		Conditions conds = Conditions.newAndConditions();
		//从配置文件中读取配置
		String forBlack = Config.get("forBlack");
		//默认手机号
		if(forBlack == null){
			forBlack = "0";
		}
		if("0".equals(forBlack)){
			conds.add(new Condition(
					NetReservationBlacklist.ST_MOBILE,Condition.OT_EQUAL,mobile));
			conds.add(new Condition(
					NetReservationBlacklist.NM_STATUS,Condition.OT_EQUAL,1));
		}else{
			conds.add(new Condition(
					NetReservationBlacklist.ST_IDENTITY_NO,Condition.OT_EQUAL,certNo));
			conds.add(new Condition(
					NetReservationBlacklist.NM_STATUS,Condition.OT_EQUAL,1));
		}
		List<NetReservationBlacklist> list = netReservationBlacklistDao.query(conds,null);
		if(list.size()>0){
			ReservationResult result = new ReservationResult();
			result.setSuccess(false);
			result.setErrorCode(ReservationResult.ERROR_CODE_4);
			result.setValue(ReservationResult.ERROR_CODE_4_VALUE);
			return result;
		}
		return placeNetReservationDao.savePlaceReservationInfo(itemNo, placeId,
				date, detailId, userId, userName, mobile, identityType, certNo,
				reservationSource, business, unit, unified);
	}

	/**
	 * 
	 */
	@Override
	@WebMethod(exclude = true)
	public String cancelReservationByCertNoAndReNo(String certNo,
			String reservationNo) {
		// 取消预约
		String result = placeNetReservationDao.cancelReservationByCertNoAndReNo(certNo, reservationNo);
		// 取消预约成功
		if ("Y".equals(result)) {
			result = placeNetReservationDao.decreaseNmCountByCancelReservation(certNo, reservationNo);
			// 对接CA证书那边取消预约接口
			NetReservationVo n = findHistoryReservationByNo(reservationNo);
			if ("1".equals(Config.get("wfc.ca.iswantCancel"))) {
				if ("PT".equals(Config.get("wfc.ca.area"))) {
					if ("数字证书".equals(n.getStItemName())) {
						sendCancelReservationMsg.sendPTCancelMsg("", Config
								.get("wfc.ca.area"), reservationNo);
					}
				} else {
					if ("数字证书".equals(n.getStItemName())) {
						sendCancelReservationMsg.sendCancelMsg("", Config
								.get("wfc.ca.area"), reservationNo);
					}
				}
			}
			// 取消预约算一个爽约
			if ("1".equals(Config.get("isWantcancelResForBlack"))) {
				Log.debug("取消预约算一个爽约");
				cancelReservationForBlackUtil.CancelReservationForBlack(n);
			}
		}
		return result;
	}
	
	
	
	/**
	 * 根据预约号查询预约信息（已经预约但是未取号的预约信息）
	 */
	@Override
	@WebMethod(exclude = true)
	public NetReservationVo findReservationByNo(String reservationNo) {
		return netReservationDao.findReservationByNo(reservationNo);
	}
	
	
	
	
	/**
	 * 根据预约号查询预约信息
	 */
	@Override
	@WebMethod(exclude = true)
	public NetReservationVo findHistoryReservationByNo(String reservationNo) {
		return netReservationDao.findHistoryReservationByNo(reservationNo);
	}
	
	
	
	/**
	 * 根据身份证号获取预约列表（已经预约但是未取号的预约信息）
	 */
	@Override
	@WebMethod(exclude = true)
	public List<NetReservationVo> findReservationListByCertNo(String certNo) {
		return netReservationDao.findReservationListByCertNo(certNo);
	}
	
	
	
	/**
	 * 根据身份证号获取预约列表（取过号的和取消预约的预约信息）
	 */
	@Override
	@WebMethod(exclude = true)
	public ReservationVoPage findHistoryReservationListByCertNo(String certNo,
			int pageSize, int currentPage) {
		return netReservationDao.findHistoryReservationListByCertNo(certNo,
				pageSize, currentPage);
	}
		
}
