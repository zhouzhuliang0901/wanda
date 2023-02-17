package com.wondersgroup.dataitem.item236012130732.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item236012130732.bean.RecordBookInfo;
import com.wondersgroup.dataitem.item236012130732.service.RecordBookService;

@Controller
public class MedicalRecordBookController {
	
	@Autowired
	private RecordBookService recordBookService;
	
	/**
	 * 参保人条件组合查询(医保)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/queryAccountInfo.do")
	public void queryAccountInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String identNo = req.getParameter("identNo");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "61c55dd6-f256-44d0-ad96-4ab0c227e33e";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "61c55dd6-f256-44d0-ad96-4ab0c227e33e";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("cxtj", "3");
		//json.put("ybkh", "");
		//json.put("sbkh", "");
		json.put("sfzh", identNo);
		json.put("ybkbz", "0");
		System.out.println("参保人条件组合查询(医保)入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		System.out.println("参保人条件组合查询(医保)结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 记录册补册校验
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/updateRecordBookCheck.do")
	public void updateRecordBookCheck(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String knsj = req.getParameter("knsj");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "78e006fa-b093-43b5-a3df-584e289a79c8";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "78e006fa-b093-43b5-a3df-584e289a79c8";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("sfzh", knsj);
		Log.debug("记录册补册校验入参："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		Log.debug("记录册补册校验结果："+result);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 记录册更换
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/updateRecordBook.do")
	public void updateRecordBook(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String zhh = req.getParameter("zhh");// 账户号  
		// 01：遗失；02：损坏；03：勘误；04：增发；05：缺页；06：涂改；07：用完；08：补增；
		String bcyy = req.getParameter("bcyy");// 补册原因  
		String wtrxm = req.getParameter("wtrxm");// 委托人姓名 
		String wtrsfzh = req.getParameter("wtrsfzh");// 委托人身份证号 
		String machineMac = req.getParameter("machineMac");
		if(StringUtils.isNotEmpty(wtrxm)){
			wtrxm = URLDecoder.decode(wtrxm, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3d817282-a1f7-4782-8239-ad6c49ec37c3";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3d817282-a1f7-4782-8239-ad6c49ec37c3";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		json.put("zhh", zhh);
		json.put("bcyy", bcyy);
		json.put("gbf", "0");
		json.put("wtrxm", wtrxm);
		json.put("wtrsfzh", wtrsfzh);
		Log.debug("记录册更换入参："+json.toString()+"设备MAC："+machineMac);
		
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		Log.debug("记录册更换结果："+result+"设备MAC："+machineMac);
		
		recordBookService.saveBook(result, machineMac);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 就医记录册补打信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/getBookingInfo.do")
	public void getBookingInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		String machineId = req.getParameter("machineId");
		
		List<String> macList = recordBookService.getAllMachineById(machineId);
		JSONArray arr = new JSONArray();
		List<RecordBookInfo> list = new ArrayList<RecordBookInfo>();
		for(String mac : macList){
			list = recordBookService.getBookInfoByIdCard(startTime,endTime,mac);
			for(RecordBookInfo book : list){
				if(StringUtils.isEmpty(book.getIdCard()) 
						|| StringUtils.isEmpty(book.getName()) 
						|| StringUtils.isEmpty(book.getRecordBookNo()) 
						|| StringUtils.isEmpty(book.getSex()) 
						|| StringUtils.isEmpty(book.getCardNo())){
					continue;
				} else {
					JSONObject jsonResult = new JSONObject();
					jsonResult.put("sfzh",book.getIdCard());
					jsonResult.put("xm",book.getName());
					jsonResult.put("jlch",book.getRecordBookNo());
					jsonResult.put("xb",book.getSex());
					jsonResult.put("kh",book.getCardNo());
					arr.add(jsonResult);
				}
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	/**
	 * 查询一段时间内某人打印记录册次数
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/medicalInsurance/getTodayInfoByIdCard.do")
	public void getTodayInfoByIdCard(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String identNo = req.getParameter("identNo");
		String range = req.getParameter("range");
		
		int count = recordBookService.getHistoryBookInfoByIdCard(identNo, range);
		AciJsonHelper.writeJsonPResponse(req, res, Integer.toString(count));
	}
}
