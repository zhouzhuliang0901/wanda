package com.wondersgroup.serverApply.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

import com.wondersgroup.serverApply.bean.SelmServerDlink;

/**
 * 服务关联设备
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class SelmServerDlinkDao {

    private Connection con = null;

    public SelmServerDlinkDao() {
    }

    public SelmServerDlinkDao(Connection con) {
        this.con = con;
    }

    public void add(SelmServerDlink info) {
        String sql = "insert into SELM_SERVER_DLINK(ST_APPLY_ID, ST_MACHINE_ID, NM_STATUS, ST_REASON, ST_AUDIT_USER_ID, ST_AUDIT_USER_NAME, DT_AUDIT, DT_CREATE, ST_DESC, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStApplyId(),
            info.getStMachineId(),
            info.getNmStatus(),
            info.getStReason(),
            info.getStAuditUserId(),
            info.getStAuditUserName(),
            info.getDtAudit(),
            info.getDtCreate(),
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

    public void update(SelmServerDlink info) {
        String sql = "update SELM_SERVER_DLINK set NM_STATUS = ?, ST_REASON = ?, ST_AUDIT_USER_ID = ?, ST_AUDIT_USER_NAME = ?, DT_AUDIT = ?, DT_CREATE = ?, ST_DESC = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_APPLY_ID = ? and ST_MACHINE_ID = ?";
        Object[] obj = {
            info.getNmStatus(),
            info.getStReason(),
            info.getStAuditUserId(),
            info.getStAuditUserName(),
            info.getDtAudit(),
            info.getDtCreate(),
            info.getStDesc(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStApplyId(),
            info.getStMachineId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SELM_SERVER_DLINK set ";
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
        String sql = "delete from SELM_SERVER_DLINK";
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

	public void delete(String[] stApplyId, String[] stMachineId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stApplyId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId[i]));
            subconds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMachineId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stApplyId, String stMachineId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
        conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, stMachineId));
        delete(conds);
    }

    public PaginationArrayList<SelmServerDlink> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_SERVER_DLINK", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_SERVER_DLINK", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmServerDlink> pal = new PaginationArrayList<SelmServerDlink>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SelmServerDlink info = new SelmServerDlink();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SelmServerDlink> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_SERVER_DLINK", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_SERVER_DLINK", "*", conds, suffix);
        }
        ArrayList<SelmServerDlink> al = new ArrayList<SelmServerDlink>();
        while (rs.next()) {
            SelmServerDlink info = new SelmServerDlink();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SelmServerDlink info, RecordSet rs){
        info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
        info.setStMachineId(rs.getOriginalString("ST_MACHINE_ID"));
        info.setNmStatus(rs.getBigDecimal("NM_STATUS"));
        info.setStReason(rs.getOriginalString("ST_REASON"));
        info.setStAuditUserId(rs.getOriginalString("ST_AUDIT_USER_ID"));
        info.setStAuditUserName(rs.getOriginalString("ST_AUDIT_USER_NAME"));
        info.setDtAudit(rs.getTimestamp("DT_AUDIT"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SelmServerDlink get(String stApplyId, String stMachineId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_APPLY_ID", Condition.OT_LIKE, stApplyId));
        conds.add(new Condition("ST_MACHINE_ID", Condition.OT_LIKE, stMachineId));
        List<SelmServerDlink> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}