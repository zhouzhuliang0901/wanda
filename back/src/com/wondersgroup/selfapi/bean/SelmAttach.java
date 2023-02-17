package com.wondersgroup.selfapi.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ATTACH")
public class SelmAttach implements Serializable{
	
	// 附件表
	public static final String SELM_ATTACH = "SELM_ATTACH";
	
	// 附件ID
	public static final String ST_ATTACH_ID = "ST_ATTACH_ID";
	
	// 关联表名称
	public static final String ST_LINK_TABLE = "ST_LINK_TABLE";
	
	// 关联主键值
	public static final String ST_LINK_ID = "ST_LINK_ID";
	
	// 附件类型
	public static final String ST_ATTACH_TYPE = "ST_ATTACH_TYPE";
	
	// 文件名
	public static final String ST_FILENAME = "ST_FILENAME";
	
	// 文件大小
	public static final String ST_FILE_SIZE = "ST_FILE_SIZE";
	
	// 文本内容
	public static final String CL_CONTENT = "CL_CONTENT";
	
	// 文件内容
	public static final String BL_CONTENT = "BL_CONTENT";
	
	// 图片缩略图
	public static final String BL_SMALL_CONTENT = "BL_SMALL_CONTENT";
	
	// 文件类型
	public static final String ST_FILE_TYPE = "ST_FILE_TYPE";
	
	// 排序
	public static final String NM_ORDER = "NM_ORDER";
	
	// 创建时间
	public static final String DT_CREATE = "DT_CREATE";
	
	// 修改时间
	public static final String DT_UPDATE = "DT_UPDATE";
	
	// 扩展字段1
	public static final String ST_EXT1 = "ST_EXT1";
	
	// 扩展字段2
	public static final String ST_EXT2 = "";
	
	/**
	 * 附件ID
	 */
    @Id
    @Column(name = "ST_ATTACH_ID")
	private String stAttachid;
    
    /**
     * 关联表名称
     */
    @Column(name = "ST_LINK_TABLE")
    private String stLinkTable;
    
    /**
     * 关联主键值
     */
    @Column(name = "ST_LINK_ID")
    private String stLinkId;
    
    @Column(name = "ST_ATTACH_TYPE")
    private String stAttachType;
    
    @Column(name = "ST_FILENAME")
    private String stFileName;
    
    @Column(name = "ST_FILE_SIZE")
    private String stFileSize;
    
    @Column(name = "CL_CONTENT")
    private String clContent;
    
    @Column(name = "BL_CONTENT")
    private byte[] blContent;
    
    @Column(name = "BL_SMALL_CONTENT")
    private byte[] blSmallContent;
    
    @Column(name = "ST_FILE_TYPE")
    private String stFileType;
    
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    @Column(name = "ST_EXT2")
    private String stExt2;

	public String getStAttachid() {
		return stAttachid;
	}

	public void setStAttachid(String stAttachid) {
		this.stAttachid = stAttachid;
	}

	public String getStLinkId() {
		return stLinkId;
	}

	public void setStLinkId(String stLinkId) {
		this.stLinkId = stLinkId;
	}

	public String getStAttachType() {
		return stAttachType;
	}

	public void setStAttachType(String stAttachType) {
		this.stAttachType = stAttachType;
	}

	public String getStFileName() {
		return stFileName;
	}

	public void setStFileName(String stFileName) {
		this.stFileName = stFileName;
	}

	public String getStFileSize() {
		return stFileSize;
	}

	public void setStFileSize(String stFileSize) {
		this.stFileSize = stFileSize;
	}

	public String getClContent() {
		return clContent;
	}

	public void setClContent(String clContent) {
		this.clContent = clContent;
	}

	public byte[] getBlContent() {
		return blContent;
	}

	public void setBlContent(byte[] blContent) {
		this.blContent = blContent;
	}

	public byte[] getBlSmallContent() {
		return blSmallContent;
	}

	public void setBlSmallContent(byte[] blSmallContent) {
		this.blSmallContent = blSmallContent;
	}

	public String getStFileType() {
		return stFileType;
	}

	public void setStFileType(String stFileType) {
		this.stFileType = stFileType;
	}

	public BigDecimal getNmOrder() {
		return nmOrder;
	}

	public void setNmOrder(BigDecimal nmOrder) {
		this.nmOrder = nmOrder;
	}

	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Timestamp getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Timestamp dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getStExt1() {
		return stExt1;
	}

	public void setStExt1(String stExt1) {
		this.stExt1 = stExt1;
	}

	public String getStExt2() {
		return stExt2;
	}

	public void setStExt2(String stExt2) {
		this.stExt2 = stExt2;
	}

	public String getStLinkTable() {
		return stLinkTable;
	}

	public void setStLinkTable(String stLinkTable) {
		this.stLinkTable = stLinkTable;
	}
    
}
