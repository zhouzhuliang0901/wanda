package com.wondersgroup.infopub.task;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;

import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.bean.InfopubOnoff;
import com.wondersgroup.infopub.bean.SetInfopubDeviceOnOff;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDaoExt;
import com.wondersgroup.infopub.dao.InfopubDeviceLogDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.infopub.dao.InfopubOdeviceStatusDao;
import com.wondersgroup.infopub.util.InfopubUtils;
import com.wondersgroup.infopub.util.InfoputConst;
import com.wondersgroup.statistics.bean.AnalyticsVisitedDetail;
import com.wondersgroup.statistics.bean.SelmAreaQueryDay;
import com.wondersgroup.statistics.bean.SelmClientStatDay;
import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;
import com.wondersgroup.statistics.dao.AnalyticsVisitedDetailDao;
import com.wondersgroup.statistics.dao.SelmAreaQueryDayDao;
import com.wondersgroup.statistics.dao.SelmClientStatDayDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDayDao;
import com.wondersgroup.statistics.util.TimingUpdate;

import coral.base.app.AppContext;
import coral.base.quartz.Task;
import coral.base.quartz.TaskScheduled;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * CRON表达式    含义  "0 0 12 * * ?"    每天中午十二点触发  "0 15 10 ? * *"    每天早上10：15触发 
 * "0 15 10 * * ?"    每天早上10：15触发  "0 15 10 * * ? *"    每天早上10：15触发 
 * "0 15 10 * * ? 2005"    2005年的每天早上10：15触发  "0 * 14 * * ?"   
 * 每天从下午2点开始到2点59分每分钟一次触发  "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
 * "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发  "0 0-5 14 * * ?" 
 *   每天14:00至14:05每分钟一次触发  "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
 * "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 0 0 10,14,16 * * ?
 * 每天上午10点，下午2点，4点 0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时 0 0 12 ? * WED
 * 表示每个星期三中午12点  "0 0 12 * * ?" 每天中午12点触发  "0 15 10 ? * *" 每天上午10:15触发 
 * "0 15 10 * * ?" 每天上午10:15触发  "0 15 10 * * ? *" 每天上午10:15触发 
 * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发  "0 * 14 * * ?"
 * 在每天下午2点到下午2:59期间的每1分钟触发  "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
 * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发  "0 0-5 14 * * ?"
 * 在每天下午2点到下午2:05期间的每1分钟触发  "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
 * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发  "0 15 10 15 * ?" 每月15日上午10:15触发 
 * "0 15 10 L * ?" 每月最后一日的上午10:15触发  "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
 * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发  "0 15 10 ? * 6#3"
 * 每月的第三个星期五上午10:15触发
 */

//@Component
//@TaskScheduled(cron = "0 0 1 * * ?")
//@TaskScheduled(cron = "0 */1 * * * ?")
//@TaskScheduled(cron = "0 0/5 * * * ?")
// 设定时间
public class TaskSetDeviceInfoOnOff implements Task {

	private InfopubDeviceLogDao infopubDeviceLogDao;
	private SelmStatisticsDayDao selmStatisticsDayDao;
	private AnalyticsVisitedDetailDao analyticsVisitedDetailDao;
	private SelmStatisticsDao selmStatisticsDao;
	private SelmClientStatDayDao selmClientStatDayDao;
	private InfopubOdeviceStatusDao infopubOdeviceStatusDao;
	private InfopubDeviceInfoDaoExt infopubDeviceInfoDaoExt;
	private InfopubAddressDao infopubAddressDao;
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	private SelmQueryHisDao selmQueryHisDao;
	private InfopubAreaDao infopubAreaDao;
	private SelmAreaQueryDayDao selmAreaQueryDayDao;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 更新数据
		updateSelmAreaQueryDay();
		updateSelmStatisticsDay();
		updateSelmClientStatisticsDay();
		updateOdeviceClientStatisticsDay();
		updateOdeviceStatisticsDay();
		delOutDevice();
		
