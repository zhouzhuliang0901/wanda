package com.wondersgroup.publicService.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.wondersgroup.publicService.bean.OrganNodeInfo;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class SelfDeclareDao {
	
	private Connection con = null;
	
	public void addZhallItem(SelmZhallItemInfo info) {
		String sql = "INSERT INTO SELM_ITEM_INFO (ST_ID, ST_ITEM_NO, ST_ITEM_NAME, ST_SUB_ITEM_NAME, NM_BELONG, " +
				"ST_ITEM_CODE, ST_ITEM_TYPE, ST_ORG_CODE, ST_ORG_NAME, DT_CREATE, DT_UPDATE, ST_REMOVE, ST_DIC_CODE, " +
				"ST_ITEM_TEN_CODE, ST_TRANSACT_NAME, ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = {
				info.getStId(),
				info.getStItemNo(),
				info.getStItemName(),
				info.getStSubItemName(),
				info.getNmBelong(),
				info.getStItemCode(),
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
				info.getStExt4()
		};
		SQL.execute(sql,obj);
	}
	
	public void updateZhallItem(SelmZhallItemInfo info){
		String sql = " UPDATE SELM_ITEM_INFO SET ST_ITEM_NO=?, ST_ITEM_NAME=?, ST_SUB_ITEM_NAME=?, NM_BELONG=?, "
				    +" ST_ITEM_CODE=?, ST_ITEM_TYPE=?, ST_ORG_CODE=?, ST_ORG_NAME=?, DT_CREATE=?, DT_UPDATE=?, "
					+" ST_REMOVE=?, ST_DIC_CODE=?, ST_ITEM_TEN_CODE=?, "
					+" ST_TRANSACT_NAME=?, ST_EXT1=?, ST_EXT2=?, ST_EXT3=?, ST_EXT4=? WHERE (ST_ID=?)";
		Object[] obj = {
				info.getStItemNo(),
				info.getStItemName(),
				info.getStSubItemName(),
				info.getNmBelong(),
				info.getStItemCode(),
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
				info.getStId()
		};
		SQL.execute(sql,obj);
	}
	
    public List<SelmZhallItemInfo> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_ITEM_INFO", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "SELM_ITEM_INFO", "*", conds, suffix);
        }
        List<SelmZhallItemInfo> al = new ArrayList<SelmZhallItemInfo>();
        while (rs.next()) {
        	SelmZhallItemInfo info = new SelmZhallItemInfo();
        	info.setStId(rs.getOriginalString("ST_ID"));
        	info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
        	info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
        	info.setStSubItemName(rs.getOriginalString("ST_SUB_ITEM_NAME"));
        	info.setNmBelong(rs.getOriginalString("NM_BELONG"));
        	info.setStItemCode(rs.getOriginalString("ST_ITEM_CODE"));
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
            al.add(info);
        }
        return al;
    }
    
    public List<SelmZhallItemInfo> getItemByItemName(String itemName){
    	String sql = "select DISTINCT ST_ITEM_NAME,ST_SUB_ITEM_NAME,ST_ITEM_NO,NM_BELONG from SELM_ITEM_INFO " +
    			" where ST_SUB_ITEM_NAME like ? ";
    	if(RdConfig.get("reindeer.service.jdbc.driver").contains("mysql")){
    		sql += " ORDER BY CONVERT(ST_SUB_ITEM_NAME USING GBK) ASC";
    	} else {
    		sql += " ORDER BY ST_SUB_ITEM_NAME";
    	}
    	List<String> param = new ArrayList<String>();
    	param.add("%"+itemName+"%");
		List<SelmZhallItemInfo> list = new ArrayList<SelmZhallItemInfo>();
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, param.toArray());
		} else {
			rs = SQL.execute(con, sql, param.toArray());
		}
		while (rs.next()) {
			SelmZhallItemInfo item = new SelmZhallItemInfo();
			item.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			item.setStSubItemName(rs.getOriginalString("ST_SUB_ITEM_NAME"));
			item.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			item.setNmBelong(rs.getOriginalString("NM_BELONG"));
			list.add(item);
		}
		return list;
    }
    
    public List<OrganNodeInfo> getAllOrgan(){
    	String sql = "select ST_ORG_NAME, ST_ORG_CODE from SELM_ITEM_INFO " +
    			"group by ST_ORG_NAME, ST_ORG_CODE ";
    	if(RdConfig.get("reindeer.service.jdbc.driver").contains("mysql")){
    		sql += " order by CONVERT(ST_ORG_NAME USING GBK) ASC";
    	} else {
    		sql += " order by ST_ORG_NAME";
    	}
    	List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
    	RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			organNodeInfoList.add(info);
		}
		return organNodeInfoList;
    }
    
    public List<SelmZhallItemInfo> getItemByOrgan(String organCode, String itemName){
    	String sql = "select DISTINCT ST_ITEM_NAME,ST_SUB_ITEM_NAME,ST_ITEM_NO,NM_BELONG from SELM_ITEM_INFO " +
    			" where ST_ORG_CODE = ? ";
    	if(RdConfig.get("reindeer.service.jdbc.driver").contains("mysql")){
    		sql += " ORDER BY CONVERT(ST_SUB_ITEM_NAME USING GBK) ASC";
    	} else {
    		sql += " ORDER BY ST_SUB_ITEM_NAME";
    	}
    	List<String> param = new ArrayList<String>();
    	param.add(organCode);
		if(StringUtils.isNotEmpty(itemName)){
			sql += " AND ST_SUB_ITEM_NAME = ?";
			param.add(itemName);
		}
		List<SelmZhallItemInfo> list = new ArrayList<SelmZhallItemInfo>();
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, param.toArray());
		} else {
			rs = SQL.execute(con, sql, param.toArray());
		}
		while (rs.next()) {
			SelmZhallItemInfo item = new SelmZhallItemInfo();
			item.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			item.setStSubItemName(rs.getOriginalString("ST_SUB_ITEM_NAME"));
			item.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			item.setNmBelong(rs.getOriginalString("NM_BELONG"));
			list.add(item);
		}
		return list;
    }
}
