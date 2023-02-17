package com.wondersgroup.wdf.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.wdf.dao.UacItemsTwo;
import com.wondersgroup.wdf.service.UacItemsServiceTwo;

import coral.base.util.RequestWrapper;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 主题事项（综合） web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemsControllerTwo {

	@RequestMapping("/wdf/uacItemsTwo/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItemsTwo> list = uacItemsServiceTwo.query(wrapper);
			result.setData(JsonUtils.toJson(list, UacItemsTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemsTwo/remove")
	public WdfResult logicDelete(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] stItemsId = wrapper.getParameterValues("ST_ITEMS_ID[]");
			uacItemsServiceTwo.logicDelete(stItemsId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemsTwo/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemsTwo uacItemsTwo = uacItemsServiceTwo.saveOrUpdate(wrapper);
			if (uacItemsTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemsTwo/queryNoLinkList")
	public WdfResult queryNoLinkList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItemsTwo> list = uacItemsServiceTwo.queryNoLink(wrapper);
			result.setData(JsonUtils.toJson(list, UacItemsTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacItemsServiceTwo uacItemsServiceTwo;

}
