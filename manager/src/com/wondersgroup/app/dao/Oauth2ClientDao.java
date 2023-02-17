package com.wondersgroup.app.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

import com.wondersgroup.app.bean.Oauth2Client;

/**
 * OAUTH2认证客户端
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class Oauth2ClientDao {

    private Connection con = null;

    public Oauth2ClientDao() {
    }

    public Oauth2ClientDao(Connection con) {
        this.con = con;
    }

    public void add(Oauth2Client info) {
        String sql = "insert into OAUTH2_CLIENT(ST_OAUTH2_ID, ST_INTERFACE_USER, ST_INTERFACE_PWD, ST_CLIENT_NAME, ST_CLIENT_ID, ST_CLIENT_SECRET, ST_DESC) values (?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStOauth2Id(),
            info.getStInterfaceUser(),
            info.getStInterfacePwd(),
            info.getStClientName(),
            info.getStClientId(),
            info.getStClientSecret(),
            info.getStDesc()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(Oauth2Client info) {
        String sql = "update OAUTH2_CLIENT set ST_INTERFACE_USER = ?, ST_INTERFACE_PWD = ?, ST_CLIENT_NAME = ?, ST_CLIENT_ID = ?, ST_CLIENT_SECRET = ?, ST_DESC = ? where ST_OAUTH2_ID = ?";
        Object[] obj = {
            info.getStInterfaceUser(),
            info.getStInterfacePwd(),
            info.getStClientName(),
            info.getStClientId(),
            info.getStClientSecret(),
            info.getStDesc(),
            info.getStOauth2Id()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update OAUTH2_CLIENT set ";
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
        String sql = "delete from OAUTH2_CLIENT";
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

	public void delete(String[] stOauth2Id) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stOauth2Id.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL, stOauth2Id[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stOauth2Id) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL, stOauth2Id));
        delete(conds);
    }

    public PaginationArrayList<Oauth2Client> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("OAUTH2_CLIENT", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "OAUTH2_CLIENT", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<Oauth2Client> pal = new PaginationArrayList<Oauth2Client>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            Oauth2Client info = new Oauth2Client();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<Oauth2Client> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("OAUTH2_CLIENT", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "OAUTH2_CLIENT", "*", conds, suffix);
        }
        ArrayList<Oauth2Client> al = new ArrayList<Oauth2Client>();
        while (rs.next()) {
            Oauth2Client info = new Oauth2Client();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(Oauth2Client info, RecordSet rs){
        info.setStOauth2Id(rs.getOriginalString("ST_OAUTH2_ID"));
        info.setStInterfaceUser(rs.getOriginalString("ST_INTERFACE_USER"));
        info.setStInterfacePwd(rs.getOriginalString("ST_INTERFACE_PWD"));
        info.setStClientName(rs.getOriginalString("ST_CLIENT_NAME"));
        info.setStClientId(rs.getOriginalString("ST_CLIENT_ID"));
        info.setStClientSecret(rs.getOriginalString("ST_CLIENT_SECRET"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
    }

	public Oauth2Client get(String stOauth2Id) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_OAUTH2_ID", Condition.OT_EQUAL, stOauth2Id));
        List<Oauth2Client> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
