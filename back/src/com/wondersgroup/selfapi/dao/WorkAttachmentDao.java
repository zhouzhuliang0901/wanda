package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wondersgroup.selfapi.bean.WorkAttachment;

import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;



/**
 * 附件表
 */
@Repository
public class WorkAttachmentDao {
	 
	private Connection con = null;

	    public WorkAttachmentDao() {
	    }

	    public WorkAttachmentDao(Connection con) {
	        this.con = con;
	    }
	
	 public static void setProperties(WorkAttachment info, RecordSet rs){
	        info.setStAttachId(rs.getOriginalString("ST_ATTACH_ID"));
	        info.setStLinkTable(rs.getOriginalString("ST_LINK_TABLE"));
	        info.setStLinkId(rs.getOriginalString("ST_LINK_ID"));
	        info.setStAttachType(rs.getOriginalString("ST_ATTACH_TYPE"));
	        info.setStFilename(rs.getOriginalString("ST_FILENAME"));
	        info.setStFileSize(rs.getOriginalString("ST_FILE_SIZE"));
	        info.setStFileType(rs.getOriginalString("ST_FILE_TYPE"));
	        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
	        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
	    }

	public int update(Map<String, Object> map, Conditions conds) {
		String sql = "UPDATE WORK_ATTACHMENT SET ";
		List<Object> list = new ArrayList<Object>();
		int i = 0;
		for (String field : map.keySet()) {
            if (i++ > 0) {
                sql += ", ";
            }
            sql += field + " = ?";
            list.add(map.get(field));
        }
        String subsql = conds != null ? conds.toString() : "";
        if ("".equals(subsql)) {
            if (con == null) {
                return SQL.execute(sql).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql).TOTAL_RECORD_COUNT;
            }
        } else {
            sql += " where " + subsql;
            list.addAll(conds.getObjectList());
            if (con == null) {
                return SQL.execute(sql, list.toArray()).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql, list.toArray()).TOTAL_RECORD_COUNT;
            }
        }
	}

	public void add(WorkAttachment info) {
		 String sql = "insert into WORK_ATTACHMENT(ST_ATTACH_ID, ST_LINK_TABLE, ST_LINK_ID, ST_ATTACH_TYPE, ST_FILENAME, ST_FILE_SIZE, ST_FILE_TYPE, DT_CREATE, DT_UPDATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        Object[] obj = {
	            info.getStAttachId(),
	            info.getStLinkTable(),
	            info.getStLinkId(),
	            info.getStAttachType(),
	            info.getStFilename(),
	            info.getStFileSize(),
	            info.getStFileType(),
	            info.getDtCreate(),
	            info.getDtUpdate()        	
	        };
	        if (con == null) {
	            SQL.execute(sql, obj);
	        } else {
	            SQL.execute(con, sql, obj);
	        }

	}
}
