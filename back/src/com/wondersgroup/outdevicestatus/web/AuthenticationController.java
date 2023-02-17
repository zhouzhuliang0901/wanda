package com.wondersgroup.outdevicestatus.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;
import com.wondersgroup.outdevicestatus.service.InfopubDeviceInfoService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private InfopubDeviceInfoService infopubDeviceInfoService;
	
	/**
	 * CA身份鉴权
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/authentication.do")
	public void authentication(HttpServletRequest req, 
			HttpServletResponse res) throws IOException{
		String token = req.getParameter("token");
		// CA协卡助手获得的设备唯一标识
		String machineId = req.getParameter("machineId");
		JSONObject result = new JSONObject();
		// 通过CA的设备唯一表示关联设备信息
		InfopubDeviceInfo machine = infopubDeviceInfoService.getMachineInfoByCertKey(machineId);
		if(machine != null ){
			String machineMAC = machine.getStDeviceMac();
			String authorizingResult = authorizeByCA(token);
			Log.debug("CA鉴权返回："+authorizingResult);
			try{
				JSONObject json = JSONObject.fromObject(authorizingResult);
				
				if(json.optInt("code") == 200 && json.optString("data").indexOf("通过") > 0){
					List<SelmItem> list = infopubDeviceInfoService.getItemByMachine(machineMAC);
					if(list.size() <= 0){
						result.put("success", true);
						result.put("msg", "此设备无授权事项！");
						result.put("data", "");
					} else {
						result.put("success", true);
						result.put("msg", "");
						result.put("data", JSONArray.fromObject(list).toString());
					}
				} else {
					result.put("success", false);
					result.put("msg", "CA鉴权未通过！");
					result.put("data", "");
				}
			} catch (Exception e) {
				result.put("success", false);
				result.put("msg", "CA鉴权异常！");
				result.put("data", "");
			}
		} else {
			result.put("success", false);
			result.put("msg", "此证书未匹配到设备，请联系管理员！");
			result.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, result.toString());
	}
	
	private String authorizeByCA(String token){
		String body = "";
		CloseableHttpClient client = null;
//		String url = "http://202.96.220.205/wonders/token/v1/public/token/verify";// test
		String url = "http://172.31.232.206:30806/v1/public/token/verify";// product
		if(url.startsWith("https://")){
			client= HttpUtil.getHttpsClient();
		} else {
			client = HttpClients.createDefault();
		}
		JSONObject param = new JSONObject();
		param.put("token", token);
		
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        
        System.out.println("CA鉴权验证参数："+param.toString());
        StringEntity se = new StringEntity(param.toString(),"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8"));
        httpPost.setEntity(se);
        
        CloseableHttpResponse response = null;
        try{
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                body = EntityUtils.toString(entity, "utf-8");
            }
            
        } catch (Exception e) {
        	Log.debug("访问失败："+e.getMessage());
		} finally {
			try {
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
}
