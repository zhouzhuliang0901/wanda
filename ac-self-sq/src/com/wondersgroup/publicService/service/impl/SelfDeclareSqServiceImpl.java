package com.wondersgroup.publicService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.publicService.bean.SelmItemOrgan;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;
import com.wondersgroup.publicService.dao.SelfDeclareDao;
import com.wondersgroup.publicService.dao.SelmItemOrganDao;
import com.wondersgroup.publicService.service.SelfDeclareSqService;

@Service
public class SelfDeclareSqServiceImpl implements SelfDeclareSqService {
	
	@Autowired
	private SelfDeclareDao delfDeclareDao;
	@Autowired
	private SelmItemOrganDao selmItemOrganDao;
	@Override
	public void addZhallItem(SelmZhallItemInfo itemInfo) {
		// TODO Auto-generated method stub
		delfDeclareDao.addZhallItem(itemInfo);
	}
	@Override
	public void addZhallItemOrgan(SelmItemOrgan organ) {
		// TODO Auto-generated method stub
		selmItemOrganDao.insert(organ);
	}
	@Override
	public SelmItemOrgan getOrganByCode(String orgCode) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ORG_CODE", Condition.OT_EQUAL, orgCode));
		List<SelmItemOrgan> list = selmItemOrganDao.query(conds, "");
		return list.size()>0?list.get(0) : null;
	}

}
