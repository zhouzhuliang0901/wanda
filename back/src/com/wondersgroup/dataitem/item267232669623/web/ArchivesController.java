package com.wondersgroup.dataitem.item267232669623.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PdfToJpeg;
import com.wondersgroup.common.utils.ZIPUitl;
import com.wondersgroup.dataitem.item267232669623.bean.ArchivesApplyInfo;
import com.wondersgroup.dataitem.item267232669623.service.ArchivesQueryService;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import wfc.service.log.Log;


@Controller
public class ArchivesController {
	
	@Autowired
	private ArchivesQueryService archivesQueryService;
	
	/**
	 * 根据身份证号和档案类型查询是否存在未受理的查询申请
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	@RequestMapping("/aci/workPlatform/archives/queryArchivesInfoByIdentNo.do")
	public void queryArchivesInfoByIdentNo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String identNo = req.getParameter("indetNo");
		String code = req.getParameter("code");
		// 根据身份证号和档案类型编号，查询此办事人该档案查询是否还存在未处理的申请
		List<ArchivesApplyInfo> list = archivesQueryService.queryArchivesInfoByIdentNo(identNo, code, null);
		// 新增
		// 限制申请一日一种一次
		List<ArchivesApplyInfo> allApplyList = archivesQueryService.queryArchivesInfoByIdentNo(identNo, code, "onlyOne");
		JSONObject result = new JSONObject();
		if(allApplyList.size() > 0){
			result.put("SUCCESS", "false");
			result.put("MSG", "查档申请每人每种当日只能申请一次。");
		} else {
			if(list.size() > 0){
				result.put("SUCCESS", "false");
				result.put("MSG", "该档案查询存在未处理的申请。");
			} else if(list.size() <= 0){
				result.put("SUCCESS", "true");
				result.put("MSG", "");
			}
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}
	
	/**
	 * 档案查询申请    申请登记受理查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/archives/archivesQueryApply.do")
	public void archivesQueryApply(HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.setHeader("Access-Control-Allow-Origin","*");
		String appName = req.getParameter("appName");
		String userId = req.getParameter("userId");
		String paramString = req.getParameter("param");
		JSONObject josn = new JSONObject();
		if(StringUtils.isNotEmpty(paramString)){
			paramString = URLDecoder.decode(paramString, "utf-8");
			josn = JSONObject.fromObject(paramString);
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		//String stApplyNo = UUID.randomUUID().toString();
		josn.put("from", "ZD0001");
		josn.put("userId", userId);
		//josn.put("affairno", stApplyNo);
		
		System.out.println("档案提交参数："+josn.toString());
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,josn.toString(),contentType);
		System.out.println("档案申请结果："+result);
		if(StringUtils.isNotEmpty(result) ){//&& result.indexOf("datas") != -1
			// 申请成功，保存受理信息
			try{
				JSONArray jsonAr = JSONArray.fromObject(result);
				JSONObject json = jsonAr.getJSONObject(0);
				String total = json.optString("total");
				if(StringUtils.isNotEmpty(total)){
					int count = Integer.parseInt(total);
					if(count > 0){
						String slid = json.optString("slid");
						String code = json.optString("code");
						String affairno = json.optString("affairno");
						JSONObject jsonParam = JSONObject.fromObject(paramString);
						JSONObject guest = jsonParam.optJSONObject("guest");
						String name = guest.optString("xm");
						String idCard =guest.optString("zjhm");
						
						archivesQueryService.addArchivesInfo(slid, affairno, code, name, idCard);
					}
				}
			} catch (Exception e) {
				Log.debug("提交失败："+e.getMessage());
			}
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 目录信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/archives/catalogInfoQuery.do")
	public void catalogInfoQuery(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String appName = req.getParameter("appName");
		String code = req.getParameter("code");// 档案类型
		String Q_EQ_UNIQUE_CODE = req.getParameter("Q_EQ_UNIQUE_CODE");// 档案编号
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject param = new JSONObject();
		JSONObject query = new JSONObject();
		JSONObject condition_detail = new JSONObject();
		condition_detail.put("Q_EQ_UNIQUE_CODE", Q_EQ_UNIQUE_CODE);
		query.put("code", code);
		query.put("condition_detail", condition_detail);
		param.put("query", query);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 申请利用全文接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/archives/archivesUtilizeQUery.do")
	public void archivesUtilizeQUery(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String appName = req.getParameter("appName");
		String Applyno = req.getParameter("Applyno");
		String archival_physical_location = req.getParameter("archival_physical_location");// 档案存址 
		String da_rid = req.getParameter("da_rid");// 档案id 
		String slid = req.getParameter("slid");// 受理ID
		String dh = req.getParameter("dh");// 馆编档号
		String reference_code = req.getParameter("reference_code");// 档案编号
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject param = new JSONObject();
		JSONObject lysq = new JSONObject();
		lysq.put("archival_physical_location", archival_physical_location);
		lysq.put("da_rid", da_rid);
		lysq.put("slid", slid);
		lysq.put("dh", dh);
		lysq.put("reference_code", reference_code);
		param.put("Applyno", Applyno);
		param.put("lysq", lysq);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 审核结果查看
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/archives/approvalResultsQuery.do")
	public void approvalResultsQuery(HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.setHeader("Access-Control-Allow-Origin","*");
		String appName = req.getParameter("appName");
		String identNo = req.getParameter("identNo");
		String code = req.getParameter("code");
		String userId = req.getParameter("userId");
		
		List<ArchivesApplyInfo> list = archivesQueryService.queryArchivesInfoByIdentNo(identNo, code, null);
//		String slid = list.get(0).getStApplyId();// 受理ID
//		String slbh = list.get(0).getStApplyNo();// 受理编号
		
		List<ArchivesApplyInfo> allList = archivesQueryService.queryArchivesInfoByIdentNo(identNo, code, "onlyOne");
		JSONObject result = new JSONObject();
		if(list.size() > 0){
			String slid = list.get(0).getStApplyId();// 受理ID
			String slbh = list.get(0).getStApplyNo();// 受理编号
			result.put("SUCCESS", "false");
			result.put("MSG", "该档案查询存在未处理的申请！");
			
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			
			JSONObject param = new JSONObject();
			JSONObject lysq = new JSONObject();
			lysq.put("slbh", slbh);
			lysq.put("slid", slid);
			param.put("lysq", lysq);
			param.put("from", "ZD0001");
			param.put("userId", userId);
			
			String contentType = "application/json;charset=utf-8";
			String resStr = HttpUtil.doPost(head,param.toString(),contentType);
			System.out.println("档案审核结果："+resStr);
			
			JSONArray jsonArr = JSONArray.fromObject(resStr);
			JSONObject resJson = jsonArr.optJSONObject(0);
			JSONArray lysqArr = resJson.optJSONArray("lysq");
			JSONObject lysqJson = lysqArr.optJSONObject(0);
			String status = lysqJson.optString("spzt");
			
			if("-1".equals(status) || "10".equals(status)){
				// 审核未通过，更新申请记录为无效
				archivesQueryService.updateArchivesInfo(slid, slbh);
			} else if(StringUtils.isEmpty(status)){
				// 审批结果经常出现没有审批状态，当连续3次出现异常返回没有审批结果，将该条申请记录标记为失效
				String excTimes = list.get(0).getStExt2();
				int times;
				if(StringUtils.isNotEmpty(excTimes)){
					times = Integer.parseInt(excTimes);
					if(times >= 3){
						archivesQueryService.updateArchivesInfo(slid, slbh);
					} else {
						times++;
						archivesQueryService.updateArchivesInfoExcTimes(slid, slbh, times);
					}
				} else {
					times = 1;
					archivesQueryService.updateArchivesInfoExcTimes(slid, slbh, times);
				}
			} else {
				// 清空异常结果次数
				archivesQueryService.updateArchivesInfoExcTimes(slid, slbh, 0);
			}
			result.put("DATA", lysqJson);
			
		} else if(list.size() <= 0){
			if(allList.size() > 0){
				result.put("SUCCESS", "false");
				result.put("MSG", "查档申请每人每种当日只能申请一次。");
				result.put("DATA", "");
			} else {
				result.put("SUCCESS", "true");
				result.put("MSG", "");
				result.put("DATA", "");
			}
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}
	
	/**
	 * 浏览全文接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/archives/browseFullText.do")
	public void browseFullText(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userId = req.getParameter("userId");
		String appName = req.getParameter("appName");
		String slbh = req.getParameter("slbh");// 受理编号
		String slid = req.getParameter("slid");// 受理ID
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject param = new JSONObject();
		JSONObject lysq = new JSONObject();
		lysq.put("slbh", slbh);
		lysq.put("slid", slid);
		param.put("lysq", lysq);
		param.put("from", "ZD0001");
		param.put("userId", userId);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param.toString(),contentType);
		
		String linkaddress = "";
		JSONObject lysqJson = new JSONObject();
		try{
			JSONArray arr = JSONArray.fromObject(result);
			JSONObject obj = arr.optJSONObject(0);
			JSONArray lysqArr = obj.optJSONArray("lysq");
			lysqJson = lysqArr.optJSONObject(0);
			linkaddress = lysqJson.optString("linkaddress");
		} catch (Exception e) {
			linkaddress = "";
		}
		
		if(StringUtils.isNotEmpty(linkaddress)){
			byte[] bytes = new BASE64Decoder().decodeBuffer(linkaddress);
			
			byte[] data = ZIPUitl.decompress(bytes);
			
			String pdfPath = ArchivesController.class.getResource("").getPath()+"template/archivesPDF_"+userId+".pdf";
			String pngPath = ArchivesController.class.getResource("").getPath()+"template/archivesPNG_"+userId;
			
			FileUtil.getFileFromBytes(data, pdfPath);
			List<byte[]> list = PdfToJpeg.changePdfToImg(pdfPath,pngPath);
			
			JSONArray pngData = new JSONArray();
			for(int i = 0;i<list.size();i++){
				JSONObject jsonPng = new JSONObject();
				String png = new BASE64Encoder().encode(list.get(i));
				jsonPng.put("png", png);
				pngData.add(jsonPng);
			}
			lysqJson.put("linkaddress", "");
			lysqJson.put("pngData",pngData);
			lysqJson.put("str","/aci/workPlatform/archives/showArchivesForBytes.do?userId="+userId);
			lysqJson.put("base64", linkaddress);
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, lysqJson.toString());
	}
	
	@RequestMapping("/aci/workPlatform/archives/showArchivesForBytes.do")
	public void showArchivesForBytes(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String userId = req.getParameter("userId");
		String pdfPath = ArchivesController.class.getResource("").getPath()+"template/archivesPDF_"+userId+".pdf";
		File file = new File(pdfPath);
		byte[] bytes = FileUtil.getBytesFromFile(file);
		file.delete();
		OutputStream out = res.getOutputStream();
		res.setContentType("application/pdf");
		out.write(bytes);
		out.close();
	}
	
	/**
	 * 打印出证接口
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/aci/workPlatform/archives/printResult.do")
	public void printResult(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String appName = req.getParameter("appName");
		String slbh = req.getParameter("slbh");// 受理编号
		String slid = req.getParameter("slid");// 受理ID
		String dysj = req.getParameter("dysj");// 打印/出证时间
		String dysl = req.getParameter("dysl");// 打印数量
		String userId = req.getParameter("userId");
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject param = new JSONObject();
		JSONObject lysq = new JSONObject();
		lysq.put("slbh", slbh);
		lysq.put("slid", slid);
		lysq.put("dysj", dysj);
		lysq.put("dysl", dysl);
		param.put("lysq", lysq);
		param.put("from", "ZD0001");
		param.put("userId", userId);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,param.toString(),contentType);
		// 将审核通过的申请记录标记为已打印
		//archivesQueryService.updateArchivesPrintStatus(slid, slbh);
		
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
}
