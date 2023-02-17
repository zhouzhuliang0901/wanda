package reindeer.base.log.dao;

import reindeer.base.log.bean.SelmRequestLog;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository

;import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelmrequestlogDao {

	private Connection con = null;

	public void SelmRequestLogDao(){}

	public void SelmRequestLogDao(Connection con){
		this.con = con;
	}

	public PaginationArrayList<SelmRequestLog> query(Conditions conds, String suffix, int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SELM_REQUEST_LOG", "*", conds, suffix, pageSize, currentPage);
		} else {
			rs = SQL.query(con, "SELM_REQUEST_LOG", "*", conds, suffix, pageSize, currentPage);
		}
		PaginationArrayList<SelmRequestLog> pal = new PaginationArrayList<SelmRequestLog>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SelmRequestLog selmRequestLog = new SelmRequestLog();
			setProperties(selmRequestLog, rs);
			pal.add(selmRequestLog);
		}
		return pal;
	}
	
	public List<SelmRequestLog> query(){
		RecordSet rs;
		String sql = "select ST_REQUEST_METHOD_NAME,DT_REQUEST_TIME,ST_REQUEST_PARAM from selm_request_log_copy2 where ST_REQUEST_METHOD_NAME = 'creatOfflineCode'";
		List<SelmRequestLog> list = new ArrayList<SelmRequestLog>();
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		while (rs.next()) {
			SelmRequestLog selmRequestLog = new SelmRequestLog();
//			setProperties(selmRequestLog, rs);
//			selmRequestLog.setStRequestLogId(rs.getOriginalString("ST_REQUEST_LOG_ID"));
//			selmRequestLog.setStRequestIp(rs.getOriginalString("ST_REQUEST_IP"));
//			selmRequestLog.setStRequsetAddress(rs.getOriginalString("ST_REQUSET_ADDRESS"));
//			selmRequestLog.setStRequestMethodType(rs.getOriginalString("ST_REQUEST_METHOD_TYPE"));
//			selmRequestLog.setStRequestPackage(rs.getOriginalString("ST_REQUEST_PACKAGE"));
			selmRequestLog.setStRequestMethodName(rs.getOriginalString("ST_REQUEST_METHOD_NAME"));
//			selmRequestLog.setStRequestUrl(rs.getOriginalString("ST_REQUEST_URL"));
			selmRequestLog.setDtRequestTime(rs.getTimestamp("DT_REQUEST_TIME"));
			selmRequestLog.setStRequestParam(rs.getOriginalString("ST_REQUEST_PARAM"));
//			selmRequestLog.setStRequestResponse(rs.getOriginalString("ST_REQUEST_RESPONSE"));
//			selmRequestLog.setStRequestMachineMac(rs.getOriginalString("ST_REQUEST_MACHINE_MAC"));
//			selmRequestLog.setStRequestMachineName(rs.getOriginalString("ST_REQUEST_MACHINE_NAME"));
//			selmRequestLog.setStRequestMachineAddress(rs.getOriginalString("ST_REQUEST_MACHINE_ADDRESS"));
//			selmRequestLog.setStExt1(rs.getOriginalString("ST_EXT1"));
//			selmRequestLog.setStExt2(rs.getOriginalString("ST_EXT2"));
			list.add(selmRequestLog);
		}
		return list;
	}

	public int insert(SelmRequestLog selmRequestLog){
		RecordSet rs;		
		String sql = "insert into SELM_REQUEST_LOG(ST_REQUEST_LOG_ID, ST_REQUEST_IP, ST_REQUSET_ADDRESS, ST_REQUEST_METHOD_TYPE, ST_REQUEST_PACKAGE, ST_REQUEST_METHOD_NAME, ST_REQUEST_URL, DT_REQUEST_TIME, ST_REQUEST_PARAM, ST_REQUEST_RESPONSE, ST_REQUEST_MACHINE_MAC, ST_REQUEST_MACHINE_NAME, ST_REQUEST_MACHINE_ADDRESS, ST_EXT1, ST_EXT2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
			selmRequestLog.getStRequestLogId(),
			selmRequestLog.getStRequestIp(),
			selmRequestLog.getStRequsetAddress(),
			selmRequestLog.getStRequestMethodType(),
			selmRequestLog.getStRequestPackage(),
			selmRequestLog.getStRequestMethodName(),
			selmRequestLog.getStRequestUrl(),
			selmRequestLog.getDtRequestTime(),
			selmRequestLog.getStRequestParam(),
			selmRequestLog.getStRequestResponse(),
			selmRequestLog.getStRequestMachineMac(),
			selmRequestLog.getStRequestMachineName(),
			selmRequestLog.getStRequestMachineAddress(),
			selmRequestLog.getStExt1(),
			selmRequestLog.getStExt2(),
		};
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}


	public int update(Map<String, Object> map, Conditions conds){
		String sql = "update SELM_REQUEST_LOG set ";
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
		String sql = "delete from SELM_REQUEST_LOG";
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

	private void setProperties(SelmRequestLog selmRequestLog, RecordSet rs) {
		selmRequestLog.setStRequestLogId(rs.getOriginalString("ST_REQUEST_LOG_ID"));
		selmRequestLog.setStRequestIp(rs.getOriginalString("ST_REQUEST_IP"));
		selmRequestLog.setStRequsetAddress(rs.getOriginalString("ST_REQUSET_ADDRESS"));
		selmRequestLog.setStRequestMethodType(rs.getOriginalString("ST_REQUEST_METHOD_TYPE"));
		selmRequestLog.setStRequestPackage(rs.getOriginalString("ST_REQUEST_PACKAGE"));
		selmRequestLog.setStRequestMethodName(rs.getOriginalString("ST_REQUEST_METHOD_NAME"));
		selmRequestLog.setStRequestUrl(rs.getOriginalString("ST_REQUEST_URL"));
		selmRequestLog.setDtRequestTime(rs.getTimestamp("DT_REQUEST_TIME"));
		selmRequestLog.setStRequestParam(rs.getOriginalString("ST_REQUEST_PARAM"));
		selmRequestLog.setStRequestResponse(rs.getOriginalString("ST_REQUEST_RESPONSE"));
		selmRequestLog.setStRequestMachineMac(rs.getOriginalString("ST_REQUEST_MACHINE_MAC"));
		selmRequestLog.setStRequestMachineName(rs.getOriginalString("ST_REQUEST_MACHINE_NAME"));
		selmRequestLog.setStRequestMachineAddress(rs.getOriginalString("ST_REQUEST_MACHINE_ADDRESS"));
		selmRequestLog.setStExt1(rs.getOriginalString("ST_EXT1"));
		selmRequestLog.setStExt2(rs.getOriginalString("ST_EXT2"));
	}}