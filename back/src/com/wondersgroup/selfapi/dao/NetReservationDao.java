package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.NetReservationVo;
import com.wondersgroup.selfapi.bean.ReservationVoPage;

@Repository
public class NetReservationDao {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	
	
	private Connection con = null;

	public NetReservationDao() {
	}

	public NetReservationDao(Connection con) {
		this.con = con;
	}

	
	
	
	
	/**
	 * 方法描述：通过预约号查询历史预约信息（取过号的和取消预约的预约信息）
	 * @param reservationNo
	 * @return
	 */
	public NetReservationVo findHistoryReservationByNo(String reservationNo) {
		NetReservationVo info = null;
		String sql = "SELECT * FROM  NET_RESERVATION_INFO WHERE NM_REMOVED != 0 and ST_RESERVATION_NO = ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { reservationNo });
		} else {
			rs = SQL.execute(con, sql, new Object[] { reservationNo });
		}
		if (rs.next()) {
			info = new NetReservationVo();
			setNetReservationVo(info, rs);
		}
		return info;
	}
	
	
	
	/**
	 * 方法描述：通过预约号查询预约信息（已经预约但是未取号的预约信息）
	 * @param reservationNo
	 * @return
	 */
	public NetReservationVo findReservationByNo(String reservationNo) {
		NetReservationVo info = null;
		String sql = "SELECT * FROM  NET_RESERVATION_INFO WHERE NM_REMOVED = 0 and ST_RESERVATION_NO = ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { reservationNo });
		} else {
			rs = SQL.execute(con, sql, new Object[] { reservationNo });
		}
		if (rs.next()) {
			info = new NetReservationVo();
			setNetReservationVo(info, rs);
		}
		return info;
	}
	
	
	
	/**
	 * 方法描述：通过身份证号查询预约信息（已经预约但是未取号的预约信息）
	 * @param certNo
	 * @return
	 */
	public List<NetReservationVo> findReservationListByCertNo(String certNo) {
		List<NetReservationVo> netReservationVoList = new ArrayList<NetReservationVo>();
		String sql = "SELECT * FROM  NET_RESERVATION_INFO WHERE NM_REMOVED = 0 and ST_IDENTITY_NO = ? ORDER BY DT_RESERVATION_START DESC ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { certNo });
		} else {
			rs = SQL.execute(con, sql, new Object[] { certNo });
		}
		while (rs.next()) {
			NetReservationVo info = new NetReservationVo();
			setNetReservationVo(info, rs);
			netReservationVoList.add(info);
		}
		return netReservationVoList.size() > 0 ? netReservationVoList : null;
	}
	
	
	
	/**
	 * 方法描述：通过身份证号查询预约信息（取过号的和取消预约的预约信息）
	 * @param certNo
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ReservationVoPage findHistoryReservationListByCertNo(String certNo,
			int pageSize, int currentPage) {
		List<NetReservationVo> netReservationVoList = new ArrayList<NetReservationVo>();
		String sql = "SELECT * FROM  NET_RESERVATION_INFO WHERE NM_REMOVED != 0 and ST_IDENTITY_NO = ? ORDER BY DT_RESERVATION_START DESC ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, pageSize, currentPage,
					new Object[] { certNo });
		} else {
			rs = SQL.execute(con, sql, pageSize, currentPage,
					new Object[] { certNo });
		}
		ReservationVoPage page = new ReservationVoPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE);
		while (rs.next()) {
			NetReservationVo info = new NetReservationVo();
			setNetReservationVo(info, rs);
			netReservationVoList.add(info);
		}
		page.setRowList(netReservationVoList);
		return page;
	}
	
	
	
	/**
	 * 方法描述：保存预约信息
	 * @param info
	 * @param rs
	 */
	public static void setNetReservationVo(NetReservationVo info, RecordSet rs) {
		info.setStReservationId(rs.getOriginalString("ST_RESERVATION_ID"));
		info.setStReservationNo(rs.getOriginalString("ST_RESERVATION_NO"));
		info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
		info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
		info.setStGroupCode(rs.getOriginalString("ST_GROUP_CODE"));
		info.setDtOperation(rs.getTimestamp("DT_OPERATION"));
		info.setDtReservationStart(rs.getTimestamp("DT_RESERVATION_START"));
		info.setDtReservationEnd(rs.getTimestamp("DT_RESERVATION_END"));
		info.setStUserId(rs.getOriginalString("ST_USER_ID"));
		info.setStUserName(rs.getOriginalString("ST_USER_NAME"));
		info.setStMobile(rs.getOriginalString("ST_MOBILE"));
		info.setNmIdentityType(rs.getBigDecimal("NM_IDENTITY_TYPE"));
		info.setStIdentityNo(rs.getOriginalString("ST_IDENTITY_NO"));
		info.setStDetailId(rs.getString("ST_DETAIL_ID"));
		info.setNmRemoved(rs.getBigDecimal("NM_REMOVED"));
		info.setStOrganCode(rs.getOriginalString("ST_ORGAN_CODE"));
		info.setStOrganName(rs.getOriginalString("ST_ORGAN_NAME"));
		info.setNmOrganNodeId(rs.getBigDecimal("NM_ORGAN_NODE_ID"));
		info.setStShow(rs.getString("ST_SHOW"));
		info.setNmDataSource(rs.getBigDecimal("NM_DATA_SOURCE"));
		info.setStBusinessNo(rs.getOriginalString("ST_BUSINESS_NO"));
		info.setStHallInfo(rs.getOriginalString("ST_HALL_INFO"));
		info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
		info.setStUnit(rs.getOriginalString("ST_UNIT"));
		info.setStUnified(rs.getOriginalString("ST_UNIFIED"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
	}

	

	

	
	
	
	
	
}

	