package com.wondersgroup.wdf.uacItems.web;

import com.wondersgroup.wdf.uacItems.dao.UacItems;
import com.wondersgroup.wdf.uacItems.service.UacItemsService;
import coral.base.util.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 主题事项（综合） web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemsController {

//	@RequestMapping("/wdf/uacItems/edit")
//	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
//			throws IOException {
//		RequestWrapper wrapper = new RequestWrapper(req);
//		// UacItems.ST_ITEMS_ID
//		String stItemsId = wrapper.getParameter(UacItems.ST_ITEMS_ID);
//		if (!StringUtils.trimToEmpty(stItemsId).isEmpty()) {
//			UacItems uacItems = uacItemsService.get(stItemsId);
//			req.setAttribute(UacItems.UAC_ITEMS, uacItems);
//		}
//		return new ModelAndView("/uacItems/edit.jsp");
//	}

	@RequestMapping("/wdf/uacItems/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stItemsId = wrapper.getParameter(UacItems.ST_ITEMS_ID);
		UacItems uacItems = uacItemsService.get(stItemsId);
		req.setAttribute(UacItems.UAC_ITEMS, uacItems);
		return WdfResult.getResult().success().setData(JsonUtils.toJson(uacItems));
	}

	@RequestMapping("/wdf/uacItems/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItems> list = uacItemsService.query(wrapper);
			result.setData(JsonUtils.toJson(list,UacItems.class));

		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}


	@RequestMapping("/wdf/uacItems/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);

			String stItemsId = wrapper.getParameter(UacItems.ST_ITEMS_ID);
			uacItemsService.remove(stItemsId);
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}

		return result;
	}


	@RequestMapping("/wdf/uacItems/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();

		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItems uacItems = uacItemsService.saveOrUpdate(wrapper);

		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
//		EasyUIHelper.writeFormResponse(res, result);
		return result;
	}

	@Autowired
	private UacItemsService uacItemsService;

}
