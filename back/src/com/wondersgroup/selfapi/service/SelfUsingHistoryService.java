package com.wondersgroup.selfapi.service;

import java.util.Date;
import java.util.List;

import com.wondersgroup.selfapi.bean.MachineInfo;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.bean.SelmAttach;

import net.sf.json.JSONArray;

public interface SelfUsingHistoryService {
	
	/**
	 * 保存使用记录
	 * @param selfUsingHistory
	 */
	public SelfUsingHistory saveOrUpdateSelfUsingHistory(SelfUsingHistory selfUsingHistory);
	
	/**
	 * 统计使用量
	 * @param startTime
	 * @param endTime
	 */
	public JSONArray queryUsingHistory(Date startTime, Date endTime);
	
	/**
	 * 查询所有模块使用记录
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SelfUsingHistory> queryUsingHistoryList(Date startTime,
			Date endTime);
	
	/**
	 * 保存附件
	 * @param linkId
	 * @param content
	 * @param fileName
	 * @param fileType
	 * @param file
	 * @param len
	 */
	public SelmAttach uploadStuff(String linkId, String fileName,
			String fileType, byte[] file, int len);
	
	/**
	 * 保存办事结果
	 * @param linkId
	 * @param content
	 * @return
	 */
	public SelmAttach saveApplyResult(String linkId, String content);
	
	/**
	 * 根据附件ID获取业务结果数据
	 * @param businessId
	 * @return
	 */
	public String getBusinessResultStringById(String businessId);
	
	/**
	 * 根据附件ID获取附件文件
	 * @param attachId
	 * @return
	 */
	public byte[] getAttchFileById(String attachId);
	
	/**
	 * 根据设备MAC查询设备信息
	 * @param machineMAC
	 * @return
	 */
	public MachineInfo getMachineInfo(String machineMAC);
}
