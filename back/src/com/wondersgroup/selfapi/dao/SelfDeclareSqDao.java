package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.SelmZhallItemOrgan;
import com.wondersgroup.selfapi.bean.SelmZhallItemSq;
import com.wondersgroup.selfapi.bean.WindowItemStatus;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;

@Repository
public class SelfDeclareSqDao {
	
	private Connection con = null;
	
	public void addZhallItem(SelmZhallItemSq info) {
		String sql = "INSERT INTO SELM_ZHALL_ITEM_SQ (ST_ID, ST_ITEM_NO, ST_ITEM_NAME, NM_BELONG, ST_ITEM_TYPE, " +
				"ST_ORG_CODE, ST_ORG_NAME, DT_CREATE, DT_UPDATE, ST_REMOVE, ST_DIC_CODE_GR, ST_DIC_CODE_FR, ST_ITEM_TEN_CODE, ST_TRANSACT_NAME, " +
				"ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4, NM_ISSON) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
				info.getStDicCodeGr(),
				info.getStDicCodeFr(),
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
	
	public void addZhallItemOrgan(SelmZhallItemOrgan organ) {
		String sql = " INSERT INTO SELM_ZHALL_ITEM_ORGAN (ST_OUGUID, ST_ORG_CODE, ST_OUNAME, ST_SHORT_NAME, "
					+" ST_OUCODE, ST_SH_CODE, IS_ONUSE, ST_DEPT_LEVEL, ST_AREA_CODE, ST_PARENT_OUGUID) " 
					+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = {
				organ.getStOuguid(),
				organ.getStOrgCode(),
				organ.getStOuname(),
				organ.getStShortName(),
				organ.getStOucode(),
				organ.getStShCode(),
				organ.getIsOnuse(),
				organ.getStDeptLevel(),
				organ.getStAreaCode(),
				organ.getStParentOuguid()
		};
		SQL.execute(sql,obj);
	}

	public OrganNodeInfo getOrganByCode(String orgCode) {
		
		String sql = "SELECT ST_OUGUID,ST_ORG_CODE,ST_OUNAME,ST_SHORT_NAME FROM SELM_ZHALL_ITEM_ORGAN WHERE ST_ORG_CODE = ?";
		RecordSet rs;
		List<OrganNodeInfo> organList = new ArrayList<OrganNodeInfo>();
		if(con ==null){
			rs = SQL.execute(sql,new Object[]{orgCode});
		}else{
			rs = SQL.execute(con,sql,new Object[]{orgCode});
		}
		while(rs.next()){
			OrganNodeInfo organ = new OrganNodeInfo();
			organ.setOrganId(rs.getOriginalString("ST_OUGUID"));
			organ.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			organ.setOrganName(rs.getOriginalString("ST_OUNAME"));
			organ.setDescription(rs.getOriginalString("ST_SHORT_NAME"));
			organList.add(organ);
		}
		return organList.size()>0 ? organList.get(0) : null;
	}

	public ItemSetPage getItemListByItemNameForPage(String itemName,
			Integer pageSize, Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();
		// TODO
		String sql = " " ;
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, pageSize, currentPage, new Object[] { "%"
					+ itemName + "%" });
		} else {
			rs = SQL.execute(con, sql, pageSize, currentPage,
					new Object[] { "%" + itemName + "%" });
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
		// TODO
		String sql = " ";
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
		String sql = "SELECT szio.ST_OUNAME,szio.ST_SHORT_NAME,szio.ST_SH_CODE FROM SELM_ZHALL_ITEM_SQ szi "
					+ " LEFT JOIN SELM_ZHALL_ITEM_ORGAN szio ON szio.ST_ORG_CODE = szi.ST_ORG_CODE "
					+ " WHERE szi.ST_ITEM_TEN_CODE != '' "
					+ " AND szi.ST_TRANSACT_NAME != '' "
					+ " AND szi.ST_ORG_CODE != '' "
					+ " GROUP BY szio.ST_OUNAME,szio.ST_SHORT_NAME,szio.ST_SH_CODE";
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
			info.setOrganCode(rs.getOriginalString("ST_SH_CODE"));
			info.setOrganName(rs.getOriginalString("ST_OUNAME"));
			info.setDescription(rs.getOriginalString("ST_SHORT_NAME"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}

	public ItemSetPage getItemListByOrganCodeForPage(String model,
			String organCode, Integer pageSize, Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();				
		String sql = " SELECT szi.ST_ITEM_NO,szi.ST_ITEM_NAME,szi.ST_ORG_NAME,szi.ST_ORG_CODE "
					+ " FROM  SELM_ZHALL_ITEM_SQ szi "
					+ " LEFT JOIN SELM_ZHALL_ITEM_ORGAN szio ON szio.ST_ORG_CODE = szi.ST_ORG_CODE "
					+ " WHERE szi.ST_ORG_CODE = ? "
					+ " AND szi.ST_REMOVE = 0 "
					+ " AND szio.IS_ONUSE != '1' "
					+ " AND szi.ST_TRANSACT_NAME != '' "
					+ " AND szi.ST_ITEM_TEN_CODE != '' "
					+ " GROUP BY szi.ST_ITEM_NO,szi.ST_ITEM_NAME,szi.ST_ORG_NAME,szi.ST_ORG_CODE"; 
		RecordSet rs;
		if(con ==null){
			rs = SQL.execute(sql,pageSize,currentPage,new Object[]{organCode});
		}else{
			rs = SQL.execute(con, sql,pageSize,currentPage,new Object[]{organCode});
		}
		ItemSetPage page = new ItemSetPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息"+ rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while(rs.next()){
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo( rs.getOriginalString("ST_ITEM_NO"));
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setPlaceCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			itemSetList.add(info);
		
		}
		page.setItemSetList(itemSetList);
		return page;
	}

	public WindowItemStatusPage getWindowItemStatusList(String itemNo,
			Integer pageSize, Integer currentPage, String deptCode) {
		List<WindowItemStatus> WindowItemStatusList = new ArrayList<WindowItemStatus>();
		Object[] obj = null;
		String sql = " SELECT szi.ST_ITEM_NO,szi.ST_ITEM_NAME,szi.ST_ITEM_TEN_CODE,szi.ST_ID,szi.ST_TRANSACT_NAME, "
					+" szi.ST_ORG_NAME,szi.ST_ORG_CODE FROM SELM_ZHALL_ITEM_SQ szi "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN szio ON szio.ST_ORG_CODE = szi.ST_ORG_CODE "
					+" WHERE szi.ST_ITEM_NO = ? "
					+" AND szi.ST_REMOVE = 0 "
					+" AND szi.ST_ITEM_TEN_CODE != '' "
					+" AND szi.ST_TRANSACT_NAME != '' "
					+" AND szio.IS_ONUSE != '1' ";
		if(StringUtils.isNotEmpty(deptCode)){
			sql += "AND szi.ST_ORG_CODE = ? ";
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

	public OrganNodeInfoPage getDeptInArea(String model, Integer pageSize,
			Integer currentPage, String areaCode) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		String sql = " SELECT szio.ST_OUNAME,szio.ST_SHORT_NAME,szio.ST_SH_CODE,szio.ST_ORG_CODE FROM SELM_ZHALL_ITEM_SQ szi "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN szio ON szio.ST_ORG_CODE = szi.ST_ORG_CODE "
					+" WHERE szi.ST_REMOVE = 0 "
					+" AND szi.ST_ITEM_TEN_CODE != '' "
					+" AND szi.ST_TRANSACT_NAME != '' "
					+" AND szio.IS_ONUSE != '1' "
					+" AND szio.ST_PARENT_OUGUID = "
					+" (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = ?) ";
					if("1".equals(model)){
						sql += " AND szi.ST_ITEM_TEN_CODE LIKE '113%' ";
					}
					sql += " GROUP BY szio.ST_OUNAME,szio.ST_SHORT_NAME,szio.ST_SH_CODE,szio.ST_ORG_CODE "
					+" ORDER BY szio.ST_OUNAME ASC";
				
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql,new Object[]{areaCode});
		} else {
			rs = SQL.execute(con, sql,new Object[]{areaCode});
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
			info.setOrganName(rs.getOriginalString("ST_OUNAME"));
			info.setDescription(rs.getOriginalString("ST_SHORT_NAME"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}

	public List<ItemTheme> getItemTheme(String model, String areaCode,
			String type) {
		String sql = " SELECT sid.ST_ITEM_DIC_NAME,sid.ST_ITEM_DIC_CODE,sid.ST_ITEM_DIC_TYPE FROM SELM_ZHALL_ITEM_SQ szi "
				+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN szio ON szio.ST_ORG_CODE = szi.ST_ORG_CODE ";
				if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
					if("1".equals(type)){
						sql += " JOIN SELM_ITEM_DIC_SQ sid on szi.ST_DIC_CODE_GR LIKE '%'+sid.ST_ITEM_DIC_CODE+'%' ";
					} else if("2".equals(type)){
						sql += " JOIN SELM_ITEM_DIC_SQ sid on szi.ST_DIC_CODE_FR LIKE '%'+sid.ST_ITEM_DIC_CODE+'%' ";
					}
				} else if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
					if("1".equals(type)){
						sql += " JOIN SELM_ITEM_DIC_SQ sid ON FIND_IN_SET(sid.ST_ITEM_DIC_CODE,szi.ST_DIC_CODE_GR) > 0 ";
					} else if("2".equals(type)){
						sql += " JOIN SELM_ITEM_DIC_SQ sid ON FIND_IN_SET(sid.ST_ITEM_DIC_CODE,szi.ST_DIC_CODE_FR) > 0 ";
					}
				}
				sql += " WHERE szi.ST_REMOVE = 0 "
				+" AND szi.NM_BELONG LIKE ? "
				+" AND sid.ST_ITEM_DIC_TYPE = ? ";
				if("1".equals(type)){
					sql += " AND szi.ST_DIC_CODE_GR != '' ";
				} else if("2".equals(type)){
					sql += " AND szi.ST_DIC_CODE_FR != '' ";
				}
				sql += " AND szi.ST_ITEM_TEN_CODE != '' "
				+" AND szi.ST_TRANSACT_NAME != '' "
				+" AND szio.IS_ONUSE != '1' "
				+" AND szio.ST_PARENT_OUGUID = "
				+" (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = ?) ";
				if("1".equals(model)){
					sql += " AND szi.ST_ITEM_TEN_CODE LIKE '113%' ";
				}
				sql += " GROUP BY sid.ST_ITEM_DIC_NAME,sid.ST_ITEM_DIC_CODE,sid.ST_ITEM_DIC_TYPE "
				+" ORDER BY sid.ST_ITEM_DIC_CODE ASC ";
		
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql,new Object[]{"%"+type+"%",type,areaCode});
		} else {
			rs = SQL.execute(con, sql,new Object[]{"%"+type+"%",type,areaCode});
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

	public List<ItemSet> getItemInTheme(String model, String themeCode,
			String areaCode, String type) {
		Object[] obj = null;
		String sql = " SELECT DISTINCT(szi.ST_ITEM_NO),szi.ST_ITEM_NAME,szi.ST_ORG_CODE from SELM_ZHALL_ITEM_SQ szi "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN szio ON szio.ST_ORG_CODE = szi.ST_ORG_CODE "
					+" WHERE szi.ST_REMOVE = 0 ";
					if("1".equals(type)){
						sql += " AND szi.ST_DIC_CODE_GR != '' ";
					} else if("2".equals(type)){
						sql += " AND szi.ST_DIC_CODE_FR != '' ";
					}
					sql += " AND szi.ST_ITEM_TEN_CODE != '' "
					+" AND szi.ST_TRANSACT_NAME != '' "
					+" AND szio.ST_PARENT_OUGUID =  "
					+" (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = ?) "
					+" AND szio.IS_ONUSE != '1' "
					+" AND szi.NM_BELONG LIKE ? ";
		if(StringUtils.isNotEmpty(themeCode)){
			if("1".equals(type)){
				sql += " AND szi.ST_DIC_CODE_GR LIKE ? ";
			} else if("2".equals(type)){
				sql += " AND szi.ST_DIC_CODE_FR LIKE ? ";
			}
			obj = new Object[]{areaCode,"%"+type+"%","%"+themeCode+"%"};
		} else {
			obj = new Object[]{areaCode,"%"+type+"%"};
		}
		sql += " ORDER BY szi.ST_ITEM_NO ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql,obj);
		} else {
			rs = SQL.execute(con, sql,obj);
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

	public List<ItemTheme> getThemeForSQ() {
		String sql = " SELECT w3.ST_ITEM_DIC_NAME,w3.ST_ITEM_DIC_CODE FROM SELM_ZHALL_ITEM_SQ w1 "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN w2 on w2.ST_ORG_CODE = w1.ST_ORG_CODE";
					if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC_SQ w3 on w1.ST_EXT2 LIKE '%'+w3.ST_ITEM_DIC_CODE+'%' ";
					} else if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC_SQ w3 ON FIND_IN_SET(w3.ST_ITEM_DIC_CODE,w1.ST_EXT2) > 0 ";
					}
					sql += " WHERE w1.ST_REMOVE = 0 "
					+" AND w1.ST_ITEM_TYPE = '公共服务' "
					+" AND w1.NM_BELONG LIKE '%'+w3.ST_ITEM_DIC_TYPE+'%' "
					+" AND w2.ST_PARENT_OUGUID = (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = 'SH00SQ')"
					+" AND w2.IS_ONUSE != '1'"
					+" GROUP BY w3.ST_ITEM_DIC_NAME,w3.ST_ITEM_DIC_CODE"
					+" ORDER BY w3.ST_ITEM_DIC_CODE";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		List<ItemTheme> list = new ArrayList<ItemTheme>();
		while (rs.next()) {
			ItemTheme theme = new ItemTheme();
			theme.setItemTypeCode(rs.getOriginalString("ST_ITEM_DIC_CODE"));
			theme.setItemTypeName(rs.getOriginalString("ST_ITEM_DIC_NAME"));
			list.add(theme);
		}
		return list;
	}

