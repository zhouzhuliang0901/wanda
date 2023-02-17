package com.wondersgroup.dataitem.item283852484724.dao;

import java.sql.Connection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.dataitem.item283852484724.bean.SelmSatisfactionInfo;

@Repository
public class SatisfactionEvaluationDao {
	
    private Connection con = null;

    public SatisfactionEvaluationDao() {
    }

    public SatisfactionEvaluationDao(Connection con) {
        this.con = con;
    }
    
	public int addSatisfaction(SelmSatisfactionInfo selmSatisfactionInfo){
		RecordSet rs;
		String sql = "INSERT INTO SELM_SATISFACTION_INFO "
				+" (ST_SATISFACTION_ID, NM_SATISFACTION_MACHINE, NM_SATISFACTION_APPEARANCE, NM_SATISFACTION_OPERATION, NM_SATISFACTION_SCREEN, "
				+" ST_SATISFACTION_CONTEXT, ST_EVALUATE_MACHINE_MAC, ST_EVALUATE_NAME, ST_EVALUATE_IDCARD, ST_EVALUATE_PHONE, DT_EVALUATE_TIME, "
				+" ST_EXT1, ST_EXT2, ST_EXT3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = {
				selmSatisfactionInfo.getStSatisfactionId(),
				selmSatisfactionInfo.getNmSatisfactionMachine(),
				selmSatisfactionInfo.getNmSatisfactionAppearacne(),
				selmSatisfactionInfo.getNmSatisfactionOperation(),
				selmSatisfactionInfo.getNmSatisfactionScreen(),
				selmSatisfactionInfo.getStSatisfactionContext(),
				selmSatisfactionInfo.getStEvaluateMachineMAC(),
				selmSatisfactionInfo.getStEvaluateName(),
				selmSatisfactionInfo.getStEvaluateIdCard(),
				selmSatisfactionInfo.getStEvaluatePhone(),
				selmSatisfactionInfo.getDtEvaluateTime(),
				selmSatisfactionInfo.getStExt1(),
				selmSatisfactionInfo.getStExt2(),
				selmSatisfactionInfo.getStExt3(),
		};
		if(con != null){
			rs = SQL.execute(con, sql, obj);
		} else {
			rs = SQL.execute(sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}
	
	public JSONObject getMachineInfoByMAC(String machineMAC){
		RecordSet rs;
		String sql = "select idi.ST_DEVICE_CODE,idi.ST_DEVICE_NAME,idi.ST_DEVICE_ADDRESS,ia.ST_LABEL, " 
				+ " ia.ST_DISTRICT,ia.ST_STREET from infopub_device_info idi "
				+ " left join infopub_address ia on ia.ST_ADDRESS_ID = idi.ST_ADDRESS_ID "
				+ " where idi.ST_DEVICE_MAC = ?";
		if(con != null){
			rs = SQL.execute(con, sql, new Object[]{machineMAC});
		} else {
			rs = SQL.execute(sql, new Object[]{machineMAC});
		}
		JSONArray array = new JSONArray();
		while(rs.next()){
			JSONObject obj = new JSONObject();
			obj.put("terminalNo", rs.getOriginalString("ST_DEVICE_CODE"));
			obj.put("terminalName", rs.getOriginalString("ST_DEVICE_NAME"));
			obj.put("serviceHallAddr", rs.getOriginalString("ST_DEVICE_ADDRESS"));
			obj.put("serviceHall", rs.getOriginalString("ST_LABEL"));
			obj.put("district", rs.getOriginalString("ST_DISTRICT"));
			obj.put("street", rs.getOriginalString("ST_STREET"));
			array.add(obj);
		}
		return array.size() > 0 ? array.getJSONObject(0) : new JSONObject();
	}
}
