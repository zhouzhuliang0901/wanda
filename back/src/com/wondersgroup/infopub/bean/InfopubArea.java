package com.wondersgroup.infopub.bean;

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

import reindeer.base.utils.StringUtil;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;

/**
 * 区域表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_AREA")
public class InfopubArea implements Serializable {
    
    /**
     * 区域表
     */
    public static final String INFOPUB_AREA = "INFOPUB_AREA";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 父ID
     */
    public static final String ST_PARENT_AREA_ID = "ST_PARENT_AREA_ID";
    
    /**
     * 区域代码
     */
    public static final String ST_AREA_CODE = "ST_AREA_CODE";
    
    /**
     * 区域名称
     */
    public static final String ST_AREA_NAME = "ST_AREA_NAME";
    
    /**
     * 排序字段
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
     * 区域描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    
    public InfopubArea() {
    }
    
    /**
     * 区域ID
     */
    @Id
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 父ID
     */
    @Column(name = "ST_PARENT_AREA_ID")
    private String stParentAreaId;
    
    /**
     * 区域代码
     */
    @Column(name = "ST_AREA_CODE")
    private String stAreaCode;
    
    /**
     * 区域名称
     */
    @Column(name = "ST_AREA_NAME")
    private String stAreaName;
    
    /**
     * 排序字段
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
     * 区域描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    
	/**
     * 区域ID
     */
    public String getStAreaId() {
        return this.stAreaId;
    }
    
    /**
     * 区域ID
     */
    public String stAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaId);
    }

    /**
     * 区域ID
     */
    public void setStAreaId(String stAreaId) {
        stAreaId = StringUtil.substringBySize(stAreaId, 50, "GB18030");
        this.stAreaId = stAreaId;
    }
    
	/**
     * 父ID
     */
    public String getStParentAreaId() {
        return this.stParentAreaId;
    }
    
    /**
     * 父ID
     */
    public String stParentAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentAreaId);
    }

    /**
     * 父ID
     */
    public void setStParentAreaId(String stParentAreaId) {
        stParentAreaId = StringUtil.substringBySize(stParentAreaId, 50, "GB18030");
        this.stParentAreaId = stParentAreaId;
    }
    
	/**
     * 区域代码
     */
    public String getStAreaCode() {
        return this.stAreaCode;
    }
    
    /**
     * 区域代码
     */
    public String stAreaCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaCode);
    }

    /**
     * 区域代码
     */
    public void setStAreaCode(String stAreaCode) {
        stAreaCode = StringUtil.substringBySize(stAreaCode, 50, "GB18030");
        this.stAreaCode = stAreaCode;
    }
    
	/**
     * 区域名称
     */
    public String getStAreaName() {
        return this.stAreaName;
    }
    
    /**
     * 区域名称
     */
    public String stAreaName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaName);
    }

    /**
     * 区域名称
     */
    public void setStAreaName(String stAreaName) {
        stAreaName = StringUtil.substringBySize(stAreaName, 500, "GB18030");
        this.stAreaName = stAreaName;
    }

	/**
     * 排序字段
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序字段
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
     * 排序字段
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
     * 区域描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 区域描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 区域描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 50, "GB18030");
        this.stDesc = stDesc;
    }
    

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}