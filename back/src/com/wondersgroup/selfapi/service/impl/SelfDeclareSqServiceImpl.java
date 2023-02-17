package com.wondersgroup.selfapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemOrgan;
import com.wondersgroup.selfapi.bean.SelmZhallItemSq;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;
import com.wondersgroup.selfapi.dao.SelfDeclareSqDao;
import com.wondersgroup.selfapi.service.SelfDeclareSqService;

@Service
public class SelfDeclareSqServiceImpl implements SelfDeclareSqService {
	
	@Autowired
	private SelfDeclareSqDao selfDeclareSqDao;
	
	@Override
	public void addZhallItem(SelmZhallItemSq info) {
		selfDeclareSqDao.addZhallItem(info);
	}

	@Override
	public void addZhallItemOrgan(SelmZhallItemOrgan organ) {
		selfDeclareSqDao.addZhallItemOrgan(organ);
	}

	@Override
	public OrganNodeInfo getOrganByCode(String orgCode) {
		return selfDeclareSqDao.getOrganByCode(orgCode);
	}

	@Override
	public ItemSetPage getItemListByItemNameForPage(String itemName,
			Integer pageSize, Integer currentPage) {
		return selfDeclareSqDao.getItemListByItemNameForPage(itemName,pageSize,currentPage);
	}

	@Override
	public ItemSetPage getAllItemListForPage(Integer pageSize,
			Integer currentPage) {
		return selfDeclareSqDao.getAllItemListForPage(pageSize,currentPage);
	}

	@Override
	public OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage) {
		return selfDeclareSqDao.getOrganListForDeclarePage(pageSize,currentPage);
	}

	@Override
	public ItemSetPage getItemListByOrganCodeForPage(String model,
			String organCode, Integer pageSize, Integer currentPage) {
		return selfDeclareSqDao.getItemListByOrganCodeForPage(model,organCode,pageSize,currentPage);
	}

	@Override
	public WindowItemStatusPage getWindowItemStatusList(String itemNo,
			Integer pageSize, Integer currentPage, String deptCode) {
		return selfDeclareSqDao.getWindowItemStatusList(itemNo,pageSize,currentPage,deptCode);
	}

	@Override
	public OrganNodeInfoPage getDeptInArea(String model, Integer pageSize,
			Integer currentPage, String areaCode) {
		return selfDeclareSqDao.getDeptInArea(model,pageSize,currentPage,areaCode);
	}

	@Override
	public List<ItemTheme> getItemTheme(String model, String areaCode,
			String type) {
		return selfDeclareSqDao.getItemTheme(model,areaCode,type);
	}

	@Override
	public List<ItemSet> getItemInTheme(String model, String themeCode,
			String areaCode, String type) {
		return selfDeclareSqDao.getItemInTheme(model,themeCode,areaCode,type);
	}

	@Override
	public List<ItemSet> getItemInThemeForSQ(String themeName) {
		return selfDeclareSqDao.getItemInThemeForSQ(themeName);
	}

	@Override
	public List<ItemTheme> getThemeForSq() {
		return selfDeclareSqDao.getThemeForSQ();
	}

	@Override
	public OrganNodeInfoPage getOrganListForSQ(Integer pageSize,
			Integer currentPage) {
		return selfDeclareSqDao.getOrganListForSQ(pageSize, currentPage);
	}

	@Override
	public ItemSetPage getItemListByOrganIdForSQ(String organCode,
			Integer pageSize, Integer currentPage) {
		return selfDeclareSqDao.getItemListByOrganIdForSQ(organCode, pageSize, currentPage);
	}
	
}
