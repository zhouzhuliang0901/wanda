package com.wondersgroup.wdf.dao;

import org.springframework.stereotype.Repository;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UacAreaDao {

    private Connection con = null;

    public List<String> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("UAC_AREA", "ST_AREA_NAME", conds, suffix);
        } else {
            rs = SQL.query(con, "UAC_AREA", "ST_AREA_NAME", conds, suffix);
        }
        ArrayList<String> al = new ArrayList<String>();
        while (rs.next()) {
            String info = new String();
            info = rs.getOriginalString("ST_AREA_NAME");
            al.add(info);
        }
        return al;
    }



}
