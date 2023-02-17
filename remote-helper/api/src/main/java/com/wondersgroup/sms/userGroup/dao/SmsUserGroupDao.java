package com.wondersgroup.sms.userGroup.dao;

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

import com.wondersgroup.sms.userGroup.bean.SmsUserGroup;

/**
 * 用户关联组
 *
 */
@Repository
public class SmsUserGroupDao {

    private Connection con = null;

    public SmsUserGroupDao() {
    }

    public SmsUserGroupDao(Connection con) {
        this.con = con;
    }

    public void add(SmsUserGroup info) {
        String sql = "insert into SMS_USER_GROUP(ST_GROUP_ID, ST_USER_ID, NM_ORDER, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStGroupId(),
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

    public void update(SmsUserGroup info) {
        String sql = "update SMS_USER_GROUP set NM_ORDER = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_GROUP_ID = ? and ST_USER_ID = ?";
        Object[] obj = {
            info.getNmOrder(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStGroupId(),
            info.getStUserId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SMS_USER_GROUP set ";
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
        String sql = "delete from SMS_USER_GROUP";
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

	public void delete(String[] stGroupId, String[] stUserId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stGroupId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId[i]));
            subconds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stGroupId, String stUserId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
        delete(conds);
    }

    public PaginationArrayList<SmsUserGroup> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_USER_GROUP", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SMS_USER_GROUP", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SmsUserGroup> pal = new PaginationArrayList<SmsUserGroup>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SmsUserGroup info = new SmsUserGroup();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SmsUserGroup> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_USER_GROUP", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SMS_USER_GROUP", "*", conds, suffix);
        }
        ArrayList<SmsUserGroup> al = new ArrayList<SmsUserGroup>();
        while (rs.next()) {
            SmsUserGroup info = new SmsUserGroup();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SmsUserGroup info, RecordSet rs){
        info.setStGroupId(rs.getOriginalString("ST_GROUP_ID"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SmsUserGroup get(String stGroupId, String stUserId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
        List<SmsUserGroup> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
