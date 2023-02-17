package com.wondersgroup.business.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;
import wfc.service.database.Sequence;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.dao.SelmAttachDao;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.business.excelBean.SelmQueryHisExcel;
import com.wondersgroup.business.service.SelmQueryHisService;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.selmAssist.bean.SelmAssist;
import com.wondersgroup.selmAssist.dao.SelmAssistDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.statistics.bean.SelmStatistics;

import freemarker.template.SimpleDate;


/**
 * 工作台模块使用历史记录业务实现
 * 
 * @author scalffold
 * 
 */
@SuppressWarnings("all")
@Service
@Transactional
public class SelmQueryHisServiceImpl implements SelmQueryHisService {
	
	
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private SelmAttachDao selmAttachDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private SelmAssistDao selmAssistDao;
	@Autowired
	private SmsUserDao smsUserDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	/**
	 * 根据主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}获取工作台模块使用历史记录
	 * 
	 * @param stQueryHisId
	 *            工作台模块使用历史记录主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}
	 * @return 工作台模块使用历史记录实例
	 */
	@Override
	public SelmQueryHis getSelmQueryHis(String stQueryHisId) {
		if (StringUtils.trimToEmpty(stQueryHisId).isEmpty())
			throw new NullPointerException("Parameter stQueryHisId cannot be null.");
		return selmQueryHisDao.get(stQueryHisId);
	}

