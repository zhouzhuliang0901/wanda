package com.wondersgroup.dataitem.item267232669CSJ.service;

import java.util.List;

import com.wondersgroup.dataitem.item267232669CSJ.bean.Archives;

public interface ArchivesService {
	
	List<Archives> getCitiesByProvinceName(String provinceName);

	List<Archives> getArchivesByCityId(String cityId);
}
