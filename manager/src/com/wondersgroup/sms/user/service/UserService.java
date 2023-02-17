package com.wondersgroup.sms.user.service;

import net.sf.json.JSONObject;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.bean.SmsUserLink;

import coral.base.util.RequestWrapper;

/**
 * 用户表业务接口
 * 
 * @author guicb
 * 
 */
public interface UserService {

	/**
	 * 根据主键 {@link SmsUser#ST_USER_ID}获取用户表
	 * 
	 * @param stUserId
	 *            用户表主键 {@link SmsUser#ST_USER_ID}
	 * @return 用户表实例
	 */
	SmsUser get(String stUserId);

	/**
	 * 查询用户表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 用户表列表
	 */
	PaginationArrayList<SmsUser> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SmsUser#ST_USER_ID}删除用户表
	 * 
	 * @param stUserId
	 *            用户表主键 {@link SmsUser#ST_USER_ID}
	 */
	void remove(String stUserId[]);

	/**
	 * 保存或更新用户表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 用户表实例
	 */
	SmsUser saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 获取用户信息
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

	/**
	 * 检查用户密码
	 * 
	 * @param wrapper
	 * @return
	 */
	boolean checkPassWord(RequestWrapper wrapper);

	/**
	 * 保存密码
	 * 
	 * @param wrapper
	 * @return
	 */
	SmsUser savePassWord(RequestWrapper wrapper);

	JSONObject areaList(HttpReqRes httpReqRes);

	SmsUser getByLoginName(String loginName);
}
