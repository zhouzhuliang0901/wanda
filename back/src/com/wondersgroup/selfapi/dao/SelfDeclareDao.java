package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemInfo;
import com.wondersgroup.selfapi.bean.WindowItemStatus;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;

@Repository
public class SelfDeclareDao {
	
	private Connection con = null;
	
	public void addZhallItem(SelmZhallItemInfo info) {
		String sql = "INSERT INTO SELM_ZHALL_ITEM (ST_ID, ST_ITEM_NO, ST_ITEM_NAME, NM_BELONG, ST_ITEM_TYPE, " +
				"ST_ORG_CODE, ST_ORG_NAME, DT_CREATE, DT_UPDATE, ST_REMOVE, ST_DIC_CODE, ST_ITEM_TEN_CODE, ST_TRANSACT_NAME, " +
				"ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4, NM_ISSON) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = {
				info.getStId(),
				info.getStItemNo(),
				info.getStItemName(),
				info.getNmBelong(),
				info.getStItemType(),
				info.getStOrgCode(),
				info.getStOrgName(),
				info.getDtCreate(),
				info.getDtUpdate(),
				info.getStRemove(),
				info.getStDicCode(),
				info.getStItemTenCode(),
				info.getStTransactName(),
				info.getStExt1(),
				info.getStExt2(),
				info.getStExt3(),
				info.getStExt4(),
				info.getNmIsson()
		};
		SQL.execute(sql,obj);
	}
	
	public void updateZhallItem(SelmZhallItemInfo info){
		String sql = " UPDATE SELM_ZHALL_ITEM SET ST_ID=?, ST_ITEM_NO=?, ST_ITEM_NAME=?, NM_BELONG=?, ST_ITEM_TYPE=?, "
					+" ST_ORG_CODE=?, ST_ORG_NAME=?, DT_CREATE=?, DT_UPDATE=?, ST_REMOVE=?, NM_ISSON=?, "
					+" ST_DIC_CODE=?, ST_ITEM_TEN_CODE=?, "
					+" ST_TRANSACT_NAME=?, ST_EXT1=?, ST_EXT2=?, ST_EXT3=?, ST_EXT4=? WHERE (ST_ID=?)";
		Object[] obj = {
				info.getStId(),
				info.getStItemNo(),
				info.getStItemName(),
				info.getNmBelong(),
				info.getStItemType(),
				info.getStOrgCode(),
				info.getStOrgName(),
				info.getDtCreate(),
				info.getDtUpdate(),
				info.getStRemove(),
				info.getStDicCode(),
				info.getStItemTenCode(),
				info.getStTransactName(),
				info.getStExt1(),
				info.getStExt2(),
				info.getStExt3(),
				info.getStExt4(),
				info.getNmIsson(),
				info.getStId()
		};
		SQL.execute(sql,obj);
	}
	
    public List<SelmZhallItemInfo> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_ZHALL_ITEM", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_ZHALL_ITEM", "*", conds, suffix);
        }
        List<SelmZhallItemInfo> al = new ArrayList<SelmZhallItemInfo>();
        while (rs.next()) {
        	SelmZhallItemInfo info = new SelmZhallItemInfo();
        	info.setStId(rs.getOriginalString("ST_ID"));
        	info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
        	info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
        	info.setNmBelong(rs.getOriginalString("NM_BELONG"));
        	info.setStItemType(rs.getOriginalString("ST_ITEM_TYPE"));
        	info.setStOrgCode(rs.getOriginalString("ST_ORG_CODE"));
        	info.setStOrgName(rs.getOriginalString("ST_ORG_NAME"));
        	info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        	info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        	info.setStRemove(Integer.valueOf(rs.getOriginalString("ST_REMOVE")));
        	info.setStDicCode(rs.getOriginalString("ST_DIC_CODE"));
        	info.setStItemTenCode(rs.getOriginalString("ST_ITEM_TEN_CODE"));
        	info.setStTransactName(rs.getOriginalString("ST_TRANSACT_NAME"));
        	info.setStExt1(rs.getOriginalString("ST_EXT1"));
        	info.setStExt2(rs.getOriginalString("ST_EXT2"));
        	info.setStExt3(rs.getOriginalString("ST_EXT3"));
        	info.setStExt4(rs.getOriginalString("ST_EXT4"));
        	info.setNmIsson(Integer.valueOf(rs.getOriginalString("NM_ISSON")));
            al.add(info);
        }
        return al;
    }

	public ItemSetPage getItemListByItemNameForPage(String itemName,
			String areaCode, Integer pageSize, Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();
		String sql = " SELECT w1.ST_ITEM_NO,w1.ST_ITEM_NAME,w1.ST_ORG_NAME " 
//				+ " c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION " 
				+ " FROM SELM_ZHALL_ITEM w1 "
//				+ " JOIN SELM_ORGAN_NODE c ON w1.ST_ORG_CODE = c.CODE "
				+ " WHERE w1.ST_ITEM_NAME LIKE ? AND w1.ST_REMOVE = 0 "
				+ " AND w1.ST_ITEM_TEN_CODE IS NOT NULL "
				+ " AND (w1.ST_ITEM_TYPE = '审批' OR w1.ST_ITEM_TYPE = '服务') "
//				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " AND w1.ST_ORG_CODE LIKE ? "
				+ " GROUP BY w1.ST_ITEM_NO,w1.ST_ITEM_NAME,w1.ST_ORG_NAME " ;
//				+ " c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, pageSize, currentPage, new Object[] {"%"+ itemName + "%", 
					"%"+ areaCode.substring(areaCode.length()-2, areaCode.length())});
		} else {
			rs = SQL.execute(con, sql, pageSize, currentPage,
					new Object[] {"%" + itemName + "%", "%"+ areaCode.substring(areaCode.length()-2, areaCode.length())});
		}
		ItemSetPage page = new ItemSetPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			info.setOrganCode(rs.getOriginalString("CODE"));
			info.setPlaceCode(rs.getOriginalString("CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			itemSetList.add(info);
		}
		page.setItemSetList(itemSetList);
		return page;
	}

	public ItemSetPage getAllItemListForPage(Integer pageSize,
			Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();
		String sql = " SELECT w1.ST_ITEM_TEN_CODE,w1.ST_ITEM_NAME,w1.ST_ORG_NAME,w1.ST_ORG_CODE " 
//				+ " c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION 
				+" FROM SELM_ZHALL_ITEM w1 "
//				+ " JOIN SELM_ORGAN_NODE c ON w1.ST_ORG_CODE = c.CODE "
				+ " WHERE  w1.ST_REMOVE = 0 "
				+ " AND w1.ST_ITEM_TEN_CODE IS NOT NULL "
				+ " AND (w1.ST_ITEM_TYPE = '审批' OR w1.ST_ITEM_TYPE = '服务') "
				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " GROUP BY w1.ST_ITEM_TEN_CODE,w1.ST_ITEM_NAME,w1.ST_ORG_NAME,W1.ST_ORG_CODE ";
//				+ " c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, pageSize, currentPage);
		} else {
			rs = SQL.execute(con, sql, pageSize, currentPage);
		}
		ItemSetPage page = new ItemSetPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			String itemNo = rs.getOriginalString("ST_ITEM_TEN_CODE");
			// info.setStItemTenNo(rs.getOriginalString("ST_ITEM_TEN_NO"));
			info.setStItemNo(itemNo);
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setPlaceCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			itemSetList.add(info);
		}
		page.setItemSetList(itemSetList);
		return page;
	}

	public OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		String sql = " SELECT w1.ST_ORG_NAME,w1.ST_ORG_CODE FROM SELM_ZHALL_ITEM w1 " 
				+ " WHERE w1.ST_REMOVE = 0 "
				+ " AND w1.ST_ITEM_TEN_CODE IS NOT NULL "
				+ " AND (w1.ST_ITEM_TYPE = '审批' OR w1.ST_ITEM_TYPE = '服务') "
