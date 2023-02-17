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

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;

/**
 * 设备（	外设）状态
 */
@Repository
public class InfopubOdeviceStatusExtDao {

	private Connection con = null;

	public InfopubOdeviceStatusExtDao() {
	}

	public InfopubOdeviceStatusExtDao(Connection con) {
		this.con = con;
	}

	public void add(InfopubOdeviceStatus info) {
		String sql = "insert into INFOPUB_ODEVICE_STATUS(ST_OUT_DEVICE_RESULT_ID, ST_DEVICE_ID, ST_OUT_DEVICE_CODE, NM_EXCEPTION, ST_CAUSE, NM_NOTICE, NM_TOTAL, NM_REMAIN, NM_HIS_TOTAL, NM_HIS_STOTAL, NM_HIS_FTOTAL, DT_UPDATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { 
				info.getStOutDeviceResultId(),
				info.getStDeviceId(),
				info.getStOutDeviceCode(),
				info.getNmException(),
				info.getStCause(),
				info.getNmNotice(),
				info.getNmTotal(),
				info.getNmRemain(),
				info.getNmHisTotal(),
				info.getNmHisStotal(),
				info.getNmHisFtotal(),
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

	public void update(InfopubOdeviceStatus info) {
		String sql = "update INFOPUB_ODEVICE_STATUS set NM_EXCEPTION = ?, ST_CAUSE = ?, NM_NOTICE = ?, NM_TOTAL = ?, NM_REMAIN = ?,NM_HIS_TOTAL = ?, NM_HIS_STOTAL = ?, NM_HIS_FTOTAL = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ? where ST_DEVICE_ID = ? and ST_OUT_DEVICE_CODE = ?";
		Object[] obj = {
				info.getNmException(),
				info.getStCause(),
				info.getNmNotice(),
				info.getNmTotal(),
				info.getNmRemain(),
				info.getNmHisTotal(),
				info.getNmHisStotal(),
				info.getNmHisFtotal(),
				info.getDtUpdate(),
				info.getStExt1(),
				info.getStExt2(),
				info.getStDeviceId(),
				info.getStOutDeviceCode()
		};
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "update INFOPUB_ODEVICE_STATUS set ";
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
		String sql = "delete from INFOPUB_ODEVICE_STATUS";
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
			subconds.add(new Condition("ST_OUT_DEVICE_RESULT_ID",
					Condition.OT_EQUAL, stOutDeviceResultId[i]));
			conds.add(subconds);
		}
		delete(conds);
	}

