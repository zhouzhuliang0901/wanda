package com.wondersgroup.sms.login.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.base.model.Result;
import com.wondersgroup.base.utils.CreateImageCode;
import com.wondersgroup.infopub.bean.InfopubCompany;
import com.wondersgroup.infopub.dao.InfopubCompanyDao;
import com.wondersgroup.infopub.util.AESUtils;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.sms.user.service.SmsPasswordService;
import com.wondersgroup.sms.userRole.bean.SmsUserRole;
import com.wondersgroup.sms.userRole.dao.SmsUserRoleDao;

import coral.base.app.AppContext;
import coral.widget.utils.EasyUIHelper;

@Controller
public class LoginController {

	@Resource
	private SmsPasswordService passwordService;

	@Resource
	private SmsUserDao smsUserDao;
	@Resource
	private SmsUserRoleDao smsUserRoleDao;
	@Resource
	private SmsRoleDao smsRoleDao;
	@Resource
	private InfopubCompanyDao infopubCompanyDao;

	@RequestMapping("/sms/login/login.do")
	public void doLogin(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		String stLoginName = req.getParameter("ST_LOGIN_NAME");
		String stPassword = req.getParameter("ST_PASSWORD");
		String stValidateCode = req.getParameter("ST_VALIDATE_CODE");
		AESUtils aesUtils = new AESUtils();
		try {
			stPassword = aesUtils.decrypt(stPassword);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UsernamePasswordToken token = new UsernamePasswordToken(stLoginName,
				stPassword);
		token.setRememberMe(true);
		String code = (String) req.getSession().getAttribute(
				AppContext.SESSION_CHECK_STRING);
		req.getSession().setAttribute(AppContext.SESSION_CHECK_STRING, null);
		if (code == null || !code.equalsIgnoreCase(stValidateCode)) {
			result.setErrorMsg("验证码错误");
		} else {
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {
					result.setSuccess(true);
					Conditions conds = Conditions.newAndConditions();
					conds.add(new Condition(SmsUser.ST_LOGIN_NAME,
							Condition.OT_EQUAL, stLoginName));
					List<SmsUser> list = smsUserDao.query(conds, null);
					for (SmsUser smsUser : list) {
						Conditions cond = Conditions.newAndConditions();
						cond.add(new Condition("ST_USER_ID",
								Condition.OT_EQUAL, smsUser.getStUserId()));
						List<SmsUserRole> SmsRolelist = smsUserRoleDao.query(
								cond, null);
						for (SmsUserRole smsUserRole : SmsRolelist) {
							Conditions cond1 = Conditions.newAndConditions();
							cond1.add(new Condition("ST_ROLE_ID",Condition.OT_EQUAL, smsUserRole.getStRoleId()));
							List<SmsRole> Rolelist = smsRoleDao.query(cond1,null);
							for (SmsRole smsRole : Rolelist) {
								req.getSession().setAttribute("smsUserStAreaId",smsUser.getStAreaId());
								req.getSession().setAttribute("权限", smsRole.getStRoleCode());
								req.getSession().setAttribute("smsUserId",smsUser.getStUserId());
							}
						}
					}
					req.getSession().setAttribute(AppContext.SESSION_USER,
							list.get(0));
					req.getSession().setAttribute("loginName", list.get(0).getStLoginName());
					SavedRequest savedRequest = WebUtils.getSavedRequest(req);
					// 获取保存的URL
					if (savedRequest == null
							|| savedRequest.getRequestUrl() == null) {
						result.setData("/sms/frame/frame.do");
					} else {
						result.setData(savedRequest.getRequestUrl());
					}
				} else {

				}
			} catch (IncorrectCredentialsException e) {
				result.setErrorMsg("登录密码错误. Password for account "
						+ token.getPrincipal() + " was incorrect.");
			} catch (ExcessiveAttemptsException e) {
				result.setErrorMsg("登录失败次数过多");
			} catch (LockedAccountException e) {
				result.setErrorMsg("帐号已被锁定. The account for username "
						+ token.getPrincipal() + " was locked.");
			} catch (DisabledAccountException e) {
				result.setErrorMsg("帐号已被禁用. The account for username "
						+ token.getPrincipal() + " was disabled.");
			} catch (ExpiredCredentialsException e) {
				result.setErrorMsg("帐号已过期. the account for username "
						+ token.getPrincipal() + "  was expired.");
			} catch (UnknownAccountException e) {
				result.setErrorMsg("帐号不存在. There is no user with username of "
						+ token.getPrincipal());
			} catch (UnauthorizedException e) {
				result.setErrorMsg("您没有得到相应的授权！" + e.getMessage());
			} catch (AccountException e) {
				result.setErrorMsg("用户名不能为空");
			} catch (AuthenticationException e) {
				result.setErrorMsg("密码不正确");
			}
		}
		EasyUIHelper.writeResponse(res, result.toString());
	}

	@RequestMapping("/sms/login/logout.do")
	public void logout(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		SecurityUtils.getSubject().logout();
		res.sendRedirect(AppContext.webRootPath + "/");
	}

	@RequestMapping("/sms/login/validateCode.do")
	public void validateCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("image/jpeg"); // 禁止图像缓存。
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);

		CreateImageCode vCode = new CreateImageCode(100, 40, 4, 10);
		req.getSession().setAttribute(AppContext.SESSION_CHECK_STRING,
				vCode.getCode());
		vCode.write(res.getOutputStream());
	}

	@RequestMapping("/sms/login/testPwd.do")
	public void test2(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		SmsUser user = new SmsUser();
		user.setStLoginName("admin");
		user.setStPassword("admin");
		passwordService.encryptPassword(user);
		EasyUIHelper.writeResponse(res, user.getStPassword());
	}
	
	
	//供窗口系统调用
	@RequestMapping("/infopub/windowDeviceInfo/info.do")
	public ModelAndView windowLogin(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		Result result = Result.getResult();
		String stLoginName = (String)req.getParameter("user");
		String type = (String)req.getParameter("type");
		req.getSession().setAttribute(AppContext.SESSION_CHECK_STRING, null);
		result.setSuccess(true);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(SmsUser.ST_LOGIN_NAME,
				Condition.OT_EQUAL, stLoginName));
		List<SmsUser> list = smsUserDao.query(conds, null);
		for (SmsUser smsUser : list) {
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_USER_ID",
					Condition.OT_EQUAL, smsUser.getStUserId()));
			List<SmsUserRole> SmsRolelist = smsUserRoleDao.query(
					cond, null);
			for (SmsUserRole smsUserRole : SmsRolelist) {
				Conditions cond1 = Conditions.newAndConditions();
				cond1.add(new Condition("ST_ROLE_ID",Condition.OT_EQUAL, smsUserRole.getStRoleId()));
				List<SmsRole> Rolelist = smsRoleDao.query(cond1,null);
				for (SmsRole smsRole : Rolelist) {
					req.getSession().setAttribute("smsUserStAreaId",smsUser.getStAreaId());
					req.getSession().setAttribute("权限", smsRole.getStRoleCode());
					req.getSession().setAttribute("smsUserId",smsUser.getStUserId());
				}
			}
		}
		req.getSession().setAttribute(AppContext.SESSION_USER,list.get(0));
		req.getSession().setAttribute("loginName", list.get(0).getStLoginName());
				
		String url = "";
		if("lm".equals(type)){
			url = "/infopub/deviceinfo/listManager.jsp";
		}else if("saq".equals(type)){
			url="/statistics/selmAreaQuery.jsp";
		}
		return new ModelAndView(url);
	}

}
