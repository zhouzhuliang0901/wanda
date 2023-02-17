package com.wondersgroup.app.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;
import wfc.service.log.Log;


import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.app.bean.Oauth2ClientDevice;
import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.app.service.SelmItemService;
import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.business.dao.SelmQueryHisDao;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;

/**
 * 事项表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmItemServiceImpl implements SelmItemService {
	@Autowired
	private SelmItemDao selmItemDao;
	
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	@Autowired
	private SmsOrganDao smsOrganDao;
	
	


	/**
	 * 根据主键 {@link SelmItem#ST_ITEM_ID}获取事项表
	 * 
	 * @param stItemId
	 *            事项表主键 {@link SelmItem#ST_ITEM_ID}
	 * @return 事项表实例
	 */
	@Override
	public SelmItem get(String stItemId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		return selmItemDao.get(stItemId);
	}

	/**
	 * 查询事项表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项表列表
	 */
	@Override
	public JSONObject itemList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		String parentName = httpReqRes.getParameter("parentName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE,
					stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName));
		}
		if (parentName != null && !StringUtils.trim(parentName).isEmpty()) {
			SelmItem parentItem = selmItemDao.getByName(parentName);
			if(null != parentItem){
				conds.add(new Condition("ST_PARENT_ID", Condition.OT_EQUAL,parentItem.getStItemId()));
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
		String suffix = "ORDER BY NM_SORT";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NO " + orderType.toUpperCase();
			} else if ("stMainName".equals(orderName)) {
				suffix = "ORDER BY ST_MAIN_NAME " + orderType.toUpperCase();
			} else if ("nmSort".equals(orderName)) {
				suffix = "ORDER BY NM_SORT " + orderType.toUpperCase();
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
		
		PaginationArrayList<SelmItem> itemList = selmItemDao.query(conds, suffix, pageSize, currentPage);
		for (SelmItem selmItem : itemList) {
			String stOrganId = selmItem.getStOrganId();
			if(null != stOrganId){
				SmsOrgan smsOrgan = smsOrganDao.get(stOrganId);
				if(null != smsOrgan){
					selmItem.setStOrganId(smsOrgan.getStOrganName());
					
				}else{
					selmItem.setStOrganId("");
				}
				
			}
			String stPId = selmItem.getStParentId();
			if(null != stPId && StringUtils.isNotEmpty(stPId)){
				SelmItem pItem = selmItemDao.get(stPId);
				if(null != pItem){
					selmItem.setStParentId(pItem.getStMainName());
				}else{
					selmItem.setStParentId("");
				}
			}
			
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(itemList,
							SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", itemList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", itemList);
		return returnObj;
	}

	/**
	 * 根据主键 {@link SelmItem#ST_ITEM_ID}删除事项表
	 * 
	 * @param stItemId
	 *            事项表主键 {@link SelmItem#ST_ITEM_ID}
	 */
	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		if (stItemIdList == null) {
			String stItemId = httpReqRes.getRequest()
					.getParameter("stItemId");
			if (stItemId != null && !StringUtils.trimToEmpty(stItemId).isEmpty()) {
				selmItemDao.delete(stItemId);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		for (String stItemId : stItemIdList) {
			selmItemDao.delete(stItemId);
		}
	}

	/**
	 * 保存或更新事项表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项表实例
	 */
	@Override
	public SelmItem saveOrUpdate(HttpReqRes httpReqRes) {
		String stItemId = httpReqRes.getParameter(SelmItem.ST_ITEM_ID);
		SelmItem oldSelmItem = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty()) {
			oldSelmItem = selmItemDao.get(stItemId);
		}
		if (oldSelmItem == null) {// new
			SelmItem newSelmItem = new SelmItem();
			httpReqRes.toBean(newSelmItem);
			newSelmItem.setStItemId(UUID.randomUUID().toString());
			newSelmItem.setStState(new BigDecimal(1));
			newSelmItem.setDtCreate(new Timestamp(System.currentTimeMillis()));
			selmItemDao.add(newSelmItem);
			return newSelmItem;
		}else {// update
			httpReqRes.toBean(oldSelmItem);
			oldSelmItem.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			selmItemDao.update(oldSelmItem);
			return oldSelmItem;
		}
	}

	
	@Override
	public JSONObject noOCDevicelist(HttpReqRes httpReqRes) {
		String stItemId = httpReqRes.getParameter("stItemId");
		//String stItemNo = httpReqRes.getParameter("stItemNo");
		String stDeviceCode = httpReqRes.getParameter("deviceCode");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		JSONObject returnObj = new JSONObject();
		ArrayList<InfopubDeviceInfo> arrayList = new ArrayList<InfopubDeviceInfo>();
		Conditions conds = Conditions.newAndConditions();
		if (stItemId != null && !StringUtils.trim(stItemId).isEmpty()) {
			conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
					stItemId));
		}
		String suffix = "ORDER BY NM_ORDER";
		List<SelmDeviceItem> selmDeviceItemList = selmDeviceItemDao
				.query(conds, suffix);

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
		Conditions cond = Conditions.newAndConditions();
		String suffixs = "ORDER BY NM_ORDER";
		
		for (SelmDeviceItem selmDeviceItem : selmDeviceItemList) {
			String stDeviceId = selmDeviceItem.getStDeviceId();
			// Conditions cond = Conditions.newOrConditions();
			cond.add(new Condition("ST_DEVICE_ID", Condition.OT_UNEQUAL, stDeviceId));
		}
		
		if (!stPermission.equals("project_admin")) {
			if (stAreaId != null && !StringUtils.trim(stAreaId).isEmpty()) {
				cond.add(new Condition("ST_AREA_ID", Condition.OT_LIKE,
						stAreaId));
			}else {
				throw new NullPointerException("AreaId不能为空");
			}
		}
		/*if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			cond.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		}*/
		if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
			cond.add(new Condition("ST_DEVICE_CODE", Condition.OT_LIKE,
					stDeviceCode));
		}
		PaginationArrayList<InfopubDeviceInfo> query = infopubDeviceInfoDao.query(cond, suffixs,
				pageSize, currentPage);
		for (InfopubDeviceInfo infopubDeviceInfo : query) {
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.get(infopubDeviceInfo.getStTypeId());
			infopubDeviceInfo.setStTypeId(infopubDeviceType.getStTypeName());
			InfopubArea infopubArea = infopubAreaDao.get(infopubDeviceInfo.getStAreaId());
			if(infopubArea.getStAreaName()==null){
				infopubDeviceInfo.setStAreaId(null);
			}else{
			infopubDeviceInfo.setStAreaId(infopubArea.getStAreaName());
			}
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query, InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", query.size());
		returnObj.put("data", query);
		return returnObj;
	}
	
	
	@Override
	public JSONObject itemDevicelist(HttpReqRes httpReqRes) {
		String stItemId = httpReqRes.getParameter("stItemId1");
		String stDeviceCode = httpReqRes.getParameter("stDeviceCode1");
		JSONObject returnObj = new JSONObject();
		ArrayList<InfopubDeviceInfo> query = new ArrayList<InfopubDeviceInfo>();
		if (stDeviceCode != null && !StringUtils.trim(stDeviceCode).isEmpty()) {
			ArrayList<String> list = new ArrayList<String>();
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_DEVICE_CODE", Condition.OT_LIKE,
					stDeviceCode));

			List<InfopubDeviceInfo> deviceList = infopubDeviceInfoDao.query(
					conds, null);
			for (int i = 0; i < deviceList.size(); i++) {
				InfopubDeviceInfo infopubDeviceInfo = deviceList.get(i);
				String deviceId = infopubDeviceInfo.getStDeviceId();
				if (stItemId != null
						&& !StringUtils.trim(stItemId).isEmpty()) {
					if (deviceId != null
							&& !StringUtils.trim(deviceId).isEmpty()) {
						SelmDeviceItem selmDeviceItem = selmDeviceItemDao
								.get(stItemId, deviceId);
						if (selmDeviceItem != null) {
							list.add(deviceId);
						}
					}
				} else {
					throw new NullPointerException(
							"Parameter stOauth2Id cannot be null.");
				}
			}
			String suffixs = "ORDER BY NM_ORDER";

			int pageSize = Integer.MAX_VALUE / 2;
			int currentPage = 1;
			if (httpReqRes != null) {
				String length = httpReqRes.getParameter("length");
				if (length != null) {
					pageSize = Integer.valueOf(length);
				}
				if (httpReqRes.getParameter("start") != null) {
					int start = Integer.valueOf(httpReqRes
							.getParameter("start"));
					if (start != 0) {
						currentPage = Integer.valueOf(start) / pageSize + 1;
					}
				}
				Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
			}

			Conditions con = Conditions.newOrConditions();
			for (int i = 0; i < list.size(); i++) {
				String id = list.get(i);
				con.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, id));
			}
			if (list.size() > 0) {
				PaginationArrayList<InfopubDeviceInfo> query2 = infopubDeviceInfoDao
						.query(con, suffixs, pageSize, currentPage);
				query = query2;
			}
		} else {

			Conditions conds = Conditions.newAndConditions();
			if (stItemId != null && !StringUtils.trim(stItemId).isEmpty()) {
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						stItemId));
			} else {
				throw new NullPointerException(
						"Parameter stOauth2Id cannot be null.");
			}
			String suffix = "ORDER BY NM_ORDER";
			List<SelmDeviceItem> selmDeviceItemList = selmDeviceItemDao
					.query(conds, suffix);
			Conditions cond = Conditions.newOrConditions();
			for (int i = 0; i < selmDeviceItemList.size(); i++) {
				SelmDeviceItem selmDeviceItem = selmDeviceItemList.get(i);
				String stDeviceId = selmDeviceItem.getStDeviceId();
				cond.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,
						stDeviceId));

			}

			int pageSize = Integer.MAX_VALUE / 2;
			int currentPage = 1;
			if (httpReqRes != null) {
				String length = httpReqRes.getParameter("length");
				if (length != null) {
					pageSize = Integer.valueOf(length);
				}
				if (httpReqRes.getParameter("start") != null) {
					int start = Integer.valueOf(httpReqRes
							.getParameter("start"));
					if (start != 0) {
						currentPage = Integer.valueOf(start) / pageSize + 1;
					}
				}
				Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
			}

			String suffixs = "ORDER BY NM_ORDER";
			if (selmDeviceItemList.size() > 0) {
				query = infopubDeviceInfoDao.query(cond, suffixs, pageSize,
						currentPage);
			}
		}
		for (InfopubDeviceInfo infopubDeviceInfo : query) {
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.get(infopubDeviceInfo.getStTypeId());
			infopubDeviceInfo.setStTypeId(infopubDeviceType.getStTypeName());
			InfopubArea infopubArea = infopubAreaDao.get(infopubDeviceInfo.getStAreaId());
			if(infopubArea.getStAreaName()==null){
				infopubDeviceInfo.setStAreaId(null);
			}else{
			infopubDeviceInfo.setStAreaId(infopubArea.getStAreaName());
			}
		}

		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query, InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", query.size());
		returnObj.put("data", query);
		return returnObj;
	}

	@SuppressWarnings("all")
	@Override
	public JSONObject sqhDeviceItem(HttpReqRes httpReqRes) {
		//String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stDeviceId = "00-E2-69-2C-4B-73";
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		Conditions condItemNo = Conditions.newOrConditions();
		conds.add(new Condition("info.ST_DEVICE_MAC", Condition.OT_EQUAL,
				stDeviceId));
		ArrayList selmItemNo = new ArrayList();
		List<SelmItem> selmItemList = selmItemDao.queryItemNo(conds, null);
		for (SelmItem selmItem : selmItemList) {
			selmItemNo.add(selmItem.getStItemNo());
		}
		ArrayList selmQueryItemNo = new ArrayList();
		
		String suffix = "GROUP BY sqh.ST_ITEM_NO";
		List<SelmQueryHis> selmQueryHisList = selmQueryHisDao.queryItemNo(conds, suffix);
		for (SelmQueryHis selmQueryHis : selmQueryHisList) {
			selmQueryItemNo.add(selmQueryHis.getStItemNo());
		}
			for (Object itemNo : selmQueryItemNo) {
				if(selmItemNo.contains(itemNo)){
				    }else{
				    	//办件信息对应设备事项多出来的
				    	//if(itemNo!=null){
				    		condItemNo.add(new Condition("ST_ITEM_NO", Condition.OT_EQUAL,
					    			itemNo));
				    	//}
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
			
			conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,
					stDeviceId));
			//System.out.println(condItemNo+"+++++++++++++");
			//办件信息对应设备事项多出来的
	    	condItemNo.add(new Condition("ST_ITEM_NO", Condition.OT_EQUAL,
	    			"++++"));
			conds.add(condItemNo);
			List<SelmQueryHis> query = selmQueryHisDao.query(conds, null);
			
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
	public List<SelmItem> queryAllItem(HttpReqRes httpReqRes) {
		List<SelmItem> siList = selmItemDao.query(null, null);
		return siList;
	}

	@Override
	public int updateItem(String stExt3, String stItemNo) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_EXT3",Condition.OT_EQUAL,stExt3));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ST_ITEM_NO", stItemNo);
		int sum = 0;
		sum = selmItemDao.update(map, conds);
		return sum;
	}

	@Override
	public SelmItem sonItemSave(HttpReqRes httpReqRes) {
		SelmItem newSelmItem = new SelmItem();
		httpReqRes.toBean(newSelmItem);
		newSelmItem.setStItemId(UUID.randomUUID().toString());
		newSelmItem.setDtCreate(new Timestamp(System.currentTimeMillis()));
		selmItemDao.add(newSelmItem);
		return newSelmItem;
		
	}

}
