package com.wondersgroup.selfapi.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;
import org.jeecgframework.poi.excel.annotation.Excel;

import reindeer.base.utils.StringUtil;


import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 预约黑名单
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NET_RESERVATION_BLACKLIST")
public class NetReservationBlacklist implements Serializable {
    
    /**
     * 预约黑名单
     */
    public static final String NET_RESERVATION_BLACKLIST = "NET_RESERVATION_BLACKLIST";
    
    /**
     * 黑名单ID
     */
    public static final String ST_BLACKLIST_ID = "ST_BLACKLIST_ID";
    
    /**
     * 用户账户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 用户姓名
     */
    public static final String ST_USER_NAME = "ST_USER_NAME";
    
    /**
     * 用户手机号
     */
    public static final String ST_MOBILE = "ST_MOBILE";
    
    /**
     * 用户证件类型
     */
    public static final String NM_IDENTITY_TYPE = "NM_IDENTITY_TYPE";
    
    /**
     * 用户证件号
     */
    public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";
    
    /**
     * 企业名称
     */
    public static final String ST_UNIT = "ST_UNIT";
    
    /**
     * 统一社会信用代码
     */
    public static final String ST_UNIFIED = "ST_UNIFIED";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
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
    
    public NetReservationBlacklist() {
    }
    
    /**
     * 黑名单ID
     */
    @Id
    @Column(name = "ST_BLACKLIST_ID")
    private String stBlacklistId;
    
    /**
     * 用户账户ID
     */
    @Excel(name = "用户账户ID",width = 15)
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 用户姓名
     */
    @Excel(name = "用户姓名",width = 15)
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 用户手机号
     */
    @Excel(name = "用户手机号",width = 15)
    @Column(name = "ST_MOBILE")
    private String stMobile;
    
    /**
     * 用户证件类型
     */
    @Excel(name = "用户证件类型",width = 15,replace = {"身份证_1","护照_2","军官证_3"})
    @Column(name = "NM_IDENTITY_TYPE")
    private BigDecimal nmIdentityType;
    
    /**
     * 用户证件号
     */
    @Excel(name = "用户证件号",width = 15)
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentityNo;
    
    /**
     * 企业名称
     */
    @Excel(name = "企业名称",width = 15)
    @Column(name = "ST_UNIT")
    private String stUnit;
    
    /**
     * 统一社会信用代码
     */
    @Excel(name = "统一社会信用代码",width = 15)
    @Column(name = "ST_UNIFIED")
    private String stUnified;
    
    /**
     * 状态
     */
    @Excel(name = "状态",width = 15,replace = {"不生效_0","生效_1"})
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 15)
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 修改时间
     */
    @Excel(name = "修改时间",width = 15)
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
     * 黑名单ID
     */
    public String getStBlacklistId() {
        return this.stBlacklistId;
    }
    
    /**
     * 黑名单ID
     */
    public String stBlacklistId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBlacklistId);
    }

    /**
     * 黑名单ID
     */
    public void setStBlacklistId(String stBlacklistId) {
        stBlacklistId = StringUtil.substringBySize(stBlacklistId, 50, "GB18030");
        this.stBlacklistId = stBlacklistId;
    }
    
	/**
     * 用户账户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 用户账户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 用户账户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }
    
	/**
     * 用户姓名
     */
    public String getStUserName() {
        return this.stUserName;
    }
    
    /**
     * 用户姓名
     */
    public String stUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserName);
    }

    /**
     * 用户姓名
     */
    public void setStUserName(String stUserName) {
        stUserName = StringUtil.substringBySize(stUserName, 50, "GB18030");
        this.stUserName = stUserName;
    }
    
	/**
     * 用户手机号
     */
    public String getStMobile() {
        return this.stMobile;
    }
    
    /**
     * 用户手机号
     */
    public String stMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMobile);
    }

    /**
     * 用户手机号
     */
    public void setStMobile(String stMobile) {
        stMobile = StringUtil.substringBySize(stMobile, 50, "GB18030");
        this.stMobile = stMobile;
    }

	/**
     * 用户证件类型
     */
    public BigDecimal getNmIdentityType() {
        return this.nmIdentityType;
    }
    
    /**
     * 用户证件类型
     */
    public String nmIdentityType2Html(int precision) {
        if (this.nmIdentityType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIdentityType);
        }
    }

    /**
     * 用户证件类型
     */
    public void setNmIdentityType(BigDecimal nmIdentityType) {
        this.nmIdentityType = nmIdentityType;
    }
    
	/**
     * 用户证件号
     */
    public String getStIdentityNo() {
        return this.stIdentityNo;
    }
    
    /**
     * 用户证件号
     */
    public String stIdentityNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIdentityNo);
    }

    /**
     * 用户证件号
     */
    public void setStIdentityNo(String stIdentityNo) {
        stIdentityNo = StringUtil.substringBySize(stIdentityNo, 50, "GB18030");
        this.stIdentityNo = stIdentityNo;
    }
    
	/**
     * 企业名称
     */
    public String getStUnit() {
        return this.stUnit;
    }
    
    /**
     * 企业名称
     */
    public String stUnit2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnit);
    }

    /**
     * 企业名称
     */
    public void setStUnit(String stUnit) {
        stUnit = StringUtil.substringBySize(stUnit, 50, "GB18030");
        this.stUnit = stUnit;
    }
    
	/**
     * 统一社会信用代码
     */
    public String getStUnified() {
        return this.stUnified;
    }
    
    /**
     * 统一社会信用代码
     */
    public String stUnified2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnified);
    }

    /**
     * 统一社会信用代码
     */
    public void setStUnified(String stUnified) {
        stUnified = StringUtil.substringBySize(stUnified, 50, "GB18030");
        this.stUnified = stUnified;
    }

	/**
     * 状态
     */
    public BigDecimal getNmStatus() {
        return this.nmStatus;
    }
    
    /**
     * 状态
     */
    public String nmStatus2Html(int precision) {
        if (this.nmStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmStatus);
        }
    }

    /**
     * 状态
     */
    public void setNmStatus(BigDecimal nmStatus) {
        this.nmStatus = nmStatus;
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