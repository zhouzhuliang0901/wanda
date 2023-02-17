package com.wondersgroup.selfapi.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import wfc.service.config.Config;
import wfc.service.database.DB;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.AppApplyInfo;
import com.wondersgroup.selfapi.bean.ApplyDetailInfo;
import com.wondersgroup.selfapi.bean.StuffInfo;

/**
 * 类描述：办件信息查询
 */

@Repository
public class EventQueryDao {
	
	private Connection con;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 方法描述：根据办件id返回办件信息
	 * @param stApplyId
	 * @return AppApplyInfo
	 */
	public AppApplyInfo getWorkApplyInfoById(String stApplyId) {
		String organ = getOrgan();
		String organ1 = getOrgan1();
		String strSQL = "SELECT"
				+ organ
				+ " ,d.ST_ITEM_NAME,a.ST_APPLY_ID,a.ST_APPLY_NO,a.ST_UNIT,a.ST_USER_NAME,a.ST_MOBILE,a.DT_START_TIME, "
				+ "a.ST_STATUS,a.DT_FINISH,a.ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_INFO a "
				+ "LEFT JOIN WINDOW_ITEM_INFO d ON a.ST_ITEM_ID = d.ST_ITEM_ID "
				+ "LEFT JOIN CS_ORGAN_NODE c ON c.ID = a.NM_ORGAN_NODE_ID "
				+ "WHERE a.ST_APPLY_ID = ? "
				+ "UNION all "
				+ "SELECT "
				+ organ1
				+ " ,ST_ITEM_NAME,ST_APPLY_ID,ST_BUSINESS_NO ST_APPLY_NO ,ST_UNIT,ST_USER_NAME,ST_MOBILE, "
				+ "DT_START_TIME,ST_STATUS,DT_FINISH,ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_QUERY "
				+ "WHERE ST_APPLY_ID = ? ORDER BY DT_START_TIME ";
		RecordSet rs = SQL.execute(strSQL, new Object[] {stApplyId, stApplyId});
		AppApplyInfo appInfo = null;
		while (rs.next()) {
			appInfo = new AppApplyInfo();
			appInfo.setStApplyNo(rs.getOriginalString("ST_APPLY_NO"));
			appInfo.setMobile(rs.getOriginalString("ST_MOBILE"));
			appInfo.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			appInfo.setStUnit(rs.getOriginalString("ST_UNIT"));
			String stName = "";
			stName = rs.getOriginalString("ST_USER_NAME");
			if (StringUtils.isBlank(stName)) {
				stName = rs.getOriginalString("ST_DEAL_USER_NAME");
				appInfo.setStName(stName);
			} else {
				appInfo.setStName(stName);
			}
			appInfo.setStFinalState(rs.getOriginalString("ST_STATUS"));
			Date apply = rs.getTimestamp("DT_START_TIME");
			if (apply != null) {
				appInfo.setStApply(apply.getTime() + "");
				appInfo.setStApplyStr(sdfDate.format(apply));
			}
			Date finish = rs.getTimestamp("DT_FINISH");
			if (finish != null) {
				appInfo.setStFinish(finish.getTime() + "");
				appInfo.setStFinishStr(sdfDate.format(finish));
			}
			appInfo.setStApplyId(stApplyId);
			appInfo.setStOrganName(rs.getOriginalString("DESCRIPTION"));
		}
		return appInfo;
	}

	
	