	/**
	 * 查询工作台模块使用历史记录列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 工作台模块使用历史记录列表
	 */
	@Override
	public JSONObject queryHisList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String username = httpReqRes.getParameter("username");
		String stAssistId = httpReqRes.getParameter("stAssistId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String stDeviceMachined = httpReqRes.getParameter("stMachineId");
		String stItemName = httpReqRes.getParameter("stItemName");
		String stName = httpReqRes.getParameter("stName");
		String stModuleOp = httpReqRes.getParameter("stModuleOp");
		String stIdentityNo = httpReqRes.getParameter("stIdentityNo");
		String stMobile = httpReqRes.getParameter("stMobile");
		String street = httpReqRes.getParameter("stStreet");
		String district = httpReqRes.getParameter("stDistrict");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		Conditions usercon = Conditions.newAndConditions();
		SmsUser smsUser = new SmsUser();
		if (username != null && !StringUtils.trim(username).isEmpty()) {
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			smsUser = smsUserL.get(0);
		}
		
		Conditions conds = Conditions.newAndConditions();
		//区账号
		if (stPermission.contains("area")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				conds.add(new Condition("idi.ST_AREA_ID", Condition.OT_EQUAL,stAreaId));
			}
		}
		//厂商账号
		if(stPermission.contains("changshang")){
			String stUserName = smsUser.getStUserName();
			Conditions typeConds = Conditions.newAndConditions();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(typeInfoName.indexOf("（")+1, typeInfoName.indexOf("）"));
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		//银行账号
		if(stPermission.contains("bank")){
			String stUserName = smsUser.getStUserName();
			Conditions typeConds = Conditions.newAndConditions();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				//System.out.println("typeInfoName-------"+typeInfoName);
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		//街道查询
		if (street != null && !StringUtils.trim(street).isEmpty()) {
			conds.add(new Condition("ia.ST_STREET", Condition.OT_LIKE,street));
		}
		// 所属区查询
		if (district != null && !StringUtils.trim(district).isEmpty()) {
			conds.add(new Condition("ia.ST_DISTRICT", Condition.OT_LIKE, district));
		}
		
		if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
			conds.add(new Condition("sqh.ST_ITEM_NAME", Condition.OT_LIKE, stItemName));
		}
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("sqh.ST_NAME", Condition.OT_LIKE, stName));
		}
		if (stIdentityNo != null && !StringUtils.trim(stIdentityNo).isEmpty()) {
			conds.add(new Condition("sqh.ST_IDENTITY_NO", Condition.OT_LIKE, stIdentityNo));
		}
		if (stDeviceMac != null && !StringUtils.trim(stDeviceMac).isEmpty()) {
			conds.add(new Condition("sqh.ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceMac));
		}
		if (stDeviceMachined != null && !StringUtils.trim(stDeviceMachined).isEmpty()&&!stDeviceMachined.equals("null")) {
			conds.add(new Condition("sqh.ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceMachined));
		}
		if (stMobile != null && !StringUtils.trim(stMobile).isEmpty()) {
			conds.add(new Condition("sqh.ST_MOBILE", Condition.OT_LIKE, stMobile));
		}
		if (stModuleOp != null && !StringUtils.trim(stModuleOp).isEmpty()) {
			conds.add(new Condition("sqh.ST_MODULE_OP", Condition.OT_LIKE, stModuleOp));
		}
		if (stAssistId != null && !StringUtils.trim(stAssistId).isEmpty()) {
			if(stAssistId .equals("1")){
				conds.add(new Condition("sqh.ST_ASSIST_ID", Condition.OT_EQUAL, null));
			}else{
				conds.add(new Condition("sqh.ST_ASSIST_ID", Condition.OT_UNEQUAL, null));
			}
			
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER, Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		String suffix = "ORDER BY sqh.DT_CREATE";
		if (orderName != null) {
			if ("stItemName".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_ITEM_NAME " + orderType.toUpperCase() ;
			} else if ("stModuleName".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_MODULE_NAME " + orderType.toUpperCase();
			} else if ("stName".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_NAME " + orderType.toUpperCase();
			} else if ("stIdentityNo".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_IDENTITY_NO " + orderType.toUpperCase();
			} else if ("stMobile".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_MOBILE " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY sqh.DT_CREATE " + orderType.toUpperCase();
			} else if ("stBusinessNo".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_BUSINESS_NO " + orderType.toUpperCase();
			} else if ("stOpResult".equals(orderName)) {
				suffix = "ORDER BY sqh.ST_OP_RESULT " + orderType.toUpperCase();
			}
		}
		
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (httpReqRes != null) {
			String length = httpReqRes.getParameter("length");
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (httpReqRes.getParameter("start") != null) {
				int start = Integer.valueOf(httpReqRes.getParameter("start"));
				if (start != 0) {
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		
		
		List<SelmQueryHis>	selmQueryHisList = selmQueryHisDao.querySelm(conds, suffix, pageSize, currentPage);
		for (SelmQueryHis selmQueryHis : selmQueryHisList) {
			SelmAssist selmAssist = selmAssistDao.get(selmQueryHis.getStAssistId());
			if(selmAssist == null){
				selmQueryHis.setStAssistId("");
			}else{
				selmQueryHis.setStAssistId(selmAssist.getStAssistName());
			}
		}
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmQueryHisList,
							SelmQueryHis.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmQueryHisList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmQueryHisList);
		return returnObj;
	}
	
	
	
	/**
	 * 查询工作台模块使用历史记录列表Excel查询导出
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 工作台模块使用历史记录列表
	 */
	@Override
	public JSONObject queryHisListExcel(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String username = httpReqRes.getParameter("username");
		String stAssistId = httpReqRes.getParameter("stAssistId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String stDeviceMachined = httpReqRes.getParameter("stMachineId");
		String stItemName = httpReqRes.getParameter("stItemName");
		String stName = httpReqRes.getParameter("stName");
		String stModuleOp = httpReqRes.getParameter("stModuleOp");
		String stIdentityNo = httpReqRes.getParameter("stIdentityNo");
		String stMobile = httpReqRes.getParameter("stMobile");
		String street = httpReqRes.getParameter("stStreet");
		String district = httpReqRes.getParameter("stDistrict");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		
		
		Conditions usercon = Conditions.newAndConditions();
		SmsUser smsUser = new SmsUser();
		if (username != null && !StringUtils.trim(username).isEmpty()) {
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			smsUser = smsUserL.get(0);
		}
		
		Conditions conds = Conditions.newAndConditions();
		//区账号
		if (stPermission.contains("area")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				conds.add(new Condition("idi.ST_AREA_ID", Condition.OT_EQUAL,stAreaId));
			}
		}
		//厂商账号
		if(stPermission.contains("changshang")){
			String stUserName = smsUser.getStUserName();
			Conditions typeConds = Conditions.newAndConditions();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(typeInfoName.indexOf("（")+1, typeInfoName.indexOf("）"));
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		//银行账号
		if(stPermission.contains("bank")){
			String stUserName = smsUser.getStUserName();
			Conditions typeConds = Conditions.newAndConditions();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				//System.out.println("typeInfoName-------"+typeInfoName);
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		
		//街道查询
		if (street != null && !StringUtils.trim(street).isEmpty()) {
			conds.add(new Condition("ia.ST_STREET", Condition.OT_LIKE,street));
		}
		// 所属区查询
		if (district != null && !StringUtils.trim(district).isEmpty()) {
			conds.add(new Condition("ia.ST_DISTRICT", Condition.OT_LIKE, district));
		}
		if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
			conds.add(new Condition("sqh.ST_ITEM_NAME", Condition.OT_LIKE, stItemName));
		}
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("sqh.ST_NAME", Condition.OT_LIKE, stName));
		}
		if (stIdentityNo != null && !StringUtils.trim(stIdentityNo).isEmpty()) {
			conds.add(new Condition("sqh.ST_IDENTITY_NO", Condition.OT_LIKE, stIdentityNo));
		}
		if (stDeviceMac != null && !StringUtils.trim(stDeviceMac).isEmpty()) {
			conds.add(new Condition("sqh.ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceMac));
		}
		if (stDeviceMachined != null && !StringUtils.trim(stDeviceMachined).isEmpty()&&!stDeviceMachined.equals("null")) {
			conds.add(new Condition("sqh.ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceMachined));
		}
		if (stMobile != null && !StringUtils.trim(stMobile).isEmpty()) {
			conds.add(new Condition("sqh.ST_MOBILE", Condition.OT_LIKE, stMobile));
		}
		if (stModuleOp != null && !StringUtils.trim(stModuleOp).isEmpty()) {
			conds.add(new Condition("sqh.ST_MODULE_OP", Condition.OT_LIKE, stModuleOp));
		}
		if (stAssistId != null && !StringUtils.trim(stAssistId).isEmpty()) {
			if(stAssistId .equals("1")){
				conds.add(new Condition("sqh.ST_ASSIST_ID", Condition.OT_EQUAL, null));
			}else{
				conds.add(new Condition("sqh.ST_ASSIST_ID", Condition.OT_UNEQUAL, null));
			}
			
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER, Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		String suffix = "ORDER BY sqh.DT_CREATE";
		
		List<SelmQueryHisExcel>	selmQueryHisList = selmQueryHisDao.queryExcel(conds,suffix);
		
		//结果生成excel
		String filenName = "办件信息（" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "）.xls";
		try {
	         // 设置相应类型
			 httpReqRes.getResponse().setContentType("application/vnd.ms-excel");
			 //设置相应头，attachment:以附件的形式进行下载
			 httpReqRes.getResponse().setHeader("content-disposition","attachment;filename="+ java.net.URLEncoder.encode(filenName, "UTF-8"));
	     	 Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("办件查询信息表","办件信息"), SelmQueryHisExcel.class, selmQueryHisList);
			
			 workbook.write(httpReqRes.getResponse().getOutputStream());
	     	 //((OutputStream) workbook).close();
		} catch (Exception e) {
	      e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 根据主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}删除工作台模块使用历史记录
	 * 
	 * @param stQueryHisId
	 *            工作台模块使用历史记录主键 {@link SelmQueryHis#ST_QUERY_HIS_ID}
	 */
	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stQueryHisIdList = httpReqRes.getRequest().getParameterValues(
				"stQueryHisId[]");
		if (stQueryHisIdList == null) {
			String stQueryHisId = httpReqRes.getRequest()
					.getParameter("stQueryHisId");
			if (stQueryHisId != null
					&& !StringUtils.trimToEmpty(stQueryHisId).isEmpty()) {
				selmQueryHisDao.delete(stQueryHisId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		selmQueryHisDao.delete(stQueryHisIdList);
	}

	/**
	 * 保存或更新工作台模块使用历史记录
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 工作台模块使用历史记录实例
	 */
	@Override
	public SelmQueryHis saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmQueryHis.ST_QUERY_HIS_ID
		String stQueryHisId = wrapper.getParameter(SelmQueryHis.ST_QUERY_HIS_ID);
		SelmQueryHis oldSelmQueryHis = null;
		if (!StringUtils.trimToEmpty(stQueryHisId).isEmpty()) {
			oldSelmQueryHis = selmQueryHisDao.get(stQueryHisId);
		}
		if (oldSelmQueryHis == null) {// new
			SelmQueryHis newSelmQueryHis = (SelmQueryHis) t4r.toBean(SelmQueryHis.class);
			newSelmQueryHis.setStQueryHisId(UUID.randomUUID().toString());
			selmQueryHisDao.add(newSelmQueryHis);
			return newSelmQueryHis;
		}else {// update
			oldSelmQueryHis = (SelmQueryHis) t4r.toBean(oldSelmQueryHis, SelmQueryHis.class);
			if(oldSelmQueryHis.getStOpResult().equals("成功")){
				oldSelmQueryHis.setStOpResult("SUCCESS");
			}
			selmQueryHisDao.update(oldSelmQueryHis);
			return oldSelmQueryHis;
		}
	}

	
	/**
	 * 获取附件表里的数据
	 */
	@Override
	public SelmAttach getSelmAttach(SelmQueryHis selmQueryHis) {
		String stSubmitDataId = selmQueryHis.getStSubmitDataId();
		if (stSubmitDataId != null && !StringUtils.trim(stSubmitDataId).isEmpty()) {
			SelmAttach attach = selmAttachDao.get(stSubmitDataId);
			return attach;
		}
		
		return null;
		
		
	}

	@Override
	public SelmAttach getSelmAttach(String stAttachId) {
		if (StringUtils.trimToEmpty(stAttachId).isEmpty())
			throw new NullPointerException(
					"Parameter stAttachId cannot be null.");
		SelmAttach selmAttach = selmAttachDao.get(stAttachId);
		/*if (selmAttach != null) {
			byte[] blContent = selmAttachDao.getAttachFildById(selmAttach.getStAttachId());
			selmAttach.setBlContent(blContent);
			return selmAttach;
		}*/
		return selmAttach;
		

	}
	/**
	 * 办件信息统计
	 */
	@Override
	public JSONObject statistics(HttpReqRes httpReqRes) {
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stAreaName = httpReqRes.getParameter("stAreaName");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stItemName = httpReqRes.getParameter("stItemName");
		String stMac = httpReqRes.getParameter("stMac");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions condAreaId = Conditions.newAndConditions();
		Conditions condOr = Conditions.newOrConditions();
		if (stPermission != null && !StringUtils.trim(stPermission).isEmpty() && stPermission.equals("null")) {
			if (!stPermission.equals("project_admin")) {
				if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
					condAreaId.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
							stAreaId));
					List<InfopubDeviceInfo> query = infopubDeviceInfoDao.query(condAreaId,null);
					for (InfopubDeviceInfo infopubDeviceInfo : query) {
						String stMachineId = infopubDeviceInfo.getStDeviceMac();
						if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
							
							condOr.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMachineId));
						}
					}
				}else {
					throw new NullPointerException("AreaId不能为空");
				}
			}
		}
		//区域查询
		if (stAreaName != null && !StringUtils.trim(stAreaName).isEmpty()) {
			InfopubArea infopubArea = infopubAreaDao.getName(stAreaName);
			condAreaId.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
					infopubArea.getStAreaId()));
			List<InfopubDeviceInfo> query = infopubDeviceInfoDao.query(condAreaId,null);
			for (InfopubDeviceInfo infopubDeviceInfo : query) {
				String stMachineId = infopubDeviceInfo.getStDeviceMac();
				if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
					
					condOr.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMachineId));
				}
			}
		}
		Conditions conds = Conditions.newAndConditions();
		if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE, stItemName));
		}
		
		//处理时间，根据时间去不同的表查询相关数据
		int year = 2021;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = null;
		Date eDate = null;
		try {
			if(startDate != null && !StringUtils.trim(startDate).isEmpty()){
				sDate = sdf.parse(startDate);
			}else{
				//查当年的
				sDate = new Date();
				int y = cal.get(Calendar.YEAR);
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(y+"-01-01" + " 00:00:00")));
			}
			if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
				eDate = sdf.parse(endDate);
			}else{
				eDate = new Date();
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal.setTime(sDate);
		int syear = cal.get(Calendar.YEAR);
		cal.setTime(eDate);
		int eyear = cal.get(Calendar.YEAR);
		//设置查哪年的表
		year = eyear;
		//跨年查询不可以,如果跨年查询则查只查今年的办件
		if(syear != eyear){
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(eyear+"-01-01" + " 00:00:00")));
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}else{
			if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(startDate + " 00:00:00")));
			}
			if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
		}
		
		int pageSize=0;
		int currentPage = 0;
		if (stMac != null && !StringUtils.trim(stMac).isEmpty()) {
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMac));
			pageSize = 5;
			currentPage = 0;
		}else{
			pageSize = Integer.MAX_VALUE / 2;
			currentPage = 1;
			if (httpReqRes != null) {
				String length = httpReqRes.getParameter("length");
				if (length != null) {
					pageSize = Integer.valueOf(length);
				}
				if (httpReqRes.getParameter("start") != null) {
					int start = Integer.valueOf(httpReqRes.getParameter("start"));
					if (start != 0) {
						currentPage = Integer.valueOf(start) / pageSize + 1;
					}
				}
			}
		}
		 conds.add(condOr);
		String suffix = "GROUP BY ST_ITEM_NAME ORDER BY COUNT(ST_ITEM_NAME)";
		if (orderName != null) {
			if ("stExt1".equals(orderName)) {
				suffix = "GROUP BY ST_ITEM_NAME ORDER BY COUNT(ST_ITEM_NAME) " + orderType.toUpperCase() ;
			}
		}
		List<SelmQueryHis> queryItem = selmQueryHisDao.queryItem(conds, suffix,pageSize, currentPage, year);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(queryItem,
							SelmQueryHis.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (stMac != null && !StringUtils.trim(stMac).isEmpty() && !stMac.equals("null")) {
			total=queryItem.size()+"";
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", queryItem.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", queryItem);
		return returnObj;
	}
	
	
	/**
	 * 办件信息统计
	 */
	@Override
	public JSONObject statisticsDay(HttpReqRes httpReqRes) {
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		/*String stAreaId="sop1s1231-0a36-472b-abd5-f821deea7080";
		String stPermission="as";*/
		String stItemName = httpReqRes.getParameter("stItemName");
		/*String stItemName = "婚姻档案查询";*/
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		
		Conditions condOr = Conditions.newOrConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				Conditions condAreaId = Conditions.newAndConditions();
				condAreaId.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						stAreaId));
				List<InfopubDeviceInfo> query = infopubDeviceInfoDao.query(condAreaId,null);
				for (InfopubDeviceInfo infopubDeviceInfo : query) {
					String stMachineId = infopubDeviceInfo.getStDeviceCode();
					if (stMachineId != null && !StringUtils.trim(stMachineId).isEmpty()) {
						
						condOr.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMachineId));
					}
				}
			}else {
				throw new NullPointerException("AreaId不能为空");
			}
		}
		
		Conditions conds = Conditions.newAndConditions();
		if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NAME", Condition.OT_EQUAL, stItemName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (httpReqRes != null) {
			String length = httpReqRes.getParameter("length");
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (httpReqRes.getParameter("start") != null) {
				int start = Integer.valueOf(httpReqRes.getParameter("start"));
				if (start != 0) {
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		conds.add(condOr);
		String suffix ="";
		if(DB.getDatabaseName()=="MySQL"){
			suffix = "GROUP BY DATE_FORMAT(DT_CREATE,'%Y-%m-%d') ";
		}else{
			suffix = "GROUP BY convert(char(10),DT_CREATE,102) ";
		}
		List<SelmQueryHis> queryItem = selmQueryHisDao.queryItemDay(conds, suffix,pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(queryItem,
							SelmQueryHis.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", queryItem.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", queryItem);
		return returnObj;
	}

	@Override
	public JSONObject selmQuertTop(HttpReqRes httpReqRes) {
		//单独区域查询办件top20
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "GROUP BY ST_ITEM_NAME ORDER BY COUNT(ST_ITEM_NAME) DESC limit 0,20";
		}else{
			suffix = "GROUP BY ST_ITEM_NAME ORDER BY COUNT(ST_ITEM_NAME) DESC";
		}
		JSONArray queryItem = selmQueryHisDao.selmQuertTop(null, suffix);
		JSONObject returnObj = new JSONObject();
		returnObj.put("data", queryItem);
		return returnObj;
	}

	@Override
	public JSONObject areaQueryHis(HttpReqRes httpReqRes) {
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		Conditions condArea= Conditions.newAndConditions();
		List<InfopubAddress> selmStatisticsList = new ArrayList<InfopubAddress>();
		InfopubArea infopubArea = infopubAreaDao.getName("上海市");
		condArea.add(new Condition("ST_PARENT_AREA_ID", Condition.OT_LIKE, infopubArea.getStAreaId()));
		
		
		List<InfopubArea> infopubAreaName = infopubAreaDao.query(condArea, null);
		for (InfopubArea infopubAreaId : infopubAreaName) {
			String stAreaId = infopubAreaId.getStAreaId();
			Conditions cond2 = Conditions.newAndConditions();
			cond2.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
					cond2, null);
			//办件量
			Conditions cond1 = Conditions.newOrConditions();
			int selmCount = 0;
			for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
				String stDeviceCode = infopubDeviceInfo.getStDeviceMac();
				cond1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceCode));
			}
			Conditions conds = Conditions.newAndConditions();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar c = Calendar.getInstance();
		    String endDate = format.format(date);
		    c.setTime(date);
		    c.add(Calendar.MONTH, -1);
		    Date m = c.getTime();
		    String startDate = format.format(m);
		    conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
		    		Timestamp.valueOf(startDate + " 00:00:00")));
		    conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
		    		Timestamp.valueOf(endDate + " 23:59:59")));
			conds.add(cond1);
			List<SelmQueryHis> selmQueryList = selmQueryHisDao.query(conds, null);
			int selmNum = selmQueryList.size();
			selmCount = selmCount + selmNum;
			InfopubAddress infopubAddressCount = new InfopubAddress();
			infopubAddressCount.setStDistrict(infopubAreaId.getStAreaName());
			infopubAddressCount.setStLabel(selmCount+"");//30天各区办件量
			infopubAddressCount.setStStreet(selmCount+"");//30天各区办件量
			selmStatisticsList.add(infopubAddressCount);
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("data", selmStatisticsList);
		return returnObj;
	}
	
	@Override
	public JSONObject sqhDeviceItem(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stMachineId");
		String stItemName = httpReqRes.getParameter("stItemName");
		String stName = httpReqRes.getParameter("stName");
		String stIdentityNo = httpReqRes.getParameter("stIdentityNo");
		String stMobile = httpReqRes.getParameter("stMobile");
		String startDate = httpReqRes.getParameter("startDate");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = httpReqRes.getParameter("endDate");
		//String stDeviceId = "00-E2-69-22-27-86";
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		Conditions condItemNo = Conditions.newOrConditions();
		conds.add(new Condition("info.ST_DEVICE_MAC", Condition.OT_EQUAL,
				stDeviceId));
		
		//事项名称
		List<SelmQueryHis> selmQueryHisList = selmQueryHisDao.queryNoItemName(stDeviceId);
		for (SelmQueryHis selmQueryHis : selmQueryHisList) {
			condItemNo.add(new Condition("ST_ITEM_NAME", Condition.OT_EQUAL,
	    			selmQueryHis.getStItemName()));
		}
		
		//事项编码匹配
		/*ArrayList selmItemNo = new ArrayList();
		List<SelmItem> selmItemList = selmItemDao.queryItemNo(conds, null);
		for (SelmItem selmItem : selmItemList) {
			selmItemNo.add(selmItem.getStItemNo());
		}
		ArrayList selmQueryItemNo = new ArrayList();
		
		String suffix1 = "GROUP BY sqh.ST_ITEM_NO";
		List<SelmQueryHis> selmQueryHisList = selmQueryHisDao.queryItemNo(conds, suffix1);
		for (SelmQueryHis selmQueryHis : selmQueryHisList) {
			selmQueryItemNo.add(selmQueryHis.getStItemNo());
		}
			for (Object itemNo : selmQueryItemNo) {
				if(selmItemNo.contains(itemNo)){
				    }else{
				    	//办件信息对应设备事项多出来的
				    	if(itemNo!=null){
				    		condItemNo.add(new Condition("ST_ITEM_NO", Condition.OT_EQUAL,
					    			itemNo));
				    	}
				  } 
			}*/
			int pageSize = Integer.MAX_VALUE / 2;
			int currentPage = 1;
			if (httpReqRes != null) {
				String length = httpReqRes.getParameter("length");
				if (length != null) {
					pageSize = Integer.valueOf(length);
				}
				if (httpReqRes.getParameter("start") != null) {
					int start = Integer.valueOf(httpReqRes.getParameter("start"));
					if (start != 0) {
						currentPage = Integer.valueOf(start) / pageSize + 1;
					}
				}
			}
			
			conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
					stDeviceId));
			//办件信息对应设备事项多出来的
	    	condItemNo.add(new Condition("ST_ITEM_NAME", Condition.OT_EQUAL,
	    			"++++"));
			conds.add(condItemNo);
			if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
				conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE, stItemName));
			}
			if (stName != null && !StringUtils.trim(stName).isEmpty()) {
				conds.add(new Condition("ST_NAME", Condition.OT_LIKE, stName));
			}
			if (stIdentityNo != null && !StringUtils.trim(stIdentityNo).isEmpty()) {
				conds.add(new Condition("ST_IDENTITY_NO", Condition.OT_LIKE, stIdentityNo));
			}
			if (stMobile != null && !StringUtils.trim(stMobile).isEmpty()) {
				conds.add(new Condition("ST_MOBILE", Condition.OT_LIKE, stMobile));
			}
			if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(startDate + " 00:00:00")));
			}
			if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
			String suffix = "ORDER BY DT_CREATE";
			if (orderName != null) {
				if ("stItemName".equals(orderName)) {
					suffix = "ORDER BY ST_ITEM_NAME " + orderType.toUpperCase() ;
				} else if ("stModuleName".equals(orderName)) {
					suffix = "ORDER BY ST_MODULE_NAME " + orderType.toUpperCase();
				} else if ("stName".equals(orderName)) {
					suffix = "ORDER BY ST_NAME " + orderType.toUpperCase();
				} else if ("stIdentityNo".equals(orderName)) {
					suffix = "ORDER BY ST_IDENTITY_NO " + orderType.toUpperCase();
				} else if ("stMobile".equals(orderName)) {
					suffix = "ORDER BY ST_MOBILE " + orderType.toUpperCase();
				} else if ("dtCreate".equals(orderName)) {
					suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
				} else if ("stBusinessNo".equals(orderName)) {
					suffix = "ORDER BY ST_BUSINESS_NO " + orderType.toUpperCase();
				} else if ("stOpResult".equals(orderName)) {
					suffix = "ORDER BY ST_OP_RESULT " + orderType.toUpperCase();
				}
			}
			
			List<SelmQueryHis> query = selmQueryHisDao.query(conds, suffix,pageSize,currentPage);
			
			String total = null;
			try {
				total = EasyUIJsonConverter.convertDataSetToJson(
						DataSet.convert(query,
								SelmQueryHis.class)).getString("total");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			JSONObject returnObj = new JSONObject();
			returnObj.put("recordsTotal", query.size());
			returnObj.put("recordsFiltered", total);
			returnObj.put("data", query);
			
			return returnObj;
	}

	@Override
	public JSONObject unInputDeviceMac(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stMachineId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stDeviceId != null && !StringUtils.trim(stDeviceId).isEmpty()) {
			conds.add(new Condition("SQH.ST_MACHINE_ID", Condition.OT_EQUAL, stDeviceId));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("SQH.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("SQH.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "GROUP BY SQH.ST_MACHINE_ID ORDER BY SQH.ST_MACHINE_ID";
		if (orderName != null) {
			if ("stMachineId".equals(orderName)) {
				suffix = "GROUP BY SQH.ST_MACHINE_ID ORDER BY SQH.ST_MACHINE_ID " + orderType.toUpperCase() ;
		}
		}
		int pageSize = Integer.MAX_VALUE / 2;
			int currentPage = 1;
			if (httpReqRes != null) {
				String length = httpReqRes.getParameter("length");
				if (length != null) {
					pageSize = Integer.valueOf(length);
				}
				if (httpReqRes.getParameter("start") != null) {
					int start = Integer.valueOf(httpReqRes.getParameter("start"));
					if (start != 0) {
						currentPage = Integer.valueOf(start) / pageSize + 1;
					}
				}
			}
		conds.add(new Condition("IDI.ST_DEVICE_MAC IS NULL"));
		List<SelmQueryHis> query = selmQueryHisDao.queryUnInDeviceMac(conds, suffix,pageSize,currentPage);
		
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query,
							SelmQueryHis.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", query.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", query);
		
		return returnObj;
	}

	/*@Override
	public JSONObject update60(HttpReqRes httpReqRes) {
		//String url = "‪C:\\Users\\Administrator\\Desktop\\60整改.xlsx";
		String url = "C:\\Users\\biany\\Desktop\\60整改1.xlsx";
		File excel = new File(url);
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject robj = new JSONObject();
		int sum = 0;
		//判断文件是否存在
		try {
			FileInputStream fis = new FileInputStream(excel);
			if(excel.isFile() && excel.exists()){
				String[] split = excel.getName().split("\\.");
				Workbook wb;
				//根据文件后缀(xls/xlsx)判断文件类型
				
				System.out.println(split[1]);
				if("xls".equals(split[1])){
					
					wb = new HSSFWorkbook(fis);
				}else if("xlsx".equals(split[1])){
					wb = new XSSFWorkbook(fis);
				}else{
					System.out.println("文件类型错误");
					obj.put("update", sum);
					return obj;
				}
				
				//开始解析
				Sheet sheet = wb.getSheetAt(0);  //读取工作表0
				
				int firstRowIndex = sheet.getFirstRowNum()+1; //第一行是标题，不读取，读取第二行
				int lastRowIndex = sheet.getLastRowNum();
				System.out.println("第一行："+firstRowIndex+",最后一行："+lastRowIndex);
				
				
				
				for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++){
					System.out.println("读取到第"+(rIndex+1)+"行");
					Row row = sheet.getRow(rIndex);// 获取地rIndex行
					if(null != row){
						int firstCellIndex = row.getFirstCellNum();  //获取第一列
						int lastCellIndex = row.getLastCellNum();  //获取最后一列
						System.out.println(firstCellIndex+"--"+lastCellIndex);
						
						Conditions conds = Conditions.newAndConditions();
						Map<String,Object> map = new HashMap<String,Object>();
						
						obj = new JSONObject();
						String name = "";
						String value = "";
						for(int cIndex = firstCellIndex; cIndex <= lastCellIndex; cIndex++){
							Cell cell = row.getCell(cIndex); //获取第rIndex行第cIndex列的单元格
							if(null != cell){
								if(cIndex == 0){
									//String id = cell.toString().trim();
									//conds.add(new Condition("ST_QUERY_HIS_ID",Condition.OT_EQUAL,id));
									name = cell.toString().trim();
								}else if(cIndex == 1){
									//String mac = cell.toString().trim();
									//map.put("ST_MACHINE_ID", mac);
									value = cell.toString().trim();
								}
								System.out.println(cIndex+"-"+cell.toString());
							}
						}
						
						obj.put(name, value);
						arr.add(obj);
						//update
						//int emp = selmQueryHisDao.update(map, conds);
						//sum++;
						
					}
				}
			}else{
				System.out.println("找不到指定文件");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//obj.put("update", sum);
		robj.put("data", arr);
		return robj;
	}*/
	
	@Override
	public JSONObject update60(HttpReqRes httpReqRes) {
		File jsonFile = new File(Thread.currentThread().getContextClassLoader().getResource("a.json").getFile());
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject sobj = new JSONObject();
		String jsonStr = "";
		int sum = 0;
		//判断文件是否存在
		try {
			System.out.println(jsonFile.getPath());
			if(jsonFile.isFile() && jsonFile.exists()){
				FileReader fileReader = new FileReader(jsonFile);
	            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
	            int ch = 0;
	            StringBuffer sb = new StringBuffer();
	            while ((ch = reader.read()) != -1) {
	                sb.append((char) ch);
	            }
	            fileReader.close();
	            reader.close();
	            jsonStr = sb.toString();
	            obj = JSONObject.fromObject(jsonStr);
	            arr = obj.getJSONArray("data");
	            System.out.println(arr.size());
	            for(int i = 0; i < arr.size(); i++){
	            	sobj = arr.getJSONObject(i);
	            	//System.out.println(sobj.toString());
	            	Set key = sobj.keySet();
	            	for(Object emp : key){
	            		String id = (String)emp;
	            		String mac = (String)sobj.get(emp);
	            		
	            		Conditions conds = Conditions.newAndConditions();
						Map<String,Object> map = new HashMap<String,Object>();
						conds.add(new Condition("ST_QUERY_HIS_ID",Condition.OT_EQUAL,id));
						map.put("ST_MACHINE_ID", mac);
						int a = selmQueryHisDao.update(map, conds);
						sum+=a;
	            	}
	            }
				
			}else{
				System.out.println("文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jo = new JSONObject();
		jo.put("update", sum);
		return jo;
	}
}
