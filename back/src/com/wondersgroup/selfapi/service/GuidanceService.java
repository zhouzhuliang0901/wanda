package com.wondersgroup.selfapi.service;

import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;

public interface GuidanceService {

	/**
	 * 方法描述：获取可自助申报的所有部门列表
	 * @param pageSize
	 * @param currentPage
	 * @return OrganNodeInfoPage
	 */
	public OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage);

	/**
	 * 方法描述：获取可自助申报的所有事项列表，若数据过多可分页返回，每页8条数据
	 * @param pageSize
	 * @param currentPage
	 * @return ItemSetPage
	 */
	public ItemSetPage getAllItemListForPage(Integer pageSize,
			Integer currentPage);

	

	/**
	 * 方法描述：根据事项编码获取事项的材料列表
	 * @param itemCode
	 * @return String
	 */
	public String getItemStuffList(String itemCode);

	/**
	 * 方法描述：获取事项的办事指南详细信息（新增咨询电话、审批期限）
	 * @param stZhallId
	 * @return WindowZhallItemDetailExt
	 */
	//public WindowZhallItemDetailExt getGuideInfoByZhallIdExt(String stZhallId);
	public String getGuideInfoByZhallIdExt(String stZhallId);
		
}