	/**
	 * 方法描述：根据身份证获取办件信息
	 * @param stIdCard
	 * @return list
	 */
	public List<AppApplyInfo> getApplyInfoByIdCard(String stIdCard) {
		String organ = getOrgan();
		String organ1 = getOrgan1();
		if (stIdCard == null) {
			return null;
		}
		String strSQL = "SELECT"
				+ organ
				+ " ,ST_ITEM_NAME,ST_APPLY_ID, ST_APPLY_NO, ST_UNIT,ST_USER_NAME,ST_MOBILE ,  "
				+ "DT_START_TIME, ST_STATUS, DT_FINISH ,a.ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_INFO a  "
				+ "LEFT JOIN WINDOW_ITEM_INFO i ON a.ST_ITEM_ID = i.ST_ITEM_ID "
				+ "LEFT JOIN CS_ORGAN_NODE c ON c.ID = a.NM_ORGAN_NODE_ID "
				+ "WHERE a.ST_IDENTITY_NO = ? "
				+ "UNION all "
				+ "SELECT "
				+ organ1
				+ " ,ST_ITEM_NAME,ST_APPLY_ID,ST_BUSINESS_NO ST_APPLY_NO ,ST_UNIT,ST_USER_NAME,ST_MOBILE, "
				+ "DT_START_TIME,ST_STATUS,DT_FINISH,ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_QUERY "
				+ "WHERE ST_IDENTITY_NO = ? ORDER BY DT_START_TIME ";
		RecordSet rs = SQL.execute(strSQL, new Object[] { stIdCard, stIdCard });
		List<AppApplyInfo> list = getAppInfoByRecordSet(rs);
		return list;
	}
	
	
	/**
	 * 方法描述：根据身份证获取办件信息(分页查询)
	 * @param pageSize
	 * @param currentPage
	 * @param stIdCard
	 * @return List<AppApplyInfo>
	 */
	public List<AppApplyInfo> getApplyInfoByIdCard(Integer pageSize,
			Integer currentPage, String stIdCard) {
		String organ = getOrgan();
		String organ1 = getOrgan1();
		if (stIdCard == null) {
			return null;
		}
		String strSQL = "SELECT "
				+ organ
				+ " ,ST_ITEM_NAME,ST_APPLY_ID, ST_APPLY_NO, ST_UNIT,ST_USER_NAME,ST_MOBILE,  "
				+ "DT_START_TIME, ST_STATUS, DT_FINISH ,a.ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_INFO a  "
				+ "LEFT JOIN WINDOW_ITEM_INFO i ON a.ST_ITEM_ID = i.ST_ITEM_ID "
				+ "LEFT JOIN CS_ORGAN_NODE c ON c.ID = a.NM_ORGAN_NODE_ID "
				+ "WHERE a.ST_IDENTITY_NO = ? "
				+ "UNION all "
				+ "SELECT "
				+ organ1
				+ " ,ST_ITEM_NAME,ST_APPLY_ID,ST_BUSINESS_NO ST_APPLY_NO ,ST_UNIT,ST_USER_NAME,ST_MOBILE, "
				+ "DT_START_TIME,ST_STATUS,DT_FINISH,ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_QUERY "
				+ "WHERE ST_IDENTITY_NO = ? ORDER BY DT_START_TIME ";
		RecordSet rs = SQL.execute(strSQL, pageSize, currentPage, new Object[] {
				stIdCard, stIdCard });
		
		List<AppApplyInfo> list = getAppInfoByRecordSet(rs);
		return list;
	}
	
	
	
	/**
	 * 方法描述：根据办件编号查询企业名称或个人姓名，返回之中某一个字符为缺失汉字
	 * @param stApplyNo
	 * @return String
	 */
	public String getNameByStApplyNo(String stApplyNo) {
		String name = "";
		String strSQL = "SELECT ST_USER_NAME,ST_UNIT FROM WORK_APPLY_INFO WHERE ST_APPLY_NO = ?";
		RecordSet rs = SQL.execute(strSQL, new Object[] { stApplyNo });
		while (rs.next()) {
			String stUserName = rs.getOriginalString("ST_USER_NAME");
			String stUnit = rs.getOriginalString("ST_UNIT");
			if (StringUtils.isBlank(stUnit)) {
				name = stUserName;
			} else {
				name = stUnit;
			}
		}
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return name;
	}

	
	
