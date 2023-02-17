package com.wondersgroup.dataitem.item293593261832.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item267232669623.bean.ArchivesApplyInfo;
import com.wondersgroup.dataitem.item267232669623.service.ArchivesQueryService;

import wfc.service.log.Log;

@Controller
public class CriminalRecordConteoller {
	
	@Autowired
	private ArchivesQueryService archivesQueryService;
	
	/**
     * 有无犯罪记录事项
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/aci/criminalRecord/queryCriminalRecord.do")
    public void queryCriminalRecord(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String startTime = req.getParameter("startTime");
    	String endTime = req.getParameter("endTime");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	long startl = 0;
    	long endl = 0;
    	try {
			startl = sdf.parse(startTime).getTime();
			endl = sdf.parse(endTime).getTime();
		} catch (ParseException e) {
			Log.debug("日期转换错误");
		}
    	
    	String accessToken = req.getParameter("accessToken");
    	String idCard = req.getParameter("idCard");
    	String mobile = req.getParameter("mobile");
    	String name = req.getParameter("name");
    	// 办理点编号
    	String departCode = req.getParameter("departCode");
    	// 现居住地址
    	String nowAddress = req.getParameter("nowAddress");
    	// 户籍地址
    	String hjAddress = req.getParameter("hjAddress");
    	// 上传材料的ID
    	String stuff011 = req.getParameter("stuff011");
    	// 证明类型
    	String zmlx = req.getParameter("zmlx");
    	if(StringUtils.isNotEmpty(name)){
    		name = URLDecoder.decode(name, "utf-8");
    	}
    	if(StringUtils.isNotEmpty(nowAddress)){
    		nowAddress = URLDecoder.decode(nowAddress, "utf-8");
    	}
    	if(StringUtils.isNotEmpty(hjAddress)){
    		hjAddress = URLDecoder.decode(hjAddress, "utf-8");
    	}
    	
    	JSONObject data = new JSONObject();
    	data.put("accessToken",accessToken);
    	data.put("departCode",departCode);
    	data.put("source","万达自助机");
    	data.put("itemCode","312000218000");
    	
    	JSONObject info = new JSONObject();
    	info.put("hjAddress", hjAddress);
    	info.put("licenseNo", idCard);
    	info.put("mobile", mobile);
    	info.put("nowAddress", nowAddress);
    	info.put("stuff011", stuff011);
    	info.put("username", name);
    	JSONObject EndTime = new JSONObject();
    	EndTime.put("unix", endl);
    	EndTime.put("value", endTime);
    	JSONObject StartTime = new JSONObject();
    	StartTime.put("unix", startl);
    	StartTime.put("value", startTime);
    	JSONObject zmlxObj = new JSONObject();
    	zmlxObj.put("value", zmlx);
    	String[] strArr = zmlx.split(",");
    	zmlxObj.put("items", strArr);
    	
    	List<String> zmlxList = Arrays.asList(strArr);
    	List<String> zm = Arrays.asList("是否曾被判处管制、拘役、有期徒刑、无期徒刑、死刑、罚金、剥夺政治权利、没收财产以及已构成犯罪，被人民法院判处免予刑事处罚的犯罪信息，包括缓刑、假释、暂予监外执行等执行方式；",
    			"是否曾被处以收容教育、劳动教养、强制隔离戒毒（强制戒毒）、责令社区戒毒（限期戒毒）的违法信息，包括不执行、暂缓执行、所外执行等执行方式；",
    			"是否曾被处以行政拘留、暂扣或吊销许可证的违法信息；",
    			"是否曾被处以警告、罚款、没收的违法信息，不包括交通类违法信息。");
    	
    	List<String> names = new ArrayList<String>();
    	for(String s : zmlxList){
    		int index = Integer.parseInt(s);
    		names.add(zm.get(index-1));
    	}
    	
    	zmlxObj.put("names", names);
    	
    	info.put("EndTime",EndTime);
    	info.put("StartTime",StartTime);
    	info.put("zmlx",zmlxObj);
    	data.put("info", info);
    	
    	JSONObject obj = new JSONObject();
    	obj.put("data", data);
    	
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7ebe0398-af91-4a38-b36c-1e6ec3b3031d";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "d6ac2222-1dd5-4f93-a514-7c8c2663f83d";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("有无犯罪记录事项办理结果："+result);
		
		if(StringUtils.isNotEmpty(result)){
			JSONObject json = JSONObject.fromObject(result);
			String success = json.optString("isSuccess");
			if("true".equals(success)){
				String applyNo = json.optString("applyNo");
				archivesQueryService.addArchivesInfo("", applyNo, "犯罪记录", name, idCard);
			}
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
    }
    
    /**
     * 查询犯罪记录证明办件的记录，获取最新办理的统一审批编码，用以查看办理结果
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/aci/criminalRecord/queryApplyInfoOfCriminalRecord.do")
    public void queryApplyInfoOfCriminalRecord(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String idCard = req.getParameter("idCard");
    	
    	List<ArchivesApplyInfo> list = archivesQueryService.queryArchivesInfoByIdentNo(idCard, "犯罪记录", null);
    	JSONObject result = new JSONObject();
		if(list.size() > 0){
			result.put("SUCCESS", "true");
			result.put("MSG", "");
			result.put("applyNo", list.get(0).getStApplyNo());
		} else if(list.size() <= 0){
			result.put("SUCCESS", "false");
			result.put("MSG", "未查询到成功办理信息！");
			result.put("applyNo", "");
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
    }
    
    /**
     * 有无犯罪记录异议提交申请
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/aci/criminalRecord/applyObjection.do")
    public void applyObjection(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	// TODO
    	String applyId = req.getParameter("applyId");
    	String applyNo = req.getParameter("applyNo");
    	String submitTime = req.getParameter("submitTime");
    	String content = req.getParameter("content");
    	String certCode = req.getParameter("certCode");
    	if(StringUtils.isNotEmpty(content)){
    		content = URLDecoder.decode(content, "utf-8");
    	}
    	
    	String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "2e22c493-7fdc-491b-8028-d04ee637e469";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "241e2199-c215-426e-b020-eddfffd30f78";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        JSONObject obj = new JSONObject();
        obj.put("applyId",applyId);
        obj.put("applyNo",applyNo);
        obj.put("source","万达自助机");
        obj.put("itemCode","312050035000-99");
        obj.put("itemName","有无违法犯罪记录证明开具");
        obj.put("submitTime",submitTime);
        obj.put("content",content);
        obj.put("certCode",certCode);
        
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
    }
    
    /**
     * 有无犯罪记录异议反馈结果
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/aci/criminalRecord/queryObjectionResult.do")
    public void queryObjectionResult(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String applyId = req.getParameter("applyId");
    	String status = req.getParameter("status");
    	String result = req.getParameter("result");
    	
    	String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "8865f9c1-b928-48fd-97cd-6d063b35ca60";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b5860e5c-ee68-4c9e-9b1b-d4bdc8386c47";
		}
        
        String signature = HttpUtil.getSignature(appName);
        Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
        
        JSONObject obj = new JSONObject();
        obj.put("applyId",applyId);
        obj.put("status",status);
        obj.put("result",result);
        
		String contentType = "application/json;charset=utf-8";
		String postResult = HttpUtil.doPost(head,obj.toString(),contentType);
		
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
    }
    
}
