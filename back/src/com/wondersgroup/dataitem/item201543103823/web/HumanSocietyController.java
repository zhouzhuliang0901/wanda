package com.wondersgroup.dataitem.item201543103823.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
//import com.wondersgroup.infopub.bean.InfopubArea;
//import com.wondersgroup.infopub.dao.InfopubAreaDao;
//import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
//import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selfapi.util.DzUtils;

@Controller
public class HumanSocietyController {
	
//	@Autowired
//	private InfopubDeviceInfoDao infopubDeviceInfoDao;
//	
//	@Autowired
//	private InfopubAreaDao infopubAreaDao;
	
	/**
	 * 上海人社自助查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/humanSociety/humanSocietyQuery.do")
	public void humanSocietyQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		String date = sdf.format(new Date());
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "03437a26-3c61-4320-abc3-91fd98c0df3d";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1896ca88-d36b-460a-bc7e-6badd765f244";
		}
		
		String type = req.getParameter("type");// jfqk：缴费情况查询        ydzy：异地转移查询
		String zjhm = req.getParameter("zjhm");// 350722199011260084
		String xm = req.getParameter("xm");
		String itemCode = req.getParameter("itemCode");// 异地转移：312014501000     参保缴费：310195323433
		String dyrq = req.getParameter("dyrq");// 只有 qyd和dzdzd两个事项需要
		String reportId = req.getParameter("reportId");// 只有ywshbjqk一个事项需要
		String accessToken = req.getParameter("accessToken");
		String machineId = req.getParameter("machineId");
		if(StringUtils.isNotEmpty(xm)){
			xm = URLDecoder.decode(xm, "utf-8");
		}
		
		String tybm = HttpUtil.getApplyNo(itemCode);
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject json = new JSONObject();
		if("qyd".equals(type) || "dzdzd".equals(type)){
			json.put("data", "\"{\"zjhm\":\""+zjhm+"\",\"xm\": \""+xm+"\",\"tybm\": \""+tybm+"\",\"dyrq\":\""+dyrq+"\"}\"");
		} else if("ywshbjqk".equals(type)){
			json.put("data", "\"{\"zjhm\":\""+zjhm+"\",\"xm\": \""+xm+"\",\"tybm\": \""+tybm+"\",\"reportId\":\""+reportId+"\"}\"");
		} else {
			json.put("data", "\"{\"zjhm\":\""+zjhm+"\",\"xm\": \""+xm+"\",\"tybm\": \""+tybm+"\"}\"");
		}
		json.put("type", type);
		json.put("accessToken", accessToken);
		Log.debug(date+"---网点："+machineId+"---人社自助查询参数："+json.toString());
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		Log.debug(date+"---网点："+machineId+"---人社自助查询返回结果："+result);
		JSONObject jsonResult = new JSONObject();
		try{
			jsonResult = JSONObject.fromObject(result);
		} catch (Exception e) {
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
		
//		JSONObject jsonResult = new JSONObject();
//		try{
//			jsonResult = JSONObject.fromObject(result);
//			jsonResult.put("tybm", tybm);
//			
//			jsonResult = JSONObject.fromObject(result);
//			JSONObject obj = JSONObject.fromObject(jsonResult.optString("data"));
//			String certUuid = obj.optString("certUuid");
//			System.out.println("人社制证的certUuid："+certUuid);
//			if(StringUtils.isEmpty(machineId)){
//				machineId = "";
//			}
//			InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
//			if(deviceInfo == null){
//				deviceInfo = DzUtils.getDefaultMachine();
//			}
//			if(deviceInfo != null){
//				InfopubArea infopubArea = infopubAreaDao.get(deviceInfo.getStAreaId());
//				if(infopubArea != null){
//					String machinePlace = "SH00"+infopubArea.getStAreaCode();
//					String machineMAC = deviceInfo.getStDeviceMac();
//					
//					String itemName = req.getParameter("itemName");
//					if(StringUtils.isNotEmpty(itemName)){
//						itemName = URLDecoder.decode(itemName, "utf-8");
//					}
//					String businessCode = req.getParameter("businessCode");
//					if(StringUtils.isEmpty(itemName)){
//						itemName = "证照自助查询或打印";
//					}
//					if(StringUtils.isEmpty(businessCode)){
//						businessCode = "21150202143253421495";
//					}
//					jsonResult = getCertUrl(certUuid, machineId, itemName, itemCode, tybm, machinePlace, machineMAC);
//				} else {
//					jsonResult.put("SUCCESS", "FALSE");
//					jsonResult.put("CODE", 0);
//					jsonResult.put("MSG", "设备信息有误！");
//				}
//			} else {
//				jsonResult.put("SUCCESS", "FALSE");
//				jsonResult.put("CODE", 0);
//				jsonResult.put("MSG", "设备不存在！");
//			}
//			
//		} catch (Exception e) {
//		}
	}
	
	@SuppressWarnings("unused")
	private JSONObject getCertUrl(String certUuid, String machineId,
			String itemName, String itemCode, String businessCode, 
			String machinePlace, String machineMAC){
		JSONObject jsonResult = new JSONObject();
		String sessionId = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			sessionId = DzUtils.getSessionIdTest(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
		}
		if(StringUtils.isNotEmpty(sessionId)){
			try{
				if(StringUtils.isNotEmpty(certUuid)){
					String deriveUuid = "";
					Thread.sleep(3000);
					if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
						deriveUuid = DzUtils.deriveCertTest(sessionId, certUuid,
								"自助终端打印", "", "",itemCode,businessCode);
					} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
						deriveUuid = DzUtils.deriveCert(sessionId, certUuid,
								"自助终端打印", "", "", machinePlace, machineMAC,itemName,itemCode,businessCode);
					}
					jsonResult.put("SUCCESS", "TRUE");
					jsonResult.put("CODE", 1);
					jsonResult.put("MSG", "");
					jsonResult.put("PNGURL", "/selfapi/dzzz/humanSocietyPNGForBytes.do?certUuid="
							+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
							+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
					jsonResult.put("PDFURL", "/selfapi/dzzz/humanSocietyPDFForBytes.do?certUuid="
							+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
							+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
					jsonResult.put("base64Url", "/selfapi/dzzz/showStuffPicForBase64.do?deriveUuid="
							+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
							+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
				} else {
					jsonResult.put("SUCCESS", "FALSE");
					jsonResult.put("CODE", 0);
					jsonResult.put("MSG", "此办件暂未生成证照！");
				}
			} catch (Exception e) {
				Log.debug(e);
			}
		} else {
			jsonResult.put("SUCCESS", "FALSE");
			jsonResult.put("CODE", 0);
			jsonResult.put("MSG", "电子证照库登陆失败！");
			jsonResult.put("URL", "");
		}
		return jsonResult;
	}
	
	public static String dealcCertUuid(String certUuid) {
		certUuid = certUuid.replaceAll("\\+", "-");
		certUuid = certUuid.replaceAll("=", ",");
		return certUuid;
	}
}