	public List<ItemSet> getItemInThemeForSQ(String themeName) {
		String sql = " SELECT DISTINCT(w1.ST_ITEM_NO),w1.ST_ID,w1.ST_ITEM_NAME,w1.ST_ORG_CODE from SELM_ZHALL_ITEM_SQ w1 "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN w2 on w2.ST_ORG_CODE = w1.ST_ORG_CODE";
					if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC_SQ w3 on w1.ST_EXT2 LIKE '%'+w3.ST_ITEM_DIC_CODE+'%' ";
					} else if("com.mysql.jdbc.Driver".equals(RdConfig.get("reindeer.service.jdbc.driver"))){
						sql += " JOIN SELM_ITEM_DIC_SQ w3 ON FIND_IN_SET(w3.ST_ITEM_DIC_CODE,w1.ST_EXT2) > 0 ";
					}
					sql += " WHERE w1.ST_REMOVE = 0 "
					+" AND w1.ST_ITEM_TYPE = '公共服务' "
					+" AND w2.ST_PARENT_OUGUID = (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = 'SH00SQ')"
					+" AND w2.IS_ONUSE != '1'"
					+" AND w3.ST_ITEM_DIC_NAME = ?";
		Object[] obj = new Object[]{themeName};
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql,obj);
		} else {
			rs = SQL.execute(con, sql,obj);
		}
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemId(rs.getOriginalString("ST_ID"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			list.add(info);
		}
		return list;
	}
	
	public OrganNodeInfoPage getOrganListForSQ(Integer pageSize,
			Integer currentPage) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		String sql = " SELECT w2.ST_OUNAME,w2.ST_SHORT_NAME,w2.ST_ORG_CODE FROM SELM_ZHALL_ITEM_SQ w1 "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN w2 on w2.ST_ORG_CODE = w1.ST_ORG_CODE "
					+" WHERE  w1.ST_REMOVE = 0 "
					+" AND w1.ST_ITEM_TYPE = '公共服务' "
					+" AND w2.ST_PARENT_OUGUID = (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = 'SH00SQ') "
					+" AND w2.IS_ONUSE != '1' "
					+" GROUP BY w2.ST_OUNAME,w2.ST_SHORT_NAME,w2.ST_ORG_CODE "
					+" ORDER BY w2.ST_OUNAME ASC ";
				
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
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_OUNAME"));
			info.setDescription(rs.getOriginalString("ST_SHORT_NAME"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}

	public ItemSetPage getItemListByOrganIdForSQ(String organCode,
			Integer pageSize, Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();
		String sql = " SELECT w1.ST_ID,w1.ST_ITEM_NO,w1.ST_ITEM_NAME,w1.ST_ORG_NAME,w1.ST_ORG_CODE "
					+" FROM  SELM_ZHALL_ITEM_SQ w1 "
					+" LEFT JOIN SELM_ZHALL_ITEM_ORGAN w2 on w2.ST_ORG_CODE = w1.ST_ORG_CODE "
					+" WHERE w1.ST_ORG_CODE = ? AND w1.ST_REMOVE = 0 "
					+" AND w1.ST_ITEM_TYPE = '公共服务' "
					+" AND w2.ST_PARENT_OUGUID = (SELECT a.ST_OUGUID FROM SELM_ZHALL_ITEM_ORGAN a WHERE a.ST_SH_CODE = 'SH00SQ') "
					+" AND w2.IS_ONUSE != '1' "
					+" GROUP BY w1.ST_ID,w1.ST_ITEM_NO,w1.ST_ITEM_NAME,w1.ST_ORG_NAME,w1.ST_ORG_CODE ";
		RecordSet rs;
		if(con ==null){
			rs = SQL.execute(sql,pageSize,currentPage,new Object[]{organCode});
		}else{
			rs = SQL.execute(con, sql,pageSize,currentPage,new Object[]{organCode});
		}
		ItemSetPage page = new ItemSetPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息"+ rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while(rs.next()){
			ItemSet info = new ItemSet();
			info.setStItemId(rs.getOriginalString("ST_ID"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo( rs.getOriginalString("ST_ITEM_NO"));
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setPlaceCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			itemSetList.add(info);
		
		}
		page.setItemSetList(itemSetList);
		return page;
	}
}