//				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " GROUP BY w1.ST_ORG_NAME,w1.ST_ORG_CODE "
				+ " ORDER BY w1.ST_ORG_NAME ASC ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		OrganNodeInfoPage page = new OrganNodeInfoPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			info.setDescription(rs.getOriginalString("ST_ORG_NAME"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}

	public WindowItemStatusPage getWindowItemStatusList(String itemNo,
			Integer pageSize, Integer currentPage, String deptCode) {
		List<WindowItemStatus> WindowItemStatusList = new ArrayList<WindowItemStatus>();
		Object[] obj = null;
		String sql = " SELECT w.ST_ITEM_NO,w.ST_ITEM_NAME,w.ST_ITEM_TEN_CODE,w.ST_ID,w.ST_TRANSACT_NAME," 
				+ " w.ST_ORG_NAME,w.ST_ORG_CODE FROM SELM_ZHALL_ITEM w "
				+ " WHERE w.ST_ITEM_NO = ? "
				+ " AND (w.ST_ITEM_TYPE = '审批' OR w.ST_ITEM_TYPE = '服务') "
				+ " AND w.ST_ITEM_TEN_CODE IS NOT NULL ";
//				+ " AND w.ST_TRANSACT_NAME IS NOT NULL ";
		if(StringUtils.isNotEmpty(deptCode)){
			sql += "AND w.ST_ORG_CODE = ? ";
			obj = new Object[] { itemNo, deptCode};
		} else {
			obj = new Object[] { itemNo };
		}
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, pageSize, currentPage,
					obj);
		} else {
			rs = SQL.execute(con, sql, pageSize, currentPage,
					obj);
		}
		WindowItemStatusPage page = new WindowItemStatusPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			WindowItemStatus info = new WindowItemStatus();
			info.setStStatusName(rs.getOriginalString("ST_TRANSACT_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			info.setStItemTenNo(rs.getOriginalString("ST_ITEM_TEN_CODE"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemId(rs.getOriginalString("ST_ID"));
			info.setDescription(rs.getOriginalString("ST_ORG_NAME"));
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setPlaceCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			WindowItemStatusList.add(info);
		}
		page.setWindowItemStatusList(WindowItemStatusList);
		return page;
	}

	public OrganNodeInfoPage queryAllAreaInShanghai(Integer pageSize,
			Integer currentPage) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		String sql = "SELECT CODE,NAME,DESCRIPTION FROM SELM_ORGAN_NODE " +
				"WHERE REMOVED = 0 AND PARENT_PLACE_CODE = 'SHSH' ORDER BY NM_ORDER ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		OrganNodeInfoPage page = new OrganNodeInfoPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganCode(rs.getOriginalString("CODE"));
			info.setOrganName(rs.getOriginalString("NAME"));
			info.setDescription(rs.getOriginalString("DESCRIPTION"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}

	public OrganNodeInfoPage queryDeptInArea(String model, Integer pageSize,
			Integer currentPage, String areaCode) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		List<String> param = new ArrayList<String>();
		String sql = " SELECT w1.ST_ORG_NAME,w1.ST_ORG_CODE FROM SELM_ZHALL_ITEM w1 "
				+ " WHERE  w1.ST_REMOVE = 0 "
				+ " AND w1.ST_ITEM_TEN_CODE IS NOT NULL "
				+ " AND (w1.ST_ITEM_TYPE = '审批' OR w1.ST_ITEM_TYPE = '服务') ";
		// if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
		// sql +=
		// " AND SUBSTRING(w1.ST_ORG_CODE, LEN(w1.ST_ORG_CODE)-1, LEN(w1.ST_ORG_CODE)) = ? ";
		// } else
		// if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
		// sql += " AND RIGHT(w1.ST_ORG_CODE, 2) = ? ";
		// }
		if ("GW".equals(areaCode.substring(areaCode.length() - 2,
				areaCode.length()))) {
			sql += " AND w1.ST_ORG_CODE = ? ";
			param.add(areaCode);
		} else {
			sql += " AND w1.ST_ORG_CODE like ? ";
			param.add("%"
					+ areaCode.substring(areaCode.length() - 2,
							areaCode.length()));
		}
		// sql += " AND w1.ST_TRANSACT_NAME IS NOT NULL ";
		if ("1".equals(model)) {
			sql += " AND w1.ST_ITEM_TEN_CODE LIKE '113%' ";
		}
		sql += " GROUP BY w1.ST_ORG_NAME,w1.ST_ORG_CODE "
				+ " ORDER BY w1.ST_ORG_NAME ASC ";
		RecordSet rs;
		Object[] obj = param.toArray();
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		OrganNodeInfoPage page = new OrganNodeInfoPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			info.setDescription(rs.getOriginalString("ST_ORG_NAME"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}

	public List<ItemTheme> queryItemThemeInArea(String model, String areaCode, String type) {
		String sql = " SELECT w3.ST_ITEM_DIC_NAME,w3.ST_ITEM_DIC_CODE FROM SELM_ZHALL_ITEM w1 ";
					if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC w3 on w1.ST_DIC_CODE LIKE '%'+w3.ST_ITEM_DIC_CODE+'%' ";
					} else if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC w3 ON FIND_IN_SET(w3.ST_ITEM_DIC_CODE,w1.ST_DIC_CODE) > 0 ";
					}
					sql +=" WHERE w1.ST_REMOVE = 0 "
					+" AND w3.ST_ITEM_DIC_CODE LIKE ? "
					+" AND w1.NM_BELONG = ? "
					+" AND w1.ST_DIC_CODE != '' "
					+" AND w1.ST_ITEM_TEN_CODE IS NOT NULL  "
					+" AND (w1.ST_ITEM_TYPE = '审批' OR w1.ST_ITEM_TYPE = '服务') ";
					if ("GW".equals(areaCode.substring(areaCode.length() - 2,
							areaCode.length()))) {
						sql += " AND w1.ST_ORG_CODE = ?";
					} else {
						if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
							sql += " AND SUBSTRING(w1.ST_ORG_CODE, LEN(w1.ST_ORG_CODE)-1, LEN(w1.ST_ORG_CODE)) = ? ";
						} else if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
							sql += " AND RIGHT(w1.ST_ORG_CODE, 2) = ? ";
						}
					}

