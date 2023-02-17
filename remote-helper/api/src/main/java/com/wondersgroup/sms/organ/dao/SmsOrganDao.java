package com.wondersgroup.sms.organ.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wondersgroup.sms.user.bean.SmsUser;
import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.sms.organ.bean.SmsOrgan;

/**
 * 组织机构表
 *
 */
@Repository
public class SmsOrganDao {

    private Connection con = null;

    public SmsOrganDao() {
    }

    public SmsOrganDao(Connection con) {
        this.con = con;
    }

    public void add(SmsOrgan info) {
        String sql = "insert into SMS_ORGAN(ST_ORGAN_ID, ST_PARENT_ID, ST_ORGAN_CODE, ST_ORGAN_NAME, NM_ORDER, DT_CREATE, DT_UPDATE, ST_DESC, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStOrganId(),
            info.getStParentId(),
            info.getStOrganCode(),
            info.getStOrganName(),
            info.getNmOrder(),
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

    public void update(SmsOrgan info) {
        String sql = "update SMS_ORGAN set ST_PARENT_ID = ?, ST_ORGAN_CODE = ?, ST_ORGAN_NAME = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_ORGAN_ID = ?";
        Object[] obj = {
            info.getStParentId(),
            info.getStOrganCode(),
            info.getStOrganName(),
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStOrganId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SMS_ORGAN set ";
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
        String sql = "delete from SMS_ORGAN";
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

	public void delete(String[] stOrganId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stOrganId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stOrganId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
        delete(conds);
    }

    public PaginationArrayList<SmsOrgan> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_ORGAN", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SMS_ORGAN", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SmsOrgan> pal = new PaginationArrayList<SmsOrgan>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SmsOrgan info = new SmsOrgan();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SmsOrgan> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_ORGAN", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SMS_ORGAN", "*", conds, suffix);
        }
        ArrayList<SmsOrgan> al = new ArrayList<SmsOrgan>();
        while (rs.next()) {
            SmsOrgan info = new SmsOrgan();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SmsOrgan info, RecordSet rs){
        info.setStOrganId(rs.getOriginalString("ST_ORGAN_ID"));
        info.setStParentId(rs.getOriginalString("ST_PARENT_ID"));
        info.setStOrganCode(rs.getOriginalString("ST_ORGAN_CODE"));
        info.setStOrganName(rs.getOriginalString("ST_ORGAN_NAME"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SmsOrgan get(String stOrganId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL, stOrganId));
        List<SmsOrgan> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	public SmsOrgan getByName(String stOrganName) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ORGAN_NAME", Condition.OT_LIKE, stOrganName));
        List<SmsOrgan> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<SmsOrgan> select() {
        RecordSet rs;
        String sql = "select \n" +
                " DISTINCT so.*\n" +
                "from SMS_USER su\n" +
                "LEFT JOIN SMS_ORGAN so on su.ST_ORGAN_ID_TWO=so.ST_ORGAN_ID\n" +
                "LEFT JOIN SMS_ORGAN sso on so.ST_PARENT_ID = sso.ST_ORGAN_ID\n" +
                "LEFT JOIN SMS_ORGAN ssso on sso.ST_PARENT_ID = ssso.ST_ORGAN_ID\n" +
                "where ssso.ST_ORGAN_CODE='sms_organ2_root'";

        rs = SQL.execute(sql);
        ArrayList<SmsOrgan> al = new ArrayList<SmsOrgan>();
        while (rs.next()) {
            SmsOrgan info = new SmsOrgan();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }



}
