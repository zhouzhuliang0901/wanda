package com.wondersgroup.wdf.dao;

import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 综合材料附件
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_ATTACH")
public class UacUapplyAttach implements Serializable {
    
    /**
     * 综合材料附件
     */
    public static final String UAC_UAPPLY_ATTACH = "UAC_UAPPLY_ATTACH";
    
    /**
     * 附件ID
     */
    public static final String ST_ATTACH_ID = "ST_ATTACH_ID";
    
    /**
     * 关联表名称
     */
    public static final String ST_LINK_TABLE = "ST_LINK_TABLE";
    
    /**
     * 关联主键值
     */
    public static final String ST_LINK_ID = "ST_LINK_ID";
    
    /**
     * 附件类型
     */
    public static final String ST_ATTACH_TYPE = "ST_ATTACH_TYPE";
    
    /**
     * 文件名
     */
    public static final String ST_FILENAME = "ST_FILENAME";
    
    /**
     * 文件大小
     */
    public static final String ST_FILE_SIZE = "ST_FILE_SIZE";
    
    /**
     * 文本内容
     */
    public static final String CL_CONTENT = "CL_CONTENT";
    
    /**
     * 文件内容
     */
    public static final String BL_CONTENT = "BL_CONTENT";
    
    /**
     * 图片缩略图
     */
    public static final String BL_SMALL_CONTENT = "BL_SMALL_CONTENT";
    
    /**
     * 文件类型
     */
    public static final String ST_FILE_TYPE = "ST_FILE_TYPE";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 修改时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public UacUapplyAttach() {
    }
    
    /**
     * 附件ID
     */
    @Id
    @Column(name = "ST_ATTACH_ID")
    private String stAttachId;
    
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
    
    /**
     * 附件类型
     */
    @Column(name = "ST_ATTACH_TYPE")
    private String stAttachType;
    
    /**
     * 文件名
     */
    @Column(name = "ST_FILENAME")
    private String stFilename;
    
    /**
     * 文件大小
     */
    @Column(name = "ST_FILE_SIZE")
    private String stFileSize;
    
    /**
     * 文本内容
     */
    @Lob
    @Column(name = "CL_CONTENT")
    private Clob clContent;
    
    /**
     * 文件内容
     */
    @Lob
    @Column(name = "BL_CONTENT")
    private byte[] blContent;
    
    /**
     * 图片缩略图
     */
    @Lob
    @Column(name = "BL_SMALL_CONTENT")
    private byte[] blSmallContent;
    
    /**
     * 文件类型
     */
    @Column(name = "ST_FILE_TYPE")
    private String stFileType;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 修改时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 扩展字段1
     */
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    /**
     * 扩展字段2
     */
    @Column(name = "ST_EXT2")
    private String stExt2;
    
	/**
     * 附件ID
     */
    public String getStAttachId() {
        return this.stAttachId;
    }
    