//					sql +=" AND w1.ST_TRANSACT_NAME IS NOT NULL ";
					if("1".equals(model)){
						sql += " AND w1.ST_ITEM_TEN_CODE LIKE '113%' ";
					}
					sql +=" GROUP BY w3.ST_ITEM_DIC_NAME,w3.ST_ITEM_DIC_CODE "
					+" ORDER BY w3.ST_ITEM_DIC_CODE ASC ";
		
		RecordSet rs;
		if (con == null) {
			if(StringUtils.isNotEmpty(areaCode)){
				if ("GW".equals(areaCode.substring(areaCode.length() - 2,areaCode.length()))) {
					rs = SQL.execute(sql,new Object[]{"%"+type+"%",type,areaCode});
				} else {
					rs = SQL.execute(sql,new Object[]{"%"+type+"%",type,areaCode.substring(areaCode.length()-2, areaCode.length())});
				}
			} else {
				rs = SQL.execute(sql,new Object[]{"%"+type+"%",type,""});
			}
		} else {
			if(StringUtils.isNotEmpty(areaCode)){
				if ("GW".equals(areaCode.substring(areaCode.length() - 2,areaCode.length()))) {
					rs = SQL.execute(sql,new Object[]{"%"+type+"%",type,areaCode});
				} else {
					rs = SQL.execute(sql,new Object[]{"%"+type+"%",type,areaCode.substring(areaCode.length()-2, areaCode.length())});
				}
			} else {
				rs = SQL.execute(con, sql,new Object[]{"%"+type+"%",type,""});
			}
		}
		List<ItemTheme> list = new ArrayList<ItemTheme>();
		while (rs.next()) {
			ItemTheme theme = new ItemTheme();
			theme.setItemTypeCode(rs.getOriginalString("ST_ITEM_DIC_NAME"));
			theme.setItemTypeName(rs.getOriginalString("ST_ITEM_DIC_CODE"));
			list.add(theme);
		}
		return list;
	}

	public List<ItemSet> queryItemInTheme(String model, String themeCode, 
			String areaCode, String type) {
		Object[] obj = null;
		String sql = " SELECT DISTINCT(ST_ITEM_NO),ST_ITEM_NAME,ST_ORG_CODE from SELM_ZHALL_ITEM "
				+ " WHERE ST_REMOVE = 0 "
				+ " AND ST_DIC_CODE != '' "
				+ " AND ST_ITEM_TEN_CODE IS NOT NULL "
				+ " AND (ST_ITEM_TYPE = '审批' OR ST_ITEM_TYPE = '服务') ";
		if ("GW".equals(areaCode.substring(areaCode.length() - 2,
				areaCode.length()))) {
			sql += " AND ST_ORG_CODE = ?";
		} else {
			if ("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig
					.get("reindeer.service.jdbc.driver"))) {
				sql += " AND SUBSTRING(ST_ORG_CODE, LEN(ST_ORG_CODE)-1, LEN(ST_ORG_CODE)) = ? ";
			} else if ("com.mysql.jdbc.Driver".equals(RdConfig
					.get("reindeer.service.jdbc.driver"))) {
				sql += " AND RIGHT(ST_ORG_CODE, 2) = ? ";
			}
		}
		if ("1".equals(model)) {
			sql += " AND ST_ITEM_TEN_CODE LIKE '113%' ";
		}
		// sql += " AND ST_TRANSACT_NAME IS NOT NULL ";
		sql += " AND NM_BELONG LIKE ?";
		if (StringUtils.isNotEmpty(themeCode)) {
			sql += " AND ST_DIC_CODE LIKE ? ";
			if ("GW".equals(areaCode.substring(areaCode.length() - 2,
			
					areaCode.length()))) {
				obj = new Object[] {areaCode, "%" + type + "%", "%" + themeCode + "%" };
			} else {
				obj = new Object[] {
						areaCode.substring(areaCode.length() - 2, areaCode.length()),
						"%" + type + "%", "%" + themeCode + "%" };
			}
		} else {
			if ("GW".equals(areaCode.substring(areaCode.length() - 2,
					areaCode.length()))) {
				obj = new Object[] {areaCode, "%" + type + "%", "%" + themeCode + "%" };
			} else {
				obj = new Object[] {
						areaCode.substring(areaCode.length() - 2, areaCode.length()),
						"%" + type + "%", "%" + themeCode + "%" };
			}
		}
		sql += " ORDER BY ST_ITEM_NO ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			list.add(info);
		}
		return list;
	}

	public List<ItemTheme> queryThemeInStreet() {
		String sql = " SELECT w3.ST_ITEM_DIC_NAME,w3.ST_ITEM_DIC_CODE FROM SELM_ZHALL_ITEM w1 ";
					if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC w3 on w1.ST_DIC_CODE LIKE '%'+w3.ST_ITEM_DIC_CODE+'%' ";
					} else if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC w3 ON FIND_IN_SET(w3.ST_ITEM_DIC_CODE,w1.ST_DIC_CODE) > 0 ";
					}
