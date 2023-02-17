package com.wondersgroup.serverApply.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.*;
import java.sql.*;
import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import coral.base.app.AppContext;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.app.bean.SelmDeviceItem;
import com.wondersgroup.app.bean.SelmItem;
import com.wondersgroup.app.bean.SelmItemLink;
import com.wondersgroup.app.bean.SelmItemType;
import com.wondersgroup.app.dao.SelmDeviceItemDao;
import com.wondersgroup.app.dao.SelmItemDao;
import com.wondersgroup.app.dao.SelmItemLinkDao;
import com.wondersgroup.app.dao.SelmItemTypeDao;
import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.dao.SelmAccessApplyDao;
import com.wondersgroup.business.dao.SelmAttachDao;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDao;
import com.wondersgroup.serverApply.bean.SelmDeviceAlink;
import com.wondersgroup.serverApply.bean.SelmServerApply;
import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.bean.SelmServerItem;
import com.wondersgroup.serverApply.dao.SelmDeviceAlinkDao;
import com.wondersgroup.serverApply.dao.SelmServerApplyDao;
import com.wondersgroup.serverApply.dao.SelmServerDlinkDao;
import com.wondersgroup.serverApply.dao.SelmServerItemDao;
import com.wondersgroup.serverApply.service.SelmServerApplyService;
import com.wondersgroup.sms.organ.bean.SmsOrgan;
import com.wondersgroup.sms.organ.dao.SmsOrganDao;
import com.wondersgroup.sms.organ.service.SmsOrganService;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

