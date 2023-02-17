package com.wondersgroup.infopub.service.impl;

import java.math.*;
import java.sql.*;
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
import wfc.service.database.Sequence;
import wfc.service.log.Log;



import ch.qos.logback.classic.Logger;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.service.InfopubAddressService;
import com.wondersgroup.infopub.util.GetLatAndLngByAddress;
import com.wondersgroup.infopub.util.ReadExcelData;

/**
 * 地址表（办理点）业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
@SuppressWarnings("all")
public class InfopubAddressServiceImpl implements InfopubAddressService {
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	/**
	 * 根据主键 {@link InfopubAddress#ST_ADDRESS_ID}获取地址表（办理点）
	 * 
	 * @param stAddressId
	 *            地址表（办理点）主键 {@link InfopubAddress#ST_ADDRESS_ID}
	 * @return 地址表（办理点）实例
	 */
	@Override
	public InfopubAddress get(String stAddressId) {
		if (StringUtils.trimToEmpty(stAddressId).isEmpty())
			throw new NullPointerException("Parameter stAddressId cannot be null.");
		return infopubAddressDao.get(stAddressId);
	}
	
	public InfopubArea getArea(String stAreaName) {
		InfopubArea city = infopubAreaDao.getName(stAreaName);
		if(city.getStAreaName().equals("上海市")){
			return city;
		}else{
			InfopubArea province = infopubAreaDao.get(city.getStParentAreaId());
			return province;
		}
		
	}

	/**
	 * 查询地址表（办理点）列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 地址表（办理点）列表
	 */
	@Override
	public PaginationArrayList<InfopubAddress> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(InfopubAddress.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return infopubAddressDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link InfopubAddress#ST_ADDRESS_ID}删除地址表（办理点）
	 * 
	 * @param stAddressId
	 *            地址表（办理点）主键 {@link InfopubAddress#ST_ADDRESS_ID}
	 */
	@Override
	public void remove(String stAddressId) {
		if (StringUtils.trimToEmpty(stAddressId).isEmpty())
			throw new NullPointerException("Parameter stAddressId cannot be null.");
		infopubAddressDao.delete(stAddressId);
	}

	/**
	 * 保存或更新地址表（办理点）
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 地址表（办理点）实例
	 */
	@Override
	public InfopubAddress saveOrUpdate(RequestWrapper wrapper) {
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		String stAddressId = wrapper.getParameter(InfopubAddress.ST_ADDRESS_ID);
		String city_id = wrapper.getParameter(InfopubAddress.ST_CITY);
		String district_id = wrapper.getParameter(InfopubAddress.ST_DISTRICT);
		InfopubAddress oldInfopubAddress = null;
		if (!StringUtils.trimToEmpty(stAddressId).isEmpty()) {
			oldInfopubAddress = infopubAddressDao.get(stAddressId);
		}
		if (oldInfopubAddress == null) {// new
			InfopubAddress newInfopubAddress = (InfopubAddress) t4r.toBean(InfopubAddress.class);
			newInfopubAddress.setStAddressId(UUID.randomUUID().toString());
			InfopubArea city = infopubAreaDao.get(city_id);
			newInfopubAddress.setStCity(city.getStAreaName());
			InfopubArea district = infopubAreaDao.get(district_id);
			newInfopubAddress.setStDistrict(district.getStAreaName());
			newInfopubAddress.setDtCreate(new Timestamp(System.currentTimeMillis()));
			newInfopubAddress.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			System.out.println("new "+newInfopubAddress);
			infopubAddressDao.add(newInfopubAddress);
			return newInfopubAddress;
		}else {// update
			oldInfopubAddress = (InfopubAddress) t4r.toBean(oldInfopubAddress, InfopubAddress.class);
			InfopubArea city = infopubAreaDao.get(city_id);
			oldInfopubAddress.setStCity(city.getStAreaName());
			InfopubArea district = infopubAreaDao.get(district_id);
			oldInfopubAddress.setStDistrict(district.getStAreaName());
			oldInfopubAddress.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			infopubAddressDao.update(oldInfopubAddress);
			System.out.println("old "+oldInfopubAddress);
			return oldInfopubAddress;
		}
	}

	
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		String stAddress = httpReqRes.getParameter("stAddress");
		String stCity = httpReqRes.getParameter("stCity");
		String stDistrict = httpReqRes.getParameter("stDistrict");
		String stStreet = httpReqRes.getParameter("stStreet");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		if (stAddress != null && !StringUtils.trim(stAddress).isEmpty()) {
			conds.add(new Condition("ST_ADDRESS", Condition.OT_LIKE, stAddress));
		}
		if (stCity != null && !StringUtils.trim(stCity).isEmpty()) {
			conds.add(new Condition("ST_CITY", Condition.OT_LIKE, stCity));
		}
		if (stDistrict != null && !StringUtils.trim(stDistrict).isEmpty()) {
			conds.add(new Condition("ST_DISTRICT", Condition.OT_LIKE, stDistrict));
		}
		if (stStreet != null && !StringUtils.trim(stStreet).isEmpty()) {
			conds.add(new Condition("ST_STREET", Condition.OT_LIKE, stStreet));
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
			System.out.println(orderName);
			if ("stAddress".equals(orderName)) {
				suffix = "ORDER BY ST_ADDRESS " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			} else if ("dtUpdate".equals(orderName)) {
				suffix = "ORDER BY DT_UPDATE " + orderType.toUpperCase();
			} else if ("stLabel".equals(orderName)) {
				suffix = "ORDER BY ST_LABEL " + orderType.toUpperCase();
			} else if ("nmLng".equals(orderName)) {
				suffix = "ORDER BY NM_LNG " + orderType.toUpperCase();
			} else if ("nmLat".equals(orderName)) {
				suffix = "ORDER BY NM_LAT " + orderType.toUpperCase();
			} else if ("stCity".equals(orderName)) {
				suffix = "ORDER BY ST_CITY " + orderType.toUpperCase();
			} else if ("stDistrict".equals(orderName)) {
				suffix = "ORDER BY ST_DISTRICT " + orderType.toUpperCase();
			} else if ("stStreet".equals(orderName)) {
				suffix = "ORDER BY ST_STREET " + orderType.toUpperCase();
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
		List<InfopubAddress> infopubAddressList = infopubAddressDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infopubAddressList,
							InfopubAddress.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infopubAddressList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infopubAddressList);
		return returnObj;
		
	}
	
	@Override
	public JSONObject initAddress(HttpReqRes httpReqRes) {
		
		List<InfopubAddress> infopubAddressList = infopubAddressDao.initAddress(null, null);
		
		JSONArray arr = new JSONArray();
		JSONObject address = new JSONObject();
		for(InfopubAddress emp :infopubAddressList){
			address = new JSONObject();
			address.put("stAddressId", emp.getStAddressId());
			String city = null == emp.getStCity() ? "" : emp.getStCity();
			String district = null == emp.getStDistrict() ? "" : emp.getStDistrict();
			String street = null == emp.getStStreet() ? "" : emp.getStStreet();
			String addr = null == emp.getStAddress() ? "" : emp.getStAddress();
			address.put("address", city+district+street+addr);
			arr.add(address);
		}
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("data", arr);
		return returnObj;
		
	}
	

	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String[] addressIdList = httpReqRes.getRequest().getParameterValues(
				"stAddressId[]");
		if (addressIdList == null) {
			String stAddressId = httpReqRes.getRequest()
					.getParameter("stAddressId");
			if (stAddressId != null
					&& !StringUtils.trimToEmpty(stAddressId).isEmpty()) {
				infopubAddressDao.delete(stAddressId);
				return;
			} else {
				throw new NullPointerException("地址ID不能为空");
			}
		}
		for (String addressId : addressIdList) {
			infopubAddressDao.delete(addressId);
		}
		
	}

	@Autowired
	private InfopubAddressDao infopubAddressDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;

	@Override
	public InfopubAddress importAddress(RequestWrapper wrapper) {
		int count = 0;
		String url = null;//要读取的文件路径
		BigDecimal lng = null;//经度
		BigDecimal lat = null;//纬度
		Map<String,BigDecimal> map = null;
		InfopubAddress newInfopubAddress = new InfopubAddress();
		//得到excel中的所有数据
		try {
			List<InfopubAddress> listExcel = ReadExcelData.getDataByExcel("D:/importExcel/地址表.xls");
			System.out.println("开始遍历");
			for(InfopubAddress address : listExcel) {
				//System.out.println("遍历获取的address");
				//地址转化经纬度
				map = GetLatAndLngByAddress.getLatAndLng("上海市"+address.getStDistrict().toString()+address.getStAddress().toString());
				Iterator<Entry<String, BigDecimal>> iterator = map.entrySet().iterator();  //map.entrySet()得到的是set集合，可以使用迭代器遍历
				while(iterator.hasNext()){
					Entry<String, BigDecimal> entry = iterator.next();
					//System.out.println("key值："+entry.getKey()+" value值："+entry.getValue());
					if(entry.getKey() == "lng") {
						lng = entry.getValue();
					}else {
						lat = entry.getValue();
					}
				}
				Log.debug("开始执行sql-------------------------------------------");
				InfopubAddress name = infopubAddressDao.getName(null, null, null, address.getStAddress());
				if(name==null){
					newInfopubAddress.setStAddressId(UUID.randomUUID().toString());
					newInfopubAddress.setStCity("上海市");
					newInfopubAddress.setStLabel(address.getStLabel());
					newInfopubAddress.setStDistrict(address.getStDistrict());
					//newInfopubAddress.setStStreet(address.getStStreet());
					newInfopubAddress.setStAddress(address.getStAddress());
					newInfopubAddress.setNmLat(lat);
					newInfopubAddress.setNmLng(lng);
					newInfopubAddress.setDtCreate(new Timestamp(System
						.currentTimeMillis()));
					infopubAddressDao.add(newInfopubAddress);
					count++;
				}
				
			}
			Log.debug("录入"+count+"数据");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newInfopubAddress;
	}

	@Override
	public JSONObject getAllArea(HttpReqRes httpReqRes) {
		JSONObject area = new JSONObject();
		List<String> all = infopubAreaDao.getAll();
		area.put("area",all);
		return area;
	}

}
