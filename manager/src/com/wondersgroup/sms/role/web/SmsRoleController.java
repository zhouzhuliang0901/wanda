package com.wondersgroup.sms.role.web;

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
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.service.SmsRoleService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 角色表 web层控制器
 *
 * @author guicb
 * 
 */
@Controller
public class SmsRoleController {

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_role_update" })
	@RequestMapping("/sms/role/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsRole.ST_ROLE_ID
		String stRoleId = wrapper.getParameter(SmsRole.ST_ROLE_ID);
		if (!StringUtils.trimToEmpty(stRoleId).isEmpty()) {
			SmsRole smsRole = smsRoleService.get(stRoleId);
			// 角色下所有菜单
			List<SmsMenu> list = smsRoleService.queryRoleMenu(stRoleId);
			// 菜单ID
			String menuId = "";
			// 菜单名称
			String menuName = "";
			for (SmsMenu menu : list) {
				menuId += menu.getStMenuId()+",";
				menuName += menu.getStMenuName()+",";
			}
			// 菜单ID
			req.setAttribute("menuId", menuId);
			// 菜单名称
			req.setAttribute("menuName", menuName);
			// 角色
			req.setAttribute(SmsRole.SMS_ROLE, smsRole);
		}
		return new ModelAndView("/sms/role/edit.jsp");
	}

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_role_info" })
	@RequestMapping("/sms/role/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsRole.ST_ROLE_ID
		String stRoleId = wrapper.getParameter(SmsRole.ST_ROLE_ID);
		SmsRole smsRole = smsRoleService.get(stRoleId);
		// 角色下所有菜单
		List<SmsMenu> list = smsRoleService.queryRoleMenu(stRoleId);
		// 菜单名称
		String menuName = "";
		for (SmsMenu menu : list) {
			menuName += menu.getStMenuName()+",";
		}
		// 菜单名称
		req.setAttribute("menuName", menuName);
		req.setAttribute(SmsRole.SMS_ROLE, smsRole);
		return new ModelAndView("/sms/role/info.jsp");
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_role" })
	@RequestMapping("/sms/role/list.do")
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
			List<SmsRole> list = smsRoleService.query(
					wrapper);
			// 总条数
			String total = EasyUIJsonConverter.convertDataSetToJson(
						DataSet.convert(list, SmsRole.class))
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
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_role_bremove" ,
			"sms_system_role_remove"})
	@RequestMapping("/sms/role/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("角色表删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] roleIdList = wrapper.getParameterValues("roleId[]");
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("roleId");
				if (roleId != null) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("用户角色ID不能为空");
				}
			}
			smsRoleService.remove(roleIdList);
			result = ExtAjaxReturnMessage.success("角色表删除成功。", null).toString();
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
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_role_update" })
	@RequestMapping("/sms/role/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("角色表保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsRole smsRole = smsRoleService.saveOrUpdate(wrapper);
			if (smsRole != null)
				result = ExtAjaxReturnMessage.success("角色表保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private SmsRoleService smsRoleService;

}
