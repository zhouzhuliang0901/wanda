package com.wondersgroup.infopub.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import wfc.facility.tool.autocode.*;
import wfc.service.database.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.wondersgroup.business.bean.SelmQueryHis;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;

import coral.base.util.AciHelper;

/**
 * 地址表（办理点）
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubAddressDao {

    private Connection con = null;

    public InfopubAddressDao() {
    }

    public InfopubAddressDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubAddress info) {
        String sql = "insert into INFOPUB_ADDRESS(ST_ADDRESS_ID, ST_ADDRESS, ST_LABEL, NM_LNG, NM_LAT, ST_CITY, ST_DISTRICT, ST_STREET, DT_CREATE, DT_UPDATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStAddressId(),
            info.getStAddress(),
            info.getStLabel(),
            info.getNmLng(),
            info.getNmLat(),
            info.getStCity(),
            info.getStDistrict(),
            info.getStStreet(),
            info.getDtCreate(),
            info.getDtUpdate()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubAddress info) {
        String sql = "update INFOPUB_ADDRESS set ST_ADDRESS = ?, ST_LABEL = ?, NM_LNG = ?, NM_LAT = ?, ST_CITY = ?, ST_DISTRICT = ?, ST_STREET = ?, DT_CREATE = ?, DT_UPDATE = ? where ST_ADDRESS_ID = ?";
        Object[] obj = {
            info.getStAddress(),
            info.getStLabel(),
            info.getNmLng(),
            info.getNmLat(),
            info.getStCity(),
            info.getStDistrict(),
            info.getStStreet(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStAddressId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_ADDRESS set ";
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
        String sql = "delete from INFOPUB_ADDRESS";
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

	public void delete(String[] stAddressId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stAddressId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAddressId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stAddressId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAddressId));
        delete(conds);
    }

    public PaginationArrayList<InfopubAddress> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubAddress> pal = new PaginationArrayList<InfopubAddress>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubAddress> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "*", conds, suffix);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubAddress> queryLat(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "ST_ADDRESS_ID,NM_LNG,NM_LAT", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "ST_ADDRESS_ID,NM_LNG,NM_LAT", conds, suffix);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubAddress> queryName(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "ST_DISTRICT", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "*", conds, suffix);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubAddress> queryStreet(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "ST_STREET", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "ST_STREET", conds, suffix);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public PaginationArrayList<InfopubAddress> queryStreet(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "ST_STREET", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "ST_STREET", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubAddress> pal = new PaginationArrayList<InfopubAddress>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }
    
    public List<InfopubAddress> selectAll() {
        RecordSet rs;
        String sql = "SELECT DISTINCT ST_DISTRICT FROM infopub_address";
        if (con == null) {
            rs = SQL.execute(sql); 
        } else {
            rs = SQL.execute(con, sql);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubAddress> selectAllStreet() {
        RecordSet rs;
        String sql = "SELECT DISTINCT ST_STREET FROM infopub_address";
        if (con == null) {
            rs = SQL.execute(sql); 
        } else {
            rs = SQL.execute(con, sql);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubAddress> selectAllStreetByDistrict(String district) {
        RecordSet rs;
        String sql = "SELECT DISTINCT ST_STREET FROM infopub_address where ST_DISTRICT='"+district+"'";
        if (con == null) {
            rs = SQL.execute(sql); 
        } else {
            rs = SQL.execute(con, sql);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubAddress> selectStreetByDistrict(String district) {
        RecordSet rs;
        String sql = "SELECT DISTINCT ST_STREET FROM infopub_address where ST_DISTRICT = '"+district+"'";
        if (con == null) {
            rs = SQL.execute(sql); 
        } else {
            rs = SQL.execute(con, sql);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    

    
    public static void setProperties(InfopubAddress info, RecordSet rs){
        info.setStAddressId(rs.getOriginalString("ST_ADDRESS_ID"));
        info.setStAddress(rs.getOriginalString("ST_ADDRESS"));
        info.setStLabel(rs.getOriginalString("ST_LABEL"));
        info.setNmLng(rs.getBigDecimal("NM_LNG"));
        info.setNmLat(rs.getBigDecimal("NM_LAT"));
        info.setStCity(rs.getOriginalString("ST_CITY"));
        info.setStDistrict(rs.getOriginalString("ST_DISTRICT"));
        info.setStStreet(rs.getOriginalString("ST_STREET"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
    }

	public InfopubAddress get(String stAddressId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ADDRESS_ID", Condition.OT_EQUAL, stAddressId));
        List<InfopubAddress> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubAddress getName(String stName) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DISTRICT", Condition.OT_EQUAL, stName));
        List<InfopubAddress> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubAddress getSpecificName(String stName) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ADDRESS", Condition.OT_EQUAL, stName));
        List<InfopubAddress> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubAddress getByLikeSpec(String stName) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ADDRESS", Condition.OT_LIKE, stName));
        List<InfopubAddress> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubAddress getName(String stCity,String stDistrict,String stStreet,String address) {
        Conditions conds = Conditions.newAndConditions();
        if (stCity != null && !StringUtils.trim(stCity).isEmpty()) {
        conds.add(new Condition("ST_CITY", Condition.OT_EQUAL, stCity));
        }
        if (stDistrict != null && !StringUtils.trim(stDistrict).isEmpty()) {
        conds.add(new Condition("ST_DISTRICT", Condition.OT_EQUAL, stDistrict));
        }
        if (stStreet != null && !StringUtils.trim(stStreet).isEmpty()) {
        conds.add(new Condition("ST_STREET", Condition.OT_EQUAL, stStreet));
        }
        if (address != null && !StringUtils.trim(address).isEmpty()) {
        conds.add(new Condition("ST_ADDRESS", Condition.OT_EQUAL, address));
        }
        List<InfopubAddress> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

	public List<InfopubAddress> streetSelmQueryCount(Conditions conds,String suffix, int pageSize, int currentPage) {
		RecordSet rs;
        String content = "IA.ST_STREET,COUNT(SQH.ST_QUERY_HIS_ID) AS NUMBER";
        String table = "SELM_QUERY_HIS SQH left join INFOPUB_DEVICE_INFO IDI ON IDI.ST_DEVICE_MAC=SQH.ST_MACHINE_ID "+
        		"left join INFOPUB_ADDRESS IA ON IA.ST_ADDRESS_ID=IDI.ST_ADDRESS_ID "+ 
        		"left JOIN INFOPUB_DEVICE_TYPE IDT ON IDI.ST_TYPE_ID = IDT.ST_TYPE_ID ";
        if (con == null) {
            rs = SQL.query(table, content, conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, table, content, conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubAddress> pal = new PaginationArrayList<InfopubAddress>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
        	InfopubAddress info = new InfopubAddress();
        	info.setStStreet(rs.getOriginalString("ST_STREET"));
            info.setStLabel(rs.getOriginalString("NUMBER"));
            pal.add(info);
        }
        return pal;
	}
	
	public List<InfopubAddress> streetSelmQueryCount(Conditions conds,String suffix) {
		RecordSet rs;
        String content = "IA.ST_STREET,COUNT(SQH.ST_QUERY_HIS_ID) AS NUMBER";
    	String table ="(select ST_QUERY_HIS_ID,ST_MACHINE_ID,DT_CREATE from SELM_QUERY_HIS "+ 
    					"union all "+
    					"select ST_QUERY_HIS_ID,ST_MACHINE_ID,DT_CREATE from selm_query_his_2022)SQH "+
    					"join INFOPUB_DEVICE_INFO IDI ON IDI.ST_DEVICE_MAC=SQH.ST_MACHINE_ID "+
    					"join INFOPUB_ADDRESS IA ON IA.ST_ADDRESS_ID=IDI.ST_ADDRESS_ID "+ 
    					"join INFOPUB_DEVICE_TYPE IDT ON IDI.ST_TYPE_ID = IDT.ST_TYPE_ID ";
        if (con == null) {
            rs = SQL.query(table, content, conds, suffix);
        } else {
            rs = SQL.query(con, table, content, conds, suffix);
        }
        ArrayList<InfopubAddress> arr = new ArrayList<InfopubAddress>();
        while (rs.next()) {
        	InfopubAddress info = new InfopubAddress();
        	info.setStStreet(rs.getOriginalString("ST_STREET"));
            info.setStLabel(rs.getOriginalString("NUMBER"));
            arr.add(info);
        }
        return arr;
	}

	public List<InfopubAddress> initAddress(Conditions conds, String suffix) {
		RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ADDRESS", "ST_ADDRESS_ID,ST_CITY,ST_DISTRICT,ST_STREET,ST_ADDRESS", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ADDRESS", "ST_ADDRESS_ID,ST_CITY,ST_DISTRICT,ST_STREET,ST_ADDRESS", conds, suffix);
        }
        ArrayList<InfopubAddress> al = new ArrayList<InfopubAddress>();
        while (rs.next()) {
            InfopubAddress info = new InfopubAddress();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
	}

	
}
