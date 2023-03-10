package com.wondersgroup.dataitem.item276652591922.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import reindeer.base.utils.AciJsonHelper;
import sun.misc.BASE64Encoder;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.FileUtil;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PooledHttpUitl;
import com.wondersgroup.dataitem.item276652591922.bean.BankDictionary;
import com.wondersgroup.dataitem.item276652591922.bean.CurrentDictionary;
import com.wondersgroup.dataitem.item276652591922.bean.FoodDictionary;
import com.wondersgroup.dataitem.item276652591922.utils.MD5;
import com.wondersgroup.dataitem.item276652591922.utils.PdfUtil;
import com.wondersgroup.dataitem.item276652591922.utils.PostUtil;

/**
 * ?????????????????????????????????????????????????????????????????????
 * @author xb
 *
 */
@Controller
public class CivilServiceController {
	
	/**
	 * 1.??????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/getApplicantInfo.do")
	public void getApplicantInfo(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String idType = req.getParameter("idType");
		String idNo = req.getParameter("idNo");
		
		JSONObject paramaJson = new JSONObject();
		paramaJson.put("idtype", idType);
		paramaJson.put("idno", idNo);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "1207c2bd-c36e-4f4f-b6d0-5c38782147b9";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "4196603f-206d-4694-b4de-d513b5e1a82a";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = PostUtil.send(paramaJson.toString(), head, contentType);
		
		String str = PostUtil.dealResult(result);
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 2.????????????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/getAffairsArchives.do")
	public void getAffairsArchives(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String affairscode = req.getParameter("affairscode");
		
		JSONObject paramaJson = new JSONObject();
		paramaJson.put("affairscode", affairscode);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "c7af3827-99fb-4f6e-b81f-1611b006025d";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "a8d7c8d9-4b21-4fe4-862c-20a2e441be1e";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = PostUtil.send(paramaJson.toString(), head, contentType);
		
		String str = PostUtil.dealResult(result);
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 3.??????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/uploadArchiveInfo.do")
	public void uploadArchiveInfo(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
//		RequestWrapper wrapper = new RequestWrapper(req);
//		System.out.println(wrapper.getParameter("archivescode"));
//		MultipartFile file = null;
		byte[] bytes = null;
		MultipartHttpServletRequest multipartRequest = ((MultipartHttpServletRequest) req); 
		
		String fitem = req.getParameter("img");
		String attachtype = "";
		if (StringUtils.isBlank(fitem)) {
			System.out.println("???????????????????????????");
			fitem = "";
//			file =  wrapper.getMultipartFile("img");
//			System.out.println("-----------" + file.getName() + "--"
//					+ file.getContentType());
//			fitem = Base64Util.encode(file.getBytes());
			
			List<MultipartFile> files = multipartRequest.getFiles("img");
			bytes = files.get(0).getBytes();
			System.out.println("-----------" + files.get(0).getName() + "--"
					+ files.get(0).getContentType());
			fitem = Base64Util.encode(bytes);
			attachtype = files.get(0).getContentType();
			
			attachtype = files.get(0).getContentType();
			if(attachtype.indexOf("image") != -1){
				attachtype = "image/jpg";
			}
			if(attachtype.indexOf("pdf") != -1){
				attachtype = "pdf";
			}
		} else {
			// ???????????????????????????????????????????????????
			System.out.println("???????????????Base64????????????");
			attachtype = multipartRequest.getParameter("attachtype");
		}
		
		String archivescode = multipartRequest.getParameter("archivescode");
		String affairscode = multipartRequest.getParameter("affairscode");
		String archivesname = multipartRequest.getParameter("archivesname");
		String needflag = multipartRequest.getParameter("needflag");
		// ?????????????????????0-?????????????????? ???1-??????????????????2-???????????????????????????3-???????????????????????????4-?????????????????????5-????????????
		String archivessource = multipartRequest.getParameter("archivessource");
		JSONObject paramaJson = new JSONObject();
		paramaJson.put("archivescode",archivescode);
		paramaJson.put("affairscode",affairscode);
		paramaJson.put("archivesname",archivesname);
		paramaJson.put("needflag",needflag);
		paramaJson.put("attachfile",fitem);
		paramaJson.put("attachtype",attachtype);
		paramaJson.put("archivessource",archivessource);
		
//		System.out.println("??????????????????????????????-?????????????????????"+paramaJson.toString());
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "eb3d87c7-d8b1-42a4-822c-4f3305fbb2ef";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b3cb4fe9-a746-47b9-a07f-d8113bdaa9ba";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = PostUtil.send(paramaJson.toString(), head, contentType);
		
		String str = PostUtil.dealResult(result);
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 4.??????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/deleteArchive.do")
	public void deleteArchive(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String imgscans = req.getParameter("imgscans");
		
		JSONObject paramaJson = new JSONObject();
		paramaJson.put("imgscans", imgscans);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "eb0f1250-8a92-4d13-983d-f76fa0941d2a";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "faa79132-9fa9-44f3-9dab-04791a3af013";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = PostUtil.send(paramaJson.toString(), head, contentType);
		
		String str = PostUtil.dealResult(result);
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 5.??????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/sendYwtbApplyInfo.do")
	public void sendYwtbApplyInfo(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String jsonStr = req.getParameter("jsonStr");
		if(StringUtils.isNotEmpty(jsonStr)){
			jsonStr = URLDecoder.decode(jsonStr, "utf-8");
		}
		System.out.println("??????????????????????????????-???????????????????????????"+jsonStr);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "e44003b0-ff36-4ccc-8350-9102c2faac8d";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "b7afad01-3ea3-4178-bdf2-f4bcc8c1db49";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = PostUtil.send(jsonStr, head, contentType);
		
		String str = PostUtil.dealResult(result);
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * 6.?????????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/getApplyFile.do")
	public void getApplyFile(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String suid = req.getParameter("suid");
		
		JSONObject paramaJson = new JSONObject();
		paramaJson.put("suid", suid);
		
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "7a57fe44-cd74-4133-a281-7b843d5633ba";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "69054673-ff5d-43f9-b570-357cba22a77c";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		
		String contentType = "application/json;charset=utf-8";
		String result = PostUtil.send(paramaJson.toString(), head, contentType);
		
		String str = PostUtil.dealResult(result);
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
	
	/**
	 * ????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/civilService/getCurrentDictionaries.do")
	public void getCurrentDictionaries(HttpServletRequest req, 
			HttpServletResponse res)throws IOException{
		String type = req.getParameter("type");
		
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(Integer.valueOf(type));
		params.setHeadRows(1);
		List<CurrentDictionary> dictionaryList = ExcelImportUtil.importExcel(
				new File(CivilServiceController.class.getResource("").getPath()+"template/????????????.xlsx"),
				CurrentDictionary.class,
				params);
		
		JSONArray arr = new JSONArray();
		for(CurrentDictionary dictionary : dictionaryList){
			JSONObject obj = new JSONObject();
			obj.put("key", dictionary.getKey());
			obj.put("value", dictionary.getValue());
			obj.put("parent-key", dictionary.getParentKey() == null? "" : dictionary.getParentKey());
			arr.add(obj);
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
	}
	
	/**
	 * ??????????????????
	 * @param req
	 * @param res
	 * @throws IOException
	 */
    @RequestMapping("/selfapi/CivilServiceController/getFoodDictionaries.do")
    public void getFoodDictionaries(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setStartRows(3);
        List<FoodDictionary> foodDictionaryList = ExcelImportUtil.importExcel(
                new File(CivilServiceController.class.getResource("").getPath()+"template/????????????.xlsx"),
                FoodDictionary.class,
                params);

        JSONArray arr = new JSONArray();
        for(FoodDictionary dictionary : foodDictionaryList){
            if (!dictionary.getKey().equals("1") && !dictionary.getKey().equals("2")) {
            	 arr.add(dictionary.getValue());
            }
        }

        AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
    }
    
