package com.wondersgroup.serverApply.service.impl;

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

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.bean.SelmItemLink;
import com.wondersgroup.app.dao.Oauth2ClientDeviceDao;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.app.dao.SelmItemLinkDao;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.serverApply.dao.SelmServerItemDao;
import com.wondersgroup.serverApply.service.SelmServerItemService;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

/**
 * 服务关联事项业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmServerItemServiceImpl implements SelmServerItemService {
	
	
	@Autowired
	private SelmServerItemDao selmServerItemDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private SmsUserDao smsUserDao;
	@Autowired
	private SmsOrganDao smsOrganDao;
	@Autowired
	private Oauth2ClientDeviceDao oauth2ClientDeviceDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	@Autowired
	private SelmItemLinkDao selmItemLinkDao;
	/**
	 * 根据主键 {@link SelmServerItem#ST_LINKS_ID}获取服务关联事项
	 * 
	 * @param stLinksId
	 *            服务关联事项主键 {@link SelmServerItem#ST_LINKS_ID}
	 * @return 服务关联事项实例
	 */
	@Override
	public SelmServerItem get(String stLinksId) {
		if (StringUtils.trimToEmpty(stLinksId).isEmpty())
			throw new NullPointerException("Parameter stLinksId cannot be null.");
		return selmServerItemDao.get(stLinksId);
	}

	/**
	 * 查询服务关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务关联事项列表
	 */
	@Override
	public PaginationArrayList<SelmServerItem> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmServerItem.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmServerItemDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmServerItem#ST_LINKS_ID}删除服务关联事项
	 * 
	 * @param stLinksId
	 *            服务关联事项主键 {@link SelmServerItem#ST_LINKS_ID}
	 */
	@Override
	public void remove(String stLinksId) {
		if (StringUtils.trimToEmpty(stLinksId).isEmpty())
			throw new NullPointerException("Parameter stLinksId cannot be null.");
		selmServerItemDao.delete(stLinksId);
	}

	/**
	 * 保存或更新服务关联事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 服务关联事项实例
	 */
	@Override
	public SelmServerItem saveOrUpdate(HttpReqRes httpReqRes) {
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		SelmServerItem newSelmServerItem = new SelmServerItem();
		if(stItemIdList==null){
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stApplyId = httpReqRes.getRequest().getParameter(
						"stApplyId");
				SelmServerItem oldSelmServerItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stApplyId).isEmpty()) {
					oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId);
				}
				if (oldSelmServerItem == null) {// new
					//SelmServerItem newSelmServerItem = new SelmServerItem();
					httpReqRes.toBean(newSelmServerItem);
					newSelmServerItem.setStLinksId(UUID.randomUUID().toString());
					newSelmServerItem.setStApplyId(stApplyId);
					newSelmServerItem.setStItemId(stItemId);
					SelmItem selmItem = selmItemDao.get(stItemId);
					newSelmServerItem.setStItemNo(selmItem.getStItemNo());
					newSelmServerItem.setStItemName(selmItem.getStMainName());
					newSelmServerItem.setStOrganId(selmItem.getStOrganId());
					selmServerItemDao.add(newSelmServerItem);
					return newSelmServerItem;
				} 
		}else{
			for (String stItemId : stItemIdList) {
				String stApplyId = httpReqRes.getRequest().getParameter(
						"stApplyId");
				SelmServerItem oldSelmServerItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stApplyId).isEmpty()) {
					oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId);
				}
				if (oldSelmServerItem == null) {// new
					//SelmServerItem newSelmServerItem = new SelmServerItem();
					httpReqRes.toBean(newSelmServerItem);
					newSelmServerItem.setStLinksId(UUID.randomUUID().toString());
					newSelmServerItem.setStApplyId(stApplyId);
					newSelmServerItem.setStItemId(stItemId);
					SelmItem selmItem = selmItemDao.get(stItemId);
					newSelmServerItem.setStItemNo(selmItem.getStItemNo());
					newSelmServerItem.setStItemName(selmItem.getStMainName());
					newSelmServerItem.setStOrganId(selmItem.getStOrganId());
					selmServerItemDao.add(newSelmServerItem);
				}
			}
		}
		return newSelmServerItem;
	}

	/**
	 * 查询服务关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务关联事项列表
	 */
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		String stUserId = httpReqRes.getParameter("stUserId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stItemName = httpReqRes.getParameter("stMainName");
		String stOrganId = httpReqRes.getParameter("stOrganId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stApplyId != null && !StringUtils.trim(stApplyId).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
		}
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, stItemNo));
		}
		if (stItemName != null && !StringUtils.trim(stItemName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE, stItemName));
		}
		if (stOrganId != null && !StringUtils.trim(stOrganId).isEmpty()) {
			Conditions condOrgan = Conditions.newOrConditions();
			Conditions cond = Conditions.newOrConditions();
			cond.add(new Condition("ST_ORGAN_NAME", Condition.OT_LIKE,
					stOrganId));
			List<SmsOrgan> smsOrganList = smsOrganDao.query(cond, null);
			for (SmsOrgan smsOrgan : smsOrganList) {
				condOrgan.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL,
						smsOrgan.getStOrganId()));
			}
			conds.add(condOrgan);
		}
		if (stPermission != null && !StringUtils.trim(stPermission).isEmpty()&&!stPermission.equals("") ) {
			if (!stPermission.equals("project_admin")) {
				if (stUserId != null && !StringUtils.trim(stUserId).isEmpty()) {
					SmsUser smsUser = smsUserDao.get(stUserId);
					conds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL, smsUser.getStOrganId()));
				}
			}
		}else{
			if (stUserId != null && !StringUtils.trim(stUserId).isEmpty()) {
				SmsUser smsUser = smsUserDao.get(stUserId);
				conds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL, smsUser.getStOrganId()));
			}
		}
		
		String suffix = "ORDER BY ST_ITEM_NAME";
		if (orderName != null) {
			if ("stItemName".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NAME " + orderType.toUpperCase();
			}else if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NO " + orderType.toUpperCase();
			}else if ("stOrganId".equals(orderName)) {
				suffix = "ORDER BY ST_ORGAN_ID " + orderType.toUpperCase();
			}else if ("nmPass".equals(orderName)) {
				suffix = "ORDER BY NM_PASS " + orderType.toUpperCase();
			}else if ("dtAudit".equals(orderName)) {
				suffix = "ORDER BY DT_AUDIT " + orderType.toUpperCase();
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
		List<SelmServerItem> selmServerItemList = selmServerItemDao.query(conds, suffix, pageSize, currentPage);
		for (SelmServerItem selmServerItem : selmServerItemList) {
			if(selmServerItem.getNmPass()==null){
				selmServerItem.setNmPass(new BigDecimal(3));
			}
			SmsOrgan smsOrgan = smsOrganDao.get(selmServerItem.getStOrganId());
			selmServerItem.setStOrganId(smsOrgan.getStOrganName());
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmServerItemList,
							SelmServerItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmServerItemList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmServerItemList);
		return returnObj;
		
	}

	@Override
	public SelmServerItem CheckSave(HttpReqRes httpReqRes) {
		String smsUserId = httpReqRes.getRequest().getParameter("stUserId");
		String stLinksId = httpReqRes.getRequest().getParameter("stLinksId");
		String nmPass = httpReqRes.getRequest().getParameter("nmPass");
		SmsUser smsUser = smsUserDao.get(smsUserId);
		SelmServerItem selmServerItem = selmServerItemDao.getLinksId(stLinksId);
		if(selmServerItem!=null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ST_AUDIT_USER_ID", smsUserId);
			map.put("ST_AUDIT_USER_NAME", smsUser.getStUserName());
			map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
			map.put("NM_PASS", nmPass);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_LINKS_ID", Condition.OT_EQUAL, stLinksId));
			selmServerItemDao.update(map, conds);
		}
		return selmServerItem;
	}
	
	
	
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		Conditions conds = Conditions.newAndConditions();
		String stApplyId = httpReqRes.getRequest()
				.getParameter("stApplyId");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		if (stItemIdList == null) {
			String stItemId = httpReqRes.getRequest()
					.getParameter("stItemId");
			if (stApplyId != null
					&& !StringUtils.trimToEmpty(stApplyId).isEmpty()) {
				conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, stItemId));
				selmServerItemDao.delete(conds);
				return;
			} else {
				throw new NullPointerException("申请ID不能为空");
			}
		}
		for (String itemId : stItemIdList) {
			conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
			conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, itemId));
			selmServerItemDao.delete(conds);
		}
	}
	
	
	@Override
	public JSONObject nodeviceOCItemlist(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stDeviceArray = httpReqRes.getParameter("stDeviceArray");
		String[] deviceArray = stDeviceArray.split(",");
		//System.out.println("222:"+deviceArray.length);
		//System.out.println("222"+Arrays.toString(deviceArray));
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		String stOrganName = httpReqRes.getParameter("stOrganName");
		JSONObject returnObj = new JSONObject();
		ArrayList<SelmItem> arrayList = new ArrayList<SelmItem>();
		Conditions conds = Conditions.newAndConditions();
		List<SelmDeviceItem> selmDeviceItemList = new ArrayList<SelmDeviceItem>();
		String suffix = "ORDER BY NM_ORDER";
		
		if(deviceArray.length >= 2){
			selmDeviceItemList.add(new SelmDeviceItem());
		}else if(deviceArray.length == 1 && !StringUtils.trim(deviceArray[0]).isEmpty()){
			stDeviceId = deviceArray[0];
			//System.out.println("数组长度："+deviceArray.length);
			//System.out.println("deviceArray[0]:"+deviceArray[0]);
		}
		if (stDeviceId != null && !StringUtils.trim(stDeviceId).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,
					stDeviceId));
			selmDeviceItemList = selmDeviceItemDao
					.query(conds, suffix);
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
		if (stOrganName != null && !StringUtils.trim(stOrganName).isEmpty()) {
			SmsOrgan organ = smsOrganDao.getByName(stOrganName);
			cond.add(new Condition("ST_ORGAN_ID", Condition.OT_LIKE, organ.getStOrganId()));
		}
		PaginationArrayList<SelmItem> query = selmItemDao.query(cond, suffixs,
				pageSize, currentPage);
		
		for(SelmItem temp : query){
			String organId = temp.getStOrganId();
			SmsOrgan organ = smsOrganDao.get(organId);
			temp.setStExt1(organ.getStOrganName());
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
	public JSONObject deviceoauth2ClientItemlist(HttpReqRes httpReqRes) {
		String stItemNo = httpReqRes.getParameter("stItemNo1");
		String stMainName = httpReqRes.getParameter("stMainName1");
		String stOrganName = httpReqRes.getParameter("stOrganName1");
		String stDeviceId1 = httpReqRes.getParameter("stDeviceId1");
		String stDeviceArray = httpReqRes.getParameter("stDeviceArray1");
		String stApplyId1 = httpReqRes.getParameter("stApplyId1");
		String[] deviceArray = stDeviceArray.split(",");
		if(!StringUtils.trim(deviceArray[0]).isEmpty()){
			stDeviceId1 = deviceArray[0];
		}
		Conditions conds = Conditions.newAndConditions();
		if (stItemNo != null && !StringUtils.trim(stItemNo).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE,stItemNo));
		}
		if (stMainName != null && !StringUtils.trim(stMainName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE,stMainName));
		}
		if (stOrganName != null && !StringUtils.trim(stOrganName).isEmpty()) {
			conds.add(new Condition("ST_ORGAN_ID", Condition.OT_LIKE, stOrganName));
		}
		if (stApplyId1 != null && !StringUtils.trim(stApplyId1).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ID", Condition.OT_LIKE, stApplyId1));
		}
		if (stDeviceId1 != null && !StringUtils.trim(stDeviceId1).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_ID", Condition.OT_LIKE, stDeviceId1));
		}
			
		String suffix = "ORDER BY DT_AUDIT DESC";

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
			
		List<SelmServerItem> ssi = selmServerItemDao.query(conds, suffix, pageSize, currentPage);
		JSONObject returnObj = new JSONObject();	
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(ssi, SelmServerItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		returnObj.put("recordsFiltered", total);
		returnObj.put("recordsTotal", ssi.size());
		returnObj.put("data", ssi);
		return returnObj;
	}
	
	@Override
	public int saveOrUpdatePower(HttpReqRes httpReqRes) {
		String stDeviceArray = httpReqRes.getParameter("stDeviceArray");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		String[] stDeviceIdList = stDeviceArray.split(",");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues("stItemId[]");
		int flag = 0;
		if(stItemIdList==null){
			if (stDeviceIdList.length == 1 && StringUtils.trim(stDeviceIdList[0]).isEmpty()) {
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
				
				SelmServerItem oldSelmServerItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
				}
				if (oldSelmServerItem == null) {// new
					//插入新的SelmServerItem
					SelmServerItem selmServerItem = new SelmServerItem();
					selmServerItem.setStLinksId(UUID.randomUUID().toString());
					selmServerItem.setStApplyId(stApplyId);
					selmServerItem.setStItemId(stItemId);
					selmServerItem.setStDeviceId(stDeviceId);
					selmServerItem.setNmStatus(new BigDecimal(0));
					selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
					Conditions con = Conditions.newAndConditions();
					con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
					List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
					if(null != itemList && itemList.size()>0){
						SelmItem si = itemList.get(0);
						selmServerItem.setStItemName(si.getStMainName());
						selmServerItem.setStItemNo(si.getStItemNo());
						selmServerItem.setStOrganId(si.getStOrganId());	
					}
					selmServerItemDao.add(selmServerItem);
					flag +=1;
				} 
				
			}else if(stDeviceIdList.length == 1 && !StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stDeviceId = stDeviceIdList[0];
				SelmServerItem oldSelmServerItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
				}
				if (oldSelmServerItem == null) {// new
					//插入新的SelmServerItem
					SelmServerItem selmServerItem = new SelmServerItem();
					selmServerItem.setStLinksId(UUID.randomUUID().toString());
					selmServerItem.setStApplyId(stApplyId);
					selmServerItem.setStItemId(stItemId);
					selmServerItem.setStDeviceId(stDeviceId);
					selmServerItem.setNmStatus(new BigDecimal(0));
					selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
					Conditions con = Conditions.newAndConditions();
					con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
					List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
					if(null != itemList && itemList.size()>0){
						SelmItem si = itemList.get(0);
						selmServerItem.setStItemName(si.getStMainName());
						selmServerItem.setStItemNo(si.getStItemNo());
						selmServerItem.setStOrganId(si.getStOrganId());	
					}
					selmServerItemDao.add(selmServerItem);
					flag +=1;
				}
			}else if(stDeviceIdList.length >= 2){
				for (String stDeviceId : stDeviceIdList) {
					String stItemId = httpReqRes.getRequest().getParameter("stItemId");
					SelmServerItem oldSelmServerItem = null;
					if (!StringUtils.trimToEmpty(stItemId).isEmpty()
							&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
						oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
					}
					if (oldSelmServerItem == null) {// new
						//插入新的SelmServerItem
						SelmServerItem selmServerItem = new SelmServerItem();
						selmServerItem.setStLinksId(UUID.randomUUID().toString());
						selmServerItem.setStApplyId(stApplyId);
						selmServerItem.setStItemId(stItemId);
						selmServerItem.setStDeviceId(stDeviceId);
						selmServerItem.setNmStatus(new BigDecimal(0));
						selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
						Conditions con = Conditions.newAndConditions();
						con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
						List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
						if(null != itemList && itemList.size()>0){
							SelmItem si = itemList.get(0);
							selmServerItem.setStItemName(si.getStMainName());
							selmServerItem.setStItemNo(si.getStItemNo());
							selmServerItem.setStOrganId(si.getStOrganId());	
						}
						selmServerItemDao.add(selmServerItem);
						flag +=1;
					} 
				}
			}
		}else{
			if(stDeviceIdList.length == 1 && StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				for (String stItemId : stItemIdList) {
					String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
					SelmServerItem oldSelmServerItem = null;
					if (!StringUtils.trimToEmpty(stItemId).isEmpty()
							&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
						oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
					}
					if (oldSelmServerItem == null) {// new
						//插入新的SelmServerItem
						SelmServerItem selmServerItem = new SelmServerItem();
						selmServerItem.setStLinksId(UUID.randomUUID().toString());
						selmServerItem.setStApplyId(stApplyId);
						selmServerItem.setStItemId(stItemId);
						selmServerItem.setStDeviceId(stDeviceId);
						selmServerItem.setNmStatus(new BigDecimal(0));
						selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
						Conditions con = Conditions.newAndConditions();
						con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
						List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
						if(null != itemList && itemList.size()>0){
							SelmItem si = itemList.get(0);
							selmServerItem.setStItemName(si.getStMainName());
							selmServerItem.setStItemNo(si.getStItemNo());
							selmServerItem.setStOrganId(si.getStOrganId());	
						}
						selmServerItemDao.add(selmServerItem);
						flag +=1;
					}
				}
			}else if(stDeviceIdList.length == 1 && !StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				for (String stItemId : stItemIdList) {
					String stDeviceId = stDeviceIdList[0];
					SelmServerItem oldSelmServerItem = null;
					if (!StringUtils.trimToEmpty(stItemId).isEmpty()
							&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
						oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
					}
					if (oldSelmServerItem == null) {// new
						//插入新的SelmServerItem
						SelmServerItem selmServerItem = new SelmServerItem();
						selmServerItem.setStLinksId(UUID.randomUUID().toString());
						selmServerItem.setStApplyId(stApplyId);
						selmServerItem.setStItemId(stItemId);
						selmServerItem.setStDeviceId(stDeviceId);
						selmServerItem.setNmStatus(new BigDecimal(0));
						selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
						Conditions con = Conditions.newAndConditions();
						con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
						List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
						if(null != itemList && itemList.size()>0){
							SelmItem si = itemList.get(0);
							selmServerItem.setStItemName(si.getStMainName());
							selmServerItem.setStItemNo(si.getStItemNo());
							selmServerItem.setStOrganId(si.getStOrganId());	
						}
						selmServerItemDao.add(selmServerItem);
						flag +=1;
					}
				}
			}else if(stDeviceIdList.length >= 2){
				for(String stDeviceId : stDeviceIdList){
					for (String stItemId : stItemIdList) {
						SelmServerItem oldSelmServerItem = null;
						if (!StringUtils.trimToEmpty(stItemId).isEmpty()
								&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
							oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
						}
						if (oldSelmServerItem == null) {// new
							//插入新的SelmServerItem
							SelmServerItem selmServerItem = new SelmServerItem();
							selmServerItem.setStLinksId(UUID.randomUUID().toString());
							selmServerItem.setStApplyId(stApplyId);
							selmServerItem.setStItemId(stItemId);
							selmServerItem.setStDeviceId(stDeviceId);
							selmServerItem.setNmStatus(new BigDecimal(0));
							selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
							Conditions con = Conditions.newAndConditions();
							con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
							List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
							if(null != itemList && itemList.size()>0){
								SelmItem si = itemList.get(0);
								selmServerItem.setStItemName(si.getStMainName());
								selmServerItem.setStItemNo(si.getStItemNo());
								selmServerItem.setStOrganId(si.getStOrganId());	
							}
							selmServerItemDao.add(selmServerItem);
							flag +=1;
						}
					}
				}
			}

		}
		return flag;

	}
	
	
	@Override
	public void removePower(HttpReqRes httpReqRes) {
		String stDeviceArray = httpReqRes.getParameter("stDeviceArray");
		String[] stDeviceIdList = stDeviceArray.split(",");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues(
				"stItemId[]");
		
		if(stItemIdList==null){
			if (stDeviceIdList.length == 1 && StringUtils.trim(stDeviceIdList[0]).isEmpty()) {
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
				if(!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()){
					selmServerItemDao.delete(stItemId, stDeviceId, stApplyId);
					return;
				}
			}else if(stDeviceIdList.length == 1 && !StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stDeviceId = stDeviceIdList[0];
				if(!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()){
					selmServerItemDao.delete(stItemId, stDeviceId, stApplyId);
					return;
				}
			}else if(stDeviceIdList.length >= 2){
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				for (String stDeviceId : stDeviceIdList) {
					if(!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()){
						selmServerItemDao.delete(stItemId, stDeviceId, stApplyId);
					}
				}
			}
		}else{
			if (stDeviceIdList.length == 1 && StringUtils.trim(stDeviceIdList[0]).isEmpty()) {
				String stDeviceId = httpReqRes.getRequest().getParameter(
						"stDeviceId");
				for(String stItemId : stItemIdList){
					if(!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()){
						selmServerItemDao.delete(stItemId, stDeviceId, stApplyId);
					}
				}
				
			}else if(stDeviceIdList.length == 1 && !StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				String stDeviceId = stDeviceIdList[0];
				for(String stItemId : stItemIdList){
					if(!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()){
						selmServerItemDao.delete(stItemId, stDeviceId, stApplyId);
					}
				}
			}else if(stDeviceIdList.length >= 2){
				for(String stDeviceId : stDeviceIdList){
					for (String stItemId : stItemIdList) {
						if(!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()){
							selmServerItemDao.delete(stItemId, stDeviceId, stApplyId);
						}
					}
				}
			}
			
		}
		
		
	}
	
	
	@Override
	public int saveOrUpdatePowerByGroup(HttpReqRes httpReqRes) {
		String stItemTypeId = httpReqRes.getParameter("stItemTypeId");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		String stDeviceArray = httpReqRes.getParameter("stDeviceArray");
		String[] stDeviceIdList = stDeviceArray.split(",");
		String[] stItemIdList = httpReqRes.getRequest().getParameterValues("stItemId[]");
		int flag = 0;
		if(stItemTypeId != null && !StringUtils.trim(stItemTypeId).isEmpty()){
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_ITEM_TYPE_ID", Condition.OT_EQUAL, stItemTypeId ));
			List<SelmItemLink> selmItemList = selmItemLinkDao.query(conds, null);
			System.out.println("selmItemList:"+selmItemList.size());
			int length = selmItemList.size();
			int i = 0;
			stItemIdList = new String[length];
			for(SelmItemLink temp : selmItemList){
				stItemIdList[i] = temp.getStItemId();
				i++;
				System.out.println(i+"---"+temp.getStItemId());
			}
		}
		
		if(stItemIdList==null){
			if (stDeviceIdList.length == 1 && StringUtils.trim(stDeviceIdList[0]).isEmpty()) {
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
				SelmDeviceItem oldSelmDeviceItem = null;
				SelmServerItem oldSelmServerItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
				}
				if (oldSelmServerItem == null) {// new
					//插入新的SelmServerItem
					SelmServerItem selmServerItem = new SelmServerItem();
					selmServerItem.setStLinksId(UUID.randomUUID().toString());
					selmServerItem.setStApplyId(stApplyId);
					selmServerItem.setStItemId(stItemId);
					selmServerItem.setStDeviceId(stDeviceId);
					selmServerItem.setNmStatus(new BigDecimal(0));
					selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
					Conditions con = Conditions.newAndConditions();
					con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
					List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
					if(null != itemList && itemList.size()>0){
						SelmItem si = itemList.get(0);
						selmServerItem.setStItemName(si.getStMainName());
						selmServerItem.setStItemNo(si.getStItemNo());
						selmServerItem.setStOrganId(si.getStOrganId());	
					}
					selmServerItemDao.add(selmServerItem);
					flag +=1;
				}
				
			}else if(stDeviceIdList.length == 1 && !StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				String stItemId = httpReqRes.getRequest().getParameter("stItemId");
				String stDeviceId = stDeviceIdList[0];
				SelmServerItem oldSelmServerItem = null;
				if (!StringUtils.trimToEmpty(stItemId).isEmpty()
						&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
				}
				if (oldSelmServerItem == null) {// new
					//插入新的SelmServerItem
					SelmServerItem selmServerItem = new SelmServerItem();
					selmServerItem.setStLinksId(UUID.randomUUID().toString());
					selmServerItem.setStApplyId(stApplyId);
					selmServerItem.setStItemId(stItemId);
					selmServerItem.setStDeviceId(stDeviceId);
					selmServerItem.setNmStatus(new BigDecimal(0));
					selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
					Conditions con = Conditions.newAndConditions();
					con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
					List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
					if(null != itemList && itemList.size()>0){
						SelmItem si = itemList.get(0);
						selmServerItem.setStItemName(si.getStMainName());
						selmServerItem.setStItemNo(si.getStItemNo());
						selmServerItem.setStOrganId(si.getStOrganId());	
					}
					selmServerItemDao.add(selmServerItem);
					flag +=1;
				}
			}else if(stDeviceIdList.length >= 2){
				for (String stDeviceId : stDeviceIdList) {
					String stItemId = httpReqRes.getRequest().getParameter("stItemId");
					SelmServerItem oldSelmServerItem = null;
					if (!StringUtils.trimToEmpty(stItemId).isEmpty()
							&& !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
						oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
					}
					if (oldSelmServerItem == null) {// new
						//插入新的SelmServerItem
						SelmServerItem selmServerItem = new SelmServerItem();
						selmServerItem.setStLinksId(UUID.randomUUID().toString());
						selmServerItem.setStApplyId(stApplyId);
						selmServerItem.setStItemId(stItemId);
						selmServerItem.setStDeviceId(stDeviceId);
						selmServerItem.setNmStatus(new BigDecimal(0));
						selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
						Conditions con = Conditions.newAndConditions();
						con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
						List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
						if(null != itemList && itemList.size()>0){
							SelmItem si = itemList.get(0);
							selmServerItem.setStItemName(si.getStMainName());
							selmServerItem.setStItemNo(si.getStItemNo());
							selmServerItem.setStOrganId(si.getStOrganId());	
						}
						selmServerItemDao.add(selmServerItem);
						flag +=1;
					}
				}
			}
		}else{
			if(stDeviceIdList.length == 1 && StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				for (String stItemId : stItemIdList) {
					String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
					SelmServerItem oldSelmServerItem = null;
					if (!StringUtils.trimToEmpty(stItemId).isEmpty()
							&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
						oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
					}
					if (oldSelmServerItem == null) {// new
						//插入新的SelmServerItem
						SelmServerItem selmServerItem = new SelmServerItem();
						selmServerItem.setStLinksId(UUID.randomUUID().toString());
						selmServerItem.setStApplyId(stApplyId);
						selmServerItem.setStItemId(stItemId);
						selmServerItem.setStDeviceId(stDeviceId);
						selmServerItem.setNmStatus(new BigDecimal(0));
						selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
						Conditions con = Conditions.newAndConditions();
						con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
						List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
						if(null != itemList && itemList.size()>0){
							SelmItem si = itemList.get(0);
							selmServerItem.setStItemName(si.getStMainName());
							selmServerItem.setStItemNo(si.getStItemNo());
							selmServerItem.setStOrganId(si.getStOrganId());	
						}
						selmServerItemDao.add(selmServerItem);
						flag +=1;
					} 
				}
			}else if(stDeviceIdList.length == 1 && !StringUtils.trim(stDeviceIdList[0]).isEmpty()){
				for (String stItemId : stItemIdList) {
					String stDeviceId = stDeviceIdList[0];
					SelmServerItem oldSelmServerItem = null;
					if (!StringUtils.trimToEmpty(stItemId).isEmpty()
							&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
						oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
					}
					if (oldSelmServerItem == null) {// new
						//插入新的SelmServerItem
						SelmServerItem selmServerItem = new SelmServerItem();
						selmServerItem.setStLinksId(UUID.randomUUID().toString());
						selmServerItem.setStApplyId(stApplyId);
						selmServerItem.setStItemId(stItemId);
						selmServerItem.setStDeviceId(stDeviceId);
						selmServerItem.setNmStatus(new BigDecimal(0));
						selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
						Conditions con = Conditions.newAndConditions();
						con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
						List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
						if(null != itemList && itemList.size()>0){
							SelmItem si = itemList.get(0);
							selmServerItem.setStItemName(si.getStMainName());
							selmServerItem.setStItemNo(si.getStItemNo());
							selmServerItem.setStOrganId(si.getStOrganId());	
						}
						selmServerItemDao.add(selmServerItem);
						flag +=1;
					}
				}
			}else if(stDeviceIdList.length >= 2){
				for(String stDeviceId : stDeviceIdList){
					for (String stItemId : stItemIdList) {
						SelmServerItem oldSelmServerItem = null;
						if (!StringUtils.trimToEmpty(stItemId).isEmpty()
								&& !StringUtils.trimToEmpty(stItemId).isEmpty()) {
							oldSelmServerItem = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
						}
						if (oldSelmServerItem == null) {// new
							//插入新的SelmServerItem
							SelmServerItem selmServerItem = new SelmServerItem();
							selmServerItem.setStLinksId(UUID.randomUUID().toString());
							selmServerItem.setStApplyId(stApplyId);
							selmServerItem.setStItemId(stItemId);
							selmServerItem.setStDeviceId(stDeviceId);
							selmServerItem.setNmStatus(new BigDecimal(0));
							selmServerItem.setDtAudit(new Timestamp(System.currentTimeMillis()));
							Conditions con = Conditions.newAndConditions();
							con.add(new Condition("si.ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
							List<SelmItem> itemList = selmItemDao.queryItemWithDetail(con, null);
							if(null != itemList && itemList.size()>0){
								SelmItem si = itemList.get(0);
								selmServerItem.setStItemName(si.getStMainName());
								selmServerItem.setStItemNo(si.getStItemNo());
								selmServerItem.setStOrganId(si.getStOrganId());	
							}
							selmServerItemDao.add(selmServerItem);
							flag +=1;
						}
					}
				}
			}

		}
		return flag;

	}

	@Override
	public SelmServerItem getSelmServerItem(HttpReqRes httpReqRes) {
		String stItemId = httpReqRes.getParameter("stItemId");
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		SelmServerItem ssi = new SelmServerItem();
		ssi = selmServerItemDao.get(stItemId, stApplyId, stDeviceId);
		return ssi;
	}

	
}
