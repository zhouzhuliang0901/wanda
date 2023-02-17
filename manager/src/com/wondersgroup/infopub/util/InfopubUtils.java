package com.wondersgroup.infopub.util;

import java.util.HashMap;
import java.util.Map;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;

public class InfopubUtils {
	/**
	 * 给设备发送操作指令
	 * 
	 * @param deviceInfo
	 * @param opt
	 */
	public static String sendOptMsg(InfopubDeviceInfo deviceInfo, String opt) {
		try {
			Map<String, Object> optMsg = new HashMap<String, Object>();
			optMsg.put("module", "cmonitor");
			optMsg.put("desc", "消息传递");
			//optMsg.put("source", deviceInfo.getStDeviceName());
			optMsg.put("source", deviceInfo.getStDeviceId());
			optMsg.put("type", "cmonitor");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("cmd", "sysopt");
			data.put("path", "");
			data.put("opt", opt);
			optMsg.put("data", data);
			CometFactory.getInstance().send(deviceInfo.getStChannel(), optMsg);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "success";
	}

	/**
	 * 给设备发送操作指令
	 * 
	 * @param deviceInfo
	 * @param opt
	 */
	public static String sendWindowsOptMsg(InfopubDeviceInfo deviceInfo,
			String opt) {
		String channel = deviceInfo.getStChannel().substring(
				0, deviceInfo.getStChannel().lastIndexOf("/")+1)
				+ deviceInfo.getStDesc();
		try {
			Map<String, Object> optMsg = new HashMap<String, Object>();
			optMsg.put("module", "cmonitor");
			optMsg.put("desc", "消息传递");
			optMsg.put("source", deviceInfo.getStDeviceName());
			optMsg.put("type", "cmonitor");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("cmd", "sysopt");
			data.put("path", deviceInfo.getStDeviceMac());
			data.put("opt", opt);
			optMsg.put("data", data);
			CometFactory.getInstance().send( channel, optMsg);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "success";
	}
}
