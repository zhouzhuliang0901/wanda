package com.wondersgroup.infopub.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.sql.rowset.serial.SerialClob;
import javax.xml.bind.DatatypeConverter;

import coral.base.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.ws.security.saml.ext.bean.ConditionsBean;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.tool.helper.LogHelper;
import tw.tool.util.BeanUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.config.Config;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.base.utils.ToJsonUtil;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.infopub.bean.AreaView;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubAttachment;
import com.wondersgroup.infopub.bean.InfopubCompany;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoHis;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoMap;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubDeviceResult;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubGroup;
import com.wondersgroup.infopub.bean.InfopubGroupDevice;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.bean.InfopubOnoff;
import com.wondersgroup.infopub.bean.InfopubPsource;
import com.wondersgroup.infopub.bean.InfopubPsourceExt;
import com.wondersgroup.infopub.bean.InfopubPublish;
import com.wondersgroup.infopub.bean.InfopubWorkspace;
import com.wondersgroup.infopub.bean.InfopubWorkspaceUser;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubAttachmentDaoExt;
import com.wondersgroup.infopub.dao.InfopubCompanyDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDaoExt;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoHisDao;
import com.wondersgroup.infopub.dao.InfopubDeviceLogDaoExt;
import com.wondersgroup.infopub.dao.InfopubDeviceResultDao;
import com.wondersgroup.infopub.dao.InfopubDeviceResultHisDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.infopub.dao.InfopubGroupDao;
import com.wondersgroup.infopub.dao.InfopubGroupDeviceDao;
import com.wondersgroup.infopub.dao.InfopubOdeviceResultDao;
import com.wondersgroup.infopub.dao.InfopubOdeviceStatusDao;
import com.wondersgroup.infopub.dao.InfopubOnoffDaoExt;
import com.wondersgroup.infopub.dao.InfopubPsourceDaoExt;
import com.wondersgroup.infopub.dao.InfopubPublishDaoExt;
import com.wondersgroup.infopub.dao.InfopubWorkspaceDaoExt;
import com.wondersgroup.infopub.dao.InfopubWorkspaceUserDao;
import com.wondersgroup.infopub.service.InfoPubService;
import com.wondersgroup.infopub.util.AESUtils;
import com.wondersgroup.infopub.util.InfopubUtils;
import com.wondersgroup.infopub.util.ReadExcelData;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;
import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;
import com.wondersgroup.sms.resource.dao.SmsResourceAccessListDao;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

import coral.base.app.AppContext;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIJsonConverter;

@SuppressWarnings("all")
@Service
@Transactional
public class InfoPubServiceImpl implements InfoPubService {
	@Autowired
	private InfopubDeviceInfoDaoExt infopubDeviceInfoDao;
	@Autowired
	private InfopubDeviceInfoHisDao infopubDeviceInfoHisDao;
	@Autowired
	private InfopubOnoffDaoExt infopubOnoffDaoExt;
	@Autowired
	private InfopubDeviceLogDaoExt infopubDeviceLogDaoExt;
	@Autowired
	private InfopubAttachmentDaoExt infopubAttachmentDaoExt;
	@Autowired
	private InfopubWorkspaceDaoExt infopubWorkspaceDaoExt;
	@Autowired
	private InfopubWorkspaceUserDao infopubWorkspaceUserDao;
	@Autowired
	private SmsResourceAccessListDao smsResourceAccessListDao;
	@Autowired
	private InfopubGroupDao infopubGroupDao;
	@Autowired
	private InfopubGroupDeviceDao infopubGroupDeviceDao;
	@Autowired
	private InfopubPsourceDaoExt infopubPsourceDaoExt;
	@Autowired
	private InfopubPublishDaoExt infopubPublishDaoExt;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	@Autowired
	private InfopubOdeviceStatusDao infopubOdeviceStatusDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	@Autowired
	private InfopubOdeviceResultDao infopubOdeviceResultDao;
	@Autowired
	private InfopubDeviceResultDao infopubDeviceResultDao;
	@Autowired
	private InfopubDeviceResultHisDao infopubDeviceResultHisDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	@Autowired
	private InfopubCompanyDao infopubCompanyDao;
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private SmsUserDao smsUserDao;
	@Autowired
	private SmsRoleDao smsRoleDao;
	@Autowired
	private SmsOrganDao smsOrganDao;
	@Autowired
	private ToJsonUtil toJsonUtil;

