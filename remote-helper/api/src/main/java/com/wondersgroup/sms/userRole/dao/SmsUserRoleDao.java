package com.wondersgroup.sms.userRole.dao;

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

import com.wondersgroup.sms.userRole.bean.SmsUserRole;

/**
 * 用户角色表
 *
 */
@Repository
public class SmsUserRoleDao {

    private Connection con = null;

    public SmsUserRoleDao() {
    }

    public SmsUserRoleDao(Connection con) {
        this.con = con;
    }

    public void add(SmsUserRole info) {
        String sql = "insert into SMS_USER_ROLE(ST_ROLE_ID, ST_USER_ID, NM_ORDER, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStRoleId(),
            info.getStUserId(),
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

    public void update(SmsUserRole info) {
        String sql = "update SMS_USER_ROLE set NM_ORDER = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_ROLE_ID = ? and ST_USER_ID = ?";
        Object[] obj = {
            info.getNmOrder(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStRoleId(),
            info.getStUserId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SMS_USER_ROLE set ";
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
        String sql = "delete from SMS_USER_ROLE";
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

	public void delete(String[] stRoleId, String[] stUserId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stRoleId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, stRoleId[i]));
            subconds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stRoleId, String stUserId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, stRoleId));
        conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
        delete(conds);
    }

    public PaginationArrayList<SmsUserRole> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_USER_ROLE", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SMS_USER_ROLE", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SmsUserRole> pal = new PaginationArrayList<SmsUserRole>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SmsUserRole info = new SmsUserRole();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SmsUserRole> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_USER_ROLE", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SMS_USER_ROLE", "*", conds, suffix);
        }
        ArrayList<SmsUserRole> al = new ArrayList<SmsUserRole>();
        while (rs.next()) {
            SmsUserRole info = new SmsUserRole();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SmsUserRole info, RecordSet rs){
        info.setStRoleId(rs.getOriginalString("ST_ROLE_ID"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SmsUserRole get(String stRoleId, String stUserId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, stRoleId));
        conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
        List<SmsUserRole> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
