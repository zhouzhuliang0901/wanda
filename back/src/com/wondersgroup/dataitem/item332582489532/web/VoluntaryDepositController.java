package com.wondersgroup.dataitem.item332582489532.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item208443121532.web.AccumulationFundChangeController;

@Controller
public class VoluntaryDepositController {
	
	/**
	 * 自愿缴存户信息变更
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/voluntaryDeposit/infoChange.do")
	public void infoChange(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
//		res.setHeader("Access-Control-Allow-Origin", "*");
		String account = req.getParameter("account");
		String address = req.getParameter("address");
		if(StringUtils.isNotEmpty(address) ){
			address = URLDecoder.decode(address, "utf-8");
		}
		String trace_no = AccumulationFundChangeController.VoucherNo();
		
		String appName = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appName = "88e97e1d-7dcf-4ece-ba06-d93ca9c8a0c8";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appName = "52f8eef7-4663-432f-b386-09d2b8dd57cd";
		}
		
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        String paramStr = "<soapenv:Envelope 	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" <soap:Header 	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
						+" </soap:Header>"
						+" <soapenv:Body>"
						+" <impl:GJGL_YDDZH_ZYJCXX_XG 	xmlns:impl=\"http://impl.ws.web.accumulation.capinfo.com/\">"
						+" <!--Optional:交易类型-->"
						+" <tx_code>01</tx_code>"
						+" <!--Optional:交易流水号-->"
						+" <trace_no>"+trace_no+"</trace_no>"
						+" <!--Optional:公积金账号-->"
						+" <account>"+account+"</account>"
						+" <!--Optional:联系地址-->"
						+" <address>"+address+"</address>"
						+" <!--Optional:来源类型-->"
						+" <source_type>06</source_type>"
						+" </impl:GJGL_YDDZH_ZYJCXX_XG>"
						+" </soapenv:Body>"
						+" </soapenv:Envelope>";
        String contentType = "text/xml";
  		String result = HttpUtil.doPost(head,paramStr,contentType);
  		System.out.println("自愿缴存户信息变更结果："+result);
  		Document document;
  		try{
  			document = DocumentHelper.parseText(result);
  			result = document.asXML();
  			String startStr = result.split("<return>")[1];
  			result = startStr.split("</return>")[0];
  		} catch (Exception e) {
  			Log.debug("返回数据格式异常！");
  			result = "";
  		}
      	AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
