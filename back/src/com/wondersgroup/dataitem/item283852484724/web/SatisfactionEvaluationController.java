package com.wondersgroup.dataitem.item283852484724.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.CharsetUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.AESUtil;
import com.wondersgroup.common.utils.HexUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item283852484724.bean.Standard;
import com.wondersgroup.dataitem.item283852484724.bean.Street;
import com.wondersgroup.dataitem.item283852484724.service.SatisfactionEvaluationService;
import com.wondersgroup.dataitem.item283852484724.utils.RSAEncryptUtil;
import com.wondersgroup.dataitem.item283852484724.utils.StandardUitl;

/**
 * 好差评
 */
@Controller
public class SatisfactionEvaluationController {

	@Autowired
	private SatisfactionEvaluationService satisfactionEvaluationService;

	/**
	 * 自助终端使用满意度评价
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/satisfactionEvaluation/saveSatisfaction.do")
	public void saveSatisfaction(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 评价设备MAC
		String machineMAC = req.getParameter("machineMAC");
		// 设备满意度
		String nmMachine = req.getParameter("nmMachine");
		// 外观满意度
		String nmAppearance = req.getParameter("nmAppearance");
		// 操作方便度
		String nmOperation = req.getParameter("nmOperation");
		// 屏幕角度满意度
		String nmScreen = req.getParameter("nmScreen");
		// 其他补充
		String context = req.getParameter("context");
		// 评价人姓名
		String name = req.getParameter("name");
		// 评价人身份证号
		String idCard = req.getParameter("idCard");
		// 评价人联系电话
		String phone = req.getParameter("phone");
		String stEtx1 = req.getParameter("stEtx1");
		String stEtx2 = req.getParameter("stEtx2");
		String stEtx3 = req.getParameter("stEtx3");
		if (StringUtils.isNotEmpty(context)) {
			context = URLDecoder.decode(context, "utf-8");
		}
		if (StringUtils.isNotEmpty(name)) {
			name = URLDecoder.decode(name, "utf-8");
			byte[] bytName = AESUtil.encrypt(name,
					RdConfig.get("reindeer.servlet.aes.key"));
			name = HexUtil.bytes2Hex(bytName);
		}
		if (StringUtils.isNotEmpty(idCard)) {
			byte[] bytIdCard = AESUtil.encrypt(idCard,
					RdConfig.get("reindeer.servlet.aes.key"));
			idCard = HexUtil.bytes2Hex(bytIdCard);
		}
		if (StringUtils.isNotEmpty(phone)) {
			byte[] bytPhone = AESUtil.encrypt(phone,
					RdConfig.get("reindeer.servlet.aes.key"));
			phone = HexUtil.bytes2Hex(bytPhone);
		}
		JSONObject json = new JSONObject();
		int record = 0;
		try {
			record = satisfactionEvaluationService.addSatisfaction(machineMAC,
					nmMachine, nmAppearance, nmOperation, nmScreen, context,
					name, idCard, phone, stEtx1, stEtx2, stEtx3);
		} catch (Exception e) {
			json.put("success", false);
			json.put("msg", "内部异常，请联系管理员！");
		}
		if (record == 1) {
			json.put("success", true);
			json.put("msg", "评价信息保存成功！");
		} else {
			json.put("success", false);
			json.put("msg", "评价信息保存失败！");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}

	/**
	 * “好差评”动态指标获取
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/satisfactionEvaluation/getStandard.do")
	public void getStandard(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String standardStr = StandardUitl.getStandard();

		String filePath = SatisfactionEvaluationController.class
				.getResource("").getPath() + "/template/自助终端指标.xls";
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(0);
		params.setHeadRows(1);
		List<Standard> list = ExcelImportUtil.importExcel(new File(filePath),
				Standard.class, params);
		List<String> standardList = new ArrayList<String>();
		for (Standard standard : list) {
			String content = standard.getContent();
			standardList.add(content);
		}
		JSONArray standards = new JSONArray();
		if (StringUtils.isNotEmpty(standardStr)) {
			JSONObject jsonStandard = JSONObject.fromObject(standardStr);
			boolean success = jsonStandard.optBoolean("success");
			if (success) {
				JSONArray standardArray = jsonStandard.optJSONObject("data")
						.optJSONArray("standards");
				for (int i = 0; i < standardArray.size(); i++) {
					JSONObject standard = new JSONObject();
					JSONObject o = standardArray.optJSONObject(i);
					String value = o.optString("value");
					String name = o.optString("name");
					JSONArray options = o.optJSONArray("options");
					JSONArray sourceOptions = new JSONArray();
					for (int j = 0; j < options.size(); j++) {
						JSONObject sourceOption = new JSONObject();
						JSONObject option = options.optJSONObject(j);
						String souceCode = option.optString("value");
						String souceCName = option.optString("name");
						if (standardList.contains(souceCName)) {
							sourceOption.put("souceCode", souceCode);
							sourceOption.put("souceName", souceCName);
							sourceOptions.add(sourceOption);
						}
					}
					standard.put("value", value);
					standard.put("name", name);
					standard.put("sourceOptions", sourceOptions);
					standards.add(standard);
				}
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, standards.toString());
	}

	/**
	 * 自助服务终端“好差评”数据提交
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping("/selfapi/satisfactionEvaluation/saveTerminalEval.do")
	public void saveTerminalEval(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String machineMac = req.getParameter("machineId");
		String userName = req.getParameter("userName");
		String mobile = req.getParameter("mobile");
		String userType = req.getParameter("userType");
		String projectTarget = req.getParameter("projectTarget");
		String targetLicenseNo = req.getParameter("targetLicenseNo");
		String itemCode = req.getParameter("itemCode");
		String itemName = req.getParameter("itemName");
		String projectNo = req.getParameter("projectNo");
		String deptName = req.getParameter("deptName");
		String shDeptCode = req.getParameter("shDeptCode");
		String totalValue = req.getParameter("totalValue");
		String totalScoreCodes = req.getParameter("totalScoreCodes");
		String windowCode = req.getParameter("windowCode");
		JSONObject jsonMachine = satisfactionEvaluationService
				.getMachineInfoByMAC(machineMac);
		if(StringUtils.isNotEmpty(userName)){
			userName = URLDecoder.decode(userName, "utf-8");
		}
		if(StringUtils.isNotEmpty(projectTarget)){
			projectTarget = URLDecoder.decode(projectTarget, "utf-8");
		}
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "utf-8");
		}
		if(StringUtils.isNotEmpty(deptName)){
			deptName = URLDecoder.decode(deptName, "utf-8");
		}
		System.out.println("设备信息："+jsonMachine.toString());
		
		String filePath = SatisfactionEvaluationController.class
				.getResource("").getPath() + "/template/街道区划代码.xlsx";
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(0);
		params.setHeadRows(1);
		List<Street> list = ExcelImportUtil.importExcel(new File(filePath),
				Street.class, params);
		String street = jsonMachine.optString("street");
		String streetCode = "";
		for(Street o : list){
			if(StringUtils.isNotEmpty(o.getStreetShortName()) 
					&& o.getStreetShortName().contains(street)){
				streetCode = o.getStreetCode();
				break;
			}
		}

		String appName = "58b4d5f0-6a10-11ec-bebb-a7e38ac68bb1";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("windowType", 4);
		jsonObject.put("district", jsonMachine.optString("district"));
		jsonObject.put("streetCode", streetCode);
		jsonObject.put("serviceHall", jsonMachine.optString("serviceHall"));
		jsonObject.put("serviceHallAddr",
				jsonMachine.optString("serviceHallAddr"));
		jsonObject.put("manageDeptCode", "SHMZSH");
		jsonObject.put("terminalNo", jsonMachine.optString("terminalNo"));
		jsonObject.put("terminalName", jsonMachine.optString("terminalName"));
		jsonObject.put("userName", userName);
		jsonObject.put("mobile", mobile);

		JSONArray array = new JSONArray();
		JSONObject projectDetail = new JSONObject();
		projectDetail.put("userType", Integer.valueOf(userType));
		projectDetail.put("projectTarget", projectTarget);
		projectDetail.put("targetLicenseNo", targetLicenseNo);
		projectDetail.put("itemCode", itemCode);
		projectDetail.put("itemName", itemName);
		projectDetail.put("serialNo", "");
		projectDetail.put("projectNo", projectNo);
		projectDetail.put("deptName", deptName);
		projectDetail.put("shDeptCode", shDeptCode);
		array.add(projectDetail);
		jsonObject.put("projectDetail", array);

		jsonObject.put("appraiseType", "A013");
		jsonObject.put("totalValue", Integer.valueOf(totalValue));
		jsonObject.put("totalScoreCodes", totalScoreCodes);
		jsonObject.put("version", 1);
		System.out.println("appraiseForm明文参数："+jsonObject.toString());

		// TODO
		String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKUPElHbSFEW2JvBRoqwtnI6ju2UYgxHo1fpG8xtAiZV7LbOK8YSDxNGI0+ZAyrLqOGGDfbDaDvebN29v4jziUHcyzIRRZu+aV6hXQi+oULyJPWKnFkA6DARo0i190R4pZ5aXJlzNHoGMT9wsHqYlVlaRrvJgF+EHGReAfR9cURQIDAQAB";

		PublicKey publicKey = RSAEncryptUtil.string2PublicKey(publicKeyStr);
		byte[] publicEncrypt = RSAEncryptUtil.publicEncrypt1(jsonObject
				.toString().getBytes("UTF-8"), publicKey);
		String appraiseForm = RSAEncryptUtil.byte2Base64(publicEncrypt);
		
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(CharsetUtils.get("UTF-8"));
        builder.addPart("windowCode", new StringBody(windowCode,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("appraiseForm", new StringBody(appraiseForm,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        HttpEntity reqEntity = builder.build();
        String result = HttpUtil.doPost(head, reqEntity);
		System.out.println("“好差评”数据提交返回：" + result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
