package com.wondersgroup.sms.user.web;

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

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.log.Log;

import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.service.InfoPubService;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.service.SmsOrganService;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.bean.SmsUserLink;
import com.wondersgroup.sms.user.service.UserService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

@Controller
public class UserController {
	
	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_user_add" })
	@RequestMapping("/sms/user/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsUser.ST_USER_ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		if (!StringUtils.trimToEmpty(stUserId).isEmpty()) {
			SmsUser smsUser = userService.get(stUserId);
			req.setAttribute(SmsUser.SMS_USER, smsUser);
		}
		return new ModelAndView("/sms/user/edit.jsp");
	}

	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_user_info" })
	@RequestMapping("/sms/user/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsUser.ST_USER_ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		SmsUser smsUser = userService.get(stUserId);
		if(smsUser.getStOrganId()!=null){
			SmsOrgan smsOrgan = smsOrganService.get(smsUser.getStOrganId());
			smsUser.setStOrganId(smsOrgan.getStOrganName());
		}
		if(smsUser.getStAreaId()!=null){
			InfopubArea infopubArea = infoPubService.get(smsUser.getStAreaId());
			smsUser.setStAreaId(infopubArea.getStAreaName());
		}
		req.setAttribute(SmsUser.SMS_USER, smsUser);
		return new ModelAndView("/sms/user/info.jsp");
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_user_info" })
	@RequestMapping("/sms/user/list.do")
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
		List<SmsUser> userList = userService.query(
				wrapper);
		// 总条数
		String total = EasyUIJsonConverter.convertDataSetToJson(
				DataSet.convert(userList, SmsUser.class))
				.getString("total");
		JSONObject obj = new JSONObject();
		// 调用次数
		obj.put("draw", drawInt);
		// 当前总数
		obj.put("recordsTotal", userList.size());
		// 选择总条数
		obj.put("recordsFiltered", total);
		// 返回数据
		obj.put("data", userList);
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * 删除、批量删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_user_bremove", 
			"sms_system_user_remove"})
	@RequestMapping("/sms/user/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("用户表删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 用户ID
			String[] userIdList = wrapper.getParameterValues("userId[]");
			if (userIdList.length == 0) {
				String userId = wrapper.getParameter("userId");
				if (userId != null) {
					userIdList = new String[1];
					userIdList[0] = userId;
				} else {
					throw new NullPointerException("用户空间ID不能为空");
				}
			}
			userService.remove(userIdList);
			result = ExtAjaxReturnMessage.success("用户表删除成功。", null).toString();
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
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_user_update" ,
			"sms_system_user_add"})
	@RequestMapping("/sms/user/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("用户表保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsUser smsUser = userService.saveOrUpdate(wrapper);
			if (smsUser != null)
				result = ExtAjaxReturnMessage.success("用户表保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	/**
	 * 添加用户关联信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sms/user/editUserLink.do")
	public ModelAndView editUserLink(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsUser.ST_USER_ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		SmsUser smsUser = userService.get(stUserId);
		req.setAttribute(SmsUser.SMS_USER, smsUser);
		return new ModelAndView("/sms/user/user-link-add.jsp");
	}
	
	/**
	 * 查找用户关联信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/user/queryUserLink.do")
	public void queryUserLink(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsUser.ST_USER_ID
		String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
		// 用户角色
		SmsUserLink userLink = userService.queryUserLink(stUserId);
		
		JSONObject obj = new JSONObject();
		// 返回数据
		obj.put("data", userLink);
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 添加用户关联信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/user/addUserLink.do")
	public void addUserLink(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = "用户关联信息保存失败";
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 添加用户角色
			userService.addUserLink(wrapper);
			// 保存成功
			result = "用户关联信息保存成功";
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		// 返回数据
		obj.put("result", result);
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 添加用户关联信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/user/checkPassWord.do")
	public void checkPassWord(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			// 获取参数
			RequestWrapper wrapper = new RequestWrapper(req);
			boolean result = true;  
			// 检查数据是否存在
			result = userService.checkPassWord(wrapper);
			// 返回json数据
			JSONObject obj = new JSONObject();
			// 用户空间下所有的目录
			obj.put("data", result);
			EasyUIHelper.writeResponse(res, obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 保存修改密码
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/user/savePassWord.do")
	public void savePassWord(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("密码修改失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsUser smsUser = userService.savePassWord(wrapper);
			if (smsUser != null)
				result = ExtAjaxReturnMessage.success("密码修改成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	@RequestMapping("/sms/organ/init.do")
	public void init(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = userService.areaList(httpReqRes);
			List<InfopubArea> infopubAreaTypeList = (List<InfopubArea>) json.get("data");
			InfopubArea infopubArea = new InfopubArea();
			//infopubDeviceType.setStTypeId(null);
			//infopubDeviceTypeList.add(infopubArea);
			obj.put("area", json);
			//obj.put("deviceinfo", infoPubService.deviceInfoList(httpReqRes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsOrganService smsOrganService;
	@Autowired
	private InfoPubService infoPubService;
}
