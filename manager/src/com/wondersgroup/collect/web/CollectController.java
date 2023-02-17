package com.wondersgroup.collect.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.config.Config;

import com.wondersgroup.collect.service.CollectService;

/**
 * 终端设备控制层
 * 
 * @author guicb
 *
 */
@Controller
public class CollectController {
	@Autowired
	private CollectService collectService;

	/**
	 * 终端发送消息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/collect/post.do")
	public void post(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			collectService.post(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 终端提交截图数据
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/collect/snapshots.do")
	public void snapshots(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			collectService.snapshots(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 终端跳转
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/collect/redirect.do")
	public void redirect(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String url = Config.get("infopub.error.redirect");
		try {
			String resultUrl = collectService.redirect(httpReqRes);
			if (resultUrl != null
					&& !StringUtils.trimToEmpty(resultUrl).isEmpty()) {
				url = resultUrl;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect(url);
	}
}
