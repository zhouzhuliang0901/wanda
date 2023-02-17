package com.wondersgroup.dataitem.item236212030335.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.druid.util.StringUtils;
import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.RSAUtil;
import com.wondersgroup.dataitem.item236212030335.web.ResidenceLicenseController;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.config.Config;
import wfc.service.log.Log;

public class ResidenceLicenseUtil {
	
	public static String giveMePublicKey(String userId, String deviceId){
        String body = "";
//        String urlStr = RdConfig.get("reindeer.residence.license.url")+"/svc/core/giveMePublicKey";
        String urlStr = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		CloseableHttpClient client = null;
		if(urlStr.startsWith("https://")){
			client= HttpUtil.getHttpsClient();
		} else {
			client = HttpClients.createDefault();
		}
        String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
//			appName = "ca1bf6a7-401f-42e9-b631-0b34aec096de";
			appName = "3085d222-5f88-43ac-88f9-d97921cd48e8";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
//			appName = "81f09434-c049-4d74-95c9-958a3dadfc83";
		}
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        HttpPost post = new HttpPost(urlStr);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            post.addHeader(entry.getKey() , entry.getValue());
        }
        post.addHeader("Content-type", "application/json;charset=utf-8");
        
        JSONObject param = new JSONObject();
        param.put("userId",userId);
