package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import com.wondersgroup.selfapi.util.QrCodeByElectronicCert;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

/**
 * 电子证照亮证
 * @author wanda
 *
 */
@Controller
public class SelfevidenceController {
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;

	private static String lzAddressInfo = RdConfig.get("reindeer.lzAddress") == null ? "上海市大数据中心"
			: RdConfig.get("reindeer.lzAddress");
	
	@RequestMapping("/selfapi/getQrCodeInfoByElectronicCert.do")
	public void getQrCodeInfoByElectronicCert(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String codeParam = req.getParameter("codeParam");
		String lzAddress = req.getParameter("lzAddress");
		String use = req.getParameter("using");
		if (StringUtils.isBlank(use)) {
			//use = "工作台亮证";
			use = RdConfig.get("reindeer.usingFor") == null ? "政务服务自助终端亮证" : RdConfig.get("reindeer.usingFor");
		}
		lzAddress = dealLzAddress(lzAddress);
		Log.debug("---codeParam---" + codeParam);
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}		
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject jsonResult = new JSONObject();
		if(deviceInfo != null){
			InfopubArea infopubArea = infopubAreaDao.get(deviceInfo.getStAreaId());
			if(infopubArea != null){
				String machinePlace = "SH00"+infopubArea.getStAreaCode();
				String machineMAC = deviceInfo.getStDeviceMac();
				
				String itemName = req.getParameter("itemName");
				if(StringUtils.isNotEmpty(itemName)){
					itemName = URLDecoder.decode(itemName, "utf-8");
				}
				String itemCode = req.getParameter("itemCode");
				String businessCode = req.getParameter("businessCode");
				if(StringUtils.isEmpty(itemName)){
					itemName = "证照自助查询或打印";
				}
				if(StringUtils.isEmpty(itemCode)){
					itemCode = "3577729031";
				}
				if(StringUtils.isEmpty(businessCode)){
					businessCode = "21150202143253421495";
				}
				
				// 电子证照库登陆获取sessionId
				String sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), 
						RdConfig.get("zzzd.cert.password"));
				
				String data = QrCodeByElectronicCert.getQrCodeInfoByElectronicCert(sessionId, lzAddress, use, codeParam,
						machinePlace,machineMAC,itemName,itemCode,businessCode);
				Log.debug("调用电子证照接口返回的亮证数据:" + data);
				net.sf.json.JSONObject js = new net.sf.json.JSONObject();

				JSONObject obj = new JSONObject();
				String result = "";
				if(data.indexOf("<!DOCTYPE") > -1 
						|| data.indexOf("<html>") > -1 
						|| StringUtils.isEmpty(data)){
				//if(data.startsWith("\r\n\r\n\r\n<html>") || StringUtils.isEmpty(data)){
					Log.debug("电子证照亮证访问失败");
					js.put("success", "false");
					obj.put("result", js);
					obj.put("url", "");
					obj.put("str", "");
				} else {
					net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(data);
					String certuuid = json.optString("certUuid");
//					String encodeCertCode = json.optString("encodeCertCode");
					String strResult = DzUtils.getCertOriginalData(sessionId,certuuid,
							machinePlace,machineMAC,itemName,itemCode,businessCode);
					JSONObject dataJson = new JSONObject();
					try{
						JSONObject idCardInfo = JSONObject.fromObject(strResult);
						String idcard = idCardInfo.optString("HOLDERCODE");
						String realname = idCardInfo.optString("HOLDERNAME");
						String VALIDSTARTDAY = idCardInfo.optString("VALIDSTARTDAY");
						String VALIDENDDAY = idCardInfo.optString("VALIDENDDAY");
						
						dataJson.put("idcard", idcard);
						dataJson.put("realname", realname);
						dataJson.put("VALIDSTARTDAY", VALIDSTARTDAY);
						dataJson.put("VALIDENDDAY", VALIDENDDAY);
						dataJson.put("certuuid", certuuid);
						
						js.put("success", "true");
					} catch (Exception e) {
						Log.debug("获取证照照面信息失败！");
						dataJson.put("idcard", "");
						dataJson.put("realname", "");
						dataJson.put("certuuid", "");
						dataJson.put("VALIDSTARTDAY", "");
						dataJson.put("VALIDENDDAY", "");
						
						js.put("success", "fasle");
					}

					js.put("data", dataJson);
					
					obj.put("result", js.toString());
					
					if (StringUtils.isNotBlank(certuuid)) {
						obj.put("url",
								"/selfapi/dzzz/showXhPicForBytes.do?certUuid="
										+ dealcCertUuid(certuuid)+"&sessionId="+sessionId);

						// 获取图片的base64位编码字符串
						byte[] bytes = DzUtils.showPic(certuuid, sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
						sun.misc.BASE64Encoder encoder = new BASE64Encoder();
						if (bytes != null) {
							String str = encoder.encode(bytes);
							obj.put("str", str);
						} else {
							obj.put("str", "");
						}

						// 新增获取营业执照编码信息和企业名称信息
						String base = DzUtils.getCertBaseData(sessionId,certuuid,
								machinePlace,machineMAC,itemName,itemCode,businessCode);
						System.out.println("----------------------" + base);
						net.sf.json.JSONObject json1 = new JSONObject();
						if (StringUtils.isNotBlank(base) 
								&& !"error".equals(base) 
								&& base.indexOf("<html>") == -1) {
							json1 = net.sf.json.JSONObject.fromObject(base);
						} else {
							obj.put("unitName", "");
							obj.put("code", "");
						}
						if (json1.has("holderList")) {
							net.sf.json.JSONObject json2 = net.sf.json.JSONObject
									.fromObject(json1.getString("holderList")
											.substring(
													1,
													json1.getString("holderList")
															.length() - 1));
							if (json2.has("name")) {// 企业名称
								obj.put("unitName", json2.getString("name"));
							}
							if (json2.has("code")) {// 信用代码
								obj.put("code", json2.getString("code"));
							}
						}
					} else {
						obj.put("url", "");
						obj.put("str", "");
						obj.put("unitName", "");
						obj.put("code", "");
					}
				}
				result = obj.toString();
				jsonResult.put("success", true);
				jsonResult.put("msg", "");
				jsonResult.put("data", result);
			} else {
				jsonResult.put("success", false);
				jsonResult.put("msg", "Error machine info, please contact the administrator!");
				jsonResult.put("data", "");
			}
		} else {
			jsonResult.put("success", false);
			jsonResult.put("msg", "Illegal machine, please contact the administrator!");
			jsonResult.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}

	public String dealcCertUuid(String certUuid) {
		certUuid = certUuid.replaceAll("\\+", "-");
		certUuid = certUuid.replaceAll("=", ",");
		return certUuid;
	}

	// 获取证照柜所在的地址
	public String dealLzAddress(String lzAddress) {
		if (StringUtils.isBlank(lzAddress)) {
			lzAddress = lzAddressInfo;
		} else {
			try {
				lzAddress = URLDecoder.decode(lzAddress, "utf-8");
			} catch (UnsupportedEncodingException e) {
				lzAddress = lzAddressInfo;
			}
		}
		return lzAddress;
	}

	public static void main(String[] args) {
	}
}
