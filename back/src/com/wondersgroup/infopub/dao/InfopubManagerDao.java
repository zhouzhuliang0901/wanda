package com.wondersgroup.infopub.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.infopub.bean.SelmAssist;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Repository
public class InfopubManagerDao {

    private Connection con = null;

    public InfopubManagerDao() {
    }

    public InfopubManagerDao(Connection con) {
        this.con = con;
    }
    
    
	
	public int checkManager(String managerIdCard, String machineMac){
		RecordSet rs;
		int count = 0;
		String sql = " SELECT sda.ST_DEVICE_ID,sa.ST_ASSIST_NAME,idi.ST_DEVICE_NAME FROM SELM_ASSIST sa "
					+" LEFT JOIN SELM_DEVICE_ASSIST sda ON sda.ST_ASSIST_ID = sa.ST_ASSIST_ID "
					+" LEFT JOIN INFOPUB_DEVICE_INFO idi ON idi.ST_DEVICE_MAC = sda.ST_DEVICE_ID "
					+" WHERE sa.ST_ASSIST_IDCARD = ? "
					+" AND sda.ST_DEVICE_ID = ? "
					+" AND idi.NM_SDTYPE = 0";
        if (con == null) {
            rs =  SQL.execute(sql, new Object[]{managerIdCard, machineMac});
        } else {
        	rs = SQL.execute(con, sql, new Object[]{managerIdCard, machineMac});
        }
        count = rs.TOTAL_RECORD_COUNT;
        return count;
	}
	
	public int checkItem(String itemName){
		RecordSet rs;
		int count = 0;
		String sql = "SELECT * FROM SELM_ITEM WHERE ST_MAIN_NAME = ? AND ST_EXT4 = 1";
        if (con == null) {
            rs =  SQL.execute(sql, new Object[]{itemName});
        } else {
        	rs = SQL.execute(con, sql, new Object[]{itemName});
        }
        count = rs.TOTAL_RECORD_COUNT;
        return count;
	}
	
    public PaginationArrayList<SelmAssist> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("SELM_ASSIST", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "SELM_ASSIST", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<SelmAssist> pal = new PaginationArrayList<SelmAssist>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
        	SelmAssist info = new SelmAssist();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }
    
	public JSONArray queryItemOrgan(String machineMac, String itemType) {
		RecordSet rs;
		String sql = "select si.ST_ORGAN_ID,"
				+ " so.ST_ORGAN_NAME "
				+ " from selm_device_itemType sdi "
				+ " left join selm_item_type sit on sit.ST_ITEM_TYPE_ID = sdi.ST_ITEM_TYPE_ID "
				+ " left join selm_item_link sil on sil.ST_ITEM_TYPE_ID = sit.ST_ITEM_TYPE_ID "
				+ " left join selm_item si on si.ST_ITEM_ID = sil.ST_ITEM_ID "
				+ " left join sms_organ so on so.ST_ORGAN_ID = si.ST_ORGAN_ID  "
				+ " where sdi.ST_DEVICE_ID = ? and si.ST_ITEM_TYPE = ? "
				+ " group by si.ST_ORGAN_ID,so.ST_ORGAN_NAME";
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { machineMac, itemType });
		} else {
			rs = SQL.execute(con, sql, new Object[] { machineMac, itemType });
		}
		JSONArray arr = new JSONArray();
		while (rs.next()) {
			JSONObject json = new JSONObject();
			json.put("stOrganId", rs.getOriginalString("ST_ORGAN_ID"));
			json.put("stOrganName", rs.getOriginalString("ST_ORGAN_NAME"));
			arr.add(json);
		}
		return arr;
	}
	
	public List<SelmItem> queryItemList(String machineMac, String itemType, String organId) {
		RecordSet rs;
		String sql = "select si.ST_ITEM_ID,si.ST_ITEM_NO,si.ST_TEN_CODE,ST_MAIN_NAME,ST_WORK_URL,ST_EXT3 "
				+ " from selm_device_itemType sdi "
				+ " left join selm_item_type sit on sit.ST_ITEM_TYPE_ID = sdi.ST_ITEM_TYPE_ID "
				+ " left join selm_item_link sil on sil.ST_ITEM_TYPE_ID = sit.ST_ITEM_TYPE_ID "
				+ " left join selm_item si on si.ST_ITEM_ID = sil.ST_ITEM_ID "
				+ " where sdi.ST_DEVICE_ID = ? "
				+ " and si.ST_ORGAN_ID = ? "
				+ " and si.ST_ITEM_TYPE = ? "
				+ " and si.ST_PARENT_ID is null";
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { machineMac, organId, itemType });
		} else {
			rs = SQL.execute(con, sql, new Object[] { machineMac, organId,
					itemType });
		}
		List<SelmItem> list = new ArrayList<SelmItem>();
		while (rs.next()) {
			SelmItem item = new SelmItem();
			item.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
			item.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			item.setStTenCode(rs.getOriginalString("ST_TEN_CODE"));
			item.setStMainName(rs.getOriginalString("ST_MAIN_NAME"));
			item.setStWorkUrl(rs.getOriginalString("ST_WORK_URL"));
			item.setStExt3(rs.getOriginalString("ST_EXT3"));
			list.add(item);
		}
		return list;
	}
	
	public List<SelmItem> querySubItemInfo(String itemId){
		RecordSet rs;
		String sql = "select si.ST_ITEM_ID,si.ST_ITEM_NO,si.ST_TEN_CODE,ST_MAIN_NAME," +
				"ST_WORK_URL,ST_EXT3 from selm_item si where si.ST_PARENT_ID = ? ";
		if (con == null) {
			rs = SQL.execute(sql, new Object[] { itemId });
		} else {
			rs = SQL.execute(con, sql, new Object[] { itemId });
		}
		List<SelmItem> list = new ArrayList<SelmItem>();
		while (rs.next()) {
			SelmItem item = new SelmItem();
			item.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
			item.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			item.setStTenCode(rs.getOriginalString("ST_TEN_CODE"));
			item.setStMainName(rs.getOriginalString("ST_MAIN_NAME"));
			item.setStWorkUrl(rs.getOriginalString("ST_WORK_URL"));
			item.setStExt3(rs.getOriginalString("ST_EXT3"));
			list.add(item);
		}
		return list;
	}
    
    private static void setProperties(SelmAssist info, RecordSet rs){
    	info.setStAssistId(rs.getOriginalString("ST_ASSIST_ID"));
    	info.setStAssistName(rs.getOriginalString("ST_ASSIST_NAME"));
    	info.setStAssistPhone(rs.getOriginalString("ST_ASSIST_PHONE"));
    	info.setStAssistIdcard(rs.getOriginalString("ST_ASSIST_IDCARD"));
    	info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
    	info.setDtCreate(rs.getTimestamp("DT_CREATE"));
    	info.setDtUpdate(rs.getTimestamp("DT_UPADTE"));
    	info.setStExt1(rs.getOriginalString("ST_EXT1"));
    	info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }
}
