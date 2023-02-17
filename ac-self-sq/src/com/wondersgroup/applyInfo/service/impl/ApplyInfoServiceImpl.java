package com.wondersgroup.applyInfo.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.applyInfo.bean.SelmApplyInfo;
import com.wondersgroup.applyInfo.dao.SelmApplyinfoDao;
import com.wondersgroup.applyInfo.service.ApplyInfoService;

@Service
public class ApplyInfoServiceImpl implements ApplyInfoService{
	
	@Autowired
	private SelmApplyinfoDao selmApplyinfoDao;

	@Override
	public int save(SelmApplyInfo selmApplyInfo) {
		selmApplyInfo.setStQueryHisId(UUID.randomUUID().toString());
		selmApplyInfo.setDtCreate(new Timestamp(System.currentTimeMillis()));
		int total = selmApplyinfoDao.insert(selmApplyInfo);
		return total;
	}

}
