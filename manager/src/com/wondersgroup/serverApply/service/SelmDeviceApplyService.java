package com.wondersgroup.serverApply.service;

import java.util.List;

import net.sf.json.JSONObject;
import tw.ecosystem.reindeer.web.HttpReqRes;

import com.wondersgroup.serverApply.bean.SelmDeviceApply;

import coral.base.util.RequestWrapper;

/**
 * 设备接入申请业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmDeviceApplyService {

	SelmDeviceApply get(String stDeviceApplyId);

	JSONObject query(HttpReqRes httpReqRes);

	void remove(String stDeviceApplyId);

	SelmDeviceApply saveOrUpdate(RequestWrapper wrapper);

	void saveSubmit(HttpReqRes httpReqRes);

	void batchDelete(String[] stDeviceApplyIds);

	void removeApply(HttpReqRes httpReqRes);

	JSONObject checkQuery(HttpReqRes httpReqRes);

	void devicePass(HttpReqRes httpReqRes);
	
	void deviceNoPass(HttpReqRes httpReqRes);

	void batchPass(HttpReqRes httpReqRes);
	
	void batchNoPass(HttpReqRes httpReqRes);
}
