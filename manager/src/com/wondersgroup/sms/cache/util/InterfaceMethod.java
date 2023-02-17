package com.wondersgroup.sms.cache.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;

import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.business.util.Decode;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoMap;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.infopub.dao.InfopubOdeviceStatusDao;

import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIJsonConverter;


public class InterfaceMethod {
	
	
	private SelmQueryHisDao selmQueryHisDao = new SelmQueryHisDao();
	private InfopubDeviceInfoDao infopubDeviceInfoDao = new InfopubDeviceInfoDao();
	private InfopubAreaDao infopubAreaDao = new InfopubAreaDao();
	private InfopubDeviceTypeDao infopubDeviceTypeDao = new InfopubDeviceTypeDao();
	private InfopubOdeviceStatusDao infopubOdeviceStatusDao = new InfopubOdeviceStatusDao();
	private SelmItemDao selmItemDao = new SelmItemDao();
	private SelmDeviceItemDao selmDeviceItemDao = new SelmDeviceItemDao();
	private InfopubAddressDao infopubAddressDao = new InfopubAddressDao();
	
	
	public static void main(String[] args){
		new InterfaceMethod().selmQuertTop();
	}
	
	/**
	 * 办件量top20
	 */
	public JSONObject selmQuertTop() {
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
	
	/**
	 * 30办件量
	 */
	public JSONObject areaQueryHis() {
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
			cond1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, "++++++++"));
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
	
	
	/**
	 * 首页头部数据
	 */
	public JSONObject leading(){
		Conditions conds = Conditions.newAndConditions();
		 conds.add(new Condition("ST_STATE", Condition.OT_UNEQUAL, new BigDecimal(0)));
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
	
		List<SelmItem> selmItemList = selmItemDao.query(null, null);
		int selmItemSum = selmItemList.size();// 事项总数*/
		
		// 判断时间日期
		Conditions condQuery = Conditions.newAndConditions();
		Conditions condQueryDay = Conditions.newAndConditions();
		
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

		int SelmQuerySum = selmQueryHisDao.queryleadCount(condQuery, null);

		condQueryDay.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
				Timestamp.valueOf(endDate + " 00:00:00")));
		condQueryDay.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
				Timestamp.valueOf(endDate + " 23:59:59")));
		int SelmQueryDaySum = selmQueryHisDao
				.queryleadCount(condQueryDay, null);
		JSONObject returnObj = new JSONObject();
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
	
	
	/**
	 * 统计分系-设备统计-区数据
	 * @param httpReqRes
	 * @return
	 */
	public JSONObject addresslistTypeDevice() {
		String staddressName = "";
		staddressName = Decode.decode(staddressName, "utf-8");
		String stPermission = "project_admin";
		String stAreaId = "cb61458b-7031-49b0-bac4-c4c67db6dbdd";
		JSONObject returnObj = new JSONObject();
		JSONObject returnType = new JSONObject();
		JSONObject returnNm = new JSONObject();
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				InfopubArea infopubArea = infopubAreaDao.get(stAreaId);
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, infopubArea.getStAreaName()));
			}else {
				throw new NullPointerException("AreaId不能为空");
			}
		}else{
			if (staddressName != null && !StringUtils.trim(staddressName).isEmpty()) {
				condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, staddressName));
			}
		}
		condaddressName.add(new Condition("ST_CITY", Condition.OT_LIKE, "上海市"));
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		// 查询全部区域并去重
		String suffix = null;
			if(DB.getDatabaseName()=="MySQL"){
				suffix = "GROUP BY ST_DISTRICT  ORDER BY field(ST_DISTRICT,'崇明区','奉贤区','青浦区','松江区','金山区','嘉定区','闵行区','宝山区','杨浦区','虹口区','普陀区','长宁区','徐汇区','静安区','黄浦区','浦东新区','临港新片区') DESC";
			}else{
				suffix = " GROUP BY ST_DISTRICT  ORDER BY  case [ST_DISTRICT] WHEN '崇明区' THEN 1 WHEN '奉贤区' THEN 2 WHEN '青浦区' THEN 3 WHEN '松江区' THEN 4 WHEN '金山区' THEN 5 WHEN '嘉定区' THEN 6 WHEN '闵行区' THEN 7 WHEN '宝山区' THEN 8 WHEN '杨浦区' THEN 9 WHEN '虹口区' THEN 10 WHEN '普陀区' THEN 11 WHEN '长宁区' THEN 12 WHEN '徐汇区' THEN 13 WHEN '静安区' THEN 14 WHEN '黄浦区' THEN 15 WHEN '浦东新区'  THEN 16 WHEN '临港新片区' THEN 16 END DESC";
		}

		addressListName = infopubAddressDao.queryName(condaddressName, suffix);

		// 查询类型
		Conditions condDevice = Conditions.newAndConditions();
		Conditions conds = Conditions.newAndConditions();
		Conditions condOr = Conditions.newOrConditions();
		Conditions condOrDevice = Conditions.newOrConditions();
		Conditions condNmDtype = Conditions.newAndConditions();
		String suffix1 = "GROUP BY NM_DTYPE";
		condNmDtype.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
				null));
		List<InfopubDeviceType> query = infopubDeviceTypeDao.queryNmDtype(
				condNmDtype, suffix1);
		List listAll = new ArrayList();
		for (InfopubDeviceType infopubDeviceType1 : query) {
			BigDecimal nmDtype = infopubDeviceType1.getNmDtype();
			conds = Conditions.newAndConditions();
			conds.add(new Condition("NM_DTYPE", Condition.OT_LIKE, nmDtype));
			conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_LIKE,
					null));
			List<InfopubDeviceType> InfopubDeviceTypeList = infopubDeviceTypeDao
					.query(conds, null);
				List listObj = new ArrayList();
				for (InfopubAddress infopubAddress : addressListName) {
					condaddress = Conditions.newAndConditions();
					//condDevice = Conditions.newAndConditions();
					condOrDevice = Conditions.newOrConditions();
					condaddress.add(new Condition("ST_DISTRICT",
							Condition.OT_LIKE, infopubAddress.getStDistrict()));
					addressList = infopubAddressDao.query(condaddress, null);
					for (InfopubAddress infopubAddress2 : addressList) {
						String stAddressId = infopubAddress2.getStAddressId();
						condOrDevice.add(new Condition("ST_ADDRESS_ID",
								Condition.OT_LIKE, stAddressId));
					}
					List listType = new ArrayList();
					if(nmDtype.equals(new BigDecimal(0))){
						condDevice.add(condOrDevice);
						condDevice.add(new Condition("IDT.NM_DTYPE",
								Condition.OT_EQUAL, 0));
						List<InfopubDeviceInfo> queryZheng = infopubDeviceInfoDao.queryZheng(condDevice,null);
						condDevice = Conditions.newAndConditions();
						List listCode = new ArrayList();
						for (InfopubDeviceInfo infopubDeviceInfo : queryZheng) {
							listCode.add(infopubDeviceInfo.getStDeviceCode());
						}
						returnObj.put("typeName", "政务终端");
						returnObj.put("code", listCode.size());
						//returnObj.put("codeName", listCode);
						listType.add(returnObj);
						returnObj = new JSONObject();
					}else{
						for (InfopubDeviceType infopubDeviceType : InfopubDeviceTypeList) {
							condOr = Conditions.newOrConditions();
							String stName = infopubDeviceType.getStTypeName();
							//System.out.println(stName);
							String stTypeId = infopubDeviceType.getStTypeId();
							condOr.add(new Condition("ST_TYPE_ID", Condition.OT_LIKE,
									stTypeId));
						// System.out.println(infopubAddress.getStDistrict());
						condDevice.add(condOr);
						condDevice.add(condOrDevice);
						List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao
								.query(condDevice, null);
						condDevice = Conditions.newAndConditions();
						List listCode = new ArrayList();
						for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
							listCode.add(infopubDeviceInfo.getStDeviceCode());
						}
						returnObj.put("typeName", stName);
						returnObj.put("code", listCode.size());
						//returnObj.put("codeName", listCode);
						listType.add(returnObj);
						returnObj = new JSONObject();
					}
					}
					returnType.put("area", infopubAddress.getStDistrict());
					returnType.put("type", listType);
					listObj.add(returnType);
					returnType = new JSONObject();
				}
			returnNm.put("nmDtype", nmDtype);
			returnNm.put("nmType", listObj);
			listAll.add(returnNm);
			returnNm = new JSONObject();
		}
		JSONObject returnAll = new JSONObject();
		returnAll.put("data", listAll);
		return returnAll;
	}
	

	

}
