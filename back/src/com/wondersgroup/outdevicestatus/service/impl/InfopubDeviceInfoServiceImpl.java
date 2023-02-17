package com.wondersgroup.outdevicestatus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.outdevicestatus.service.InfopubDeviceInfoService;

@Service
public class InfopubDeviceInfoServiceImpl implements InfopubDeviceInfoService{
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Override
	public List<SelmItem> getItemByMachine(String machineId) {
		return infopubDeviceInfoDao.getItemByMachine(machineId);
	}

	@Override
	public InfopubDeviceInfo getMachineInfoByCertKey(String stCertKey) {
		return infopubDeviceInfoDao.getMachineInfoByCertKey(stCertKey);
	}
}
