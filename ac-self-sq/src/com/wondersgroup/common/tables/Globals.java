package com.wondersgroup.common.tables;

import com.wondersgroup.common.factory.EnumFinder;
import com.wondersgroup.common.factory.EnumFinderFactory;
import com.wondersgroup.common.factory.IEnum;

public interface Globals {
	
	/**
	 * 是否标志
	 */
	enum Flag implements IEnum<String, String> {

		YES("y", "是"),
		NO("n", "否");
		
		private String name;
		private String value;

		/** 构建EnumFinder类 */
		public static EnumFinder<String, String> finder = EnumFinderFactory.newEnumFinder(Flag.class);

    	/** 构造器 */
    	private Flag(String value, String name) {
    		this.name = name;
    		this.value = value;
    	}

    	@Override
    	public String getValue() {
    		return value;
    	}

    	@Override
    	public String getName() {
    		return name;
    	}

    	public static Flag of(String _method) {
    		for (Flag method : Flag.values()) {
    			if (method.value.equalsIgnoreCase(_method)) {
    				return method;
    			}
    		}
    		return null;
    	}
    	
    	public static String GetName(String _method) {
    		if (_method != null) {
	    		for (Flag method : Flag.values()) {
	    			if (method.value.equalsIgnoreCase(_method)) {
	    				return method.getName();
	    			}
	    		}
    		}
    		return null;
    	}
	}
	
	/**
	 * 时间维度
	 */
	enum TimeRange implements IEnum<String, String> {

		DAY("day", "日"),
		WEEK("week", "周"),
		MONTH("month", "月"),
		SEASON("season", "季"),
		YEAR("year","年");
		
		private String name;
		private String value;

		/** 构建EnumFinder类 */
		public static EnumFinder<String, String> finder = EnumFinderFactory.newEnumFinder(TimeRange.class);

    	/** 构造器 */
    	private TimeRange(String value, String name) {
    		this.name = name;
    		this.value = value;
    	}

    	@Override
    	public String getValue() {
    		return value;
    	}

    	@Override
    	public String getName() {
    		return name;
    	}

    	public static TimeRange of(String _method) {
    		for (TimeRange method : TimeRange.values()) {
    			if (method.value.equalsIgnoreCase(_method)) {
    				return method;
    			}
    		}
    		return null;
    	}
    	
    	public static String GetName(String _method) {
    		if (_method != null) {
	    		for (TimeRange method : TimeRange.values()) {
	    			if (method.value.equalsIgnoreCase(_method)) {
	    				return method.getName();
	    			}
	    		}
    		}
    		return null;
    	}
	}
}
