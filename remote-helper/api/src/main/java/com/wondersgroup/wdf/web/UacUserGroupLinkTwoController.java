package com.wondersgroup.wdf.web;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wondersgroup.wdf.dao.UacUserGroupLinkTwo;
import com.wondersgroup.wdf.service.UacUserGroupLinkTwoService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 组关联人员 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacUserGroupLinkTwoController {

	//根据事项组id查事项人员关联
	@RequestMapping("/wdf/uacUserGroupLinkTwo/queryBystGroupId")
	public WdfResult queryBystGroupId(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			String stGroupId = wrapper.getParameter("ST_GROUP_ID");
			Integer pageSize = wrapper.getParameterInt("rows",10,true);
			Integer currentPage = wrapper.getParameterInt("page",1,true);
			PaginationArrayList<SmsUser> list = smsUserDao.queryBystGroupId(stGroupId,pageSize,currentPage);
			result.setData(JsonUtils.toJson(list,SmsUser.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacUserGroupLinkTwo/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUserGroupLinkTTwo.ST_USER_ID
			String[] stUserId = wrapper.getParameterValues("ST_USER_ID[]");
			// UacUserGroupLinkTTwo.ST_GROUP_ID
			String[] stGroupId = wrapper.getParameterValues("ST_GROUP_ID[]");
			uacUserGroupLinkTwoService.remove(stUserId, stGroupId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 根据一个事项组id保存多个组关联人员
	 */
	@RequestMapping("/wdf/uacUserGroupLinkTwo/saveUser")
	public WdfResult saveUser(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUserGroupLinkTwo uacUserGroupLinkTwo = uacUserGroupLinkTwoService.saveitem(wrapper);
			if (uacUserGroupLinkTwo != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacUserGroupLinkTwoService uacUserGroupLinkTwoService;
	@Autowired
	private SmsUserDao smsUserDao;

}