	/**
	 * 根据身份证获取办件信息（数据的存储 ）
	 * 
	 * @param rs
	 * @return list
	 */
	public List<AppApplyInfo> getAppInfoByRecordSet(RecordSet rs) {
		List<AppApplyInfo> list = new ArrayList<AppApplyInfo>();
		while (rs.next()) {
			AppApplyInfo appInfo = new AppApplyInfo();
			String stItemName = rs.getOriginalString("ST_ITEM_NAME");
			Date apply = rs.getTimestamp("DT_START_TIME");
			String stApply = "";
			if (apply != null) {
				stApply = apply.getTime() + "";
				appInfo.setStApplyStr(sdfDate.format(apply));
				// Log.debug("办件申请时间的long型："+stApply);
				// stApply = sdf.format(apply);
			}
			Date finish = rs.getTimestamp("DT_FINISH");
			String stFinish = "";
			if (finish != null) {
				stFinish = finish.getTime() + "";
				appInfo.setStFinishStr(sdfDate.format(finish));
				// Log.debug("办件办结时间的long型："+stFinish);
				// stFinish = sdf.format(finish);
			}
			// 部门名称
			appInfo.setStOrganName(rs.getOriginalString("DESCRIPTION"));
			appInfo.setStUnit(rs.getOriginalString("ST_UNIT"));
			String stName = "";
			stName = rs.getOriginalString("ST_USER_NAME");
			if (StringUtils.isBlank(stName)) {
				stName = rs.getOriginalString("ST_DEAL_USER_NAME");
				appInfo.setStName(stName);
			} else {
				appInfo.setStName(stName);
			}
			appInfo.setMobile(rs.getOriginalString("ST_MOBILE"));
			appInfo.setStApplyNo(rs.getOriginalString("ST_APPLY_NO"));
			appInfo.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
			appInfo.setStItemName(stItemName);
			appInfo.setStFinalState(rs.getOriginalString("ST_STATUS"));
			appInfo.setStApply(stApply);
			appInfo.setStFinish(stFinish);
			list.add(appInfo);
		}
		return list;
	}
	
	


	/**
	 * 方法描述： 通过申请人姓名or申请单位名称and办证编号查询办理进度(网站，自助终端，手机APP)
	 * @param name
	 * @param stApplyNo
	 * @return appInfo
	 */
	public AppApplyInfo getWorkApplyInfoByNameAndId(String name,
			String stApplyNo) {
		String organ = getOrgan();
		String organ1 = getOrgan1();
		String strSQL = "SELECT "
				+ organ
				+ " ,d.ST_ITEM_NAME,a.ST_APPLY_ID,a.ST_APPLY_NO,a.ST_UNIT, a.ST_USER_NAME, a.ST_MOBILE, a.DT_START_TIME, a.ST_STATUS, a.DT_FINISH ,a.ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_INFO a "
				+ "LEFT JOIN WINDOW_ITEM_INFO d ON a.ST_ITEM_ID = d.ST_ITEM_ID "
				+ "LEFT JOIN CS_ORGAN_NODE c ON c.ID = a.NM_ORGAN_NODE_ID "
				+ "WHERE a.ST_APPLY_NO = ? AND ( a.ST_USER_NAME = ? or a.ST_UNIT = ? ) "
				+ "UNION  "
				+ "SELECT "
				+ organ1
				+ " ,ST_ITEM_NAME,ST_APPLY_ID,ST_BUSINESS_NO ST_APPLY_NO ,ST_UNIT,ST_USER_NAME,ST_MOBILE, "
				+ "DT_START_TIME,ST_STATUS,DT_FINISH,ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_QUERY "
				+ "where ST_BUSINESS_NO = ?  AND ( ST_USER_NAME = ? or ST_UNIT = ? )  ORDER BY DT_START_TIME ";
		RecordSet rs = SQL.execute(strSQL, new Object[] { stApplyNo, name,
				name, stApplyNo, name, name });
		List<AppApplyInfo> list = new ArrayList<AppApplyInfo>();
		while (rs.next()) {
			AppApplyInfo appInfo = new AppApplyInfo();
			String stItemName = rs.getOriginalString("ST_ITEM_NAME");
			Log.debug(rs.getOriginalString("ST_ITEM_NAME"));
			Date apply = rs.getTimestamp("DT_START_TIME");
			String stApply = "";
			if (apply != null) {
				stApply = apply.getTime() + "";
				appInfo.setStApplyStr(sdfDate.format(apply));
			}
			Date finish = rs.getTimestamp("DT_FINISH");
			String stFinish = "";
			if (finish != null) {
				stFinish = finish.getTime() + "";
				appInfo.setStFinishStr(sdfDate.format(finish));
				// stFinish = sdf.format(finish);
			}
			appInfo.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
			appInfo.setStApplyNo(stApplyNo);
			appInfo.setStItemName(stItemName);
			appInfo.setStFinalState(rs.getOriginalString("ST_STATUS"));
			appInfo.setStApply(stApply);
			appInfo.setStFinish(stFinish);
			appInfo.setStUnit(rs.getOriginalString("ST_UNIT"));
			// appInfo.setStName(rs.getOriginalString("ST_USER_NAME"));
			String stName = "";
			stName = rs.getOriginalString("ST_USER_NAME");
			if (StringUtils.isBlank(stName)) {
				stName = rs.getOriginalString("ST_DEAL_USER_NAME");
				appInfo.setStName(stName);
			} else {
				appInfo.setStName(stName);
			}
			appInfo.setMobile(rs.getOriginalString("ST_MOBILE"));
			appInfo.setStOrganName(rs.getOriginalString("DESCRIPTION"));
			list.add(appInfo);
		}
		return list.size() > 0 ? list.get(0) : null;
	}



