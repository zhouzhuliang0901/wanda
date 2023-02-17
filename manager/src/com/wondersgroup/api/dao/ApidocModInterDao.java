package com.wondersgroup.api.dao;

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

import com.wondersgroup.api.bean.ApidocModInter;

/**
 * 模块关联接口
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class ApidocModInterDao {

    private Connection con = null;

    public ApidocModInterDao() {
    }

    public ApidocModInterDao(Connection con) {
        this.con = con;
    }

    public void add(ApidocModInter info) {
        String sql = "insert into APIDOC_MOD_INTER(ST_MODULE_ID, ST_INTERFACE_ID, NM_ORDER) values (?, ?, ?)";
        Object[] obj = {
            info.getStModuleId(),
            info.getStInterfaceId(),
            info.getNmOrder()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }
    
	public void add(String stInterfaceId,String[] moduleId){
    	ApidocModInter apidocModInter = null;
    	for(String id: moduleId){
    		apidocModInter = new ApidocModInter();
    		apidocModInter.setStInterfaceId(stInterfaceId);
    		apidocModInter.setStModuleId(id);
    		add(apidocModInter);
    	}
    }

    public void update(ApidocModInter info) {
        String sql = "update APIDOC_MOD_INTER set NM_ORDER = ? where ST_MODULE_ID = ? and ST_INTERFACE_ID = ?";
        Object[] obj = {
            info.getNmOrder(),
            info.getStModuleId(),
            info.getStInterfaceId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update APIDOC_MOD_INTER set ";
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
        String sql = "delete from APIDOC_MOD_INTER";
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

	public void delete(String[] stModuleId, String[] stInterfaceId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stModuleId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_MODULE_ID", Condition.OT_EQUAL, stModuleId[i]));
            subconds.add(new Condition("ST_INTERFACE_ID", Condition.OT_EQUAL, stInterfaceId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stModuleId, String stInterfaceId) {
        Conditions conds = Conditions.newAndConditions();
        if(stModuleId != null && !stModuleId.isEmpty()){
        	conds.add(new Condition("ST_MODULE_ID", Condition.OT_EQUAL, stModuleId));
        }
        if(stInterfaceId != null && !stInterfaceId.isEmpty()){
        	conds.add(new Condition("ST_INTERFACE_ID", Condition.OT_EQUAL, stInterfaceId));
        }
        delete(conds);
    }

    public PaginationArrayList<ApidocModInter> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("APIDOC_MOD_INTER", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "APIDOC_MOD_INTER", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<ApidocModInter> pal = new PaginationArrayList<ApidocModInter>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            ApidocModInter info = new ApidocModInter();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<ApidocModInter> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("APIDOC_MOD_INTER", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "APIDOC_MOD_INTER", "*", conds, suffix);
        }
        ArrayList<ApidocModInter> al = new ArrayList<ApidocModInter>();
        while (rs.next()) {
            ApidocModInter info = new ApidocModInter();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(ApidocModInter info, RecordSet rs){
        info.setStModuleId(rs.getOriginalString("ST_MODULE_ID"));
        info.setStInterfaceId(rs.getOriginalString("ST_INTERFACE_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
    }

	public ApidocModInter get(String stModuleId, String stInterfaceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_MODULE_ID", Condition.OT_EQUAL, stModuleId));
        conds.add(new Condition("ST_INTERFACE_ID", Condition.OT_EQUAL, stInterfaceId));
        List<ApidocModInter> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
