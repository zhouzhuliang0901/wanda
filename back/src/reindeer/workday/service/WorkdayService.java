package reindeer.workday.service;

import java.util.Calendar;

/**
 * 计算工作日
 * 
 * @author 邬本春、虞越
 * 
 */
public interface WorkdayService {

	/**
	 * 获得给定的两个时间点之间（startTime，endTime）工作日天数
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getWorkdayInterval(Calendar startDay, Calendar endDay);

	int getNaturalDayInterval(Calendar startDay, Calendar endDay);

	/**
	 * 获得给的时间点延后interval个工作日是哪一天
	 * 
	 * @param startDay
	 * @param interval
	 * @return
	 */
	Calendar getWorkday(Calendar startDay, int interval);

	Calendar getNaturalDay(Calendar startDay, int interval);

	/**
	 * 判断给定的一天是否是工作日
	 * 
	 * @param day
	 * @return
	 */
	boolean isWorkday(Calendar day);

}
