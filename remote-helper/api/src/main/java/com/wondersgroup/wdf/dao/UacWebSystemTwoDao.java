package com.wondersgroup.wdf.dao;

import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

/**
 * 系统信息
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class UacWebSystemTwoDao {

    private Connection con = null;

    public UacWebSystemTwoDao() {
    }

    public UacWebSystemTwoDao(Connection con) {
        this.con = con;
    }

    public void add(UacWebSystemTwo info) {
        String sql = "insert into UAC_WEB_SYSTEM(ST_WEB_SYSTEM_ID, ST_SYSTEM_NAME, ST_SYSTEM_URL, ST_ORGAN_ID, ST_MORGAN, ST_CONTACTOR, ST_TEL, ST_TYPE, ST_STATUS, DT_CREATE, DT_UPDATE, ST_DESC, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStWebSystemId(),
            info.getStSystemName(),
            info.getStSystemUrl(),
            info.getStOrganId(),
            info.getStMorgan(),
            info.getStContactor(),
            info.getStTel(),
            info.getStType(),
            info.getStStatus(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStExt1(),
            info.getStExt2()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(UacWebSystemTwo info) {
        String sql = "update UAC_WEB_SYSTEM set ST_SYSTEM_NAME = ?, ST_SYSTEM_URL = ?, ST_ORGAN_ID = ?, ST_MORGAN = ?, ST_CONTACTOR = ?, ST_TEL = ?, ST_TYPE = ?, ST_STATUS = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_WEB_SYSTEM_ID = ?";
        Object[] obj = {
            info.getStSystemName(),
            info.getStSystemUrl(),
            info.getStOrganId(),
            info.getStMorgan(),
            info.getStContactor(),
            info.getStTel(),
            info.getStType(),
            info.getStStatus(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStWebSystemId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update UAC_WEB_SYSTEM set ";
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
        String sql = "delete from UAC_WEB_SYSTEM";
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

	public void delete(String[] stWebSystemId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stWebSystemId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_WEB_SYSTEM_ID", Condition.OT_EQUAL, stWebSystemId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stWebSystemId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_WEB_SYSTEM_ID", Condition.OT_EQUAL, stWebSystemId));
        delete(conds);
    }

    public PaginationArrayList<UacWebSystemTwo> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_WEB_SYSTEM", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "UAC_WEB_SYSTEM", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<UacWebSystemTwo> pal = new PaginationArrayList<UacWebSystemTwo>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            UacWebSystemTwo info = new UacWebSystemTwo();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<UacWebSystemTwo> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_WEB_SYSTEM", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "UAC_WEB_SYSTEM", "*", conds, suffix);
        }
        ArrayList<UacWebSystemTwo> al = new ArrayList<UacWebSystemTwo>();
        while (rs.next()) {
            UacWebSystemTwo info = new UacWebSystemTwo();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(UacWebSystemTwo info, RecordSet rs){
        info.setStWebSystemId(rs.getOriginalString("ST_WEB_SYSTEM_ID"));
        info.setStSystemName(rs.getOriginalString("ST_SYSTEM_NAME"));
        info.setStSystemUrl(rs.getOriginalString("ST_SYSTEM_URL"));
        info.setStOrganId(rs.getOriginalString("ST_ORGAN_ID"));
        info.setStMorgan(rs.getOriginalString("ST_MORGAN"));
        info.setStContactor(rs.getOriginalString("ST_CONTACTOR"));
        info.setStTel(rs.getOriginalString("ST_TEL"));
        info.setStType(rs.getOriginalString("ST_TYPE"));
        info.setStStatus(rs.getOriginalString("ST_STATUS"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public UacWebSystemTwo get(String stWebSystemId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_WEB_SYSTEM_ID", Condition.OT_EQUAL, stWebSystemId));
        List<UacWebSystemTwo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
