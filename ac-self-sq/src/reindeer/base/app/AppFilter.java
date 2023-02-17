package reindeer.base.app;

import tw.ecosystem.reindeer.web.ReindeerFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppFilter extends ReindeerFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		// 强制不使用缓存
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", -1);

		// 强制使用IE的兼容模式
		// response.setHeader("X-UA-Compatible", "IE=EmulateIE7");

		// 强制禁用IE的P3P协议
		res.addHeader("P3P",
				"CP=\"IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA\"");
		// 如果使用同一个应用服务器，要注意此设置无效，需修改cookie name
		super.doFilter(request, response, chain);
	}
}
