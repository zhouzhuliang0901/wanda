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

import com.wondersgroup.app.bean.Oauth2Client;
import com.wondersgroup.app.bean.Oauth2ClientDevice;
import com.wondersgroup.app.bean.Oauth2ClientItem;
import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.dao.Oauth2ClientDao;
import com.wondersgroup.app.dao.Oauth2ClientDeviceDao;
import com.wondersgroup.app.dao.Oauth2ClientItemDao;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.app.service.Oauth2ClientService;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.sms.user.service.SmsPasswordService;
import com.wondersgroup.statistics.bean.SelmStatistics;

/**
 * OAUTH2认证客户端业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class Oauth2ClientServiceImpl implements Oauth2ClientService {

	/**
	 * 根据主键 {@link Oauth2Client#ST_OAUTH2_ID}获取OAUTH2认证客户端
	 * 
	 * @param stOauth2Id
	 *            OAUTH2认证客户端主键 {@link Oauth2Client#ST_OAUTH2_ID}
	 * @return OAUTH2认证客户端实例
	 */
	@Override
	public Oauth2Client get(String stOauth2Id) {
		if (StringUtils.trimToEmpty(stOauth2Id).isEmpty())
			throw new NullPointerException(
					"Parameter stOauth2Id cannot be null.");
		return oauth2ClientDao.get(stOauth2Id);
	}

	/**
	 * 查询OAUTH2认证客户端列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return OAUTH2认证客户端列表
	 */
	@Override
	public PaginationArrayList<Oauth2Client> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(Oauth2Client.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return oauth2ClientDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link Oauth2Client#ST_OAUTH2_ID}删除OAUTH2认证客户端
	 * 
	 * @param stOauth2Id
	 *            OAUTH2认证客户端主键 {@link Oauth2Client#ST_OAUTH2_ID}
	 */
	@Override
	public void remove(HttpReqRes httpReqRes) {
		String[] stOauth2IdList = httpReqRes.getRequest().getParameterValues(
				"stOauth2Id[]");
		if (stOauth2IdList == null) {
			String stOauth2Id = httpReqRes.getRequest().getParameter(
					"stOauth2Id");
			if (stOauth2Id != null
					&& !StringUtils.trimToEmpty(stOauth2Id).isEmpty()) {
				oauth2ClientDao.delete(stOauth2Id);
				return;
			} else {
				throw new NullPointerException("设备ID不能为空");
			}
		}
		for (String stOauth2Id : stOauth2IdList) {
			oauth2ClientDao.delete(stOauth2Id);
		}
	}

	/*
	 * if (StringUtils.trimToEmpty(stOauth2Id).isEmpty()) throw new
	 * NullPointerException("Parameter stOauth2Id cannot be null.");
	 * oauth2ClientDao.delete(stOauth2Id); }
	 */

	/**
	 * 保存或更新OAUTH2认证客户端
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return OAUTH2认证客户端实例
	 */
	@Override
	public Oauth2Client saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// Oauth2Client.ST_OAUTH2_ID
		String stOauth2Id = wrapper.getParameter(Oauth2Client.ST_OAUTH2_ID);
		Oauth2Client oldOauth2Client = null;
		if (!StringUtils.trimToEmpty(stOauth2Id).isEmpty()) {
			oldOauth2Client = oauth2ClientDao.get(stOauth2Id);
		}
		if (oldOauth2Client == null) {// new
			Oauth2Client newOauth2Client = (Oauth2Client) t4r
					.toBean(Oauth2Client.class);
			newOauth2Client.setStOauth2Id(UUID.randomUUID().toString());
			passwordService.encryptPassword(newOauth2Client);
			oauth2ClientDao.add(newOauth2Client);
			return newOauth2Client;
		} else {// update
			oldOauth2Client = (Oauth2Client) t4r.toBean(oldOauth2Client,
					Oauth2Client.class);
			oauth2ClientDao.update(oldOauth2Client);
			return oldOauth2Client;
		}
	}

	@Autowired
	private SmsPasswordService passwordService;
	@Autowired
	private Oauth2ClientDao oauth2ClientDao;

	@Override
	public JSONObject oauth2ClientList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();

		String stInterfaceUser = httpReqRes.getParameter("stInterfaceUser");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");

		if (stInterfaceUser != null
				&& !StringUtils.trim(stInterfaceUser).isEmpty()) {
			conds.add(new Condition("ST_INTERFACE_USER", Condition.OT_LIKE,
					stInterfaceUser));
		}
		String suffix = "ORDER BY ST_DESC";
		if (orderName != null) {
			if ("stInterfaceUser".equals(orderName)) {
				suffix = "ORDER BY ST_INTERFACE_USER "
						+ orderType.toUpperCase();
			} else if ("nmCount".equals(orderName)) {
				suffix = "ORDER BY ST_DESC " + orderType.toUpperCase();
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
		List<Oauth2Client> oauth2ClientList = oauth2ClientDao.query(conds,
				suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(oauth2ClientList, Oauth2Client.class))
					.getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", oauth2ClientList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", oauth2ClientList);
		return returnObj;
	}

	// ---------------------------------------------------------------------------
	@Autowired
	private Oauth2ClientItemDao oauth2ClientItemDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private Oauth2ClientDeviceDao oauth2ClientDeviceDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	
	@Override
	public JSONObject oauth2ClientItemlist(HttpReqRes httpReqRes) {
		String stOauth2Id = httpReqRes.getParameter("oauth2Id1");
		String stItemNo = httpReqRes.getParameter("stItemNo1");
		String stMainName = httpReqRes.getParameter("stMainName1");
		JSONObject returnObj = new JSONObject();
		ArrayList<SelmItem> query = new ArrayList<SelmItem>();
		if ((stItemNo != null && !StringUtils.trim(stItemNo).isEmpty())
				|| (stMainName != null && !StringUtils.trim(stMainName)
						.isEmpty())) {
			ArrayList<String> list = new ArrayList<String>();
			Conditions conds = Conditions.newAndConditions();
			if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
				conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE,
						stItemNo));
			}
			if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
				conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
						stMainName));
			}
		
			List<SelmItem> itemList = selmItemDao.query(conds, null);
			for (int i = 0; i < itemList.size(); i++) {
				SelmItem selmItem = itemList.get(i);
				String itemId = selmItem.getStItemId();
				if (stOauth2Id != null && !StringUtils.trim(stOauth2Id).isEmpty()) {
					if (itemId != null && !StringUtils.trim(itemId).isEmpty()) {
						Oauth2ClientItem oauth2ClientItem = oauth2ClientItemDao.get(stOauth2Id, itemId);
						if (oauth2ClientItem != null) {
							list.add(itemId);
						}
					}
				}else {
					throw new NullPointerException(
							"Parameter stOauth2Id cannot be null.");
				}
			}
			

			String suffixs = "ORDER BY NM_SORT";

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
				con.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						id));
			}
			if (list.size() > 0) {
				PaginationArrayList<SelmItem> query2 = selmItemDao.query(con, suffixs, pageSize, currentPage);
				query = query2;
			}
		} else {

			Conditions conds = Conditions.newAndConditions();
			if (stOauth2Id != null && !StringUtils.trim(stOauth2Id).isEmpty()) {
				conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL,
						stOauth2Id));
			} else {
				throw new NullPointerException(
						"Parameter stOauth2Id cannot be null.");
			}
			String suffix = "ORDER BY NM_ORDER";
			List<Oauth2ClientItem> Oauth2ClientItemList = oauth2ClientItemDao
					.query(conds, suffix);
			
			Conditions cond = Conditions.newOrConditions();
			for (int i = 0; i < Oauth2ClientItemList.size(); i++) {
				Oauth2ClientItem oauth2ClientItem = Oauth2ClientItemList.get(i);
				String stItemId = oauth2ClientItem.getStItemId();
				cond.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						stItemId));
				
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
			
			String suffixs = "ORDER BY NM_SORT";
			if (Oauth2ClientItemList.size() > 0) {
				query = selmItemDao.query(cond, suffixs, pageSize, currentPage);
			}
		}

		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query, SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", query.size());
		returnObj.put("data", query);
		return returnObj;
	}

	@Override
	public JSONObject noOCItemlist(HttpReqRes httpReqRes) {
		String stOauth2Id = httpReqRes.getParameter("oauth2Id");
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		JSONObject returnObj = new JSONObject();
		ArrayList<SelmItem> arrayList = new ArrayList<SelmItem>();
		Conditions conds = Conditions.newAndConditions();
		if (stOauth2Id != null && !StringUtils.trim(stOauth2Id).isEmpty()) {
			conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL,
					stOauth2Id));
		}
		String suffix = "ORDER BY NM_ORDER";
		List<Oauth2ClientItem> Oauth2ClientItemList = oauth2ClientItemDao
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
		String suffixs = "ORDER BY NM_SORT";
		for (Oauth2ClientItem oauth2ClientItem : Oauth2ClientItemList) {
			String stItemId = oauth2ClientItem.getStItemId();
			// Conditions cond = Conditions.newOrConditions();
			cond.add(new Condition("ST_ITEM_ID", Condition.OT_UNEQUAL, stItemId));
		}
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			cond.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			cond.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName));
		}
		PaginationArrayList<SelmItem> query = selmItemDao.query(cond, suffixs,
				pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query, SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", query.size());
		returnObj.put("data", query);
		return returnObj;
	}

	@Override
	public JSONObject oauth2ClientDevicelist(HttpReqRes httpReqRes) {
		String stOauth2Id = httpReqRes.getParameter("oauth2Id1");
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
				if (stOauth2Id != null
						&& !StringUtils.trim(stOauth2Id).isEmpty()) {
					if (deviceId != null
							&& !StringUtils.trim(deviceId).isEmpty()) {
						Oauth2ClientDevice oauth2ClientDevice = oauth2ClientDeviceDao
								.get(stOauth2Id, deviceId);
						if (oauth2ClientDevice != null) {
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
			if (stOauth2Id != null && !StringUtils.trim(stOauth2Id).isEmpty()) {
				conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL,
						stOauth2Id));
			} else {
				throw new NullPointerException(
						"Parameter stOauth2Id cannot be null.");
			}
			String suffix = "ORDER BY NM_ORDER";
			List<Oauth2ClientDevice> oauth2ClientDeviceList = oauth2ClientDeviceDao
					.query(conds, suffix);
			Conditions cond = Conditions.newOrConditions();
			for (int i = 0; i < oauth2ClientDeviceList.size(); i++) {
				Oauth2ClientDevice oauth2ClientDevice = oauth2ClientDeviceList.get(i);
				String stDeviceId = oauth2ClientDevice.getStDeviceId();
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
			if (oauth2ClientDeviceList.size() > 0) {
				query = infopubDeviceInfoDao.query(cond, suffixs, pageSize,
						currentPage);
			}
		}
		for (InfopubDeviceInfo infopubDeviceInfo : query) {
			InfopubDeviceType infopubDeviceType = infopubDeviceTypeDao.get(infopubDeviceInfo.getStTypeId());
			infopubDeviceInfo.setStTypeId(infopubDeviceType.getStTypeName());
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
	public JSONObject noOCDevicelist(HttpReqRes httpReqRes) {
		String stOauth2Id = httpReqRes.getParameter("oauth2Id");
		//String stItemNo = httpReqRes.getParameter("stItemNo");
		String stDeviceCode = httpReqRes.getParameter("deviceCode");
		String stAreaId = httpReqRes.getParameter("stAreaId");
		String stPermission = httpReqRes.getParameter("stPermission");
		JSONObject returnObj = new JSONObject();
		ArrayList<InfopubDeviceInfo> arrayList = new ArrayList<InfopubDeviceInfo>();
		Conditions conds = Conditions.newAndConditions();
		if (stOauth2Id != null && !StringUtils.trim(stOauth2Id).isEmpty()) {
			conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL,
					stOauth2Id));
		}
		String suffix = "ORDER BY NM_ORDER";
		List<Oauth2ClientDevice> oauth2ClientDeviceList = oauth2ClientDeviceDao
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
		for (Oauth2ClientDevice oauth2ClientDevice : oauth2ClientDeviceList) {
			String stDeviceId = oauth2ClientDevice.getStDeviceId();
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
	public JSONObject nodeviceOCItemlist(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		JSONObject returnObj = new JSONObject();
		ArrayList<SelmItem> arrayList = new ArrayList<SelmItem>();
		Conditions conds = Conditions.newAndConditions();
		if (stDeviceId != null && !StringUtils.trim(stDeviceId).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,
					stDeviceId));
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
		String suffixs = "ORDER BY NM_SORT";
		for (SelmDeviceItem selmDeviceItem : selmDeviceItemList) {
			String stItemId = selmDeviceItem.getStItemId();
			// Conditions cond = Conditions.newOrConditions();
			cond.add(new Condition("ST_ITEM_ID", Condition.OT_UNEQUAL, stItemId));
		}
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			cond.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			cond.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
					stMainName));
		}
		PaginationArrayList<SelmItem> query = selmItemDao.query(cond, suffixs,
				pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query, SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", query.size());
		returnObj.put("data", query);
		return returnObj;
	}
	
	
	
	@Override
	public JSONObject deviceoauth2ClientItemlist(HttpReqRes httpReqRes) {
		String stDeviceId1 = httpReqRes.getParameter("stDeviceId1");
		String stItemNo = httpReqRes.getParameter("stItemNo1");
		String stMainName = httpReqRes.getParameter("stMainName1");
		JSONObject returnObj = new JSONObject();
		ArrayList<SelmItem> query = new ArrayList<SelmItem>();
		if ((stItemNo != null && !StringUtils.trim(stItemNo).isEmpty())
				|| (stMainName != null && !StringUtils.trim(stMainName)
						.isEmpty())) {
			ArrayList<String> list = new ArrayList<String>();
			Conditions conds = Conditions.newAndConditions();
			if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
				conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE,
						stItemNo));
			}
			if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
				conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE,
						stMainName));
			}
		
			List<SelmItem> itemList = selmItemDao.query(conds, null);
			for (int i = 0; i < itemList.size(); i++) {
				SelmItem selmItem = itemList.get(i);
				String itemId = selmItem.getStItemId();
				if (stDeviceId1 != null && !StringUtils.trim(stDeviceId1).isEmpty()) {
					if (itemId != null && !StringUtils.trim(itemId).isEmpty()) {
						SelmDeviceItem selmDeviceItem = selmDeviceItemDao.getdevice(stDeviceId1, itemId);
						if (selmDeviceItem != null) {
							list.add(itemId);
						}
					}
				}else {
					throw new NullPointerException(
							"Parameter stOauth2Id cannot be null.");
				}
			}
			

			String suffixs = "ORDER BY NM_SORT";

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
				con.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						id));
			}
			if (list.size() > 0) {
				PaginationArrayList<SelmItem> query2 = selmItemDao.query(con, suffixs, pageSize, currentPage);
				query = query2;
			}
		} else {

			Conditions conds = Conditions.newAndConditions();
			if (stDeviceId1 != null && !StringUtils.trim(stDeviceId1).isEmpty()) {
				conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,
						stDeviceId1));
			} else {
				throw new NullPointerException(
						"Parameter stOauth2Id cannot be null.");
			}
			String suffix = "ORDER BY NM_ORDER";
			List<SelmDeviceItem> selmDeviceItemList = selmDeviceItemDao
					.query(conds, suffix);
			
			Conditions cond = Conditions.newOrConditions();
			for (int i = 0; i < selmDeviceItemList.size(); i++) {
				SelmDeviceItem oauth2ClientItem = selmDeviceItemList.get(i);
				String stItemId = oauth2ClientItem.getStItemId();
				cond.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						stItemId));
				
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
			
			String suffixs = "ORDER BY NM_SORT";
			if (selmDeviceItemList.size() > 0) {
				query = selmItemDao.query(cond, suffixs, pageSize, currentPage);
			}
		}

		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(query, SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", query.size());
		returnObj.put("data", query);
		return returnObj;
	}
}
