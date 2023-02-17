package coral.base.app;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class AppFilter extends
		org.springframework.web.filter.CharacterEncodingFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 强制不使用缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);

		// 强制使用IE10的兼容模式
		// response.setHeader("X-UA-Compatible", "IE=10.000");
		// response.setHeader("X-UA-Compatible", "IE=EmulateIE8");
		String url = request.getRequestURI().toUpperCase();
		if (StringUtils.isNotEmpty(url)) {
			// 资源管理系统和窗口管理系统用IE7
			if (url.indexOf("/FRAME/COMBO") != -1) {
				response.setHeader("X-UA-Compatible", "IE=EmulateIE7");
			}
		}
		// 强制禁用IE的P3P协议
		response.addHeader("P3P",
				"CP=\"IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA\"");
		// 如果使用同一个应用服务器，要注意此设置无效，需修改cookie name

		super.doFilterInternal(request, response, filterChain);
	}

}
