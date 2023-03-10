package com.wondersgroup.infopub.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.infopub.bean.InfopubAttachment;

/**
 * 附件表
 * 
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubAttachmentDaoExt extends InfopubAttachmentDao{

	private Connection con = null;

	public InfopubAttachmentDaoExt() {
	}

	public InfopubAttachmentDaoExt(Connection con) {
		this.con = con;
	}

	public void addWithFile(InfopubAttachment info) {
		String sql = "insert into INFOPUB_ATTACHMENT(ST_ATTACH_ID, ST_LINK_TABLE, ST_LINK_ID, ST_ATTACH_TYPE, ST_FILENAME, ST_FILE_SIZE, ST_FILE_TYPE, DT_CREATE, DT_UPDATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = { info.getStAttachId(), info.getStLinkTable(),
				info.getStLinkId(), info.getStAttachType(),
				info.getStFilename(), info.getStFileSize(),
				info.getStFileType(), info.getDtCreate(), info.getDtUpdate() };
		if (con == null) {
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}

		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, info
				.getStAttachId()));
		BlobHelper.setBlob("INFOPUB_ATTACHMENT", "BL_CONTENT", conds.toString(),
				info.getBlContent(), conds.getObjectArray());
	}
	
	/**
	 * 获取附件Id
	 * 
	 * @param linkId
	 * @param linkTable
	 * @return
	 */
    public List<String> getAttachIds(String linkId, String linkTable) {
    	String suffix = "ORDER BY DT_CREATE DESC";
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_LINK_TABLE", Condition.OT_EQUAL, linkTable));
		conds.add(new Condition("ST_LINK_ID", Condition.OT_EQUAL, linkId));
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_ATTACHMENT", "ST_ATTACH_ID", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ATTACHMENT", "ST_ATTACH_ID", conds, suffix);
        }
        ArrayList<String> al = new ArrayList<String>();
        while (rs.next()) {
        	String attachId = rs.getOriginalString("ST_ATTACH_ID");
            al.add(attachId);
        }
        return al;
    }
    
    public List<InfopubAttachment> queryWithoutFile(Conditions conds, String suffix) {
        RecordSet rs;
        String content = "ST_ATTACH_ID,ST_LINK_TABLE,ST_LINK_ID,ST_ATTACH_TYPE,ST_FILENAME,ST_FILE_SIZE,CL_CONTENT,ST_FILE_TYPE,DT_CREATE,DT_UPDATE";
        if (con == null) {
            rs = SQL.query("INFOPUB_ATTACHMENT", content, conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_ATTACHMENT", content, conds, suffix);
        }
        ArrayList<InfopubAttachment> al = new ArrayList<InfopubAttachment>();
        while (rs.next()) {
            InfopubAttachment info = new InfopubAttachment();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
	public InfopubAttachment getWithoutFile(String stAttachId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, stAttachId));
        List<InfopubAttachment> list = queryWithoutFile(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }

	public byte[] getAttachFildById(String stAttachId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, stAttachId));
		byte[] blContent = BlobHelper.getBlob("INFOPUB_ATTACHMENT", "BL_CONTENT",
				conds.toString(), conds.getObjectArray());

		return blContent;
	}
}
