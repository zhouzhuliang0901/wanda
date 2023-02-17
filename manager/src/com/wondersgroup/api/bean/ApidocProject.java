package com.wondersgroup.api.bean;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 项目
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APIDOC_PROJECT")
public class ApidocProject implements Serializable {
    
    /**
     * 项目
     */
    public static final String APIDOC_PROJECT = "APIDOC_PROJECT";
    
    /**
     * 项目ID
     */
    public static final String ST_PROJECT_ID = "ST_PROJECT_ID";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 项目名称
     */
    public static final String ST_PROJECT_NAME = "ST_PROJECT_NAME";
    
    /**
     * 排序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 备注
     */
    public static final String ST_REMARK = "ST_REMARK";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public ApidocProject() {
    }
    
    /**
     * 项目ID
     */
    @Id
    @Column(name = "ST_PROJECT_ID")
    private String stProjectId;
    
    /**
     * 用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 项目名称
     */
    @Column(name = "ST_PROJECT_NAME")
    private String stProjectName;
    
    /**
     * 排序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 备注
     */
    @Column(name = "ST_REMARK")
    private String stRemark;
    
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
     * 项目ID
     */
    public String getStProjectId() {
        return this.stProjectId;
    }
    
    /**
     * 项目ID
     */
    public String stProjectId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stProjectId);
    }

    /**
     * 项目ID
     */
    public void setStProjectId(String stProjectId) {
        stProjectId = StringUtil.substringBySize(stProjectId, 50, "GB18030");
        this.stProjectId = stProjectId;
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
     * 项目名称
     */
    public String getStProjectName() {
        return this.stProjectName;
    }
    
    /**
     * 项目名称
     */
    public String stProjectName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stProjectName);
    }

    /**
     * 项目名称
     */
    public void setStProjectName(String stProjectName) {
        stProjectName = StringUtil.substringBySize(stProjectName, 50, "GB18030");
        this.stProjectName = stProjectName;
    }

	/**
     * 排序号
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序号
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
     * 排序号
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
     * 备注
     */
    public String getStRemark() {
        return this.stRemark;
    }
    
    /**
     * 备注
     */
    public String stRemark2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRemark);
    }

    /**
     * 备注
     */
    public void setStRemark(String stRemark) {
        stRemark = StringUtil.substringBySize(stRemark, 100, "GB18030");
        this.stRemark = stRemark;
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