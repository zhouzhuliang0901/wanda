package com.wondersgroup.dataitem.item276683593700.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class MeteorologicalController {
	
	/**
	 * 十日天气
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/meteorological/weatherOnThe10th.do")
	public void weatherOnThe10th(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String appName = "31f9b069-ae0d-4ea6-9356-008f07dbabf8";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
	    String result = HttpUtil.doPost(head, null);
		JSONObject obj = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			JSONObject json = JSONObject.fromObject(result);
			if(json.optBoolean("Success") && json.optInt("Code") == 0){
				JSONArray arr = json.optJSONArray("Data");
				for(int iLoop = 0;iLoop<arr.size();iLoop++){
					JSONObject info = arr.getJSONObject(iLoop);
					long jhpt_update_time = info.optLong("jhpt_update_time");
					long forecastdate = info.optLong("forecastdate");
					long makedatetime = info.optLong("makedatetime");
					Timestamp ts1 = new Timestamp(jhpt_update_time);
					Timestamp ts2 = new Timestamp(forecastdate);
					Timestamp ts3 = new Timestamp(makedatetime);
					info.put("jhpt_update_time", sdf.format(ts1));
					info.put("forecastdate", sdf.format(ts2));
					info.put("makedatetime", sdf.format(ts3));
				}
				obj.put("success", true);
				obj.put("msg", "");
				obj.put("data", json.toString());
				result = obj.toString();
			}
		} catch (Exception e) {
			obj.put("success", false);
			obj.put("msg", "数据请求异常！");
			obj.put("data", "");
			result = obj.toString();
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 生活指数
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/meteorological/livingPre.do")
	public void livingPre(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String appName = "68a3082d-44f0-470d-af22-69f0a9c7e7a9";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
	    String result = HttpUtil.doPost(head, null);
		JSONObject obj = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			JSONObject json = JSONObject.fromObject(result);
			if(json.optBoolean("Success") && json.optInt("Code") == 0){
				JSONArray arr = json.optJSONArray("Data");
				for(int iLoop = 0;iLoop<arr.size();iLoop++){
					JSONObject info = arr.getJSONObject(iLoop);
					long jhpt_update_time = info.optLong("jhpt_update_time");
					long pre_time = info.optLong("pre_time");
					Timestamp ts1 = new Timestamp(jhpt_update_time);
					Timestamp ts2 = new Timestamp(pre_time);
					info.put("jhpt_update_time", sdf.format(ts1));
					info.put("pre_time", sdf.format(ts2));
				}
				obj.put("success", true);
				obj.put("msg", "");
				obj.put("data", json.toString());
				result = obj.toString();
			}
		} catch (Exception e) {
			obj.put("success", false);
			obj.put("msg", "数据请求异常！");
			obj.put("data", "");
			result = obj.toString();
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 灾害天气预警
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/meteorological/severeWeatherWarning.do")
	public void severeWeatherWarning(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String appName = "e31a1e7b-016e-464e-9645-0f3e2468dd22";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
	    String result = HttpUtil.doPost(head, null);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
