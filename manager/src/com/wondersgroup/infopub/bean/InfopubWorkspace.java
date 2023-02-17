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
 * 信息发布用户空间
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_WORKSPACE")
public class InfopubWorkspace implements Serializable {
    
    /**
     * 信息发布用户空间
     */
    public static final String INFOPUB_WORKSPACE = "INFOPUB_WORKSPACE";
    
    /**
     * 空间ID
     */
    public static final String ST_WORKSPACE_ID = "ST_WORKSPACE_ID";
    
    /**
     * 唯一代码
     */
    public static final String ST_WORKSPACE_CODE = "ST_WORKSPACE_CODE";
    
    /**
     * 空间名称
     */
    public static final String ST_WORKSPACE_NAME = "ST_WORKSPACE_NAME";
    
    /**
     * 空间总共大小
     */
    public static final String NM_TOTAL = "NM_TOTAL";
    
    /**
     * 已经使用
     */
    public static final String NM_USED = "NM_USED";
    
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
    
    public InfopubWorkspace() {
    }
    
    /**
     * 空间ID
     */
    @Id
    @Column(name = "ST_WORKSPACE_ID")
    private String stWorkspaceId;
    
    /**
     * 唯一代码
     */
    @Column(name = "ST_WORKSPACE_CODE")
    private String stWorkspaceCode;
    
    /**
     * 空间名称
     */
    @Column(name = "ST_WORKSPACE_NAME")
    private String stWorkspaceName;
    
    /**
     * 空间总共大小
     */
    @Column(name = "NM_TOTAL")
    private BigDecimal nmTotal;
    
    /**
     * 已经使用
     */
    @Column(name = "NM_USED")
    private BigDecimal nmUsed;
    
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
    
	/**
     * 唯一代码
     */
    public String getStWorkspaceCode() {
        return this.stWorkspaceCode;
    }
    
    /**
     * 唯一代码
     */
    public String stWorkspaceCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWorkspaceCode);
    }

    /**
     * 唯一代码
     */
    public void setStWorkspaceCode(String stWorkspaceCode) {
        stWorkspaceCode = StringUtil.substringBySize(stWorkspaceCode, 50, "GB18030");
        this.stWorkspaceCode = stWorkspaceCode;
    }
    
	/**
     * 空间名称
     */
    public String getStWorkspaceName() {
        return this.stWorkspaceName;
    }
    
    /**
     * 空间名称
     */
    public String stWorkspaceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWorkspaceName);
    }

    /**
     * 空间名称
     */
    public void setStWorkspaceName(String stWorkspaceName) {
        stWorkspaceName = StringUtil.substringBySize(stWorkspaceName, 50, "GB18030");
        this.stWorkspaceName = stWorkspaceName;
    }

	/**
     * 空间总共大小
     */
    public BigDecimal getNmTotal() {
        return this.nmTotal;
    }
    
    /**
     * 空间总共大小
     */
    public String nmTotal2Html(int precision) {
        if (this.nmTotal == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmTotal);
        }
    }

    /**
     * 空间总共大小
     */
    public void setNmTotal(BigDecimal nmTotal) {
        this.nmTotal = nmTotal;
    }

	/**
     * 已经使用
     */
    public BigDecimal getNmUsed() {
        return this.nmUsed;
    }
    
    /**
     * 已经使用
     */
    public String nmUsed2Html(int precision) {
        if (this.nmUsed == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmUsed);
        }
    }

    /**
     * 已经使用
     */
    public void setNmUsed(BigDecimal nmUsed) {
        this.nmUsed = nmUsed;
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

}