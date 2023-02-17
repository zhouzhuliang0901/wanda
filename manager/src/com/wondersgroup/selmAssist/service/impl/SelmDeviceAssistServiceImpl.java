package com.wondersgroup.selmAssist.service.impl;

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

import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.delivery.bean.SelmDelivery;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.selmAssist.bean.SelmDeviceAssist;
import com.wondersgroup.selmAssist.dao.SelmDeviceAssistDao;
import com.wondersgroup.selmAssist.service.SelmDeviceAssistService;
import com.wondersgroup.serverApply.bean.SelmServerItem;

/**
 * 设备关联人员业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmDeviceAssistServiceImpl implements SelmDeviceAssistService {

	/**
	 * 根据主键 {@link SelmDeviceAssist#ST_ASSIST_ID} {@link SelmDeviceAssist#ST_DEVICE_ID}获取设备关联人员
	 * 
	 * @param stAssistId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_ASSIST_ID}
	 * @param stDeviceId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_DEVICE_ID}
	 * @return 设备关联人员实例
	 */
	@Override
	public SelmDeviceAssist get(String stAssistId, String stDeviceId) {
		if (StringUtils.trimToEmpty(stAssistId).isEmpty())
			throw new NullPointerException("Parameter stAssistId cannot be null.");
		if (StringUtils.trimToEmpty(stDeviceId).isEmpty())
			throw new NullPointerException("Parameter stDeviceId cannot be null.");
		return selmDeviceAssistDao.get(stAssistId, stDeviceId);
	}

	/**
	 * 查询设备关联人员列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备关联人员列表
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
		String stAssistId = httpReqRes.getParameter("stAssistId");
		String stDeviceMac = httpReqRes.getParameter("stDeviceMac");
		String stDeviceAddress = httpReqRes.getParameter("stDeviceAddress");
		String stDeviceMac1 = httpReqRes.getParameter("stDeviceMac1");
		String stDeviceAddress1 = httpReqRes.getParameter("stDeviceAddress1");
		String type = httpReqRes.getParameter("type"); //当type为1时  表示查询的当前辅助人员没有绑定的设备
		if(type.equals("1")){
			Conditions cond = Conditions.newOrConditions();
			if (stAssistId != null && !StringUtils.trim(stAssistId).isEmpty()) {
				cond.add(new Condition("A.ST_ASSIST_ID", Condition.OT_UNEQUAL, stAssistId));
				cond.add(new Condition("A.ST_ASSIST_ID", Condition.OT_EQUAL,null));
			}
			if (stDeviceMac != null && !StringUtils.trim(stDeviceMac).isEmpty()) {
				conds.add(new Condition("A.ST_DEVICE_MAC", Condition.OT_LIKE, stDeviceMac));
			}
			if (stDeviceAddress != null && !StringUtils.trim(stDeviceAddress).isEmpty()) {
				conds.add(new Condition("A.ST_DEVICE_ADDRESS", Condition.OT_LIKE, stDeviceAddress));
			}
			conds.add(cond);
		}else{
			if (stDeviceMac1 != null && !StringUtils.trim(stDeviceMac1).isEmpty()) {
				conds.add(new Condition("A.ST_DEVICE_MAC", Condition.OT_LIKE, stDeviceMac1));
			}
			if (stDeviceAddress1 != null && !StringUtils.trim(stDeviceAddress1).isEmpty()) {
				conds.add(new Condition("A.ST_DEVICE_ADDRESS", Condition.OT_LIKE, stDeviceAddress1));
			}
			if (stAssistId != null && !StringUtils.trim(stAssistId).isEmpty()) {
				conds.add(new Condition("A.ST_ASSIST_ID", Condition.OT_EQUAL, stAssistId));
			}
		}
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		String suffix = "ORDER BY A.ST_DEVICE_CODE";
		if (orderName != null) {
			if ("stDeviceCode".equals(orderName)) {
				suffix = "ORDER BY A.ST_DEVICE_CODE " + orderType.toUpperCase();
			}else if("stTypeId".equals(orderName)) {
				suffix = "ORDER BY A.ST_TYPE_ID " + orderType.toUpperCase();
			}else if("stDeviceAddress".equals(orderName)) {
				suffix = "ORDER BY A.ST_DEVICE_ADDRESS " + orderType.toUpperCase();
			}else if("stDeviceMac".equals(orderName)) {
				suffix = "ORDER BY A.ST_DEVICE_MAC " + orderType.toUpperCase();
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
		List<InfopubDeviceInfo> infopubDeviceInfoList = infopubDeviceInfoDao.querySelmAssistDevice(conds, suffix, pageSize, currentPage);
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
	 * 根据主键 {@link SelmDeviceAssist#ST_ASSIST_ID} {@link SelmDeviceAssist#ST_DEVICE_ID}删除设备关联人员
	 * 
	 * @param stAssistId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_ASSIST_ID}
	 * @param stDeviceId
	 *            设备关联人员主键 {@link SelmDeviceAssist#ST_DEVICE_ID}
	 */
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String[] stDeviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceId[]");
		String stAssistId = httpReqRes.getRequest().getParameter("stAssistId");
		if (stDeviceIdList == null) {
			String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
			selmDeviceAssistDao.delete(stAssistId,stDeviceId);
				return;
		}else{
			for (String  stDeviceId : stDeviceIdList) {
				selmDeviceAssistDao.delete(stAssistId,stDeviceId);
			}
		}
	}

	/**
	 * 保存或更新设备关联人员
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备关联人员实例
	 */
	@Override
	public SelmDeviceAssist saveOrUpdate(HttpReqRes httpReqRes) { 
		String[] stDeviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceId[]");
		SelmDeviceAssist newSelmDeviceAssist = new SelmDeviceAssist();
		if(stDeviceIdList==null){
			String stAssistId = httpReqRes.getRequest().getParameter("stAssistId");
			String stDeviceId = httpReqRes.getRequest().getParameter("stDeviceId");
			SelmDeviceAssist oldSelmDeviceAssist = null;
			if (!StringUtils.trimToEmpty(stAssistId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
				oldSelmDeviceAssist = selmDeviceAssistDao.get(stAssistId, stDeviceId);
			}
			if (oldSelmDeviceAssist == null) {// new
				newSelmDeviceAssist.setStAssistId(stAssistId);
				newSelmDeviceAssist.setStDeviceId(stDeviceId);
				selmDeviceAssistDao.add(newSelmDeviceAssist);
				return newSelmDeviceAssist;
			}
		}else{
			for (String stDeviceId : stDeviceIdList) {
				String stAssistId = httpReqRes.getRequest().getParameter("stAssistId");
				SelmDeviceAssist oldSelmDeviceAssist = null;
				if (!StringUtils.trimToEmpty(stAssistId).isEmpty() && !StringUtils.trimToEmpty(stDeviceId).isEmpty()) {
					oldSelmDeviceAssist = selmDeviceAssistDao.get(stAssistId, stDeviceId);
				}
				if (oldSelmDeviceAssist == null) {// new
					newSelmDeviceAssist.setStAssistId(stAssistId);
					newSelmDeviceAssist.setStDeviceId(stDeviceId);
					selmDeviceAssistDao.add(newSelmDeviceAssist);
					
				}
			}
		}
		return newSelmDeviceAssist;
	}

	@Autowired
	private SelmDeviceAssistDao selmDeviceAssistDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	

}
