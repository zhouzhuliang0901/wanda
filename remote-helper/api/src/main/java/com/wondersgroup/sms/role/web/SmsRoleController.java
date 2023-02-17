package com.wondersgroup.sms.role.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.service.SmsMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.service.SmsRoleService;

import coral.base.util.RequestWrapper;
/**
 * 角色表 web层控制器
 *
 */
@RestController
public class SmsRoleController {
	@Autowired
	private SmsRoleService smsRoleService;
	@Autowired
	private SmsMenuService smsMenuService;

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sms/role/edit")
	public WdfResult edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		RequestWrapper wrapper = new RequestWrapper(req);
		String stRoleId = wrapper.getParameter(SmsRole.ST_ROLE_ID);
		if (!StringUtils.trimToEmpty(stRoleId).isEmpty()) {
			SmsRole smsRole = smsRoleService.get(stRoleId);
			result.setData(JsonUtils.toJson(smsRole));

		}
		return result;
	}

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sms/role/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stRoleId = wrapper.getParameter(SmsRole.ST_ROLE_ID);
		SmsRole smsRole = smsRoleService.get(stRoleId);
		// 角色下所有菜单
		List<SmsMenu> list = smsRoleService.queryRoleMenu(stRoleId);

		List<String> listId = new ArrayList<>();
		for (SmsMenu menu : list) {
			listId.add(menu.getStMenuId());
		}
		smsRole.setMenuIds(listId);
		/*SmsRoleVo smsRoleVo = new SmsRoleVo();
		smsRoleVo.setST_ROLE_ID(smsRole.getStRoleId());
		smsRoleVo.setST_ROLE_CODE(smsRole.getStRoleCode());
		smsRoleVo.setST_ROLE_NAME(smsRole.getStRoleName());
		smsRoleVo.setST_DESC(smsRole.getStDesc());
		smsRoleVo.setNM_ORDER(smsRole.getNmOrder());
		smsRoleVo.setMenuIds(listId);*/

		return WdfResult.getResult().success().setData(JsonUtils.toJson(smsRole));


	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/role/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<SmsRole> list = smsRoleService.query(wrapper);
			result.setData(JsonUtils.toJson(list, SmsRole.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}
	/**
	 * 删除、批量删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/role/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] roleIdList = wrapper.getParameterValues(SmsRole.ST_ROLE_IDS);
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
			result.success().setMsg("角色表删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 保存
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/role/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsRole smsRole = smsRoleService.saveOrUpdate(wrapper);
			if (smsRole != null)
				result.success().setMsg("角色表保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}




}
