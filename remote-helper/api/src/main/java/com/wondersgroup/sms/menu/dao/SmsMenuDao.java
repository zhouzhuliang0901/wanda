package com.wondersgroup.sms.menu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wondersgroup.sms.menu.view.SmsMenuView;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.sms.menu.bean.SmsMenu;

/**
 * 系统菜单表
 * 
 */
public class SmsMenuDao {

	private Connection con = null;

	public SmsMenuDao() {
	}

	public SmsMenuDao(Connection con) {
		this.con = con;
	}

	public void add(SmsMenu info) {
		String sql = "insert into SMS_MENU(ST_MENU_ID, ST_MENU_CODE, ST_MENU_NAME, ST_PARENT_ID, ST_URL, ST_IMAGE, ST_TARGET, NM_ORDER, DT_CREATE, DT_UPDATE, ST_DESC, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { info.getStMenuId(), info.getStMenuCode(),
				info.getStMenuName(), info.getStParentId(), info.getStUrl(),
				info.getStImage(), info.getStTarget(), info.getNmOrder(),
				info.getDtCreate(), info.getDtUpdate(), info.getStDesc(),
				info.getStExt1(), info.getStExt2() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public void update(SmsMenu info) {
		String sql = "update SMS_MENU set ST_MENU_CODE = ?, ST_MENU_NAME = ?, ST_PARENT_ID = ?, ST_URL = ?, ST_IMAGE = ?, ST_TARGET = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_MENU_ID = ?";
		Object[] obj = { info.getStMenuCode(), info.getStMenuName(),
				info.getStParentId(), info.getStUrl(), info.getStImage(),
				info.getStTarget(), info.getNmOrder(), info.getDtCreate(),
				info.getDtUpdate(), info.getStDesc(), info.getStExt1(),
				info.getStExt2(), info.getStMenuId() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "update SMS_MENU set ";
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
		String sql = "delete from SMS_MENU";
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

	public void delete(String[] stMenuId) {
		Conditions conds = Conditions.newOrConditions();
		for (int i = 0; i < stMenuId.length; i++) {
			Conditions subconds = Conditions.newAndConditions();
			subconds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL,
					stMenuId[i]));
			conds.add(subconds);
		}
		delete(conds);
	}

	public void delete(String stMenuId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, stMenuId));
		delete(conds);
	}

	public PaginationArrayList<SmsMenu> query(Conditions conds, String suffix,
			int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SMS_MENU", "*", conds, suffix, pageSize,
					currentPage);
		} else {
			rs = SQL.query(con, "SMS_MENU", "*", conds, suffix, pageSize,
					currentPage);
		}
		PaginationArrayList<SmsMenu> pal = new PaginationArrayList<SmsMenu>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SmsMenu info = new SmsMenu();
			setProperties(info, rs);
			pal.add(info);
		}
		return pal;
	}

	public List<SmsMenu> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SMS_MENU", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "SMS_MENU", "*", conds, suffix);
		}
		ArrayList<SmsMenu> al = new ArrayList<SmsMenu>();
		while (rs.next()) {
			SmsMenu info = new SmsMenu();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
	
    public List<SmsMenu> queryByRoleId(Conditions conds, String suffix) {
        RecordSet rs;
        String table = "SMS_ROLE_MENU SRM INNER JOIN SMS_MENU SM ON SRM.ST_MENU_ID = SM.ST_MENU_ID";
        if (con == null) {
            rs = SQL.query(table, "SM.*", conds, suffix);
        } else {
            rs = SQL.query(con, table, "SM.*", conds, suffix);
        }
        ArrayList<SmsMenu> al = new ArrayList<SmsMenu>();
        while (rs.next()) {
        	SmsMenu info = new SmsMenu();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
	
    public List<SmsMenu> queryByGroupId(Conditions conds, String suffix) {
        RecordSet rs;
        String table = "SMS_GROUP_MENU SGM INNER JOIN SMS_MENU SM ON SGM.ST_MENU_ID = SM.ST_MENU_ID";
        if (con == null) {
            rs = SQL.query(table, "SM.*", conds, suffix);
        } else {
            rs = SQL.query(con, table, "SM.*", conds, suffix);
        }
        ArrayList<SmsMenu> al = new ArrayList<SmsMenu>();
        while (rs.next()) {
        	SmsMenu info = new SmsMenu();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
	public static void setProperties(SmsMenu info, RecordSet rs) {
		info.setStMenuId(rs.getOriginalString("ST_MENU_ID"));
		info.setStMenuCode(rs.getOriginalString("ST_MENU_CODE"));
		info.setStMenuName(rs.getOriginalString("ST_MENU_NAME"));
		info.setStParentId(rs.getOriginalString("ST_PARENT_ID"));
		info.setStUrl(rs.getOriginalString("ST_URL"));
		info.setStImage(rs.getOriginalString("ST_IMAGE"));
		info.setStTarget(rs.getOriginalString("ST_TARGET"));
		info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
		info.setDtCreate(rs.getTimestamp("DT_CREATE"));
		info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
		info.setStDesc(rs.getOriginalString("ST_DESC"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
	}

	public SmsMenu get(String stMenuId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, stMenuId));
		List<SmsMenu> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public SmsMenu checkMenuCode(Conditions conds) {
		List<SmsMenu> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<SmsMenu> getSystemMenuByUserId(String userId){
		/*Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("sur.ST_USER_ID", Condition.OT_EQUAL, userId));
		RecordSet rs;
		String content = "sm.*";
		String table = "SMS_MENU sm left join SMS_ROLE_MENU srm on sm.ST_MENU_ID = srm.ST_MENU_ID " +
				"left join SMS_USER_ROLE sur on sur.ST_ROLE_ID = srm.ST_ROLE_ID";
		if(con == null){
			rs = SQL.query(table,content,conds, null);
		}else{
			rs = SQL.query(con,table,content,conds, null);
		}*/
		RecordSet rs;
		String sql = "select sm.* from SMS_MENU sm left join SMS_ROLE_MENU srm on sm.ST_MENU_ID = srm.ST_MENU_ID left join SMS_USER_ROLE sur on sur.ST_ROLE_ID = srm.ST_ROLE_ID where sur.ST_USER_ID = '"+userId+"' UNION select sm1.* from SMS_MENU sm1 left join SMS_USER_MENU sume ON sm1.ST_MENU_ID = sume.ST_MENU_ID where sume.ST_USER_ID = '"+userId+"' ";

		rs = SQL.execute(sql);
		ArrayList<SmsMenu> al = new ArrayList<SmsMenu>();
		while(rs.next()){
			SmsMenu info = new SmsMenu();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
}
