package com.wondersgroup.statistics.service.impl;

import java.math.*;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;
import wfc.service.log.Log;


import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.statistics.bean.SelmAreaQueryDay;
import com.wondersgroup.statistics.bean.SelmStatistics;
import com.wondersgroup.statistics.dao.SelmAreaQueryDayDao;
import com.wondersgroup.statistics.service.SelmAreaQueryDayService;

/**
 * 区域日办件统计表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
@SuppressWarnings("all")
public class SelmAreaQueryDayServiceImpl implements SelmAreaQueryDayService {
	
	@Autowired
	private SmsUserDao smsUserDao;
	@Autowired
	private SelmAreaQueryDayDao selmAreaQueryDayDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	private Object suffix;
	/**
	 * 根据主键 {@link SelmAreaQueryDay#ST_AREA_ID} {@link SelmAreaQueryDay#ST_DAY}获取区域日办件统计表
	 * @param stAreaId
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_AREA_ID}
	 * @param stDay
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_DAY}
	 * @return 区域日办件统计表实例
	 */
	@Override
	public SelmAreaQueryDay get(String stAreaId, String stDay) {
		if (StringUtils.trimToEmpty(stAreaId).isEmpty())
			throw new NullPointerException("Parameter stAreaId cannot be null.");
		if (StringUtils.trimToEmpty(stDay).isEmpty())
			throw new NullPointerException("Parameter stDay cannot be null.");
		return selmAreaQueryDayDao.get(stAreaId, stDay);
	}

	/**
	 * 查询区域日办件统计表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 区域日办件统计表列表
	 */
	@Override
	public PaginationArrayList<SelmAreaQueryDay> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmAreaQueryDay.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmAreaQueryDayDao.query(conds, suffix, pageSize, currentPage);
	}
	

	/**
	 * 银行区域统计
	 * @param wrapper
	 *            查询条件
	 * @return 区域日办件统计表列表
	 */
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		/*int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}*/
		Conditions conds = Conditions.newAndConditions();
		Conditions gconds = Conditions.newAndConditions();
		Conditions sconds = Conditions.newAndConditions();
		String stPermission = httpReqRes.getParameter("stPermission");
		String user = httpReqRes.getParameter("user");
		//区域id
		String stAreaId = httpReqRes.getParameter("stAreaId");
		//区域名称
		String stName = httpReqRes.getParameter("stAddressName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String typeId = httpReqRes.getParameter("typeId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (stName != null && !StringUtils.trim(stName).isEmpty()) {
			conds.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, stName));
			gconds.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, stName));
			//sconds.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, stName));
		}
		
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL,Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL,Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		JSONObject obj = new JSONObject();
		//办件量
		JSONArray itemArr = new JSONArray();
		//政务设备数
		JSONArray govArr = new JSONArray();
		
		
		String suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by ia.NM_ORDER DESC";
		
		Conditions usercon = Conditions.newAndConditions();
		SmsUser smsUser = new SmsUser();
		if (user != null && !StringUtils.trim(user).isEmpty()) {
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			smsUser = smsUserL.get(0);
		}
		
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
					conds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
					gconds.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}else if(stPermission.contains("organ")){//按部门划分
			String stOrganId = smsUser.getStOrganId();
			if(stOrganId != null && !StringUtils.trim(stOrganId).isEmpty()){
				conds.add(new Condition("idi.ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
				gconds.add(new Condition("idi.ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
			}
			
		}else{//其他按区划分
			if (stPermission.contains("area")) {
				if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
					conds.add(new Condition("ia.ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
					gconds.add(new Condition("ia.ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
				}
			}
			conds.add(new Condition("ia.NM_ORDER",Condition.OT_LESS_EQUAL,16));
			conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,0));
			
		}
		
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			conds.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL, typeId));
			gconds.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL, typeId));
		}
		
		//办件量
		itemArr = selmQueryHisDao.areaItemCount(conds, suffix);
		
		//政务设备数
		gconds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,0));
		gconds.add(new Condition("ia.NM_ORDER",Condition.OT_LESS_EQUAL,16));
		govArr = selmQueryHisDao.govCount(gconds,suffix);
		
		
		List<SelmAreaQueryDay> selmAreaQueryDayList = new ArrayList<SelmAreaQueryDay>();
		
		String sum = "总计";
		BigDecimal iSum = new BigDecimal(0);
		BigDecimal gSum = new BigDecimal(0);
		SelmAreaQueryDay saqd = null;
		
		for(int i = 0; i <= itemArr.size()-1; i++){
			saqd = new SelmAreaQueryDay();
			saqd.setStAreaName(itemArr.getJSONObject(i).getString("areaName"));
			saqd.setNmDay(new BigDecimal(itemArr.getJSONObject(i).getString("iCount")));
			iSum = iSum.add(new BigDecimal(itemArr.getJSONObject(i).getString("iCount")));
			saqd.setNmGovNumber(new BigDecimal(govArr.getJSONObject(i).getString("gCount")));
			gSum = gSum.add(new BigDecimal(govArr.getJSONObject(i).getString("gCount")));
			selmAreaQueryDayList.add(saqd);
		}
		saqd = new SelmAreaQueryDay();
		saqd.setStAreaName(sum);
		saqd.setNmDay(iSum);
		saqd.setNmGovNumber(gSum);
		selmAreaQueryDayList.add(saqd);
		
		JSONObject returnObj = new JSONObject();
		//returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmAreaQueryDayList.size());
		returnObj.put("data", selmAreaQueryDayList);
		return returnObj;
	}
	
	/**
	 * 银行省统计
	 */
	@Override
	public JSONObject blist(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		Conditions cond1 = Conditions.newAndConditions();
		
		String stPermission = httpReqRes.getParameter("stPermission");
		String user = httpReqRes.getParameter("user");
		//区域名称
		String address = httpReqRes.getParameter("stAddressName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String typeId = httpReqRes.getParameter("typeId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		
		
		Conditions cond2 = Conditions.newAndConditions();
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		Conditions cond3 = Conditions.newAndConditions();
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			cond3.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL, typeId));
		}
		
		//银行分类
		if("bank".equals(stPermission)){
			//获取银行用户名标识
			Conditions usercon = Conditions.newAndConditions();
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			SmsUser smsUser = smsUserL.get(0);
			String stUserName = smsUser.getStUserName();
			//获取银行设备类型标识
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				if(stUserName.contains(typeInfoName)){
					cond1.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		
		List<SelmAreaQueryDay> selmAreaQueryDayList = new ArrayList<SelmAreaQueryDay>();
		
		String sum = "总计";
		BigDecimal iSum = new BigDecimal(0);
		BigDecimal sSum = new BigDecimal(0);
		SelmAreaQueryDay saqd = null;
		List<InfopubArea> pList = new ArrayList<InfopubArea>();
		if (address != null && !StringUtils.trim(address).isEmpty()) {
			cond1.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, address));
			Conditions aConds = Conditions.newAndConditions();
			aConds.add(new Condition("ST_AREA_NAME",Condition.OT_LIKE,address));
			pList = infopubAreaDao.query(aConds, null); //省列表
		}else{
			Conditions aConds = Conditions.newAndConditions();
			aConds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,null));
			pList = infopubAreaDao.query(aConds, null); //省列表
		}
		
		for(InfopubArea p : pList){
			String province = p.getStAreaName();
			List<String> areaIdList = null;
			if(province.equals("上海市")){
				Conditions shconds = Conditions.newAndConditions();
				shconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,province));
				String suffix = "order by ia2.NM_ORDER";
				areaIdList = infopubAreaDao.getSHIdList(shconds,suffix);
				
			}else{
				Conditions opconds = Conditions.newAndConditions();//其他省市
				opconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,province));
				String suffix = "order by ia3.NM_ORDER";
				areaIdList = infopubAreaDao.getOPIdList(opconds,suffix);
				
			}
			//某个省的服务量
			Conditions conds = Conditions.newAndConditions();
			conds.add(cond1);
			conds.add(cond2);
			conds.add(cond3);
			conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
			conds.add(new Condition("idi.ST_AREA_ID",Condition.OT_IN,areaIdList));
			String itemSum = selmQueryHisDao.bareaItemCount(conds, null);
			//某个省的设备量
			Conditions sconds = Conditions.newAndConditions();
			sconds.add(cond1);
			sconds.add(cond3);
			sconds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
			sconds.add(new Condition("idi.ST_AREA_ID",Condition.OT_IN,areaIdList));
			String devSum = selmQueryHisDao.socCount(sconds,null);
			//
			saqd = new SelmAreaQueryDay();
			saqd.setStAreaName(province);//省名
			saqd.setNmDay(new BigDecimal(itemSum));//办件量
			iSum = iSum.add(new BigDecimal(itemSum));//全部办件量叠加
			saqd.setNmSocialNumber(new BigDecimal(devSum));//终端量
			sSum = sSum.add(new BigDecimal(devSum));//全部设备叠加
			selmAreaQueryDayList.add(saqd);
		}
		
		saqd = new SelmAreaQueryDay();
		saqd.setStAreaName(sum);
		saqd.setNmDay(iSum);
		saqd.setNmSocialNumber(sSum);
		selmAreaQueryDayList.add(saqd);
		
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmAreaQueryDayList.size());
		returnObj.put("data", selmAreaQueryDayList);
		return returnObj;
	}
	
	
	/**
	 * 银行市统计
	 */
	@Override
	public JSONObject clist(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		Conditions cond1 = Conditions.newAndConditions();
		Conditions gconds = Conditions.newAndConditions();
		
		String stPermission = httpReqRes.getParameter("stPermission");
		String user = httpReqRes.getParameter("user");
		String province = httpReqRes.getParameter("province");
		//区域名称
		String address = httpReqRes.getParameter("stAddressName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		System.out.println(startDate+"--"+startDate);
		String typeId = httpReqRes.getParameter("typeId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (address != null && !StringUtils.trim(address).isEmpty()) {
			cond1.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, address));
			gconds.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, address));
		}
		
		Conditions cond2 = Conditions.newAndConditions();
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		Conditions cond3 = Conditions.newAndConditions();
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			cond3.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL, typeId));
		}

		//银行分类
		if("bank".equals(stPermission)){
			//获取银行用户名标识
			Conditions usercon = Conditions.newAndConditions();
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			SmsUser smsUser = smsUserL.get(0);
			String stUserName = smsUser.getStUserName();
			//获取银行设备类型标识
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				if(stUserName.contains(typeInfoName)){
					cond1.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		
		List<SelmAreaQueryDay> selmAreaQueryDayList = new ArrayList<SelmAreaQueryDay>();
		
		String sum = "总计";
		BigDecimal iSum = new BigDecimal(0);
		BigDecimal sSum = new BigDecimal(0);
		SelmAreaQueryDay saqd = null;
		
		
		InfopubArea cityInfo = infopubAreaDao.getName(province);
		List<String> areaIdList = null;
		if(province.equals("上海市")){
			Conditions shconds = Conditions.newAndConditions();
			shconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,province));
			String suffix = "order by ia2.NM_ORDER";
			areaIdList = infopubAreaDao.getSHIdList(shconds,suffix);
			Conditions conds = Conditions.newAndConditions();
			conds.add(cond1);
			conds.add(cond2);
			conds.add(cond3);
			conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
			conds.add(new Condition("idi.ST_AREA_ID",Condition.OT_IN,areaIdList));
			String itemSum = selmQueryHisDao.bareaItemCount(conds, null);
			//某个市的设备量
			Conditions sconds = Conditions.newAndConditions();
			sconds.add(cond1);
			sconds.add(cond3);
			sconds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
			sconds.add(new Condition("idi.ST_AREA_ID",Condition.OT_IN,areaIdList));
			String devSum = selmQueryHisDao.socCount(sconds,null);
			//
			saqd = new SelmAreaQueryDay();
			saqd.setStAreaName(province);//市名
			saqd.setNmDay(new BigDecimal(itemSum));//办件量
			iSum = iSum.add(new BigDecimal(itemSum));//全部办件量叠加
			saqd.setNmSocialNumber(new BigDecimal(devSum));//终端量
			sSum = sSum.add(new BigDecimal(devSum));//全部设备叠加
			selmAreaQueryDayList.add(saqd);
		}else{
			Conditions aConds = Conditions.newAndConditions();
			aConds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,cityInfo.getStAreaId()));
			List<InfopubArea> cList = infopubAreaDao.query(aConds, null); //市列表
			for(InfopubArea c : cList){
				String city = c.getStAreaName();
				Conditions opconds = Conditions.newAndConditions();//其他市
				opconds.add(new Condition("ia1.ST_AREA_NAME",Condition.OT_EQUAL,city));
				String suffix = "order by ia2.NM_ORDER";
				areaIdList = infopubAreaDao.getSHIdList(opconds,suffix);
				//某个市的服务量
				Conditions conds = Conditions.newAndConditions();
				conds.add(cond1);
				conds.add(cond2);
				conds.add(cond3);
				conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
				conds.add(new Condition("idi.ST_AREA_ID",Condition.OT_IN,areaIdList));
				String itemSum = selmQueryHisDao.bareaItemCount(conds, null);
				//某个市的设备量
				Conditions sconds = Conditions.newAndConditions();
				sconds.add(cond1);
				sconds.add(cond3);
				sconds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
				sconds.add(new Condition("idi.ST_AREA_ID",Condition.OT_IN,areaIdList));
				String devSum = selmQueryHisDao.socCount(sconds,null);
				//
				saqd = new SelmAreaQueryDay();
				saqd.setStAreaName(city);//市名
				saqd.setNmDay(new BigDecimal(itemSum));//办件量
				iSum = iSum.add(new BigDecimal(itemSum));//全部办件量叠加
				saqd.setNmSocialNumber(new BigDecimal(devSum));//终端量
				sSum = sSum.add(new BigDecimal(devSum));//全部设备叠加
				selmAreaQueryDayList.add(saqd);
			}
		}
		
		
		saqd = new SelmAreaQueryDay();
		saqd.setStAreaName(sum);
		saqd.setNmDay(iSum);
		saqd.setNmSocialNumber(sSum);
		selmAreaQueryDayList.add(saqd);
		
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmAreaQueryDayList.size());
		returnObj.put("data", selmAreaQueryDayList);
		return returnObj;
	}
	
	/**
	 * 银行区统计
	 */
	@Override
	public JSONObject dlist(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		Conditions cond1 = Conditions.newAndConditions();
		Conditions gconds = Conditions.newAndConditions();
		
		String stPermission = httpReqRes.getParameter("stPermission");
		String user = httpReqRes.getParameter("user");
		String city = httpReqRes.getParameter("city");
		//区域名称
		String address = httpReqRes.getParameter("stAddressName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String typeId = httpReqRes.getParameter("typeId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (address != null && !StringUtils.trim(address).isEmpty()) {
			//cond1.add(new Condition("idi.ST_AREA_NAME", Condition.OT_LIKE, address));
			gconds.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, address));
		}
		
		Conditions cond2 = Conditions.newAndConditions();
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}
		
		Conditions cond3 = Conditions.newAndConditions();
		if (typeId != null && !StringUtils.trim(typeId).isEmpty()) {
			cond3.add(new Condition("idt.ST_TYPE_ID", Condition.OT_EQUAL, typeId));
		}

		//银行分类
		if("bank".equals(stPermission)){
			//获取银行用户名标识
			Conditions usercon = Conditions.newAndConditions();
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			SmsUser smsUser = smsUserL.get(0);
			String stUserName = smsUser.getStUserName();
			//获取银行设备类型标识
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				if(stUserName.contains(typeInfoName)){
					cond1.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		
		List<SelmAreaQueryDay> selmAreaQueryDayList = new ArrayList<SelmAreaQueryDay>();
		
		String sum = "总计";
		BigDecimal iSum = new BigDecimal(0);
		BigDecimal sSum = new BigDecimal(0);
		SelmAreaQueryDay saqd = null;
		
		
		InfopubArea cityInfo = infopubAreaDao.getName(city);
		List<String> areaIdList = null;
		
		Conditions aConds = Conditions.newAndConditions();
		aConds.add(new Condition("ST_PARENT_AREA_ID",Condition.OT_EQUAL,cityInfo.getStAreaId()));
		List<InfopubArea> cList = infopubAreaDao.query(aConds, null); //区列表
		for(InfopubArea c : cList){
			String districtName = c.getStAreaName();
			String districtId = c.getStAreaId();
			//某个区的服务量
			Conditions conds = Conditions.newAndConditions();
			conds.add(cond1);
			conds.add(cond2);
			conds.add(cond3);
			conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
			conds.add(new Condition("idi.ST_AREA_ID",Condition.OT_EQUAL,districtId));
			String itemSum = selmQueryHisDao.bareaItemCount(conds, null);
			//某个区的设备量
			Conditions sconds = Conditions.newAndConditions();
			sconds.add(cond1);
			sconds.add(cond3);
			sconds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
			sconds.add(new Condition("idi.ST_AREA_ID",Condition.OT_EQUAL,districtId));
			String devSum = selmQueryHisDao.socCount(sconds,null);
			//
			saqd = new SelmAreaQueryDay();
			saqd.setStAreaName(districtName);//区名
			saqd.setNmDay(new BigDecimal(itemSum));//办件量
			iSum = iSum.add(new BigDecimal(itemSum));//全部办件量叠加
			saqd.setNmSocialNumber(new BigDecimal(devSum));//终端量
			sSum = sSum.add(new BigDecimal(devSum));//全部设备叠加
			selmAreaQueryDayList.add(saqd);
		}
		
		
		saqd = new SelmAreaQueryDay();
		saqd.setStAreaName(sum);
		saqd.setNmDay(iSum);
		saqd.setNmSocialNumber(sSum);
		selmAreaQueryDayList.add(saqd);
		
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmAreaQueryDayList.size());
		returnObj.put("data", selmAreaQueryDayList);
		return returnObj;
	}
	
	/**
	 * 银行街道统计
	 */
	@Override
	public JSONObject slist(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		
		Conditions gconds = Conditions.newAndConditions();
		
		String stPermission = httpReqRes.getParameter("stPermission");
		String user = httpReqRes.getParameter("user");
		String district = httpReqRes.getParameter("district");
		//区域名称
		String address = httpReqRes.getParameter("stAddressName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		Conditions cond1 = Conditions.newAndConditions();
		if (address != null && !StringUtils.trim(address).isEmpty()) {
			cond1.add(new Condition("ia.ST_DISTRICT", Condition.OT_LIKE, address));
		}
		
		Conditions cond3 = Conditions.newAndConditions();
		if (address != null && !StringUtils.trim(address).isEmpty()) {
			cond1.add(new Condition("ia.ST_AREA_NAME", Condition.OT_LIKE, address));
		}
		
		Conditions cond2 = Conditions.newAndConditions();
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			cond2.add(new Condition("sqh.DT_CREATE", Condition.OT_LESS_EQUAL, Timestamp.valueOf(endDate + " 23:59:59")));
		}

		//银行分类
		if("bank".equals(stPermission)){
			//获取银行用户名标识
			Conditions usercon = Conditions.newAndConditions();
			usercon.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, user));
			List<SmsUser> smsUserL = smsUserDao.query(usercon, null);
			SmsUser smsUser = smsUserL.get(0);
			String stUserName = smsUser.getStUserName();
			//获取银行设备类型标识
			Conditions typeInfoConds = Conditions.newAndConditions();
			typeInfoConds.add(new Condition("NM_DTYPE",Condition.OT_EQUAL,new BigDecimal(1)));
			List<InfopubDeviceType> typeInfo = infopubDeviceTypeDao.query(typeInfoConds, null);
			for(InfopubDeviceType emp : typeInfo){
				String typeInfoName = emp.getStTypeName();
				typeInfoName = typeInfoName.substring(0, typeInfoName.indexOf("行")+1);
				if(stUserName.contains(typeInfoName)){
					cond1.add(new Condition("idt.ST_TYPE_NAME", Condition.OT_LIKE, typeInfoName));
				}
			}
		}
		
		List<SelmAreaQueryDay> selmAreaQueryDayList = new ArrayList<SelmAreaQueryDay>();
		
		String sum = "总计";
		BigDecimal iSum = new BigDecimal(0);
		BigDecimal sSum = new BigDecimal(0);
		int numStQueryCount = 0;
		int numSheHui = 0;
		SelmAreaQueryDay saqd = null;
		
		//判断上海还是其他省份，其他省市的街道用区代替
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		InfopubArea disInfo = infopubAreaDao.getName(district);
		InfopubArea cityInfo = infopubAreaDao.get(disInfo.getStParentAreaId());
		
		if(cityInfo.getStAreaName().equals("上海市")){
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("IDT.NM_DTYPE", Condition.OT_EQUAL, 1));
			conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_EQUAL, district));
			conds.add(cond1);
			conds.add(cond2);
			String suffix="GROUP BY IA.ST_STREET";
			
			List<InfopubAddress> infopubAddressList = infopubAddressDao.streetSelmQueryCount(conds,suffix);
			
			for (InfopubAddress infopubAddress : infopubAddressList) {
				String stStreet = infopubAddress.getStStreet(); //街道信息
				String stQueryCount = infopubAddress.getStLabel(); //街道办件量
				conds = Conditions.newAndConditions();
				conds.add(new Condition("IA.ST_STREET", Condition.OT_EQUAL, stStreet));
				conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_EQUAL, district));
				List<InfopubDeviceType> infopubDeviceTypeList = infopubDeviceTypeDao.streetDeviceType(conds,null);
				String sheHui="";
				for (InfopubDeviceType infopubDeviceType : infopubDeviceTypeList) {
					sheHui = infopubDeviceType.getStDesc(); //社会化终端
				}
				SelmStatistics selmStatistics = new SelmStatistics();
				selmStatistics.setStName(stStreet);
				selmStatistics.setStExt2(sheHui);//社会化
				selmStatistics.setNmSort(new BigDecimal(stQueryCount));//办件量
				selmStatisticsList.add(selmStatistics);
				numStQueryCount+=Integer.parseInt(stQueryCount);
				numSheHui+=Integer.parseInt(sheHui);
			}	
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName("总计");
			selmStatistics.setStExt2(numSheHui+"");//社会化
			selmStatistics.setNmSort(new BigDecimal(numStQueryCount));//办件量
			selmStatisticsList.add(selmStatistics);
		
		}else{
			Conditions aConds = Conditions.newAndConditions();
			aConds.add(new Condition("ST_AREA_ID",Condition.OT_EQUAL,disInfo.getStAreaId()));
			List<InfopubArea> dList = infopubAreaDao.query(aConds, null); //区列表
			for(InfopubArea d : dList){
				String districtName = d.getStAreaName();
				String districtId = d.getStAreaId();
				//某个区的服务量
				Conditions conds = Conditions.newAndConditions();
				conds.add(cond1);
				conds.add(cond3);
				conds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
				conds.add(new Condition("ia.ST_AREA_ID",Condition.OT_EQUAL,districtId));
				String itemSum = selmQueryHisDao.bareaItemCount(conds, null);
				//某个qu的设备量
				Conditions sconds = Conditions.newAndConditions();
				sconds.add(cond1);
				sconds.add(new Condition("idt.NM_DTYPE",Condition.OT_EQUAL,1));
				sconds.add(new Condition("ia.ST_AREA_ID",Condition.OT_EQUAL,districtId));
				String devSum = selmQueryHisDao.socCount(sconds,null);
				
				SelmStatistics selmStatistics = new SelmStatistics();
				selmStatistics.setStName(districtName);
				selmStatistics.setStExt2(devSum);//社会化
				selmStatistics.setNmSort(new BigDecimal(itemSum));//办件量
				selmStatisticsList.add(selmStatistics);
				numStQueryCount+=Integer.parseInt(itemSum);
				numSheHui+=Integer.parseInt(devSum);
			}
			SelmStatistics selmStatistics = new SelmStatistics();
			selmStatistics.setStName("总计");
			selmStatistics.setStExt2(numSheHui+"");//社会化
			selmStatistics.setNmSort(new BigDecimal(numStQueryCount));//办件量
			selmStatisticsList.add(selmStatistics);
		}

		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmStatisticsList.size());
		returnObj.put("data", selmStatisticsList);
		return returnObj;
	}


	/**
	 * 根据主键 {@link SelmAreaQueryDay#ST_AREA_ID} {@link SelmAreaQueryDay#ST_DAY}删除区域日办件统计表
	 * 
	 * @param stAreaId
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_AREA_ID}
	 * @param stDay
	 *            区域日办件统计表主键 {@link SelmAreaQueryDay#ST_DAY}
	 */
	@Override
	public void remove(String stAreaId, String stDay) {
		if (StringUtils.trimToEmpty(stAreaId).isEmpty())
			throw new NullPointerException("Parameter stAreaId cannot be null.");
		if (StringUtils.trimToEmpty(stDay).isEmpty())
			throw new NullPointerException("Parameter stDay cannot be null.");
		selmAreaQueryDayDao.delete(stAreaId, stDay);
	}

	/**
	 * 保存或更新区域日办件统计表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 区域日办件统计表实例
	 */
	@Override
	public SelmAreaQueryDay saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmAreaQueryDay.ST_AREA_ID
		String stAreaId = wrapper.getParameter(SelmAreaQueryDay.ST_AREA_ID);
		// SelmAreaQueryDay.ST_DAY
		String stDay = wrapper.getParameter(SelmAreaQueryDay.ST_DAY);
		SelmAreaQueryDay oldSelmAreaQueryDay = null;
		if (!StringUtils.trimToEmpty(stAreaId).isEmpty() && !StringUtils.trimToEmpty(stDay).isEmpty()) {
			oldSelmAreaQueryDay = selmAreaQueryDayDao.get(stAreaId, stDay);
		}
		if (oldSelmAreaQueryDay == null) {// new
			SelmAreaQueryDay newSelmAreaQueryDay = (SelmAreaQueryDay) t4r.toBean(SelmAreaQueryDay.class);
			newSelmAreaQueryDay.setStAreaId(UUID.randomUUID().toString());
			newSelmAreaQueryDay.setStDay(UUID.randomUUID().toString());
			selmAreaQueryDayDao.add(newSelmAreaQueryDay);
			return newSelmAreaQueryDay;
		}else {// update
			oldSelmAreaQueryDay = (SelmAreaQueryDay) t4r.toBean(oldSelmAreaQueryDay, SelmAreaQueryDay.class);
			selmAreaQueryDayDao.update(oldSelmAreaQueryDay);
			return oldSelmAreaQueryDay;
		}
	}
	
	/**
	 * 日办件量统计录入（也可以录入某一天的）
	 */
	@Override
	public JSONObject updateSaqd(HttpReqRes httpReqRes) {

		String dtCreate = httpReqRes.getParameter("dtCreate");
		Conditions conds = Conditions.newAndConditions();
		if (dtCreate != null && !StringUtils.trim(dtCreate).isEmpty()) {
			conds.add(new Condition("convert(char(10),DT_CREATE,121)", Condition.OT_LIKE, dtCreate));
		}
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		//查询全部区域并去重
		String suffix="GROUP BY ST_DISTRICT";
		addressListName = infopubAddressDao.queryName(condaddressName, suffix);
		List<SelmStatistics> selmStatisticsList = new ArrayList<SelmStatistics>();
		//查询时间并去重
		String suffixdate ="";
		if(DB.getDatabaseName()=="MySQL"){
			suffixdate = "GROUP BY DATE_FORMAT(DT_CREATE,'%Y-%m-%d') ";
		}else{
			suffixdate = "GROUP BY convert(char(10),DT_CREATE,121) ";
		}
		List<SelmQueryHis> querydate = selmQueryHisDao.querydate(conds, suffixdate);
		for (SelmQueryHis selmQueryHis : querydate) {
			String cerate = selmQueryHis.getStDesc();
			String time = cerate.substring(4,5);
			if(time.equals(".")){
				cerate = cerate.replace(".", "-");
			}
		for (InfopubAddress infopubAddress : addressListName) {
			condaddress= Conditions.newAndConditions();
			condaddress.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubAddress.getStDistrict()));
			 addressList = infopubAddressDao.query(
					condaddress, null);
			//int sum = 0;
			int typeSum =0;
			int typeSum1 =0; 
			int selmSum =0; 
			for (InfopubAddress infopubAddress2 : addressList) {
				String stAdderssId = infopubAddress2.getStAddressId();
				Conditions cond2 = Conditions.newAndConditions();
				cond2.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAdderssId));
				List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
						cond2, null);
				//政务终端数量
				int typeCount = 0;
				for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
					String stTypeId = infopubDeviceInfo.getStTypeId();
					int suCount = infopubDeviceTypeDao.suCount(stTypeId, 0);
					typeCount = typeCount + suCount;
				}
				typeSum = typeSum + typeCount;
				
				//社会化终端数量
				int typeCount1 = 0;
				for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
					String stTypeId = infopubDeviceInfo.getStTypeId();
					int suCount = infopubDeviceTypeDao.suCount(stTypeId, 1);
					typeCount1 = typeCount1 + suCount;
				}
				typeSum1 = typeSum1 + typeCount1;
				//办件量
				int selmCount = 0;
				for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
					String stDeviceCode = infopubDeviceInfo.getStDeviceMac();
					 int selmNum = selmQueryHisDao
								.getAreaSelmQueryTime(stDeviceCode,cerate);
					 selmCount = selmCount + selmNum;
				}
				selmSum = selmSum + selmCount;
			}
			SelmAreaQueryDay newSelmAreaQueryDay  = new SelmAreaQueryDay();
			InfopubArea areaId = infopubAreaDao.getName(infopubAddress.getStDistrict());
			newSelmAreaQueryDay.setStAreaId(areaId.getStAreaId());
			newSelmAreaQueryDay.setStAreaName(infopubAddress.getStDistrict());
			newSelmAreaQueryDay.setStDay(cerate);
			newSelmAreaQueryDay.setNmGovNumber(new BigDecimal(typeSum));
			newSelmAreaQueryDay.setNmSocialNumber(new BigDecimal(typeSum1));
			newSelmAreaQueryDay.setNmDay(new BigDecimal(selmSum));
			selmAreaQueryDayDao.add(newSelmAreaQueryDay);
		}
	}
		
			JSONObject returnObj = new JSONObject();
			returnObj.put("data", selmStatisticsList);
			return returnObj;
	}

	@Override
	public JSONObject areaUpdateSaqd(HttpReqRes httpReqRes) {
		String district = httpReqRes.getParameter("district");
		//String district = "青浦区";
		String dtCreate = httpReqRes.getParameter("dtCreate");
		Conditions conds = Conditions.newAndConditions();
		if (dtCreate != null && !StringUtils.trim(dtCreate).isEmpty()) {
			conds.add(new Condition("convert(char(10),DT_CREATE,121)", Condition.OT_LIKE, dtCreate));
		}
				//查询时间并去重
		String suffixdate ="";
		if(DB.getDatabaseName()=="MySQL"){
			suffixdate = "GROUP BY DATE_FORMAT(DT_CREATE,'%Y-%m-%d') ";
		}else{
			suffixdate = "GROUP BY convert(char(10),DT_CREATE,121) ";
		}
		// 所属区查询
		Conditions condOrDis = Conditions.newOrConditions();
		if (district != null && !StringUtils.trim(district).isEmpty()) {
			Conditions conddistrict = Conditions.newAndConditions();
			conddistrict.add(new Condition("a.ST_DISTRICT", Condition.OT_LIKE, district));
			List<InfopubDeviceInfo> queryDis = infopubDeviceInfoDao.queryDis(conddistrict,null);
			for (InfopubDeviceInfo infopubDeviceInfo : queryDis) {
				String stMachineId = infopubDeviceInfo.getStDeviceMac();
				condOrDis.add(new Condition("ST_MACHINE_ID",
						Condition.OT_EQUAL, stMachineId));
			}
		}		
		List<SelmQueryHis> querydate = selmQueryHisDao.querydate(conds, suffixdate);
		int selmUp=0;
		for (SelmQueryHis selmQueryHis : querydate) {
			conds = Conditions.newAndConditions();
			conds.add(condOrDis);
			String cerate = selmQueryHis.getStDesc();
			conds.add(new Condition("convert(char(10),DT_CREATE,121)", Condition.OT_LIKE, cerate));
			List<SelmQueryHis> query = selmQueryHisDao.query(conds, null);
			//System.out.println(query.size());
			 Map<String, Object > map = new HashMap<String, Object >();
			 Conditions condArea = Conditions.newAndConditions();
			 condArea.add(new Condition("ST_DAY", Condition.OT_EQUAL,
					 cerate));
			 condArea.add(new Condition("ST_AREA_NAME", Condition.OT_EQUAL,
					 district));
			 map.put("NM_DAY", query.size());
			selmUp = selmAreaQueryDayDao.update(map,condArea);
		}
		selmUp+=selmUp;
		if(selmUp !=0){
			JSONObject returnObj = new JSONObject();
			returnObj.put("data", querydate);
			return returnObj;
		}
		return null;
	}

	

	
	
	
	
}
