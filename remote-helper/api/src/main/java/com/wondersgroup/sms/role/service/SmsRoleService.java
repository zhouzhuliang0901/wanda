package com.wondersgroup.sms.role.service;

import java.util.List;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.role.bean.SmsRole;

import coral.base.util.RequestWrapper;

/**
 * 角色表业务接口
 * 
 */
public interface SmsRoleService {

	/**
	 * 根据主键 {@link SmsRole#ST_ROLE_ID}获取角色表
	 * 
	 * @param stRoleId
	 *            角色表主键 {@link SmsRole#ST_ROLE_ID}
	 * @return 角色表实例
	 */
	SmsRole get(String stRoleId);

	/**
	 * 查询角色表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 角色表列表
	 */
	PaginationArrayList<SmsRole> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SmsRole#ST_ROLE_ID}删除角色表
	 * 
	 * @param roleIdList
	 *            角色表主键 {@link SmsRole#ST_ROLE_ID}
	 */
	void remove(String[] roleIdList);

	/**
	 * 保存或更新角色表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 角色表实例
	 */
	SmsRole saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 获取角色菜单权限
	 * 
	 * @param stRoleId
	 * @return
	 */
	List<SmsMenu> queryRoleMenu(String stRoleId);

}
