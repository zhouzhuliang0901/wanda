package com.wondersgroup.wdf.uacItems.dao;

import org.springframework.stereotype.Repository;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 主题事项（综合）
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class UacItemsDao {

    private Connection con = null;

    public UacItemsDao() {
    }

    public UacItemsDao(Connection con) {
        this.con = con;
    }

    public void add(UacItems info) {
        String sql = "insert into UAC_ITEMS(ST_ITEMS_ID, ST_ITEMS_CODE, ST_ITEMS_NAME, ST_DESC, NM_STATUS, ST_PARENT_ID, ST_PC_URL, ST_APP_URL, ST_T_URL, ST_AREA_ID, ST_AREA_CODE, ST_ORGAN_ID, ST_DEPART_CODE, ST_DEPART_NAME, NM_ORDER, ST_TYPE, DT_CREATE, DT_UPDATE, ST_EXT1, ST_EXT2, NM_FTYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStItemsId(),
            info.getStItemsCode(),
            info.getStItemsName(),
            info.getStDesc(),
            info.getNmStatus(),
            info.getStParentId(),
            info.getStPcUrl(),
            info.getStAppUrl(),
            info.getStTUrl(),
            info.getStAreaId(),
            info.getStAreaCode(),
            info.getStOrganId(),
            info.getStDepartCode(),
            info.getStDepartName(),
            info.getNmOrder(),
            info.getStType(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getNmFtype()
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(UacItems info) {
        String sql = "update UAC_ITEMS set ST_ITEMS_CODE = ?, ST_ITEMS_NAME = ?, ST_DESC = ?, NM_STATUS = ?, ST_PARENT_ID = ?, ST_PC_URL = ?, ST_APP_URL = ?, ST_T_URL = ?, ST_AREA_ID = ?, ST_AREA_CODE = ?, ST_ORGAN_ID = ?, ST_DEPART_CODE = ?, ST_DEPART_NAME = ?, NM_ORDER = ?, ST_TYPE = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ?, NM_FTYPE = ? where ST_ITEMS_ID = ?";
        Object[] obj = {
            info.getStItemsCode(),
            info.getStItemsName(),
            info.getStDesc(),
            info.getNmStatus(),
            info.getStParentId(),
            info.getStPcUrl(),
            info.getStAppUrl(),
            info.getStTUrl(),
            info.getStAreaId(),
            info.getStAreaCode(),
            info.getStOrganId(),
            info.getStDepartCode(),
            info.getStDepartName(),
            info.getNmOrder(),
            info.getStType(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStItemsId(),
            info.getNmFtype()
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update UAC_ITEMS set ";
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
        String sql = "delete from UAC_ITEMS";
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

	public void delete(String[] stItemsId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stItemsId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ITEMS_ID", Condition.OT_EQUAL, stItemsId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stItemsId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ITEMS_ID", Condition.OT_EQUAL, stItemsId));
        delete(conds);
    }

    public PaginationArrayList<UacItems> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_ITEMS", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "UAC_ITEMS", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<UacItems> pal = new PaginationArrayList<UacItems>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            UacItems info = new UacItems();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<UacItems> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_ITEMS", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "UAC_ITEMS", "*", conds, suffix);
        }
        ArrayList<UacItems> al = new ArrayList<UacItems>();
        while (rs.next()) {
            UacItems info = new UacItems();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(UacItems info, RecordSet rs){
        info.setStItemsId(rs.getOriginalString("ST_ITEMS_ID"));
        info.setStItemsCode(rs.getOriginalString("ST_ITEMS_CODE"));
        info.setStItemsName(rs.getOriginalString("ST_ITEMS_NAME"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setNmStatus(rs.getBigDecimal("NM_STATUS"));
        info.setStParentId(rs.getOriginalString("ST_PARENT_ID"));
        info.setStPcUrl(rs.getOriginalString("ST_PC_URL"));
        info.setStAppUrl(rs.getOriginalString("ST_APP_URL"));
        info.setStTUrl(rs.getOriginalString("ST_T_URL"));
        info.setStAreaId(rs.getOriginalString("ST_AREA_ID"));
        info.setStAreaCode(rs.getOriginalString("ST_AREA_CODE"));
        info.setStOrganId(rs.getOriginalString("ST_ORGAN_ID"));
        info.setStDepartCode(rs.getOriginalString("ST_DEPART_CODE"));
        info.setStDepartName(rs.getOriginalString("ST_DEPART_NAME"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setStType(rs.getOriginalString("ST_TYPE"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
        info.setNmFtype(rs.getBigDecimal("NM_FTYPE"));
    }

	public UacItems get(String stItemsId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ITEMS_ID", Condition.OT_EQUAL, stItemsId));
        List<UacItems> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
