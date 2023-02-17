package com.wondersgroup.selfapi.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import reindeer.base.utils.AciHelper;

import com.wondersgroup.common.utils.MessyCodeUtil;
import com.wondersgroup.selfapi.bean.MachineInfo;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.bean.SelmAttach;

import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelfUsingHistoryDao {
	
	private Connection con = null;
	
	public SelfUsingHistoryDao(){}
	
	public SelfUsingHistoryDao(Connection con){
		this.con = con;
	}
	
	public int add(SelfUsingHistory selfUsingHistory){
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		RecordSet rs;
		String sql = "INSERT INTO "+table+" (" +
				"ST_QUERY_HIS_ID," +
				"ST_MACHINE_ID," +
				"ST_ASSIST_ID," +
				"ST_MODULE_NAME," +
				"ST_MODULE_OP," +
				"ST_ITEM_NAME," +
				"ST_ITEM_NO," +
				"ST_NAME," +
				"ST_IDENTITY_NO," +
				"ST_MOBILE," +
				"ST_BUSINESS_NO," +
				"ST_SUBMIT_DATA_ID," +
				"ST_DESC," +
				"ST_OP_RESULT," +
				"DT_CREATE," +
				"ST_ATTACH_ID1," +
				"ST_ATTACH_ID2," +
				"ST_ATTACH_ID3," +
				"ST_ATTACH_ID4," +
				"ST_EXT1," +
				"ST_EXT2," +
				"ST_EXT3," +
				"ST_EXT4," +
				"ST_EXT5) VALUES (?,?,?,?,?,?,?,N'"+selfUsingHistory.getStName()+"',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
				selfUsingHistory.getStHisId(),
				selfUsingHistory.getStMachineId(),
				selfUsingHistory.getStAssistId(),
				selfUsingHistory.getStModuleName(),
				selfUsingHistory.getStModuleOp(),
				selfUsingHistory.getStItemName(),
				selfUsingHistory.getStItemNo(),
//				selfUsingHistory.getStName(),
				selfUsingHistory.getStIdentityNo(),
				selfUsingHistory.getStMobile(),
				selfUsingHistory.getStBusinessNo(),
				selfUsingHistory.getStSubmitDataId(),
				selfUsingHistory.getStDesc(),
				selfUsingHistory.getStOpResult(),
				selfUsingHistory.getDtCreate(),
				selfUsingHistory.getStAttachId1(),
				selfUsingHistory.getStAttachId2(),
				selfUsingHistory.getStAttachId3(),
				selfUsingHistory.getStAttachId4(),
				selfUsingHistory.getStExt1(),
				selfUsingHistory.getStExt2(),
				selfUsingHistory.getStExt3(),
				selfUsingHistory.getStExt4(),
				selfUsingHistory.getStExt5(),
		};
        if (con == null) {
            rs = SQL.execute(sql, obj);
        } else {
            rs = SQL.execute(con, sql, obj);
        }
        return rs.TOTAL_RECORD_COUNT;
	}
	
	public int addEx(SelfUsingHistory selfUsingHistory){
		RecordSet rs;
		String sql = "INSERT INTO SELM_QUERY_HIS_EX (" +
				"ST_QUERY_HIS_ID," +
				"ST_MACHINE_ID," +
				"ST_ASSIST_ID," +
				"ST_MODULE_NAME," +
				"ST_MODULE_OP," +
				"ST_ITEM_NAME," +
				"ST_ITEM_NO," +
				"ST_NAME," +
				"ST_IDENTITY_NO," +
				"ST_MOBILE," +
				"ST_BUSINESS_NO," +
				"ST_SUBMIT_DATA_ID," +
				"ST_DESC," +
				"ST_OP_RESULT," +
				"DT_CREATE," +
				"ST_ATTACH_ID1," +
				"ST_ATTACH_ID2," +
				"ST_ATTACH_ID3," +
				"ST_ATTACH_ID4," +
				"ST_EXT1," +
				"ST_EXT2," +
				"ST_EXT3," +
				"ST_EXT4," +
				"ST_EXT5) VALUES (?,?,?,?,?,?,?,N'"+selfUsingHistory.getStName()+"',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
				selfUsingHistory.getStHisId(),
				selfUsingHistory.getStMachineId(),
				selfUsingHistory.getStAssistId(),
				selfUsingHistory.getStModuleName(),
				selfUsingHistory.getStModuleOp(),
				selfUsingHistory.getStItemName(),
				selfUsingHistory.getStItemNo(),
//				selfUsingHistory.getStName(),
				selfUsingHistory.getStIdentityNo(),
				selfUsingHistory.getStMobile(),
				selfUsingHistory.getStBusinessNo(),
				selfUsingHistory.getStSubmitDataId(),
				selfUsingHistory.getStDesc(),
				selfUsingHistory.getStOpResult(),
				selfUsingHistory.getDtCreate(),
				selfUsingHistory.getStAttachId1(),
				selfUsingHistory.getStAttachId2(),
				selfUsingHistory.getStAttachId3(),
				selfUsingHistory.getStAttachId4(),
				selfUsingHistory.getStExt1(),
				selfUsingHistory.getStExt2(),
				selfUsingHistory.getStExt3(),
				selfUsingHistory.getStExt4(),
				selfUsingHistory.getStExt5(),
		};
        if (con == null) {
            rs = SQL.execute(sql, obj);
        } else {
            rs = SQL.execute(con, sql, obj);
        }
        return rs.TOTAL_RECORD_COUNT;
	}
	
	public JSONArray countUsingHistory(String start, String end){
		String sql = " select ST_MACHINE_ID,ST_MODULE_NAME,COUNT(1) as num1,"
					+" (SELECT COUNT(1) FROM SELM_QUERY_HIS b WHERE a.ST_MACHINE_ID=b.ST_MACHINE_ID) num2 "
					+" from SELM_QUERY_HIS a"
					+" where DT_CREATE >= ?"
					+" and DT_CREATE <= ?"
					+" GROUP BY ST_MACHINE_ID,ST_MODULE_NAME";
		RecordSet rs = SQL.execute(sql, new Object[] { start, end });
		JSONArray arr = new JSONArray();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.put("ST_MACHINE_ID",rs.getOriginalString("ST_MACHINE_ID"));
			json.put("ST_MODULE_NAME",rs.getOriginalString("ST_MODULE_NAME"));
			json.put("moduleCount",rs.getOriginalString("num1"));
			json.put("machineCount",rs.getOriginalString("num2"));
			arr.add(json);
		}
		return arr;
	}

	public JSONArray getAllMachinesAndTimes(String start, String end) {
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		String sql = " SELECT ST_MACHINE_ID,COUNT(*) AS COUNT FROM "+table+" " +
				     " WHERE DT_CREATE >= ? " +
				     " AND DT_CREATE <= ?" +
				     " GROUP BY ST_MACHINE_ID";
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, new Object[] { start, end });
        } else {
        	rs = SQL.execute(con, sql, new Object[] { start, end });
        }
        JSONArray arr = new JSONArray();
        while(rs.next()){
        	JSONObject json = new JSONObject();
			json.put("ST_MACHINE_ID",rs.getOriginalString("ST_MACHINE_ID"));
			json.put("COUNT",rs.getOriginalString("COUNT"));
			arr.add(json);
        }
		return arr;
	}

	public JSONArray getModuleTimesInMachine(String machineID, String start,
			String end) {
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		String sql = " SELECT ST_MODULE_NAME,COUNT(*) AS COUNT FROM "+table+" WHERE ST_MACHINE_ID = ? " +
				     " AND DT_CREATE >= ? AND DT_CREATE <= ? " +
				     " GROUP BY ST_MODULE_NAME";
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, new Object[] { machineID, start, end });
        } else {
        	rs = SQL.execute(con, sql, new Object[] { machineID, start, end });
        }
        JSONArray arr = new JSONArray();
        while(rs.next()){
        	JSONObject json = new JSONObject();
			json.put("ST_MODULE_NAME",rs.getOriginalString("ST_MODULE_NAME"));
			json.put("COUNT",rs.getOriginalString("COUNT"));
			System.out.println(json.toString());
			arr.add(json);
        }
		return arr;
	}
	
	public List<SelfUsingHistory> queryUsingHistoryList(String startTime,
			String endTime) {
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		String sql = " SELECT sqh.* FROM "+table+" sqh "
					+" LEFT JOIN SELM_ITEM item ON item.ST_MAIN_NAME = sqh.ST_ITEM_NAME "
					+" LEFT JOIN SELM_ITEM_LINK sil ON sil.ST_ITEM_ID = item.ST_ITEM_ID"
					+" WHERE ((sqh.ST_MODULE_OP IN ('办理','查询+打印','打印','申领','补领','挂失') "
					+" AND sqh.ST_NAME IS NOT NULL AND sqh.ST_IDENTITY_NO IS NOT NULL) "
					+" OR  (sqh.ST_MODULE_OP IN ('查询')) ) "
					+" AND sqh.DT_CREATE >= ? AND sqh.DT_CREATE <= ? "
					+" AND sqh.ST_MODULE_NAME IS NOT NULL "
					+" AND sqh.ST_ITEM_NAME IS NOT NULL "
					+" AND sil.ST_ITEM_TYPE_ID = 'a6a0ea20-66b6-4d30-98b7-e02bc74e64c9' "
					+" union all "
					+" SELECT sqh.* FROM "+table+" sqh "
					+" where sqh.ST_MODULE_NAME = '我的证照' "
					+" AND sqh.ST_NAME IS NOT NULL AND sqh.ST_IDENTITY_NO IS NOT NULL"
					+" AND sqh.DT_CREATE >= ? AND sqh.DT_CREATE <= ? "
					+" AND sqh.ST_ITEM_NAME IS NOT NULL ";
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, new Object[] { startTime, endTime, startTime, endTime });
        } else {
        	rs = SQL.execute(con, sql, new Object[] { startTime, endTime, startTime, endTime });
        }
        List<SelfUsingHistory> list = new ArrayList<SelfUsingHistory>();
        while(rs.next()){
        	SelfUsingHistory hisInfo = new SelfUsingHistory();
        	hisInfo.setStHisId(rs.getOriginalString("ST_QUERY_HIS_ID"));
        	hisInfo.setStMachineId(rs.getOriginalString("ST_MACHINE_ID"));
//        	hisInfo.setStAssistId(rs.getOriginalString("ST_ASSIST_ID"));
        	hisInfo.setStModuleName(rs.getOriginalString("ST_MODULE_NAME"));
        	hisInfo.setStModuleOp(rs.getOriginalString("ST_MODULE_OP"));
        	hisInfo.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
        	hisInfo.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
        	hisInfo.setStName(rs.getOriginalString("ST_NAME"));
        	hisInfo.setStIdentityNo(rs.getOriginalString("ST_IDENTITY_NO"));
        	hisInfo.setStMobile(rs.getOriginalString("ST_MOBILE"));
        	hisInfo.setStBusinessNo(rs.getOriginalString("ST_BUSINESS_NO"));
        	hisInfo.setStSubmitDataId(rs.getOriginalString("ST_SUBMIT_DATA_ID"));
        	hisInfo.setStDesc(rs.getOriginalString("ST_DESC"));
        	hisInfo.setStOpResult(rs.getOriginalString("ST_OP_RESULT"));
        	Timestamp createTime = rs.getTimestamp("DT_CREATE");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(new Date(createTime.getTime())); 
			hisInfo.setDtCreaterStr(str);
			
        	hisInfo.setStAttachId1(rs.getOriginalString("ST_ATTACH_ID1"));
        	hisInfo.setStAttachId2(rs.getOriginalString("ST_ATTACH_ID2"));
        	hisInfo.setStAttachId3(rs.getOriginalString("ST_ATTACH_ID3"));
        	hisInfo.setStAttachId4(rs.getOriginalString("ST_ATTACH_ID4"));
//        	hisInfo.setStExt1(rs.getOriginalString("ST_EXT1"));
        	hisInfo.setStExt1("");
        	hisInfo.setStExt2(rs.getOriginalString("ST_EXT2"));
        	hisInfo.setStExt3(rs.getOriginalString("ST_EXT3"));
        	hisInfo.setStExt4(rs.getOriginalString("ST_EXT4"));
        	hisInfo.setStExt5(rs.getOriginalString("ST_EXT5"));
        	list.add(hisInfo);
        }
		return list;
	}

	public void saveAttach(SelmAttach attach) {
		String table = AciHelper.getTable("SELM_ATTACH");
		if(attach.getBlContent() == null){
			String sql = "INSERT INTO "+table+" (ST_ATTACH_ID," +
					"ST_LINK_TABLE," +
					"ST_LINK_ID," +
					"ST_ATTACH_TYPE," +
					"ST_FILENAME," +
					"ST_FILE_SIZE," +
					"CL_CONTENT," +
					"ST_FILE_TYPE," +
					"NM_ORDER," +
					"DT_CREATE," +
					"DT_UPDATE," +
					"ST_EXT1," +
					"ST_EXT2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] obj = {
					attach.getStAttachid(),
					attach.getStLinkTable(),
					attach.getStLinkId(),
					attach.getStAttachType(),
					attach.getStFileName(),
					attach.getStFileSize(),
					attach.getClContent(),
					attach.getStFileType(),
					attach.getNmOrder(),
					attach.getDtCreate(),
					attach.getDtUpdate(),
					attach.getStExt1(),
					attach.getStExt2()
				};
	        if (con == null) {
	            SQL.execute(sql, obj);
	        } else {
	            SQL.execute(con, sql, obj);
	        }
		} else {
			String sql = "INSERT INTO "+table+" (ST_ATTACH_ID," +
					"ST_LINK_TABLE," +
					"ST_LINK_ID," +
					"ST_ATTACH_TYPE," +
					"ST_FILENAME," +
					"ST_FILE_SIZE," +
					"CL_CONTENT," +
					"BL_CONTENT," +
					"BL_SMALL_CONTENT," +
					"ST_FILE_TYPE," +
					"NM_ORDER," +
					"DT_CREATE," +
					"DT_UPDATE," +
					"ST_EXT1," +
					"ST_EXT2) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] obj = {
					attach.getStAttachid(),
					attach.getStLinkTable(),
					attach.getStLinkId(),
					attach.getStAttachType(),
					attach.getStFileName(),
					attach.getStFileSize(),
					attach.getClContent(),
					attach.getBlContent(),
					attach.getBlSmallContent(),
					attach.getStFileType(),
					attach.getNmOrder(),
					attach.getDtCreate(),
					attach.getDtUpdate(),
					attach.getStExt1(),
					attach.getStExt2()
				};
	        if (con == null) {
	            SQL.execute(sql, obj);
	        } else {
	            SQL.execute(con, sql, obj);
	        }
		}
	}
	
	public String getBusinessResultStringById(String attachId) {
		String table = AciHelper.getTable("SELM_ATTACH");
		String  sql = "SELECT * FROM "+table+" WHERE ST_ATTACH_ID = ?";
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, new Object[]{attachId});
        } else {
        	rs = SQL.execute(con, sql, new Object[]{attachId});
        }
        String clContent = "";
        while(rs.next()){
        	clContent = rs.getString("CL_CONTENT");
        }
		return clContent;
	}

	public byte[] getAttchFileById(String attachId) {
		String table = AciHelper.getTable("SELM_ATTACH");
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL,attachId));
		byte[] blContent = BlobHelper.getBlob(table, "BL_CONTENT",
				conds.toString(), conds.getObjectArray());
		return blContent;
	}

	public SelfUsingHistory queryUsingHistoryById(String linkId) {
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		String sql = " SELECT * FROM "+table+" WHERE ST_QUERY_HIS_ID = ? ";
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, new Object[] { linkId });
        } else {
        	rs = SQL.execute(con, sql, new Object[] { linkId });
        }
        List<SelfUsingHistory> list = new ArrayList<SelfUsingHistory>();
        while(rs.next()){
        	SelfUsingHistory hisInfo = setInfo(rs);
        	list.add(hisInfo);
        }
		return list.size()>0 ? list.get(0) : null;
	}
	
	public int update(String feild,Conditions conds,String linkId) {
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		String sql = " UPDATE "+table+" SET "+feild+" WHERE "+conds.toString();
		return SQL.execute(sql,new Object[]{linkId}).TOTAL_RECORD_COUNT;
	}
	
	public MachineInfo getmachineInfoByMAC(String machineMAC){
		String  sql = " SELECT i.ST_DEVICE_ID,i.ST_DEVICE_MAC,i.ST_DEVICE_ADDRESS,a.ST_LABEL,a.ST_CITY,a.ST_DISTRICT," +
				" a.ST_STREET,a.ST_ADDRESS FROM INFOPUB_DEVICE_INFO i" +
				" LEFT JOIN INFOPUB_ADDRESS a ON i.ST_ADDRESS_ID = a.ST_ADDRESS_ID " +
				" LEFT JOIN SMS_ORGAN so ON so.ST_ORGAN_ID = i.ST_ORGAN_ID" +
				" WHERE so.ST_ORGAN_NAME = '市民政局' ";
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotEmpty(machineMAC)){
			sql += " AND I.ST_DEVICE_MAC = ?";
			params.add(machineMAC);
		}
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, params.toArray());
        } else {
        	rs = SQL.execute(con, sql, params.toArray());
        }
        List<MachineInfo> list = new ArrayList<MachineInfo>();
        while(rs.next()){
        	MachineInfo info = new MachineInfo();
        	info.setStMachineId(rs.getOriginalString("ST_DEVICE_ID"));
        	info.setStMachineMAC(rs.getOriginalString("ST_DEVICE_MAC"));
        	info.setStMachineAddress(rs.getOriginalString("ST_DEVICE_ADDRESS"));
        	info.setStLabel(rs.getOriginalString("ST_LABEL"));
        	info.setStCity(rs.getOriginalString("ST_CITY"));
        	info.setStDistrict(rs.getOriginalString("ST_DISTRICT"));
        	info.setStStreet(rs.getOriginalString("ST_STREET"));
        	info.setStAddress(rs.getOriginalString("ST_ADDRESS"));
        	list.add(info);
        }
        return list.size() <= 0 ? null : list.get(0);
	}
	
	public String queryIdentityInfo(String idCard) throws UnsupportedEncodingException{
		String sql = "select ST_IDENTITY_NO,ST_NAME from selm_query_his where ST_IDENTITY_NO = ?";
		RecordSet rs;
        if (con == null) {
        	rs = SQL.execute(sql, new Object[]{idCard});
        } else {
        	rs = SQL.execute(con, sql, new Object[]{idCard});
        }
        String name = "";
        while(rs.next()){
        	String str = rs.getOriginalString("ST_NAME");
        	if(!MessyCodeUtil.isMessyCode(str)){
        		name = str;
        		break;
        	}
        }
        return name;
	}
	
	private static SelfUsingHistory setInfo(RecordSet rs){
		SelfUsingHistory hisInfo = new SelfUsingHistory();
    	hisInfo.setStHisId(rs.getOriginalString("ST_QUERY_HIS_ID"));
    	hisInfo.setStMachineId(rs.getOriginalString("ST_MACHINE_ID"));
    	hisInfo.setStAssistId(rs.getOriginalString("ST_ASSIST_ID"));
    	hisInfo.setStModuleName(rs.getOriginalString("ST_MODULE_NAME"));
    	hisInfo.setStModuleOp(rs.getOriginalString("ST_MODULE_OP"));
    	hisInfo.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
    	hisInfo.setStName(rs.getOriginalString("ST_NAME"));
    	hisInfo.setStIdentityNo(rs.getOriginalString("ST_IDENTITY_NO"));
    	hisInfo.setStMobile(rs.getOriginalString("ST_MOBILE"));
    	hisInfo.setStBusinessNo(rs.getOriginalString("ST_BUSINESS_NO"));
    	hisInfo.setStSubmitDataId(rs.getOriginalString("ST_SUBMIT_DATA_ID"));
    	hisInfo.setStDesc(rs.getOriginalString("ST_DESC"));
    	hisInfo.setStOpResult(rs.getOriginalString("ST_OP_RESULT"));
//    	hisInfo.setDtCreate(rs.getTimestamp("DT_CREATE"));
    	Timestamp createTime = rs.getTimestamp("DT_CREATE");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(new Date(createTime.getTime())); 
		hisInfo.setDtCreaterStr(str);
		
    	hisInfo.setStAttachId1(rs.getOriginalString("ST_ATTACH_ID1"));
    	hisInfo.setStAttachId2(rs.getOriginalString("ST_ATTACH_ID2"));
    	hisInfo.setStAttachId3(rs.getOriginalString("ST_ATTACH_ID3"));
    	hisInfo.setStAttachId4(rs.getOriginalString("ST_ATTACH_ID4"));
    	hisInfo.setStExt1(rs.getOriginalString("ST_EXT1"));
    	hisInfo.setStExt2(rs.getOriginalString("ST_EXT2"));
    	hisInfo.setStExt3(rs.getOriginalString("ST_EXT3"));
    	hisInfo.setStExt4(rs.getOriginalString("ST_EXT4"));
    	hisInfo.setStExt5(rs.getOriginalString("ST_EXT5"));
    	
    	return hisInfo;
	}

}
