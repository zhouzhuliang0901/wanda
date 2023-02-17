package com.wondersgroup.sms.user.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.util.AESUtils;
import com.wondersgroup.sms.group.dao.SmsGroupDao;
import com.wondersgroup.sms.groupMenu.bean.SmsGroupMenu;
import com.wondersgroup.sms.groupMenu.dao.SmsGroupMenuDao;
import com.wondersgroup.sms.menu.dao.SmsMenuDao;
import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;
import com.wondersgroup.sms.resource.dao.SmsResourceAccessListDao;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.roleMenu.bean.SmsRoleMenu;
import com.wondersgroup.sms.roleMenu.dao.SmsRoleMenuDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.bean.SmsUserLink;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.sms.user.service.SmsPasswordService;
import com.wondersgroup.sms.user.service.UserService;
import com.wondersgroup.sms.userGroup.bean.SmsUserGroup;
import com.wondersgroup.sms.userGroup.dao.SmsUserGroupDao;
import com.wondersgroup.sms.userMenu.bean.SmsUserMenu;
import com.wondersgroup.sms.userMenu.dao.SmsUserMenuDao;
import com.wondersgroup.sms.userRole.bean.SmsUserRole;
import com.wondersgroup.sms.userRole.dao.SmsUserRoleDao;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 用户表业务实现
 * 
 * @author guicb
 * 
 */
