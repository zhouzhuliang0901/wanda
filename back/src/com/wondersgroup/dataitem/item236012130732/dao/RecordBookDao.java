package com.wondersgroup.dataitem.item236012130732.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import reindeer.base.utils.AciHelper;

import com.wondersgroup.dataitem.item236012130732.bean.RecordBookInfo;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.config.Config;

@Repository
public class RecordBookDao {

	public void save(RecordBookInfo book) {
		String sql = "INSERT INTO RECORD_BOOK_INFO(ST_BOOK_ID,ST_BOOK_NO,ST_NAME," +
				"ST_IDENTITY_NO,ST_SEX,ST_CARD_NO,DT_CREATE,ST_MACHINE_MAC) " +
				"VALUES(?,?,?,?,?,?,?,?)";
		Object[] obj = {
				book.getId(),
				book.getRecordBookNo(),
				book.getName(),
				book.getIdCard(),
				book.getSex(),
				book.getCardNo(),
				book.getDtCreate(),
				book.getStMachineId()
		};
		SQL.execute(sql,obj);
	}

	public List<RecordBookInfo> RecordBookInfo(String startTime, String endTime, String machineId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isEmpty(startTime)){
			Date startDate = new Date();
			startTime = sdf.format(startDate);
		}
		Date endDate = null;
		if(StringUtils.isEmpty(endTime)){
			endDate = new Date();
		} else {
			try {
				endDate = sdf.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		endDate = DateUtils.addDays(endDate, 1);
//		endDate = DateUtils.addMinutes(endDate, -10);
		
//		String mac = getAllDeviceByMac(machineId);
		
//		String hisTable = AciHelper.getTable("SELM_QUERY_HIS");
//		String attachTable = AciHelper.getTable("SELM_ATTACH");
//		String sql =  "SELECT sa.CL_CONTENT FROM "+hisTable+" sqh "
//		+" LEFT JOIN "+attachTable+" sa ON sa.ST_LINK_ID = sqh.ST_QUERY_HIS_ID "
//		+" WHERE sqh.ST_MACHINE_ID = ? "
//		+" AND sqh.DT_CREATE >= ? AND sqh.DT_CREATE <= ? "
//		+" AND sqh.ST_ITEM_NAME = '办理就医记录册的申领、更换、补发' "
//		+" AND sqh.ST_MODULE_NAME = '医保服务' "
//		+" AND sqh.ST_SUBMIT_DATA_ID IS NOT NULL "
//		+" AND sa.CL_CONTENT IS NOT NULL "
//		+" ORDER BY sqh.DT_CREATE DESC";
		String sql = " select ST_BOOK_NO,ST_NAME,ST_IDENTITY_NO,ST_SEX,ST_CARD_NO from record_book_info" +
					 " where DT_CREATE >= ? and DT_CREATE < ? and ST_MACHINE_MAC = ? order by DT_CREATE desc";
//		RecordSet rs = SQL.execute(sql, new Object[]{machineId, startTime, sdf.format(endDate),});
		RecordSet rs = SQL.execute(sql, new Object[]{startTime, sdf.format(endDate), machineId});
		List<RecordBookInfo> list = new ArrayList<RecordBookInfo>();
		while(rs.next()){
//			String content = rs.getOriginalString("CL_CONTENT");
//			JSONObject json = JSONObject.fromObject(content);
//			JSONObject data = json.optJSONObject("data");
			RecordBookInfo book = new RecordBookInfo();
			book.setRecordBookNo(rs.getOriginalString("ST_BOOK_NO"));
			book.setName(rs.getOriginalString("ST_NAME"));
			book.setIdCard(rs.getOriginalString("ST_IDENTITY_NO"));
			book.setSex(rs.getOriginalString("ST_SEX"));
			book.setCardNo(rs.getOriginalString("ST_CARD_NO"));
//			book.setRecordBookNo(data.optString("newBookBh"));// 记录册号
//			book.setName(data.optString("userName"));//姓名
//			book.setIdCard(data.optString("userAge"));// 身份证号
//			book.setSex(data.optString("userXb"));// 性别
//			book.setCardNo(data.optString("newBookKh"));// 卡号
			list.add(book);
		}
		return list;
	}
	
	public List<String> getAllDeviceByMac(String machineId){
		String sql = " select ST_DEVICE_MAC from infopub_device_info "
					+" where ST_ADDRESS_ID = ( "
					+" select ST_ADDRESS_ID from infopub_device_info where ST_DEVICE_MAC = ?)";
		RecordSet rs = SQL.execute(sql, new Object[]{machineId});
		List<String> list = new ArrayList<String>();
		while(rs.next()){
			String mac = rs.getOriginalString("ST_DEVICE_MAC");
			list.add(mac);
		}
		return list;
	}
	
	public List<RecordBookInfo> getAllRecordBookInfo() {
		String sql = "SELECT * FROM RECORD_BOOK_INFO ORDER BY DT_CREATE";
		RecordSet rs = SQL.execute(sql);
		List<RecordBookInfo> list = new ArrayList<RecordBookInfo>();
		while(rs.next()){
			RecordBookInfo book = new RecordBookInfo();
			book.setRecordBookNo(rs.getOriginalString("ST_BOOK_NO"));
			book.setName(rs.getOriginalString("ST_NAME"));
			book.setIdCard(rs.getOriginalString("ST_IDENTITY_NO"));
			book.setSex(rs.getOriginalString("ST_SEX"));
			book.setCardNo(rs.getOriginalString("ST_CARD_NO"));
			book.setDtCreate(rs.getTimestamp("DT_CREATE"));
			list.add(book);
		}
		return list;
	}

	/**
	 * 查询一段时间内某人打印记录册次数
	 * @param identNo
	 * @param range
	 */
	public int getHistoryBookInfoByIdCard(String identNo, String range) {
		String table = AciHelper.getTable("SELM_QUERY_HIS");
		String sql = " SELECT ST_MODULE_NAME,ST_IDENTITY_NO,ST_NAME,ST_ITEM_NAME FROM "+table+" "
					+" WHERE ST_IDENTITY_NO = ? "
					+" AND ST_MODULE_NAME = '医保服务' "
					+" AND ST_ITEM_NAME = '办理就医记录册的申领、更换、补发' ";
					if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(Config.get("reindeer.service.jdbc.driver"))){
						if("day".equals(range)){
							sql += " AND DATEDIFF(dd, DT_CREATE, GETDATE()) = 0";
						} else if("week".equals(range)){
							sql += " AND DATEDIFF(dd, DT_CREATE, GETDATE()) <= 7";
						} else if("month".equals(range)){
							sql += " AND DATEDIFF(mm, DT_CREATE, GETDATE()) = 0";
						}
					} else if("com.mysql.jdbc.Driver".equals(Config.get("reindeer.service.jdbc.driver"))){
						if("day".equals(range)){
							sql += " AND TO_DAYS(DT_CREATE) = TO_DAYS(now())";
						} else if("week".equals(range)){
							sql += " AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(DT_CREATE)";
						} else if("month".equals(range)){
							sql += " AND DATE_FORMAT(DT_CREATE, '%Y%m') = DATE_FORMAT(CURDATE(), '%Y%m')";
						}
					}
		RecordSet rs = SQL.execute(sql, new Object[]{identNo});
		int count = rs.TOTAL_RECORD_COUNT;
		return count;
	}
}
