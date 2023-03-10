package com.wondersgroup.infopub.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;
import com.wondersgroup.sms.organ.service.SmsOrganService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.config.Config;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.base.utils.AciJsonHelper;
import com.wondersgroup.base.utils.HttpUtil;
import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubAttachment;
import com.wondersgroup.infopub.bean.InfopubCompany;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoHis;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubDeviceResult;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubGroup;
import com.wondersgroup.infopub.bean.InfopubGroupDevice;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.bean.InfopubOnoff;
import com.wondersgroup.infopub.bean.InfopubPsource;
import com.wondersgroup.infopub.bean.InfopubPsourceExt;
import com.wondersgroup.infopub.bean.InfopubPublish;
import com.wondersgroup.infopub.bean.InfopubWorkspace;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.service.InfoPubService;
import com.wondersgroup.infopub.service.InfopubAddressService;
import com.wondersgroup.infopub.service.InfopubCompanyService;
import com.wondersgroup.infopub.util.AESUtils;
import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.userRole.bean.SmsUserRole;

import coral.base.app.AppContext;
import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;

/**
 * ??????web?????????
 * 
 * @author guicb
 * 
 */
@Controller
public class InfoPubController {

	@Autowired
	private InfopubCompanyService infopubCompanyService;
	@Autowired
	private InfopubAddressService infopubAddressService;
	@Autowired
	private SmsOrganService smsOrganService;
	@Autowired
	private SmsOrganDao smsOrganDao;

