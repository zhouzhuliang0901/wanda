package com.wondersgroup.dataitem.item367103164912.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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
import sun.misc.BASE64Encoder;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.Pdf2pngUtil;
import com.wondersgroup.dataitem.item251053034032.web.NewElectronicCertificateController;
import com.wondersgroup.dataitem.item367103164912.utils.DESUtil;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selfapi.util.DzUtils;

@Controller
public class VehicleManagementController {
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	/**
	 * (1)本人机动车查询接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/vehicleManagement/getVehicle.do")
	public void getVehicle(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String accessToken = req.getParameter("accessToken");
		JSONObject userJson = getUserInfoByToken(accessToken);
		String mobile = userJson.optString("zwdtsw_link_phone");
		String userName = userJson.optString("zwdtsw_name");
		String idCard = userJson.optString("zwdtsw_cert_id");
		
		JSONObject obj = new JSONObject();
		JSONObject jsonResult = new JSONObject();
		if(StringUtils.isEmpty(mobile) 
				|| StringUtils.isEmpty(userName) 
				|| StringUtils.isEmpty(idCard)){
			jsonResult.put("success", false);
			jsonResult.put("msg", "未获取到对应用户标识！");
			jsonResult.put("data", "");
		} else {
			obj.put("sjhm",mobile);
			obj.put("xm",userName);
			obj.put("sfzh",idCard);
			System.out.println("本人机动车查询入参原文："+obj.toString());
			String param = DESUtil.bytesToHexString(DESUtil.encode(obj.toString()));
			System.out.println("本人机动车查询入参密文："+param);
			
			if(StringUtils.isNotEmpty(param)){
				String appName = "";
				if("test".equals(Config.get("reindeer.huidao.environment"))){
					appName = "60cf2dc6-92c8-4206-9497-fffc9a4281bb";
				} else if("product".equals(Config.get("reindeer.huidao.environment"))){
					appName = "0082e6ac-8dfd-4475-9b70-1018ca8ac6e5";
				}
				String signature = HttpUtil.getSignature(appName);
				Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
				
				String contentType = "text/plain;charset=utf-8";
				String result = HttpUtil.doPost(head,param,contentType);
				System.out.println("本人机动车查询结果："+result);
				if(!"error".equals(result)){
					jsonResult = dealRresult(result);
					jsonResult.optJSONObject("data").put("mobile", mobile);
				} else {
					jsonResult.put("success", false);
					jsonResult.put("msg", "接口服务异常！");
					jsonResult.put("data", "");
				}
			} else {
				jsonResult.put("success", false);
				jsonResult.put("msg", "入参加密异常！");
				jsonResult.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/**
	 * (2)机动车违法信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/vehicleManagement/myVehVio.do")
	public void myVehVio(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String hphm = req.getParameter("hphm");
		String hpzl = req.getParameter("hpzl");
		String fdjh6 = req.getParameter("fdjh6");
		// 数据类型：1-全部、2-未处理、3-已处理未缴款、4-已缴款
		String sjlx = req.getParameter("sjlx");
		String accessToken = req.getParameter("accessToken");
		JSONObject userJson = getUserInfoByToken(accessToken);
		String mobile = userJson.optString("zwdtsw_link_phone");
		String userName = userJson.optString("zwdtsw_name");
		String idCard = userJson.optString("zwdtsw_cert_id");
		if(StringUtils.isNotEmpty(hphm)){
			hphm = URLDecoder.decode(hphm, "utf-8");
		}
		
		JSONObject obj = new JSONObject();
		JSONObject jsonResult = new JSONObject();
		if(StringUtils.isEmpty(mobile) 
				|| StringUtils.isEmpty(userName) 
				|| StringUtils.isEmpty(idCard)){
			jsonResult.put("success", false);
			jsonResult.put("msg", "未获取到对应用户标识！");
			jsonResult.put("data", "");
		} else {
			obj.put("sjhm",mobile);
			obj.put("xm",userName);
			obj.put("sfzh",idCard);
			obj.put("hphm",hphm);
			obj.put("hpzl",hpzl);
			obj.put("fdjh6",fdjh6);
			obj.put("sjlx",sjlx);
			obj.put("pageNo","1");
			System.out.println("机动车违法信息查询入参原文："+obj.toString());
			String param = DESUtil.bytesToHexString(DESUtil.encode(obj.toString()));
			System.out.println("机动车违法信息查询入参密文："+param);
			
			if(StringUtils.isNotEmpty(param)){
				String appName = "";
				if("test".equals(Config.get("reindeer.huidao.environment"))){
					appName = "a0513ab7-0c42-4485-b047-52ebbe621414";
				} else if("product".equals(Config.get("reindeer.huidao.environment"))){
					appName = "b5aeab8c-cf22-48ea-8721-5699e7d82d91";
				}
				String signature = HttpUtil.getSignature(appName);
				Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
				
				String contentType = "text/plain;charset=utf-8";
				String result = HttpUtil.doPost(head,param,contentType);
				System.out.println("机动车违法信息查询结果："+result);
				if(!"error".equals(result)){
					jsonResult = dealRresult(result);
				} else {
					jsonResult.put("success", false);
					jsonResult.put("msg", "接口服务异常！");
					jsonResult.put("data", "");
				}
			} else {
				jsonResult.put("success", false);
				jsonResult.put("msg", "入参加密异常！");
				jsonResult.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/**
	 * (3)驾驶证违法信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/vehicleManagement/myDrvVio.do")
	public void myDrvVio(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String jszh = req.getParameter("jszh");
		String dabh = req.getParameter("dabh");
		// 数据类型：1-全部、2-未处理、3-已处理未缴款、4-已缴款
		String sjlx = req.getParameter("sjlx");
		String accessToken = req.getParameter("accessToken");
		JSONObject userJson = getUserInfoByToken(accessToken);
		String mobile = userJson.optString("zwdtsw_link_phone");
		String userName = userJson.optString("zwdtsw_name");
		String idCard = userJson.optString("zwdtsw_cert_id");
		if(StringUtils.isNotEmpty(jszh)){
			jszh = URLDecoder.decode(jszh, "utf-8");
		}
		
		JSONObject obj = new JSONObject();
		JSONObject jsonResult = new JSONObject();
		if(StringUtils.isEmpty(mobile) 
				|| StringUtils.isEmpty(userName) 
				|| StringUtils.isEmpty(idCard)){
			jsonResult.put("success", false);
			jsonResult.put("msg", "未获取到对应用户标识！");
			jsonResult.put("data", "");
		} else {
			obj.put("sjhm",mobile);
			obj.put("xm",userName);
			obj.put("sfzh",idCard);
			obj.put("jszh",jszh);
			obj.put("dabh",dabh);
			obj.put("sjlx",sjlx);
			obj.put("pageNo","1");
			System.out.println("驾驶证违法信息查询入参原文："+obj.toString());
			String param = DESUtil.bytesToHexString(DESUtil.encode(obj.toString()));
			System.out.println("驾驶证违法信息查询入参密文："+param);
			
			if(StringUtils.isNotEmpty(param)){
				String appName = "";
				if("test".equals(Config.get("reindeer.huidao.environment"))){
					appName = "a35d9874-89ed-407a-9e5c-11be9ae1d544";
				} else if("product".equals(Config.get("reindeer.huidao.environment"))){
					appName = "34a862aa-b468-4e46-8837-8f4e46a707af";
				}
				String signature = HttpUtil.getSignature(appName);
				Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
				
				String contentType = "text/plain;charset=utf-8";
				String result = HttpUtil.doPost(head,param,contentType);
				System.out.println("驾驶证违法信息查询结果："+result);
				if(!"error".equals(result)){
					jsonResult = dealRresult(result);
				} else {
					jsonResult.put("success", false);
					jsonResult.put("msg", "接口服务异常！");
					jsonResult.put("data", "");
				}
			} else {
				jsonResult.put("success", false);
				jsonResult.put("msg", "入参加密异常！");
				jsonResult.put("data", "");
			}	
		}
		AciJsonHelper.writeJsonPResponse(req, res, jsonResult.toString());
	}
	
	/**
	 * 查询驾驶证图片
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/vehicleManagement/driverLicenseForImg.do")
	public void driverLicenseForImg(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
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
		String machineId = req.getParameter("machineId");
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		// 驾驶证照编号
		String catMainCode = "310100208000100";
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
		if(deviceInfo != null){
			InfopubArea infopubArea = infopubAreaDao.get(deviceInfo.getStAreaId());
			if(infopubArea != null){
//				String machinePlace = "SH00"+infopubArea.getStAreaCode();
//				String machineMAC = machineId;
				
				String itemName = req.getParameter("itemName");
				if(StringUtils.isNotEmpty(itemName)){
					itemName = URLDecoder.decode(itemName, "utf-8");
				}
				String itemCode = req.getParameter("itemCode");
				String businessCode = req.getParameter("businessCode");
				if(StringUtils.isEmpty(itemName)){
					itemName = "驾驶证违法信息查询";
				}
				if(StringUtils.isEmpty(itemCode)){
					itemCode = "0105105000-07-04";
				}
				if(StringUtils.isEmpty(businessCode)){
					businessCode = "";
				}
				
//				String userName = RdConfig.get("zzzd.cert.account");
//				String password = RdConfig.get("zzzd.cert.password");
				String userName = NewElectronicCertificateController.account;
				String password = NewElectronicCertificateController.password;
				String sessionId = DzUtils.getSessionId(userName, password);
				if(StringUtils.isEmpty(sessionId)){
					Log.debug("第一次登陆异常！");
					sessionId = DzUtils.getSessionId(userName, password);
				}
				System.out.println("sessionId:" + sessionId);
				// 查询证照基本信息
				String regex = "\\d{15}|\\d{17}[\\dxX]";
				if(certNo.matches(regex)){
					certNo = DzUtils.checkIDCard(sessionId, name, certNo, startDay, endDay, "", "", "", "", "");
				} else {
					certNo = certNo.replaceAll("-", "+");
					certNo = certNo.replaceAll(",", "#");
				}
				String certUuid = DzUtils.getCertInfo("0",certNo,catMainCode,sessionId, 
						"", "", "", "", "");
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
						String deriveUuid = DzUtils.deriveCert(sessionId, certUuid,
								use, "", "","","","","","");
						byte[] bytes = DzUtils.getDeriveCertFile(sessionId, deriveUuid,
								"","","","","");
						if(bytes != null){
							String suffix = Long.toString(System.currentTimeMillis());
							String pdfPath = VehicleManagementController.class.getResource("").getPath()
									+"template/driverLicensePDF_"+suffix+".pdf";
							FileUtil.getFileFromBytes(bytes, pdfPath);
							String pngPath = VehicleManagementController.class.getResource("").getPath()
									+"template/driverLicensePNG";
							List<String> list = Pdf2pngUtil.pdf2png(pdfPath, pngPath, "png");
							JSONArray arrResult = new JSONArray();
							for(String path : list){
								JSONObject obj = new JSONObject();
								File pngFile = new File(path);
								byte[] pngByte = FileUtil.getBytesFromFile(pngFile);
								String pngStr = new BASE64Encoder().encode(pngByte);
								obj.put("png", pngStr);
								arrResult.add(obj);
								pngFile.delete();
							}
							json.put("success", true);
							json.put("msg", "");
							json.put("data", arrResult);
						} else {
							json.put("success", false);
							json.put("msg", "证照PDF文件获取失败！");
							json.put("data", "");
						}
					}
				} else {
					json.put("success", false);
					json.put("msg", "无此证照信息!");
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
	
	private JSONObject getUserInfoByToken(String accessToken){
		String userInfo = HttpUtil.getUserInfoByAccesstoken(accessToken);
		String mobile = "";
		String userName = "";
		String certNo = "";
		String userID = "";
		JSONObject obj = new JSONObject();
		System.out.println("用户信息："+userInfo);
		JSONObject userJson = new JSONObject();
		try{
			userJson = JSONObject.fromObject(userInfo);
			mobile = userJson.optString("zwdtsw_link_phone");
			userName = userJson.optString("zwdtsw_name");
			certNo = userJson.optString("zwdtsw_cert_id");
			userID = userJson.optString("zwdtsw_user_id");
			
			obj.put("zwdtsw_link_phone",mobile);
			obj.put("zwdtsw_name",userName);
			obj.put("zwdtsw_cert_id",certNo);
			obj.put("zwdtsw_user_id",userID);
		} catch (Exception e) {
			Log.debug("获取用户信息失败"+e.getMessage());
		}
		return obj;
	}
	
	private JSONObject dealRresult(String result) throws IOException{
		byte[] decodeByt =  DESUtil.decode(result);
		JSONObject jsonResult = new JSONObject();
		if(decodeByt != null){
			jsonResult.put("success", true);
			jsonResult.put("msg", "");
			jsonResult.put("data", JSONObject.fromObject(new String(decodeByt,"utf-8")));
		} else {
			jsonResult.put("success", false);
			jsonResult.put("msg", "返回数据解密失败！");
			jsonResult.put("data", "");
		}
		return jsonResult;
	}
	
	public static void main(String[] args) {
	}
}
