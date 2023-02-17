package com.wondersgroup.sms.group.web;

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

import com.wondersgroup.sms.group.bean.SmsGroup;
import com.wondersgroup.sms.group.service.SmsGroupService;
import com.wondersgroup.sms.menu.bean.SmsMenu;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 用户组 web层控制器
 *
 * @author guicb
 * 
 */
@Controller
public class SmsGroupController {

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_group_info" })
	@RequestMapping("/sms/group/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsGroup.ST_GROUP_ID
		String stGroupId = wrapper.getParameter(SmsGroup.ST_GROUP_ID);
		if (!StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			SmsGroup smsGroup = smsGroupService.get(stGroupId);
			req.setAttribute(SmsGroup.SMS_GROUP, smsGroup);
			// 获取组菜单
			List<SmsMenu> list = smsGroupService.queryGroupMenu(stGroupId);
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
		}
		return new ModelAndView("/sms/group/edit.jsp");
	}

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_group_info" })
	@RequestMapping("/sms/group/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsGroup.ST_GROUP_ID
		String stGroupId = wrapper.getParameter(SmsGroup.ST_GROUP_ID);
		SmsGroup smsGroup = smsGroupService.get(stGroupId);
		// 获取组菜单
		List<SmsMenu> list = smsGroupService.queryGroupMenu(stGroupId);
		// 菜单名称
		String menuName = "";
		for (SmsMenu menu : list) {
			menuName += menu.getStMenuName()+",";
		}
		req.setAttribute(SmsGroup.SMS_GROUP, smsGroup);
		// 菜单名称
		req.setAttribute("menuName", menuName);
		return new ModelAndView("/sms/group/info.jsp");
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_group" })
	@RequestMapping("/sms/group/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		// 获取参数
		RequestWrapper wrapper = new RequestWrapper(req);
		String draw = wrapper.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当然调用的次数:" + drawInt);
		}
		// 获取数据
		List<SmsGroup> list = smsGroupService.query(wrapper);
		// 总条数
		String total = EasyUIJsonConverter.convertDataSetToJson(
				DataSet.convert(list, SmsGroup.class))
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
		
	}

	/**
	 * 删除和批量删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { 
			"sms_system_group_remove" ,	"sms_system_menu_bremove"})
	@RequestMapping("/sms/group/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("用户组删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 用户组ID
			String[] groupIdList = wrapper.getParameterValues("groupId[]");
			if (groupIdList.length == 0) {
				String groupId = wrapper.getParameter("groupId");
				if (groupId != null) {
					groupIdList = new String[1];
					groupIdList[0] = groupId;
				} else {
					throw new NullPointerException("用户组ID不能为空");
				}
			}
			smsGroupService.remove(groupIdList);
			result = ExtAjaxReturnMessage.success("用户组删除成功。", null).toString();
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
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_group_update" })
	@RequestMapping("/sms/group/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("用户组保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsGroup smsGroup = smsGroupService.saveOrUpdate(wrapper);
			if (smsGroup != null)
				result = ExtAjaxReturnMessage.success("用户组保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private SmsGroupService smsGroupService;
}
