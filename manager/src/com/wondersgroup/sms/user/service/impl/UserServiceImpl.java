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
 * ?????????????????????
 * 
 * @author guicb
 * 
 */
@SuppressWarnings("all")
@Service
@Transactional
public class UserServiceImpl implements UserService {

	/**
	 * ???????????? {@link SmsUser#ST_USER_ID}???????????????
	 * 
	 * @param stUserId
	 *            ??????????????? {@link SmsUser#ST_USER_ID}
	 * @return ???????????????
	 */
	@Override
	public SmsUser get(String stUserId) {
		if (StringUtils.trimToEmpty(stUserId).isEmpty())
			throw new NullPointerException("Parameter stUserId cannot be null.");
		return smsUserDao.get(stUserId);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ???????????????
	 */
	@Override
	public PaginationArrayList<SmsUser> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// ????????????
		String userName = wrapper.getParameter("userName");
		// ??????????????????
		String startDate = wrapper.getParameter("startDate");
		// ????????????
		String endDate = wrapper.getParameter("endDate");

		// ????????????
		String orderName = wrapper.getParameter("columns["
				+ wrapper.getParameter("order[0][column]") + "][data]");
		// ????????????
		String orderType = wrapper.getParameter("order[0][dir]");
		// ??????
		String suffix = "";
		// ???????????????
		if (orderName != null) {
			// ??????ID???????????????
			if ("stUserId".equals(orderName)) {
				suffix = "ORDER BY ST_USER_ID " + orderType.toUpperCase();
				// ?????????
			} else if ("stLoginName".equals(orderName)) {
				suffix = "ORDER BY ST_LOGIN_NAME " + orderType.toUpperCase();
				// ??????
			} else if ("stUserCode".equals(orderName)) {
				suffix = "ORDER BY ST_USER_CODE " + orderType.toUpperCase();
				// ????????????
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
				// ??????
			} else if ("stUserName".equals(orderName)) {
				suffix = "ORDER BY 	ST_USER_NAME " + orderType.toUpperCase();
				// ??????
			} else if ("stMobile".equals(orderName)) {
				suffix = "ORDER BY 	ST_MOBILE " + orderType.toUpperCase();
				// ????????????????????????
			} else if ("nmLocked".endsWith(orderName)) {
				suffix = "ORDER BY 	NM_LOCKED " + orderType.toUpperCase();
			}
			Log.info("orderName:" + orderName + "*****orderType:" + orderType);
		}
		// ????????????
		conds = Conditions.newAndConditions();
		// ???????????????????????????
		if (userName != null) {
			if (!StringUtils.trim(userName).isEmpty()) {
				// ????????????
				conds.add(new Condition("ST_USER_NAME", Condition.OT_LIKE,
						userName));
			}
		}
		// ???????????????????????????
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// ????????????
				conds.add(new Condition("DT_CREATE",
						Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate
								+ " 00:00:00")));
			}
		}
		// ???????????????????????????
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// ????????????
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			// ????????????
			String length = wrapper.getParameter("length");
			// ????????????
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// ?????????
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// ??????????????????
				if (start != 0) {
					// ?????????
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		return smsUserDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * ???????????? {@link SmsUser#ST_USER_ID}???????????????
	 * 
	 * @param stUserId
	 *            ??????????????? {@link SmsUser#ST_USER_ID}
	 */
	@Override
	public void remove(String[] stUserId) {
		if (stUserId.length == 0) {
			throw new NullPointerException("Parameter stUserId cannot be null.");
		}
		// ????????????
		smsUserDao.delete(stUserId);
		// ??????????????????
		for (String userId : stUserId) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, userId));
			// ??????????????????
			smsUserRoleDao.delete(conds);
			// ?????????????????????
			smsUserGroupDao.delete(conds);
			// ??????????????????
			smsUserMenuDao.delete(conds);
		}
	}

	/**
	 * ????????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ???????????????
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
			// ????????????
			newSmsUser.setStPassword("wanda2016");
			// ????????????
			newSmsUser.setDtCreate(new Timestamp(System.currentTimeMillis()));
			// ????????????
			passwordService.encryptPassword(newSmsUser);
			smsUserDao.add(newSmsUser);
			return newSmsUser;
		} else {// update
			oldSmsUser = (SmsUser) t4r.toBean(oldSmsUser, SmsUser.class);
			// ????????????
			oldSmsUser.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			// ??????????????????
			String resetPD = wrapper.getParameter("resetPD");
			if (resetPD != null) {
				// ????????????
				oldSmsUser.setStPassword("wanda2016");
				// ????????????
				passwordService.encryptPassword(oldSmsUser);
			}
			smsUserDao.update(oldSmsUser);
			return oldSmsUser;
		}
	}

	/**
	 * ????????????????????????
	 * 
	 * @param stUserId
	 *            ??????ID
	 */
	@Override
	public SmsUserLink queryUserLink(String stUserId) {
		if (stUserId == null) {
			throw new NullPointerException("??????ID????????????");
		}
		// ??????????????????
		SmsUserLink userLink = new SmsUserLink();
		// ????????????
		userLink.setGroupList(smsGroupDao.query(null, ""));
		// ????????????
		userLink.setMenuList(smsMenuDao.query(null, ""));
		// ????????????
		userLink.setRoleList(smsRoleDao.query(null, ""));

		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
		// ??????-??????
		userLink.setUserGroupList(smsUserGroupDao.query(conds, ""));
		// ??????-??????
		userLink.setUserMenuList(smsUserMenuDao.query(conds, ""));
		// ??????-??????
		userLink.setUserRoleList(smsUserRoleDao.query(conds, ""));

		return userLink;
	}

	/**
	 * ????????????????????????
	 * 
	 * @param wrapper
	 */
	@Override
	public void addUserLink(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("????????????????????????");
		}
		// ??????ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		// ??????ID
		String menuIds = wrapper.getParameter("menuId");
		// ?????????????????????
		Conditions conds = Conditions.newAndConditions();
		// ??????ID
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
		// ??????????????????
		smsUserRoleDao.delete(conds);
		// ??????????????????
		smsUserGroupDao.delete(conds);
		// ??????????????????
		smsUserMenuDao.delete(conds);

		// ???????????????
		String[] uniqueValue = { SmsUserRole.SMS_USER_ROLE,
				SmsRoleMenu.SMS_ROLE_MENU, SmsUserGroup.SMS_USER_GROUP,
				SmsGroupMenu.SMS_GROUP_MENU, SmsUserMenu.SMS_USER_MENU };
		// ???????????????
		conds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_IN,
				uniqueValue));
		// ????????????????????????????????????
		smsResourceAccessListDao.delete(conds);

		// ??????????????????
		String[] roleIdList = wrapper.getParameterValues("roles[]");
		if (roleIdList != null) {
			for (String roleId : roleIdList) {
				// ?????????????????????
				if (!StringUtils.trimToEmpty(roleId).isEmpty()) {
					SmsUserRole smsUserRole = new SmsUserRole();
					// ??????ID
					smsUserRole.setStUserId(stUserId);
					// ??????Id
					smsUserRole.setStRoleId(roleId);
					// ????????????
					smsUserRoleDao.add(smsUserRole);

					// ????????????????????????
					SmsResourceAccessList resourceAccessList = new SmsResourceAccessList();
					// ??????ID
					resourceAccessList.setStUserId(stUserId);
					// ??????ID
					resourceAccessList.setStResourceId(roleId);
					// ????????????ID
					resourceAccessList
							.setStResourceTypeId(SmsUserRole.SMS_USER_ROLE);
					// ???????????????
					resourceAccessList.setStUniqueValue("role");
					// ????????????
					smsResourceAccessListDao.add(resourceAccessList);

					// ??????????????????
					conds = Conditions.newAndConditions();
					// ??????ID
					conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL,
							roleId));
					// ????????????
					List<SmsRoleMenu> roleMenuList = smsRoleMenuDao.query(
							conds, "");
					for (SmsRoleMenu smsRoleMenu : roleMenuList) {
						// ????????????????????????
						resourceAccessList = new SmsResourceAccessList();
						// ??????ID
						resourceAccessList.setStUserId(stUserId);
						// ??????ID
						resourceAccessList.setStResourceId(smsRoleMenu
								.getStMenuId());
						// ????????????ID
						resourceAccessList
								.setStResourceTypeId(SmsRoleMenu.SMS_ROLE_MENU);
						// ???????????????
						resourceAccessList.setStUniqueValue("roleMenu");
						// ????????????
						smsResourceAccessListDao.add(resourceAccessList);
					}
				}
			}
		}

		// ??????????????????ID
		String[] groupIdList = wrapper.getParameterValues("groupId[]");
		if (groupIdList != null) {
			for (String groupId : groupIdList) {
				// ?????????????????????
				if (!StringUtils.trimToEmpty(groupId).isEmpty()) {
					SmsUserGroup smsUserGroup = new SmsUserGroup();
					// ??????ID
					smsUserGroup.setStUserId(stUserId);
					// ??????Id
					smsUserGroup.setStGroupId(groupId);
					// ????????????
					smsUserGroupDao.add(smsUserGroup);

					// ????????????????????????
					SmsResourceAccessList resourceAccessList = new SmsResourceAccessList();
					// ??????ID
					resourceAccessList.setStUserId(stUserId);
					// ??????ID
					resourceAccessList.setStResourceId(groupId);
					// ????????????ID
					resourceAccessList
							.setStResourceTypeId(SmsUserGroup.SMS_USER_GROUP);
					// ???????????????
					resourceAccessList.setStUniqueValue("userGroup");
					// ??????????????????????????????
					smsResourceAccessListDao.add(resourceAccessList);

					// ??????????????????
					conds = Conditions.newAndConditions();
					// ??????ID
					conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,
							groupId));
					// ????????????
					List<SmsGroupMenu> groupMenuList = smsGroupMenuDao.query(
							conds, "");
					for (SmsGroupMenu smsGroupMenu : groupMenuList) {
						// ????????????????????????
						resourceAccessList = new SmsResourceAccessList();
						// ??????ID
						resourceAccessList.setStUserId(stUserId);
						// ??????ID
						resourceAccessList.setStResourceId(smsGroupMenu
								.getStMenuId());
						// ????????????ID
						resourceAccessList
								.setStResourceTypeId(SmsGroupMenu.SMS_GROUP_MENU);
						// ???????????????
						resourceAccessList.setStUniqueValue("groupMenu");
						// ??????????????????????????????
						smsResourceAccessListDao.add(resourceAccessList);
					}
				}
			}
		}

		// ??????ID???????????????
		if (menuIds != null) {
			String[] menuIdArray = menuIds.split(",");
			for (String menuId : menuIdArray) {
				if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
					// ????????????
					SmsUserMenu userMenu = new SmsUserMenu();
					// ??????ID
					userMenu.setStMenuId(menuId);
					// ??????ID
					userMenu.setStUserId(stUserId);
					// ??????????????????
					smsUserMenuDao.add(userMenu);

					// ????????????????????????
					SmsResourceAccessList resourceAccessList = new SmsResourceAccessList();
					// ??????ID
					resourceAccessList.setStUserId(stUserId);
					// ??????ID
					resourceAccessList.setStResourceId(menuId);
					// ????????????ID
					resourceAccessList
							.setStResourceTypeId(SmsUserMenu.SMS_USER_MENU);
					// ???????????????
					resourceAccessList.setStUniqueValue("userMenu");
					// ??????????????????????????????
					smsResourceAccessListDao.add(resourceAccessList);
				}
			}
		}
	}

	/**
	 * ??????????????????
	 */
	@Override
	public boolean checkPassWord(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("??????????????????");
		}
		// ??????ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		// ???????????????
		String passWord = wrapper.getParameter("passWord");
		AESUtils aesUtils = new AESUtils();
		try {
			passWord = aesUtils.decrypt(passWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!StringUtils.trimToEmpty(stUserId).isEmpty()) {
			// ??????????????????
			SmsUser smsUser = smsUserDao.get(stUserId);
			// ??????????????????
			SmsUser checkUser = new SmsUser();
			// ?????????
			checkUser.setStPassword(passWord);
			// ????????????
			passwordService.encryptPassword(checkUser);
			// ???????????????
			if (smsUser.getStPassword().equals(checkUser.getStPassword())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ????????????
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
			// ????????????
			oldSmsUser.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			// ????????????

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
			Log.info("?????????????????????:" + drawInt);
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
			Log.info("????????????:" + pageSize + "?????????:" + currentPage);
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