	/**
	 * 方法描述：扫描二维码获得办件信息（自助终端，手机APP）
	 * @param stApplyNo
	 * @return AppApplyInfo
	 */
	public AppApplyInfo getWorkApplyInfoByStApplyNo(String stApplyNo) {
		String organ = getOrgan();
		String organ1 = getOrgan1();
		if (StringUtils.isBlank(stApplyNo)) {
			return null;
		}
		String strSQL = "SELECT"
				+ organ
				+ " ,d.ST_ITEM_NAME,A.ST_APPLY_ID,A.ST_APPLY_NO, A.ST_UNIT, A.ST_USER_NAME, A.ST_MOBILE, A.DT_START_TIME, A.ST_STATUS, A.DT_FINISH,A.ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_INFO A "
				+ "LEFT JOIN WINDOW_ITEM_INFO d ON a.ST_ITEM_ID = d.ST_ITEM_ID "
				+ "LEFT JOIN CS_ORGAN_NODE c ON c.ID = A.NM_ORGAN_NODE_ID "
				+ "WHERE A.ST_APPLY_NO = ? "
				+ "UNION ALL "
				+ "SELECT "
				+ organ1
				+ " ,ST_ITEM_NAME,ST_APPLY_ID,ST_BUSINESS_NO ST_APPLY_NO ,ST_UNIT,ST_USER_NAME,ST_MOBILE, "
				+ "DT_START_TIME,ST_STATUS,DT_FINISH,ST_DEAL_USER_NAME "
				+ "FROM WORK_APPLY_QUERY  "
				+ "where ST_BUSINESS_NO = ? ORDER BY DT_START_TIME ";
		RecordSet rs = SQL.execute(strSQL, new Object[] {stApplyNo, stApplyNo});
		AppApplyInfo appInfo = null;
		while (rs.next()) {
			appInfo = new AppApplyInfo();
			Date apply = rs.getTimestamp("DT_START_TIME");
			String stApply = "";
			if (apply != null) {
				stApply = apply.getTime() + "";
				appInfo.setStApplyStr(sdfDate.format(apply));
			}
			Date finish = rs.getTimestamp("DT_FINISH");
			String stFinish = "";
			if (finish != null) {
				stFinish = finish.getTime() + "";
				appInfo.setStFinishStr(sdfDate.format(finish));
			}
			appInfo.setMobile(rs.getOriginalString("ST_MOBILE"));
			appInfo.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			appInfo.setStUnit(rs.getOriginalString("ST_UNIT"));
			appInfo.setStApplyNo(stApplyNo);
			String stName = "";
			stName = rs.getOriginalString("ST_USER_NAME");
			if (StringUtils.isBlank(stName)) {
				stName = rs.getOriginalString("ST_DEAL_USER_NAME");
				appInfo.setStName(stName);
			} else {
				appInfo.setStName(stName);
			}
			appInfo.setStFinalState(rs.getOriginalString("ST_STATUS"));
			appInfo.setStApply(stApply);
			appInfo.setStFinish(stFinish);
			appInfo.setStOrganName(rs.getOriginalString("DESCRIPTION"));
		}
		return appInfo;
	}



