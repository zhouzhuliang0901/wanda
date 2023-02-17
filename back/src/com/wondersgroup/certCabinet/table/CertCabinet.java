package com.wondersgroup.certCabinet.table;

import com.wondersgroup.common.factory.EnumFinder;
import com.wondersgroup.common.factory.EnumFinderFactory;
import com.wondersgroup.common.factory.IEnum;

public interface CertCabinet {
	
	/**
	 * 存件结果状态码
	 * @author wanda
	 *
	 */
	enum StoreFlag implements IEnum<String, String> {
		
		MINUS_ONE("-1", "其他"),
		ZORE("0", "成功"),
		ONE("1", "柜号为空"),
		TWO("2", "柜号不存在"),
		THREE("3", "不是空柜子");
		
		private String name;
		private String value;

		/** 构建EnumFinder类 */
		public static EnumFinder<String, String> finder = EnumFinderFactory.newEnumFinder(StoreFlag.class);

    	/** 构造器 */
    	private StoreFlag(String value, String name) {
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

    	public static StoreFlag of(String _method) {
    		for (StoreFlag method : StoreFlag.values()) {
    			if (method.value.equalsIgnoreCase(_method)) {
    				return method;
    			}
    		}
    		return null;
    	}
    	
    	public static String GetName(String _method) {
    		if (_method != null) {
	    		for (StoreFlag method : StoreFlag.values()) {
	    			if (method.value.equalsIgnoreCase(_method)) {
	    				return method.getName();
	    			}
	    		}
    		}
    		return null;
    	}
	}
	
	/**
	 * 取件结果状态码
	 * @author wanda
	 *
	 */
	enum TakeFlag implements IEnum<String, String> {
		
		MINUS_ONE("-1", "其他"),
		ZORE("0", "成功"),
		ONE("1", "柜号和取件码同时为空"),
		TWO("2", "未找到存放证照（材料）的柜子"),
		THREE("3", "柜子信息更新失败");
		
		private String name;
		private String value;

		/** 构建EnumFinder类 */
		public static EnumFinder<String, String> finder = EnumFinderFactory.newEnumFinder(TakeFlag.class);

    	/** 构造器 */
    	private TakeFlag(String value, String name) {
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

    	public static TakeFlag of(String _method) {
    		for (TakeFlag method : TakeFlag.values()) {
    			if (method.value.equalsIgnoreCase(_method)) {
    				return method;
    			}
    		}
    		return null;
    	}
    	
    	public static String GetName(String _method) {
    		if (_method != null) {
	    		for (TakeFlag method : TakeFlag.values()) {
	    			if (method.value.equalsIgnoreCase(_method)) {
	    				return method.getName();
	    			}
	    		}
    		}
    		return null;
    	}
	}
}
