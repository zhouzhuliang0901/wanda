package reindeer.workday.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class WorkdayDao {

	private Connection con = null;

	public WorkdayDao() {
	}

	public WorkdayDao(Connection con) {
		this.con = con;
	}

	public Set<Workday> findAll() {
		String sql = "SELECT * FROM WWD_WORKDAY";
		Set<Workday> set = new HashSet<Workday>();
		RecordSet rs = SQL.execute(sql);
		while (rs.next()) {
			Workday workday = new Workday();
			Timestamp date = rs.getTimestamp("DT_DAY");
			workday.setStWorkdayId(rs.getString("ST_WORKDAY_ID"));
			workday.setDtWorkday(date);
			workday.setStIsWorkday(rs.getString("ST_IS_WORKDAY"));
			set.add(workday);
		}
		return set;
	}

	public void add(Workday workday) {
		String sql = "insert into WWD_WORKDAY(ST_WORKDAY_ID,DT_DAY,ST_IS_WORKDAY) values(?,?,?)";
		Object[] info = { workday.getStWorkdayId(), workday.getDtWorkday(),
				workday.getStIsWorkday() };
		if (con == null) {
			SQL.execute(sql, info);
		} else {
			SQL.execute(con, sql, info);
		}
	}

	public int delete(Date cal) {
		String sql = "delete from WWD_WORKDAY where DT_DAY = ?";
		Object[] obj = { new Timestamp(cal.getTime()) };
		if (con == null) {
			return SQL.execute(sql, obj).TOTAL_RECORD_COUNT;
		} else {
			return SQL.execute(con, sql, obj).TOTAL_RECORD_COUNT;
		}
	}

	public void update(Workday info) {
		String sql = "update WWD_WORKDAY set DT_DAY = ?, ST_IS_WORKDAY = ? where ST_WORKDAY_ID = ?";
		Object[] obj = { info.getDtWorkday(), info.getStIsWorkday(),
				info.getStWorkdayId(), };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public Workday getWorkdayById(String id) {
		String sql = "select * from WWD_WORKDAY where ST_WORKDAY_ID = ?";
		Object[] obj = { id };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		Workday workday = new Workday();
		while (rs.next()) {
			workday.setStWorkdayId(rs.getString("ST_WORKDAY_ID"));
			workday.setDtWorkday(rs.getTimestamp("DT_DAY"));
			workday.setStIsWorkday(rs.getString("ST_IS_WORKDAY"));
		}
		return workday;
	}

	public Workday getWorkdayByDate(Date date) {
		String sql = "select * from WWD_WORKDAY where DT_DAY = ?";
		Object[] obj = { new Timestamp(date.getTime()) };
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		Workday workday = new Workday();
		while (rs.next()) {
			workday.setStWorkdayId(rs.getString("ST_WORKDAY_ID"));
			workday.setDtWorkday(rs.getTimestamp("DT_DAY"));
			workday.setStIsWorkday(rs.getString("ST_IS_WORKDAY"));
		}
		return workday;
	}
}
