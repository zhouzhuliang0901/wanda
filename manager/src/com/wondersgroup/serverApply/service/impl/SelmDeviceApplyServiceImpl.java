package com.wondersgroup.serverApply.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubCompany;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubCompanyDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;
import com.wondersgroup.serverApply.bean.SelmDeviceApply;
import com.wondersgroup.serverApply.dao.SelmDeviceAlinkDao;
import com.wondersgroup.serverApply.dao.SelmDeviceApplyDao;
import com.wondersgroup.serverApply.service.SelmDeviceApplyService;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;

/**
 * 设备接入申请业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmDeviceApplyServiceImpl implements SelmDeviceApplyService {
	
	@Autowired
	private SelmDeviceApplyDao selmDeviceApplyDao;
	
	@Autowired
	private SelmDeviceAlinkDao selmDeviceAlinkDao;

	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	
	@Autowired
	private InfopubCompanyDao infopubCompanyDao;
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	@Autowired
	private SmsUserDao smsUserDao;
	
	
	
	/**
	 * 保存或更新设备接入申请
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备接入申请实例
	 */
	@Override
	public SelmDeviceApply saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		
		String stDeviceApplyId = wrapper.getParameter("stDeviceApplyId");
		String type = wrapper.getParameter("type");
		String user = wrapper.getParameter("user");
		String permission = wrapper.getParameter("permission");
		String areaId = wrapper.getParameter("areaId");
		String stApplyOrganName = wrapper.getParameter(SelmDeviceApply.ST_APPLY_ORGAN_NAME);
		String stMainOrgName = wrapper.getParameter(SelmDeviceApply.ST_MAIN_ORG_NAME);
		String stApplyUserName = wrapper.getParameter(SelmDeviceApply.ST_APPLY_USER_NAME);
		String stApplyUserMobile = wrapper.getParameter(SelmDeviceApply.ST_APPLY_USER_MOBILE);
		String stApplyUserPhone = wrapper.getParameter(SelmDeviceApply.ST_APPLY_USER_PHONE);
		String stApplyUserEmail = wrapper.getParameter(SelmDeviceApply.ST_APPLY_USER_EMAIL);
		String dtPlanCreate = wrapper.getParameter(SelmDeviceApply.DT_PLAN_CREATE);
		System.out.println(dtPlanCreate);
		String stDesc = wrapper.getParameter(SelmDeviceApply.ST_DESC);
	
		
		//新申请信息
		SelmDeviceApply selmDeviceApply  = new SelmDeviceApply();
		selmDeviceApply.setStDeviceApplyId(stDeviceApplyId);
		selmDeviceApply.setStApplyOrganName(stApplyOrganName);
		selmDeviceApply.setStMainOrgName(stMainOrgName);
		selmDeviceApply.setStApplyUserName(stApplyUserName);
		selmDeviceApply.setStApplyUserMobile(stApplyUserMobile);
		selmDeviceApply.setStApplyUserPhone(stApplyUserPhone);
		selmDeviceApply.setStApplyUserEmail(stApplyUserEmail);
		selmDeviceApply.setNmStatus(new BigDecimal(0));
		selmDeviceApply.setStExt1(type);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp planTime = null;
	    try {
			long planCreate = sdf.parse(dtPlanCreate).getTime();
			planTime = new Timestamp(planCreate);
			System.out.println("计划接入时间"+planTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		selmDeviceApply.setDtPlanCreate(planTime);
		selmDeviceApply.setStDesc(stDesc);
		selmDeviceApply.setDtCreate(new Timestamp(System.currentTimeMillis()));
		if("area".equals(permission)){
			selmDeviceApply.setStDeviceDistrict(areaId);
		}
		
		SelmDeviceApply oldSelmDeviceApply = null;
		if (!StringUtils.trimToEmpty(stDeviceApplyId).isEmpty()) {
			oldSelmDeviceApply = selmDeviceApplyDao.get(stDeviceApplyId);
		}
		if (oldSelmDeviceApply == null) {// new
			selmDeviceApplyDao.add(selmDeviceApply);
			return selmDeviceApply;
		}else {// update
			selmDeviceApplyDao.update(selmDeviceApply);
			return oldSelmDeviceApply;
		}
	}

	/**
	 * 根据主键 {@link SelmDeviceApply#ST_DEVICE_APPLY_ID}获取设备接入申请
	 * 
	 * @param stDeviceApplyId
	 *            设备接入申请主键 {@link SelmDeviceApply#ST_DEVICE_APPLY_ID}
	 * @return 设备接入申请实例
	 */
	@Override
	public SelmDeviceApply get(String stDeviceApplyId) {
		if (StringUtils.trimToEmpty(stDeviceApplyId).isEmpty())
			throw new NullPointerException("Parameter stDeviceApplyId cannot be null.");
		return selmDeviceApplyDao.get(stDeviceApplyId);
	}

	/**
	 * 查询设备接入申请列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备接入申请列表
	 */
	@Override
	public JSONObject query(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String stApplyOragnName = httpReqRes.getParameter("stDeviceOragnName");
		String stDeviceNmstatus = httpReqRes.getParameter("stDeviceNmstatus");
		String startDate = httpReqRes.getParameter("startDate");
		String type = httpReqRes.getParameter("type");
		String user = httpReqRes.getParameter("user");
		String permission = httpReqRes.getParameter("permission");
		String areaId = httpReqRes.getParameter("areaId");
		Conditions conds = Conditions.newAndConditions();
		if (stApplyOragnName != null && !StringUtils.trim(stApplyOragnName).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ORGAN_NAME", Condition.OT_LIKE, stApplyOragnName));
		}
		if (stDeviceNmstatus != null && !StringUtils.trim(stDeviceNmstatus).isEmpty()) {
			conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, new BigDecimal(stDeviceNmstatus)));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,Timestamp.valueOf(startDate + " 00:00:00")));
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,Timestamp.valueOf(startDate + " 23:59:59")));
		}
		if (type != null && !StringUtils.trim(type).isEmpty()) {
			conds.add(new Condition("ST_EXT1", Condition.OT_EQUAL,type));
		}
		
		if("area".equals(permission)){
			conds.add(new Condition("ST_DEVICE_DESTRICT", Condition.OT_EQUAL,areaId));
		}

		String suffix = "ORDER BY DT_CREATE";
	
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
		
		List<SelmDeviceApply> selmDeviceApplyList = new ArrayList<SelmDeviceApply>();
		selmDeviceApplyList = selmDeviceApplyDao.query(conds, suffix, pageSize, currentPage);
		//System.out.println("---"+selmDeviceApplyList.size());
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmDeviceApplyList,
							SelmDeviceApply.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmDeviceApplyList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmDeviceApplyList);
		return returnObj;
	}
	
	
	@Override
	public JSONObject checkQuery(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String stApplyOragnName = httpReqRes.getParameter("stDeviceOragnName");
		String stDeviceNmstatus = httpReqRes.getParameter("stDeviceNmstatus");
		String startDate = httpReqRes.getParameter("startDate");
		Conditions conds = Conditions.newAndConditions();
		if (stApplyOragnName != null && !StringUtils.trim(stApplyOragnName).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ORGAN_NAME", Condition.OT_LIKE, stApplyOragnName));
		}
		if (stDeviceNmstatus != null && !StringUtils.trim(stDeviceNmstatus).isEmpty()) {
			conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, new BigDecimal(stDeviceNmstatus)));
		}else{
			conds.add(new Condition("NM_STATUS", Condition.OT_GREATER_EQUAL, new BigDecimal(1)));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,Timestamp.valueOf(startDate + " 00:00:00")));
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,Timestamp.valueOf(startDate + " 23:59:59")));
		}

		String suffix = "ORDER BY DT_CREATE";
	
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
		
		List<SelmDeviceApply> selmDeviceApplyList = new ArrayList<SelmDeviceApply>();
		selmDeviceApplyList = selmDeviceApplyDao.query(conds, suffix, pageSize, currentPage);
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmDeviceApplyList,
							SelmDeviceApply.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmDeviceApplyList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmDeviceApplyList);
		return returnObj;
	}

	/**
	 * 根据主键 {@link SelmDeviceApply#ST_DEVICE_APPLY_ID}删除设备接入申请
	 * 
	 * @param stDeviceApplyId
	 *            设备接入申请主键 {@link SelmDeviceApply#ST_DEVICE_APPLY_ID}
	 */
	@Override
	public void remove(String stDeviceApplyId) {
		if (StringUtils.trimToEmpty(stDeviceApplyId).isEmpty())
			throw new NullPointerException("Parameter stDeviceApplyId cannot be null.");
		selmDeviceApplyDao.delete(stDeviceApplyId);
	}
	
	@Override
	public void removeApply(HttpReqRes httpReqRes) {
		String stDeviceApplyId = httpReqRes.getRequest().getParameter("stApplyId");
		Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, stDeviceApplyId));
		selmDeviceApplyDao.delete(conds);
		selmDeviceAlinkDao.delete(conds);
	}
	
	@Override
	public void saveSubmit(HttpReqRes httpReqRes) {
		String stApplyId =httpReqRes.getRequest().getParameter("stApplyId");
		Conditions conds = Conditions.newAndConditions();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("NM_STATUS", "1");
		conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL,stApplyId));
		selmDeviceApplyDao.update(hashMap, conds);
		selmDeviceAlinkDao.update(hashMap, conds);
	}
	
	/**
	 * 根据主键 {@link SelmDeviceApply#ST_DEVICE_APPLY_ID}删除项目
	 * 
	 * @param stDeviceApplyId
	 *            项目主键 {@link SelmDeviceApply#ST_DEVICE_APPLY_ID}
	 */
	@Override
	public void batchDelete(String[] stDeviceApplyIds) {
		if (stDeviceApplyIds.length == 0)
			throw new NullPointerException("Parameter stTextId cannot be null.");
		// 批量删除
		for (String Id : stDeviceApplyIds) {
			// ID不为空的场合
			if (!StringUtils.trimToEmpty(Id).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, Id));
				selmDeviceApplyDao.delete(conds);
			}
		}
	}
	
	public void addOrUpdateDevice(String stDeviceApplyId,String stMachineId){
		InfopubDeviceInfo device = infopubDeviceInfoDao.get(stMachineId);
		if(null != device){
			SelmDeviceAlink sdk = selmDeviceAlinkDao.get(stDeviceApplyId, stMachineId);
			//设置区域id
			InfopubArea infopubArea = infopubAreaDao.getName(sdk.getStAreaId());
			device.setStAreaId(infopubArea != null ? infopubArea.getStAreaId() : "");
			//设置地址id
			InfopubAddress infopubAddress = infopubAddressDao.getSpecificName(sdk.getStDeviceAddress());
			device.setStAddressId(infopubAddress != null ? infopubAddress.getStAddressId() : "");
			//设置地址
			device.setStDeviceAddress("上海市"+sdk.getStAreaId()+sdk.getStAddressId()+sdk.getStDeviceAddress());
			//设置网点
			device.setStDeviceName(sdk.getStDeviceName());
			//设置ip
			device.setStDeviceIp(sdk.getStDeviceIp());
			//设置mac
			device.setStDeviceMac(sdk.getStDeviceMac());
			//设置类型
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.getByName(sdk.getStTypeId());
			device.setStTypeId(infopubDeviceType != null ? infopubDeviceType.getStTypeId() : "");
			
			//设置设备编码
			/*InfopubCompany infopubCompany = infopubCompanyDao.get(infopubDeviceType.getStCompanyId());
			List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(null,null);
			List arrayListCount = new ArrayList();
			for (InfopubDeviceInfo infopubDeviceInfo : list) {
				String aCode = infopubDeviceInfo.getStDeviceCode();
				String str = aCode.substring(aCode.length() - 4, aCode.length());
				arrayListCount.add(str);
			}
			String num = Collections.max(arrayListCount);
			int cout = Integer.decode(num) + 1;
			DecimalFormat mFormat = new DecimalFormat("0000");// 确定格式，把1转换为01
			String s = mFormat.format(cout);
			device.setStDeviceCode(infopubArea.getStAreaCode()+infopubCompany.getStCompanyCode()+infopubDeviceType.getStTypeCode()+s);*/
			//设置更新时间
			device.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			//更新
			infopubDeviceInfoDao.update(device);
		}else{
			InfopubDeviceInfo newInfo = new InfopubDeviceInfo();
			SelmDeviceAlink sdk = selmDeviceAlinkDao.get(stDeviceApplyId, stMachineId);
			newInfo.setStDeviceId(sdk.getStMachineId());
			//设置区域id
			InfopubArea infopubArea = infopubAreaDao.getName(sdk.getStAreaId());
			newInfo.setStAreaId(infopubArea != null ? infopubArea.getStAreaId() : "");
			//设置地址id
			InfopubAddress infopubAddress = infopubAddressDao.getSpecificName(sdk.getStDeviceAddress());
			newInfo.setStAddressId(infopubAddress != null ? infopubAddress.getStAddressId() : "");
			//设置地址
			newInfo.setStDeviceAddress("上海市"+sdk.getStAreaId()+sdk.getStAddressId()+sdk.getStDeviceAddress());
			//设置网点
			newInfo.setStDeviceName(sdk.getStDeviceName());
			//设置ip
			newInfo.setStDeviceIp(sdk.getStDeviceIp());
			//设置mac
			newInfo.setStDeviceMac(sdk.getStDeviceMac());
			//设置类型
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.getByName(sdk.getStTypeId());
			newInfo.setStTypeId(infopubDeviceType != null ? infopubDeviceType.getStTypeId() : "");
			//设置设备编码
			InfopubCompany infopubCompany = infopubCompanyDao.get(infopubDeviceType.getStCompanyId());
			List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(null,null);
			List arrayListCount = new ArrayList();
			for (InfopubDeviceInfo infopubDeviceInfo : list) {
				String aCode = infopubDeviceInfo.getStDeviceCode();
				String str = aCode.substring(aCode.length() - 4, aCode.length());
				arrayListCount.add(str);
			}
			String num = Collections.max(arrayListCount);
			int cout = Integer.decode(num) + 1;
			DecimalFormat mFormat = new DecimalFormat("0000");// 确定格式，把1转换为01
			String s = mFormat.format(cout);
			newInfo.setStDeviceCode(infopubArea.getStAreaCode()+infopubCompany.getStCompanyCode()+infopubDeviceType.getStTypeCode()+s);
			//设置更新时间
			newInfo.setDtCreate(new Timestamp(System.currentTimeMillis()));
			newInfo.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			//添加
			infopubDeviceInfoDao.add(newInfo);
		}
	}

	@Override
	public void devicePass(HttpReqRes httpReqRes) {
		String stDeviceApplyId =httpReqRes.getRequest().getParameter("stDeviceApplyId");
		String stMachineId =httpReqRes.getRequest().getParameter("stMachineId");
		//添加或更新设备信息
		addOrUpdateDevice(stDeviceApplyId,stMachineId);
		Conditions conds = Conditions.newAndConditions();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("NM_STATUS", new BigDecimal(2));
		hashMap.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
		String userId = (String)httpReqRes.getRequest().getSession().getAttribute("smsUserId");
		SmsUser user = smsUserDao.get(userId);
		if(null != user){
			hashMap.put("ST_AUDIT_USER_ID", user.getStUserId());
			hashMap.put("ST_AUDIT_USER_NAME", user.getStUserName());
		}
		if(stDeviceApplyId!=null && !StringUtils.trim(stDeviceApplyId).isEmpty()){
			conds.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		}
		if(stMachineId!=null && !StringUtils.trim(stMachineId).isEmpty()){
			conds.add(new Condition("ST_MACHINE_ID",Condition.OT_EQUAL,stMachineId));
		}
		selmDeviceAlinkDao.update(hashMap, conds);
		
		Conditions co1 = Conditions.newAndConditions();
		co1.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		co1.add(new Condition("NM_STATUS",Condition.OT_EQUAL,new BigDecimal(1)));
		List<SelmDeviceAlink> sda = new ArrayList<SelmDeviceAlink>();
		sda = selmDeviceAlinkDao.query(co1, null);
		HashMap<String, Object> map = new HashMap<String, Object>();
		Conditions co2 = Conditions.newAndConditions();
		co2.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		if(sda.size()>0){
			map.put("NM_STATUS", new BigDecimal(3));
			selmDeviceApplyDao.update(map, co2);
		}else{
			map.put("NM_STATUS", new BigDecimal(2));
			selmDeviceApplyDao.update(map, co2);
		}
		
	}

	@Override
	public void deviceNoPass(HttpReqRes httpReqRes) {
		String stDeviceApplyId =httpReqRes.getRequest().getParameter("stDeviceApplyId");
		String stMachineId =httpReqRes.getRequest().getParameter("stMachineId");
		Conditions conds = Conditions.newAndConditions();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("NM_STATUS", new BigDecimal(3));
		hashMap.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
		String userId = (String)httpReqRes.getRequest().getSession().getAttribute("smsUserId");
		SmsUser user = smsUserDao.get(userId);
		if(null != user){
			hashMap.put("ST_AUDIT_USER_ID", user.getStUserId());
			hashMap.put("ST_AUDIT_USER_NAME", user.getStUserName());
		}
		if(stDeviceApplyId!=null && !StringUtils.trim(stDeviceApplyId).isEmpty()){
			conds.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		}
		if(stMachineId!=null && !StringUtils.trim(stMachineId).isEmpty()){
			conds.add(new Condition("ST_MACHINE_ID",Condition.OT_EQUAL,stMachineId));
		}
		selmDeviceAlinkDao.update(hashMap, conds);
		
		Conditions co1 = Conditions.newAndConditions();
		co1.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		co1.add(new Condition("NM_STATUS",Condition.OT_EQUAL,new BigDecimal(1)));
		List<SelmDeviceAlink> sda = new ArrayList<SelmDeviceAlink>();
		sda = selmDeviceAlinkDao.query(co1, null);
		Conditions co2 = Conditions.newAndConditions();
		co2.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(sda.size()>0){
			map.put("NM_STATUS", new BigDecimal(3));
			selmDeviceApplyDao.update(map, co2);
		}else{
			map.put("NM_STATUS", new BigDecimal(2));
			selmDeviceApplyDao.update(map, co2);
		}
	}

	@Override
	public void batchPass(HttpReqRes httpReqRes) {
		String userId = (String)httpReqRes.getRequest().getSession().getAttribute("smsUserId");
		SmsUser user = smsUserDao.get(userId);
		String[] stMachineIdArray = httpReqRes.getRequest().getParameterValues(
				"stMachineIdArray[]");
		String stDeviceApplyId = httpReqRes.getParameter("stDeviceApplyId");
		if(stMachineIdArray!=null && stMachineIdArray.length>0){
			if(stDeviceApplyId!=null && !StringUtils.trim(stDeviceApplyId).isEmpty()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("NM_STATUS", new BigDecimal(2));
				map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
				if(null != user){
					map.put("ST_AUDIT_USER_ID", user.getStUserId());
					map.put("ST_AUDIT_USER_NAME", user.getStUserName());
				}
				Conditions conds = null;
				for(String temp : stMachineIdArray){
					conds = Conditions.newAndConditions();
					conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL,stDeviceApplyId));
					conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,temp));
					selmDeviceAlinkDao.update(map, conds);
					//添加或更新设备信息
					addOrUpdateDevice(stDeviceApplyId,temp);
				}
				
				Conditions co1 = Conditions.newAndConditions();
				co1.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
				co1.add(new Condition("NM_STATUS",Condition.OT_EQUAL,new BigDecimal(1)));
				List<SelmDeviceAlink> sda = new ArrayList<SelmDeviceAlink>();
				sda = selmDeviceAlinkDao.query(co1, null);
				Conditions co2 = Conditions.newAndConditions();
				co2.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				if(sda.size()>0){
					map1.put("NM_STATUS", new BigDecimal(3));
					selmDeviceApplyDao.update(map1, co2);
				}else{
					map1.put("NM_STATUS", new BigDecimal(2));
					selmDeviceApplyDao.update(map1, co2);
				}
				
			}
		}
		
	}

	@Override
	public void batchNoPass(HttpReqRes httpReqRes) {
		String userId = (String)httpReqRes.getRequest().getSession().getAttribute("smsUserId");
		SmsUser user = smsUserDao.get(userId);
		String[] stMachineIdArray = httpReqRes.getRequest().getParameterValues(
				"stMachineIdArray[]");
		String stDeviceApplyId = httpReqRes.getParameter("stDeviceApplyId");
		
		if(stMachineIdArray!=null && stMachineIdArray.length>0){
			if(stDeviceApplyId!=null && !StringUtils.trim(stDeviceApplyId).isEmpty()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("NM_STATUS", new BigDecimal(3));
				map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
				if(null != user){
					map.put("ST_AUDIT_USER_ID", user.getStUserId());
					map.put("ST_AUDIT_USER_NAME", user.getStUserName());
				}
				for(String temp : stMachineIdArray){
					Conditions conds = Conditions.newAndConditions();
					conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL,stDeviceApplyId));
					conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,temp));
					selmDeviceAlinkDao.update(map, conds);
				}
				
				Conditions co1 = Conditions.newAndConditions();
				co1.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
				co1.add(new Condition("NM_STATUS",Condition.OT_EQUAL,new BigDecimal(1)));
				List<SelmDeviceAlink> sda = new ArrayList<SelmDeviceAlink>();
				sda = selmDeviceAlinkDao.query(co1, null);
				Conditions co2 = Conditions.newAndConditions();
				co2.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				if(sda.size()>0){
					map1.put("NM_STATUS", new BigDecimal(3));
					selmDeviceApplyDao.update(map1, co2);
				}else{
					map1.put("NM_STATUS", new BigDecimal(2));
					selmDeviceApplyDao.update(map1, co2);
				}
				
			}
		}
	}

	

	
}
