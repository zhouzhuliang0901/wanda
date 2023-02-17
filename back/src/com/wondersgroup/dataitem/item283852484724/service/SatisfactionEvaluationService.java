package com.wondersgroup.dataitem.item283852484724.service;

import net.sf.json.JSONObject;

public interface SatisfactionEvaluationService {
	
	/**
	 * 保存评价信息
	 * @param nmMachine
	 * @param nmAppearance
	 * @param nmOperation
	 * @param nmScreen
	 * @param context
	 * @param name
	 * @param idCard
	 * @param phone
	 * @return 
	 */
	int addSatisfaction(String machineMAC, String nmMachine,
			String nmAppearance, String nmOperation, String nmScreen,
			String context, String name, String idCard, String phone,
			String stEtx1, String stEtx2, String stEtx3);
	
	/**
	 * 根据设备MAC获取相关办理点信息
	 * @param machineMac
	 * @return
	 */
	JSONObject getMachineInfoByMAC(String machineMac);

}
