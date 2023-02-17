package com.wondersgroup.sms.organ.bean;
		
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
 * 组织机构表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_ORGAN")
public class SmsOrgan implements Serializable {
    
    /**
     * 组织机构表
     */
    public static final String SMS_ORGAN = "SMS_ORGAN";
    
    /**
     * 组织机构ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 父ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * 机构代码
     */
    public static final String ST_ORGAN_CODE = "ST_ORGAN_CODE";
    
    /**
     * 机构名称
     */
    public static final String ST_ORGAN_NAME = "ST_ORGAN_NAME";
    
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
     * 组织描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SmsOrgan() {
    }
    
    /**
     * 组织机构ID
     */
    @Id
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 父ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
    /**
     * 机构代码
     */
    @Column(name = "ST_ORGAN_CODE")
    private String stOrganCode;
    
    /**
     * 机构名称
     */
    @Column(name = "ST_ORGAN_NAME")
    private String stOrganName;
    
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
     * 组织描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
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
     * 组织机构ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 组织机构ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 组织机构ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 父ID
     */
    public String getStParentId() {
        return this.stParentId;
    }
    
    /**
     * 父ID
     */
    public String stParentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentId);
    }

    /**
     * 父ID
     */
    public void setStParentId(String stParentId) {
        stParentId = StringUtil.substringBySize(stParentId, 50, "GB18030");
        this.stParentId = stParentId;
    }
    
	/**
     * 机构代码
     */
    public String getStOrganCode() {
        return this.stOrganCode;
    }
    
    /**
     * 机构代码
     */
    public String stOrganCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganCode);
    }

    /**
     * 机构代码
     */
    public void setStOrganCode(String stOrganCode) {
        stOrganCode = StringUtil.substringBySize(stOrganCode, 50, "GB18030");
        this.stOrganCode = stOrganCode;
    }
    
	/**
     * 机构名称
     */
    public String getStOrganName() {
        return this.stOrganName;
    }
    
    /**
     * 机构名称
     */
    public String stOrganName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganName);
    }

    /**
     * 机构名称
     */
    public void setStOrganName(String stOrganName) {
        stOrganName = StringUtil.substringBySize(stOrganName, 50, "GB18030");
        this.stOrganName = stOrganName;
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
     * 组织描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 组织描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 组织描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 50, "GB18030");
        this.stDesc = stDesc;
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