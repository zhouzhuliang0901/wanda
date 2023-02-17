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

import com.wondersgroup.selmAssist.bean.SelmAssist;
import com.wondersgroup.selmAssist.bean.SelmDeviceAssist;
import com.wondersgroup.selmAssist.dao.SelmAssistDao;
import com.wondersgroup.selmAssist.service.SelmAssistService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设备辅助人员业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmAssistServiceImpl implements SelmAssistService {

	/**
	 * 根据主键 {@link SelmAssist#ST_ASSIST_ID}获取设备辅助人员
	 * 
	 * @param stAssistId
	 *            设备辅助人员主键 {@link SelmAssist#ST_ASSIST_ID}
	 * @return 设备辅助人员实例
	 */
	@Override
	public SelmAssist get(String stAssistId) {
		if (StringUtils.trimToEmpty(stAssistId).isEmpty())
			throw new NullPointerException("Parameter stAssistId cannot be null.");
		return selmAssistDao.get(stAssistId);
	}

	/**
	 * 查询设备辅助人员列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备辅助人员列表
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
		String stAssistName = httpReqRes.getParameter("stAssistName");
		String stAssistPhone = httpReqRes.getParameter("stAssistPhone");
		String stAssistIdcard = httpReqRes.getParameter("stAssistIdcard");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stAssistName != null && !StringUtils.trim(stAssistName).isEmpty()) {
			conds.add(new Condition("ST_ASSIST_NAME", Condition.OT_LIKE, stAssistName));
		}
		if (stAssistPhone != null && !StringUtils.trim(stAssistPhone).isEmpty()) {
			conds.add(new Condition("ST_ASSIST_PHONE", Condition.OT_LIKE, stAssistPhone));
		}
		if (stAssistIdcard != null && !StringUtils.trim(stAssistIdcard).isEmpty()) {
			conds.add(new Condition("ST_ASSIST_IDCARD", Condition.OT_LIKE, stAssistIdcard));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(endDate + " 23:59:59")));
		}
		//String suffix = "ORDER BY NM_ORDER";
		/*if (orderName != null) {
			if ("stAssistId".equals(orderName)) {
				suffix = "ORDER BY NM_ORDER " + orderType.toUpperCase();
			}else if("stAssistName".equals(orderName)){
				suffix = "ORDER BY ST_ASSIST_NAME " + orderType.toUpperCase();
			}else if("stAssistPhone".equals(orderName)){
				suffix = "ORDER BY ST_ASSIST_PHONE " + orderType.toUpperCase();
			}else if("stAssistIdcard".equals(orderName)){
				suffix = "ORDER BY ST_ASSIST_IDCARD " + orderType.toUpperCase();
			}else if("dtUpadte".equals(orderName)){
				suffix = "ORDER BY DT_UPADTE " + orderType.toUpperCase();
			}else if("dtCreate".equals(orderName)){
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			}
		}*/
		String suffix = "ORDER BY DT_CREATE DESC";
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
		List<SelmAssist> selmAssistList = selmAssistDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmAssistList,
							SelmAssist.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmAssistList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmAssistList);
		return returnObj;
		
	}

	/**
	 * 根据主键 {@link SelmAssist#ST_ASSIST_ID}删除设备辅助人员
	 * 
	 * @param stAssistId
	 *            设备辅助人员主键 {@link SelmAssist#ST_ASSIST_ID}
	 */
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String[] stAssistIdList = httpReqRes.getRequest().getParameterValues(
				"stAssistId[]");
		if (stAssistIdList == null) {
			String stAssistId = httpReqRes.getRequest()
					.getParameter("stAssistId");
			if (stAssistId != null
					&& !StringUtils.trimToEmpty(stAssistId).isEmpty()) {
				selmAssistDao.delete(stAssistId);
				return;
			} else {
				throw new NullPointerException("设备辅助人员id不能为空");
			}
		}else{
			for (String  stAssistId : stAssistIdList) {
				selmAssistDao.delete(stAssistId);
			}
		}
	}

	/**
	 * 保存或更新设备辅助人员
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备辅助人员实例
	 */
	@Override
	public SelmAssist saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmAssist.ST_ASSIST_ID
		String stAssistId = wrapper.getParameter(SelmAssist.ST_ASSIST_ID);
		SelmAssist oldSelmAssist = null;
		if (!StringUtils.trimToEmpty(stAssistId).isEmpty()) {
			oldSelmAssist = selmAssistDao.get(stAssistId);
		}
		if (oldSelmAssist == null) {// new
			SelmAssist newSelmAssist = (SelmAssist) t4r.toBean(SelmAssist.class);
			newSelmAssist.setStAssistId(UUID.randomUUID().toString());
			newSelmAssist.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			selmAssistDao.add(newSelmAssist);
			return newSelmAssist;
		}else {// update
			oldSelmAssist = (SelmAssist) t4r.toBean(oldSelmAssist, SelmAssist.class);
			oldSelmAssist.setDtUpadte(new Timestamp(System
					.currentTimeMillis()));
			selmAssistDao.update(oldSelmAssist);
			return oldSelmAssist;
		}
	}

	@Autowired
	private SelmAssistDao selmAssistDao;

}
