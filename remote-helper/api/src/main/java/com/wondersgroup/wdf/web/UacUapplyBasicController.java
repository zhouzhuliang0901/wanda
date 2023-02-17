package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacUapplyBasic;
import com.wondersgroup.wdf.service.UacUapplyBasicService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 综合受理一表式 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class UacUapplyBasicController {

	@RequestMapping("/uacUapplyBasic/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyBasic.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUapplyBasic.ST_APPLY_ID);
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			UacUapplyBasic uacUapplyBasic = uacUapplyBasicService.get(stApplyId);
			req.setAttribute(UacUapplyBasic.UAC_UAPPLY_BASIC, uacUapplyBasic);
		}
		return new ModelAndView("/uacUapplyBasic/edit.jsp");
	}

	@RequestMapping("/uacUapplyBasic/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyBasic.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUapplyBasic.ST_APPLY_ID);
		UacUapplyBasic uacUapplyBasic = uacUapplyBasicService.get(stApplyId);
		req.setAttribute(UacUapplyBasic.UAC_UAPPLY_BASIC, uacUapplyBasic);
		return new ModelAndView("/uacUapplyBasic/info.jsp");
	}

	@RequestMapping("/uacUapplyBasic/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyBasic> list = uacUapplyBasicService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyBasic.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyBasic/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合受理一表式删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUapplyBasic.ST_APPLY_ID
			String stApplyId = wrapper.getParameter(UacUapplyBasic.ST_APPLY_ID);
			uacUapplyBasicService.remove(stApplyId);
			result = ExtAjaxReturnMessage.success("综合受理一表式删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyBasic/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合受理一表式保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUapplyBasic uacUapplyBasic = uacUapplyBasicService.saveOrUpdate(wrapper);
			if (uacUapplyBasic != null)
				result = ExtAjaxReturnMessage.success("综合受理一表式保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacUapplyBasicService uacUapplyBasicService;

}