//					+" JOIN SELM_ITEM_DIC w3 on w1.ST_DIC_CODE LIKE '%'+w3.ST_ITEM_DIC_CODE+'%' "
					sql +=" WHERE w1.ST_REMOVE = 0 "
					+" AND w1.ST_DIC_CODE != '' "
					+" AND w1.ST_ITEM_TEN_CODE IS NOT NULL "
					+" AND w1.ST_ITEM_TYPE = '社区服务' "
					+" AND w1.ST_TRANSACT_NAME IS NOT NULL "
					+" AND w1.ST_DIC_CODE NOT LIKE '%241%' "
					+" GROUP BY w3.ST_ITEM_DIC_NAME,w3.ST_ITEM_DIC_CODE "
					+" ORDER BY w3.ST_ITEM_DIC_CODE ASC ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		List<ItemTheme> list = new ArrayList<ItemTheme>();
		while (rs.next()) {
			ItemTheme theme = new ItemTheme();
			theme.setItemTypeCode(rs.getOriginalString("ST_ITEM_DIC_NAME"));
			theme.setItemTypeName(rs.getOriginalString("ST_ITEM_DIC_CODE"));
			list.add(theme);
		}
		return list;
	}
	
	public int deleteItemTemp(){
		String sql = "DELETE SELM_ZHALL_ITEM_TEMP";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		return rs.TOTAL_RECORD_COUNT;
	}
	
	public int backupItem(String area) {
		String sql = " INSERT INTO SELM_ZHALL_ITEM_TEMP SELECT * FROM SELM_ZHALL_ITEM "
				+ " WHERE ST_ORG_CODE LIKE ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { "%" + area });
		} else {
			rs = SQL.execute(con, sql, new Object[] { "%" + area });
		}
		return rs.TOTAL_RECORD_COUNT;
	}
	
	public int deleteItem(String area){
		String sql = "DELETE SELM_ZHALL_ITEM WHERE ST_ORG_CODE LIKE ? ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql , new Object[]{"%"+area});
		} else {
			rs = SQL.execute(con, sql, new Object[]{"%"+area});
		}
		return rs.TOTAL_RECORD_COUNT;
	}

}