    /**
     * 附件ID
     */
    public String stAttachId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId);
    }

    /**
     * 附件ID
     */
    public void setStAttachId(String stAttachId) {
        stAttachId = StringUtil.substringBySize(stAttachId, 50, "GB18030");
        this.stAttachId = stAttachId;
    }
    
	/**
     * 关联表名称
     */
    public String getStLinkTable() {
        return this.stLinkTable;
    }
    
    /**
     * 关联表名称
     */
    public String stLinkTable2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLinkTable);
    }

    /**
     * 关联表名称
     */
    public void setStLinkTable(String stLinkTable) {
        stLinkTable = StringUtil.substringBySize(stLinkTable, 50, "GB18030");
        this.stLinkTable = stLinkTable;
    }
    
	/**
     * 关联主键值
     */
    public String getStLinkId() {
        return this.stLinkId;
    }
    
    /**
     * 关联主键值
     */
    public String stLinkId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLinkId);
    }

    /**
     * 关联主键值
     */
    public void setStLinkId(String stLinkId) {
        stLinkId = StringUtil.substringBySize(stLinkId, 50, "GB18030");
        this.stLinkId = stLinkId;
    }
    
	/**
     * 附件类型
     */
    public String getStAttachType() {
        return this.stAttachType;
    }
    
    /**
     * 附件类型
     */
    public String stAttachType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachType);
    }

    /**
     * 附件类型
     */
    public void setStAttachType(String stAttachType) {
        stAttachType = StringUtil.substringBySize(stAttachType, 50, "GB18030");
        this.stAttachType = stAttachType;
    }
    
	/**
     * 文件名
     */
    public String getStFilename() {
        return this.stFilename;
    }
    
    /**
     * 文件名
     */
    public String stFilename2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFilename);
    }

    /**
     * 文件名
     */
    public void setStFilename(String stFilename) {
        stFilename = StringUtil.substringBySize(stFilename, 100, "GB18030");
        this.stFilename = stFilename;
    }
    
	/**
     * 文件大小
     */
    public String getStFileSize() {
        return this.stFileSize;
    }
    
    /**
     * 文件大小
     */
    public String stFileSize2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFileSize);
    }

    /**
     * 文件大小
     */
    public void setStFileSize(String stFileSize) {
        stFileSize = StringUtil.substringBySize(stFileSize, 50, "GB18030");
        this.stFileSize = stFileSize;
    }

    /**
     * 文件内容 clContent
     * @return
     */
    public Clob getClContent(){
        return this.clContent;
    }

    /**
     * 文件内容 clContent
     */
    public void setClContent(Clob clContent){
        this.clContent = clContent;
    }
    
	/**
     * 文件内容
     */
    public byte[] getBlContent() {
        return this.blContent;
    }
    
    /**
     * 文件内容
     */
    public byte[] blContent2Html() {
        return this.blContent;
    }

    /**
     * 文件内容
     */
    public void setBlContent(byte[] blContent) {
        this.blContent = blContent;
    }
    
	/**
     * 图片缩略图
     */
    public byte[] getBlSmallContent() {
        return this.blSmallContent;
    }
    
    /**
     * 图片缩略图
     */
    public byte[] blSmallContent2Html() {
        return this.blSmallContent;
    }

    /**
     * 图片缩略图
     */
    public void setBlSmallContent(byte[] blSmallContent) {
        this.blSmallContent = blSmallContent;
    }
    
	/**
     * 文件类型
     */
    public String getStFileType() {
        return this.stFileType;
    }
    
    /**
     * 文件类型
     */
    public String stFileType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFileType);
    }

    /**
     * 文件类型
     */
    public void setStFileType(String stFileType) {
        stFileType = StringUtil.substringBySize(stFileType, 10, "GB18030");
        this.stFileType = stFileType;
    }

	/**
     * 排序
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序
     */
    public String nmOrder2Html(int precision) {
        if (this.nmOrder == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOrder);
        }
    }

    /**
     * 排序
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
    }

	/**
     * 创建时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtCreate() {
        return this.dtCreate;
    }
    
    /**
     * 创建时间
     */
    public String dtCreate2Html(String pattern) {
        if (this.dtCreate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtCreate);
        }
    }

    /**
     * 创建时间
     */
    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
    }

	/**
     * 修改时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 修改时间
     */
    public String dtUpdate2Html(String pattern) {
        if (this.dtUpdate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtUpdate);
        }
    }

    /**
     * 修改时间
     */
    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
    
	/**
     * 扩展字段1
     */
    public String getStExt1() {
        return this.stExt1;
    }
    
    /**
     * 扩展字段1
     */
    public String stExt12Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt1);
    }

    /**
     * 扩展字段1
     */
    public void setStExt1(String stExt1) {
        stExt1 = StringUtil.substringBySize(stExt1, 50, "GB18030");
        this.stExt1 = stExt1;
    }
    
	/**
     * 扩展字段2
     */
    public String getStExt2() {
        return this.stExt2;
    }
    
    /**
     * 扩展字段2
     */
    public String stExt22Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt2);
    }

    /**
     * 扩展字段2
     */
    public void setStExt2(String stExt2) {
        stExt2 = StringUtil.substringBySize(stExt2, 50, "GB18030");
        this.stExt2 = stExt2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * base64字节码
     */
    @Transient
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}