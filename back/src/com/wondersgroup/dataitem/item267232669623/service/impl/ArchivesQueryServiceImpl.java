package com.wondersgroup.dataitem.item267232669623.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.dataitem.item267232669623.bean.ArchivesApplyInfo;
import com.wondersgroup.dataitem.item267232669623.dao.ArchivesQueryDao;
import com.wondersgroup.dataitem.item267232669623.service.ArchivesQueryService;

@Service
public class ArchivesQueryServiceImpl implements ArchivesQueryService{
	
	@Autowired
	private ArchivesQueryDao archivesQueryDao;
	
	@Override
	public void addArchivesInfo(String slid, String stApplyNo, String code, 
			String name,String idCard) {
		ArchivesApplyInfo info = new ArchivesApplyInfo();
		info.setStId(UUID.randomUUID().toString());
		info.setStApplyId(slid);
		info.setStApplyNo(stApplyNo);
		info.setStArchivesNo(code);
		info.setStUserName(name);
		info.setStIdentNo(idCard);
		info.setDtCreat(new Timestamp(System.currentTimeMillis()));
		info.setNmStatus(0);
		archivesQueryDao.add(info);
	}

	@Override
	public List<ArchivesApplyInfo> queryArchivesInfoByIdentNo(String identNo, String code, String flag) {
		return archivesQueryDao.queryArchivesInfoByIdentNo(identNo, code, flag);
	}

	@Override
	public void updateArchivesInfo(String slid, String slbh) {
		archivesQueryDao.updateArchivesInfo(slid, slbh);
	}

	@Override
	public void updateArchivesPrintStatus(String slid, String slbh) {
		archivesQueryDao.updateArchivesPrintStatus(slid, slbh);
	}

	@Override
	public void updateArchivesInfoExcTimes(String slid, String slbh, int i) {
		archivesQueryDao.updateArchivesInfoExcTimes(slid, slbh, i);
	}
	
}
