package com.wondersgroup.dataitem.item310100010323.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

@Controller
public class MedicalEnquiryController {
	
	/**
	 * 上海医疗服务信息查询-医师执业资格信息、护士执业资格信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/medicalEnquiry/queryDocAndNurse.do")
	public void queryDocAndNurse(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String certCode = req.getParameter("certCode");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
//		name = "张露白";
//		certCode = "110310114002694";
//		name = "王洪凤";
//		certCode = "200931018085";
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			if("doc".equals(code)){
				appName = "7d4c48bd-735d-483d-8ce2-c05a8d1b184a";
			} else if("nurse".equals(code)){
				appName = "e2d8e65d-1720-47da-8b2d-18356da7fd5e";
			}
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			if("doc".equals(code)){
				appName = "cd6db81a-6670-4edc-9b98-22f8b37f80fa";
			} else if("nurse".equals(code)){
				appName = "91df5582-c7f1-4612-a036-283d51a63c81";
			}
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String paramStr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.webservice/\">"
					   +" <soapenv:Header/>"
					   +" <soapenv:Body>"
					      +" <ser:queryDoc>"
					         +" <code>64a68ca39fd440b9ad024d5ebb524f8b</code>"
					         +" <doctorName>"+name+"</doctorName>"
					         +" <doctorWorkCode>"+certCode+"</doctorWorkCode>"
					      +" </ser:queryDoc>"
					   +" </soapenv:Body>"
					+" </soapenv:Envelope>";
		if("nurse".equals(code)){
			paramStr = paramStr.replaceAll("queryDoc", "queryNurse").replaceAll("doctorName", "userName").replaceAll("doctorWorkCode", "nurseCertCode");
		}
		System.out.println("上海医疗服务信息查询参数："+paramStr);

		String result = HttpUtil.doPost(head,paramStr,"text/xml");
		System.out.println("上海医疗服务信息查询结果："+result);
		
		JSONObject obj = new JSONObject();
		Document document;
		try {
			document = DocumentHelper.parseText(result);
			org.json.JSONObject json = XML.toJSONObject(document.asXML());
			org.json.JSONObject response = null;
			if("doc".equals(code)){
				response = json.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns2:queryDocResponse");
			} else if("nurse".equals(code)){
				response = json.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns2:queryNurseResponse");
			}
			System.out.println(response);
			obj.put("success", true);
			obj.put("msg", "");
			obj.put("data", response.toString());
		} catch (DocumentException e) {
			obj.put("success", false);
			obj.put("msg", "系统异常，请联系管理员！");
			obj.put("data", "");
			Log.debug(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
}
