package reindeer.base.app;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import tw.ecosystem.reindeer.websec.XSSSecurityFilter;

@Order(1)
// 重点
@WebFilter(filterName = "aclFilter", urlPatterns = "/*")
@Component
public class AclFilter extends XSSSecurityFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		super.doFilter(new DecryptRequest(req), response, chain);
	}
}
