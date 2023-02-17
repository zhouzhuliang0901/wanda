package com.wondersgroup.dataitem.item199773732923.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PooledHttpUitl;

import wfc.service.config.Config;
import wfc.service.log.Log;

/**
 * 三金查询
 * @author xb
 *
 */
@Controller
public class SJInfoQueryController {
	
	/**
	 * 个人住房公积金帐户基本信息查询接口
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("/selfapi/SJInfoQuery/queryAccumulationFund.do")
	public void queryAccumulationFund(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String name = req.getParameter("name");
		String identNo = req.getParameter("identNo");
		String type = req.getParameter("type");
		// GJ0001Q1：帐户基本信息查询，GJ0001Q2：基本帐户明细查询，GJ0001Q3：补充帐户明细查询
		String code = req.getParameter("code");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "c9b4adc4-f0f5-4945-89f9-41af79a0e5e6";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "7d41ea66-29de-405e-8582-ccd16f64c07f";
		}
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        String xmlStr = creatXML(code,name,identNo,type);
        byte flage = 0;
        String paramStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sqsw=\"http://sqsw.channels.xxgx.shgjj/\">"
	        	+ "<soapenv:Header/>"
	            + "<soapenv:Body>"
	            	+ "<sqsw:serviceNew>"
	                  + "<flag>"+flage+"</flag>"
	                  + "<message>"+xmlStr+"</message>"
	                + "</sqsw:serviceNew>"
	           + "</soapenv:Body>"
	         + "</soapenv:Envelope>";
        StringEntity se = new StringEntity(paramStr,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "text/xml"));
        String result = HttpUtil.doPost(head, se);
        
        String resultXml = "";
        if(result.indexOf("<return>") != -1){
        	 String[] arrStr = result.split("<return>");
             String base64XmlResult = arrStr[1].split("</return>")[0];
             byte[] resultByte = Base64Util.decode(base64XmlResult);
             resultXml = new String(resultByte,"UTF-8");
             try {
             	resultXml = resultXml.substring(1, resultXml.length());
     			Document document = DocumentHelper.parseText(resultXml);
     			result = parseXml(document,type);
     		} catch (DocumentException e) {
     			e.printStackTrace();
     		}
        } else {
        	result = "";
        }
        
        res.setHeader("Access-Control-Allow-Origin", "*");
        AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 医保金查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/SJInfoQuery/queryMedicalInsuranceFund.do")
	public void queryMedicalInsuranceFund(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		String userName = req.getParameter("userName");
		if(StringUtils.isNotEmpty(userName)){
			userName = URLDecoder.decode(userName, "utf-8");
		}
		String appName = "817b41d2-efa7-42a8-8c25-28933a8b4058";
		
		 String signature = HttpUtil.getSignature(appName);
	     Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	     
	     String param = "{username:\""+idCard+"\",xm:\""+userName+"\"}";
	     param = URLEncoder.encode(param, "utf-8");
	     
	     String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
	    		 +"?json="+param;
	     String result = HttpUtil.doGet(head, url, "GET");
	     
	     AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 养老金查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/SJInfoQuery/queryOldAgePension.do")
	public void queryOldAgePension(HttpServletRequest req, 
			HttpServletResponse res) throws IOException {
		String accessToekn = req.getParameter("accessToekn");
		
		String appName = "e035844b-d34f-41aa-8b73-b11fab84dad5";
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
	    Map<String, String> paramTokenMap = new HashMap<String, String>();
	    paramTokenMap.put("access_token", accessToekn);
	    
	    HttpEntity tokenReqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramTokenMap), "utf-8");
	    String result = HttpUtil.doPost(head, tokenReqEntity);
	    
	    String token = "";
	    if(StringUtils.isNotEmpty(result)){
	    	try{
		    	org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(result);
		    	org.jsoup.nodes.Element form = doc.getElementById("access_token");
		    	token = form.attr("value");
		    	Log.debug("养老金返回的token："+token);
	    	} catch (Exception e) {
	    		Log.debug(e.getMessage());
			}
	    }
	    
	    JSONObject obj = new JSONObject();
	    if(StringUtils.isEmpty(token) || token.indexOf("pid") != -1){
	    	obj.put("success", false);
	    	obj.put("msg", "未查询到参保人！");
	    	obj.put("data", "");
	    } else {
	    	appName = "b20bb273-49bf-4c86-9e44-049bc2dfdb0a";
	 		signature = HttpUtil.getSignature(appName);
	 	    head = HttpUtil.setHttpHeard(signature, appName);
	 	    
		    Map<String, String> paramInfoMap = new HashMap<String, String>();
		    paramInfoMap.put("access_token", token);
		    HttpEntity infoReqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramInfoMap), "utf-8");
	 	    result = HttpUtil.doPost(head, infoReqEntity);
	 	    
	 	   JSONObject json = new JSONObject();
	 	    if(StringUtils.isNotEmpty(result)){
	 	    	try{
		 	    	org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(result);
		 	    	org.jsoup.nodes.Element sum1 = doc.getElementById("dataisxxb_sum1");
		 	    	org.jsoup.nodes.Element sum2 = doc.getElementById("dataisxxb_sum2");
		 	    	org.jsoup.nodes.Element sum3 = doc.getElementById("dataisxxb_sum3");
		 	    	org.jsoup.nodes.Element sum4 = doc.getElementById("dataisxxb_sum4");
		 	    	JSONObject sum1Json = getsum1Info(sum1);
		 	    	JSONArray sum2Arr = getsum2Info(sum2);
		 	    	JSONArray sum3Arr = getsum3Info(sum3);
		 	    	JSONObject sum4Json = getsum4Info(sum4);
		 	    	json.put("sum1", sum1Json);
		 	    	json.put("sum2", sum2Arr);
		 	    	json.put("sum3", sum3Arr);
		 	    	json.put("sum4", sum4Json);
	 	    	} catch (Exception e) {
	 		    	obj.put("success", false);
	 		    	obj.put("msg", "养老金接口返回数据异常！");
	 		    	obj.put("data", "");
				}
	 	    }
	    	obj.put("success", true);
	    	obj.put("msg", "");
	    	obj.put("data", json);
	    }
	    AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	private static JSONObject getsum1Info(org.jsoup.nodes.Element sum1){
		JSONObject obj = new JSONObject();
    	Elements xm = sum1.getElementsByTag("xm");
    	Elements zjhm = sum1.getElementsByTag("zjhm");
    	Elements jsjs1 = sum1.getElementsByTag("jsjs1");
    	obj.put("userName", xm.text());
    	obj.put("idCard", zjhm.text());
    	obj.put("workingYears", jsjs1.text());
    	Log.debug("基本信息："+obj.toString());
		return obj;
	}
	private static JSONArray getsum2Info(org.jsoup.nodes.Element sum2){
		JSONArray arr = new JSONArray();
		Elements jsjs = sum2.getElementsByTag("jsjs");
		ListIterator<org.jsoup.nodes.Element> it = jsjs.listIterator();
		while(it.hasNext()){
			org.jsoup.nodes.Element e = it.next();
			JSONObject json = new JSONObject();
			Elements jsjs1 = e.getElementsByTag("jsjs1");
			String year = jsjs1.text();
			Elements jsjs3 = e.getElementsByTag("jsjs3");
			String baseNum = jsjs3.text();
			Elements jsjs4 = e.getElementsByTag("jsjs4");
			String ylNum = jsjs4.text();
			Elements jsjs6 = e.getElementsByTag("jsjs6");
			String ybNum = jsjs6.text();
			Elements jsjs8 = e.getElementsByTag("jsjs8");
			String syNum = jsjs8.text();
			json.put("year", year);
			json.put("baseNum", baseNum);
			json.put("ylNum", ylNum);
			json.put("ybNum", ybNum);
			json.put("syNum", syNum);
			arr.add(json);
		}
		Log.debug("近期应缴情况："+arr.toString());
		return arr;
	}
	private static JSONArray getsum3Info(org.jsoup.nodes.Element sum3){
		JSONArray arr = new JSONArray();
		Elements jsjs = sum3.getElementsByTag("jsjs");
		ListIterator<org.jsoup.nodes.Element> it = jsjs.listIterator();
		while(it.hasNext()){
			org.jsoup.nodes.Element e = it.next();
			JSONObject json = new JSONObject();
			Elements jsjs1 = e.getElementsByTag("jsjs1");
			String year = jsjs1.text();
			Elements jsjs2 = e.getElementsByTag("jsjs2");
			String baseNum = jsjs2.text();
			Elements jsjs3 = e.getElementsByTag("jsjs3");
			String personal = jsjs3.text();
			json.put("year", year);
			json.put("baseNum", baseNum);
			json.put("personal", personal);
			arr.add(json);
		}
		Log.debug("养老保险实缴记录："+arr.toString());
		return arr;
	}
	private static JSONObject getsum4Info(org.jsoup.nodes.Element sum4){
		JSONObject obj = new JSONObject();
    	Elements jsjs1 = sum4.getElementsByTag("jsjs1");
    	Elements jsjs2 = sum4.getElementsByTag("jsjs2");
    	Elements jsjs3 = sum4.getElementsByTag("jsjs3");
    	Elements jsjs4 = sum4.getElementsByTag("jsjs3");
    	obj.put("year", jsjs1.text());
    	obj.put("monthCount", jsjs2.text());
    	obj.put("sumNum", jsjs3.text());
    	obj.put("personal", jsjs4.text());
    	Log.debug("累计缴费信息："+obj.toString());
		return obj;
	}
	
	@SuppressWarnings("unused")
	private static String queryAccumulationFundInfo(
			Map<String, String> head, byte param0, String param1) {
		String body = "";
		
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		String url = Config.get("reindeer.huidao.url."+Config.get("reindeer.huidao.environment"));
        HttpPost httpPost = new HttpPost(Config.get("reindeer.huidao.url."+Config.get("reindeer.huidao.environment")));
		
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
        }
        
        String paramStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sqsw=\"http://sqsw.channels.xxgx.shgjj/\">"
			        	+ "<soapenv:Header/>"
			            + "<soapenv:Body>"
			            	+ "<sqsw:serviceNew>"
			                  + "<flag>"+param0+"</flag>"
			                  + "<message>"+param1+"</message>"
			                + "</sqsw:serviceNew>"
			           + "</soapenv:Body>"
			         + "</soapenv:Envelope>";
        
        StringEntity se = new StringEntity(paramStr,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "text/xml"));
        httpPost.setEntity(se);
        
        CloseableHttpResponse response = null;
        try{
//        	if(client == null){
//        		if(url.startsWith("https://")){
//	    			client= HttpUtil.getHttpsClient();
//	    		} else {
//	    			client = HttpClients.createDefault();
//	    		}
//        	}
        	response = client.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
            // 释放链接
            response.close();
        } catch (Exception e) {
        	Log.debug(e.getMessage());
        	Log.debug("访问失败");
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
        System.out.println(body);
		return body;
	}
	
	private static String creatXML(String code, String name, String identNo, String type){
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("utf-8");
		Element Req = document.addElement("msg");
		Element head = Req.addElement("head");
		head.addElement("src");
		head.addElement("dest");
		Element body = Req.addElement("body");
		head.addElement("rst");
		
		head.addElement("version").setText("1.0");
		head.addElement("code").setText(code);//接口代码
		head.addElement("affairno").setText(UUID.randomUUID().toString());
		
		Date date = new Date();
		Calendar year = Calendar.getInstance();
		Calendar halfYear = Calendar.getInstance();
		year.setTime(new Date());
		year.add(Calendar.YEAR, -1);
		halfYear.setTime(new Date());
		halfYear.add(Calendar.MONTH, -6);
        Date yearDate = year.getTime();
        Date halfYearDate = halfYear.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
		head.addElement("time").setText(sdf.format(date));
		
		body.addElement("accept_channel").setText("04");
		body.addElement("person_name").setText(name);
		body.addElement("cetf_id").setText(identNo);
		if("GJ0001Q1".equals(code)){
			body.addElement("info_type").setText("0");
		} else if("GJ0001Q2".equals(code)){
			body.addElement("info_type").setText("1");
			if("year".equals(type)){
				body.addElement("start_date").setText(sdfDate.format(yearDate));
			} else {
				body.addElement("start_date").setText(sdfDate.format(halfYearDate));
			}
			body.addElement("end_date").setText(sdfDate.format(date));
			body.addElement("pageno").setText("1");
		} else if("GJ0001Q3".equals(code)){
			body.addElement("info_type").setText("2");
			if("year".equals(type)){
				body.addElement("start_date").setText(sdfDate.format(yearDate));
			} else {
				body.addElement("start_date").setText(sdfDate.format(halfYearDate));
			}
			body.addElement("end_date").setText(sdfDate.format(date));
			body.addElement("pageno").setText("1");
		}

		System.out.println(document.asXML());
		byte[] message = null;
		String xmlStr = "";
		try {
			message = document.asXML().getBytes("UTF-8");
			xmlStr = Base64Util.encode(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return xmlStr;
	}
	
	private static String parseXml(Document document, String type){
		Element root = document.getRootElement();
		Element xmlBody = root.element("body");
		Element xmlHead = root.element("head");
		List<Element> bodyList = xmlBody.elements();
		List<Element> headList = xmlHead.elements();
		JSONObject json = new JSONObject();
		JSONObject bodyJson = new JSONObject();
		JSONObject headJson = new JSONObject();
		for (Iterator<Element> itBody = bodyList.iterator(); itBody.hasNext();) {
			Element e = (Element) itBody.next();
			String xmlName = e.getName();
			if("datas".equals(xmlName)){
				List<Element> datasList = e.elements();
				JSONArray datasArr = new JSONArray();
				for (Iterator<Element> itDatas = datasList.iterator(); itDatas.hasNext();) {
					Element datas = (Element) itDatas.next();
					List<Element> dataList = datas.elements();
					JSONObject dataJson = new JSONObject();
					for(Iterator<Element> itData = dataList.iterator(); itData.hasNext();){
						Element data = (Element) itData.next();
						String dataName = data.getName();
						String dataValue = data.getText();
						dataJson.put(dataName, dataValue);
					}
					datasArr.add(dataJson);
				}
				bodyJson.put(xmlName, datasArr);
			} else {
				String value = e.getText();
				bodyJson.put(xmlName, value);
			}
			
		}
		for (Iterator<Element> ithead = headList.iterator(); ithead.hasNext();) {
			Element e = (Element) ithead.next();
			String xmlName = e.getName();
			if("rst".equals(xmlName)){
				List<Element> rstList = e.elements();
				JSONObject rstJson = new JSONObject();
				for(Iterator<Element> itRst = rstList.iterator(); itRst.hasNext();){
					Element rst = (Element) itRst.next();
					String rstName = rst.getName();
					String rstValue = rst.getText();
					rstJson.put(rstName, rstValue);
				}
				headJson.put(xmlName, rstJson);
			} else {
				String value = e.getText();
				headJson.put(xmlName, value);
			}
		}
		json.put("head",headJson);
		json.put("body",bodyJson);
		
		Date date = new Date();
		Calendar year = Calendar.getInstance();
		Calendar halfYear = Calendar.getInstance();
		year.setTime(new Date());
		year.add(Calendar.YEAR, -1);
		halfYear.setTime(new Date());
		halfYear.add(Calendar.MONTH, -6);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		if("year".equals(type)){
			json.put("start_date",sdfDate.format(year.getTime()));
		} else if("halfYear".equals(type)){
			json.put("start_date",sdfDate.format(halfYear.getTime()));
		}
		json.put("end_date",sdfDate.format(date));
		
		return json.toString();
	}
}
