package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacAttachLink;
import com.wondersgroup.wdf.service.UacAttachLinkService;
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
 * 电子材料关联附件 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class UacAttachLinkController {

	@RequestMapping("/uacAttachLink/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacAttachLink.ST_ESTUFF_ID
		String stEstuffId = wrapper.getParameter(UacAttachLink.ST_ESTUFF_ID);
		// UacAttachLink.ST_ATTACH_ID
		String stAttachId = wrapper.getParameter(UacAttachLink.ST_ATTACH_ID);
		if (!StringUtils.trimToEmpty(stEstuffId).isEmpty() && !StringUtils.trimToEmpty(stAttachId).isEmpty()) {
			UacAttachLink uacAttachLink = uacAttachLinkService.get(stEstuffId, stAttachId);
			req.setAttribute(UacAttachLink.UAC_ATTACH_LINK, uacAttachLink);
		}
		return new ModelAndView("/uacAttachLink/edit.jsp");
	}

	@RequestMapping("/uacAttachLink/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacAttachLink.ST_ESTUFF_ID
		String stEstuffId = wrapper.getParameter(UacAttachLink.ST_ESTUFF_ID);
		// UacAttachLink.ST_ATTACH_ID
		String stAttachId = wrapper.getParameter(UacAttachLink.ST_ATTACH_ID);
		UacAttachLink uacAttachLink = uacAttachLinkService.get(stEstuffId, stAttachId);
		req.setAttribute(UacAttachLink.UAC_ATTACH_LINK, uacAttachLink);
		return new ModelAndView("/uacAttachLink/info.jsp");
	}

	@RequestMapping("/uacAttachLink/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacAttachLink> list = uacAttachLinkService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacAttachLink.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacAttachLink/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("电子材料关联附件删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacAttachLink.ST_ESTUFF_ID
			String stEstuffId = wrapper.getParameter(UacAttachLink.ST_ESTUFF_ID);
			// UacAttachLink.ST_ATTACH_ID
			String stAttachId = wrapper.getParameter(UacAttachLink.ST_ATTACH_ID);
			uacAttachLinkService.remove(stEstuffId, stAttachId);
			result = ExtAjaxReturnMessage.success("电子材料关联附件删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacAttachLink/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("电子材料关联附件保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacAttachLink uacAttachLink = uacAttachLinkService.saveOrUpdate(wrapper);
			if (uacAttachLink != null)
				result = ExtAjaxReturnMessage.success("电子材料关联附件保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	/**
	 * 获取多文件列表
	 * @param req ST_ESTUFF_ID
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/uacAttachLink/getFileList")
	public void getFileList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacAttachLink> list = uacAttachLinkService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacAttachLink.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@Autowired
	private UacAttachLinkService uacAttachLinkService;

}
