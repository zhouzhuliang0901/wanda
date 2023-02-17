/**
 * Project: Coral
 * Source file: ListPagedUtils.java
 * Create At 2014-1-26 下午01:16:23
 * Create By 龚云
 */
package coral.base.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龚云
 * 
 */
public class ListPagedUtils {

	public static int getPageCount(int total) {
		return getPageCount(total, PAGE_SIZE);
	}

	public static int getPageCount(int total, int pageSize) {
		boolean mod = total % pageSize == 0;
		return mod ? total / pageSize : total / pageSize + 1;
	}

	public static <T> List<T> getPage(List<T> list, int pageNo) {
		return getPage(list, pageNo, PAGE_SIZE);
	}

	public static <T> List<T> getPage(List<T> list, int pageNo, int pageSize) {
		List<T> subList = new ArrayList<T>();
		for (int i = pageSize * (pageNo - 1); i < pageSize * pageNo
				&& i < list.size(); i++) {
			subList.add(list.get(i));
		}
		return subList;
	}

	public static final int PAGE_SIZE = 1000;

}
