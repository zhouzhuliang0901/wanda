package com.wondersgroup.serverApply.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;
import com.wondersgroup.serverApply.bean.SelmDeviceApply;
import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.dao.SelmDeviceAlinkDao;
import com.wondersgroup.serverApply.service.SelmDeviceAlinkService;
import com.wondersgroup.serverApply.service.impl.SelmDeviceAlinkServiceImpl;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 接入申请关联设备 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmDeviceAlinkController {

	@Autowired
	private SelmDeviceAlinkService selmDeviceAlinkService;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	
	/**
	 * 设备接入申请-添加设备信息
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping("/serverApply/selmDeviceAlink/addDeviceInfo.do")
	public ModelAndView addDeviceInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ParseException {
		String stMachineId = UUID.randomUUID().toString();
		SelmDeviceAlink selmDeviceAlink = new SelmDeviceAlink();
		selmDeviceAlink.setStMachineId(stMachineId);
		String deviceApplyId = (String) req.getParameter("stDeviceApplyId");
		req.setAttribute("deviceApplyId", deviceApplyId);
		req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, selmDeviceAlink);
		
		req.getSession().setAttribute("deviceApplyId", deviceApplyId);
		req.getSession().setAttribute("stDeviceApplyFlag", "1");//添加完成返回上一页面标志
		return new ModelAndView("/serverApply/addDeviceInfo.jsp");
		
	}
	
	
	@RequestMapping("/serverApply/selmDeviceAlink/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接入申请关联设备保存失败。", "错误",
				null).toString();
		try {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			SelmDeviceAlink selmDeviceAlink = selmDeviceAlinkService.saveOrUpdate(httpReqRes);
			if (selmDeviceAlink != null)
				result = ExtAjaxReturnMessage.success("接入申请关联设备保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@RequestMapping("/serverApply/selmDeviceAlink/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeviceAlinkService.query(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/serverApply/selmDeviceAlink/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMachineId = wrapper.getParameter("stMachineId");
		String stApplyId = wrapper.getParameter("stApplyId");
		SelmDeviceAlink sda = selmDeviceAlinkService.getDeviceAlink(wrapper);	
		String base64Str = "";
		if(null != sda.getBlContent()){
			base64Str = DatatypeConverter.printBase64Binary(sda.getBlContent());
		}
		req.setAttribute("base64",base64Str);
		req.setAttribute("stMachineId", stMachineId);
		req.setAttribute("stApplyId", stApplyId);
		req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, sda);
		return new ModelAndView("/serverApply/deviceInfo.jsp");
	}
	
	@RequestMapping("/serverApply/selmDeviceAlink/deviceEdit.do")
	public ModelAndView deviceEdit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMachineId = wrapper.getParameter("stMachineId");
		String stApplyId = wrapper.getParameter("stApplyId");
		SelmDeviceAlink sda = selmDeviceAlinkService.getDeviceAlink(wrapper);
		String base64Str = "";
		if(null != sda.getBlContent()){
			base64Str = DatatypeConverter.printBase64Binary(sda.getBlContent());
		}
		req.setAttribute("base64",base64Str);
		req.setAttribute("stMachineId", stMachineId);
		req.setAttribute("stApplyId", stApplyId);
		req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, sda);
		return new ModelAndView("/serverApply/deviceEdit.jsp");
	}

	
	@RequestMapping("/serverApply/selmDeviceAlink/changeEdit.do")
	public ModelAndView changeEdit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stMachineId = wrapper.getParameter("stMachineId");
		String stApplyId = wrapper.getParameter("stApplyId");
		SelmDeviceAlink sda = selmDeviceAlinkService.getDeviceAlink(wrapper);
		String base64Str = "";
		if(null != sda.getBlContent()){
			base64Str = DatatypeConverter.printBase64Binary(sda.getBlContent());
		}
		req.setAttribute("base64",base64Str);
		req.setAttribute("stMachineId", stMachineId);
		req.setAttribute("stApplyId", stApplyId);
		req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, sda);
		return new ModelAndView("/serverApply/deviceChange/changeEdit.jsp");
	}
	

	@RequestMapping("/serverApply/selmDeviceAlink/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接入申请关联设备删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			String stDeviceApplyId = wrapper.getParameter("stApplyId");
			String stMachineId = wrapper.getParameter("stMachineId");
			selmDeviceAlinkService.remove(stDeviceApplyId, stMachineId);
			result = ExtAjaxReturnMessage.success("接入申请关联设备删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}
	
	
	@RequestMapping("/serverApply/selmDeviceAlink/deviceChange/changeRemove.do")
	public void changeRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("删除失败。");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			String stDeviceApplyId = httpReqRes.getParameter("stApplyId");
			String stMachineId = httpReqRes.getParameter("stMachineId");
			selmDeviceAlinkService.remove(stDeviceApplyId, stMachineId);
			result.success().setMsg("删除成功。");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	//编辑
	@RequestMapping("/serverApply/selmDeviceAlink/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stDeviceApplyId = wrapper.getParameter(SelmDeviceAlink.ST_DEVICE_APPLY_ID);
		String stMachineId = wrapper.getParameter(SelmDeviceAlink.ST_MACHINE_ID);
		if (!StringUtils.trimToEmpty(stDeviceApplyId).isEmpty() && !StringUtils.trimToEmpty(stMachineId).isEmpty()) {
			SelmDeviceAlink selmDeviceAlink = selmDeviceAlinkService.get(stDeviceApplyId, stMachineId);
			req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, selmDeviceAlink);
		}
		return new ModelAndView("/serverApply/editTwo.jsp");
	}

	@RequestMapping("/serverApply/selmDeviceAlink/deviceChangeSave.do")
	public void deviceChangeSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String result = ExtAjaxReturnMessage.toJsonErrorObj("服务变更设备保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<SelmDeviceAlink> selmDeviceAlink = selmDeviceAlinkService.deviceChangeSaveOrUpdate(wrapper);
			if (selmDeviceAlink != null)
				result = ExtAjaxReturnMessage.success("服务变更设备保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	
	@RequestMapping("/serverApply/selmDeviceAlink/stDeviceList.do")
	public void stDeviceList(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmDeviceAlinkService.deviceList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
		
	}
	
	
	@RequestMapping("/serverApply/selmDeviceApply/noPassReason.do")
	public ModelAndView noPassReason(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		SelmDeviceAlink sda = selmDeviceAlinkService.getDeviceAlinkById(httpReqRes);
		req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, sda);
		return new ModelAndView("/serverApply/deviceNoPass.jsp");
	}
	
	@RequestMapping("/serverApply/selmDeviceAlink/checkDeviceReason.do")
	public ModelAndView checkDeviceReason(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		SelmDeviceAlink sda = selmDeviceAlinkService.getDeviceAlinkById(httpReqRes);
		req.setAttribute(SelmDeviceAlink.SELM_DEVICE_ALINK, sda);
		return new ModelAndView("/serverApply/checkDeviceReason.jsp");
	
	}
	
	
	@RequestMapping("/serverApply/selmDeviceApply/saveNoPassReason.do")
	public void saveNoPassReason(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("保存失败。", "错误",
				null).toString();
		try {
			HttpReqRes httpReqRes = new HttpReqRes(req, res);
			int i = selmDeviceAlinkService.saveNoPassReason(httpReqRes);
			if (i != 0)
				result = ExtAjaxReturnMessage.success("保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}
	
	
}
