package com.wondersgroup.dataitem.item288953360923.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item288953360923.util.AESUtil;

import wfc.service.log.Log;

@Controller
public class TobaccoController {

	/**
	 * 许可基本信息查询
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/tobacco/getBookingLicenceinfo.do")
	public void getBookingLicenceinfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		// 02：变更 03：补办 04：停业 05：恢复营业 06：歇业 07：延续
		String applytype = req.getParameter("applytype");
		// idCard = "341181198209046229";
		// applytype = "04";
		String appName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "1d2d9fc0-9771-11eb-a375-476157e7d2a8";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			appName = "4bf07220-0642-11ec-bbfc-cb132a2116fd";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("SOAPAction", "");
		JSONObject data = new JSONObject();
		data.put("idno", idCard);
		data.put("applytype", applytype);
		data.put("usertype", "chinasoftxzsp");

		String param = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.webservice.tf.com\">\n"
				+ "   <soapenv:Header/>\n"
				+ "   <soapenv:Body>\n"
				+ "      <ser:getBookingLicenceinfo soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n"
				+ "         <paramStr xsi:type=\"soapenc:string\" xs:type=\"type:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xs=\"http://www.w3.org/2000/XMLSchema-instance\">"
				+ data.toString()
				+ "</paramStr>\n"
				+ "      </ser:getBookingLicenceinfo>\n"
				+ "   </soapenv:Body>\n" + "</soapenv:Envelope>\n";

		String contentType = "text/xml;charset=utf-8";
		String result = HttpUtil.doPost(head, param, contentType);

		Document document;
		try {
			document = DocumentHelper.parseText(result);
			Element root = document.getRootElement();
			Element Body = root.element("Body");
			Element Response = Body.element("getBookingLicenceinfoResponse");
			Element Return = Response.element("getBookingLicenceinfoReturn");
			String encrypted = Return.getText();
			result = AESUtil.decryptPro(
					encrypted,
					RdConfig.get("reindeer.tobacco.aes.key."
							+ RdConfig.get("reindeer.huidao.environment")));
		} catch (DocumentException e) {
			result = "";
			Log.debug("查询结果XML格式异常！");
		} catch (Exception e) {
			result = "";
			e.printStackTrace();
		}

		System.out.println("烟草专卖许可信息查询结果：" + result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
