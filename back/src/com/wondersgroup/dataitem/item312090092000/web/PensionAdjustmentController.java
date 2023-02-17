package com.wondersgroup.dataitem.item312090092000.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item312090092000.bean.Message;
import com.wondersgroup.dataitem.item312090092000.utils.CallUtil;
import com.wondersgroup.dataitem.item312090092000.utils.CipherUtil;
import com.wondersgroup.dataitem.item312090092000.utils.RsaUtil;

/**
 * 人社不见面事项相关
 * 养老金卡（折）调整
 * 劳动者个人信息查询及维护
 * @author wanda
 *
 */
@Controller
public class PensionAdjustmentController {
	
	/**
	 * 生产环境，签名、加密、验签、解密通过CA云签服务
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/pensionAdjustment/RSunifiedInterface.do")
	public void RSunifiedInterface(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		// 接口代码
		String code = req.getParameter("code");
//		code = "LD0060S1";
		// 接口业务参数的json格式数据
		String paramStr = req.getParameter("paramStr");
		if(StringUtils.isNotEmpty(paramStr)){
			paramStr = URLDecoder.decode(paramStr, "utf-8");
		}
		// 服务类型：社保服务-sb；劳动服务-ld
		String serverType= req.getParameter("serverType");
//		serverType = "sb";
		// 事项编码，用于生成同意审批编码
		String appplyNo= req.getParameter("appplyNo");
//		String appplyNo= HttpUtil.getApplyNo("312090092000");
		
		JSONObject param = JSONObject.parseObject(paramStr);
//		JSONObject param = new JSONObject();
//		param.put("zjlx", "01");
//		param.put("zjhm", "310102195912191297");
		
//		param.put("pid", "200008012286250");
//		param.put("yhdm", "13");
//		param.put("ffxs_in", "13");
//		param.put("yhzh", "6222031001033990164");
//		param.put("kzbz", "1");
//		param.put("shzlall", "|001,发放机构变更核定表,1,1|002,《领取养老金方式确认表（个人填写）》,4,0|003,居民身份证正、反面复印件,4,0|004,本人在本市指定金融机构范围内开立的实名制结算账户卡（折）复印件,4,0|005,委托人和受托人居民身份证正、反面复印件,4,0|");
//		param.put("shzlsl", "0");
//		param.put("hdbsl", "1");
//		param.put("tsds", "0");
//		param.put("shzltotal", "5");
//		param.put("hdbbh", "核10表");
//		param.put("czqx", "");
		
		String appName = "0124ceb7-52be-4319-8b9e-70c237e45bca";
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
        Date now = new Date();
        String date = sdf1.format(now);
        String time = sdf2.format(now);
        
        Message request = new Message();
        // 报文头
        String headXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        		"<head><version>1.0.0</version><ref>00002014032715394308</ref><sysCode>O00003</sysCode><busCode>W0002001" +
        		"</busCode><tradeSrc>O</tradeSrc><sender>O00004</sender><reciver>O00003</reciver><reSnd>N</reSnd><date>"+date+"</date><time>"+time+"</time><rst><tradeCode>99</tradeCode><busiCode>9999</busiCode><info></info></rst></head>";
        request.headbytes = headXml.getBytes(CallUtil.ENCODING);
        // 报文体
        String bodyXml = creatBodyXml(code,appplyNo,serverType,param);
        request.bodybytes =  bodyXml.getBytes(CallUtil.ENCODING);
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
        request.flag = 0x01;
        request.headLen = (short) request.headbytes.length;
        request.bodyLen = request.bodybytes.length;
        System.out.println("签名长度："+request.signbytes.length);
        
        Message response = CallUtil.callWithHttp(request, head, "text/plain;charset=utf-8");
        
        String verifyResult = "";
        String decryptResult = "";
        net.sf.json.JSONObject resultObj = new net.sf.json.JSONObject();
        if(response != null){
        	if (request.flag == 0x01 && response.bodybytes != null 
            		&& response.headbytes != null && response.signbytes != null){
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
            if (request.flag == 0x01 && response.bodybytes != null){
            	// 云签服务-解密-生产
            	decryptResult = CipherUtil.decryptData(Base64.encodeBase64String(response.bodybytes));
            	decryptResult = StringEscapeUtils.unescapeXml(decryptResult);
            	decryptResult = CipherUtil.dealResult(decryptResult,"decryptByPrivateKeyResponse","OutData");
            	Log.debug("解密后报文体:"+decryptResult);
            }
            Log.debug("报文头:" + new String(response.headbytes,CallUtil.ENCODING));
            
            resultObj = dealResponse(verifyResult,decryptResult);
            Log.debug("http响应结果："+resultObj.toString());
        }
        AciJsonHelper.writeJsonPResponse(req, res, resultObj.toString());
	}
	
	/**
	 * 测试环境，签名、加密、验签、解密通过人社定义方式
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/pensionAdjustment/RSunifiedInterfaceForTest.do")
	public void RSunifiedInterfaceForTest(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		// 接口代码
		String code = req.getParameter("code");
		// 接口业务参数的json格式数据
		String paramStr = req.getParameter("paramStr");
		if(StringUtils.isNotEmpty(paramStr)){
			paramStr = URLDecoder.decode(paramStr, "utf-8");
		}
		// 服务类型：社保服务-sb；劳动服务-ld
		String serverType= req.getParameter("serverType");
		// 事项编码，用于生成同意审批编码
		String appplyNo= req.getParameter("appplyNo");
//		code = "LD0017Q2";
//		serverType = "ld";
		appplyNo = "004009220003573";
		
		JSONObject param = JSONObject.parseObject(paramStr);
//		JSONObject param = new JSONObject();
//		param.put("cert_type", "01");
//		param.put("cert_id", "429004199312101138");
//		param.put("per_name", "肖邦");
//		param.put("qry_type", "4");
		
		String appName = "8f129830-7594-4c50-906b-31bf705b677f";
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
        Date now = new Date();
        String date = sdf1.format(now);
        String time = sdf2.format(now);
        
        Message request = new Message();
        // 报文头
        String headXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        		"<head><version>1.0.0</version><ref>00002014032715394308</ref><sysCode>O99999</sysCode><busCode>W0002001" +
        		"</busCode><tradeSrc>O</tradeSrc><sender>O99997</sender><reciver>O99999</reciver><reSnd>N</reSnd><date>"+date+"</date><time>"+time+"</time><rst><tradeCode>99</tradeCode><busiCode>9999</busiCode><info></info></rst></head>";
        request.headbytes = headXml.getBytes(CallUtil.ENCODING);
        // 报文体
        String bodyXml = creatBodyXml(code,appplyNo,serverType,param);
        request.bodybytes =  bodyXml.getBytes(CallUtil.ENCODING);
        // 人社demo-加密-测试
        request.bodybytes = RsaUtil.encrypt256(CallUtil.rs_publicKey, bodyXml.getBytes(CallUtil.ENCODING));
        // 人社demo-签名-测试
        request.signbytes = RsaUtil.sign256(CallUtil.dsj_sign_privateKey, request.headbytes, request.bodybytes);
        request.flag = 0x01;
        System.out.println("签名长度："+request.signbytes.length);
        
        Message response = CallUtil.callWithHttp(request, head, "text/plain;charset=utf-8");
        
        String decryptResult = "";
        if (request.flag == 0x01 && response.bodybytes != null 
        		&& response.headbytes != null && response.signbytes != null){
        	// 人社demo-验签-测试
        	boolean b= RsaUtil.verify256(CallUtil.rs_publicKey, response.signbytes, response.headbytes, response.bodybytes);
            System.out.println("验签结果:" + b);
        }
        if (request.flag == 0x01 && response.bodybytes != null){
        	// 人社demo-解密-测试
        	decryptResult = new String(RsaUtil.decrypt256(CallUtil.dsj_enc_privateKey, response.bodybytes),CallUtil.ENCODING);
        	System.out.println("报文体:" + decryptResult);
        	Document document;
			try {
				document = DocumentHelper.parseText(decryptResult);
				org.json.JSONObject parseJSON = XML.toJSONObject(document.asXML());
				org.json.JSONObject data = parseJSON.optJSONObject("data");
				String xmlStr = data.optString("response");
				Document xml = DocumentHelper.parseText(xmlStr);
				data.put("response", XML.toJSONObject(xml.asXML()));
				decryptResult = parseJSON.toString();
			} catch (DocumentException e) {
				decryptResult = "";
				Log.debug(e);
			}
        }
        System.out.println("报文头:" + new String(response.headbytes,CallUtil.ENCODING));
        res.setHeader("Access-Control-Allow-Origin", "*");
        
        AciJsonHelper.writeJsonPResponse(req, res, decryptResult);
	}
	
	/**
	 * 办件信息同步到一网通办办件库
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/pensionAdjustment/saveItemInfo.do")
	public void saveItemInfo(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String userId = req.getParameter("userId");
		String itemCode = req.getParameter("itemCode");
		String itemName = req.getParameter("itemName");
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "UTF-8");
		}
		String username = req.getParameter("username");
		if(StringUtils.isNotEmpty(username)){
			username = URLDecoder.decode(username, "UTF-8");
		}
		String mobile = req.getParameter("mobile");
		String idCardNo = req.getParameter("idCardNo");
		// 结果 1：成功；0：失败
		String result = req.getParameter("result");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "14b03be5-5a94-4531-82d2-7022318fdbf9";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "bf4e7eb6-3ee7-477b-9bfb-7c67bc9dc9dc";
		}
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    JSONObject json = new JSONObject();
	    json.put("userId",userId);
	    json.put("itemCode",itemCode);
	    json.put("itemName",itemName);
	    json.put("username",username);
	    json.put("mobile",mobile);
	    json.put("idCardNo",idCardNo);
	    json.put("result",result);
	    json.put("queryTime",sdf.format(new Date()));
	    json.put("source", "6");
	    
		String contentType = "application/json;charset=utf-8";
		String str = HttpUtil.doPost(head,json.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 劳动者个人信息-个人基本信息查询--公安数据
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/pensionAdjustment/queryBasicInfoForPer.do")
	public void queryBasicInfoForPer(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String appName = "26f64785-6009-49ee-9533-64c5e3418e2e";
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    JSONObject json = new JSONObject();
	    json.put("name",name);
	    json.put("idCard",idCard);
	    
		String contentType = "application/json;charset=utf-8";
		String str = HttpUtil.doPost(head,json.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 劳动者个人信息-个人学历信息--学信网数据
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/pensionAdjustment/queryEducationalInfoForPer.do")
	public void queryEducationalInfoForPer(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String appName = "92d019b7-9586-4f86-83c7-6e1d5a67840f";
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    head.put("AppKey", "588309377908211712");
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+
//				"?xm="+name+"&zjhm="+idCard+"&reqid=s_1200000500000_2193&ywtype=xl_gxpt_nLFQkaqg74tjoyIEl2O2yAK&oc=xl_gxpt_pYxTBs1dSJGTYygDWOkIWoc";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("xm", name);
		paramMap.put("zjhm", idCard);
		paramMap.put("reqid", "s_1200000500000_2193");
		paramMap.put("ywtype", "xl_gxpt_nLFQkaqg74tjoyIEl2O2yAK");
		paramMap.put("oc", "xl_gxpt_pYxTBs1dSJGTYygDWOkIWoc");
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String str = HttpUtil.doPost(head, reqEntity);
		
		try{
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(str);
			String xmlStr = json.optString("data");
			org.json.JSONObject objXML = XML.toJSONObject(xmlStr);
			json.put("data", objXML.toString());
			str = json.toString();
		} catch (Exception e) {
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 人社字典-省市三级联动
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/pensionAdjustment/RSDictionary.do")
	public void RSDictionary(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String proId = req.getParameter("proId");
		String cityId = req.getParameter("cityId");
		String streetId = req.getParameter("streetId");
		
		String appName = "";
		String str = "";
		if(StringUtils.isEmpty(proId)
				&& StringUtils.isEmpty(cityId)
				&& StringUtils.isEmpty(streetId)){
			appName = "eea6ee8e-0bdd-468e-97eb-6babaaf5709d";
		} else if(StringUtils.isNotEmpty(proId)
				&& StringUtils.isEmpty(cityId)
				&& StringUtils.isEmpty(streetId)){
			appName = "e349c488-08e1-4cd7-9fa7-3e10a1e6cbb9";
			str = "?proId="+proId;
		} else if(StringUtils.isEmpty(proId)
				&& StringUtils.isNotEmpty(cityId)
				&& StringUtils.isEmpty(streetId)){
			appName = "fcc3d6ac-7c6f-4430-8a0e-1e6ca9725627";
			str = "?cityId="+cityId;
		} else if(StringUtils.isEmpty(proId)
				&& StringUtils.isEmpty(cityId)
				&& StringUtils.isNotEmpty(streetId)){
			appName = "6388da94-408a-4268-bb36-7d17c41ccdc6";
			str = "?streetId="+streetId;
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+str;
	    String result = HttpUtil.doGet(head,url,"GET");
	    AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 生产环境CA云签返回结果处理
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
	
	public String creatBodyXml(String code, String appplyNo, String serverType, JSONObject param){
		StringBuffer buffer = new StringBuffer();
		String s = jsonToXmlstr(param, buffer);
//		String ip = "http://10.8.205.36:9080/cmc-cics-"+serverType;
//		String ip = "http://10.5.102.71:8080/cmc-cics-"+serverType;// 测试
//		String suid = HttpUtil.getApplyNo(itemCode);
        String bodyXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>" + 
//        		"<url>"+ip+"/services/dispatchService</url><flag>0</flag><message><![CDATA[" +
				"<url/><flag>0</flag><subsys>"+serverType+"</subsys><message><![CDATA[" +
        		"<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head>" +
        		"<version>1.0</version><ref>20190925090922TEST0000118165262</ref><code>"+code+"</code><src><code>31011712000001</code><uid>3101171000000256</uid>" +
        		"<uname>蔡洁</uname><uexpinf></uexpinf></src><dest><code>SB</code><uid>0000000003</uid><uname>caij</uname><uexpinf></uexpinf></dest><time>20190925090653</time><ext></ext><sign></sign>" +
        		"<rst><syscode>FF</syscode><buscode></buscode><errmsg></errmsg></rst><affairno>3101171200000120190925000033</affairno><suid>"+appplyNo+"</suid></head>" +//179020119003097
        		"<body>"+s+"</body>" +
        		"</msg>]]></message>" + "</data>";
        System.out.println(bodyXml);
        return bodyXml;
	}
	
	public String jsonToXmlstr(JSONObject jObj, StringBuffer buffer){
		Set<Map.Entry<String, Object>> se = jObj.entrySet();
		for(Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext(); ){
			Map.Entry<String, Object> en = it.next();
			if(en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")){
				buffer.append("<"+en.getKey()+">");
				JSONObject jo = jObj.getJSONObject(en.getKey());
				jsonToXmlstr(jo,buffer);
				buffer.append("</"+en.getKey()+">");
			} else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
				JSONArray jarray = jObj.getJSONArray(en.getKey());
				for (int i = 0; i < jarray.size(); i++) {
					buffer.append("<"+en.getKey()+">");
					JSONObject jsonobject =  jarray.getJSONObject(i);
					jsonToXmlstr(jsonobject,buffer);
					buffer.append("</"+en.getKey()+">");
				}
			} else if (en.getValue().getClass().getName().equals("java.lang.String")){
				buffer.append("<"+en.getKey()+">"+en.getValue());
				buffer.append("</"+en.getKey()+">");
			} else if(en.getValue().getClass().getName().equals("java.lang.Integer")){
				buffer.append("<"+en.getKey()+">"+en.getValue());
				buffer.append("</"+en.getKey()+">");
			}
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
	}
}