/**
 * 服务开通申请业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
@SuppressWarnings("all")
public class SelmServerApplyServiceImpl implements SelmServerApplyService {
	
	@Autowired
	private SelmServerApplyDao selmServerApplyDao;
	@Autowired
	private SelmServerItemDao selmServerItemDao;
	@Autowired
	private SelmItemDao selmItemDao;
	@Autowired
	private SelmAccessApplyDao selmAccessApplyDao;
	@Autowired
	private SelmAttachDao selmAttachDao;
	@Autowired
	private SmsUserDao smsUserDao;
	@Autowired
	private SmsRoleDao smsRoleDao;
	@Autowired
	private SmsOrganDao smsOrganDao;
	@Autowired
	private SelmServerDlinkDao selmServerDlinkDao;
	@Autowired
	private InfopubDeviceInfoDao infopubDeviceInfoDao;
	@Autowired
	private SelmDeviceAlinkDao selmDeviceAlinkDao;
	@Autowired
	private SelmDeviceItemDao selmDeviceItemDao;
	@Autowired
	private SelmItemTypeDao selmItemTypeDao;
	@Autowired
	private SelmItemLinkDao selmItemLinkDao;

	/**
	 * 根据主键 {@link SelmServerApply#ST_APPLY_ID}获取服务开通申请
	 * 
	 * @param stApplyId
	 *            服务开通申请主键 {@link SelmServerApply#ST_APPLY_ID}
	 * @return 服务开通申请实例
	 */
	@Override
	public SelmServerApply get(String stApplyId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		return selmServerApplyDao.get(stApplyId);
	}

	/**
	 * 查询服务开通申请列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务开通申请列表
	 */
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String stServerUserName = httpReqRes.getParameter("stServerUserName");
		String stServerNmstatus = httpReqRes.getParameter("searchNameStatus");
		String stApplyOragnName = httpReqRes.getParameter("stServerOragnName");
		String startDate = httpReqRes.getParameter("startDate");
		String endDate = httpReqRes.getParameter("endDate");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		Conditions conds = Conditions.newAndConditions();
		if (stServerUserName != null && !StringUtils.trim(stServerUserName).isEmpty()) {
			conds.add(new Condition("ST_SERVER_USER_NAME", Condition.OT_LIKE, stServerUserName));
		}
		if (stApplyOragnName != null && !StringUtils.trim(stApplyOragnName).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ORGAN_NAME", Condition.OT_LIKE, stApplyOragnName));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(startDate + " 23:59:59")));
		}
		if (endDate != null && !StringUtils.trim(endDate).isEmpty()) {
			conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
					endDate + " 23:59"));
		}
		if (stServerNmstatus != null && !StringUtils.trim(stServerNmstatus).isEmpty()) {
			conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, stServerNmstatus));
		}
		String suffix = "ORDER BY DT_CREATE";
		if (orderName != null) {
			if ("stApplyOrganName".equals(orderName)) {
				suffix = "ORDER BY ST_APPLY_ORGAN_NAME " + orderType.toUpperCase();
			} else if ("stServerUserName".equals(orderName)) {
				suffix = "ORDER BY ST_SERVER_USER_NAME " + orderType.toUpperCase();
			} else if ("stServerUserPhone".equals(orderName)) {
				suffix = "ORDER BY ST_SERVER_USER_PHONE " + orderType.toUpperCase();
			} else if ("stServerUserMobile".equals(orderName)) {
				suffix = "ORDER BY ST_SERVER_USER_MOBILE " + orderType.toUpperCase();
			} else if ("stServerUserEmail".equals(orderName)) {
				suffix = "ORDER BY ST_SERVER_USER_EMAIL " + orderType.toUpperCase();
			} else if ("stServerContent".equals(orderName)) {
				suffix = "ORDER BY ST_SERVER_CONTENT " + orderType.toUpperCase();
			} else if ("dtUpCreate".equals(orderName)) {
				suffix = "ORDER BY DT_UP_CREATE " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			} else if ("nmStatus".equals(orderName)) {
				suffix = "ORDER BY NM_STATUS " + orderType.toUpperCase();
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
		
		List<SelmServerApply> SelmServerApplyList = selmServerApplyDao.query(conds, suffix, pageSize, currentPage);
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(SelmServerApplyList,
							SelmServerApply.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", SelmServerApplyList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", SelmServerApplyList);
		return returnObj;
	}

	/**
	 * 根据主键 {@link SelmServerApply#ST_APPLY_ID}删除服务开通申请
	 * 
	 * @param stApplyId
	 *            服务开通申请主键 {@link SelmServerApply#ST_APPLY_ID}
	 */
	@Override
	public void remove(String stApplyId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		selmServerApplyDao.delete(stApplyId);
	}

	/**
	 * 保存或更新服务开通申请
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 服务开通申请实例
	 */
	@Override
	public SelmServerApply saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		FileItem fileItem = wrapper.getFileItem("file");
		System.out.println(fileItem);
		String fileName = null;
		String fileType = null;
		byte[] file = null;
		int len = 0;
		if(fileItem != null){
			fileName = fileItem.getName();
			fileType = fileItem.getContentType();
			file = ((FileItem) fileItem).get();
			len = file.length;
		}
		// SelmServerApply.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(SelmServerApply.ST_APPLY_ID);
		SelmServerApply oldSelmServerApply = null;
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			oldSelmServerApply = selmServerApplyDao.get(stApplyId);
		}
		if (oldSelmServerApply == null) {// new
			SelmServerApply newSelmServerApply = (SelmServerApply) t4r.toBean(SelmServerApply.class);
			newSelmServerApply.setDtCreate(new Timestamp(System.currentTimeMillis()));
			String stAttachId = UUID.randomUUID().toString();
			newSelmServerApply.setStAttachId(stAttachId);
			newSelmServerApply.setNmStatus(new BigDecimal(0));
			selmServerApplyDao.add(newSelmServerApply);
			SelmAttach attach = new SelmAttach();
			attach.setStAttachId(stAttachId);
			attach.setStLinkTable("SELM_SERVER_APPLY");
			attach.setStAttachType(fileType);
			attach.setStFilename(fileName);
			attach.setStFileSize(Integer.valueOf(len).toString());
			attach.setBlContent(file);
			attach.setBlSmallContent(new byte[]{});
			attach.setStFileType(fileType);
			attach.setDtCreate(new Timestamp(System.currentTimeMillis()));
			selmAccessApplyDao.saveAttach(attach);
			return newSelmServerApply;
		}else {// update
			oldSelmServerApply = (SelmServerApply) t4r.toBean(oldSelmServerApply, SelmServerApply.class);
			selmServerApplyDao.update(oldSelmServerApply);
			return oldSelmServerApply;
		}
	}
	
	@Override
	public void removeList(HttpReqRes httpReqRes) {
		String stApplyId = httpReqRes.getParameter("stApplyId");
		if (stApplyId != null&& !StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_APPLY_ID",Condition.OT_EQUAL,stApplyId));
			selmServerItemDao.delete(conds);
			selmServerDlinkDao.delete(conds);
			selmServerApplyDao.delete(stApplyId);
			return;
		} else {
			throw new NullPointerException("申请ID不能为空");
		}
		
		
	}
	
	@Override
	public JSONObject sreverNoItem(HttpReqRes httpReqRes) {
		String stApplyId = httpReqRes.getParameter("stApplyId");
		String stItemNo = httpReqRes.getParameter("stItemNo");
		String stMainName = httpReqRes.getParameter("stMainName");
		String stOrganName = httpReqRes.getParameter("stOrganId");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		JSONObject returnObj = new JSONObject();
		Conditions conds = Conditions.newAndConditions();
		if (stApplyId != null && !StringUtils.trim(stApplyId).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL,
					stApplyId));
		}
		List<SelmServerItem> selmServerItemList = selmServerItemDao
				.query(conds, null);

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
		for (SelmServerItem selmServerItem : selmServerItemList) {
			String stItemId = selmServerItem.getStItemId();
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
			Conditions condOrgan = Conditions.newOrConditions();
			conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_ORGAN_NAME", Condition.OT_LIKE,
					stOrganName));
			List<SmsOrgan> smsOrganList = smsOrganDao.query(conds, null);
			for (SmsOrgan smsOrgan : smsOrganList) {
				condOrgan.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL,
						smsOrgan.getStOrganId()));
			}
			cond.add(condOrgan);
		}
		String suffix = "ORDER BY DT_CREATE";
		if (orderName != null) {
			if ("stItemNo".equals(orderName)) {
				suffix = "ORDER BY ST_ITEM_NO " + orderType.toUpperCase();
			}else if ("stMainName".equals(orderName)) {
				suffix = "ORDER BY ST_MAIN_NAME " + orderType.toUpperCase();
			}else if ("stOrganId".equals(orderName)) {
				suffix = "ORDER BY ST_ORGAN_ID " + orderType.toUpperCase();
			}
		}
		PaginationArrayList<SelmItem> query = selmItemDao.query(cond, suffix,
				pageSize, currentPage);
		for (SelmItem selmItem : query) {
			String stOrgan = selmItem.getStOrganId();
			SmsOrgan smsOrgan = smsOrganDao.get(stOrgan);
			selmItem.setStOrganId(smsOrgan.getStOrganName());
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
	public void downLoad(HttpReqRes httpReqRes) {
		String stAttachId = httpReqRes.getParameter("stAttachId");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		byte[] attachFildById = selmAttachDao.getAttachFildById(stAttachId);
		SelmAttach selmAttach = selmAttachDao.get(stAttachId);
		BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        String fileName = selmAttach.getStFilename();
        try {
            try {
            	java.io.OutputStream o = httpReqRes.getResponse().getOutputStream();
	             // 设置相应类型
	          httpReqRes.getResponse().setContentType("application/vnd.ms-excel");
				//设置相应头，attachment:以附件的形式进行下载
	          httpReqRes.getResponse().setHeader("content-disposition","attachment;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8"));
	          o.write(attachFildById);
			 } catch (Exception e) {
		      e.printStackTrace();
	}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
	}

	@Override
	public void saveSubmit(HttpReqRes httpReqRes) {
		String stApplyId = 	StringUtils.trim(httpReqRes.getRequest().getParameter("stApplyId"));
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL,stApplyId));
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("NM_STATUS", new BigDecimal(1));
		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
		hashMap2.put("NM_STATUS", new BigDecimal(2));
		HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
		hashMap3.put("NM_STATUS", new BigDecimal(2));
		hashMap3.put("NM_PASS", new BigDecimal(1));
		
		Conditions co0 = null;
		Conditions co1 = null;
				
		int flag = 0;//需要审核的服务
		boolean flag1 = true;
		//找到申请关联的设备
		List<SelmServerDlink> ssd = selmServerDlinkDao.query(conds, null);
		for(SelmServerDlink temp : ssd){
			String deviceId = temp.getStMachineId();
			List<String> itemList = selmServerItemDao.getItemByDevice(deviceId,new BigDecimal(0));
			SelmItem si = null;
			for(String emp : itemList){
				si = selmItemDao.get(emp);
				//判断事项是否有需要审核的事项，不需要审核的事项直接通过
				if("否".equals(si.getStExt2())){
					//更新selmServerItem
					co0 = Conditions.newAndConditions();
					co0.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,deviceId));
					co0.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,emp));
					selmServerItemDao.update(hashMap3, co0);
					//更新selmServerDlink
					if(flag1){
						co1 = Conditions.newAndConditions();
						co1.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL,stApplyId));
						co1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,deviceId));
						selmServerDlinkDao.update(hashMap2, co1);
					}
					//插入SelmDeviceItem
					SelmDeviceItem info = new SelmDeviceItem();
					info.setStItemId(emp);
					info.setStDeviceId(deviceId);
					info.setDtCreate(new Timestamp(System.currentTimeMillis()));
					selmDeviceItemDao.add(info);
				}
				if("是".equals(si.getStExt2())){
					co0 = Conditions.newAndConditions();
					co0.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,deviceId));
					co0.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,emp));
					selmServerItemDao.update(hashMap, co0);
					//更新selmServerDlink
					co1 = Conditions.newAndConditions();
					co1.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL,stApplyId));
					co1.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL,deviceId));
					selmServerDlinkDao.update(hashMap, conds);
					flag+=1;
					flag1 = false;
				}
			}	
		}
		if(flag != 0){
			selmServerApplyDao.update(hashMap, conds);
		}else{
			selmServerApplyDao.update(hashMap2, conds);
		}
	}

	
	/**
	 * 设备列表
	 */
	@Override
	public JSONObject deviceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
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
		
		String stApplyId = httpReqRes.getParameter("stApplyId");
		Conditions conds = Conditions.newAndConditions();
		if (stApplyId != null && !StringUtils.trim(stApplyId).isEmpty()) {
			conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
		}
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<InfopubDeviceInfo> infopubDeviceInfoList = new ArrayList<InfopubDeviceInfo>();
		List<SelmServerDlink> selmServerDlinkList = selmServerDlinkDao.query(conds, suffix, pageSize, currentPage);
		
		for(SelmServerDlink temp : selmServerDlinkList){
			String deviceId = temp.getStMachineId();
			InfopubDeviceInfo info = infopubDeviceInfoDao.get(deviceId);
			Conditions con1 = Conditions.newAndConditions();
			con1.add(new Condition("ST_APPLY_ID",Condition.OT_EQUAL,stApplyId));
			con1.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
			List<SelmServerItem> ssi = selmServerItemDao.query(con1, null);
			int count = ssi.size();
			//暂代申请事项数
			temp.setStAuditUserName(String.valueOf(count));
			//暂代网点
			temp.setStAuditUserId(info.getStDeviceName());
			//暂代详细地址
			temp.setStApplyId(info.getStDeviceAddress());
			//暂代mac
			temp.setStExt1(info.getStDeviceMac());
			//暂代是否绑定证书
			//暂无
			//暂代医保制册机
			temp.setNmStatus(new BigDecimal(0));
			//暂代居住证签注机
			temp.setStExt2(String.valueOf(0));
			//暂代制卡机
			temp.setStDesc(String.valueOf(0));
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmServerDlinkList,
							SelmServerDlink.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmServerDlinkList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmServerDlinkList);
		return returnObj;
	
	}
	
	
	
	@Override
	public JSONObject checkRecordsList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		String stItemId = httpReqRes.getParameter("stItemId");
		String searchDeviceName = httpReqRes.getParameter("searchDeviceName");
		String searchAddress = httpReqRes.getParameter("searchAddress");
		String searchMac = httpReqRes.getParameter("searchMac");
		String startDate = httpReqRes.getParameter("startDate");
		String checkDate = httpReqRes.getParameter("checkDate");
		
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String suffix = "ORDER BY idi.DT_CREATE";
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
		Conditions conds =  Conditions.newAndConditions();
		if (stItemId != null && !StringUtils.trim(stItemId).isEmpty()) {
			conds.add(new Condition("ssi.ST_ITEM_ID", Condition.OT_EQUAL, stItemId));
		}
		if (searchDeviceName != null && !StringUtils.trim(searchDeviceName).isEmpty()) {
			conds.add(new Condition("idi.ST_DEVICE_NAME", Condition.OT_LIKE, searchDeviceName));
		}
		if (searchAddress != null && !StringUtils.trim(searchAddress).isEmpty()) {
			conds.add(new Condition("idi.ST_DEVICE_ADDRESS", Condition.OT_LIKE, searchAddress));
		}
		if (searchMac != null && !StringUtils.trim(searchMac).isEmpty()) {
			conds.add(new Condition("idi.ST_DEVICE_MAC", Condition.OT_LIKE, searchMac));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("ssi.DT_AUDIT", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
			conds.add(new Condition("sdi.DT_AUDIT", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(startDate + " 23:59:59")));
		}
		if (checkDate != null && !StringUtils.trim(checkDate).isEmpty()) {
			conds.add(new Condition("ssi.DT_AUDIT", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(checkDate + " 00:00:00")));
			conds.add(new Condition("ssi.DT_AUDIT", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(checkDate + " 23:59:59")));
		}
		
		conds.add(new Condition("ssi.NM_STATUS", Condition.OT_GREATER,new BigDecimal(1)));
		
		
		List<InfopubDeviceInfo> infoList = infopubDeviceInfoDao.queryItemWithDevice(conds, suffix, pageSize, currentPage);
		for(InfopubDeviceInfo temp : infoList){
			SelmDeviceAlink link = selmDeviceAlinkDao.getAlinkByMachineId(temp.getStDeviceId());
			if(link!=null){
				temp.setStCertKey(null != link.getNmYbzc() ? String.valueOf(link.getNmYbzc()) : String.valueOf(0));
				temp.setStChannel(null != link.getNmJzzqz() ? String.valueOf(link.getNmJzzqz()) : String.valueOf(0));
				temp.setStConfigId(null != link.getNmJzzzk() ? String.valueOf(link.getNmJzzzk()) : String.valueOf(0));
			}else{
				temp.setStCertKey(String.valueOf(0));
				temp.setStChannel(String.valueOf(0));
				temp.setStConfigId(String.valueOf(0));
			}
			
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infoList);
		return returnObj;
	
	}
	
	@Override
	public JSONObject checkdeviceList(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		String stItemId = httpReqRes.getParameter("stItemId");
		String searchDeviceName = httpReqRes.getParameter("searchDeviceName");
		String searchAddress = httpReqRes.getParameter("searchAddress");
		String searchMac = httpReqRes.getParameter("searchMac");
		String startDate = httpReqRes.getParameter("startDate");
		
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		String suffix = "ORDER BY idi.DT_CREATE";
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
		Conditions conds =  Conditions.newAndConditions();
		if (stItemId != null && !StringUtils.trim(stItemId).isEmpty()) {
			conds.add(new Condition("ssi.ST_ITEM_ID", Condition.OT_EQUAL, stItemId));
		}
		if (searchDeviceName != null && !StringUtils.trim(searchDeviceName).isEmpty()) {
			conds.add(new Condition("idi.ST_DEVICE_NAME", Condition.OT_LIKE, searchDeviceName));
		}
		if (searchAddress != null && !StringUtils.trim(searchAddress).isEmpty()) {
			conds.add(new Condition("idi.ST_DEVICE_ADDRESS", Condition.OT_LIKE, searchAddress));
		}
		if (searchMac != null && !StringUtils.trim(searchMac).isEmpty()) {
			conds.add(new Condition("idi.ST_DEVICE_MAC", Condition.OT_LIKE, searchMac));
		}
		if (startDate != null && !StringUtils.trim(startDate).isEmpty()) {
			conds.add(new Condition("ssi.DT_AUDIT", Condition.OT_GREATER_EQUAL,
					Timestamp.valueOf(startDate + " 00:00:00")));
			conds.add(new Condition("sdi.DT_AUDIT", Condition.OT_LESS_EQUAL,
					Timestamp.valueOf(startDate + " 23:59:59")));
		}
		
		conds.add(new Condition("ssi.NM_STATUS", Condition.OT_EQUAL,new BigDecimal(1)));
		
		
		List<InfopubDeviceInfo> infoList = infopubDeviceInfoDao.queryItemWithDevice(conds, suffix, pageSize, currentPage);
		for(InfopubDeviceInfo temp : infoList){
			SelmDeviceAlink link = selmDeviceAlinkDao.getAlinkByMachineId(temp.getStDeviceId());
			if(link!=null){
				temp.setStCertKey(String.valueOf(link.getNmYbzc()));
				temp.setStChannel(String.valueOf(link.getNmJzzqz()));
				temp.setStConfigId(String.valueOf(link.getNmJzzzk()));
			}else{
				temp.setStCertKey(String.valueOf(0));
				temp.setStChannel(String.valueOf(0));
				temp.setStConfigId(String.valueOf(0));
			}
			
		}
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(infoList,
							InfopubDeviceInfo.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", infoList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", infoList);
		return returnObj;
	
	}

	
	/**
	 * 删除服务申请关联的设备
	 */
	@Override
	public void removeDevice(HttpReqRes httpReqRes) {
		String stApplyId = (String) httpReqRes.getRequest().getSession().getAttribute("serverApplyId");
		String stDeviceId = httpReqRes.getParameter("ST_DEVICE_ID");
		if(stApplyId != null && stDeviceId != null 
				&& !StringUtils.trim(stDeviceId).isEmpty() && !StringUtils.trim(stApplyId).isEmpty()){
			selmServerDlinkDao.delete(stApplyId, stDeviceId);
			selmServerItemDao.delete(stDeviceId, stApplyId);
		}
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,stDeviceId));
		conds.add(new Condition("NM_STATUS",Condition.OT_EQUAL,new BigDecimal(0)));
		selmDeviceItemDao.delete(conds);
	}

	@Override
	public JSONObject groupItemData(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
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
		
		String stOrganName = httpReqRes.getParameter("OrganName");
		Conditions conds = Conditions.newAndConditions();
		if(stOrganName != null && !StringUtils.trim(stOrganName).isEmpty()){
			conds.add(new Condition("ST_ITEM_TYPE_NAME" ,Condition.OT_LIKE,stOrganName));
		}
		PaginationArrayList<SelmItemType> selmItemTypeList = selmItemTypeDao.query(conds, suffix, pageSize, currentPage);
		for(SelmItemType empt : selmItemTypeList){
			String sitId = empt.getStItemTypeId();
			Conditions co1= Conditions.newAndConditions();
			co1.add(new Condition("ST_ITEM_TYPE_ID" ,Condition.OT_EQUAL,sitId));
			List<SelmItemLink> selmItemLinkList = selmItemLinkDao.query(co1, null);
			if(selmItemLinkList!=null){
				empt.setStExt2(String.valueOf(selmItemLinkList.size()));
			}else{
				empt.setStExt2(String.valueOf(0));
			}
		}
		
		
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmItemTypeList,
							SelmItemType.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmItemTypeList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmItemTypeList);
		return returnObj;
	}
	
	
	
	@Override
	public JSONObject applyItemlist(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String searchItemName = StringUtils.trim(httpReqRes.getParameter("searchItemName"));
		String searchItemCode = StringUtils.trim(httpReqRes.getParameter("searchItemCode"));
		String searchOrganName = StringUtils.trim(httpReqRes.getParameter("searchOrganName"));
		String user = httpReqRes.getParameter("user");
		//用户
		SmsUser sUser = smsUserDao.getUserName(user);
		Conditions coo = Conditions.newAndConditions();
		coo.add(new Condition("su.ST_LOGIN_NAME", Condition.OT_LIKE, user));
		//角色
		SmsRole sRole = smsRoleDao.queryRoleByUser(coo, null);
		String roleCode = sRole.getStRoleCode();
		

		Conditions conds = Conditions.newAndConditions();
		if (searchItemName != null && !StringUtils.trim(searchItemName).isEmpty()) {
			conds.add(new Condition("ST_MAIN_NAME", Condition.OT_LIKE, searchItemName));
		}
		if (searchItemCode != null && !StringUtils.trim(searchItemCode).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, searchItemCode));
		}
		if("organ" == roleCode || "organ".equals(roleCode)){
			conds.add(new Condition("ST_ORGAN_ID", Condition.OT_LIKE, sUser.getStOrganId()));
		}/*else if("area" == roleCode || "area".equals(roleCode)){
			conds.add(new Condition("ST_AREA_ID", Condition.OT_LIKE, sUser.getStAreaId()));
		}*/
		if (searchOrganName != null && !StringUtils.trim(searchOrganName).isEmpty()) {
			SmsOrgan so = smsOrganDao.getByName(searchOrganName);
			if(so != null){
				conds.add(new Condition("ST_ORGAN_ID", Condition.OT_LIKE, so.getStOrganId()));
			}
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
		
		
		List<SelmItem> selmItemList = selmItemDao.query(conds, suffix, pageSize, currentPage);
		for(SelmItem temp : selmItemList){
			try{
				String organID = temp.getStOrganId();
				SmsOrgan so = smsOrganDao.get(organID);
				//暂代部门
				temp.setStExt1(so.getStOrganName());
				//暂代申请设备数
				String itemId = temp.getStItemId();
				Conditions co1 = Conditions.newAndConditions();
				co1.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,itemId));
				co1.add(new Condition("NM_STATUS",Condition.OT_GREATER_EQUAL,new BigDecimal(1)));
				List<SelmServerItem> ssi = selmServerItemDao.query(co1, null);
				temp.setStExt2(String.valueOf(ssi.size()));
				//暂代未审核设备数
				Conditions co2 = Conditions.newAndConditions();
				co2.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,itemId));
				co2.add(new Condition("NM_STATUS",Condition.OT_EQUAL,new BigDecimal(1)));
				List<SelmServerItem> ssi1 = selmServerItemDao.query(co2, null);
				temp.setStExt3(String.valueOf(ssi1.size()));
				//更新时间
				Conditions co3 = Conditions.newAndConditions();
				co3.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,itemId));
				List<SelmServerItem> ssi3 = selmServerItemDao.query(co3, "ORDER BY DT_AUDIT DESC");
				temp.setDtUpdate(ssi3.get(0).getDtAudit());
			} catch (Exception e) {
				Log.debug(e.getMessage().toString());
			}

		}
		
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmItemList,
							SelmItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmItemList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmItemList);
		return returnObj;
	}
	
	
	@Override
	public JSONObject checkDeviceWithItem(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		
		String searchItemName = httpReqRes.getParameter("searchItemName");
		String searchItemCode = httpReqRes.getParameter("searchItemCode");
		String searchStatus = httpReqRes.getParameter("searchStatus");
		String searchOrganName = httpReqRes.getParameter("searchOrganName");
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
		if (searchItemName != null && !StringUtils.trim(searchItemName).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NAME", Condition.OT_LIKE, searchItemName));
		}
		if (searchItemCode != null && !StringUtils.trim(searchItemCode).isEmpty()) {
			conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE, searchItemCode));
		}
		if (searchOrganName != null && !StringUtils.trim(searchOrganName).isEmpty()) {
			conds.add(new Condition("ST_ORGAN_ID", Condition.OT_LIKE, searchOrganName));	
		}
		if (searchStatus != null && !StringUtils.trim(searchStatus).isEmpty()) {
			conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, searchStatus));	
		}
		
		String suffix = "ORDER BY DT_AUDIT";
		
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
		
		List<SelmServerItem> ssi = selmServerItemDao.query(conds, suffix, pageSize, currentPage);
		
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(ssi,
							SelmServerItem.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", ssi.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", ssi);
		return returnObj;
	}

	
	/**
	 * 不通过
	 */
	@Override
	public int saveNoPass(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stItemId = httpReqRes.getParameter("stItemId");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		//SelmServerItem更新
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("NM_STATUS", new BigDecimal(3));//不通过
		map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
		map.put("NM_PASS", new BigDecimal(0));
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,stDeviceId));
		conds.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
		int i = selmServerItemDao.update(map, conds);
		
		//设置申请
		Conditions co1 = Conditions.newAndConditions();
		co1.add(new Condition("ST_MACHINE_ID",Condition.OT_LIKE,stDeviceId));
		SelmServerDlink sdd = selmServerDlinkDao.query(co1, null).get(0);
		String applyId = sdd.getStApplyId();
		String deviceId = sdd.getStMachineId();
		Conditions co2 = Conditions.newAndConditions();
		co2.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
		co2.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(1)));
		List<SelmServerItem> ssi = selmServerItemDao.query(co2, null);
		Conditions co3 = Conditions.newAndConditions();
		co3.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
		co3.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(0)));
		List<SelmServerItem> allSsi = selmServerItemDao.query(co3, null);
		if(ssi.size() == allSsi.size()){
			//已完成
			Conditions co4 = Conditions.newAndConditions();
			co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("NM_STATUS", new BigDecimal(2));//部分完成
			selmServerApplyDao.update(map1, co4);
		}else if(ssi.size() < allSsi.size()){
			//部分完成
			Conditions co4 = Conditions.newAndConditions();
			co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("NM_STATUS", new BigDecimal(3));//部分完成
			selmServerApplyDao.update(map1, co4);
		}

		return i;
	}

	
	/**
	 * 通过
	 */
	@Override
	public int savePass(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stItemId = httpReqRes.getParameter("stItemId");
		Map<String, Object> map = new HashMap<String, Object>();
		//更新selm_server_item
		map.put("NM_STATUS", new BigDecimal(2));//通过
		map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
		map.put("NM_PASS", new BigDecimal(1));
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,stDeviceId));
		conds.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
		int i = selmServerItemDao.update(map, conds);
		//插入selm_device_item
		List<SelmDeviceItem> sdiList = selmDeviceItemDao.query(conds, null);
		if(null == sdiList || sdiList.size() == 0){
			SelmDeviceItem info = new SelmDeviceItem();
			info.setStItemId(stItemId);
			info.setStDeviceId(stDeviceId);
			info.setDtCreate(new Timestamp(System.currentTimeMillis()));
			selmDeviceItemDao.add(info);
		}
		
		
		//设置申请
		Conditions co1 = Conditions.newAndConditions();
		co1.add(new Condition("ST_MACHINE_ID",Condition.OT_LIKE,stDeviceId));
		SelmServerDlink sdd = selmServerDlinkDao.query(co1, null).get(0);
		String applyId = sdd.getStApplyId();
		String deviceId = sdd.getStMachineId();
		Conditions co2 = Conditions.newAndConditions();
		co2.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
		co2.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(1)));
		List<SelmServerItem> ssi = selmServerItemDao.query(co2, null);
		Conditions co3 = Conditions.newAndConditions();
		co3.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
		co3.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(0)));
		List<SelmServerItem> allSsi = selmServerItemDao.query(co3, null);
		if(ssi.size() == allSsi.size()){
			//已完成
			Conditions co4 = Conditions.newAndConditions();
			co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("NM_STATUS", new BigDecimal(2));//部分完成
			selmServerApplyDao.update(map1, co4);
		}else if(ssi.size() < allSsi.size()){
			//部分完成
			Conditions co4 = Conditions.newAndConditions();
			co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("NM_STATUS", new BigDecimal(3));//部分完成
			selmServerApplyDao.update(map1, co4);
		}
		
		return i;
	}

	

	
	/**
	 * 批量通过
	 */
	@Override
	public int batchPass(HttpReqRes httpReqRes) {
		String[] stDeviceIdList = httpReqRes.getRequest().getParameterValues(
				"stDeviceArray[]");
		String stItemId = httpReqRes.getParameter("stItemId");
		int i = 0;
		Conditions conds = null;
		Conditions co1 = null;
		Conditions co2 = null;
		Conditions co3 = null;
		Conditions co4 = null;
		if(stDeviceIdList != null && stDeviceIdList.length >= 1){
			for(String temp : stDeviceIdList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("NM_STATUS", new BigDecimal(2));//通过
				map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
				map.put("NM_PASS", new BigDecimal(1));
				conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,temp));
				conds.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
				int w = selmServerItemDao.update(map, conds);
				i+=w;
				//插入selm_device_item
				List<SelmDeviceItem> sdiList = selmDeviceItemDao.query(conds, null);
				if(null == sdiList || sdiList.size() == 0){
					SelmDeviceItem info = new SelmDeviceItem();
					info.setStItemId(stItemId);
					info.setStDeviceId(temp);
					info.setDtCreate(new Timestamp(System.currentTimeMillis()));
					selmDeviceItemDao.add(info);
				}
				
				//设置申请
				co1 = Conditions.newAndConditions();
				co1.add(new Condition("ST_MACHINE_ID",Condition.OT_LIKE,temp));
				SelmServerDlink sdd = selmServerDlinkDao.query(co1, null).get(0);
				String applyId = sdd.getStApplyId();
				String deviceId = sdd.getStMachineId();
				co2 = Conditions.newAndConditions();
				co2.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
				co2.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(1)));
				List<SelmServerItem> ssi = selmServerItemDao.query(co2, null);
				co3 = Conditions.newAndConditions();
				co3.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
				co3.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(0)));
				List<SelmServerItem> allSsi = selmServerItemDao.query(co3, null);
				if(ssi.size() == allSsi.size()){
					//已完成
					co4 = Conditions.newAndConditions();
					co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("NM_STATUS", new BigDecimal(2));//部分完成
					selmServerApplyDao.update(map1, co4);
				}else if(ssi.size() < allSsi.size()){
					//部分完成
					co4 = Conditions.newAndConditions();
					co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("NM_STATUS", new BigDecimal(3));//部分完成
					selmServerApplyDao.update(map1, co4);
				}
				
			}
		}
		
		return i;
	}
	
	
	/**
	 * 批量不通过
	 */
	@Override
	public int batchNoPass(HttpReqRes httpReqRes) {
		String[] stDeviceIdList = httpReqRes.getRequest().getParameterValues("stDeviceArray[]");
		String stItemId = httpReqRes.getParameter("stItemId");
		int i = 0;
		if(stDeviceIdList != null && stDeviceIdList.length >= 1){
			for(String temp : stDeviceIdList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("NM_STATUS", new BigDecimal(3));//不通过
				map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
				map.put("NM_PASS", new BigDecimal(0));
				map.put("ST_REASON", new String("批量不通过"));
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,temp));
				conds.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
				int w = selmServerItemDao.update(map, conds);
				i+=w;
				
				//设置申请
				Conditions co1 = Conditions.newAndConditions();
				co1.add(new Condition("ST_MACHINE_ID",Condition.OT_LIKE,temp));
				SelmServerDlink sdd = selmServerDlinkDao.query(co1, null).get(0);
				String applyId = sdd.getStApplyId();
				String deviceId = sdd.getStMachineId();
				Conditions co2 = Conditions.newAndConditions();
				co2.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
				co2.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(1)));
				List<SelmServerItem> ssi = selmServerItemDao.query(co2, null);
				Conditions co3 = Conditions.newAndConditions();
				co3.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,deviceId));
				co3.add(new Condition("NM_STATUS",Condition.OT_GREATER,new BigDecimal(0)));
				List<SelmServerItem> allSsi = selmServerItemDao.query(co3, null);
				if(ssi.size() == allSsi.size()){
					//已完成
					Conditions co4 = Conditions.newAndConditions();
					co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("NM_STATUS", new BigDecimal(2));//部分完成
					selmServerApplyDao.update(map1, co4);
				}else if(ssi.size() < allSsi.size()){
					//部分完成
					Conditions co4 = Conditions.newAndConditions();
					co4.add(new Condition("ST_APPLY_ID",Condition.OT_LIKE,applyId));
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("NM_STATUS", new BigDecimal(3));//部分完成
					selmServerApplyDao.update(map1, co4);
				}
				
			}
		}
		
		return i;
	}
	
	/**
	 * 保存不通过原因
	 */
	@Override
	public int saveNoPassReason(HttpReqRes httpReqRes) {
		String stDeviceId = httpReqRes.getParameter("stDeviceId");
		String stItemId = httpReqRes.getParameter("stItemId");
		String stApplyId = httpReqRes.getParameter("stApplyId");
		String result = httpReqRes.getParameter("stResult");
		System.out.println("不通过原因："+result);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ST_REASON", result);
		map.put("NM_STATUS", new BigDecimal(3));//不通过
		map.put("DT_AUDIT", new Timestamp(System.currentTimeMillis()));
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID",Condition.OT_EQUAL,stDeviceId));
		conds.add(new Condition("ST_ITEM_ID",Condition.OT_EQUAL,stItemId));
		conds.add(new Condition("ST_APPLY_ID",Condition.OT_EQUAL,stApplyId));
		int i = selmServerItemDao.update(map, conds);
		return i;
	}
	
	
	
	
}