//		byte[] ip = DigestUtils.md5(deviceId);
//		deviceId = Base64Util.encode(ip);
        param.put("deviceId","");
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
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(entity, "utf-8");
            }
            response.close();
        } catch (Exception e) {
      	  Log.debug(e);
      	  Log.debug("giveMePublicKey访问失败");
	    }
        System.out.println("giveMePublicKey返回结果："+body);
		return body;
	}
	
	public static String giveMeMainKey(){
		byte[] encryptbyts = null;
		try {
			encryptbyts = RSAUtil.encryptByPublicKey(ResidenceLicenseController.randomStr.getBytes("utf-8"), 
					ResidenceLicenseController.rsaPublicKey);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String encryptedRandStringInBase64 = Base64Util.encode(encryptbyts);
		
		String body = "";
//        String urlStr = RdConfig.get("reindeer.residence.license.url")+"/svc/core/giveMeMainKey";
        String urlStr = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		CloseableHttpClient client = null;
		if(urlStr.startsWith("https://")){
			client= HttpUtil.getHttpsClient();
		} else {
			client = HttpClients.createDefault();
		}
        String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
//			appName = "c11e873d-ca87-4da2-9707-f729be468f60";
			appName = "2290d09c-fd89-4e40-b5c8-bc82eeb9b226";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "2d331537-eb8f-4109-b7cc-3cc13683bc08";
		}
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        HttpPost post = new HttpPost(urlStr);
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            post.addHeader(entry.getKey() , entry.getValue());
        }
        post.addHeader("Content-type", "application/json;charset=utf-8");
        
        JSONObject param = new JSONObject();
        param.put("encryptedRandStringInBase64",encryptedRandStringInBase64);
        param.put("token",ResidenceLicenseController.token);
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
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(entity, "utf-8");
            }
            response.close();
        } catch (Exception e) {
      	  Log.debug(e);
      	  Log.debug("giveMeMainKey访问失败");
	    }
        System.out.println("giveMeMainKey返回结果："+body);
		return body;
	}
	
	public static void update(){
		Log.debug("------初始化居住证签注接口相关信息开始------");
		String tokenData = ResidenceLicenseUtil.giveMePublicKey(RdConfig.get("reindeer.residence.license.userId"),
				RdConfig.get("reindeer.residence.license.deviceId"));
		try{
			JSONObject json = JSONObject.fromObject(tokenData);
			if("0000".equals(json.optString("resultCode"))){
				ResidenceLicenseController.token = json.optJSONObject("data").optString("token");
				ResidenceLicenseController.rsaPublicKey = json.optJSONObject("data").optString("rsaPublicKey");
				Log.debug("初始化居住证签注接口相关信息token："+ResidenceLicenseController.token);
				Log.debug("初始化居住证签注接口相关信息rsaPublicKey："+ResidenceLicenseController.rsaPublicKey);
				ResidenceLicenseController.randomStr = RandomStringUtils.randomAlphanumeric(32);
				Log.debug("初始化居住证签注接口相关信息randomStr："+ResidenceLicenseController.randomStr);
				String AESData = ResidenceLicenseUtil.giveMeMainKey();
				try{
					JSONObject obj = JSONObject.fromObject(AESData);
					if("0000".equals(obj.optString("resultCode"))){
						String data = obj.optString("data");
						byte[] decryptAESkey = AESUtil.decrypt(data, ResidenceLicenseController.randomStr);
						ResidenceLicenseController.AESKey = new String(decryptAESkey);
						Log.debug("初始化居住证签注接口相关信息AESKey："+ ResidenceLicenseController.AESKey);
					} else {
						Log.debug("giveMeMainKey接口请求失败！");
					}
				} catch (Exception e) {
					Log.debug("giveMeMainKey接口异常！");
				}
			} else {
				Log.debug("giveMePublicKey接口请求失败！");
			}
		} catch (Exception e) {
			Log.debug("giveMePublicKey接口异常！");
		}
		Log.debug("------初始化居住证签注接口相关信息结束------");
	}
	
	public static JSONObject dealResult(String result){
		JSONObject json = new JSONObject();
		try{
			json = JSONObject.fromObject(result);
			String payloadData = json.optString("payloadData");
			JSONObject respHeader = json.optJSONObject("respHeader");
			if(respHeader == null){
				respHeader = new JSONObject();
				respHeader.put("resultCode", "");
				respHeader.put("resultMessage", "");
			}
			if(StringUtils.isEmpty(payloadData)){
				payloadData = "";
			} else {
				byte[] decryptAESBytes = AESUtil.decrypt(payloadData, ResidenceLicenseController.randomStr);
				payloadData = new String(decryptAESBytes);
				System.out.println("居住证相关接口返回的业务数据："+payloadData);
			}
			json.put("payloadData", payloadData);
			json.put("respHeader", respHeader);
			return json;
		} catch (Exception e) {}
		return json;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String rsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRigCV6jv4l76FoybHPmmYUC05TaFvSItn4IWnBR+sC8EwKXx7OB+VeqCylDSFMino05FRCbODzN9wX2znyFJuJDbAUHWEyS7sBSHfmvGY/CI95T7Px5UypNFu2uUvCjNZXrkJyU5q58Z78wogI06o/JfUECAFuBepCWFOST5mJwIDAQAB";
////		String randomStr = RandomStringUtils.randomAlphanumeric(32);
//		String randomStr= "JUmk0G81DZUanPxdpr8hr58HIE8Pghkf";
//		System.out.println(randomStr);
//		byte[] encryptbyts = null;
//		try {
//			encryptbyts = RSA.publicEncrypt(randomStr.getBytes("utf-8"), RSA.string2PublicKey(rsaPublicKey));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(encryptbyts.length);
//		String encryptedRandStringInBase64 = Base64Util.encode(encryptbyts);
//		System.out.println("加密的随机串："+encryptedRandStringInBase64);
//		System.out.println(encryptedRandStringInBase64.length());
		
		
		
//		String randomStr = "JUmk0G81DZUanPxdpr8hr58HIE8Pghkf";
//		String enctyptAESkey = "38409691f8dcc9e8e0f0033a608c890b5d48bafd29043d45272399d0f8ddde74";
//		byte[] decryptAESkey = AESUtil.decrypt(enctyptAESkey, randomStr);
//		System.out.println(decryptAESkey.length);
////		String AESKey = AESUtil.parseByte2HexStr(decryptAESkey).toLowerCase();
//		String AESKey = new String(decryptAESkey);
//		System.out.println(AESKey);
//		
//		
//		
//		String token = "8e81d559bc95f27d0260fe154e3608ea";
//		String cardId = "310000D156000005750216308E7BFFE9";
//		String regcode = "310000000170171302";
//		String deviceId = "10.81.16.56";
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		String packTime = sdf.format(new Date());
//		System.out.println(packTime);
//		
//		JSONObject payloadData = new JSONObject();
//		payloadData.put("cardId", cardId);
//		payloadData.put("regcode", regcode);
//		payloadData.put("applattice", "35003391");
//		
//		String payloadDataStr = payloadData.toString();
//		System.out.println("payloadData加密前内容："+payloadDataStr);
//		
//		if (payloadDataStr.length() % 16 != 0){
//            int tem = payloadDataStr.length() % 16;
//            for (int i = 0; i < 16 - tem; i++){
//            	payloadDataStr += " ";
//            }
//        }
//		
//		System.out.println(payloadDataStr.length());
//		byte[] encrypybyts = AESUtil.encrypt(payloadDataStr, AESKey);
//		payloadDataStr = AESUtil.parseByte2HexStr(encrypybyts);
//		
////		String sign = packTime+".000"+"|"+userId+"|"+payloadData.toString();
//		// 新sign内容
//		String sign = packTime+"|"+token+"|wd_1eaacbb0220ce971|266dc77f558c09b3|"+payloadDataStr;
//		
//		try {
//			System.out.println("加密前的sign："+sign);
//			String originString = Base64Util.encode(sign.getBytes("UTF-8"));
//			sign = DigestUtils.md5Hex(originString);
//			System.out.println("加密后的sign："+sign);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		JSONObject reqHeader = new JSONObject();
//		reqHeader.put("sign", sign);
//		reqHeader.put("token", token);
//		reqHeader.put("appId", "266dc77f558c09b3");
//		reqHeader.put("userId", "wd_1eaacbb0220ce971");
//		byte[] ip = DigestUtils.md5(deviceId);
//		deviceId = Base64Util.encode(ip);
//		reqHeader.put("deviceId", "");
//		reqHeader.put("packTime", packTime);
//		
//        
//        JSONObject param = new JSONObject();
//        param.put("payloadData",payloadDataStr);
//        param.put("reqHeader",reqHeader);
//        System.out.println(param.toString());
		
		
		String s = "3a3d1888c66c82715c168baeeef3769424aa1082929dbcd858731397215e5734340c2cbf576b0114362671eccb48b65f34d21d610d81c45e51b48a34a42124ba75aaea48c223d7ecdfdd9964f72a86d9f98b23d1db02aaac86c23a15e17fd7756ce665cae4b011e4628539d795bd6b61d1fd1018010be0146afe068656c695b7cfc976908d311be7e2002ae27b4fc3b7e54b51687b976fd2027adae2b59007c8f6c30ba62e1bd54a30f6ca5ba8a5e55a9aeef8a34273e7f11978ff6dc4dcc17905795788cff22609d60f191a30c449ef";
		String randomStr = "397f34b7b342c2054ef9ff9ab3e51651";
		byte[] decryptAESkey = AESUtil.decrypt(s, randomStr);
//		String AESKey = AESUtil.parseByte2HexStr(decryptAESkey).toLowerCase();
		String AESKey = new String(decryptAESkey);
		System.out.println(AESKey);
	}
}
