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

import com.wondersgroup.infopub.bean.InfopubGroup;

/**
 * 设备分组
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubGroupDao {

    private Connection con = null;

    public InfopubGroupDao() {
    }

    public InfopubGroupDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubGroup info) {
        String sql = "insert into INFOPUB_GROUP(ST_GROUP_ID, ST_GROUP_NAME, ST_USER_ID, DT_CREATE, DT_UPDATE, ST_DESC, ST_AREA_ID) values (?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStGroupId(),
            info.getStGroupName(),
            info.getStUserId(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStAreaId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubGroup info) {
        String sql = "update INFOPUB_GROUP set ST_GROUP_NAME = ?, ST_USER_ID = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ?, ST_AREA_ID = ? where ST_GROUP_ID = ?";
        Object[] obj = {
            info.getStGroupName(),
            info.getStUserId(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStAreaId(),
            info.getStGroupId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_GROUP set ";
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
        String sql = "delete from INFOPUB_GROUP";
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

	public void delete(String[] stGroupId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stGroupId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stGroupId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        delete(conds);
    }

    public PaginationArrayList<InfopubGroup> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_GROUP", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_GROUP", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubGroup> pal = new PaginationArrayList<InfopubGroup>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubGroup info = new InfopubGroup();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubGroup> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_GROUP", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_GROUP", "*", conds, suffix);
        }
        ArrayList<InfopubGroup> al = new ArrayList<InfopubGroup>();
        while (rs.next()) {
            InfopubGroup info = new InfopubGroup();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubGroup info, RecordSet rs){
        info.setStGroupId(rs.getOriginalString("ST_GROUP_ID"));
        info.setStGroupName(rs.getOriginalString("ST_GROUP_NAME"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setStAreaId(rs.getOriginalString("ST_AREA_ID"));
    }

	public InfopubGroup get(String stGroupId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        List<InfopubGroup> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

	public InfopubGroup getByName(String stGroupName) {
		Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_GROUP_NAME", Condition.OT_EQUAL, stGroupName));
        List<InfopubGroup> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
	}

}