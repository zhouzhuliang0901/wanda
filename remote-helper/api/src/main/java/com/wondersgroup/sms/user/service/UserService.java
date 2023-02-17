package com.wondersgroup.sms.user.service;

import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.bean.SmsUserLink;
import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import com.wondersgroup.wdf.uacWebSystem.dao.UacWebSystem;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 用户表业务接口
 *
 */
public interface UserService {
    /**
     * 查询用户表列表
     *
     * @param wrapper
     *            查询条件
     * @return 用户表列表
     */
    PaginationArrayList<SmsUser> query(RequestWrapper wrapper);

    /**
     * 根据主键 {@link SmsUser#ST_USER_ID}删除系统信息
     *
     * @param userIdList
     *            系统信息主键 {@link SmsUser#ST_USER_ID}
     */
    void remove(String[] userIdList);

    /**
     * 根据主键 {@link SmsUser#ST_USER_ID}获取角色表
     *
     * @param stUserId
     *            角色表主键 {@link SmsUser#ST_USER_ID}
     * @return 角色表实例
     */
    SmsUser get(String stUserId);

    /**
     * 保存或更新用户信息
     *
     * @param wrapper
     *            提交参数
     * @return 用户信息实例
     */
    SmsUser saveOrUpdate(RequestWrapper wrapper);

    /**
     * 获取关联信息
     *
     * @param stUserId
     * @return
     */
    SmsUserLink queryUserLink(String stUserId);

    /**
     * 保存用户角色
     *
     * @param wrapper
     */
    void addUserLink(RequestWrapper wrapper);

    PaginationArrayList<SmsUser> queryNoUserLink(RequestWrapper wrapper);
}