@SuppressWarnings("all")
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/**
	 * 根据主键 {@link SmsUser#ST_USER_ID}获取用户表
	 * 
	 * @param stUserId
	 *            用户表主键 {@link SmsUser#ST_USER_ID}
	 * @return 用户表实例
	 */
	@Override
	public SmsUser get(String stUserId) {
		if (StringUtils.trimToEmpty(stUserId).isEmpty())
			throw new NullPointerException("Parameter stUserId cannot be null.");
		return smsUserDao.get(stUserId);
	}

	/**
	 * 查询用户表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 用户表列表
	 */
	@Override
	public PaginationArrayList<SmsUser> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// 用户名称
		String userName = wrapper.getParameter("userName");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");

		// 排列内容
		String orderName = wrapper.getParameter("columns["
				+ wrapper.getParameter("order[0][column]") + "][data]");
		// 排序内容
		String orderType = wrapper.getParameter("order[0][dir]");
		// 排序
		String suffix = "";
		// 存在的场合
		if (orderName != null) {
			// 用户ID排序的场合
			if ("stUserId".equals(orderName)) {
				suffix = "ORDER BY ST_USER_ID " + orderType.toUpperCase();
				// 登录名
			} else if ("stLoginName".equals(orderName)) {
				suffix = "ORDER BY ST_LOGIN_NAME " + orderType.toUpperCase();
				// 工号
			} else if ("stUserCode".equals(orderName)) {
				suffix = "ORDER BY ST_USER_CODE " + orderType.toUpperCase();
				// 创建时间
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
				// 姓名
			} else if ("stUserName".equals(orderName)) {
				suffix = "ORDER BY 	ST_USER_NAME " + orderType.toUpperCase();
				// 手机
			} else if ("stMobile".equals(orderName)) {
				suffix = "ORDER BY 	ST_MOBILE " + orderType.toUpperCase();
				// 已经使用（字节）
			} else if ("nmLocked".endsWith(orderName)) {
				suffix = "ORDER BY 	NM_LOCKED " + orderType.toUpperCase();
			}
			Log.info("orderName:" + orderName + "*****orderType:" + orderType);
		}
		// 获取数据
		conds = Conditions.newAndConditions();
		// 用户名称存在的场合
		if (userName != null) {
			if (!StringUtils.trim(userName).isEmpty()) {
				// 用户名称
				conds.add(new Condition("ST_USER_NAME", Condition.OT_LIKE,
						userName));
			}
		}
		// 开始时间存在的场合
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// 开始时间
				conds.add(new Condition("DT_CREATE",
						Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate
								+ " 00:00:00")));
			}
		}
		// 结束时间存在的场合
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			// 每页长度
			String length = wrapper.getParameter("length");
			// 页面长度
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// 开始页
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// 第一页的场合
				if (start != 0) {
					// 当前页
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		return smsUserDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SmsUser#ST_USER_ID}删除用户表
	 * 
	 * @param stUserId
	 *            用户表主键 {@link SmsUser#ST_USER_ID}
	 */
	@Override
	public void remove(String[] stUserId) {
		if (stUserId.length == 0) {
			throw new NullPointerException("Parameter stUserId cannot be null.");
		}
		// 删除用户
		smsUserDao.delete(stUserId);
		// 删除关联数据
		for (String userId : stUserId) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, userId));
			// 删除用户角色
			smsUserRoleDao.delete(conds);
			// 删除用户关联组
			smsUserGroupDao.delete(conds);
			// 删除用户菜单
			smsUserMenuDao.delete(conds);
		}
	}

	/**
	 * 保存或更新用户表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 用户表实例
	 */
	@Override
	public SmsUser saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsUser.ST_USER_ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		SmsUser oldSmsUser = null;
		if (!StringUtils.trimToEmpty(stUserId).isEmpty()) {
			oldSmsUser = smsUserDao.get(stUserId);
		}
		if (oldSmsUser == null) {// new
			SmsUser newSmsUser = (SmsUser) t4r.toBean(SmsUser.class);
			newSmsUser.setStUserId(UUID.randomUUID().toString());
			// 初始密码
			newSmsUser.setStPassword("wanda2016");
			// 插入时间
			newSmsUser.setDtCreate(new Timestamp(System.currentTimeMillis()));
			// 生成密码
			passwordService.encryptPassword(newSmsUser);
			smsUserDao.add(newSmsUser);
			return newSmsUser;
		} else {// update
			oldSmsUser = (SmsUser) t4r.toBean(oldSmsUser, SmsUser.class);
			// 更新时间
			oldSmsUser.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			// 重置登录密码
			String resetPD = wrapper.getParameter("resetPD");
			if (resetPD != null) {
				// 初始密码
				oldSmsUser.setStPassword("wanda2016");
				// 生成密码
				passwordService.encryptPassword(oldSmsUser);
			}
			smsUserDao.update(oldSmsUser);
			return oldSmsUser;
		}
	}

	/**
	 * 获取用户关联信息
	 * 
	 * @param stUserId
	 *            用户ID
	 */
	@Override
	public SmsUserLink queryUserLink(String stUserId) {
		if (stUserId == null) {
			throw new NullPointerException("用户ID不能为空");
		}
		// 用户关联信息
		SmsUserLink userLink = new SmsUserLink();
		// 所有组别
		userLink.setGroupList(smsGroupDao.query(null, ""));
		// 所有菜单
		userLink.setMenuList(smsMenuDao.query(null, ""));
		// 所有角色
		userLink.setRoleList(smsRoleDao.query(null, ""));

		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
		// 用户-组别
		userLink.setUserGroupList(smsUserGroupDao.query(conds, ""));
		// 用户-菜单
		userLink.setUserMenuList(smsUserMenuDao.query(conds, ""));
		// 用户-角色
		userLink.setUserRoleList(smsUserRoleDao.query(conds, ""));

		return userLink;
	}

	/**
	 * 保存用户关联信息
	 * 
	 * @param wrapper
	 */
	@Override
	public void addUserLink(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("用户参数不能为空");
		}
		// 用户ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		// 菜单ID
		String menuIds = wrapper.getParameter("menuId");
		// 删除用户角色表
		Conditions conds = Conditions.newAndConditions();
		// 用户ID
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
		// 删除用户角色
		smsUserRoleDao.delete(conds);
		// 删除用户组别
		smsUserGroupDao.delete(conds);
		// 删除用户菜单
		smsUserMenuDao.delete(conds);

		// 资源唯一值
		String[] uniqueValue = { SmsUserRole.SMS_USER_ROLE,
				SmsRoleMenu.SMS_ROLE_MENU, SmsUserGroup.SMS_USER_GROUP,
				SmsGroupMenu.SMS_GROUP_MENU, SmsUserMenu.SMS_USER_MENU };
		// 资源唯一值
		conds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_IN,
				uniqueValue));
		// 资源权限访问列表数据删除
		smsResourceAccessListDao.delete(conds);

		// 已选择的角色
		String[] roleIdList = wrapper.getParameterValues("roles[]");
		if (roleIdList != null) {
			for (String roleId : roleIdList) {
				// 数据存在的场合
				if (!StringUtils.trimToEmpty(roleId).isEmpty()) {
					SmsUserRole smsUserRole = new SmsUserRole();
					// 用户ID
					smsUserRole.setStUserId(stUserId);
					// 角色Id
					smsUserRole.setStRoleId(roleId);
					// 保存手机
					smsUserRoleDao.add(smsUserRole);

					// 资源权限访问列表
					SmsResourceAccessList resourceAccessList = new SmsResourceAccessList();
					// 用户ID
					resourceAccessList.setStUserId(stUserId);
					// 资源ID
					resourceAccessList.setStResourceId(roleId);
					// 资源类型ID
					resourceAccessList
							.setStResourceTypeId(SmsUserRole.SMS_USER_ROLE);
					// 资源唯一值
					resourceAccessList.setStUniqueValue("role");
					// 保存数据
					smsResourceAccessListDao.add(resourceAccessList);

					// 获取角色菜单
					conds = Conditions.newAndConditions();
					// 角色ID
					conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL,
							roleId));
					// 角色菜单
					List<SmsRoleMenu> roleMenuList = smsRoleMenuDao.query(
							conds, "");
					for (SmsRoleMenu smsRoleMenu : roleMenuList) {
						// 资源权限访问列表
						resourceAccessList = new SmsResourceAccessList();
						// 用户ID
						resourceAccessList.setStUserId(stUserId);
						// 资源ID
						resourceAccessList.setStResourceId(smsRoleMenu
								.getStMenuId());
						// 资源类型ID
						resourceAccessList
								.setStResourceTypeId(SmsRoleMenu.SMS_ROLE_MENU);
						// 资源唯一值
						resourceAccessList.setStUniqueValue("roleMenu");
						// 保存数据
						smsResourceAccessListDao.add(resourceAccessList);
					}
				}
			}
		}

		// 以选择的组别ID
		String[] groupIdList = wrapper.getParameterValues("groupId[]");
		if (groupIdList != null) {
			for (String groupId : groupIdList) {
				// 数据存在的场合
				if (!StringUtils.trimToEmpty(groupId).isEmpty()) {
					SmsUserGroup smsUserGroup = new SmsUserGroup();
					// 用户ID
					smsUserGroup.setStUserId(stUserId);
					// 组别Id
					smsUserGroup.setStGroupId(groupId);
					// 保存组别
					smsUserGroupDao.add(smsUserGroup);

					// 资源权限访问列表
					SmsResourceAccessList resourceAccessList = new SmsResourceAccessList();
					// 用户ID
					resourceAccessList.setStUserId(stUserId);
					// 资源ID
					resourceAccessList.setStResourceId(groupId);
					// 资源类型ID
					resourceAccessList
							.setStResourceTypeId(SmsUserGroup.SMS_USER_GROUP);
					// 资源唯一值
					resourceAccessList.setStUniqueValue("userGroup");
					// 保存资源权限访问列表
					smsResourceAccessListDao.add(resourceAccessList);

					// 获取角色菜单
					conds = Conditions.newAndConditions();
					// 角色ID
					conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,
							groupId));
					// 角色菜单
					List<SmsGroupMenu> groupMenuList = smsGroupMenuDao.query(
							conds, "");
					for (SmsGroupMenu smsGroupMenu : groupMenuList) {
						// 资源权限访问列表
						resourceAccessList = new SmsResourceAccessList();
						// 用户ID
						resourceAccessList.setStUserId(stUserId);
						// 资源ID
						resourceAccessList.setStResourceId(smsGroupMenu
								.getStMenuId());
						// 资源类型ID
						resourceAccessList
								.setStResourceTypeId(SmsGroupMenu.SMS_GROUP_MENU);
						// 资源唯一值
						resourceAccessList.setStUniqueValue("groupMenu");
						// 保存资源权限访问列表
						smsResourceAccessListDao.add(resourceAccessList);
					}
				}
			}
		}

		// 菜单ID存在的场合
		if (menuIds != null) {
			String[] menuIdArray = menuIds.split(",");
			for (String menuId : menuIdArray) {
				if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
					// 用户菜单
					SmsUserMenu userMenu = new SmsUserMenu();
					// 菜单ID
					userMenu.setStMenuId(menuId);
					// 用户ID
					userMenu.setStUserId(stUserId);
					// 添加用户菜单
					smsUserMenuDao.add(userMenu);

					// 资源权限访问列表
					SmsResourceAccessList resourceAccessList = new SmsResourceAccessList();
					// 用户ID
					resourceAccessList.setStUserId(stUserId);
					// 资源ID
					resourceAccessList.setStResourceId(menuId);
					// 资源类型ID
					resourceAccessList
							.setStResourceTypeId(SmsUserMenu.SMS_USER_MENU);
					// 资源唯一值
					resourceAccessList.setStUniqueValue("userMenu");
					// 保存资源权限访问列表
					smsResourceAccessListDao.add(resourceAccessList);
				}
			}
		}
	}

	/**
	 * 检查用户密码
	 */
	@Override
	public boolean checkPassWord(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("参数不能为空");
		}
		// 用户ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		// 原用户密码
		String passWord = wrapper.getParameter("passWord");
		AESUtils aesUtils = new AESUtils();
		try {
			passWord = aesUtils.decrypt(passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!StringUtils.trimToEmpty(stUserId).isEmpty()) {
			// 获取用户信息
			SmsUser smsUser = smsUserDao.get(stUserId);
			// 校验密码用户
			SmsUser checkUser = new SmsUser();
			// 原密码
			checkUser.setStPassword(passWord);
			// 生成密码
			passwordService.encryptPassword(checkUser);
			// 原密码匹配
			if (smsUser.getStPassword().equals(checkUser.getStPassword())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存密码
	 */

	@Override
	public SmsUser savePassWord(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsUser.ST_USER_ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		String stNewPassword = wrapper.getParameter(SmsUser.ST_PASSWORD);
		String stOldPassword = wrapper.getParameter("ST_OLD_PASSWORD");
		AESUtils aesUtils = new AESUtils();
		try {
			stOldPassword = aesUtils.decrypt(stOldPassword);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SmsUser oldSmsUser = null;
		if (!StringUtils.trimToEmpty(stUserId).isEmpty()) {
			oldSmsUser = smsUserDao.get(stUserId);
		}
		if (oldSmsUser != null) {
			SmsUser smsUser = new SmsUser();
			smsUser.setStLoginName(oldSmsUser.getStLoginName());
			smsUser.setStPassword(stOldPassword);
			passwordService.encryptPassword(smsUser);
			if (!(smsUser.getStPassword()).equals(oldSmsUser.getStPassword())) {
				return null;
			}
			oldSmsUser = (SmsUser) t4r.toBean(oldSmsUser, SmsUser.class);
			// 更新时间
			oldSmsUser.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			// 生成密码

			try {
				oldSmsUser.setStPassword(aesUtils.decrypt(stNewPassword));
			} catch (Exception e) {
				e.printStackTrace();
			}

			passwordService.encryptPassword(oldSmsUser);
			smsUserDao.update(oldSmsUser);
		}
		return oldSmsUser;
	}

	@Autowired
	private SmsUserDao smsUserDao;

	@Autowired
	private SmsPasswordService passwordService;

	@Autowired
	private SmsUserRoleDao smsUserRoleDao;

	@Autowired
	private SmsUserGroupDao smsUserGroupDao;

	@Autowired
	private SmsUserMenuDao smsUserMenuDao;

	@Autowired
	private SmsGroupDao smsGroupDao;

	@Autowired
	private SmsRoleDao smsRoleDao;

	@Autowired
	private SmsMenuDao smsMenuDao;

	@Autowired
	private SmsResourceAccessListDao smsResourceAccessListDao;

	@Autowired
	private SmsRoleMenuDao smsRoleMenuDao;

	@Autowired
	private SmsGroupMenuDao smsGroupMenuDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;

	@Override
	public JSONObject areaList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		/*
		 * conds.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, null));
		 */
		String deviceTypeName = httpReqRes.getParameter("deviceTypeName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		if (deviceTypeName != null
				&& !StringUtils.trim(deviceTypeName).isEmpty()) {
			conds.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE,
					deviceTypeName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		conds.add(new Condition("NM_ORDER", Condition.OT_UNEQUAL, null));
		String suffix = "ORDER BY NM_ORDER";
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (httpReqRes != null) {
			String length = httpReqRes.getParameter("length");
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (httpReqRes.getParameter("start") != null) {
				int start = Integer.valueOf(httpReqRes.getParameter("start"));
				if (start != 0) {
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubArea> areaList = infopubAreaDao.query(conds, suffix,
				pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(areaList, InfopubArea.class)).getString(
					"total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", areaList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", areaList);
		return returnObj;
	}

	@Override
	public SmsUser getByLoginName(String loginName) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(SmsUser.ST_LOGIN_NAME, Condition.OT_EQUAL,
				loginName));
		List<SmsUser> list = smsUserDao.query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}
}
