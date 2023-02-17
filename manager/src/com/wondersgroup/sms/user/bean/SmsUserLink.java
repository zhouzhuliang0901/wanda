package com.wondersgroup.sms.user.bean;

import java.util.List;

import com.wondersgroup.sms.group.bean.SmsGroup;
import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.userGroup.bean.SmsUserGroup;
import com.wondersgroup.sms.userMenu.bean.SmsUserMenu;
import com.wondersgroup.sms.userRole.bean.SmsUserRole;

public class SmsUserLink {
	
	/*
	 * 角色信息
	 */
	private List<SmsRole> roleList;
	
	/*
	 * 组别
	 */
	private List<SmsGroup> groupList;
	
	/*
	 * 菜单
	 */
	private List<SmsMenu> menuList;
	
	/*
	 * 用户-角色
	 */
	private List<SmsUserRole> userRoleList;
	
	/*
	 * 用户-组
	 */
	private List<SmsUserGroup> userGroupList;
	
	/*
	 * 用户菜单
	 */
	private List<SmsUserMenu> userMenuList;

	/**
	 * @return the roleList
	 */
	public List<SmsRole> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<SmsRole> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the groupList
	 */
	public List<SmsGroup> getGroupList() {
		return groupList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<SmsGroup> groupList) {
		this.groupList = groupList;
	}

	/**
	 * @return the menuList
	 */
	public List<SmsMenu> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<SmsMenu> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the userRoleList
	 */
	public List<SmsUserRole> getUserRoleList() {
		return userRoleList;
	}

	/**
	 * @param userRoleList the userRoleList to set
	 */
	public void setUserRoleList(List<SmsUserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	/**
	 * @return the userGroupList
	 */
	public List<SmsUserGroup> getUserGroupList() {
		return userGroupList;
	}

	/**
	 * @param userGroupList the userGroupList to set
	 */
	public void setUserGroupList(List<SmsUserGroup> userGroupList) {
		this.userGroupList = userGroupList;
	}

	/**
	 * @return the userMenuList
	 */
	public List<SmsUserMenu> getUserMenuList() {
		return userMenuList;
	}

	/**
	 * @param userMenuList the userMenuList to set
	 */
	public void setUserMenuList(List<SmsUserMenu> userMenuList) {
		this.userMenuList = userMenuList;
	}
	

}
