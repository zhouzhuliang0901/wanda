package com.wondersgroup.common.tables;

import com.wondersgroup.common.factory.EnumFinder;
import com.wondersgroup.common.factory.EnumFinderFactory;
import com.wondersgroup.common.factory.IEnum;

public interface SQItems {
	
	/**
	 * 社区事项类型
	 * @author wanda
	 *
	 */
	enum Type implements IEnum<String, String> {

		ZERO_TWO("02", "行政处罚"),
		ZERO_THREE("03", "行政强制"),
		ZERO_FOUR("04", "行政征收"),
		ZERO_FIVE("05", "行政给付"),
		ZERO_SIX("06", "行政检查"),
		ZERO_SEVEN("07", "行政确认"),
		ZERO_EIGHT("08", "行政奖励"),
		ZERO_NINE("09", "行政裁决"),
		ONE_ZERO("10", "其他权力"),
		TWO_ZERO("20", "公共服务"),
		ZERO_ONE("01", "行政许可");
		
		private String name;
		private String value;

		/** 构建EnumFinder类 */
		public static EnumFinder<String, String> finder = EnumFinderFactory.newEnumFinder(Type.class);

    	/** 构造器 */
    	private Type(String value, String name) {
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

    	public static Type of(String _method) {
    		for (Type method : Type.values()) {
    			if (method.value.equalsIgnoreCase(_method)) {
    				return method;
    			}
    		}
    		return null;
    	}
    	
    	public static String GetName(String _method) {
    		if (_method != null) {
	    		for (Type method : Type.values()) {
	    			if (method.value.equalsIgnoreCase(_method)) {
	    				return method.getName();
	    			}
	    		}
    		}
    		return null;
    	}
	}
}
