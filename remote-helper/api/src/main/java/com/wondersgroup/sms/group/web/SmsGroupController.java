package com.wondersgroup.sms.group.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.wondersgroup.sms.user.bean.SmsUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.group.bean.SmsGroup;
import com.wondersgroup.sms.group.service.SmsGroupService;
import com.wondersgroup.sms.menu.bean.SmsMenu;

import coral.base.util.RequestWrapper;

/**
 * 用户组 web层控制器
 *
 */
@RestController
public class SmsGroupController {
	@Autowired
	private SmsGroupService smsGroupService;

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sms/group/edit")
	public WdfResult edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stGroupId = wrapper.getParameter(SmsGroup.ST_GROUP_ID);
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			SmsGroup smsGroup = smsGroupService.get(stGroupId);
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
			map.put("menuId",menuId);
			// 菜单名称
			map.put("menuName",menuName);
		}
		JSONObject json = new JSONObject(map);
		return WdfResult.getResult().success().setData(json);
	}

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sms/group/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stGroupId = wrapper.getParameter(SmsGroup.ST_GROUP_ID);
		SmsGroup smsGroup = smsGroupService.get(stGroupId);
		// 获取组菜单
		List<SmsMenu> list = smsGroupService.queryGroupMenu(stGroupId);
		List<String> listId = new ArrayList<>();
		for (SmsMenu menu : list) {
			listId.add(menu.getStMenuId());
		}
		smsGroup.setMenuIds(listId);
		return WdfResult.getResult().success().setData(JsonUtils.toJson(smsGroup));
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/group/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<SmsGroup> list = smsGroupService.query(wrapper);
			JsonNode node=JsonUtils.toJson(list, SmsGroup.class);
			result.setData(node);
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 删除和批量删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/group/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 样表ID
			String[] groupIdList = wrapper.getParameterValues(SmsGroup.ST_GROUP_IDS);
			if (groupIdList.length == 0) {
				String groupId = wrapper.getParameter("stGroupId");
				if (!StringUtils.isBlank(groupId)) {
					groupIdList = new String[1];
					groupIdList[0] = groupId;
				} else {
					throw new NullPointerException("用户组ID不能为空");
				}
			}
			smsGroupService.remove(groupIdList);
			result.success().setMsg("用户组信息表删除成功");
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
	@RequestMapping("/sms/group/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsGroup smsGroup = smsGroupService.saveOrUpdate(wrapper);
			if (smsGroup != null)
				result.success().setMsg("用户组保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}


}
