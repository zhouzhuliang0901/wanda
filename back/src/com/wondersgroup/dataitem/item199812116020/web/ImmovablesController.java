package com.wondersgroup.dataitem.item199812116020.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selfapi.util.DzUtils;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

/**
 * 不动产登记信息自助查询、打印
 * @author wanda
 *
 */
@Controller
public class ImmovablesController {
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	/**
	 * 不动产查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/immovables/queryImmovables.do")
	public void queryImmovables(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String certCode = req.getParameter("certCode");// 201325747090
		String machineId = req.getParameter("machineId");
		
		String machineMAC = "";
		String machinePlace = "";
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}		
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		if(deviceInfo != null){
			InfopubArea infopubArea = infopubAreaDao.get(deviceInfo.getStAreaId());
			if(infopubArea != null){
				machinePlace = "SH00"+infopubArea.getStAreaCode();
				machineMAC = deviceInfo.getStDeviceMac();
			}
		}
		
		String itemName = "不动产查询打印";
		String itemCode = "199812116020";
		String businessCode = req.getParameter("businessCode");
		if(StringUtils.isEmpty(businessCode)){
			businessCode = "";
		}
		
		String sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
		if(StringUtils.isEmpty(sessionId)){
			Log.debug("第一次登陆异常！");
			sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
		}
		System.out.println("sessionId:" + sessionId);
		
		String str = DzUtils.getCertListByBDC(sessionId, certCode, machinePlace, 
				machineMAC, itemCode, businessCode, itemName);
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 不动产核验
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/immovables/checkImmovables.do")
	public void checkImmovables(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String districtId = req.getParameter("districtId");
		String houseId = req.getParameter("houseId"); // 房屋编号
		String transactionId = req.getParameter("transactionId");// 收件编号
		String ownerCardNo = req.getParameter("ownerCardNo");
        String machineId = req.getParameter("machineId");
		
		String machineMAC = "";
		String machinePlace = "";
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		if(deviceInfo != null){
			InfopubArea infopubArea = infopubAreaDao.get(deviceInfo.getStAreaId());
			if(infopubArea != null){
				machinePlace = "SH00"+infopubArea.getStAreaCode();
				machineMAC = deviceInfo.getStDeviceMac();
			}
		}
		
		String itemName = "不动产查询打印";
		String itemCode = "199812116020";
		String businessCode = req.getParameter("businessCode");
		if(StringUtils.isEmpty(businessCode)){
			businessCode = "";
		}
		
		String sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
		if(StringUtils.isEmpty(sessionId)){
			Log.debug("第一次登陆异常！");
			sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
		}
		System.out.println("sessionId:" + sessionId);
		
		String str = DzUtils.confirmCertByBDC(sessionId, districtId, houseId, transactionId, ownerCardNo, 
				machinePlace, machineMAC, itemCode, businessCode, itemName);
		
		JSONObject json = new JSONObject();
		try{
			 json = JSONObject.fromObject(str);
			 String errorCode = json.optString("errorCode");
			 if("0".equals(errorCode) && json.optBoolean("result")){
				 String certUuid = json.optString("certUuid");
					String deriveUuid = DzUtils.deriveCert(sessionId, certUuid,
							"用于不动产核验", "", "",machinePlace,machineMAC,itemName,itemCode,businessCode);
					Log.debug("deriveUuid:" + deriveUuid);
				 // 证照缩略图（GIF文件，文件流）
				 json.put("pictureUrlForBytes","/selfapi/dzzz/showXhPicForBytes.do?certUuid="
									+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
									+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
				 // 派生证照（PDF文件，文件流）
				 json.put("derivePictureUrlForBytes","/selfapi/dzzz/showStuffPicForBytes.do?deriveUuid="
									+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
									+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
			 }
		} catch (Exception e) {
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	private static String dealcCertUuid(String certUuid) {
		certUuid = certUuid.replaceAll("\\+", "-");
		certUuid = certUuid.replaceAll("=", ",");
		return certUuid;
	}
}
