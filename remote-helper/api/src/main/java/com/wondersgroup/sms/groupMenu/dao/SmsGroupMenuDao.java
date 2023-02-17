package com.wondersgroup.sms.groupMenu.dao;

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

import com.wondersgroup.sms.groupMenu.bean.SmsGroupMenu;

/**
 *组菜单
 *
 */
@Repository
public class SmsGroupMenuDao {

    private Connection con = null;

    public SmsGroupMenuDao() {
    }

    public SmsGroupMenuDao(Connection con) {
        this.con = con;
    }

    public void add(SmsGroupMenu info) {
        String sql = "insert into SMS_GROUP_MENU(ST_GROUP_ID, ST_MENU_ID, NM_ORDER, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStGroupId(),
            info.getStMenuId(),
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

    public void update(SmsGroupMenu info) {
        String sql = "update SMS_GROUP_MENU set NM_ORDER = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_GROUP_ID = ? and ST_MENU_ID = ?";
        Object[] obj = {
            info.getNmOrder(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStGroupId(),
            info.getStMenuId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SMS_GROUP_MENU set ";
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
        String sql = "delete from SMS_GROUP_MENU";
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

	public void delete(String[] stGroupId, String[] stMenuId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stGroupId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId[i]));
            subconds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, stMenuId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stGroupId, String stMenuId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        conds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, stMenuId));
        delete(conds);
    }

    public PaginationArrayList<SmsGroupMenu> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_GROUP_MENU", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SMS_GROUP_MENU", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SmsGroupMenu> pal = new PaginationArrayList<SmsGroupMenu>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SmsGroupMenu info = new SmsGroupMenu();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SmsGroupMenu> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_GROUP_MENU", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SMS_GROUP_MENU", "*", conds, suffix);
        }
        ArrayList<SmsGroupMenu> al = new ArrayList<SmsGroupMenu>();
        while (rs.next()) {
            SmsGroupMenu info = new SmsGroupMenu();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SmsGroupMenu info, RecordSet rs){
        info.setStGroupId(rs.getOriginalString("ST_GROUP_ID"));
        info.setStMenuId(rs.getOriginalString("ST_MENU_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SmsGroupMenu get(String stGroupId, String stMenuId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        conds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, stMenuId));
        List<SmsGroupMenu> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
