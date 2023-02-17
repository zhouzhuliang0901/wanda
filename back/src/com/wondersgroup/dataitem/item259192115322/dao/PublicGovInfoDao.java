package com.wondersgroup.dataitem.item259192115322.dao;

import java.sql.Connection;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class PublicGovInfoDao {
	
    private Connection con = null;

    public PublicGovInfoDao() {
    }

    public PublicGovInfoDao(Connection con) {
        this.con = con;
    }
	
	public int addChannel(JSONObject json){
		String sql = "INSERT INTO SELFM_PUBLIC_GOV (ST_ID, ST_PRIORITY, ST_PARENT_ID, ST_CHILD, ST_NAME) " +
				"VALUES (?, ?, ?, ?, ?);";
		boolean b = json.optBoolean("hasChild");
		int child = 0;
		if(b){
			child = 1;
		}
		Object[] obj = {
				json.optString("id"),
				json.optString("priority"),
				json.optString("parentId"),
				child,
				json.optString("name")
		};
		int result = 0;
        if (con == null) {
            RecordSet rs = SQL.execute(sql, obj);
            result = rs.TOTAL_RECORD_COUNT;
        } else {
        	result = SQL.execute(con, sql, obj).TOTAL_RECORD_COUNT;
        }
        return result;
	}
}
