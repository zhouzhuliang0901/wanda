package com.wondersgroup.dataitem.item361512745432.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;

/**
 * 贷款、还款计算及查询
 * @author wanda
 *
 */
@Controller
public class LoanAndRepaymentController {
	
	/**
	 * 还款试算
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/loanAndRepayment/repaymentTrialBalance.do")
	public void repaymentTrialBalance(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		// 1：公积金贷；2：商贷；12：组合贷
		String loanType = req.getParameter("loanType");
		// 0：按总额贷；1：按揭成数贷
		String type = req.getParameter("type");
		// 还款方式，0：等额本息；1：等额本金
		String repaymentType = req.getParameter("repaymentType");
		// 贷款年数
		String year = req.getParameter("year");
		// 公积金贷款总额
		String fundSum = req.getParameter("fundSum");
		// 商贷总额
		String commercialSum = req.getParameter("commercialSum");
		// 房屋总价
		String housePrice = req.getParameter("housePrice");
		// 公积金贷款利率
		String fundRate = req.getParameter("fundRate");
		// 商贷利率
		String commercialRate = req.getParameter("commercialRate");
		// 按揭成数
		String number = req.getParameter("number");
		
//		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String appName = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		// 公积金按总额贷款，等额本息还款
		if("1".equals(loanType) && "0".equals(type) && "0".equals(repaymentType)){
			appName = "ce06e75c-216f-4dd2-b4d1-b6a1d9f83ad5";
//			url += "?year="+year+"&sum="+fundSum+"&rate="+fundRate;
			paramMap.put("year", year);
			paramMap.put("sum", fundSum);
			paramMap.put("rate", fundRate);
		}
		// 公积金按总额贷款，等额本金还款
		if("1".equals(loanType) && "0".equals(type) && "1".equals(repaymentType)){
			appName = "b32b1f44-2b2f-4c7e-9492-57e49a2f5be0";
//			url += "?year="+year+"&sum="+fundSum+"&rate="+fundRate;
			paramMap.put("year", year);
			paramMap.put("sum", fundSum);
			paramMap.put("rate", fundRate);
		}
		// 公积金按揭成数贷款，等额本息还款
		if("1".equals(loanType) && "1".equals(type) && "0".equals(repaymentType)){
			appName = "67b14d68-bc39-4e02-9305-3387651ef68d";
//			url += "?year="+year+"&number="+number+"&rate="+fundRate+"&sumByHousePrice="+housePrice;
			paramMap.put("year", year);
			paramMap.put("number", number);
			paramMap.put("rate", fundRate);
			paramMap.put("sumByHousePrice", housePrice);
		}
		// 公积金按揭成数贷款，等额本金还款
		if("1".equals(loanType) && "1".equals(type) && "1".equals(repaymentType)){
			appName = "fa4c6ea4-94ba-4c01-b5a2-934b3111f7e9";
//			url += "?year="+year+"&number="+number+"&rate="+fundRate+"&sumByHousePrice="+housePrice;
			paramMap.put("year", year);
			paramMap.put("number", number);
			paramMap.put("rate", fundRate);
			paramMap.put("sumByHousePrice", housePrice);
		}
		// 商业按总额贷款，等额本息还款
		if("2".equals(loanType) && "0".equals(type) && "0".equals(repaymentType)){
			appName = "e7af5a2d-7318-487c-8de8-52727d70b747";
//			url += "?year="+year+"&sum="+commercialSum+"&rate="+commercialRate;
			paramMap.put("year", year);
			paramMap.put("sum", commercialSum);
			paramMap.put("rate", commercialRate);
		}
		// 商业按总额贷款，等额本金还款
		if("2".equals(loanType) && "0".equals(type) && "1".equals(repaymentType)){
			appName = "a8ed3249-5c0c-4e3f-bd2a-2ba7ea0a4a22";
//			url += "?year="+year+"&sum="+commercialSum+"&rate="+commercialRate;
			paramMap.put("year", year);
			paramMap.put("sum", commercialSum);
			paramMap.put("rate", commercialRate);
		}
		// 商业按揭成数贷款，等额本息还款
		if("2".equals(loanType) && "1".equals(type) && "0".equals(repaymentType)){
			appName = "4b21a8a9-0608-4d22-8ede-b1a7209ab585";
//			url += "?year="+year+"&number="+number+"&rate="+commercialRate+"&sumByHousePrice="+housePrice;
			paramMap.put("year", year);
			paramMap.put("number", number);
			paramMap.put("rate", commercialRate);
			paramMap.put("sumByHousePrice", housePrice);
		}
		// 商业按揭成数贷款，等额本金还款
		if("2".equals(loanType) && "1".equals(type) && "1".equals(repaymentType)){
			appName = "d5614c7f-0499-4dc9-892d-73a96eeb4a6e";
//			url += "?year="+year+"&number="+number+"&rate="+commercialRate+"&sumByHousePrice="+housePrice;
			paramMap.put("year", year);
			paramMap.put("number", number);
			paramMap.put("rate", commercialRate);
			paramMap.put("sumByHousePrice", housePrice);
		}
		// 组合按总额贷款，等额本息还款
		if("12".equals(loanType) && "0".equals(type) && "0".equals(repaymentType)){
			appName = "b25a90fb-bbb3-4545-b38d-92c37153a78a";
//			url += "?year="+year+"&sumA="+fundSum+"&rateA="+fundRate+"&sumB="+commercialSum+"&rateB="+commercialRate;
			paramMap.put("year", year);
			paramMap.put("sumA", fundSum);
			paramMap.put("rateA", fundRate);
			paramMap.put("sumB", commercialSum);
			paramMap.put("rateB", commercialRate);
		}
		// 组合按总额贷款，等额本金还款
		if("12".equals(loanType) && "0".equals(type) && "1".equals(repaymentType)){
			appName = "c72de37f-5364-4a64-aacb-add4d4245886";
//			url += "?year="+year+"&sumA="+fundSum+"&rateA="+fundRate+"&sumB="+commercialSum+"&rateB="+commercialRate;
			paramMap.put("year", year);
			paramMap.put("sumA", fundSum);
			paramMap.put("rateA", fundRate);
			paramMap.put("sumB", commercialSum);
			paramMap.put("rateB", commercialRate);
		}
		// 组合按揭成数贷款，等额本息还款
		if("12".equals(loanType) && "1".equals(type) && "0".equals(repaymentType)){
			appName = "85e39924-166b-449a-beae-231c394d3a74";
//			url += "?year="+year+"&sumA="+fundSum+"&rateA="+fundRate+"&sumB="+commercialSum+"&rateB="+commercialRate+
//					"&sumByHousePrice="+housePrice+"&number="+number;
			paramMap.put("year", year);
			paramMap.put("sumA", fundSum);
			paramMap.put("rateA", fundRate);
			paramMap.put("sumB", commercialSum);
			paramMap.put("rateB", commercialRate);
			paramMap.put("sumByHousePrice", housePrice);
			paramMap.put("number", number);
		}
		// 组合按揭成数贷款，等额本金还款
		if("12".equals(loanType) && "1".equals(type) && "1".equals(repaymentType)){
			appName = "54f0ff17-3798-4dd5-a102-72fbc87a4687";
//			url += "?year="+year+"&sumA="+fundSum+"&rateA="+fundRate+"&sumB="+commercialSum+"&rateB="+commercialRate+
//					"&sumByHousePrice="+housePrice+"&number="+number;
			paramMap.put("year", year);
			paramMap.put("sumA", fundSum);
			paramMap.put("rateA", fundRate);
			paramMap.put("sumB", commercialSum);
			paramMap.put("rateB", commercialRate);
			paramMap.put("sumByHousePrice", housePrice);
			paramMap.put("number", number);
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		HttpEntity reqEntity = new UrlEncodedFormEntity(HttpUtil.convertMapToPair(paramMap), "utf-8");
		String result = HttpUtil.doPost(head, reqEntity);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 贷款额度试算
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/loanAndRepayment/loanTrialBalance.do")
	public void loanTrialBalance(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String accFundHost = req.getParameter("accFundHost");
		String accFundHostSupply = req.getParameter("accFundHostSupply");
		String accFundHostBase = req.getParameter("accFundHostBase");
		String hostAge = req.getParameter("hostAge");
		String hostSex = req.getParameter("hostSex");
		String accFundSecondary = req.getParameter("accFundSecondary");
		String accFundSecondarySupply = req.getParameter("accFundSecondarySupply");
		String accFundSecondaryBase = req.getParameter("accFundSecondaryBase");
		String housePrice = req.getParameter("housePrice");
		String loanNumb = req.getParameter("loanNumb");
		String houseType = req.getParameter("houseType");
		String houseArea = req.getParameter("houseArea");
		String numberIdentification = req.getParameter("numberIdentification");
		String houseGenre = req.getParameter("houseGenre");
		String endYear = req.getParameter("endYear");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "924b48cb-3dd9-40d8-81ec-b46c9034f293";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "924b48cb-3dd9-40d8-81ec-b46c9034f293";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		JSONObject obj = new JSONObject();
		obj.put("accFundHost", accFundHost);
		obj.put("accFundHostSupply", accFundHostSupply);
		obj.put("accFundHostBase", accFundHostBase);
		obj.put("hostAge", hostAge);
		obj.put("hostSex", hostSex);
		obj.put("accFundSecondary", accFundSecondary);
		obj.put("accFundSecondarySupply", accFundSecondarySupply);
		obj.put("accFundSecondaryBase", accFundSecondaryBase);
		obj.put("housePrice", housePrice);
		obj.put("loanNumb", loanNumb);
		obj.put("houseType", houseType);
		obj.put("houseArea", houseArea);
		obj.put("numberIdentification", numberIdentification);
		obj.put("houseGenre", houseGenre);
		obj.put("endYear", endYear);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		System.out.println("贷款额度试算结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	/**
	 * 组合贷款大行字典
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/loanAndRepayment/queryBankName.do")
	public void queryBankName(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "042d0861-46d6-441f-9fcb-3d2d63b26229";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "042d0861-46d6-441f-9fcb-3d2d63b26229";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 组合贷款各受理银行查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/loanAndRepayment/queryAccepBank.do")
	public void queryAccepBank(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String officeName = req.getParameter("officeName");
		officeName = officeName == null ? "" : officeName;
		officeName = URLDecoder.decode(officeName, "utf-8");
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "52ec9b54-829c-4326-978e-5ddea77f391f";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "52ec9b54-829c-4326-978e-5ddea77f391f";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))
				+"?officeName="+officeName;
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 纯公积金贷款受理网点
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/loanAndRepayment/queryLoanPlace.do")
	public void queryLoanPlace(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "465416da-a4e5-4e11-a169-8f3172bedeea";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "465416da-a4e5-4e11-a169-8f3172bedeea";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
		String str = HttpUtil.doGet(head,url,"GET");
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
