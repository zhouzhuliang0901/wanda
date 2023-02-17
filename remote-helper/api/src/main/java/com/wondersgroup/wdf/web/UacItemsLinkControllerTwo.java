package com.wondersgroup.wdf.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.wdf.dao.ItemsLinkItemTwo;
import com.wondersgroup.wdf.dao.UacStuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.wdf.dao.UacItemsLinkTwo;
import com.wondersgroup.wdf.service.UacItemsLinkServiceTwo;

import coral.base.util.RequestWrapper;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 主题服务事项关联 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemsLinkControllerTwo {

	//根据主题id查事项主题关联
	@RequestMapping("/wdf/uacItemsLink/queryByStItemsId")
	public WdfResult queryByStItemsId(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<ItemsLinkItemTwo> list = uacItemsLinkServiceTwo.queryByStItemsId(wrapper);
			result.setData(JsonUtils.toJson(list,ItemsLinkItemTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}


	//根据事项id查事项主题关联
	@RequestMapping("/wdf/uacItemsLink/queryByStItemId")
	public WdfResult queryByStItemId(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<ItemsLinkItemTwo> list = uacItemsLinkServiceTwo.queryByStItemId(wrapper);
			result.setData(JsonUtils.toJson(list,ItemsLinkItemTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemsLinkTwo/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacItemsLink.ST_ITEMS_ID
			String[] stItemsId = wrapper.getParameterValues("ST_ITEMS_ID[]");
			// UacItemsLink.ST_ITEM_ID
			String[] stItemId = wrapper.getParameterValues("ST_ITEM_ID[]");
			uacItemsLinkServiceTwo.remove(stItemsId, stItemId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemsLinkTwo/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemsLinkTwo uacItemsLinkTwo = uacItemsLinkServiceTwo.save(wrapper);
			if (uacItemsLinkTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemsLinkTwo/saveitem")
	public WdfResult saveitem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemsLinkTwo uacItemsLinkTwo = uacItemsLinkServiceTwo.saveitem(wrapper);
			if (uacItemsLinkTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	//事项主题关联列表
	@RequestMapping("/wdf/uacItemsLink/itemsLinkList")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<ItemsLinkItemTwo> list = uacItemsLinkServiceTwo.select(wrapper);
			result.setData(JsonUtils.toJson(list,ItemsLinkItemTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacItemsLinkServiceTwo uacItemsLinkServiceTwo;

}
