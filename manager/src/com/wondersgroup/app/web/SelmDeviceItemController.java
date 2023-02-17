package com.wondersgroup.app.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.CheckPermissions;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.ecosystem.reindeer.web.Result;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.service.SelmDeviceItemService;
import com.wondersgroup.app.service.SelmDeviceItemTypeService;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubOnoff;
import com.wondersgroup.infopub.service.InfoPubService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 设备关联事项 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmDeviceItemController {
	
	@Autowired
	private SelmDeviceItemService selmDeviceItemService;
	@Autowired
	private SelmDeviceItemTypeService selmDeviceItemTypeService;
	@Autowired
	private InfoPubService infoPubService;

	@RequestMapping("app/selmDeviceItem/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceItem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(SelmDeviceItem.ST_ITEM_ID);
		// SelmDeviceItem.ST_DEVICE_ID
		String stDeviceId = wrapper.getParameter(SelmDeviceItem.ST_DEVICE_ID);
		if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			SelmDeviceItem selmDeviceItem = selmDeviceItemService.get(stItemId, stDeviceId);
			req.setAttribute(SelmDeviceItem.SELM_DEVICE_ITEM, selmDeviceItem);
		}
		return new ModelAndView("/selmDeviceItem/edit.jsp");
	}

	@RequestMapping("app/selmDeviceItem/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// SelmDeviceItem.ST_ITEM_ID
		String stItemId = wrapper.getParameter(SelmDeviceItem.ST_ITEM_ID);
		// SelmDeviceItem.ST_DEVICE_ID
		String stDeviceId = wrapper.getParameter(SelmDeviceItem.ST_DEVICE_ID);
		SelmDeviceItem selmDeviceItem = selmDeviceItemService.get(stItemId, stDeviceId);
		req.setAttribute(SelmDeviceItem.SELM_DEVICE_ITEM, selmDeviceItem);
		return new ModelAndView("/selmDeviceItem/info.jsp");
	}

	@RequestMapping("app/selmDeviceItem/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<SelmDeviceItem> list = selmDeviceItemService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, SelmDeviceItem.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/*@RequestMapping("/app/selmDeviceItem/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("设备关联事项删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// SelmDeviceItem.ST_ITEM_ID
			String stItemId = wrapper.getParameter(SelmDeviceItem.ST_ITEM_ID);
			// SelmDeviceItem.ST_DEVICE_ID
			String stDeviceId = wrapper.getParameter(SelmDeviceItem.ST_DEVICE_ID);
			selmDeviceItemService.remove(stItemId, stDeviceId);
			result = ExtAjaxReturnMessage.success("设备关联事项删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}*/

	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO_REMOVEPOWER","ITEM_INFO_BREMOVEPOWER" ,
			"selm_device_item_removePower","selm_device_item_bremovePower"})
	@RequestMapping("/app/selmDeviceItem/remove.do")
	public void deviceGroupRemove(HttpServletRequest req,
			HttpServletResponse res) throws IOException, JSONException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		Result result = Result.getResult();
		result.setMsg("删除失败");
		try {
			selmDeviceItemService.remove(httpReqRes);
			result.setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	/*@RequestMapping("/app/selmDeviceItem/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("设备关联事项保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			SelmDeviceItem selmDeviceItem = selmDeviceItemService.saveOrUpdate(wrapper);
			if (selmDeviceItem != null)
				result = ExtAjaxReturnMessage.success("设备关联事项保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}*/
	
	@CheckPermissions(roles = { "admin" }, permissions = { "ITEM_INFO_ADDPOWER" ,"ITEM_INFO_ADDALLPOWER",
			"selm_device_item_addPower","selm_device_item_addAllPower"})
	@RequestMapping("/app/selmDeviceItem/save.do")
	public void psrouceSave(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		try {
			SelmDeviceItem selmDeviceItem = selmDeviceItemService.saveOrUpdate(httpReqRes);
			if (selmDeviceItem != null)
				result.setSuccess(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	
	/**
	 * 授权
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/selmDeviceitem/add.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService.getDeviceInfo(httpReqRes);
		req.setAttribute(InfopubDeviceInfo.ST_DEVICE_ID, infopubDeviceInfo);
		return new ModelAndView("/app/infopuboauth2item.jsp");
	}
	
	/**
	 * 按事项分组授权
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/app/selmDeviceitem/groupAdd.do")
	public ModelAndView groupAdd(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService.getDeviceInfo(httpReqRes);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO, infopubDeviceInfo);
		return new ModelAndView("/app/groupItem.jsp");
	}
	
	
	/**
	 * 按事项分组给设备授权
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/app/selmDeviceitem/saveDeviceItemType.do")
	public void savePowerByGroup(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		Result result = Result.getResult();
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		result.success().setMsg("添加失败");
		try {
			int i = selmDeviceItemTypeService.add(httpReqRes);
			if (i > 0){
				result.success().setMsg("添加成功");
				result.setSuccess(true);
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		httpReqRes.writeJsonP(result);
	}
	
	@CheckPermissions(roles = { "admin" }, permissions = { "selm_device_item_info" })
	@RequestMapping("/app/selmDeviceitem/deviceinfo.do")
	public ModelAndView deviceInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		InfopubDeviceInfo infopubDeviceInfo = infoPubService.getDeviceInfo(httpReqRes);
		InfopubOnoff infopubOnoff = infoPubService
				.getDeviceOnOffInfo(httpReqRes);
		req.setAttribute(InfopubOnoff.INFOPUB_ONOFF, infopubOnoff);
		req.setAttribute(InfopubDeviceInfo.INFOPUB_DEVICE_INFO,
				infopubDeviceInfo);
		return new ModelAndView("/app/deviceInfo.jsp");
	}

	

}
