package coral.base.acl;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import tw.ecosystem.reindeer.config.RdConfig;
import tw.ecosystem.reindeer.websec.XSSSecurityFilter;
import wfc.service.config.Config;

public class AclFilter extends XSSSecurityFilter {

	private FilterConfig config = null;

	public void init(FilterConfig config) {
		RdConfig.switchClassResource("/" + Config.CONFIG_FILENAME);
		try {
			super.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		this.config = config;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		super.doFilter(req, res, chain);
	}

	public FilterConfig getFilterConfig() {
		return this.config;
	}

	public void setFilterConfig(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		super.destroy();
		this.config = null;
	}

}