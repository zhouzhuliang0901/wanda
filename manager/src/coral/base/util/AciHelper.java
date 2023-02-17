package coral.base.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Encoder;

import wfc.service.config.Config;

public class AciHelper {

	
	/**
	 * 编码（base64）
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(byte[] image) {
		String str = "";
		sun.misc.BASE64Encoder encoder = new BASE64Encoder();
		if (image != null) {
			str = encoder.encode(image);
		}
		return str;
	}
	
	
	/**
	 * 解码（base64）
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;
	}

	
	
	/**
	 * 按年份水平分表，获取对应表名
	 * @param tableName
	 * @return
	 */
	public static String getTable(String tableName){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String table = "";
		if(year < 2022){
			table = tableName;
		} else {
			table = tableName+"_"+year;
		}
		return table;
	}
	
	/**
	 * 获取此前的所有水平分表
	 * @param tableName
	 * @return
	 */
	public static List<String> getTables(String tableName){
		Calendar calendar = Calendar.getInstance();
		//int year = calendar.get(Calendar.YEAR);
		int year = 2022;
		List<String> tables = new ArrayList<String>();
		for(int i = 2021; i <= year; i++){
			if(i == 2021){
				tables.add(tableName);
			}else{
				String table = tableName+"_"+year;
				tables.add(table);
			}
		}
		return tables;
	}
	
	public static String getTableOfYear(String table,int year){
		String t = "";
		if(year <= 2021){
			t = table;
		}else{
			t = table+"_"+year;
		}
		return t;
	}
}
