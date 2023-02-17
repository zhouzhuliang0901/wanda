package com.wondersgroup.infopub.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.SelmAssist;

public interface InfopubManagerService {

	/**
	 * 验证管理员对设备是否有权操作
	 * 
	 * @param managerIdCard
	 * @param machineMac
	 * @return
	 */
	int checkManager(String managerIdCard, String machineMac);

	/**
	 * 验证事项是否是适老事项
	 * 
	 * @param itemName
	 * @return
	 */
	int checkItem(String itemName);

	/**
	 * 根据身份证号查询管理员信息
	 * 
	 * @param managerIdCard
	 * @return
	 */
	List<SelmAssist> getManagetInfoByIdCard(String managerIdCard);

	/**
	 * 根据MAC获取设备信息
	 * 
	 * @param machineMac
	 * @return
	 */
	InfopubDeviceInfo getDeviceByMac(String machineMac);

	/**
	 * 获取设备配置的事项的部门
	 * 
	 * @param machineMac
	 * @return
	 */
	JSONArray getItemOrgan(String machineMac, String itemType);

	/**
	 * 根据部门ID和设备MAC获取改设备某部门下的事项
	 * @param machineMac
	 * @param itemType
	 * @param organId
	 * @return
	 */
	List<SelmItem> getItemList(String machineMac, String itemType,
			String organId);
	
	/**
	 * 根据事项ID获取子事项信息
	 * @param itemId
	 * @return
	 */
	List<SelmItem> getSubItemInfo(String itemId);
}
