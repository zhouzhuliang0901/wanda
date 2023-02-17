package com.wondersgroup.dataitem.item208443121537.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.common.utils.HttpUtil;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

@Controller
public class AccumulationFundController {
	
	/**
	 * 公积金所有可选年份查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/getAllYear.do")
	public void getAllYear(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "07da26ec-9f19-4828-ac71-29125190a11f";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "07da26ec-9f19-4828-ac71-29125190a11f";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 住房公积金缴存比例
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/queryDepositRatioByYear.do")
	public void queryDepositRatioByYear(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String year = req.getParameter("year");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "d2009dfd-b2aa-45a3-a70c-fe5398db1c7f";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "d2009dfd-b2aa-45a3-a70c-fe5398db1c7f";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?year="+year;
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 住房公积金缴存数额计算（基础和补充）
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/calculationDepositLimit.do")
	public void calculationDepositLimit(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String salary = req.getParameter("salary");// 工资基数
		String year = req.getParameter("year");// 缴存年份
		String zfjc = req.getParameter("zfjc");// 缴存比例(基础)
		String addzfjc = req.getParameter("addzfjc");// 缴存比例(补充 )
//		salary = "12000";
//		year = "2019.9—2020.3";
//		zfjc = "7%";
//		addzfjc = "4%";
//		zfjc = URLEncoder.encode(zfjc, "utf-8");
//		addzfjc = URLEncoder.encode(addzfjc, "utf-8");
		
		String url = "";
		String appName = "";
		JSONObject obj = new JSONObject();
		if(StringUtils.isNotEmpty(zfjc)){
			appName = "446b205d-51e5-45e3-bcc2-4c32b0c6e39b";
			url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
					+"?salary="+salary+"&year="+year+"&zfjc="+zfjc;
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			String str = HttpUtil.doGet(head,url,"GET");
			System.out.println("公积金url："+url);
			obj.put("basic", str);
		} else {
			obj.put("basic", "0");
		}
		if(StringUtils.isNotEmpty(addzfjc)){
			appName = "47c6d8b9-fe19-4ee6-b6ed-3a9127c69177";
			url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
					+"?salary="+salary+"&year="+year+"&addzfjc="+addzfjc;
			String signature = HttpUtil.getSignature(appName);
			Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
			String str = HttpUtil.doGet(head,url,"GET");
			obj.put("supplement", str);
		} else {
			obj.put("supplement", "0");
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 公积金区管理部查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/queryManagementDept.do")
	public void queryManagementDept(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String area = req.getParameter("area");
		if(StringUtils.isNotEmpty(area)){
			area = URLDecoder.decode(area,"utf-8");
		}
		area = area==null?"":area;
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "f9426a62-b8b8-48c3-9b07-46567c3c81e6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "f9426a62-b8b8-48c3-9b07-46567c3c81e6";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?area="+area;
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 经办银行查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/queryHandlingBank.do")
	public void queryHandlingBank(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "720ff216-b053-4fb1-8dd2-b43fd8e9b425";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "720ff216-b053-4fb1-8dd2-b43fd8e9b425";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 公积金缴存提取经办银行网点
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/queryHandlingPlace.do")
	public void queryHandlingPlace(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String bankname = req.getParameter("bankname");
		bankname = bankname == null ? "" : bankname;
		bankname = URLDecoder.decode(bankname, "utf-8");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6917ba6b-cfca-4198-aca0-aa44b636a19b";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "6917ba6b-cfca-4198-aca0-aa44b636a19b";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?bankname="+bankname;
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 住房公积金历年查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/queryAccumulationFund.do")
	public void queryAccumulationFund(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ef8e20bb-5595-4a19-9c43-e20157bb32b8";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "ef8e20bb-5595-4a19-9c43-e20157bb32b8";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 补充住房公积金历年查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/accumulationFund/querySupplementAccumulationFund.do")
	public void querySupplementAccumulationFund(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "919ebee3-d26c-4b51-84d3-886a87c7a2e5";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "919ebee3-d26c-4b51-84d3-886a87c7a2e5";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	public static boolean ping(String ipAddress, int pingTimes, int timeOut) {  
        BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令  
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;  
        try {   // 执行命令并获取输出  
            System.out.println(pingCommand);   
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return false;   
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
            int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {   
            	System.out.println(line);
                connectedCount += getCheckResult(line);   
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真  
            return connectedCount == pingTimes;  
        } catch (Exception ex) {   
            ex.printStackTrace();   // 出现异常则返回假  
            return false;  
        } finally {   
            try {    
                in.close();   
            } catch (IOException e) {    
                e.printStackTrace();   
            }  
        }
    }
	
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);  
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(line);  
        while (matcher.find()) {
            return 1;
        }
        return 0; 
    }
	
	public static void main(String[] args) {
		System.out.println(ping("www.baidu.com",4,10));
	}
}