		updateSelmStatistics();

		infopubDeviceLogDao = (InfopubDeviceLogDao) AppContext
				.getBean("infopubDeviceLogDao");
		for (SetInfopubDeviceOnOff setInfopubDeviceOnOff : InfoputConst.deviceInfos) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String nowDate = dateFormat.format(timestamp).toString();
			String nowTime = timeFormat.format(timestamp).toString();

			InfopubDeviceInfoExt deviceInfoExt = setInfopubDeviceOnOff
					.getInfopubDeviceInfoExt();
			InfopubOnoff infopubOnoff = setInfopubDeviceOnOff.getInfopubOnoff();
			if (infopubOnoff != null && deviceInfoExt != null) {
				if ("DAY".equals(infopubOnoff.getStPtype())) {
					String dtOnoff = dateFormat.format(infopubOnoff
							.getDtOnoff());
					if (nowDate.equals(dtOnoff)) {
						if (nowTime.equals(infopubOnoff.getStOnTime())) {
							setPubInfoOn(deviceInfoExt);
						} else if (nowTime.equals(infopubOnoff.getStOffTime())) {
							setPubInfoOff(deviceInfoExt);
						}
					}
				} else if ("WEEK".equals(infopubOnoff.getStPtype())) {
					if (isWeekToSet(infopubOnoff.getStPeriod())) {
						if (nowTime.equals(infopubOnoff.getStOnTime())) {
							setPubInfoOn(deviceInfoExt);
						} else if (nowTime.equals(infopubOnoff.getStOffTime())) {
							setPubInfoOff(deviceInfoExt);
						}
					}
				}
			}
		}

	}

	/**
	 * 当前星期是否可以设置
	 * 
	 * @param week
	 * @return
	 */
	private boolean isWeekToSet(String week) {
		if (week == null || StringUtils.trimToEmpty(week).isEmpty()) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String[] days = week.split("");
		if ("1".equals(days[w])) {
			return true;
		}
		return false;
	}

	/**
	 * 设置设备开机
	 * 
	 * @param deviceInfo
	 * @param infopubOnoff
	 */
	private void setPubInfoOn(InfopubDeviceInfoExt deviceInfoExt) {
		if (deviceInfoExt != null) {
			InfopubDeviceLog deviceLog = new InfopubDeviceLog();
			String result = StringUtils.EMPTY;
			String action = StringUtils.EMPTY;
			if ("wakeup".equals(deviceInfoExt.getStShutdownType())) {
				result = InfopubUtils
						.sendWindowsOptMsg(deviceInfoExt, "wakeup");
				action = "开机";
			} else if ("openclosescrn"
					.equals(deviceInfoExt.getStShutdownType())) {
				result = InfopubUtils.sendOptMsg(deviceInfoExt, "openscrn");
				action = "开屏window";
			} else if ("turnonoff".equals(deviceInfoExt.getStShutdownType())) {
				result = InfopubUtils.sendOptMsg(deviceInfoExt, "turnon");
				action = "开屏android";
			} else {
				result = "error";
				action = "未知操作类型";
			}

			if ("success".equals(result)) {
				deviceLog.setStMsg("操作成功");
			} else {
				deviceLog.setStMsg(result);
				deviceLog.setStLevel("error");
			}
			deviceLog.setStDeviceId(deviceInfoExt.getStDeviceId());
			deviceLog.setStOperator("系统定时");
			deviceLog.setStOperand(deviceInfoExt.getStDeviceName());
			deviceLog.setStLocation(deviceInfoExt.getStDeviceAddress());
			deviceLog.setStAction(action);
			infopubDeviceLogDao.add(deviceLog);
		}
	}

	/**
	 * 设置设备关机
	 * 
	 * @param deviceInfo
	 * @param infopubOnoff
	 */
	private void setPubInfoOff(InfopubDeviceInfoExt deviceInfoExt) {
		if (deviceInfoExt != null) {
			InfopubDeviceLog deviceLog = new InfopubDeviceLog();
			String result = StringUtils.EMPTY;
			String action = StringUtils.EMPTY;
			if ("wakeup".equals(deviceInfoExt.getStShutdownType())) {
				result = InfopubUtils.sendOptMsg(deviceInfoExt, "shutdown");
				action = "开机";
			} else if ("openclosescrn"
					.equals(deviceInfoExt.getStShutdownType())) {
				result = InfopubUtils.sendOptMsg(deviceInfoExt, "closescrn");
				action = "关屏window";
			} else if ("turnonoff".equals(deviceInfoExt.getStShutdownType())) {
				result = InfopubUtils.sendOptMsg(deviceInfoExt, "turnoff");
				action = "关屏android";
			} else {
				action = "未知操作类型";
			}
			if ("success".equals(result)) {
				deviceLog.setStMsg("操作成功");
			} else {
				deviceLog.setStMsg(result);
				deviceLog.setStLevel("error");
			}
			deviceLog.setStDeviceId(deviceInfoExt.getStDeviceId());
			deviceLog.setStOperator("系统定时");
			deviceLog.setStOperand(deviceInfoExt.getStDeviceName());
			deviceLog.setStLocation(deviceInfoExt.getStDeviceAddress());
			deviceLog.setStAction(action);
			infopubDeviceLogDao.add(deviceLog);
		}
	}

	// 业务表更新
	public void updateSelmStatistics() {
		analyticsVisitedDetailDao = (AnalyticsVisitedDetailDao) AppContext
				.getBean("analyticsVisitedDetailDao");
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		// 得到所有数据的总点击量
		List<SelmStatistics> statisticsList = selmStatisticsDao.query(null,
				null);
		for (int i = 0; i < statisticsList.size(); i++) {
			SelmStatistics selmStatistics = statisticsList.get(i);
			BigDecimal nmOdevice = selmStatistics.getNmOdevice();
			String stStatisticsId = selmStatistics.getStStatisticsId();
			if (nmOdevice.intValue() == 1) {
				Conditions cond = Conditions.newAndConditions();
				cond.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE,
						stStatisticsId));
				List<SelmStatisticsDay> OdeviceStatisticsDayList = selmStatisticsDayDao
						.query(cond, null);
				// 成功总数
				int scount = 0;
				// 失败总数
				int fcount = 0;

				for (int j = 0; j < OdeviceStatisticsDayList.size(); j++) {
					SelmStatisticsDay selmStatisticsDay = OdeviceStatisticsDayList
							.get(j);

					BigDecimal nmFaild = selmStatisticsDay.getNmFaild();
					if (nmFaild != null) {
						fcount = fcount + nmFaild.intValue();
					}

					BigDecimal nmSuccess = selmStatisticsDay.getNmSuccess();
					if (nmSuccess != null) {
						scount = scount + nmSuccess.intValue();
					}
				}
				// 总数
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
				for (int j = 0; j < statisticsDayList.size(); j++) {
					SelmStatisticsDay selmStatisticsDay = statisticsDayList
							.get(j);
					BigDecimal nmCount = selmStatisticsDay.getNmCount();
					if (nmCount != null) {
						count = count + nmCount.intValue();
					}
				}
				selmStatistics.setNmCount(new BigDecimal(count));
				selmStatisticsDao.update(selmStatistics);
			}

		}
		System.out.println("执行完成1......");
	}

	// 按天业务表更新（）
	public void updateSelmStatisticsDay() {
		analyticsVisitedDetailDao = (AnalyticsVisitedDetailDao) AppContext
				.getBean("analyticsVisitedDetailDao");
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		selmStatisticsDayDao = (SelmStatisticsDayDao) AppContext
				.getBean("selmStatisticsDayDao");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -1);
		// 前一天的时间
		String date = simpleDateFormat.format(calendar.getTime());
		// 得到所有业务
		String suffix = "GROUP BY ST_NET_FLAG";
		List<AnalyticsVisitedDetail> list = analyticsVisitedDetailDao
				.queryStNetFlag(null, suffix);
		Iterator<AnalyticsVisitedDetail> iterator = list.iterator();
		while (iterator.hasNext()) {
			AnalyticsVisitedDetail analyticsVisitedDetail = iterator.next();
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStNetFlag(analyticsVisitedDetail.getStNetFlag());

			// StStatisticsId就是业务标识，业务标识不能重复
			selmStatistics.setStStatisticsId(analyticsVisitedDetail
					.getStNetFlag());
			// 根据业务去查询需要的数据（按天分类）
			List<SelmStatisticsDay> statisticsDayList = analyticsVisitedDetailDao
					.yesterdayCount(selmStatistics, date);
			Iterator<SelmStatisticsDay> statisticsDayIterator = statisticsDayList
					.iterator();
			while (statisticsDayIterator.hasNext()) {
				// 判断按天业务表中是否有该条数据
				SelmStatisticsDay statisticsDay = statisticsDayIterator.next();
				if (statisticsDay.getNmCount().intValue() != 0) {
					
					String stStatisticsId = statisticsDay.getStStatisticsId();
					String stDate = statisticsDay.getStDate();
					SelmStatisticsDay flag = selmStatisticsDayDao.get(
							stStatisticsId, stDate);
					// 判断是否存在
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
		}
		System.out.println("执行完成2......");
	}

	// 更新终端业务统计(按天)()
	public void updateSelmClientStatisticsDay() {
		analyticsVisitedDetailDao = (AnalyticsVisitedDetailDao) AppContext
				.getBean("analyticsVisitedDetailDao");
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		selmClientStatDayDao = (SelmClientStatDayDao) AppContext
				.getBean("selmClientStatDayDao");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, -1);
		// 前一天的时间
		String date = simpleDateFormat.format(calendar.getTime());
		// 得到所有业务
		String suffix = "GROUP BY ST_NET_FLAG";
		List<AnalyticsVisitedDetail> list = analyticsVisitedDetailDao
				.queryStNetFlag(null, suffix);
		Iterator<AnalyticsVisitedDetail> iterator = list.iterator();
		while (iterator.hasNext()) {
			AnalyticsVisitedDetail analyticsVisitedDetail = iterator.next();

			SelmStatistics selmStatistics = new SelmStatistics();

			selmStatistics.setStNetFlag(analyticsVisitedDetail.getStNetFlag());

			SelmStatisticsDay statisticsDay = new SelmStatisticsDay();

			statisticsDay.setStDate(date);
			// StStatisticsId就是业务标识，业务标识不能重复
			statisticsDay.setStStatisticsId(analyticsVisitedDetail
					.getStNetFlag());
			// 查询终端业务统计（按天）的数据
			List<SelmClientStatDay> selmClientStatDayList = analyticsVisitedDetailDao
					.clientCount(selmStatistics, statisticsDay);
			Iterator<SelmClientStatDay> clientStatDayIterator = selmClientStatDayList
					.iterator();
			while (clientStatDayIterator.hasNext()) {
				SelmClientStatDay selmClientStatDay = clientStatDayIterator
						.next();
				if (selmClientStatDay.getNmCount().intValue() != 0) {
					String stStatisticsId = selmClientStatDay.getStStatisticsId();
					String stDate = selmClientStatDay.getStDate();
					String stMachineId = selmClientStatDay.getStMachineId();
					// 查询是否存在该条数据
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
		System.out.println("执行完成3......");
	}

	// 更新外设统计
	public void updateOdeviceClientStatisticsDay() {
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		infopubOdeviceStatusDao = (InfopubOdeviceStatusDao) AppContext
				.getBean("infopubOdeviceStatusDao");
		infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
				.getBean("infopubDeviceInfoDaoExt");
		selmClientStatDayDao = (SelmClientStatDayDao) AppContext
				.getBean("selmClientStatDayDao");
		/*
		 * BigDecimal nmOdevice = new BigDecimal(1); Conditions conds =
		 * Conditions.newAndConditions(); conds.add(new Condition("NM_ODEVICE",
		 * Condition.OT_EQUAL, nmOdevice)); List<SelmStatistics> list =
		 * selmStatisticsDao.query(conds, null); for (int i = 0; i <
		 * list.size(); i++) { SelmStatistics selmStatistics = list.get(i);
		 * String stNetFlag = selmStatistics.getStNetFlag(); String
		 * stStatisticsId = selmStatistics.getStStatisticsId(); //String stName
		 * = selmStatistics.getStName(); Conditions cond =
		 * Conditions.newAndConditions(); cond.add(new
		 * Condition("ST_OUT_DEVICE_CODE", Condition.OT_EQUAL, stNetFlag));
		 * String suffix = "GROUP BY ST_DEVICE_ID"; // success //int scount = 0;
		 * // false //int fcount = 0;
		 */
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
			// 设备编号
			String stDeviceCode = deviceInfo.getStDeviceCode();
			// 成功
			int scount = 0;
			// 失败
			int fcount = 0;
			
			// 成功总次数
			BigDecimal nmHisStotal = infopubOdeviceStatus.getNmHisStotal();
			if (nmHisStotal != null) {
				scount = nmHisStotal.intValue();
			}
			// int stotal = nmHisStotal.intValue();
			// scount = stotal;
			// scount +=stotal;
			// 失败总次数
			BigDecimal nmHisFtotal = infopubOdeviceStatus.getNmHisFtotal();
			if (nmHisFtotal != null) {
				fcount = nmHisFtotal.intValue();
			}
			// int ftotal = nmHisFtotal.intValue();
			// fcount = ftotal ;
			// 得到前一天的时间
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1); // 得到前一天容
			Date date = calendar.getTime();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String stDate = df.format(date);
			SelmClientStatDay selmClientStatDay = new SelmClientStatDay();
			selmClientStatDay.setStDate(stDate);
			selmClientStatDay.setStStatisticsId(infopubOdeviceStatus
					.getStOutDeviceCode());
			if (scount != 0 || fcount != 0) {
				// 访问失败
				selmClientStatDay.setNmFaild(new BigDecimal(fcount));
				// 访问成功
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
		System.out.println("外设设备统计（按天）更新完成。。。。。");
	}

	// selmStatisticsDay (外设)
	public void updateOdeviceStatisticsDay() {
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		infopubOdeviceStatusDao = (InfopubOdeviceStatusDao) AppContext
				.getBean("infopubOdeviceStatusDao");
		infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
				.getBean("infopubDeviceInfoDaoExt");
		selmClientStatDayDao = (SelmClientStatDayDao) AppContext
				.getBean("selmClientStatDayDao");
		selmStatisticsDayDao = (SelmStatisticsDayDao) AppContext
				.getBean("selmStatisticsDayDao");
		// 得到前一天的时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天容
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String stDate = df.format(date);

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
			// 成功
			int scount = 0;
			// 失败
			int fcount = 0;
			for (int j = 0; j < odeviceClientStatDayList.size(); j++) {
				SelmClientStatDay selmClientStatDay = odeviceClientStatDayList
						.get(j);
				// 失败
				BigDecimal nmFaild = selmClientStatDay.getNmFaild();
				if (nmFaild != null) {
					// nmFaild.intValue();
					fcount = fcount + nmFaild.intValue();
				}

				// 成功
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
		System.out.println("外设统计（按天）更新完成。。。。。");
	}
	
	
	/**
	 * 清空外设表数据
	 */
	public void delOutDevice(){
		infopubOdeviceStatusDao = (InfopubOdeviceStatusDao) AppContext
				.getBean("infopubOdeviceStatusDao");
		List<InfopubOdeviceStatus> list = infopubOdeviceStatusDao.query(null, null);
		for (int i = 0; i < list.size(); i++) {
			InfopubOdeviceStatus infopubOdeviceStatus = list.get(i);
			infopubOdeviceStatus.setNmHisStotal(new BigDecimal(0));
			infopubOdeviceStatus.setNmHisFtotal(new BigDecimal(0));
			infopubOdeviceStatusDao.update(infopubOdeviceStatus);
		}
		
	}
	
	
	// 按天业务表更新（）
		public void updateSelmAreaQueryDay() {
			infopubAddressDao = (InfopubAddressDao) AppContext
					.getBean("infopubAddressDao");
			infopubDeviceInfoDao = (InfopubDeviceInfoDao) AppContext
					.getBean("infopubDeviceInfoDao");
			infopubDeviceTypeDao = (InfopubDeviceTypeDao) AppContext
					.getBean("infopubDeviceTypeDao");
			selmQueryHisDao = (SelmQueryHisDao) AppContext
					.getBean("selmQueryHisDao");
			infopubAreaDao = (InfopubAreaDao) AppContext
					.getBean("infopubAreaDao");
			selmAreaQueryDayDao = (SelmAreaQueryDayDao) AppContext
					.getBean("selmAreaQueryDayDao");
			
			//获取当前日期的前一天
			//Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//String format = formatter.format(date);
			Calendar calendar = Calendar.getInstance();    
	        calendar.add(Calendar.DATE, -1);
	        String format =  formatter.format(calendar.getTime());
			
	        Conditions condaddress = Conditions.newAndConditions();
			Conditions condaddressName = Conditions.newAndConditions();
			List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
			List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
				//查询全部区域并去重
			String suffix="GROUP BY ST_DISTRICT";
			addressListName = infopubAddressDao.queryName(condaddressName, suffix);
			//List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
			for (InfopubAddress infopubAddress : addressListName) {
				condaddress= Conditions.newAndConditions();
				condaddress.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubAddress.getStDistrict()));
				 addressList = infopubAddressDao.query(
						condaddress, null);
				int typeSum =0;
				int typeSum1 =0; 
				int selmSum =0; 
				for (InfopubAddress infopubAddress2 : addressList) {
					String stAdderssId = infopubAddress2.getStAddressId();
					Conditions cond2 = Conditions.newAndConditions();
					cond2.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAdderssId));
					List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
							cond2, null);
					//政务终端数量
					int typeCount = 0;
					for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
						String stTypeId = infopubDeviceInfo.getStTypeId();
						int suCount = infopubDeviceTypeDao.suCount(stTypeId, 0);
						typeCount = typeCount + suCount;
					}
					typeSum = typeSum + typeCount;
					
					//社会化终端数量
					int typeCount1 = 0;
					for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
						String stTypeId = infopubDeviceInfo.getStTypeId();
						int suCount = infopubDeviceTypeDao.suCount(stTypeId, 1);
						typeCount1 = typeCount1 + suCount;
					}
					typeSum1 = typeSum1 + typeCount1;
					//办件量
					int selmCount = 0;
					for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
						String stDeviceCode = infopubDeviceInfo.getStDeviceMac();
						 int selmNum = selmQueryHisDao
									.getAreaSelmQueryTime(stDeviceCode,format);
						 selmCount = selmCount + selmNum;
					}
					selmSum = selmSum + selmCount;
					
				}
				SelmAreaQueryDay newSelmAreaQueryDay  = new SelmAreaQueryDay();
				InfopubArea areaId = infopubAreaDao.getName(infopubAddress.getStDistrict());
				newSelmAreaQueryDay.setStAreaId(areaId.getStAreaId());
				newSelmAreaQueryDay.setStAreaName(infopubAddress.getStDistrict());
				newSelmAreaQueryDay.setStDay(format);
				newSelmAreaQueryDay.setNmGovNumber(new BigDecimal(typeSum));
				newSelmAreaQueryDay.setNmSocialNumber(new BigDecimal(typeSum1));
				newSelmAreaQueryDay.setNmDay(new BigDecimal(selmSum));
				selmAreaQueryDayDao.add(newSelmAreaQueryDay);
			}
			System.out.println("区域日办件统计表更新完成");
		}
}
