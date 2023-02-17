package com.wondersgroup.outdevicestatus.service;

import java.util.List;

import com.wondersgroup.outdevicestatus.bean.InfopubOdeviceStatus;



import tw.ecosystem.reindeer.web.HttpReqRes;


public interface OdeviceStatusService {

	/**
	 * 更新或者保存外设状态
	 * 
	 * @param httpReqRes
	 * @return
	 */
	public InfopubOdeviceStatus outDeviceStatusSave(HttpReqRes httpReqRes);

	/**
	 * 查看外设状态(通过设备id和外设标识)
	 * 
	 * @param httpReqRes
	 * @return
	 */
	public InfopubOdeviceStatus getOdeviceStatus(HttpReqRes httpReqRes);
	
	
	/**
	 * 查看外设状态(通过设备id)
	 * 
	 * @param httpReqRes
	 * @return
	 */
	public List <InfopubOdeviceStatus> getOdeviceStatusByDeviceId(HttpReqRes httpReqRes);

	/**
	 * 删除外设状态
	 * 
	 * @param httpReqRes
	 */
	public void odeviceStatusRemove(HttpReqRes httpReqRes);
	
}