    /**
     * ??????????????????????????????
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/CivilServiceController/getTransportationSubsidyDictionaries.do")
    public void getTransportationSubsidyDictionaries(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String parentKey = req.getParameter("parentKey");
		
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(0);
		params.setHeadRows(1);
		List<CurrentDictionary> dictionaryList = ExcelImportUtil.importExcel(
				new File(CivilServiceController.class.getResource("").getPath()+"template/????????????????????????????????????.xlsx"),
				CurrentDictionary.class,
				params);
		
		JSONArray arr = new JSONArray();
		for(CurrentDictionary dictionary : dictionaryList){
			JSONObject obj = new JSONObject();
			if(StringUtils.isEmpty(parentKey)){
				if(StringUtils.isNotEmpty(dictionary.getParentKey())){
					continue;
				}
			} else {
				if(StringUtils.isEmpty(dictionary.getParentKey()) || !parentKey.equals(dictionary.getParentKey())){
					continue;
				}
			}
			obj.put("key", dictionary.getKey());
			obj.put("value", dictionary.getValue());
			obj.put("parentKey", dictionary.getParentKey() == null? "" : dictionary.getParentKey());
			arr.add(obj);
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
    }
    
    /**
     * ??????????????????????????????
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/CivilServiceController/getBankDictionaries.do")
    public void getBankDictionaries(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
    	String place = req.getParameter("place");
    	place = URLDecoder.decode(place, "utf-8");
    	
		ImportParams params1 = new ImportParams();
		params1.setStartSheetIndex(0);
		params1.setHeadRows(1);
		List<BankDictionary> list1 = ExcelImportUtil.importExcel(
				new File(CivilServiceController.class.getResource("").getPath()+"template/????????????????????????.xlsx"),
				BankDictionary.class,
				params1);
		
		ImportParams params2 = new ImportParams();
		params2.setStartSheetIndex(1);
		params2.setHeadRows(1);
		List<BankDictionary> list2 = ExcelImportUtil.importExcel(
				new File(CivilServiceController.class.getResource("").getPath()+"template/????????????????????????.xlsx"),
				BankDictionary.class,
				params2);
		
		JSONArray arr = new JSONArray();
		for(BankDictionary b1 : list1){
			if(place.equals(b1.getKey().trim())){
				String bankId = b1.getValue();
				if("18".equals(bankId)){
					arr = JSONArray.fromObject(list2);
				} else {
					String[] bankArr = bankId.split(",");
					for(int iLoop = 0;iLoop<bankArr.length;iLoop++){
						String s = bankArr[iLoop];
						for(BankDictionary b2 : list2){
							if(s.equals(b2.getId())){
								JSONObject json = JSONObject.fromObject(b2);
								arr.add(json);
							}
						}
					}
				}
			}
		}
		AciJsonHelper.writeJsonPResponse(req, res, arr.toString());
    }
    
    /**
     * ???????????????
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/CivilServiceController/checkBankCradWithPerson.do")
    public void checkBankCradWithPerson(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
    	String bankId = req.getParameter("bankId");
    	String idCard = req.getParameter("idCard");
    	String name = req.getParameter("name");
    	String bankKey = req.getParameter("bankKey");
    	if(StringUtils.isNotEmpty(name)){
    		name = URLDecoder.decode(name, "utf-8");
    	}
//    	bankId = "6222031001033742557";
//    	idCard = "330623196001069060";
//    	name = "?????????";
//    	bankKey = "408";
    	
		String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "0818fcd5-e252-406e-beaa-c0c194c4d538";
		}
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("Content-Type","application/json");
    	
		JSONObject obj = new JSONObject();
		obj.put("bankId", bankId);
		obj.put("idCard", idCard);
		obj.put("name", name);
		obj.put("bankKey", bankKey);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType);
		AciJsonHelper.writeJsonPResponse(req, res, result);
    }
    
    /**
     * ???????????????????????????PDF
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/CivilServiceController/getlicensePDF.do")
	public void getlicensePDF(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		byte[] signByt = null;
		MultipartHttpServletRequest multipartRequest = ((MultipartHttpServletRequest) req); 
		
		String area = multipartRequest.getParameter("area");
		String street = multipartRequest.getParameter("street");
		String rc = multipartRequest.getParameter("rc");
		String name = multipartRequest.getParameter("name");
		String sex = multipartRequest.getParameter("sex");
		String birth = multipartRequest.getParameter("birth");
		String mobile = multipartRequest.getParameter("mobile");
		String idCard = multipartRequest.getParameter("idCard");
		String certNo = multipartRequest.getParameter("certNo");
		String hjAddress = multipartRequest.getParameter("hjAddress");
		String hjZipCode = multipartRequest.getParameter("hjZipCode");
		String jzAddress = multipartRequest.getParameter("jzAddress");
		String jzZipCode = multipartRequest.getParameter("jzZipCode");
		String custodian = multipartRequest.getParameter("custodian");
		String custodianId = multipartRequest.getParameter("custodianId");
		String custodianMobile = multipartRequest.getParameter("custodianMobile");
		String agent = multipartRequest.getParameter("agent");
		String agentId = multipartRequest.getParameter("agentId");
		String agentMobile = multipartRequest.getParameter("agentMobile");
		String  signPng = multipartRequest.getParameter("signPng");
		if (StringUtils.isBlank(signPng)) {
			System.out.println("???????????????????????????");
			signPng = "";
			List<MultipartFile> files = multipartRequest.getFiles("signPng");
			signByt = files.get(0).getBytes();
			System.out.println("-----------" + files.get(0).getName() + "--"
					+ files.get(0).getContentType());
		} else {
			System.out.println("???????????????Base64????????????");
			signByt = Base64Util.decode(signPng);
		}
		
		String fileName = CivilServiceController.class.getResource("").getPath()+"template/???????????????.pdf";
		String fontName = CivilServiceController.class.getResource("").getPath()+"template/simsun.ttc,1";
		String signFile = CivilServiceController.class.getResource("").getPath()+"template/sign.pdf";
		
		byte[] blContent = FileUtil.getBytesFromFile(new File(fileName));
		try {
			PdfUtil.fillImg(blContent,signFile,"Signature",signByt);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		/* ?????????????????? */
		BaseFont bf = null;
//		Font font = null;
		try {
			bf = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//			font = new Font(bf, 10, Font.NORMAL);
//			bf = new Paragraph(body, FontChinese1);bf.setAlignment(1);setAlignment(Element.ALIGN_CENTER);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		if(StringUtils.isNotEmpty(name)  && name.length() == 2){
			char[] cs = name.toCharArray();
			name = String.valueOf(cs[0])+" "+String.valueOf(cs[1]);
		}
		
