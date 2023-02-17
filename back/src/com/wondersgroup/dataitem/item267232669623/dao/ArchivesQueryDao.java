package com.wondersgroup.dataitem.item267232669623.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.wondersgroup.dataitem.item267232669623.bean.ArchivesApplyInfo;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.config.Config;

@Repository
public class ArchivesQueryDao {

	public void add(ArchivesApplyInfo info) {
		String sql = "INSERT INTO ARCHIVES_APPLY_INFO (ID,ST_APPLY_ID,ST_APPLY_NO,ST_USER_NAME,ST_IDENTITY_NO,ST_ARCHIVES_NO,DT_CREAT,DT_UPDATE,NM_STATUS,ST_EXT1,ST_EXT2,ST_EXT3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
				info.getStId(),
				info.getStApplyId(),
				info.getStApplyNo(),
				info.getStUserName(),
				info.getStIdentNo(),
				info.getStArchivesNo(),
				info.getDtCreat(),
				info.getDtUpdate(),
				info.getNmStatus(),
				info.getStExt1(),
				info.getStExt2(),
				info.getStExt3(),
		};
		SQL.execute(sql,obj);
	}

	public List<ArchivesApplyInfo> queryArchivesInfoByIdentNo(String identNo, String code, String flag) {
		RecordSet rs;
		
		String sql = " SELECT * FROM ARCHIVES_APPLY_INFO WHERE ST_IDENTITY_NO = ?" +
				" AND ST_ARCHIVES_NO = ? ";
		if(StringUtils.isEmpty(flag)){
			sql += " AND NM_STATUS = 0 ";
		} else {
			String sqlType = Config.get("wfc.service.jdbc.driver");
			if(sqlType.indexOf("sqlserver") != -1){
				sql += " AND DateDiff(dd,DT_CREAT,getdate())=0 ";
			} else if(sqlType.indexOf("mysql") != -1){
				sql += " AND to_days(DT_CREAT) = to_days(now()) ";
			}
		}
		sql += " ORDER BY DT_CREAT DESC ";
		rs = SQL.execute(sql, new Object[]{identNo, code});
		List<ArchivesApplyInfo> list = new ArrayList<ArchivesApplyInfo>();
		while (rs.next()) {
			ArchivesApplyInfo info = new ArchivesApplyInfo();
			info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
			info.setStApplyNo(rs.getOriginalString("ST_APPLY_NO"));
			info.setStExt2(rs.getOriginalString("ST_EXT2"));
			list.add(info);
		}
		return list;
	}
	
	/**
	 * 审批未通过，或者通过3天后将状态更新为无效
	 * @param slid
	 * @param slbh
	 */
	public void updateArchivesInfo(String slid, String slbh) {
		String sql = "UPDATE ARCHIVES_APPLY_INFO SET NM_STATUS = 1 WHERE ST_APPLY_ID = ? ";
		SQL.execute(sql,new Object[]{slid});
	}
	
	/**
	 * 审批通过打印后将状态更新为已打印，ST_EXT1字段标记
	 * @param slid
	 * @param slbh
	 */
	public void updateArchivesPrintStatus(String slid, String slbh) {
		String sql = "UPDATE ARCHIVES_APPLY_INFO SET ST_EXT1 = 1 WHERE ST_APPLY_ID = ? ";
		SQL.execute(sql,new Object[]{slid});
	}
	
	/**
	 * 查询3天前所有有效状态的申请记录
	 * @param queryStartDate
	 * @param queryEndDate
	 * @return
	 */
	public List<ArchivesApplyInfo> queryAllArchivesInfo(Date queryStartDate,
			Date queryEndDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String start = sdf.format(queryStartDate);
		String end = sdf.format(queryEndDate);
		RecordSet rs;
		
		String sql = " SELECT * FROM ARCHIVES_APPLY_INFO WHERE DT_CREAT <= ? " +
				" AND NM_STATUS = 0 AND ST_APPLY_ID != ''";
		rs = SQL.execute(sql, new Object[]{end});
		List<ArchivesApplyInfo> list = new ArrayList<ArchivesApplyInfo>();
		while (rs.next()) {
			ArchivesApplyInfo info = new ArchivesApplyInfo();
			info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
			info.setStApplyNo(rs.getOriginalString("ST_APPLY_NO"));
			list.add(info);
		}
		return list;
	}
	
	/**
	 * 查询结果返回没有审批状态是，更新异常返回次数，EXT2用作异常次数
	 * @param slid
	 * @param slbh
	 * @param i
	 */
	public void updateArchivesInfoExcTimes(String slid, String slbh, int i) {
		Integer times = Integer.valueOf(i);
		String sql = "UPDATE ARCHIVES_APPLY_INFO SET ST_EXT2 = ? WHERE ST_APPLY_ID = ? ";
		SQL.execute(sql,new Object[]{times, slid});
	}
	
}