	/**
	 * 获取设备信息列表
	 */
	@Override
	public JSONObject deviceInfoList(HttpReqRes httpReqRes) {
		List<InfopubDeviceInfo> infopubDeviceInfoList = new ArrayList<InfopubDeviceInfo>();
		String username = httpReqRes.getParameter("userName");
		String companyId = httpReqRes.getParameter("companyCodeId");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stTypeName = httpReqRes.getParameter("stTypeName");
		
		String deviceCode = httpReqRes.getParameter("deviceCode");
		String deviceMac = httpReqRes.getParameter("deviceMac");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		
		String deviceProvince = httpReqRes.getParameter("deviceProvince");
		String deviceCity = httpReqRes.getParameter("deviceCity");
		String deviceDistrict = httpReqRes.getParameter("deviceDistrict");
		String deviceStreet = httpReqRes.getParameter("deviceStreet");
		String deviceAddress = httpReqRes.getParameter("deviceAddress");
		
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		String suffix = "ORDER BY DT_CREATE DESC";
		/*if (orderName != null) {
			if ("stDeviceName".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_NAME " + orderType.toUpperCase();
			} else if ("stDeviceCode".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_CODE " + orderType.toUpperCase();
			} else if ("stDeviceAddress".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_ADDRESS "
						+ orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_UPDATE " + orderType.toUpperCase();
			} else if ("stDeviceIp".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_IP " + orderType.toUpperCase();
			} else if ("stDeviceMac".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_MAC " + orderType.toUpperCase();
			} else if ("stTypeId".equals(orderName)) {
				suffix = "ORDER BY ST_TYPE_ID " + orderType.toUpperCase();
			}
		}*/
		
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
	
		
		Conditions usercon = Conditions.newAndConditions();
		SmsUser smsUser = new SmsUser();
		if (username != null && !StringUtils.trim(username).isEmpty()) {
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			smsUser = smsUserL.get(0);
		}
		
		Conditions devicecond = Conditions.newAndConditions();
		devicecond.add(new Condition("ST_STATE", Condition.OT_UNEQUAL, new BigDecimal(0)));//过滤掉逻辑删除

		if(stPermission.contains("changshang")){
			List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
			String stUserName = smsUser.getStUserName();
			Conditions typeConds = Conditions.newAndConditions();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(typeInfoName.indexOf("（")+1, typeInfoName.indexOf("）"));
				if(stUserName.contains(typeInfoName)){
					typeConds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
			
			String typeId = httpReqRes.getParameter("typeId");
			if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE, typeId));
			}else{
				infopubDeviceType0 = infopubDeviceTypeDao.query(typeConds, null);
				List<String> stTypeList = new ArrayList<String>();
				for(InfopubDeviceType empt : infopubDeviceType0){
					stTypeList.add(empt.getStTypeId());	
				}
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_IN, stTypeList));	
			}
			
		}else if(stPermission.contains("bank")){
			List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
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
					typeConds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
			
			String typeId = httpReqRes.getParameter("typeId");
			if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE, typeId));
			}else{
				infopubDeviceType0 = infopubDeviceTypeDao.query(typeConds, null);
				List<String> stTypeList = new ArrayList<String>();
				for(InfopubDeviceType empt : infopubDeviceType0){
					System.out.println("设备类型："+empt.getStTypeId());
					stTypeList.add(empt.getStTypeId());	
				}
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_IN, stTypeList));	
			}
			
		}else if(stPermission.contains("area")){ //政务区设备
			String typeId = httpReqRes.getParameter("typeId");
			if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL, typeId));
			}else{
				List<String> tyleList = infopubDeviceTypeDao.getTypeIdList("政务服务%");
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_IN, tyleList));
			}

			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				devicecond.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
			}
			
		}else if(stPermission.contains("organ")){
			//部门类型
			String stOrganId = smsUser.getStOrganId();
			if(stOrganId != null && !StringUtils.trim(stOrganId).isEmpty()){
				devicecond.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
			}
		}else{
			//其他类型，查询所有设备
			String typeId = httpReqRes.getParameter("typeId");
			if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
				devicecond.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE, typeId));
			}
			
		}
		
		//判断省市区
		
		if (deviceProvince != null && !StringUtils.trim(deviceProvince).isEmpty()) {
			List<String> districtList = new ArrayList<String>();
			if (deviceCity != null && !StringUtils.trim(deviceCity).isEmpty()) {
				if (deviceDistrict != null && !StringUtils.trim(deviceDistrict).isEmpty()) {
					//固定区
					devicecond.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL,deviceDistrict));
				}else{
					//一个市的所有区
					Conditions conds = Conditions.newAndConditions();
					conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,deviceCity));
					List<InfopubArea> district = infopubAreaDao.query(conds, null);
					districtList.add(deviceCity);
					if(null != district){
						for(InfopubArea d : district){
							districtList.add(d.getStAreaId());
						}
					}
					devicecond.add(new Condition("ST_AREA_ID", Condition.OT_IN,districtList));
				}
			}else{
				//一个省的所有区，直辖市另算
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,deviceProvince));
				if("e66c5ae7-77cd-4de1-9d72-a4657a97079d".equals(deviceProvince)){//上海市
					List<InfopubArea> district = infopubAreaDao.query(conds, null);
					if(null != district){
						for(InfopubArea d : district){
							districtList.add(d.getStAreaId());
						}
					}
				}else{
					List<InfopubArea> city = infopubAreaDao.query(conds, null);
					if(null != city){
						for(InfopubArea emp : city){
							String cId = emp.getStAreaId();
							conds = Conditions.newAndConditions();
							conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,cId));
							List<InfopubArea> district = infopubAreaDao.query(conds, null);
							if(null != district){
								districtList.add(cId);
								for(InfopubArea d : district){
									districtList.add(d.getStAreaId());
								}
							}
						}
					}
				}
				
				devicecond.add(new Condition("ST_AREA_ID", Condition.OT_IN,districtList));
			}
			
		}
		
		
		if (deviceStreet != null && !StringUtils.trim(deviceStreet).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_STREET",Condition.OT_EQUAL,deviceStreet));
			List<InfopubAddress> iaList = infopubAddressDao.query(conds, suffix);
			if(null != iaList && iaList.size() > 0){
//				String stAddressId = iaList.get(0).getStAddressId();
//				devicecond.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL,stAddressId));
				List<String> stAddressId = new ArrayList<String>();
				for(InfopubAddress infopubAddress : iaList){
					stAddressId.add(infopubAddress.getStAddressId());	
				}
				devicecond.add(new Condition("ST_ADDRESS_ID", Condition.OT_IN,stAddressId));
			}
			
		}
		
		if (deviceAddress != null && !StringUtils.trim(deviceAddress).isEmpty()) {
			devicecond.add(new Condition("ST_DEVICE_ADDRESS", Condition.OT_LIKE,
					deviceAddress));
		}
		if (deviceCode != null && !StringUtils.trim(deviceCode).isEmpty()) {
			devicecond.add(new Condition("ST_DEVICE_CODE", Condition.OT_LIKE,
					deviceCode));
		}
		if (deviceMac != null && !StringUtils.trim(deviceMac).isEmpty()) {
			devicecond.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL,
					deviceMac));
		}

		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			devicecond.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
			Timestamp.valueOf(startDate + " 00:00:00")));
		}

		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			devicecond.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		
		infopubDeviceInfoList = infopubDeviceInfoDao.query(devicecond, suffix,pageSize, currentPage);
		

		for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {

//			if (!infopubDeviceInfo.getStTypeId().equals("")) {
			if(infopubDeviceInfo.getStTypeId() != null && !infopubDeviceInfo.getStTypeId().isEmpty()){
				InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.get(infopubDeviceInfo.getStTypeId());
				infopubDeviceInfo.setStTypeId(infopubDeviceType.getStTypeName());
			}
//			if(infopubDeviceInfo.getStOrganId() != null && !infopubDeviceInfo.getStOrganId().isEmpty()){
//				SmsOrgan smsOrgan = smsOrganDao.get(infopubDeviceInfo.getStOrganId());
//				infopubDeviceInfo.setStOrganId(smsOrgan.getStOrganName());
//			}
//			System.out.println(infopubDeviceInfo.getStOrganId());
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceInfoList);
		return returnObj;

	}

	/**
	 * 获取设备信息列表
	 */
	@Override
	public JSONObject streetDeviceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String staddressName = httpReqRes.getParameter("staddressName");
		String stTypeName = httpReqRes.getParameter("stTypeName");
		
		Conditions conds = Conditions.newAndConditions();
		if (staddressName != null && !StringUtils.trim(staddressName).isEmpty()) {
			Conditions condaddress = Conditions.newAndConditions();
			Conditions condId = Conditions.newOrConditions();
			condaddress.add(new Condition("ST_STREET", Condition.OT_LIKE,
					staddressName));
			List<InfopubAddress> query = infopubAddressDao.query(condaddress,
					null);
			for (InfopubAddress infopubAddress : query) {
				condId.add(new Condition("ST_ADDRESS_ID", Condition.OT_LIKE,
						infopubAddress.getStAddressId()));
			}
			conds.add(condId);
		}
		
		if (stTypeName != null && !StringUtils.trim(stTypeName).isEmpty()) {
			InfopubDeviceType byName = infopubDeviceTypeDao
					.getByName(stTypeName);
			conds.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE, byName
					.getStTypeId()));
		}
		String suffix = "ORDER BY DT_CREATE ";
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubDeviceInfo> infopubDeviceInfoList = new ArrayList<InfopubDeviceInfo>();
		infopubDeviceInfoList = infopubDeviceInfoDao.query(conds, suffix,pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceInfoList);
		return returnObj;

	}
	
	
	
	
	
	/**
	 * 赋能注册获取设备信息列表
	 */
	@Override
	public JSONObject appDeviceInfoList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		String typeId = httpReqRes.getParameter("typeId");
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE, typeId));
		}
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		if (stPermission != null && !StringUtils.trim(stPermission).isEmpty()) {
			if (!stPermission.equals("project_admin")) {
				if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
					conds.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
							stAreaId));
				} else {
					throw new NullPointerException("AreaId不能为空");
				}
			}
		}
		List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
				.query(conds, null);

		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceInfoList);
		return returnObj;

	}
	
	
	
	/**
	 * 保存和更新设备
	 */
	@Override
	public InfopubDeviceInfo saveOrUpdateDeviceInfo(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
		String stAddressId = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_ADDRESS_ID);
		String stOrganId = httpReqRes
				.getParameter(SmsOrgan.ST_ORGAN_ID);
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		String stAreaId = (String) httpReqRes.getRequest().getSession()
				.getAttribute("smsUserStAreaId");
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		InfopubDeviceInfo oldInfopubDeviceInfo = null;
		if (stDeviceId != null
				&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			oldInfopubDeviceInfo = infopubDeviceInfoDao.get(stDeviceId);
		}
		if (oldInfopubDeviceInfo != null) {
			httpReqRes.toBean(oldInfopubDeviceInfo);
			// 把地址表中获取得地址和经纬度添加到设备信息表中
			InfopubAddress infopubAddress = infopubAddressDao.get(stAddressId);
			String district = infopubAddress.getStDistrict();
			InfopubArea infopubArea = null;
			if(null != district){
				infopubArea = infopubAreaDao.getName(district);
				oldInfopubDeviceInfo.setStAreaId(infopubArea.getStAreaId());
			}
			String stStreet = infopubAddress.getStStreet();
			String stDeviceMac = oldInfopubDeviceInfo.getStDeviceMac();
			oldInfopubDeviceInfo.setStUserId(user.getStUserId());
			oldInfopubDeviceInfo.setStChannel("/client/" + stDeviceMac);
			if (stStreet == null) {
				stStreet = "";
			}
			if (stOrganId != null && !stOrganId.isEmpty())
				oldInfopubDeviceInfo.setStOrganId(stOrganId);
			oldInfopubDeviceInfo.setStDeviceAddress(infopubAddress.getStCity()
					+ infopubAddress.getStDistrict() + stStreet
					+ infopubAddress.getStAddress());
			oldInfopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
			oldInfopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
			if (oldInfopubDeviceInfo.getNmSdtype().intValue() == 2) {
				oldInfopubDeviceInfo.setNmSdtype(null);
			}
			oldInfopubDeviceInfo.setStUpdate(new Timestamp(System
					.currentTimeMillis()));
			oldInfopubDeviceInfo.setStState(new BigDecimal(2));
			infopubDeviceInfoDao.update(oldInfopubDeviceInfo);
			//记录设备变更状态
			saveOrUpdateDeviceInfoHis(oldInfopubDeviceInfo,"update");
			return oldInfopubDeviceInfo;
		} else {
			InfopubDeviceInfo newInfopubDeviceInfo = new InfopubDeviceInfo();
			httpReqRes.toBean(newInfopubDeviceInfo);
			List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(null,
					null);
			List arrayListCount = new ArrayList();
			for (InfopubDeviceInfo infopubDeviceInfo : list) {
				String aCode = infopubDeviceInfo.getStDeviceCode();
				String str = aCode
						.substring(aCode.length() - 4, aCode.length());
				arrayListCount.add(str);
			}
			InfopubAddress infopubAddress = infopubAddressDao
					.get(newInfopubDeviceInfo.getStAddressId());
			String stStreet = infopubAddress.getStStreet();
			String district = infopubAddress.getStDistrict();
			InfopubArea infopubArea = null;
			if(null != district){
				infopubArea = infopubAreaDao.getName(district);
				newInfopubDeviceInfo.setStAreaId(infopubArea.getStAreaId());
			}
			String stAreaCode = infopubArea.getStAreaCode();
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao
					.get(newInfopubDeviceInfo.getStTypeId());
			String stTypeCode = infopubDeviceType.getStTypeCode();
			InfopubCompany infopubCompany = infopubCompanyDao
					.get(infopubDeviceType.getStCompanyId());
			String company = infopubCompany.getStCompanyCode();
			String num = Collections.max(arrayListCount);
			int cout = Integer.decode(num) + 1;
			DecimalFormat mFormat = new DecimalFormat("0000");// 确定格式，把1转换为01
			String s = mFormat.format(cout);
			newInfopubDeviceInfo.setStDeviceCode(stAreaCode + company
					+ stTypeCode + s);
			newInfopubDeviceInfo.setStDeviceId(UUID.randomUUID().toString());
			newInfopubDeviceInfo.setStUserId(user.getStUserId());
			if (stStreet == null) {
				stStreet = "";
			}
			String stDeviceMac = newInfopubDeviceInfo.getStDeviceMac();
			newInfopubDeviceInfo.setStDeviceMac(stDeviceMac.replace(" ", ""));//对MAC去所有空格
			newInfopubDeviceInfo.setStDeviceAddress(infopubAddress.getStCity()
					+ infopubAddress.getStDistrict() + stStreet
					+ infopubAddress.getStAddress());
			newInfopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
			newInfopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
			if (!SecurityUtils.getSubject().hasRole("admin")) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, user
						.getStUserId()));
				conds.add(new Condition("ST_RESOURCE_TYPE_ID",
						Condition.OT_EQUAL, InfopubWorkspace.INFOPUB_WORKSPACE));
				SmsResourceAccessList resourceAccess = smsResourceAccessListDao
						.getByUserId(conds);
				conds = Conditions.newAndConditions();
				if (resourceAccess != null) {
					// String workSpaceId = resourceAccess.getStResourceId();
					// newInfopubDeviceInfo.setStWorkspaceId(workSpaceId);
				}
			}
			newInfopubDeviceInfo.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubDeviceInfo.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubDeviceInfo.setStUpdate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubDeviceInfo.setStChannel("/client/"
					+ newInfopubDeviceInfo.getStDeviceMac());
			if (stOrganId != null && !stOrganId.isEmpty())
				newInfopubDeviceInfo.setStOrganId(stOrganId);
			// newInfopubDeviceInfo.setStAreaId(stAreaId);
			newInfopubDeviceInfo.setStState(new BigDecimal(1));
			infopubDeviceInfoDao.add(newInfopubDeviceInfo);
			//记录设备变更状态
			saveOrUpdateDeviceInfoHis(newInfopubDeviceInfo,"add");
			return newInfopubDeviceInfo;
		}
	}

	/**
	 * 证书唯一编码修改保存
	 */
	@Override
	public InfopubDeviceInfo CertKeySaveOrUpdate(HttpReqRes httpReqRes) {
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String stCertKey = httpReqRes.getParameter("stCertKey");
		InfopubDeviceInfo oldInfopubDeviceInfo = infopubDeviceInfoDao
				.getMac(stDeviceMac);
		if (oldInfopubDeviceInfo.getStCertKey() != null) {
			if (oldInfopubDeviceInfo.getStCertKey().equals("")) {
				if (oldInfopubDeviceInfo != null) {
					infopubDeviceInfoDao.updateCretKey(stCertKey, stDeviceMac);
				}
				return oldInfopubDeviceInfo;
			}
			return null;
		}
		if (oldInfopubDeviceInfo != null) {
			infopubDeviceInfoDao.updateCretKey(stCertKey, stDeviceMac);
		}
		return oldInfopubDeviceInfo;
	}

	/**
	 * 证书唯一编码加密修改保存
	 */
	@Override
	public InfopubDeviceInfo KeyEncryptionSaveOrUpdate(HttpReqRes httpReqRes) {
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String stDeviceKey = httpReqRes.getParameter("stDeviceKey");
		InfopubDeviceInfo oldInfopubDeviceInfo = infopubDeviceInfoDao
				.getMac(stDeviceMac);
		if (oldInfopubDeviceInfo != null) {
			Conditions conds = Conditions.newAndConditions();
			String stDeviceCode = oldInfopubDeviceInfo.getStDeviceCode();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String value = "";
			// 利用AES加密
			try {
				value = AESUtils.encryption(stDeviceCode + stDeviceKey
						+ formatter.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println(AESUtils.decrypt("")); //解密方法
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ST_CERT_KEY", value);
			conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL,
					stDeviceMac));
			infopubDeviceInfoDao.update(map, conds);
		}
		return oldInfopubDeviceInfo;
	}

	/**
	 * 设备excel导入
	 */
	@Override
	public InfopubDeviceInfo deviceImport(HttpReqRes httpReqRes) {
		FileItem fileItem = httpReqRes.getFileItem("file");
		/*
		 * SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
		 * .getAttribute(AppContext.SESSION_USER);
		 */
		byte[] file = null;
		String fileName = null;
		if (fileItem != null) {
			file = ((FileItem) fileItem).get();
			fileName = fileItem.getName();
		}
		InfopubDeviceInfo newInfopubDeviceInfo = new InfopubDeviceInfo();
		List<InfopubDeviceInfo> listExcel = ReadExcelData.getDataByExcel(file,
				fileName);
		int count = 0;
		for (InfopubDeviceInfo infopubDeviceInfo : listExcel) {
			InfopubDeviceInfo mac = infopubDeviceInfoDao
					.getMac(infopubDeviceInfo.getStDeviceMac());
			if (mac == null) {
				// 获取区域id
				InfopubArea infopubArea = infopubAreaDao
						.getName(infopubDeviceInfo.getStAreaId());
				// 获取类型相关信息
				InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao
						.getByName(infopubDeviceInfo.getStTypeId());
				// 获取地址表相关信息 市 区 街道 详细
				InfopubAddress infopubAddress = infopubAddressDao.getName(
						infopubDeviceInfo.getStAddressId(),
						infopubDeviceInfo.getStAreaId(), null,
						infopubDeviceInfo.getStDeviceAddress());
				// 设置设备编码规则 (区域编码+设备厂商+类型编码+四位序列号)
				List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(null,
						null);
				List arrayListCount = new ArrayList();
				for (InfopubDeviceInfo infopubDeviceInfoList : list) {
					String aCode = infopubDeviceInfoList.getStDeviceCode();
					String str = aCode.substring(aCode.length() - 4,
							aCode.length());
					arrayListCount.add(str);
				}
				String stAreaCode = infopubArea.getStAreaCode();
				String stTypeCode = infopubDeviceType.getStTypeCode();
				InfopubCompany infopubCompany = infopubCompanyDao
						.get(infopubDeviceType.getStCompanyId());
				String company = infopubCompany.getStCompanyCode();
				String num = Collections.max(arrayListCount);
				int cout = Integer.decode(num) + 1;
				DecimalFormat mFormat = new DecimalFormat("0000");// 确定格式，把1转换为01
				String s = mFormat.format(cout);
				// 添加信息
				newInfopubDeviceInfo
						.setStDeviceId(UUID.randomUUID().toString());
				newInfopubDeviceInfo.setStDeviceName(infopubDeviceInfo
						.getStDeviceName() + "智慧柜员机");
				newInfopubDeviceInfo.setStDeviceCode(stAreaCode + company
						+ stTypeCode + s);
				newInfopubDeviceInfo.setStDeviceIp(infopubDeviceInfo
						.getStDeviceIp());
				newInfopubDeviceInfo.setStDeviceMac(infopubDeviceInfo
						.getStDeviceMac());
				newInfopubDeviceInfo.setStDeviceAddress(infopubAddress
						.getStCity()
						+ infopubAddress.getStDistrict()
						+ infopubAddress.getStAddress());
				newInfopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
				newInfopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
				newInfopubDeviceInfo.setStTypeId(infopubDeviceType
						.getStTypeId());
				newInfopubDeviceInfo.setStAreaId(infopubArea.getStAreaId());
				newInfopubDeviceInfo.setStUserId("admin");
				newInfopubDeviceInfo.setDtCreate(new Timestamp(System
						.currentTimeMillis()));
				newInfopubDeviceInfo.setDtUpdate(new Timestamp(System
						.currentTimeMillis()));
				newInfopubDeviceInfo.setStChannel("/client/"
						+ newInfopubDeviceInfo.getStDeviceMac());
				newInfopubDeviceInfo.setStAddressId(infopubAddress
						.getStAddressId());
				infopubDeviceInfoDao.add(newInfopubDeviceInfo);
				count++;
				/*
				 * if(String.valueOf(count).equals("528")){
				 * System.out.println("录入"+count+"条数据"); return
				 * newInfopubDeviceInfo; }
				 */
			}
		}
		System.out.println("录入" + count + "条数据");
		return newInfopubDeviceInfo;
	}

	@Override
	public InfopubDeviceInfo getDeviceInfo(HttpReqRes httpReqRes) {
		String deviceId = httpReqRes.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
		InfopubDeviceInfo infopubDeviceInfo = null;
		if (deviceId != null && !StringUtils.trim(deviceId).isEmpty()) {
			infopubDeviceInfo = infopubDeviceInfoDao.get(deviceId);
		} else {
			throw new NullPointerException("deviceId is null");
		}
		return infopubDeviceInfo;
	}

	/**
	 * 删除设备
	 */
	@Override
	public void deviceRemove(HttpReqRes httpReqRes) {
		String[] deviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceId[]");
		if (deviceIdList == null) {
			String deviceId = httpReqRes.getRequest().getParameter("stDeviceId");
			if (deviceId != null && !StringUtils.trimToEmpty(deviceId).isEmpty()) {
				deleteDeviceInfo(deviceId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		for (String deviceId : deviceIdList) {
			deleteDeviceInfo(deviceId);
		}
	}

	/**
	 * 逻辑删除设备
	 */
	@Override
	public void deviceLogicRemove(HttpReqRes httpReqRes) {
		String[] deviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceId[]");
		if (deviceIdList == null) {
			String deviceId = httpReqRes.getRequest().getParameter("stDeviceId");
			if (deviceId != null && !StringUtils.trimToEmpty(deviceId).isEmpty()) {
				logicDeleteDeviceInfo(deviceId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		for (String deviceId : deviceIdList) {
			logicDeleteDeviceInfo(deviceId);
		}
	}


	/**
	 * 逻辑删除设备
	 *
	 * @param deviceId
	 */
	private void logicDeleteDeviceInfo(String deviceId) {
		if (deviceId != null && !StringUtils.trimToEmpty(deviceId).isEmpty()) {
			InfopubDeviceInfo idi = new InfopubDeviceInfo();
			idi = infopubDeviceInfoDao.get(deviceId);
			if(null != idi){
				saveOrUpdateDeviceInfoHis(idi,"delete");
			}
			infopubDeviceInfoDao.updateLogicDelete(deviceId);
		}
	}

	@Override
	public JSONObject deviceOnOffList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		String deviceId = httpReqRes.getParameter(InfopubOnoff.ST_DEVICE_ID);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, deviceId));
		conds.add(new Condition("ST_PTYPE", Condition.OT_EQUAL, "DAY"));
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
		List<InfopubOnoff> infopubOnoffList = infopubOnoffDaoExt.query(conds,
				StringUtils.EMPTY, pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubOnoffList, InfopubOnoff.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubOnoffList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubOnoffList);
		return returnObj;
	}

	/**
	 * 获取设备开关机信息
	 */
	@Override
	public InfopubOnoff getDeviceOnOffInfo(HttpReqRes httpReqRes) {
		String stOnoffId = httpReqRes.getParameter(InfopubOnoff.ST_ONOFF_ID);
		if (stOnoffId != null && !StringUtils.trimToEmpty(stOnoffId).isEmpty()) {
			return infopubOnoffDaoExt.get(stOnoffId);
		} else {
			String stDeviceId = httpReqRes
					.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
			if (stDeviceId != null
					&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				return infopubOnoffDaoExt.getWeekCycle(stDeviceId);
			}
		}
		return null;
	}

	/**
	 * 设备信息的设定
	 */
	@Override
	public InfopubOnoff saveOrUpdateDeviceOnOff(HttpReqRes httpReqRes,
			String deviceId) {
		String stOnoffId = httpReqRes.getParameter(InfopubOnoff.ST_ONOFF_ID);
		InfopubOnoff oldInfopubOnoff = null;
		if (stOnoffId != null && !StringUtils.trimToEmpty(stOnoffId).isEmpty()) {
			oldInfopubOnoff = infopubOnoffDaoExt.get(stOnoffId);
		}
		if (oldInfopubOnoff != null) {
			httpReqRes.toBean(oldInfopubOnoff);
			if ("WEEK".endsWith(oldInfopubOnoff.getStPtype())) {
				String[] weeks = httpReqRes.getRequest().getParameterValues(
						"week[]");
				oldInfopubOnoff.setStPeriod(setWeekDay(weeks));
			}

			oldInfopubOnoff.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubOnoffDaoExt.update(oldInfopubOnoff);
			return oldInfopubOnoff;
		} else {
			InfopubOnoff newInfopubOnoff = new InfopubOnoff();
			httpReqRes.toBean(newInfopubOnoff);
			newInfopubOnoff.setStOnoffId(UUID.randomUUID().toString());
			if (deviceId != null
					&& !StringUtils.trimToEmpty(deviceId).isEmpty()
					&& "WEEK".equals(newInfopubOnoff.getStPtype())) {
				Conditions conds = Conditions.newOrConditions();
				Conditions subResourceConds = Conditions.newAndConditions();
				subResourceConds.add(new Condition("ST_DEVICE_ID",
						Condition.OT_EQUAL, deviceId));
				subResourceConds.add(new Condition("ST_PTYPE",
						Condition.OT_EQUAL, "WEEK"));
				conds.add(subResourceConds);
				infopubOnoffDaoExt.delete(conds);

				newInfopubOnoff.setStDeviceId(deviceId);
				String[] weeks = httpReqRes.getRequest().getParameterValues(
						"week[]");
				newInfopubOnoff.setStPeriod(setWeekDay(weeks));
			}
			if (newInfopubOnoff.getStDeviceId() == null) {
				newInfopubOnoff.setStDeviceId(deviceId);
			}
			newInfopubOnoff.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubOnoff.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubOnoffDaoExt.add(newInfopubOnoff);
			return newInfopubOnoff;
		}
	}

	/**
	 * 设备定时信息删除
	 */
	@Override
	public void deviceOnOffRemove(HttpReqRes httpReqRes) {
		String[] onOffIdList = httpReqRes.getRequest().getParameterValues(
				"stOnOffId[]");
		if (onOffIdList == null) {
			String stOnOffId = httpReqRes.getRequest()
					.getParameter("stOnOffId");
			if (stOnOffId != null
					&& !StringUtils.trimToEmpty(stOnOffId).isEmpty()) {
				infopubOnoffDaoExt.delete(stOnOffId);
				return;
			} else {
				throw new NullPointerException("设备开关机ID不能为空");
			}
		}
		for (String stOnOffId : onOffIdList) {
			infopubOnoffDaoExt.delete(stOnOffId);
		}
	}

	private String setWeekDay(String[] weeks) {
		String Monday = "0";
		String Tuesday = "0";
		String Wednesday = "0";
		String Thursday = "0";
		String Friday = "0";
		String Saturday = "0";
		String Sunday = "0";
		if (weeks != null && weeks.length > 0) {
			for (String day : weeks) {
				switch (Integer.valueOf(day)) {
				case 1:
					Monday = "1";
					break;
				case 2:
					Tuesday = "1";
					break;
				case 3:
					Wednesday = "1";
					break;
				case 4:
					Thursday = "1";
					break;
				case 5:
					Friday = "1";
					break;
				case 6:
					Saturday = "1";
					break;
				case 7:
					Sunday = "1";
					break;
				default:
					break;
				}
			}
		}
		return Monday + Tuesday + Wednesday + Thursday + Friday + Saturday
				+ Sunday;
	}

	/**
	 * 日志信息列表
	 */
	@Override
	public JSONObject deviceLogList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		Conditions conds = Conditions.newAndConditions();
		String operand = httpReqRes.getParameter("operand");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (operand != null && !StringUtils.trim(operand).isEmpty()) {
			conds.add(new Condition("ST_OPERAND", Condition.OT_LIKE, operand));
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
			if ("stOperand".equals(orderName)) {
				suffix = "ORDER BY ST_OPERAND " + orderType.toUpperCase();
			} else if ("stAction".equals(orderName)) {
				suffix = "ORDER BY ST_ACTION " + orderType.toUpperCase();
			} else if ("stMsg".equals(orderName)) {
				suffix = "ORDER BY ST_MSG " + orderType.toUpperCase();
			} else if ("stLevel".equals(orderName)) {
				suffix = "ORDER BY ST_LEVEL " + orderType.toUpperCase();
			} else if ("stOperator".equals(orderName)) {
				suffix = "ORDER BY ST_OPERATOR " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
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
		List<InfopubDeviceLog> infopubDeviceLogList = infopubDeviceLogDaoExt
				.query(conds, suffix, pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceLogList,
							InfopubDeviceLog.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceLogList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceLogList);
		return returnObj;
	}

	/**
	 * 设备操作
	 */
	@Override
	public void deviceOperate(HttpReqRes httpReqRes, String stDeviceId) {
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String deviceId = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
		if (deviceId == null) {
			deviceId = stDeviceId;
		}

		String optType = httpReqRes.getParameter("type");
		System.out.println(optType + "------------------------");
		if (deviceId != null && !StringUtils.trimToEmpty(deviceId).isEmpty()
				&& optType != null
				&& !StringUtils.trimToEmpty(optType).isEmpty()) {
			InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
					.get(deviceId);
			if (infopubDeviceInfo != null) {
				String result = StringUtils.EMPTY;
				/*
				 * if ("open".equals(optType) || "close".equals(optType)) {
				 * String closeType = infopubDeviceInfo.getStShutdownType(); if
				 * (closeType != null &&
				 * !StringUtils.trimToEmpty(closeType).isEmpty()) { if
				 * ("turnonoff".equals(closeType)) { if ("open".equals(optType))
				 * { optType = "turnon"; } else { optType = "turnoff"; } } else
				 * if ("wakeup".equals(closeType)) { if ("open".equals(optType))
				 * { optType = "wakeup"; } else { optType = "shutdown"; } } else
				 * if ("openclosescrn".equals(closeType)) { if
				 * ("open".equals(optType)) { optType = "openscrn"; } else {
				 * optType = "closescrn"; } } } }
				 */
				if ("wakeup".endsWith(optType)) {
					String dec = infopubDeviceInfo.getStDesc();
					if (dec != null || !StringUtils.trimToEmpty(dec).isEmpty()) {
						result = InfopubUtils.sendWindowsOptMsg(infopubDeviceInfo, optType);
					}
				} else {
					result = InfopubUtils
							.sendOptMsg(infopubDeviceInfo, optType);
				}
				InfopubDeviceLog deviceLog = new InfopubDeviceLog();
				deviceLog.setStOperator(user.getStUserName());
				if ("success".equals(result)) {
					deviceLog.setStMsg("操作成功");
				} else {
					deviceLog.setStMsg(result);
					deviceLog.setStLevel("error");
				}
				deviceLog.setStDeviceId(infopubDeviceInfo.getStDeviceId());
				deviceLog.setStOperand(infopubDeviceInfo.getStDeviceName());
				deviceLog.setStLocation(infopubDeviceInfo.getStDeviceAddress());
				deviceLog.setStAction(getAciton(optType));
				infopubDeviceLogDaoExt.add(deviceLog);
			}
		} else {
			LogHelper.info("deviceId or optType is null");
		}
	}

	/**
	 * 开关机设置信息删除
	 */
	@Override
	public void deviceLogRemove(HttpReqRes httpReqRes) {
		String[] onOffIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceLogId[]");
		if (onOffIdList == null) {
			String stOnOffId = httpReqRes.getRequest().getParameter(
					"stDeviceLogId");
			if (stOnOffId != null
					&& !StringUtils.trimToEmpty(stOnOffId).isEmpty()) {
				infopubDeviceLogDaoExt.delete(stOnOffId);
				return;
			} else {
				throw new NullPointerException("日志ID不能为空");
			}
		}
		for (String stOnOffId : onOffIdList) {
			infopubDeviceLogDaoExt.delete(stOnOffId);
		}
	}

	/**
	 * 获取操作类型
	 * 
	 * @param type
	 * @return
	 */
	private String getAciton(String type) {
		if (type == null) {
			return "未传递操作指令";
		} else if ("openscrn".equals(type) || "turnon".equals(type)) {
			return "打开屏幕";
		} else if ("closescrn".equals(type) || "turnoff".equals(type)) {
			return "关闭屏幕";
		} else if ("reboot".equals(type)) {
			return "重启";
		} else if ("snapshots".equals(type)) {
			return "截屏";
		} else if ("wakeup".equals(type)) {
			return "开机";
		} else if ("shutdown".equals(type)) {
			return "关机";
		} else if ("runexe".equals(type)) {
			return "运行向日葵";
		} else if ("stopexe".equals(type)) {
			return "关闭向日葵";
		} else if ("download".equals(type)) {
			return "下载";
		} else if ("modify".equals(type)) {
			return "修改xml";
		} else {
			return "未知操作类型";
		}
	}

	/**
	 * 查看日志信息
	 */
	@Override
	public InfopubDeviceLog getDeviceLogInfo(HttpReqRes httpReqRes) {
		String deviceLogId = httpReqRes
				.getParameter(InfopubDeviceLog.ST_DEVICE_LOG_ID);
		return infopubDeviceLogDaoExt.get(deviceLogId);
	}

	/**
	 * 获取附件Id
	 */
	@Override
	public List<String> getAttachIds(HttpReqRes httpReqRes) {
		String linkId = httpReqRes.getParameter(InfopubAttachment.ST_LINK_ID);
		String linkTable = httpReqRes
				.getParameter(InfopubAttachment.ST_LINK_TABLE);
		if (linkId != null && !StringUtils.trimToEmpty(linkId).isEmpty()
				&& linkTable != null
				&& !StringUtils.trimToEmpty(linkTable).isEmpty()) {

			return infopubAttachmentDaoExt.getAttachIds(linkId, linkTable);
		}
		return null;
	}

	/**
	 * 信息发布用户空间列表
	 */
	@Override
	public JSONObject workSpaceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		Conditions conds = null;
		String workspaceName = httpReqRes.getParameter("workspaceName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		String suffix = StringUtils.EMPTY;
		if (orderName != null) {
			if ("stWorkspaceId".equals(orderName)) {
				suffix = "ORDER BY ST_WORKSPACE_ID " + orderType.toUpperCase();
			} else if ("stWorkspaceCode".equals(orderName)) {
				suffix = "ORDER BY ST_WORKSPACE_CODE "
						+ orderType.toUpperCase();
			} else if ("stWorkspaceName".equals(orderName)) {
				suffix = "ORDER BY ST_WORKSPACE_NAME "
						+ orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			} else if ("stExt1".equals(orderName)) {
				suffix = "ORDER BY 	ST_USER_NAME " + orderType.toUpperCase();
			} else if ("nmTotal".equals(orderName)) {
				suffix = "ORDER BY 	NM_TOTAL " + orderType.toUpperCase();
			} else if ("nmUsed".endsWith(orderName)) {
				suffix = "ORDER BY 	NM_USED " + orderType.toUpperCase();
			}
		}
		conds = Conditions.newAndConditions();
		if (workspaceName != null && !StringUtils.trim(workspaceName).isEmpty()) {
			conds.add(new Condition("ST_WORKSPACE_NAME", Condition.OT_LIKE,
					workspaceName));
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
		List<InfopubWorkspace> infopubWorkspaceList = infopubWorkspaceDaoExt
				.query(conds, suffix, pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubWorkspaceList,
							InfopubWorkspace.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubWorkspaceList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubWorkspaceList);
		return returnObj;
	}

	/**
	 * 保存空间信息
	 */
	@Override
	public InfopubWorkspace workSpaceSave(HttpReqRes httpReqRes) {
		String stWorkSpaceId = httpReqRes
				.getParameter(InfopubWorkspace.ST_WORKSPACE_ID);
		InfopubWorkspace oldInfopubWorkspace = null;
		if (stWorkSpaceId != null
				&& !StringUtils.trimToEmpty(stWorkSpaceId).isEmpty()) {
			oldInfopubWorkspace = infopubWorkspaceDaoExt.get(stWorkSpaceId);
		}
		if (oldInfopubWorkspace != null) {
			httpReqRes.toBean(oldInfopubWorkspace);
			oldInfopubWorkspace.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			Conditions conds = Conditions.newOrConditions();
			conds.add(new Condition("ST_WORKSPACE_ID", Condition.OT_EQUAL,
					oldInfopubWorkspace.getStWorkspaceId()));
			infopubWorkspaceUserDao.delete(conds);
			conds = Conditions.newOrConditions();
			Conditions subResourceConds = Conditions.newAndConditions();
			subResourceConds
					.add(new Condition("ST_RESOURCE_ID", Condition.OT_EQUAL,
							oldInfopubWorkspace.getStWorkspaceId()));
			subResourceConds.add(new Condition("ST_RESOURCE_TYPE_ID",
					Condition.OT_EQUAL, InfopubWorkspace.INFOPUB_WORKSPACE));
			subResourceConds.add(new Condition("ST_UNIQUE_VALUE",
					Condition.OT_EQUAL, "infopubWorkSpace"));
			conds.add(subResourceConds);
			smsResourceAccessListDao.delete(conds);
			String userIds[] = httpReqRes.getRequest().getParameterValues(
					"userId[]");
			if (userIds != null) {
				addWorkSpaceUserAndResourceAccess(userIds,
						oldInfopubWorkspace.getStWorkspaceId());
			}
			infopubWorkspaceDaoExt.update(oldInfopubWorkspace);
			return oldInfopubWorkspace;
		} else {
			InfopubWorkspace newInfopubWorkspace = new InfopubWorkspace();
			httpReqRes.toBean(newInfopubWorkspace);
			newInfopubWorkspace.setStWorkspaceId(UUID.randomUUID().toString());
			newInfopubWorkspace.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubWorkspace.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubWorkspaceDaoExt.add(newInfopubWorkspace);
			String userIds[] = httpReqRes.getRequest().getParameterValues(
					"userId[]");
			if (userIds != null) {
				addWorkSpaceUserAndResourceAccess(userIds,
						newInfopubWorkspace.getStWorkspaceId());
			}
			return newInfopubWorkspace;
		}
	}

	/**
	 * 添加用户资源
	 * 
	 * @param userIds
	 * @param stWorkspaceId
	 */
	private void addWorkSpaceUserAndResourceAccess(String[] userIds,
			String stWorkspaceId) {
		for (String userId : userIds) {
			InfopubWorkspaceUser wsUser = new InfopubWorkspaceUser();
			wsUser.setStUserId(userId);
			wsUser.setStWorkspaceId(stWorkspaceId);
			infopubWorkspaceUserDao.add(wsUser);
			SmsResourceAccessList smsResourceAccessList = new SmsResourceAccessList();
			smsResourceAccessList.setStUserId(userId);
			smsResourceAccessList.setStResourceId(stWorkspaceId);
			smsResourceAccessList
					.setStResourceTypeId(InfopubWorkspace.INFOPUB_WORKSPACE);
			smsResourceAccessList.setStUniqueValue("infopubWorkSpace");
			smsResourceAccessListDao.add(smsResourceAccessList);
		}
	}

	/**
	 * 获取用户空间信息
	 */
	@Override
	public InfopubWorkspace getWorkSpaceInfo(HttpReqRes httpReqRes) {
		String stWorkSpaceId = httpReqRes
				.getParameter(InfopubWorkspace.ST_WORKSPACE_ID);
		if (stWorkSpaceId != null
				&& !StringUtils.trimToEmpty(stWorkSpaceId).isEmpty()) {
			return infopubWorkspaceDaoExt.get(stWorkSpaceId);
		}
		return null;
	}

	/**
	 * 删除用户空间
	 */
	@Override
	public void workSpaceRemove(HttpReqRes httpReqRes) {
		String[] workSpaceIdList = httpReqRes.getRequest().getParameterValues(
				"stWorkSpaceId[]");
		if (workSpaceIdList == null) {
			String stWorkSpaceId = httpReqRes.getRequest().getParameter(
					"stWorkSpaceId");
			if (stWorkSpaceId != null) {
				infopubWorkspaceDaoExt.delete(stWorkSpaceId);
				return;
			} else {
				throw new NullPointerException("ID不能为空");
			}
		}
		for (String stWorkSpaceId : workSpaceIdList) {
			infopubWorkspaceDaoExt.delete(stWorkSpaceId);
		}
	}

	/**
	 * 获取空间用户信息
	 */
	@Override
	public List<SmsResourceAccessList> checkUserSelect(HttpReqRes httpReqRes) {
		String workSpaceId = httpReqRes.getParameter("workSpaceId");
		if (workSpaceId != null
				&& !StringUtils.trimToEmpty(workSpaceId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_RESOURCE_ID", Condition.OT_EQUAL,
					workSpaceId));
			conds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_EQUAL,
					InfopubWorkspace.INFOPUB_WORKSPACE));
			List<SmsResourceAccessList> smsResourceAccessLists = smsResourceAccessListDao
					.query(conds, StringUtils.EMPTY);
			return smsResourceAccessLists;
		}
		return null;
	}

	/**
	 * 设备信息分组
	 */
	@Override
	public JSONObject deviceGroupList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		Conditions conds = Conditions.newAndConditions();
		/*
		 * if (!SecurityUtils.getSubject().hasRole("admin")) { String
		 * workSpaceId = getWorkspaceIdByUserId(httpReqRes); if (workSpaceId !=
		 * null && !StringUtils.trimToEmpty(workSpaceId).isEmpty()) {
		 * conds.add(new Condition("ST_WORKSPACE_ID", Condition.OT_EQUAL,
		 * workSpaceId)); } else { throw new NullPointerException("用户空间ID为空"); }
		 * }
		 */
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String deviceName = httpReqRes.getParameter("groupName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				conds.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						stAreaId));
			} else {
				throw new NullPointerException("AreaId不能为空");
			}

		}
		if (deviceName != null && !StringUtils.trim(deviceName).isEmpty()) {
			conds.add(new Condition("ST_GROUP_NAME", Condition.OT_LIKE,
					deviceName));
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
			if ("stGroupName".equals(orderName)) {
				suffix = "ORDER BY ST_GROUP_NAME " + orderType.toUpperCase();
			} else if ("stDesc".equals(orderName)) {
				suffix = "ORDER BY ST_DESC " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubGroup> infopubGroupList = infopubGroupDao.query(conds,
				suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubGroupList, InfopubGroup.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubGroupList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubGroupList);
		return returnObj;
	}

	/**
	 * 保存、更新发布组
	 */
	@Override
	public InfopubGroup deviceGroupSaveOrUpate(HttpReqRes httpReqRes) {
		String stGroupId = httpReqRes.getParameter(InfopubGroup.ST_GROUP_ID);
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		InfopubGroup oldInfopubGroup = null;
		if (stGroupId != null && !StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			oldInfopubGroup = infopubGroupDao.get(stGroupId);
		}
		if (oldInfopubGroup != null) {
			httpReqRes.toBean(oldInfopubGroup);
			oldInfopubGroup.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			oldInfopubGroup.setStUserId(user.getStUserId());
			infopubGroupDao.update(oldInfopubGroup);
			deleteGroupDeviceByGroupId(oldInfopubGroup.getStGroupId());
			String[] deviceIds = httpReqRes.getRequest().getParameterValues(
					"deviceId[]");
			if (deviceIds != null) {
				InfopubGroupDevice groupDevice = null;
				for (String deviceId : deviceIds) {
					groupDevice = new InfopubGroupDevice();
					groupDevice.setStDeviceId(deviceId);
					groupDevice.setStGroupId(oldInfopubGroup.getStGroupId());
					infopubGroupDeviceDao.add(groupDevice);
				}
			}
			return oldInfopubGroup;
		} else {
			InfopubGroup newInfopubGroup = new InfopubGroup();
			httpReqRes.toBean(newInfopubGroup);
			newInfopubGroup.setStGroupId(UUID.randomUUID().toString());
			newInfopubGroup.setStUserId(user.getStUserId());
			newInfopubGroup.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubGroup.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubGroup.setStAreaId(httpReqRes
					.getParameter(InfopubGroup.ST_AREA_ID));
			infopubGroupDao.add(newInfopubGroup);
			String[] deviceIds = httpReqRes.getRequest().getParameterValues(
					"deviceId[]");
			if (deviceIds != null) {
				InfopubGroupDevice groupDevice = null;
				for (String deviceId : deviceIds) {
					groupDevice = new InfopubGroupDevice();
					groupDevice.setStDeviceId(deviceId);
					groupDevice.setStGroupId(newInfopubGroup.getStGroupId());
					infopubGroupDeviceDao.add(groupDevice);
				}
			}
			return newInfopubGroup;
		}
	}

	/**
	 * 获取用户组信息
	 */
	@Override
	public InfopubGroup getDeviceGroupInfo(HttpReqRes httpReqRes) {
		String groupId = httpReqRes.getParameter(InfopubGroup.ST_GROUP_ID);
		if (groupId != null && !StringUtils.trimToEmpty(groupId).isEmpty()) {
			return infopubGroupDao.get(groupId);
		}
		return null;
	}

	/**
	 * 删除用户组信息
	 */
	@Override
	public void deviceGroupRemove(HttpReqRes httpReqRes) {
		String[] groupIdList = httpReqRes.getRequest().getParameterValues(
				"stGroupId[]");
		if (groupIdList == null) {
			String stGroupId = httpReqRes.getRequest()
					.getParameter("stGroupId");
			if (stGroupId != null) {
				infopubGroupDao.delete(stGroupId);
				deleteGroupDeviceByGroupId(stGroupId);
				return;
			} else {
				throw new NullPointerException("日志ID不能为空");
			}
		}
		for (String stGroupId : groupIdList) {
			System.out.println(stGroupId);
			infopubGroupDao.delete(stGroupId);
			deleteGroupDeviceByGroupId(stGroupId);
		}
	}

	/**
	 * 组设备信息选择
	 */
	@Override
	public List<InfopubGroupDevice> deviceGroupSelect(HttpReqRes httpReqRes) {
		String stGroupId = httpReqRes
				.getParameter(InfopubGroupDevice.ST_GROUP_ID);
		if (stGroupId != null && !StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,
					stGroupId));
			return infopubGroupDeviceDao.query(conds, StringUtils.EMPTY);
		} else {
			String[] groupIdList = httpReqRes.getRequest().getParameterValues(
					"groupId[]");
			if (groupIdList != null && groupIdList.length > 0) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_GROUP_ID", Condition.OT_IN,
						groupIdList));
				return infopubGroupDeviceDao.query(conds, StringUtils.EMPTY);
			}
		}
		return null;
	}

	/**
	 * 设备组下的设备
	 */
	@Override
	public List<InfopubDeviceInfo> groupDeviceSelect(HttpReqRes httpReqRes) {
		String stGroupId = httpReqRes
				.getParameter(InfopubGroupDevice.ST_GROUP_ID);
		if (stGroupId != null && !StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,
					stGroupId));
			List<InfopubGroupDevice> groupdeviceList = infopubGroupDeviceDao
					.query(conds, StringUtils.EMPTY);
			List<InfopubDeviceInfo> list = new ArrayList<InfopubDeviceInfo>();
			for (InfopubGroupDevice infopubGroupDevice : groupdeviceList) {
				String stDeviceId = infopubGroupDevice.getStDeviceId();
				InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
						.get(stDeviceId);
				if (infopubDeviceInfo != null) {
					list.add(infopubDeviceInfo);
				}
			}
			return list;
		} else {
			String[] groupIdList = httpReqRes.getRequest().getParameterValues(
					"groupId[]");
			if (groupIdList != null && groupIdList.length > 0) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_GROUP_ID", Condition.OT_IN,
						groupIdList));
				List<InfopubGroupDevice> groupdeviceList = infopubGroupDeviceDao
						.query(conds, StringUtils.EMPTY);
				List<InfopubDeviceInfo> list = new ArrayList<InfopubDeviceInfo>();
				for (InfopubGroupDevice infopubGroupDevice : groupdeviceList) {
					String stDeviceId = infopubGroupDevice.getStDeviceId();
					InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
							.get(stDeviceId);
					if (infopubDeviceInfo != null) {
						list.add(infopubDeviceInfo);
					}
				}
				return list;
			}
		}
		return null;
	}

	/**
	 * 删除设备
	 * 
	 * @param groupId
	 */
	private void deleteGroupDeviceByGroupId(String groupId) {
		if (groupId != null && !StringUtils.trimToEmpty(groupId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, groupId));
			infopubGroupDeviceDao.delete(conds);
		}
	}

	/**
	 * 获取用户空间ID
	 * 
	 * @param httpReqRes
	 * @return
	 */
	private String getWorkspaceIdByUserId(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, user
				.getStUserId()));
		conds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_EQUAL,
				InfopubWorkspace.INFOPUB_WORKSPACE));
		SmsResourceAccessList resourceAccess = smsResourceAccessListDao
				.getByUserId(conds);
		conds = Conditions.newAndConditions();
		if (resourceAccess != null) {
			return resourceAccess.getStResourceId();
		}
		return null;
	}

	/**
	 * 附件删除
	 */
	@Override
	public void deviceAttachmentRemove(HttpReqRes httpReqRes) {
		String[] attachmentIdList = httpReqRes.getRequest().getParameterValues(
				"attachmentId[]");
		if (attachmentIdList == null) {
			String attachmentId = httpReqRes.getParameter("attachmentId");
			if (attachmentId != null) {
				infopubAttachmentDaoExt.delete(attachmentId);
				return;
			} else {
				throw new NullPointerException("日志ID不能为空");
			}
		}
		for (String attachmentId : attachmentIdList) {
			infopubAttachmentDaoExt.delete(attachmentId);
		}
	}

	/**
	 * 发布源信息列表
	 */
	@Override
	public JSONObject psrouceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		if (!SecurityUtils.getSubject().hasRole("admin")) {
			String workSpaceId = getWorkspaceIdByUserId(httpReqRes);
			if (workSpaceId != null
					&& !StringUtils.trimToEmpty(workSpaceId).isEmpty()) {
				conds.add(new Condition("IP.ST_WORKSPACE_ID",
						Condition.OT_EQUAL, workSpaceId));
			} else {
				conds.add(new Condition("IP.ST_WORKSPACE_ID",
						Condition.OT_EQUAL, UUID.randomUUID().toString()));
			}
		}
		String deviceName = httpReqRes.getParameter("psourceName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (deviceName != null && !StringUtils.trim(deviceName).isEmpty()) {
			conds.add(new Condition("IP.ST_PSOURCE_NAME", Condition.OT_LIKE,
					deviceName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("IP.DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("IP.DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY IP.DT_CREATE";
		if (orderName != null) {
			if ("stPsourceName".equals(orderName)) {
				suffix = "ORDER BY IP.ST_PSOURCE_NAME "
						+ orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY IP.DT_CREATE " + orderType.toUpperCase();
			} else if ("stContent".endsWith(orderName)) {
				suffix = "ORDER BY IA.CL_CONTENT " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubPsourceExt> infopubPsourceList = infopubPsourceDaoExt
				.queryWithContent(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter
					.convertDataSetToJson(
							DataSet.convert(infopubPsourceList,
									InfopubPsourceExt.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubPsourceList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubPsourceList);
		return returnObj;
	}

	/**
	 * 更新或者保存发布源
	 */
	@Override
	public InfopubPsource saveOrUpdatePsrouce(HttpReqRes httpReqRes) {
		String stPsourceId = httpReqRes
				.getParameter(InfopubPsource.ST_PSOURCE_ID);
		String content = httpReqRes.getParameter("CL_CONTENT");
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		InfopubPsource oldInfopubPsource = null;
		if (stPsourceId != null
				&& !StringUtils.trimToEmpty(stPsourceId).isEmpty()) {
			oldInfopubPsource = infopubPsourceDaoExt.get(stPsourceId);
		}
		if (oldInfopubPsource != null) {
			httpReqRes.toBean(oldInfopubPsource);
			oldInfopubPsource.setStUserId(user.getStUserId());
			infopubPsourceDaoExt.update(oldInfopubPsource);
			InfopubAttachment attachment = savePsourceAttachment(oldInfopubPsource
					.getStAttachId());
			attachment.setClContent(content);
			attachment.setStLinkId(oldInfopubPsource.getStPsourceId());
			attachment.setStLinkTable(InfopubPsource.INFOPUB_PSOURCE);
			infopubAttachmentDaoExt.update(attachment);
			return oldInfopubPsource;
		} else {
			InfopubPsource newInfopubPsource = new InfopubPsource();
			httpReqRes.toBean(newInfopubPsource);
			newInfopubPsource.setStPsourceId(UUID.randomUUID().toString());
			newInfopubPsource.setStUserId(user.getStUserId());
			if (!SecurityUtils.getSubject().hasRole("admin")) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, user
						.getStUserId()));
				conds.add(new Condition("ST_RESOURCE_TYPE_ID",
						Condition.OT_EQUAL, InfopubWorkspace.INFOPUB_WORKSPACE));
				SmsResourceAccessList resourceAccess = smsResourceAccessListDao
						.getByUserId(conds);
				conds = Conditions.newAndConditions();
				if (resourceAccess != null) {
					String workSpaceId = resourceAccess.getStResourceId();
					newInfopubPsource.setStWorkspaceId(workSpaceId);
				}
			}
			newInfopubPsource.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubPsource.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			InfopubAttachment newttachment = savePsourceAttachment(newInfopubPsource
					.getStAttachId());
			newttachment.setClContent(content);
			newttachment.setStLinkId(newInfopubPsource.getStPsourceId());
			newttachment.setStLinkTable(InfopubPsource.INFOPUB_PSOURCE);
			infopubAttachmentDaoExt.add(newttachment);
			newInfopubPsource.setStAttachId(newttachment.getStAttachId());
			infopubPsourceDaoExt.add(newInfopubPsource);
			return newInfopubPsource;
		}
	}

	/**
	 * 查看发布源
	 */
	@Override
	public InfopubPsourceExt getPsrouceInfo(HttpReqRes httpReqRes) {
		String psourceId = httpReqRes
				.getParameter(InfopubPsource.ST_PSOURCE_ID);
		if (psourceId != null && !StringUtils.trimToEmpty(psourceId).isEmpty()) {
			return infopubPsourceDaoExt.getWithContent(psourceId);
		}
		return null;
	}

	/**
	 * 发布源删除
	 */
	@Override
	public void psrouceRemove(HttpReqRes httpReqRes) {
		String[] psrouceIdList = httpReqRes.getRequest().getParameterValues(
				"psrouceId[]");
		if (psrouceIdList == null) {
			String psrouceId = httpReqRes.getParameter("psrouceId");
			if (psrouceId != null
					&& !StringUtils.trimToEmpty(psrouceId).isEmpty()) {
				infopubPsourceDaoExt.delete(psrouceId);
				Conditions conds = Conditions.newAndConditions();
				Conditions subConds = Conditions.newAndConditions();
				subConds.add(new Condition("ST_LINK_TABLE", Condition.OT_EQUAL,
						InfopubPsource.INFOPUB_PSOURCE));
				subConds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL,
						psrouceId));
				conds.add(subConds);
				infopubAttachmentDaoExt.delete(conds);
				return;
			} else {
				throw new NullPointerException("日志ID不能为空");
			}
		}
		for (String psrouceId : psrouceIdList) {
			infopubPsourceDaoExt.delete(psrouceId);
			Conditions conds = Conditions.newAndConditions();
			Conditions subConds = Conditions.newAndConditions();
			subConds.add(new Condition("ST_LINK_TABLE", Condition.OT_EQUAL,
					InfopubPsource.INFOPUB_PSOURCE));
			subConds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL,
					psrouceId));
			conds.add(subConds);
			infopubAttachmentDaoExt.delete(conds);
		}
	}

	/**
	 * 更新或者保存附件
	 */
	@Override
	public InfopubAttachment saveOrUpdateAttachment(HttpReqRes httpReqRes,
			String linkTable, String linkId) {
		String attachmentId = httpReqRes
				.getParameter(InfopubAttachment.ST_ATTACH_ID);
		InfopubAttachment oldInfopubAttachment = null;
		if (attachmentId != null
				&& !StringUtils.trimToEmpty(attachmentId).isEmpty()) {
			oldInfopubAttachment = infopubAttachmentDaoExt.get(attachmentId);
		}
		if (oldInfopubAttachment != null) {
			httpReqRes.toBean(oldInfopubAttachment);
			oldInfopubAttachment.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubAttachmentDaoExt.update(oldInfopubAttachment);
			return oldInfopubAttachment;
		} else {
			InfopubAttachment newInfopubAttachment = new InfopubAttachment();
			httpReqRes.toBean(newInfopubAttachment);
			newInfopubAttachment.setStAttachId(UUID.randomUUID().toString());
			newInfopubAttachment.setStLinkId(linkId);
			newInfopubAttachment.setStLinkTable(linkTable);
			newInfopubAttachment.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubAttachment.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubAttachmentDaoExt.add(newInfopubAttachment);
			return newInfopubAttachment;
		}
	}

	/**
	 * 查看附件
	 */
	@Override
	public InfopubAttachment getInfopubAttachment(HttpReqRes httpReqRes,
			String stAttachmentId) {
		String attachmentId = httpReqRes
				.getParameter(InfopubAttachment.ST_ATTACH_ID);
		if (attachmentId == null) {
			attachmentId = stAttachmentId;
		}
		if (attachmentId != null
				&& !StringUtils.trimToEmpty(attachmentId).isEmpty()) {
			return infopubAttachmentDaoExt.get(attachmentId);
		}
		return null;
	}

	/**
	 * 保存发布源信息
	 * 
	 * @param stAttachmentId
	 * @return
	 */
	private InfopubAttachment savePsourceAttachment(String stAttachmentId) {
		InfopubAttachment oldInfopubAttachment = null;
		if (stAttachmentId != null
				&& !StringUtils.trimToEmpty(stAttachmentId).isEmpty()) {
			oldInfopubAttachment = infopubAttachmentDaoExt
					.getWithoutFile(stAttachmentId);
		}
		if (oldInfopubAttachment != null) {
			return oldInfopubAttachment;
		} else {
			InfopubAttachment newInfopubAttachment = new InfopubAttachment();
			newInfopubAttachment.setStAttachId(UUID.randomUUID().toString());
			newInfopubAttachment.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubAttachment.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			return newInfopubAttachment;
		}
	}

	/**
	 * 设备发布列表
	 */
	@Override
	public JSONObject publishList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String deviceId = httpReqRes.getParameter(InfopubPublish.ST_DEVICE_ID);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, deviceId));
		conds.add(new Condition("ST_PTYPE", Condition.OT_EQUAL, "DAY"));
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
		List<InfopubPublish> infopubPublishList = infopubPublishDaoExt.query(
				conds, StringUtils.EMPTY, pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubPublishList, InfopubPublish.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubPublishList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubPublishList);
		return returnObj;
	}

	/**
	 * 保存和更新发布信息
	 */
	@Override
	public InfopubPublish saveOrUpdatePublish(HttpReqRes httpReqRes,
			String deviceId) {
		String stpubishId = httpReqRes
				.getParameter(InfopubPublish.ST_PUBLISH_ID);
		InfopubPublish oldInfopubPublish = null;
		if (stpubishId != null
				&& !StringUtils.trimToEmpty(stpubishId).isEmpty()) {
			oldInfopubPublish = infopubPublishDaoExt.get(stpubishId);
		}
		if (oldInfopubPublish != null) {
			httpReqRes.toBean(oldInfopubPublish);
			oldInfopubPublish.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubPublishDaoExt.update(oldInfopubPublish);
			return oldInfopubPublish;
		} else {
			InfopubPublish newInfopubPublish = new InfopubPublish();
			httpReqRes.toBean(newInfopubPublish);
			newInfopubPublish.setStPublishId(UUID.randomUUID().toString());
			if (deviceId != null
					&& !StringUtils.trimToEmpty(deviceId).isEmpty()) {
				Conditions subResourceConds = Conditions.newAndConditions();
				subResourceConds.add(new Condition("ST_DEVICE_ID",
						Condition.OT_EQUAL, deviceId));
				infopubPublishDaoExt.delete(subResourceConds);
			}
			if (newInfopubPublish.getStDeviceId() == null) {
				newInfopubPublish.setStDeviceId(deviceId);
			}
			newInfopubPublish.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubPublish.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubPublishDaoExt.add(newInfopubPublish);
			return newInfopubPublish;
		}
	}

	/**
	 * 获取发布信息
	 */
	@Override
	public InfopubPublish getPublish(HttpReqRes httpReqRes) {
		String stpublishId = httpReqRes
				.getParameter(InfopubPublish.ST_PUBLISH_ID);
		if (stpublishId != null
				&& !StringUtils.trimToEmpty(stpublishId).isEmpty()) {
			return infopubPublishDaoExt.get(stpublishId);
		} else {
			String stDeviceId = httpReqRes
					.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
			if (stDeviceId != null
					&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				return infopubPublishDaoExt.getByDeviceId(stDeviceId);
			}
		}
		return null;
	}

	/**
	 * 删除发布信息
	 */
	@Override
	public void publishRemove(HttpReqRes httpReqRes) {
		String[] publishIdList = httpReqRes.getRequest().getParameterValues(
				"publishId[]");
		if (publishIdList == null) {
			String publishId = httpReqRes.getParameter("publishId");
			if (publishId != null) {
				infopubPublishDaoExt.delete(publishId);
				return;
			} else {
				throw new NullPointerException("日志ID不能为空");
			}
		}
		for (String publishId : publishIdList) {
			infopubPublishDaoExt.delete(publishId);
		}
	}

	/**
	 * 删除设备截图
	 */
	@Override
	public void deviceAttachmentRemoveShot(HttpReqRes httpReqRes) {
		String deviceId = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
		if (deviceId != null && !StringUtils.trimToEmpty(deviceId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			Conditions subConds = Conditions.newAndConditions();
			subConds.add(new Condition("ST_LINK_TABLE", Condition.OT_EQUAL,
					InfopubDeviceInfo.INFOPUB_DEVICE_INFO));
			subConds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL,
					deviceId));
			subConds.add(new Condition("ST_FILENAME", Condition.OT_EQUAL,
					"snapshots"));
			conds.add(subConds);
			infopubAttachmentDaoExt.delete(conds);
		}
	}

	/**
	 * 删除设备及附属信息
	 * 
	 * @param deviceId
	 */
	private void deleteDeviceInfo(String deviceId) {
		if (deviceId != null && !StringUtils.trimToEmpty(deviceId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,deviceId));
			InfopubDeviceInfo idi = new InfopubDeviceInfo();
			idi = infopubDeviceInfoDao.get(deviceId);
			if(null != idi){
				saveOrUpdateDeviceInfoHis(idi,"delete");
			}
			
			infopubDeviceInfoDao.delete(conds);
			infopubOnoffDaoExt.delete(conds);
			infopubGroupDeviceDao.delete(conds);
			infopubPublishDaoExt.delete(conds);
		}
	}

	/**
	 * 设备信息类别列表
	 */
	@Override
	public JSONObject deviceInfoTypeList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL, null));
		String deviceTypeName = httpReqRes.getParameter("deviceTypeName");
		String username = httpReqRes.getParameter("userName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (deviceTypeName != null
				&& !StringUtils.trim(deviceTypeName).isEmpty()) {
			conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
					deviceTypeName));
		}else{
			Conditions usercon = Conditions.newAndConditions();
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			SmsUser smsUser = smsUserDao.query(usercon, null).get(0);
			
			Conditions ruconds = Conditions.newAndConditions();
			ruconds.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			SmsRole smsRole = smsRoleDao.queryRoleByUser(ruconds, null);
			System.out.println("角色代码："+smsRole.getStRoleCode());
			
			if("changshang".equals(smsRole.getStRoleCode())){
				String stUserName = smsUser.getStUserName();
				Conditions typeInfoConds = Conditions.newAndConditions();
				typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
				List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
				for(InfopubDeviceType emp : typeInfo){
					String typeInfoName = emp.getStTypeName();
					typeInfoName = typeInfoName.substring(typeInfoName.indexOf("（")+1, typeInfoName.indexOf("）"));
					if(stUserName.contains(typeInfoName)){
						conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
					}
				}
				
			}else if("bank".equals(smsRole.getStRoleCode())){
				String stUserName = smsUser.getStUserName();
				Conditions typeInfoConds = Conditions.newAndConditions();
				typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
				List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
				for(InfopubDeviceType emp : typeInfo){
					String typeInfoName = emp.getStTypeName();
					typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
					//System.out.println("typeInfoName-------"+typeInfoName);
					if(stUserName.contains(typeInfoName)){
						conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
					}
				}
			}else if("area".equals(smsRole.getStRoleCode())){
				conds.add(new Condition("NM_DTYPE", Condition.OT_EQUAL, 0));
			}
		}
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY NM_ORDER";
		if (orderName != null) {
			if ("stTypeName".equals(orderName)) {
				suffix = "ORDER BY ST_TYPE_NAME " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao
				.query(conds, suffix, pageSize, currentPage);
		for (InfopubDeviceType infopubDeviceType : infopubDeviceTypeList) {
			String companyId = infopubDeviceType.getStCompanyId();
			if (companyId != null) {
				InfopubCompany infopubCompany = infopubCompanyDao
						.get(companyId);
			if(null != infopubCompany){
				infopubDeviceType.setStCompanyId(infopubCompany
						.getStCompanyName()) ;
			}else{
				infopubDeviceType.setStCompanyId("");
			}
					
					
				
				
			}
			String stTypeId = infopubDeviceType.getStTypeId();
			InfopubAttachment infopubAttachment = infopubAttachmentDaoExt
					.getLinkId(stTypeId);
			if (infopubAttachment != null) {
				Conditions condImage = Conditions.newAndConditions();
				condImage.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL,
						infopubAttachment.getStLinkId()));
				byte[] blob = BlobHelper.getBlob("INFOPUB_ATTACHMENT",
						"BL_CONTENT", condImage.toString(),
						condImage.getObjectArray());
				String base64Str = DatatypeConverter.printBase64Binary(blob);
				infopubDeviceType.setStIcon(base64Str);
			}
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceTypeList,
							InfopubDeviceType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceTypeList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceTypeList);
		return returnObj;
	}
	
	
	@Override
	public JSONObject deviceTypeOptionList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL, null));
		String deviceTypeName = httpReqRes.getParameter("deviceTypeName");
		String username = httpReqRes.getParameter("userName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (deviceTypeName != null
				&& !StringUtils.trim(deviceTypeName).isEmpty()) {
			conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
					deviceTypeName));
		}else{
			Conditions usercon = Conditions.newAndConditions();
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			SmsUser smsUser = smsUserDao.query(usercon, null).get(0);
			
			Conditions ruconds = Conditions.newAndConditions();
			ruconds.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_EQUAL, username));
			SmsRole smsRole = smsRoleDao.queryRoleByUser(ruconds, null);
			System.out.println("角色代码："+smsRole.getStRoleCode());
			
			if("changshang".equals(smsRole.getStRoleCode())){
				List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
				String stUserName = smsUser.getStUserName();
				System.out.println("用户名："+stUserName);
				Conditions typeConds = Conditions.newAndConditions();
				if(stUserName.contains("新点")){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
							"新点"));
				}else if(stUserName.contains("卓繁")){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
							"卓繁"));
				}else if(stUserName.contains("万达")){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
							"万达"));
				}
			}
		}
		
		String suffix = "ORDER BY NM_ORDER";
		if (orderName != null) {
			if ("stTypeName".equals(orderName)) {
				suffix = "ORDER BY ST_TYPE_NAME " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao
				.query(conds, suffix, pageSize, currentPage);
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceTypeList,
							InfopubDeviceType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceTypeList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceTypeList);
		return returnObj;
	}
	

	/**
	 * 设备区域信息列表
	 */
	@Override
	public JSONObject deviceDistrict(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		List<InfopubAddress> districtList = infopubAddressDao.selectAll();
		InfopubAddress infopubAddress = new InfopubAddress();
		infopubAddress.setStAddressId("");
		infopubAddress.setStDistrict("");
		districtList.add(infopubAddress);
		obj.put("data", districtList);
		return obj;
	}

	/**
	 * 设备街道信息列表
	 */
	@Override
	public JSONObject deviceStreet(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		List<InfopubAddress> streetList = null;
		String district = httpReqRes.getParameter("distirct");
		// System.out.println("拿到的地区："+district);
		if (district == null || district == "") {
			streetList = infopubAddressDao.selectAllStreet();
		} else {
			streetList = infopubAddressDao.selectStreetByDistrict(district);
		}
		InfopubAddress infopubAddress = new InfopubAddress();
		infopubAddress.setStAddressId("");
		infopubAddress.setStStreet("");
		streetList.add(infopubAddress);
		obj.put("data", streetList);
		return obj;
	}

	/**
	 * 获取设备附属信息
	 */
	@Override
	public InfopubDeviceInfo getDeviceInfoExt(HttpReqRes httpReqRes) {
		String deviceId = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
		InfopubDeviceInfo infopubDeviceInfo = null;
		if (deviceId != null && !StringUtils.trim(deviceId).isEmpty()) {
			infopubDeviceInfo = infopubDeviceInfoDao.get(deviceId);
		} else {
			throw new NullPointerException("deviceId is null");
		}
		return infopubDeviceInfo;
	}

	/**
	 * 保存、更新设备信息类别
	 */
	@Override
	public InfopubDeviceType deviceInfoTypeSaveOrUpate(HttpReqRes httpReqRes) {
		String stTypeId = httpReqRes.getParameter(InfopubDeviceType.ST_TYPE_ID);
		FileItem fileItem = httpReqRes.getFileItem("file");
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		InfopubDeviceType oldInfopubDeviceType = null;
		if (stTypeId != null && !StringUtils.trimToEmpty(stTypeId).isEmpty()) {
			oldInfopubDeviceType = infopubDeviceTypeDao.get(stTypeId);
		}
		byte[] file = null;
		String fileName = null;
		if (fileItem != null) {
			file = ((FileItem) fileItem).get();
			fileName = fileItem.getName();
		}
		if (oldInfopubDeviceType != null) {
			httpReqRes.toBean(oldInfopubDeviceType);
			infopubDeviceTypeDao.update(oldInfopubDeviceType);
			if (!fileName.equals("")) {
				InfopubAttachment infopubAttachment = new InfopubAttachment();
				String linkId = oldInfopubDeviceType.getStTypeId();
				InfopubAttachment infopubAttactId = infopubAttachmentDaoExt
						.getLinkId(linkId);
				if (infopubAttactId == null) {
					infopubAttachment.setStAttachId(UUID.randomUUID()
							.toString());
					infopubAttachment.setStLinkId(linkId);
					infopubAttachment.setStLinkTable("INFOPUB_DEVICE_TYPE");
					infopubAttachment.setBlContent(file);
					infopubAttachmentDaoExt.add(infopubAttachment);
				} else {
					infopubAttachment.setStAttachId(infopubAttactId
							.getStAttachId());
					infopubAttachment.setBlContent(file);
					infopubAttachment.setStLinkId(linkId);
					infopubAttachment.setStLinkTable("INFOPUB_DEVICE_TYPE");
					infopubAttachmentDaoExt.update(infopubAttachment);
				}
			}
			return oldInfopubDeviceType;
		} else {
			String uuid = UUID.randomUUID().toString();
			InfopubAttachment infopubAttachment = new InfopubAttachment();
			InfopubDeviceType newInfopubDeviceType = new InfopubDeviceType();
			httpReqRes.toBean(newInfopubDeviceType);
			newInfopubDeviceType.setStTypeId(uuid);
			newInfopubDeviceType.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubDeviceType.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubDeviceTypeDao.add(newInfopubDeviceType);
			infopubAttachment.setStAttachId(UUID.randomUUID().toString());
			infopubAttachment.setStLinkId(uuid);
			infopubAttachment.setStLinkTable("INFOPUB_DEVICE_TYPE");
			infopubAttachment.setBlContent(file);
			infopubAttachmentDaoExt.add(infopubAttachment);
			return newInfopubDeviceType;
		}
	}

	/**
	 * 获取设备信息类别
	 */
	@Override
	public InfopubDeviceType getDeviceInfoType(HttpReqRes httpReqRes) {
		String typeId = httpReqRes.getParameter(InfopubDeviceType.ST_TYPE_ID);
		if (typeId != null && !StringUtils.trimToEmpty(typeId).isEmpty()) {
			return infopubDeviceTypeDao.get(typeId);
		}
		return null;
	}

	/**
	 * 删除设备信息类别
	 */
	@Override
	public void deviceInfoTypeRemove(HttpReqRes httpReqRes) {
		String[] typeIdList = httpReqRes.getRequest().getParameterValues(
				"stTypeId[]");
		if (typeIdList == null) {
			String stTypeId = httpReqRes.getRequest().getParameter("stTypeId");
			if (stTypeId != null
					&& !StringUtils.trimToEmpty(stTypeId).isEmpty()) {
				infopubDeviceTypeDao.delete(stTypeId);
				InfopubAttachment infopubAttachment = infopubAttachmentDaoExt
						.getLinkId(stTypeId);
				infopubAttachmentDaoExt.delete(infopubAttachment
						.getStAttachId());
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		infopubDeviceTypeDao.delete(typeIdList);
		infopubAttachmentDaoExt.deleteLinkId(typeIdList);
	}

	/**
	 * 检查分类编号
	 */
	@Override
	public boolean checkTypeCode(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("参数不能为空");
		}
		boolean reslut = false;
		// 分类编号
		String typeCode = wrapper.getParameter("typeCode");
		// 类型ID
		String stTypeId = wrapper.getParameter("ST_TYPE_ID");

		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_TYPE_CODE", Condition.OT_EQUAL, typeCode));
		// 获取类型
		InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao
				.checkTypeCode(conds);
		// 数据存在且不是修改的场合
		if (infopubDeviceType != null
				&& !stTypeId.equals(infopubDeviceType.getStTypeId())) {
			reslut = true;
		}
		Log.info("分类编号:" + typeCode + ",类型ID:" + stTypeId + ",是否存在:"
				+ String.valueOf(reslut));
		return reslut;
	}

	/**
	 * 添加或更新外设状态
	 */
	@Override
	public InfopubOdeviceStatus outDeviceStatusSave(HttpReqRes httpReqRes) {
		// 设备id
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		if (stDeviceId == null || StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			return null;
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao
				.getByMacId(stDeviceId);
		if (deviceInfo == null) {
			return null;
		}
		// 外设标识
		String stOutDeviceCode = httpReqRes.getParameter("stOutDeviceCode");
		// 是否异常
		// Integer nmException = httpReqRes.getParameterInteger("nmException");
		BigDecimal nmException = httpReqRes
				.getParameterBigdecimal("nmException");
		// 异常原因
		String stCause = httpReqRes.getParameter("stCause");
		// 是否通知
		// Integer nmNotice = httpReqRes.getParameterInteger("nmNotice");
		BigDecimal nmNotice = httpReqRes.getParameterBigdecimal("nmNotice");
		// 总量
		// Integer nmTotal = httpReqRes.getParameterInteger("nmTotal");
		BigDecimal nmTotal = httpReqRes.getParameterBigdecimal("nmTotal");
		// 剩余量
		// Integer nmRemain = httpReqRes.getParameterInteger("nmRemain");
		BigDecimal nmRemain = httpReqRes.getParameterBigdecimal("nmRemain");
		// int intnmRemain = nmRemain.intValue();
		// 拓展1
		String stExt1 = httpReqRes.getParameter("stExt1");
		// 拓展2
		String stExt2 = httpReqRes.getParameter("stExt2");
		// 存放使用量
		Integer count = httpReqRes.getParameterInteger("count");
		BigDecimal useCount = new BigDecimal(count.toString());

		InfopubOdeviceStatus oldInfopubOdeviceStatus = null;
		if (stDeviceId != null
				&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			if (stOutDeviceCode != null
					&& !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
				InfopubOdeviceStatus info = new InfopubOdeviceStatus();
				info.setStDeviceId(stDeviceId);
				info.setStOutDeviceCode(stOutDeviceCode);
				oldInfopubOdeviceStatus = infopubOdeviceStatusDao
						.getOdeviceStatus(info);
			}
		}
		if (oldInfopubOdeviceStatus != null) {
			// 打印机放纸或其他外设
			if (count == 0) {
				// 判断外设状态是否异常，异常将oldInfopubOdeviceStatus存储到历史中
				/*
				 * if (nmException.compareTo(new BigDecimal(1)) == 0) { if
				 * (stCause != null && oldInfopubOdeviceStatus.getStCause() !=
				 * null) { if
				 * (stCause.equals(oldInfopubOdeviceStatus.getStCause())) { if
				 * (nmNotice.compareTo(oldInfopubOdeviceStatus.getNmNotice()) ==
				 * 0) { if
				 * (nmTotal.compareTo(oldInfopubOdeviceStatus.getNmTotal()) ==
				 * 0) { System.out.println("外设状态未改变...."); }else {
				 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }
				 * else { infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
				 * } }else {
				 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }else
				 * if (stCause == null && oldInfopubOdeviceStatus.getStCause()
				 * == null) { if
				 * (nmNotice.compareTo(oldInfopubOdeviceStatus.getNmNotice()) ==
				 * 0) { if
				 * (nmTotal.compareTo(oldInfopubOdeviceStatus.getNmTotal()) ==
				 * 0) { System.out.println("外设状态未改变...."); }else {
				 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }
				 * else { infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
				 * } }else {
				 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }else
				 * { infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); }
				 */
				// 判断nmException是否变化，发生变化，添加到历史中。
				if (nmException.compareTo(oldInfopubOdeviceStatus
						.getNmException()) != 0) {
					infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
				}
				// 更新外设状态
				// 没有异常，成功总次数+1,否则失败总次数+1
				if (nmException.compareTo(new BigDecimal(0)) == 0) {
					BigDecimal newNmHisStotal = oldInfopubOdeviceStatus
							.getNmHisStotal().add(new BigDecimal(1));
					oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal);
				} else {
					BigDecimal newNmHisFtotal = oldInfopubOdeviceStatus
							.getNmHisFtotal().add(new BigDecimal(1));
					oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal);
				}
				oldInfopubOdeviceStatus.setNmException(nmException);
				oldInfopubOdeviceStatus.setNmNotice(nmNotice);
				oldInfopubOdeviceStatus.setStCause(stCause);
				oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
						.currentTimeMillis()));

				// 判断nmException是否为1，为1则异常，结束方法。
				if (nmException.compareTo(new BigDecimal(1)) == 0) {
					infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
					return oldInfopubOdeviceStatus;
				}

				oldInfopubOdeviceStatus.setNmHisTotal(oldInfopubOdeviceStatus
						.getNmTotal());
				// 判断剩余量是大于零还是小于零
				BigDecimal oldnmRemain = oldInfopubOdeviceStatus.getNmRemain();
				// 大于0,nmTotal = nmTotal + 剩余量
				if (oldnmRemain.compareTo(new BigDecimal(0)) >= 0) {
					oldInfopubOdeviceStatus
							.setNmTotal(nmTotal.add(oldnmRemain));
					oldInfopubOdeviceStatus.setNmRemain(nmTotal
							.add(oldnmRemain));
				} else {
					// 小于0,nmTotal = nmTotal
					oldInfopubOdeviceStatus.setNmTotal(nmTotal);
					oldInfopubOdeviceStatus.setNmRemain(nmTotal);
				}

				oldInfopubOdeviceStatus.setStExt1(stExt1);
				oldInfopubOdeviceStatus.setStExt2(stExt2);

				infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
				return oldInfopubOdeviceStatus;
			} else {
				// 打印机打印
				// 判断nmException是否变化，发生变化，添加到历史中。
				if (nmException.compareTo(oldInfopubOdeviceStatus
						.getNmException()) != 0) {
					infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
				}
				if (nmException.compareTo(new BigDecimal(0)) == 0) {
					// 没有异常，成功总次数+1,否则失败总次数+1
					BigDecimal newNmHisStotal = oldInfopubOdeviceStatus
							.getNmHisStotal().add(new BigDecimal(1));
					oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal);
				} else {
					BigDecimal newNmHisFtotal = oldInfopubOdeviceStatus
							.getNmHisFtotal().add(new BigDecimal(1));
					oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal);
				}
				oldInfopubOdeviceStatus.setNmException(nmException);
				oldInfopubOdeviceStatus.setNmNotice(nmNotice);
				oldInfopubOdeviceStatus.setStCause(stCause);
				oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
						.currentTimeMillis()));

				oldInfopubOdeviceStatus.setStExt1(stExt1);
				oldInfopubOdeviceStatus.setStExt2(stExt2);

				// 判断nmException是否为1，为1则异常，结束方法。
				if (nmException.compareTo(new BigDecimal(1)) == 0) {
					infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
					return oldInfopubOdeviceStatus;
				}
				// 查询到的剩余量
				BigDecimal oldnmRemain = oldInfopubOdeviceStatus.getNmRemain();
				// 减少后
				BigDecimal newnmRemain = oldnmRemain.subtract(useCount);
				oldInfopubOdeviceStatus.setNmRemain(newnmRemain);
				infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
				return oldInfopubOdeviceStatus;
			}
		} else {
			if (count == 0) {
				InfopubOdeviceStatus newInfopubOdeviceStatus = new InfopubOdeviceStatus();
				newInfopubOdeviceStatus.setStOutDeviceResultId(UUID
						.randomUUID().toString());
				newInfopubOdeviceStatus.setStDeviceId(stDeviceId);
				newInfopubOdeviceStatus.setStOutDeviceCode(stOutDeviceCode);
				newInfopubOdeviceStatus.setNmException(nmException);
				newInfopubOdeviceStatus.setStCause(stCause);
				newInfopubOdeviceStatus.setNmNotice(nmNotice);
				newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
						.currentTimeMillis()));
				newInfopubOdeviceStatus.setStExt1(stExt1);
				newInfopubOdeviceStatus.setStExt2(stExt2);
				/*
				 * // 判断nmException是否为1，为1则异常，保存到历史中。 if
				 * (nmException.compareTo(new BigDecimal(1)) == 0) {
				 * infopubOdeviceResultDao.add(newInfopubOdeviceStatus); }
				 */
				// 没有异常，成功总次数+1,否则失败总次数+1
				if (nmException.compareTo(new BigDecimal(0)) == 0) {
					// 没有异常，成功总次数+1,否则失败总次数+1
					newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(1));
					newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(0));
				} else {
					newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(0));
					newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(1));
				}
				newInfopubOdeviceStatus.setNmTotal(nmTotal);
				newInfopubOdeviceStatus.setNmRemain(nmTotal);
				infopubOdeviceStatusDao.add(newInfopubOdeviceStatus);
				return newInfopubOdeviceStatus;
			} else {
				InfopubOdeviceStatus newInfopubOdeviceStatus = new InfopubOdeviceStatus();
				newInfopubOdeviceStatus.setStOutDeviceResultId(UUID
						.randomUUID().toString());
				newInfopubOdeviceStatus.setStDeviceId(stDeviceId);
				newInfopubOdeviceStatus.setStOutDeviceCode(stOutDeviceCode);
				newInfopubOdeviceStatus.setNmException(nmException);
				newInfopubOdeviceStatus.setStCause(stCause);
				newInfopubOdeviceStatus.setNmNotice(nmNotice);
				newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
						.currentTimeMillis()));
				newInfopubOdeviceStatus.setStExt1(stExt1);
				newInfopubOdeviceStatus.setStExt2(stExt2);
				/*
				 * // 判断nmException是否为1，为1则异常，保存到历史中。 if
				 * (nmException.compareTo(new BigDecimal(1)) == 0) {
				 * infopubOdeviceResultDao.add(newInfopubOdeviceStatus); }
				 */
				// 没有异常，成功总次数+1,否则失败总次数+1
				if (nmException.compareTo(new BigDecimal(0)) == 0) {
					// 没有异常，成功总次数+1,否则失败总次数+1
					newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(1));
					newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(0));
				} else {
					newInfopubOdeviceStatus.setNmHisStotal(new BigDecimal(0));
					newInfopubOdeviceStatus.setNmHisFtotal(new BigDecimal(1));
				}
				newInfopubOdeviceStatus.setNmTotal(nmTotal);
				newInfopubOdeviceStatus.setNmRemain(nmTotal.subtract(useCount));
				// newInfopubOdeviceStatus.setNmHisTotal(nmTotal);
				infopubOdeviceStatusDao.add(newInfopubOdeviceStatus);
				return newInfopubOdeviceStatus;
			}
		}
	}

	/**
	 * 获取外设状态(通过设备id和外设标识)
	 */
	@Override
	public InfopubOdeviceStatus getOutdeviceStatus(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stOutDeviceCode = httpReqRes.getParameter("stOutDeviceCode");
		if (stDeviceId != null
				&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			if (stOutDeviceCode != null
					&& !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
				InfopubOdeviceStatus info = new InfopubOdeviceStatus();
				info.setStDeviceId(stDeviceId);
				info.setStOutDeviceCode(stOutDeviceCode);
				return infopubOdeviceStatusDao.getOdeviceStatus(info);
			}
		}
		return null;
	}

	/**
	 * 添加或更新外设状态(后台)
	 */
	@Override
	public InfopubOdeviceStatus odeviceStatusSaveOrUpate(HttpReqRes httpReqRes) {
		// 设备id
		String stDeviceId = httpReqRes
				.getParameter(InfopubOdeviceStatus.ST_DEVICE_ID);
		if (stDeviceId == null || StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			return null;
		}
		InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao
				.getByMacId(stDeviceId);
		if (deviceInfo == null) {
			return null;
		}
		// 外设状态Id
		String stOutDeviceResultId = httpReqRes
				.getParameter(InfopubOdeviceStatus.ST_OUT_DEVICE_RESULT_ID);
		InfopubOdeviceStatus oldInfopubOdeviceStatus = null;
		if (stOutDeviceResultId != null
				&& !StringUtils.trimToEmpty(stOutDeviceResultId).isEmpty()) {
			oldInfopubOdeviceStatus = infopubOdeviceStatusDao
					.get(stOutDeviceResultId);
		}
		if (oldInfopubOdeviceStatus != null) {
			// 添加到历史记录中
			infopubOdeviceResultDao.add(oldInfopubOdeviceStatus);
			httpReqRes.toBean(oldInfopubOdeviceStatus);
			oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus);
			return oldInfopubOdeviceStatus;
		} else {
			InfopubOdeviceStatus newInfopubOdeviceStatus = new InfopubOdeviceStatus();
			httpReqRes.toBean(newInfopubOdeviceStatus);
			newInfopubOdeviceStatus.setStOutDeviceResultId(UUID.randomUUID()
					.toString());
			newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubOdeviceStatusDao.add(newInfopubOdeviceStatus);
			// infopubOdeviceResultDao.add(newInfopubOdeviceStatus);
			return newInfopubOdeviceStatus;
		}
	}

	/**
	 * 获取外设状态(通过外设状态id)
	 */
	@Override
	public InfopubOdeviceStatus getOdeviceStatus(HttpReqRes httpReqRes) {
		String stOutDeviceResultId = httpReqRes
				.getParameter("ST_OUT_DEVICE_RESULT_ID");
		if (stOutDeviceResultId != null
				&& !StringUtils.trimToEmpty(stOutDeviceResultId).isEmpty()) {
			return infopubOdeviceStatusDao.get(stOutDeviceResultId);
		}

		return null;
	}

	/**
	 * 获取外设状态(通过设备id和外设标识)(后台)
	 */
	@Override
	public InfopubOdeviceStatus getOdevice(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stOutDeviceCode = httpReqRes.getParameter("stOutDeviceCode");
		if (stDeviceId != null
				&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
			if (stOutDeviceCode != null
					&& !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
				InfopubOdeviceStatus info = new InfopubOdeviceStatus();
				info.setStDeviceId(stDeviceId);
				info.setStOutDeviceCode(stOutDeviceCode);
				return infopubOdeviceStatusDao.getOdeviceStatus(info);
			}
		}
		return null;
	}

	/**
	 * 外设状态列表
	 */
	@Override
	public JSONObject getOdeviceStatusList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String stOutDeviceCode = httpReqRes.getParameter("stOutDeviceCode");
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stOutDeviceCode != null
				&& !StringUtils.trim(stOutDeviceCode).isEmpty()) {
			conds.add(new Condition("ST_OUT_DEVICE_CODE", Condition.OT_LIKE,
					stOutDeviceCode));
		}
		if (stDeviceId != null && !StringUtils.trim(stDeviceId).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ID", Condition.OT_LIKE,
					stDeviceId));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_UPDATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_UPDATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY ST_DEVICE_ID";
		if (orderName != null) {
			if ("stExt1".equals(orderName)) {
				suffix = "ORDER BY ST_OUT_DEVICE_CODE "
						+ orderType.toUpperCase();
			} else if ("stOutDeviceCode".equals(orderName)) {
				suffix = "ORDER BY ST_OUT_DEVICE_CODE "
						+ orderType.toUpperCase();
			} else if ("stDeviceId".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_ID " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		// ArrayList<InfopubOdeviceStatus> list = new
		// ArrayList<InfopubOdeviceStatus>();
		List<InfopubOdeviceStatus> infopubOdeviceStatusList = infopubOdeviceStatusDao
				.query(conds, suffix, pageSize, currentPage);
		for (InfopubOdeviceStatus infopubOdeviceStatus : infopubOdeviceStatusList) {
			String stDeviceCode = infopubOdeviceStatus.getStDeviceId();
			InfopubDeviceInfo byMacId = infopubDeviceInfoDao
					.getByMacId(stDeviceCode);
			infopubOdeviceStatus.setStDeviceId(byMacId.getStDeviceCode());
		}
		for (int i = 0; i < infopubOdeviceStatusList.size(); i++) {
			InfopubOdeviceStatus infopubOdeviceStatus = infopubOdeviceStatusList
					.get(i);
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao
					.getByTypeCode(infopubOdeviceStatus);
			if (infopubDeviceType == null) {

			} else {
				String stTypeName = infopubDeviceType.getStTypeName();
				infopubOdeviceStatus.setStExt1(stTypeName);
				// list.add(infopubOdeviceStatus);
			}
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubOdeviceStatusList,
							InfopubOdeviceStatus.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubOdeviceStatusList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubOdeviceStatusList);
		return returnObj;
	}

	/**
	 * 删除外设状态
	 */
	@Override
	public void odeviceStatusRemove(HttpReqRes httpReqRes) {
		String[] stOutDeviceResultIdList = httpReqRes.getRequest()
				.getParameterValues("stOutDeviceResultId[]");
		if (stOutDeviceResultIdList == null) {
			String stOutDeviceResultId = httpReqRes.getRequest().getParameter(
					"stOutDeviceResultId");
			if (stOutDeviceResultId != null
					&& !StringUtils.trimToEmpty(stOutDeviceResultId).isEmpty()) {
				infopubOdeviceStatusDao.delete(stOutDeviceResultId);
				return;
			} else {
				throw new NullPointerException("外设状态结果ID不能为空");
			}
		}
		infopubOdeviceStatusDao.delete(stOutDeviceResultIdList);
	}

	/**
	 * 截屏保存
	 */

	@Override
	public InfopubAttachment saveImage(HttpReqRes httpReqRes) {
		if (httpReqRes == null)
			throw new NullPointerException("paramter can not be null");
		String image = httpReqRes.getParameter("image");
		String stDeviceId = httpReqRes.getParameter("soucer");
		String images = image.replace(" ", "+");
		byte[] bytes = Base64.decode(images);
		InfopubAttachment infopubAttachment = new InfopubAttachment();
		infopubAttachment.setStAttachId(UUID.randomUUID().toString());
		infopubAttachment.setBlContent(bytes);
		infopubAttachment.setStLinkTable("INFOPUB_DEVICE_INFO");
		infopubAttachment.setStLinkId(stDeviceId);
		infopubAttachment.setStFilename("snapshots");
		infopubAttachmentDaoExt.add(infopubAttachment);
		return infopubAttachment;

	}

	/**
	 * 是否在线
	 */
	@Override
	public InfopubDeviceInfo isOnline(HttpReqRes httpReqRes) {
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		if (stDeviceMac == null) {
			throw new NullPointerException("设备ID不能为空");
		} else {
			InfopubDeviceInfo infopubDeviceInfo = new InfopubDeviceInfo();
			infopubDeviceInfo.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubDeviceInfo.setStDeviceMac(stDeviceMac);
			infopubDeviceInfoDao.updateTime(infopubDeviceInfo);
			return infopubDeviceInfo;
		}
	}

	/**
	 * 建行是否在线
	 */
	@Override
	public JSONObject jhisonline(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		String deviceMac = httpReqRes.getParameter("deviceMacList");
		List<String> deviceList = JSON.parseArray(deviceMac, String.class);
		List<String> updateDList = new ArrayList<String>();
		List<String> nUpdateDList = new ArrayList<String>();
		List<String> noList = new ArrayList<String>();
		int count = 0;
		int temp = 0;
		InfopubDeviceInfo infopubDeviceInfo = null;
		for (String di : deviceList) {
			if (null == di || "".equals(di)) {
				continue;
			} else {
				InfopubDeviceInfo emp = infopubDeviceInfoDao.getMac(di);
				if(null != emp){
					infopubDeviceInfo = new InfopubDeviceInfo();
					infopubDeviceInfo.setDtUpdate(new Timestamp(System.currentTimeMillis()));
					infopubDeviceInfo.setStDeviceMac(di);
					temp = infopubDeviceInfoDao.updateTime(infopubDeviceInfo);
				}else{
					noList.add(di);
				}
				
				if(temp>0){
					updateDList.add(di);
					temp = 0;
				}else{
					nUpdateDList.add(di);
				}
			}
		}
		
		obj.put("msg", true);
		obj.put("更新的设备列表", updateDList);
		obj.put("未更新的设备列表", nUpdateDList);
		obj.put("未同步的设备列表", noList);
		return obj;

	}
	
	@Override
	public JSONObject getAlldeviceInfo(HttpReqRes httpReqRes) {
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String online = httpReqRes.getParameter("online");
		String adderss = httpReqRes.getParameter("address");
		String stInfopubTypeId = httpReqRes.getParameter("stTypeId");
		Conditions conds = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				conds.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						stAreaId));
			} else {
				throw new NullPointerException("AreaId不能为空");
			}
		}
		if (online != null && !StringUtils.trim(online).isEmpty()) {
			conds.add(new Condition("NM_ONLINE", Condition.OT_LIKE, online));
		}
		if (adderss != null && !StringUtils.trim(adderss).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ADDRESS", Condition.OT_LIKE,
					adderss));
		}
		if (stInfopubTypeId != null
				&& !StringUtils.trim(stInfopubTypeId).isEmpty()) {
			conds.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL,
					stInfopubTypeId));
		} else {
			conds.add(new Condition("ST_TYPE_ID", Condition.OT_UNEQUAL,
					"29cf0e14-2d45-4ee7-886d-39a062165149"));
		}
		List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(conds, null);
		// 获取设备类型图片
		for (InfopubDeviceInfo infopubDeviceInfo : list) {
			String stTypeId = infopubDeviceInfo.getStTypeId();
			/*
			 * InfopubAttachment infopubAttachment =
			 * infopubAttachmentDaoExt.getLinkId(stTypeId);
			 * if(infopubAttachment!=null){ Conditions condImage =
			 * Conditions.newAndConditions(); condImage.add(new
			 * Condition("ST_LINK_ID", Condition.OT_EQUAL,
			 * infopubAttachment.getStLinkId())); byte[] blob =
			 * BlobHelper.getBlob("INFOPUB_ATTACHMENT", "BL_CONTENT",
			 * condImage.toString(),condImage.getObjectArray()); String
			 * base64Str = DatatypeConverter.printBase64Binary(blob);
			 * infopubDeviceInfo.setStConfigId(base64Str); }
			 */
			if (!stTypeId.equals("")) {
				InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao
						.get(stTypeId);
				infopubDeviceInfo.setStConfigId(infopubDeviceType.getStIcon());
			}
			// 查询设备对应办理事项数量
			int selmDeviceItem = selmDeviceItemDao.getCount(infopubDeviceInfo
					.getStDeviceId());
			infopubDeviceInfo.setNmOrder(new BigDecimal(selmDeviceItem));

			String addressId = infopubDeviceInfo.getStAddressId();
			/*
			 * if(addressId!=null){ InfopubAddress infopubAddress =
			 * infopubAddressDao.get(addressId);
			 * infopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
			 * infopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
			 * infopubDeviceInfo
			 * .setStDeviceAddress(infopubAddress.getStCity()+infopubAddress
			 * .getStDistrict
			 * ()+infopubAddress.getStStreet()+infopubAddress.getStAddress()); }
			 */
		}
		// 获取设备对应外设
		for (int i = 0; i < list.size(); i++) {
			InfopubDeviceInfo infopubDeviceInfo = list.get(i);
			List<InfopubOdeviceStatus> infopubOdeviceStatus = infopubOdeviceStatusDao
					.getByTypeCode(infopubDeviceInfo);
			if (infopubOdeviceStatus == null) {

			} else {
				JSONObject returnObj = new JSONObject();
				// Log.debug(JSONArray.fromObject(infopubOdeviceStatus).toString());
				returnObj.put("data", JSONArray
						.fromObject(infopubOdeviceStatus).toString());
				infopubDeviceInfo.setStDesc(returnObj.toString());
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		return obj;
	}

	@Override
	public JSONObject infopubMapList(HttpReqRes httpReqRes) {
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String username = httpReqRes.getParameter("userName");
		String stPermission = httpReqRes.getParameter("stPermission");
		String online = httpReqRes.getParameter("online");
		String adderss = httpReqRes.getParameter("address");
		String stInfopubTypeId = httpReqRes.getParameter("stTypeId");
		
		Conditions usercon = Conditions.newAndConditions();
		usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
		SmsUser smsUser = smsUserDao.query(usercon, null).get(0);
		
		Conditions ruconds = Conditions.newAndConditions();
		ruconds.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_EQUAL, username));
		SmsRole smsRole = smsRoleDao.queryRoleByUser(ruconds, null);
		
		Conditions conds = Conditions.newAndConditions();
		List<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// int queryNumber = infopubDeviceInfoDao.queryNumber(null,null);//总数
		// int onlineNumber = 0;//在线
		// String suffixLat = "GROUP BY NM_LAT";
		List<InfopubAddress> infopubAddressList = infopubAddressDao.queryLat(
				null, null);
		for (InfopubAddress infopubAddress : infopubAddressList) {
			hashMap = new HashMap<String, Object>();
			BigDecimal nmLat = infopubAddress.getNmLat();
			BigDecimal nmLng = infopubAddress.getNmLng();
			conds = Conditions.newAndConditions();
			/*
			 * if (!stPermission.equals("project_admin")) { if (stAreaId != null
			 * && !StringUtils.trim(stAreaId).isEmpty()) { conds.add(new
			 * Condition("ST_AREA_ID", Condition.OT_LIKE, stAreaId)); }else {
			 * throw new NullPointerException("AreaId不能为空"); } }
			 */
			if (online != null && !StringUtils.trim(online).isEmpty()) {
				if (online.equals("1")) {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					// System.out.println(formatter.format(date));
					Calendar calendarSta = Calendar.getInstance();
					calendarSta.add(Calendar.MINUTE, -1);
					Calendar calendarEnd = Calendar.getInstance();
					calendarEnd.add(Calendar.MINUTE, +1);
					conds.add(new Condition("DT_UPDATE",
							Condition.OT_GREATER_EQUAL, formatter
									.format(calendarSta.getTime())));
					conds.add(new Condition("DT_UPDATE",
							Condition.OT_LESS_EQUAL, formatter
									.format(calendarEnd.getTime())));
				} else {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Calendar calendarSta = Calendar.getInstance();
					calendarSta.add(Calendar.MINUTE, -1);
					conds.add(new Condition("DT_UPDATE",
							Condition.OT_LESS_EQUAL, formatter
									.format(calendarSta.getTime())));
				}
			}
			if (adderss != null && !StringUtils.trim(adderss).isEmpty()) {
				conds.add(new Condition("ST_DEVICE_ADDRESS", Condition.OT_LIKE,
						adderss));
			}
			if (stInfopubTypeId != null
					&& !StringUtils.trim(stInfopubTypeId).isEmpty()) {
				conds.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL,
						stInfopubTypeId));
			} else {
				conds.add(new Condition("ST_TYPE_ID", Condition.OT_UNEQUAL,
						"29cf0e14-2d45-4ee7-886d-39a062165149"));
				
			}
			conds.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL,
					infopubAddress.getStAddressId()));
			if("area".equals(smsRole.getStRoleCode())){
				conds.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL,
						smsUser.getStAreaId()));	
			}
			
			List<InfopubDeviceInfoMap> infopubDeviceInfoList = infopubDeviceInfoDao
					.queryMap(conds, null);
			if (infopubDeviceInfoList.size() != 0) {
				for (InfopubDeviceInfoMap infopubDeviceInfo : infopubDeviceInfoList) {
					String stTypeId = infopubDeviceInfo.getStTypeId();
					if (!stTypeId.equals("")) {
						InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao
								.get(stTypeId);
						infopubDeviceInfo.setStConfigId(infopubDeviceType
								.getStIcon());
					}
					// 查询设备对应办理事项数量
					int selmDeviceItem = selmDeviceItemDao
							.getCount(infopubDeviceInfo.getStDeviceId());
					infopubDeviceInfo
							.setNmOrder(new BigDecimal(selmDeviceItem));

					// 外设状态
					List<InfopubOdeviceStatus> byDeviceId = infopubOdeviceStatusDao
							.getByDeviceId(infopubDeviceInfo.getStDeviceMac());
					if (byDeviceId != null) {
						List infopubOdeviceStatusList = infopubOdeviceStatusDao
								.getMapDeviceStatus(infopubDeviceInfo
										.getStDeviceMac());
						if (infopubOdeviceStatusList != null) {
							if (infopubOdeviceStatusList.contains("2")) {
								infopubDeviceInfo
										.setNmOnline(new BigDecimal(2));
							}
							infopubDeviceInfo
									.setStatusList(infopubOdeviceStatusList);
						}
					}
				}
				hashMap.put("LNG", nmLng);
				hashMap.put("LAT", nmLat);
				hashMap.put("COUNT", infopubDeviceInfoList.size());
				hashMap.put("MAP", infopubDeviceInfoList);
				arrayList.add(hashMap);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("data", arrayList);
		return obj;

	}

	/**
	 * 查询异常设备得mac
	 */
	@Override
	public JSONObject getOdeviceStatusInfoListCount(HttpReqRes httpReqRes) {

		String stDeviceId = httpReqRes.getParameter("stDeviceId");

		ArrayList arrayList = new ArrayList();
		if (stDeviceId != null && !StringUtils.trim(stDeviceId).isEmpty()) {
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_DEVICE_MAC", Condition.OT_LIKE,
					stDeviceId));
			List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
					.query(cond, null);
			/*
			 * for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList)
			 * { String stTypeId = infopubDeviceInfo.getStTypeId();
			 * InfopubAttachment infopubAttachment =
			 * infopubAttachmentDaoExt.getLinkId(stTypeId);
			 * if(infopubAttachment!=null){ Conditions condImage =
			 * Conditions.newAndConditions(); condImage.add(new
			 * Condition("ST_LINK_ID", Condition.OT_EQUAL,
			 * infopubAttachment.getStLinkId())); byte[] blob =
			 * BlobHelper.getBlob("INFOPUB_ATTACHMENT", "BL_CONTENT",
			 * condImage.toString(),condImage.getObjectArray()); String
			 * base64Str = DatatypeConverter.printBase64Binary(blob);
			 * infopubDeviceInfo.setStConfigId(base64Str); } }
			 */
			// 获取设备对应外设
			for (int i = 0; i < infopubDeviceInfoList.size(); i++) {
				InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoList
						.get(i);
				List<InfopubOdeviceStatus> infopubOdevice = infopubOdeviceStatusDao
						.getByTypeCode(infopubDeviceInfo);
				String addressId = infopubDeviceInfo.getStAddressId();
				/*
				 * if(addressId!=null){ InfopubAddress infopubAddress =
				 * infopubAddressDao.get(addressId);
				 * infopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
				 * infopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
				 * infopubDeviceInfo
				 * .setStDeviceAddress(infopubAddress.getStCity(
				 * )+infopubAddress.
				 * getStDistrict()+infopubAddress.getStStreet()+
				 * infopubAddress.getStAddress()); }
				 */
				if (infopubOdevice == null) {

				} else {
					JSONObject returnObj = new JSONObject();
					// Log.debug(JSONArray.fromObject(infopubOdeviceStatus).toString());
					returnObj.put("data", JSONArray.fromObject(infopubOdevice)
							.toString());
					infopubDeviceInfo.setStDesc(returnObj.toString());
				}
			}
			JSONObject returnObj = new JSONObject();
			arrayList.add(infopubDeviceInfoList);
			returnObj.put("data", arrayList);
			return returnObj;
		} else {
			List<InfopubOdeviceStatus> infopubOdeviceStatusList = infopubOdeviceStatusDao
					.getCount();

			for (InfopubOdeviceStatus infopubOdeviceStatus : infopubOdeviceStatusList) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL,
						infopubOdeviceStatus.getStDeviceId()));
				List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
						.query(conds, null);
				for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
					String stTypeId = infopubDeviceInfo.getStTypeId();
					InfopubAttachment infopubAttachment = infopubAttachmentDaoExt
							.getLinkId(stTypeId);
					if (infopubAttachment != null) {
						Conditions condImage = Conditions.newAndConditions();
						condImage.add(new Condition("ST_LINK_ID",
								Condition.OT_EQUAL, infopubAttachment
										.getStLinkId()));
						byte[] blob = BlobHelper.getBlob("INFOPUB_ATTACHMENT",
								"BL_CONTENT", condImage.toString(),
								condImage.getObjectArray());
						String base64Str = DatatypeConverter
								.printBase64Binary(blob);
						infopubDeviceInfo.setStConfigId(base64Str);
					}
				}
				// 获取设备对应外设
				for (int i = 0; i < infopubDeviceInfoList.size(); i++) {
					InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoList
							.get(i);
					List<InfopubOdeviceStatus> infopubOdevice = infopubOdeviceStatusDao
							.getByTypeCode(infopubDeviceInfo);
					String addressId = infopubDeviceInfo.getStAddressId();
					/*
					 * if(addressId!=null){ InfopubAddress infopubAddress =
					 * infopubAddressDao.get(addressId);
					 * infopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
					 * infopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
					 * infopubDeviceInfo
					 * .setStDeviceAddress(infopubAddress.getStCity
					 * ()+infopubAddress
					 * .getStDistrict()+infopubAddress.getStStreet
					 * ()+infopubAddress.getStAddress()); }
					 */
					if (infopubOdevice == null) {

					} else {
						JSONObject returnObj = new JSONObject();
						// Log.debug(JSONArray.fromObject(infopubOdeviceStatus).toString());
						returnObj
								.put("data",
										JSONArray.fromObject(infopubOdevice)
												.toString());
						infopubDeviceInfo.setStDesc(returnObj.toString());
					}
				}
				arrayList.add(infopubDeviceInfoList);

			}
			JSONObject returnObj = new JSONObject();
			returnObj.put("data", arrayList);
			return returnObj;
		}

	}

	/**
	 * 按时间段查询异常
	 */
	@Override
	public JSONObject getOdeviceStatusInfoListdate(HttpReqRes httpReqRes) {
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		Conditions conds = Conditions.newAndConditions();
		String startStr = stampToDate(startDate);
		String endStr = stampToDate(endDate);
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_UPDATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startStr + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_UPDATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endStr + " 23:59:59")));
		}

		conds.add(new Condition("NM_EXCEPTION", Condition.OT_EQUAL, "1"));
		String suffix = "GROUP BY ST_DEVICE_ID";
		List<InfopubOdeviceStatus> infopubOdeviceStatusList = infopubOdeviceStatusDao
				.queryDate(conds, suffix);
		ArrayList arrayList = new ArrayList();
		for (InfopubOdeviceStatus infopubOdeviceStatus : infopubOdeviceStatusList) {
			Conditions cond = Conditions.newAndConditions();
			cond.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL,
					infopubOdeviceStatus.getStDeviceId()));
			List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
					.query(cond, null);
			arrayList.add(infopubDeviceInfoList);
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("data", arrayList);
		return returnObj;
	}

	// 时间戳转化
	private String stampToDate(String stampStr) {

		Long stamp = new Long(stampStr);
		Date date = new Date(stamp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	@Override
	public List<AreaView> getSystemAllAreaTree() {
		List<InfopubArea> list = infopubAreaDao.query(null, null);
		return getTree(list, "infopub_area_root");
	}

	private List<AreaView> getTree(List<InfopubArea> list,
			String parentCatalogId) {
		List<AreaView> tree = getChildrenTree(list, parentCatalogId);
		Collections.sort(tree, new Comparator<AreaView>() {

			@Override
			public int compare(AreaView o1, AreaView o2) {
				BigDecimal bo1 = o1.getNmOrder() == null ? new BigDecimal("0")
						: o1.getNmOrder();
				BigDecimal bo2 = o2.getNmOrder() == null ? new BigDecimal("0")
						: o2.getNmOrder();
				return bo1.intValue() - bo2.intValue();
			}
		});
		List<AreaView> result = new ArrayList<AreaView>();
		for (int i = 0; i < tree.size(); i++) {
			AreaView view = tree.get(i);
			if (StringUtils.isNotBlank(view.getStParentAreaId())) {
				view.setChildrenList(getTree(list, view.getStAreaId()));
			}
			result.add(view);
		}
		return result;
	}

	private List<AreaView> getChildrenTree(List<InfopubArea> list,
			String parentCatalogId) {
		List<AreaView> result = new ArrayList<AreaView>();
		for (InfopubArea area : list) {
			if (parentCatalogId.equals(area.getStParentAreaId())) {
				AreaView view = new AreaView();
				try {
					BeanUtils.copy(area, view);
				} catch (Exception e) {
					e.printStackTrace();
				}
				result.add(view);
			}
		}
		return result;
	}

	/**
	 * 根据主键 {@link INFOPUB_AREA#ST_AREA_ID}获取组织机构表
	 * 
	 * @param stAreaId
	 *            区域表主键 {@link INFOPUB_AREA#ST_AREA_ID}
	 * @return 区域表实例
	 */
	@Override
	public InfopubArea get(String stAreaId) {
		if (StringUtils.trimToEmpty(stAreaId).isEmpty())
			throw new NullPointerException(
					"Parameter stOrganId cannot be null.");
		return infopubAreaDao.get(stAreaId);
	}

	@Override
	public InfopubDeviceInfo getDeviceId(String stDeviceId) {
		if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
			throw new NullPointerException(
					"Parameter stOrganId cannot be null.");
		return infopubDeviceInfoDao.getByMacId(stDeviceId);
	}

	@Override
	public InfopubDeviceType getDeviceType(String stTypeId) {
		if (StringUtils.trimToEmpty(stTypeId).isEmpty())
			throw new NullPointerException(
					"Parameter stOrganId cannot be null.");
		return infopubDeviceTypeDao.get(stTypeId);
	}

	@Override
	public InfopubDeviceInfo getCode(String stCode) {
		if (StringUtils.trimToEmpty(stCode).isEmpty())
			throw new NullPointerException(
					"Parameter stOrganId cannot be null.");
		return infopubDeviceInfoDao.getCode(stCode);
	}

	@Override
	public InfopubDeviceInfo getMac(String stMac) {
		if (StringUtils.trimToEmpty(stMac).isEmpty())
			throw new NullPointerException(
					"Parameter stOrganId cannot be null.");
		return infopubDeviceInfoDao.getMac(stMac);
	}

	/**
	 * 查询区域信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 区域表列表
	 */
	@Override
	public PaginationArrayList<InfopubArea> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// 区域名称
		String areaName = wrapper.getParameter("areaName");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");

		// 排列内容
		String orderName = wrapper.getParameter("columns["
				+ wrapper.getParameter("order[0][column]") + "][data]");
		// 排序内容
		String orderType = wrapper.getParameter("order[0][dir]");
		// 排序
		String suffix = "ORDER BY NM_ORDER";
		// 存在的场合
		if (orderName != null) {
			// 区域代码
			if ("stAreaCode".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_CODE " + orderType.toUpperCase();
				// 区域名称
			} else if ("stAreaName".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_NAME " + orderType.toUpperCase();
				// 区域描述
			} else if ("stDesc".equals(orderName)) {
				suffix = "ORDER BY ST_URL " + orderType.toUpperCase();
				// 创建时间
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			}
			Log.info("orderName:" + orderName + "*****orderType:" + orderType);
		}
		// 获取数据
		conds = Conditions.newAndConditions();
		// 区域名称存在的场合
		if (areaName != null) {
			if (!StringUtils.trim(areaName).isEmpty()) {
				// 区域名称
				conds.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE,
						areaName));
			}
		}
		// 开始时间存在的场合
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// 开始时间
				conds.add(new Condition("DT_CREATE",
						Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate + " 00:00:00")));
			}
		}
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			// 每页长度
			String length = wrapper.getParameter("length");
			// 页面长度
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// 开始页
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// 第一页的场合
				if (start != 0) {
					// 当前页
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		return infopubAreaDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link INFOPUB_AREA#ST_AREA_ID}删除组织机构表
	 * 
	 * @param stAreaId
	 *            区域表主键 {@link INFOPUB_AREA#ST_AREA_ID}
	 */
	@Override
	public void remove(String[] stAreaId) {
		if (stAreaId.length == 0)
			throw new NullPointerException(
					"Parameter stOrganId cannot be null.");
		infopubAreaDao.delete(stAreaId);
	}

	/**
	 * 保存或更新区域表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 区域表实例
	 */
	@Override
	public InfopubArea saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);

		String stAreaId = wrapper.getParameter(InfopubArea.ST_AREA_ID);
		InfopubArea oldInfopubArea = null;
		if (!StringUtils.trimToEmpty(stAreaId).isEmpty()) {
			oldInfopubArea = infopubAreaDao.get(stAreaId);
		}
		if (oldInfopubArea == null) {// new
			InfopubArea newInfopubArea = (InfopubArea) t4r
					.toBean(InfopubArea.class);
			newInfopubArea.setStAreaId(UUID.randomUUID().toString());
			// 插入时间
			newInfopubArea
					.setDtCreate(new Timestamp(System.currentTimeMillis()));
			infopubAreaDao.add(newInfopubArea);
			return newInfopubArea;
		} else {// update
			oldInfopubArea = (InfopubArea) t4r.toBean(oldInfopubArea,
					InfopubArea.class);
			// 更新时间
			oldInfopubArea
					.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			infopubAreaDao.update(oldInfopubArea);
			return oldInfopubArea;
		}
	}

	/**
	 * 检查资源编号
	 */
	@Override
	public boolean checkAreaCode(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("参数不能为空");
		}
		boolean reslut = false;
		// 区域编号
		String areaCode = wrapper.getParameter("areaCode");
		// 区域ID
		String stAreaId = wrapper.getParameter("ST_AREA_ID");

		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_AREA_CODE", Condition.OT_EQUAL, areaCode));
		// 获取区域
		InfopubArea infopubArea = infopubAreaDao.checkAreaCode(conds);
		// 数据存在且不是修改的场合
		if (infopubArea != null && !stAreaId.equals(infopubArea.getStAreaId())) {
			reslut = true;
		}
		Log.info("区域编号:" + areaCode + ",区域ID:" + stAreaId + ",是否存在:"
				+ String.valueOf(reslut));
		return reslut;
	}

	

	@Override
	public JSONObject areaList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		/*
		 * conds.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, null));
		 */
		String deviceTypeName = httpReqRes.getParameter("deviceTypeName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		if (deviceTypeName != null
				&& !StringUtils.trim(deviceTypeName).isEmpty()) {
			conds.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE,
					deviceTypeName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY NM_ORDER DESC";
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubArea> areaList = infopubAreaDao.query(conds, suffix,
				pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(areaList, InfopubArea.class)).getString(
					"total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", areaList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", areaList);
		return returnObj;
	}

	/**
	 * 设备信息子类别列表
	 */
	@Override
	public JSONObject subDeviceInfoTypeList(HttpReqRes httpReqRes) {

		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String stTypeId = httpReqRes.getParameter("stTypeId");
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL,
				stTypeId));
		String deviceTypeName = httpReqRes.getParameter("deviceTypeName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (deviceTypeName != null
				&& !StringUtils.trim(deviceTypeName).isEmpty()) {
			conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,
					deviceTypeName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY NM_ORDER";
		if (orderName != null) {
			if ("stTypeName".equals(orderName)) {
				suffix = "ORDER BY ST_TYPE_NAME " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao
				.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceTypeList,
							InfopubDeviceType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceTypeList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceTypeList);
		return returnObj;

	}

	/**
	 * 保存修改信息，发送修改xml文件操作指令
	 */
	@Override
	public String modifyOperate(HttpReqRes httpReqRes) {
		// 发送消息指令
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		if (user == null) {
			try {
				LogHelper.error("session 过期，无法获取用户");
				httpReqRes.getResponse().sendRedirect(
						AppContext.webRootPath + "/");
				return "";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String config = Config.get("device.type.0001");
		String stDeviceId = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_DEVICE_ID);
		JSONObject jsonObject = JSONObject.fromObject(config);
		JSONArray jsonArray = jsonObject.getJSONArray("list");
		JSONObject json = new JSONObject();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jop = jsonArray.getJSONObject(i);
			String key = (String) jop.get("empAnnualIncome");
			String value = httpReqRes.getParameter(key);
			// System.out.println(jop.get("empAnnualIncome"));
			if (value != null && !StringUtils.trimToEmpty(value).isEmpty()) {
				json.put(key, value);
			}
		}
		/*
		 * String config = Config.get("device.type.0001");
		 * com.alibaba.fastjson.JSONObject jsobject =
		 * com.alibaba.fastjson.JSONObject .parseObject(config);
		 * 
		 * String stDeviceId = httpReqRes
		 * .getParameter(InfopubDeviceInfo.ST_DEVICE_ID); JSONObject json = new
		 * JSONObject(); for (Map.Entry<String, Object> entry :
		 * jsobject.entrySet()) { String key = (String) entry.getValue(); String
		 * value = httpReqRes.getParameter(key); if (value != null &&
		 * !StringUtils.trimToEmpty(value).isEmpty()) { json.put(key, value); }
		 * }
		 */
		// 判断是否有输入修改信息
		if (!json.toString().equals("{}")) {
			InfopubAttachment info = new InfopubAttachment();
			info.setStAttachId(UUID.randomUUID().toString());
			info.setClContent(json.toString());
			infopubAttachmentDaoExt.addModify(info);

			InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
					.get(stDeviceId);
			infopubDeviceInfo.setStConfigId(info.getStAttachId());
			infopubDeviceInfoDao.update(infopubDeviceInfo);
			String result = StringUtils.EMPTY;
			result = InfopubUtils.sendOptMsg(infopubDeviceInfo, "modify");
			InfopubDeviceLog deviceLog = new InfopubDeviceLog();
			deviceLog.setStOperator(user.getStUserName());
			if ("success".equals(result)) {
				deviceLog.setStMsg("操作成功");
			} else {
				deviceLog.setStMsg(result);
				deviceLog.setStLevel("error");
			}
			deviceLog.setStDeviceId(infopubDeviceInfo.getStDeviceId());
			deviceLog.setStOperand(infopubDeviceInfo.getStDeviceName());
			deviceLog.setStLocation(infopubDeviceInfo.getStDeviceAddress());
			deviceLog.setStAction(getAciton("modify"));
			infopubDeviceLogDaoExt.add(deviceLog);
			return json.toString();
		}

		return "";
	}

	/**
	 * 修改xml文件(获取数据)
	 */
	@Override
	public String modifyXML(HttpReqRes httpReqRes) {
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		if (stDeviceMac == null) {
			throw new NullPointerException("参数不能为空");
		}
		InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
				.getByMacId(stDeviceMac);
		if (infopubDeviceInfo == null
				|| infopubDeviceInfo.getStConfigId() == null) {
			return "{}";
		}
		InfopubAttachment infopubAttachment = infopubAttachmentDaoExt
				.get(infopubDeviceInfo.getStConfigId());
		if (infopubAttachment == null) {
			return "{}";
		}
		String jsonString = infopubAttachment.getClContent();
		if (jsonString != null) {
			return jsonString;
		} else {
			return "{}";
		}
	}

	@Override
	public InfopubDeviceResult addOrUpdate(HttpReqRes httpReqRes)
			throws Exception {
		String stDeviceMac = httpReqRes
				.getParameter(InfopubDeviceInfo.ST_DEVICE_MAC);
		String nmMemUsed = httpReqRes
				.getParameter(InfopubDeviceResult.NM_MEM_USED);
		String nmCpuUsed = httpReqRes
				.getParameter(InfopubDeviceResult.NM_CPU_USED);
		String clHdUsed = httpReqRes
				.getParameter(InfopubDeviceResult.CL_HD_USED);
		String clNetUsed = httpReqRes
				.getParameter(InfopubDeviceResult.CL_NET_USED); // 网络使用情况
		String clServiceUsed = httpReqRes
				.getParameter(InfopubDeviceResult.CL_SERVICE_USED);// 服务使用情况
		Clob netUsed = null;
		if (clNetUsed != null && !StringUtils.trim(clNetUsed).isEmpty()) {
			char[] ch = clNetUsed.toCharArray();
			netUsed = new SerialClob(ch);
		}
		Clob serviceUsed = null;
		if (clServiceUsed != null && !StringUtils.trim(clServiceUsed).isEmpty()) {
			char[] ch = clServiceUsed.toCharArray();
			serviceUsed = new SerialClob(ch);
		}
		Clob c = null;
		if (clHdUsed != null && !StringUtils.trim(clHdUsed).isEmpty()) {
			char[] ch = clHdUsed.toCharArray();
			c = new SerialClob(ch);
		}
		InfopubDeviceResult oldInfopubDeviceResult = null;
		if (stDeviceMac != null && !StringUtils.trim(stDeviceMac).isEmpty()) {
			InfopubDeviceInfo deviceInfo = infopubDeviceInfoDao
					.getByMacId(stDeviceMac);
			if (deviceInfo == null) {
				return null;
			}
			oldInfopubDeviceResult = infopubDeviceResultDao.getDeviceResultId(stDeviceMac);
			if (oldInfopubDeviceResult != null) {
				InfopubDeviceResult infopubDeviceResult = infopubDeviceResultHisDao
						.get(stDeviceMac);
				if (infopubDeviceResult != null) {
					infopubDeviceResult.setDtCreate(new Timestamp(System
							.currentTimeMillis()));
					infopubDeviceResultHisDao.update(oldInfopubDeviceResult);
				} else {
					// 更改前先保存到历史记录中
					infopubDeviceResultHisDao.add(oldInfopubDeviceResult);
				}
				oldInfopubDeviceResult
						.setStDeviceId(deviceInfo.getStDeviceId());
				oldInfopubDeviceResult.setNmCpuUsed(new BigDecimal(nmCpuUsed));
				oldInfopubDeviceResult.setNmMemUsed(new BigDecimal(nmMemUsed));
				oldInfopubDeviceResult.setClHdUsed(c);
				oldInfopubDeviceResult.setClNetUsed(netUsed);
				oldInfopubDeviceResult.setClServiceUsed(serviceUsed);
				oldInfopubDeviceResult.setDtCreate(new Timestamp(System
						.currentTimeMillis()));
				infopubDeviceResultDao.update(oldInfopubDeviceResult);
				return oldInfopubDeviceResult;

			} else {
				InfopubDeviceResult newInfopubDeviceResult = new InfopubDeviceResult();
				newInfopubDeviceResult.setStDeviceResultId(stDeviceMac);
				newInfopubDeviceResult
						.setStDeviceId(deviceInfo.getStDeviceId());
				newInfopubDeviceResult.setNmCpuUsed(new BigDecimal(nmCpuUsed));
				newInfopubDeviceResult.setNmMemUsed(new BigDecimal(nmMemUsed));
				newInfopubDeviceResult.setClHdUsed(c);
				newInfopubDeviceResult.setClNetUsed(netUsed);
				newInfopubDeviceResult.setClServiceUsed(serviceUsed);
				newInfopubDeviceResult.setDtCreate(new Timestamp(System
						.currentTimeMillis()));
				infopubDeviceResultDao.add(newInfopubDeviceResult);
				// infopubDeviceResultHisDao.add(newInfopubDeviceResult);
				return newInfopubDeviceResult;
			}
		}

		return null;
	}

	/**
	 * 外设调用接口，保存外设状态信息
	 */
	/*
	 * @Override public InfopubOdeviceStatus outDeviceStatusSave(HttpReqRes
	 * httpReqRes) { // 设备id String stDeviceId =
	 * httpReqRes.getParameter("stDeviceId"); // 外设标识 String stOutDeviceCode =
	 * httpReqRes.getParameter("stOutDeviceCode"); // 是否异常 // Integer
	 * nmException = httpReqRes.getParameterInteger("nmException"); BigDecimal
	 * nmException = httpReqRes .getParameterBigdecimal("nmException"); // 异常原因
	 * String stCause = httpReqRes.getParameter("stCause"); // 是否通知 // Integer
	 * nmNotice = httpReqRes.getParameterInteger("nmNotice"); BigDecimal
	 * nmNotice = httpReqRes.getParameterBigdecimal("nmNotice"); // 总量 //
	 * Integer nmTotal = httpReqRes.getParameterInteger("nmTotal"); BigDecimal
	 * nmTotal = httpReqRes.getParameterBigdecimal("nmTotal"); // 剩余量 // Integer
	 * nmRemain = httpReqRes.getParameterInteger("nmRemain"); BigDecimal
	 * nmRemain = httpReqRes.getParameterBigdecimal("nmRemain"); // int
	 * intnmRemain = nmRemain.intValue(); // 拓展1 String stExt1 =
	 * httpReqRes.getParameter("stExt1"); // 拓展2 String stExt2 =
	 * httpReqRes.getParameter("stExt2"); // 存放使用量 Integer count =
	 * httpReqRes.getParameterInteger("count"); BigDecimal useCount = new
	 * BigDecimal(count.toString());
	 * 
	 * InfopubOdeviceStatus oldInfopubOdeviceStatus = null; if (stDeviceId !=
	 * null && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) { if
	 * (stOutDeviceCode != null &&
	 * !StringUtils.trimToEmpty(stOutDeviceCode).isEmpty()) {
	 * InfopubOdeviceStatus info = new InfopubOdeviceStatus();
	 * info.setStDeviceId(stDeviceId); info.setStOutDeviceCode(stOutDeviceCode);
	 * oldInfopubOdeviceStatus = infopubOdeviceStatusDao
	 * .getOdeviceStatus(info); } } if (oldInfopubOdeviceStatus != null) { //
	 * 打印机放纸或其他外设 if (count == 0) {
	 * //判断外设状态是否异常，异常将oldInfopubOdeviceStatus存储到历史中 if
	 * (nmException.compareTo(new BigDecimal(1)) == 0) { if (stCause != null &&
	 * oldInfopubOdeviceStatus.getStCause() != null) { if
	 * (stCause.equals(oldInfopubOdeviceStatus.getStCause())) { if
	 * (nmNotice.compareTo(oldInfopubOdeviceStatus.getNmNotice()) == 0) { if
	 * (nmTotal.compareTo(oldInfopubOdeviceStatus.getNmTotal()) == 0) {
	 * System.out.println("外设状态未改变...."); }else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } } else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }else if (stCause
	 * == null && oldInfopubOdeviceStatus.getStCause() == null) { if
	 * (nmNotice.compareTo(oldInfopubOdeviceStatus.getNmNotice()) == 0) { if
	 * (nmTotal.compareTo(oldInfopubOdeviceStatus.getNmTotal()) == 0) {
	 * System.out.println("外设状态未改变...."); }else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } } else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } }else {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } //
	 * 判断nmException是否变化，发生变化，添加到历史中。 if
	 * (nmException.compareTo(oldInfopubOdeviceStatus.getNmException()) != 0) {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } // 更新外设状态 //
	 * 没有异常，成功总次数+1,否则失败总次数+1 if (nmException.compareTo(new BigDecimal(0)) == 0)
	 * { BigDecimal newNmHisStotal =
	 * oldInfopubOdeviceStatus.getNmHisStotal().add(new BigDecimal(1));
	 * oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal); } else {
	 * BigDecimal newNmHisFtotal =
	 * oldInfopubOdeviceStatus.getNmHisFtotal().add(new BigDecimal(1));
	 * oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal); }
	 * oldInfopubOdeviceStatus.setNmException(nmException);
	 * oldInfopubOdeviceStatus.setNmNotice(nmNotice);
	 * oldInfopubOdeviceStatus.setStCause(stCause);
	 * oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
	 * .currentTimeMillis()));
	 * 
	 * // 判断nmException是否为1，为1则异常，结束方法。 if (nmException.compareTo(new
	 * BigDecimal(1)) == 0) {
	 * infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus); return
	 * oldInfopubOdeviceStatus; }
	 * 
	 * oldInfopubOdeviceStatus.setNmHisTotal(oldInfopubOdeviceStatus.getNmTotal()
	 * ); // 判断剩余量是大于零还是小于零 BigDecimal oldnmRemain =
	 * oldInfopubOdeviceStatus.getNmRemain(); // 大于0,nmTotal = nmTotal + 剩余量 if
	 * (oldnmRemain.compareTo(new BigDecimal(0)) >= 0) {
	 * oldInfopubOdeviceStatus.setNmTotal(nmTotal.add(oldnmRemain));
	 * oldInfopubOdeviceStatus.setNmRemain(nmTotal.add(oldnmRemain)); }else { //
	 * 小于0,nmTotal = nmTotal oldInfopubOdeviceStatus.setNmTotal(nmTotal);
	 * oldInfopubOdeviceStatus.setNmRemain(nmTotal); }
	 * 
	 * oldInfopubOdeviceStatus.setStExt1(stExt1);
	 * oldInfopubOdeviceStatus.setStExt2(stExt2);
	 * 
	 * infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus); return
	 * oldInfopubOdeviceStatus; } else { // 打印机打印 //
	 * 判断nmException是否变化，发生变化，添加到历史中。 if
	 * (nmException.compareTo(oldInfopubOdeviceStatus.getNmException()) != 0) {
	 * infopubOdeviceResultDao.add(oldInfopubOdeviceStatus); } if
	 * (nmException.compareTo(new BigDecimal(0)) == 0) { //
	 * 没有异常，成功总次数+1,否则失败总次数+1 BigDecimal newNmHisStotal =
	 * oldInfopubOdeviceStatus.getNmHisStotal().add(new BigDecimal(1));
	 * oldInfopubOdeviceStatus.setNmHisStotal(newNmHisStotal); } else {
	 * BigDecimal newNmHisFtotal =
	 * oldInfopubOdeviceStatus.getNmHisFtotal().add(new BigDecimal(1));
	 * oldInfopubOdeviceStatus.setNmHisFtotal(newNmHisFtotal); }
	 * oldInfopubOdeviceStatus.setNmException(nmException);
	 * oldInfopubOdeviceStatus.setNmNotice(nmNotice);
	 * oldInfopubOdeviceStatus.setStCause(stCause);
	 * oldInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
	 * .currentTimeMillis()));
	 * 
	 * oldInfopubOdeviceStatus.setStExt1(stExt1);
	 * oldInfopubOdeviceStatus.setStExt2(stExt2);
	 * 
	 * // 判断nmException是否为1，为1则异常，结束方法。 if (nmException.compareTo(new
	 * BigDecimal(1)) == 0) {
	 * infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus); return
	 * oldInfopubOdeviceStatus; } // 查询到的剩余量 BigDecimal oldnmRemain =
	 * oldInfopubOdeviceStatus.getNmRemain(); // 减少后 BigDecimal newnmRemain =
	 * oldnmRemain.subtract(useCount);
	 * oldInfopubOdeviceStatus.setNmRemain(newnmRemain);
	 * infopubOdeviceStatusDao.update(oldInfopubOdeviceStatus); return
	 * oldInfopubOdeviceStatus; } } else { InfopubOdeviceStatus
	 * newInfopubOdeviceStatus = new InfopubOdeviceStatus();
	 * newInfopubOdeviceStatus.setStOutDeviceResultId(UUID.randomUUID()
	 * .toString()); newInfopubOdeviceStatus.setStDeviceId(stDeviceId);
	 * newInfopubOdeviceStatus.setStOutDeviceCode(stOutDeviceCode);
	 * newInfopubOdeviceStatus.setNmException(nmException);
	 * newInfopubOdeviceStatus.setStCause(stCause);
	 * newInfopubOdeviceStatus.setNmNotice(nmNotice);
	 * newInfopubOdeviceStatus.setDtUpdate(new Timestamp(System
	 * .currentTimeMillis())); newInfopubOdeviceStatus.setStExt1(stExt1);
	 * newInfopubOdeviceStatus.setStExt2(stExt2);
	 * 
	 * // 判断nmException是否为1，为1则异常，保存到历史中。 if (nmException.compareTo(new
	 * BigDecimal(1)) == 0) {
	 * infopubOdeviceResultDao.add(newInfopubOdeviceStatus); }
	 * 
	 * // 没有异常，成功总次数+1,否则失败总次数+1 if (nmException.compareTo(new BigDecimal(0)) ==
	 * 0) { // 没有异常，成功总次数+1,否则失败总次数+1 newInfopubOdeviceStatus.setNmHisStotal(new
	 * BigDecimal(1)); newInfopubOdeviceStatus.setNmHisFtotal(new
	 * BigDecimal(0)); } else { newInfopubOdeviceStatus.setNmHisStotal(new
	 * BigDecimal(0)); newInfopubOdeviceStatus.setNmHisFtotal(new
	 * BigDecimal(1)); } newInfopubOdeviceStatus.setNmTotal(nmTotal);
	 * newInfopubOdeviceStatus.setNmRemain(nmRemain);
	 * newInfopubOdeviceStatus.setNmHisTotal(nmTotal);
	 * infopubOdeviceStatusDao.add(newInfopubOdeviceStatus); return
	 * newInfopubOdeviceStatus; } }
	 */

	/**
	 * 设备报警下的外设报警状态列表
	 */
	@Override
	public JSONObject getOdevicewarnList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}

		JSONObject returnObj = new JSONObject();

		List<InfopubOdeviceStatus> odeviceStatusList = new ArrayList<InfopubOdeviceStatus>();

		String stTypeId = httpReqRes.getParameter("stTypeId");
		String stTypeCode = httpReqRes.getParameter("stTypeCode");
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");

		if (stTypeId == null || StringUtils.trim(stTypeId).isEmpty()) {
			return returnObj;
		}
		// 得到类型下的外设
		Conditions conditions = Conditions.newAndConditions();
		conditions.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL,"LX003"));

		if (stTypeCode != null && !StringUtils.trim(stTypeCode).isEmpty()) {
			conditions.add(new Condition("ST_TYPE_CODE", Condition.OT_LIKE,stTypeCode));
		}
		List<InfopubDeviceType> odeviceList = infopubDeviceTypeDao.query(conditions, null);
		for (int i = 0; i < odeviceList.size(); i++) {
			//code查询
			Conditions con = Conditions.newAndConditions();
			con.add(new Condition("ST_DEVICE_ID", Condition.OT_LIKE,stDeviceMac));
			String code = odeviceList.get(i).getStTypeCode();
			con.add(new Condition("ST_OUT_DEVICE_CODE", Condition.OT_LIKE, code));
			List<InfopubOdeviceStatus> statusList = infopubOdeviceStatusDao.query(con, null);
			if (statusList.size() > 0) {
				InfopubOdeviceStatus info = statusList.get(0);
				info.setStExt2(odeviceList.get(i).getStTypeName());
				odeviceStatusList.add(info);
			} else {
				InfopubOdeviceStatus infopubOdeviceStatus = new InfopubOdeviceStatus();
				infopubOdeviceStatus.setStOutDeviceCode(code);
				infopubOdeviceStatus.setStDeviceId(stDeviceMac);
				infopubOdeviceStatus.setStOutDeviceResultId(UUID.randomUUID()
						.toString());
				infopubOdeviceStatus.setStExt2(odeviceList.get(i)
						.getStTypeName());
				infopubOdeviceStatus.setNmException(new BigDecimal(2));
				infopubOdeviceStatus.setNmNotice(new BigDecimal(2));
				odeviceStatusList.add(infopubOdeviceStatus);
			}
		}
		
		// -------------------------------------
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(odeviceStatusList,
							InfopubOdeviceStatus.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", odeviceStatusList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", odeviceStatusList);
		return returnObj;
	}

	/**
	 * 设备MAC唯一性检查
	 */
	@Override
	public boolean checkDeviceMac(RequestWrapper wrapper) {

		if (wrapper == null) {
			throw new NullPointerException("参数不能为空");
		}
		boolean reslut = false;
		// 区域编号
		String Mac = wrapper.getParameter("Mac");
		// 区域ID
		String stDeviceId = wrapper.getParameter("ST_DEVICE_ID");

		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL, Mac));
		// 获取设备
		InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
				.getByMacId(Mac);
		// 数据存在且不是修改的场合
		if (infopubDeviceInfo != null
				&& !stDeviceId.equals(infopubDeviceInfo.getStAreaId())) {
			reslut = true;
		}
		Log.info("设备mac:" + Mac + ",设备ID:" + stDeviceId + ",是否存在:"
				+ String.valueOf(reslut));
		return reslut;

	}

	@Override
	public JSONObject deviceInfoListMac(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		if (stDeviceMac != null && !StringUtils.trim(stDeviceMac).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_LIKE,
					stDeviceMac));
		}
		List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
				.query(conds, null);
		for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
			String addressId = infopubDeviceInfo.getStAddressId();
			/*
			 * if(addressId!=null){ InfopubAddress infopubAddress =
			 * infopubAddressDao.get(addressId);
			 * infopubDeviceInfo.setNmLng(infopubAddress.getNmLng());
			 * infopubDeviceInfo.setNmLat(infopubAddress.getNmLat());
			 * infopubDeviceInfo
			 * .setStDeviceAddress(infopubAddress.getStCity()+infopubAddress
			 * .getStDistrict
			 * ()+infopubAddress.getStStreet()+infopubAddress.getStStreet()); }
			 */
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceInfoList);
		return returnObj;
	}

	/**
	 * 首页展示头部信息数量
	 */
	@Override
	public JSONObject leading(HttpReqRes httpReqRes) {
		/*
		 * String stAreaId = httpReqRes.getParameter("stAreaId"); String
		 * stPermission = httpReqRes.getParameter("stPermission");
		 */
		/*
		 * String stAreaId = "sop1s1231-0a36-472b-abd5-f821deea7080"; String
		 * stPermission = "123project_admin";
		 */
		Conditions conds = Conditions.newAndConditions();
		/*
		 * if (!stPermission.equals("project_admin")) { if (stAreaId != null &&
		 * !StringUtils.trim(stAreaId).isEmpty()) { conds.add(new
		 * Condition("ST_AREA_ID", Condition.OT_EQUAL, stAreaId)); }else { throw
		 * new NullPointerException("AreaId不能为空"); } }
		 */
		List<InfopubDeviceInfo> infopubDeviceList = infopubDeviceInfoDao.query(
				conds, null);
		int sum = infopubDeviceList.size();// 总数
		int online = 0;// 在线设备
		for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceList) {
			if (infopubDeviceInfo.getNmOnline().intValue() == 1) {
				online++;
			}
		}
		int noOnLine = sum - online; // 离线设备
		int socialSum = 0;
		int typeCount = 0;
		for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceList) {
			// 社会化终端数量
			String stTypeId = infopubDeviceInfo.getStTypeId();
			int suCount = infopubDeviceTypeDao.suCount(stTypeId, 1);
			typeCount = typeCount + suCount;
		}
		socialSum = socialSum + typeCount; // 社会化终端
		int govSum = sum - socialSum;// 政务终端

		// 异常数量
		int Excount = 0;
		for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceList) {
			List<InfopubOdeviceStatus> infopubOdeviceStatus = infopubOdeviceStatusDao
					.getByExcepyion(infopubDeviceInfo.getStDeviceMac());
			// 查询异常信息，如果设备含有异常的外设则设备异常
			if (infopubOdeviceStatus != null) {
				Excount++;
			}
		}
		// 以接入事项数 查询办件表，查不所有事项去重
		/*
		 * List<SelmQueryHis> selmQueryHis = selmQueryHisDao.selmQuertNum(); int
		 * selmItemSum = selmQueryHis.size();//事项总数
		 */
		// 接入事项 查询事项表
		/*
		 * int selmItemSum=0; if(!stPermission.equals("project_admin")){ for
		 * (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceList) { String
		 * deviceId = infopubDeviceInfo.getStDeviceId(); conds =
		 * Conditions.newAndConditions(); conds.add(new
		 * Condition("ST_DEVICE_ID", Condition.OT_EQUAL, deviceId));
		 * List<SelmDeviceItem> query = selmDeviceItemDao.query(conds, null);
		 * selmItemSum = selmItemSum+query.size(); } }else{
		 */
		List<SelmItem> selmItemList = selmItemDao.query(null, null);
		int selmItemSum = selmItemList.size();// 事项总数*/
		// }
		// 判断时间日期
		Conditions condQuery = Conditions.newAndConditions();
		Conditions condQueryDay = Conditions.newAndConditions();
		/*
		 * Conditions condMac = Conditions.newOrConditions();
		 * if(!stPermission.equals("project_admin")){ for (InfopubDeviceInfo
		 * infopubDeviceInfo : infopubDeviceList) { condMac.add(new
		 * Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
		 * infopubDeviceInfo.getStDeviceMac())); } condQuery.add(condMac);
		 * condQueryDay.add(condMac); }
		 * System.out.println(condQuery+"---------");
		 */
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String endDate = format.format(date);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String startDate = format.format(c.getTime());
		condQuery.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
				Timestamp.valueOf(startDate + " 00:00:00")));
		condQuery.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
				Timestamp.valueOf(endDate + " 23:59:59")));

		/*
		 * List<SelmQueryHis> selmQueryHisList =
		 * selmQueryHisDao.query(condQuery, null); int SelmQuerySum =
		 * selmQueryHisList.size(); //本月办件量
		 */
		int SelmQuerySum = selmQueryHisDao.queryleadCount(condQuery, null);

		condQueryDay.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
				endDate + " 00:00"));
		condQueryDay.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
				endDate + " 23:59"));
		int SelmQueryDaySum = selmQueryHisDao.queryleadCount(condQueryDay, null);
		/*
		 * List<SelmQueryHis> selmQueryHisListDay =
		 * selmQueryHisDao.query(condQueryDay, null); int SelmQueryDaySum =
		 * selmQueryHisListDay.size(); //当日办件量
		 */JSONObject returnObj = new JSONObject();
		returnObj.put("deviceSum", sum);
		returnObj.put("online", online);
		returnObj.put("noOnLine", noOnLine);
		returnObj.put("socialSum", socialSum);
		returnObj.put("govSum", govSum);
		returnObj.put("Excount", Excount);
		returnObj.put("selmItemSum", selmItemSum);
		returnObj.put("SelmQuerySum", SelmQuerySum);
		returnObj.put("SelmQueryDaySum", SelmQueryDaySum);

		return returnObj;
	}

	@Override
	public JSONObject loadingXML(HttpReqRes httpReqRes) {
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String clContent = httpReqRes.getParameter("clContent");
		String version = httpReqRes.getParameter("version");
		System.out.println(version);
		InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDao
				.getByMacId(stDeviceMac);
		if(null != infopubDeviceInfo){
			String uuid = UUID.randomUUID().toString();
			InfopubAttachment infopubAttachment = new InfopubAttachment();
			if (null == infopubDeviceInfo.getStConfigId()
					|| infopubDeviceInfo.getStConfigId().equals("")) {
				infopubDeviceInfo.setStConfigId(uuid);
				infopubDeviceInfoDao.update(infopubDeviceInfo);
				infopubAttachment.setStAttachId(uuid);
				infopubAttachment.setClContent(clContent);
				infopubAttachment.setBlContent(new byte[] {});
				infopubAttachment.setStFilename(version);
				infopubAttachmentDaoExt.add(infopubAttachment);
			} else {
				InfopubAttachment infopubAttachment2 = infopubAttachmentDaoExt
						.get(infopubDeviceInfo.getStConfigId());
				if (infopubAttachment2 == null) {
					infopubAttachment.setStAttachId(infopubDeviceInfo
							.getStConfigId());
					infopubAttachment.setClContent(clContent);
					infopubAttachment.setStFilename(version);
					infopubAttachment.setBlContent(new byte[] {});
					infopubAttachmentDaoExt.add(infopubAttachment);
				} else {
					infopubAttachment.setStAttachId(infopubDeviceInfo
							.getStConfigId());
					infopubAttachment.setClContent(clContent);
					infopubAttachment.setStFilename(version);
					infopubAttachment.setBlContent(new byte[] {});
					infopubAttachmentDaoExt.update(infopubAttachment);
				}

			}
			String deviceId = infopubDeviceInfo.getStDeviceId();
			String jsonString = infopubAttachment.getClContent();
			JSONObject obj = new JSONObject();
			obj.put("deviceId", deviceId);
			obj.put("clcontent", jsonString);

			if (jsonString != null && deviceId != null) {
				return obj;
			} else {
				return null;
			}
		}
		return null;
		
	}

	@Override
	public InfopubAttachment getById(String stAttachId) {
		InfopubAttachment infopubAttachment = infopubAttachmentDaoExt
				.get(stAttachId);
		return infopubAttachment;
	}

	@Override
	public InfopubAttachment uploadDebug(String stAttachId, String fileName,
			String fileType, byte[] file, int len, String applytitle,
			String applycontent) {
		InfopubAttachment infopubAttachment = new InfopubAttachment();
		InfopubAttachment infopubAttactId = infopubAttachmentDaoExt
				.get(stAttachId);
		if (infopubAttactId == null) {
			infopubAttachment.setStAttachId(stAttachId);
			infopubAttachment.setStFilename(fileName);
			infopubAttachment.setStFileSize(String.valueOf(len));
			infopubAttachment.setBlContent(file);
			infopubAttachment.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			infopubAttachmentDaoExt.add(infopubAttachment);
		} else {
			infopubAttachment.setStAttachId(infopubAttactId.getStAttachId());
			infopubAttachment.setBlContent(file);
			infopubAttachment.setStFilename(fileName);
			infopubAttachment.setStFileSize(String.valueOf(len));
			infopubAttachment.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			infopubAttachmentDaoExt.update(infopubAttachment);
		}
		return infopubAttachment;
	}

	@Override
	public void downLoad(HttpReqRes httpReqRes) {
		String stAttachId = httpReqRes.getParameter("stAttachId");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		byte[] attachFildById = infopubAttachmentDaoExt
				.getAttachFildById(stAttachId);
		InfopubAttachment infopubAttachment = new InfopubAttachment();
		Map<String, Object> map = new HashMap<String, Object>();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, stAttachId));
		// map.put("ST_ATTACH_ID",stAttachId);
		map.put("DT_UPDATE", new Timestamp(System.currentTimeMillis()));
		/*
		 * infopubAttachment.setStAttachId(stAttachId);
		 * infopubAttachment.setBlContent(new byte[]{});
		 * 
		 * infopubAttachment.setDtUpdate(new Timestamp(System
		 * .currentTimeMillis()));
		 */
		infopubAttachmentDaoExt.update(map, conds);
		// SelmAttach selmAttach = selmAttachDao.get(stAttachId);
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		String fileName = "Debug";
		try {
			try {
				java.io.OutputStream o = httpReqRes.getResponse()
						.getOutputStream();
				// 设置相应类型
				httpReqRes.getResponse().setContentType(
						"application/vnd.ms-excel");
				// 设置相应头，attachment:以附件的形式进行下载
				httpReqRes.getResponse()
						.setHeader(
								"content-disposition",
								"attachment;filename="
										+ java.net.URLEncoder.encode(fileName,
												"UTF-8"));
				o.write(attachFildById);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取设备信息列表
	 */
	@Override
	public JSONObject itemDeviceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		Conditions condOr = Conditions.newOrConditions();
		String deviceName = httpReqRes.getParameter("deviceName");
		String codeName = httpReqRes.getParameter("codeName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (deviceName != null && !StringUtils.trim(deviceName).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ADDRESS", Condition.OT_LIKE,
					deviceName));
		}
		if (codeName != null && !StringUtils.trim(codeName).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_CODE", Condition.OT_LIKE,
					codeName));
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}

		String suffix = "ORDER BY DT_CREATE ";
		if (orderName != null) {
			if ("stDeviceName".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_NAME " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_UPDATE " + orderType.toUpperCase();
			} else if ("stDeviceMac".equals(orderName)) {
				suffix = "ORDER BY ST_DEVICE_MAC " + orderType.toUpperCase();
			}
		}
		List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
				.queryItemNoDevice();

		for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
			String stDeviceMac = infopubDeviceInfo.getStDeviceMac();
			condOr.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL,
					stDeviceMac));
		}
		condOr.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL, "++++++"));
		conds.add(condOr);
		List<InfopubDeviceInfo> info = infopubDeviceInfoDao.query(conds,
				suffix, pageSize, currentPage);
		for (InfopubDeviceInfo infopubDeviceInfo : info) {
			infopubDeviceInfo.setStDesc("未绑定事项");
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(info, InfopubDeviceInfo.class)).getString(
					"total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", info.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", info);
		return returnObj;

	}

	@Override
	public JSONObject getCertKey(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		String deviceMac = httpReqRes.getParameter("deviceMac");
		if (deviceMac != null && !StringUtils.trim(deviceMac).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL,
					deviceMac));
			InfopubDeviceInfo infoDeviceList = infopubDeviceInfoDao
					.getMac(deviceMac);
			JSONObject json = new JSONObject();
			JSONObject returnObj = new JSONObject();
			if (infoDeviceList != null) {
				if (infoDeviceList.getStCertKey() == null
						|| infoDeviceList.getStCertKey().equals("")) {
					json.put("cretKey", "");
					json.put("msg", "该设备未上传证书唯一标识");
					json.put("SUCCESS", false);
					returnObj.put("data", json);
					return returnObj;
				} else {
					json.put("cretKey", infoDeviceList.getStCertKey());
					json.put("SUCCESS", true);
					returnObj.put("data", json);
					return returnObj;
				}
			} else {
				json.put("cretKey", "");
				json.put("SUCCESS", false);
				json.put("msg", "该设备没有录入系统");
				returnObj.put("data", json);
				return returnObj;
			}
		} else {
			JSONObject json = new JSONObject();
			JSONObject returnObj = new JSONObject();
			json.put("cretKey", "");
			json.put("SUCCESS", false);
			json.put("msg", "设备唯一标识不能为空");
			returnObj.put("data", json);
			return returnObj;
		}
	}

	
	//查询赋能设备和未赋能设备
	@Override
	public JSONObject itemLinkDevice(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		String para = httpReqRes.getParameter("para");		
		String deviecMac = StringUtils.trim(httpReqRes.getParameter("deviecMac"));		
		String deviceaddress = StringUtils.trim(httpReqRes.getParameter("deviceAddress"));	
		String deviceArea = StringUtils.trim(httpReqRes.getParameter("deviceArea"));	
		String user = StringUtils.trim(httpReqRes.getParameter("user"));	
		String userAreaId = StringUtils.trim(httpReqRes.getParameter("userAreaId"));	
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
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
		
		Conditions co1 = Conditions.newAndConditions();
		co1.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_EQUAL, user));
		SmsRole sr = smsRoleDao.queryRoleByUser(co1, null);
		
		/*Set<String> infoDeviceInfoIdSet = new HashSet<String>();
		infoDeviceInfoIdSet = infopubDeviceInfoDao.checkItemLinkDevice();*/
		List<InfopubDeviceInfo> infopubDeviceInfoList = new ArrayList<InfopubDeviceInfo>();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("idi.ST_DEVICE_MAC", Condition.OT_LIKE, deviecMac));
		conds.add(new Condition("idi.ST_DEVICE_ADDRESS", Condition.OT_LIKE, deviceArea+deviceaddress));
		if("area" == sr.getStRoleCode() || sr.getStRoleCode().equals("area")){
			if(userAreaId != null && !StringUtils.trim(userAreaId).isEmpty()){
				conds.add(new Condition("idi.ST_AREA_ID", Condition.OT_EQUAL, userAreaId));
				
			}
		}else if("organ" == sr.getStRoleCode() || sr.getStRoleCode().equals("organ")){
			SmsUser su = smsUserDao.getUserName(user);
			if(userAreaId != null && !StringUtils.trim(userAreaId).isEmpty()){
				conds.add(new Condition("idi.ST_ORGAN_ID", Condition.OT_EQUAL, su.getStOrganId()));
				
			}
		}
		if(para != null && !StringUtils.trim(para).isEmpty()){
			//已赋能设备
			if(para == "yes" || para.equals("yes")){
				conds.add(new  Condition("sdi.ST_DEVICE_ID", Condition.OT_UNEQUAL,null));
				infopubDeviceInfoList = infopubDeviceInfoDao.queryDWithIOrNoI(conds, null, pageSize, currentPage);
			}else if(para == "no" || para.equals("no")){//未赋能设备
				conds.add(new  Condition("sdi.ST_DEVICE_ID", Condition.OT_EQUAL, null));
				infopubDeviceInfoList = infopubDeviceInfoDao.queryDWithIOrNoI(conds, null, pageSize, currentPage);
			}
		}
		
		for(InfopubDeviceInfo temp : infopubDeviceInfoList){
			InfopubDeviceType type = infopubDeviceTypeDao.get(temp.getStTypeId());
			String deviceType =type.getStTypeName();
			//暂代设备类型
			temp.setStDesc(deviceType);
			
		}
		
		//总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceInfoList);
		return returnObj;
		
		
	}

	@Override
	public JSONObject odeviceInfoList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_NOT_LIKE, null));
		String odeviceName = httpReqRes.getParameter("odeviceName");
		String username = httpReqRes.getParameter("userName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (null != odeviceName && !StringUtils.trim(odeviceName).isEmpty()) {
			conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE,odeviceName));
		}
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		String suffix = "ORDER BY NM_ORDER";
		if (orderName != null) {
			if ("stTypeName".equals(orderName)) {
				suffix = "ORDER BY ST_TYPE_NAME " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceTypeList,
							InfopubDeviceType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceTypeList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceTypeList);
		return returnObj;
	}

	@Override
	public void odeviceInfoRemove(HttpReqRes httpReqRes) {
		String[] typeIdList = httpReqRes.getRequest().getParameterValues("stTypeId[]");
		if (typeIdList == null) {
			String stTypeId = httpReqRes.getRequest().getParameter("stTypeId");
			if (stTypeId != null && !StringUtils.trimToEmpty(stTypeId).isEmpty()) {
				infopubDeviceTypeDao.delete(stTypeId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		infopubDeviceTypeDao.delete(typeIdList);
	}

	@Override
	public InfopubDeviceType odeviceInfoSaveOrUpate(HttpReqRes httpReqRes) {
		String stTypeId = httpReqRes.getParameter(InfopubDeviceType.ST_TYPE_ID);
		InfopubDeviceType oldInfopubDeviceType = null;
		if (stTypeId != null && !StringUtils.trimToEmpty(stTypeId).isEmpty()) {
			oldInfopubDeviceType = infopubDeviceTypeDao.get(stTypeId);
		}
	
		
		if (oldInfopubDeviceType != null) {
			httpReqRes.toBean(oldInfopubDeviceType);
			infopubDeviceTypeDao.update(oldInfopubDeviceType);
			return oldInfopubDeviceType;
		} else {
			String uuid = UUID.randomUUID().toString();
			InfopubAttachment infopubAttachment = new InfopubAttachment();
			InfopubDeviceType newInfopubDeviceType = new InfopubDeviceType();
			httpReqRes.toBean(newInfopubDeviceType);
			newInfopubDeviceType.setStTypeId(uuid);
			newInfopubDeviceType.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubDeviceType.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubDeviceType.setStParentTypeId("LX003");
			infopubDeviceTypeDao.add(newInfopubDeviceType);
			
			return newInfopubDeviceType;
		}
	}

	@Override
	public JSONObject deviceWarnInfoList(HttpReqRes httpReqRes) {
		String username = httpReqRes.getParameter("userName");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stDeviceName = httpReqRes.getParameter("stDeviceName");
		String nmException = httpReqRes.getParameter("nmException");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
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
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		String suffix = "ORDER BY DT_CREATE DESC";
		
		
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
	
		
		Conditions conds = Conditions.newAndConditions();
		List<String> stDeviceMacList = null;
		if (nmException != null && !StringUtils.trim(nmException).isEmpty()) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Conditions conds1 = Conditions.newAndConditions();
			conds1.add(new Condition("NM_EXCEPTION", Condition.OT_EQUAL, nmException));
			
			if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
				conds1.add(new Condition("DT_UPDATE", Condition.OT_GREATER_EQUAL,Timestamp.valueOf(startDate + " 00:00:00")));
			}else{
				conds1.add(new Condition("DT_UPDATE", Condition.OT_GREATER_EQUAL, Timestamp.valueOf("2021-01-01 00:00:00")));
			}
			if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
				conds1.add(new Condition("DT_UPDATE", Condition.OT_LESS_EQUAL,Timestamp.valueOf(endDate + " 23:59:59")));
			}else{
				conds1.add(new Condition("DT_UPDATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(date+" 23:59:59")));
			}
			stDeviceMacList = infopubOdeviceStatusDao.getDeviceByExcepyion(conds1);
		}
		conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_IN,stDeviceMacList));
		
		
		if (stDeviceName != null && !StringUtils.trim(stDeviceName).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_NAME", Condition.OT_LIKE,stDeviceName));
		}
		
		/*if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					startDate + " 00:00"));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					endDate + " 23:59"));
		}*/
		List<InfopubDeviceInfo> infopubDeviceInfoList = new ArrayList<InfopubDeviceInfo>();
		infopubDeviceInfoList = infopubDeviceInfoDao.query(conds, suffix,pageSize, currentPage);

		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceInfoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubDeviceInfoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceInfoList);
		return returnObj;
	}

	@Override
	public JSONObject deviceWarnInfoCount(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("NM_EXCEPTION", Condition.OT_EQUAL, 1));
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdf.format(date);
		conds.add(new Condition("DT_UPDATE",Condition.OT_GREATER_EQUAL, sDate+" 00:00:00"));
		conds.add(new Condition("DT_UPDATE",Condition.OT_LESS_EQUAL, sDate+" 23:59:59"));
		List<String> stDeviceMacList = null;
		stDeviceMacList = infopubOdeviceStatusDao.getDeviceByExcepyion(conds);
		JSONObject returnObj = new JSONObject();
		returnObj.put("count", stDeviceMacList.size());
		return returnObj;
	}

	@Override
	public JSONObject deviceInfoTypeInit(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL, null));
		String username = httpReqRes.getParameter("userName");
		String type = httpReqRes.getParameter("type");
		if(null == username && StringUtils.isEmpty(username)){
			username = "admin";
		}
		Conditions usercon = Conditions.newAndConditions();
		usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
		SmsUser smsUser = smsUserDao.query(usercon, null).get(0);
		
		Conditions ruconds = Conditions.newAndConditions();
		ruconds.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_EQUAL, username));
		SmsRole smsRole = smsRoleDao.queryRoleByUser(ruconds, null);
		System.out.println("角色代码："+smsRole.getStRoleCode());
		
		if("changshang".equals(smsRole.getStRoleCode())){
			List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
			String stUserName = smsUser.getStUserName();
			
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(typeInfoName.indexOf("（")+1, typeInfoName.indexOf("）"));
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
			
		}else if("bank".equals(smsRole.getStRoleCode())){
			List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
			String stUserName = smsUser.getStUserName();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}else if("area".equals(smsRole.getStRoleCode())){
			conds.add(new Condition("NM_DTYPE", Condition.OT_EQUAL,0));
		}
		
		if(null != type && !StringUtils.isEmpty(type)){
			//政务
			if(type.equals("ZW")){
				conds.add(new Condition("NM_DTYPE", Condition.OT_EQUAL,0));
			}
			//银行
			if(type.equals("YH")){
				conds.add(new Condition("NM_DTYPE", Condition.OT_EQUAL,1));
			}
		}
		
		String suffix = "ORDER BY NM_ORDER";
		List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao.query(conds, suffix);
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubDeviceTypeList,
							InfopubDeviceType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", infopubDeviceTypeList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubDeviceTypeList);
		return returnObj;
	}

	@Override
	public JSONObject wGetDeviceByTime(HttpReqRes httpReqRes) {
		String time = httpReqRes.getParameter("dtUpdate");
		String type = httpReqRes.getParameter("type");
		JSONObject returnObj = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		List<InfopubDeviceInfoHis> idihList = new ArrayList<InfopubDeviceInfoHis>();
		List<InfopubDeviceInfo> idiList = new ArrayList<InfopubDeviceInfo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String function = "“一网通办”政务服务自助终端集咨询、申报、办理、查询、打印等业务功能于一身，同时覆盖公安、医保、人社、民政、档案、" +
				"住建等各条线部门的业务服务，实现多条线、多业务的政务服务事项集成办理。";
		if(null != time && !StringUtils.isEmpty(time)){
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("DT_UPDATE",Condition.OT_GREATER_EQUAL,time));
			idihList = infopubDeviceInfoHisDao.query(conds, null);
			
			if(idihList.size() > 0){
				for(InfopubDeviceInfoHis emp : idihList){
					conds = Conditions.newAndConditions();
					conds.add(new Condition("idi.ST_DEVICE_MAC",Condition.OT_EQUAL,emp.getStDeviceMac()));
					idiList = infopubDeviceInfoHisDao.wGetDevice(conds,null);
					if(idiList.size() > 0){
						InfopubDeviceInfo info = idiList.get(0);
						obj = new JSONObject();
						obj.put("stDeviceId", info.getStDeviceId());
						obj.put("stDeviceName", info.getStDeviceName());
						obj.put("stDeviceMac", info.getStDeviceMac());
						obj.put("stDeviceIp", info.getStDeviceIp());
						obj.put("stLable", info.getStAreaId());
						obj.put("stCity", info.getStAddressId());
						obj.put("stDistrict", info.getStDeviceAddress());
						obj.put("stStreet", info.getStDesc());
						obj.put("stAddress", info.getStConfigId());
						if(null != emp.getDtUpdate()){
							obj.put("dtUpdate", sdf.format(new Date(emp.getDtUpdate().getTime())));
						}
						String dType = info.getStTypeId();
						if(dType.contains("银行")){
							obj.put("stGTypeName", "银行");
							obj.put("stTypeName",dType.substring(0, dType.indexOf("行")+1));
						}else if(dType.contains("政务服务")){
							obj.put("stGTypeName",  "政务");
							obj.put("stTypeName", dType.substring(dType.indexOf("（")+1, dType.indexOf("）")));
						}
						obj.put("stFunction", function);
						obj.put("stState", emp.getStState());
						arr.add(obj);
					}
				}
			}		
		}else{
			if(null != type && !StringUtils.isEmpty(type)){
				Conditions conds = Conditions.newAndConditions();
				if("all".equals(type)){//查全部设备
					conds.add(new Condition("iaa.NM_ORDER",Condition.OT_LESS,17));
				}else{//传单个MAC，查单个设备
					conds.add(new Condition("idi.ST_DEVICE_MAC",Condition.OT_EQUAL,type));
				}
				idiList = infopubDeviceInfoHisDao.wGetDevice(conds,null);
				if(idiList.size() > 0){
					for(InfopubDeviceInfo info : idiList){
						obj = new JSONObject();
						obj.put("stDeviceId", info.getStDeviceId());
						obj.put("stDeviceName", info.getStDeviceName());
						obj.put("stDeviceMac", info.getStDeviceMac());
						obj.put("stDeviceIp", info.getStDeviceIp());
						obj.put("stLable", info.getStAreaId());
						obj.put("stCity", info.getStAddressId());
						obj.put("stDistrict", info.getStDeviceAddress());
						obj.put("stStreet", info.getStDesc());
						obj.put("stAddress", info.getStConfigId());
						obj.put("dtUpdate", info.getDtUpdate());
						String dType = info.getStTypeId();
						if(dType.contains("银行")){
							obj.put("stGTypeName", "银行");
							obj.put("stTypeName",dType.substring(0, dType.indexOf("行")+1));
						}else if(dType.contains("政务服务")){
							obj.put("stGTypeName",  "政务");
							obj.put("stTypeName", dType.substring(dType.indexOf("（")+1, dType.indexOf("）")));
						}
						obj.put("stFunction", function);
						obj.put("stState","add");
						arr.add(obj);
					}
				}
				
			}
		}
		returnObj.put("sum", arr.size());
		returnObj.put("data", arr);
		return returnObj;
	}

	@Override
	public JSONObject wGetItemByDevice(HttpReqRes httpReqRes) {
		String deviceMac = httpReqRes.getParameter("stDeviceMacList");
		List<String> deviceList = JSON.parseArray(deviceMac, String.class);
		JSONObject returnObj = new JSONObject();
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		Conditions conds = Conditions.newAndConditions();
		if(null != deviceList && deviceList.size()>0){
			for(String emp : deviceList){
				conds = Conditions.newAndConditions();
				conds.add(new Condition("idi.ST_DEVICE_MAC",Condition.OT_EQUAL,emp));
				List<String> itemCodeList = selmItemDao.getByDevice(conds,null);
				InfopubDeviceInfo info = infopubDeviceInfoDao.getMac(emp);
				obj = new JSONObject();
				obj.put("stDeviceId", info.getStDeviceId());
				obj.put("stDeviceMac", emp);
				obj.put("stItemCode", itemCodeList);
				arr.add(obj);
			}
		}
		returnObj.put("data",arr);
		return returnObj;
	}

	@Override
	public InfopubDeviceInfoHis saveOrUpdateDeviceInfoHis(InfopubDeviceInfo info,String state) {
		InfopubDeviceInfoHis idih = new InfopubDeviceInfoHis();
		if(null != info){
			idih.setStDeviceId(UUID.randomUUID().toString());
			idih.setStDeviceName(info.getStDeviceName());
			idih.setStDeviceCode(info.getStDeviceCode());
			idih.setStDeviceIp(info.getStDeviceIp());
			idih.setStDeviceMac(info.getStDeviceMac());
			idih.setStDeviceAddress(info.getStDeviceAddress());
			idih.setStTypeId(info.getStTypeId());
			idih.setNmIsHost(info.getNmIsHost());
			idih.setNmOrder(info.getNmOrder());
			idih.setNmInterval(info.getNmInterval());
			idih.setNmRecover(info.getNmRecover());
			idih.setNmDownTry(info.getNmDownTry());
			idih.setNmNotification(info.getNmNotification());
			idih.setNmLat(info.getNmLat());
			idih.setNmLng(info.getNmLng());
			idih.setNmOnline(info.getNmOnline());
			idih.setStChannel(info.getStChannel());
			idih.setStConfigId(info.getStConfigId());
			idih.setNmSdtype(info.getNmSdtype());
			idih.setStUserId(info.getStUserId());
			idih.setStAreaId(info.getStAreaId());
			idih.setStAddressId(info.getStAddressId());
			idih.setStOrganId(info.getStOrganId());
			idih.setStCertKey(info.getStCertKey());
			idih.setDtCreate(info.getDtCreate());
			idih.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			idih.setStDesc(info.getStDesc());
			if("update".equals(state)){
				idih.setStState("update");
			}else if("add".equals(state)){
				idih.setStState("add");
			}else if("delete".equals(state)){
				idih.setStState("delete");
			}
			infopubDeviceInfoHisDao.add(idih);
		}
		return null;
	}

	@Override
	public JSONObject isonlineofmap(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		JSONObject obj = new JSONObject();
		InfopubDeviceInfo info = null;
		BigDecimal nmOnline = new BigDecimal(0);
		if(null != stDeviceId){
			info = infopubDeviceInfoDao.get(stDeviceId);
			if(null != info){
				nmOnline = info.getNmOnline(); 
				obj.put("code", "success");
				obj.put("stDeviceId", stDeviceId);
				obj.put("nmOnline", nmOnline);
			}else{
				obj.put("code", "error");
				obj.put("text", "找不到设备");
			}
		}else{
			obj.put("code", "error");
			obj.put("text", "找不到设备ID");
		}
		return obj;
	}

	@Override
	public JSONObject batchisonlineofmap(HttpReqRes httpReqRes) {
		JSONObject returnObj = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		List<InfopubDeviceInfo> infoList = new ArrayList<InfopubDeviceInfo>();
		BigDecimal nmOnline = new BigDecimal(0);
		infoList = infopubDeviceInfoDao.query(null, null);
		for(InfopubDeviceInfo emp : infoList){
			obj = new JSONObject();
			obj.put("stDeviceId", emp.getStDeviceId());
			obj.put("nmOnline", emp.getNmOnline());
			arr.add(obj);
		}
		
		returnObj.put("code","success");
		returnObj.put("sum",arr.size());
		returnObj.put("deviecList", arr);
		return returnObj;
	}

	@Override
	public JSONObject mGetDevInfo(HttpReqRes httpReqRes) {
		String address = httpReqRes.getParameter("address");//地址，可填区、街道、详细地址
		String item = httpReqRes.getParameter("item");//事项名称
		String organ = httpReqRes.getParameter("organ");//部门名称
		Conditions conds = Conditions.newAndConditions();
		if(null != address && StringUtils.isNotEmpty(address)){
			conds.add(new Condition("idi.ST_DEVICE_ADDRESS",Condition.OT_LIKE,address));
		}
		if(null != item && StringUtils.isNotEmpty(item)){
			conds.add(new Condition("si.ST_MAIN_NAME",Condition.OT_LIKE,item));
		}
		if(null != organ && StringUtils.isNotEmpty(organ)){
			SmsOrgan so = smsOrganDao.getByName(organ);
			if(null != so){
				String organId = so.getStOrganId();
				conds.add(new Condition("si.ST_ORGAN_ID",Condition.OT_EQUAL,organId));
			}
		}
		List<InfopubDeviceInfo> infoList = infopubDeviceInfoDao.mGetDeviceInfo(conds,null);
		InfopubDeviceInfo info = new InfopubDeviceInfo();
		JSONObject returnObj = new JSONObject();
		JSONObject obj = new JSONObject();
		if(infoList.size() > 0){
			info = infoList.get(0);
			InfopubDeviceType type = infopubDeviceTypeDao.get(info.getStTypeId());
			if(type.getNmDtype().equals(new BigDecimal(0))){
				obj.put("stDeviceName", "“一网通办”政务服务自助终端");
			}else if((type.getNmDtype().equals(new BigDecimal(1)))){
				obj.put("stDeviceName", info.getStDeviceName());
			}
			obj.put("stDeviceType", type.getStTypeName());
			obj.put("stDeviecId", info.getStDeviceId());
			obj.put("stDeviceAddress", info.getStDeviceAddress());
			//介绍
			String introduce = "“一网通办”政务服务自助终端集咨询、申报、办理、查询、打印等业务功能于一身，同时覆盖公安、医保、人社、民政、档案、" +
					"住建等各条线部门的业务服务，实现多条线、多业务的政务服务事项集成办理。";
			obj.put("stDeviceInfo", introduce);
			//图片
			String stTypeId = type.getStTypeId();
			InfopubAttachment infopubAttachment = infopubAttachmentDaoExt.getLinkId(stTypeId);
			if (infopubAttachment != null) {
				Conditions condImage = Conditions.newAndConditions();
				condImage.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL,stTypeId));
				byte[] blob = BlobHelper.getBlob("INFOPUB_ATTACHMENT","BL_CONTENT", condImage.toString(),
						condImage.getObjectArray());
				String base64Str = DatatypeConverter.printBase64Binary(blob);
				JSONObject base64 = new JSONObject();
				base64.put("base64", base64Str);
				obj.put("stIcon", base64);
			}
			int itemCount = selmDeviceItemDao.getCount(info.getStDeviceId()); //关联的事项数
			obj.put("itemCount",itemCount);
			returnObj.put("code", "success");
		}else{
			returnObj.put("code", "error");
			returnObj.put("text", "设备找不到");
		}
		
		returnObj.put("data", obj);
		returnObj.put("deviceCount", infoList.size());
		return returnObj;
	}

	@Override
	public JSONObject deviceAddressInit(HttpReqRes httpReqRes) {
		
		String deviceProvince = httpReqRes.getParameter("deviceProvince");
		String deviceCity = httpReqRes.getParameter("deviceCity");
		String deviceDistrict = httpReqRes.getParameter("deviceDistrict");
		
		JSONObject returnObj = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		//选省
		if(null == deviceDistrict && null == deviceCity && 
				(null == deviceProvince || StringUtils.isEmpty(deviceProvince))){//根据父ID为空选省
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,null));
			List<InfopubArea> proviceList = infopubAreaDao.query(conds, null);
			for(InfopubArea emp : proviceList){
				obj = new JSONObject();
				obj.put("area_id", emp.getStAreaId());
				obj.put("area_name", emp.getStAreaName());
				arr.add(obj);
			}
			obj = new JSONObject();
			obj.put("area_id", "");
			obj.put("area_name", "");
			arr.add(obj);
			returnObj.put("province_list", arr);
		}else if(null != deviceProvince || StringUtils.isNotEmpty(deviceProvince)){//根据省ID选市
			if("e66c5ae7-77cd-4de1-9d72-a4657a97079d".equals(deviceProvince)){//直辖市：上海(省市一体)
				obj = new JSONObject();
				obj.put("area_id", "e66c5ae7-77cd-4de1-9d72-a4657a97079d");
				obj.put("area_name", "上海市");
				arr.add(obj);
			}else{
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,deviceProvince));
				List<InfopubArea> cityList = infopubAreaDao.query(conds, null);
				for(InfopubArea emp : cityList){
					obj = new JSONObject();
					obj.put("area_id", emp.getStAreaId());
					obj.put("area_name", emp.getStAreaName());
					arr.add(obj);
				}
			}
			obj = new JSONObject();
			obj.put("area_id", "");
			obj.put("area_name", "");
			arr.add(obj);
			returnObj.put("city_list", arr);
			
		}else if(null != deviceCity || StringUtils.isNotEmpty(deviceCity)){ //根据市ID选区
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,deviceCity));
			List<InfopubArea> cityList = infopubAreaDao.query(conds, null);
			for(InfopubArea emp : cityList){
				obj = new JSONObject();
				obj.put("area_id", emp.getStAreaId());
				obj.put("area_name", emp.getStAreaName());
				arr.add(obj);
			}
			obj = new JSONObject();
			obj.put("area_id", "");
			obj.put("area_name", "");
			arr.add(obj);
			returnObj.put("district_list", arr);
		}else if(null != deviceDistrict || StringUtils.isNotEmpty(deviceDistrict)){ //根据区ID选街道
			
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,deviceDistrict));
			List<InfopubArea> streetList = infopubAreaDao.query(conds,"");
			for(InfopubArea emp : streetList){
				String street = emp.getStAreaName();
				if(StringUtils.isNotEmpty(street)){
					obj = new JSONObject();
					obj.put("area_id", street);
					obj.put("area_name", street);
					arr.add(obj);
				}
			}

