package com.wondersgroup.certCabinet.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.certCabinet.bean.CabinetInfo;
import com.wondersgroup.certCabinet.bean.SelmDelivery;
import com.wondersgroup.certCabinet.service.CertCabinetService;
import com.wondersgroup.certCabinet.table.CertCabinet;
import com.wondersgroup.certCabinet.util.SendMesageUtil;

@Controller
public class CertCabinetController {
	
	@Autowired
	private CertCabinetService certCabinetService;
	
	/**
	 * 保存设备信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/addCabinetInfo.do")
	public void addCabinetInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String stCabinetNum = req.getParameter("stCabinetNum");
		String nmCabinet = req.getParameter("nmCabinet");
		String[] numArr = stCabinetNum.split(",");
		JSONObject json = new JSONObject();
		if(numArr.length != Integer.valueOf(nmCabinet)){
			String msg = "柜子数量与柜号列表不匹配！";
			Log.debug(msg);
			json.put("success", false);
			json.put("msg", msg);
		} else {
			int result = certCabinetService.addCabinetInfo(req);
			if(result != 1){
				json.put("success", false);
				json.put("msg", "设备信息保存失败!");
			} else {
				json.put("success", true);
				json.put("msg", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 初始化证照柜柜号
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/initCabinet.do")
	public void initCabinet(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		String cabinetNos = req.getParameter("stCabinetNos");
		String[] cabinetNoArr = cabinetNos.split(",");
		List<String> successList = new ArrayList<String>();
		List<String> failedList = new ArrayList<String>();
		List<String> existList = new ArrayList<String>();
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			for(String str : cabinetNoArr){
				int result = certCabinetService.addCabinets(machineId, str);
				if(result == 1){
					successList.add(str);
				} else if(result == 10){
					existList.add(str);
				} else {
					failedList.add(str);
				}
			}
			JSONObject obj = new JSONObject();
			obj.put("machineId", machineId);
			obj.put("successList", successList);
			obj.put("existList", existList);
			obj.put("failedList", failedList);
			json.put("success", true);
			json.put("msg", "");
			json.put("data", obj);
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("data", "");
		}

		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 查询空柜子列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/getEmptyCabinet.do")
	public void getEmptyCabinet(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			List<String> list = certCabinetService.queryEmptyCabinet(machineId);
			json.put("success", true);
			json.put("msg", "");
			json.put("data", JSONArray.fromObject(list).toString());
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 开柜-存
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/store.do")
	public void store(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			int resultCode = certCabinetService.store(req);
			if(resultCode == 0){
				json.put("success", true);
			} else {
				json.put("success", false);
			}
			json.put("msg", CertCabinet.StoreFlag.GetName(Integer.toString(resultCode)));
			json.put("code", resultCode);
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("code", CertCabinet.StoreFlag.MINUS_ONE.getValue());
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 开柜-取
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/take.do")
	public void take(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			JSONObject result = certCabinetService.take(req);
			int resultCode = Integer.valueOf(result.optString("resultCode"));
			String stCabinetNo = result.optString("stCabinetNo");
			if(resultCode == 0){
				json.put("success", true);
				json.put("stCabinetNo", stCabinetNo);
			} else {
				json.put("success", false);
				json.put("stCabinetNo", stCabinetNo);
			}
			json.put("msg", CertCabinet.TakeFlag.GetName(Integer.toString(resultCode)));
			json.put("code", resultCode);
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("code", CertCabinet.TakeFlag.MINUS_ONE.getValue());
			json.put("stCabinetNo", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 补材料列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/supplementStuffs.do")
	public void supplementStuffs(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		String stSenderId = req.getParameter("stSenderId");
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
			conds.add(new Condition("ST_SENDER_ID", Condition.OT_EQUAL, stSenderId));
			conds.add(new Condition("NM_CERT_TYPE", Condition.OT_EQUAL, 1));
			conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, 1));
			List<SelmDelivery> list = certCabinetService.queryCabinet(conds, null);
			JSONArray arr = new JSONArray();
			for(SelmDelivery info : list){
				JSONObject obj = new JSONObject();
				obj.put("stCabinetNo", info.getStCabinetNo());
				obj.put("stSenderId", info.getStSenderId());
				obj.put("stSenderName", info.getStSenderName());
				obj.put("stSenderPhone", info.getStSenderPhone());
				arr.add(obj);
			}
			json.put("success", true);
			json.put("msg", "");
			json.put("data", arr);
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 取材料列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/takeStuffs.do")
	public void takeStuffs(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
			conds.add(new Condition("NM_CERT_TYPE", Condition.OT_EQUAL, 1));
			conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, 1));
			List<SelmDelivery> list = certCabinetService.queryCabinet(conds, null);
			JSONArray arr = new JSONArray();
			for(SelmDelivery info : list){
				JSONObject obj = new JSONObject();
				obj.put("stCabinetNo", info.getStCabinetNo());
				obj.put("stSenderId", info.getStSenderId());
				obj.put("stSenderName", info.getStSenderName());
				obj.put("stSenderPhone", info.getStSenderPhone());
				arr.add(obj);
			}
			json.put("success", true);
			json.put("msg", "");
			json.put("data", arr);
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 查询所有柜子状态信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/allCabinetInfo.do")
	public void allCabinetInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("stMachineId");
		JSONObject json = new JSONObject();
		if(checkMachine(machineId)){
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
			List<SelmDelivery> list = certCabinetService.queryCabinet(conds, null);
			JSONArray arr = new JSONArray();
			for(SelmDelivery info : list){
				JSONObject obj = new JSONObject();
				obj.put("stCabinetNo", info.getStCabinetNo());
				obj.put("nmCertType", info.getNmCertType());
				obj.put("nmStatus", info.getNmStatus());
				arr.add(obj);
			}
			json.put("success", true);
			json.put("msg", "");
			json.put("data", arr);
		} else {
			json.put("success", false);
			json.put("msg", "Machine Not Exist!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 短信转发跳转接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/certCabinet/sendMesage.do")
	public void sendMesage(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String msgNumber = req.getParameter("msgNumber");
		String msgContent = req.getParameter("msgContent");
		String sendResult = SendMesageUtil.sendMesage(msgNumber, msgContent);
		AciJsonHelper.writeJsonPResponse(req, res, sendResult);
	}
	
	private boolean checkMachine(String machineId){
		CabinetInfo cabinetInfo = certCabinetService.getByMacId(machineId);
		if(cabinetInfo != null){
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
	}
}
