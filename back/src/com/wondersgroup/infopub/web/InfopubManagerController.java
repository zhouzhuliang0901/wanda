package com.wondersgroup.infopub.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.SelmAssist;
import com.wondersgroup.infopub.service.InfopubManagerService;

import reindeer.base.utils.AciJsonHelper;

@Controller
public class InfopubManagerController {
	
	@Autowired
	private InfopubManagerService infopubManagerService;
	
	/**
	 * 验证改事项是否是适老事项，且当前操作管理员是否有权操作此设备
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/infopubManager/checkItemForElderly.do")
	public void checkItemForElderly(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String managerIdCard = req.getParameter("managerIdCard");
		String machineMac = req.getParameter("machineMac");
		String itemName = req.getParameter("itemName");
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		
		boolean isManager = true;
		boolean isItem = true;
		String managerID = "";
		if(StringUtils.isEmpty(managerIdCard) || StringUtils.isEmpty(machineMac)){
			isManager = false;
		} else {
			int countManager = infopubManagerService.checkManager(managerIdCard, machineMac);
			if(countManager <= 0){
				isManager = false;
			} else {
				List<SelmAssist> list = infopubManagerService.getManagetInfoByIdCard(managerIdCard);
				SelmAssist manager = list.get(0);
				managerID = manager.getStAssistId();
			}
		}
		int countItem = infopubManagerService.checkItem(itemName);
		if(countItem <= 0){
			isItem = false;
		}
		JSONObject json = new JSONObject();
		if(!isItem){
			json.put("success", false);
			json.put("msg", "非适老事项！");
		} else if(!isManager){
			json.put("success", false);
			json.put("msg", "非管理员操作或管理员无权限设备！");
		} else if(isManager && isItem){
			json.put("success", true);
			json.put("msg", "可操作的适老事项。");
		}
		json.put("managerID", managerID);
		json.put("isManager", isManager);
		json.put("isItem", isItem);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 获取设备配置的事项的部门
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/infopubManager/getItemOrgan.do")
	public void getItemOrgan(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String machineMac = req.getParameter("machineMac");
		String itemType = req.getParameter("itemType");
		InfopubDeviceInfo device = infopubManagerService
				.getDeviceByMac(machineMac);
		JSONObject json = new JSONObject();
		if (device != null) {
			JSONArray arr = infopubManagerService.getItemOrgan(device.getStDeviceId(),
					itemType);
			json.put("success", true);
			json.put("msg", "");
			json.put("data", arr);
		} else {
			json.put("success", false);
			json.put("msg", "设备不存在，请查看配置信息！");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 根据部门ID和设备MAC获取改设备某部门下的事项(主事项)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/infopubManager/getItemInfo.do")
	public void getItemInfo(HttpServletRequest req, HttpServletResponse res)throws IOException {
		String machineMac = req.getParameter("machineMac");
		String itemType = req.getParameter("itemType");
		String organId = req.getParameter("organId");
		InfopubDeviceInfo device = infopubManagerService
				.getDeviceByMac(machineMac);
		JSONObject json = new JSONObject();
		if (device != null) {
			List<SelmItem> list = infopubManagerService.getItemList(device.getStDeviceId(), itemType, organId);
			json.put("success", true);
			json.put("msg", "");
			json.put("data", list);
		} else {
			json.put("success", false);
			json.put("msg", "设备不存在，请查看配置信息！");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 根据事项ID获取子事项信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/infopubManager/getSubItemInfo.do")
	public void getSubItemInfo(HttpServletRequest req, HttpServletResponse res)throws IOException {
		String itemId = req.getParameter("itemId");
		JSONObject json = new JSONObject();
		if(StringUtils.isNotEmpty(itemId)){
			List<SelmItem> list = infopubManagerService.getSubItemInfo(itemId);
			json.put("success", true);
			json.put("msg", "");
			json.put("data", list);
		} else {
			json.put("success", false);
			json.put("msg", "事项ID不能为空");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
}
