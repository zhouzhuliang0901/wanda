package reindeer.workday.service.impl;

import java.util.Calendar;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reindeer.workday.service.WorkdayConfigService;
import reindeer.workday.service.WorkdayService;

@Service
@Transactional
public class WorkdayServiceImpl implements WorkdayService {

	@Autowired
	private WorkdayConfigService workdayConfigService ;

	/**
	 * 获得给定的两个时间点之间工作日天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public int getWorkdayInterval(Calendar startDate, Calendar endDate) {
		Set<Calendar> workdaySet = workdayConfigService.getWorkdaySet();
		Set<Calendar> holidaySet = workdayConfigService.getHolidaySet();
		startDate = (Calendar) startDate.clone();
		int workday = 0;
		while (!startDate.after(endDate)) {
			int day = startDate.get(Calendar.DAY_OF_WEEK);
			if (workdaySet.contains(startDate)
					|| (!holidaySet.contains(startDate)
							&& day != Calendar.SATURDAY && day != Calendar.SUNDAY)) {
				workday++;
			}
			startDate.add(Calendar.DATE, 1);
		}
		return workday;
	}

	public int getNaturalDayInterval(Calendar startDate, Calendar endDate) {
		startDate = (Calendar) startDate.clone();
		int naturalDay = 0;
		while (!startDate.after(endDate)) {
			naturalDay++;
			startDate.add(Calendar.DATE, 1);
		}
		return naturalDay;
	}

	/**
	 * 获得给的时间点延后interval个工作日是哪一天
	 * 
	 * @param date
	 * @param interval
	 * @return
	 */
	@Override
	public Calendar getWorkday(Calendar date, int interval) {
		Set<Calendar> workdaySet = workdayConfigService.getWorkdaySet();
		Set<Calendar> holidaySet = workdayConfigService.getHolidaySet();
		date = (Calendar) date.clone();
		int i = isWorkday(date) ? 1 : 0;
		while (i < interval) {
			date.add(Calendar.DATE, 1);
			int day = date.get(Calendar.DAY_OF_WEEK);
			if (workdaySet.contains(date)
					|| (!holidaySet.contains(date) && day != Calendar.SATURDAY && day != Calendar.SUNDAY)) {
				i++;
			}
		}
		return date;

	}

	public Calendar getNaturalDay(Calendar date, int interval) {
		date = (Calendar) date.clone();
		date.add(Calendar.DATE, interval - 1);
		return date;
	}

	/**
	 * 判断给定的一天是否是工作日
	 * 
	 * @param date
	 * @return
	 */
	@Override
	public boolean isWorkday(Calendar date) {
		Set<Calendar> workdaySet = workdayConfigService.getWorkdaySet();
		Set<Calendar> holidaySet = workdayConfigService.getHolidaySet();
		int day = date.get(Calendar.DAY_OF_WEEK);
		return (workdaySet.contains(date) || (!holidaySet.contains(date)
				&& day != Calendar.SATURDAY && day != Calendar.SUNDAY));
	}

}
