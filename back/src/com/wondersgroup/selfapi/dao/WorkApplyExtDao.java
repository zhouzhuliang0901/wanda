package com.wondersgroup.selfapi.dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import wfc.service.database.SQL;

import com.wondersgroup.selfapi.bean.WorkApplyExt;

/**
 * 办理信息扩展字段表
 * @author Administrator
 */
@Repository
public class WorkApplyExtDao {
	 private Connection con = null;

	    public WorkApplyExtDao() {
	    }

	    public WorkApplyExtDao(Connection con) {
	        this.con = con;
	    }

	    public void add(WorkApplyExt info) {
	        String sql = "insert into WORK_APPLY_EXT" +
	        		"(ST_EXT_ID, ST_TABLE, ST_ENTITY_ID, " +
	        		"ST_INFO_NAME_EN, ST_INFO_NAME, ST_INFO_VALUE, " +
	        		"NM_ORDER, ST_EXT1, ST_EXT2) values " +
	        		"(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        Object[] obj = {
	            info.getStExtId(),
	            info.getStTable(),
	            info.getStEntityId(),
	            info.getStInfoNameEn(),
	            info.getStInfoName(),
	            info.getStInfoValue(),
	            info.getNmOrder(),
	            info.getStExt1(),
	            info.getStExt2()        	
	        };
	        if (con == null) {
	            SQL.execute(sql, obj);
	        } else {
	            SQL.execute(con, sql, obj);
	        }
	    }
}
