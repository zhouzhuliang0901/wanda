package com.wondersgroup.dataitem.item327912644823.web;

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
public class ConsumablesInquiryController {
	
	/**
	 * 耗材基本信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/consumablesInquiry/basicInfoQuery.do")
	public void basicInfoQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 作用科室：01-骨科；02-心血管科；99-其他；all-全部
		String department = req.getParameter("department");
		// 耗材类别：01-手术器材；02-植入型器材；03-血液、体液处理器材；99-其他；all-全部
		String category = req.getParameter("category");
		String keyWord = req.getParameter("keyWord");
		if(StringUtils.isNotEmpty(keyWord)){
			keyWord = URLDecoder.decode(keyWord, "utf-8");
		}
		
		String appcode = "hYrwKL";
		String sign = "081E87CE70E132FF2BA4D108331C38F7";
		String appName = "24a0edf7-2242-4e1c-a81d-9974530c935b";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("appcode", appcode);
		head.put("sign", sign);
		
		JSONObject json = new JSONObject();
		JSONArray departmentArr = JSONArray.fromObject(department.split(","));
		JSONArray categoryArr = JSONArray.fromObject(category.split(","));
		json.put("department", departmentArr);
		json.put("category", categoryArr);
		json.put("keyWord", keyWord);
		json.put("page", 1);
		json.put("pageSize", Integer.MAX_VALUE/2);
		
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("耗材基本信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 耗材详细信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/consumablesInquiry/detailInfoQuery.do")
	public void detailInfoQuery(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String code = req.getParameter("code");
//		code = "C02110110000800";
		
		String appcode = "hYrwKL";
		String sign = "9ED064F721DEDF323B2992FCB7B9FF98";
		String appName = "73a2e13c-d44c-4165-86cb-1605cc4e5144";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("appcode", appcode);
		head.put("sign", sign);
		
		JSONObject json = new JSONObject();
		json.put("xmbm", code);
		
		String result = HttpUtil.doPost(head,json.toString(),"application/json;charset=utf-8");
		System.out.println("耗材详细信息查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
