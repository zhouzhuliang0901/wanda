package com.wondersgroup.serverApply.dao;

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

import com.wondersgroup.serverApply.bean.SelmDeviceApply;

/**
 * 设备接入申请
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class SelmDeviceApplyDao {

    private Connection con = null;

    public SelmDeviceApplyDao() {
    }

    public SelmDeviceApplyDao(Connection con) {
        this.con = con;
    }

    public void add(SelmDeviceApply info) {
        String sql = "insert into SELM_DEVICE_APPLY(ST_DEVICE_APPLY_ID, ST_DAPPLY_NO, ST_APPLY_ORGAN_ID, ST_APPLY_ORGAN_NAME, ST_MAIN_ORG_ID, ST_MAIN_ORG_NAME, NM_STATUS, ST_APPLY_USER_NAME, ST_APPLY_USER_PHONE, ST_APPLY_USER_MOBILE, ST_APPLY_USER_EMAIL, ST_DESC, DT_PLAN_CREATE, ST_APPLY_USER_ID, ST_APPLY_USER_NAME2, DT_CREATE, ST_EXT1, ST_EXT2,ST_DEVICE_DESTRICT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStDeviceApplyId(),
            info.getStDapplyNo(),
            info.getStApplyOrganId(),
            info.getStApplyOrganName(),
            info.getStMainOrgId(),
            info.getStMainOrgName(),
            info.getNmStatus(),
            info.getStApplyUserName(),
            info.getStApplyUserPhone(),
            info.getStApplyUserMobile(),
            info.getStApplyUserEmail(),
            info.getStDesc(),
            info.getDtPlanCreate(),
            info.getStApplyUserId(),
            info.getStApplyUserName2(),
            info.getDtCreate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStDeviceDistrict()
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(SelmDeviceApply info) {
        String sql = "update SELM_DEVICE_APPLY set ST_DAPPLY_NO = ?, ST_APPLY_ORGAN_ID = ?, ST_APPLY_ORGAN_NAME = ?, ST_MAIN_ORG_ID = ?, ST_MAIN_ORG_NAME = ?, NM_STATUS = ?, ST_APPLY_USER_NAME = ?, ST_APPLY_USER_PHONE = ?, ST_APPLY_USER_MOBILE = ?, ST_APPLY_USER_EMAIL = ?, ST_DESC = ?, DT_PLAN_CREATE = ?, ST_APPLY_USER_ID = ?, ST_APPLY_USER_NAME2 = ?, DT_CREATE = ?, ST_EXT1 = ?, ST_EXT2 = ?, ST_DEVICE_DESTRICT = ? where ST_DEVICE_APPLY_ID = ?";
        Object[] obj = {
            info.getStDapplyNo(),
            info.getStApplyOrganId(),
            info.getStApplyOrganName(),
            info.getStMainOrgId(),
            info.getStMainOrgName(),
            info.getNmStatus(),
            info.getStApplyUserName(),
            info.getStApplyUserPhone(),
            info.getStApplyUserMobile(),
            info.getStApplyUserEmail(),
            info.getStDesc(),
            info.getDtPlanCreate(),
            info.getStApplyUserId(),
            info.getStApplyUserName2(),
            info.getDtCreate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStDeviceApplyId(),
            info.getStDeviceDistrict()
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SELM_DEVICE_APPLY set ";
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
        String sql = "delete from SELM_DEVICE_APPLY";
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

	public void delete(String[] stDeviceApplyId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stDeviceApplyId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, stDeviceApplyId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stDeviceApplyId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, stDeviceApplyId));
        delete(conds);
    }

    public PaginationArrayList<SelmDeviceApply> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_DEVICE_APPLY", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_DEVICE_APPLY", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmDeviceApply> pal = new PaginationArrayList<SelmDeviceApply>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SelmDeviceApply info = new SelmDeviceApply();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SelmDeviceApply> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_DEVICE_APPLY", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_DEVICE_APPLY", "*", conds, suffix);
        }
        ArrayList<SelmDeviceApply> al = new ArrayList<SelmDeviceApply>();
        while (rs.next()) {
            SelmDeviceApply info = new SelmDeviceApply();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SelmDeviceApply info, RecordSet rs){
        info.setStDeviceApplyId(rs.getOriginalString("ST_DEVICE_APPLY_ID"));
        info.setStDapplyNo(rs.getOriginalString("ST_DAPPLY_NO"));
        info.setStApplyOrganId(rs.getOriginalString("ST_APPLY_ORGAN_ID"));
        info.setStApplyOrganName(rs.getOriginalString("ST_APPLY_ORGAN_NAME"));
        info.setStMainOrgId(rs.getOriginalString("ST_MAIN_ORG_ID"));
        info.setStMainOrgName(rs.getOriginalString("ST_MAIN_ORG_NAME"));
        info.setNmStatus(rs.getBigDecimal("NM_STATUS"));
        info.setStApplyUserName(rs.getOriginalString("ST_APPLY_USER_NAME"));
        info.setStApplyUserPhone(rs.getOriginalString("ST_APPLY_USER_PHONE"));
        info.setStApplyUserMobile(rs.getOriginalString("ST_APPLY_USER_MOBILE"));
        info.setStApplyUserEmail(rs.getOriginalString("ST_APPLY_USER_EMAIL"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setDtPlanCreate(rs.getTimestamp("DT_PLAN_CREATE"));
        info.setStApplyUserId(rs.getOriginalString("ST_APPLY_USER_ID"));
        info.setStApplyUserName2(rs.getOriginalString("ST_APPLY_USER_NAME2"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
        info.setStDeviceDistrict(rs.getOriginalString("ST_DEVICE_DESTRICT"));
    }

	public SelmDeviceApply get(String stDeviceApplyId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_APPLY_ID", Condition.OT_EQUAL, stDeviceApplyId));
        List<SelmDeviceApply> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}