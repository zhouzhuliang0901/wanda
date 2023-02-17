package coral.base.acl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.wondersgroup.base.acl.BaseSessionCheck;

public class SmsFormAuthenticationFilter extends FormAuthenticationFilter {

	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		boolean isLogin = isLoginRequest(request, response);
		if (!isLogin) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			isLogin = BaseSessionCheck.checkSession(req, res);
		}
		if (isLogin) {
			if (isLoginSubmission(request, response)) {

				return executeLogin(request, response);
			} else {

				// allow them to see the login page ;)
				return true;
			}
		} else {

			saveRequestAndRedirectToLogin(request, response);
			return false;
		}
	}

}
