package com.wondersgroup.serverApply.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.serverApply.bean.SelmDeviceApply;
import com.wondersgroup.serverApply.bean.SelmServerApply;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.serverApply.service.SelmServerApplyService;
import com.wondersgroup.serverApply.service.SelmServerItemService;

import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;

/**
 * 服务开通申请 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmServerApplyController {
	
	@Autowired
	private SelmServerApplyService selmServerApplyService;
	@Autowired
	private SelmServerItemService selmServerItemService;
	

	@CheckPermissions(roles = { "admin" }, permissions = { "SELM_SERVER_APPLY_EDIT" })
	@RequestMapping("/serverApply/selmServerApply/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		
		String stApplyId = (String) req.getSession().getAttribute("serverApplyId");
		String stServerApplyIdSession = req.getParameter("stServerApplyIdSession");
		if(stServerApplyIdSession != null || stServerApplyIdSession == "ff"){
			req.getSession().setAttribute("serverApplyId", null);
		}

		if(stApplyId == null || StringUtils.trim(stApplyId).isEmpty()){
			stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
			req.getSession().setAttribute("serverApplyId", stApplyId);	
			System.out.println("serverApplyId.do:"+stApplyId);
		}
		
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			SelmServerApply selmServerApply = selmServerApplyService.get(stApplyId);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
		}
		return new ModelAndView("/serverApply/editApply.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "SELM_SERVER_APPLY_INFO" })
	@RequestMapping("/serverApply/selmServerApply/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			SelmServerApply selmServerApply = selmServerApplyService.get(stApplyId);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
		}
		return new ModelAndView("/serverApply/oneApplyInfo.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "SELM_SERVER_APPLY" ,"SELM_SERVER_ITEM"})
	@RequestMapping("/serverApply/selmServerApply/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmServerApplyService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "SELM_SERVER_APPLY_REMOVE" })
	@RequestMapping("/serverApply/selmServerApply/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("申请删除失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmServerApplyService.removeList(httpReqRes);
			result.success().setMsg("申请删除成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/serverApply/selmServerApply/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("服务开通申请保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmServerApply selmServerApply = selmServerApplyService.saveOrUpdate(wrapper);
			if (selmServerApply != null)
				result = ExtAjaxReturnMessage.success("服务开通申请保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	/**
	 * 提交申请
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	
	@RequestMapping("/serverApply/selmServerApply/saveSubmit.do")
	public void saveSubmit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("提交失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmServerApplyService.saveSubmit(httpReqRes);
			result.success().setMsg("提交成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	
	
	/**
	 * 编辑移出事项
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmServerApply/editDelItem.do")
	public ModelAndView editDelItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
		SelmServerApply selmServerApply = new SelmServerApply();
		selmServerApply.setStApplyId(stApplyId);
		//SelmServerApply selmServerApply = selmServerApplyService.get(stApplyId);
		req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
		return new ModelAndView("/serverApply/itemList.jsp");
	}
	
	/**
	 * 跳转事项列表审核界面
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "SELM_SERVER_ITEM_VERIFY" })
	@RequestMapping("/serverApply/selmServerApply/checkItem.do")
	public ModelAndView checkItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stItemId = wrapper.getParameter("ST_ITEM_ID");
		req.setAttribute("stItemId",stItemId);
		return new ModelAndView("/serverApply/checkItem.jsp");
	
	}
	
	
	@RequestMapping("/serverApply/selmServerApply/checkRecords.do")
	public ModelAndView checkRecords(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stItemId = wrapper.getParameter("ST_ITEM_ID");
		req.setAttribute("stItemId",stItemId);
		return new ModelAndView("/serverApply/checkRecords.jsp");
	
	}
	
	
	@RequestMapping("/serverApply/selmServerApply/checkReason.do")
	public ModelAndView checkReason(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		SelmServerItem ssi = new SelmServerItem();
		ssi = selmServerItemService.getSelmServerItem(httpReqRes);
		req.setAttribute(SelmServerItem.SELM_SERVER_ITEM,ssi);
		return new ModelAndView("/serverApply/checkReason.jsp");
	
	}
	
	/**
	  * 申请服务对应没有的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/serverApply/selmServerApply/sreverNoItem.do")
	 public void nodeviceItemList(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = selmServerApplyService.sreverNoItem(httpReqRes);
	   req.setAttribute("obj", obj);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 /**
		 * 下载附件。
		 * @param req
		 * @param res
		 * @throws IOException
		 */
		@RequestMapping("/serverApply/selmServerApply/download.do")
		public void download(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			selmServerApplyService.downLoad(httpReqRes);
			return;
		}
		
		
		/**
		 * 申请添加
		 * 
		 * @param req
		 * @param res
		 * @return
		 * @throws IOException
		 */
		@CheckPermissions(roles = { "admin" }, permissions = { "SELM_SERVER_APPLY_ADD" })
		@RequestMapping("/serverApply/selmServerApply/addServerApply.do")
		public ModelAndView addServerApply(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String stApplyId = "";
			stApplyId = (String) req.getSession().getAttribute("serverApplyId");
			String stServerApplyIdSession = req.getParameter("stServerApplyIdSession");
			if(stServerApplyIdSession != null || stServerApplyIdSession == "ff"){
				req.getSession().setAttribute("serverApplyId", null);
			}
			
			if(stApplyId == null || StringUtils.trim(stApplyId).isEmpty()){
				stApplyId = UUID.randomUUID().toString();
				req.getSession().setAttribute("serverApplyId", stApplyId);	
				System.out.println("serverApplyId.do:"+stApplyId);
			}
			
			SelmServerApply selmServerApply = null;
			selmServerApply = selmServerApplyService.get(stApplyId);
			if(null == selmServerApply){
				selmServerApply = new SelmServerApply();
				selmServerApply.setStApplyId(stApplyId);
			}
			
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
			return new ModelAndView("/serverApply/add.jsp");
		}
	
	
		
		/**
		 * 设备选择添加
		 * 
		 * @param req
		 * @param res
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("/serverApply/selmServerApply/choiceDevice.do")
		public ModelAndView choiceDevice(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			
			String stApplyId = req.getParameter("ST_APPLY_ID");
			SelmServerApply selmServerApply = new SelmServerApply();
			selmServerApply.setStApplyId(stApplyId);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
			return new ModelAndView("/serverApply/choiceDevice.jsp");
		}
		
		
		/**
		 * 设备变更设备选择添加
		 * 
		 * @param req
		 * @param res
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("/serverApply/deviceChange/choiceChangeDevice.do")
		public ModelAndView choiceChangeDevice(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			
			String stApplyId = req.getParameter("stDeviceApplyId");
			SelmDeviceApply selmDeviceApply = new SelmDeviceApply();
			selmDeviceApply.setStDeviceApplyId(stApplyId);
			req.setAttribute(SelmDeviceApply.SELM_DEVICE_APPLY, selmDeviceApply);
			req.setAttribute("deviceApplyId", stApplyId);
			return new ModelAndView("/serverApply/deviceChange/choiceChangeDevice.jsp");
		}
		
		
		
		@RequestMapping("/serverApply/selmServerApply/deviceLinkItem.do")
		public ModelAndView deviceLinkItem(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String stDeviceId = req.getParameter("ST_DEVICE_ID");
			req.setAttribute("ST_DEVICE_ID",stDeviceId);
			return new ModelAndView("/serverApply/deviceLinkItem.jsp");
		}
	

		
		/**
		 * 已添加设备列表
		 * @throws IOException 
		 */
		@RequestMapping("/serverApply/selmServerApply/stDeviceList.do")
		public void stDeviceList(HttpServletRequest req, HttpServletResponse res) throws IOException{
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			JSONObject obj = null;
			try {
				obj = selmServerApplyService.deviceList(httpReqRes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			EasyUIHelper.writeResponse(res, obj.toString());
			
		}
		
		/**
		 * 已添加设备列表
		 * @throws IOException 
		 */
		@RequestMapping("/serverApply/selmServerApply/checkstDeviceList.do")
		public void ckeckstDeviceList(HttpServletRequest req, HttpServletResponse res) throws IOException{
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			JSONObject obj = null;
			try {
				obj = selmServerApplyService.checkdeviceList(httpReqRes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			EasyUIHelper.writeResponse(res, obj.toString());
			
		}
		
		@RequestMapping("/serverApply/selmServerApply/checkRecordsList.do")
		public void checkRecordsList(HttpServletRequest req, HttpServletResponse res) throws IOException{
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			JSONObject obj = null;
			try {
				obj = selmServerApplyService.checkRecordsList(httpReqRes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			EasyUIHelper.writeResponse(res, obj.toString());
			
		}
		
		
		
		/**
		 * 删除服务申请关联的设备
		 */
		@RequestMapping("/serverApply/selmServerApply/removeDevice.do")
		public void removeDevice(HttpServletRequest req, HttpServletResponse res) throws IOException{
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			Result result = Result.getResult();
			result.success().setMsg("删除失败。");
			try {
				selmServerApplyService.removeDevice(httpReqRes);
				result.success().setMsg("删除成功。");
			} catch (Exception e) {
				e.printStackTrace();
			}

			httpReqRes.writeJsonP(result);
			
		} 
		
		/**
		 * 绑定事项界面跳转
		 * 
		 * @param req
		 * @param res
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("/serverApply/selmServerApply/addItem.do")
		public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			RequestWrapper wrapper = new RequestWrapper(req);
			String stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
			System.out.println("stApplyId_____--"+stApplyId);
			String stDeviceId = wrapper.getParameter("ST_DEVICE_ID");
			String stDeviceIdList = wrapper.getParameter("stDeviceIdList");
			SelmServerApply selmServerApply = new SelmServerApply();
			selmServerApply.setStApplyId(stApplyId);
			InfopubDeviceInfo infopubDeviceInfo = new InfopubDeviceInfo();
			infopubDeviceInfo.setStDeviceId(stDeviceId);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
			req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO, infopubDeviceInfo);
			req.setAttribute("stDeviceArray", stDeviceIdList);
			return new ModelAndView("/serverApply/itemListServer.jsp");
		}
		
		
		
		/**
		 * 查看绑定事项界面
		 * 
		 * @param req
		 * @param res
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("/serverApply/selmServerApply/checkItemStatus.do")
		public ModelAndView checkItemStatus(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			RequestWrapper wrapper = new RequestWrapper(req);
			String stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
			String stDeviceId = wrapper.getParameter("ST_DEVICE_ID");
			String itemCount = wrapper.getParameter("itemCount");
			req.setAttribute("stApplyId", stApplyId);
			req.setAttribute("stDeviceId", stDeviceId);
			req.setAttribute("itemCount", itemCount);
			return new ModelAndView("/serverApply/checkItemStatus.jsp");
		}
		
		/**
		 * 批量给设备添加事项
		 * 
		 * @param req
		 * @param res
		 * @return
		 * @throws IOException
		 */
		@RequestMapping("/serverApply/selmServerApply/batchAddItem.do")
		public ModelAndView batchAddItem(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			RequestWrapper wrapper = new RequestWrapper(req);
			String stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
			String stDeviceIdList = wrapper.getParameter("stDeviceIdList");
			SelmServerApply selmServerApply = new SelmServerApply();
			selmServerApply.setStApplyId(stApplyId);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
			req.setAttribute("stDeviceArray", stDeviceIdList);
			return new ModelAndView("/serverApply/itemListServer.jsp");
		}
		
		
		
		@RequestMapping("/serverApply/selmServerApply/applyItemlist.do")
		public void applyItemlist(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			JSONObject obj = null;
			try {
				obj = selmServerApplyService.applyItemlist(httpReqRes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			EasyUIHelper.writeResponse(res, obj.toString());
		}
		
		
		@RequestMapping("/serverApply/selmServerApply/checkDeviceWithItem.do")
		public void checkDeviceWithItem(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			JSONObject obj = null;
			try {
				obj = selmServerApplyService.checkDeviceWithItem(httpReqRes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			EasyUIHelper.writeResponse(res, obj.toString());
		}
		
		//不通过
		@RequestMapping("/serverApply/selmServerApply/saveNoPass.do")
		public void saveNoPass(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String result = ExtAjaxReturnMessage.toJsonErrorObj("保存失败。", "错误",
					null).toString();
			try {
				HttpReqRes httpReqRes = new HttpReqRes(req, res);
				int i = selmServerApplyService.saveNoPass(httpReqRes);
				if (i != 0)
					result = ExtAjaxReturnMessage.success("保存成功。", null)
							.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			EasyUIHelper.writeFormResponse(res, result);
		}
		
		//通过
		@RequestMapping("/serverApply/selmServerApply/savePass.do")
		public void savePass(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String result = ExtAjaxReturnMessage.toJsonErrorObj("保存失败。", "错误",
					null).toString();
			try {
				HttpReqRes httpReqRes = new HttpReqRes(req, res);
				int i = selmServerApplyService.savePass(httpReqRes);
				if (i != 0)
					result = ExtAjaxReturnMessage.success("保存成功。", null)
							.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			EasyUIHelper.writeFormResponse(res, result);
		}
		
		
		
		//不通过原因
		@RequestMapping("/serverApply/selmServerApply/saveNoPassReason.do")
		public void saveNoPassReason(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String result = ExtAjaxReturnMessage.toJsonErrorObj("保存失败。", "错误",
					null).toString();
			try {
				HttpReqRes httpReqRes = new HttpReqRes(req, res);
				int i = selmServerApplyService.saveNoPassReason(httpReqRes);
				if (i != 0)
					result = ExtAjaxReturnMessage.success("保存成功。", null)
							.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			EasyUIHelper.writeFormResponse(res, result);
		}
		
		
		@RequestMapping("/serverApply/selmServerApply/batchPass.do")
		public void batchPass(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String result = ExtAjaxReturnMessage.toJsonErrorObj("保存失败。", "错误",
					null).toString();
			try {
				HttpReqRes httpReqRes = new HttpReqRes(req, res);
				int i = selmServerApplyService.batchPass(httpReqRes);
				if (i != 0)
					result = ExtAjaxReturnMessage.success("保存成功。", null)
							.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			EasyUIHelper.writeFormResponse(res, result);
		}
		
		@RequestMapping("/serverApply/selmServerApply/batchNoPass.do")
		public void batchNoPass(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
			String result = ExtAjaxReturnMessage.toJsonErrorObj("保存失败。", "错误",
					null).toString();
			try {
				HttpReqRes httpReqRes = new HttpReqRes(req, res);
				int i = selmServerApplyService.batchNoPass(httpReqRes);
				if (i != 0)
					result = ExtAjaxReturnMessage.success("保存成功。", null)
							.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			EasyUIHelper.writeFormResponse(res, result);
		}
		
		
}
