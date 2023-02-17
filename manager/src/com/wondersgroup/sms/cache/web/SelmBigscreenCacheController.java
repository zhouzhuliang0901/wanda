package com.wondersgroup.sms.cache.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;

import com.wondersgroup.sms.cache.bean.SelmBigscreenCache;
import com.wondersgroup.sms.cache.service.SelmBigscreenCacheService;

import coral.base.quartz.Task;
import coral.base.quartz.TaskScheduled;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 大屏统计缓存表 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmBigscreenCacheController {
	
	@Autowired
	private SelmBigscreenCacheService selmBigscreenCacheService;
	
	
	/**
	 * 获取缓存数据
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/selmBigscreenCache/info.do")
	public void info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = selmBigscreenCacheService.getCacheData(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		
	}
	
	

	

}
