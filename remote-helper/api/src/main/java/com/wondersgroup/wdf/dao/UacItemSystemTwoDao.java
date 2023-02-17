package com.wondersgroup.wdf.dao;

import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

/**
 * 事项关联系统
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class UacItemSystemTwoDao {

    private Connection con = null;

    public UacItemSystemTwoDao() {
    }

    public UacItemSystemTwoDao(Connection con) {
        this.con = con;
    }

    public void add(UacItemSystemTwo info) {
        String sql = "insert into UAC_ITEM_SYSTEM(ST_ITEM_ID, ST_WEB_SYSTEM_ID, NM_ORDER) values (?, ?, ?)";
        Object[] obj = {
            info.getStItemId(),
            info.getStWebSystemId(),
            info.getNmOrder()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(UacItemSystemTwo info) {
        String sql = "update UAC_ITEM_SYSTEM set NM_ORDER = ? where ST_ITEM_ID = ? and ST_WEB_SYSTEM_ID = ?";
        Object[] obj = {
            info.getNmOrder(),
            info.getStItemId(),
            info.getStWebSystemId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update UAC_ITEM_SYSTEM set ";
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
        String sql = "delete from UAC_ITEM_SYSTEM";
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

	public void delete(String[] stItemId, String[] stWebSystemId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stItemId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, stItemId[i]));
            subconds.add(new Condition("ST_WEB_SYSTEM_ID", Condition.OT_EQUAL, stWebSystemId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stItemId, String stWebSystemId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, stItemId));
        conds.add(new Condition("ST_WEB_SYSTEM_ID", Condition.OT_EQUAL, stWebSystemId));
        delete(conds);
    }

    public PaginationArrayList<UacItemSystemTwo> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_ITEM_SYSTEM", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "UAC_ITEM_SYSTEM", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<UacItemSystemTwo> pal = new PaginationArrayList<UacItemSystemTwo>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            UacItemSystemTwo info = new UacItemSystemTwo();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<UacItemSystemTwo> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_ITEM_SYSTEM", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "UAC_ITEM_SYSTEM", "*", conds, suffix);
        }
        ArrayList<UacItemSystemTwo> al = new ArrayList<UacItemSystemTwo>();
        while (rs.next()) {
            UacItemSystemTwo info = new UacItemSystemTwo();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(UacItemSystemTwo info, RecordSet rs){
        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
        info.setStWebSystemId(rs.getOriginalString("ST_WEB_SYSTEM_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
    }

	public UacItemSystemTwo get(String stItemId, String stWebSystemId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL, stItemId));
        conds.add(new Condition("ST_WEB_SYSTEM_ID", Condition.OT_EQUAL, stWebSystemId));
        List<UacItemSystemTwo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
