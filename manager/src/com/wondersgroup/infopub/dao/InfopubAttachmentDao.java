package com.wondersgroup.infopub.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.infopub.bean.InfopubAttachment;

/**
 * 附件表
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubAttachmentDao {

    private Connection con = null;

    public InfopubAttachmentDao() {
    }

    public InfopubAttachmentDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubAttachment info) {
        String sql = "insert into INFOPUB_ATTACHMENT(ST_ATTACH_ID, ST_LINK_TABLE, ST_LINK_ID, ST_ATTACH_TYPE, ST_FILENAME, ST_FILE_SIZE, ST_FILE_TYPE, DT_CREATE, DT_UPDATE, CL_CONTENT,BL_CONTENT) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        Object[] obj = {
            info.getStAttachId(),
            info.getStLinkTable(),
            info.getStLinkId(),
            info.getStAttachType(),
            info.getStFilename(),
            info.getStFileSize(),
            info.getStFileType(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getClContent(),
            info.getBlContent()
        };
        System.out.println(obj);
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }
    
    public void addModify(InfopubAttachment info) {
        String sql = "insert into INFOPUB_ATTACHMENT(ST_ATTACH_ID, ST_LINK_TABLE, ST_LINK_ID, ST_ATTACH_TYPE, ST_FILENAME, ST_FILE_SIZE, ST_FILE_TYPE, DT_CREATE, DT_UPDATE, CL_CONTENT) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStAttachId(),
            info.getStLinkTable(),
            info.getStLinkId(),
            info.getStAttachType(),
            info.getStFilename(),
            info.getStFileSize(),
            info.getStFileType(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getClContent(),
            //info.getBlContent()
        };
        System.out.println(obj);
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubAttachment info) {
        String sql = "update INFOPUB_ATTACHMENT set ST_LINK_TABLE = ?, ST_LINK_ID = ?, ST_ATTACH_TYPE = ?, ST_FILENAME = ?, ST_FILE_SIZE = ?, ST_FILE_TYPE = ?, DT_CREATE = ?, DT_UPDATE = ?, CL_CONTENT = ?,BL_CONTENT=? where ST_ATTACH_ID = ?";
        Object[] obj = {
            info.getStLinkTable(),
            info.getStLinkId(),
            info.getStAttachType(),
            info.getStFilename(),
            info.getStFileSize(),
            info.getStFileType(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getClContent(),
            info.getBlContent(),
            info.getStAttachId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_ATTACHMENT set ";
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
    
    public int delete(Conditions conds) {
        String sql = "delete from INFOPUB_ATTACHMENT";
        String subsql = conds != null ? conds.toString() : "";
        if ("".equals(subsql)) {
            if (con == null) {
                return SQL.execute(sql).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql).TOTAL_RECORD_COUNT;
            }
        } else {
            sql += " where " + subsql;
            if (con == null) {
                return SQL.execute(sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;
            }
        }
    }

	public void delete(String[] stAttachId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stAttachId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, stAttachId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }
	
	public void deleteLinkId(String[] linkId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < linkId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL, linkId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stAttachId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, stAttachId));
        delete(conds);
    }

    public PaginationArrayList<InfopubAttachment> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ATTACHMENT", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_ATTACHMENT", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubAttachment> pal = new PaginationArrayList<InfopubAttachment>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubAttachment info = new InfopubAttachment();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubAttachment> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ATTACHMENT", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ATTACHMENT", "*", conds, suffix);
        }
        ArrayList<InfopubAttachment> al = new ArrayList<InfopubAttachment>();
        while (rs.next()) {
            InfopubAttachment info = new InfopubAttachment();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubAttachment info, RecordSet rs){
        info.setStAttachId(rs.getOriginalString("ST_ATTACH_ID"));
        info.setStLinkTable(rs.getOriginalString("ST_LINK_TABLE"));
        info.setStLinkId(rs.getOriginalString("ST_LINK_ID"));
        info.setStAttachType(rs.getOriginalString("ST_ATTACH_TYPE"));
        info.setStFilename(rs.getOriginalString("ST_FILENAME"));
        info.setStFileSize(rs.getOriginalString("ST_FILE_SIZE"));
        info.setStFileType(rs.getOriginalString("ST_FILE_TYPE"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setClContent(rs.getOriginalString("CL_CONTENT"));
       // info.setBlContent(rs.getOriginalString("CL_CONTENT"));
    }

	public InfopubAttachment get(String stAttachId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, stAttachId));
        List<InfopubAttachment> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubAttachment getLinkId(String stLinkId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL, stLinkId));
        conds.add(new Condition("ST_LINK_TABLE", Condition.OT_EQUAL, "INFOPUB_DEVICE_TYPE"));
        List<InfopubAttachment> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

}
