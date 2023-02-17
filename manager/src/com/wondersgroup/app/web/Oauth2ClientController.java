package com.wondersgroup.app.web;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.app.bean.Oauth2Client;
import com.wondersgroup.app.bean.Oauth2ClientItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.service.Oauth2ClientItemService;
import com.wondersgroup.app.service.Oauth2ClientService;
import com.wondersgroup.app.service.SelmItemService;
import com.wondersgroup.infopub.service.InfoPubService;
import com.wondersgroup.statistics.bean.SelmStatistics;



import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * OAUTH2认证客户端 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class Oauth2ClientController {
	
	/**
	 * 查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/oauth2Client/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		// SelmStatistics.ST_STATISTICS_ID
		String stOauth2Id = httpReqRes
				.getParameter(Oauth2Client.ST_OAUTH2_ID);
		Oauth2Client oauth2Client = oauth2ClientService
				.get(stOauth2Id);
		Oauth2ClientItem oauth2ClientItem = oauth2ClientItemService.getitem(oauth2Client.getStOauth2Id());
		SelmItem selmItem = selmItemService.get(oauth2ClientItem.getStItemId());
		System.out.println(selmItem.getStMainName());
		oauth2Client.setStDesc(selmItem.getStMainName());
		req.setAttribute(Oauth2Client.OAUTH2_CLIENT, oauth2Client);
		return new ModelAndView("/app/oauth2info.jsp");
	}
	/**
	 * 授权
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/oauth2item/add.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		// SelmStatistics.ST_STATISTICS_ID
		String stOauth2Id = httpReqRes
				.getParameter(Oauth2Client.ST_OAUTH2_ID);
		Oauth2Client oauth2Client = oauth2ClientService
				.get(stOauth2Id);
		req.setAttribute(Oauth2Client.OAUTH2_CLIENT, oauth2Client);
		return new ModelAndView("/app/oauth2item.jsp");
	}
	
	/**
	 * 编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/oauth2Client/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stOauth2Id = httpReqRes
				.getParameter(Oauth2Client.ST_OAUTH2_ID);
		if (!StringUtils.trimToEmpty(stOauth2Id).isEmpty()) {
			Oauth2Client oauth2Client = oauth2ClientService
					.get(stOauth2Id);
			req.setAttribute(Oauth2Client.OAUTH2_CLIENT, oauth2Client);
		}
		return new ModelAndView("/app/oauth2edit.jsp");
	}
	
	
	@RequestMapping("/app/oauth2Client/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = oauth2ClientService.oauth2ClientList(httpReqRes);
			req.setAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
		
	/**
	 * 删除
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/app/oauth2Client/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			oauth2ClientService.remove(httpReqRes);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/app/oauth2Client/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("OAUTH2认证客户端保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			Oauth2Client oauth2Client = oauth2ClientService.saveOrUpdate(wrapper);
			if (oauth2Client != null)
				result = ExtAjaxReturnMessage.success("OAUTH2认证客户端保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	
	//-------------------------------------------------------
	
	 /**
	  * oauth2Client对应没有的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/oauth2Client/noOCItemlist.do")
	 public void noItemList(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = oauth2ClientService.noOCItemlist(httpReqRes);

	   req.setAttribute("obj", obj);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  /*System.out.println(obj.toString());*/
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 /**
	  * oauth2Client对应的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/oauth2Client/OCItemlist.do")
	 public void itemList(HttpServletRequest req, HttpServletResponse res)
			 throws IOException {
		 HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = oauth2ClientService.oauth2ClientItemlist(httpReqRes);
			 req.setAttribute("obj", obj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 
	 /**
	  * oauth2Client对应没有的设备
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/oauth2Client/noOCDevicelist.do")
	 public void noDeviceList(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = oauth2ClientService.noOCDevicelist(httpReqRes);
	   req.setAttribute("obj", obj);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 
	 /**
	  * oauth2Client对应的设备
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/oauth2Client/OCDevicelist.do")
	 public void deviceList(HttpServletRequest req, HttpServletResponse res)
			 throws IOException {
		 HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = oauth2ClientService.oauth2ClientDevicelist(httpReqRes);
			 req.setAttribute("obj", obj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 

	 /**
	  * 设备对应没有的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/oauth2Client/nodeviceOCItemlist.do")
	 public void nodeviceItemList(HttpServletRequest req, HttpServletResponse res)
	   throws IOException {
	  HttpReqRes httpReqRes = new HttpReqRes(req, res);
	  JSONObject obj = null;
	  try {
	   obj = oauth2ClientService.nodeviceOCItemlist(httpReqRes);
	   /*obj.put("deviceinfo", infoPubService.appDeviceInfoList(httpReqRes));*/
	   req.setAttribute("obj", obj);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  /*System.out.println(obj.toString());*/
	  EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 
	 /**
	  * 设备对应的事项
	  * @param req
	  * @param res
	  * @throws IOException
	  */
	 @RequestMapping("/app/oauth2Client/deviceOCItemlist.do")
	 public void deviceitemList(HttpServletRequest req, HttpServletResponse res)
			 throws IOException {
		 HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = oauth2ClientService.deviceoauth2ClientItemlist(httpReqRes);
			 //System.out.println(obj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	 }
	 

	 //-------------------------------------------------------
	@Autowired
	private Oauth2ClientService oauth2ClientService;
	@Autowired
	private InfoPubService infoPubService;
	@Autowired
	private SelmItemService selmItemService;
	@Autowired
	private Oauth2ClientItemService oauth2ClientItemService;

}
