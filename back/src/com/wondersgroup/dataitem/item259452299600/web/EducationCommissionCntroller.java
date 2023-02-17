package com.wondersgroup.dataitem.item259452299600.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PooledHttpUitl;
import com.wondersgroup.dataitem.item259452299600.utils.EducationCommissionUtil;

@Controller
public class EducationCommissionCntroller {
	
	/**
	 * 全市示范高中信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryHighSchoolInfo.do")
	public void queryHighSchoolInfo(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String schoolName = req.getParameter("schoolName");
		if(StringUtils.isNotEmpty(schoolName)){
			schoolName = URLDecoder.decode(schoolName, "utf-8");
		}
		
		String appName = "d4fd88f0-e373-49d6-b6c2-efd368d1fbe1";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		if(StringUtils.isNotEmpty(schoolName)){
			JSONArray resultArr = new JSONArray();
			JSONArray arr = JSONArray.fromObject(str);
			for(int iLoop = 0;iLoop<arr.size();iLoop++){
				JSONObject obj = arr.getJSONObject(iLoop);
				String mz = obj.optString("mz");
				if(schoolName.equals(mz) || mz.indexOf(schoolName) != -1){
					resultArr.add(obj);
				}
			}
			AciJsonHelper.writeJsonPResponse(req, res, resultArr.toString());
		} else {
			AciJsonHelper.writeJsonPResponse(req, res, str);
		}
	}
	
	/**
	 * 市学生事务中心存档证明信
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryProofFiling.do")
	public void queryProofFiling(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String sfzh = req.getParameter("sfzh");// 310112201006247827
		String ywlx = "archives";
		String year = "";
		
		String appName = "864b5d6d-2a9c-443f-b8a5-e5f602501547";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
        // 设置参数到请求对象中
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(CharsetUtils.get("UTF-8"));
        builder.addPart("sfzh", new StringBody(sfzh,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("ywlx", new StringBody(ywlx,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
        builder.addPart("year", new StringBody(year,ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		HttpEntity reqEntity = builder.build();
        
		String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 根据学校名称获取民办学校信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryCivilianSchool.do")
	public void queryCivilianSchool(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String xmmc = req.getParameter("schoolName");
		if(StringUtils.isNotEmpty(xmmc)){
			xmmc = URLDecoder.decode(xmmc, "utf-8");
		}
		String accessToken = EducationCommissionUtil.getToken("d7c57a00-b26f-11ec-8d2d-c77fd338b4a7");
		String appName = "60e8f85a-bc77-461b-a97f-e87c85a0b131";
//		String userName = "bigdatacenter";
//		String password = "WkCM987NVab2zhX";
//		String sign = userName+":"+password;
//		String Authorization = "Basic "+new BASE64Encoder().encode(sign.getBytes("utf-8"));
//		String replayId = UUID.randomUUID().toString().replace("-", "");
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		head.put("Authorization", Authorization);
//		head.put("replayId", replayId);
		head.put("access_token", accessToken);
		head.put("authoritytype", "2");
		head.put("elementsVersion", "1.00");
		
		JSONObject json = new JSONObject();
		json.put("xmmc", xmmc);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,json.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 中外合作办学信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryCooperationSchool.do")
	public void queryCooperationSchool(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String name = req.getParameter("name");
//		String accessToken = EducationCommissionUtil.access_token("cooperationSchool");
//		JSONObject json = JSONObject.fromObject(accessToken);
//		String code = json.optString("code");
//		if(StringUtils.isNotEmpty(code) && "200".equals(code)){
//			JSONObject data = json.optJSONObject("data");
//			try{
//				accessToken = data.optString("accessToken");
//			} catch (Exception e) {
//				accessToken = "";
//			}
//		}
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		
		String accessToken = EducationCommissionUtil.getToken("0f9bae01-3ec2-4879-976b-3fcc33dc0ae4");
		String appName = "287bfd1c-2b50-475f-badc-f772ca90c6b8";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		head.put("Authorization","Bearer "+accessToken);
		head.put("access_token", accessToken);
		head.put("authoritytype", "2");
		head.put("elementsVersion", "1.00");
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+
				"?name="+name+"&page=1&order=JGMC&size="+Integer.MAX_VALUE / 2;
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("name", name);
//		paramMap.put("page", "1");
//		paramMap.put("order", "JGMC");
//		paramMap.put("size", Integer.toString(Integer.MAX_VALUE / 2));
//		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
//	    String result = HttpUtil.doPost(head, reqEntity);
	    String result = send(url, head);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 义务教育：幼升小、小升初报名信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryRegistrationInformation.do")
	public void queryRegistrationInformation(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String applyNo = req.getParameter("applyNo");
		String username = req.getParameter("username");
		// 居民身份证、港澳居民居住证、港澳居民来往内地通行证、台湾居民居住证、台湾居民来往大陆通行证、护照、中华人民共和国外国人永久居留证、其他
		String licenseType = req.getParameter("licenseType");
		String licenseNo = req.getParameter("licenseNo");
		
//		applyNo = "001003519500001";
//		licenseNo = "110111199812311111";
		if(StringUtils.isNotEmpty(licenseType)){
			licenseType = URLDecoder.decode(licenseType, "utf-8");
		}
		if(StringUtils.isNotEmpty(username)){
			username = URLDecoder.decode(username, "utf-8");
		}
		
		String appName = "9016f2d5-6072-4cdf-989a-0cd8072e9160";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+
				"?applyNo="+applyNo+"&username="+username+"&licenseType="+licenseType+"&licenseNo="+licenseNo;
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("applyNo", applyNo);
//		paramMap.put("username", username);
//		paramMap.put("licenseType", licenseType);
//		paramMap.put("licenseNo", licenseNo);
//		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
//	    String result = HttpUtil.doPost(head, reqEntity);
	    String result = send(url, head);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 3岁以下幼儿托育服务机构信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryChildCareInstitution.do")
	public void queryChildCareInstitution(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String name = req.getParameter("name");
//		String accessToken = EducationCommissionUtil.access_token("childCareInstitution");
//		JSONObject json = JSONObject.fromObject(accessToken);
//		String code = json.optString("code");
//		if(StringUtils.isNotEmpty(code) && "200".equals(code)){
//			JSONObject data = json.optJSONObject("data");
//			try{
//				accessToken = data.optString("accessToken");
//			} catch (Exception e) {
//				accessToken = "";
//			}
//		}
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		
		String accessToken = EducationCommissionUtil.getToken("c6640b4c-7732-4ec9-978a-4ef256a9d23c");
		String appName = "94fca06f-e65d-4b34-a249-fa9cd1321a99";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		head.put("Authorization","Bearer "+accessToken);
		head.put("access_token", accessToken);
		head.put("authoritytype", "2");
		head.put("elementsVersion", "1.00");
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+
				"?sname="+name+"&page=1&order=sname&size="+Integer.MAX_VALUE / 2;
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("sname", name);
//		paramMap.put("page", "1");
//		paramMap.put("order", "sname");
//		paramMap.put("size", Integer.toString(Integer.MAX_VALUE / 2));
//		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
//	    String result = HttpUtil.doPost(head, reqEntity);
		String result = send(url, head);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 民办非学历培训机构信息查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/educationCommission/queryTrainingInstitution.do")
	public void queryTrainingInstitution(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String name = req.getParameter("name");
//		String accessToken = EducationCommissionUtil.access_token("trainingInstitution");
//		JSONObject json = JSONObject.fromObject(accessToken);
//		String code = json.optString("code");
//		if(StringUtils.isNotEmpty(code) && "200".equals(code)){
//			JSONObject data = json.optJSONObject("data");
//			try{
//				accessToken = data.optString("accessToken");
//			} catch (Exception e) {
//				accessToken = "";
//			}
//		}
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		String accessToken = EducationCommissionUtil.getToken("cd522a17-425b-425b-a519-ab6d3b02dcb8");
		String appName = "513b08f9-0bc6-42af-a784-646024c5d544";
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
//		head.put("Authorization","Bearer "+accessToken);
		head.put("access_token", accessToken);
		head.put("authoritytype", "2");
		head.put("elementsVersion", "1.00");
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+
				"?name="+name+"&page=1&order=name&size="+Integer.MAX_VALUE / 2;
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("name", name);
//		paramMap.put("page", "1");
//		paramMap.put("order", "name");
//		paramMap.put("size", Integer.toString(Integer.MAX_VALUE / 2));
//		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
//	    String result = HttpUtil.doPost(head, reqEntity);
		String result = send(url, head);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	private String send(String url, Map<String, String> head){
		String body = "";
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;

		HttpPost httpPost = new HttpPost(url);
 		
		for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            httpPost.addHeader(entry.getKey() , entry.getValue());
		}
         
		CloseableHttpResponse response = null;
        
        try{
        	if(client == null){
        		if(url.startsWith("https://")){
        			client= HttpUtil.getHttpsClient();
        		} else {
        			client = HttpClients.createDefault();
        		}
        	}
            response = client.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
           
            
        } catch (Exception e) {
        	Log.debug("访问失败："+e);
		} finally {
			try {
				if(response != null){
					// 释放链接
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return body;
	}
}
