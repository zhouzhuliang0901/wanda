package com.wondersgroup.infopub.dao;

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

import com.wondersgroup.infopub.bean.InfopubWorkspace;

/**
 * 信息发布用户空间
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubWorkspaceDao {

    private Connection con = null;

    public InfopubWorkspaceDao() {
    }

    public InfopubWorkspaceDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubWorkspace info) {
        String sql = "insert into INFOPUB_WORKSPACE(ST_WORKSPACE_ID, ST_WORKSPACE_CODE, ST_WORKSPACE_NAME, NM_TOTAL, NM_USED, DT_CREATE, DT_UPDATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStWorkspaceId(),
            info.getStWorkspaceCode(),
            info.getStWorkspaceName(),
            info.getNmTotal(),
            info.getNmUsed(),
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

    public void update(InfopubWorkspace info) {
        String sql = "update INFOPUB_WORKSPACE set ST_WORKSPACE_CODE = ?, ST_WORKSPACE_NAME = ?, NM_TOTAL = ?, NM_USED = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_WORKSPACE_ID = ?";
        Object[] obj = {
            info.getStWorkspaceCode(),
            info.getStWorkspaceName(),
            info.getNmTotal(),
            info.getNmUsed(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStWorkspaceId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_WORKSPACE set ";
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
        String sql = "delete from INFOPUB_WORKSPACE";
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

	public void delete(String[] stWorkspaceId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stWorkspaceId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_WORKSPACE_ID", Condition.OT_EQUAL, stWorkspaceId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stWorkspaceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_WORKSPACE_ID", Condition.OT_EQUAL, stWorkspaceId));
        delete(conds);
    }

    public PaginationArrayList<InfopubWorkspace> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_WORKSPACE", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_WORKSPACE", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubWorkspace> pal = new PaginationArrayList<InfopubWorkspace>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubWorkspace info = new InfopubWorkspace();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubWorkspace> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_WORKSPACE", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_WORKSPACE", "*", conds, suffix);
        }
        ArrayList<InfopubWorkspace> al = new ArrayList<InfopubWorkspace>();
        while (rs.next()) {
            InfopubWorkspace info = new InfopubWorkspace();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubWorkspace info, RecordSet rs){
        info.setStWorkspaceId(rs.getOriginalString("ST_WORKSPACE_ID"));
        info.setStWorkspaceCode(rs.getOriginalString("ST_WORKSPACE_CODE"));
        info.setStWorkspaceName(rs.getOriginalString("ST_WORKSPACE_NAME"));
        info.setNmTotal(rs.getBigDecimal("NM_TOTAL"));
        info.setNmUsed(rs.getBigDecimal("NM_USED"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public InfopubWorkspace get(String stWorkspaceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_WORKSPACE_ID", Condition.OT_EQUAL, stWorkspaceId));
        List<InfopubWorkspace> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
