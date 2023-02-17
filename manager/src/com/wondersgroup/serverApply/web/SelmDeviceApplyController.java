package com.wondersgroup.serverApply.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;
import com.wondersgroup.serverApply.bean.SelmDeviceApply;
import com.wondersgroup.serverApply.bean.SelmServerApply;
import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.serverApply.dao.SelmDeviceAlinkDao;
import com.wondersgroup.serverApply.service.SelmDeviceApplyService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 设备接入申请 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmDeviceApplyController {

	@Autowired
	private SelmDeviceApplyService selmDeviceApplyService;
	
	
	
	/**
	 * 设备接入申请-添加申请信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@RequestMapping("/serverApply/selmDeviceApply/addDeviceApply.do")
	public ModelAndView addDeviceApply(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stDeviceApplyId = "";
		stDeviceApplyId = (String) req.getSession().getAttribute("deviceApplyId");
		String stDeviceApplyIdSession = req.getParameter("stDeviceApplyIdSession");
		if(stDeviceApplyIdSession != null || stDeviceApplyIdSession == "ff"){
			req.getSession().setAttribute("deviceApplyId", null);
		}
		if(stDeviceApplyId == null || StringUtils.trim(stDeviceApplyId).isEmpty()){
			stDeviceApplyId = UUID.randomUUID().toString();
			req.getSession().setAttribute("deviceApplyId", stDeviceApplyId);	
			System.out.println("addDeviceApply.do:"+stDeviceApplyId);
		}
		SelmDeviceApply selmDeviceApply = null;
		selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
		if(null == selmDeviceApply){
			selmDeviceApply = new SelmDeviceApply();
			selmDeviceApply.setStDeviceApplyId(stDeviceApplyId);
		}
		req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		
		return new ModelAndView("/serverApply/addDevice.jsp");
		
	}
	
	/**
	 * 设备变更申请-添加申请信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@RequestMapping("/serverApply/selmDeviceApply/addDeviceChangeApply.do")
	public ModelAndView addDeviceChangeApply(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stDeviceApplyId = "";
		stDeviceApplyId = (String) req.getSession().getAttribute("deviceApplyId");
		String stDeviceApplyIdSession = req.getParameter("stDeviceApplyIdSession");
		if(stDeviceApplyIdSession != null || stDeviceApplyIdSession == "ff" ){
			req.getSession().setAttribute("deviceApplyId", null);
		}
		if(stDeviceApplyId == null || StringUtils.trim(stDeviceApplyId).isEmpty()){
			stDeviceApplyId = UUID.randomUUID().toString();
			req.getSession().setAttribute("deviceApplyId", stDeviceApplyId);	
			System.out.println("addDeviceApply.do:"+stDeviceApplyId);
		}
		SelmDeviceApply selmDeviceApply = null;
		selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
		if(selmDeviceApply == null){
			selmDeviceApply = new SelmDeviceApply();
			selmDeviceApply.setStDeviceApplyId(stDeviceApplyId);	
		}
		req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		
		return new ModelAndView("/serverApply/deviceChange/addChangeApply.jsp");
		
	}
	
	
	
	@RequestMapping("/serverApply/selmServerApply/backAddDevice.do")
	public ModelAndView backAddDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		//stDeviceApplyId
		System.out.println("----:"+req.getParameter("stDeviceApplyId"));
		req.getSession().setAttribute("deviceApplyId", req.getParameter("stDeviceApplyId"));
		return new ModelAndView("/serverApply/addDevice.jsp");
		
	}
	
	
	
	/**
	 * 保存添加的设备信息
	 */
	@RequestMapping("/serverApply/selmDeviceApply/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("设备接入申请保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmDeviceApply selmDeviceApply = selmDeviceApplyService.saveOrUpdate(wrapper);
			if (selmDeviceApply != null)
				result = ExtAjaxReturnMessage.success("设备接入申请保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	
	
	@RequestMapping("/serverApply/selmDeviceApply/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeviceApplyService.query(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
		
	}
	
	@RequestMapping("/serverApply/selmDeviceApply/checkList.do")
	public void checkList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeviceApplyService.checkQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
		
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping("/serverApply/selmDeviceApply/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceApply.ST_DEVICE_APPLY_ID
		String stDeviceApplyId = wrapper.getParameter(SelmDeviceApply.ST_DEVICE_APPLY_ID);
		if (!StringUtils.trimToEmpty(stDeviceApplyId).isEmpty()) {
			SelmDeviceApply selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
			System.out.println(selmDeviceApply.getStDeviceApplyId());
			req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		}
		return new ModelAndView("/serverApply/deviceApplyEdit.jsp");
	}
	
	/**
	 * 设备变更编辑
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/deviceChange/selmDeviceApply/edit.do")
	public ModelAndView changeEdit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceApply.ST_DEVICE_APPLY_ID
		String stDeviceApplyId = wrapper.getParameter(SelmDeviceApply.ST_DEVICE_APPLY_ID);
		if (!StringUtils.trimToEmpty(stDeviceApplyId).isEmpty()) {
			SelmDeviceApply selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
			System.out.println(selmDeviceApply.getStDeviceApplyId());
			req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		}
		return new ModelAndView("/serverApply/deviceChange/deviceChangeEdit.jsp");
	}

	/**
	 * 查看审批事项
	 */
	@RequestMapping("/serverApply/selmDeviceApply/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stDeviceApplyId = wrapper.getParameter(SelmDeviceApply.ST_DEVICE_APPLY_ID);
		SelmDeviceApply selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
		req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		return new ModelAndView("/serverApply/deviceApplyInfo.jsp");
	}
	
	
	/**
	 * 查看变更设备
	 */
	@RequestMapping("/serverApply/selmDeviceApply/changeInfo.do")
	public ModelAndView changeInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceApply.ST_DEVICE_APPLY_ID
		String stDeviceApplyId = wrapper.getParameter(SelmDeviceApply.ST_DEVICE_APPLY_ID);
		SelmDeviceApply selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
		req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		return new ModelAndView("/serverApply/deviceChange/deviceChangeInfo.jsp");
	}
	
	
	/**
	 * 设备接入审核-审核申请信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmDeviceApply/checkDeviceApply.do")
	public ModelAndView checkDeviceApply(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stDeviceApplyId = wrapper.getParameter(SelmDeviceApply.ST_DEVICE_APPLY_ID);
		SelmDeviceApply selmDeviceApply = selmDeviceApplyService.get(stDeviceApplyId);
		req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
		return new ModelAndView("/serverApply/checkDeviceApply.jsp");
		
	}
	
	
	
	
	@RequestMapping("/serverApply/selmDeviceApply/saveSubmit.do")
	public void saveSubmit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("提交失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceApplyService.saveSubmit(httpReqRes);
			result.success().setMsg("提交成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	

	@RequestMapping("/serverApply/selmDeviceApply/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("删除失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceApplyService.removeApply(httpReqRes);
			result.success().setMsg("删除成功。");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	

	
	
	
	/**
	 * 服务管理-设备接入申请-批量删除
	 * 
	 * @param req
	 * @param stDeviceApplyId[]
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmDeviceApply/batchDelete.do")
	public void batchDelete(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("项目删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			String[] roleIdList = wrapper.getParameterValues("stDeviceApplyId[]");
			System.out.println(roleIdList);
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("stDeviceApplyId");
				if (roleId != null) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("ID不能为空");
				}
			}
			selmDeviceApplyService.batchDelete(roleIdList);
			result = ExtAjaxReturnMessage.success("删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}
	
	
	/**
	 * 设备审核通过
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmDeviceAlink/devicePass.do")
	public void devicePass(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("审核失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceApplyService.devicePass(httpReqRes);
			result.success().setMsg("审核成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	/**
	 * 设备审核不通过
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmDeviceAlink/deviceNoPass.do")
	public void deviceNoPass(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("审核失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceApplyService.deviceNoPass(httpReqRes);
			result.success().setMsg("审核成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	/**
	 * 批量通过
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmDeviceAlink/batchPass.do")
	public void batchPass(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("审核失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceApplyService.batchPass(httpReqRes);
			result.success().setMsg("审核成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	/**
	 * 批量不通过
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmDeviceAlink/batchNoPass.do")
	public void batchNoPass(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("审核失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmDeviceApplyService.batchNoPass(httpReqRes);
			result.success().setMsg("审核成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	

}