	/**
	 * 方法描述：通过申请人姓名or申请单位名称and办证编号查询办理进度(网站，自助终端，手机APP)
	 * (海口定制化)
	 * @param name
	 * @param stApplyNo
	 * @return AppApplyInfo
	 */
	public AppApplyInfo getHKWorkApplyInfoByNameAndId(String name,
			String stApplyNo) {
		String condition = "";
		if (StringUtils.isBlank(stApplyNo) && StringUtils.isBlank(name)) {
			return null;
		}
		if (StringUtils.isNotBlank(stApplyNo) && StringUtils.isNotBlank(name)) {
			condition = " WHERE x.CASEID = '" + stApplyNo+"'"
					+ " AND ( x.SQDWHSQRMC = '" + name + "' OR x.PROJECTNAME = '"
					+ name + "') ";
		} else {
			if (StringUtils.isNotBlank(stApplyNo)) {
				condition = " WHERE x.CASEID = '" + stApplyNo+"' ";
			} else {
				condition = " WHERE x.SQDWHSQRMC = '" + name
						+ "' OR x.PROJECTNAME = '" + name+"' ";
			}
		}
		con = getConnection();
		String strSQL = " SELECT top 1 p.ITEMNAME,x.PROJECTNAME,x.REVDATE,x.SQDWHSQRMC,x.CASEID,x.STATUSID,o.ORGNAME "
				+ " ,x1.STARTDATE,x1.SQDWJBRXM,x1.STATUSID,x2.SPSJ,x2.SPJG,x2.SPHJMC,x3.BJSJ,x3.BJJGMS "
				+ " FROM v_SHOUJIAN x "
				+ " JOIN V_SERVICEVIEW p  ON x.SERVICEOID = p.SERVICEOID "
				+ " JOIN v_Organ o ON o.OID = x.ORG_ID "
				+ " LEFT JOIN V_shouliview x1 ON x.CASEID = x1.CASEID "
				+ " LEFT JOIN V_SHENPIPROCE x2 ON x1.SHOULIOID = x2.SHOULIOID "
				+ " LEFT JOIN V_BANJIEPAUSE x3 ON  x1.SHOULIOID = x3.SHOULIOID "
				+ condition + " ORDER BY x2.SPSJ desc ";
		RecordSet rs = SQL.execute(con, strSQL);
		List<AppApplyInfo> list = new ArrayList<AppApplyInfo>();
		while (rs.next()) {
			AppApplyInfo appInfo = new AppApplyInfo();
			String stItemName = rs.getOriginalString("ITEMNAME");
			Date apply = rs.getTimestamp("REVDATE");
			String stApply = "";
			if (apply != null) {
				stApply = apply.getTime() + "";
				appInfo.setStApplyStr(sdfDate.format(apply));
			}
			appInfo.setStApplyId("");
			appInfo.setStApplyNo(stApplyNo);
			appInfo.setStItemName(stItemName);
			appInfo.setStApply(stApply);
			appInfo.setStUnit(rs.getOriginalString("PROJECTNAME"));
			// appInfo.setStName(rs.getOriginalString("ST_USER_NAME"));
			String stName = rs.getOriginalString("SQDWHSQRMC");
			appInfo.setStName(stName);
			appInfo.setStOrganName(rs.getOriginalString("ORGNAME"));
			// 获取办件的状态信息
			Date finish = rs.getTimestamp("BJSJ");
			String status = "已办结";
			if (finish == null) {
				finish = rs.getTimestamp("SPSJ");
				status = "已审批";
				if (finish == null) {
					finish = rs.getTimestamp("STARTDATE");
					status = "已受理";
					if (finish == null) {
						finish = rs.getTimestamp("REVDATE");
						status = "已收件";
					}
				}
			}
			String stFinish = "";
			if (finish != null) {
				stFinish = finish.getTime() + "";
				appInfo.setStFinishStr(sdfDate.format(finish));
			}
			appInfo.setStFinish(stFinish);
			appInfo.setStFinalState(status);
			list.add(appInfo);
		}
		
		return list.size() > 0 ? list.get(0) : null;
	}
	

