package com.wondersgroup.api.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.api.bean.ApidocModInter;
import com.wondersgroup.api.service.ApidocModInterService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 模块关联接口 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class ApidocModInterController {

	@RequestMapping("/apidocModInter/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocModInter.ST_MODULE_ID
		String stModuleId = wrapper.getParameter(ApidocModInter.ST_MODULE_ID);
		// ApidocModInter.ST_INTERFACE_ID
		String stInterfaceId = wrapper.getParameter(ApidocModInter.ST_INTERFACE_ID);
		if (!StringUtils.trimToEmpty(stModuleId).isEmpty() && !StringUtils.trimToEmpty(stInterfaceId).isEmpty()) {
			ApidocModInter apidocModInter = apidocModInterService.get(stModuleId, stInterfaceId);
			req.setAttribute(ApidocModInter.APIDOC_MOD_INTER, apidocModInter);
		}
		return new ModelAndView("/apidocModInter/edit.jsp");
	}

	@RequestMapping("/apidocModInter/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocModInter.ST_MODULE_ID
		String stModuleId = wrapper.getParameter(ApidocModInter.ST_MODULE_ID);
		// ApidocModInter.ST_INTERFACE_ID
		String stInterfaceId = wrapper.getParameter(ApidocModInter.ST_INTERFACE_ID);
		ApidocModInter apidocModInter = apidocModInterService.get(stModuleId, stInterfaceId);
		req.setAttribute(ApidocModInter.APIDOC_MOD_INTER, apidocModInter);
		return new ModelAndView("/apidocModInter/info.jsp");
	}

	@RequestMapping("/apidocModInter/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<ApidocModInter> list = apidocModInterService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, ApidocModInter.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/apidocModInter/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("模块关联接口删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// ApidocModInter.ST_MODULE_ID
			String stModuleId = wrapper.getParameter(ApidocModInter.ST_MODULE_ID);
			// ApidocModInter.ST_INTERFACE_ID
			String stInterfaceId = wrapper.getParameter(ApidocModInter.ST_INTERFACE_ID);
			apidocModInterService.remove(stModuleId, stInterfaceId);
			result = ExtAjaxReturnMessage.success("模块关联接口删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/apidocModInter/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("模块关联接口保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			ApidocModInter apidocModInter = apidocModInterService.saveOrUpdate(wrapper);
			if (apidocModInter != null)
				result = ExtAjaxReturnMessage.success("模块关联接口保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private ApidocModInterService apidocModInterService;

}
