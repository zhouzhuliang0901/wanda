package com.wondersgroup.wdf.uacItemSystem.web;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.wondersgroup.wdf.uacItemSystem.dao.UacItemSystem;
import com.wondersgroup.wdf.uacItemSystem.service.UacItemSystemService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 事项关联系统 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemSystemController {

	@RequestMapping("/wdf/uacItemSystem/edit")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacItemSystem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemSystem.ST_ITEM_ID);
		// UacItemSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacItemSystem.ST_WEB_SYSTEM_ID);
		if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
			UacItemSystem uacItemSystem = uacItemSystemService.get(stItemId, stWebSystemId);
			req.setAttribute(UacItemSystem.UAC_ITEM_SYSTEM, uacItemSystem);
		}
		return new ModelAndView("/uacItemSystem/edit.jsp");
	}

	@RequestMapping("/wdf/uacItemSystem/info")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacItemSystem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemSystem.ST_ITEM_ID);
		// UacItemSystem.ST_WEB_SYSTEM_ID
		String stWebSystemId = wrapper.getParameter(UacItemSystem.ST_WEB_SYSTEM_ID);
		UacItemSystem uacItemSystem = uacItemSystemService.get(stItemId, stWebSystemId);
		req.setAttribute(UacItemSystem.UAC_ITEM_SYSTEM, uacItemSystem);
		return new ModelAndView("/uacItemSystem/info.jsp");
	}

	@RequestMapping("/wdf/uacItemSystem/list")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacItemSystem> list = uacItemSystemService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacItemSystem.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/wdf/uacItemSystem/remove")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("事项关联系统删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacItemSystem.ST_ITEM_ID
			String stItemId = wrapper.getParameter(UacItemSystem.ST_ITEM_ID);
			// UacItemSystem.ST_WEB_SYSTEM_ID
			String stWebSystemId = wrapper.getParameter(UacItemSystem.ST_WEB_SYSTEM_ID);
			uacItemSystemService.remove(stItemId, stWebSystemId);
			result = ExtAjaxReturnMessage.success("事项关联系统删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/wdf/uacItemSystem/save")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("事项关联系统保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemSystem uacItemSystem = uacItemSystemService.saveOrUpdate(wrapper);
			if (uacItemSystem != null)
				result = ExtAjaxReturnMessage.success("事项关联系统保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacItemSystemService uacItemSystemService;

}
