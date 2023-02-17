package com.wondersgroup.infopub.service.impl;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;

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


import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.infopub.bean.InfopubCompany;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubCompanyDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.infopub.service.InfopubCompanyService;

/**
 * 设备厂商业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class InfopubCompanyServiceImpl implements InfopubCompanyService {
	
	@Autowired
	private InfopubCompanyDao infopubCompanyDao;
	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;


	/**
	 * 根据主键 {@link InfopubCompany#ST_COMPANY_ID}获取设备厂商
	 * 
	 * @param stCompanyId
	 *            设备厂商主键 {@link InfopubCompany#ST_COMPANY_ID}
	 * @return 设备厂商实例
	 */
	@Override
	public InfopubCompany get(String stCompanyId) {
		if (StringUtils.trimToEmpty(stCompanyId).isEmpty())
			throw new NullPointerException("Parameter stCompanyId cannot be null.");
		return infopubCompanyDao.get(stCompanyId);
	}

	/**
	 * 查询设备厂商列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 设备厂商列表
	 */
	@Override
	public JSONObject companyList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
		}
		Conditions conds = Conditions.newAndConditions();
		String companyName = httpReqRes.getParameter("companyName");
		String code = httpReqRes.getParameter("code");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (companyName != null && !StringUtils.trim(companyName).isEmpty()) {
			conds.add(new Condition("ST_COMPANY_NAME", Condition.OT_LIKE, companyName));
		}
		if (code != null && !StringUtils.trim(code).isEmpty()) {
			conds.add(new Condition("ST_COMPANY_CODE", Condition.OT_LIKE, code));
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
			if ("stCompanyName".equals(orderName)) {
				suffix = "ORDER BY ST_COMPANY_NAME " + orderType.toUpperCase();
			} else if ("stCompanyCode".equals(orderName)) {
				suffix = "ORDER BY ST_COMPANY_CODE " + orderType.toUpperCase();
			} else if ("stContactName".equals(orderName)) {
				suffix = "ORDER BY ST_CONTACT_NAME " + orderType.toUpperCase();
			} else if ("stContactTel".equals(orderName)) {
				suffix = "ORDER BY ST_CONTACT_TEL " + orderType.toUpperCase();
			} else if ("dtUpdate".equals(orderName)) {
				suffix = "ORDER BY DT_UPDATE " + orderType.toUpperCase();
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
		PaginationArrayList<InfopubCompany> companyList = infopubCompanyDao.query(conds, suffix, pageSize, currentPage);
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(companyList,
							InfopubCompany.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", companyList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", companyList);
		return returnObj;
	}

	/**
	 * 根据主键 {@link InfopubCompany#ST_COMPANY_ID}删除设备厂商
	 * 
	 * @param stCompanyId
	 *            设备厂商主键 {@link InfopubCompany#ST_COMPANY_ID}
	 */
	@Override
	public void remove(String stCompanyId) {
		if (StringUtils.trimToEmpty(stCompanyId).isEmpty())
			throw new NullPointerException("Parameter stCompanyId cannot be null.");
		infopubCompanyDao.delete(stCompanyId);
	}

	/**
	 * 保存或更新设备厂商
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 设备厂商实例
	 */
	@Override
	public InfopubCompany saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// InfopubCompany.ST_COMPANY_ID
		String stCompanyId = wrapper.getParameter(InfopubCompany.ST_COMPANY_ID);
		InfopubCompany oldInfopubCompany = null;
		if (!StringUtils.trimToEmpty(stCompanyId).isEmpty()) {
			oldInfopubCompany = infopubCompanyDao.get(stCompanyId);
		}
		if (oldInfopubCompany == null) {// new
			InfopubCompany newInfopubCompany = (InfopubCompany) t4r.toBean(InfopubCompany.class);
			newInfopubCompany.setStCompanyId(UUID.randomUUID().toString());
			newInfopubCompany.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newInfopubCompany.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubCompanyDao.add(newInfopubCompany);
			return newInfopubCompany;
		}else {// update
			oldInfopubCompany = (InfopubCompany) t4r.toBean(oldInfopubCompany, InfopubCompany.class);
			oldInfopubCompany.setDtUpdate(new Timestamp(System
					.currentTimeMillis()));
			infopubCompanyDao.update(oldInfopubCompany);
			return oldInfopubCompany;
		}
	}

	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String[] stCompanyIdList = httpReqRes.getRequest().getParameterValues(
				"stCompanyId[]");
		if (stCompanyIdList == null) {
			String stCompanyId = httpReqRes.getRequest()
					.getParameter("stCompanyId");
			if (stCompanyId != null
					&& !StringUtils.trimToEmpty(stCompanyId).isEmpty()) {
				infopubCompanyDao.delete(stCompanyId);
				return;
			} else {
				throw new NullPointerException("厂商id不能为空");
			}
		}
		for (String companyId : stCompanyIdList) {
			infopubCompanyDao.delete(companyId);
		}
		
	}

	@Override
	public JSONObject CompWithType(HttpReqRes httpReqRes) {
		JSONObject compWithType = null;
		JSONObject type = new JSONObject();
		JSONObject all = new JSONObject();
		List<InfopubCompany> compL = infopubCompanyDao.getAll();
		List<String> compName = new ArrayList<String>();
		List<JSONObject> obj = new ArrayList<JSONObject>();
		for(InfopubCompany emp : compL){
			//获取厂商
			compName.add(emp.getStCompanyName());
			//获取厂商+类型
			List<String> t = infopubDeviceTypeDao.getByCompanyId(emp.getStCompanyId());
			compWithType = new JSONObject();
			compWithType.put("cname",emp.getStCompanyName());
			compWithType.put("type", t);
			obj.add(compWithType);
		}
		all.put("compWithType", obj);
		all.put("company", compName);
		return all;
	}

}
