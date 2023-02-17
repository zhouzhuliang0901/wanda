package reindeer.base.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Encoder;

import wfc.service.config.Config;

public class AciHelper {

	// 组别默认可预约 为1
	public static Integer getDefaultReservation() {
		return 1;
	}

	// 事项默认可预约 为1
	public static Integer getDefaultItemReservation() {
		return 1;
	}

	// 最大可预约人数为
	public static Integer getReservationNmTotalCount() {
		return 30;
	}

	public static String getQueueNumberServiceModule() {
		String result = Config.get("server.queuenumber.module");
		if (result == null)
			result = "default";
		return result;
	}

	public static String getBroadSoundServiceModule() {
		String result = Config.get("server.broadsound.module");
		if (result == null)
			result = "default";
		return result;
	}

	public static String getSmsServiceModule() {
		String result = Config.get("server.SmsService.module");
		if (result == null)
			result = "default";
		return result;
	}

	public static String getLedDefaultConent() {
		String result = Config.get("server.queuenumber.defaultled");
		if (result == null)
			result = " ";
		return result;
	}

	public static String getWindowOpenConent() {
		String result = Config.get("server.queuenumber.windowopen");
		if (result == null)
			result = " ";
		return result;
	}

	public static String getWindowCloseConent() {
		String result = Config.get("server.queuenumber.windowclose");
		if (result == null)
			result = "关         闭";
		return result;
	}

	public static String getWindowPauseConent() {
		String result = Config.get("server.queuenumber.windowpause");
		if (result == null)
			result = "暂停服务";
		return result;
	}

	/**
	 * 判断传入的办理点的ID是否为null 为null的话配置为配置文件上面的配置信息
	 * 
	 * @param placeId
	 * @return
	 */
	public static String getPlaceId(String placeId) {
		// 如果placeId为空的话默认为配置文件上面的默认数据
		if (StringUtils.isBlank(placeId)) {
			String defaultPlaceId = Config.get("defaultPlaceId");
			if (defaultPlaceId == null) {
				defaultPlaceId = "";
			}
			placeId = defaultPlaceId;
		}
		return placeId;
	}

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
	 * 小艾机器人账号账号配置
	 */
	public static String getAppKey() {
		String appKey = Config.get("appKey");
		if (appKey == null) {
			appKey = "";
		}
		return appKey;
	}

	/**
	 * 小艾机器人账号密码配置
	 */
	public static String getAppSecret() {
		String appSecret = Config.get("appSecret");
		if (appSecret == null) {
			appSecret = "";
		}
		return appSecret;
	}

	/**
	 * 小艾机器人语音账号账号配置
	 */
	public static String getXhAppKey() {
		String xhAppKey = Config.get("xhAppKey");
		if (xhAppKey == null) {
			xhAppKey = "";
		}
		return xhAppKey;
	}

	/**
	 * 小艾机器人语音账号密码配置
	 */
	public static String getXhAppSecret() {
		String xhAppSecret = Config.get("xhAppSecret");
		if (xhAppSecret == null) {
			xhAppSecret = "";
		}
		return xhAppSecret;
	}

	/**
	 * 获取回答问题中的url地址配置
	 * 
	 * @return
	 */
	public static String getXiaoIRobotUrl() {
		String xiaoIRobotUrl = Config.get("xiaoIRobotUrl");
		if (xiaoIRobotUrl == null) {
			xiaoIRobotUrl = "";
		}
		return xiaoIRobotUrl;
	}

	/**
	 * 获取相关问题中的url地址配置
	 * 
	 * @return
	 */
	public static String getXiaoIRobotRelationUrl() {
		String xiaoIRobotRelationUrl = Config.get("xiaoIRobotRelationUrl");
		if (xiaoIRobotRelationUrl == null) {
			xiaoIRobotRelationUrl = "";
		}
		return xiaoIRobotRelationUrl;
	}

	/**
	 * 获取常见问题中的url地址配置
	 * 
	 * @return
	 */
	public static String getXiaoIRobotHotUrl() {
		String xiaoIRobotHotUrl = Config.get("xiaoIRobotHotUrl");
		if (xiaoIRobotHotUrl == null) {
			xiaoIRobotHotUrl = "";
		}
		return xiaoIRobotHotUrl;
	}

	/**
	 * 获取回答问题中的url地址配置
	 * 
	 * @return
	 */
	public static String getProduceXAuthUrl() {
		String produceXAuthUrl = Config.get("produceXAuthUrl");
		if (produceXAuthUrl == null) {
			produceXAuthUrl = "";
		}
		return produceXAuthUrl;
	}

	/**
	 * 获取相关问题中的url地址配置
	 * 
	 * @return
	 */
	public static String getProduceXAuthRetionUrl() {
		String produceXAuthRetionUrl = Config.get("produceXAuthRetionUrl");
		if (produceXAuthRetionUrl == null) {
			produceXAuthRetionUrl = "";
		}
		return produceXAuthRetionUrl;
	}

	/**
	 * 获取常见问题中的url地址配置
	 * 
	 * @return
	 */
	public static String getProduceXAuthHotUrl() {
		String produceXAuthHotUrl = Config.get("produceXAuthHotUrl");
		if (produceXAuthHotUrl == null) {
			produceXAuthHotUrl = "";
		}
		return produceXAuthHotUrl;
	}

	/**
	 * 小艾机器人语音url地址配置
	 */
	public static String getRecognizeUrl() {
		String recognizeUrl = Config.get("recognizeUrl");
		if (recognizeUrl == null) {
			recognizeUrl = "";
		}
		return recognizeUrl;
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
		int year = calendar.get(Calendar.YEAR);
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
}
