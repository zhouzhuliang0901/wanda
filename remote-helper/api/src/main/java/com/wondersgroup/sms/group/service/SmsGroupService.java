package com.wondersgroup.sms.group.service;

import java.util.List;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.group.bean.SmsGroup;
import com.wondersgroup.sms.menu.bean.SmsMenu;

import coral.base.util.RequestWrapper;

/**
 * 用户组业务接口
 * 
 */
public interface SmsGroupService {

	/**
	 * 根据主键 {@link SmsGroup#ST_GROUP_ID}获取用户组
	 * 
	 * @param stGroupId
	 *            用户组主键 {@link SmsGroup#ST_GROUP_ID}
	 * @return 用户组实例
	 */
	SmsGroup get(String stGroupId);

	/**
	 * 查询用户组列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 用户组列表
	 */
	PaginationArrayList<SmsGroup> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SmsGroup#ST_GROUP_ID}删除用户组
	 * 
	 * @param stGroupId
	 *            用户组主键 {@link SmsGroup#ST_GROUP_ID}
	 */
	void remove(String[] stGroupId);

	/**
	 * 保存或更新用户组
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 用户组实例
	 */
	SmsGroup saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 获取组菜单
	 * 
	 * @param stGroupId
	 * @return
	 */
	List<SmsMenu> queryGroupMenu(String stGroupId);

}
