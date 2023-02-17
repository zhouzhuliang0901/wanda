package com.wondersgroup.sms.login.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.sms.login.utils.SSOUtil;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.sms.userRole.bean.SmsUserRole;
import com.wondersgroup.sms.userRole.dao.SmsUserRoleDao;

import coral.base.app.AppContext;

@WebServlet("/SSOlogin")
public class SSOServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) {
		String sso_ctk = request.getParameter("sso_ctk");
		String sso_ctkEnc = request.getParameter("sso_ctkEnc");
		String str = SSOUtil.ssoValidate(sso_ctk, sso_ctkEnc);
		JSONObject obj = JSONObject.fromObject(str);
		String userName = obj.optString("Username");
		String newUserName=null;
		if(userName.contains("ZZYX")){
			String[] namerArr = userName.split("_");
			newUserName = namerArr[1];
		}else{
			newUserName=userName;
		}
		/*String[] namerArr = userName.split("_");
		newUserName = namerArr[1];*/
		String user = new String(newUserName);
		userName = user.toLowerCase();
		if(StringUtils.isEmpty(userName)){
			Log.debug("单点登陆失败--->token："+sso_ctk);
			SecurityUtils.getSubject().logout();
			try {
				response.sendRedirect(AppContext.webRootPath + "/");
			} catch (IOException e) {
				Log.debug(e.getMessage());
			}
		} else {
			SmsUserDao userDao = new SmsUserDao();
			SmsUserRoleDao userRoleDao = new SmsUserRoleDao();
			SmsRoleDao roleDao = new SmsRoleDao();
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition(SmsUser.ST_LOGIN_NAME, Condition.OT_EQUAL,userName));
			List<SmsUser> userList = userDao.query(conds,null);
			String stLoginName = userList.get(0).getStLoginName();
			String stPassword = userList.get(0).getStPassword();
			
			UsernamePasswordToken token = new UsernamePasswordToken(stLoginName,
					stPassword);
			token.setRememberMe(true);
			
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {
//					result.setSuccess(true);
//					Conditions userConds = Conditions.newAndConditions();
//					conds1.add(new Condition(SmsUser.ST_LOGIN_NAME,
//							Condition.OT_EQUAL, stLoginName));
//					List<SmsUser> list = userDao.query(conds1, null);
					for (SmsUser smsUser : userList) {
						Conditions cond = Conditions.newAndConditions();
						cond.add(new Condition("ST_USER_ID",
								Condition.OT_EQUAL, smsUser.getStUserId()));
						List<SmsUserRole> SmsRolelist = userRoleDao.query(
								cond, null);
						for (SmsUserRole smsUserRole : SmsRolelist) {
							Conditions cond1 = Conditions.newAndConditions();
							cond1.add(new Condition("ST_ROLE_ID",
									Condition.OT_EQUAL, smsUserRole
											.getStRoleId()));
							List<SmsRole> Rolelist = roleDao.query(cond1,
									null);
							for (SmsRole smsRole : Rolelist) {
								request.getSession().setAttribute(
										"smsUserStAreaId",
										smsUser.getStAreaId());
								if (!smsRole.getStRoleName().equals(
										"project_admin")) {
									request.getSession().setAttribute(
											"权限", smsRole.getStRoleCode());
								} else {
									request.getSession().setAttribute(
											"权限", "project_admin");
								}
							}
						}
					}
					request.getSession().setAttribute("loginName", userList.get(0).getStLoginName());
					request.getSession().setAttribute(AppContext.SESSION_USER,
							userList.get(0));
					
					try {
						response.sendRedirect(AppContext.webRootPath + "/sms/frame/frame.do");
					} catch (IOException e) {
						Log.debug(e.getMessage());
					}
				} else {
					try {
						response.sendRedirect(AppContext.webRootPath + "/");
					} catch (IOException e) {
						Log.debug(e.getMessage());
					}
				}
			} catch (IncorrectCredentialsException e) {
				Log.debug("登录密码错误. Password for account "
						+ token.getPrincipal() + " was incorrect.");
			} catch (ExcessiveAttemptsException e) {
				Log.debug("登录失败次数过多");
			} catch (LockedAccountException e) {
				Log.debug("帐号已被锁定. The account for username "
						+ token.getPrincipal() + " was locked.");
			} catch (DisabledAccountException e) {
				Log.debug("帐号已被禁用. The account for username "
						+ token.getPrincipal() + " was disabled.");
			} catch (ExpiredCredentialsException e) {
				Log.debug("帐号已过期. the account for username "
						+ token.getPrincipal() + "  was expired.");
			} catch (UnknownAccountException e) {
				Log.debug("帐号不存在. There is no user with username of "
						+ token.getPrincipal());
			} catch (UnauthorizedException e) {
				Log.debug("您没有得到相应的授权！" + e.getMessage());
			} catch (AccountException e) {
				Log.debug("用户名不能为空");
			} catch (AuthenticationException e) {
				Log.debug("密码不正确");
			}
			
		}
	}
		
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) {
		doPost(request, response);
	}
}
