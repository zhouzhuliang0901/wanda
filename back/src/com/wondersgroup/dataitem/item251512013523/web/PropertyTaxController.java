package com.wondersgroup.dataitem.item251512013523.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class PropertyTaxController {
	
	/**
	 * 房产税查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/propertyTax/queryPropertyTax.do")
	public void queryPropertyTax(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 房产证件类型:1-房产证;2-不动产证
		String fczjlx = req.getParameter("fczjlx");
		// 证件类型:01-身份证;09-香港;10-澳门;11-台湾;12-港澳通行;13-台胞;14-护照
		String zjlx = req.getParameter("zjlx");
		// 证件号码
		String zjhm = req.getParameter("zjhm");
		// 区县简称
		String qxjc = req.getParameter("qxjc");
		// 年份
		String nf = req.getParameter("nf");
		// 数字编码
		String szbm = req.getParameter("szbm");
		if(StringUtils.isNotEmpty(qxjc)){
			qxjc = URLDecoder.decode(qxjc, "utf-8");
		}
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7b7178c2-de5b-4684-aa7e-9abf477fde18";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7b7178c2-de5b-4684-aa7e-9abf477fde18";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?fczjlx="+fczjlx+"&zjlx="+zjlx+"&zjhm="+zjhm+"&qxjc="+qxjc+"&nf="+nf+"&szbm="+szbm+"&cpage=1&ip=127.0.0.1";
		String result = HttpUtil.doGet(head, url, "GET");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
