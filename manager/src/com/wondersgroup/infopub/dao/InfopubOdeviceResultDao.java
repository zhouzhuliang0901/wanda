package com.wondersgroup.infopub.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;


/**
 * 外设状态结果信息
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubOdeviceResultDao {

    private Connection con = null;

    public InfopubOdeviceResultDao() {
    }

    public InfopubOdeviceResultDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubOdeviceStatus info) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String date = simpleDateFormat.format(new Date());
        String sql = "insert into INFOPUB_ODEVICE_RESULT(ST_OUT_DEVICE_RESULT_ID, ST_DEVICE_ID, ST_OUT_DEVICE_CODE, NM_EXCEPTION, ST_CAUSE, NM_NOTICE, NM_TOTAL, NM_REMAIN, DT_UPDATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            date,
            info.getStDeviceId(),
            info.getStOutDeviceCode(),
            info.getNmException(),
            info.getStCause(),
            info.getNmNotice(),
            info.getNmTotal(),
            info.getNmRemain(),
            new Timestamp(System.currentTimeMillis()),
            info.getStExt1(),
            info.getStExt2()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubOdeviceStatus info) {
        String sql = "update INFOPUB_ODEVICE_RESULT set ST_DEVICE_ID = ?, ST_OUT_DEVICE_CODE = ?, NM_EXCEPTION = ?, ST_CAUSE = ?, NM_NOTICE = ?, NM_TOTAL = ?, NM_REMAIN = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_OUT_DEVICE_RESULT_ID = ?";
        Object[] obj = {
            info.getStDeviceId(),
            info.getStOutDeviceCode(),
            info.getNmException(),
            info.getStCause(),
            info.getNmNotice(),
            info.getNmTotal(),
            info.getNmRemain(),
            info.getDtUpdate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStOutDeviceResultId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_ODEVICE_RESULT set ";
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
        String sql = "delete from INFOPUB_ODEVICE_RESULT";
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

	public void delete(String[] stOutDeviceResultId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stOutDeviceResultId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_OUT_DEVICE_RESULT_ID", Condition.OT_EQUAL, stOutDeviceResultId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stOutDeviceResultId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_OUT_DEVICE_RESULT_ID", Condition.OT_EQUAL, stOutDeviceResultId));
        delete(conds);
    }

    public PaginationArrayList<InfopubOdeviceStatus> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ODEVICE_RESULT", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_ODEVICE_RESULT", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubOdeviceStatus> pal = new PaginationArrayList<InfopubOdeviceStatus>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
        	InfopubOdeviceStatus info = new InfopubOdeviceStatus();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubOdeviceStatus> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ODEVICE_RESULT", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ODEVICE_RESULT", "*", conds, suffix);
        }
        ArrayList<InfopubOdeviceStatus> al = new ArrayList<InfopubOdeviceStatus>();
        while (rs.next()) {
        	InfopubOdeviceStatus info = new InfopubOdeviceStatus();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubOdeviceStatus info, RecordSet rs){
        info.setStOutDeviceResultId(rs.getOriginalString("ST_OUT_DEVICE_RESULT_ID"));
        info.setStDeviceId(rs.getOriginalString("ST_DEVICE_ID"));
        info.setStOutDeviceCode(rs.getOriginalString("ST_OUT_DEVICE_CODE"));
        info.setNmException(rs.getBigDecimal("NM_EXCEPTION"));
        info.setStCause(rs.getOriginalString("ST_CAUSE"));
        info.setNmNotice(rs.getBigDecimal("NM_NOTICE"));
        info.setNmTotal(rs.getBigDecimal("NM_TOTAL"));
        info.setNmRemain(rs.getBigDecimal("NM_REMAIN"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public InfopubOdeviceStatus get(String stOutDeviceResultId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_OUT_DEVICE_RESULT_ID", Condition.OT_EQUAL, stOutDeviceResultId));
        List<InfopubOdeviceStatus> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
