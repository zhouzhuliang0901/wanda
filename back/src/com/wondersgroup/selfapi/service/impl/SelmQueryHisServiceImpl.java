package com.wondersgroup.selfapi.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tw.ecosystem.reindeer.config.RdConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.selfapi.bean.DoPostByJSON;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.service.SelmQueryHisService;

@Service
public class SelmQueryHisServiceImpl implements SelmQueryHisService {
	
	private static String IP = RdConfig.get("reindeer.async.addApi.ip");
	@Override
	@Async
	public JSONObject setSelmQueryHis(
			SelfUsingHistory sqh) {
		//LogHelper.debug(Thread.currentThread().getName() + "------------");
		JSONObject returnObj = new JSONObject();
		String url = IP+"/business/selmQueryHis/setHis.do";
		String json = JSON.toJSONString(sqh);
		JSONObject o = JSON.parseObject(json);
		try {
			String s = DoPostByJSON.doPostByJSONObect(url, o);
			returnObj = JSONObject.parseObject(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj;
	}

}
