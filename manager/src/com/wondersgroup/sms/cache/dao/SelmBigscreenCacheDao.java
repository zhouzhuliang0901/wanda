package com.wondersgroup.sms.cache.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

import com.wondersgroup.sms.cache.bean.SelmBigscreenCache;

/**
 * 大屏统计缓存表
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class SelmBigscreenCacheDao {

    private Connection con = null;

    public SelmBigscreenCacheDao() {
    }

    public SelmBigscreenCacheDao(Connection con) {
        this.con = con;
    }

    public void add(SelmBigscreenCache info) {
        String sql = "insert into SELM_BIGSCREEN_CACHE(ST_BIGSCREEN_CACHE_ID, ST_FRAME, ST_FCODE, ST_SCODE, ST_TCODE, ST_JSON, ST_CLOB_ID, NM_ORDER, DT_CREATE, DT_UPDATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStBigscreenCacheId(),
            info.getStFrame(),
            info.getStFcode(),
            info.getStScode(),
            info.getStTcode(),
            info.getStJson(),
            info.getStClobId(),
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(SelmBigscreenCache info) {
        String sql = "update SELM_BIGSCREEN_CACHE set ST_FRAME = ?, ST_FCODE = ?, ST_SCODE = ?, ST_TCODE = ?, ST_JSON = ?, ST_CLOB_ID = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_BIGSCREEN_CACHE_ID = ?";
        Object[] obj = {
            info.getStFrame(),
            info.getStFcode(),
            info.getStScode(),
            info.getStTcode(),
            info.getStJson(),
            info.getStClobId(),
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStBigscreenCacheId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SELM_BIGSCREEN_CACHE set ";
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
        String sql = "delete from SELM_BIGSCREEN_CACHE";
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

	public void delete(String[] stBigscreenCacheId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stBigscreenCacheId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_BIGSCREEN_CACHE_ID", Condition.OT_EQUAL, stBigscreenCacheId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stBigscreenCacheId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_BIGSCREEN_CACHE_ID", Condition.OT_EQUAL, stBigscreenCacheId));
        delete(conds);
    }

    public PaginationArrayList<SelmBigscreenCache> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_BIGSCREEN_CACHE", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_BIGSCREEN_CACHE", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmBigscreenCache> pal = new PaginationArrayList<SelmBigscreenCache>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SelmBigscreenCache info = new SelmBigscreenCache();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SelmBigscreenCache> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_BIGSCREEN_CACHE", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_BIGSCREEN_CACHE", "*", conds, suffix);
        }
        ArrayList<SelmBigscreenCache> al = new ArrayList<SelmBigscreenCache>();
        while (rs.next()) {
            SelmBigscreenCache info = new SelmBigscreenCache();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SelmBigscreenCache info, RecordSet rs){
        info.setStBigscreenCacheId(rs.getOriginalString("ST_BIGSCREEN_CACHE_ID"));
        info.setStFrame(rs.getOriginalString("ST_FRAME"));
        info.setStFcode(rs.getOriginalString("ST_FCODE"));
        info.setStScode(rs.getOriginalString("ST_SCODE"));
        info.setStTcode(rs.getOriginalString("ST_TCODE"));
        info.setStJson(rs.getOriginalString("ST_JSON"));
        info.setStClobId(rs.getOriginalString("ST_CLOB_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SelmBigscreenCache get(String stBigscreenCacheId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_BIGSCREEN_CACHE_ID", Condition.OT_EQUAL, stBigscreenCacheId));
        List<SelmBigscreenCache> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}