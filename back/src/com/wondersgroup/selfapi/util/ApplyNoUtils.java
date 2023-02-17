package com.wondersgroup.selfapi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import wfc.service.database.Sequence;

public class ApplyNoUtils {

	private static final Object lock = new Object();

	// 生成综合办件编号
	public static String genApplyNo(String itemNo) {
		synchronized (lock) {
			String yearLast = new SimpleDateFormat("yy", Locale.CHINESE)
					.format(Calendar.getInstance().getTime());
			String xl = String.format("%06d",
					Sequence.getNextValue("windowAccept" + yearLast));
			return "149"+itemNo + yearLast + xl;
		}
	}
}
