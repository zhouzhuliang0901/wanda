package com.wondersgroup.base.acl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Component;

import tw.tool.helper.LogHelper;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.service.UserService;
import com.wondersgroup.ssologin.SsoHelper;

import coral.base.app.AppContext;

@Component
public class BaseSessionCheck {

	public static boolean checkSession(HttpServletRequest req,
			HttpServletResponse res) {
		SmsUser user = getSessionUser(req);
		if (user == null) {
			try {
				/*String ssols = req.getParameter("ssols");
				String ssols1 = ssols.replace(' ', '+');
				String loginName = SsoHelper.getSafeUser(ssols1);*/
				String loginName = SsoHelper.getSafeUser(req);
				if (StringUtils.isNotBlank(loginName)) {
					UserService userService = (UserService) AppContext
							.getBean("userServiceImpl");
					user = userService.getByLoginName(loginName);
					setSessionUser(req, user);
					return true;
				}
			} catch (Exception e) {
				LogHelper.error(e);
			}
		}
		return false;
	}

	private static SmsUser getSessionUser(HttpServletRequest req) {
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if (obj != null && obj instanceof SmsUser) {
			return (SmsUser) obj;
		}
		return null;
	}

	private static void setSessionUser(HttpServletRequest req, SmsUser user) {
		SecurityUtils.getSubject().login(
				new UsernamePasswordToken(user.getStLoginName(), user
						.getStPassword()));
	}

}