		data.put("area", new Object[]{area, bf});
		data.put("street", new Object[]{street, bf});
		data.put("rc", new Object[]{rc, bf});
		data.put("name", new Object[]{name, bf});
		data.put("name", new Object[]{name, bf});
		data.put("sex", new Object[]{sex, bf});
		data.put("birth", new Object[]{birth, bf});
		data.put("mobile", new Object[]{mobile, bf});
		data.put("idCard", new Object[]{idCard, bf});
		data.put("certNo", new Object[]{certNo, bf});
		data.put("hjAddress", new Object[]{hjAddress, bf});
		data.put("hjZipCode", new Object[]{hjZipCode, bf});
		data.put("jzAddress", new Object[]{jzAddress, bf});
		data.put("jzZipCode", new Object[]{jzZipCode, bf});
		data.put("custodian", new Object[]{custodian, bf});
		data.put("custodianId", new Object[]{custodianId, bf});
		data.put("custodianMobile", new Object[]{custodianMobile, bf});
		data.put("agent", new Object[]{agent, bf});
		data.put("agentId", new Object[]{agentId, bf});
		data.put("agentMobile", new Object[]{agentMobile, bf});
		Date now = new Date();
		data.put("year", new Object[]{new SimpleDateFormat("yyyy").format(now), bf});
		data.put("month", new Object[]{new SimpleDateFormat("MM").format(now), bf});
		data.put("day", new Object[]{new SimpleDateFormat("dd").format(now), bf});
		
