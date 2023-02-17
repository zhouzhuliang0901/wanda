package com.wondersgroup.serverApply.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

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
import wfc.service.log.Log;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.service.SelmItemTypeService;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;
import com.wondersgroup.serverApply.bean.SelmServerApply;
import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.serverApply.dao.SelmDeviceAlinkDao;
import com.wondersgroup.serverApply.dao.SelmServerApplyDao;
import com.wondersgroup.serverApply.dao.SelmServerDlinkDao;
import com.wondersgroup.serverApply.service.SelmServerApplyService;
import com.wondersgroup.serverApply.service.SelmServerItemService;


import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 服务关联事项 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmServerItemController {

	@Autowired
	private SelmServerItemService selmServerItemService;
	@Autowired
	private SelmServerApplyService selmServerApplyService;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private SelmDeviceAlinkDao selmDeviceAlinkDao;
	@Autowired
	private SelmServerDlinkDao selmServerDlinkDao;
	@Autowired
	private SelmServerApplyDao selmServerApplyDao;
	@Autowired
	private SelmItemTypeService selmItemTypeService;
	
	@RequestMapping("/serverApply/selmServerItem/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmServerItem.ST_LINKS_ID
		String stLinksId = wrapper.getParameter(SelmServerItem.ST_LINKS_ID);
		if (!StringUtils.trimToEmpty(stLinksId).isEmpty()) {
			SelmServerItem selmServerItem = selmServerItemService.get(stLinksId);
			req.setAttribute(SelmServerItem.SELM_SERVER_ITEM, selmServerItem);
		}
		return new ModelAndView("/selmServerItem/edit.jsp");
	}

	@RequestMapping("/serverApply/selmServerItem/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmServerItem.ST_LINKS_ID
		String stLinksId = wrapper.getParameter(SelmServerItem.ST_LINKS_ID);
		SelmServerItem selmServerItem = selmServerItemService.get(stLinksId);
		req.setAttribute(SelmServerItem.SELM_SERVER_ITEM, selmServerItem);
		return new ModelAndView("/selmServerItem/info.jsp");
	}

	@RequestMapping("/serverApply/selmServerItem/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmServerItemService.list(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/serverApply/selmServerItem/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("绑定事项移出失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmServerItemService.removeList(httpReqRes);
			result.success().setMsg("绑定事项移出成功。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/serverApply/selmServerItem/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmServerItem selmServerItem = selmServerItemService.saveOrUpdate(httpReqRes);
			if (selmServerItem != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	/**
	 * 修改保存审核状态
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/serverApply/selmServerItem/CheckSave.do")
	public void CheckSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmServerItem selmServerItem = selmServerItemService.CheckSave(httpReqRes);
			if (selmServerItem != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	
	
	
	
	/**
	  * 设备对应没有的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/serverApply/selmServerItem/nodeviceOCItemlist.do")
	 public void nodeviceItemList(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = selmServerItemService.nodeviceOCItemlist(httpReqRes);
	   req.setAttribute("obj", obj);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 /**
	  * 设备对应的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/serverApply/selmServerItem/deviceOCItemlist.do")
	 public void deviceitemList(HttpServletRequest req, HttpServletResponse res)
			 throws IOException {
		 HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = selmServerItemService.deviceoauth2ClientItemlist(httpReqRes);
			 System.out.println(obj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 @RequestMapping("/serverApply/selmServerItem/removePower.do")
		public void removePower(HttpServletRequest req,
				HttpServletResponse res) throws IOException, JSONException {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			Result result = Result.getResult();
			result.setMsg("删除失败");
			try {
				selmServerItemService.removePower(httpReqRes);
				result.setMsg("删除成功");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			httpReqRes.writeJsonP(result);
		}
	 
	 @RequestMapping("/serverApply/selmServerItem/savePower.do")
		public void savePower(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
			Result result = Result.getResult();
			result.success().setMsg("添加失败");
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			try {
				int i = selmServerItemService.saveOrUpdatePower(httpReqRes);
				if (i > 0){
					result.success().setMsg("添加成功");
					result.setSuccess(true);
				}
					
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			httpReqRes.writeJsonP(result); 
		}
	 
	 @RequestMapping("/serverApply/selmServerItem/savePowerByGroup.do")
		public void savePowerByGroup(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
			Result result = Result.getResult();
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			result.success().setMsg("添加失败");
			try {
				int i = selmServerItemService.saveOrUpdatePowerByGroup(httpReqRes);
				if (i > 0){
					result.success().setMsg("添加成功");
					result.setSuccess(true);
				} 
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			httpReqRes.writeJsonP(result);
		}
	 
	 
	 
	 @RequestMapping("/serverApply/selmServerItem/groupItem.do")
		public ModelAndView groupItem(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
		  	RequestWrapper wrapper = new RequestWrapper(req);
		 	String stDeviceId = wrapper.getParameter("stDeviceId");
			String stDeviceIdList = wrapper.getParameter("stDeviceArray");
			String stApplyId = wrapper.getParameter("stApplyId");
			InfopubDeviceInfo info = new InfopubDeviceInfo();
			info.setStDeviceId(stDeviceId);
			SelmServerApply selmServerApply = new SelmServerApply();
			selmServerApply.setStApplyId(stApplyId);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY, selmServerApply);
			req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,info);
			req.setAttribute("stDeviceArray", stDeviceIdList);
			req.setAttribute("stApplyId", stApplyId);
			return new ModelAndView("/serverApply/groupItem.jsp");
		}
	 
	 
	 @RequestMapping("/serverApply/selmServerItem/groupData.do")
		public void groupItemData(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
		 HttpReqRes httpReqRes = new HttpReqRes(req, res);
			JSONObject obj = null;
			try {
				obj = selmServerApplyService.groupItemData(httpReqRes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			EasyUIHelper.writeResponse(res, obj.toString());
		}
	 
	 
	 @RequestMapping("/serverApply/selmServerItem/applyInfo.do")
		public ModelAndView applyInfo(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
		  	RequestWrapper wrapper = new RequestWrapper(req);
		 	String stDeviceId = wrapper.getParameter("ST_DEVICE_ID");
		 	System.out.println("stDeviceId:"+stDeviceId);
			InfopubDeviceInfo info = new InfopubDeviceInfo();
			info.setStDeviceId(stDeviceId);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID",Condition.OT_LIKE,stDeviceId));
			SelmServerDlink sdd = selmServerDlinkDao.query(conds, null).get(0);
			if(sdd == null){
				sdd = new SelmServerDlink();
			}
			String applyId = sdd.getStApplyId();
			System.out.println("applyId:"+applyId);
			SelmServerApply ssa = selmServerApplyDao.get(applyId);
			req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,info);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY,ssa);
			return new ModelAndView("/serverApply/applyInfo.jsp");
		}
	 
	    
	    @RequestMapping("/serverApply/selmServerItem/noPassReason.do")
		public ModelAndView noPassReason(HttpServletRequest req, HttpServletResponse res)
				throws IOException, JSONException {
		  	RequestWrapper wrapper = new RequestWrapper(req);
		 	String stDeviceId = wrapper.getParameter("ST_DEVICE_ID");
		 	String stItemId = wrapper.getParameter("ST_ITEM_ID");
			InfopubDeviceInfo info = new InfopubDeviceInfo();
			info.setStDeviceId(stDeviceId);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID",Condition.OT_LIKE,stDeviceId));
			SelmServerDlink sdd = selmServerDlinkDao.query(conds, null).get(0);
			String applyId = sdd.getStApplyId();
			SelmServerApply ssa = selmServerApplyDao.get(applyId);
			req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,info);
			req.setAttribute(SelmServerApply.SELM_SERVER_APPLY,ssa);
			req.setAttribute("stItemId",stItemId);
			return new ModelAndView("/serverApply/noPass.jsp");
		}
	 

}
