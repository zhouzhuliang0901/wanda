package com.wondersgroup.sms.organ.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.service.UserService;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.service.SmsOrganService;
import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
/**
 * 组织机构表 web层控制器
 *
 */
@RestController
public class SmsOrganController {
	@Autowired
	private SmsOrganService smsOrganService;

	@Autowired
	private UserService userService;


	@RequestMapping("/exam/organ/organList")
	public WdfResult organList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		SmsUser user =  (SmsUser) SecurityUtils.getSubject().getPrincipal();
		SmsUser smsUser = userService.get(user.getStUserId());
		return WdfResult.getResult().success().setData(JsonUtils.toJson(smsUser));

		/*try {
			if(!(smsUser.getStOrganIdTwo().isEmpty())){
				SmsOrgan so = smsOrganService.get(smsUser.getStOrganId());
				SmsOrgan smsOrgan = smsOrganService.get(smsUser.getStOrganIdTwo());
				String stOrganName = so.getStOrganName();
				List<Object> list = new ArrayList<Object>();
 			    list.add(stOrganName);
				list.add(smsOrgan);
				result.setData(list);
			}else {
				RequestWrapper wrapper = new RequestWrapper(req);
				PaginationArrayList<SmsOrgan> list = smsOrganService.query(wrapper);
				result.setData(list);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}*/
	}

	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sms/organ/edit")
	public WdfResult edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		RequestWrapper wrapper = new RequestWrapper(req);
		String stOrganId = wrapper.getParameter(SmsOrgan.ST_ORGAN_ID);
		if (!StringUtils.trimToEmpty(stOrganId).isEmpty()) {
			SmsOrgan smsOrgan = smsOrganService.get(stOrganId);
			result.setData(JsonUtils.toJson(smsOrgan));
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
	@RequestMapping("/sms/organ/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stOrganId = wrapper.getParameter(SmsOrgan.ST_ORGAN_ID);
		SmsOrgan smsOrgan = smsOrganService.get(stOrganId);
		return WdfResult.getResult().success().setData(JsonUtils.toJson(smsOrgan));
	}

	/**
	 * 列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/sms/organ/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<SmsOrgan> list = smsOrganService.query(wrapper);
			result.setData(JsonUtils.toJson(list, SmsOrgan.class));
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
	@RequestMapping("/sms/organ/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 样表ID
			String[] organIdList = wrapper.getParameterValues(SmsOrgan.ST_ORGAN_ID);
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
			result.success().setMsg("组织机构表删除成功");
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
	@RequestMapping("/sms/organ/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SmsOrgan smsOrgan = smsOrganService.saveOrUpdate(wrapper);
			if (smsOrgan != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/sms/organ/organList")
	public void init(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		RequestWrapper wrapper = new RequestWrapper(req);
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		List<SmsOrgan> list = smsOrganService.query(wrapper);
		obj.put("data", list);
		try {
			obj1.put("organ", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj1.toString());
	}

}