		byte[] btContent = null;
		try {
			btContent = PdfUtil.fillData(signFile, data);
			String pdfStr = new BASE64Encoder().encode(btContent);
			JSONObject obj = new JSONObject();
			obj.put("data", pdfStr);
			AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
		} catch (DocumentException e) {
			Log.debug(e);
		}
		finally {
		}
	}
    
    @RequestMapping("/selfapi/nonStapleFood/getsUid.do")
    public void getsUid(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
    	 String itemCode = req.getParameter("itemCode");

         String appName = "";
         if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
             appName = "2f34d2df-43b7-46e1-94fc-c0114e6f573b";
         } else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
             appName = "f950f6db-d504-4918-b3a3-4b7572fdcced";
         }

         String signature = HttpUtil.getSignature(appName);
         Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);

         JSONObject json = new JSONObject();
         json.put("itemCode", itemCode);
         String contentType = "application/json;charset=utf-8";
         String result = HttpUtil.doPost(head,json.toString(),contentType);
         AciJsonHelper.writeJsonPResponse(req, res, result);
    }
    
    /**
     * ??????"????????????????????????????????????????????????????????????"??????????????????CA????????????
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/CivilServiceController/creatApplyPDF.do")
    public void creatApplyPDF(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	// &prePhoneService=Unicom&preBroadbandServiceSub=preBroadbandServiceSub50
    	String name = req.getParameter("name");
    	String sex = req.getParameter("sex");
    	// ?????????
    	String birthdayYear = req.getParameter("birthdayYear");
    	// ?????????
    	String birthdayMonth = req.getParameter("birthdayMonth");
    	// ?????????
    	String birthdayDay = req.getParameter("birthdayDay");
    	// ???????????????
    	String disabilityId = req.getParameter("disabilityId");
    	// ??????????????????
    	String speech = req.getParameter("speech");
    	// ??????????????????
    	String hearing = req.getParameter("hearing");
    	// ???????????????
    	String domicile = req.getParameter("domicile");
    	// ???????????????
    	String idCardNo = req.getParameter("idCardNo");
    	// ????????????
    	String address = req.getParameter("address");
    	// ??????
    	String postcode = req.getParameter("postcode");
    	// ????????????
    	String mobilePhoneNumber = req.getParameter("mobilePhoneNumber");
    	// ????????????
    	String contactNumber = req.getParameter("contactNumber");
    	// ????????????
    	String installationAddress = req.getParameter("installationAddress");
    	// ???????????????
    	String broadbandEquipmentNumber = req.getParameter("broadbandEquipmentNumber");
    	// ???????????????
    	String guardianName = req.getParameter("guardianName");
    	// ?????????????????????
    	String guardianTel = req.getParameter("guardianTel");
    	// ?????????????????????
    	String relationship = req.getParameter("relationship");
    	
    	// ???????????????(Telecommunications????????????Mobile????????????Unicom?????????)
    	String prePhoneService = req.getParameter("prePhoneService");
    	// ?????????????????????(preBroadbandServiceSub50???50M???preBroadbandServiceSub300???300M)
    	String preBroadbandServiceSub = req.getParameter("preBroadbandServiceSub");
    	
    	// ???????????????????????????(Telecommunications????????????Mobile????????????Unicom?????????)
    	String phoneService = req.getParameter("phoneService");
    	// ?????????????????????????????????(broadbandServiceSub50???50M???broadbandServiceSub300???300M)
    	String broadbandServiceSub = req.getParameter("broadbandServiceSub");
    	// base64??????????????????
    	String seal = req.getParameter("seal");
    	if(StringUtils.isNotEmpty(name))
    		name = URLDecoder.decode(name, "utf-8");
    	if(StringUtils.isNotEmpty(sex))
    		sex = URLDecoder.decode(sex, "utf-8");
    	if(StringUtils.isNotEmpty(address))
    		address = URLDecoder.decode(address, "utf-8");
    	if(StringUtils.isNotEmpty(domicile))
    		domicile = URLDecoder.decode(domicile, "utf-8");
    	if(StringUtils.isNotEmpty(installationAddress))
    		installationAddress = URLDecoder.decode(installationAddress, "utf-8");
    	if(StringUtils.isNotEmpty(guardianName))
    		guardianName = URLDecoder.decode(guardianName, "utf-8");
    	if(StringUtils.isNotEmpty(relationship))
    		relationship = URLDecoder.decode(relationship, "utf-8");
    	
    	String filePath = CivilServiceController.class.getResource("").getPath()+"template/";
    	String fileName = "";
    	if(StringUtils.isNotEmpty(prePhoneService) && StringUtils.isNotEmpty(prePhoneService)){
    		fileName += "BroadbandChangeApplication.pdf";
    	} else {
    		fileName += "BroadbandApplication.pdf";
    	}
    	filePath += fileName;
    	// ????????????
    	String fontName = CivilServiceController.class.getResource("").getPath()
    			+"template/simsun.ttc,1";
		BaseFont bf = null;
		try {
			bf = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("name", new Object[]{name, bf});
		data.put("sex", new Object[]{sex, bf});
		data.put("birthdayYear", new Object[]{birthdayYear, bf});
		data.put("birthdayMonth", new Object[]{birthdayMonth, bf});
		data.put("birthdayDay", new Object[]{birthdayDay, bf});
		data.put("disabilityId", new Object[]{disabilityId, bf});
		data.put("speech", new Object[]{speech, bf});
		data.put("hearing", new Object[]{hearing, bf});
		data.put("domicile", new Object[]{domicile, bf});
		data.put("idCardNo", new Object[]{idCardNo, bf});
		data.put("address", new Object[]{address, bf});
		data.put("postcode", new Object[]{postcode, bf});
		data.put("mobilePhoneNumber", new Object[]{mobilePhoneNumber, bf});
		data.put("contactNumber", new Object[]{contactNumber, bf});
		data.put("installationAddress", new Object[]{installationAddress, bf});
		data.put("broadbandEquipmentNumber", new Object[]{broadbandEquipmentNumber, bf});
		data.put("guardianName", new Object[]{guardianName, bf});
		data.put("guardianTel", new Object[]{guardianTel, bf});
		data.put("relationship", new Object[]{relationship, bf});
		data.put("mobilePhoneService", new Object[]{"on", bf});
		data.put("mobilePhoneServiceSub"+phoneService, new Object[]{"on", bf});
		data.put("broadbandService", new Object[]{"on", bf});
		data.put(broadbandServiceSub, new Object[]{"on", bf});
		// ??????  
		data.put("preMobilePhoneServiceSub"+prePhoneService, new Object[]{"on", bf});
		data.put(preBroadbandServiceSub, new Object[]{"on", bf});
		Date now = new Date();
		data.put("applyYear", new Object[]{new SimpleDateFormat("yyyy").format(now), bf});
		data.put("applyMonth", new Object[]{new SimpleDateFormat("MM").format(now), bf});
		data.put("applyDay", new Object[]{new SimpleDateFormat("dd").format(now), bf});
		
		byte[] btContent = null;
		try {
			btContent = PdfUtil.fillData(filePath, data);
			FileUtil.getFileFromBytes(btContent, RdConfig.get("reindeer.credit.pdf.url")+"\\???????????????????????????????????????_"+System.currentTimeMillis()+".pdf");
		} catch (DocumentException e) {
			Log.debug(e);
		}
		finally {
		}
		
		JSONObject obj = new JSONObject();
		if(btContent != null){
			String result =  createPersonalPDF(btContent,fileName,name,mobilePhoneNumber,idCardNo,seal);
			obj.put("success", true);
			obj.put("msg", "");
			obj.put("data", result);
		} else {
			obj.put("success", false);
			obj.put("msg", "????????????????????????");
			obj.put("data", "");
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
    }
    
    /**
     * ???????????????????????????
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/CivilServiceController/PDFDownload.do")
    public void PDFDownload(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String documentId = req.getParameter("documentId");
    	String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "833da38f-ecf4-4586-bd55-5926fcce0be0";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "3f2c850e-82f9-43e1-9b61-1f7141fd3bc4";
		}
		long timestamp = System.currentTimeMillis();
		String qyssignature = getHeardSignature(timestamp);
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("x-qys-timestamp", Long.toString(timestamp));
		head.put("x-qys-signature", qyssignature);
		head.put("x-qys-accesstoken", RdConfig.get("reindeer.CA.accessToken"));
		
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?documentId="+documentId;
		InputStream in = null;
		byte[] bytes = null;
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
        HttpGet get = new HttpGet(url);
		
        for (Map.Entry<String, String> entry : head.entrySet()) {
            System.out.println(entry.getKey() + "???" + entry.getValue());
            get.addHeader(entry.getKey() , entry.getValue());
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
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	in = entity.getContent();
            	ByteArrayOutputStream bos = new ByteArrayOutputStream();
            	byte[] buff = new byte[100];
            	int rc = 0;
            	while((rc = in.read(buff, 0, 100)) > 0){
            		bos.write(buff, 0, rc);
            	}
            	bytes = bos.toByteArray();
            }
            response.close();
        } catch (Exception e) {
        	Log.debug(e);
        	Log.debug("????????????");
		} finally {
            try {
            	if(response != null){
            		// ????????????
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        JSONObject obj = new JSONObject();
        if(bytes != null){
    		String pdfStr = new BASE64Encoder().encode(bytes);
    		obj.put("success", true);
    		obj.put("msg", "");
    		obj.put("data", pdfStr);
        } else {
    		obj.put("success", false);
    		obj.put("msg", "????????????????????????????????????");
    		obj.put("data", "");
        }
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
    }
    
    private String getHeardSignature(long timestamp){
    	String accessToken = RdConfig.get("reindeer.CA.accessToken");
    	String accessSecret = RdConfig.get("reindeer.CA.accessSecret");
    	String signatureStr = accessToken+accessSecret+timestamp;
    	String signature = MD5.toMD5(signatureStr);
    	return signature;
    }
    
    private String createPersonalPDF(byte[] btContent, String fileName,
    		String name, String mobilePhoneNumber, String idCardNo, String seal){
    	long timestamp = System.currentTimeMillis();
		String qyssignature = getHeardSignature(timestamp);
    	String appName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "f1c69856-abf1-4a27-953c-b1000cd618c6";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			appName = "f63d5ff7-edd3-4c81-b75e-97d6f655b385";
		}
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("x-qys-timestamp", Long.toString(timestamp));
		head.put("x-qys-signature", qyssignature);
		head.put("x-qys-accesstoken", RdConfig.get("reindeer.CA.accessToken"));
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
				.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		multipartEntityBuilder.addBinaryBody("file", btContent,
				ContentType.MULTIPART_FORM_DATA,fileName);
		multipartEntityBuilder.addPart("tenantName", new StringBody(name, 
				ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		multipartEntityBuilder.addPart("contact", new StringBody(mobilePhoneNumber, 
				ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		multipartEntityBuilder.addPart("cardno", new StringBody(idCardNo , 
				ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		multipartEntityBuilder.addPart("seal", new StringBody(seal , 
				ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		JSONObject stamperStr = new JSONObject();
		stamperStr.put("type", "SEAL_PERSONAL");
		stamperStr.put("x", 0.65);
		stamperStr.put("y", 0.30);
		stamperStr.put("page", 1);
		JSONArray arr = new JSONArray();
		arr.add(stamperStr);
		multipartEntityBuilder.addPart("stamperStr", new StringBody(arr.toString() , 
				ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		multipartEntityBuilder.addPart("fileName", new StringBody(fileName , 
				ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
		HttpEntity reqEntity = multipartEntityBuilder
				.build();
		String result = HttpUtil.doPost(head, reqEntity);
    	return result;
    }
}
