package com.wondersgroup.dataitem.item277002115323.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;

@Controller
public class WaterAuthorityController {
	
	/**
	 * 公共服务事项详细信息查询
	 * 水压数据查询
	 * 水质数据查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/saterAuthority/queryDataDetail.do")
	public void queryDataDetail(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		// 水压数据:312001418000;水质数据:312002467000
		String itemcode = req.getParameter("itemcode");
		// yyyy-MM
		String time = req.getParameter("time");
		
		String appName = "a9a14700-0c87-11ec-9ca6-ff6b2def5f54";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject paramJson = new JSONObject();
		JSONObject json = new JSONObject();
		json.put("itemcode", itemcode);
		json.put("time", time);
		paramJson.put("params", json);
		String result = HttpUtil.doPost(head, paramJson.toString(),
				"application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 公共服务下载附件
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("/selfapi/saterAuthority/getAppendix.do")
	public void getAppendix(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String fileName = req.getParameter("fileName");
		if (StringUtils.isNotEmpty(fileName)) {
			fileName = URLDecoder.decode(fileName, "utf-8");
		}
		String filePath = req.getParameter("filePath");
		
		
		String appName = "8748f9f0-0ebc-11ec-9ca6-ff6b2def5f54";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		JSONObject json = new JSONObject();
		json.put("fileName", fileName);
		json.put("filePath", filePath);
		byte[] byts = HttpUtil.doPostForFile(head, json.toString(),
				"application/json;charset=utf-8");
		if (byts != null) {
			OutputStream out = res.getOutputStream();
			res.setContentType("image/jpeg");
			out.write(byts);
			out.close();
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, "获取文件失败！");
		}
	}
}
