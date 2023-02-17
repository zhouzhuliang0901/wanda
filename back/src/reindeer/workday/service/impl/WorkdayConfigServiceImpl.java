package reindeer.workday.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reindeer.workday.dao.Workday;
import reindeer.workday.dao.WorkdayDao;
import reindeer.workday.service.WorkdayConfigService;
import edu.emory.mathcs.backport.java.util.Collections;

@Service
@Transactional
public class WorkdayConfigServiceImpl implements WorkdayConfigService {

	@Autowired
	private WorkdayDao workdayDao;

	private Set<Calendar> workdaySet;

	private Set<Calendar> holidaySet;

	private boolean isDirty;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void initDayMap() {
		workdaySet = Collections.synchronizedSet(new HashSet<Calendar>());
		holidaySet = Collections.synchronizedSet(new HashSet<Calendar>());
		isDirty = false;
		Set<Workday> dates = workdayDao.findAll();
		for (Workday date : dates) {
			boolean isWorkday = "Y".equals(date.getStIsWorkday());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date.getDtWorkday());
			if (isWorkday) {
				workdaySet.add(calendar);
			} else {
				holidaySet.add(calendar);
			}
		}
	}

	public Set<Calendar> getWorkdaySet() {
		if (isDirty) {
			initDayMap();
		}
		return workdaySet;
	}

	public Set<Calendar> getHolidaySet() {
		if (isDirty) {
			initDayMap();
		}
		return holidaySet;
	}

	/**
	 * 保存对某天的改动
	 * 
	 * @param workday
	 */
	@Override
	public boolean save(Workday workday) {
		Date date = (Date) workday.getDtWorkday();
		isDirty = true;
		if (workdayDao.getWorkdayByDate(date).getDtWorkday() != null) {
			workdayDao.delete(date);
			return false;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (cal.get(Calendar.DAY_OF_WEEK) == 1
					|| cal.get(Calendar.DAY_OF_WEEK) == 7) {
				workday.setStIsWorkday("Y");
			} else {
				workday.setStIsWorkday("N");
			}
			workdayDao.add(workday);
			return true;
		}
	}

	/**
	 * 查找所有改动的日期
	 * 
	 * @return
	 */
	@Override
	public List<String> findAll() {
		List<String> list = new ArrayList<String>();
		Set<Workday> dates = workdayDao.findAll();
		for (Workday date : dates) {
			list.add(sdf.format(date.getDtWorkday()));
		}
		return list;
	}

}
