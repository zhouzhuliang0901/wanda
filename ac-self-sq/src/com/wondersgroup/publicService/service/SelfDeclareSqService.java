package com.wondersgroup.publicService.service;

import com.wondersgroup.publicService.bean.SelmItemOrgan;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;

public interface SelfDeclareSqService {

	void addZhallItem(SelmZhallItemInfo itemInfo);

	void addZhallItemOrgan(SelmItemOrgan organ);

	SelmItemOrgan getOrganByCode(String orgCode);

}
