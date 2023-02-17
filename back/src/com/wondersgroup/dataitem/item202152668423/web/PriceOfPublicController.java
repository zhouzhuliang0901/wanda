package com.wondersgroup.dataitem.item202152668423.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class PriceOfPublicController {
	
	/**
	 * 收费项目公示与查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/priceOfPublic/chargingProjectsQuery.do")
	public void chargingProjectsQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 项目类别：1-综合医疗服务类；2-医技诊疗类；3-临床诊疗类；4-中医及民族医诊疗类；S-手术特殊仪器设备；
		// X-临床用血项目；K-口腔修复材料；N-本市新增医疗服务项目；all-全部
		String category = req.getParameter("category");
		String keyWord = req.getParameter("keyWord");
		String minPrice = req.getParameter("minPrice");
		String maxPrice = req.getParameter("maxPrice");
		if(StringUtils.isNotEmpty(keyWord)){
			keyWord = URLDecoder.decode(keyWord, "utf-8");
		}
		
		String appcode = "hYrwKL";
		String sign = "F39B01C3F476D0DCD49D27479FC8BFE6";
		String appName = "2e9dec6f-8d82-493b-867a-265bb13f1797";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("appcode", appcode);
		head.put("sign", sign);
		
		JSONObject json = new JSONObject();
		JSONArray categoryArr = JSONArray.fromObject(category.split(","));
		json.put("category", categoryArr);
		json.put("keyWord", keyWord);
		json.put("minPrice", minPrice);
		json.put("maxPrice", maxPrice);
		json.put("page", 1);
		json.put("pageSize", Integer.MAX_VALUE/2);
		
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("收费项目公示与查询返回结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
