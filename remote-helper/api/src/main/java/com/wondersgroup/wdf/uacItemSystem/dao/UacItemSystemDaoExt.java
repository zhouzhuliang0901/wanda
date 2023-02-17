package com.wondersgroup.wdf.uacItemSystem.dao;

import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLink;
import org.springframework.stereotype.Repository;
import wfc.service.database.SQL;

import java.sql.Connection;

/**
 * @Author: whp
 * @Date: 2021/7/30 14:41
 */

@Repository
public class UacItemSystemDaoExt extends UacItemSystemDao{

    private Connection con = null;

    public UacItemSystemDaoExt() {
    }

    public UacItemSystemDaoExt(Connection con) {
        this.con = con;
    }

    public void addWeb(String stItemId,String...str_stWebSystemId){
        String sql = "insert into UAC_ITEM_SYSTEM(ST_ITEM_ID, ST_WEB_SYSTEM_ID, NM_ORDER) values (?, ?, ?)";
        UacItemSystem info = new UacItemSystem();
        info.setStItemId(stItemId);
        info.setNmOrder(null);
        for (int i = 0;i < str_stWebSystemId.length;i++){
            info.setStWebSystemId(str_stWebSystemId[i]);
            Object[] obj = {
                    info.getStItemId(),
                    info.getStWebSystemId(),
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
