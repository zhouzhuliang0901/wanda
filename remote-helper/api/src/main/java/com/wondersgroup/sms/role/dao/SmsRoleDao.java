package com.wondersgroup.sms.role.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.sms.role.bean.SmsRole;

/**
 * 角色表
 * 
 */
@Repository
public class SmsRoleDao {

	private Connection con = null;

	public SmsRoleDao() {
	}

	public SmsRoleDao(Connection con) {
		this.con = con;
	}

	public void add(SmsRole info) {
		String sql = "insert into SMS_ROLE(ST_ROLE_ID, ST_ROLE_CODE, ST_ROLE_NAME, NM_ORDER, DT_CREATE, DT_UPDATE, ST_DESC, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { info.getStRoleId(), info.getStRoleCode(),
				info.getStRoleName(), info.getNmOrder(), info.getDtCreate(),
				info.getDtUpdate(), info.getStDesc(), info.getStExt1(),
				info.getStExt2() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public void update(SmsRole info) {
		String sql = "update SMS_ROLE set ST_ROLE_CODE = ?, ST_ROLE_NAME = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_ROLE_ID = ?";
		Object[] obj = { info.getStRoleCode(), info.getStRoleName(),
				info.getNmOrder(), info.getDtCreate(), info.getDtUpdate(),
				info.getStDesc(), info.getStExt1(), info.getStExt2(),
				info.getStRoleId() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "update SMS_ROLE set ";
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
		String sql = "delete from SMS_ROLE";
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

	public void delete(String[] stRoleId) {
		Conditions conds = Conditions.newOrConditions();
		for (int i = 0; i < stRoleId.length; i++) {
			Conditions subconds = Conditions.newAndConditions();
			subconds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL,
					stRoleId[i]));
			conds.add(subconds);
		}
		delete(conds);
	}

	public void delete(String stRoleId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, stRoleId));
		delete(conds);
	}

	public PaginationArrayList<SmsRole> query(Conditions conds, String suffix,
			int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SMS_ROLE", "*", conds, suffix, pageSize,
					currentPage);
		} else {
			rs = SQL.query(con, "SMS_ROLE", "*", conds, suffix, pageSize,
					currentPage);
		}
		PaginationArrayList<SmsRole> pal = new PaginationArrayList<SmsRole>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SmsRole info = new SmsRole();
			setProperties(info, rs);
			pal.add(info);
		}
		return pal;
	}

	public List<SmsRole> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SMS_ROLE", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "SMS_ROLE", "*", conds, suffix);
		}
		ArrayList<SmsRole> al = new ArrayList<SmsRole>();
		while (rs.next()) {
			SmsRole info = new SmsRole();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
	
	public SmsRole queryRoleByUser(Conditions conds, String suffix){
		String subsql = conds != null ? conds.toString() : "";
		suffix = suffix != null ? suffix : "";
		String sql = "select sr.* from SMS_USER su " +
				"left apprRunItemBoList SMS_USER_ROLE sur on su.ST_USER_ID = sur.ST_USER_ID " +
				"left join SMS_ROLE sr on sur.ST_ROLE_ID = sr.ST_ROLE_ID ";
		//List<SmsRole> al = new ArrayList<SmsRole>();
		SmsRole smsRole = new SmsRole();
		RecordSet rs = null;
		if ("".equals(subsql)) {
			rs = SQL.execute(sql);
		} else {
			sql += " where " + subsql + suffix;
			rs = SQL.execute(sql, conds.getObjectArray());
		}
		/*while (rs.next()) {
			SmsRole smsRole = new SmsRole();
			SmsRoleDao.setProperties(smsRole, rs);
			al.add(smsRole);
		}*/
		while(rs.next()){
			SmsRoleDao.setProperties(smsRole, rs);			
		}
		return smsRole;
		
	}

	public static void setProperties(SmsRole info, RecordSet rs) {
		info.setStRoleId(rs.getOriginalString("ST_ROLE_ID"));
		info.setStRoleCode(rs.getOriginalString("ST_ROLE_CODE"));
		info.setStRoleName(rs.getOriginalString("ST_ROLE_NAME"));
		info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
		info.setDtCreate(rs.getTimestamp("DT_CREATE"));
		info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
		info.setStDesc(rs.getOriginalString("ST_DESC"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
	}

	public SmsRole get(String stRoleId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, stRoleId));
		List<SmsRole> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

}
