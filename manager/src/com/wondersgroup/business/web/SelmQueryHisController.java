package com.wondersgroup.business.web;

import java.io.IOException;
import java.io.OutputStream;

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
import tw.ecosystem.reindeer.web.Result;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.service.SelmQueryHisService;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.service.InfoPubService;
import com.wondersgroup.infopub.service.InfopubAddressService;

import coral.base.util.CloseUtils;
import coral.base.util.RequestWrapper;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;

/**
 * 工作台模块使用历史记录 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmQueryHisController {
	
	@Autowired
	private SelmQueryHisService selmQueryHisService;
	
	@Autowired
	private InfoPubService infoPubService;
	
	@Autowired
	private InfopubAddressService infopubAddressService;

	@RequestMapping("/business/selmQueryHis/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmQueryHis.ST_QUERY_HIS_ID
		String stQueryHisId = wrapper.getParameter(SelmQueryHis.ST_QUERY_HIS_ID);
		if (!StringUtils.trimToEmpty(stQueryHisId).isEmpty()) {
			SelmQueryHis selmQueryHis = selmQueryHisService.getSelmQueryHis(stQueryHisId);
			req.setAttribute(SelmQueryHis.SELM_QUERY_HIS, selmQueryHis);
		}
		return new ModelAndView("/business/edit.jsp");
	}

	/**
	 * 查看历史办件信息
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	
	/*@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_affairs_query_info" ,"warning_unInput_Device_Mac_info"})*/
	@RequestMapping("/business/selmQueryHis/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stQueryHisId = httpReqRes.getParameter("ST_QUERY_HIS_ID");
		SelmQueryHis selmQueryHis = selmQueryHisService.getSelmQueryHis(stQueryHisId);
		SelmAttach selmAttach = null;
		if (selmQueryHis != null) {
			selmAttach = selmQueryHisService.getSelmAttach(selmQueryHis);
		}
		String stCode = selmQueryHis.getStMachineId();
		InfopubDeviceInfo code = infoPubService.getMac(stCode);
		if(code ==null){
			selmQueryHis.setStExt3("");
			selmQueryHis.setStExt4("");
		}else{
			InfopubAddress infopubAddress = infopubAddressService.get(code.getStAddressId());
			selmQueryHis.setStExt3(infopubAddress.getStDistrict());
			selmQueryHis.setStExt4(infopubAddress.getStStreet());
		}
		String mobile =selmQueryHis.getStMobile();
		String idCard =selmQueryHis.getStIdentityNo();
		String phoneNumber =null;
		if (mobile != null && !StringUtils.trim(mobile).isEmpty()) {
			phoneNumber = mobile.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
		}
		String idNumber=null;
		if (idCard != null && !StringUtils.trim(idCard).isEmpty()) {
			if (idCard.length() == 15){
	             idNumber = idCard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1*************$2");
	         }
	         if (idCard.length() == 18){
	             idNumber = idCard.replaceAll("(\\w{1})\\w*(\\w{1})", "$1****************$2");
	         }
		}
         selmQueryHis.setStIdentityNo(idNumber);
         selmQueryHis.setStMobile(phoneNumber);
		req.setAttribute(SelmQueryHis.SELM_QUERY_HIS, selmQueryHis);
		req.setAttribute(SelmAttach.SELM_ATTACH, selmAttach);
		return new ModelAndView("/business/info.jsp");
	}
	
	/**
	 * 跳转展示图片页面
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/business/selmQueryHis/file.do")
	public ModelAndView filePage(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stAttachId = httpReqRes.getParameter(SelmAttach.ST_ATTACH_ID);
		SelmAttach selmAttach = selmQueryHisService.getSelmAttach(stAttachId);
		req.setAttribute(SelmAttach.SELM_ATTACH, selmAttach);
		return new ModelAndView("/business/file.jsp");
	}

	/**
	 * 打开文件
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/business/selmQueryHis/openAttach.do")
	public void open(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			String stAttachId = httpReqRes.getParameter(SelmAttach.ST_ATTACH_ID);
			SelmAttach selmAttach = selmQueryHisService.getSelmAttach(stAttachId);
			if (selmAttach != null) {
				result.success().setData(selmAttach.getStAttachId());
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	/**
	 * 办件信息列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_affairs_query","ifillform_device_ma","ifillform_device_info"})*/
	@RequestMapping("/business/selmQueryHis/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmQueryHisService.queryHisList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 办件信息列表查询导出
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/business/selmQueryHis/listExcel.do")
	public void listExcel(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmQueryHisService.queryHisListExcel(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除办件历史信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "warning-selm_device_no_item_remove" })
	@RequestMapping("/business/selmQueryHis/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Result result = Result.getResult();
		result.setMsg("办件信息删除失败");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			selmQueryHisService.remove(httpReqRes);
			result.success().setMsg("办件信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}

	@RequestMapping("/business/selmQueryHis/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("工作台模块使用历史记录保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmQueryHis selmQueryHis = selmQueryHisService.saveOrUpdate(wrapper);
			if (selmQueryHis != null)
				result = ExtAjaxReturnMessage.success("工作台模块使用历史记录保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	
	/**
	 * 展示图片
	 */
	@RequestMapping("/business/selmQueryHis/common/getImage.do")
	public void getImage(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 获取图片ID
		String picId = req.getParameter("picId");
		Log.debug("图片ID:" + picId);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, picId));
		res.setContentType("image/jpeg");
		OutputStream out = res.getOutputStream();
		try {
			BlobHelper.getBlobToStream("SELM_ATTACH", "BL_CONTENT",
					conds.toString(), conds.getObjectArray(), out);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug("图片IDD:" + picId + ",获取用户图片错误!");
		} finally {
			CloseUtils.close(out);
		}
	}
	
	
	/**
	 * 办件信息统计
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@CheckPermissions(roles = { "admin" }, permissions = { "ifillform_device_info_info",
			"ifillform_device_info_deviceinfo","ifillform_affairs_query"})*/
	@RequestMapping("/business/selmQueryHis/statistics.do")
	public void statistics(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmQueryHisService.statistics(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	/**
	 * 办件信息统计top20
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_home" })
	@RequestMapping("/business/selmQueryHis/selmQuertTop.do")
	public void selmQuertTop(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmQueryHisService.selmQuertTop(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		//EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 各区域30天办件量
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "infopub_home" })
	@RequestMapping("/business/selmQueryHis/areaQueryHis.do")
	public void areaQueryHis(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = new JSONObject();
		try {
			obj = selmQueryHisService.areaQueryHis(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
		//EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 查看历史办件信息
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@CheckPermissions(roles = { "admin" }, permissions = { "sys_query_list_byTime" })
	@RequestMapping("/business/selmQueryHis/statisticsInfo.do")
	public ModelAndView statisticsInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		String stItemName = httpReqRes.getParameter(SelmQueryHis.ST_ITEM_NAME);
		String stDate = httpReqRes.getParameter("stDate");
		String edDate = httpReqRes.getParameter("edDate");
		stItemName = Decode.decode(stItemName, "utf-8");
		if (!StringUtils.trimToEmpty(stItemName).isEmpty()) {
			SelmQueryHis selmQueryHis = new SelmQueryHis();
			selmQueryHis.setStItemName(stItemName);
			selmQueryHis.setStExt1(stDate);
			selmQueryHis.setStExt2(edDate);
			req.setAttribute(SelmQueryHis.SELM_QUERY_HIS, selmQueryHis);
		}
		
		return new ModelAndView("/statistics/business/info.jsp");
	}
	
	/**
	 * 办件信息统计(按天）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/business/selmQueryHis/statisticsDay.do")
	public void statisticsDay(HttpServletRequest req, HttpServletResponse res)
				throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = selmQueryHisService.statisticsDay(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	@RequestMapping("/business/selmQueryHis/selmQueryDeviceinfo.do")
	public ModelAndView selmQueryDeviceinfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmQueryHis.ST_QUERY_HIS_ID
		String stMachineId = wrapper.getParameter(SelmQueryHis.ST_MACHINE_ID);
		String unInputDeviceMac = wrapper.getParameter("unInputDeviceMac");
		stMachineId = Decode.decode(stMachineId, "utf-8");
		if (!StringUtils.trimToEmpty(stMachineId).isEmpty()) {
			SelmQueryHis selmQueryHis = new SelmQueryHis();
			selmQueryHis.setStMachineId(stMachineId);
			selmQueryHis.setStExt1(unInputDeviceMac);
			req.setAttribute(SelmQueryHis.SELM_QUERY_HIS, selmQueryHis);
		}
		return new ModelAndView("/business/list.jsp");
	}
	
	/**
	 * 设备未绑定事项跳转
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/business/selmQueryHis/deviceNoItemList.do")
	public ModelAndView deviceNoItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmQueryHis.ST_QUERY_HIS_ID
		String stMachineId = wrapper.getParameter(SelmQueryHis.ST_MACHINE_ID);
		if (!StringUtils.trimToEmpty(stMachineId).isEmpty()) {
			SelmQueryHis selmQueryHis = new SelmQueryHis();
			selmQueryHis.setStMachineId(stMachineId);
			req.setAttribute(SelmQueryHis.SELM_QUERY_HIS, selmQueryHis);
		}
		return new ModelAndView("/business/deviceNoItemlist.jsp");
	}
	
	 /**
	 * 读取设备绑定没有的事项
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	
	@RequestMapping("/business/selmQueryHis/sqhDeviceItem.do")
	public void sqhDeviceItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = selmQueryHisService.sqhDeviceItem(httpReqRes);
			 req.setAttribute("obj", obj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	/**
	 * 未录入系统设备含有办件量的MAC
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	
	@CheckPermissions(roles = { "admin" }, permissions = { "warning_unInput_Device_Mac" })
	@RequestMapping("/business/selmQueryHis/unInputDeviceMac.do")
	public void unInputDeviceMac(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = selmQueryHisService.unInputDeviceMac(httpReqRes);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	/**
	 * 用于更新办件信息
	 */
	/*@RequestMapping("/business/selmQueryHis/update60.do")
	public void update60(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		 JSONObject obj = null;
		 try {
			 obj = selmQueryHisService.update60(httpReqRes);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 EasyUIHelper.writeResponse(res, obj.toString());
	}*/

}