//		}else if(null != deviceDistrict || StringUtils.isNotEmpty(deviceDistrict)){ //根据区ID选街道
//			Conditions conds = Conditions.newAndConditions();
//			conds.add(new Condition("ST_AREA_ID",Condition.OT_EQUAL,deviceDistrict));
//			InfopubArea info = infopubAreaDao.get(deviceDistrict);
//			if(null != info){
//				List<InfopubAddress> streetList = infopubAddressDao.selectStreetByDistrict(info.getStAreaName());
//				for(InfopubAddress emp : streetList){
//					String street = emp.getStStreet();
//					if(StringUtils.isNotEmpty(street)){
//						obj = new JSONObject();
//						obj.put("area_id", street);
//						obj.put("area_name", street);
//						arr.add(obj);
//					}
//				}
//			}
			
			obj = new JSONObject();
			obj.put("area_id", "");
			obj.put("area_name", "");
			arr.add(obj);
			returnObj.put("street_list", arr);
		}
		
		return returnObj;
	}

	@Override
	public JSONObject InitDeviceType(HttpReqRes httpReqRes) {
		
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL, null));
		String username = httpReqRes.getParameter("userName");
		if(null == username && StringUtils.isEmpty(username)){
			username = "admin";
		}
		Conditions usercon = Conditions.newAndConditions();
		usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, username));
		SmsUser smsUser = smsUserDao.query(usercon, null).get(0);
		
		Conditions ruconds = Conditions.newAndConditions();
		ruconds.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_EQUAL, username));
		SmsRole smsRole = smsRoleDao.queryRoleByUser(ruconds, null);
		System.out.println("角色代码："+smsRole.getStRoleCode());
		
		if("changshang".equals(smsRole.getStRoleCode())){
			List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
			String stUserName = smsUser.getStUserName();
			
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(0)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(typeInfoName.indexOf("（")+1, typeInfoName.indexOf("）"));
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
			
		}else if("bank".equals(smsRole.getStRoleCode())){
			List<InfopubDeviceType> infopubDeviceType0 = new ArrayList<InfopubDeviceType>();
			String stUserName = smsUser.getStUserName();
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				if(stUserName.contains(typeInfoName)){
					conds.add(new Condition("ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}else if("area".equals(smsRole.getStRoleCode())){
			conds.add(new Condition("NM_DTYPE", Condition.OT_EQUAL,0));
		}
		
		String suffix = "ORDER BY NM_ORDER";
		
		List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao.query(conds, null);
		
		JSONObject type = new JSONObject();
		JSONArray arr = new JSONArray();
		for(InfopubDeviceType emp : infopubDeviceTypeList){
			type = new JSONObject();
			type.put("stTypeId", emp.getStTypeId());
			type.put("stTypeName", emp.getStTypeName());
			arr.add(type);
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("data", arr);
		return returnObj;
		
	}

	@Override
	public JSONObject mGetDeviceByTime(HttpReqRes httpReqRes) {
		String dtBeginUpdate = httpReqRes.getParameter("dtBeginUpdate");
		String dtEndUpdate = httpReqRes.getParameter("dtEndUpdate");
		String type = httpReqRes.getParameter("type");
		
		Conditions conds = Conditions.newAndConditions();
		
		if(null != dtBeginUpdate || StringUtils.isNotEmpty(dtBeginUpdate)){
			conds.add(new Condition("idi.ST_UPDATE",Condition.OT_GREATER_EQUAL,Timestamp.valueOf(dtBeginUpdate + " 00:00:00")));
		}
		if(null != dtEndUpdate || StringUtils.isNotEmpty(dtEndUpdate)){
			conds.add(new Condition("idi.ST_UPDATE",Condition.OT_LESS_EQUAL,Timestamp.valueOf(dtEndUpdate + " 23:59:59")));
		}
		if(null != type || StringUtils.isNotEmpty(type)){
			if("1".equals(type)){
				type = "0";
			}
			if("2".equals(type)){
				type = "1";
			}
			conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,type));
		}
		
		conds.add(new Condition("iaa.NM_ORDER",Condition.OT_LESS_EQUAL,17));
		
		String function = "“一网通办”政务服务自助终端集咨询、申报、办理、查询、打印等业务功能于一身，同时覆盖公安、医保、人社、民政、档案、" +
				"住建等各条线部门的业务服务，实现多条线、多业务的政务服务事项集成办理。";
		
		List<InfopubDeviceInfo> list = infopubDeviceInfoDao.mGetDeviceByTime(conds, null);
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		try{
			for(InfopubDeviceInfo emp : list){
				JSONObject o = new JSONObject();
				o.put("stDeviceName",emp.getStDeviceName());
				o.put("stDeviceMac",emp.getStDeviceMac());
				o.put("stDeviceIp", emp.getStDeviceIp());
				o.put("stLable", emp.getStAddressId());
				o.put("stCity", emp.getStAreaId());
				o.put("stDistrict", emp.getStCertKey());
				o.put("stStreet", emp.getStChannel());
				o.put("stAddress", emp.getStDeviceAddress());
				o.put("stUpdate", emp.getStUpdate().toString());
				o.put("stGTypeName", emp.getNmSdtype().equals(new BigDecimal(0))? "政务" : emp.getNmSdtype().equals(new BigDecimal(1))? "银行" : "");
				o.put("stTypeName", emp.getStTypeId());
				o.put("stFunction", function);
				o.put("stState", emp.getStState().equals(new BigDecimal(0)) ? "delete" : emp.getStState().equals(new BigDecimal(1)) ? "add" :  emp.getStState().equals(new BigDecimal(2)) ? "update"  : "");
				arr.add(o);
			}
			obj.put("data", arr);
			obj.put("code",200);
			obj.put("content","成功");
			obj.put("isSuccess", true);
		}catch(Exception e){
			e.printStackTrace();
			obj.put("msg", "请联系相关人员");
			obj.put("code",500);
			obj.put("content","失败");
			obj.put("isSuccess", false);
		}
		
		return obj;
	}

	@Override
	public JSONObject mGetItemByTime(HttpReqRes httpReqRes) {
		String dtBeginUpdate = httpReqRes.getParameter("dtBeginUpdate");
		String dtEndUpdate = httpReqRes.getParameter("dtEndUpdate");
		String type = httpReqRes.getParameter("type");
		
		Conditions conds = Conditions.newAndConditions();
		
		if(null != dtBeginUpdate && StringUtils.isNotEmpty(dtBeginUpdate)){
			conds.add(new Condition("si.DT_UPDATE",Condition.OT_GREATER_EQUAL,Timestamp.valueOf(dtBeginUpdate + " 00:00:00")));
		}
		if(null != dtEndUpdate && StringUtils.isNotEmpty(dtEndUpdate)){
			conds.add(new Condition("si.DT_UPDATE",Condition.OT_LESS_EQUAL,Timestamp.valueOf(dtEndUpdate + " 23:59:59")));
		}
		if(null != type && StringUtils.isNotEmpty(type)){
			if("1".equals(type)){
				type = "上海";
				conds.add(new Condition("si.ST_EXT1",Condition.OT_EQUAL,"上海"));
			}else if("2".equals(type)){
				type = "通办";
				conds.add(new Condition("si.ST_EXT1",Condition.OT_EQUAL,"通办"));
			}else if("3".equals(type)){
				conds.add(new Condition("si.ST_EXT1",Condition.OT_UNEQUAL,"上海"));
				conds.add(new Condition("si.ST_EXT1",Condition.OT_UNEQUAL,"通办"));
			}
		}
		List<SelmItem> list = selmItemDao.queryItemWithDetail(conds, null);
		
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		try{
			if(null != list){
				for(SelmItem emp : list){
					JSONObject o = new JSONObject();
					String stItemType = emp.getStExt1();
					String stItemTNo = emp.getStItemNo();
					String stExt3 = emp.getStExt3();
					if(null != stItemType && StringUtils.isNotEmpty(stItemType) && "上海".equals(stItemType)){
						if(null != stExt3 && StringUtils.isNotEmpty(stExt3)){
							o.put("stItemCode",stExt3);
						}else{
							if(null != stItemTNo && StringUtils.isNotEmpty(stItemTNo)){
								o.put("stItemCode",stItemTNo);
							}else{
								o.put("stItemCode","");
								
							}
						}
					}else{
						if(null != stItemTNo && StringUtils.isNotEmpty(stItemTNo)){
							o.put("stItemCode",stItemTNo);
						}else{
							o.put("stItemCode","");
							
						}
				
					}
					o.put("stItemName",emp.getStMainName());
					o.put("stItemType", stItemType);
					o.put("stOrgan", emp.getStOrganId());
					o.put("stWorkType", emp.getStWorkType());
					o.put("stIntroduce", "");
					o.put("dtUpdate", emp.getDtUpdate().toString());
					o.put("stState", emp.getStState().equals(new BigDecimal(0)) ? "delete" : emp.getStState().equals(new BigDecimal(1)) ? "add" :  emp.getStState().equals(new BigDecimal(2)) ? "update"  : "");
					arr.add(o);
				}
				
			}
			obj.put("data", arr);
			obj.put("code",200);
			obj.put("content","成功");
			obj.put("isSuccess", true);
		}catch(Exception e){
			e.printStackTrace();
			obj.put("msg", "请联系相关人员");
			obj.put("code",500);
			obj.put("content","失败");
			obj.put("isSuccess", false);
		}
		
		return obj;
	}

	@Override
	public JSONObject mGetItemByDevice(HttpReqRes httpReqRes) {
		String deviceMac = httpReqRes.getParameter("stDeviceMacList");
		List<String> macList = JSON.parseArray(deviceMac, String.class);
		Map<String,String> stDeviecId = new HashMap<String,String>();
		ArrayList<String> stItemId = new ArrayList<String>();
		
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		
		if(null != macList){
			for(String emp : macList){
				InfopubDeviceInfo idi = infopubDeviceInfoDao.getMac(emp);
				if(idi != null){
					stDeviecId.put(emp,idi.getStDeviceId());
				}
				
			}
		}
		
		
		try{
			if(stDeviecId.entrySet().size()>0){
				for(Map.Entry<String, String> emp : stDeviecId.entrySet()){
					String mac = emp.getKey();
					String id = emp.getValue();
					List<String> sdi = selmDeviceItemDao.getItemByDevice(id);
					JSONArray code = new JSONArray();
					if(null!= sdi){
						for(String s : sdi){
							SelmItem si = selmItemDao.get(s);
							if(null != si){
								
								String stItemType = si.getStExt1();
								String stItemTNo = si.getStItemNo();
								String stExt3 = si.getStExt3();
								if(null != stItemType && StringUtils.isNotEmpty(stItemType) && "上海".equals(stItemType)){
									if(null != stExt3 && StringUtils.isNotEmpty(stExt3)){
										code.add(stExt3);
									}else{
										if(null != stItemTNo && StringUtils.isNotEmpty(stItemTNo)){
											code.add(stItemTNo);
										}
									}
								}else{
									if(null != stItemTNo && StringUtils.isNotEmpty(stItemTNo)){
										code.add(stItemTNo);
									}
							
								}
								
							}
							
						}
						JSONObject o = new JSONObject();
						o.put("stDeviceMac", mac);
						o.put("stItemCode", code);
						ja.add(o);
					}
				}
			}
			
			obj.put("data", ja);
			obj.put("code",200);
			obj.put("content","成功");
			obj.put("isSuccess", true);
			
		}catch(Exception e){
			e.printStackTrace();
			obj.put("msg", "请联系相关人员");
			obj.put("code",500);
			obj.put("content","失败");
			obj.put("isSuccess", false);
		}
		
			
		return obj;
	}

	

}
