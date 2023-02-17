package com.wondersgroup.selfapi.service;

import java.util.List;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemOrgan;
import com.wondersgroup.selfapi.bean.SelmZhallItemSq;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;

public interface SelfDeclareSqService {
	
	/**
	 * 新增事项数据
	 * @param info
	 */
	void addZhallItem(SelmZhallItemSq info);
	
	/**
	 * 新增部门数据
	 * @param organ
	 */
	void addZhallItemOrgan(SelmZhallItemOrgan organ);
	
	/**
	 * 根据部门代码查询部门信息
	 * @param orgCode
	 * @return
	 */
	OrganNodeInfo getOrganByCode(String orgCode);
	
	/**
	 * 输入事项名称关键字搜索事项
	 * @param itemName
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	ItemSetPage getItemListByItemNameForPage(String itemName, Integer pageSize,
			Integer currentPage);
	
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
	 * 获取某部门的所有事项列表
	 * @param model
	 * @param organCode
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	ItemSetPage getItemListByOrganCodeForPage(String model, String organCode,
			Integer pageSize, Integer currentPage);
	
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
	 * 查询市、区下有办事指南事项的部门
	 * @param model
	 * @param pageSize
	 * @param currentPage
	 * @param areaCode
	 * @return
	 */
	OrganNodeInfoPage getDeptInArea(String model, Integer pageSize,
			Integer currentPage, String areaCode);
	
	/**
	 * 查询市、区下事项主题
	 * @param model
	 * @param areaCode
	 * @param type
	 * @return
	 */
	List<ItemTheme> getItemTheme(String model, String areaCode, String type);
	
	/**
	 * 查询市、区主题下的事项
	 * @param model
	 * @param themeCode
	 * @param areaCode
	 * @param type
	 * @return
	 */
	List<ItemSet> getItemInTheme(String model, String themeCode,
			String areaCode, String type);
	
	/**
	 * 查询社区下事项主题
	 * @return
	 */
	List<ItemTheme> getThemeForSq();
	
	/**
	 * 查询社区主题下事项
	 * @param themeName
	 * @return
	 */
	List<ItemSet> getItemInThemeForSQ(String themeName);
	
	/**
	 * 获取社区有办事指南事项的所有部门列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	OrganNodeInfoPage getOrganListForSQ(Integer pageSize, Integer currentPage);
	
	/**
	 * 获取社区部门的所有事项列表
	 * @param organCode
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	ItemSetPage getItemListByOrganIdForSQ(String organCode, Integer pageSize,
			Integer currentPage);

}
