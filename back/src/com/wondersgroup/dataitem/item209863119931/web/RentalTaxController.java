package com.wondersgroup.dataitem.item209863119931.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class RentalTaxController {
	
	/**
	 * 个人房屋出租税收代征点查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/rentalTax/SubstituteChargingOfRentalTax.do")
	public void SubstituteChargingOfRentalTax(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String txtUnitName = req.getParameter("txtUnitName");// 单位
		String txtZSD = req.getParameter("txtZSD");// 征收点名称
		String txtZSDaddress = req.getParameter("txtZSDaddress");// 征收点地址
		if(StringUtils.isNotEmpty(txtUnitName)){
			txtUnitName = URLDecoder.decode(txtUnitName, "utf-8");
		} else {
			txtUnitName= "";
		}
		if(StringUtils.isNotEmpty(txtZSD)){
			txtZSD = URLDecoder.decode(txtZSD, "utf-8");
		} else {
			txtZSD= "";
		}
		if(StringUtils.isNotEmpty(txtZSDaddress)){
			txtZSDaddress = URLDecoder.decode(txtZSDaddress, "utf-8");
		} else {
			txtZSDaddress= "";
		}
		
		String appName = "8856ff10-21b6-11ec-9963-8523ae84ab01";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?txtUnitName="+txtUnitName+"&txtZSD="+txtZSD+"&txtZSDaddress="+txtZSDaddress;
		String str = HttpUtil.doGet(head, url, null);
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
