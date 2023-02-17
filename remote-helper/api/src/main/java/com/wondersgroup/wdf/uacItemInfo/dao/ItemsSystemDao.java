package com.wondersgroup.wdf.uacItemInfo.dao;

import org.springframework.stereotype.Repository;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 事项信息&系统信息关联dao层
 * @Author: whp
 * @Date: 2021/7/28 9:26
 */
@Repository
public class ItemsSystemDao {

    private Connection con = null;

    public ItemsSystemDao(){
    }

    public ItemsSystemDao(Connection con){
        this.con = con;
    }

    public PaginationArrayList<ItemsSystem> query(Conditions conds, String suffix,int pageSize, int currentPage) {
        RecordSet rs;
        String sql = "SELECT s.*,w.ST_WEB_SYSTEM_ID,w.ST_SYSTEM_NAME,w.ST_SYSTEM_URL,w.ST_MORGAN, w.ST_CONTACTOR, w.ST_TEL, w.ST_STATUS, w.DT_CREATE, w.DT_UPDATE, w.ST_DESC FROM UAC_ITEM_INFO s JOIN UAC_ITEM_SYSTEM i ON i.ST_ITEM_ID = s.ST_ITEM_ID JOIN UAC_WEB_SYSTEM w ON i.ST_WEB_SYSTEM_ID = w.ST_WEB_SYSTEM_ID  ";
        if (con == null) {
            rs = SQL.execute(sql,pageSize,currentPage);
        } else {
            sql += "where" + conds.toString();
            rs = SQL.execute(sql,pageSize,currentPage);
        }
        PaginationArrayList<ItemsSystem> pal = new PaginationArrayList<ItemsSystem>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            ItemsSystem info = new ItemsSystem();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<ItemsSystem> query(Conditions conds, String suffix) {
        RecordSet rs;
        String sql = "SELECT s.*,w.ST_WEB_SYSTEM_ID,w.ST_SYSTEM_NAME,w.ST_SYSTEM_URL,w.ST_MORGAN, w.ST_CONTACTOR, w.ST_TEL, w.ST_STATUS, w.DT_CREATE, w.DT_UPDATE, w.ST_DESC FROM UAC_ITEM_INFO s JOIN UAC_ITEM_SYSTEM i ON i.ST_ITEM_ID = s.ST_ITEM_ID JOIN UAC_WEB_SYSTEM w ON i.ST_WEB_SYSTEM_ID = w.ST_WEB_SYSTEM_ID  ";
        if (con == null) {
            rs = SQL.execute(sql);
        } else {

            sql += "where" + conds.toString();
            rs = SQL.execute(sql);
        }
        ArrayList<ItemsSystem> al = new ArrayList<ItemsSystem>();
        while (rs.next()) {
            ItemsSystem info = new ItemsSystem();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }

    public static void setProperties(ItemsSystem info, RecordSet rs){
        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
        info.setStItemCode(rs.getOriginalString("ST_ITEM_CODE"));
        info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
        info.setStInnerNo(rs.getOriginalString("ST_INNER_NO"));
        info.setStInheritId(rs.getOriginalString("ST_INHERIT_ID"));
        info.setStAreaId(rs.getOriginalString("ST_AREA_ID"));
        info.setStAreaCode(rs.getOriginalString("ST_AREA_CODE"));
        info.setStOrganId(rs.getOriginalString("ST_ORGAN_ID"));
        info.setStDepartCode(rs.getOriginalString("ST_DEPART_CODE"));
        info.setStDepartName(rs.getOriginalString("ST_DEPART_NAME"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setStParentId(rs.getOriginalString("ST_PARENT_ID"));
        info.setNmSms(rs.getBigDecimal("NM_SMS"));
        info.setNmAllOnline(rs.getBigDecimal("NM_ALL_ONLINE"));
        info.setNmInheritType(rs.getBigDecimal("NM_INHERIT_TYPE"));
        info.setStType(rs.getOriginalString("ST_TYPE"));
        info.setStEcertCode(rs.getOriginalString("ST_ECERT_CODE"));
        info.setStEcertName(rs.getOriginalString("ST_ECERT_NAME"));
        info.setNmRemoved(rs.getBigDecimal("NM_REMOVED"));
        info.setStWebSystemId(rs.getOriginalString("ST_WEB_SYSTEM_ID"));
        info.setStSystemName(rs.getOriginalString("ST_SYSTEM_NAME"));
        info.setStSystemUrl(rs.getOriginalString("ST_SYSTEM_URL"));
        info.setStMorgan(rs.getOriginalString("ST_MORGAN"));
        info.setStContactor(rs.getOriginalString("ST_CONTACTOR"));
        info.setStTel(rs.getOriginalString("ST_TEL"));
        info.setStStatus(rs.getOriginalString("ST_STATUS"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
    }

    public List<ItemsSystem> getBystItemId(String stItemId){
        RecordSet rs;
        String sql = "SELECT s.*,w.ST_WEB_SYSTEM_ID,w.ST_SYSTEM_NAME,w.ST_SYSTEM_URL,w.ST_MORGAN, w.ST_CONTACTOR, w.ST_TEL, w.ST_STATUS, w.DT_CREATE, w.DT_UPDATE, w.ST_DESC FROM UAC_ITEM_INFO s JOIN UAC_ITEM_SYSTEM i ON i.ST_ITEM_ID = s.ST_ITEM_ID JOIN UAC_WEB_SYSTEM w ON i.ST_WEB_SYSTEM_ID = w.ST_WEB_SYSTEM_ID where s.ST_ITEM_ID like " + "'" + stItemId + "'";
        rs = SQL.execute(sql);
        ArrayList<ItemsSystem> al = new ArrayList<>();
        while (rs.next()) {
            ItemsSystem info = new ItemsSystem();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }

    public List<ItemsSystem> getBystWebSystemId(String stWebSystemId){
        RecordSet rs;
        String sql = "SELECT s.*,w.ST_WEB_SYSTEM_ID,w.ST_SYSTEM_NAME,w.ST_SYSTEM_URL,w.ST_MORGAN, w.ST_CONTACTOR, w.ST_TEL, w.ST_STATUS, w.DT_CREATE, w.DT_UPDATE, w.ST_DESC FROM UAC_ITEM_INFO s JOIN UAC_ITEM_SYSTEM i ON i.ST_ITEM_ID = s.ST_ITEM_ID JOIN UAC_WEB_SYSTEM w ON i.ST_WEB_SYSTEM_ID = w.ST_WEB_SYSTEM_ID where w.ST_WEB_SYSTEM_ID like " + "'" + stWebSystemId + "'";
        rs = SQL.execute(sql);
        ArrayList<ItemsSystem> al = new ArrayList<>();
        while (rs.next()) {
            ItemsSystem info = new ItemsSystem();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
}
