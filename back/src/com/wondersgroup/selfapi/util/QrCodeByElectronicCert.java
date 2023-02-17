package com.wondersgroup.selfapi.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.wondersgroup.common.utils.HttpUtil;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

public class QrCodeByElectronicCert {
	
	private static final String CERT_QRCODE_ADDRESS = RdConfig.get("cert.zzk")+"/cert/usage/scanCertQrCode.do";
	
	public static String getQrCodeInfoByElectronicCert(String sessionId, String pos, String use, String certQrCode,
			String machinePlace,String machineMAC,String itemName, String itemCode, String businessCode){
		String body = "";
		
		JSONObject param = new JSONObject();
		param.put("sessionId", sessionId);
		param.put("pos", pos);
		param.put("use", use);
		
		param.put("orgName",machinePlace);
		param.put("username",machineMAC);
		param.put("itemName",itemName);
		param.put("itemCode",itemCode);
		param.put("businessCode",businessCode);
		param.put("certQrCode", certQrCode);
		
//		try {
//			param.put("certQrCode", URLEncoder.encode(certQrCode, "UTF-8"));
//		} catch (UnsupportedEncodingException e1) {
//			param.put("certQrCode", "");
//			e1.printStackTrace();
//		}
		
        CloseableHttpClient client = null;
		String url = RdConfig.get("cert.zzk");
        HttpPost httpPost = new HttpPost(CERT_QRCODE_ADDRESS);
        
        StringEntity se = new StringEntity(param.toString(),"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8"));
        httpPost.setEntity(se);

        // 指定报文头【Content-type】
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        CloseableHttpResponse response = null;
        try{
        	if(client == null){
        		if(url.startsWith("https://")){
        			client= HttpUtil.getHttpsClient();
        		} else {
        			client = HttpClients.createDefault();
        		}
        	}
            response = client.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug(e.getMessage());
        	Log.debug(e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	public static void main(String[] args) {
//		DZCertController dz = new DZCertController();
////		String sessionId = dz.login();
//		String pos = "徐汇";
//		String use = "测试";
//		//String certQrCode = "7FE7F7D4E8FE8BE3258024AC4D7C763E41639BC00D21F2B75FBBCE1FB1ED1577BDFFC7445196038700BBC8F4567A1F57460C5E95B99C0D21A765CB09E951209E%2CmPMb7PdtGADkxg5GAZkzuSapLB12KS0DsJ5NQ%2BPXlYD6vPWzWPDkOxmXM7Y%2B%2B6H6tdL3cnFsfmGXHsRK8sY3JEDgU4M%2Bk%2BnhVtZWuJfVA%2BuVrMTviD73WTN43%2F%2FW6HD6jufR7MsS%2BfK7bHEp90NtV4ZvU3wYESPClKT62qATGYI%3D%2C1568121098508%2C1568098989000";
//		String certQrCode = "952733196688607079";
//		// f6c819b1-cfd5-47ad-b7c9-d57c4095200b
//		String sessionId = "f6c819b1-cfd5-47ad-b7c9-d57c4095200b";
//		String result = getQrCodeInfoByElectronicCert(sessionId, pos, use, certQrCode,"","","","","");
//		System.out.println(result);
//		if(result.startsWith("\r\n\r\n\r\n<html>")){
//			System.out.println("报错了");
//		}
//		JSONObject json = JSONObject.fromObject(result);
//		String certuuid = json.optString("certUuid"); 
//		String str = dz.getCertOriginalData(sessionId,certuuid,"","","","","");
//		System.out.println(str);
	}
}
