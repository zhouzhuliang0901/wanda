package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacItemStuffTwo;
import com.wondersgroup.wdf.service.UacItemStuffTwoService;
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
 * 事项材料 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemStuffTwoController {

	/**
	 * 根据事项取材料列表，参数 事项ID：ST_ITEM_ID
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/wdf/uacItemStuff/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacItemStuffTwo> list =uacItemStuffTwoService.query(wrapper);
			result.setData(JsonUtils.toJson(list,UacItemStuffTwo.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemStuff/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] stItemStuffId = wrapper.getParameterValues("ST_ITEM_STUFF_ID[]");
			uacItemStuffTwoService.logicDelete(stItemStuffId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacItemStuff/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemStuffTwo UacItemStuffTwo = uacItemStuffTwoService.saveOrUpdate(wrapper);
			if (UacItemStuffTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacItemStuffTwoService uacItemStuffTwoService;

}
