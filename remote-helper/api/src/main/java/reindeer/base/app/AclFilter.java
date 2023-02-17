package reindeer.base.app;

import java.io.IOException;
import java.net.URL;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;

import tw.ecosystem.reindeer.config.RdConfig;
import tw.ecosystem.reindeer.websec.XSSSecurityFilter;
import wfc.service.config.Config;

@Order(1)
// 重点
@WebFilter(filterName = "aclFilter", urlPatterns = "/*")
public class AclFilter extends XSSSecurityFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		super.doFilter(request, response, chain);
	}
}