	/**
	 * 方法描述：根据办件编码获取办件的详细信息
	 * @param stApplyNo
	 * @return ApplyDetailInfo
	 */
	public ApplyDetailInfo getApplyDetailInfoByNo(String stApplyNo) {
		String strSQL = "SELECT w1.ST_ITEM_NAME,w1.ST_ITEM_NO,"
				+ "w.ST_STATUS,w.ST_UNIT,w.ST_USER_ID,w.DT_START_TIME,w.NM_SATISFATION,"
				+ "w.ST_WINDOW_NO,w.ST_APPLY_NO,w.ST_USER_NAME,w.ST_APPLY_ID,w.ST_MOBILE,w.ST_DEAL_MOBILE,"
				+ "c.NAME,c1.NAME organName,c1.DESCRIPTION,w2.ST_JOB_NUMBER,w5.ST_AREA_NAME,w6.ST_STUFF_NAME,w6.NM_COPY,w6.NM_ORIGINAL,w6.ST_DESC "
				+ "FROM WORK_APPLY_INFO w join CS_USER c ON w.ST_USER_ID = c.ID "
				+ "JOIN CS_ORGAN_NODE c1 ON w.NM_ORGAN_NODE_ID = c1.ID "
				+ "JOIN WINDOW_ITEM_INFO w1 ON w.ST_ITEM_ID = w1.ST_ITEM_ID "
				+ "JOIN WINDOW_USER_EXT w2 ON w.ST_USER_ID = w2.ST_USER_ID "
				+ "JOIN WINDOW_WINDOW_INFO w3 ON w.ST_WINDOW_NO = w3.ST_WINDOW_NO "
				+ "JOIN WINDOW_AREA_HALL w4 ON w3.ST_HALL_ID = w4.ST_HALL_ID "
				+ "JOIN WINDOW_AREA w5 ON w4.ST_AREA_ID = w5.ST_AREA_ID "
				+ "LEFT JOIN WORK_APPLY_STUFF w6 ON w.ST_APPLY_ID = w6.ST_APPLY_ID "
				+ "where w.ST_APPLY_NO = ? AND c1.REMOVED = 0 ";
		RecordSet rs = SQL.execute(strSQL, new Object[] { stApplyNo });
		ApplyDetailInfo applyDetailInfo = null;
		List<StuffInfo> stStuffNameList = new ArrayList<StuffInfo>();
		while (rs.next()) {
			if (applyDetailInfo == null) {
				applyDetailInfo = new ApplyDetailInfo();
				Date apply = rs.getTimestamp("DT_START_TIME");
				String stApply = "";
				if (apply != null) {
					stApply = apply.getTime() + "";
					applyDetailInfo.setStApply(stApply);
					applyDetailInfo.setStApplyStr(sdfDate.format(apply));
				}
				applyDetailInfo.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
				applyDetailInfo.setStUnit(rs.getOriginalString("ST_UNIT"));
				applyDetailInfo.setStApplyNo(stApplyNo);
				applyDetailInfo.setStFinalState(rs.getOriginalString("ST_STATUS"));
				applyDetailInfo.setStUserId(rs.getOriginalString("ST_USER_ID"));
				applyDetailInfo.setNmSatisfation(rs.getBigDecimal("applyDetailInfo") == null ? new BigDecimal(10) : rs.getBigDecimal("applyDetailInfo"));
				applyDetailInfo.setStWindowNo(rs.getOriginalString("ST_WINDOW_NO"));
				applyDetailInfo.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
				applyDetailInfo.setStUserName(rs.getOriginalString("NAME"));
				applyDetailInfo.setStJobNumber(rs.getOriginalString("ST_JOB_NUMBER"));
				applyDetailInfo.setStPlaceName(rs.getOriginalString("ST_AREA_NAME"));
				applyDetailInfo.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
				String userImageUrl = "/aci/autoterminal/eventquery/getUserImageById.do?userId=" + rs.getOriginalString("ST_USER_ID");
				applyDetailInfo.setUserImageUrl(userImageUrl);
				applyDetailInfo.setStApplyUserName(rs.getOriginalString("ST_USER_NAME"));
				applyDetailInfo.setMobile(rs.getOriginalString("ST_DEAL_MOBILE"));
				applyDetailInfo.setApplyMobile(rs.getOriginalString("ST_MOBILE"));
				applyDetailInfo.setStOrganName(rs.getOriginalString("DESCRIPTION"));
			}
			StuffInfo info = new StuffInfo();
			info.setStStuffName(rs.getOriginalString("ST_STUFF_NAME"));
			info.setCopyNumber(rs.getBigDecimal("NM_COPY") == null ? new BigDecimal(0) : rs.getBigDecimal("NM_COPY"));
			info.setOriginalNumber(rs.getBigDecimal("NM_ORIGINAL") == null ? new BigDecimal(0) : rs.getBigDecimal("NM_ORIGINAL"));
			info.setStDesc(rs.getOriginalString("ST_DESC"));
			stStuffNameList.add(info);
		}
		applyDetailInfo.setStStuffNameList(stStuffNameList);
		return applyDetailInfo;
	}
	

