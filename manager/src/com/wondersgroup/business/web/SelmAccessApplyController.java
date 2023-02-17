package com.wondersgroup.business.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;

import com.wondersgroup.business.bean.SelmAccessApply;
import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.service.SelmAccessApplyService;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.statistics.bean.SelmStatistics;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 接入申请 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmAccessApplyController {

	@RequestMapping("/selmAccessApply/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAccessApply.ST_ACCESS_APPLY_ID
		String stAccessApplyId = wrapper.getParameter(SelmAccessApply.ST_ACCESS_APPLY_ID);
		if (!StringUtils.trimToEmpty(stAccessApplyId).isEmpty()) {
			SelmAccessApply selmAccessApply = selmAccessApplyService.get(stAccessApplyId);
			req.setAttribute(SelmAccessApply.SELM_ACCESS_APPLY, selmAccessApply);
		}
		return new ModelAndView("/selmAccessApply/edit.jsp");
	}

	@RequestMapping("/selmAccessApply/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAccessApply.ST_ACCESS_APPLY_ID
		String stAccessApplyId = wrapper.getParameter(SelmAccessApply.ST_ACCESS_APPLY_ID);
		SelmAccessApply selmAccessApply = selmAccessApplyService.get(stAccessApplyId);
		req.setAttribute(SelmAccessApply.SELM_ACCESS_APPLY, selmAccessApply);
		return new ModelAndView("/selmAccessApply/info.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_apply" })
	@RequestMapping("/business/selmAccessApply/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAccessApplyService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	

	@RequestMapping("/selmAccessApply/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接入申请删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// SelmAccessApply.ST_ACCESS_APPLY_ID
			String stAccessApplyId = wrapper.getParameter(SelmAccessApply.ST_ACCESS_APPLY_ID);
			selmAccessApplyService.remove(stAccessApplyId);
			result = ExtAjaxReturnMessage.success("接入申请删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/selmAccessApply/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接入申请保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmAccessApply selmAccessApply = selmAccessApplyService.saveOrUpdate(wrapper);
			if (selmAccessApply != null)
				result = ExtAjaxReturnMessage.success("接入申请保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	
	/**
	 * 附件上传
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/business/selmAccessApply/uploadStuff.do")
	public void uploadStuff(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		FileItem fileItem = wrapper.getFileItem("file");
		System.out.println(fileItem);
		String fileName = null;
		String fileType = null;
		byte[] file = null;
		int len = 0;
		if(fileItem != null){
			fileName = fileItem.getName();
			fileType = fileItem.getContentType();
			file = ((FileItem) fileItem).get();
			len = file.length;
		}
		String stApplyUserId = wrapper.getParameter("stApplyUserId");
		//String stAttachId = wrapper.getParameter("stAttachId");
		String applytitle = wrapper.getParameter("applytitle");//提交标题
		String applycontent = wrapper.getParameter("applycontent");//提交内容
		SelmAttach selmAttach = selmAccessApplyService.uploadStuff(stApplyUserId,fileName,fileType,file,len,applytitle,applycontent);
		JSONObject json = new JSONObject();
		if(selmAttach != null){
			json.put("SUCCESS", true);
			//json.put("ATTACHID", selmAttach.getStAttachid());
		} else {
			json.put("SUCCESS", false);
			json.put("ATTACHID", "");
		}
		/*EasyUIHelper.writeResponse(res, json.toString());*/
	}
	
	@RequestMapping("/business/selmAccessApply/add.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmAccessApply.ST_ACCESS_APPLY_ID
		String stAccessApplyId = wrapper.getParameter(SelmAccessApply.ST_ACCESS_APPLY_ID);
		if (!StringUtils.trimToEmpty(stAccessApplyId).isEmpty()) {
			SelmAccessApply selmAccessApply = selmAccessApplyService.get(stAccessApplyId);
			req.setAttribute(SelmAccessApply.SELM_ACCESS_APPLY, selmAccessApply);
		}
		return new ModelAndView("/selmApply/edit.jsp");
	}
	/**
	 * 下载附件。
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/business/selmAccessApply/download.do")
	public void download(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		selmAccessApplyService.downLoad(httpReqRes);
		return;
	}
	
	/**
	 * 查询未审核的申请
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_home" })
	@RequestMapping("/business/selmAccessApply/NoApplylist.do")
	public void NoApplylist(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmAccessApplyService.NoApplylist(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		//EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 首页点击未审核标题跳转
	 */
	@RequestMapping("/business/selmAccessApply/noApply.do")
	public ModelAndView streetInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stAccessApplyId = httpReqRes
				.getParameter("stAccessApplyId");
		if (!StringUtils.trimToEmpty(stAccessApplyId).isEmpty()) {
			SelmAccessApply selmAccessApply = selmAccessApplyService.get(stAccessApplyId);
			req.setAttribute(SelmAccessApply.SELM_ACCESS_APPLY, selmAccessApply);
		}
		return new ModelAndView("/selmApply/list.jsp");
	}
	

	@Autowired
	private SelmAccessApplyService selmAccessApplyService;

}
