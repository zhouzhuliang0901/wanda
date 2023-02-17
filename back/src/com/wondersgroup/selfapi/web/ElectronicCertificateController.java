package com.wondersgroup.selfapi.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selfapi.util.DzUtils;

@Controller
public class ElectronicCertificateController {
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取电子证照信息列表（返回的数据是获取证照图片的地址链接）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/dzzz/queryCertBaseData.do")
	public void queryCertBaseData(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String type = req.getParameter("type");
		String licenseType = req.getParameter("licenseType");
		String certNo = req.getParameter("certNo");
		// 生成派生照的用途
		String use = req.getParameter("use");
		if(StringUtils.isNotEmpty(use)){
			use = URLDecoder.decode(use, "utf-8");
		}
		// 电子证照安全用证，通过姓名、身份证号换取加密身份
		String name = req.getParameter("name");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay");
		if(StringUtils.isNotEmpty(endDay)){
			endDay = URLDecoder.decode(endDay, "utf-8");
		}
		String catMainCode = req.getParameter("catMainCode");
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				long a = System.currentTimeMillis();
				String userName = RdConfig.get("zzzd.cert.account");
				String password = RdConfig.get("zzzd.cert.password");
				String sessionId = DzUtils.getSessionId(userName, password);
				if(StringUtils.isEmpty(sessionId)){
					Log.debug("第一次登陆异常！");
					sessionId = DzUtils.getSessionId(userName, password);
				}
				System.out.println("sessionId:" + sessionId);
				long b = System.currentTimeMillis();
				System.out.println("登陆获取sessionId所花费时间："+(double)(b-a)/1000+"秒");
				String str = "";
				if("1".equals(type)){
					str = DzUtils.getCertInfo(type, certNo, catMainCode, sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
				} else {
					String regex = "\\d{15}|\\d{17}[\\dxX]";
					if(certNo.matches(regex)){
						certNo = DzUtils.checkIDCard(sessionId, name, certNo, startDay, endDay, machinePlace, machineMAC, itemName, itemCode, businessCode);
					} else {
						certNo = certNo.replaceAll("-", "+");
						certNo = certNo.replaceAll(",", "#");
					}
					str = DzUtils.getCertInfo(type, certNo, catMainCode, sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
				}

				JSONArray arrays = new JSONArray();
				JSONArray jsonArray = JSONObject.parseArray(str);
				List<String> list = Arrays.asList("312003001000500","310195631043500","310193291602500","310196646654500","312090119000500",
					"312014501000500","310196102986500","311050593000500","310750113000500","310193490271500","312014502000500","310199154354400");
				String catalogStr = DzUtils.getCertCatList(sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
				
				if (jsonArray != null && jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						long c = System.currentTimeMillis();
						String certUuid = jsonArray.getJSONObject(i).getString("certUuid");
						String catMainCodeResult = jsonArray.getJSONObject(i).getString("catMainCode");
						String validStartDay = jsonArray.getJSONObject(i).getString("validStartDay");
						String validEndDay = jsonArray.getJSONObject(i).getString("validEndDay");
						if(StringUtils.isEmpty(catMainCode) && list.contains(catMainCodeResult)){
							continue;
						}
						net.sf.json.JSONObject objCert = getCertName(catMainCodeResult,catalogStr);
						String certType = objCert.optString("type");
//						System.out.println("我的证照调试："+certType);
//						System.out.println("我的证照调试："+"stuff".equals(licenseType));
						if("cert".equals(licenseType) && "证明材料".equals(certType)){
							continue;
						} else if("stuff".equals(licenseType) && !"证明材料".equals(certType)){
							continue;
						}
						String certName = objCert.optString("name");
						if(StringUtils.isEmpty(use)){
							use = "自助终端打印";
						}
						String deriveUuid = DzUtils.deriveCert(sessionId, certUuid,
								use, "", "",machinePlace,machineMAC,itemName,itemCode,businessCode);
						Log.debug("deriveUuid:" + deriveUuid);
						JSONObject obj = new JSONObject();
						// 证照缩略图（GIF文件，base64格式）
//						obj.put("pictureUrl",
//								"/selfapi/dzzz/showXhPicForBase64.do?certUuid="
//										+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
//										+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
						// 证照缩略图（GIF文件，文件流）
						obj.put("pictureUrlForBytes",
								"/selfapi/dzzz/showXhPicForBytes.do?certUuid="
										+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
										+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
						// 派生证照（PDF文件，文件流）
						obj.put("derivePictureUrlForBytes",
								"/selfapi/dzzz/showStuffPicForBytes.do?deriveUuid="
										+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
										+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
						// 派生证照（PDF文件，base64格式）
						obj.put("derivePictureUrl",
								"/selfapi/dzzz/showStuffPicForBase64.do?deriveUuid="
										+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
										+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
						// 证照名称
						obj.put("certName",certName);
						// 证照有效期起始时间
						obj.put("validStartDay",validStartDay);
						// 证照有效期结束时间
						obj.put("validEndDay",validEndDay);
						if("310105109000100".equals(objCert.optString("code"))){
							obj.put("order", "3");
						} else if("310105105000100".equals(objCert.optString("code"))){
							obj.put("order", "2");
						} else if("310199561233100".equals(objCert.optString("code"))){
							obj.put("order", "1");
						} else {
							obj.put("order", "0");
						}
						
						arrays.add(obj);
						long d = System.currentTimeMillis();
						System.out.println("获取第"+(i+1)+"张证照所用时间："+(double)(d-c)/1000+"秒");
					}
					arrays = sortJSONArray(arrays);
					json.put("success", true);
					json.put("msg", "");
					json.put("data", arrays);
					long e = System.currentTimeMillis();
					System.out.println("获取证照总共所用时间："+(double)(e-a)/1000+"秒");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toJSONString());
	}
	
	/**
	 * 获取电子证照信息列表（返回的数据是获取证照图片的base64位编码的字符串）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/dzzz/queryCertBaseDatas.do")
	public void queryCertBaseDatas(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String type = req.getParameter("type");
		String certNo = req.getParameter("certNo");
		// 电子证照安全用证，通过姓名、身份证号换取加密身份
		String name = req.getParameter("name");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay"); 
		String catMainCode = req.getParameter("catMainCode");
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				String userName = RdConfig.get("zzzd.cert.account");
				String password = RdConfig.get("zzzd.cert.password");
				String sessionId = DzUtils.getSessionId(userName, password);
				System.out.println("sessionId:" + sessionId);
				
				String str = "";
				if("1".equals(type)){
					str = DzUtils.getCertInfo(type, certNo, catMainCode, sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
				} else {
					String regex = "\\d{15}|\\d{17}[\\dxX]";
					if(certNo.matches(regex)){
						certNo = DzUtils.checkIDCard(sessionId, name, certNo, startDay, endDay, machinePlace, machineMAC, itemName, itemCode, businessCode);
					} else {
						certNo = certNo.replaceAll("-", "+");
						certNo = certNo.replaceAll(",", "#");
					}
					str = DzUtils.getCertInfo(type, certNo, catMainCode, sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
				}
				
				JSONArray arrays = new JSONArray();
				JSONArray jsonArray = JSONObject.parseArray(str);
				if (jsonArray != null && jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						String certUuid = jsonArray.getJSONObject(i).getString(
								"certUuid");
						byte[] bytes = DzUtils.showPic(certUuid, sessionId, machinePlace, machineMAC,itemName,itemCode,businessCode);
						String result = "";
						sun.misc.BASE64Encoder encoder = new BASE64Encoder();
						if (bytes != null) {
							result = encoder.encode(bytes);
						}
						JSONObject obj = new JSONObject();
						obj.put("str", result);
						arrays.add(obj);
					}
					
				}
				json.put("success", true);
				json.put("msg", "");
				json.put("data", arrays);
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, json.toJSONString());
	}

	/**
	 * 获取证照图片的base64位编码字符串
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/dzzz/showXhPicForBase64.do")
	public void showXhPicForBase64(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String certUuid = req.getParameter("certUuid");
		String sessionId = req.getParameter("sessionId");
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				System.out.println("参数获取sessionId："+sessionId);
				if(StringUtils.isEmpty(sessionId)){
					String userName = RdConfig.get("zzzd.cert.account");
					String password = RdConfig.get("zzzd.cert.password");
					sessionId = DzUtils.getSessionId(userName, password);
					System.out.println("再次请求获取sessionId："+sessionId);
				}
				System.out.println("certUuid:" + certUuid);
				certUuid = certUuid.replaceAll("-", "+");
				certUuid = certUuid.replaceAll(",", "=");
				System.out.println("certUuid:" + certUuid);
				byte[] bytes = DzUtils.showPic(certUuid, sessionId, machinePlace, machineMAC,itemName,itemCode,businessCode);

				String str = "";
				sun.misc.BASE64Encoder encoder = new BASE64Encoder();
				if (bytes != null) {
					str = encoder.encode(bytes);
				}
				JSONObject obj = new JSONObject();
				obj.put("str", str);
				json.put("success", true);
				json.put("msg", "");
				json.put("data", obj);
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toJSONString());
	}
	
	/**
	 * 获取证照图片的文件流信息
	 */
	@RequestMapping("/selfapi/dzzz/showXhPicForBytes.do")
	public void showXhPicForBytes(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String certUuid = req.getParameter("certUuid");
		System.out.println("certUuid:" + certUuid);
		String sessionId = req.getParameter("sessionId");
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				System.out.println("参数获取sessionId："+sessionId);
				if(StringUtils.isEmpty(sessionId)){
					String userName = RdConfig.get("zzzd.cert.account");
					String password = RdConfig.get("zzzd.cert.password");
					sessionId = DzUtils.getSessionId(userName, password);
					System.out.println("再次请求获取sessionId："+sessionId);
				}
				certUuid = certUuid.replaceAll("-", "+");
				certUuid = certUuid.replaceAll(",", "=");
				System.out.println("certUuid:" + certUuid);
				byte[] bytes = DzUtils.showPic(certUuid, sessionId, machinePlace, machineMAC,itemName,itemCode,businessCode);
				if(bytes != null){
					OutputStream out = res.getOutputStream();
					res.setContentType("image/gif");
					out.write(bytes);
					out.close();
				} else {
					json.put("success", false);
					json.put("msg", "获取证照文件失败！");
					json.put("data", "");
					AciJsonHelper.writeJsonPResponse(req, res, json.toString());
				}
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
				AciJsonHelper.writeJsonPResponse(req, res, json.toString());
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
			AciJsonHelper.writeJsonPResponse(req, res, json.toString());
		}
	}
	
	

	/**
	 * 获取电子证照信息列表（返回的数据是获取证照图片的地址链接---------------个人材料证明的信息 出入境记录 社保证明等）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/dzzz/queryCertBaseStuffData.do")
	public void queryCertBaseStuffData(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String type = req.getParameter("type");
		String certNo = req.getParameter("certNo");
		// 电子证照安全用证，通过姓名、身份证号换取加密身份
		String name = req.getParameter("name");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String catMainCode = req.getParameter("catMainCode");
		String cause = req.getParameter("cause");
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay");
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				String userName = RdConfig.get("zzzd.cert.account");
				String password = RdConfig.get("zzzd.cert.password");
				String sessionId = DzUtils.getSessionId(userName, password);
				if(StringUtils.isEmpty(sessionId)){
					Log.debug("第一次登陆异常！");
					sessionId = DzUtils.getSessionId(userName, password);
				}
				System.out.println("sessionId:" + sessionId);
				String idCard = DzUtils.checkIDCard(sessionId, name, certNo, startDay, endDay, machinePlace, machineMAC, itemName, itemCode, businessCode);
				String str = DzUtils.getCertInfo(type, idCard, catMainCode, sessionId,machinePlace,
						machineMAC,itemName,itemCode,businessCode);
				JSONArray arrays = new JSONArray();
				JSONArray jsonArray = JSONObject.parseArray(str);
				if (jsonArray != null && jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						String certUuid = jsonArray.getJSONObject(i).getString(
								"certUuid");
						Log.debug("certUuid:" + certUuid);
						String deriveUuid = DzUtils.deriveCert(sessionId, certUuid,
								cause, "", "",machinePlace,machineMAC,itemName,itemCode,businessCode);
						Log.debug("deriveUuid:" + deriveUuid);
						JSONObject obj = new JSONObject();
						obj.put("pictureUrl",
								"/selfapi/dzzz/showStuffPicForBase64.do?deriveUuid="
										+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId);
						obj.put("pictureUrlForBytes",
								"/selfapi/dzzz/showStuffPicForBytes.do?deriveUuid="
										+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId);
						arrays.add(obj);
					}
				}
				json.put("success", true);
				json.put("msg", "");
				json.put("data", arrays);
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toJSONString());
	}

	/**
	 * 获取证照图片的base64位编码字符串
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/dzzz/showStuffPicForBase64.do")
	public void showStuffPicForBase64(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String deriveUuid = req.getParameter("deriveUuid");
		String sessionId = req.getParameter("sessionId");
		System.out.println("参数获取sessionId："+sessionId);
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				if(StringUtils.isEmpty(sessionId)){
					String userName = RdConfig.get("zzzd.cert.account");
					String password = RdConfig.get("zzzd.cert.password");
					sessionId = DzUtils.getSessionId(userName, password);
					System.out.println("再次请求获取sessionId："+sessionId);
				}
				System.out.println("deriveUuid:" + deriveUuid);
				deriveUuid = deriveUuid.replaceAll("-", "+");
				deriveUuid = deriveUuid.replaceAll(",", "=");
				System.out.println("deriveUuid:" + deriveUuid);
				byte[] bytes = DzUtils.getDeriveCertFile(sessionId, deriveUuid,machinePlace,machineMAC,itemName,itemCode,businessCode);
				String str = "";
//				sun.misc.BASE64Encoder encoder = new BASE64Encoder();
				if (bytes != null) {
					str = Base64Util.encode(bytes);
				}
				JSONObject obj = new JSONObject();
				obj.put("str", str);
				json.put("success", true);
				json.put("msg", "");
				json.put("data", obj);
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toJSONString());
	}

	/**
	 * 获取证照的派生本，返回为PDF文件
	 */
	@RequestMapping("/selfapi/dzzz/showStuffPicForBytes.do")
	public void showStuffPicForBytes(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String deriveUuid = req.getParameter("deriveUuid");
		System.out.println("deriveUuid:" + deriveUuid);
		String sessionId = req.getParameter("sessionId");
		System.out.println("参数获取sessionId："+sessionId);
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				if(StringUtils.isEmpty(sessionId)){
					String userName = RdConfig.get("zzzd.cert.account");
					String password = RdConfig.get("zzzd.cert.password");
					sessionId = DzUtils.getSessionId(userName, password);
					System.out.println("再次请求获取sessionId："+sessionId);
				}
				deriveUuid = deriveUuid.replaceAll("-", "+");
				deriveUuid = deriveUuid.replaceAll(",", "=");
				System.out.println("deriveUuid:" + deriveUuid);
				byte[] bytes = DzUtils.getDeriveCertFile(sessionId, deriveUuid,machinePlace,machineMAC,itemName,itemCode,businessCode);
				if(bytes != null){
					OutputStream out = res.getOutputStream();
					res.setContentType("application/pdf");
					out.write(bytes);
					out.close();
				} else {
					json.put("success", false);
					json.put("msg", "获取证照文件失败！");
					json.put("data", "");
					AciJsonHelper.writeJsonPResponse(req, res, json.toString());
				}
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
				AciJsonHelper.writeJsonPResponse(req, res, json.toString());
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
			AciJsonHelper.writeJsonPResponse(req, res, json.toString());
		}
	}

	public static String dealcCertUuid(String certUuid) {
		certUuid = certUuid.replaceAll("\\+", "-");
		certUuid = certUuid.replaceAll("=", ",");
		return certUuid;
	}
	
	/**
	 * 获取全部证照目录，遍历获得对应的证照名称
	 * @param catMainCode
	 * @param sessionId
	 * @return
	 */
	public String getCertName(String catMainCode,String sessionId,String machinePlace,
			String machineMAC,String itemName,String itemCode,String businessCode){
		String str = DzUtils.getCertCatList(sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
		JSONArray jsonArray = JSONObject.parseArray(str);
		String certName = "";
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				if(StringUtils.isNotEmpty(catMainCode) 
						&& catMainCode.equals(jsonArray.getJSONObject(i).getString("code"))){
					certName = jsonArray.getJSONObject(i).getString("name");
				}
			}
		}
		return certName;
	}
	
	/**
	 * 获取证照照面信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/DZCert/getCertOriginalData.do")
	public void getCertOriginalData(HttpServletRequest req,HttpServletResponse res) throws IOException{
		// 请求头身份验证
		String loginAccount = req.getHeader("account");
		String loginPassword = req.getHeader("password");
		String originString = new BASE64Encoder().encode(RdConfig.get("reindeer.cert.login.password").getBytes("utf-8"));
		String md5Password = DigestUtils.md5Hex(originString);
		
		JSONObject json = new JSONObject();
		if(RdConfig.get("reindeer.cert.login.account").equals(loginAccount) 
				&& md5Password.equals(loginPassword)){
			String identNo = req.getParameter("holderCode");// 91110102560421751N
			// 电子证照安全用证，通过姓名、身份证号换取加密身份
			String name = req.getParameter("name");// 持证人姓名
			if(StringUtils.isNotEmpty(name)){
				name = URLDecoder.decode(name, "utf-8");
			}
			String startDay = req.getParameter("startDay");// 证件有效期
			String endDay = req.getParameter("endDay");// 证件有效期
			String catMainCode = req.getParameter("catMainCode");// 证件编号  310100717000200
			String type = req.getParameter("type");
			
			String machineId = req.getParameter("machineId");// 设备MAC
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
//						itemCode = "3577729031";
						itemCode = RdConfig.get("zzzd.cert.default.itemCode");
					}
					if(StringUtils.isEmpty(businessCode)){
//						businessCode = "21150202143253421495";
						businessCode = UUID.randomUUID().toString().replace("-", "");
					}
					
					String userName = RdConfig.get("zzzd.cert.account");
					String password = RdConfig.get("zzzd.cert.password");
					String sessionId = DzUtils.getSessionId(userName, password);
					if(StringUtils.isEmpty(sessionId)){
						Log.debug("第一次登陆异常！");
						sessionId = DzUtils.getSessionId(userName, password);
					}
					System.out.println("sessionId:" + sessionId);
					// 查询证照基本信息
					String regex = "\\d{15}|\\d{17}[\\dxX]";// 身份证号码正则
					if(identNo.matches(regex)){
						identNo = DzUtils.checkIDCard(sessionId, name, identNo, startDay, endDay, machinePlace, machineMAC, itemName, itemCode, businessCode);
					} else {
						identNo = identNo.replaceAll("-", "+");
						identNo = identNo.replaceAll(",", "#");
					}
					String certUuid = DzUtils.getCertInfo(type,identNo,catMainCode,sessionId, 
							machinePlace, machineMAC, itemName, itemCode, businessCode);
					if(StringUtils.isNotEmpty(certUuid)){
						if(certUuid.startsWith("error")){
							json.put("success", false);
							json.put("msg", certUuid);
							json.put("data", "");
						} else {
				    		net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(certUuid);
				    		net.sf.json.JSONObject jsonResult = null;
				    		for(int iLoop = 0;iLoop<arr.size();iLoop++){
				    			jsonResult = arr.getJSONObject(iLoop);
				    			if(catMainCode.equals(jsonResult.getString("catMainCode"))){
				    				certUuid = jsonResult.getString("certUuid");
				    			}
				    		}
							// 查询证照照面信息
							String result = DzUtils.getCertOriginalData(sessionId,certUuid,
									machinePlace, machineMAC, itemName, itemCode, businessCode);
							if(result.indexOf("<html>") != -1 || StringUtils.isEmpty(result)){
								json.put("success", false);
								json.put("msg", "查询证照照面信息异常!");
								json.put("data", "");
							} else {
								json.put("success", true);
								json.put("msg", "");
								json.put("data", net.sf.json.JSONObject.fromObject(result));
							}
						}
					} else {
						json.put("success", false);
						json.put("msg", "无此类证照信息!");
						json.put("data", "");
					}
				} else {
					json.put("success", false);
					json.put("msg", "Error machine info, please contact the administrator!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Illegal machine, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal request, please check the parameters!");
			json.put("data", "");
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 通用：所有通过统一审批编码获取办事结果证照,都可以用此接口
	 * @param itemCode 办事事项编码
	 * @param businessCode 统一审批编号
	 * @param itemName 办事事项名称
	 * @param machineId 自助终端机器唯一码（debug配置）
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/DZCert/getCertByApplyNo.do")
	public void getCertByApplyNo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "3577729031";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				JSONObject josnResult = new JSONObject();
				String sessionId = "";
				if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
					sessionId = DzUtils.getSessionIdTest("test", "abcd1234");
				} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
					sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
				}
				if(StringUtils.isNotEmpty(sessionId)){
					
					try{
						String str = "";
						if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
							str = DzUtils.getCertBaseDataByBusinessCodeTest(sessionId, itemCode, businessCode);
						} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
							str = DzUtils.getCertBaseDataByBusinessCode(sessionId, machinePlace, machineMAC, itemCode, businessCode,itemName);
						}
						JSONObject obj = JSONObject.parseObject(str);
						String certUuid = obj.getString("certUuid");
						if(StringUtils.isNotEmpty(certUuid)){
							String deriveUuid = "";
							Thread.sleep(5000);
							if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
								deriveUuid = DzUtils.deriveCertTest(sessionId, certUuid,
										"自助终端打印", "", "",itemCode,businessCode);
							} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
								deriveUuid = DzUtils.deriveCert(sessionId, certUuid,
										"自助终端打印", "", "", machinePlace, machineMAC,itemName,itemCode,businessCode);
							}
							josnResult.put("SUCCESS", "TRUE");
							josnResult.put("CODE", 1);
							josnResult.put("MSG", "");
							josnResult.put("PNGURL", "/selfapi/dzzz/humanSocietyPNGForBytes.do?certUuid="
									+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
									+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
							josnResult.put("PDFURL", "/selfapi/dzzz/humanSocietyPDFForBytes.do?certUuid="
									+ dealcCertUuid(certUuid)+"&sessionId="+sessionId+"&machineId="+machineId
									+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
							// 派生证照（PDF文件，base64格式）
							josnResult.put("base64Url",
									"/selfapi/dzzz/showStuffPicForBase64.do?deriveUuid="
											+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
											+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
						} else {
							josnResult.put("SUCCESS", "FALSE");
							josnResult.put("CODE", 1);
							josnResult.put("MSG", "此办件暂未生成证照！");
						}
					} catch (Exception e) {
						Log.debug(e);
						Log.debug("根据办件编号（统一审批编码）获取证照基本信息失败！");
						josnResult.put("SUCCESS", "FALSE");
						josnResult.put("CODE", 0);
						josnResult.put("MSG", "根据办件编号（统一审批编码）获取证照基本信息失败！");
						josnResult.put("URL","");
					}
				} else {
					josnResult.put("SUCCESS", "FALSE");
					josnResult.put("CODE", 0);
					josnResult.put("MSG", "电子证照库登陆失败！");
					josnResult.put("URL", "");
				}
				json.put("success", true);
				json.put("msg", "");
				json.put("data", josnResult);
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 查询被授权证照信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/DZCert/queryeles.do")
	public void queryeles(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String certNo = req.getParameter("certNo");
		String phone = req.getParameter("phone");
		String name = req.getParameter("name");
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay"); 
		String use = req.getParameter("use");
		String licenseType = req.getParameter("licenseType");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		if(StringUtils.isNotEmpty(use)){
			use = URLDecoder.decode(use, "utf-8");
		} else {
			use = "自助终端打印";
		}
		
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "3577729031";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				JSONObject josnResult = new JSONObject();
				String sessionId = DzUtils.getSessionId(RdConfig.get("zzzd.cert.account"), RdConfig.get("zzzd.cert.password"));
				if(StringUtils.isNotEmpty(sessionId)){
					String regex = "\\d{15}|\\d{17}[\\dxX]";
					if(certNo.matches(regex)){
						certNo = DzUtils.checkIDCard(sessionId, name, certNo, startDay, endDay, 
								machinePlace, machineMAC, itemName, itemCode, businessCode);
					} else {
						certNo = certNo.replaceAll("-", "+");
						certNo = certNo.replaceAll(",", "#");
					}
					String str = DzUtils.queryeles(sessionId,name,certNo,phone,machinePlace,
							machineMAC,itemName,itemCode,businessCode);
					String catalogStr = DzUtils.getCertCatList(sessionId,machinePlace,machineMAC,itemName,
							itemCode,businessCode);
					if(StringUtils.isNotEmpty(str)){
						JSONArray arrays = new JSONArray();
						try{
							JSONObject resJson =  JSONObject.parseObject(str);
							JSONObject respDataJson =  resJson.getJSONObject("data").getJSONObject("respData");
							int ret = respDataJson.getIntValue("ret");
							String message = respDataJson.getString("message");
							if(ret == 0){
								String is_authed_person = respDataJson.getJSONObject("data").getString("is_authed_person");
								if("1".equals(is_authed_person)){
									JSONArray eleArrs = respDataJson.getJSONObject("data").getJSONArray("ele_infos");
									if (eleArrs != null && eleArrs.size() > 0) {
										for(int i = 0; i < eleArrs.size(); i++){
											JSONObject eleJson = eleArrs.getJSONObject(i);
											JSONObject obj = new JSONObject();
											String status = eleJson.getString("status");
											if("1".equals(status)){
												String ele_name = eleJson.getString("ele_name");
												String ele_code = eleJson.getString("ele_code");
												String auth_start_time = eleJson.getString("auth_start_time");
												String auth_end_time = eleJson.getString("auth_end_time");
												String ele_id = eleJson.getString("ele_id");
												net.sf.json.JSONObject objCert = getCertName(ele_code, catalogStr);
												String certType = objCert.optString("type");
												if("cert".equals(licenseType) && "证明材料".equals(certType)){
													continue;
												} else if("stuff".equals(licenseType) && !"证明材料".equals(certType)){
													continue;
												}
												// 证照缩略图（GIF文件，文件流）
												obj.put("pictureUrlForBytes", "/selfapi/dzzz/showXhPicForBytes.do?certUuid="
														+ dealcCertUuid(ele_id)+"&sessionId="+sessionId+"&machineId="+machineId
														+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
												String deriveUuid = DzUtils.deriveCert(sessionId, ele_id,
														use, "", "",machinePlace,machineMAC,itemName,itemCode,businessCode);
												Log.debug("被授权证照deriveUuid:" + deriveUuid);
												if(StringUtils.isNotEmpty(deriveUuid)){
													// 派生证照（PDF文件，文件流）
													obj.put("derivePictureUrlForBytes", "/selfapi/dzzz/showStuffPicForBytes.do?deriveUuid="
															+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
															+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
													// 派生证照（PDF文件，base64格式）
													obj.put("derivePictureUrl", "/selfapi/dzzz/showStuffPicForBase64.do?deriveUuid="
																	+ dealcCertUuid(deriveUuid)+"&sessionId="+sessionId+"&machineId="+machineId
																	+"&itemName="+URLEncoder.encode(itemName, "UTF-8")+"&itemCode="+itemCode+"&businessCode="+businessCode);
												}
												obj.put("certName", ele_name);
												obj.put("auth_start_time", auth_start_time);
												obj.put("auth_end_time", auth_end_time);
												arrays.add(obj);
											} else {
												continue;
											}
										}
//										josnResult.put("SUCCESS", true);
										josnResult.put("CODE", 0);
										josnResult.put("MSG", URLDecoder.decode(message, "utf-8"));
										josnResult.put("certArrs", arrays);
									}
								} else {
//									josnResult.put("SUCCESS", false);
									josnResult.put("CODE", 4);
									josnResult.put("MSG", "操作人不是被授权人！");
									josnResult.put("certArrs", "");
								}
							} else {
								josnResult.put("CODE", 5);
								josnResult.put("MSG", URLDecoder.decode(message, "utf-8"));
								josnResult.put("certArrs", "");
							}
						} catch (Exception e) {
//							josnResult.put("SUCCESS", false);
							josnResult.put("CODE", 3);
							josnResult.put("MSG", "授权证照列表数据异常！");
							josnResult.put("certArrs", "");
						}
					} else {
//						josnResult.put("SUCCESS", false);
						josnResult.put("CODE", 2);
						josnResult.put("MSG", "被授权证照信息查询失败！");
						josnResult.put("certArrs", "");
					}
				} else {
//					josnResult.put("SUCCESS", false);
					josnResult.put("CODE", 1);
					josnResult.put("MSG", "电子证照库登陆失败！");
					josnResult.put("certArrs", "");
				}
				json.put("success", true);
				json.put("msg", "");
				json.put("data", josnResult);
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 获取持证人所持有证照目录清单
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/DZCert/queryCertExistence.do")
	public void queryCertExistence(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String catMainCode = req.getParameter("catMainCode");
		String holderCode = req.getParameter("holderCode");//ywtbUser,-sAjuldomhgUtyhS0eBS3qRQ/La5zDnOnvpAUP3pHQnehKCRjE8rxNX3s7g02sP-
		String holderType = req.getParameter("holderType");
		// 电子证照安全用证，通过姓名、身份证号换取加密身份
		String name = req.getParameter("name");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay"); 
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
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
//					itemCode = "357772903100";
					itemCode = RdConfig.get("zzzd.cert.default.itemCode");
				}
				if(StringUtils.isEmpty(businessCode)){
//					businessCode = "21150202143253421495";
					businessCode = UUID.randomUUID().toString().replace("-", "");
				}
				
				String userName = RdConfig.get("zzzd.cert.account");
				String password = RdConfig.get("zzzd.cert.password");
				String sessionId = DzUtils.getSessionId(userName, password);
				if(StringUtils.isEmpty(sessionId)){
					Log.debug("第一次登陆异常！");
					sessionId = DzUtils.getSessionId(userName, password);
				}
				System.out.println("sessionId:" + sessionId);
				String str = "";
				if("0".equals(holderType)){
					String regex = "\\d{15}|\\d{17}[\\dxX]";
					if(holderCode.matches(regex)){
						holderCode = DzUtils.checkIDCard(sessionId, name, holderCode, startDay, endDay, machinePlace, machineMAC, itemName, itemCode, businessCode);
					} else {
						holderCode = holderCode.replaceAll("-", "+");
						holderCode = holderCode.replaceAll(",", "#");
					}
				} else {
				}
				str = DzUtils.queryCertExistence(holderType, holderCode, catMainCode, sessionId,machinePlace,machineMAC,itemName,itemCode,businessCode);
				try{
					json.put("success", true);
					json.put("msg", "");
					json.put("data", JSONArray.parseArray(str));
				} catch (Exception e) {
					json.put("success", false);
					json.put("msg", "Format JSONArray Exception!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Error machine info, please contact the administrator!");
				json.put("data", "");
			}
		} else {
			json.put("success", false);
			json.put("msg", "Illegal machine, please contact the administrator!");
			json.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	public net.sf.json.JSONObject getCertName(String catMainCode,String catalogStr){
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
		net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray();
		try{
			jsonArray = net.sf.json.JSONArray.fromObject(catalogStr);
		} catch (Exception e) {
			return obj;
		}
		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				if(StringUtils.isNotEmpty(catMainCode) 
						&& catMainCode.equals(jsonArray.getJSONObject(i).getString("code"))){
					obj = jsonArray.getJSONObject(i);
				}
			}
		}
		return obj;
	}
	
    public static String getMachineInfo(File jsonFile,String machineId) {
        String jsonStr = "";
        Log.debug("————开始读取" + jsonFile.getPath() + "文件————");
        try {
            //File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            Log.debug("————读取" + jsonFile.getPath() + "文件结束!————");
            
            net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(jsonStr);
            net.sf.json.JSONObject jsonMachine = new net.sf.json.JSONObject();
            for(int iLoop = 0;iLoop<arr.size();iLoop++){
            	jsonMachine = arr.optJSONObject(iLoop);
            	String id = jsonMachine.optString("machineId");
            	if(id.equals(machineId)){
            		break;
            	}
            }
            return jsonMachine.toString();
        } catch (Exception e) {
        	Log.debug("————读取" + jsonFile.getPath() + "文件出现异常，读取失败!————");
        	Log.debug(e);
            return null;
        }
    }
	
	 private static JSONArray sortJSONArray(JSONArray bindArrayResult) {
	      List<JSONObject> list = JSONArray.parseArray(bindArrayResult.toJSONString(), JSONObject.class);
//	      System.out.println("排序前："+bindArrayResult);
	      Collections.sort(list, new Comparator<JSONObject>() {
	          @Override
	          public int compare(JSONObject o1, JSONObject o2) {
	              int a = Integer.valueOf(o1.getString("order"));
	              int b = Integer.valueOf(o2.getString("order"));
	              if (a > b) {
	                  return -1;
	              } else if(a == b) {
	                  return 0;
	              } else
	                  return 1;
	              }
	      });
	      JSONArray jsonArray = JSONArray.parseArray(list.toString());
//	      System.out.println("排序后："+jsonArray);
	      return jsonArray;
	 }
    
    @RequestMapping("/selfapi/dzzz/getCertCategory.do")
    public void getCertCategory(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String sessionId = DzUtils.getSessionId("zhzzzdyz", "YcbkLoeVwK5rH4y");
		String str = DzUtils.getCertCatList(sessionId,"SH00MH","00-E2-69-27-A2-6F","证照自助查询或打印","357772903100","21150202143253421495");
		AciJsonHelper.writeJsonPResponse(req, res, str);
    }
    
    public static void main(String[] args) {
    	File jsonFile = new File("C:\\Users\\wanda\\Desktop\\电子证照照面字段信息\\证照目录20220105.txt");
    	String jsonStr = "";
    	try{
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            Log.debug("————读取" + jsonFile.getPath() + "文件结束!————");
            net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(jsonStr);
            writeExcel(arr, "C:\\Users\\wanda\\Desktop\\电子证照照面字段信息\\证照目录20220105.xlsx");
    	} catch (Exception e) {
    		Log.debug("————读取" + jsonFile.getPath() + "文件出现异常，读取失败!————");
		}

	}
    
    public static void writeExcel(net.sf.json.JSONArray arr ,String filePath){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("cert");
        XSSFRow firstRow = sheet.createRow(0);//第一行表头
        XSSFCell cells[] = new XSSFCell[8];

        String[] titles = new String[]{"code","name","depart", "type", "category", "holderType", "source", "level"};
        //循环设置表头信息
        for (int i=0;i<8;i++){
            cells[0]=firstRow.createCell(i);
            cells[0].setCellValue(titles[i]);
        }

        //遍历list,将数据写入Excel中
        for (int i=0;i<arr.size();i++){
            XSSFRow row = sheet.createRow(i+1);
//            Student student = list.get(i);
            net.sf.json.JSONObject obj = arr.optJSONObject(i);
            XSSFCell cell = row.createCell(0); //第一列
            cell.setCellValue(obj.optString("code"));
            cell=row.createCell(1); 
            cell.setCellValue(obj.optString("name"));
            cell=row.createCell(2); 
            cell.setCellValue(obj.optString("depart"));
            cell=row.createCell(3); 
            cell.setCellValue(obj.optString("type"));
            cell=row.createCell(4); 
            cell.setCellValue(obj.optString("category"));
            cell=row.createCell(5); 
            cell.setCellValue(obj.optString("holderType"));
            cell=row.createCell(6); 
            cell.setCellValue(obj.optString("source"));
            cell=row.createCell(7); 
            cell.setCellValue(obj.optString("level"));
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
