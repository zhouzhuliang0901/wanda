package com.wondersgroup.selfapi.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.selfapi.bean.ArchivesApplyInfo;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemInfo;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;
import com.wondersgroup.selfapi.dao.NewSelfDeclareDao;
import com.wondersgroup.selfapi.dao.SelfDeclareDao;
import com.wondersgroup.selfapi.service.SelfDeclareService;
import com.wondersgroup.selfapi.util.NewZzsbUtils;

@Service
public class SelfDeclareServiceImpl implements SelfDeclareService {

	@Autowired
	private NewSelfDeclareDao newSelfDeclareDao;
	
	@Autowired
	private SelfDeclareDao selfDeclareDao;

	/**
	 * 查询市、区下事项主题
	 */
	@Override
	public List<ItemTheme> getItemTheme(String model, String areaCode, String type) {
		return selfDeclareDao.queryItemThemeInArea(model,areaCode,type);
	}
	
	/**
	 * 获取个人和法人的目录列表
	 */
	public List<ItemTheme> getItemTheme(String type) {
		List<ItemTheme> list = newSelfDeclareDao.queryItemThemeInArea(type);
		return list;
	}

	/**
	 * 查询区下有办事指南事项的部门
	 */
	@Override

	public OrganNodeInfoPage getDeptInArea(String model, Integer pageSize, 
			Integer currentPage, String areaCode) { 
		return selfDeclareDao.queryDeptInArea(model, pageSize, currentPage,areaCode);
	}
//	public OrganNodeInfoPage getDeptInArea(Integer pageSize,
//			Integer currentPage, String type) {
//		OrganNodeInfoPage OrganNodeInfoPage = newSelfDeclareDao
//				.queryDeptInArea(pageSize, currentPage, type);
//		return OrganNodeInfoPage;
//	}

	/**
	 * 查询市、区主题下的事项
	 */
	@Override
	public List<ItemSet> getItemInTheme(String model, String themeCode, 
			String areaCode, String type) {
		List<ItemSet> list = selfDeclareDao.queryItemInTheme(model,themeCode,areaCode,type);
		return list;
	}
	
	/**
	 * 查询个人和法人的下级目录信息 (查询个人、法人、部门目录中的事项)
	 */
	public List<ItemSet> getItemInTheme(String themeCode, String type) {
		List<ItemSet> list = newSelfDeclareDao
				.queryItemInTheme(themeCode, type);
		return list;
	}

	/**
	 * 查询部门下面的所有事项列表
	 */
	@Override
	public ItemSetPage getItemListByOrganCodeForPage(String model, String organCode,
			Integer pageSize, Integer currentPage) {
		return newSelfDeclareDao.getItemListByOrganIdForPage(model, organCode,
				pageSize, currentPage);
	}

	/**
	 * 查询根据事项编码获取其下的情形事项
	 */
	@Override
	/*
	 * public WindowItemStatusPage getWindowItemStatusList(Integer pageSize,
	 * Integer currentPage, String deptCode, String itemNo) { return
	 * newSelfDeclareDao.getWindowItemStatusList(pageSize, currentPage,
	 * deptCode,itemNo); }
	 */
	public WindowItemStatusPage getWindowItemStatusList(Integer pageSize,
			Integer currentPage, String itemNo) {
		return newSelfDeclareDao.getWindowItemStatusList(pageSize, currentPage,
				itemNo);
	}
	
	
	

	//  保存办件信息
	@Override
	public String saveApply(String applyInfo) {
		// String str = "";
		// String result = XhZzsbUtils.saveApply(applyInfo);
		String result = NewZzsbUtils.saveApply(applyInfo);
		// String success =
		// JSONObject.parseObject(result).getString("isSuccess");
		// if ("true".equals(success)) {
		// str = JSONObject.parseObject(result).getJSONObject("data")
		// .getString("applyNo");
		// }
		return result;
	}

	@Override
	public List<ArchivesApplyInfo> queryArchivesInfoByIdentNo(String idCard,
			String item, String flag) {
		return newSelfDeclareDao.queryArchivesInfoByIdentNo(idCard, item, flag);
	}

	@Override
	public void addArchivesInfo(String slid, String applyNo, String code,
			String name, String idCard) {
		ArchivesApplyInfo info = new ArchivesApplyInfo();
		info.setStId(UUID.randomUUID().toString());
		info.setStApplyId(slid);
		info.setStApplyNo(applyNo);
		info.setStArchivesNo(code);
		info.setStUserName(name);
		info.setStIdentNo(idCard);
		info.setDtCreat(new Timestamp(System.currentTimeMillis()));
		info.setNmStatus(0);
		newSelfDeclareDao.add(info);
	}

	@Override
	public void addZhallItem(SelmZhallItemInfo info) {
		selfDeclareDao.addZhallItem(info);
	}
	
	@Override
	public List<SelmZhallItemInfo> queryZhallItem(String id) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ID", Condition.OT_EQUAL, id));
		List<SelmZhallItemInfo> list = selfDeclareDao.query(conds, "");
		return list;
	}
	
	@Override
	public void updateZhallItem(SelmZhallItemInfo info) {
		selfDeclareDao.updateZhallItem(info);
	}

	@Override
	public ItemSetPage getItemListByItemNameForPage(String itemName,
			String areaCode, Integer pageSize, Integer currentPage) {
		return selfDeclareDao.getItemListByItemNameForPage(itemName, areaCode, pageSize,
				currentPage);
	}

	@Override
	public ItemSetPage getAllItemListForPage(Integer pageSize,
			Integer currentPage) {
		return selfDeclareDao.getAllItemListForPage(pageSize, currentPage);
	}

	@Override
	public OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage) {
		return selfDeclareDao.getOrganListForDeclarePage(pageSize, currentPage);
	}

	@Override
	public WindowItemStatusPage getWindowItemStatusList(String itemNo,
			Integer pageSize, Integer currentPage, String deptCode) {
		return selfDeclareDao.getWindowItemStatusList(itemNo, pageSize,
				currentPage, deptCode);
	}

	@Override
	public OrganNodeInfoPage getAllAreaInShanghai(Integer pageSize,
			Integer currentPage) {
		return selfDeclareDao.queryAllAreaInShanghai(pageSize, currentPage);
	}

	@Override
	public List<ItemTheme> getThemeInStreet() {
		return selfDeclareDao.queryThemeInStreet();
	}
}
