package reindeer.base.log.dao;

import reindeer.base.log.bean.SelmExceptionInfo;
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
public class SelmexceptioninfoDao {

	private Connection con = null;

	public void SelmExceptionInfoDao(){}

	public void SelmExceptionInfoDao(Connection con){
		this.con = con;
	}

	public PaginationArrayList<SelmExceptionInfo> query(Conditions conds, String suffix, int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SELM_EXCEPTION_INFO", "*", conds, suffix, pageSize, currentPage);
		} else {
			rs = SQL.query(con, "SELM_EXCEPTION_INFO", "*", conds, suffix, pageSize, currentPage);
		}
		PaginationArrayList<SelmExceptionInfo> pal = new PaginationArrayList<SelmExceptionInfo>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SelmExceptionInfo selmExceptionInfo = new SelmExceptionInfo();
			setProperties(selmExceptionInfo, rs);
			pal.add(selmExceptionInfo);
		}
		return pal;
	}


	public int insert(SelmExceptionInfo selmExceptionInfo){
		RecordSet rs;		
		String sql = "insert into SELM_EXCEPTION_INFO(ST_ID, ST_EXCEPTION_METHOD, ST_EXCEPTION_PACKAGE, ST_EXCEPTION_CAUSE, ST_EXCEPTION_LINE, ST_EXCEPTION_FILE, DT_EXCEPTION_TIME, ST_REQUEST_URL, ST_REQUEST_IP, ST_REQUEST_METHOD, ST_REQUEST_PARAM, ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
			selmExceptionInfo.getStId(),
			selmExceptionInfo.getStExceptionMethod(),
			selmExceptionInfo.getStExceptionPackage(),
			selmExceptionInfo.getStExceptionCause(),
			selmExceptionInfo.getStExceptionLine(),
			selmExceptionInfo.getStExceptionFile(),
			selmExceptionInfo.getDtExceptionTime(),
			selmExceptionInfo.getStRequestUrl(),
			selmExceptionInfo.getStRequestIp(),
			selmExceptionInfo.getStRequestMethod(),
			selmExceptionInfo.getStRequestParam(),
			selmExceptionInfo.getStExt1(),
			selmExceptionInfo.getStExt2(),
			selmExceptionInfo.getStExt3(),
			selmExceptionInfo.getStExt4(),
		};
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}


	public int update(Map<String, Object> map, Conditions conds){
		String sql = "update SELM_EXCEPTION_INFO set ";
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
		String sql = "delete from SELM_EXCEPTION_INFO";
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

	private void setProperties(SelmExceptionInfo selmExceptionInfo, RecordSet rs) {
		selmExceptionInfo.setStId(rs.getOriginalString("ST_ID"));
		selmExceptionInfo.setStExceptionMethod(rs.getOriginalString("ST_EXCEPTION_METHOD"));
		selmExceptionInfo.setStExceptionPackage(rs.getOriginalString("ST_EXCEPTION_PACKAGE"));
		selmExceptionInfo.setStExceptionCause(rs.getOriginalString("ST_EXCEPTION_CAUSE"));
		selmExceptionInfo.setStExceptionLine(Integer.valueOf(rs.getOriginalString("ST_EXCEPTION_LINE")));
		selmExceptionInfo.setStExceptionFile(rs.getOriginalString("ST_EXCEPTION_FILE"));
		selmExceptionInfo.setDtExceptionTime(rs.getTimestamp("DT_EXCEPTION_TIME"));
		selmExceptionInfo.setStRequestUrl(rs.getOriginalString("ST_REQUEST_URL"));
		selmExceptionInfo.setStRequestIp(rs.getOriginalString("ST_REQUEST_IP"));
		selmExceptionInfo.setStRequestMethod(rs.getOriginalString("ST_REQUEST_METHOD"));
		selmExceptionInfo.setStRequestParam(rs.getOriginalString("ST_REQUEST_PARAM"));
		selmExceptionInfo.setStExt1(rs.getOriginalString("ST_EXT1"));
		selmExceptionInfo.setStExt2(rs.getOriginalString("ST_EXT2"));
		selmExceptionInfo.setStExt3(rs.getOriginalString("ST_EXT3"));
		selmExceptionInfo.setStExt4(rs.getOriginalString("ST_EXT4"));
	}}