package com.wondersgroup.statistics.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.statistics.bean.SelmSatisfactionInfo;
import com.wondersgroup.statistics.bean.SelmStatisticsDay;

@Repository
public class SelmSatisfactionInfoDao {
	
	private Connection con = null;
	
	public SelmSatisfactionInfoDao() {
	}
	
	public SelmSatisfactionInfoDao(Connection con) {
	    this.con = con;
	}
	
	
	public List<SelmSatisfactionInfo> query(Conditions conds, String suffix) {
	    RecordSet rs;
	    if (con == null) {
	        rs = SQL.query("SELM_SATISFACTION_INFO", "*", conds, suffix);
	    } else {
	        rs = SQL.query(con, "SELM_SATISFACTION_INFO", "*", conds, suffix);
	    }
	    ArrayList<SelmSatisfactionInfo> al = new ArrayList<SelmSatisfactionInfo>();
	    while (rs.next()) {
	    	SelmSatisfactionInfo info = new SelmSatisfactionInfo();
	        setProperties(info, rs);
	        al.add(info);
	    }
	    return al;
	}
	
	public PaginationArrayList<SelmSatisfactionInfo> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_SATISFACTION_INFO", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_SATISFACTION_INFO", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmSatisfactionInfo> pal = new PaginationArrayList<SelmSatisfactionInfo>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
        	SelmSatisfactionInfo info = new SelmSatisfactionInfo();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }
	
	public static void setProperties(SelmSatisfactionInfo info, RecordSet rs){
	    info.setStSatisfactionId(rs.getOriginalString("ST_SATISFACTION_ID"));
	    info.setNmSatisfactionMachine(rs.getBigDecimal("NM_SATISFACTION_MACHINE"));
	    info.setNmSatisfactionAppearacne(rs.getBigDecimal("NM_SATISFACTION_APPEARANCE"));
	    info.setNmSatisfactionOperation(rs.getBigDecimal("NM_SATISFACTION_OPERATION"));
	    info.setNmSatisfactionScreen(rs.getBigDecimal("NM_SATISFACTION_SCREEN"));
	    info.setStSatisfactionContext(rs.getOriginalString("ST_SATISFACTION_CONTEXT"));
	    info.setStEvaluateMachineMAC(rs.getOriginalString("ST_EVALUATE_MACHINE_MAC"));
	    info.setStEvaluateName(rs.getOriginalString("ST_EVALUATE_NAME"));
	    info.setStEvaluateIdCard(rs.getOriginalString("ST_EVALUATE_IDCARD"));
	    info.setStEvaluatePhone(rs.getOriginalString("ST_EVALUATE_PHONE"));
	    info.setDtEvaluateTime(rs.getTimestamp("DT_EVALUATE_TIME"));
	    info.setStExt1(rs.getOriginalString("ST_EXT1"));
	    info.setStExt2(rs.getOriginalString("ST_EXT2"));
	}
	
	public BigDecimal getMachine(){
		String sql = "SELECT count(NM_SATISFACTION_MACHINE) mCou,sum(NM_SATISFACTION_MACHINE) mSum from SELM_SATISFACTION_INFO";
		Object[] obj = { };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		BigDecimal mGrade = null;
		while (rs.next()) {
			BigDecimal mCou = rs.getBigDecimal("mCou"); //数量
			BigDecimal mSum = rs.getBigDecimal("mSum"); //总评分
			mGrade = mSum.divide(mCou,0,BigDecimal.ROUND_HALF_UP);
		}
		return mGrade;
	}
	
	public BigDecimal getApp(){
		String sql = "SELECT count(NM_SATISFACTION_APPEARANCE) mCou,sum(NM_SATISFACTION_APPEARANCE) mSum from SELM_SATISFACTION_INFO";
		Object[] obj = { };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		BigDecimal aGrade = null;
		while (rs.next()) {
			BigDecimal mCou = rs.getBigDecimal("mCou"); //数量
			BigDecimal mSum = rs.getBigDecimal("mSum"); //总评分
			aGrade = mSum.divide(mCou,0,BigDecimal.ROUND_HALF_UP);
		}
		return aGrade;
	}
	
	public BigDecimal getOperation(){
		String sql = "SELECT count(NM_SATISFACTION_OPERATION) mCou,sum(NM_SATISFACTION_OPERATION) mSum from SELM_SATISFACTION_INFO";
		Object[] obj = { };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		BigDecimal oGrade = null;
		while (rs.next()) {
			BigDecimal mCou = rs.getBigDecimal("mCou"); //数量
			BigDecimal mSum = rs.getBigDecimal("mSum"); //总评分
			oGrade = mSum.divide(mCou,0,BigDecimal.ROUND_HALF_UP);
		}
		return oGrade;
	}
	
	public BigDecimal getScreen(){
		String sql = "SELECT count(NM_SATISFACTION_SCREEN) mCou,sum(NM_SATISFACTION_SCREEN) mSum from SELM_SATISFACTION_INFO";
		Object[] obj = { };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		BigDecimal sGrade = null;
		while (rs.next()) {
			BigDecimal mCou = rs.getBigDecimal("mCou"); //数量
			BigDecimal mSum = rs.getBigDecimal("mSum"); //总评分
			sGrade = mSum.divide(mCou,0,BigDecimal.ROUND_HALF_UP);
		}
		return sGrade;
	}


}
