package com.wondersgroup.dataitem.item236012130732.service;

import java.util.List;

import com.wondersgroup.dataitem.item236012130732.bean.RecordBookInfo;

public interface RecordBookService {
	
	/**
	 * 保存就医记录册信息
	 * @param result
	 */
	void saveBook(String result, String machineId);
	
	/**
	 * 就医记录册补打信息查询
	 * @param idCard
	 * @return
	 */
	List<RecordBookInfo> getBookInfoByIdCard(String startTime, String endTime, String machineId);
	
	/**
	 * 查询所有记录册信息
	 * @return
	 */
	List<RecordBookInfo> getAllRecordBookInfo();
	
	/**
	 * 通过某台机器的机器ID，查询到机器所在网点的所有机器
	 * @param machineId
	 */
	List<String> getAllMachineById(String machineId);
	
	/**
	 * 查询一段时间内某人打印记录册次数
	 * @param identNo
	 * @param range
	 */
	int getHistoryBookInfoByIdCard(String identNo, String range);

}
