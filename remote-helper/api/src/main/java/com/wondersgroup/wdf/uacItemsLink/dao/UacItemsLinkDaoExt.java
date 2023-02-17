package com.wondersgroup.wdf.uacItemsLink.dao;

import org.springframework.stereotype.Repository;
import wfc.service.database.SQL;

import java.sql.Connection;

/**
 * @Author: whp
 * @Date: 2021/7/30 10:46
 */

@Repository
public class UacItemsLinkDaoExt extends UacItemsLinkDao{

    private Connection con = null;

    public UacItemsLinkDaoExt() {
    }

    public UacItemsLinkDaoExt(Connection con) {
        this.con = con;
    }

    public void addItems(String stItemsId,String...str_stItemId){
         String sql = "insert into UAC_ITEMS_LINK(ST_ITEMS_ID, ST_ITEM_ID, NM_ORDER) values (?, ?, ?)";
         UacItemsLink info = new UacItemsLink();
         info.setStItemsId(stItemsId);
         info.setNmOrder(null);
         for (int i = 0;i < str_stItemId.length;i++){
             info.setStItemId(str_stItemId[i]);
             Object[] obj = {
                     info.getStItemsId(),
                     info.getStItemId(),
                     info.getNmOrder()
             };
             if (con == null) {
                SQL.execute(sql, obj);
             } else {
                SQL.execute(con, sql, obj);
             }
         }
    }


}
