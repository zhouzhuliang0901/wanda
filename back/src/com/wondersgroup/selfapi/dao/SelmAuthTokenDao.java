package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wondersgroup.selfapi.bean.SelmAuthToken;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelmAuthTokenDao {

	private Connection con = null;

	public SelmAuthTokenDao() {
	}

	public SelmAuthTokenDao(Connection con) {
		this.con = con;
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "UPDATE SELM_AUTH_TOKEN SET ";
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		for (String field : map.keySet()) {
			if (i++ > 0) {
				sql += ", ";
			}
			sql += field + " = ?";
			list.add(map.get(field));
		}
		String subsql = conds != null ? conds.toString() : "";
		if ("".equals(subsql)) {
			if (con == null) {
				return SQL.execute(sql).TOTAL_RECORD_COUNT;
			} else {
				return SQL.execute(con, sql).TOTAL_RECORD_COUNT;
			}
		} else {
			sql += " where " + subsql;
			list.addAll(conds.getObjectList());
			if (con == null) {
				return SQL.execute(sql, list.toArray()).TOTAL_RECORD_COUNT;
			} else {
				return SQL.execute(con, sql, list.toArray()).TOTAL_RECORD_COUNT;
			}
		}
	}

	public List<SelmAuthToken> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SELM_AUTH_TOKEN", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "SELM_AUTH_TOKEN", "*", conds, suffix);
		}
		ArrayList<SelmAuthToken> al = new ArrayList<SelmAuthToken>();
		while (rs.next()) {
			SelmAuthToken info = new SelmAuthToken();
			info.setStAuthApiName(rs.getOriginalString("ST_AUTH_API_NAME"));
			info.setStAuthToken(rs.getOriginalString("ST_AUTH_TOKEN"));
			al.add(info);
		}
		return al;
	}

	public SelmAuthToken get(String apiName) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_AUTH_API_NAME", Condition.OT_EQUAL, apiName));
		List<SelmAuthToken> list = query(conds, "");
		return list.size() > 0 ? list.get(0) : null;
	}
}
