package com.wondersgroup.dataitem.item267232669CSJ.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.dataitem.item267232669CSJ.bean.Archives;
import com.wondersgroup.dataitem.item267232669CSJ.dao.ArchivesForCSJDao;
import com.wondersgroup.dataitem.item267232669CSJ.service.ArchivesService;

@Service
public class ArchivesServiceImpl implements ArchivesService{
	
	@Autowired
	private ArchivesForCSJDao archivesForCSJDao;

	@Override
	public List<Archives> getCitiesByProvinceName(String provinceName) {
		return archivesForCSJDao.queryCitiesByProvinceName(provinceName);
	}

	@Override
	public List<Archives> getArchivesByCityId(String cityId) {
		return archivesForCSJDao.queryArchivesByCityId(cityId);
	}
}
