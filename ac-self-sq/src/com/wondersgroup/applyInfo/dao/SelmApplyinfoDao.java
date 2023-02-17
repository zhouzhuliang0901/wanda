package com.wondersgroup.applyInfo.dao;

import com.wondersgroup.applyInfo.bean.SelmApplyInfo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository

;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelmApplyinfoDao {

	private Connection con = null;

	public void SelmApplyInfoDao() {
	}

	public void SelmApplyInfoDao(Connection con) {
		this.con = con;
	}

	public PaginationArrayList<SelmApplyInfo> query(Conditions conds,
			String suffix, int pageSize, int currentPage) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SELM_APPLY_INFO", "*", conds, suffix, pageSize,
					currentPage);
		} else {
			rs = SQL.query(con, "SELM_APPLY_INFO", "*", conds, suffix,
					pageSize, currentPage);
		}
		PaginationArrayList<SelmApplyInfo> pal = new PaginationArrayList<SelmApplyInfo>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SelmApplyInfo selmApplyInfo = new SelmApplyInfo();
			setProperties(selmApplyInfo, rs);
			pal.add(selmApplyInfo);
		}
		return pal;
	}

	public int insert(SelmApplyInfo selmApplyInfo) {
		RecordSet rs;
		String sql = "insert into SELM_APPLY_INFO(ST_QUERY_HIS_ID, ST_MACHINE_ID, ST_MODULE_NAME, ST_MODULE_OP, " +
				"ST_ITEM_NAME, ST_ITEM_NO, ST_NAME, ST_IDENTITY_NO, ST_MOBILE, ST_BUSINESS_NO, ST_SUBMIT_DATA_ID, " +
				"ST_DESC, ST_OP_RESULT, DT_CREATE, ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4, ST_EXT5) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = { selmApplyInfo.getStQueryHisId(),
				selmApplyInfo.getStMachineId(),
				selmApplyInfo.getStModuleName(), selmApplyInfo.getStModuleOp(),
				selmApplyInfo.getStItemName(), selmApplyInfo.getStItemNo(),
				selmApplyInfo.getStName(), selmApplyInfo.getStIdentityNo(),
				selmApplyInfo.getStMobile(), selmApplyInfo.getStBusinessNo(),
				selmApplyInfo.getStSubmitDataId(), selmApplyInfo.getStDesc(),
				selmApplyInfo.getStOpResult(), selmApplyInfo.getDtCreate(),
				selmApplyInfo.getStExt1(), selmApplyInfo.getStExt2(),
				selmApplyInfo.getStExt3(), selmApplyInfo.getStExt4(),
				selmApplyInfo.getStExt5(), };
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		return rs.TOTAL_RECORD_COUNT;
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "update SELM_APPLY_INFO set ";
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
		String sql = "delete from SELM_APPLY_INFO";
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

	private void setProperties(SelmApplyInfo selmApplyInfo, RecordSet rs) {
		selmApplyInfo.setStQueryHisId(rs.getOriginalString("ST_QUERY_HIS_ID"));
		selmApplyInfo.setStMachineId(rs.getOriginalString("ST_MACHINE_ID"));
		selmApplyInfo.setStModuleName(rs.getOriginalString("ST_MODULE_NAME"));
		selmApplyInfo.setStModuleOp(rs.getOriginalString("ST_MODULE_OP"));
		selmApplyInfo.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
		selmApplyInfo.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
		selmApplyInfo.setStName(rs.getOriginalString("ST_NAME"));
		selmApplyInfo.setStIdentityNo(rs.getOriginalString("ST_IDENTITY_NO"));
		selmApplyInfo.setStMobile(rs.getOriginalString("ST_MOBILE"));
		selmApplyInfo.setStBusinessNo(rs.getOriginalString("ST_BUSINESS_NO"));
		selmApplyInfo.setStSubmitDataId(rs
				.getOriginalString("ST_SUBMIT_DATA_ID"));
		selmApplyInfo.setStDesc(rs.getOriginalString("ST_DESC"));
		selmApplyInfo.setStOpResult(rs.getOriginalString("ST_OP_RESULT"));
		selmApplyInfo.setDtCreate(rs.getTimestamp("DT_CREATE"));
		selmApplyInfo.setStExt1(rs.getOriginalString("ST_EXT1"));
		selmApplyInfo.setStExt2(rs.getOriginalString("ST_EXT2"));
		selmApplyInfo.setStExt3(rs.getOriginalString("ST_EXT3"));
		selmApplyInfo.setStExt4(rs.getOriginalString("ST_EXT4"));
		selmApplyInfo.setStExt5(rs.getOriginalString("ST_EXT5"));
	}
}