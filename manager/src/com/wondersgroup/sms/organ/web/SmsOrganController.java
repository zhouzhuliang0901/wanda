package com.wondersgroup.sms.organ.web;

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
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.log.Log;

import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.service.SmsOrganService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 组织机构表 web层控制器
 *
 * @author guicb
 * 
 */
@Controller
public class SmsOrganController {
	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_user_add" })
	@RequestMapping("/sms/organ/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsOrgan.ST_ORGAN_ID
		String stOrganId = wrapper.getParameter(SmsOrgan.ST_ORGAN_ID);
		if (!StringUtils.trimToEmpty(stOrganId).isEmpty()) {
			SmsOrgan smsOrgan = smsOrganService.get(stOrganId);
			req.setAttribute(SmsOrgan.SMS_ORGAN, smsOrgan);
		}
		return new ModelAndView("/sms/organ/edit.jsp");
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
	@RequestMapping("/sms/organ/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SmsOrgan.ST_ORGAN_ID
		String stOrganId = wrapper.getParameter(SmsOrgan.ST_ORGAN_ID);
		SmsOrgan smsOrgan = smsOrganService.get(stOrganId);
		req.setAttribute(SmsOrgan.SMS_ORGAN, smsOrgan);
		return new ModelAndView("/smsOrgan/info.jsp");
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_organ" })
	@RequestMapping("/sms/organ/list.do")
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
			List<SmsOrgan> list = smsOrganService.query(
					wrapper);
			// 总条数
			String total = EasyUIJsonConverter.convertDataSetToJson(
						DataSet.convert(list, SmsOrgan.class))
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
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_organ_bremove",
			"sms_system_organ_remove"})
	@RequestMapping("/sms/organ/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("组织机构表删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 样表ID
			String[] organIdList = wrapper.getParameterValues("stOrganId[]");
			if (organIdList.length == 0) {
				String organId = wrapper.getParameter("stOrganId");
				if (organId != null) {
					organIdList = new String[1];
					organIdList[0] = organId;
				} else {
					throw new NullPointerException("组织机构ID不能为空");
				}
			}
			smsOrganService.remove(organIdList);
			result = ExtAjaxReturnMessage.success("组织机构表删除成功。", null).toString();
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
	@CheckPermissions(roles = { "admin" }, permissions = { "sms_system_organ_add" })
	@RequestMapping("/sms/organ/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("组织机构表保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsOrgan smsOrgan = smsOrganService.saveOrUpdate(wrapper);
			if (smsOrgan != null)
				result = ExtAjaxReturnMessage.success("组织机构表保存成功。", null)
						.toString();
			// 返回json数据
			JSONObject obj = new JSONObject();
			// 用户空间下所有的目录
			obj.put("SmsOrgan", smsOrgan);
			// 保存状态
			obj.put("result", result);
			EasyUIHelper.writeResponse(res, obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/sms/organ/organList.do")
	public void init(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		List<SmsOrgan> list = smsOrganService.query(
				wrapper);
		SmsOrgan blankOrgan = new SmsOrgan();
		blankOrgan.setStOrganId("");
		blankOrgan.setStOrganName("");
		list.add(blankOrgan);
		obj.put("data", list);
		try {
			obj1.put("organ", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj1.toString());
	}
	@Autowired
	private SmsOrganService smsOrganService;
}
