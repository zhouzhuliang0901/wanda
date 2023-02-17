package com.wondersgroup.publicService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.publicService.bean.OrganNodeInfo;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;
import com.wondersgroup.publicService.dao.SelfDeclareDao;
import com.wondersgroup.publicService.service.SelfDeclareService;

@Service
public class SelfDeclareServiceImpl implements SelfDeclareService{
	
	@Autowired
	private SelfDeclareDao selfDeclareDao;
	
	@Override
	public void addZhallItem(SelmZhallItemInfo info) {
		selfDeclareDao.addZhallItem(info);
	}

	@Override
	public void updateZhallItem(SelmZhallItemInfo info) {
		selfDeclareDao.updateZhallItem(info);
	}

	@Override
	public List<SelmZhallItemInfo> queryZhallItem(String id) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ID", Condition.OT_EQUAL, id));
		List<SelmZhallItemInfo> list = selfDeclareDao.query(conds, "");
		return list;
	}

	@Override
	public List<SelmZhallItemInfo> getallItem() {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_REMOVE", Condition.OT_EQUAL, 0));
		String order = " ORDER BY ST_SUB_ITEM_NAME";
    	if(RdConfig.get("reindeer.service.jdbc.driver").contains("mysql")){
    		order = " ORDER BY CONVERT(ST_SUB_ITEM_NAME USING GBK) ASC";
    	}
		List<SelmZhallItemInfo> list = selfDeclareDao.query(conds, order);
		return list;
	}

	@Override
	public List<SelmZhallItemInfo> getItemByItemName(String itemName) {
		List<SelmZhallItemInfo> list = selfDeclareDao.getItemByItemName(itemName);
		return list;
	}

	@Override
	public List<OrganNodeInfo> getAllOrgan() {
		return selfDeclareDao.getAllOrgan();
	}
	
	@Override
	public List<SelmZhallItemInfo> getItemByOrgan(String organCode, String itemName) {
		List<SelmZhallItemInfo> list = selfDeclareDao.getItemByOrgan(organCode, itemName);
		return list;
	}

	@Override
	public List<SelmZhallItemInfo> getItemTransactName(String stItemNo) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		conds.add(new Condition("ST_REMOVE", Condition.OT_EQUAL, 0));
		List<SelmZhallItemInfo> list = selfDeclareDao.query(conds, "");
		return list;
	}
}
