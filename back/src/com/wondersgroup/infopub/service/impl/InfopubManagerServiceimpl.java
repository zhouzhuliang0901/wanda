package com.wondersgroup.infopub.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.SelmAssist;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoExtDao;
import com.wondersgroup.infopub.dao.InfopubManagerDao;
import com.wondersgroup.infopub.service.InfopubManagerService;

@Service
public class InfopubManagerServiceimpl implements InfopubManagerService {

	@Autowired
	private InfopubManagerDao infopubManagerDao;

	@Autowired
	private InfopubDeviceInfoExtDao infopubDeviceInfoExtDao;

	@Override
	public int checkManager(String managerIdCard, String machineMac) {
		return infopubManagerDao.checkManager(managerIdCard, machineMac);
	}

	@Override
	public int checkItem(String itemName) {
		return infopubManagerDao.checkItem(itemName);
	}

	@Override
	public List<SelmAssist> getManagetInfoByIdCard(String managerIdCard) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ASSIST_IDCARD", Condition.OT_EQUAL,
				managerIdCard));
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		return infopubManagerDao.query(conds, "", pageSize, currentPage);
	}

	@Override
	public InfopubDeviceInfo getDeviceByMac(String machineMac) {
		return infopubDeviceInfoExtDao.getMac(machineMac);
	}

	@Override
	public JSONArray getItemOrgan(String machineMac, String itemType) {
		return infopubManagerDao.queryItemOrgan(machineMac, itemType);
	}

	@Override
	public List<SelmItem> getItemList(String machineMac, String itemType,
			String organId) {
		return infopubManagerDao.queryItemList(machineMac, itemType, organId);
	}

	@Override
	public List<SelmItem> getSubItemInfo(String itemId) {
		return infopubManagerDao.querySubItemInfo(itemId);
	}
}
