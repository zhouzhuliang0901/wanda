package com.wondersgroup.business.dao;

import java.math.*;
import java.sql.*;
import java.util.*;

import wfc.facility.tool.autocode.*;
import wfc.service.database.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import coral.base.util.AciHelper;

import com.wondersgroup.business.bean.SelmQueryHisTime;

@Repository
public class SelmQueryHisTimeDao {

	    private Connection con = null;

	    public SelmQueryHisTimeDao() {
	    }

	    public SelmQueryHisTimeDao(Connection con) {
	        this.con = con;
	    }

	    public void add(SelmQueryHisTime info) {
	        String sql = "insert into SELM_QUERY_HIS_Time(ST_QUERY_HIS_TIME_ID,ST_QUERY_HIS_ID,DT_CREATE) values (?, ?, ?)";
	        Object[] obj = {
	            info.getStQueryHisTimeId(),
	            info.getStQueryHisId(),
	            info.getDtCreate(),	
	        };
	        if (con == null) {
	            SQL.execute(sql, obj);
	        } else {
	            SQL.execute(con, sql, obj);
	        }
	    }

	    
	    public static void setProperties(SelmQueryHisTime info, RecordSet rs){
	        info.setStQueryHisTimeId(rs.getOriginalString("ST_QUERY_HIS_TIME_ID"));
	        info.setStQueryHisId(rs.getOriginalString("ST_QUERY_HIS_ID"));
	        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
	    }


}
