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

import com.wondersgroup.infopub.bean.InfopubDeviceLog;

/**
 * 设备日志记录
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubDeviceLogDao {

    private Connection con = null;

    public InfopubDeviceLogDao() {
    }

    public InfopubDeviceLogDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubDeviceLog info) {
        String sql = "insert into INFOPUB_DEVICE_LOG(ST_DEVICE_LOG_ID, ST_DEVICE_ID, ST_THREAD, ST_LEVEL, ST_LOGGER, ST_OPERATOR, ST_OPERAND, ST_ACTION, ST_LOCATION, ST_LINE, ST_METHOD, ST_MSG, ST_EXCEPTION, DT_CREATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStDeviceLogId(),
            info.getStDeviceId(),
            info.getStThread(),
            info.getStLevel(),
            info.getStLogger(),
            info.getStOperator(),
            info.getStOperand(),
            info.getStAction(),
            info.getStLocation(),
            info.getStLine(),
            info.getStMethod(),
            info.getStMsg(),
            info.getStException(),
            info.getDtCreate(),
            info.getStExt1(),
            info.getStExt2()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubDeviceLog info) {
        String sql = "update INFOPUB_DEVICE_LOG set ST_DEVICE_ID = ?, ST_THREAD = ?, ST_LEVEL = ?, ST_LOGGER = ?, ST_OPERATOR = ?, ST_OPERAND = ?, ST_ACTION = ?, ST_LOCATION = ?, ST_LINE = ?, ST_METHOD = ?, ST_MSG = ?, ST_EXCEPTION = ?, DT_CREATE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_DEVICE_LOG_ID = ?";
        Object[] obj = {
            info.getStDeviceId(),
            info.getStThread(),
            info.getStLevel(),
            info.getStLogger(),
            info.getStOperator(),
            info.getStOperand(),
            info.getStAction(),
            info.getStLocation(),
            info.getStLine(),
            info.getStMethod(),
            info.getStMsg(),
            info.getStException(),
            info.getDtCreate(),
            info.getStExt1(),
            info.getStExt2(),
            info.getStDeviceLogId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_DEVICE_LOG set ";
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
        String sql = "delete from INFOPUB_DEVICE_LOG";
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

	public void delete(String[] stDeviceLogId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stDeviceLogId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_DEVICE_LOG_ID", Condition.OT_EQUAL, stDeviceLogId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stDeviceLogId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_LOG_ID", Condition.OT_EQUAL, stDeviceLogId));
        delete(conds);
    }

    public PaginationArrayList<InfopubDeviceLog> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_LOG", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_LOG", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubDeviceLog> pal = new PaginationArrayList<InfopubDeviceLog>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubDeviceLog info = new InfopubDeviceLog();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubDeviceLog> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_LOG", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_LOG", "*", conds, suffix);
        }
        ArrayList<InfopubDeviceLog> al = new ArrayList<InfopubDeviceLog>();
        while (rs.next()) {
            InfopubDeviceLog info = new InfopubDeviceLog();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubDeviceLog info, RecordSet rs){
        info.setStDeviceLogId(rs.getOriginalString("ST_DEVICE_LOG_ID"));
        info.setStDeviceId(rs.getOriginalString("ST_DEVICE_ID"));
        info.setStThread(rs.getOriginalString("ST_THREAD"));
        info.setStLevel(rs.getOriginalString("ST_LEVEL"));
        info.setStLogger(rs.getOriginalString("ST_LOGGER"));
        info.setStOperator(rs.getOriginalString("ST_OPERATOR"));
        info.setStOperand(rs.getOriginalString("ST_OPERAND"));
        info.setStAction(rs.getOriginalString("ST_ACTION"));
        info.setStLocation(rs.getOriginalString("ST_LOCATION"));
        info.setStLine(rs.getOriginalString("ST_LINE"));
        info.setStMethod(rs.getOriginalString("ST_METHOD"));
        info.setStMsg(rs.getOriginalString("ST_MSG"));
        info.setStException(rs.getOriginalString("ST_EXCEPTION"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

	public InfopubDeviceLog get(String stDeviceLogId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_LOG_ID", Condition.OT_EQUAL, stDeviceLogId));
        List<InfopubDeviceLog> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
}