package com.wondersgroup.dataitem.item382711997735.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.wondersgroup.dataitem.item382711997735.bean.SelmDictionaryCsjhj;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelmdictionarycsjhjDao {

	private Connection con = null;

	public void SelmDictionaryCsjhjDao(){}

	public void SelmDictionaryCsjhjDao(Connection con){
		this.con = con;
	}

	public PaginationArrayList<SelmDictionaryCsjhj> query(Conditions conds, String suffix, int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SELM_DICTIONARY_CSJHJ", "*", conds, suffix, pageSize, currentPage);
		} else {
			rs = SQL.query(con, "SELM_DICTIONARY_CSJHJ", "*", conds, suffix, pageSize, currentPage);
		}
		PaginationArrayList<SelmDictionaryCsjhj> pal = new PaginationArrayList<SelmDictionaryCsjhj>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SelmDictionaryCsjhj selmDictionaryCsjhj = new SelmDictionaryCsjhj();
			setProperties(selmDictionaryCsjhj, rs);
			pal.add(selmDictionaryCsjhj);
		}
		return pal;
	}


	public int insert(SelmDictionaryCsjhj selmDictionaryCsjhj){
		RecordSet rs;
		String sql = "insert into SELM_DICTIONARY_CSJHJ(ST_DICTIONARY_CODE, ST_DICTIONARY_NAME, ST_PARENT_DICTIONARY_CODE, ST_DICTIONARY_GROUP) values (?,?,?,?)";
		Object[] obj = {
			selmDictionaryCsjhj.getStDictionaryCode(),
			selmDictionaryCsjhj.getStDictionaryName(),
			selmDictionaryCsjhj.getStParentDictionaryCode(),
			selmDictionaryCsjhj.getStDictionaryGroup(),
		};
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}


	public int update(Map<String, Object> map, Conditions conds){
		String sql = "update SELM_DICTIONARY_CSJHJ set ";
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
		String sql = "delete from SELM_DICTIONARY_CSJHJ";
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

	private void setProperties(SelmDictionaryCsjhj selmDictionaryCsjhj, RecordSet rs) {
		selmDictionaryCsjhj.setStDictionaryCode(rs.getOriginalString("ST_DICTIONARY_CODE"));
		selmDictionaryCsjhj.setStDictionaryName(rs.getOriginalString("ST_DICTIONARY_NAME"));
		selmDictionaryCsjhj.setStParentDictionaryCode(rs.getOriginalString("ST_PARENT_DICTIONARY_CODE"));
		selmDictionaryCsjhj.setStDictionaryGroup(rs.getOriginalString("ST_DICTIONARY_GROUP"));
	}}