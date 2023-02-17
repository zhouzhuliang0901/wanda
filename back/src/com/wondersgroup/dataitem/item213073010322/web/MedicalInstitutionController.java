package com.wondersgroup.dataitem.item213073010322.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item213073010322.bean.DrugClassificationDictionary;

@Controller
public class MedicalInstitutionController {
	
	/**
	 * 医保查询服务-医疗机构查询-医保定点医院查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/queryDesignatedHospital.do")
	public void queryDesignatedHospital(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// 所属区
		String area = req.getParameter("area");
		// 医院名称（输入，非必填）
		String hospitalName = req.getParameter("hospitalName");
		// 医院等级(一级医院、二级医院、三级医院)
		String hospitalClass = req.getParameter("hospitalClass");
		if(StringUtils.isNoneEmpty(hospitalName)){
			hospitalName = URLDecoder.decode(hospitalName, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "676e2716-76e0-4ff0-b474-81d322d9c7d5";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "676e2716-76e0-4ff0-b474-81d322d9c7d5";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("AREA_ID", area);
		obj.put("JGMC", hospitalName);
		obj.put("HOS_CLASS", hospitalClass);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("医保定点医院查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保查询服务-网站密码修改
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/updatePassword.do")
	public void updatePassword(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String ZHH = req.getParameter("ZHH");
		String OLDMM = req.getParameter("oldPassword");
		String MM = req.getParameter("password");
		// 委托人信息
		String WTRXM = req.getParameter("consignerName");
		String WTRSFZH = req.getParameter("consignerID");
		String SJHM = req.getParameter("consignerMobile");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b6d36036-28c2-47ec-8994-ccdb1193e65b";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b6d36036-28c2-47ec-8994-ccdb1193e65b";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("ZHH", ZHH);
		obj.put("OLDMM", OLDMM);
		obj.put("MM", MM);
		obj.put("WTRXM", WTRXM);
		obj.put("WTRSFZH", WTRSFZH);
		obj.put("SJHM", SJHM);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("网站密码修改结果："+result);
		JSONObject rObj = new JSONObject();
		if(StringUtils.isEmpty(result)){
			rObj.put("success", true);
			rObj.put("data", "");
		} else if("error".equals(result)){
			rObj.put("success", false);
			rObj.put("data", "");
		} else {
			rObj.put("success", false);
			rObj.put("data", JSONArray.fromObject(result));
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, rObj.toString());
	}
	
	/**
	 * 医保查询服务-医保范围药品查询-医保范围药品分类字典
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/getDrugClassificationDictionaries.do")
	public void getDrugClassificationDictionaries(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		// chineseMedicine-中药；westernMedicine-西药
		String drugType = req.getParameter("drugType");
		String drugClassification = req.getParameter("drugClassification");
		Log.debug("药品类型："+drugType+"===>"+"分类级别："+drugClassification);
		
		int sheet = 0;
		if("westernMedicine".equals(drugType)){
			switch (Integer.valueOf(drugClassification)){
			case 1:
				sheet = 0;
				break;
			case 2:
				sheet = 1;
				break;
			case 3:
				sheet = 2;
				break;
			default:
				sheet = 0;
				break;
			}
		} else if("chineseMedicine".equals(drugType)){
			switch (Integer.valueOf(drugClassification)){
			case 1:
				sheet = 3;
				break;
			case 2:
				sheet = 4;
				break;
			case 3:
				sheet = 5;
				break;
			default:
				sheet = 0;
				break;
			}
		}
		
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(sheet);
		params.setHeadRows(1);
		List<DrugClassificationDictionary> dictionaryList = ExcelImportUtil.importExcel(
				new File(MedicalInstitutionController.class.getResource("").getPath()+"template/医保范围药品分类字典.xls"),
				DrugClassificationDictionary.class,
				params);
		
		JSONArray arr = new JSONArray();
		for(DrugClassificationDictionary dictionary : dictionaryList){
			JSONObject obj = new JSONObject();
			obj.put("key", dictionary.getKey());
			obj.put("value", dictionary.getValue());
			obj.put("parentkey", dictionary.getParentKey() == null? "" : dictionary.getParentKey());
			arr.add(obj);
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	/**
	 * 医保查询服务-医保范围药品查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/queryDrugInfo.do")
	public void queryDrugInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String drugType = req.getParameter("drugType");
		String FL1 = req.getParameter("FL1");
		String FL2 = req.getParameter("FL2");
		String FL3 = req.getParameter("FL3");
		String NAME_CN = req.getParameter("drugName");
		if(StringUtils.isNotEmpty(NAME_CN)){
			NAME_CN = URLDecoder.decode(NAME_CN, "utf-8");
		}
		
//		String drugType = "";
//		if(FL1.startsWith("X") && FL2.startsWith("X") && FL3.startsWith("X")){
//			drugType = "1";
//		} else if(FL1.startsWith("Z") && FL2.startsWith("Z") && FL3.startsWith("Z")){
//			drugType = "2";
//		} else {
//			drugType = "药品分类不匹配";
//			JSONObject obj = new JSONObject();
//			obj.put("success","false");
//			obj.put("msg",drugType);
//		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "34e28c23-b76f-4d95-bba1-4d92422c874d";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "34e28c23-b76f-4d95-bba1-4d92422c874d";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("ZXYBZ", drugType);
		obj.put("FL1", FL1);
		obj.put("FL2", FL2);
		obj.put("FL3", FL3);
		obj.put("NAME_CN", NAME_CN == null ? "" : NAME_CN);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("医保范围药品查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保查询服务-医疗费用分担模拟计算器（城保）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/calculationSocialInsuranceForEmployees.do")
	public void calculationSocialInsuranceForEmployees(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String JYLB = req.getParameter("JYLB");// 就诊类别
		String ZTZT = req.getParameter("ZTZT");// 职退情况
		String NLD_DIC = req.getParameter("NLD_DIC");// 对象类别
		String NLFL = req.getParameter("NLFL");// 年龄分类
		String CURACCOUNTAMT = req.getParameter("CURACCOUNTAMT");// 当年帐户余额
		String HISACCOUNTAMT = req.getParameter("HISACCOUNTAMT");// 历年帐户余额
		String MJZZFCASHTOTAL = req.getParameter("MJZZFCASHTOTAL");// 年度累计门诊自负段已付医疗费用数额
		String ZY_QFLJTOTAL = req.getParameter("ZY_QFLJTOTAL");// 住院起付段医疗费用累计
		String TCTOTALLJ = req.getParameter("TCTOTALLJ");// 年度起付线上封顶线下(住院/家床/大病)医疗费用数值累计
		String TOTALEXPENSE = req.getParameter("TOTALEXPENSE");// 本次医疗费用总额
		
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "5eeb7177-5f3c-432a-a5bd-1ff5e9fa54d6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "5eeb7177-5f3c-432a-a5bd-1ff5e9fa54d6";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("JYLB", JYLB);
		obj.put("ZTZT", ZTZT);
		obj.put("NLD_DIC", NLD_DIC);
		obj.put("NLFL", NLFL);
		obj.put("CURACCOUNTAMT", CURACCOUNTAMT);
		obj.put("HISACCOUNTAMT", HISACCOUNTAMT);
		obj.put("MJZZFCASHTOTAL", MJZZFCASHTOTAL);
		obj.put("ZY_QFLJTOTAL", ZY_QFLJTOTAL);
		obj.put("TCTOTALLJ", TCTOTALLJ);
		obj.put("TOTALEXPENSE", TOTALEXPENSE);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("医疗费用分担模拟计算器（城保）模拟结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保查询服务-医疗费用分担模拟计算器（居保）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/calculationSocialInsuranceForResidents.do")
	public void calculationSocialInsuranceForResidents(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String JYLB = req.getParameter("JYLB");
		String RYLB = req.getParameter("RYLB");
		String TOTALEXPENSE = req.getParameter("TOTALEXPENSE");
		String MJZZFDYFLJ = req.getParameter("MJZZFDYFLJ");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "fd0fc95a-f974-4a3a-b2b0-d3326a4b1e58";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "fd0fc95a-f974-4a3a-b2b0-d3326a4b1e58";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("JYLB", JYLB);
		obj.put("RYLB", RYLB);
		obj.put("TOTALEXPENSE", TOTALEXPENSE);
		obj.put("MJZZFDYFLJ", MJZZFDYFLJ);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("医疗费用分担模拟计算器（居保）模拟结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保定点药店查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/MedicalInstitution/queryDrugstoreInfo.do")
	public void queryDrugstoreInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String area = req.getParameter("area");
		String storeName = req.getParameter("storeName");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "76a650f7-15a7-4cf0-8a20-58968a6257cb";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "76a650f7-15a7-4cf0-8a20-58968a6257cb";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("AREA_ID", area);
		obj.put("JGMC", storeName);
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("医保定点药店查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
