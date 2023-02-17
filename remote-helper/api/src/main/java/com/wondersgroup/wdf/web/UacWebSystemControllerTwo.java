package com.wondersgroup.wdf.web;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.wdf.dao.UacWebSystemTwo;
import com.wondersgroup.wdf.service.UacWebSystemServiceTwo;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 系统信息 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class UacWebSystemControllerTwo {

	@RequestMapping("/uacWebSystem/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacWebSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacWebSystemTwo.ST_WEB_SYSTEM_ID);
		if (!StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
			UacWebSystemTwo uacWebSystemTwo = uacWebSystemServiceTwo.get(stWebSystemId);
			req.setAttribute(UacWebSystemTwo.UAC_WEB_SYSTEM, uacWebSystemTwo);
		}
		return new ModelAndView("/uacWebSystem/edit.jsp");
	}

	@RequestMapping("/uacWebSystem/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacWebSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacWebSystemTwo.ST_WEB_SYSTEM_ID);
		UacWebSystemTwo uacWebSystemTwo = uacWebSystemServiceTwo.get(stWebSystemId);
		req.setAttribute(UacWebSystemTwo.UAC_WEB_SYSTEM, uacWebSystemTwo);
		return new ModelAndView("/uacWebSystem/info.jsp");
	}

	@RequestMapping("/uacWebSystem/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacWebSystemTwo> list = uacWebSystemServiceTwo.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacWebSystemTwo.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacWebSystem/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("系统信息删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacWebSystem.ST_WEB_SYSTEM_ID
			String stWebSystemId = wrapper.getParameter(UacWebSystemTwo.ST_WEB_SYSTEM_ID);
			uacWebSystemServiceTwo.remove(stWebSystemId);
			result = ExtAjaxReturnMessage.success("系统信息删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacWebSystem/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("系统信息保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacWebSystemTwo uacWebSystemTwo = uacWebSystemServiceTwo.saveOrUpdate(wrapper);
			if (uacWebSystemTwo != null)
				result = ExtAjaxReturnMessage.success("系统信息保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacWebSystemServiceTwo uacWebSystemServiceTwo;

}
