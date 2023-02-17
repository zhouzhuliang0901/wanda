package com.wondersgroup.sms.user.dao;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import org.springframework.stereotype.Repository;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 */
@Repository
public class SmsUserDao {

	private Connection con = null;

	public SmsUserDao() {
	}

	public SmsUserDao(Connection con) {
		this.con = con;
	}

    public List<SmsUser> queryByUsername(String stLoginName) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, stLoginName));
		List<SmsUser> list = query(conds, null);
		return list;
    }

    public void add(SmsUser info) {
		String sql = "insert into SMS_USER(ST_USER_ID, ST_LOGIN_NAME, ST_USER_CODE, ST_USER_NAME, ST_PASSWORD, ST_PINYIN, ST_ORGAN_ID, ST_ORG_NAME , ST_IDCARD , ST_SEX ,ST_EMAIL, ST_MOBILE, NM_RECEIVE_EMAIL, ST_THEME_NAME, NM_LOCKED, ST_SALT, ST_EXT_ID, DT_CREATE, DT_UPDATE, ST_EXT1, ST_EXT2, ST_AREA_ID,ST_ORGAN_ID_TWO) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { info.getStUserId(), info.getStLoginName(),
				info.getStUserCode(), info.getStUserName(),
				info.getStPassword(), info.getStPinyin(), info.getStOrganId(),
				info.getStOrgName(),info.getStIdcard(), info.getStSex(),
				info.getStEmail(), info.getStMobile(),
				info.getNmReceiveEmail(), info.getStThemeName(),
				info.getNmLocked(), info.getStSalt(), info.getStExtId(),
				info.getDtCreate(), info.getDtUpdate(), info.getStExt1(),
				info.getStExt2(), info.getStAreaId(),info.getStOrganIdTwo() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public void update(SmsUser info) {
		String sql = "update SMS_USER set ST_LOGIN_NAME = ?,ST_ORGAN_ID_TWO = ?, ST_USER_CODE = ?, ST_USER_NAME = ?, ST_PASSWORD = ?, ST_PINYIN = ?, ST_ORGAN_ID = ?, ST_ORG_NAME = ?, ST_IDCARD = ?, ST_SEX = ?,ST_EMAIL = ?, ST_MOBILE = ?, NM_RECEIVE_EMAIL = ?, ST_THEME_NAME = ?, NM_LOCKED = ?, ST_SALT = ?, ST_EXT_ID = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_EXT1 = ?, ST_EXT2 = ?, ST_AREA_ID = ? where ST_USER_ID = ?";
		Object[] obj = { info.getStLoginName(), info.getStOrganIdTwo(),info.getStUserCode(),
				info.getStUserName(), info.getStPassword(), info.getStPinyin(),
				info.getStOrganId(), info.getStOrgName(),
	            info.getStIdcard(),info.getStSex(),info.getStEmail(), info.getStMobile(),
				info.getNmReceiveEmail(), info.getStThemeName(),
				info.getNmLocked(), info.getStSalt(), info.getStExtId(),
				info.getDtCreate(), info.getDtUpdate(), info.getStExt1(),
				info.getStExt2(), info.getStAreaId(),info.getStUserId() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "update SMS_USER set ";
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
		String sql = "delete from SMS_USER";
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

	public void delete(String[] stUserId) {
		Conditions conds = Conditions.newOrConditions();
		for (int i = 0; i < stUserId.length; i++) {
			Conditions subconds = Conditions.newAndConditions();
			subconds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL,
					stUserId[i]));
			conds.add(subconds);
		}
		delete(conds);
	}

	public void delete(String stUserId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
		delete(conds);
	}

	public PaginationArrayList<SmsUser> query(Conditions conds, String suffix,
			int pageSize, int currentPage) {
		RecordSet rs;
//		String content = "	ST_USER_ID, "+
//						"   ST_LOGIN_NAME, "+
//						"	ST_USER_CODE, "+
//						"	ST_USER_NAME, "+
//						"	ST_ORGAN_ID, "+
//						"	ST_EMAIL, "+
//						"	ST_MOBILE, "+
//						"	NM_RECEIVE_EMAIL, "+
//						"	ST_THEME_NAME, "+
//						"	DT_CREATE, "+
//				        "   DT_UPDATE"+
//						"	ST_ORGAN_ID_TWO";
		if (con == null) {
			rs = SQL.query("SMS_USER", "*", conds, suffix, pageSize,
					currentPage);
		} else {
			rs = SQL.query(con, "SMS_USER", "*", conds, suffix, pageSize,
					currentPage);
		}
		PaginationArrayList<SmsUser> pal = new PaginationArrayList<SmsUser>(
				rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SmsUser info = new SmsUser();
			setProperties(info, rs);
			pal.add(info);
		}
		return pal;
	}

	public List<SmsUser> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("SMS_USER", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "SMS_USER", "*", conds, suffix);
		}
		ArrayList<SmsUser> al = new ArrayList<SmsUser>();
		while (rs.next()) {
			SmsUser info = new SmsUser();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

	public static void setProperties(SmsUser info, RecordSet rs) {
		info.setStUserId(rs.getOriginalString("ST_USER_ID"));
		info.setStLoginName(rs.getOriginalString("ST_LOGIN_NAME"));
		info.setStUserCode(rs.getOriginalString("ST_USER_CODE"));
		info.setStUserName(rs.getOriginalString("ST_USER_NAME"));
		info.setStPassword(rs.getOriginalString("ST_PASSWORD"));
		info.setStPinyin(rs.getOriginalString("ST_PINYIN"));
		info.setStOrganId(rs.getOriginalString("ST_ORGAN_ID"));
		info.setStOrgName(rs.getOriginalString("ST_ORG_NAME"));
	    info.setStIdcard(rs.getOriginalString("ST_IDCARD"));
	    info.setStSex(rs.getBigDecimal("ST_SEX"));
		info.setStEmail(rs.getOriginalString("ST_EMAIL"));
		info.setStMobile(rs.getOriginalString("ST_MOBILE"));
		info.setNmReceiveEmail(rs.getBigDecimal("NM_RECEIVE_EMAIL"));
		info.setStThemeName(rs.getOriginalString("ST_THEME_NAME"));
		info.setNmLocked(rs.getBigDecimal("NM_LOCKED"));
		info.setStSalt(rs.getOriginalString("ST_SALT"));
		info.setStExtId(rs.getOriginalString("ST_EXT_ID"));
		info.setDtCreate(rs.getTimestamp("DT_CREATE"));
		info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
		info.setStAreaId(rs.getOriginalString("ST_AREA_ID"));
		info.setStOrganIdTwo(rs.getOriginalString("ST_ORGAN_ID_TWO"));
		info.setStGroupName(rs.getOriginalString("ST_GROUP_NAME"));
	}
	public SmsUser get(String stUserId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, stUserId));
		List<SmsUser> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public SmsUser getUserName(String stUserName) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_LOGIN_NAME", Condition.OT_EQUAL, stUserName));
		List<SmsUser> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<SmsUser> select() {
		RecordSet rs;
//		String sql ="select * from SMS_USER";
		String sql = "select \n" +
				" DISTINCT su.*\n" +
				"from SMS_USER su\n" +
				"LEFT JOIN SMS_ORGAN so on su.ST_ORGAN_ID_TWO=so.ST_ORGAN_ID\n" +
				"LEFT JOIN SMS_ORGAN sso on so.ST_PARENT_ID = sso.ST_ORGAN_ID\n" +
				"LEFT JOIN SMS_ORGAN ssso on sso.ST_PARENT_ID = ssso.ST_ORGAN_ID\n" +
				"where ssso.ST_ORGAN_CODE='sms_organ2_root'";

		rs = SQL.execute(sql);
		ArrayList<SmsUser> al = new ArrayList<SmsUser>();
		while (rs.next()) {
			SmsUser info = new SmsUser();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

	//根据事项组id查关联的人员信息
	public PaginationArrayList<SmsUser> queryBystGroupId(String stGroupId, int pageSize, int currentPage) {
		RecordSet rs;
		String sql = "SELECT su.*,ug.ST_GROUP_NAME FROM UAC_USER_GROUP_LINK uugl JOIN SMS_USER su ON su.ST_USER_ID= uugl.ST_USER_ID JOIN UAC_GROUP ug ON ug.ST_GROUP_ID= uugl.ST_GROUP_ID  WHERE ug.ST_GROUP_ID = '"+stGroupId+"'";
		rs = SQL.execute(sql,pageSize,currentPage);
		PaginationArrayList<SmsUser> pal = new PaginationArrayList<SmsUser>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SmsUser info = new SmsUser();
			setProperties(info, rs);
			pal.add(info);
		}
		return pal;
	}

	//除去与事项组关联的人员列表
	public PaginationArrayList<SmsUser> queryNoUserLink(Conditions conds, String stGroupId,String stUserName, int pageSize, int currentPage) {
		RecordSet rs;
		String content = "*";
		String table;
		if (stUserName != null) {
			table = "SMS_USER where ST_USER_ID  NOT IN (SELECT su.ST_USER_ID  FROM UAC_USER_GROUP_LINK uugl JOIN SMS_USER su ON su.ST_USER_ID= uugl.ST_USER_ID JOIN UAC_GROUP ug ON ug.ST_GROUP_ID= uugl.ST_GROUP_ID  WHERE ug.ST_GROUP_ID= '"+stGroupId+"' ) AND ST_USER_NAME like '%"+stUserName+"%'";
		}else {
			table = "SMS_USER where ST_USER_ID  NOT IN (SELECT su.ST_USER_ID  FROM UAC_USER_GROUP_LINK uugl JOIN SMS_USER su ON su.ST_USER_ID= uugl.ST_USER_ID JOIN UAC_GROUP ug ON ug.ST_GROUP_ID= uugl.ST_GROUP_ID  WHERE ug.ST_GROUP_ID= '"+stGroupId+"' )";
		}
		String suffix = " ";
		if(con == null){
			rs = SQL.query(table,content,conds, suffix,pageSize,currentPage);
		}else{
			rs = SQL.query(con,table,content,conds, suffix,pageSize,currentPage);
		}

		PaginationArrayList<SmsUser> pal = new PaginationArrayList<SmsUser>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
		while (rs.next()) {
			SmsUser info = new SmsUser();
			setProperties(info, rs);
			pal.add(info);
		}
		return pal;
	}

}
