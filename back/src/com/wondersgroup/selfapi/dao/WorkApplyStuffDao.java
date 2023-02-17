package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.selfapi.bean.WorkApplyStuff;

/**
 *  办理申请材料表
 * @author Administrator
 *
 */
@Repository
public class WorkApplyStuffDao {

    private Connection con = null;

    public WorkApplyStuffDao() {
    }

    public WorkApplyStuffDao(Connection con) {
        this.con = con;
    }

    public void add(WorkApplyStuff info) {
        String sql = "insert into WORK_APPLY_STUFF(" +
        		"ST_APPLY_STUFF_ID, ST_APPLY_ID, ST_ITEM_ID, ST_STUFF_NAME, " +
        		"NM_ORIGINAL, NM_COPY, NM_ORDER, ST_DESC, ST_EXT1, ST_EXT2) " +
        		"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStApplyStuffId(),
            info.getStApplyId(),
            info.getStItemId(),
            info.getStStuffName(),
            info.getNmOriginal(),
            info.getNmCopy(),
            info.getNmOrder(),
            info.getStDesc(),
            info.getStExt1(),
            info.getStExt2()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }
    
    
	public List<WorkApplyStuff> query(Conditions conds, String suffix) {
		 RecordSet rs;
	        if (con == null) {
	            rs = SQL.query("WORK_APPLY_STUFF", "*", conds, suffix);
	        } else {
	            rs = SQL.query(con, "WORK_APPLY_STUFF", "*", conds, suffix);
	        }
	        ArrayList<WorkApplyStuff> al = new ArrayList<WorkApplyStuff>();
	        while (rs.next()) {
	            WorkApplyStuff info = new WorkApplyStuff();
	            setProperties(info, rs);
	            al.add(info);
	        }
	        return al;
	}
	 public static void setProperties(WorkApplyStuff info, RecordSet rs){
	        info.setStApplyStuffId(rs.getOriginalString("ST_APPLY_STUFF_ID"));
	        info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
	        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
	        info.setStStuffName(rs.getOriginalString("ST_STUFF_NAME"));
	        info.setNmOriginal(rs.getBigDecimal("NM_ORIGINAL"));
	        info.setNmCopy(rs.getBigDecimal("NM_COPY"));
	        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
	        info.setStDesc(rs.getOriginalString("ST_DESC"));
	        info.setStExt1(rs.getOriginalString("ST_EXT1"));
	        info.setStExt2(rs.getOriginalString("ST_EXT2"));
	    }

	
}
