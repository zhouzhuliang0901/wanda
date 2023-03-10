package com.wondersgroup.sms.smsSlog.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import wfc.facility.tool.autocode.*;
import wfc.service.database.*;
import org.springframework.stereotype.Repository;

/**
 * 日志表
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class SmsSlogDao {

    private Connection con = null;

    public SmsSlogDao() {
    }

    public SmsSlogDao(Connection con) {
        this.con = con;
    }

    public void add(SmsSlog info) {
        String sql = "insert into SMS_SLOG(ST_LOG_ID, DT_START, ST_USER_ID, ST_USER_NAME, ST_URL, ST_MODULE, ST_LOG_TYPE, NM_HTTP_STATUS, NM_ELAPSEDTIME, ST_REQ_ID, ST_RES_ID, ST_EXCEPTION_ID, ST_IP, NM_SUCCESS, ST_SERVER_ID, ST_BROWSER_TYPE, DT_END) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStLogId(),
            info.getDtStart(),
            info.getStUserId(),
            info.getStUserName(),
            info.getStUrl(),
            info.getStModule(),
            info.getStLogType(),
            info.getNmHttpStatus(),
            info.getNmElapsedtime(),
            info.getStReqId(),
            info.getStResId(),
            info.getStExceptionId(),
            info.getStIp(),
            info.getNmSuccess(),
            info.getStServerId(),
            info.getStBrowserType(),
            info.getDtEnd()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(SmsSlog info) {
        String sql = "update SMS_SLOG set DT_START = ?, ST_USER_ID = ?, ST_USER_NAME = ?, ST_URL = ?, ST_MODULE = ?, ST_LOG_TYPE = ?, NM_HTTP_STATUS = ?, NM_ELAPSEDTIME = ?, ST_REQ_ID = ?, ST_RES_ID = ?, ST_EXCEPTION_ID = ?, ST_IP = ?, NM_SUCCESS = ?, ST_SERVER_ID = ?, ST_BROWSER_TYPE = ?, DT_END = ? where ST_LOG_ID = ?";
        Object[] obj = {
            info.getDtStart(),
            info.getStUserId(),
            info.getStUserName(),
            info.getStUrl(),
            info.getStModule(),
            info.getStLogType(),
            info.getNmHttpStatus(),
            info.getNmElapsedtime(),
            info.getStReqId(),
            info.getStResId(),
            info.getStExceptionId(),
            info.getStIp(),
            info.getNmSuccess(),
            info.getStServerId(),
            info.getStBrowserType(),
            info.getDtEnd(),
            info.getStLogId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update SMS_SLOG set ";
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
        String sql = "delete from SMS_SLOG";
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

	public void delete(String[] stLogId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stLogId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_LOG_ID", Condition.OT_EQUAL, stLogId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stLogId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_LOG_ID", Condition.OT_EQUAL, stLogId));
        delete(conds);
    }

    public PaginationArrayList<SmsSlog> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_SLOG", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SMS_SLOG", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SmsSlog> pal = new PaginationArrayList<SmsSlog>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            SmsSlog info = new SmsSlog();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<SmsSlog> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SMS_SLOG", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SMS_SLOG", "*", conds, suffix);
        }
        ArrayList<SmsSlog> al = new ArrayList<SmsSlog>();
        while (rs.next()) {
            SmsSlog info = new SmsSlog();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(SmsSlog info, RecordSet rs){
        info.setStLogId(rs.getOriginalString("ST_LOG_ID"));
        info.setDtStart(rs.getTimestamp("DT_START"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setStUserName(rs.getOriginalString("ST_USER_NAME"));
        info.setStUrl(rs.getOriginalString("ST_URL"));
        info.setStModule(rs.getOriginalString("ST_MODULE"));
        info.setStLogType(rs.getOriginalString("ST_LOG_TYPE"));
        info.setNmHttpStatus(rs.getBigDecimal("NM_HTTP_STATUS"));
        info.setNmElapsedtime(rs.getBigDecimal("NM_ELAPSEDTIME"));
        info.setStReqId(rs.getOriginalString("ST_REQ_ID"));
        info.setStResId(rs.getOriginalString("ST_RES_ID"));
        info.setStExceptionId(rs.getOriginalString("ST_EXCEPTION_ID"));
        info.setStIp(rs.getOriginalString("ST_IP"));
        info.setNmSuccess(rs.getBigDecimal("NM_SUCCESS"));
        info.setStServerId(rs.getOriginalString("ST_SERVER_ID"));
        info.setStBrowserType(rs.getOriginalString("ST_BROWSER_TYPE"));
        info.setDtEnd(rs.getTimestamp("DT_END"));
    }

	public SmsSlog get(String stLogId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_LOG_ID", Condition.OT_EQUAL, stLogId));
        List<SmsSlog> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
