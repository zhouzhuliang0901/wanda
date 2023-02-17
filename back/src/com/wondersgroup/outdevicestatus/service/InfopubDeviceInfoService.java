package com.wondersgroup.outdevicestatus.service;

import java.util.List;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;

public interface InfopubDeviceInfoService {
	
	/**
	 * 通过设备唯一标识获取设备授权事项
	 * @param machineId
	 * @return
	 */
	public List<SelmItem> getItemByMachine(String machineId);
	
	/**
	 * 通过CA协卡助手获得的证书唯一编号关联设备
	 * @param machineId
	 * @return
	 */
	public InfopubDeviceInfo getMachineInfoByCertKey(String stCertKey);
}
