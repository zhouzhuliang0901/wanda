package com.wondersgroup.wdf.dao;

import org.springframework.stereotype.Repository;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Connection;

@Repository
public class ItemsLinkItemTwoDao {

    private Connection con = null;

    public ItemsLinkItemTwoDao() {
    }

    public ItemsLinkItemTwoDao(Connection con) {
        this.con = con;
    }

    public PaginationArrayList<ItemsLinkItemTwo> query(Conditions conds, int pageSize, int currentPage) {
        RecordSet rs;
        String content = "uii.*,ui.ST_ITEMS_ID,ui.ST_TYPE as ST_TYPE_UI,ui.ST_ITEMS_NAME,ui.ST_DESC";
        String table = "UAC_ITEMS_LINK uil JOIN UAC_ITEM_INFO uii ON uii.ST_ITEM_ID= uil.ST_ITEM_ID JOIN UAC_ITEMS ui ON ui.ST_ITEMS_ID= uil.ST_ITEMS_ID";
        String suffix = " ";
        if(con == null){
            rs = SQL.query(table,content,conds, suffix,pageSize,currentPage);
        }else{
            rs = SQL.query(con,table,content,conds, suffix,pageSize,currentPage);
        }

        PaginationArrayList<ItemsLinkItemTwo> pal = new PaginationArrayList<ItemsLinkItemTwo>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            ItemsLinkItemTwo info = new ItemsLinkItemTwo();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public static void setProperties(ItemsLinkItemTwo info, RecordSet rs){
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
        info.setStTypeUi(rs.getOriginalString("ST_TYPE_UI"));
    }

    public PaginationArrayList<ItemsLinkItemTwo> queryByStItemId(String stItemId,int pageSize, int currentPage) {
        RecordSet rs;
        String sql = "SELECT uii.*,ui.ST_ITEMS_ID,ui.ST_TYPE as ST_TYPE_UI,ui.ST_ITEMS_NAME,ui.ST_DESC FROM UAC_ITEMS_LINK uil JOIN UAC_ITEM_INFO uii ON uii.ST_ITEM_ID= uil.ST_ITEM_ID JOIN UAC_ITEMS ui ON ui.ST_ITEMS_ID= uil.ST_ITEMS_ID WHERE uii.ST_ITEM_ID = '"+stItemId+"'";
        rs = SQL.execute(sql,pageSize,currentPage);
        PaginationArrayList<ItemsLinkItemTwo> pal = new PaginationArrayList<ItemsLinkItemTwo>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            ItemsLinkItemTwo info = new ItemsLinkItemTwo();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public PaginationArrayList<ItemsLinkItemTwo> queryByStItemsId(String stItemsId,int pageSize, int currentPage) {
        RecordSet rs;
        String sql = "SELECT uii.*,ui.ST_ITEMS_ID,ui.ST_TYPE as ST_TYPE_UI,ui.ST_ITEMS_NAME,ui.ST_DESC FROM UAC_ITEMS_LINK uil JOIN UAC_ITEM_INFO uii ON uii.ST_ITEM_ID= uil.ST_ITEM_ID JOIN UAC_ITEMS ui ON ui.ST_ITEMS_ID= uil.ST_ITEMS_ID WHERE ui.ST_ITEMS_ID = '"+stItemsId+"'";
        rs = SQL.execute(sql,pageSize,currentPage);
        PaginationArrayList<ItemsLinkItemTwo> pal = new PaginationArrayList<ItemsLinkItemTwo>(rs.TOTAL_RECORD_COUNT,rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            ItemsLinkItemTwo info = new ItemsLinkItemTwo();
            setProperties(info, rs);
            pal.add(info);
        }


        return pal;
    }


}
