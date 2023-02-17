package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacUapplyFeedback;
import com.wondersgroup.wdf.service.UacUapplyFeedbackService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 综合办件反馈 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacUapplyFeedbackController {

	@RequestMapping("/uacUapplyFeedback/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyFeedback.ST_FEEDBACK_ID
		String stFeedbackId = wrapper.getParameter(UacUapplyFeedback.ST_FEEDBACK_ID);
		if (!StringUtils.trimToEmpty(stFeedbackId).isEmpty()) {
			UacUapplyFeedback uacUapplyFeedback = uacUapplyFeedbackService.get(stFeedbackId);
			req.setAttribute(UacUapplyFeedback.UAC_UAPPLY_FEEDBACK, uacUapplyFeedback);
		}
		return new ModelAndView("/uacUapplyFeedback/edit.jsp");
	}

	@RequestMapping("/uacUapplyFeedback/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyFeedback.ST_FEEDBACK_ID
		String stFeedbackId = wrapper.getParameter(UacUapplyFeedback.ST_FEEDBACK_ID);
		UacUapplyFeedback uacUapplyFeedback = uacUapplyFeedbackService.get(stFeedbackId);
		req.setAttribute(UacUapplyFeedback.UAC_UAPPLY_FEEDBACK, uacUapplyFeedback);
		return new ModelAndView("/uacUapplyFeedback/info.jsp");
	}

	@RequestMapping("/uacUapplyFeedback/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyFeedback> list = uacUapplyFeedbackService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyFeedback.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyFeedback/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合办件反馈删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUapplyFeedback.ST_FEEDBACK_ID
			String stFeedbackId = wrapper.getParameter(UacUapplyFeedback.ST_FEEDBACK_ID);
			uacUapplyFeedbackService.remove(stFeedbackId);
			result = ExtAjaxReturnMessage.success("综合办件反馈删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyFeedback/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合办件反馈保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUapplyFeedback uacUapplyFeedback = uacUapplyFeedbackService.saveOrUpdate(wrapper);
			if (uacUapplyFeedback != null)
				result = ExtAjaxReturnMessage.success("综合办件反馈保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}



	@Autowired
	private UacUapplyFeedbackService uacUapplyFeedbackService;

}
