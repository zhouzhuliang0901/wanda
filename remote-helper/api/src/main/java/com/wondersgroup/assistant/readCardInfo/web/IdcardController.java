package com.wondersgroup.assistant.readCardInfo.web;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.assistant.readCardInfo.dao.Idcard;
import com.wondersgroup.assistant.readCardInfo.service.readCardInfoService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.springframework.web.bind.annotation.RestController;

/**
 * IDCard web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class IdcardController {

	@RequestMapping("/idcard/getReadCardInfo")
	public String getReadCardInfo(@RequestBody(required = false) JSONObject json){
		String readCardInfo = idcardService.getReadCardInfo(json);
		return readCardInfo;
	}
	@RequestMapping("/idcard/getreadScannerInfo")
	public String getreadScannerInfo(@RequestBody(required = false) JSONObject json){
		String readCardInfo = idcardService.getreadScannerInfo(json);
		return readCardInfo;
	}
	@RequestMapping("/idcard/getGpyinfo")
	public String getGpyinfo(@RequestBody(required = false) JSONObject json){
		String readCardInfo = idcardService.getGpyinfo(json);
		return readCardInfo;
	}

	@RequestMapping("/idcard/outputHzPdf")
	public void outputHzPdf(HttpServletResponse response){
		idcardService.outputHzPdf(response);
	}
	@RequestMapping("/idcard/outputHzJlc")
	public String outputHzJlc(@RequestBody(required = false) JSONObject json){
		String readCardInfo = idcardService.outputHzJlc(json);
		return readCardInfo;
	}
	@RequestMapping("/idcard/getQmPic")
	public String getQmPic(@RequestBody(required = false) JSONObject json){
		String readCardInfo = idcardService.getQmPic(json);
		return readCardInfo;
	}
	@RequestMapping("/idcard/callTerminal")
	public String callTerminal(@RequestBody(required = false) JSONObject json){
		String readCardInfo = idcardService.callTerminal(json);
		return readCardInfo;
	}
	@Autowired
	private readCardInfoService idcardService;

}
