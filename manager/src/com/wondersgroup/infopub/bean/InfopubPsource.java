package com.wondersgroup.infopub.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;
import coral.base.util.StringUtil;
import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 发布源
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_PSOURCE")
public class InfopubPsource implements Serializable {
    
    /**
     * 发布源
     */
    public static final String INFOPUB_PSOURCE = "INFOPUB_PSOURCE";
    
    /**
     * 发布源ID
     */
    public static final String ST_PSOURCE_ID = "ST_PSOURCE_ID";
    
    /**
     * 发布源名称
     */
    public static final String ST_PSOURCE_NAME = "ST_PSOURCE_NAME";
    
    /**
     * 是否离线
     */
    public static final String NM_OFFLINE = "NM_OFFLINE";
    
    /**
     * 附件ID
     */
    public static final String ST_ATTACH_ID = "ST_ATTACH_ID";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 空间ID
     */
    public static final String ST_WORKSPACE_ID = "ST_WORKSPACE_ID";
    
    public InfopubPsource() {
    }
    
    /**
     * 发布源ID
     */
    @Id
    @Column(name = "ST_PSOURCE_ID")
    private String stPsourceId;
    
    /**
     * 发布源名称
     */
    @Column(name = "ST_PSOURCE_NAME")
    private String stPsourceName;
    
    /**
     * 是否离线
     */
    @Column(name = "NM_OFFLINE")
    private BigDecimal nmOffline;
    
    /**
     * 附件ID
     */
    @Column(name = "ST_ATTACH_ID")
    private String stAttachId;
    
    /**
     * 用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 更新时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 空间ID
     */
    @Column(name = "ST_WORKSPACE_ID")
    private String stWorkspaceId;
    
	/**
     * 发布源ID
     */
    public String getStPsourceId() {
        return this.stPsourceId;
    }
    
    /**
     * 发布源ID
     */
    public String stPsourceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPsourceId);
    }

    /**
     * 发布源ID
     */
    public void setStPsourceId(String stPsourceId) {
        stPsourceId = StringUtil.substringBySize(stPsourceId, 50, "GB18030");
        this.stPsourceId = stPsourceId;
    }
    
	/**
     * 发布源名称
     */
    public String getStPsourceName() {
        return this.stPsourceName;
    }
    
    /**
     * 发布源名称
     */
    public String stPsourceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPsourceName);
    }

    /**
     * 发布源名称
     */
    public void setStPsourceName(String stPsourceName) {
        stPsourceName = StringUtil.substringBySize(stPsourceName, 50, "GB18030");
        this.stPsourceName = stPsourceName;
    }

	/**
     * 是否离线
     */
    public BigDecimal getNmOffline() {
        return this.nmOffline;
    }
    
    /**
     * 是否离线
     */
    public String nmOffline2Html(int precision) {
        if (this.nmOffline == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOffline);
        }
    }

    /**
     * 是否离线
     */
    public void setNmOffline(BigDecimal nmOffline) {
        this.nmOffline = nmOffline;
    }
    
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
     * 用户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 用户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 用户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
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
     * 更新时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 更新时间
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
     * 更新时间
     */
    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
    
	/**
     * 备注
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 备注
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 备注
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }
    
	/**
     * 空间ID
     */
    public String getStWorkspaceId() {
        return this.stWorkspaceId;
    }
    
    /**
     * 空间ID
     */
    public String stWorkspaceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWorkspaceId);
    }

    /**
     * 空间ID
     */
    public void setStWorkspaceId(String stWorkspaceId) {
        stWorkspaceId = StringUtil.substringBySize(stWorkspaceId, 50, "GB18030");
        this.stWorkspaceId = stWorkspaceId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}