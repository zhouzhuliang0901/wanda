package com.wondersgroup.publicService.dao;

import com.wondersgroup.publicService.bean.SelmItemOrgan;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelmItemOrganDao {

	private Connection con = null;

	public SelmItemOrganDao(){}

	public SelmItemOrganDao(Connection con){
		this.con = con;
	}

	public List<SelmItemOrgan> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SELM_ITEM_ORGAN", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "SELM_ITEM_ORGAN", "*", conds, suffix);
		}
		List<SelmItemOrgan> pal = new ArrayList<SelmItemOrgan>();
		while (rs.next()) {
			SelmItemOrgan selmItemOrgan = new SelmItemOrgan();
			setProperties(selmItemOrgan, rs);
			pal.add(selmItemOrgan);
		}
		return pal;
	}


	public int insert(SelmItemOrgan selmItemOrgan){
		RecordSet rs;		
		String sql = "insert into SELM_ITEM_ORGAN(ST_OUGUID, ST_ORG_CODE, ST_OUNAME, ST_SHORT_NAME, ST_OUCODE, ST_SH_CODE, IS_ONUSE, ST_DEPT_LEVEL, ST_AREA_CODE, ST_PARENT_OUGUID) values (?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
			selmItemOrgan.getStOuguid(),
			selmItemOrgan.getStOrgCode(),
			selmItemOrgan.getStOuname(),
			selmItemOrgan.getStShortName(),
			selmItemOrgan.getStOucode(),
			selmItemOrgan.getStShCode(),
			selmItemOrgan.getIsOnuse(),
			selmItemOrgan.getStDeptLevel(),
			selmItemOrgan.getStAreaCode(),
			selmItemOrgan.getStParentOuguid(),
		};
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}


	public int update(Map<String, Object> map, Conditions conds){
		String sql = "update SELM_ITEM_ORGAN set ";
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

	public int delete(Conditions conds) {
		String sql = "delete from SELM_ITEM_ORGAN";
		String subsql = conds != null ? conds.toString() : "";
		if ("".equals(subsql)) {
			if (con == null) {
				return SQL.execute(sql).TOTAL_RECORD_COUNT;
			} else {
				return SQL.execute(con, sql).TOTAL_RECORD_COUNT;
			}
		} else {
			sql += " where " + subsql;
			if (con == null) {
				return SQL.execute(sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;
			} else {
				return SQL.execute(con, sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;
			}
		}
	}

	private void setProperties(SelmItemOrgan selmItemOrgan, RecordSet rs) {
		selmItemOrgan.setStOuguid(rs.getOriginalString("ST_OUGUID"));
		selmItemOrgan.setStOrgCode(rs.getOriginalString("ST_ORG_CODE"));
		selmItemOrgan.setStOuname(rs.getOriginalString("ST_OUNAME"));
		selmItemOrgan.setStShortName(rs.getOriginalString("ST_SHORT_NAME"));
		selmItemOrgan.setStOucode(rs.getOriginalString("ST_OUCODE"));
		selmItemOrgan.setStShCode(rs.getOriginalString("ST_SH_CODE"));
		selmItemOrgan.setIsOnuse(rs.getOriginalString("IS_ONUSE"));
		selmItemOrgan.setStDeptLevel(rs.getOriginalString("ST_DEPT_LEVEL"));
		selmItemOrgan.setStAreaCode(rs.getOriginalString("ST_AREA_CODE"));
		selmItemOrgan.setStParentOuguid(rs.getOriginalString("ST_PARENT_OUGUID"));
	}}