package com.wondersgroup.dataitem.item312462115323.web;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.Pdf2pngUtil;
import com.wondersgroup.common.utils.PdfToJpeg;
import com.wondersgroup.dataitem.item312462115323.util.TaxBureauUtil;

/**
 * 税务局事项:
 * 城乡居民养老保险证明打印
 * 城乡居民医疗保险证明打印
 * 个人纳税记录打印
 * @author xb
 *
 */
@Controller
public class TaxBureauConteoller {
	
	/**
	 * 缴费证明文件预览（图片）
	 * xz:险种类型;02-城乡居民养老保险证明打印;01-城乡居民医疗保险证明打印
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/taxBureau/proofPriview.do")
	public void proofPriview(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String Authorization = TaxBureauUtil.getAuthorization();
		if(StringUtils.isEmpty(Authorization))
			throw new RuntimeException("Login Failed!");
		
		String name = req.getParameter("name");
		String idCard = req.getParameter("idCard");
		// 01:医疗保险;02:养老保险
		String xz = req.getParameter("xz");
		// 费款所属期起
		String fkssqq = req.getParameter("fkssqq");
		// 费款所属期止
		String fkssqz = req.getParameter("fkssqz");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		
		String appName = "";
		if("01".equals(xz)){
			appName = "5ddd69d0-1dd3-11ed-9afd-ff274f79b579";
		} else if("02".equals(xz)) {
			appName = "51731e70-1dd2-11ed-9afd-ff274f79b579";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("xm", name);
		param.put("sfzjlxDm", "201");
		param.put("sfzjhm", idCard);
		param.put("gjdm", "156");
		param.put("xz", xz);
		param.put("fkssqq", fkssqq);
		param.put("fkssqz", fkssqz);
		String postResult = HttpUtil.doPost(head, param.toString(),
				"application/json;charset=utf-8");
		byte[] byts = null;
		JSONObject json = new JSONObject();
		try{
			Log.debug(postResult);
			json = JSONObject.fromObject(postResult);
			if("0".equals(json.optString("code"))){
				String jfzm = json.optJSONObject("data").optString("jfzm");
				byts = Base64Util.decode(jfzm);
			}
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		if(byts == null)
			throw new RuntimeException("证明文件下载失败！");
		
        String pdfPath = TaxBureauConteoller.class.getResource("").getPath()+"template/";
        String fileName = "proof_"+idCard+".pdf";
        pdfPath += fileName;
		FileUtil.getFileFromBytes(byts, pdfPath);
		String pngPath = TaxBureauConteoller.class.getResource("").getPath()
				+"template/proof_"+idCard+"_";
		List<String> list = Pdf2pngUtil.pdf2png(pdfPath, pngPath, "jpg");
		JSONArray arrResult = new JSONArray();
		File file = null;
		for(String path : list){
			JSONObject obj = new JSONObject();
			byte[] pngByte = PdfToJpeg.image2byte(path);
			String pngStr = new BASE64Encoder().encode(pngByte);
			obj.put("png", pngStr);
			arrResult.add(obj);
			file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		json.optJSONObject("data").put("jfzm", "");
		json.optJSONObject("data").put("fileName", fileName);
		json.optJSONObject("data").put("pngList", arrResult);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
	
	@RequestMapping("/selfapi/taxBureau/proofPrint.do")
	public void proofPrint(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String fileName = req.getParameter("fileName");
		String type = req.getParameter("type");
		String filePath = TaxBureauConteoller.class.getResource("").getPath()
				+ "template/" + fileName;
		File pdf = new File(filePath);
		if(pdf.exists()){
			byte[] bytes = FileUtil.getBytesFromFile(pdf);
			pdf.delete();
			if("byte".equals(type)){
				OutputStream out = res.getOutputStream();
				res.setContentType("application/pdf");
				out.write(bytes);
				out.close();
			} else if("base64".equals(type)){
				String str = Base64Util.encode(bytes);
				AciJsonHelper.writeJsonPResponse(req, res, str);
			}
		} else {
			throw new RuntimeException("File Not Found!");
		}
	}
	
	@RequestMapping("/selfapi/taxBureau/createQrCode.do")
	public void createQrCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String Authorization = TaxBureauUtil.getAuthorization();
		if(StringUtils.isEmpty(Authorization))
			throw new RuntimeException("Login Failed!");
		
		String appName = "79e677a0-1dd5-11ed-9afd-ff274f79b579";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		String postResult = HttpUtil.doPost(head, "", "application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	@RequestMapping("/selfapi/taxBureau/getAuthStatus.do")
	public void getAuthStatus(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String Authorization = TaxBureauUtil.getAuthorization();
		if(StringUtils.isEmpty(Authorization))
			throw new RuntimeException("Login Failed!");
		
		String wybs = req.getParameter("wybs");
		String appName = "f3afaed0-1dd5-11ed-9afd-ff274f79b579";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("wybs", wybs);
		String postResult = HttpUtil.doPost(head, param.toString(), "application/json;charset=utf-8");
		System.out.println(param.toString());
		System.out.println(postResult);
//		String status = "";
//		int count = 0;
//		String postResult = "";
//		do {
//			try {
//				// 第一次等待用户准备扫码，延迟5秒
//				if(count == 0){
//					Thread.sleep(5000);
//				} else {
//					Thread.sleep(3000);
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			postResult = HttpUtil.doPost(head, param.toString(), "application/json;charset=utf-8");
//			try{
//				JSONObject json = JSONObject.fromObject(postResult);
//				if("0".equals(json.optString("code"))){
//					status = json.optJSONObject("data").optString("status");
//				}
//			} catch (Exception e) {
//				Log.debug(e.getMessage());
//			}
//			count++;
//		} while (!"03".equals(status) && count <= 3);
		
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	@RequestMapping("/selfapi/taxBureau/cancelAuth.do")
	public void cancelAuth(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String Authorization = TaxBureauUtil.getAuthorization();
		if(StringUtils.isEmpty(Authorization))
			throw new RuntimeException("Login Failed!");
		
		String accessToken = req.getParameter("accessToken");
		String appName = "823631b0-1dd6-11ed-9afd-ff274f79b579";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("accessToken", accessToken);
		String postResult = HttpUtil.doPost(head, param.toString(), "application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	@RequestMapping("/selfapi/taxBureau/applyForProof.do")
	public void applyForProof(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String Authorization = TaxBureauUtil.getAuthorization();
		if(StringUtils.isEmpty(Authorization))
			throw new RuntimeException("Login Failed!");
		
		// 自然人档案号
		String zrrdah = req.getParameter("zrrdah");
		// 税款所属期起
		String skssqq = req.getParameter("skssqq");
		// 税款所属期止
		String skssqz = req.getParameter("skssqz");
		// 受理人税务机关代码
		String slswjgDm = req.getParameter("slswjgDm");
		String accessToken = req.getParameter("accessToken");
		String appName = "3f233700-1dd7-11ed-9afd-ff274f79b579";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("zrrdah", zrrdah);
		param.put("skssqq", skssqq);
		param.put("skssqz", skssqz);
		param.put("slswjgDm", slswjgDm == null ? "" : slswjgDm);
		param.put("accessToken", accessToken);
		System.out.println(param.toString());
		String postResult = HttpUtil.doPost(head, param.toString(), "application/json;charset=utf-8");
		AciJsonHelper.writeJsonPResponse(req, res, postResult);
	}
	
	@RequestMapping("/selfapi/taxBureau/priview.do")
	public void priview(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String Authorization = TaxBureauUtil.getAuthorization();
		if (StringUtils.isEmpty(Authorization))
			throw new RuntimeException("Login Failed!");

		// 税款所属期起
		String skssqq = req.getParameter("skssqq");
		// 税款所属期止
		String skssqz = req.getParameter("skssqz");
		String sqxh = req.getParameter("sqxh");
		String accessToken = req.getParameter("accessToken");
		String appName = "3d2cc8c0-1dd8-11ed-9afd-ff274f79b579";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Authorization", Authorization);
		JSONObject param = new JSONObject();
		param.put("skssqq", skssqq);
		param.put("skssqz", skssqz);
		param.put("sqxh", sqxh);
		param.put("accessToken", accessToken);
		String nsjlwjid = "";
		int count = 0;
		do {
			try {
				// 等待税务系统生成证明文件
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String postResult = HttpUtil.doPost(head, param.toString(),
					"application/json;charset=utf-8");
			System.out.println("生成纳税记录文件失败--->"+postResult);
			try {
				JSONObject json = JSONObject.fromObject(postResult);
				if ("0".equals(json.optString("code"))) {
					nsjlwjid = json.optJSONObject("data").optString("nsjlwjid");
				}
			} catch (Exception e) {
				Log.debug(e.getMessage());
			}
			count++;
		} while (StringUtils.isEmpty(nsjlwjid) && count <= 3);
		if (StringUtils.isEmpty(nsjlwjid))
			throw new RuntimeException("生成纳税记录文件失败！");

		String fileAppName = "869904b0-1dd8-11ed-9afd-ff274f79b579";
		String sileSignature = HttpUtil.getSignature(fileAppName);
		Map<String, String> fileHead = HttpUtil.setHttpHeard(sileSignature,
				fileAppName);
		fileHead.put("Authorization", Authorization);
		JSONObject fileParam = new JSONObject();
		fileParam.put("nsjlwjid", nsjlwjid);
		fileParam.put("accessToken", accessToken);
		String postResult = HttpUtil.doPost(fileHead, fileParam.toString(),
				"application/json;charset=utf-8");
		
		byte[] byts = null;
		JSONObject json = new JSONObject();
		try {
			json = JSONObject.fromObject(postResult);
			if ("0".equals(json.optString("code"))) {
				String nsjlwj = json.optJSONObject("data").optString("nsjlwj");
				byts = Base64Util.decode(nsjlwj);
			}
		} catch (Exception e) {
			Log.debug(e.getMessage());
		}
		if (byts == null)
			throw new RuntimeException("证明文件下载失败！");

		String pdfPath = TaxBureauConteoller.class.getResource("").getPath()
				+ "template/";
		String fileName = "taxBureau_" + sqxh + ".pdf";
		pdfPath += fileName;
		FileUtil.getFileFromBytes(byts, pdfPath);
		String pngPath = TaxBureauConteoller.class.getResource("").getPath()
				+ "template/taxBureau_" + sqxh + "_";
		List<String> list = Pdf2pngUtil.pdf2png(pdfPath, pngPath, "jpg");
		JSONArray arrResult = new JSONArray();
		File file = null;
		for (String path : list) {
			JSONObject obj = new JSONObject();
			byte[] pngByte = PdfToJpeg.image2byte(path);
			String pngStr = new BASE64Encoder().encode(pngByte);
			obj.put("png", pngStr);
			arrResult.add(obj);
			file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		}
		json.optJSONObject("data").put("nsjlwj", "");
		json.optJSONObject("data").put("fileName", fileName);
		json.optJSONObject("data").put("pngList", arrResult);
		AciJsonHelper.writeJsonPResponse(req, res, json.toString());
	}
}