	/**
	 * 根据身份证获取办件信息(海口定制化)
	 * 
	 * @param stIdCard
	 * @return list
	 */
	public List<AppApplyInfo> getHKApplyInfoByIdCard(String stIdCard) {
		if (stIdCard == null) {
			return null;
		}
		List<AppApplyInfo> list = new ArrayList<AppApplyInfo>();
		con = getConnection();
		String strSQL = " SELECT DISTINCT x.CASEID,p.ITEMNAME,x.REVDATE "
				+ " FROM v_SHOUJIAN x "
				+ " JOIN V_SERVICEVIEW p  ON x.SERVICEOID = p.SERVICEOID "
				+ " LEFT JOIN V_shouliview x1 ON x.CASEID = x1.CASEID "
				+ " WHERE x1.cardid = ? ORDER BY x.REVDATE desc ";
		RecordSet rs = SQL.execute(con, strSQL, new Object[] { stIdCard });
		while (rs.next()) {
			AppApplyInfo appInfo = new AppApplyInfo();
			String stItemName = rs.getOriginalString("ITEMNAME");
			String stApplyNo = rs.getOriginalString("CASEID");
			appInfo.setStApplyNo(stApplyNo);
			appInfo.setStItemName(stItemName);
			list.add(appInfo);
		}
		return list;
	}


	/**
	 * 方法描述：创建连接对象 访问海口数据库 sqlserver
	 * @return con
	 */
	public Connection getConnection() {
		String url = Config.get("haikou.string");
		String user = Config.get("haikou.user");
		String password = Config.get("haikou.password");
		String driverName = Config.get("wfc.service.jdbc.driver");
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建数据库连接失败！");
		}
		return con;
	}


	/**
	 * 方法描述：根据办件ID改变满意度评价状态
	 * @param nmSatisfation
	 * @param content
	 * @param stApplyId
	 * @return
	 */
	public String saveNmSatisfation(String nmSatisfation, String content,
			String stApplyId) {
		String result = "0";
		String saveSQL = "UPDATE WORK_APPLY_INFO SET NM_SATISFATION = ? ,ST_SATISFATION_REMARK = ? "
				+ "WHERE ST_APPLY_ID = ? ";
		RecordSet rs = SQL.execute(saveSQL, new Object[] { nmSatisfation,
				content, stApplyId });
		if (rs.TOTAL_RECORD_COUNT > 0) {
			result = "1";
		}
		return result;
	}


	
	
	
	public String getOrgan() {
		String organ = "";
		if (DB.DB_ORACLE.equals(DB.getDatabaseName())) {
			organ = " to_char(c.DESCRIPTION) DESCRIPTION ";
		} else {
			organ = " c.DESCRIPTION ";
		}
		return organ;
	}

	public String getOrgan1() {
		String organ = "";
		if (DB.DB_ORACLE.equals(DB.getDatabaseName())) {
			organ = " to_char(ST_ORGAN_NODE_NAME) DESCRIPTION ";
		} else {
			organ = " ST_ORGAN_NODE_NAME DESCRIPTION ";
		}
		return organ;
	}























}



