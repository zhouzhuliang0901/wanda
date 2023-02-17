package com.wondersgroup.sms.menu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wfc.service.log.Log;

import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.service.SmsMenuService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 系统菜单表 web层控制器
 *
 * @author guicb
 * 
 */
@Controller
public class SmsMenuController {

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_menu_update" })
	@RequestMapping("/sms/menu/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsMenu.ST_MENU_ID
		String stMenuId = wrapper.getParameter(SmsMenu.ST_MENU_ID);
		if (!StringUtils.trimToEmpty(stMenuId).isEmpty()) {
			SmsMenu smsMenu = smsMenuService.get(stMenuId);
			req.setAttribute(SmsMenu.SMS_MENU, smsMenu);
		}
		return new ModelAndView("/sms/menu/edit.jsp");
	}

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_menu_info" })
	@RequestMapping("/sms/menu/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsMenu.ST_MENU_ID
		String stMenuId = wrapper.getParameter(SmsMenu.ST_MENU_ID);
		SmsMenu smsMenu = smsMenuService.get(stMenuId);
		req.setAttribute(SmsMenu.SMS_MENU, smsMenu);
		return new ModelAndView("/sms/menu/info.jsp");
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_menu" })
	@RequestMapping("/sms/menu/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			// 获取参数
			RequestWrapper wrapper = new RequestWrapper(req);
			String draw = wrapper.getParameter("draw");
			int drawInt = 0;
			if (draw != null) {
				drawInt = Integer.valueOf(draw) + 1;
				Log.info("当然调用的次数:" + drawInt);
			}
			// 获取数据
			List<SmsMenu> list = smsMenuService.query(
					wrapper);
			// 总条数
			String total = EasyUIJsonConverter.convertDataSetToJson(
						DataSet.convert(list, SmsMenu.class))
						.getString("total");
		
			JSONObject obj = new JSONObject();
			// 调用次数
			obj.put("draw", drawInt);
			// 当前总数
			obj.put("recordsTotal", list.size());
			// 选择总条数
			obj.put("recordsFiltered", total);
			// 返回数据
			obj.put("data", list);
			EasyUIHelper.writeResponse(res, obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除、批量删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_menu_bremove",
			"sms_system_menu_remove"})
	@RequestMapping("/sms/menu/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("系统菜单表删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 样表ID
			String[] roleIdList = wrapper.getParameterValues("stMenuId[]");
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("stMenuId");
				if (roleId != null) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("用户菜单ID不能为空");
				}
			}
			smsMenuService.remove(roleIdList);
			result = ExtAjaxReturnMessage.success("系统菜单表删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 保存
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_menu_update" })
	@RequestMapping("/sms/menu/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = "系统菜单表保存失败。";
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsMenu smsMenu = smsMenuService.saveOrUpdate(wrapper);
			if (smsMenu != null)
				result = "系统菜单表保存成功,";
		// 返回json数据
		JSONObject obj = new JSONObject();
		// 用户空间下所有的目录
		obj.put("SmsMenu", smsMenu);
		// 保存状态
		obj.put("result", result);
		EasyUIHelper.writeResponse(res, obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/sms/menu/checkMenuCode.do")
	public void checkMenuCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			// 获取参数
			RequestWrapper wrapper = new RequestWrapper(req);
			boolean result = true;  
			// 检查数据是否存在
			result = smsMenuService.checkMenuCode(wrapper);
			// 返回json数据
			JSONObject obj = new JSONObject();
			// 用户空间下所有的目录
			obj.put("data", result);
			EasyUIHelper.writeResponse(res, obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Autowired
	private SmsMenuService smsMenuService;
}
