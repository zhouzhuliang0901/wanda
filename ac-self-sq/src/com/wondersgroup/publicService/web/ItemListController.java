package com.wondersgroup.publicService.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.common.tables.SQItems;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.publicService.bean.SelmItemOrgan;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;
import com.wondersgroup.publicService.service.SelfDeclareSqService;
import com.wondersgroup.publicService.utils.ItemGuidUtil;

@Controller
public class ItemListController {
	
	@Autowired
	private SelfDeclareSqService selfDeclareSqService;
	
	 /**
     * 获取事项列表（中台服务：上海市政务服务事项管理系统数据发布接口服务）
     * 
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/selfDeclare/getItemInfoByXD.do")
    public void getItemInfoByXD(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	// y：获取社区事项；n：获取非社区（市区）事项
    	String flag = req.getParameter("flag");
    	// 市区区划编码
    	String areacode = req.getParameter("areacode");
    	
    	String accesstoken = "f27315e1-ec88-42e3-ae08-796ac4335999";
    	
    	String appNameForList = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			if("y".equals(flag)){
				appNameForList = "a2cca1bc-b4bc-4f36-b7a0-1869181ea54b";
			} else if("n".equals(flag)){
				appNameForList = "7a3de8ad-baeb-4b41-b77b-c3cb78002b13";
			}
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			if("y".equals(flag)){
				appNameForList = "78672147-b8a3-4b4e-af07-355d4608ce0d";
			} else if("n".equals(flag)){
				appNameForList = "3d45bc55-b6cd-4dad-8372-f4cf62869541";
			}
		}
    	String signatureForList = HttpUtil.getSignature(appNameForList);
        Map<String, String> headForList = HttpUtil.setHttpHeard(signatureForList, appNameForList);
        
    	String appNameForInfo = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "966a4c9d-dfa6-4e0c-87bc-53643f5ab3de";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "d1f15aa3-6cf1-4ae2-8e94-03847b008ad6";
		}
    	String signatureForInfo = HttpUtil.getSignature(appNameForInfo);
        Map<String, String> headForInfo = HttpUtil.setHttpHeard(signatureForInfo, appNameForInfo);
        
        String fromtimestamp = "";
        JSONObject data = new JSONObject();
        data.put("accesstoken", accesstoken);
        data.put("qlkind", "");
        if("y".equals(flag)){
        	data.put("arealevel", "");
        } else if("n".equals(flag)){
        	 data.put("areacode", areacode);
             data.put("orgcode", "");
             data.put("businesscode", "");
             data.put("limitnum", "");
        }
        
        String contentType = "application/json;charset=utf-8";
        JSONArray fwznList = new JSONArray();
        
        List<SelmZhallItemInfo> itemList =  ItemGuidUtil.exportExcelItemId();
        Map<String, String> codeMap = new HashMap<String, String>();
		for(SelmZhallItemInfo item : itemList){
			codeMap.put(item.getStItemCode(), item.getStItemNo());
		}
        do{
        	data.put("fromtimestamp", fromtimestamp);
        	System.out.println("列表入参："+data.toString());
        	String result = ItemGuidUtil.getResponse(headForList,data.toString(),contentType);
          	try{
          		JSONObject json = JSONObject.fromObject(result);
          		String code = json.optJSONObject("status").optString("code");
          		if("success".equals(code)){
          			fwznList = json.optJSONObject("custom").optJSONArray("fwznList");
          			int sumNum = json.optJSONObject("custom").optInt("sumNum");
          			System.out.println("事项总数："+sumNum);
          			String lastTimestamp = json.optJSONObject("custom").optString("lastTimestamp");
          			fromtimestamp = lastTimestamp;
          			System.out.println("lastTimestamp："+lastTimestamp);
          			for(int iLoop = 0;iLoop<fwznList.size();iLoop++){
          		        JSONObject infoParam = new JSONObject();
          		        infoParam.put("accesstoken", accesstoken);
          		        infoParam.put("rowguid", fwznList.getJSONObject(iLoop).optString("rowguid"));
          		        String info = ItemGuidUtil.getResponse(headForInfo,infoParam.toString(),contentType);
          		        
          		        JSONObject infoJson = JSONObject.fromObject(info);
          		        JSONObject auditfwzn = infoJson.optJSONObject("custom").optJSONObject("auditfwzn");
          		        JSONObject auditfwznextend = infoJson.optJSONObject("custom").optJSONObject("auditfwznextend");
          		        String itemNo = auditfwzn.optString("catalog_code");
          		        String itemCode = auditfwzn.optString("item_code");
//          		        if(StringUtils.isNotEmpty(itemNo) && itemNo.equals(codeMap.get(itemCode))){
          		        	SelmZhallItemInfo itemInfo = new SelmZhallItemInfo();
              		        itemInfo.setStId(fwznList.getJSONObject(iLoop).optString("rowguid"));
              		        itemInfo.setStItemNo(itemNo);
              		        itemInfo.setStItemCode(itemCode);
              		        itemInfo.setStItemName(auditfwzn.optString("ql_name"));
              		        itemInfo.setStSubItemName(auditfwzn.optString("ql_name"));
              		        itemInfo.setStItemType(SQItems.Type.GetName(auditfwzn.optString("ql_kind")));
              		        
              		        String ql_object = auditfwzn.optString("ql_object");
              		        String nmBelong = "";
              		        if(ql_object.contains("1")){
              		        	nmBelong +="个人#";
              		        }
              		        if(ql_object.contains("2")
              		        		||ql_object.contains("3")
              		        		||ql_object.contains("4")
              		        		||ql_object.contains("5")
              		        		||ql_object.contains("6")){
              		        	nmBelong +="法人#";
              		        }
              		        if(ql_object.contains("9")){
              		        	nmBelong +="其他组织#";
              		        }
              		        itemInfo.setNmBelong(nmBelong);
              		        String orgCode = auditfwzn.optString("org_code");
              		        SelmItemOrgan organ = selfDeclareSqService.getOrganByCode(orgCode);
              		        itemInfo.setStOrgCode(organ.getStShCode());
              		        itemInfo.setStOrgName(organ.getStOuname());

              		        itemInfo.setDtCreate(new Timestamp(System.currentTimeMillis()));
              		        itemInfo.setStRemove(0);
              		       
              		        itemInfo.setStItemTenCode(auditfwznextend.optString("st_ten_code"));
              		        itemInfo.setStTransactName(auditfwznextend.optString("ywqx"));
              		        
              				try{
                 				selfDeclareSqService.addZhallItem(itemInfo);
                 			} catch (Exception e) {
                 				Log.debug("事项："+itemInfo.getStItemName()+"，ID："+itemInfo.getStId()+"，保存失败："+e.getMessage().toString());
            				}
//          		        }
          			}
          		}
          	} catch (Exception e) {
          		Log.debug("运行异常："+e.getMessage().toString());
    		}
        } while(fwznList.size() == 50);
      	AciJsonHelper.writeJsonPResponse(req, res, "事项信息获取完成！");
    }
    
    /**
     * 请求组织机构列表数据（中台服务：上海市政务服务事项管理系统数据发布接口服务）
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/selfDeclare/getOrgListInfo.do")
    public void getOrgListInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String areacode = req.getParameter("areacode");
    	
    	String accesstoken = "f27315e1-ec88-42e3-ae08-796ac4335999";
		
		String appNameForList = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForList = "0dbbb1b4-6337-4229-8129-03fb47cc327c";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForList = "00886b23-01d0-4dfe-bdcf-a3f0d863811e";
		}
		String signatureForList = HttpUtil.getSignature(appNameForList);
        Map<String, String> headForList = HttpUtil.setHttpHeard(signatureForList, appNameForList);
		
		String appNameForInfo = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "e9b9b609-e471-481a-a387-414596991b27";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "0e5d6379-8497-455c-8354-7711e496a2ce";
		}
		String signatureForInfo = HttpUtil.getSignature(appNameForInfo);
        Map<String, String> headForInfo = HttpUtil.setHttpHeard(signatureForInfo, appNameForInfo);
        
        String fromtimestamp = "";
        JSONObject listParam = new JSONObject();
        listParam.put("accesstoken", accesstoken);
        listParam.put("areacode", areacode);
        listParam.put("orgcode", "");
        listParam.put("businesscode", "");
        
        JSONArray ouList = new JSONArray();
        do{
        	listParam.put("fromtimestamp", fromtimestamp);
        	String result = ItemGuidUtil.getResponse(headForList,listParam.toString(),"application/json;charset=utf-8");
        	try{
        		JSONObject json = JSONObject.fromObject(result);
          		String code = json.optJSONObject("status").optString("code");
          		if("success".equals(code)){
          			ouList = json.optJSONObject("custom").optJSONArray("ouList");
          			int sumNum = json.optJSONObject("custom").optInt("sumNum");
          			System.out.println("部门列表总数："+sumNum);
          			String lastTimestamp = json.optJSONObject("custom").optString("lastTimestamp");
          			fromtimestamp = lastTimestamp;
          			for(int iLoop = 0;iLoop<ouList.size();iLoop++){
          				String ouguid = ouList.getJSONObject(iLoop).optString("ouguid");
          				JSONObject infoParam = new JSONObject();
          				infoParam.put("accesstoken", accesstoken);
          				infoParam.put("ouguid", ouguid);
          				String info = ItemGuidUtil.getResponse(headForInfo,infoParam.toString(),"application/json;charset=utf-8");
          				SelmItemOrgan organ = new SelmItemOrgan();
          				try{
              				JSONObject infoJson = JSONObject.fromObject(info);
              				String infoCode = infoJson.optJSONObject("status").optString("code");
              				if("success".equals(infoCode)){
              					JSONObject auditorg = infoJson.optJSONObject("custom").optJSONObject("auditorg");
              					organ.setStOuguid(auditorg.optString("ouguid"));
              					organ.setStOrgCode(auditorg.optString("org_code"));
              					organ.setStOuname(auditorg.optString("ouname"));
              					organ.setStShortName(auditorg.optString("oushortname"));
              					organ.setStOucode(auditorg.optString("oucode"));
              					organ.setStShCode(auditorg.optString("sh_code"));
              					organ.setIsOnuse(auditorg.optString("isnouse"));
              					organ.setStDeptLevel(auditorg.optString("dept_level"));
              					organ.setStAreaCode(auditorg.optString("areacode"));
              					organ.setStParentOuguid(auditorg.optString("parentouguid"));
              					
              					selfDeclareSqService.addZhallItemOrgan(organ);
              				} else {
              					Log.debug("部门信息查询失败，部门主键："+ouguid);
              				}
          				} catch (Exception e) {
          					Log.debug("部门信息查询失败，部门主键："+ouguid+"------"+e.getMessage().toString());
    					}
          			}
          		}
        	} catch (Exception e) {
        		Log.debug("部门列表获取失败："+e.getMessage().toString());
    		}
        }while(ouList.size() == 50);
    	
    	AciJsonHelper.writeJsonPResponse(req, res, "部门信息获取完成！");
    }
    
    /**
     * 根据事项主键获取事项的办事指南（中台服务：上海市政务服务事项管理系统数据发布接口服务）
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/selfapi/selfDeclare/getItemDetailByIDForSQ.do")
    public void getItemDetailByIDForSQ(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
    	String stId = req.getParameter("stId");
    	String accesstoken = "f27315e1-ec88-42e3-ae08-796ac4335999";
    	
    	String appNameForInfo = "";
		if("test".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "966a4c9d-dfa6-4e0c-87bc-53643f5ab3de";
		} else if("product".equals(Config.get("reindeer.huidao.environment"))){
			appNameForInfo = "d1f15aa3-6cf1-4ae2-8e94-03847b008ad6";
		}
    	String signatureForInfo = HttpUtil.getSignature(appNameForInfo);
        Map<String, String> headForInfo = HttpUtil.setHttpHeard(signatureForInfo, appNameForInfo);
    	
    	JSONObject infoParam = new JSONObject();
	    infoParam.put("accesstoken", accesstoken);
	    infoParam.put("rowguid", stId);
    	String info = ItemGuidUtil.getResponse(headForInfo,infoParam.toString(),"application/json;charset=utf-8");
    	
    	try{
    		JSONObject infoJson = JSONObject.fromObject(info);
      		String code = infoJson.optJSONObject("status").optString("code");
      		if("success".equals(code)){
      			JSONObject auditfwzn = infoJson.optJSONObject("custom").optJSONObject("auditfwzn");
      			String orgCode = auditfwzn.optString("org_code");
      			SelmItemOrgan organ = selfDeclareSqService.getOrganByCode(orgCode);
      			auditfwzn.put("org_name", organ.getStOuname());
      			info = infoJson.toString();
      		}
    	} catch (Exception e) {
    		Log.debug(e.getMessage().toString());
		}
    	
    	AciJsonHelper.writeJsonPResponse(req, res, info);
    }
}
