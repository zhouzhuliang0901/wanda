package reindeer.workday.service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import reindeer.workday.dao.Workday;

/**
 * 计算工作日
 * 
 * @author 邬本春、虞越
 * 
 */
public interface WorkdayConfigService {

	Set<Calendar> getWorkdaySet();

	Set<Calendar> getHolidaySet();

	/**
	 * 保存对某天的改动
	 * 
	 * @param workday
	 */
	boolean save(Workday workday);

	/**
	 * 查找所有改动的日期
	 * 
	 * @return
	 */
	List<String> findAll();
}
