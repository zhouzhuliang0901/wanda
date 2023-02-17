package com.wondersgroup.delivery.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

import com.wondersgroup.delivery.bean.SelmDelivery;

/**
 * 快递柜
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class SelmDeliveryDao {

    private Connection con = null;

    public SelmDeliveryDao() {
    }

    public SelmDeliveryDao(Connection con) {
        this.con = con;
    }

    public void add(SelmDelivery info) {
        String sql = "insert into SELM_DELIVERY(ST_DELIVERY_ID, ST_MACHINE_ID, ST_CABINET_NO, ST_CERT_FLOW_NO, ST_RECEIVER_PHONE, ST_RECEIVER_NAME, ST_RECEIVER_IDCARD, ST_SENDER_ID, ST_SENDER_NAME,ST_SENDER_PHONE,NM_CERT_TYPE, ST_CERT_NAME, NM_TYPE, ST_APPLY_ID, ST_NAME, NM_STATUS, ST_RECEIVE_NUM, ST_DESC, DT_CREATE, DT_STORE, DT_TAKE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,? ,?)";
        Object[] obj = {
            info.getStDeliveryId(),
            info.getStMachineId(),
            info.getStCabinetNo(),
            info.getStCertFlowNo(),
            info.getStReceiverPhone(),
            info.getStReceiverName(),
            info.getStReceiverIdcard(),
            info.getStSenderId(),
            info.getStSenderName(),
            info.getStSenderPhone(),
            info.getNmCertType(),
            info.getStCertName(),
            info.getNmType(),
            info.getStApplyId(),
            info.getStName(),
            info.getNmStatus(),
            info.getStReceiveNum(),
            info.getStDesc(),
            info.getDtCreate(),
            info.getDtStore(),
            info.getDtTake(),
            info.getStExt1(),
            info.getStExt2()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(SelmDelivery info) {
        String sql = "update SELM_DELIVERY set ST_MACHINE_ID = ?, ST_CABINET_NO = ?, ST_CERT_FLOW_NO = ?, ST_RECEIVER_PHONE = ?, ST_RECEIVER_NAME = ?, ST_RECEIVER_IDCARD = ?, ST_SENDER_ID = ?, ST_SENDER_NAME = ?, ST_SENDER_PHONE = ?,  NM_CERT_TYPE = ?,ST_CERT_NAME = ?, NM_TYPE = ?, ST_APPLY_ID = ?, ST_NAME = ?, NM_STATUS = ?, ST_RECEIVE_NUM = ?, ST_DESC = ?, DT_CREATE = ?, DT_STORE = ?, DT_TAKE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_DELIVERY_ID = ?";
        Object[] obj = {
            info.getStMachineId(),
            info.getStCabinetNo(),
            info.getStCertFlowNo(),
            info.getStReceiverPhone(),
            info.getStReceiverName(),
            info.getStReceiverIdcard(),
            info.getStSenderId(),
            info.getStSenderName(),
            info.getStSenderPhone(),
            info.getNmCertType(),
            info.getStCertName(),
            info.getNmType(),
            info.getStApplyId(),
            info.getStName(),
            info.getNmStatus(),
            info.getStReceiveNum(),
            info.getStDesc(),
            info.getDtCreate(),
            info.getDtStore(),
            info.getDtTake(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStDeliveryId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SELM_DELIVERY set ";
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
        String sql = "delete from SELM_DELIVERY";
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

	public void delete(String[] stDeliveryId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stDeliveryId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_DELIVERY_ID", Condition.OT_EQUAL, stDeliveryId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stDeliveryId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DELIVERY_ID", Condition.OT_EQUAL, stDeliveryId));
        delete(conds);
    }

    public PaginationArrayList<SelmDelivery> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_DELIVERY", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_DELIVERY", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmDelivery> pal = new PaginationArrayList<SelmDelivery>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SelmDelivery info = new SelmDelivery();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SelmDelivery> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_DELIVERY", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_DELIVERY", "*", conds, suffix);
        }
        ArrayList<SelmDelivery> al = new ArrayList<SelmDelivery>();
        while (rs.next()) {
            SelmDelivery info = new SelmDelivery();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SelmDelivery info, RecordSet rs){
        info.setStDeliveryId(rs.getOriginalString("ST_DELIVERY_ID"));
        info.setStMachineId(rs.getOriginalString("ST_MACHINE_ID"));
        info.setStCabinetNo(rs.getOriginalString("ST_CABINET_NO"));
        info.setStCertFlowNo(rs.getOriginalString("ST_CERT_FLOW_NO"));
        info.setStReceiverPhone(rs.getOriginalString("ST_RECEIVER_PHONE"));
        info.setStReceiverName(rs.getOriginalString("ST_RECEIVER_NAME"));
        info.setStReceiverIdcard(rs.getOriginalString("ST_RECEIVER_IDCARD"));
        info.setStSenderId(rs.getOriginalString("ST_SENDER_ID"));
        info.setStSenderName(rs.getOriginalString("ST_SENDER_NAME"));
        info.setStSenderPhone(rs.getOriginalString("ST_SENDER_PHONE"));
        info.setNmCertType(rs.getBigDecimal("NM_CERT_TYPE"));
        info.setStCertName(rs.getOriginalString("ST_CERT_NAME"));
        info.setNmType(rs.getBigDecimal("NM_TYPE"));
        info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
        info.setStName(rs.getOriginalString("ST_NAME"));
        info.setNmStatus(rs.getBigDecimal("NM_STATUS"));
        info.setStReceiveNum(rs.getOriginalString("ST_RECEIVE_NUM"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtStore(rs.getTimestamp("DT_STORE"));
        info.setDtTake(rs.getTimestamp("DT_TAKE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public SelmDelivery get(String stDeliveryId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DELIVERY_ID", Condition.OT_EQUAL, stDeliveryId));
        List<SelmDelivery> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

	public SelmDelivery getMac(String getMacHineId) {
		Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, getMacHineId));
        List<SelmDelivery> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
	}

}