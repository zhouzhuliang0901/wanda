package com.wondersgroup.dataitem.item236212030333.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class ResidencePermitPointsController {
	
	/**
	 * 居住证积分查询接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/residenceLicense/queryResidenceLicenseScore.do")
	public void queryResidenceLicenseScore(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String idCard = req.getParameter("idCard");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1b9cecaa-495d-4f74-a8c6-06fa294471ee";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1b9cecaa-495d-4f74-a8c6-06fa294471ee";
		}
		
		String pid = Base64.encodeBase64String(idCard.getBytes("utf-8"));
		String APIKEY = "SpicJzzjfQueryPKEY_NDg1OTYyNDAxQTRDRkQ2QjMxMjI0RjUwQzI3QkIzQkE=";
		String sign = DigestUtils.md5Hex(idCard+APIKEY);
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?pid="+pid+"&sign="+sign;
	    Map<String, String> paramMap = new HashMap<String, String>();
	    paramMap.put("pid", pid);
	    paramMap.put("sign", sign);

	    HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
	    String result = HttpUtil.doPost(head, reqEntity);
		byte[] bytes = Base64.decodeBase64(result);
		result = new String(bytes,"utf-8");
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