	/**
	 * xaizai ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	// ifillform_device_info
	/*
	 * @CheckPermissions(roles = { "admin" }, permissions = {
	 * "ifillform_device_info_oper",
	 * "infopub_tuyou","ifillform_device_devicewarn"
	 * ,"ifillform_device_ma","ifillform_device_info_info",
	 * "selm_delivery_deviceInfo","selm_delivery_parcel"})
	 */
	@RequestMapping("/infopub/deviceinfo/list.do")
	public void deviceInfoList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceInfoList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceWarnInfo/list.do")
	public void deviceWarnInfoList(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceWarnInfoList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceWarnInfo/count.do")
	public void deviceWarnInfocount(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceWarnInfoCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/infopub/deviceinfo/singleStreetDeviceList.do")
	public void singleStreetDeviceList(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.streetDeviceList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_device_info_add", "selm_device_info_add" })
	@RequestMapping("/infopub/deviceinfo/save.do")
	public void deviceSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getSuccessResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceInfo infopubDeviceInfo = infoPubService
					.saveOrUpdateDeviceInfo(httpReqRes);
			InfopubOnoff infopubOnoff = infoPubService.saveOrUpdateDeviceOnOff(
					httpReqRes, infopubDeviceInfo.getStDeviceId());

			if (infopubDeviceInfo != null && infopubOnoff != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = Result.getResult();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????????????????
	 */
	@RequestMapping("/infopub/deviceinfo/CertKeySave.do")
	public void CertKeySaveOrUpdate(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceInfo infopubDeviceInfo = infoPubService
					.CertKeySaveOrUpdate(httpReqRes);
			if (infopubDeviceInfo != null) {
				result.setSuccess(true);
				result.setMsg("??????");
				result.setCode("200");
			} else {
				result.setSuccess(false);
				result.setMsg("????????????????????????????????????");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????????????????????????????/infopub/deviceinfo/KeyEncryptionSave.do
	 */
	@RequestMapping("/infopub/deviceinfo/KeyEncryptionSave.do")
	public void KeyEncryptionSaveOrUpdate(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceInfo infopubDeviceInfo = infoPubService
					.KeyEncryptionSaveOrUpdate(httpReqRes);
			if (infopubDeviceInfo != null) {
				result.setSuccess(true);
				result.setMsg("??????");
				result.setCode("200");
			} else {
				result.setCode("500");
				result.setMsg("??????????????????");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????excel??????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/deviceImport.do")
	public void deviceImport(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceInfo infopubDeviceInfo = infoPubService
					.deviceImport(httpReqRes);
			if (infopubDeviceInfo != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);

	}

	/**
	 * ??????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*
	 * @CheckPermissions(roles = { "admin" }, permissions = {
	 * "ifillform_device_info_info",
	 * "ifillform_device_info_deviceinfo","sys_addressType_list_info" })
	 */
	@RequestMapping("/infopub/deviceinfo/info.do")
	public ModelAndView deviceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService
				.getDeviceInfo(httpReqRes);
		InfopubOnoff infopubOnoff = infoPubService
				.getDeviceOnOffInfo(httpReqRes);
		req.setAttribute(InfopubOnoff.INFOPUB_ONOFF, infopubOnoff);
		String stTypeId = infopubDeviceInfo.getStTypeId();
		InfopubDeviceType deviceType = infoPubService.getDeviceType(stTypeId);
		infopubDeviceInfo.setStTypeId(deviceType.getStTypeName());
		if (infopubDeviceInfo.getStOrganId() != null
				&& !infopubDeviceInfo.getStOrganId().isEmpty()) {
			SmsOrgan smsOrgan = smsOrganDao.get(infopubDeviceInfo
					.getStOrganId());
			infopubDeviceInfo.setStOrganId(smsOrgan.getStOrganName());
		}
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/infopub/deviceinfo/info.jsp");
	}

	@CheckPermissions(roles = { "admin" }, permissions = { "selm_device_info_info" })
	@RequestMapping("/infopub/delivery/deliveryDeviceInfo.do")
	public ModelAndView deliveryDeviceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService
				.getDeviceInfo(httpReqRes);
		InfopubOnoff infopubOnoff = infoPubService
				.getDeviceOnOffInfo(httpReqRes);
		req.setAttribute(InfopubOnoff.INFOPUB_ONOFF, infopubOnoff);
		/*
		 * String stAddressId = infopubDeviceInfo.getStAddressId();
		 * if(stAddressId!=null){ InfopubAddress infopubAddress =
		 * infopubAddressService.get(infopubDeviceInfo.getStAddressId());
		 * infopubDeviceInfo
		 * .setStDeviceAddress(infopubAddress.getStCity()+infopubAddress
		 * .getStDistrict
		 * ()+infopubAddress.getStStreet()+infopubAddress.getStAddress());
		 * infopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
		 * infopubDeviceInfo.setNmLng(infopubAddress.getNmLng()); }
		 */
		String stTypeId = infopubDeviceInfo.getStTypeId();
		InfopubDeviceType deviceType = infoPubService.getDeviceType(stTypeId);
		infopubDeviceInfo.setStTypeId(deviceType.getStTypeName());
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/selmDelivery/deviceInfo/info.jsp");
	}

	@RequestMapping("/infopub/delivery/deliveryDeviceEdit.do")
	public ModelAndView deliveryDeviceEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService
				.getDeviceInfo(httpReqRes);
		InfopubOnoff infopubOnoff = infoPubService
				.getDeviceOnOffInfo(httpReqRes);
		req.setAttribute(InfopubOnoff.INFOPUB_ONOFF, infopubOnoff);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/selmDelivery/deviceInfo/edit.jsp");
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_device_info_edit", "selm_device_info_edit" })
	@RequestMapping("/infopub/deviceinfo/edit.do")
	public ModelAndView deviceEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService
				.getDeviceInfo(httpReqRes);
		InfopubOnoff infopubOnoff = infoPubService
				.getDeviceOnOffInfo(httpReqRes);
		req.setAttribute(InfopubOnoff.INFOPUB_ONOFF, infopubOnoff);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/infopub/deviceinfo/edit.jsp");
	}

	/**
	 * ??????????????????xml???????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/modify.do")
	public ModelAndView addModifyInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService
				.getDeviceInfo(httpReqRes);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		String stAttachId = infopubDeviceInfo.getStConfigId();
		System.out.println(stAttachId + "-------------");
		if (stAttachId != null) {
			InfopubAttachment infopubAttachment = infoPubService
					.getById(stAttachId);
			if (infopubAttachment != null) {
				String clContent = infopubAttachment.getClContent();
				System.out.println("?????????????????????" + clContent);
				req.setAttribute("clContent", clContent);
			}
		}
		return new ModelAndView("/infopub/deviceinfo/modify.jsp");
	}

	/**
	 * ?????????????????????????????????xml?????????????????????????????????xml?????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/modifyoperate.do")
	public void modifyoperate(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.success().setMsg("????????????");
		String jsonString = infoPubService.modifyOperate(httpReqRes);
		if (!StringUtils.trimToEmpty(jsonString).isEmpty()) {
			result.success().setMsg("????????????");
			result.setData(jsonString);
			System.out.println(jsonString);
		}

		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????xml??????(????????????)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/modifyXML.do")
	public void modifyXML(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.success().setMsg("????????????");
		String jsonString = infoPubService.modifyXML(httpReqRes);
		if (!StringUtils.trimToEmpty(jsonString).isEmpty()) {
			result.success().setMsg("????????????");
		}

		EasyUIHelper.writeResponse(res, jsonString);
	}

	/**
	 * ?????????xml??????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/inforpub/deviceinfo/XMLPreloading.do")
	public void xmlPreloading(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		infoPubService.loadingXML(httpReqRes);

	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*
	 * @CheckPermissions(roles = { "admin" }, permissions = {
	 * "ifillform_device_info_remove",
	 * "ifillform_device_info_bremove","selm_device_item_remove",
	 * "selm_device_info_remove" ,"selm_device_info_bremove"})
	 */
	@RequestMapping("/infopub/deviceinfo/remove.do")
	public void deviceRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
//			infoPubService.deviceRemove(httpReqRes);
			infoPubService.deviceLogicRemove(httpReqRes); 	//????????????
			result.success().setMsg("????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_info_onoff" })
	@RequestMapping("/infopub/deviceonoff/list.do")
	public void deviceOnOffList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceOnOffList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_info_onoff_edit" })
	@RequestMapping("/infopub/deviceonoff/edit.do")
	public ModelAndView deviceOnOffEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOnoff infopubOnoff = infoPubService
				.getDeviceOnOffInfo(httpReqRes);
		req.setAttribute(InfopubOnoff.INFOPUB_ONOFF, infopubOnoff);
		return new ModelAndView("/infopub/deviceinfo/on-off-edit.jsp");
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_info_onoff_save" })
	@RequestMapping("/infopub/deviceonoff/save.do")
	public void deviceOnOffSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String deviceId = httpReqRes.getParameter(InfopubOnoff.ST_DEVICE_ID);
		try {
			InfopubOnoff infopubOnoff = infoPubService.saveOrUpdateDeviceOnOff(
					httpReqRes, deviceId);
			if (infopubOnoff != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_device_info_onoff_bremove",
			"ifillform_device_info_onoff_remove" })
	@RequestMapping("/infopub/deviceonoff/remove.do")
	public void deviceOnOffRemove(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.deviceOnOffRemove(httpReqRes);
			result.success().setMsg("????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_log" })
	@RequestMapping("/infopub/deviceonlog/list.do")
	public void deviceLogList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceLogList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_device_log_remove", "ifillform_device_log_bremove" })
	@RequestMapping("/infopub/deviceonlog/remove.do")
	public void deviceLogRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("??????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.deviceLogRemove(httpReqRes);
			result.success().setMsg("??????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_log_info" })
	@RequestMapping("/infopub/deviceonlog/info.do")
	public ModelAndView deviceLogInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceLog infopubDeviceLog = infoPubService
				.getDeviceLogInfo(httpReqRes);
		req.setAttribute(InfopubDeviceLog.INFOPUB_DEVICE_LOG, infopubDeviceLog);
		return new ModelAndView("/infopub/infopublog/info.jsp");
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/operate.do")
	public void deviceOperate(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.success().setMsg("????????????");
		try {
			infoPubService.deviceOperate(httpReqRes, null);
			result.success().setMsg("????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/getAttachIds.do")
	public void getAttachIds(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		try {
			List<String> attchmentIds = infoPubService.getAttachIds(httpReqRes);
			if (attchmentIds.size() != 0) {
				result.success().setData(attchmentIds.get(0));
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_workspace" })
	@RequestMapping("/infopub/workspace/list.do")
	public void workSpaceList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject object = null;
		try {
			object = infoPubService.workSpaceList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, object.toString());
	}

	/**
	 * ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_workspace_save" })
	@RequestMapping("/infopub/workspace/save.do")
	public void workSpaceSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		try {
			// ????????????????????????
			InfopubWorkspace ifillWorkspace = infoPubService
					.workSpaceSave(httpReqRes);
			if (ifillWorkspace != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_workspace_info" })
	@RequestMapping("/infopub/workspace/info.do")
	public ModelAndView workSpaceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubWorkspace infopubWorkspace = infoPubService
				.getWorkSpaceInfo(httpReqRes);
		req.setAttribute(InfopubWorkspace.INFOPUB_WORKSPACE, infopubWorkspace);
		return new ModelAndView("/infopub/workspace/info.jsp");
	}

	/**
	 * ??????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_workspace_edit" })
	@RequestMapping("/infopub/workspace/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubWorkspace infopubWorkspace = infoPubService
				.getWorkSpaceInfo(httpReqRes);
		req.setAttribute(InfopubWorkspace.INFOPUB_WORKSPACE, infopubWorkspace);
		return new ModelAndView("/infopub/workspace/edit.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_infopub_workspace_remove",
			"ifillform_infopub_workspace_bremove" })
	@RequestMapping("/infopub/workspace/remove.do")
	public void workSpaceRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.setMsg("????????????");
		try {
			infoPubService.workSpaceRemove(httpReqRes);
			result.setMsg("????????????");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@RequestMapping("infopub/workspace/checkUserSelect.do")
	public void checkUserSelect(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			List<SmsResourceAccessList> smsResourceAccessList = infoPubService
					.checkUserSelect(httpReqRes);
			obj = new JSONObject();
			obj.put("data", smsResourceAccessList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_devicegroup" })
	@RequestMapping("/infopub/devicegroup/list.do")
	public void deviceGroupList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject object = null;
		try {
			object = infoPubService.deviceGroupList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, object.toString());
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_devicegroup_save" })
	@RequestMapping("/infopub/devicegroup/save.do")
	public void deviceGroupSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		try {
			// ????????????????????????
			InfopubGroup ifillWorkspace = infoPubService
					.deviceGroupSaveOrUpate(httpReqRes);
			if (ifillWorkspace != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_devicegroup_info" })
	@RequestMapping("/infopub/devicegroup/info.do")
	public ModelAndView deviceGroupInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubGroup infopubGroupInfo = infoPubService
				.getDeviceGroupInfo(httpReqRes);
		req.setAttribute(InfopubGroup.INFOPUB_GROUP, infopubGroupInfo);
		return new ModelAndView("/infopub/group/info.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_devicegroup_edit" })
	@RequestMapping("/infopub/devicegroup/edit.do")
	public ModelAndView deviceGroupEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubGroup infopubWorkspace = infoPubService
				.getDeviceGroupInfo(httpReqRes);
		req.setAttribute(InfopubGroup.INFOPUB_GROUP, infopubWorkspace);
		return new ModelAndView("/infopub/group/edit.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_infopub_devicegroup_bremove",
			"ifillform_infopub_devicegroup_remove" })
	@RequestMapping("/infopub/devicegroup/remove.do")
	public void deviceGroupRemove(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.setMsg("????????????");
		try {
			infoPubService.deviceGroupRemove(httpReqRes);
			result.setMsg("????????????");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/devicegroup/deviceGroupSelect.do")
	public void deviceGroupSelect(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			List<InfopubDeviceInfo> InfopubDeviceInfoList = infoPubService
					.groupDeviceSelect(httpReqRes);
			obj = new JSONObject();
			obj.put("data", InfopubDeviceInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_devicegroup_setonoff" })
	@RequestMapping("/infopub/devicegroup/deviceGroupOnoffSet.do")
	public void deviceGroupOnoffSet(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			List<InfopubGroupDevice> infopubGroupDeviceList = infoPubService
					.deviceGroupSelect(httpReqRes);
			if (infopubGroupDeviceList != null) {
				for (InfopubGroupDevice infopubGroupDevice : infopubGroupDeviceList) {
					infoPubService.saveOrUpdateDeviceOnOff(httpReqRes,
							infopubGroupDevice.getStDeviceId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_infopub_devicegroup_setpublish" })
	@RequestMapping("/infopub/devicegroup/deviceGroupPublishSet.do")
	public void deviceGroupPublishSet(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			List<InfopubGroupDevice> infopubGroupDeviceList = infoPubService
					.deviceGroupSelect(httpReqRes);
			if (infopubGroupDeviceList != null) {
				for (InfopubGroupDevice infopubGroupDevice : infopubGroupDeviceList) {
					infoPubService.saveOrUpdatePublish(httpReqRes,
							infopubGroupDevice.getStDeviceId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/devicegroup/operate.do")
	public void deviceGroupOperate(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.success().setMsg("????????????");
		try {
			List<InfopubGroupDevice> infopubGroupDeviceList = infoPubService
					.deviceGroupSelect(httpReqRes);
			if (infopubGroupDeviceList != null) {
				for (InfopubGroupDevice infopubGroupDevice : infopubGroupDeviceList) {
					infoPubService.deviceOperate(httpReqRes,
							infopubGroupDevice.getStDeviceId());
				}
				result.success().setMsg("????????????");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceattachment/remove.do")
	public void deviceAttachmentRemove(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.setMsg("????????????");
		try {
			infoPubService.deviceAttachmentRemove(httpReqRes);
			result.setMsg("????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceattachment/removeshot.do")
	public void deviceAttachmentRemoveShot(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.setMsg("????????????");
		try {
			infoPubService.deviceAttachmentRemoveShot(httpReqRes);
			result.setMsg("????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_psource" })
	@RequestMapping("/infopub/psource/list.do")
	public void psrouceList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.psrouceList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_psource_save" })
	@RequestMapping("/infopub/psource/save.do")
	public void psrouceSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubPsource infopubPsource = infoPubService
					.saveOrUpdatePsrouce(httpReqRes);
			if (infopubPsource != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ???????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_psource_info" })
	@RequestMapping("/infopub/psource/info.do")
	public ModelAndView psrouceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubPsourceExt infopubPsourceExt = infoPubService
				.getPsrouceInfo(httpReqRes);
		req.setAttribute(InfopubPsource.INFOPUB_PSOURCE, infopubPsourceExt);
		return new ModelAndView("/infopub/psource/info.jsp");
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_psource_edit" })
	@RequestMapping("/infopub/psource/edit.do")
	public ModelAndView psrouceEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubPsource infopubPsource = infoPubService
				.getPsrouceInfo(httpReqRes);
		InfopubAttachment infopubAttachment = infoPubService
				.getInfopubAttachment(httpReqRes,
						infopubPsource.getStAttachId());
		req.setAttribute(InfopubPsource.INFOPUB_PSOURCE, infopubPsource);
		req.setAttribute(InfopubAttachment.INFOPUB_ATTACHMENT,
				infopubAttachment);
		return new ModelAndView("/infopub/psource/edit.jsp");
	}

	/**
	 * ???????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_device_psource_bremove",
			"ifillform_device_psource_remove" })
	@RequestMapping("/infopub/psource/remove.do")
	public void psrouceRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.psrouceRemove(httpReqRes);
			result.success().setMsg("????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/publish/list.do")
	public void publishList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.publishList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/publish/save.do")
	public void publishSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			String deviceId = httpReqRes
					.getParameter(InfopubPublish.ST_DEVICE_ID);
			InfopubPublish infopubPublish = infoPubService.saveOrUpdatePublish(
					httpReqRes, deviceId);
			if (infopubPublish != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/publish/edit.do")
	public ModelAndView publishEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubPublish infopubPublish = infoPubService
					.getPublish(httpReqRes);
			req.setAttribute(InfopubPublish.INFOPUB_PUBLISH, infopubPublish);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/infopub/deviceinfo/publish-edit.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/publish/remove.do")
	public void publishRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.publishRemove(httpReqRes);
			result.success().setMsg("????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "device-type" })
	@RequestMapping("/infopub/deviceinfotype/list.do")
	public void deviceInfoTypeList(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceInfoTypeList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = { "device-type"})
	@RequestMapping("/infopub/odeviceInfo/list.do")
	public void odeviceInfoList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.odeviceInfoList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ?????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfotype/optionList.do")
	public void deviceTypeOptionList(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceTypeOptionList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfotype/odevicelist.do")
	public void subDeviceInfoTypeList(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.subDeviceInfoTypeList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceinfotype/init.do")
	public void deviceinfotypeInit(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = infopubCompanyService.companyList(httpReqRes);
			obj.put("infopubCompany", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceCompWithType/init.do")
	public void deviceCompWithType(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = infopubCompanyService.CompWithType(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceArea/init.do")
	public void deviceArea(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = infopubAddressService.getAllArea(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceinfo/init.do")
	public void init(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		RequestWrapper wrapper = new RequestWrapper(req);
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			/*
			 * obj.put("area", infoPubService.areaList(httpReqRes)); JSONObject
			 * addressJSON = infopubAddressService.initAddress(httpReqRes);
			 * obj.put("address", addressJSON); JSONObject devicetypeJSON =
			 * infoPubService.InitDeviceType(httpReqRes); obj.put("devicetype",
			 * devicetypeJSON);
			 */
			// obj.put("area", infoPubService.areaList(httpReqRes));
			// obj.put("deviceinfo", infoPubService.deviceInfoList(httpReqRes));
			JSONObject addressJSON = infopubAddressService
					.initAddress(httpReqRes);
			JSONObject devicetypeJSON = infoPubService
					.InitDeviceType(httpReqRes);
			PaginationArrayList<SmsOrgan> organList = smsOrganService
					.queryCompetent(wrapper);
			JSONArray organArray = JSONArray.fromObject(organList);
			JSONObject organJSON = new JSONObject();
			organJSON.put("data", organArray);
			obj.put("address", addressJSON);
			obj.put("devicetype", devicetypeJSON);
			obj.put("organList", organJSON);

		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/devicetype/init.do")
	public void initDeviceType(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = infoPubService.deviceInfoTypeInit(httpReqRes);
			List<InfopubDeviceType> infopubDeviceTypeList = (List<InfopubDeviceType>) json
					.get("data");
			InfopubDeviceType infopubDeviceType = new InfopubDeviceType();
			infopubDeviceType.setStTypeId(null);
			infopubDeviceTypeList.add(infopubDeviceType);
			obj.put("devicetype", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceAddress/init.do")
	public void initDeviceAddress(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = infoPubService.deviceAddressInit(httpReqRes);
			if (null != json) {
				obj.put("code", "success");
				obj.put("data", json);
			} else {
				obj.put("code", "error");
				obj.put("msg", "??????????????????");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());

	}

	/**
	 * ????????????
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceDistric/init.do")
	public void initDeviceDistrict(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = infoPubService.deviceDistrict(httpReqRes);
			obj.put("deviceDistrict", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());

	}

	/**
	 * ????????????
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/deviceStreet/init.do")
	public void initDeviceStreet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = infoPubService.deviceStreet(httpReqRes);
			obj.put("deviceStreet", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());

	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	/*
	 * @CheckPermissions(roles = { "admin" }, permissions = { "selm_device_item"
	 * })
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/infopub/devicegroup/init.do")
	public void initDeviceGroup(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			JSONObject json = infoPubService.deviceGroupList(httpReqRes);
			List<InfopubGroup> infopubDeviceList = (List<InfopubGroup>) json
					.get("data");
			InfopubGroup infopubGroup = new InfopubGroup();
			infopubGroup.setStGroupId(null);
			infopubDeviceList.add(infopubGroup);
			obj.put("devicegroup", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_type_add" })
	@RequestMapping("/infopub/deviceinfotype/save.do")
	public void deviceTypeSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceType infopubDeviceType = infoPubService
					.deviceInfoTypeSaveOrUpate(httpReqRes);
			if (infopubDeviceType != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = {
	// "ifillform_device_type_add" })
	@RequestMapping("/infopub/odeviceInfo/save.do")
	public void odeviceSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceType infopubDeviceType = infoPubService
					.odeviceInfoSaveOrUpate(httpReqRes);
			if (infopubDeviceType != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfotype/add.do")
	public ModelAndView deviceTypeOdeviceAdd(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType deviceInfoType = infoPubService
				.getDeviceInfoType(httpReqRes);
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE, deviceInfoType);
		return new ModelAndView("/infopub/devicetype/odeviceadd.jsp");
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "device-type-info" })
	@RequestMapping("/infopub/deviceinfotype/info.do")
	public ModelAndView deviceTypeInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType deviceInfoType = infoPubService
				.getDeviceInfoType(httpReqRes);
		if (deviceInfoType.getStCompanyId() != null) {
			InfopubCompany infopubCompany = infopubCompanyService
					.get(deviceInfoType.getStCompanyId());
			deviceInfoType.setStCompanyId(infopubCompany.getStCompanyName());
		}
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE, deviceInfoType);
		return new ModelAndView("/infopub/devicetype/info.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = { "device-type-info"
	// })
	@RequestMapping("/infopub/odeviceInfo/info.do")
	public ModelAndView odeviceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType deviceInfoType = infoPubService
				.getDeviceInfoType(httpReqRes);
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE, deviceInfoType);
		return new ModelAndView("/infopub/odevice/info.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = { "device-type-info"
	// })
	@RequestMapping("/infopub/deviceinfotype/check.do")
	public ModelAndView outdeviceTypeInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType infopubDeviceType = infoPubService
				.getDeviceInfoType(httpReqRes);
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE,
				infopubDeviceType);
		return new ModelAndView("/infopub/devicetype/check.jsp");
	}

	/**
	 * ??????(??????)????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "device-type-edit" })
	@RequestMapping("/infopub/deviceinfotype/edit.do")
	public ModelAndView deviceTypeEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType infopubDeviceType = infoPubService
				.getDeviceInfoType(httpReqRes);
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE,
				infopubDeviceType);
		return new ModelAndView("/infopub/devicetype/edit.jsp");
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = { "device-type-edit"
	// })
	@RequestMapping("/infopub/odeviceInfo/edit.do")
	public ModelAndView odeviceEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType infopubDeviceType = infoPubService
				.getDeviceInfoType(httpReqRes);
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE,
				infopubDeviceType);
		return new ModelAndView("/infopub/odevice/edit.jsp");
	}

	/**
	 * ??????(??????)??????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = { "device-type-edit"
	// })
	@RequestMapping("/infopub/deviceinfotype/outdeviceEdit.do")
	public ModelAndView outdeviceEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceType infopubDeviceType = infoPubService
				.getDeviceInfoType(httpReqRes);
		req.setAttribute(InfopubDeviceType.INFOPUB_DEVICE_TYPE,
				infopubDeviceType);
		return new ModelAndView("/infopub/devicetype/outdeviceEdit.jsp");
	}

	/**
	 * ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"device-type-remove", "device-type-bremove" })
	@RequestMapping("/infopub/deviceinfotype/remove.do")
	public void deviceTypeRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("??????????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.deviceInfoTypeRemove(httpReqRes);
			result.success().setMsg("??????????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions =
	// {"device-type-remove", "device-type-bremove" })
	@RequestMapping("/infopub/odeviceInfo/remove.do")
	public void odeviceRemove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.odeviceInfoRemove(httpReqRes);
			result.success().setMsg("????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/checkTypeCode.do")
	public void checkTypeCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			// ????????????
			RequestWrapper wrapper = new RequestWrapper(req);
			boolean result = true;
			// ????????????????????????
			result = infoPubService.checkTypeCode(wrapper);
			// ??????json??????
			JSONObject obj = new JSONObject();
			// ??????????????????????????????
			obj.put("data", result);
			EasyUIHelper.writeResponse(res, obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/savePic.do")
	public void savePicture(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		try {
			List<String> attchmentIds = infoPubService.getAttachIds(httpReqRes);
			if (attchmentIds.size() != 0) {
				result.success().setData(attchmentIds.get(0));
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);

	}

	@Autowired
	private InfoPubService infoPubService;

	/**
	 * ?????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	/*
	 * @RequestMapping("/outdevicestatus/odeviceStatus/save.do") public void
	 * odeviceStatusSave(HttpServletRequest req, HttpServletResponse res) throws
	 * IOException, JSONException { Result result = Result.getResult();
	 * HttpReqRes httpReqRes = new HttpReqRes(req, res); InfopubOdeviceStatus
	 * infopubOdeviceStatus = null; try { infopubOdeviceStatus =
	 * infoPubService.outDeviceStatusSave(httpReqRes); if (infopubOdeviceStatus
	 * != null){ result.setSuccess(true); }
	 * result.setData(infopubOdeviceStatus.toString()); } catch (Exception ex) {
	 * ex.printStackTrace(); } httpReqRes.writeJsonP(result); }
	 */

	/**
	 * ??????????????????(????????????id???????????????)
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	/*
	 * @RequestMapping("/outdevicestatus/odeviceStatus/info.do") public void
	 * odeviceInfo(HttpServletRequest req, HttpServletResponse res) throws
	 * IOException { Result result = Result.getResult(); HttpReqRes httpReqRes =
	 * new HttpReqRes(req, res); InfopubOdeviceStatus infopubOdeviceStatus =
	 * null; try { infopubOdeviceStatus = infoPubService.getOdevice(httpReqRes);
	 * result.setData(infopubOdeviceStatus.toString()); } catch (Exception e) {
	 * e.printStackTrace(); } httpReqRes.writeJsonP(result);
	 * 
	 * }
	 */

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * 
	 * @author guicb
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/odeviceStatus/saveupdate.do")
	public void odeviceStatusSaveUpdate(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus infopubOdeviceStatus = null;
		try {
			infopubOdeviceStatus = infoPubService
					.odeviceStatusSaveOrUpate(httpReqRes);
			if (infopubOdeviceStatus != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_devicewarn_list_peripheral_info" })
	@RequestMapping("/infopub/odeviceStatus/info.do")
	public ModelAndView odeviceStatusInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus odeviceStatus = infoPubService
				.getOdeviceStatus(httpReqRes);
		if (odeviceStatus != null) {
			String stDeviceId = odeviceStatus.getStDeviceId();
			InfopubDeviceInfo deviceId = infoPubService.getDeviceId(stDeviceId);
			odeviceStatus.setStDeviceId(deviceId.getStDeviceCode());
		}
		req.setAttribute(InfopubOdeviceStatus.INFOPUB_ODEVICE_STATUS,
				odeviceStatus);
		return new ModelAndView("/infopub/odeviceStatus/info.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/list.do")
	public void odeviceStatusInfoList(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.getOdeviceStatusList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ?????????????????????????????? ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/listMap.do")
	public void odeviceStatusInfoListMap(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.getOdeviceStatusList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/listCount.do")
	public void odeviceStatusInfoListCount(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.getOdeviceStatusInfoListCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/listCountdate.do")
	public void odeviceStatusInfoListdate(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.getOdeviceStatusInfoListdate(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}

	/**
	 * ????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/edit.do")
	public ModelAndView odeviceStatusEdit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubOdeviceStatus infopubOdeviceStatus = infoPubService
				.getOdeviceStatus(httpReqRes);
		req.setAttribute(InfopubOdeviceStatus.INFOPUB_ODEVICE_STATUS,
				infopubOdeviceStatus);
		return new ModelAndView("/infopub/odeviceStatus/edit.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odeviceStatus/remove.do")
	public void odeviceStatusRemove(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("??????????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infoPubService.odeviceStatusRemove(httpReqRes);
			result.success().setMsg("??????????????????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/saveImage.do")
	public void saveUserMs(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.setMsg("????????????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubAttachment infopubAttachment = infoPubService
					.saveImage(httpReqRes);
			if (infopubAttachment != null)
				result.setMsg("????????????????????????");
			result.setCode("200");
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????????????????
	 */
	@RequestMapping("/infopub/deviceinfo/isonline.do")
	public void isOnline(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			InfopubDeviceInfo infopubDeviceInfo = infoPubService
					.isOnline(httpReqRes);
			if (infopubDeviceInfo != null)
				result.setSuccess(true);
			result.setCode("200");
			result.setMsg("??????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ????????????????????????
	 */
	@ResponseBody
	@RequestMapping(value = "/infopub/deviceinfo/batchisonline.do", method = RequestMethod.POST)
	public void jhisOnline(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			JSONObject obj = infoPubService.jhisonline(httpReqRes);
			boolean msg = obj.getBoolean("msg");
			if (msg) {
				JSONArray u1 = (JSONArray) obj.get("?????????????????????");
				JSONArray u2 = (JSONArray) obj.get("????????????????????????");
				JSONArray u3 = (JSONArray) obj.get("????????????????????????");
				result.setSuccess(true);
				result.setCode("200");
				result.setMsg("???????????????????????????" + u2.toString() + ",???????????????????????????"
						+ u3.toString());
				result.setData("?????????" + u1.size() + "?????????");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		httpReqRes.writeJsonP(result);
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/infopub/deviceinfo/batchisonline.do",
	 * method=RequestMethod.POST) public void setDeviceStatus(HttpServletRequest
	 * req, HttpServletResponse res) throws IOException{ Result result =
	 * Result.getResult(); HttpReqRes httpReqRes = new HttpReqRes(req, res);
	 * try{ JSONObject obj = infoPubService.jhisonline(httpReqRes); boolean msg
	 * = obj.getBoolean("msg"); if(msg){ JSONArray u1 = (JSONArray)
	 * obj.get("?????????????????????"); JSONArray u2 = (JSONArray) obj.get("????????????????????????");
	 * JSONArray u3 = (JSONArray) obj.get("????????????????????????"); result.setSuccess(true);
	 * result.setCode("200");
	 * result.setMsg("???????????????????????????"+u2.toString()+",???????????????????????????"+u3.toString());
	 * result.setData("?????????"+u1.size()+"?????????"); }else{
	 * result.setMsg("????????????????????????????????????"); result.setSuccess(false);
	 * result.setCode("403"); result.setVersion("1.0"); }
	 * 
	 * }catch(Exception e){ e.printStackTrace(); }
	 * 
	 * httpReqRes.writeJsonP(result); }
	 */

	/**
	 * ????????????
	 */
	@RequestMapping("/infopub/deviceinfo/placeshow.do")
	public void placeShow(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject json = null;
		try {
			json = infoPubService.getAlldeviceInfo(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(json.toString());
	}

	/**
	 * ????????????????????????
	 */
	@RequestMapping("/infopub/deviceinfo/infopubMap.do")
	public void infopubMap(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject json = null;
		try {
			json = infoPubService.infopubMapList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(json.toString());
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = {
	// "sms_system_menu_update" })
	@RequestMapping("/infopub/area/edit.do")
	public ModelAndView editArea(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);

		String stAreaId = wrapper.getParameter(InfopubArea.ST_AREA_ID);
		if (!StringUtils.trimToEmpty(stAreaId).isEmpty()) {
			InfopubArea infopubArea = infoPubService.get(stAreaId);
			req.setAttribute(InfopubArea.INFOPUB_AREA, infopubArea);
		}
		return new ModelAndView("/area/edit.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = {
	// "sms_system_menu_info" })
	@RequestMapping("/infopub/area/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);

		String stAreaId = wrapper.getParameter(InfopubArea.ST_AREA_ID);
		InfopubArea infopubArea = infoPubService.get(stAreaId);
		req.setAttribute(InfopubArea.INFOPUB_AREA, infopubArea);
		return new ModelAndView("/area/info.jsp");
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = { "sms_system_menu"
	// })
	@CheckPermissions(roles = { "admin" }, permissions = { "area-manager-info" })
	@RequestMapping("/infopub/area/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());

	}

	/**
	 * ?????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = {
	// "sms_system_menu_bremove",
	// "sms_system_menu_remove"})
	@RequestMapping("/infopub/area/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("????????????????????????", "??????",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// ??????ID
			String[] roleIdList = wrapper.getParameterValues("stAreaId[]");
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("stAreaId");
				if (roleId != null) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("??????ID????????????");
				}
			}
			infoPubService.remove(roleIdList);
			result = ExtAjaxReturnMessage.success("????????????????????????", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * ??????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	// @CheckPermissions(roles = { "admin" }, permissions = {
	// "sms_system_menu_update" })
	@RequestMapping("/infopub/area/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = "????????????????????????";
		// ??????json??????
		JSONObject obj = new JSONObject();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			InfopubArea infopubArea = infoPubService.saveOrUpdate(wrapper);
			if (infopubArea != null)
				result = "?????????????????????,";
			// ??????????????????????????????
			obj.put("InfopubArea", infopubArea);
			// ????????????
			obj.put("result", result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/infopub/area/checkAreaCode.do")
	public void checkAreaCode(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		JSONObject obj = new JSONObject();
		try {
			// ????????????
			RequestWrapper wrapper = new RequestWrapper(req);
			boolean result = true;
			// ????????????????????????
			result = infoPubService.checkAreaCode(wrapper);
			// ??????json??????
			// ??????????????????????????????
			obj.put("data", result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????MAC???????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/MacCheck.do")
	public void checkDeviceMac(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		JSONObject obj = new JSONObject();
		try {
			// ????????????
			RequestWrapper wrapper = new RequestWrapper(req);
			boolean result = true;
			// ????????????????????????
			result = infoPubService.checkDeviceMac(wrapper);
			// ??????json??????
			// ??????????????????????????????
			obj.put("data", result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceresult/save.do")
	public void saveOrUpdate(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();

		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			/*
			 * InfopubDeviceResult deviceResult =
			 * infoPubService.addOrUpdate(httpReqRes); if (deviceResult != null)
			 * { result.success().setMsg("????????????");
			 * result.success().setCode("200"); }else{
			 * result.success().setMsg("????????????"); result.success().setCode("500");
			 * }
			 */

			result.success().setMsg("????????????");
			result.success().setCode("200");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ?????????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/devicewarn/odevicewarn.do")
	public ModelAndView odevicewarn(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService
				.getDeviceInfo(httpReqRes);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/infopub/devicewarn/odevicewarn.jsp");
	}

	/**
	 * ??????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/infopub/odevicewarn/list.do")
	public void odevicewarnList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.getOdevicewarnList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * xaizai ?????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/listMac.do")
	public void deviceInfoListMac(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceInfoListMac(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_company_edit" })
	@RequestMapping("/infopub/infopubCompany/edit.do")
	public ModelAndView editCompany(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// InfopubCompany.ST_COMPANY_ID
		String stCompanyId = wrapper.getParameter(InfopubCompany.ST_COMPANY_ID);
		if (!StringUtils.trimToEmpty(stCompanyId).isEmpty()) {
			InfopubCompany infopubCompany = infopubCompanyService
					.get(stCompanyId);
			req.setAttribute(InfopubCompany.INFOPUB_COMPANY, infopubCompany);
		}
		return new ModelAndView("/infopub/company/edit.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_company_info" })
	@RequestMapping("/infopub/infopubCompany/info.do")
	public ModelAndView infoCompany(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// InfopubCompany.ST_COMPANY_ID
		String stCompanyId = wrapper.getParameter(InfopubCompany.ST_COMPANY_ID);
		InfopubCompany infopubCompany = infopubCompanyService.get(stCompanyId);
		req.setAttribute(InfopubCompany.INFOPUB_COMPANY, infopubCompany);
		return new ModelAndView("/infopub/company/info.jsp");
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_company" })
	@RequestMapping("/infopub/infopubCompany/list.do")
	public void listCompany(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = infopubCompanyService.companyList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = {
			"ifillform_device_company_remove",
			"ifillform_device_company_bremove" })
	@RequestMapping("/infopub/infopubCompany/remove.do")
	public void removeCompany(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.success().setMsg("??????????????????");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			infopubCompanyService.removeList(httpReqRes);
			result.success().setMsg("??????????????????");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_company_save" })
	@RequestMapping("/infopub/infopubCompany/save.do")
	public void saveCompany(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("???????????????????????????", "??????",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			InfopubCompany infopubCompany = infopubCompanyService
					.saveOrUpdate(wrapper);
			if (infopubCompany != null)
				result = ExtAjaxReturnMessage.success("???????????????????????????", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@RequestMapping("/infopub/deviceinfo/addressdeviceInfo.do")
	public ModelAndView addressdeviceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String areaName = httpReqRes.getParameter("areaName");
		String typeName = httpReqRes.getParameter("typeName");
		areaName = Decode.decode(areaName, "utf-8");
		typeName = Decode.decode(typeName, "utf-8");
		InfopubDeviceInfo infopubDeviceInfo = new InfopubDeviceInfo();
		// System.out.println(areaName+typeName+"????????????");
		infopubDeviceInfo.setStAddressId(areaName);
		infopubDeviceInfo.setStTypeId(typeName);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/infopub/deviceinfo/singleStreetDevice.jsp");
	}

	/**
	 * ??????????????????????????????
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_home" })
	@RequestMapping("/infopub/home/leading.do")
	public void leading(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = infoPubService.leading(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		// EasyUIHelper.writeResponse(res, obj.toString());

	}

	/**
	 * ??????debug???????????????????????????
	 */
	/**
	 * ????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/uploadDebug.do")
	public void uploadDebug(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		FileItem fileItem = wrapper.getFileItem("file");
		String fileName = null;
		String fileType = null;
		byte[] file = null;
		int len = 0;
		if (fileItem != null) {
			fileName = fileItem.getName();
			fileType = fileItem.getContentType();
			file = ((FileItem) fileItem).get();
			len = file.length;
		}
		String stAttachId = "6225ec32-c595-4698-a7b4-174ac1dd5b91";
		// String stAttachId = wrapper.getParameter("stApplyUserId");
		String applytitle = wrapper.getParameter("applytitle");// ????????????
		String applycontent = wrapper.getParameter("applycontent");// ????????????
		JSONObject json = new JSONObject();
		InfopubAttachment infopubAttachment = infoPubService.uploadDebug(
				stAttachId, fileName, fileType, file, len, applytitle,
				applycontent);
		// SelmAttach selmAttach =
		// selmAccessApplyService.uploadStuff(stApplyUserId,fileName,fileType,file,len,applytitle,applycontent);
		if (infopubAttachment != null) {
			json.put("SUCCESS", true);
			// json.put("ATTACHID", selmAttach.getStAttachid());
		} else {
			json.put("SUCCESS", false);
			json.put("ATTACHID", "");
		}
		/* EasyUIHelper.writeResponse(res, json.toString()); */
	}

	/**
	 * ??????debug???
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/downloadDebug.do")
	public void download(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		infoPubService.downLoad(httpReqRes);
		return;
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/ItemDeviceList.do")
	public void ItemDeviceList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemDeviceList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????mac????????????key???
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/getCertKey.do")
	public void getCertKey(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Cache-Control", "no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.getCertKey(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@RequestMapping("/infopub/deviceinfo/checkItemLinkDevice.do")
	public void checkItemLinkDevice(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemLinkDevice(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/wGetAllDevice.do")
	public void wGetDeviceByTime(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.wGetDeviceByTime(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ????????????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/infopub/deviceinfo/wGetItemByDevice.do"
	 * ,method=RequestMethod.POST) public void
	 * wGetItemByDevice(HttpServletRequest req, HttpServletResponse res) throws
	 * IOException, JSONException{ HttpReqRes httpReqRes = new HttpReqRes(req,
	 * res); JSONObject obj = null; try { obj =
	 * infoPubService.wGetItemByDevice(httpReqRes); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * EasyUIHelper.writeResponse(res, obj.toString()); }
	 */

	/**
	 * ???????????????????????????
	 */
	@RequestMapping("/infopub/deviceinfo/isonlineofmap.do")
	public void isonlineofmap(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = infoPubService.isonlineofmap(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}

	/**
	 * ?????????????????????????????????????????????
	 */
	@RequestMapping("/infopub/deviceinfo/batchisonlineofmap.do")
	public void batchisonlineofmap(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = infoPubService.batchisonlineofmap(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}

	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/mGetDevInfo.do")
	public void mGetDevInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.mGetDevInfo(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/wGetDeviceByTime.do")
	public void mGetDeviceByTime(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.mGetDeviceByTime(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ??????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/wGetItemByTime.do")
	public void mGetItemByTime(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.mGetItemByTime(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

	/**
	 * ?????????????????????????????????????????????????????????
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value = "/infopub/deviceinfo/wGetItemByDevice.do", method = RequestMethod.POST)
	public void mGetItemByDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.mGetItemByDevice(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}

}
