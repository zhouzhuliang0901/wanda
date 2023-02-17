package com.wondersgroup.selfapi.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;
import reindeer.base.utils.StringUtil;
import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 预约过期临时表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NET_RESERVATION_DATED_TEMP")
public class NetReservationDatedTemp implements Serializable {
    
    /**
     * 预约过期临时表
     */
    public static final String NET_RESERVATION_DATED_TEMP = "NET_RESERVATION_DATED_TEMP";
    
    /**
     * 临时ID
     */
    public static final String ST_TEMP_ID = "ST_TEMP_ID";
    
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
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 修改时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 次数
     */
    public static final String NM_COUNT = "NM_COUNT";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public NetReservationDatedTemp() {
    }
    
    /**
     * 临时ID
     */
    @Id
    @Column(name = "ST_TEMP_ID")
    private String stTempId;
    
    /**
     * 用户账户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 用户姓名
     */
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 用户手机号
     */
    @Column(name = "ST_MOBILE")
    private String stMobile;
    
    /**
     * 用户证件类型
     */
    @Column(name = "NM_IDENTITY_TYPE")
    private BigDecimal nmIdentityType;
    
    /**
     * 用户证件号
     */
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentityNo;
    
    /**
     * 企业名称
     */
    @Column(name = "ST_UNIT")
    private String stUnit;
    
    /**
     * 统一社会信用代码
     */
    @Column(name = "ST_UNIFIED")
    private String stUnified;
    
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
     * 次数
     */
    @Column(name = "NM_COUNT")
    private BigDecimal nmCount;
    
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
     * 临时ID
     */
    public String getStTempId() {
        return this.stTempId;
    }
    
    /**
     * 临时ID
     */
    public String stTempId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTempId);
    }

    /**
     * 临时ID
     */
    public void setStTempId(String stTempId) {
        stTempId = StringUtil.substringBySize(stTempId, 50, "GB18030");
        this.stTempId = stTempId;
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
     * 次数
     */
    public BigDecimal getNmCount() {
        return this.nmCount;
    }
    
    /**
     * 次数
     */
    public String nmCount2Html(int precision) {
        if (this.nmCount == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCount);
        }
    }

    /**
     * 次数
     */
    public void setNmCount(BigDecimal nmCount) {
        this.nmCount = nmCount;
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