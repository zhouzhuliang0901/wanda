package com.wondersgroup.dataitem.item310750019000.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item310750019000.utils.CipherUtil;
import com.wondersgroup.dataitem.item310750019000.utils.handleXml;
import com.wondersgroup.dataitem.item310750019000.bean.Message;
import com.wondersgroup.dataitem.item310750019000.utils.CallUtil;
import com.wondersgroup.dataitem.item310750019000.utils.RsaUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class UnemploymentRegistrationController {
	
	//code参数为接口编码； 如办理失业登记查询接口(LD0009Q3)
	@PostMapping("/selfapi/UnemploymentRegistration/{code}")
	public void mzReserveDateQueryNew(@PathVariable String code, HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		
		Message request = new Message();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
        
        Date now = new Date();
        
        String date = sdf1.format(now);
        String time = sdf2.format(now);
        
    	String appName = "";
		
    	//构造平台校验请求头
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "8f129830-7594-4c50-906b-31bf705b677f";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "0124ceb7-52be-4319-8b9e-70c237e45bca";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String json =  req.getParameter("json");
		System.out.println(json);
		if(StringUtils.isNotEmpty(json)){
			json = URLDecoder.decode(json, "utf-8");
		}
		
		String bodyXml = handleXml.buildBody(json,code);
		System.out.println("请求体"+bodyXml);
	     
        //报文体加密
        if("test".equals(RdConfig.get("reindeer.huidao.environment"))){	
        	//测试环境-加密-测试
        	String headXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            		"<head><version>1.0.0</version><ref>00002014032715394308</ref><sysCode>O99999</sysCode><busCode>W0002001" +
            		"</busCode><tradeSrc>O</tradeSrc><sender>O99997</sender><reciver>O99999</reciver><reSnd>N</reSnd><date>"+date+"</date><time>"+time+"</time><rst><tradeCode>99</tradeCode><busiCode>9999</busiCode><info></info></rst></head>";
        	System.out.println("请求头"+headXml);
        	request.headbytes = headXml.getBytes(CallUtil.ENCODING);
        	
        	request.bodybytes = RsaUtil.encrypt256(CallUtil.rs_publicKey, bodyXml.getBytes(CallUtil.ENCODING));
        	request.signbytes = RsaUtil.sign256(CallUtil.dsj_sign_privateKey, request.headbytes, request.bodybytes);
        	
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
	        String headXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
	        		"<head><version>1.0.0</version><ref>00002014032715394308</ref><sysCode>O00003</sysCode><busCode>W0002001" +
	        		"</busCode><tradeSrc>O</tradeSrc><sender>O00004</sender><reciver>O00003</reciver><reSnd>N</reSnd><date>"+date+"</date><time>"+time+"</time><rst><tradeCode>99</tradeCode><busiCode>9999</busiCode><info></info></rst></head>";
	        request.headbytes = headXml.getBytes(CallUtil.ENCODING);
	        System.out.println("请求头"+headXml);
	        // 云签服务-加密-生产
	        request.bodybytes =  Base64.decodeBase64(CipherUtil.encryptData(bodyXml));
	        // 云签服务-签名
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        if(request.headbytes != null && request.bodybytes != null){
	            baos.write(request.headbytes);
	            baos.write(request.bodybytes);
	        }
			String signData = Base64.encodeBase64String(baos.toByteArray());
	        request.signbytes = Base64.decodeBase64(CipherUtil.signData(signData));
		}
        
        request.flag = 0x01;
        request.headLen = (short) request.headbytes.length;
        request.bodyLen = request.bodybytes.length;
        System.out.println("签名长度："+request.signbytes.length);
        Message response = CallUtil.callWithHttp(request, head, "text/plain;charset=utf-8");
        //正式返回
        String verifyResult = "";
        //测试返回
        String decryptResult = "";
        if (request.flag == 0x01 && response.bodybytes != null && response.headbytes != null && response.signbytes != null){
        	if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
        		// 人社demo-验签-测试
        		boolean b= RsaUtil.verify256(CallUtil.rs_publicKey, response.signbytes, response.headbytes, response.bodybytes);
        		System.out.println("验签结果:" + b);
        		
    		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
    			// 云签服务-验签-生产
            	ByteArrayOutputStream bs = new ByteArrayOutputStream();
                if(response.headbytes != null && response.bodybytes != null){
                	bs.write(response.headbytes);
                	bs.write(response.bodybytes);
                }
                String verifyDate = Base64.encodeBase64String(bs.toByteArray());
                verifyResult = CipherUtil.verifySignData(verifyDate, Base64.encodeBase64String(response.signbytes));
                verifyResult = CipherUtil.dealResult(verifyResult,"signDataResponse","SignData");
                System.out.println("验签结果:" + verifyResult);
    		}
        }
        if (request.flag == 0x01 && response.bodybytes != null){
        	if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
        		// 人社demo-解密-测试
    			decryptResult = new String(RsaUtil.decrypt256(CallUtil.dsj_enc_privateKey, response.bodybytes),CallUtil.ENCODING);
    			System.out.println("报文头:" + new String(response.headbytes,CallUtil.ENCODING));
    			System.out.println("报文体:" + decryptResult);
    			decryptResult = handleXml.xmlToJson(decryptResult);
    			decryptResult = longToString(decryptResult);
				AciJsonHelper.writeJsonPResponse(req, res, decryptResult);           
    		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
//    			 云签服务-解密-生产
            	decryptResult = CipherUtil.decryptData(Base64.encodeBase64String(response.bodybytes));
            	decryptResult = StringEscapeUtils.unescapeXml(decryptResult);
            	decryptResult = CipherUtil.dealResult(decryptResult,"decryptByPrivateKeyResponse","OutData");
            	System.out.println("报文体:" + decryptResult);
            	System.out.println("报文头:" + new String(response.headbytes,CallUtil.ENCODING));
              net.sf.json.JSONObject resultObj = dealResponse(verifyResult,decryptResult);
              if(resultObj.containsKey("data")){
            	  String resultString = resultObj.getString("data");
            	  resultString = longToString(resultString);
            	  System.out.println(resultString);
            	  AciJsonHelper.writeJsonPResponse(req, res, resultString);
              }else {
            	  String resultString1 = resultObj.toString();
            	  AciJsonHelper.writeJsonPResponse(req, res, resultString1);
              }
    		}
        }
	}
	/**
	 * 生产环境CA云签返回结果处理响应
	 * @param verifyResult
	 * @param decryptResult
	 * @return
	 */
	public net.sf.json.JSONObject dealResponse(String verifyResult, String decryptResult){
        net.sf.json.JSONObject resultObj = new net.sf.json.JSONObject();
        net.sf.json.JSONObject verifyObj = net.sf.json.JSONObject.fromObject(verifyResult);
        net.sf.json.JSONObject dataObj = net.sf.json.JSONObject.fromObject(decryptResult);
        String verifyCode = verifyObj.optString("RetCode");
        String dataCode = dataObj.optString("RetCode");
        if(StringUtils.isEmpty(verifyCode) || !"1".equals(verifyCode)){
        	resultObj.put("success", false);
        	resultObj.put("msg", "验签失败！");
        	resultObj.put("data", "");
        } else {
        	if(StringUtils.isEmpty(dataCode) || !"1".equals(dataCode)){
            	resultObj.put("success", false);
            	resultObj.put("msg", "返回报文体解密失败！");
            	resultObj.put("data", "");
        	} else {
            	try{
            		resultObj.put("success", true);
                	resultObj.put("msg", "");
                	resultObj.put("data", dataObj.optJSONObject("Response").optJSONObject("OutData"));
            	} catch (Exception e) {
            		resultObj.put("success", false);
                	resultObj.put("msg", "返回报文体内容格式异常！");
                	resultObj.put("data", "");
				}

        	}
        }
        return resultObj;
	}

	public static String longToString(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		if(jsonObject != null&&jsonObject.containsKey("msg")){
			JSONObject msg = jsonObject.getJSONObject("msg");
			if(msg != null&&msg.containsKey("body")) {
				JSONObject body = msg.getJSONObject("body");
				if(body != null && body.containsKey("yhzh")) {
					String yhkh = body.getString("yhzh");
					body.put("yhzh", yhkh);
					msg.put("body", body);
					jsonObject.put("msg",msg);
					return jsonObject.toString();
				}else {
					return result;
				}
			}else {
				return result;
			}
		}else {
			return result;
		}
		
	}
}
