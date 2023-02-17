package com.wondersgroup.app.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

import com.wondersgroup.app.bean.Oauth2ClientDevice;

/**
 * 客户端关联设备
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class Oauth2ClientDeviceDao {

    private Connection con = null;

    public Oauth2ClientDeviceDao() {
    }

    public Oauth2ClientDeviceDao(Connection con) {
        this.con = con;
    }

    public void add(Oauth2ClientDevice info) {
        String sql = "insert into OAUTH2_CLIENT_DEVICE(ST_OAUTH2_ID, ST_DEVICE_ID, NM_ORDER, DT_CREATE, DT_UPDATE) values (?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStOauth2Id(),
            info.getStDeviceId(),
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(Oauth2ClientDevice info) {
        String sql = "update OAUTH2_CLIENT_DEVICE set NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ? where ST_OAUTH2_ID = ? and ST_DEVICE_ID = ?";
        Object[] obj = {
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStOauth2Id(),
            info.getStDeviceId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update OAUTH2_CLIENT_DEVICE set ";
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
        String sql = "delete from OAUTH2_CLIENT_DEVICE";
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

	public void delete(String[] stOauth2Id, String[] stDeviceId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stOauth2Id.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL, stOauth2Id[i]));
            subconds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stOauth2Id, String stDeviceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL, stOauth2Id));
        conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
        delete(conds);
    }

    public PaginationArrayList<Oauth2ClientDevice> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("OAUTH2_CLIENT_DEVICE", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "OAUTH2_CLIENT_DEVICE", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<Oauth2ClientDevice> pal = new PaginationArrayList<Oauth2ClientDevice>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            Oauth2ClientDevice info = new Oauth2ClientDevice();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<Oauth2ClientDevice> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("OAUTH2_CLIENT_DEVICE", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "OAUTH2_CLIENT_DEVICE", "*", conds, suffix);
        }
        ArrayList<Oauth2ClientDevice> al = new ArrayList<Oauth2ClientDevice>();
        while (rs.next()) {
            Oauth2ClientDevice info = new Oauth2ClientDevice();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(Oauth2ClientDevice info, RecordSet rs){
        info.setStOauth2Id(rs.getOriginalString("ST_OAUTH2_ID"));
        info.setStDeviceId(rs.getOriginalString("ST_DEVICE_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
    }

	public Oauth2ClientDevice get(String stOauth2Id, String stDeviceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL, stOauth2Id));
        conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
        List<Oauth2ClientDevice> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
