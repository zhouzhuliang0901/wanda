package com.wondersgroup.wdf.dao;
		
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
 * 区域办理点
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_AREA")
public class UacAreaTwo implements Serializable {
    
    /**
     * 区域办理点
     */
    public static final String UAC_AREA = "UAC_AREA";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 区域名称
     */
    public static final String ST_AREA_NAME = "ST_AREA_NAME";
    
    /**
     * 区域代码
     */
    public static final String ST_AREA_CODE = "ST_AREA_CODE";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 实际地址
     */
    public static final String ST_ADDRESS = "ST_ADDRESS";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 父ID
     */
    public static final String ST_PARENT_AREA_ID = "ST_PARENT_AREA_ID";
    
    /**
     * 区域组织标识
     */
    public static final String ST_ORG = "ST_ORG";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    public UacAreaTwo() {
    }
    
    /**
     * 区域ID
     */
    @Id
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 区域名称
     */
    @Column(name = "ST_AREA_NAME")
    private String stAreaName;
    
    /**
     * 区域代码
     */
    @Column(name = "ST_AREA_CODE")
    private String stAreaCode;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 实际地址
     */
    @Column(name = "ST_ADDRESS")
    private String stAddress;
    
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
     * 父ID
     */
    @Column(name = "ST_PARENT_AREA_ID")
    private String stParentAreaId;
    
    /**
     * 区域组织标识
     */
    @Column(name = "ST_ORG")
    private String stOrg;
    
    /**
     * 备注
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
        stAreaName = StringUtil.substringBySize(stAreaName, 50, "GB18030");
        this.stAreaName = stAreaName;
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
     * 实际地址
     */
    public String getStAddress() {
        return this.stAddress;
    }
    
    /**
     * 实际地址
     */
    public String stAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAddress);
    }

    /**
     * 实际地址
     */
    public void setStAddress(String stAddress) {
        stAddress = StringUtil.substringBySize(stAddress, 200, "GB18030");
        this.stAddress = stAddress;
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
     * 区域组织标识
     */
    public String getStOrg() {
        return this.stOrg;
    }
    
    /**
     * 区域组织标识
     */
    public String stOrg2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrg);
    }

    /**
     * 区域组织标识
     */
    public void setStOrg(String stOrg) {
        stOrg = StringUtil.substringBySize(stOrg, 50, "GB18030");
        this.stOrg = stOrg;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}