	public void delete(String stOutDeviceResultId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_OUT_DEVICE_RESULT_ID", Condition.OT_EQUAL,
				stOutDeviceResultId));
		delete(conds);
	}

	public PaginationArrayList<InfopubOdeviceStatus> query(Conditions conds,
			String suffix, int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("INFOPUB_ODEVICE_STATUS", "*", conds, suffix,
					pageSize, currentPage);
		} else {
			rs = SQL.query(con, "INFOPUB_ODEVICE_STATUS", "*", conds, suffix,
					pageSize, currentPage);
		}
		PaginationArrayList<InfopubOdeviceStatus> pal = new PaginationArrayList<InfopubOdeviceStatus>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
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
			rs = SQL.query("INFOPUB_ODEVICE_STATUS", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "INFOPUB_ODEVICE_STATUS", "*", conds, suffix);
		}
		ArrayList<InfopubOdeviceStatus> al = new ArrayList<InfopubOdeviceStatus>();
		while (rs.next()) {
			InfopubOdeviceStatus info = new InfopubOdeviceStatus();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
	
	public List<InfopubOdeviceStatus> distinct() {
		String sql = "select distinct ST_OUT_DEVICE_CODE from INFOPUB_ODEVICE_STATUS ";
        RecordSet rs;
        if (con == null) {
            rs = SQL.execute(sql);
        } else {
            rs = SQL.execute(con, sql);
        }
		ArrayList<InfopubOdeviceStatus> al = new ArrayList<InfopubOdeviceStatus>();
		while (rs.next()) {
			InfopubOdeviceStatus info = new InfopubOdeviceStatus();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

	public static void setProperties(InfopubOdeviceStatus info, RecordSet rs) {
		info.setStOutDeviceResultId(rs.getOriginalString("ST_OUT_DEVICE_RESULT_ID"));
		info.setStDeviceId(rs.getOriginalString("ST_DEVICE_ID"));
		info.setStOutDeviceCode(rs.getOriginalString("ST_OUT_DEVICE_CODE"));
		info.setNmException(rs.getBigDecimal("NM_EXCEPTION"));
		info.setStCause(rs.getOriginalString("ST_CAUSE"));
		info.setNmNotice(rs.getBigDecimal("NM_NOTICE"));
		info.setNmTotal(rs.getBigDecimal("NM_TOTAL"));
		info.setNmRemain(rs.getBigDecimal("NM_REMAIN"));
		info.setNmHisTotal(rs.getBigDecimal("NM_HIS_TOTAL"));
		info.setNmHisStotal(rs.getBigDecimal("NM_HIS_STOTAL"));
		info.setNmHisFtotal(rs.getBigDecimal("NM_HIS_FTOTAL"));
		info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
	}

	public InfopubOdeviceStatus get(String stOutDeviceResultId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_OUT_DEVICE_RESULT_ID", Condition.OT_EQUAL,
				stOutDeviceResultId));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public InfopubOdeviceStatus getOdeviceStatus(InfopubOdeviceStatus infopubOdeviceStatus) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL,
				infopubOdeviceStatus.getStDeviceId()));
		conds.add(new Condition("ST_OUT_DEVICE_CODE", Condition.OT_EQUAL,
				infopubOdeviceStatus.getStOutDeviceCode()));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<InfopubOdeviceStatus> getByDeviceId(String stDeviceId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list : null;
	}

	public List<InfopubOdeviceStatus> getCount() {
		String sql = "SELECT ST_DEVICE_ID from infopub_odevice_status WHERE NM_EXCEPTION ='1' GROUP BY ST_DEVICE_ID;";
        RecordSet rs;
        if (con == null) {
            rs = SQL.execute(sql);
        } else {
            rs = SQL.execute(con, sql);
        }
		ArrayList<InfopubOdeviceStatus> al = new ArrayList<InfopubOdeviceStatus>();
		while (rs.next()) {
			InfopubOdeviceStatus info = new InfopubOdeviceStatus();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	
	}
	/**
	 * 时间段查询异常
	 * @param conds
	 * @param suffix
	 * @return
	 */
	public List<InfopubOdeviceStatus> queryDate(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("INFOPUB_ODEVICE_STATUS", "ST_DEVICE_ID", conds, suffix);
		} else {
			rs = SQL.query(con, "INFOPUB_ODEVICE_STATUS", "ST_DEVICE_ID", conds, suffix);
		}
		ArrayList<InfopubOdeviceStatus> al = new ArrayList<InfopubOdeviceStatus>();
		while (rs.next()) {
			InfopubOdeviceStatus info = new InfopubOdeviceStatus();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

	public List<InfopubOdeviceStatus> getByTypeCode(InfopubDeviceInfo infopubDeviceInfo) {
		String stDeviceId = infopubDeviceInfo.getStDeviceMac();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list : null;
	}
	
	/*
	 * 查询异常设备
	 */
	public List<InfopubOdeviceStatus> getByExcepyion(String stDeviceId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
		conds.add(new Condition("NM_EXCEPTION", Condition.OT_EQUAL, "1"));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list : null;
	}
	/*
	 * 查询医保数量
	 */
	public List<InfopubOdeviceStatus> getByMecount(String stDeviceId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
		conds.add(new Condition("ST_OUT_DEVICE_CODE", Condition.OT_EQUAL, "MedicalInsuranc"));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list : null;
	}
	
	/*
	 * 查询打印纸数量
	 */
	public List<InfopubOdeviceStatus> getByPrcount(String stDeviceId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
		conds.add(new Condition("ST_OUT_DEVICE_CODE", Condition.OT_EQUAL, "A4Printer"));
		List<InfopubOdeviceStatus> list = query(conds, null);
		return list.size() > 0 ? list : null;
	}
	
}
