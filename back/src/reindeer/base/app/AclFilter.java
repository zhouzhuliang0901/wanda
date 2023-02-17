package reindeer.base.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;
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
		HttpServletResponse res = (HttpServletResponse) response;
		String host = req.getHeader("Host");
		String url = req.getRequestURL().toString();
		List<String> illegalList = new ArrayList<String>();
		illegalList.add("183.194.250.112");
		illegalList.add("zzzd.sh.gov.cn");
		if (url.startsWith("https") && !illegalList.contains(host)) {
			// request.getRequestDispatcher("/test.html").forward(request,
			// response);
			Result result = Result.getResult();
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			result.setCode("400");
			result.setSuccess(false);
			result.setMsg("The Request Host Is Illegal!");
			httpReqRes.writeJsonP(result);
		} else {
			super.doFilter(new DecryptRequest(req), response, chain);
		}
	}
}
