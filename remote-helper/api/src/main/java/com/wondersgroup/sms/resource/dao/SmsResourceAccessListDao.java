package com.wondersgroup.sms.resource.dao;

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

import com.wondersgroup.sms.resource.bean.SmsResourceAccessList;

/**
 * 资源权限访问列表
 *
 */
@Repository
public class SmsResourceAccessListDao {

    private Connection con = null;

    public SmsResourceAccessListDao() {
    }

    public SmsResourceAccessListDao(Connection con) {
        this.con = con;
    }

    public void add(SmsResourceAccessList info) {
        String sql = "insert into SMS_RESOURCE_ACCESS_LIST(ST_USER_ID, ST_RESOURCE_ID, ST_RESOURCE_TYPE_ID, ST_UNIQUE_VALUE) values (?, ?, ?, ?)";
        Object[] obj = {
            info.getStUserId(),
            info.getStResourceId(),
            info.getStResourceTypeId(),
            info.getStUniqueValue()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(SmsResourceAccessList info) {
        String sql = "update SMS_RESOURCE_ACCESS_LIST set ST_UNIQUE_VALUE = ? where ST_USER_ID = ? and ST_RESOURCE_ID = ? and ST_RESOURCE_TYPE_ID = ?";
        Object[] obj = {
            info.getStUniqueValue(),
            info.getStUserId(),
            info.getStResourceId(),
            info.getStResourceTypeId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SMS_RESOURCE_ACCESS_LIST set ";
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
        String sql = "delete from SMS_RESOURCE_ACCESS_LIST";
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

	public void delete(String[] stUserId, String[] stResourceId, String[] stResourceTypeId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stUserId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId[i]));
            subconds.add(new Condition("ST_RESOURCE_ID", Condition.OT_EQUAL, stResourceId[i]));
            subconds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_EQUAL, stResourceTypeId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stUserId, String stResourceId, String stResourceTypeId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
        conds.add(new Condition("ST_RESOURCE_ID", Condition.OT_EQUAL, stResourceId));
        conds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_EQUAL, stResourceTypeId));
        delete(conds);
    }

    public PaginationArrayList<SmsResourceAccessList> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_RESOURCE_ACCESS_LIST", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SMS_RESOURCE_ACCESS_LIST", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SmsResourceAccessList> pal = new PaginationArrayList<SmsResourceAccessList>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SmsResourceAccessList info = new SmsResourceAccessList();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SmsResourceAccessList> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_RESOURCE_ACCESS_LIST", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SMS_RESOURCE_ACCESS_LIST", "*", conds, suffix);
        }
        ArrayList<SmsResourceAccessList> al = new ArrayList<SmsResourceAccessList>();
        while (rs.next()) {
            SmsResourceAccessList info = new SmsResourceAccessList();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SmsResourceAccessList info, RecordSet rs){
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setStResourceId(rs.getOriginalString("ST_RESOURCE_ID"));
        info.setStResourceTypeId(rs.getOriginalString("ST_RESOURCE_TYPE_ID"));
        info.setStUniqueValue(rs.getOriginalString("ST_UNIQUE_VALUE"));
    }

	public SmsResourceAccessList get(String stUserId, String stResourceId, String stResourceTypeId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
        conds.add(new Condition("ST_RESOURCE_ID", Condition.OT_EQUAL, stResourceId));
        conds.add(new Condition("ST_RESOURCE_TYPE_ID", Condition.OT_EQUAL, stResourceTypeId));
        List<SmsResourceAccessList> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public SmsResourceAccessList getByUserId(Conditions conds) {
        List<SmsResourceAccessList> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
