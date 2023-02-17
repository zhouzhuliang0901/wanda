package com.wondersgroup.wdf.dao;

import org.springframework.stereotype.Repository;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Clob;
import java.sql.Connection;
import java.util.List;


/**
 * @Author: whp
 * @Date: 2021/09/10/14:47
 * @Description:
 */
@Repository
public class UacUapplyAttachDaoExt extends UacUapplyAttachDao {

    private Connection con = null;

    public UacUapplyAttachDaoExt(){
    }

    public void add(UacUapplyAttach info){
        String sql = "insert into UAC_UAPPLY_ATTACH(ST_ATTACH_ID, ST_LINK_TABLE, ST_LINK_ID, ST_ATTACH_TYPE, ST_FILENAME, ST_FILE_SIZE, CL_CONTENT,BL_CONTENT,ST_FILE_TYPE, NM_ORDER, DT_CREATE, DT_UPDATE, ST_EXT1, ST_EXT2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
                info.getStAttachId(),
                info.getStLinkTable(),
                info.getStLinkId(),
                info.getStAttachType(),
                info.getStFilename(),
                info.getStFileSize(),
                info.getClContent(),
                info.getBlContent(),
                info.getStFileType(),
                info.getNmOrder(),
                info.getDtCreate(),
                info.getDtUpdate(),
                info.getStExt1(),
                info.getStExt2()
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }


    public static void setProperties(UacUapplyAttach info, RecordSet rs){
        info.setStAttachId(rs.getOriginalString("ST_ATTACH_ID"));
        info.setStLinkTable(rs.getOriginalString("ST_LINK_TABLE"));
        info.setStLinkId(rs.getOriginalString("ST_LINK_ID"));
        info.setStAttachType(rs.getOriginalString("ST_ATTACH_TYPE"));
        info.setStFilename(rs.getOriginalString("ST_FILENAME"));
        info.setStFileSize(rs.getOriginalString("ST_FILE_SIZE"));
        info.setClContent((Clob) rs.getBigDecimal("CL_CONTENT"));
        info.setBlContent(rs.getOriginalString("BL_CONTENT").getBytes());
        info.setStFileType(rs.getOriginalString("ST_FILE_TYPE"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStExt1(rs.getOriginalString("ST_EXT1"));
        info.setStExt2(rs.getOriginalString("ST_EXT2"));
    }

    public List<UacUapplyAttach> getBystLinkId(String stLinkId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL, stLinkId));
        List<UacUapplyAttach> list = query(conds, null);
        return list;
    }
}
