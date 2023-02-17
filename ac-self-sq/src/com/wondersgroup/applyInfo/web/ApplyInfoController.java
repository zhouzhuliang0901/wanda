package com.wondersgroup.applyInfo.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.bean.Result;
import reindeer.base.utils.AciJsonHelper;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.applyInfo.bean.SelmApplyInfo;
import com.wondersgroup.applyInfo.service.ApplyInfoService;

@Controller
public class ApplyInfoController {
	
	@Autowired
	private ApplyInfoService applyInfoService;
	
	@RequestMapping("/selfapi/applyInfo/saveApplyInfo.do")
//	public void saveApplyInfo(@RequestBody SelmApplyInfo selmApplyInfo,
	public void saveApplyInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Result result = null;
		String param = req.getParameter("param");
		SelmApplyInfo selmApplyInfo = new SelmApplyInfo();
		try{
			JSONObject json = JSONObject.parseObject(param);
//			json.put("stQueryHisId", "");
//			json.put("dtCreate", "");
			selmApplyInfo = com.alibaba.fastjson.JSON.toJavaObject(json, selmApplyInfo.getClass());
		} catch (Exception e) {
			Log.debug(e);
			result = Result.getFailedResult();
			result.setData("参数格式异常！");
		}
		
		if(StringUtils.isNotEmpty(selmApplyInfo.getStItemName()) 
				|| StringUtils.isNotEmpty(selmApplyInfo.getStItemName())){
			result = Result.getFailedResult();
			result.setData("事项信息不完整！");
		}
		if(StringUtils.isNotEmpty(selmApplyInfo.getStName()) 
				|| StringUtils.isNotEmpty(selmApplyInfo.getStIdentityNo())){
			result = Result.getFailedResult();
			result.setData("办理人信息不完整！");
		}
		int total = applyInfoService.save(selmApplyInfo);
		if(1 == total){
			result = Result.getSuccessResult();
			result.setData(selmApplyInfo.getStQueryHisId());
		} else {
			result = Result.getFailedResult();
			result.setData("保存异常！");
		}
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}
	
	public static void main(String[] args) {
	}
}
