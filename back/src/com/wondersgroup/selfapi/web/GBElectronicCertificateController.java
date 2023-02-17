package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import wfc.service.log.Log;

import com.wondersgroup.dataitem.item251053034032.web.NewElectronicCertificateController;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selfapi.util.DzUtils;
import com.wondersgroup.selfapi.util.GbECertUtil;

/**
 * 国办长三角电子证照查询
 * @author wanda
 *
 */
@Controller
public class GBElectronicCertificateController {
	
	/**
	 * 3.1.获取国办证照类型清单
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/GBElecCert/getCertificateType.do")
	public void getCertificateType(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String machineId = req.getParameter("machineId");
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
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
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				String str = GbECertUtil.getCertificateType(sessionId,"","","","","");
				JSONObject result = JSONObject.fromObject(str);
				json.put("data", result);
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 3.2.检索国办证照
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/GBElecCert/retrieval.do")
	public void retrieval(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String CertificateHolderCode = req.getParameter("CertificateHolderCode");
		String CertificateType = req.getParameter("CertificateType");
		String machineId = req.getParameter("machineId");
		// 明文身份证时必传
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay");
		String name = req.getParameter("name");
		
		if(StringUtils.isNotEmpty(CertificateType)){
			CertificateType = URLDecoder.decode(CertificateType, "utf-8");
		}
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
			String itemName = req.getParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)){
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			String itemCode = req.getParameter("itemCode");
			String businessCode = req.getParameter("businessCode");
			String UseFor = req.getParameter("UseFor");
			if(StringUtils.isEmpty(itemName)){
				itemName = "证照自助查询或打印";
			} else {
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			if(StringUtils.isEmpty(itemCode)){
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			if(StringUtils.isEmpty(UseFor)){
				UseFor = "自助终端打印";
			} else {
				UseFor = URLDecoder.decode(UseFor, "utf-8");
			}
			
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				String regex = "\\d{15}|\\d{17}[\\dxX]";
				if(CertificateHolderCode.matches(regex)){
					CertificateHolderCode = GbECertUtil.checkIDCard(sessionId, name, CertificateHolderCode, startDay, endDay, 
							"", "", "", "", "");
				}
				String str = GbECertUtil.retrieval(sessionId, CertificateHolderCode, CertificateType, UseFor, 
						"","","","","");
				try{
					JSONObject result = JSONObject.fromObject(str);
//					JSONObject dataJ = JSONObject.fromObject(result.optString("data"));
//					JSONArray dataList = dataJ.optJSONObject("data").optJSONArray("dataList");
//					JSONArray arr = new JSONArray();
//					for(int iLoop = 0;iLoop<dataList.size();iLoop++){
//						JSONObject e = dataList.optJSONObject(iLoop);
//						String certificateAreaCode = e.optString("certificateAreaCode");
//						if(!certificateAreaCode.startsWith("31")){
//							arr.add(e);
//						}
//					}
//					dataJ.optJSONObject("data").put("dataList", arr);
//					result.put("data", dataJ);
					json.put("data", result);
				} catch (JSONException je) {
					json.put("success", false);
					json.put("msg", "Illegal JSONObject format");
					json.put("data", "");
				}catch (Exception e) {
					Log.debug(e);
					json.put("success", false);
					json.put("msg", "Inner Server Exception!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 3.3.基于证照标识获取国办证照信息
	 * 3.4.基于持证主体获取国办证照信息
	 * @param CertificateID 国办证照的唯一编号
	 * @param CertificateType 证照类型中文名称
	 * @param CertificateNumber 证照照面编号
	 * @param CertificateHolderCode 持证人编号
	 * @param req
	 * @param res
	 * @throws IOException
	 * (CertificateID)与(CertificateType、CertificateNumber、CertificateHolderCode)为两组参数，传其一组即可，
	 * 前者是3.3接口，后者是3.4接口
	 */
	@RequestMapping("/selfapi/GBElecCert/getCertificateData.do")
	public void getCertificateData(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String CertificateID = req.getParameter("CertificateID");
		String CertificateType = req.getParameter("CertificateType");
		String CertificateNumber = req.getParameter("CertificateNumber");
		String CertificateHolderCode = req.getParameter("CertificateHolderCode");
		String machineId = req.getParameter("machineId");
		if(StringUtils.isNotEmpty(CertificateType)){
			CertificateType = URLDecoder.decode(CertificateType, "utf-8");
		}
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
			String itemName = req.getParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)){
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			String itemCode = req.getParameter("itemCode");
			String businessCode = req.getParameter("businessCode");
			String UseFor = req.getParameter("UseFor");
			if(StringUtils.isEmpty(itemName)){
				itemName = "证照自助查询或打印";
			} else {
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			if(StringUtils.isEmpty(itemCode)){
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			if(StringUtils.isEmpty(UseFor)){
				UseFor = "自助终端打印";
			} else {
				UseFor = URLDecoder.decode(UseFor, "utf-8");
			}
			
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				String str = "";
				if(StringUtils.isNotEmpty(CertificateID)){
					str = GbECertUtil.getCertificateDataByCertificateID(sessionId, CertificateID, UseFor, 
							"","","","","");
				} else if(StringUtils.isNotEmpty(CertificateType) 
						&& StringUtils.isNotEmpty(CertificateNumber) 
						&& StringUtils.isNotEmpty(CertificateHolderCode)){
					str = GbECertUtil.getCertificateDataByHolder(sessionId, CertificateType, CertificateNumber, 
							CertificateHolderCode, UseFor, "","","","","");
				}
				try{
					JSONObject result = JSONObject.fromObject(str);
					json.put("data", result);
				} catch (JSONException je) {
					json.put("success", false);
					json.put("msg", "Illegal JSONObject format");
					json.put("data", "");
				}catch (Exception e) {
					json.put("success", false);
					json.put("msg", "Inner Server Exception!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 3.5.基于证照标识下载国办证照文件
	 * 3.6.基于持证主体下载国办证照文件
	 * @param CertificateID 国办证照的唯一编号
	 * @param CertificateType 证照类型中文名称
	 * @param CertificateNumber 证照照面编号
	 * @param CertificateHolderCode 持证人编号
	 * @param req
	 * @param res
	 * @throws IOException
	 * (CertificateID)与(CertificateType、CertificateNumber、CertificateHolderCode)为两组参数，传其一组即可，
	 * 前者是3.5接口，后者是3.6接口
	 */
	@RequestMapping("/selfapi/GBElecCert/getCertificateFile.do")
	public void getCertificateFile(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String CertificateID = req.getParameter("CertificateID");
		String CertificateType = req.getParameter("CertificateType");
		String CertificateNumber = req.getParameter("CertificateNumber");
		String CertificateHolderCode = req.getParameter("CertificateHolderCode");
		String machineId = req.getParameter("machineId");
		if(StringUtils.isNotEmpty(CertificateType)){
			CertificateType = URLDecoder.decode(CertificateType, "utf-8");
		}
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
			String itemName = req.getParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)){
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			String itemCode = req.getParameter("itemCode");
			String businessCode = req.getParameter("businessCode");
			String UseFor = req.getParameter("UseFor");
			if(StringUtils.isEmpty(itemName)){
				itemName = "证照自助查询或打印";
			} else {
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			if(StringUtils.isEmpty(itemCode)){
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			if(StringUtils.isEmpty(UseFor)){
				UseFor = "自助终端打印";
			} else {
				UseFor = URLDecoder.decode(UseFor, "utf-8");
			}
			
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				String str = "";
				if(StringUtils.isNotEmpty(CertificateID)){
					str = GbECertUtil.getCertificateFileByCertificateID(sessionId, CertificateID, UseFor, 
							"","","","","");
				} else if(StringUtils.isNotEmpty(CertificateType) 
						&& StringUtils.isNotEmpty(CertificateNumber) 
						&& StringUtils.isNotEmpty(CertificateHolderCode)){
					str = GbECertUtil.getCertificateFileByHolder(sessionId, CertificateType, CertificateNumber, 
							CertificateHolderCode, UseFor, "","","","","");
				}
				try{
					JSONObject result = JSONObject.fromObject(str);
					json.put("data", result);
				} catch (JSONException je) {
					json.put("success", false);
					json.put("msg", "Illegal JSONObject format");
					json.put("data", "");
				}catch (Exception e) {
					json.put("success", false);
					json.put("msg", "Inner Server Exception!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 3.7.国办证照文件下载
	 * @param url 下载证照的链接
	 * @param fileFormat 证照文件格式
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/GBElecCert/getCertFile.do")
	public void getCertFile(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String url = req.getParameter("url");
		String fileFormat = req.getParameter("fileFormat");
		String machineId = req.getParameter("machineId");
		String type = req.getParameter("type");
		if(StringUtils.isNotEmpty(url)){
			url = URLDecoder.decode(url, "utf-8");
		}
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
			String itemName = req.getParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)){
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			String itemCode = req.getParameter("itemCode");
			String businessCode = req.getParameter("businessCode");
			if(StringUtils.isEmpty(itemName)){
				itemName = "证照自助查询或打印";
			} else {
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			if(StringUtils.isEmpty(itemCode)){
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				byte[] byts = GbECertUtil.getCertFile(sessionId, url, fileFormat, "","","","","");
				if(byts != null){
					if("byte".equals(type)){
						OutputStream out = res.getOutputStream();
						res.setContentType("application/pdf");
						out.write(byts);
						out.close();
					} else {
						String base64File = new BASE64Encoder().encode(byts);
						json.put("success", true);
						json.put("msg", "");
						json.put("data", base64File);
					}
				} else {
					json.put("success", false);
					json.put("msg", "Ecert File Not Found!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	/**
	 * 3.8.国办证照缩略图下载（文件方式）
	 * 3.9.国办证照缩略图下载（JSON方式）
	 * @param certificateType 证照类型中文名称
	 * @param certificateNumber 证照照面编号
	 * @param url 下载证照的链接
	 * @param fileFormat 证照文件格式
	 * @param watermark 图片上水印
	 * @param type 下在缩略图形式。byte：文件形式；string：base64形式
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/GBElecCert/getThumbnail.do")
	public void getThumbnail(HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		String certificateType = req.getParameter("certificateType");
		String certificateNumber = req.getParameter("certificateNumber");
		String url = req.getParameter("url");
		String fileFormat = req.getParameter("fileFormat");
		String watermark = req.getParameter("watermark");
		String machineId = req.getParameter("machineId");
		String type = req.getParameter("type");
		if(StringUtils.isNotEmpty(certificateType)){
			certificateType = URLDecoder.decode(certificateType, "utf-8");
		}
		if(StringUtils.isNotEmpty(url)){
			url = URLDecoder.decode(url, "utf-8");
		}
		if(StringUtils.isNotEmpty(watermark)){
			watermark = URLDecoder.decode(watermark, "utf-8");
		} else {
			watermark = "自助终端打印";
		}
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
			String itemName = req.getParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)){
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			String itemCode = req.getParameter("itemCode");
			String businessCode = req.getParameter("businessCode");
			if(StringUtils.isEmpty(itemName)){
				itemName = "证照自助查询或打印";
			} else {
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			if(StringUtils.isEmpty(itemCode)){
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				if("byte".equals(type)){
					byte[] byts = GbECertUtil.getThumbnail(sessionId, certificateType, certificateNumber, url, 
							fileFormat, watermark, "","","","","");
					if(byts != null){
						OutputStream out = res.getOutputStream();
						res.setContentType("image/gif");
						out.write(byts);
						out.close();
					} else {
						json.put("success", false);
						json.put("msg", "Ecert File Not Found!");
						json.put("data", "");
						AciJsonHelper.writeJsonPResponse(req, res, json.toString());
					}
				} else if ("string".equals(type)){
					String str = GbECertUtil.getThumbnails(sessionId, certificateType, certificateNumber, url, 
							fileFormat, watermark, "","","","","");
					if(StringUtils.isNotEmpty(str)){
						json.put("data", str);
					}
					AciJsonHelper.writeJsonPResponse(req, res, json.toString());
				}
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
				AciJsonHelper.writeJsonPResponse(req, res, json.toString());
			}
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, json.toString());
		}
	}
	
	/**
	 * 我的证照-长三角证照
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/GBElecCert/queryCertBaseDataForGb.do")
	public void queryCertBaseDataForGb(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String CertificateHolderCode = req.getParameter("CertificateHolderCode");//320721199408210016
		String CertificateType = req.getParameter("CertificateType");
		String machineId = req.getParameter("machineId");
		// 明文身份证时必传
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay");
		String name = req.getParameter("name");
//		String watermark = req.getParameter("watermark");
		if(StringUtils.isNotEmpty(CertificateType)){
			CertificateType = URLDecoder.decode(CertificateType, "utf-8");
		}
		JSONObject json = checkMachine(machineId);
		if(json.optBoolean("success")){
//			JSONObject machineJson = json.optJSONObject("data");
//			String machinePlace = machineJson.optString("machinePlace");
//			String machineMAC = machineJson.optString("machineMAC");
			
			String itemName = req.getParameter("itemName");
			if(StringUtils.isNotEmpty(itemName)){
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			String itemCode = req.getParameter("itemCode");
			String businessCode = req.getParameter("businessCode");
			String UseFor = req.getParameter("UseFor");
			if(StringUtils.isEmpty(itemName)){
				itemName = "证照自助查询或打印";
			} else {
				itemName = URLDecoder.decode(itemName, "utf-8");
			}
			if(StringUtils.isEmpty(itemCode)){
				itemCode = "1131000068735121X0231010032700001";
			}
			if(StringUtils.isEmpty(businessCode)){
				businessCode = "001003519500001";
			}
			if(StringUtils.isEmpty(UseFor)){
				UseFor = "自助终端打印";
			} else {
				UseFor = URLDecoder.decode(UseFor, "utf-8");
			}
			
			String sessionId = GbECertUtil.getSessionId(NewElectronicCertificateController.account, 
					NewElectronicCertificateController.password);
			if(StringUtils.isNotEmpty(sessionId)){
				String regex = "\\d{15}|\\d{17}[\\dxX]";
				if(CertificateHolderCode.matches(regex)){
					CertificateHolderCode = GbECertUtil.checkIDCard(sessionId, name, CertificateHolderCode, startDay, endDay, 
							"", "", "", "", "");
				}
				String str = GbECertUtil.retrieval(sessionId, CertificateHolderCode, CertificateType, UseFor, 
						"","","","","");
				try{
					JSONObject result = JSONObject.fromObject(str);
					JSONObject dataJ = JSONObject.fromObject(result.optString("data"));
					JSONArray dataList = dataJ.optJSONObject("data").optJSONArray("dataList");
					JSONArray arr = new JSONArray();
					for(int iLoop = 0;iLoop<dataList.size();iLoop++){
						JSONObject e = dataList.optJSONObject(iLoop);
						String dataSource = e.optString("dataSource");
						String certificateID = e.optString("certificateID");
						String certificateName = e.optString("certificateName");
						String certificateNumber = e.optString("certificateNumber");
						JSONObject obj = new JSONObject();
						if(!"131".equals(dataSource)){
							String certFileStr = GbECertUtil.getCertificateFileByCertificateID(sessionId, certificateID, 
									UseFor, "","","","","");
							String fileUrl = "";
							String fileFormat = "";
							String certificateValidateStart = "";
							String certificateValidateEnd = "";
							try {
								JSONObject certFileJson = JSONObject.fromObject(certFileStr);
								certFileJson = JSONObject.fromObject(certFileJson.optString("data"));
								JSONObject fileHeadJson = certFileJson.optJSONObject("head");
								if(!"0".equals(fileHeadJson.optString("status"))){
									continue;
								}
								JSONArray certFileList = certFileJson.optJSONObject("data").optJSONArray("dataList");
								JSONObject certFile = certFileList.optJSONObject(0);
								fileUrl = certFile.optString("fileUrl");
								fileFormat = certFile.optString("fileFormat");
								
								String certDataStr = GbECertUtil.getCertificateDataByCertificateID(sessionId, certificateID, UseFor, 
										"","","","","");
								JSONObject certDataJson = JSONObject.fromObject(certDataStr);
								certDataJson = JSONObject.fromObject(certDataJson.optString("data"));
								JSONObject dataHeadJson = certDataJson.optJSONObject("head");
								if(!"0".equals(dataHeadJson.optString("status"))){
									continue;
								}
								JSONArray certDataList = JSONObject.fromObject(certDataJson.optString("data")).optJSONArray("dataList");
								JSONObject certData = certDataList.optJSONObject(0);
								certificateValidateStart = certData.optString("certificateValidateStart");
								certificateValidateEnd = certData.optString("certificateValidateEnd");
							} catch (JSONException je) {
								continue;
							} catch (Exception e2) {
								continue;
							}
							
//							byte[] bytes = GbECertUtil.getCertFile(sessionId, fileUrl, fileFormat, machinePlace, machineMAC, itemName, itemCode, businessCode);
//							String base64File = "";
//							if (bytes != null) {
//								base64File = new BASE64Encoder().encode(bytes);
//							}
							obj.put("certName", certificateName);// 证照名称
							obj.put("validStartDay", certificateValidateStart);// 证照有效期起始时间
							obj.put("validEndDay", certificateValidateEnd);// 证照有效期结束时间
							obj.put("certificateType", CertificateType);
							obj.put("certificateNumber", certificateNumber);
							obj.put("url", fileUrl);
							obj.put("fileFormat", fileFormat);
							// 证照缩略图（GIF文件，文件流）
//							obj.put("pictureUrlForBytes", "/selfapi/GBElecCert/getThumbnail.do?certificateType="+
//									CertificateType+"&certificateNumber="+certificateNumber+"&url="+URLEncoder.encode(
//									fileUrl, "utf-8")+"&fileFormat="+fileFormat+"&watermark="+watermark+"&machineId="
//									+machineId+"&type=byte");
//							// 派生证照（PDF文件，文件流）
//							obj.put("derivePictureUrlForBytes", "/selfapi/GBElecCert/getCertFile.do?url="+URLEncoder.
//									encode(fileUrl, "utf-8")+"&fileFormat="+fileFormat+"&machineId="+machineId);
							// 派生证照（PDF文件，base64格式）
//							obj.put("derivePictureUrl", base64File);
							arr.add(obj);
						}
					}
					json.put("data", arr);
				} catch (JSONException je) {
					json.put("success", false);
					json.put("msg", "Illegal JSONObject format");
					json.put("data", "");
				}catch (Exception e) {
					Log.debug(e);
					json.put("success", false);
					json.put("msg", "Inner Server Exception!");
					json.put("data", "");
				}
			} else {
				json.put("success", false);
				json.put("msg", "Login in to Ecert failed!");
				json.put("data", "");
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	private static JSONObject checkMachine(String machineId){
		if(StringUtils.isEmpty(machineId)){
			machineId = "";
		}
		InfopubDeviceInfoDao infopubDeviceInfoDao = new InfopubDeviceInfoDao();
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao.getByMacId(machineId);
		if(deviceInfo == null){
			deviceInfo = DzUtils.getDefaultMachine();
		}
		JSONObject json = new JSONObject();
		if(deviceInfo != null){
			InfopubAreaDao infopubAreaDao = new InfopubAreaDao();
			InfopubArea infopubArea = infopubAreaDao.get(deviceInfo.getStAreaId());
			if(infopubArea != null){
				String machinePlace = "SH00"+infopubArea.getStAreaCode();
				String machineMAC = deviceInfo.getStDeviceMac();
				
				JSONObject machineJson = new JSONObject();
				machineJson.put("machinePlace", machinePlace);
				machineJson.put("machineMAC", machineMAC);
				json.put("success", true);
				json.put("msg", "");
				json.put("data", machineJson);
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
		return json;
	}
}
