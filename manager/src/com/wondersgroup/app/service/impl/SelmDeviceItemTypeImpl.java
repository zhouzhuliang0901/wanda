package com.wondersgroup.app.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmDeviceItemType;
import com.wondersgroup.app.bean.SelmItemLink;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmDeviceItemTypeDao;
import com.wondersgroup.app.dao.SelmItemLinkDao;
import com.wondersgroup.app.dao.SelmItemTypeDao;
import com.wondersgroup.app.service.SelmDeviceItemTypeService;

@Service
@Transactional
public class SelmDeviceItemTypeImpl implements SelmDeviceItemTypeService{

	@Autowired
	private SelmDeviceItemTypeDao selmDeviceItemTypeDao;
	@Autowired
	private SelmItemLinkDao selmItemLinkDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	
	@Override
	public int add(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stItemTypeId = httpReqRes.getParameter("stItemTypeId");
		SelmDeviceItemType sdit = new SelmDeviceItemType();
		if(null != stDeviceId && StringUtils.isNotEmpty(stDeviceId)){
			sdit.setStDeviceId(stDeviceId);
		}
		List<SelmItemLink> silList = new ArrayList<SelmItemLink>();
		int i = 0;
		if(null != stItemTypeId && StringUtils.isNotEmpty(stItemTypeId)){
			sdit.setStItemTypeId(stItemTypeId);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_ITEM_TYPE_ID",Condition.OT_EQUAL,stItemTypeId));
			silList = selmItemLinkDao.query(conds, null);
			if(silList.size() > 0){
				List<SelmDeviceItem> sdiList = new ArrayList<SelmDeviceItem>();
				SelmDeviceItem sdi;
				for(SelmItemLink emp : silList){
					sdi = new SelmDeviceItem();
					sdi.setStDeviceId(stDeviceId);
					sdi.setStItemId(emp.getStItemId());
					sdi.setDtCreate(new Timestamp(System.currentTimeMillis()));
					sdiList.add(sdi);
				}
				i = selmDeviceItemDao.addList(sdiList);
			}
		}
		selmDeviceItemTypeDao.add(sdit);		
		return i;
	}

}
