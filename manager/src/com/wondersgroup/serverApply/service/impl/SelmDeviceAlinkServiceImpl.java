package com.wondersgroup.serverApply.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import Decoder.BASE64Encoder;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;
import com.wondersgroup.infopub.dao.InfopubCompanyDao;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.infopub.dao.InfopubDeviceTypeDao;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;
import com.wondersgroup.serverApply.bean.SelmDeviceApply;
import com.wondersgroup.serverApply.bean.SelmServerApply;
import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.serverApply.dao.SelmDeviceAlinkDao;
import com.wondersgroup.serverApply.dao.SelmDeviceApplyDao;
import com.wondersgroup.serverApply.service.SelmDeviceAlinkService;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;

/**
 * ????????????????????????????????????
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmDeviceAlinkServiceImpl implements SelmDeviceAlinkService {

	@Autowired
	private SelmDeviceAlinkDao selmDeviceAlinkDao;

	@Autowired
	private SelmDeviceApplyDao selmDeviceApplyDao;

	@Autowired
	private InfopubDeviceTypeDao infopubDeviceTypeDao;
	
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	
	@Autowired
	private InfopubAddressDao infopubAddressDao;
	
	@Autowired
	private InfopubAreaDao infopubAreaDao;
	
	@Autowired
	private InfopubCompanyDao infopubCompanyDao;
	
	@Autowired
	private SmsUserDao smsUserDao;
	
	/**
	 * ???????????????????????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ??????????????????????????????
	 */
	@Override
	public SelmDeviceAlink saveOrUpdate(HttpReqRes httpReqRes) {
		String stDeviceApplyId = httpReqRes.getParameter("deviceApplyId");
		String stMachineId = httpReqRes.getParameter(SelmDeviceAlink.ST_MACHINE_ID);	
		String stArea = httpReqRes.getParameter("ST_AREA");
		String stAddress = httpReqRes.getParameter("ST_ADDRESS");
		String stSpecificAddress = httpReqRes.getParameter("ST_SPECINICADDRESS");
		String stNetWork = httpReqRes.getParameter("ST_NETWORK");
		String stMac = httpReqRes.getParameter("ST_MAC");
		String stIp = httpReqRes.getParameter("ST_IP");
		String stProducer = httpReqRes.getParameter("ST_PRODUCER");
		String stDeviceType = httpReqRes.getParameter("ST_DEVICETYPE");
		
		String nmybzc = httpReqRes.getParameter("NM_YBZC");
		String nmgpy = httpReqRes.getParameter("NM_GPY");
		String nmjzzqu = httpReqRes.getParameter("NM_JZZQU");
		String nmjzzzk = httpReqRes.getParameter("NM_JZZZK");
		String nmNetWork = httpReqRes.getParameter("NM_NETWORK");
		String nmDuty = httpReqRes.getParameter("NM_DUTY");
		String nm24hours = httpReqRes.getParameter("NM_24HOURS");
		FileItem fileItem = httpReqRes.getFileItem("file");
		String username = httpReqRes.getParameter("username");
		
		
		//???????????????
		SelmDeviceAlink selmDeviceAlick  = new SelmDeviceAlink();
		selmDeviceAlick.setStDeviceApplyId(stDeviceApplyId);
		selmDeviceAlick.setStMachineId(stMachineId);
		//???
		selmDeviceAlick.setStAreaId(null != stArea ? stArea : "");
		//??????
		selmDeviceAlick.setStAddressId(null != stAddress ? stAddress : "");
		//????????????
		selmDeviceAlick.setStDeviceAddress(null != stSpecificAddress ? stSpecificAddress : "");
		//??????
		selmDeviceAlick.setStDeviceName(null != stNetWork ? stNetWork : "");
		//mac
		selmDeviceAlick.setStDeviceMac(null != stMac ? stMac : "");
		//ip
		selmDeviceAlick.setStDeviceIp(null != stIp ? stIp : "");
		//??????
		selmDeviceAlick.setStDesc(null != stProducer ? stProducer : "");
		//????????????
		selmDeviceAlick.setStTypeId(null != stDeviceType ? stDeviceType : "");
		//??????	
		selmDeviceAlick.setStUserId(username);
		selmDeviceAlick.setNmYbzc(null==nmybzc||nmybzc.isEmpty() ? new BigDecimal(0) : new BigDecimal(nmybzc));
		selmDeviceAlick.setNmGpy(null==nmgpy||nmgpy.isEmpty() ? new BigDecimal(0) : new BigDecimal(nmgpy));
		selmDeviceAlick.setNmJzzqz(null==nmjzzqu||nmjzzqu.isEmpty() ? new BigDecimal(0) : new BigDecimal(nmjzzqu));
		selmDeviceAlick.setNmJzzzk(null==nmjzzzk||nmjzzzk.isEmpty() ? new BigDecimal(0) : new BigDecimal(nmjzzzk));
		selmDeviceAlick.setStNetwork(null==nmNetWork||nmNetWork.isEmpty() ? "" : nmNetWork);
		selmDeviceAlick.setNmDuty(null==nmDuty||nmDuty.isEmpty() ? new BigDecimal(0) : new BigDecimal(nmDuty));
		selmDeviceAlick.setNmStatus(new BigDecimal(0));
		selmDeviceAlick.setNm24Hours(null==nm24hours||nm24hours.isEmpty() ? new BigDecimal(0) : new BigDecimal(nm24hours));
		
		byte[] file = null;
		/*if (fileItem != null) {
			file = ((FileItem) fileItem).get();
		}
		selmDeviceAlick.setBlContent(file);*/
		
		SelmDeviceAlink oldAlink = selmDeviceAlinkDao.get(stDeviceApplyId, stMachineId);
		
		if(oldAlink!=null){
			if (null != fileItem && fileItem.getSize() != 0) {
				file = ((FileItem) fileItem).get();
				selmDeviceAlick.setBlContent(file);
			}else{
				selmDeviceAlick.setBlContent(oldAlink.getBlContent());
			}
			selmDeviceAlick.setDtAudit(new Timestamp(System.currentTimeMillis()));
			selmDeviceAlinkDao.update(selmDeviceAlick);
		}else{
			if (null != fileItem && fileItem.getSize() != 0) {
				file = ((FileItem) fileItem).get();
				selmDeviceAlick.setBlContent(file);
			}else{
				file = new byte[0];
				selmDeviceAlick.setBlContent(file);
			}
			selmDeviceAlick.setDtCreate(new Timestamp(System.currentTimeMillis()));
			selmDeviceAlick.setDtAudit(new Timestamp(System.currentTimeMillis()));
			selmDeviceAlinkDao.add(selmDeviceAlick);
		}
		return selmDeviceAlick;
	
	}

	
	
	
	/**
	 * ???????????? {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID} {@link SelmDeviceAlink#ST_MACHINE_ID}??????????????????????????????
	 * 
	 * @param stDeviceApplyId
	 *            ?????????????????????????????? {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID}
	 * @param stMachineId
	 *            ?????????????????????????????? {@link SelmDeviceAlink#ST_MACHINE_ID}
	 * @return ??????????????????????????????
	 */
	@Override
	public SelmDeviceAlink get(String stDeviceApplyId, String stMachineId) {
		if (StringUtils.trimToEmpty(stDeviceApplyId).isEmpty())
			throw new NullPointerException("Parameter stDeviceApplyId cannot be null.");
		if (StringUtils.trimToEmpty(stMachineId).isEmpty())
			throw new NullPointerException("Parameter stMachineId cannot be null.");
		return selmDeviceAlinkDao.get(stDeviceApplyId, stMachineId);
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ??????????????????????????????
	 */
	@Override
	public JSONObject query(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		
		String stDeviceApplyId = httpReqRes.getParameter("stDeviceApplyId");
		Conditions conds = Conditions.newAndConditions();
		if (stDeviceApplyId != null && !StringUtils.trim(stDeviceApplyId).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, stDeviceApplyId));
		}
		
		
		String suffix = "ORDER BY DT_CREATE ";
	
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
		
		List<SelmDeviceAlink> selmDeviceAlinkList = selmDeviceAlinkDao.queryDeviceInfo(conds, suffix, pageSize, currentPage);
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmDeviceAlinkList,
							SelmDeviceAlink.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmDeviceAlinkList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmDeviceAlinkList);
		return returnObj;
		
	}

	/**
	 * ???????????? {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID} {@link SelmDeviceAlink#ST_MACHINE_ID}??????????????????????????????
	 * 
	 * @param stDeviceApplyId
	 *            ?????????????????????????????? {@link SelmDeviceAlink#ST_DEVICE_APPLY_ID}
	 * @param stMachineId
	 *            ?????????????????????????????? {@link SelmDeviceAlink#ST_MACHINE_ID}
	 */
	@Override
	public void remove(String stDeviceApplyId, String stMachineId) {
		if (StringUtils.trimToEmpty(stDeviceApplyId).isEmpty())
			throw new NullPointerException("Parameter stDeviceApplyId cannot be null.");
		if (StringUtils.trimToEmpty(stMachineId).isEmpty())
			throw new NullPointerException("Parameter stMachineId cannot be null.");
		selmDeviceAlinkDao.delete(stDeviceApplyId, stMachineId);
	}




	@Override
	public SelmDeviceAlink getDeviceAlink(RequestWrapper wrapper) {
		String stMachineId = wrapper.getParameter("stMachineId");
		String stApplyId = wrapper.getParameter("stApplyId");
		SelmDeviceAlink sda = new SelmDeviceAlink();
		sda = selmDeviceAlinkDao.getAlinkByMAId(stApplyId,stMachineId);
		return sda;			
		
	}
	
	@Override
	public InfopubDeviceInfo getDeviceInfo(RequestWrapper wrapper) {
		String stMachineId = wrapper.getParameter("stMachineId");
		InfopubDeviceInfo idi = new InfopubDeviceInfo();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,stMachineId));
		idi = infopubDeviceInfoDao.get(stMachineId);
		String stAreaId = idi.getStAreaId();
		if(stAreaId!=null){
			String area = infopubAreaDao.get(stAreaId).getStAreaName();
			idi.setStAreaId(area!=null?area:"");
		} 
		String stAddressId = idi.getStAddressId();
		if(stAddressId!=null){
			InfopubAddress infoAddress = infopubAddressDao.get(stAddressId);
			idi.setStAddressId(infoAddress.getStStreet()!=null?infoAddress.getStStreet():"") ;
			idi.setStDesc(infoAddress.getStAddress()!=null?infoAddress.getStAddress():"");
		}
		String stTypeId = idi.getStTypeId();
		if(stTypeId!=null){
			InfopubDeviceType type = infopubDeviceTypeDao.get(stTypeId);
			if(type!=null){
				idi.setStTypeId(type.getStTypeName()!=null?type.getStTypeName():"");
				String companyId = type.getStCompanyId();
				String companyName = infopubCompanyDao.get(companyId).getStCompanyName();
				idi.setStUserId(companyName!=null?companyName:"");
			}
			
		}
		
		return idi;			
		
	}




	@Override
	public List<SelmDeviceAlink> deviceChangeSaveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		
		String stApplyId = wrapper.getParameter("deviceApplyId");
		JSONArray stDeviceArray = JSONArray.fromObject( wrapper.getParameter("stDeviceIdList"));
		System.out.println(stDeviceArray.toString());
		List<SelmDeviceAlink> alink = new ArrayList<SelmDeviceAlink>();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_APPLY_ID", Condition.OT_LIKE, stApplyId));
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty() && stDeviceArray.size() > 0) {
			for(int i=0; i<stDeviceArray.size(); i++){
				InfopubDeviceInfo info = infopubDeviceInfoDao.get(stDeviceArray.getString(i));
				SelmDeviceAlink selmDeviceAlink = new SelmDeviceAlink();
				selmDeviceAlink.setStDeviceApplyId(stApplyId);
				selmDeviceAlink.setStMachineId(stDeviceArray.getString(i));
				selmDeviceAlink.setNmStatus(new BigDecimal(0));
				selmDeviceAlink.setDtCreate(new Timestamp(System.currentTimeMillis()));
				selmDeviceAlink.setStDeviceName(info.getStDeviceName());
				selmDeviceAlink.setStDeviceMac(info.getStDeviceMac());
				String specificAddress = infopubAddressDao.get(info.getStAddressId()).getStAddress();  //????????????
				String street = infopubAddressDao.get(info.getStAddressId()).getStStreet();  //??????
				selmDeviceAlink.setStAddressId(street);
				selmDeviceAlink.setStDeviceAddress(specificAddress);
				String area = infopubAreaDao.get(info.getStAreaId()).getStAreaName();
				selmDeviceAlink.setStAreaId(area);
				selmDeviceAlink.setStDeviceIp(info.getStDeviceIp());
				String type = infopubDeviceTypeDao.get(info.getStTypeId()).getStTypeName();
				selmDeviceAlink.setStTypeId(type);
				//?????????????????????
				byte[] b = new byte[0];
				selmDeviceAlink.setBlContent(b);
				selmDeviceAlinkDao.add(selmDeviceAlink);
				alink.add(selmDeviceAlink);
			}
		}
		return alink;
		
	}




	@Override
	public JSONObject deviceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("?????????????????????:" + drawInt);
		}
		String suffix = "ORDER BY DT_CREATE";
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
		
		String stApplyId = httpReqRes.getParameter("stDeviceApplyId");
		Conditions conds = Conditions.newAndConditions();
		if (stApplyId != null && !StringUtils.trim(stApplyId).isEmpty()) {
			conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, stApplyId));
		}
	
		List<SelmDeviceAlink> selmDeviceAlinkList = selmDeviceAlinkDao.query(conds, suffix, pageSize, currentPage);
		
		
		// ?????????
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmDeviceAlinkList,
							SelmDeviceAlink.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmDeviceAlinkList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmDeviceAlinkList);
		return returnObj;
	
	}




	@Override
	public SelmDeviceAlink getDeviceAlinkById(HttpReqRes httpReqRes) {
		String stMachineId = httpReqRes.getParameter("stMachineId");
	 	String stDeviceApplyId = httpReqRes.getParameter("stDeviceApplyId");
		SelmDeviceAlink sda = selmDeviceAlinkDao.get(stDeviceApplyId, stMachineId);
		return sda;
	}




	@Override
	public int saveNoPassReason(HttpReqRes httpReqRes) {
		String stMachineId = httpReqRes.getParameter("stMachineId");
		String stDeviceApplyId = httpReqRes.getParameter("stDeviceApplyId");
		String result = httpReqRes.getParameter("stResult");
		System.out.println("??????????????????"+result);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ST_REASON", result);
		map.put("NM_STATUS", new BigDecimal(3));//?????????
		map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MACHINE_ID",Condition.OT_EQUAL,stMachineId));
		conds.add(new Condition("ST_DEVICE_APPLY_ID",Condition.OT_EQUAL,stDeviceApplyId));
		int i = selmDeviceAlinkDao.update(map, conds);
		return i;
	}




	
	
}
