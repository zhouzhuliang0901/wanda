package com.wondersgroup.wdf.uacCert.dao;

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
 * 发证信息
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class UacCertDao {

    private Connection con = null;

    public UacCertDao() {
    }

    public UacCertDao(Connection con) {
        this.con = con;
    }

    public void add(UacCert info) {
        String sql = "insert into UAC_CERT(ST_CERT_ID, ST_APPLY_ID, ST_FEEDBACK_ID, ST_ITEM_ID, ST_ITEM_CODE, ST_ITEM_NAME, ST_CERT_NO, DT_MARK, ST_CERT_NAME, ST_LICENSE_NO, ST_VALID_DATE, ST_AREA, ST_ORGAN, ST_BCONTENT, ST_LNAME, ST_LCONTACT, ST_REMARK, ST_UNION_LOGISTICS_ID, DT_PORDER, DT_CERTIFICATION, DT_CREATE, DT_UPDATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStCertId(),
            info.getStApplyId(),
            info.getStFeedbackId(),
            info.getStItemId(),
            info.getStItemCode(),
            info.getStItemName(),
            info.getStCertNo(),
            info.getDtMark(),
            info.getStCertName(),
            info.getStLicenseNo(),
            info.getStValidDate(),
            info.getStArea(),
            info.getStOrgan(),
            info.getStBcontent(),
            info.getStLname(),
            info.getStLcontact(),
            info.getStRemark(),
            info.getStUnionLogisticsId(),
            info.getDtPorder(),
            info.getDtCertification(),
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

    public void update(UacCert info) {
        String sql = "update UAC_CERT set ST_APPLY_ID = ?, ST_FEEDBACK_ID = ?, ST_ITEM_ID = ?, ST_ITEM_CODE = ?, ST_ITEM_NAME = ?, ST_CERT_NO = ?, DT_MARK = ?, ST_CERT_NAME = ?, ST_LICENSE_NO = ?, ST_VALID_DATE = ?, ST_AREA = ?, ST_ORGAN = ?, ST_BCONTENT = ?, ST_LNAME = ?, ST_LCONTACT = ?, ST_REMARK = ?, ST_UNION_LOGISTICS_ID = ?, DT_PORDER = ?, DT_CERTIFICATION = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_CERT_ID = ?";
        Object[] obj = {
            info.getStApplyId(),
            info.getStFeedbackId(),
            info.getStItemId(),
            info.getStItemCode(),
            info.getStItemName(),
            info.getStCertNo(),
            info.getDtMark(),
            info.getStCertName(),
            info.getStLicenseNo(),
            info.getStValidDate(),
            info.getStArea(),
            info.getStOrgan(),
            info.getStBcontent(),
            info.getStLname(),
            info.getStLcontact(),
            info.getStRemark(),
            info.getStUnionLogisticsId(),
            info.getDtPorder(),
            info.getDtCertification(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStCertId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update UAC_CERT set ";
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
        String sql = "delete from UAC_CERT";
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

	public void delete(String[] stCertId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stCertId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_CERT_ID", Condition.OT_EQUAL, stCertId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stCertId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_CERT_ID", Condition.OT_EQUAL, stCertId));
        delete(conds);
    }

    public PaginationArrayList<UacCert> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_CERT", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "UAC_CERT", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<UacCert> pal = new PaginationArrayList<UacCert>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            UacCert info = new UacCert();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<UacCert> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_CERT", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "UAC_CERT", "*", conds, suffix);
        }
        ArrayList<UacCert> al = new ArrayList<UacCert>();
        while (rs.next()) {
            UacCert info = new UacCert();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(UacCert info, RecordSet rs){
        info.setStCertId(rs.getOriginalString("ST_CERT_ID"));
        info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
        info.setStFeedbackId(rs.getOriginalString("ST_FEEDBACK_ID"));
        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
        info.setStItemCode(rs.getOriginalString("ST_ITEM_CODE"));
        info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
        info.setStCertNo(rs.getOriginalString("ST_CERT_NO"));
        info.setDtMark(rs.getTimestamp("DT_MARK"));
        info.setStCertName(rs.getOriginalString("ST_CERT_NAME"));
        info.setStLicenseNo(rs.getOriginalString("ST_LICENSE_NO"));
        info.setStValidDate(rs.getOriginalString("ST_VALID_DATE"));
        info.setStArea(rs.getOriginalString("ST_AREA"));
        info.setStOrgan(rs.getOriginalString("ST_ORGAN"));
        info.setStBcontent(rs.getOriginalString("ST_BCONTENT"));
        info.setStLname(rs.getOriginalString("ST_LNAME"));
        info.setStLcontact(rs.getOriginalString("ST_LCONTACT"));
        info.setStRemark(rs.getOriginalString("ST_REMARK"));
        info.setStUnionLogisticsId(rs.getOriginalString("ST_UNION_LOGISTICS_ID"));
        info.setDtPorder(rs.getTimestamp("DT_PORDER"));
        info.setDtCertification(rs.getTimestamp("DT_CERTIFICATION"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public UacCert get(String stCertId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_CERT_ID", Condition.OT_EQUAL, stCertId));
        List<UacCert> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
