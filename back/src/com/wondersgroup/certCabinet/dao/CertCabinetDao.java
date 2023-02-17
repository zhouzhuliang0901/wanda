package com.wondersgroup.certCabinet.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wondersgroup.certCabinet.bean.CabinetInfo;
import com.wondersgroup.certCabinet.bean.SelmDelivery;
import com.wondersgroup.certCabinet.bean.SelmDeliveryHistory;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class CertCabinetDao {
	
	private Connection con = null;
	
	public CertCabinetDao(){}
	public CertCabinetDao(Connection con){
		this.con = con;
	}
	
	public int addCabinets(SelmDelivery info) {
		RecordSet rs;
		String sql = "INSERT INTO SELM_DELIVERY (ST_DELIVERY_ID, ST_MACHINE_ID, ST_CABINET_NO, ST_CERT_FLOW_NO, ST_RECEIVER_PHONE, ST_RECEIVER_NAME, ST_RECEIVER_IDCARD, ST_SENDER_ID, ST_SENDER_NAME, ST_SENDER_PHONE, NM_CERT_TYPE, ST_CERT_NAME, NM_TYPE, ST_APPLY_ID, ST_NAME, NM_STATUS, ST_RECEIVE_NUM, ST_DESC, DT_CREATE, DT_STORE, DT_TAKE, ST_EXT1, ST_EXT2) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		if(con == null){
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}
	
	public void updateCabinets(SelmDelivery info){
		String sql = " UPDATE SELM_DELIVERY SET ST_MACHINE_ID=?, ST_CABINET_NO=?, ST_CERT_FLOW_NO=?, ST_RECEIVER_PHONE=?, ST_RECEIVER_NAME=?, "
					+" ST_RECEIVER_IDCARD=?, ST_SENDER_ID=?, ST_SENDER_NAME=?, ST_SENDER_PHONE=?, NM_CERT_TYPE=?, ST_CERT_NAME=?, NM_TYPE=?, "
					+" ST_APPLY_ID=?, ST_NAME=?, NM_STATUS=?, ST_RECEIVE_NUM=?, ST_DESC=?, DT_CREATE=?, DT_STORE=?, DT_TAKE=?, ST_EXT1=?, ST_EXT2=? WHERE ST_DELIVERY_ID= ?";
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
		if(con == null){
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}
	
	public int addCabinetsHistory(SelmDeliveryHistory info) {
		RecordSet rs;
		String sql = "INSERT INTO SELM_DELIVERY_HISTORY (ST_DELIVERY_ID, ST_MACHINE_ID, ST_CABINET_NO, ST_CERT_FLOW_NO, ST_RECEIVER_PHONE, ST_RECEIVER_NAME, ST_RECEIVER_IDCARD, ST_SENDER_ID, ST_SENDER_NAME, ST_SENDER_PHONE, NM_CERT_TYPE, ST_CERT_NAME, NM_TYPE, ST_APPLY_ID, ST_NAME, NM_STATUS, ST_RECEIVE_NUM, ST_DESC, DT_CREATE, DT_STORE, DT_TAKE, ST_EXT1, ST_EXT2) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		if(con == null){
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}
	
	public void updateCabinetsHistory(SelmDeliveryHistory info){
		String sql = " UPDATE SELM_DELIVERY_HISTORY SET ST_MACHINE_ID=?, ST_CABINET_NO=?, ST_CERT_FLOW_NO=?, ST_RECEIVER_PHONE=?, ST_RECEIVER_NAME=?, "
					+" ST_RECEIVER_IDCARD=?, ST_SENDER_ID=?, ST_SENDER_NAME=?, ST_SENDER_PHONE=?, NM_CERT_TYPE=?, ST_CERT_NAME=?, NM_TYPE=?, "
					+" ST_APPLY_ID=?, ST_NAME=?, NM_STATUS=?, ST_RECEIVE_NUM=?, ST_DESC=?, DT_CREATE=?, DT_STORE=?, DT_TAKE=?, ST_EXT1=?, ST_EXT2=? WHERE ST_DELIVERY_ID= ?";
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
		if(con == null){
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}
	
	public List<String> queryEmptyCabinet(String machineId) {
		RecordSet rs;
		String sql = " SELECT ST_CABINET_NO FROM SELM_DELIVERY "
					+" WHERE ST_MACHINE_ID = ? "
					+" AND (NM_STATUS = 0 OR NM_STATUS = 2)";
		if(con == null){
			rs = SQL.execute(sql, new Object[]{machineId});
		} else {
			rs = SQL.execute(con, sql, new Object[]{machineId});
		}
		List<String> list = new ArrayList<String>();
		while(rs.next()){
			String cabinetNo = rs.getOriginalString("ST_CABINET_NO");
			list.add(cabinetNo);
		}
		return list;
	}
	
    public List<SelmDelivery> querySelmDelivery(Conditions conds, String suffix) {
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
    
    public List<SelmDeliveryHistory> querySelmDeliveryHistory(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_DELIVERY_HISTORY", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_DELIVERY_HISTORY", "*", conds, suffix);
        }
        ArrayList<SelmDeliveryHistory> al = new ArrayList<SelmDeliveryHistory>();
        while (rs.next()) {
        	SelmDeliveryHistory info = new SelmDeliveryHistory();
            setHistoryProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
	private void setProperties(SelmDelivery info, RecordSet rs) {
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
	private void setHistoryProperties(SelmDeliveryHistory info, RecordSet rs) {
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
	
	public int addCabinetInfo(CabinetInfo cabinetInfo){
		RecordSet rs;
		String sql = "INSERT INTO SELM_CABINET_INFO (ST_CABINET_ID, ST_CABINET_MAC, ST_CABINET_NUM, ST_CABINET_NO, NM_CABINET, ST_CABINET_STREET, ST_STREET_ADDRESS, ST_CONTACTS, ST_PHONE, DT_CREAT, ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = {
				cabinetInfo.getStCabinetId(),
				cabinetInfo.getStCabinetMac(),
				cabinetInfo.getStCabinetNum(),
				cabinetInfo.getStCabinetNo(),
				cabinetInfo.getNmCabinet(),
				cabinetInfo.getStCabinetStreet(),
				cabinetInfo.getStStreetAddress(),
				cabinetInfo.getStContacts(),
				cabinetInfo.getStPhone(),
				cabinetInfo.getDtCreat(),
				cabinetInfo.getStExt1(),
				cabinetInfo.getStExt2(),
				cabinetInfo.getStExt3(),
				cabinetInfo.getStExt4()
		};
		if(con == null){
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}
	
	public int updateCabinetInfo(String feild,Conditions conds, Object[] params) {
		String sql = " UPDATE SELM_CABINET_INFO SET "+feild+" WHERE "+conds.toString();
		return SQL.execute(sql, params).TOTAL_RECORD_COUNT;
	}
	
    public List<CabinetInfo> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_CABINET_INFO", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_CABINET_INFO", "*", conds, suffix);
        }
        ArrayList<CabinetInfo> al = new ArrayList<CabinetInfo>();
        while (rs.next()) {
        	CabinetInfo info = new CabinetInfo();
            setCabinetProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
	public CabinetInfo queryCaCabinetInfo(String machineId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_CABINET_MAC", Condition.OT_EQUAL, machineId));
        List<CabinetInfo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
	}
	
	private void setCabinetProperties(CabinetInfo info, RecordSet rs) {
		info.setStCabinetId(rs.getOriginalString("ST_CABINET_ID"));
		info.setStCabinetMac(rs.getOriginalString("ST_CABINET_MAC"));
		info.setStCabinetNum(rs.getOriginalString("ST_CABINET_NUM"));
		info.setStCabinetNo(rs.getOriginalString("ST_CABINET_NO"));
		info.setNmCabinet(new BigDecimal(rs.getOriginalString("NM_CABINET")));
		info.setStCabinetStreet(rs.getOriginalString("ST_CABINET_STREET"));
		info.setStStreetAddress(rs.getOriginalString("ST_STREET_ADDRESS"));
		info.setStContacts(rs.getOriginalString("ST_CONTACTS"));
		info.setStPhone(rs.getOriginalString("ST_PHONE"));
		info.setDtCreat(rs.getTimestamp("DT_CREAT"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
		info.setStExt3(rs.getOriginalString("ST_EXT3"));
		info.setStExt4(rs.getOriginalString("ST_EXT1"));
	}
}
