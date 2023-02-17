package com.wondersgroup.dataitem.item213072044CSJ.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.item382711997735.utils.CSJutil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

@Controller
public class MedicalInsuranceTransferController {
	
	/**
	 * 医保转移申报
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/medicalInsuranceTransfer/accessApply.do")
	public void accessApply(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String licenseNo = req.getParameter("licenseNo");
		String userName = req.getParameter("userName");
		String toArea = req.getParameter("toArea");
		String fromArea = req.getParameter("fromArea");
		String mobile = req.getParameter("mobile");
		if(StringUtils.isNotEmpty(userName)){
			userName = URLDecoder.decode(userName, "utf-8");
		}
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "2e994dc7-61bf-496c-bac2-308e0a426d5b";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
//			appName = "4b6a92ba-1ae1-4401-8684-6075a424237e";
			// 长三角个性化事项
			appName = "4fb771a0-a666-11ec-8d2d-c77fd338b4a7";
		}
		
		String signature = HttpUtil.getSignature(appName);
	    Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
	    
		JSONObject param = new JSONObject();
//		String applyNo = HttpUtil.getApplyNo("CSJ312090039000");
		String applyNo =  CSJutil.getCSJApplyNo("CSJ312090039000");
		param.put("applyNo", applyNo);
		param.put("fromArea", fromArea);
		param.put("licenseNo", licenseNo);
		param.put("mobile", mobile);
		param.put("source", "万达自助机");
		param.put("toArea", toArea);
		param.put("username", userName);
		System.out.println("医保转移申报参数："+param.toString());
	    
	    String contentType = "application/json;charset=utf-8";
	    String result = HttpUtil.doPost(head, param.toString(),contentType);
	    System.out.println("医保转移申报结果："+result);
	    JSONObject json = new JSONObject();
		if(StringUtils.isNotEmpty(result)){
			json.put("success", true);
			json.put("msg", "申报请求成功！");
			json.put("data", result);
		} else {
			json.put("success", false);
			json.put("msg", "申报请求失败！");
			json.put("data","");
		}
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}
	
	/**
	 * 医保转移区划代码
	 * 上海只有上海市
	 * 浙江省和江苏省
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/medicalInsuranceTransfer/getAreaCode.do")
	public void getAreaCode(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		// 330000-浙江省；320000-江苏省；340000-安徽省
		String provinceCode = req.getParameter("provinceCode");
		if(StringUtils.isNotEmpty(provinceCode)){
			JSONArray arr = getAreaJsonFromText(provinceCode);
			AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
		} else {
			res.setStatus(500);
			AciJsonHelper.writeJsonPResponse(req, res, "Province Code Can Not Be Null!");
		}
	}
	
	private JSONArray getAreaJsonFromText(String provinceCode){
		//"C:\\Users\\wanda\\Desktop\\自助终端\\“一网通办”自助终端接入事项清单\\长三角\\医保转移\\test.dic";
		String path =  MedicalInsuranceTransferController.class.getResource("").
				getPath()+"template/medicalTransfer.dic";
		File medicalTransfer = new File(path);
		FileInputStream fis;
		String textContent = "";
		try {
			fis = new FileInputStream(medicalTransfer);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis, "utf-8"));
			String getLine;
			while((getLine = in.readLine()) != null){
				textContent += getLine;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = "上海市";
		if("330000".equals(provinceCode)){
			name = "浙江省";
		} else if("320000".equals(provinceCode)){
			name = "江苏省";
		} else if("340000".equals(provinceCode)){
			name = "安徽省";
		}
		String[] contentArr = textContent.split("#"+name);
		String str = contentArr[1];
		String[] areaArr = str.split(";");
		JSONArray arr = new JSONArray();
		for(String areaStr : areaArr){
			String[] strArr = areaStr.split(",");
			JSONObject obj = new JSONObject();
			obj.put("parentCode", strArr[0]);
			obj.put("code", strArr[1]);
			obj.put("name", strArr[2]);
			arr.add(obj);
		}
		return arr;
	}
	
	@SuppressWarnings("unused")
	private String send(String paramString){
		String body = "";
	    CloseableHttpClient client = HttpClients.createDefault();
	    //测试
        HttpPost httpPost = new HttpPost("http://10.81.16.163:8510/ac-product-net-csj/medTransferApi/accessApply.do");
        //生产
//        HttpPost httpPost = new HttpPost("http://10.81.16.161:7070/ac-product-net-csj/medTransferApi/accessApply.do");
   	    RequestConfig config = RequestConfig.custom().setConnectTimeout(15000) //连接超时时间
               .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
               .setSocketTimeout(10000) //数据传输的超时时间
               .build();
	    httpPost.setConfig(config);

        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        // 设置参数到请求对象中
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8"));
        httpPost.setEntity(se);
        System.out.println("长三角医保转移申报参数："+paramString);
        
        CloseableHttpResponse response;
        try{
            response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				body = EntityUtils.toString(response.getEntity(),"utf-8");
				System.out.println("长三角医保转移申报结果："+paramString);
			} else {
				String resultstr = EntityUtils.toString(response.getEntity(),"utf-8");
				System.out.println("---长三角医保转移申报请求异常---" + resultstr);
			}
            response.close();
        } catch (Exception e) {
        	Log.debug(e);
		}
		return body;
	}
	
	public static void main(String[] args) {
	}
}
