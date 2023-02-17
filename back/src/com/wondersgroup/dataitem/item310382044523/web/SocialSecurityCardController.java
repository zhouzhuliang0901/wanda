package com.wondersgroup.dataitem.item310382044523.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item310382044523.util.SignatureUtils;

@Controller
public class SocialSecurityCardController {
	
	private final static String APP_ID = "APP000000035";
	
	private static List<Character> illegalChars = new ArrayList<Character>();
	
	/**
	 * 社保卡信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/socialSecurityCard/getSocialSecurityCardInfo.do")
	public void getSocialSecurityCardInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String zjhm = req.getParameter("zjhm");
		String zjlx = req.getParameter("zjlx");
		String sbkh = req.getParameter("sbkh");
		zjhm = zjhm == null ? "" : zjhm;
		zjlx = zjlx == null ? "" : zjlx;
		sbkh = sbkh == null ? "" : sbkh;
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "251f8270-d4cd-4eca-857f-1cf49b189fb0";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c2629630-1539-4651-bfe2-90f271c1ba3b";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		String method = "card.info.query";
		Map<String ,String> body = new HashMap<String, String>();
		body.put("zjhm", zjhm);
		body.put("zjlx", zjlx);
		body.put("sbkh", sbkh);
		
		String requestXml = creatXML(method,zjhm,body);
		Log.debug("===社保卡信息查询入参XML===>"+requestXml);
		
		// product
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCR15kWM1nTIeCc9eToJ12RhGs6Kim4CIoV5Mmr/rhMi64Q5bMWaH9t+bLa5SYVg1qRNKZP6Z5lDJg8DRR3eHWJusiqI9VlErjcQMoTSGG/TM7keoX4/9teAmqDG+HVaLHrDtCKCfwe6+SXsfSOONn2wuSEmXN1OC8/cyozsI8NvBtN7tvP5/2OnxvHc9Kak6a7A4Q7nj8ilFWvY5dUyFIzxEMIEWZAmMa36Vtze5etU55MninuItipkjv8Shl8J7Q4ENIw04SrwTJBQWc3x46G7ww3U8w5nBqmNObDT8w/lcn8GaceIrxwbCFvCwsx9v6puJuhEdtwxn4Wwcn/cLszAgMBAAECggEBAIWZLahzo+5QE1VVv7qXDRTXa+6dXxgx4mTJ76B6N1QPhIYAFT0Kvuj8m0YAMhH1AQsRYuTDipDgp7gi90O1+l4P1/Bb4Qi0zNkfFL3zSW+XqdMNFhsSLl8A8Zdm/EqlnI5KROie2vFsgDwiDXgXRjvRLsr1UxnnPQjvs5IkQ/d8nDxjQFFwHVRnpYa0rfmizTUWyUTTIBnl1r/O8LudFF+g1U3z6aN6hQZ3ikC+vnhJyLY2hrgmXO3O0Sb/w5/oc+Srt97dy+Jle6gObeA7Tk3U/8RdFSSUIg4nJsuLdoCRXHs5vNvQwKorXKhk9dcNKHUq+59mUy3m3cltcpGyYHkCgYEA2YCayxCmoflDMm/NXP4EU1desDd0oO2fKeQHWmZkUXZXNriMtyvRhmMP/KL09ukbAMzvFaj7knNZheY/By89tuVKRaSZehYcebHEolKfsrD20zByB4H7u/TU7T1615C3HA0CWKf6FQRRCBpnpq76yjFOOJcVJ9GjqiEboEmYKy0CgYEAq6fzZdGcWiCL1BAIT5c+Tpet/qE+pjTUfNYr28yj9QTatuvdDncFI7KRr7r7t5jFuJ+LelN8JwTDLHGF5gSyD7kGEw8NnBLAvZpL9RTxjm+lJCXdtx2yacdM6Ob9dgYnZolKlk+EunZRhf3RIitlCaKjOZozrncbpf6mVniG+98CgYBt6hbPre93hT7XGzbd2qSW9V0tkijHNq4rfkfVAE+L91Dln2fFHUi6MA+hLTy8FlsGEYp1GLthbBd+mjeZyt7WMZNSFHdXAhuLyDFFkpmPtOxXF3FYp/BuMLD13w7YJxLSAyT/Zovz2QWWIbnluyGT0kzkPj17rO6XiDmsy+QxVQKBgDoTd7ECKxD/I+/agMVU2VrDzLVynnyjMSUPXQVfhjVWsBzlTgQf1phOSSYYcCOcx4CAUjNP3Zhh7xqxSekZu5jAM8Ls++DLI3TCqGJB9LwJH+lL+miC3qMVL/s4c5aj3F6/ooGaY8CmWUypJEGYRvXslabeeg/T8oHF10c4PgsnAoGANFyfnxeM1JtgrNAgjv8zoVamBX41u/LwQOaC1e8fZ++KaWsM+ExnirVYaXlOUhmxzSftBOAH1L0MvVCsYFM0xC8eL0/8vug0czBrRKEqNfQ4jgHoIdU7/ztq2Bdcry0wsPz8dhwNYaCie6w84bszS3JLBlWrtwEaZCEqP2pKX/c=";
		// test
//		String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfJn9MIfOs8APkPpiRyb4pvh1vTDXjSLS8iMOCjD2v6ZDyDHvD286R5FeW1zTufdPZQBHDHQO47Gc3gVxOOYUX0pkWZohcjXLzVJ3AEtS0evlL9CqFynft6KVzzwq5cpt3cwrFcflCGf9Ya6K9UX3vAHo57R6btdZ8Q8uQ9HyL0SibsotlfvgTUl4LOU2jAr9Y8s1VuxMhfys24A3bjqLets7YLWL5vKiLvro9Z2486YRbUJoexNyF2ZQjmJylMyEvK6JH6Cq7nFTPOKaMWOcKkJODdDqLsLTVPFzBPB8Kn154BdNC9OEi2UTGYTU6/xF1PZAYwqnYtqzB2cLbYsfPAgMBAAECggEAdmxxALeq2J5oZuhlIuZZiDodMPomhw9M3ZVu1a+BH2rPHi1H0v+2gDzHKEjO7D/Y6amRDfyDDpFqZJTvmI2YiZd9rntxMF1YkdP+5jOc7wcqhegT1CZKg08//iKruj6bjJCgDTe34AX0SVkeaX1AFh3m1T+qLPdruF/CFAS4wFgdsX/9IlBM27H+5xUAvVzn8Pnqk09inPp6sOLRyuZn+Zsw3cgjTmQLgJpmHGCUR34zwBL5c+U4mjeNlg2mb6mBso2sMDPYOP7ZdM56fUmdfPunTCv9nykmTNq/IePVex2TClLFWWRBph7OX6Ec5nflk0gcrCOGqfS8DRcSFvDpUQKBgQDmg7VkmZ7fkYXxpGJmzpru/yFdkrYBZbhIzHgEK/wgTUhhYljF7iNRlKd+mTe50fnF5QrSBuSIXsBwC0faA//v/32gJLJNIW5/P0/q4of1NCYSAMNO0BYp+8bfSuzE0cwz/sY1ge6lH3efctbcCECtJBNWdpmTnpiU7DAQIWaaGwKBgQCwvvVAvPWHXgNXWGdWx6ak7VeLThdhUJ39+qq21AjiDAiwSuT0cpv3DWbi00LB2IC1lAtV48lgAlm42+sFcG6OuPrhBiNf3EPuuEdQFIH3W2Gh9tYJStQfU/UIj8aYHqqAg7I0j/7bzByXK9XgVaYrkClKe7OAw6i+knEPSWMkXQKBgB+ASD2jMK7kuCU5jaj2+v1GKE0dS3oNaI2qQ4xuZloTtZx5UCBcVr9DTVBjp9Xg9/0vO7dP8dMg/7NQF1WXzlYB2C0WVrdrpioFgOpcYrADnGBBcm9GyICcQ6UoHAsZkIpaesO9Kduu3S4RB4OyLQR8NZhPRNWDit5vGHJb3iWZAoGAJnfpeUWnSuczYvdixoh9xLfDrhY5EH0zZewA6/oKVOi5m2Uxn+Kx/3tzQTsp9HbizKKENRV25uacEJgM9woZhgbM0906JcsTHgoNu2g7QVMqVM4cl7kpogfr9QkV+FX0VjRTvQz6M2yhbGWmD0WU/TZLRmLWFth/Cbtl0CXw5iECgYAdlvISi3azz6PVV+B1eRFAYDM+JBFEajwyq3WVFQ7OF3nw7j18NeMsGwN3T8Z1aqFYZPPCmr+hQyfqpjFvTt51KlMK9Ig1wvFQ8H9Xp5XOldtjSF7JBCDo5t3M2BUFGVB2NAghyls0NpUO+eYTbXvLwjChbk4y+H6HZVUNqZgVmA==";
		String sign = SignatureUtils.genSign(requestXml,privateKey,"SHA256");
		
		requestXml = "<![CDATA["+requestXml+"]]>";
		
		String paramStr = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.framework.wondersgroup.com\">"
					    + "<soapenv:Header/>"
					    + "<soapenv:Body>"
					        + "<web:service soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
					            + "<requestXml xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+requestXml+"</requestXml>"
					            + "<signType xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">SHA256</signType>"
					            + "<sign xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+sign+"</sign>"
					        + "</web:service>"
					    + "</soapenv:Body>"
					    + "</soapenv:Envelope>";
		
		String result = HttpUtil.doPost(head,paramStr,"text/xml");
		String responseXML = "";
		String responseSign = "";
		if(result.startsWith("<?xml")){
			try {
				Document document = DocumentHelper.parseText(result);
				Element root = document.getRootElement();
				Element Body = root.element("Body");
				Element serviceResponse = Body.element("serviceResponse");
				Element serviceReturn = serviceResponse.element("serviceReturn"); 
				List<Element> list = serviceReturn.elements();
				responseXML = list.get(0).getText();
				responseSign = list.get(2).getText();
			} catch (Exception e) {
				Log.debug(e.getMessage());
			}
		}
		if(StringUtils.isEmpty(responseXML) || StringUtils.isEmpty(responseSign)){
			result = "";
		} else {
			try {
				Document document = DocumentHelper.parseText(responseXML);
				result = parseXml(document);
				Log.debug("===社保卡信息查询反参XML解析结果===>"+result);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		}
	}
	
	/**
	 * 社保功能状态变动--激活
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/socialSecurityCard/changeCardActivated.do")
	public void changeCardActivated(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String shbzhm = req.getParameter("shbzhm");
		String sbkh = req.getParameter("sbkh");
		String yhkh = req.getParameter("yhkh");
		
		String bgyxq = req.getParameter("bgyxq");
		String zxyy = req.getParameter("zxyy");
		String kplzzt = req.getParameter("kplzzt");
		
		String dbr_xm = req.getParameter("dbr_xm");
		String dbr_lxdh = req.getParameter("dbr_lxdh");
		String dbr_zjlx = req.getParameter("dbr_zjlx");
		String dbr_zjhm = req.getParameter("dbr_zjhm");
//		String dbr_ysqrgx = req.getParameter("dbr_ysqrgx");// 默认父母
		String bz = req.getParameter("bz");
		
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
//		String ywdjh = APP_ID+sdf1.format(now)+new Random().nextInt(1000)+new Random().nextInt(1000);
		String ywdjh = UUID.randomUUID().toString().replace("-", "").trim();
		String bgrq = sdf1.format(now);
		String bglx = "1";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "251f8270-d4cd-4eca-857f-1cf49b189fb0";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c2629630-1539-4651-bfe2-90f271c1ba3b";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		
		String method = "card.status.change";
		Map<String ,String> body = new HashMap<String, String>();
		body.put("ywdjh", ywdjh);
		body.put("shbzhm", shbzhm);
		body.put("sbkh", sbkh);
		body.put("yhkh", yhkh);
		body.put("bgrq", bgrq);
		body.put("bglx", bglx);
		body.put("bgyxq", bgyxq == null ? "" : bgyxq);
		body.put("zxyy", zxyy == null ? "" : zxyy);
		body.put("kplzzt", kplzzt == null ? "" : kplzzt);
		body.put("dbr_xm", dbr_xm == null ? "" : dbr_xm);
		body.put("dbr_lxdh", dbr_lxdh == null ? "" : dbr_lxdh);
		body.put("dbr_zjlx", dbr_zjlx == null ? "" : dbr_zjlx);
		body.put("dbr_zjhm", dbr_zjhm == null ? "" : dbr_zjhm);
		body.put("dbr_ysqrgx", "5");// 5
		body.put("bz", bz == null ? "" : bz);
		
		String requestXml = creatXML(method,shbzhm,body);
		Log.debug("===社保功能状态变动--激活入参XML===>"+requestXml);
		
		// product
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCR15kWM1nTIeCc9eToJ12RhGs6Kim4CIoV5Mmr/rhMi64Q5bMWaH9t+bLa5SYVg1qRNKZP6Z5lDJg8DRR3eHWJusiqI9VlErjcQMoTSGG/TM7keoX4/9teAmqDG+HVaLHrDtCKCfwe6+SXsfSOONn2wuSEmXN1OC8/cyozsI8NvBtN7tvP5/2OnxvHc9Kak6a7A4Q7nj8ilFWvY5dUyFIzxEMIEWZAmMa36Vtze5etU55MninuItipkjv8Shl8J7Q4ENIw04SrwTJBQWc3x46G7ww3U8w5nBqmNObDT8w/lcn8GaceIrxwbCFvCwsx9v6puJuhEdtwxn4Wwcn/cLszAgMBAAECggEBAIWZLahzo+5QE1VVv7qXDRTXa+6dXxgx4mTJ76B6N1QPhIYAFT0Kvuj8m0YAMhH1AQsRYuTDipDgp7gi90O1+l4P1/Bb4Qi0zNkfFL3zSW+XqdMNFhsSLl8A8Zdm/EqlnI5KROie2vFsgDwiDXgXRjvRLsr1UxnnPQjvs5IkQ/d8nDxjQFFwHVRnpYa0rfmizTUWyUTTIBnl1r/O8LudFF+g1U3z6aN6hQZ3ikC+vnhJyLY2hrgmXO3O0Sb/w5/oc+Srt97dy+Jle6gObeA7Tk3U/8RdFSSUIg4nJsuLdoCRXHs5vNvQwKorXKhk9dcNKHUq+59mUy3m3cltcpGyYHkCgYEA2YCayxCmoflDMm/NXP4EU1desDd0oO2fKeQHWmZkUXZXNriMtyvRhmMP/KL09ukbAMzvFaj7knNZheY/By89tuVKRaSZehYcebHEolKfsrD20zByB4H7u/TU7T1615C3HA0CWKf6FQRRCBpnpq76yjFOOJcVJ9GjqiEboEmYKy0CgYEAq6fzZdGcWiCL1BAIT5c+Tpet/qE+pjTUfNYr28yj9QTatuvdDncFI7KRr7r7t5jFuJ+LelN8JwTDLHGF5gSyD7kGEw8NnBLAvZpL9RTxjm+lJCXdtx2yacdM6Ob9dgYnZolKlk+EunZRhf3RIitlCaKjOZozrncbpf6mVniG+98CgYBt6hbPre93hT7XGzbd2qSW9V0tkijHNq4rfkfVAE+L91Dln2fFHUi6MA+hLTy8FlsGEYp1GLthbBd+mjeZyt7WMZNSFHdXAhuLyDFFkpmPtOxXF3FYp/BuMLD13w7YJxLSAyT/Zovz2QWWIbnluyGT0kzkPj17rO6XiDmsy+QxVQKBgDoTd7ECKxD/I+/agMVU2VrDzLVynnyjMSUPXQVfhjVWsBzlTgQf1phOSSYYcCOcx4CAUjNP3Zhh7xqxSekZu5jAM8Ls++DLI3TCqGJB9LwJH+lL+miC3qMVL/s4c5aj3F6/ooGaY8CmWUypJEGYRvXslabeeg/T8oHF10c4PgsnAoGANFyfnxeM1JtgrNAgjv8zoVamBX41u/LwQOaC1e8fZ++KaWsM+ExnirVYaXlOUhmxzSftBOAH1L0MvVCsYFM0xC8eL0/8vug0czBrRKEqNfQ4jgHoIdU7/ztq2Bdcry0wsPz8dhwNYaCie6w84bszS3JLBlWrtwEaZCEqP2pKX/c=";
		// test
//		String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfJn9MIfOs8APkPpiRyb4pvh1vTDXjSLS8iMOCjD2v6ZDyDHvD286R5FeW1zTufdPZQBHDHQO47Gc3gVxOOYUX0pkWZohcjXLzVJ3AEtS0evlL9CqFynft6KVzzwq5cpt3cwrFcflCGf9Ya6K9UX3vAHo57R6btdZ8Q8uQ9HyL0SibsotlfvgTUl4LOU2jAr9Y8s1VuxMhfys24A3bjqLets7YLWL5vKiLvro9Z2486YRbUJoexNyF2ZQjmJylMyEvK6JH6Cq7nFTPOKaMWOcKkJODdDqLsLTVPFzBPB8Kn154BdNC9OEi2UTGYTU6/xF1PZAYwqnYtqzB2cLbYsfPAgMBAAECggEAdmxxALeq2J5oZuhlIuZZiDodMPomhw9M3ZVu1a+BH2rPHi1H0v+2gDzHKEjO7D/Y6amRDfyDDpFqZJTvmI2YiZd9rntxMF1YkdP+5jOc7wcqhegT1CZKg08//iKruj6bjJCgDTe34AX0SVkeaX1AFh3m1T+qLPdruF/CFAS4wFgdsX/9IlBM27H+5xUAvVzn8Pnqk09inPp6sOLRyuZn+Zsw3cgjTmQLgJpmHGCUR34zwBL5c+U4mjeNlg2mb6mBso2sMDPYOP7ZdM56fUmdfPunTCv9nykmTNq/IePVex2TClLFWWRBph7OX6Ec5nflk0gcrCOGqfS8DRcSFvDpUQKBgQDmg7VkmZ7fkYXxpGJmzpru/yFdkrYBZbhIzHgEK/wgTUhhYljF7iNRlKd+mTe50fnF5QrSBuSIXsBwC0faA//v/32gJLJNIW5/P0/q4of1NCYSAMNO0BYp+8bfSuzE0cwz/sY1ge6lH3efctbcCECtJBNWdpmTnpiU7DAQIWaaGwKBgQCwvvVAvPWHXgNXWGdWx6ak7VeLThdhUJ39+qq21AjiDAiwSuT0cpv3DWbi00LB2IC1lAtV48lgAlm42+sFcG6OuPrhBiNf3EPuuEdQFIH3W2Gh9tYJStQfU/UIj8aYHqqAg7I0j/7bzByXK9XgVaYrkClKe7OAw6i+knEPSWMkXQKBgB+ASD2jMK7kuCU5jaj2+v1GKE0dS3oNaI2qQ4xuZloTtZx5UCBcVr9DTVBjp9Xg9/0vO7dP8dMg/7NQF1WXzlYB2C0WVrdrpioFgOpcYrADnGBBcm9GyICcQ6UoHAsZkIpaesO9Kduu3S4RB4OyLQR8NZhPRNWDit5vGHJb3iWZAoGAJnfpeUWnSuczYvdixoh9xLfDrhY5EH0zZewA6/oKVOi5m2Uxn+Kx/3tzQTsp9HbizKKENRV25uacEJgM9woZhgbM0906JcsTHgoNu2g7QVMqVM4cl7kpogfr9QkV+FX0VjRTvQz6M2yhbGWmD0WU/TZLRmLWFth/Cbtl0CXw5iECgYAdlvISi3azz6PVV+B1eRFAYDM+JBFEajwyq3WVFQ7OF3nw7j18NeMsGwN3T8Z1aqFYZPPCmr+hQyfqpjFvTt51KlMK9Ig1wvFQ8H9Xp5XOldtjSF7JBCDo5t3M2BUFGVB2NAghyls0NpUO+eYTbXvLwjChbk4y+H6HZVUNqZgVmA==";
		String sign = SignatureUtils.genSign(requestXml,privateKey,"SHA256");
		
		requestXml = "<![CDATA["+requestXml+"]]>";
		
		String paramStr = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.framework.wondersgroup.com\">"
					    + "<soapenv:Header/>"
					    + "<soapenv:Body>"
					        + "<web:service soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
					            + "<requestXml xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+requestXml+"</requestXml>"
					            + "<signType xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">SHA256</signType>"
					            + "<sign xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+sign+"</sign>"
					        + "</web:service>"
					    + "</soapenv:Body>"
					    + "</soapenv:Envelope>";
		
		String result = HttpUtil.doPost(head,paramStr,"text/xml");
		String responseXML = "";
		String responseSign = "";
		if(result.startsWith("<?xml")){
			try {
				Document document = DocumentHelper.parseText(result);
				Element root = document.getRootElement();
				Element Body = root.element("Body");
				Element serviceResponse = Body.element("serviceResponse");
				Element serviceReturn = serviceResponse.element("serviceReturn"); 
				List<Element> list = serviceReturn.elements();
				responseXML = list.get(0).getText();
				responseSign = list.get(2).getText();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isEmpty(responseXML) || StringUtils.isEmpty(responseSign)){
			result = "";
		} else {
			try {
				Document document = DocumentHelper.parseText(responseXML);
				result = parseXml(document);
				Log.debug("===社保卡信息查询反参XML解析结果===>"+result);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		}
	}
	
	private static String creatXML(String method, String object_dm,Map<String ,String> map){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/HHmmss/");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("utf-8");
		Element Req = document.addElement("msg");
		Element head = Req.addElement("header").addAttribute("desc", "报文头");
		Element body = Req.addElement("body").addAttribute("desc", "报文体");
		
		head.addElement("app_id").addAttribute("desc", "应用ID").setText(APP_ID);
		head.addElement("method").addAttribute("desc", "接口名称").setText(method);
		head.addElement("timestamp").addAttribute("desc", "交易发起时间").setText(sdf.format(now));
		head.addElement("version").addAttribute("desc", "版本号").setText("1.0");
		head.addElement("object_dm").addAttribute("desc", "业务对象代码").setText(object_dm);
		head.addElement("channel").addAttribute("desc", "渠道").setText("01");
		
		Element channel_msg = head.addElement("channel_msg").addAttribute("desc", "渠道信息");
//		bochannel_msgdy.addElement("brand").addAttribute("desc", "当前手机品牌").setText("自助终端");
//		bochannel_msgdy.addElement("type").addAttribute("desc", "当前手机型号").setText("万达工作台");
//		bochannel_msgdy.addElement("os_version").addAttribute("desc", "系统版本").setText("Windows 10");
//		bochannel_msgdy.addElement("longitude").addAttribute("desc", "坐标经度").setText("121.465987");
//		bochannel_msgdy.addElement("latitude").addAttribute("desc", "坐标纬度").setText("31.302575");
//		bochannel_msgdy.addElement("ip").addAttribute("desc", "当前手机入网ip").setText("172.16.125.53");
		channel_msg.addElement("trml_id").addAttribute("desc", "终端设备唯一ID").setText("‎FA163E665DCD");
		channel_msg.addElement("ip").addAttribute("desc", "自助机入网IP").setText("10.81.16.56");
		channel_msg.addElement("other").addAttribute("desc", "其他备注").setText("");
		head.addElement("jylsh").addAttribute("desc", "交易流水号").setText(APP_ID+sdf1.format(now)+new Random().nextInt(1000)+new Random().nextInt(1000));
		
		// body标签写入业务参数
		Set<Entry<String, String>> set = map.entrySet();
		for(Entry<String, String> e : set){
			body.addElement(e.getKey()).setText(e.getValue() == null ? "" : e.getValue());
		}
		
		System.out.println("社保卡开通接口请求报文的业务XML："+document.asXML());
//		byte[] message = null;
		String xmlStr = document.asXML();
//		try {
//			message = document.asXML().getBytes("UTF-8");
//			xmlStr = Base64Util.encode(message);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return xmlStr;
	}
	
	private static String parseXml(Document document){
		Element root = document.getRootElement();
		Element xmlBody = root.element("body");
		Element xmlHead = root.element("header");
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
		
		return json.toString();
	}
	
	public static void main(String[] args) {
		
		String method = "card.info.query";
		Map<String ,String> body = new HashMap<String, String>();
		body.put("zjhm", "123");
		body.put("zjlx", "212");
		body.put("sbkh", "");
		
		String requestXml = creatXML(method,"123",body);
		
		StringBuilder builder = new StringBuilder();
        char[] chars = requestXml.toCharArray();
        for (char c : chars) {
            if (illegalChars.contains(c)) {
                builder.append("<![CDATA[" + c + "]]>");
            } else {
                builder.append(c);
            }
        }
        System.out.println(builder.toString());
		
	}
}
