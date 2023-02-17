package com.wondersgroup.wdf.uacItemsLink.web;


import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLink;
import com.wondersgroup.wdf.uacItemsLink.service.UacItemsLinkService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import reindeer.base.bean.WdfResult;


/**
 * 主题服务事项关联 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemsLinkController {

	@RequestMapping("/wdf/uacItemsLink/edit")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacItemsLink.ST_ITEMS_ID
		String stItemsId = wrapper.getParameter(UacItemsLink.ST_ITEMS_ID);
		// UacItemsLink.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemsLink.ST_ITEM_ID);
		if (!StringUtils.trimToEmpty(stItemsId).isEmpty() && !StringUtils.trimToEmpty(stItemId).isEmpty()) {
			UacItemsLink uacItemsLink = uacItemsLinkService.get(stItemsId, stItemId);
			req.setAttribute(UacItemsLink.UAC_ITEMS_LINK, uacItemsLink);
		}
		return new ModelAndView("/uacItemsLink/edit.jsp");
	}

	@RequestMapping("/wdf/uacItemsLink/info")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacItemsLink.ST_ITEMS_ID
		String stItemsId = wrapper.getParameter(UacItemsLink.ST_ITEMS_ID);
		// UacItemsLink.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemsLink.ST_ITEM_ID);
		UacItemsLink uacItemsLink = uacItemsLinkService.get(stItemsId, stItemId);
		req.setAttribute(UacItemsLink.UAC_ITEMS_LINK, uacItemsLink);
		return new ModelAndView("/uacItemsLink/info.jsp");
	}

	@RequestMapping("/wdf/uacItemsLink/list")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacItemsLink> list = uacItemsLinkService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacItemsLink.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/wdf/uacItemsLink/remove")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("主题服务事项关联删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacItemsLink.ST_ITEMS_ID
			String stItemsId = wrapper.getParameter(UacItemsLink.ST_ITEMS_ID);
			// UacItemsLink.ST_ITEM_ID
			String stItemId = wrapper.getParameter(UacItemsLink.ST_ITEM_ID);
			uacItemsLinkService.remove(stItemsId, stItemId);
			result = ExtAjaxReturnMessage.success("主题服务事项关联删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/wdf/uacItemsLink/save")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("主题服务事项关联保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemsLink uacItemsLink = uacItemsLinkService.saveOrUpdate(wrapper);
			if (uacItemsLink != null)
				result = ExtAjaxReturnMessage.success("主题服务事项关联保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacItemsLinkService uacItemsLinkService;

}
