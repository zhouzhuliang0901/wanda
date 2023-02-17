package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.selfapi.bean.WorkApplyInfo;

/**
 * 办理信息表
 * @author Administrator
 */
@Repository
public class WorkApplyInfoDao {
	
	private Connection con = null;
	
	public WorkApplyInfoDao() {
	}

	public WorkApplyInfoDao(Connection con) {
		this.con = con;
	}

	public  void add(WorkApplyInfo info) {
		String sql ="insert into WORK_APPLY_INFO " +
				"(ST_APPLY_ID, ST_APPLY_NO, ST_BUSINESS_NO, ST_BUSINESS_ID, " +
				"ST_ITEM_ID, ST_USER_ID, ST_LOGIN_NAME, ST_WINDOW_NO, ST_USER_NAME, " +
				"ST_MOBILE, NM_IDENTITY_TYPE, ST_IDENTITY_NO, ST_DEAL_USER_NAME, ST_DEAL_MOBILE, " +
				"NM_DEAL_IDENTITY_TYPE, ST_DEAL_IDENTITY_NO, DT_START_TIME, DT_CONFIRM_TIME, NM_COST, NM_TOLL, ST_UNIT, " +
				"ST_ORGANIZATION_CODE, ST_CONTACT_PHONE, DT_FINISH, ST_STATUS, ST_INFORMATION, NM_FINAL_STATE, NM_INTERVAL, " +
				"NM_WAIT_COUNT, NM_ORGAN_NODE_ID, NM_IS_PRINT, ST_RECEIPT_REMARK, ST_CALL_ID, ST_DEAL_SERVICE_ID, " +
				"ST_DEAL_SERVICE_NAME, DT_DEAL_SERVICE,DT_ASSESS_START, DT_ASSESS_END, NM_SATISFATION, " +
				"ST_SATISFATION_REMARK, ST_EXT1, ST_EXT2) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		  Object[] obj = {
		            info.getStApplyId(),
		            info.getStApplyNo(),
		            info.getStBusinessNo(),
		            info.getStBusinessId(),
		            info.getStItemId(),
		            info.getStUserId(),
		            info.getStLoginName(),
		            info.getStWindowNo(),
		            info.getStUserName(),
		            info.getStMobile(),
		            info.getNmIdentityType(),
		            info.getStIdentityNo(),
		            info.getStDealUserName(),
		            info.getStDealMobile(),
		            info.getNmDealIdentityType(),
		            info.getStDealIdentityNo(),
		            info.getDtStartTime(),
		            info.getDtConfirmTime(),
		            info.getNmCost(),
		            info.getNmToll(),
		            info.getStUnit(),
		            info.getStOrganizationCode(),
		            info.getStContactPhone(),
		            info.getDtFinish(),
		            info.getStStatus(),
		            info.getStInformation(),
		            info.getNmFinalState(),
		            info.getNmInterval(),
		            info.getNmWaitCount(),
		            info.getNmOrganNodeId(),
		            info.getNmIsPrint(),
		            info.getStReceiptRemark(),
		            info.getStCallId(),
		            info.getStDealServiceId(),
		            info.getStDealServiceName(),
		            info.getDtDealService(),
		            info.getDtAssessStart(),
		            info.getDtAssessEnd(),
		            info.getNmSatisfation(),
		            info.getStSatisfationRemark(),
		            info.getStExt1(),
		            info.getStExt2()        	
		        };
		        if (con == null) {
		            SQL.execute(sql, obj);
		        } else {
		            SQL.execute(con, sql, obj);
		        }
		    }

	public  WorkApplyInfo get(String stApplyId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_APPLY_ID",
				Condition.OT_EQUAL,stApplyId));
		List<WorkApplyInfo> list = query(conds,null);
		return list.size()>0?list.get(0):null;
	}

	private  List<WorkApplyInfo> query(Conditions conds, String suffix) {
		RecordSet rs;
		if(con == null){
			rs = SQL.query("WORK_APPLY_INFO", "*", conds, suffix);
		}else{
			rs = SQL.query(con, "WORK_APPLY_INFO", "*", conds, suffix);
		}
		ArrayList<WorkApplyInfo> al = new ArrayList<WorkApplyInfo>();
		while(rs.next()){
			WorkApplyInfo info = new WorkApplyInfo();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

	public  void update(WorkApplyInfo info) {
		 String sql = "update WORK_APPLY_INFO set ST_APPLY_NO = ?, ST_BUSINESS_NO = ?, ST_BUSINESS_ID = ?, ST_ITEM_ID = ?, ST_USER_ID = ?, ST_LOGIN_NAME = ?, ST_WINDOW_NO = ?, ST_USER_NAME = ?, ST_MOBILE = ?, NM_IDENTITY_TYPE = ?, ST_IDENTITY_NO = ?, ST_DEAL_USER_NAME = ?, ST_DEAL_MOBILE = ?, NM_DEAL_IDENTITY_TYPE = ?, ST_DEAL_IDENTITY_NO = ?, DT_START_TIME = ?, DT_CONFIRM_TIME = ?, NM_COST = ?, NM_TOLL = ?, ST_UNIT = ?, ST_ORGANIZATION_CODE = ?, ST_CONTACT_PHONE = ?, DT_FINISH = ?, ST_STATUS = ?, ST_INFORMATION = ?, NM_FINAL_STATE = ?, NM_INTERVAL = ?, NM_WAIT_COUNT = ?, NM_ORGAN_NODE_ID = ?, NM_IS_PRINT = ?, ST_RECEIPT_REMARK = ?, ST_CALL_ID = ?,ST_DEAL_SERVICE_ID = ?,ST_DEAL_SERVICE_NAME = ?,DT_DEAL_SERVICE = ?,DT_ASSESS_START = ?, DT_ASSESS_END = ?, NM_SATISFATION = ?, ST_SATISFATION_REMARK = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_APPLY_ID = ?";
	        Object[] obj = {
	            info.getStApplyNo(),
	            info.getStBusinessNo(),
	            info.getStBusinessId(),
	            info.getStItemId(),
	            info.getStUserId(),
	            info.getStLoginName(),
	            info.getStWindowNo(),
	            info.getStUserName(),
	            info.getStMobile(),
	            info.getNmIdentityType(),
	            info.getStIdentityNo(),
	            info.getStDealUserName(),
	            info.getStDealMobile(),
	            info.getNmDealIdentityType(),
	            info.getStDealIdentityNo(),
	            info.getDtStartTime(),
	            info.getDtConfirmTime(),
	            info.getNmCost(),
	            info.getNmToll(),
	            info.getStUnit(),
	            info.getStOrganizationCode(),
	            info.getStContactPhone(),
	            info.getDtFinish(),
	            info.getStStatus(),
	            info.getStInformation(),
	            info.getNmFinalState(),
	            info.getNmInterval(),
	            info.getNmWaitCount(),
	            info.getNmOrganNodeId(),
	            info.getNmIsPrint(),
	            info.getStReceiptRemark(),
	            info.getStCallId(),
	            info.getStDealServiceId(),
	            info.getStDealServiceName(),
	            info.getDtDealService(),
	            info.getDtAssessStart(),
	            info.getDtAssessEnd(),
	            info.getNmSatisfation(),
	            info.getStSatisfationRemark(),
	            info.getStExt1(),
	            info.getStExt2(),
	            info.getStApplyId()        	
	        };
	        if(con == null){
	        	SQL.execute(sql,obj);
	        }else{
	        	SQL.execute(con, sql,obj);
	        }
		
	}
	
	  public static void setProperties(WorkApplyInfo info, RecordSet rs){
	        info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
	        info.setStApplyNo(rs.getOriginalString("ST_APPLY_NO"));
	        info.setStBusinessNo(rs.getOriginalString("ST_BUSINESS_NO"));
	        info.setStBusinessId(rs.getOriginalString("ST_BUSINESS_ID"));
	        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
	        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
	        info.setStLoginName(rs.getOriginalString("ST_LOGIN_NAME"));
	        info.setStWindowNo(rs.getOriginalString("ST_WINDOW_NO"));
	        info.setStUserName(rs.getOriginalString("ST_USER_NAME"));
	        info.setStMobile(rs.getOriginalString("ST_MOBILE"));
	        info.setNmIdentityType(rs.getBigDecimal("NM_IDENTITY_TYPE"));
	        info.setStIdentityNo(rs.getOriginalString("ST_IDENTITY_NO"));
	        info.setStDealUserName(rs.getOriginalString("ST_DEAL_USER_NAME"));
	        info.setStDealMobile(rs.getOriginalString("ST_DEAL_MOBILE"));
	        info.setNmDealIdentityType(rs.getBigDecimal("NM_DEAL_IDENTITY_TYPE"));
	        info.setStDealIdentityNo(rs.getOriginalString("ST_DEAL_IDENTITY_NO"));
	        info.setDtStartTime(rs.getTimestamp("DT_START_TIME"));
	        info.setDtConfirmTime(rs.getTimestamp("DT_CONFIRM_TIME"));
	        info.setNmCost(rs.getBigDecimal("NM_COST"));
	        info.setNmToll(rs.getBigDecimal("NM_TOLL"));
	        info.setStUnit(rs.getOriginalString("ST_UNIT"));
	        info.setStOrganizationCode(rs.getOriginalString("ST_ORGANIZATION_CODE"));
	        info.setStContactPhone(rs.getOriginalString("ST_CONTACT_PHONE"));
	        info.setDtFinish(rs.getTimestamp("DT_FINISH"));
	        info.setStStatus(rs.getOriginalString("ST_STATUS"));
	        info.setStInformation(rs.getOriginalString("ST_INFORMATION"));
	        info.setNmFinalState(rs.getBigDecimal("NM_FINAL_STATE"));
	        info.setNmInterval(rs.getBigDecimal("NM_INTERVAL"));
	        info.setNmWaitCount(rs.getBigDecimal("NM_WAIT_COUNT"));
	        info.setNmOrganNodeId(rs.getBigDecimal("NM_ORGAN_NODE_ID"));
	        info.setNmIsPrint(rs.getBigDecimal("NM_IS_PRINT"));
	        info.setStReceiptRemark(rs.getOriginalString("ST_RECEIPT_REMARK"));
	        info.setStCallId(rs.getOriginalString("ST_CALL_ID"));
	        info.setStDealServiceId(rs.getOriginalString("ST_DEAL_SERVICE_ID"));
	        info.setStDealServiceName(rs.getOriginalString("ST_DEAL_SERVICE_NAME"));
	        info.setDtDealService(rs.getTimestamp("DT_DEAL_SERVICE"));
	        info.setDtAssessStart(rs.getTimestamp("DT_ASSESS_START"));
	        info.setDtAssessEnd(rs.getTimestamp("DT_ASSESS_END"));
	        info.setNmSatisfation(rs.getBigDecimal("NM_SATISFATION"));
	        info.setStSatisfationRemark(rs.getOriginalString("ST_SATISFATION_REMARK"));
	        info.setStExt1(rs.getOriginalString("ST_EXT1"));
	        info.setStExt2(rs.getOriginalString("ST_EXT2"));
	    }
}
