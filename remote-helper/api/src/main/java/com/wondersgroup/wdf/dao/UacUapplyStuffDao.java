package com.wondersgroup.wdf.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

/**
 * 综合受理电子材料
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class UacUapplyStuffDao {

    private Connection con = null;

    public UacUapplyStuffDao() {
    }

    public UacUapplyStuffDao(Connection con) {
        this.con = con;
    }

    public void add(UacUapplyStuff info) {
        String sql = "insert into UAC_UAPPLY_STUFF(ST_ESTUFF_ID, ST_APPLY_ID, ST_CSTUFF_ID, ST_FILE_ID, ST_TABLE_ID, ST_ENTITY_TYPE, ST_ENTITY_TABLE, ST_STUFF_TYPE, ST_STUFF_USE, ST_DESC, ST_OPINION, NM_STATUS, NM_OFFLINE_SUBMIT, ST_SOURCE, ST_SOURCE_ID, NM_ORIGINAL, NM_COPY, DT_CREATE, DT_UPDATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStEstuffId(),
            info.getStApplyId(),
            info.getStCstuffId(),
            info.getStFileId(),
            info.getStTableId(),
            info.getStEntityType(),
            info.getStEntityTable(),
            info.getStStuffType(),
            info.getStStuffUse(),
            info.getStDesc(),
            info.getStOpinion(),
            info.getNmStatus(),
            info.getNmOfflineSubmit(),
            info.getStSource(),
            info.getStSourceId(),
            info.getNmOriginal(),
            info.getNmCopy(),
            info.getDtCreate(),
            info.getDtUpdate()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(UacUapplyStuff info) {
        String sql = "update UAC_UAPPLY_STUFF set ST_APPLY_ID = ?, ST_CSTUFF_ID = ?, ST_FILE_ID = ?, ST_TABLE_ID = ?, ST_ENTITY_TYPE = ?, ST_ENTITY_TABLE = ?, ST_STUFF_TYPE = ?, ST_STUFF_USE = ?, ST_DESC = ?, ST_OPINION = ?, NM_STATUS = ?, NM_OFFLINE_SUBMIT = ?, ST_SOURCE = ?, ST_SOURCE_ID = ?, NM_ORIGINAL = ?, NM_COPY = ?, DT_CREATE = ?, DT_UPDATE = ? where ST_ESTUFF_ID = ?";
        Object[] obj = {
            info.getStApplyId(),
            info.getStCstuffId(),
            info.getStFileId(),
            info.getStTableId(),
            info.getStEntityType(),
            info.getStEntityTable(),
            info.getStStuffType(),
            info.getStStuffUse(),
            info.getStDesc(),
            info.getStOpinion(),
            info.getNmStatus(),
            info.getNmOfflineSubmit(),
            info.getStSource(),
            info.getStSourceId(),
            info.getNmOriginal(),
            info.getNmCopy(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStEstuffId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update UAC_UAPPLY_STUFF set ";
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
        String sql = "delete from UAC_UAPPLY_STUFF";
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

	public void delete(String[] stEstuffId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stEstuffId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ESTUFF_ID", Condition.OT_EQUAL, stEstuffId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stEstuffId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ESTUFF_ID", Condition.OT_EQUAL, stEstuffId));
        delete(conds);
    }

    public PaginationArrayList<UacUapplyStuff> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_UAPPLY_STUFF", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "UAC_UAPPLY_STUFF", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<UacUapplyStuff> pal = new PaginationArrayList<UacUapplyStuff>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            UacUapplyStuff info = new UacUapplyStuff();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<UacUapplyStuff> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_UAPPLY_STUFF", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "UAC_UAPPLY_STUFF", "*", conds, suffix);
        }
        ArrayList<UacUapplyStuff> al = new ArrayList<UacUapplyStuff>();
        while (rs.next()) {
            UacUapplyStuff info = new UacUapplyStuff();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(UacUapplyStuff info, RecordSet rs){
        info.setStEstuffId(rs.getOriginalString("ST_ESTUFF_ID"));
        info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
        info.setStCstuffId(rs.getOriginalString("ST_CSTUFF_ID"));
        info.setStFileId(rs.getOriginalString("ST_FILE_ID"));
        info.setStTableId(rs.getOriginalString("ST_TABLE_ID"));
        info.setStEntityType(rs.getOriginalString("ST_ENTITY_TYPE"));
        info.setStEntityTable(rs.getOriginalString("ST_ENTITY_TABLE"));
        info.setStStuffType(rs.getOriginalString("ST_STUFF_TYPE"));
        info.setStStuffUse(rs.getOriginalString("ST_STUFF_USE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setStOpinion(rs.getOriginalString("ST_OPINION"));
        info.setNmStatus(rs.getBigDecimal("NM_STATUS"));
        info.setNmOfflineSubmit(rs.getBigDecimal("NM_OFFLINE_SUBMIT"));
        info.setStSource(rs.getOriginalString("ST_SOURCE"));
        info.setStSourceId(rs.getOriginalString("ST_SOURCE_ID"));
        info.setNmOriginal(rs.getBigDecimal("NM_ORIGINAL"));
        info.setNmCopy(rs.getBigDecimal("NM_COPY"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
    }

	public UacUapplyStuff get(String stEstuffId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ESTUFF_ID", Condition.OT_EQUAL, stEstuffId));
        List<UacUapplyStuff> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}