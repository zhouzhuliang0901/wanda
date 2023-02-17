package com.wondersgroup.sms.menu.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.sms.user.bean.SmsUser;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.service.SmsMenuService;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;

/**
 * 系统菜单表 web层控制器
 *
 */
@RestController
public class SmsMenuController {
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
	@RequestMapping("/sms/menu/edit")
	public WdfResult edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMenuId = wrapper.getParameter(SmsMenu.ST_MENU_ID);
		if (!StringUtils.trimToEmpty(stMenuId).isEmpty()) {
			SmsMenu smsMenu = smsMenuService.get(stMenuId);
			result.setData(JsonUtils.toJson(smsMenu));
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
	@RequestMapping("/sms/menu/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMenuId = wrapper.getParameter(SmsMenu.ST_MENU_ID);
		SmsMenu smsMenu = smsMenuService.get(stMenuId);
		return WdfResult.getResult().success().setData(JsonUtils.toJson(smsMenu));
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/menu/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<SmsMenu> list = smsMenuService.query(wrapper);
			result.setData(JsonUtils.toJson(list, SmsMenu.class));
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
	@RequestMapping("/sms/menu/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 样表ID
			String[] roleIdList = wrapper.getParameterValues(SmsMenu.ST_MENU_ID);
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("stMenuId");
				if (!StringUtils.isBlank(roleId)) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("用户菜单ID不能为空");
				}
			}
			smsMenuService.remove(roleIdList);
			result.success().setMsg("系统菜单表删除成功");
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
	@RequestMapping("/sms/menu/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsMenu smsMenu = smsMenuService.saveOrUpdate(wrapper);
			if (smsMenu != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;

	}
	
	@RequestMapping("/sms/menu/checkMenuCode")
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


}
