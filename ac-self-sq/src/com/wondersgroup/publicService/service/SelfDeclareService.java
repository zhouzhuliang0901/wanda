package com.wondersgroup.publicService.service;

import java.util.List;

import com.wondersgroup.publicService.bean.OrganNodeInfo;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;

public interface SelfDeclareService {

	void addZhallItem(SelmZhallItemInfo info);

	void updateZhallItem(SelmZhallItemInfo info);

	List<SelmZhallItemInfo> queryZhallItem(String stId);

	List<SelmZhallItemInfo> getallItem();

	List<SelmZhallItemInfo> getItemByItemName(String itemName);

	List<OrganNodeInfo> getAllOrgan();

	List<SelmZhallItemInfo> getItemByOrgan(String organCode, String itemName);

	List<SelmZhallItemInfo> getItemTransactName(String stItemNo);

}
