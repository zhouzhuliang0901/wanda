package com.wondersgroup.sms.frame.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.sms.menu.service.SmsMenuService;
import com.wondersgroup.sms.menu.view.SmsMenuView;

@Controller
public class FrameController {

	@Autowired
	private SmsMenuService smsMenuService;

	@RequiresAuthentication
	@RequestMapping("/sms/frame/frame.do")
	public ModelAndView doLogin(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		List<SmsMenuView> list = smsMenuService.getSystemAllMenuTree();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuList", list);
		return new ModelAndView("/sms/frame/frame.jsp", map);
	}
}
