package com.wondersgroup.assistant.readCardInfo.service;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.assistant.readCardInfo.dao.Idcard;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

import javax.servlet.http.HttpServletResponse;

/**
 * IDCard业务接口
 * 
 * @author scalffold
 * 
 */
public interface readCardInfoService {

	public String getReadCardInfo(JSONObject remoteInfo);
	public String getreadScannerInfo(JSONObject sbInfo);
	public String getGpyinfo(JSONObject archivesInfo);
	public void outputHzPdf(HttpServletResponse response);
	public String outputHzJlc(JSONObject archivesInfo);
	public String getQmPic(JSONObject remoteInfo);
	public String callTerminal(JSONObject remoteInfo);




}
