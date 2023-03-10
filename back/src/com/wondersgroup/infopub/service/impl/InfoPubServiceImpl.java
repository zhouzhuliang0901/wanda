package com.wondersgroup.infopub.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;
import wfc.service.log.Log;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.dataitem.forward.web.dao.SelmItemDao;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubAttachment;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;
import com.wondersgroup.infopub.bean.SelmQueryHis;
import com.wondersgroup.infopub.dao.AnalyticsVisitedDetailDao;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubAttachmentDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoExtDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeExtDao;
import com.wondersgroup.infopub.dao.InfopubOdeviceStatusExtDao;
import com.wondersgroup.infopub.dao.SelmDeviceItemDao;
import com.wondersgroup.infopub.dao.SelmQueryHisDao;
import com.wondersgroup.infopub.service.InfoPubService;

@Service
@Transactional
public class InfoPubServiceImpl implements InfoPubService {
	
	@Autowired
	private InfopubDeviceInfoExtDao infopubDeviceInfoDao;
	@Autowired
	private InfopubOdeviceStatusExtDao infopubOdeviceStatusDao;
	@Autowired
	private InfopubAttachmentDao infopubAttachmentDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	@Autowired
	private InfopubDeviceTypeExtDao infopubDeviceTypeDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private AnalyticsVisitedDetailDao analyticsVisitedDetailDao;
	
	
	/**
	 * ????????????????????????
	 */
	@Override
	public JSONObject getAlldeviceInfo(HttpReqRes httpReqRes) {
		String adderss = httpReqRes.getParameter("address");
		Conditions conds = Conditions.newAndConditions();
		if (adderss != null && !StringUtils.trim(adderss).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ADDRESS", Condition.OT_LIKE,
					adderss));
		}
		List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(conds, null);
		for (InfopubDeviceInfo infopubDeviceInfo : list) {
			List<InfopubOdeviceStatus> infopubOdeviceStatus = infopubOdeviceStatusDao
					.getByExcepyion(infopubDeviceInfo.getStDeviceMac());
			//?????????????????????????????????????????????????????????????????????
			if(infopubOdeviceStatus!=null){
				infopubDeviceInfo.setNmNotification(new BigDecimal(1));
			}else{
				infopubDeviceInfo.setNmNotification(new BigDecimal(0));
			}
			//?????????????????????
			List<InfopubOdeviceStatus> infopubOdeviceMecount = infopubOdeviceStatusDao
					.getByMecount(infopubDeviceInfo.getStDeviceMac());
			if(infopubOdeviceMecount==null){
			}else{
			for (InfopubOdeviceStatus infopubOdeviceStatusMe : infopubOdeviceMecount) {
				infopubDeviceInfo.setNmIsHost(infopubOdeviceStatusMe.getNmRemain());
				}
			}
			
			//????????????????????????
			List<InfopubOdeviceStatus> infopubOdevicePrcount = infopubOdeviceStatusDao
					.getByPrcount(infopubDeviceInfo.getStDeviceMac());
			if(infopubOdevicePrcount==null){
			}else{
			for (InfopubOdeviceStatus infopubOdeviceStatusPr : infopubOdevicePrcount) {
				infopubDeviceInfo.setNmRecover(infopubOdeviceStatusPr.getNmRemain());
				}
			}
			//????????????????????????????????????
			int selmDeviceItem = selmDeviceItemDao
					.getCount(infopubDeviceInfo.getStDeviceId());
			infopubDeviceInfo.setNmOrder(new BigDecimal(selmDeviceItem));
			//??????????????????????????????
			 int num = selmQueryHisDao
						.getAreaSelmQuery(infopubDeviceInfo.getStDeviceId());
			infopubDeviceInfo.setNmInterval(new BigDecimal(num));
		}
		JSONObject obj = new JSONObject();
		obj.put("data", list);
		return obj;
	}
	
	/**
	 * ?????????????????????????????????
	 */
	@Override
	public JSONObject getAreadeviceInfo(HttpReqRes httpReqRes) {
		String adderss = httpReqRes.getParameter("address");
		Conditions conds = Conditions.newAndConditions();
		if (adderss != null && !StringUtils.trim(adderss).isEmpty()) {
			conds.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE,
					adderss));
		}
		List<InfopubArea> Arealist = infopubAreaDao.query(conds, null);
		for (InfopubArea infopubArea : Arealist) {
			Conditions cond = Conditions.newAndConditions();
				cond.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						infopubArea.getStAreaId()));
			List<InfopubDeviceInfo> infopubDeviceInfolist = infopubDeviceInfoDao.query(cond, null);
			int Excount=0;
			int Nocount=0;
			for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfolist) {
				System.out.println(infopubDeviceInfo.getStDeviceMac());
				List<InfopubOdeviceStatus> infopubOdeviceStatus = infopubOdeviceStatusDao
						.getByExcepyion(infopubDeviceInfo.getStDeviceMac());
				//?????????????????????????????????????????????????????????????????????
				if(infopubOdeviceStatus!=null){
					Excount++;
				}else{
					Nocount++;
				}
			}
			String count = Excount+"";
			infopubArea.setStDesc(count);//??????
			infopubArea.setNmOrder(new BigDecimal(Nocount));//??????
			
		}
		JSONObject obj = new JSONObject();
		obj.put("data", Arealist);
		return obj;
	}
	
	
	/**
	 * ??????????????????????????????
	 */
	@Override
	public JSONObject getSelmQuery(HttpReqRes httpReqRes) {
		List<SelmQueryHis> selmQueryHis = selmQueryHisDao
				.getSelmQuery();
		JSONObject obj = new JSONObject();
		obj.put("data", selmQueryHis);
		return obj;
	}
	

	/**
	 * ????????????????????????????????????
	 * address???**???
	 */
	@Override
	public JSONObject getAreaSelmQuery(HttpReqRes httpReqRes) {
		String adderss = httpReqRes.getParameter("address");
		Conditions conds = Conditions.newAndConditions();
		if (adderss != null && !StringUtils.trim(adderss).isEmpty()) {
			conds.add(new Condition("ST_AREA_NAME", Condition.OT_LIKE,
					adderss));
		}
		List<InfopubArea> Arealist = infopubAreaDao.query(conds, null);
		
		for (InfopubArea infopubArea : Arealist) {
			Conditions cond = Conditions.newAndConditions();
				cond.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						infopubArea.getStAreaId()));
		List<InfopubDeviceInfo> list = infopubDeviceInfoDao.query(cond, null);
		int count=0;
		for (InfopubDeviceInfo infopubDeviceInfo : list) {
			 int num = selmQueryHisDao
					.getAreaSelmQuery(infopubDeviceInfo.getStDeviceId());
			 count+=num;
			}
		String selmQueryHisCount = count + "";
		infopubArea.setStAreaCode(selmQueryHisCount);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("data", Arealist);
		return obj;
	}

	@Override
	public JSONObject deviceInfoListMac(HttpReqRes httpReqRes) {
			String draw = httpReqRes.getParameter("draw");
			int drawInt = 0;
			if (draw != null) {
				drawInt = Integer.valueOf(draw) + 1;
				Log.info("?????????????????????:" + drawInt);
			}
			Conditions conds = Conditions.newAndConditions();
			String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
			JSONObject returnObj = new JSONObject();
			if (stDeviceMac != null && !StringUtils.trim(stDeviceMac).isEmpty()) {
				conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_LIKE, stDeviceMac));
			List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao.queryListMac(conds,null);
			returnObj.put("draw", drawInt);
			returnObj.put("recordsTotal", infopubDeviceInfoList.size());
			returnObj.put("data", infopubDeviceInfoList);
			}else{
				returnObj.put("draw", drawInt);
				returnObj.put("recordsTotal","");
				returnObj.put("data", "");
			}
			return returnObj;
		}

	@Override
	public JSONObject selmQuertTop(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = "GROUP BY ST_ITEM_NAME ORDER BY cont DESC";
		int pageSize=20;
		int currentPage=0;
		List<SelmQueryHis> queryItem = selmQueryHisDao.selmQuertTop(conds, suffix,pageSize,currentPage);
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", queryItem.size());
		returnObj.put("data", queryItem);
		return returnObj;
	}

	@Override
	public JSONObject addresslistTypeDevice(HttpReqRes httpReqRes) {
		
		List<InfopubAddress> selmStatisticsList = new ArrayList<InfopubAddress>();
		List<Integer> amList = new ArrayList<Integer>();
		List<Integer> typeList = new ArrayList<Integer>();
		List<Integer> typeList1 = new ArrayList<Integer>();
		List<Integer> selmList = new ArrayList<Integer>();
		Conditions condArea = Conditions.newAndConditions();
		InfopubArea infopubArea = infopubAreaDao.getName("?????????");
		condArea.add(new Condition("ST_PARENT_AREA_ID", Condition.OT_LIKE, infopubArea.getStAreaId()));
		String suffix1="ORDER BY NM_ORDER DESC";
		List<InfopubArea> infopubAreaName = infopubAreaDao.query(condArea, suffix1);
		//??????????????????
		for (InfopubArea infopubAreaId : infopubAreaName) {
			String stAreaId = infopubAreaId.getStAreaId();
			Conditions cond2 = Conditions.newAndConditions();
			cond2.add(new Condition("ST_AREA_ID", Condition.OT_EQUAL, stAreaId));
			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
					cond2, null);
			int typeCount = 0;
			int typeCount1 = 0;
			int selmCount = 0;
			int nmCont = 0;
			int count = 0;
			Conditions condMacId = Conditions.newOrConditions();
			for (InfopubDeviceInfo infopubDeviceInfo : deviceList) {
				BigDecimal nmSdtype = infopubDeviceInfo.getNmSdtype();
				if(infopubDeviceInfo.getNmSdtype()!=null){
					int sdtype = nmSdtype.intValue();
					if(sdtype==0 ){
						typeCount++; //??????
					}
					if(sdtype==1){
						selmCount++; //??????
					}
				}else{
					nmCont++;
				}
				
				String stTypeId = infopubDeviceInfo.getStTypeId();
				int suCount = infopubDeviceTypeDao.suCount(stTypeId, 1);
				typeCount1 = typeCount1 + suCount; //??????
				
				String stDeviceCode = infopubDeviceInfo.getStDeviceMac();
				condMacId.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
						stDeviceCode));
		}
			Conditions condQuery = Conditions.newAndConditions();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar c = Calendar.getInstance();
		    String endDate = format.format(date);
		    c.setTime(date);
		    c.add(Calendar.MONTH, -1);
		    Date m = c.getTime();
		    String startDate = format.format(m);
		    	condQuery.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						startDate + " 00:00"));
				condQuery.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						endDate + " 23:59"));
			condMacId.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, "++++++++"));
			condQuery.add(condMacId);
			List<SelmQueryHis>	selmQueryHisList = selmQueryHisDao.query(condQuery, null);
			int SelmQuerySum = selmQueryHisList.size(); //30????????????
			 count = count + SelmQuerySum;
			
			typeList.add(typeCount+nmCont); //??????
			typeList1.add(typeCount1);//??????
			selmList.add(selmCount); //??????
			amList.add(count); //?????????
			InfopubAddress infopubAddressCount = new InfopubAddress();
			infopubAddressCount.setStDistrict(infopubAreaId.getStAreaName());
			infopubAddressCount.setNmLat(new BigDecimal(typeCount+nmCont));//????????????????????????
			infopubAddressCount.setNmLng(new BigDecimal(typeCount1));//?????????
			infopubAddressCount.setStAddress(selmCount+"");//????????????????????????
			infopubAddressCount.setStLabel(count+"");//30??????????????????
			infopubAddressCount.setStStreet(count+"");//30??????????????????
			selmStatisticsList.add(infopubAddressCount);
		}
		
			int typeAll = 0;
			for(int i=0;i<typeList.size();i++){
				typeAll= typeList.get(i) + typeAll;
			}
			int typeAll1 = 0;
			for(int i=0;i<typeList1.size();i++){
				typeAll1= typeList1.get(i) + typeAll1;
			}
			int selmAll = 0;
			for(int i=0;i<selmList.size();i++){
				selmAll= selmList.get(i) + selmAll;
			}
			int amAll = 0;
			for(int i=0;i<amList.size();i++){
				amAll= amList.get(i) + amAll;
			}
			InfopubAddress infopubAddressCount = new InfopubAddress();
			infopubAddressCount.setStDistrict("??????");
			infopubAddressCount.setNmLat(new BigDecimal(typeAll));//????????????????????????
			infopubAddressCount.setNmLng(new BigDecimal(typeAll1));//?????????
			infopubAddressCount.setStAddress(selmAll+"");//????????????????????????
			infopubAddressCount.setStLabel(amAll+"");//?????????
			selmStatisticsList.add(infopubAddressCount);
			JSONObject returnObj = new JSONObject();
			returnObj.put("recordsTotal", selmStatisticsList.size());
			returnObj.put("data", selmStatisticsList);
			return returnObj;
	}



	/**
	 * ??????????????????
	 */
	@Override
	public JSONObject selmQuertNum(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		List<SelmItem> selmItemList = selmItemDao.query(conds, null);
		int selmItemSum = selmItemList.size();//????????????*/
		JSONObject obj = new JSONObject();
		obj.put("data", selmItemSum);
		return obj;
	}

	@Override
	public JSONObject selmQuertListTow(HttpReqRes httpReqRes) {
//		Conditions conds = Conditions.newAndConditions();
//		String suffix = "ORDER BY DT_CREATE DESC";
//		int pageSize=2;
//		int currentPage=0;
//		List<SelmQueryHis>selmQueryHisList = selmQueryHisDao.query(conds, suffix,pageSize,currentPage);
		List<SelmQueryHis> selmQueryHisList = selmQueryHisDao.queryTop2();
		for (int i = 0; i < selmQueryHisList.size(); i++) {
			SelmQueryHis selmQueryHis = selmQueryHisList.get(i);
			String stDeviceMac = selmQueryHis.getStMachineId();
			InfopubDeviceInfo mac = infopubDeviceInfoDao.getMac(stDeviceMac);
			if(mac==null){
				selmQueryHis.setStDesc("");
			}else{
				InfopubAddress infopubAddress = infopubAddressDao.get(mac.getStAddressId());
				selmQueryHis.setStDesc(infopubAddress.getStLabel());
			}
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmQueryHisList.size());
		returnObj.put("data", selmQueryHisList);
		return returnObj;
	}

	@Override
	public JSONObject areaSelmQuert(HttpReqRes httpReqRes) {
		String stDistrictName = httpReqRes.getParameter("stDistrictName");
		Conditions conds = Conditions.newAndConditions();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c = Calendar.getInstance();
	    String endDate = format.format(date);
	    c.setTime(date);
	    c.add(Calendar.MONTH, -1);
	    Date m = c.getTime();
	    String startDate = format.format(m);
	    conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_LIKE,
				stDistrictName));
	    conds.add(new Condition("SQH.DT_CREATE", Condition.OT_GREATER_EQUAL,
				startDate + " 00:00"));
	    conds.add(new Condition("SQH.DT_CREATE", Condition.OT_LESS_EQUAL,
				endDate + " 23:59"));
		String suffix = "GROUP BY IA.ST_STREET";
		
		List<InfopubAddress> areaListName = infopubAddressDao.streetSelmQueryHis(conds, suffix);
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", areaListName.size());
		returnObj.put("data", areaListName);
		
		return returnObj;
	}

	@Override
	public JSONObject areaSelmQuertTop(HttpReqRes httpReqRes) {
		String stDistrictName = httpReqRes.getParameter("stDistrictName");
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("IA.ST_DISTRICT", Condition.OT_LIKE,
				stDistrictName));
		String suffix = "GROUP BY SQH.ST_ITEM_NAME ORDER BY C DESC";
		int pageSize=10;
		int currentPage=0;
		List<SelmQueryHis> queryItem = selmQueryHisDao.queryAreaSelmHisTop(conds,suffix,pageSize,currentPage);
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", queryItem.size());
		returnObj.put("data", queryItem);
		return returnObj;
   }

	@Override
	public JSONObject streetDeviceList(HttpReqRes httpReqRes) {
		String stStreetName = httpReqRes.getParameter("stStreetName");
		Conditions conds = Conditions.newAndConditions();
		Conditions condDevice = Conditions.newOrConditions();
		conds.add(new Condition("ST_STREET", Condition.OT_EQUAL,
				stStreetName));
		List<InfopubDeviceInfo> infopubDeviceInfoList = new ArrayList<InfopubDeviceInfo>();
		List<InfopubAddress> infopubAddressList = infopubAddressDao.query(conds, null);
		for (InfopubAddress infopubAddress : infopubAddressList) {
			String stAddressId = infopubAddress.getStAddressId();
			condDevice.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL,
					stAddressId));
			infopubDeviceInfoList = infopubDeviceInfoDao.query(condDevice, null);
			for (InfopubDeviceInfo infopubDeviceInfo : infopubDeviceInfoList) {
				List<InfopubOdeviceStatus> infopubOdeviceStatus = infopubOdeviceStatusDao
						.getByExcepyion(infopubDeviceInfo.getStDeviceMac());
				//?????????????????????????????????????????????????????????????????????
				if(infopubOdeviceStatus!=null){
					infopubDeviceInfo.setNmNotification(new BigDecimal(1));
				}else{
					infopubDeviceInfo.setNmNotification(new BigDecimal(0));
				}
				//????????????????????????????????????
				int selmDeviceItem = selmDeviceItemDao
						.getCount(infopubDeviceInfo.getStDeviceId());
				infopubDeviceInfo.setNmOrder(new BigDecimal(selmDeviceItem));
				//??????????????????????????????
				 int num = selmQueryHisDao
							.getAreaSelmQuery(infopubDeviceInfo.getStDeviceMac());
				infopubDeviceInfo.setNmInterval(new BigDecimal(num));
				
				String stTypeId = infopubDeviceInfo.getStTypeId();
				InfopubAttachment infopubAttachment = infopubAttachmentDao.getLinkId(stTypeId);
				if(infopubAttachment!=null){
					Conditions condImage = Conditions.newAndConditions();
					condImage.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL, infopubAttachment.getStLinkId()));
					byte[] blob = BlobHelper.getBlob("INFOPUB_ATTACHMENT", "BL_CONTENT", condImage.toString(),condImage.getObjectArray());
					String base64Str = DatatypeConverter.printBase64Binary(blob);
					infopubDeviceInfo.setStTypeId(base64Str);
				}
			}
			
		}
		JSONObject obj = new JSONObject();
		obj.put("recordsTotal", infopubDeviceInfoList.size());
		obj.put("data", infopubDeviceInfoList);
		return obj;
	}

	@Override
	public JSONObject areaDeviceInfo(HttpReqRes httpReqRes) {
		String stDistrictName = httpReqRes.getParameter("stDistrictName");
		Conditions condaddress = Conditions.newAndConditions();
		Conditions condaddressName = Conditions.newAndConditions();
		condaddressName.add(new Condition("ST_DISTRICT", Condition.OT_LIKE,
				stDistrictName));
		List<InfopubAddress> areaListName = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressListName = new ArrayList<InfopubAddress>();
		List<InfopubAddress> addressList = new ArrayList<InfopubAddress>();
		//????????????id??????????????????????????????
		String suffix="GROUP BY ST_STREET";
		addressListName = infopubAddressDao.queryStreet(condaddressName, suffix);
			for (InfopubAddress infopubAddress : addressListName) {
				if(infopubAddress.getStStreet()!=null && !infopubAddress.getStStreet().equals("")){
				condaddress= Conditions.newAndConditions();
				condaddress.add(new Condition("ST_STREET", Condition.OT_EQUAL, infopubAddress.getStStreet()));
				 addressList = infopubAddressDao.query(
						condaddress, null);
				Conditions cond2 = Conditions.newOrConditions();
				 for (InfopubAddress infopubAddress2 : addressList) {
						String stAdderssId = infopubAddress2.getStAddressId();
						cond2.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAdderssId));
		}
				 List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
							cond2, null);
				 InfopubAddress address = new InfopubAddress();
				 address.setStStreet(infopubAddress.getStStreet());
				 address.setNmLat(new BigDecimal(deviceList.size()));
				 areaListName.add(address);
			}
	}
			JSONObject returnObj = new JSONObject();
			returnObj.put("recordsTotal", areaListName.size());
			returnObj.put("data", areaListName);
		
			return returnObj;
	}

	@Override
	public JSONObject itemAreaQuery(HttpReqRes httpReqRes) {

			String stItemName = httpReqRes.getParameter("stItemName");
			//String stItemName = "???????????????";
			Conditions conds= Conditions.newAndConditions();
			if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
				conds.add(new Condition("SQH.ST_ITEM_NAME", Condition.OT_EQUAL,
						stItemName));
			}
			String suffix = " GROUP BY IA.ST_DISTRICT ORDER BY NUMBER DESC"; 
			List<InfopubArea> infopubAreaList = infopubAreaDao.queryDisNumber(conds, suffix);
			
			
			
			
				JSONObject returnObj = new JSONObject();
				returnObj.put("recordsTotal", infopubAreaList.size());
				returnObj.put("data", infopubAreaList);
			
				return returnObj;
		
	}

	@Override
	public JSONObject itemStreetQuery(HttpReqRes httpReqRes) {
		String stItemName = httpReqRes.getParameter("stItemName");
		Conditions conds = Conditions.newOrConditions();
		conds.add(new Condition("a.ST_ITEM_NAME", Condition.OT_EQUAL,
				stItemName));
		String suffix = " GROUP BY c.st_street ORDER BY cont desc ";
		int pageSize=16;
		int currentPage=0;
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.selmStreetTop(conds,suffix,pageSize,currentPage);
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("data", selmQueryList);
		return returnObj;
	}

	@Override
	public JSONObject streetSelmQuerTop(HttpReqRes httpReqRes) {
		String stStreetName = httpReqRes.getParameter("stStreetName");
		Conditions conds = Conditions.newOrConditions();
		conds.add(new Condition("IA.ST_STREET", Condition.OT_EQUAL,
				stStreetName));
		String suffix = "GROUP BY SQH.ST_ITEM_NAME ORDER BY NUMBER DESC";
		int pageSize=10;
		int currentPage=0;
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.streetSelmQuerTop(conds,suffix,pageSize,currentPage);
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("data", selmQueryList);
		return returnObj;
	}

	@Override
	public JSONObject itemPeNumber(HttpReqRes httpReqRes) {
		String stItemName = httpReqRes.getParameter("stItemName");
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.itemPeopleNumber(stItemName);
		int sum = 0;
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			String selmPeopleNum = selmQueryHis.getStDesc();
			sum += Integer.parseInt(selmPeopleNum);
		}
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			String num = selmQueryHis.getStDesc();
			int number = Integer.parseInt(num);
			// ?????????????????????????????????   
			NumberFormat numberFormat = NumberFormat.getInstance();   
			// ???????????????????????????2???   
			numberFormat.setMaximumFractionDigits(0);   
			String result = numberFormat.format((float)number/(float)sum*100);
			selmQueryHis.setStDesc(result+"%");
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("data", selmQueryList);
		return returnObj;
	}

	@Override
	public JSONObject visitComItem(HttpReqRes httpReqRes) {
		String stItemName = httpReqRes.getParameter("stItemName");
		int selmQueryCount = selmQueryHisDao.getSelmQueryCount(stItemName);//?????????
		int analyticsCount = analyticsVisitedDetailDao.getAnalyticsCount(stItemName);//?????????
		//System.out.println("?????????"+analyticsCount);
		//????????? = ????????? >= ????????? ??? ????????????1+20%??? ????????????
		analyticsCount = (int) (selmQueryCount > analyticsCount ? (selmQueryCount+selmQueryCount*0.2) : analyticsCount);
		//System.out.println("?????????"+analyticsCount+"??????"+selmQueryCount);
		// ?????????????????????????????????   
		NumberFormat numberFormat = NumberFormat.getInstance();   
		// ???????????????????????????2???   
		numberFormat.setMaximumFractionDigits(2);   
		String result = numberFormat.format((float)selmQueryCount/(float)analyticsCount*100);
		JSONObject returnObj = new JSONObject();
		returnObj.put("visitor", analyticsCount);
		returnObj.put("transaction", selmQueryCount);
		returnObj.put("data", result+"%");
		return returnObj;
	}

	@Override
	public JSONObject itemPercentMonth(HttpReqRes httpReqRes) {
		String stItemName = httpReqRes.getParameter("stItemName");
		List<SelmQueryHis> selmQueryList = selmQueryHisDao.itemPercentMonth(stItemName);
		int sum = 0;
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			String number = selmQueryHis.getStExt2();
			sum+=Integer.parseInt(number);
		}
		for (SelmQueryHis selmQueryHis : selmQueryList) {
			String number = selmQueryHis.getStExt2();
			int num = Integer.parseInt(number);
			// ?????????????????????????????????   
			NumberFormat numberFormat = NumberFormat.getInstance();   
			// ???????????????????????????2???   
			numberFormat.setMaximumFractionDigits(2);   
			String result = numberFormat.format((float)num/(float)sum*100);
			selmQueryHis.setStExt2(result+"%");
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", selmQueryList.size());
		returnObj.put("data", selmQueryList);
		return returnObj;
	}
	/**
	 * ???????????????30????????????
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject areaThirtySelmQuery(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c = Calendar.getInstance();
	    String endDate = format.format(date);
	    c.setTime(date);
	    c.add(Calendar.MONTH, -1);
	    Date m = c.getTime();
	    String startDate = format.format(m);
	    conds.add(new Condition("SQH.DT_CREATE", Condition.OT_GREATER_EQUAL,
				startDate + " 00:00"));
	    conds.add(new Condition("SQH.DT_CREATE", Condition.OT_LESS_EQUAL,
				endDate + " 23:59"));
		String suffix = "GROUP BY IA.ST_DISTRICT ORDER BY case [ST_DISTRICT] WHEN '?????????' THEN 1 WHEN '?????????' THEN 2 WHEN '?????????' THEN 3 WHEN '?????????' THEN 4 WHEN '?????????' THEN 5 WHEN '?????????' THEN 6 WHEN '?????????' THEN 7 WHEN '?????????' THEN 8 WHEN '?????????' THEN 9 WHEN '?????????' THEN 10 WHEN '?????????' THEN 11 WHEN '?????????' THEN 12 WHEN '?????????' THEN 13 WHEN '?????????' THEN 14 WHEN '?????????' THEN 15 WHEN '????????????' THEN 16 END DESC";
		List selmQueryList = infopubAddressDao.areaThirtySelmQuery(conds,suffix);
		 JSONObject returnObj = new JSONObject();
			returnObj.put("recordsTotal", selmQueryList.size());
			returnObj.put("data", selmQueryList);
			return returnObj;
	}

	@Override
	public JSONObject areaDeviceCount(HttpReqRes httpReqRes) {
		String suffix = null;
			if(DB.getDatabaseName()=="MySQL"){
				suffix = "GROUP BY IA.ST_DISTRICT  ORDER BY field(ST_DISTRICT,'?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','?????????','????????????') DESC";
			}else{
				suffix = " GROUP BY IA.ST_DISTRICT  ORDER BY  case [ST_DISTRICT] WHEN '?????????' THEN 1 WHEN '?????????' THEN 2 WHEN '?????????' THEN 3 WHEN '?????????' THEN 4 WHEN '?????????' THEN 5 WHEN '?????????' THEN 6 WHEN '?????????' THEN 7 WHEN '?????????' THEN 8 WHEN '?????????' THEN 9 WHEN '?????????' THEN 10 WHEN '?????????' THEN 11 WHEN '?????????' THEN 12 WHEN '?????????' THEN 13 WHEN '?????????' THEN 14 WHEN '?????????' THEN 15 WHEN '????????????' THEN 16 END DESC";
		}
		List<InfopubAddress> infopubAddressList = infopubAddressDao.areaDeviceCount(suffix);
		int zhongXinSum = 0;
		int sheHuiSum = 0;
		int yanShenSum = 0;
		for (InfopubAddress infopubAddress : infopubAddressList) {
			int zhongXin = infopubAddress.getNmLat().intValue();//??????
			int sheHui = infopubAddress.getNmLng().intValue();//??????
			int yanShen = Integer.parseInt(infopubAddress.getStAddress());//??????
			 zhongXinSum += zhongXin;
			 sheHuiSum += sheHui;
			 yanShenSum += yanShen;
		}
		InfopubAddress infopubAddress = new InfopubAddress();
		infopubAddress.setStDistrict("??????");
		infopubAddress.setNmLat(new BigDecimal(zhongXinSum));//????????????????????????
		infopubAddress.setNmLng(new BigDecimal(sheHuiSum));//?????????
		infopubAddress.setStAddress(yanShenSum+"");//????????????????????????
		infopubAddressList.add(infopubAddress);
		JSONObject returnObj = new JSONObject();
		returnObj.put("recordsTotal", infopubAddressList.size());
		returnObj.put("data", infopubAddressList);
		return returnObj;
	}

	@Override
	public JSONObject todaySelmQuery(HttpReqRes httpReqRes) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("DT_CREATE",Condition.OT_GREATER_EQUAL,today+" 00:00:00"));
		conds.add(new Condition("DT_CREATE",Condition.OT_LESS_EQUAL,today+" 23:59:59"));
		BigDecimal sum = selmQueryHisDao.getTodaySelmQueryHis(conds);
		JSONObject json = new JSONObject();
		json.put("count", sum);
		return json;
	}

	@Override
	public JSONObject deviceAndHandCount(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		//????????????
		conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"????????????"));
		conds.add(new Condition("idt.ST_PARENT_TYPE_ID",Condition.OT_EQUAL,null));
		BigDecimal zwDeviceAmount = infopubDeviceInfoDao.getAmountByType(conds);
		BigDecimal zwAllRecord = selmQueryHisDao.getSelmQueryHisByType(conds);
		JSONObject obj2 = new JSONObject();
		obj2.put("deviceType","????????????");
		obj2.put("dCount",zwDeviceAmount);
		obj2.put("iCount",zwAllRecord);
		arr.add(obj2);
		//???????????????
		conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"??????"));
		conds.add(new Condition("idt.ST_PARENT_TYPE_ID",Condition.OT_EQUAL,null));
		BigDecimal shDeviceAmount = infopubDeviceInfoDao.getAmountByType(conds);
		BigDecimal shAllRecord = selmQueryHisDao.getSelmQueryHisByType(conds);
		JSONObject obj3 = new JSONObject();
		obj3.put("deviceType","???????????????");
		obj3.put("dCount",shDeviceAmount);
		obj3.put("iCount",shAllRecord);
		arr.add(obj3);
		
		//???????????????????????????
		//conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,""));
		//conds.add(new Condition("idt.ST_PARENT_TYPE_ID",Condition.OT_EQUAL,null));
		//BigDecimal deviceAmount = infopubDeviceInfoDao.getAmountByType(conds);
		//BigDecimal allRecord = selmQueryHisDao.getTodaySelmQueryHis(null);
		BigDecimal deviceAmount = zwDeviceAmount.add(shDeviceAmount);
		BigDecimal allRecord = zwAllRecord.add(shAllRecord);
		JSONObject obj1 = new JSONObject();
		obj1.put("deviceType","????????????");
		obj1.put("dCount",deviceAmount);
		obj1.put("iCount",allRecord);
		arr.add(obj1);
		
		obj.put("data", arr);
		return obj;
	}
	
	

	@Override
	public JSONObject itemOfMonth(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		/*Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);//?????????????????????
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);*/
		//Conditions conds = Conditions.newAndConditions();
		//conds.add(new Condition("DT_CREATE",Condition.OT_GREATER_EQUAL,time+" 00:00:00"));
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "group by ST_ITEM_NAME order by count(ST_ITEM_NAME) DESC limit 0,5";
		}else{
			suffix = "group by ST_ITEM_NAME order by count(ST_ITEM_NAME) DESC";
		}
		arr = selmQueryHisDao.itemOfMonth(null, suffix);
		
		obj.put("data", arr);
		return obj;
	}

	

	@Override
	public JSONObject socialCount(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"??????"));
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "group by idt.ST_TYPE_NAME order by count(idt.ST_TYPE_NAME) DESC limit 0,5";
		}else{
			suffix = "group by idt.ST_TYPE_NAME order by count(idt.ST_TYPE_NAME) DESC";
		}
		arr = selmQueryHisDao.socialCount(conds, suffix);
		obj.put("data", arr);
		return obj;
	}
	
	@Override
	public JSONObject areaItemOfMonth(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);//?????????????????????
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("sqh.DT_CREATE",Condition.OT_GREATER_EQUAL,time+" 00:00:00"));
		conds.add(new Condition("ia.NM_ORDER",Condition.OT_LESS_EQUAL,16));
		String suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by ia.NM_ORDER DESC";
		arr = selmQueryHisDao.areaItemCount1(conds, suffix);
		obj.put("data", arr);
		System.out.println(1);
		return obj;
		
	}

	@Override
	public JSONObject areaItemCount(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ia.NM_ORDER",Condition.OT_LESS_EQUAL,16));
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by count(ia.ST_AREA_NAME) DESC limit 0,5";
		}else{
			suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by count(ia.ST_AREA_NAME) DESC";
		}
		 
		arr = selmQueryHisDao.areaItemCount(conds, suffix);
		obj.put("data", arr);
		return obj;
	}

	@Override
	public JSONObject realTimeHand(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		int minutes1 = cal.get(Calendar.MINUTE)-5;
		int seconds = cal.get(Calendar.SECOND);
		String time = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		String time1 = year+"-"+month+"-"+day+" "+hours+":"+minutes1+":"+seconds;
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("DT_CREATE",Condition.OT_LESS_EQUAL,time));
		conds.add(new Condition("DT_CREATE",Condition.OT_GREATER_EQUAL,time1));
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "order by DT_CREATE DESC limit 0,20";
		}else{
			suffix = "order by DT_CREATE DESC";
		}
		List<SelmQueryHis> list = new LinkedList<SelmQueryHis>();
		list = selmQueryHisDao.realTimeHand(conds, suffix);
		String mac = "";
		InfopubDeviceInfo info = null;
		InfopubAddress ia = null;
		String addressId = "";
		JSONObject obj1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(SelmQueryHis emp : list){
			mac = emp.getStMachineId();
			info = infopubDeviceInfoDao.getMac(mac);
			if(null != info){
				addressId = info.getStAddressId();
				if("" != addressId){
					ia = infopubAddressDao.get(addressId);
					if(null != ia){
						if(null != emp.getStName()){
							//??????????????????
							String name = emp.getStName();
							//System.out.println(name);
							int len = name.length();
							name = name.substring(len-1, len);
							//System.out.println(name);
							for(int i = 0; i <= len-2; i++){
								name = "*"+name;
							}
							System.out.println(name);
							if(arr.size() < 3){
								obj1 = new JSONObject();
								obj1.put("itemName", emp.getStItemName());
						    	obj1.put("uName", name);
						    	emp.getDtCreate().getTime();
						    	obj1.put("date", sdf.format(new Date(emp.getDtCreate().getTime())));
						    	obj1.put("address", ia.getStLabel());
						    	obj1.put("area", ia.getStDistrict());
						    	obj1.put("state", "??????");
						    	arr.add(obj1);
							}else{
								continue;
							}
						}else{
							continue;
						}
						
					}else{
						continue;
					}
				}else{
					continue;
				}
			}else{
				continue;
			}
		}
		
		obj.put("data", arr);
		return obj;
	}

	@Override
	public JSONObject govDeviceCount(HttpReqRes httpReqRes) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"??????"));
		String suffix = "GROUP BY ia.ST_AREA_NAME";
		arr = infopubDeviceInfoDao.govDeviceCount(conds,suffix);
		obj.put("govCount",arr);
		return obj;
	}
	
}