package coral.base.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import coral.base.acl.SmsFormAuthenticationFilter;

public class SmsShiroFilterFactoryBean extends ShiroFilterFactoryBean {

	@Override
	public void setLoginUrl(String loginUrl) {
		super.setLoginUrl(loginUrl);
		this.setFilters(new HashMap<String, Filter>());
	}

	@Override
	public void setFilters(Map<String, Filter> filters) {
		super.setFilters(filters);
		filters.put("sso_authc", new SmsFormAuthenticationFilter());
	}

}
