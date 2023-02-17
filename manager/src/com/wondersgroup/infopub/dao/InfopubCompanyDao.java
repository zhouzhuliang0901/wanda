package com.wondersgroup.infopub.dao;

import java.math.*;
import java.sql.*;
import java.util.*;

import wfc.facility.tool.autocode.*;
import wfc.service.database.*;

import org.springframework.stereotype.Repository;

import com.wondersgroup.infopub.bean.InfopubCompany;

/**
 * 设备厂商
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubCompanyDao {

    private Connection con = null;

    public InfopubCompanyDao() {
    }

    public InfopubCompanyDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubCompany info) {
        String sql = "insert into INFOPUB_COMPANY(ST_COMPANY_ID, ST_COMPANY_CODE, ST_COMPANY_NAME, ST_CONTACT_NAME, ST_CONTACT_TEL, NM_ORDER, DT_CREATE, DT_UPDATE) values (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStCompanyId(),
            info.getStCompanyCode(),
            info.getStCompanyName(),
            info.getStContactName(),
            info.getStContactTel(),
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

    public void update(InfopubCompany info) {
        String sql = "update INFOPUB_COMPANY set ST_COMPANY_CODE = ?, ST_COMPANY_NAME = ?, ST_CONTACT_NAME = ?, ST_CONTACT_TEL = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ? where ST_COMPANY_ID = ?";
        Object[] obj = {
            info.getStCompanyCode(),
            info.getStCompanyName(),
            info.getStContactName(),
            info.getStContactTel(),
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStCompanyId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_COMPANY set ";
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
        String sql = "delete from INFOPUB_COMPANY";
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

	public void delete(String[] stCompanyId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stCompanyId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_COMPANY_ID", Condition.OT_EQUAL, stCompanyId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stCompanyId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_COMPANY_ID", Condition.OT_EQUAL, stCompanyId));
        delete(conds);
    }

    public PaginationArrayList<InfopubCompany> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_COMPANY", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_COMPANY", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubCompany> pal = new PaginationArrayList<InfopubCompany>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubCompany info = new InfopubCompany();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubCompany> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_COMPANY", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_COMPANY", "*", conds, suffix);
        }
        ArrayList<InfopubCompany> al = new ArrayList<InfopubCompany>();
        while (rs.next()) {
            InfopubCompany info = new InfopubCompany();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubCompany info, RecordSet rs){
        info.setStCompanyId(rs.getOriginalString("ST_COMPANY_ID"));
        info.setStCompanyCode(rs.getOriginalString("ST_COMPANY_CODE"));
        info.setStCompanyName(rs.getOriginalString("ST_COMPANY_NAME"));
        info.setStContactName(rs.getOriginalString("ST_CONTACT_NAME"));
        info.setStContactTel(rs.getOriginalString("ST_CONTACT_TEL"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
    }

	public InfopubCompany get(String stCompanyId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_COMPANY_ID", Condition.OT_EQUAL, stCompanyId));
        List<InfopubCompany> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

	public InfopubCompany getCode(String stCompanyCode) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_COMPANY_CODE", Condition.OT_EQUAL, stCompanyCode));
        List<InfopubCompany> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

	public List<InfopubCompany> getAll() {
		String sql = "SELECT ST_COMPANY_ID,ST_COMPANY_NAME from INFOPUB_COMPANY";
		Object[] obj = { };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
        ArrayList<InfopubCompany> al = new ArrayList<InfopubCompany>();
        while (rs.next()) {
        	InfopubCompany info = new InfopubCompany();
        	info.setStCompanyId(rs.getOriginalString("ST_COMPANY_ID"));
            info.setStCompanyName(rs.getOriginalString("ST_COMPANY_NAME"));
            al.add(info);
        }
        return al;
	
	}
}
