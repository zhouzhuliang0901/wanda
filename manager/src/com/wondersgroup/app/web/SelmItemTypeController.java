package com.wondersgroup.app.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.app.bean.SelmItemType;
import com.wondersgroup.app.service.SelmItemTypeService;

import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;

/**
 * 事项类别 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmItemTypeController {

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_item_type_edit" })
	@RequestMapping("/app/selmItemType/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmItemType.ST_ITEM_TYPE_ID
		String stItemTypeId = wrapper.getParameter(SelmItemType.ST_ITEM_TYPE_ID);
		if (!StringUtils.trimToEmpty(stItemTypeId).isEmpty()) {
			SelmItemType selmItemType = selmItemTypeService.get(stItemTypeId);
			req.setAttribute(SelmItemType.SELM_ITEM_TYPE, selmItemType);
		}
		return new ModelAndView("/app/selmItemType/edit.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_item_type_info" })
	@RequestMapping("/app/selmItemType/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmItemType.ST_ITEM_TYPE_ID
		String stItemTypeId = wrapper.getParameter(SelmItemType.ST_ITEM_TYPE_ID);
		SelmItemType selmItemType = selmItemTypeService.get(stItemTypeId);
		req.setAttribute(SelmItemType.SELM_ITEM_TYPE, selmItemType);
		return new ModelAndView("/app/selmItemType/itemedit.jsp");
	}
	
	@CheckPermissions(roles = { "admin" }, permissions = { "selm_item_type_bindItem" })
	@RequestMapping("/app/selmItem/itemTypeAdd.do")
	public ModelAndView itemTypeAdd(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmItemType.ST_ITEM_TYPE_ID
		String stItemTypeId = wrapper.getParameter(SelmItemType.ST_ITEM_TYPE_ID);
		SelmItemType selmItemType = selmItemTypeService.get(stItemTypeId);
		req.setAttribute(SelmItemType.SELM_ITEM_TYPE, selmItemType);
		return new ModelAndView("/app/selmItemType/itemList.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_item_type" })
	@RequestMapping("/app/selmItemType/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmItemTypeService.list(httpReqRes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_item_type_remove" ,"selm_item_type_bremove"})
	@RequestMapping("/app/selmItemType/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("事项分组信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmItemTypeService.removeList(httpReqRes);
			result.success().setMsg("事项分组信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_item_type_save" })
	@RequestMapping("/app/selmItemType/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("事项类别保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmItemType selmItemType = selmItemTypeService.saveOrUpdate(wrapper);
			if (selmItemType != null)
				result = ExtAjaxReturnMessage.success("事项类别保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private SelmItemTypeService selmItemTypeService;

}
