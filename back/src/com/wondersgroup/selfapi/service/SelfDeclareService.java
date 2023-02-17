package com.wondersgroup.selfapi.service;

import java.util.List;

import com.wondersgroup.selfapi.bean.ArchivesApplyInfo;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemInfo;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;

public interface SelfDeclareService {

	List<ItemTheme> getItemTheme(String model, String areaCode, String type);
	List<ItemTheme> getItemTheme(String type);

	OrganNodeInfoPage getDeptInArea(String model, Integer pageSize, 
			Integer currentPage, String areaCode);

	List<ItemSet> getItemInTheme(String model, String themeCode, String areaCode, String type);
	List<ItemSet> getItemInTheme(String themeCode, String type);
	
	/**
	 * 获取某部门的所有事项列表
	 * @param organCode
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	ItemSetPage getItemListByOrganCodeForPage(String model, String organCode, 
			Integer pageSize, Integer currentPage);

	/*WindowItemStatusPage getWindowItemStatusList(Integer pageSize,
			Integer currentPage, String deptCode, String itemNo);*/
	WindowItemStatusPage getWindowItemStatusList(Integer pageSize,
			Integer currentPage, String itemNo);

	
	
	// 保存办件信息
	public String saveApply(String applyInfo);

	List<ArchivesApplyInfo> queryArchivesInfoByIdentNo(String idCard,
			String item, String flage);

	void addArchivesInfo(String string, String applyNo, String string2,
			String name, String idCard);

	void addZhallItem(SelmZhallItemInfo info);
	
	List<SelmZhallItemInfo> queryZhallItem(String id);
	
	void updateZhallItem(SelmZhallItemInfo info);
	
	/**
	 * 输入事项名称关键字，从全部事项中进行搜索，并返回数据列表
	 * @param itemName
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	ItemSetPage getItemListByItemNameForPage(String itemName, String areaCode, 
			Integer pageSize, Integer currentPage);
	
	/**
	 * 获取办事指南的所有事项列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	ItemSetPage getAllItemListForPage(Integer pageSize, Integer currentPage);
	
	/**
	 * 获取有办事指南的所有部门列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage);
	
	/**
	 * 根据事项编码获取其下的情形事项
	 * @param itemNo
	 * @param pageSize
	 * @param currentPage
	 * @param deptCode
	 * @return
	 */
	WindowItemStatusPage getWindowItemStatusList(String itemNo,
			Integer pageSize, Integer currentPage, String deptCode);
	
	/**
	 * 查询所有行政区
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	OrganNodeInfoPage getAllAreaInShanghai(Integer pageSize, Integer currentPage);
	
	/**
	 * 查询街道下主题
	 * @return
	 */
	List<ItemTheme> getThemeInStreet();
}
