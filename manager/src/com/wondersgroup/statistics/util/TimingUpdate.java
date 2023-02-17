package com.wondersgroup.statistics.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDaoExt;
import com.wondersgroup.infopub.dao.InfopubOdeviceStatusDao;
import com.wondersgroup.statistics.bean.SelmClientStatDay;
import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;
import com.wondersgroup.statistics.dao.AnalyticsVisitedDetailDao;
import com.wondersgroup.statistics.dao.SelmClientStatDayDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDao;
import com.wondersgroup.statistics.dao.SelmStatisticsDayDao;

import coral.base.app.AppContext;

@Controller
public class TimingUpdate {

	@Autowired
	private SelmStatisticsDayDao selmStatisticsDayDao;
	@Autowired
	private AnalyticsVisitedDetailDao analyticsVisitedDetailDao;
	@Autowired
	private SelmStatisticsDao selmStatisticsDao;
	@Autowired
	private SelmClientStatDayDao selmClientStatDayDao;
	
	
	//更新终端业务统计(按天)(最后更新)
	@RequestMapping("/statistics/util/updateSelmClientStatDay.do")
	public void updateSelmClientStatisticsDay() {
		// 得到业务表中存在的业务
		List<SelmStatistics> statisticsList = selmStatisticsDao.query(null,
				null);
		Iterator<SelmStatistics> statisticsIterator = statisticsList.iterator();
		while (statisticsIterator.hasNext()) {
			SelmStatistics selmStatistics = statisticsIterator.next();
			// 根据业务去查询需要的数据（按天分类）
			List<SelmStatisticsDay> statisticsDayList = analyticsVisitedDetailDao
					.count(selmStatistics);
			Iterator<SelmStatisticsDay> statisticsDayIterator = statisticsDayList
					.iterator();
			while (statisticsDayIterator.hasNext()) {
				SelmStatisticsDay statisticsDay = statisticsDayIterator.next();
				// 查询终端业务统计（按天）的数据
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
		System.out.println("执行完成......");
	}
	
	
	//按天业务表更新（再更新）
	@RequestMapping("/statistics/util/updateSelmStatisticsDay.do")
	public void updateSelmStatisticsDay() {
		//得到业务表中存在的业务
		List<SelmStatistics> statisticsList = selmStatisticsDao.query(null, null);
		Iterator<SelmStatistics> statisticsIterator = statisticsList.iterator();
		while (statisticsIterator.hasNext()) {
			SelmStatistics selmStatistics = statisticsIterator.next();
			//根据业务去查询需要的数据（按天分类）
			List<SelmStatisticsDay> statisticsDayList = analyticsVisitedDetailDao
					.count(selmStatistics);
			Iterator<SelmStatisticsDay> statisticsDayIterator = statisticsDayList.iterator();
			while (statisticsDayIterator.hasNext()) {
				//判断按天业务表中是否有该条数据
				SelmStatisticsDay statisticsDay = statisticsDayIterator.next();
				String stStatisticsId = statisticsDay.getStStatisticsId();
				String stDate = statisticsDay.getStDate();
				SelmStatisticsDay flag = selmStatisticsDayDao.get(
						stStatisticsId, stDate);
				// 判断是否存在
				if (flag == null) {
					selmStatisticsDayDao.add(statisticsDay);
				} else {
					/*statisticsDay.setDtTime(new Timestamp(System
							.currentTimeMillis()));*/
					selmStatisticsDayDao.update(statisticsDay);
				}
			}
		}
		System.out.println("执行完成......");
	}
	
	//业务表更新(先更新)
	@RequestMapping("/statistics/util/updateSelmStatistics.do")
	public void updateSelmStatistics(){
		//得到所有数据的总点击量
		List<SelmStatistics> list = analyticsVisitedDetailDao.query();
		Iterator<SelmStatistics> ListIterator = list.iterator();
		while (ListIterator.hasNext()) {
			SelmStatistics selmStatistics = ListIterator.next();
			String stNetFlag = selmStatistics.getStNetFlag();
			/*Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_NET_FLAG", Condition.OT_LIKE, stNetFlag);*/
			// 根据业务标识在业务表查找数据，看看业务表中是否存在
			SelmStatistics flag = selmStatisticsDao.getBystNetFlag(stNetFlag);
			if (flag == null) {
				selmStatistics.setStStatisticsId(UUID.randomUUID().toString());
				selmStatisticsDao.add(selmStatistics);
			}else {
				selmStatisticsDao.update(selmStatistics);
			}
		}
		
		System.out.println("执行完成......");
	}
	
	
	public void updateSelmClientStatDay(){
		
	}
	
	
	
	@Autowired
	private InfopubOdeviceStatusDao infopubOdeviceStatusDao;
	@Autowired
	private InfopubDeviceInfoDaoExt infopubDeviceInfoDaoExt;
	
	
	
	// 更新外设统计
	public void updateOdeviceClientStatisticsDay(){
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		infopubOdeviceStatusDao = (InfopubOdeviceStatusDao) AppContext
				.getBean("infopubOdeviceStatusDao");
		infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
				.getBean("infopubDeviceInfoDaoExt");
		selmClientStatDayDao = (SelmClientStatDayDao) AppContext
				.getBean("selmClientStatDayDao");
		/*BigDecimal nmOdevice = new BigDecimal(1);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("NM_ODEVICE", Condition.OT_EQUAL, nmOdevice));
		List<SelmStatistics> list = selmStatisticsDao.query(conds, null);
		for (int i = 0; i < list.size(); i++) {
			SelmStatistics selmStatistics = list.get(i);
			String stNetFlag = selmStatistics.getStNetFlag();
			String stStatisticsId = selmStatistics.getStStatisticsId();
			//String stName = selmStatistics.getStName();
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_OUT_DEVICE_CODE", Condition.OT_EQUAL, stNetFlag));
			String suffix = "GROUP BY ST_DEVICE_ID";
			// success
			//int scount = 0;
			// false
			//int fcount = 0;
			*/
			List<InfopubOdeviceStatus> odeviceList = infopubOdeviceStatusDao.query(null, null);
			if (odeviceList.size() > 0) {
				for (InfopubOdeviceStatus infopubOdeviceStatus : odeviceList) {
					String stDeviceMac = infopubOdeviceStatus.getStDeviceId();
					InfopubDeviceInfo deviceInfo = infopubDeviceInfoDaoExt.getByMacId(stDeviceMac);
					if (deviceInfo == null) {
						continue;
					}
					// 设备编号
					String stDeviceCode = deviceInfo.getStDeviceCode();
					// 成功总次数
					BigDecimal nmHisStotal = infopubOdeviceStatus.getNmHisStotal();
					//int stotal = nmHisStotal.intValue();
					//scount = stotal;
					//scount +=stotal;
					// 失败总次数
					BigDecimal nmHisFtotal = infopubOdeviceStatus.getNmHisFtotal();
					//int ftotal = nmHisFtotal.intValue();
					//fcount = ftotal ;
					// 得到前一天的时间
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -1); //得到前一天容
					Date date = calendar.getTime();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String stDate = df.format(date);
					SelmClientStatDay selmClientStatDay = new SelmClientStatDay();
					selmClientStatDay.setStDate(stDate);
					selmClientStatDay.setStStatisticsId(infopubOdeviceStatus.getStOutDeviceCode());
					// 访问失败
					selmClientStatDay.setNmFaild(nmHisFtotal);
					// 访问成功
					selmClientStatDay.setNmSuccess(nmHisStotal);
					selmClientStatDay.setStMachineId(stDeviceCode);
					selmClientStatDay.setDtTime(new Timestamp(System
							.currentTimeMillis()));
					selmClientStatDayDao.add(selmClientStatDay);
				}
		}
	}
	
	// selmStatisticsDay (外设)
	public void updateOdeviceStatisticsDay(){
		selmStatisticsDao = (SelmStatisticsDao) AppContext
				.getBean("selmStatisticsDao");
		infopubOdeviceStatusDao = (InfopubOdeviceStatusDao) AppContext
				.getBean("infopubOdeviceStatusDao");
		infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
				.getBean("infopubDeviceInfoDaoExt");
		/*infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
				.getBean("infopubDeviceInfoDaoExt");*/
		selmClientStatDayDao = (SelmClientStatDayDao) AppContext
				.getBean("selmClientStatDayDao");
		selmStatisticsDayDao = (SelmStatisticsDayDao) AppContext
				.getBean("selmStatisticsDayDao");
		// 得到前一天的时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天容
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String stDate = df.format(date);
		
		/*BigDecimal nmOdevice = new BigDecimal(1);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("NM_ODEVICE", Condition.OT_EQUAL, nmOdevice));
		List<SelmStatistics> list = selmStatisticsDao.query(conds, null);
		for (int i = 0; i < list.size(); i++) {
			SelmStatistics selmStatistics = list.get(i);
			String stStatisticsId = selmStatistics.getStStatisticsId();
			
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE, stStatisticsId));
			cond.add(new Condition("ST_DATE", Condition.OT_LIKE, stDate));
			//cond.add(new Condition("NM_COUNT", Condition.OT_EQUAL, null));
			//String suffix = "GROUP BY ST_DATE" ;
			*/
		String str = "GROUP BY ST_DEVICE_ID";
		List<InfopubOdeviceStatus> odeviceList = infopubOdeviceStatusDao.query(null, str);
		for (int i = 0; i < odeviceList.size(); i++) {
			InfopubOdeviceStatus infopubOdeviceStatus = odeviceList.get(i);
			String stOutDeviceCode = infopubOdeviceStatus.getStOutDeviceCode();
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE, stOutDeviceCode));
			cond.add(new Condition("ST_DATE", Condition.OT_LIKE, stDate));
			//cond.add(new Condition("NM_COUNT", Condition.OT_EQUAL, null));
			//String suffix = "GROUP BY ST_DATE" ;
			List<SelmClientStatDay> odeviceClientStatDayList = selmClientStatDayDao.query(cond, null);
			// 成功
			int scount = 0;
			// 失败
			int fcount = 0;
			for (int j = 0; j < odeviceClientStatDayList.size(); j++) {
				SelmClientStatDay selmClientStatDay = odeviceClientStatDayList.get(j);
				// 失败
				BigDecimal nmFaild = selmClientStatDay.getNmFaild();
				//nmFaild.intValue();	
				fcount = fcount + nmFaild.intValue();
				
				// 成功
				BigDecimal nmSuccess = selmClientStatDay.getNmSuccess();
				scount = scount + nmSuccess.intValue();
			}
			if (scount != 0 || fcount != 0) {
				SelmStatisticsDay selmStatisticsDay = new SelmStatisticsDay();
				selmStatisticsDay.setStDate(stDate);
				selmStatisticsDay.setNmFaild(new BigDecimal(fcount));
				selmStatisticsDay.setNmSuccess(new BigDecimal(scount));
				selmStatisticsDay.setStStatisticsId(stOutDeviceCode);
				selmStatisticsDay.setDtTime(new Timestamp(System
						.currentTimeMillis()));
				selmStatisticsDayDao.add(selmStatisticsDay);
			}
		}

	}
	
	
	/*// selmStatistics(外设)
	public void updateOdeviceStatistics(){
			selmStatisticsDao = (SelmStatisticsDao) AppContext
					.getBean("selmStatisticsDao");
			infopubOdeviceStatusDao = (InfopubOdeviceStatusDao) AppContext
					.getBean("infopubOdeviceStatusDao");
			infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
					.getBean("infopubDeviceInfoDaoExt");
			infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
					.getBean("infopubDeviceInfoDaoExt");
			selmClientStatDayDao = (SelmClientStatDayDao) AppContext
					.getBean("selmClientStatDayDao");
			selmStatisticsDayDao = (SelmStatisticsDayDao) AppContext
					.getBean("selmStatisticsDayDao");
			BigDecimal nmOdevice = new BigDecimal(1);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("NM_ODEVICE", Condition.OT_EQUAL, nmOdevice));
			List<SelmStatistics> list = selmStatisticsDao.query(conds, null);
			for (int i = 0; i < list.size(); i++) {
				SelmStatistics selmStatistics = list.get(i);
				String stStatisticsId = selmStatistics.getStStatisticsId();
				//String stName = selmStatistics.getStName();
				//String stNetFlag = selmStatistics.getStNetFlag();
				Conditions cond = Conditions.newAndConditions();
				cond.add(new Condition("ST_STATISTICS_ID", Condition.OT_LIKE, stStatisticsId));
				List<SelmStatisticsDay> OdeviceStatisticsDayList = selmStatisticsDayDao.query(cond, null);
				// 成功总数
				int scount = 0;
				// 失败总数
				int fcount = 0;
				
				for (int j = 0; j < OdeviceStatisticsDayList.size(); j++) {
					SelmStatisticsDay selmStatisticsDay = OdeviceStatisticsDayList.get(j);
					
					BigDecimal nmFaild = selmStatisticsDay.getNmFaild();
					fcount = fcount + nmFaild.intValue();
					
					BigDecimal nmSuccess = selmStatisticsDay.getNmSuccess();
					scount = scount + nmSuccess.intValue();
				}
				// 总数
				int count = scount + fcount;
				//SelmStatistics info = new SelmStatistics();
				//info.setStStatisticsId(stStatisticsId);
				//info.setStName(stName);
				//info.setStNetFlag(stNetFlag);
				selmStatistics.setNmCount(new BigDecimal(count));
				selmStatisticsDao.update(selmStatistics);
			}
	}
	*/
}
