package com.wondersgroup.sms.group.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wondersgroup.sms.user.bean.SmsUser;
import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.sms.group.bean.SmsGroup;

/**
 * 用户组
 * 
 */
@Repository
public class SmsGroupDao {

	private Connection con = null;

	public SmsGroupDao() {
	}

	public SmsGroupDao(Connection con) {
		this.con = con;
	}

	public void add(SmsGroup info) {
		String sql = "insert into SMS_GROUP(ST_GROUP_ID, ST_GROUP_CODE, ST_GROUP_NAME, NM_ORDER, DT_CREATE, DT_UPDATE, ST_DESC, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { info.getStGroupId(), info.getStGroupCode(),
				info.getStGroupName(), info.getNmOrder(), info.getDtCreate(),
				info.getDtUpdate(), info.getStDesc(), info.getStExt1(),
				info.getStExt2() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public void update(SmsGroup info) {
		String sql = "update SMS_GROUP set ST_GROUP_CODE = ?, ST_GROUP_NAME = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_GROUP_ID = ?";
		Object[] obj = { info.getStGroupCode(), info.getStGroupName(),
				info.getNmOrder(), info.getDtCreate(), info.getDtUpdate(),
				info.getStDesc(), info.getStExt1(), info.getStExt2(),
				info.getStGroupId() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "update SMS_GROUP set ";
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
		String sql = "delete from SMS_GROUP";
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

	public void delete(String[] stGroupId) {
		Conditions conds = Conditions.newOrConditions();
		for (int i = 0; i < stGroupId.length; i++) {
			Conditions subconds = Conditions.newAndConditions();
			subconds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,
					stGroupId[i]));
			conds.add(subconds);
		}
		delete(conds);
	}

	public void delete(String stGroupId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
		delete(conds);
	}

	public PaginationArrayList<SmsGroup> query(Conditions conds, String suffix,
			int pageSize, int currentPage) {
		RecordSet rs;
		String content = "	ST_GROUP_ID, "+
				"   ST_GROUP_CODE, "+
				"	ST_GROUP_NAME, "+
				"	NM_ORDER, "+
				"	ST_DESC, "+
				"	DT_CREATE, "+
				"	DT_UPDATE";
		if (con == null) {
			rs = SQL.query("SMS_GROUP", content, conds, suffix, pageSize,
					currentPage);
		} else {
			rs = SQL.query(con, "SMS_GROUP", content, conds, suffix, pageSize,
					currentPage);
		}
		PaginationArrayList<SmsGroup> pal = new PaginationArrayList<SmsGroup>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SmsGroup info = new SmsGroup();
			setProperties(info, rs);
			pal.add(info);
		}
		return pal;
	}

	public List<SmsGroup> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SMS_GROUP", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "SMS_GROUP", "*", conds, suffix);
		}
		ArrayList<SmsGroup> al = new ArrayList<SmsGroup>();
		while (rs.next()) {
			SmsGroup info = new SmsGroup();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

	public static void setProperties(SmsGroup info, RecordSet rs) {
		info.setStGroupId(rs.getOriginalString("ST_GROUP_ID"));
		info.setStGroupCode(rs.getOriginalString("ST_GROUP_CODE"));
		info.setStGroupName(rs.getOriginalString("ST_GROUP_NAME"));
		info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
		info.setDtCreate(rs.getTimestamp("DT_CREATE"));
		info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
		info.setStDesc(rs.getOriginalString("ST_DESC"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
	}

	public SmsGroup get(String stGroupId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
		List<SmsGroup> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

}
