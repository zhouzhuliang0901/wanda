package com.wondersgroup.sms.menu.service;

import java.util.List;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.view.SmsMenuView;

import coral.base.util.RequestWrapper;

public interface SmsMenuService {

	public List<SmsMenuView> getSystemAllMenuTree( );
	
	/**
	 * 根据主键 {@link SmsMenu#ST_MENU_ID}获取系统菜单表
	 * 
	 * @param stMenuId
	 *            系统菜单表主键 {@link SmsMenu#ST_MENU_ID}
	 * @return 系统菜单表实例
	 */
	SmsMenu get(String stMenuId);

	/**
	 * 查询系统菜单表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 系统菜单表列表
	 */
	PaginationArrayList<SmsMenu> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SmsMenu#ST_MENU_ID}删除系统菜单表
	 * 
	 * @param userIdList
	 *            系统菜单表主键 {@link SmsMenu#ST_MENU_ID}
	 */
	void remove(String[] userIdList);

	/**
	 * 保存或更新系统菜单表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 系统菜单表实例
	 */
	SmsMenu saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 检查资源编码
	 * 
	 * @param wrapper
	 * @return
	 */
	public boolean checkMenuCode(RequestWrapper wrapper);

	/**
	 * 根据用户ID获取菜单列表
	 * @return
	 */
	List<SmsMenuView> getSystemMenuByUserId(String userId);
}
