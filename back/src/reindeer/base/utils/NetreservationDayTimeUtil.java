package reindeer.base.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wondersgroup.selfapi.bean.NetreservationDayTime;

import wfc.service.log.Log;



public class NetreservationDayTimeUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<NetreservationDayTime> get(List<NetreservationDayTime> list){
		// 对大厅各组别业务等待人数的排序
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				NetreservationDayTime w1 = (NetreservationDayTime) o1;
				NetreservationDayTime w2 = (NetreservationDayTime) o2;
				long l1 = 0 ;
				long l2 = 0 ;
				try {
					 l1 = sdf.parse("1970-01-01 "+w1.getStartTime()).getTime() ;
					 l2 = sdf.parse("1970-01-01 "+w2.getStartTime()).getTime() ;
				} catch (Exception e) {
					Log.debug(e);
					Log.debug("日期转换异常");
				}
				// 小于时返回1是降序，返回-1是升序
				if (l1 < l2) {
					return -1;
				} else if (l1 > l2) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		return list ;
	}
	
}
