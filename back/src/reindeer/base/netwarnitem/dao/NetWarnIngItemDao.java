package reindeer.base.netwarnitem.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import reindeer.base.netwarnitem.bean.CompanyInfo;
import reindeer.base.netwarnitem.bean.SendmessageInfo;
import reindeer.base.netwarnitem.bean.UserMobileInfo;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class NetWarnIngItemDao {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/**
	 * 查询企业关键字的集合
	 * 
	 * @return
	 */
	public List<CompanyInfo> getCompanyInfoList() {
		List<CompanyInfo> companyList = new ArrayList<CompanyInfo>();
		String sql = " SELECT * FROM  NET_COMPANY_INFO ";
		RecordSet rs = SQL.execute(sql);
		while (rs.next()) {
			CompanyInfo info = new CompanyInfo();
			info.setStCompanyName(rs.getOriginalString("ST_COMPANY_NAME"));
			info.setStCompanyKeyName(rs
					.getOriginalString("ST_COMPANY_KEY_WORD"));
			info.setStAreaCode(rs.getOriginalString("ST_AREA_CODE"));
			info.setStAreaName(rs.getOriginalString("ST_AREA_NAME"));
			info.setStCompanyCreditCode(rs
					.getOriginalString("ST_COMPANY_CREDIT_NAME"));
			companyList.add(info);
		}
		return companyList;
	}

	/**
	 * 查询出片区对应人员集合(方法1)
	 * 
	 * @param areaCodes
	 * @return
	 */
	public List<UserMobileInfo> getUserMobileList(String areaCodesStr) {
		List<UserMobileInfo> userMobileList = new ArrayList<UserMobileInfo>();
		String sql = " SELECT * FROM NET_USERMOBILE_INFO "
				+ " WHERE (ST_AREA_CODE IN (?) OR NM_USER_LEVEAL = 2 )";
		RecordSet rs = SQL.execute(sql, new Object[] { areaCodesStr });
		while (rs.next()) {
			UserMobileInfo info = new UserMobileInfo();
			info.setStUsermobileId(rs.getOriginalString("ST_USERMOBILE_ID"));
			info.setStUserName(rs.getOriginalString("ST_USER_NAME"));
			info.setStMobile(rs.getOriginalString("ST_MOBILE"));
			info.setStAreaCode(rs.getOriginalString("ST_AREA_CODE"));
			info.setStAreaName(rs.getOriginalString("ST_AREA_NAME"));
			info.setNmUserLeveal(rs.getBigDecimal("NM_SUER_LEVEAL"));
			userMobileList.add(info);
		}
		return userMobileList;
	}

	/**
	 * 查询出片区对应人员集合(方法2)
	 * 
	 * @param areaCodes
	 * @return
	 */
	public List<UserMobileInfo> getUserMobileList(List<String> areaCodes) {
		List<UserMobileInfo> userMobileList = new ArrayList<UserMobileInfo>();
		Conditions conds = Conditions.newOrConditions();
		conds.add(new Condition("ST_AREA_CODE", Condition.OT_IN, areaCodes));
		if (!"100".equals(areaCodes.get(0))) {
			conds.add(new Condition("NM_USER_LEVEAL", Condition.OT_EQUAL, 2));
		}
		RecordSet rs = SQL.query("NET_USERMOBILE_INFO", "*", conds, "");
		while (rs.next()) {
			UserMobileInfo info = new UserMobileInfo();
			String stUserMobileId = rs.getOriginalString("ST_USERMOBILE_ID");
			info.setStUsermobileId(stUserMobileId);
			List<String> warnItemList = getWarnItemList(stUserMobileId);
			info.setWarnItemList(warnItemList);
			info.setStUserName(rs.getOriginalString("ST_USER_NAME"));
			info.setStMobile(rs.getOriginalString("ST_MOBILE"));
			info.setStAreaCode(rs.getOriginalString("ST_AREA_CODE"));
			info.setStAreaName(rs.getOriginalString("ST_AREA_NAME"));
			info.setNmUserLeveal(rs.getBigDecimal("NM_USER_LEVEAL"));
			userMobileList.add(info);
		}
		return userMobileList;
	}

	/**
	 * 查询出人员手机维护信息表所对应需要管理的预警事项
	 * 
	 * @param areaCodes
	 * @return
	 */
	public List<String> getWarnItemList(String stUserMobileId) {
		List<String> list = new ArrayList<String>();
		String sql = " SELECT n2.ST_WARNITEM_NO FROM NET_USERMOBILE_INFO n JOIN NET_USERMOBILE_WARNITEM n1 "
				+ " ON n.ST_USERMOBILE_ID = n1.ST_USERMOBILE_ID "
				+ " JOIN NET_WARN_ITEM n2 ON n1.ST_WARNITEM_ID = n2.ST_WARNITEM_ID "
				+ " WHERE n.ST_USERMOBILE_ID = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { stUserMobileId });
		while (rs.next()) {
			String stWarnItemNo = rs.getOriginalString("ST_WARNITEM_NO");
			list.add(stWarnItemNo);
		}
		return list;
	}

	/**
	 * 判断事项是否属于预警事项
	 * 
	 * @param areaCodes
	 * @return
	 */
	public boolean isWarningItem(String itemNo) {
		boolean result = false;
		String sql = " SELECT ST_WARNITEM_NO FROM NET_WARN_ITEM WHERE ST_WARNITEM_NO = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { itemNo });
		if (rs.TOTAL_RECORD_COUNT > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 更新预约信息状态为预警事项预约
	 * 
	 * @param areaCodes
	 * @return
	 */
	public String updateWarnItemStatus(String reservationNo) {
		String result = "N";
		String sql = " UPDATE NET_RESERVATION_INFO SET ST_EXT2 = '1' WHERE ST_RESERVATION_NO = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { reservationNo });
		if (rs.TOTAL_RECORD_COUNT > 0) {
			result = "Y";
		}
		return result;
	}

	/**
	 * 更新预约信息状态为重点企业非预警事项预约
	 * 
	 * @param areaCodes
	 * @return
	 */
	public String updateItemStatusForWarnCompany(String reservationNo) {
		String result = "N";
		String sql = " UPDATE NET_RESERVATION_INFO SET ST_EXT2 = '2' WHERE ST_RESERVATION_NO = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { reservationNo });
		if (rs.TOTAL_RECORD_COUNT > 0) {
			result = "Y";
		}
		return result;
	}

	/**
	 * 对发送的预警短信进行记录
	 * 
	 * @param info
	 */
	public String add(SendmessageInfo info) {
		String result = "N";
		String sql = "insert into NET_SENDMESSAGE_INFO(ST_SENDMESSAGE_ID, ST_COMPANY_NAME, ST_COMPANY_CREDIT_CODE, ST_AREA_CODE, ST_AREA_NAME, ST_MOBILE, ST_USER_NAME, NM_USER_LEVEAL,ST_MESSAGE_INFO, DT_SEND_TIME_DATE, ST_RESERVATION_NO, ST_ITEM_NAME) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		Object[] obj = { info.getStSendMessageId(), info.getStCompanyName(),
				info.getStCompanyCreditCode(), info.getStAreaCode(),
				info.getStAreaName(), info.getStMobile(), info.getStUserName(),
				info.getNmUserLeveal(), info.getStMessageInfo(),
				info.getDtSendTimeDate(), info.getStReservationNo(),
				info.getStItemName() };
		RecordSet rs = SQL.execute(sql, obj);
		if (rs.TOTAL_RECORD_COUNT > 0) {
			result = "Y";
		}
		return result;
	}
}
