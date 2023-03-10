package com.wondersgroup.statistics.dao;

import java.math.*;
import java.sql.*;
import java.util.*;

import wfc.facility.tool.autocode.*;
import wfc.service.database.*;

import org.springframework.stereotype.Repository;

import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.statistics.bean.SelmStatistics;

/**
 * 业务表
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class SelmStatisticsDao {

    private Connection con = null;

    public SelmStatisticsDao() {
    }

    public SelmStatisticsDao(Connection con) {
        this.con = con;
    }

    public void add(SelmStatistics info) {
        String sql = "insert into SELM_STATISTICS(ST_STATISTICS_ID, ST_NET_FLAG, ST_NET_SUB_FLAG, ST_NAME, NM_COUNT, NM_SORT, ST_EXT1, ST_EXT2, NM_ODEVICE) values (?,?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStStatisticsId(),
            info.getStNetFlag(),
            info.getStNetSubFlag(),
            info.getStName(),
            info.getNmCount(),
            info.getNmSort(),
            info.getStExt1(),
            info.getStExt2(),
            info.getNmOdevice()
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(SelmStatistics info) {
        String sql = "update SELM_STATISTICS set ST_NET_FLAG = ?, ST_NET_SUB_FLAG = ?, ST_NAME = ?, NM_COUNT = ?, NM_SORT = ?, ST_EXT1 = ?, ST_EXT2 = ?,NM_ODEVICE=? where ST_STATISTICS_ID = ?";
        Object[] obj = {
            info.getStNetFlag(),
            info.getStNetSubFlag(),
            info.getStName(),
            info.getNmCount(),
            info.getNmSort(),
            info.getStExt1(),
            info.getStExt2(),
            info.getNmOdevice(),
            info.getStStatisticsId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SELM_STATISTICS set ";
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
        String sql = "delete from SELM_STATISTICS";
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

	public void delete(String[] stStatisticsId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stStatisticsId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_STATISTICS_ID", Condition.OT_EQUAL, stStatisticsId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stStatisticsId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_EQUAL, stStatisticsId));
        delete(conds);
    }

    public PaginationArrayList<SelmStatistics> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_STATISTICS", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_STATISTICS", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmStatistics> pal = new PaginationArrayList<SelmStatistics>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SelmStatistics info = new SelmStatistics();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SelmStatistics> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_STATISTICS", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_STATISTICS", "*", conds, suffix);
        }
        ArrayList<SelmStatistics> al = new ArrayList<SelmStatistics>();
        while (rs.next()) {
            SelmStatistics info = new SelmStatistics();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SelmStatistics info, RecordSet rs){
        info.setStStatisticsId(rs.getOriginalString("ST_STATISTICS_ID"));
        info.setStNetFlag(rs.getOriginalString("ST_NET_FLAG"));
        info.setStNetSubFlag(rs.getOriginalString("ST_NET_SUB_FLAG"));
        info.setStName(rs.getOriginalString("ST_NAME"));
        info.setNmCount(rs.getBigDecimal("NM_COUNT"));
        info.setNmSort(rs.getBigDecimal("NM_SORT"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
        info.setNmOdevice(rs.getBigDecimal("NM_ODEVICE"));
    }

	public SelmStatistics get(String stStatisticsId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_STATISTICS_ID", Condition.OT_EQUAL, stStatisticsId));
        List<SelmStatistics> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	public SelmStatistics getBystNetFlag(String stNetFlag) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_NET_FLAG", Condition.OT_EQUAL, stNetFlag));
		List<SelmStatistics> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}
	
}
