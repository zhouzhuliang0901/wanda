package com.wondersgroup.dataitem.item236212030335.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item236212030335.utils.AESUtil;
import com.wondersgroup.dataitem.item236212030335.utils.ResidenceLicenseUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import wfc.service.config.Config;
import wfc.service.log.Log;

@Controller
public class ResidenceLicenseController {
	
	public static String token ;
	public static String rsaPublicKey;
	public static String AESKey;
	public static String randomStr;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	/**
	 * 居住证签注（卡面信息擦写）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/residenceLicense/updateResidenceLicenseInfo.do")
	public void updateResidenceLicenseInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String cardId = req.getParameter("cardId");
		// 登记号
		String regcode = req.getParameter("regcode");
		String packTime = sdf.format(new Date());
		
		JSONObject payloadData = new JSONObject();
		payloadData.put("cardId", cardId);
		payloadData.put("regcode", regcode);
//		payloadData.put("applattice", RdConfig.get("reindeer.residence.license.applattice"));
		
		byte[] encrypybyts = AESUtil.encrypt(payloadData.toString(), AESKey);
		String payloadDataStr = AESUtil.parseByte2HexStr(encrypybyts);
		
		String sign = getSign(packTime, payloadDataStr);
		JSONObject reqHeader = new JSONObject();
		reqHeader.put("sign", sign);
		reqHeader.put("token", token);
		reqHeader.put("appId", RdConfig.get("reindeer.residence.license.appId"));
		reqHeader.put("userId", RdConfig.get("reindeer.residence.license.userId"));
//		byte[] ip = DigestUtils.md5(deviceId);
//		deviceId = Base64Util.encode(ip);
		reqHeader.put("deviceId", "");
		reqHeader.put("packTime", packTime);
		reqHeader.put("orgId", RdConfig.get("reindeer.residence.license.orgId"));
		
		String body = "{}";
        CloseableHttpClient client = HttpClients.createDefault();
        
//        String urlStr = "http://10.102.86.73:9001/dp/svc";
        String urlStr = RdConfig.get("reindeer.residence.license.url")+"/svc/card/autoCardRenew";
        HttpPost post = new HttpPost(urlStr);
        
        JSONObject param = new JSONObject();
        param.put("payloadData",payloadDataStr);
        param.put("reqHeader",reqHeader);
//        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//		formParams.add(new BasicNameValuePair("method", "autoCardRenew"));
//		formParams.add(new BasicNameValuePair("data", param.toString()));
//		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
//		post.setEntity(urlEncodedFormEntity);
		
        StringEntity se = new StringEntity(param.toString(),"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8"));
        post.setEntity(se);
        
        CloseableHttpResponse response;
        try{
            response = client.execute(post);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
            // 释放链接
            response.close();
        } catch (Exception e) {
      	  Log.debug(e);
      	  //Log.debug("访问失败");
	    }
        System.out.println(body);
        
        JSONObject jsonResult = ResidenceLicenseUtil.dealResult(body);
        JSONObject respHeader = jsonResult.optJSONObject("respHeader");
        String resultCode = respHeader.optString("resultCode");
        JSONObject obj = new JSONObject();
        if(respHeader == null || StringUtils.isEmpty(resultCode)){
    	    obj.put("SUCCESS", false);
            obj.put("resultMessage", "接口异常，请联系管理员");
            obj.put("respData", "");
         } else{
            String resultMessage = respHeader.optString("resultMessage");
            if("0000".equals(resultCode) || "5101".equals(resultCode)){
                String respData = jsonResult.optString("payloadData");
                JSONObject respDataJson = JSONObject.fromObject(respData);
                String validdate = respDataJson.optString("validdate");
                String[] validdateArr = validdate.split("—");
                SimpleDateFormat sdfCN = new SimpleDateFormat("yyyy年MM月dd");
                SimpleDateFormat sdfEN = new SimpleDateFormat("yyyy-MM-dd");
                Date validdateStart = null;
                Date validdateEnd = null;
                try {
					validdateStart = sdfCN.parse(validdateArr[0]);
					validdateEnd = sdfCN.parse(validdateArr[1]);
				} catch (ParseException e) {
					Log.debug("日期格式转换失败");
				}
                respDataJson.put("validdateStart",sdfEN.format(validdateStart));
                respDataJson.put("validdateEnd",sdfEN.format(validdateEnd));
                obj.put("SUCCESS", true);
                obj.put("resultMessage", resultMessage);
                obj.put("respData", respDataJson.toString());
            } else {
                obj.put("SUCCESS", true);
                obj.put("resultMessage", resultMessage);
                obj.put("respData", "");
            }
        }
        AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 居住登记业务状态查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/residenceLicense/residenceRegistrationCheck.do")
	public void residenceRegistrationCheck(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		String packTime = sdf.format(new Date());
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		name = "翁守权";
		idCard = "341126199306127311";
		
		JSONObject payloadData = new JSONObject();
		payloadData.put("chname", name);
		payloadData.put("chidcard", idCard);
		
		byte[] encrypybyts = AESUtil.encrypt(payloadData.toString(), AESKey);
		String payloadDataStr = AESUtil.parseByte2HexStr(encrypybyts);
		
		String sign = getSign(packTime, payloadDataStr);
		JSONObject reqHeader = new JSONObject();
		reqHeader.put("userId", RdConfig.get("reindeer.residence.license.userId"));
		reqHeader.put("sign", sign);
		reqHeader.put("deviceId", "");
		reqHeader.put("packTime", packTime);
		reqHeader.put("appId", RdConfig.get("reindeer.residence.license.appId"));
		reqHeader.put("orgId", RdConfig.get("reindeer.residence.license.orgId"));
		
        JSONObject param = new JSONObject();
        param.put("payloadData",payloadDataStr);
        param.put("reqHeader",reqHeader);
        
        String appName = "";
        if("test".equals(Config.get("reindeer.huidao.environment"))){
        	appName = "821bafd3-0f7c-409c-a0db-3169461d927a";
        } else if("product".equals(Config.get("reindeer.huidao.environment"))){
//        	appName = "fb18a1cb-1f8e-46e0-865d-cad4e15e4f12";
        }
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param.toString(),contentType);
		JSONObject resultJson = ResidenceLicenseUtil.dealResult(result);
		AciJsonHelper.writeJsonPResponse(req, res, resultJson.toString());
	}
	
	/**
	 * 照片材料信息上传
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/residenceLicense/imageUpload.do")
	public void imageUpload(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String businessId = req.getParameter("businessId");
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		// 材料文件，压缩后的base64
		String image = req.getParameter("image");
		// 材料类型
		String imageType = req.getParameter("imageType");
		String packTime = sdf.format(new Date());
		
		JSONObject identityInfoVO = new JSONObject();
		identityInfoVO.put("chidcard", idCard);
		identityInfoVO.put("chname", name);
		JSONObject jzzImageVO = new JSONObject();
		jzzImageVO.put("image", image);
		jzzImageVO.put("imageType", imageType);
		JSONObject payloadData = new JSONObject();
		payloadData.put("identityInfoVO", identityInfoVO);
		payloadData.put("jzzImageVO", jzzImageVO);
		payloadData.put("businessId", businessId);
		
		byte[] encrypybyts = AESUtil.encrypt(payloadData.toString(), AESKey);
		String payloadDataStr = AESUtil.parseByte2HexStr(encrypybyts);
		
		String sign = getSign(packTime, payloadDataStr);
		JSONObject reqHeader = new JSONObject();
		reqHeader.put("userId", RdConfig.get("reindeer.residence.license.userId"));
		reqHeader.put("sign", sign);
		reqHeader.put("deviceId", "");
		reqHeader.put("packTime", packTime);
		reqHeader.put("appId", RdConfig.get("reindeer.residence.license.appId"));
		reqHeader.put("orgId", RdConfig.get("reindeer.residence.license.orgId"));
		
        JSONObject param = new JSONObject();
        param.put("payloadData",payloadDataStr);
        param.put("reqHeader",reqHeader);
        
        String appName = "";
        if("test".equals(Config.get("reindeer.huidao.environment"))){
        	appName = "a731a23b-0a62-455d-8c4f-2d1c56f0ca80";
        } else if("product".equals(Config.get("reindeer.huidao.environment"))){
//        	appName = "51998234-90b2-444a-869a-1ffe5a35b398";
        }
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param.toString(),contentType);
		JSONObject resultJson = ResidenceLicenseUtil.dealResult(result);
		AciJsonHelper.writeJsonPResponse(req, res, resultJson.toString());
	}
	
	/**
	 * 居住登记
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void residenceRegistration(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		
	}
	
	private String getSign(String packTime, String payloadDataStr){
//		String sign = packTime+".000"+"|"+userId+"|"+payloadData.toString();
		// 新sign内容
		String sign = packTime+"|"+token+"|"+RdConfig.get("reindeer.residence.license.userId")+
				"|"+RdConfig.get("reindeer.residence.license.appId")+"|"+payloadDataStr;
		
		try {
			System.out.println("加密前的sign："+sign);
			String originString = Base64Util.encode(sign.getBytes("UTF-8"));
			sign = DigestUtils.md5Hex(originString);
			System.out.println("加密后的sign："+sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}
	
	@PostConstruct
	public void init() {
//		ResidenceLicenseUtil.update();
	}
}
