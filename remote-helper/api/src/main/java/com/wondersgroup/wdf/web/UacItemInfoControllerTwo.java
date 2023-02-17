package com.wondersgroup.wdf.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import com.wondersgroup.wdf.service.UacItemInfoServiceTwo;

import coral.base.util.RequestWrapper;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 事项信息 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemInfoControllerTwo {

	@RequestMapping("/wdf/uacItemInfoTwo/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItemInfoTwo> list = uacItemInfoServiceTwo.query(wrapper);
			result.setData(JsonUtils.toJson(list, UacItemInfoTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemInfoTwo/remove")
	public WdfResult logicDelete(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] stItemId = wrapper.getParameterValues("ST_ITEM_ID[]");
			uacItemInfoServiceTwo.logicDelete(stItemId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemInfoTwo/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemInfoTwo uacItemInfoTwo = uacItemInfoServiceTwo.saveOrUpdate(wrapper);
			if (uacItemInfoTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	//除去与主题关联的事项列表
	@RequestMapping("/wdf/uacItemInfoTwo/queryNoLinkList")
	public WdfResult queryNoLinkList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItemInfoTwo> list = uacItemInfoServiceTwo.queryNoLink(wrapper);
			result.setData(JsonUtils.toJson(list, UacItemInfoTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	//除去与事项组关联的事项列表
	@RequestMapping("/wdf/uacItemInfoTwo/queryNoGroupLinkList")
	public WdfResult queryNoGroupLink(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItemInfoTwo> list = uacItemInfoServiceTwo.queryNoGroupLink(wrapper);
			result.setData(JsonUtils.toJson(list, UacItemInfoTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}



	@Autowired
	private UacItemInfoServiceTwo uacItemInfoServiceTwo;

}
