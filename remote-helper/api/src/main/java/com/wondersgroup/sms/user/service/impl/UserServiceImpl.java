package com.wondersgroup.sms.user.service.impl;

import com.wondersgroup.sms.group.dao.SmsGroupDao;
import com.wondersgroup.sms.groupMenu.bean.SmsGroupMenu;
import com.wondersgroup.sms.groupMenu.dao.SmsGroupMenuDao;
import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.dao.SmsMenuDao;
import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;
import com.wondersgroup.sms.resource.dao.SmsResourceAccessListDao;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.roleMenu.bean.SmsRoleMenu;
import com.wondersgroup.sms.roleMenu.dao.SmsRoleMenuDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.bean.SmsUserLink;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.sms.user.service.UserService;
import com.wondersgroup.sms.userGroup.bean.SmsUserGroup;
import com.wondersgroup.sms.userGroup.dao.SmsUserGroupDao;
import com.wondersgroup.sms.userMenu.bean.SmsUserMenu;
import com.wondersgroup.sms.userMenu.dao.SmsUserMenuDao;
import com.wondersgroup.sms.userRole.bean.SmsUserRole;
import com.wondersgroup.sms.userRole.dao.SmsUserRoleDao;
import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import com.wondersgroup.wdf.uacItemInfo.dao.UacItemInfo;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


/**
 * 用户表业务实现
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private SmsUserDao smsUserDao;

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

    /**
     * 根据主键 {@link SmsUser#ST_USER_ID}获取角色表
     *
     * @param stUserId
     *            角色表主键 {@link SmsUser#ST_USER_ID}
     * @return 角色表实例
     */
    @Override
    public SmsUser get(String stUserId) {
        if (StringUtils.trimToEmpty(stUserId).isEmpty())
            throw new NullPointerException("Parameter stUserId cannot be null.");
        return smsUserDao.get(stUserId);
    }

    /**
     * 保存或更新事项信息
     *
     * @param wrapper
     *            提交参数
     * @return 事项信息实例
     */
    @Override
    public SmsUser saveOrUpdate(RequestWrapper wrapper) {
        if (wrapper == null)
            throw new NullPointerException("Parameter wrapper cannot be null.");
        Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
        String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
        SmsUser oldSmsUser = null;
        if (!org.apache.commons.lang3.StringUtils.trimToEmpty(stUserId).isEmpty()) {
            oldSmsUser = smsUserDao.get(stUserId);
        }
        if (oldSmsUser == null) {// new
            SmsUser newSmsUser = (SmsUser) t4r.toBean(SmsUser.class);
            newSmsUser.setStUserId(UUID.randomUUID().toString());
            //初始密码
            newSmsUser.setStPassword("123456");
            // 插入时间
            newSmsUser.setDtCreate(new Timestamp(System.currentTimeMillis()));
            smsUserDao.add(newSmsUser);
            return newSmsUser;
        }else {// update
            oldSmsUser = (SmsUser) t4r.toBean(oldSmsUser, SmsUser.class);
            // 更新时间
            oldSmsUser.setDtUpdate(new Timestamp(System.currentTimeMillis()));
            // 重置登录密码
            Boolean resetPWD = wrapper.getParameterBoolean("resetPWD",false,false);
            if (resetPWD) {
                // 初始密码
                oldSmsUser.setStPassword("123456");
            }
            smsUserDao.update(oldSmsUser);
            return oldSmsUser;
        }
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
        //登录名
        String loginName = wrapper.getParameter("ST_LOGIN_NAME");
        //工号
        String userCode = wrapper.getParameter("ST_USER_CODE");
        // 用户名称
        String userName = wrapper.getParameter("ST_USER_NAME");
        // 手机
        String mobile = wrapper.getParameter("ST_MOBILE");

        // 插入开始时间
        String startDate = wrapper.getParameter("startDate");
        // 结束时间
        String endDate = wrapper.getParameter("endDate");

        // 排列内容
        String orderName = wrapper.getParameter("sort");

        // 排序内容
        String orderType = wrapper.getParameter("order");
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
        }
        // 获取数据
        conds = Conditions.newAndConditions();
        // 登录名存在的场合
        if (loginName != null) {
            if (!StringUtils.trim(loginName).isEmpty()) {
                // 登录名
                conds.add(new Condition("ST_LOGIN_NAME", Condition.OT_LIKE,
                        loginName));
            }
        }
        // 工号存在的场合
        if (userCode != null) {
            if (!StringUtils.trim(userCode).isEmpty()) {
                // 工号
                conds.add(new Condition("ST_USER_CODE", Condition.OT_LIKE,
                        userCode));
            }
        }
        // 用户名称存在的场合
        if (userName != null) {
            if (!StringUtils.trim(userName).isEmpty()) {
                // 用户名称
                conds.add(new Condition("ST_USER_NAME", Condition.OT_LIKE,
                        userName));
            }
        }
        // 手机号存在的场合
        if (mobile != null) {
            if (!StringUtils.trim(mobile).isEmpty()) {
                // 手机
                conds.add(new Condition("ST_MOBILE", Condition.OT_LIKE,
                        mobile));
            }
        }
        // 开始时间存在的场合
        if (startDate != null) {
            if (!StringUtils.trim(startDate).isEmpty()) {
                // 开始时间
                conds.add(new Condition("DT_CREATE",
                        Condition.OT_GREATER_EQUAL, startDate + " 00:00"));
            }
        }
        // 结束时间存在的场合
        if (endDate != null) {
            if (!StringUtils.trim(endDate).isEmpty()) {
                // 结束时间
                conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
                        endDate + " 23:59"));
            }
        }
        int pageSize = Integer.MAX_VALUE / 2;
        int currentPage = 1;
        if (wrapper != null) {
            Page page = EasyUIHelper.getPage(wrapper);
            pageSize = page.getPageSize();
            currentPage = page.getCurrentPage();
        }
       /* int pageSize = Integer.MAX_VALUE / 2;
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
        }*/
        return smsUserDao.query(conds, suffix, pageSize, currentPage);
    }

    /**
     * 根据主键 {@link SmsUser#ST_USER_ID}删除角色表
     *
     * @param stUserId
     *            角色表主键 {@link SmsUser#ST_USER_ID}
     */
    @Override
    public void remove(String[] stUserId){
        if (stUserId.length == 0)
            throw new NullPointerException("Parameter stUserId cannot be null.");
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
        String stUserId = wrapper.getParameter("userId");
        // 菜单ID
        String menuIds = wrapper.getParameter("menuIds");
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
        String[] roleIdList = wrapper.getParameterValues("ST_ROLE_ID");
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
        String[] groupIdList = wrapper.getParameterValues("ST_GROUP_ID");
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

    //除去与事项组关联的人员列表
    @Override
    public PaginationArrayList<SmsUser> queryNoUserLink(RequestWrapper wrapper) {
        Conditions conds = Conditions.newAndConditions();
        String stGroupId = wrapper.getParameter("ST_GROUP_ID");
        String stUserName = wrapper.getParameter("ST_USER_NAME");

        int pageSize;
        int currentPage;
        Page page = EasyUIHelper.getPage(wrapper);
        pageSize = page.getPageSize();
        currentPage = page.getCurrentPage();
        return smsUserDao.queryNoUserLink(conds, stGroupId, stUserName, pageSize, currentPage);
    }



}
