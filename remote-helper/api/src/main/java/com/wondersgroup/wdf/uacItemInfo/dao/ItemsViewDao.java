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
 * 事项信息&业态信息关联dao层
 * @Author: whp
 * @Date: 2021/7/27 11:22
 */

@Repository
public class ItemsViewDao {

    private Connection con = null;

    public ItemsViewDao() {
    }

    public ItemsViewDao(Connection con) {
        this.con = con;
    }

    public PaginationArrayList<ItemsView> query(Conditions conds, String suffix,int pageSize, int currentPage) {
        RecordSet rs;
        String sql = "select s.* ,y.ST_ITEMS_ID,y.ST_ITEMS_CODE,y.ST_ITEMS_NAME,y.ST_DESC,y.NM_STATUS,y.ST_PC_URL,y.ST_APP_URL,y.ST_T_URL,y.DT_CREATE,y.DT_UPDATE from UAC_ITEM_INFO s JOIN UAC_ITEMS_LINK l on l.ST_ITEM_ID = s.ST_ITEM_ID join UAC_ITEMS y on l.ST_ITEMS_ID = y.ST_ITEMS_ID  ";
        if (con == null) {
            rs = SQL.execute(sql,pageSize,currentPage);
        } else {
            sql += "where" + conds.toString();
            rs = SQL.execute(sql,pageSize,currentPage);
        }
        PaginationArrayList<ItemsView> pal = new PaginationArrayList<ItemsView>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
//        ArrayList<ItemsView> list = new ArrayList<>();
        while (rs.next()) {
            ItemsView info = new ItemsView();
            setProperties(info, rs);
            pal.add(info);
        }
//        return (PaginationArrayList<ItemsView>) list;
        return pal;
    }

    public List<ItemsView> query(Conditions conds, String suffix) {
        RecordSet rs;
        String sql = "SELECT s.ST_ITEM_ID,s.ST_ITEM_CODE,s.ST_ITEM_NAME,s.ST_INNER_NO,s.ST_INHERIT_ID,s.ST_AREA_ID,s.ST_AREA_CODE,s.ST_ORGAN_ID,s.ST_DEPART_CODE,s.ST_DEPART_NAME,s.NM_ORDER,s.ST_PARENT_ID,s.NM_SMS,s.NM_ALL_ONLINE,s.NM_INHERIT_TYPE,s.ST_TYPE,s.ST_ECERT_CODE,s.ST_ECERT_NAME,s.NM_REMOVED,y.ST_ITEMS_ID,y.ST_ITEMS_CODE,y.ST_ITEMS_NAME,y.ST_DESC,y.NM_STATUS,y.ST_PC_URL,y.ST_APP_URL,y.ST_T_URL,y.DT_CREATE,y.DT_UPDATE from UAC_ITEM_INFO s  JOIN UAC_ITEMS_LINK l on l.ST_ITEM_ID = s.ST_ITEM_ID join UAC_ITEMS y on l.ST_ITEMS_ID = y.ST_ITEMS_ID   ";
        if (con == null) {
            rs = SQL.execute(sql);
        } else {

            sql += "where" + conds.toString();
            rs = SQL.execute(sql);
        }
        ArrayList<ItemsView> al = new ArrayList<ItemsView>();
        while (rs.next()) {
            ItemsView info = new ItemsView();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }


    public static void setProperties(ItemsView info, RecordSet rs){
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
        info.setStItemsId(rs.getOriginalString("ST_ITEMS_ID"));
        info.setStItemsCode(rs.getOriginalString("ST_ITEMS_CODE"));
        info.setStItemsName(rs.getOriginalString("ST_ITEMS_NAME"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setNmStatus(rs.getBigDecimal("NM_STATUS"));
        info.setStPcUrl(rs.getOriginalString("ST_PC_URL"));
        info.setStAppUrl(rs.getOriginalString("ST_APP_URL"));
        info.setStTUrl(rs.getOriginalString("ST_T_URL"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
    }

    public List<ItemsView> getBystItemId(String stItemId){
        RecordSet rs;
        String sql = "SELECT s.ST_ITEM_ID,s.ST_ITEM_CODE,s.ST_ITEM_NAME,s.ST_INNER_NO,s.ST_INHERIT_ID,s.ST_AREA_ID,s.ST_AREA_CODE,s.ST_ORGAN_ID,s.ST_DEPART_CODE,s.ST_DEPART_NAME,s.NM_ORDER,s.ST_PARENT_ID,s.NM_SMS,s.NM_ALL_ONLINE,s.NM_INHERIT_TYPE,s.ST_TYPE,s.ST_ECERT_CODE,s.ST_ECERT_NAME,s.NM_REMOVED,y.ST_ITEMS_ID,y.ST_ITEMS_CODE,y.ST_ITEMS_NAME,y.ST_DESC,y.NM_STATUS,y.ST_PC_URL,y.ST_APP_URL,y.ST_T_URL,y.DT_CREATE,y.DT_UPDATE from UAC_ITEM_INFO s  JOIN UAC_ITEMS_LINK l on l.ST_ITEM_ID = s.ST_ITEM_ID join UAC_ITEMS y on l.ST_ITEMS_ID = y.ST_ITEMS_ID where s.ST_ITEM_ID like " + "'" + stItemId + "'";
        rs = SQL.execute(sql);
        ArrayList<ItemsView> al = new ArrayList<>();
        while (rs.next()) {
            ItemsView info = new ItemsView();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }

    public List<ItemsView> getBystItemsId(String stItemsId){
        RecordSet rs;
        String sql = "SELECT s.ST_ITEM_ID,s.ST_ITEM_CODE,s.ST_ITEM_NAME,s.ST_INNER_NO,s.ST_INHERIT_ID,s.ST_AREA_ID,s.ST_AREA_CODE,s.ST_ORGAN_ID,s.ST_DEPART_CODE,s.ST_DEPART_NAME,s.NM_ORDER,s.ST_PARENT_ID,s.NM_SMS,s.NM_ALL_ONLINE,s.NM_INHERIT_TYPE,s.ST_TYPE,s.ST_ECERT_CODE,s.ST_ECERT_NAME,s.NM_REMOVED,y.ST_ITEMS_ID,y.ST_ITEMS_CODE,y.ST_ITEMS_NAME,y.ST_DESC,y.NM_STATUS,y.ST_PC_URL,y.ST_APP_URL,y.ST_T_URL,y.DT_CREATE,y.DT_UPDATE from UAC_ITEM_INFO s  JOIN UAC_ITEMS_LINK l on l.ST_ITEM_ID = s.ST_ITEM_ID join UAC_ITEMS y on l.ST_ITEMS_ID = y.ST_ITEMS_ID where y.ST_ITEMS_ID like " + "'" + stItemsId + "'";
        rs = SQL.execute(sql);
        ArrayList<ItemsView> al = new ArrayList<>();
        while (rs.next()) {
            ItemsView info = new ItemsView();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
}
