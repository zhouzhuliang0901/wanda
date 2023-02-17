package com.wondersgroup.statistics.bean;
		
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
 * 区域日办件统计表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_AREA_QUERY_DAY")
public class SelmAreaQueryDay implements Serializable {
    
    /**
     * 区域日办件统计表
     */
    public static final String SELM_AREA_QUERY_DAY = "SELM_AREA_QUERY_DAY";
    
    /**
     * 区域标识
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 天
     */
    public static final String ST_DAY = "ST_DAY";
    
    /**
     * 区域名称
     */
    public static final String ST_AREA_NAME = "ST_AREA_NAME";
    
    /**
     * 政务服务自助终端数量
     */
    public static final String NM_GOV_NUMBER = "NM_GOV_NUMBER";
    
    /**
     * 社会化自助终端数量
     */
    public static final String NM_SOCIAL_NUMBER = "NM_SOCIAL_NUMBER";
    
    /**
     * 日办件数量
     */
    public static final String NM_DAY = "NM_DAY";
    
    public SelmAreaQueryDay() {
    }
    
    /**
     * 区域标识
     */
    @Id
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 天
     */
    @Id
    @Column(name = "ST_DAY")
    private String stDay;
    
    /**
     * 区域名称
     */
    @Column(name = "ST_AREA_NAME")
    private String stAreaName;
    
    /**
     * 政务服务自助终端数量
     */
    @Column(name = "NM_GOV_NUMBER")
    private BigDecimal nmGovNumber;
    
    /**
     * 社会化自助终端数量
     */
    @Column(name = "NM_SOCIAL_NUMBER")
    private BigDecimal nmSocialNumber;
    
    /**
     * 日办件数量
     */
    @Column(name = "NM_DAY")
    private BigDecimal nmDay;
    
	/**
     * 区域标识
     */
    public String getStAreaId() {
        return this.stAreaId;
    }
    
    /**
     * 区域标识
     */
    public String stAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaId);
    }

    /**
     * 区域标识
     */
    public void setStAreaId(String stAreaId) {
        stAreaId = StringUtil.substringBySize(stAreaId, 50, "GB18030");
        this.stAreaId = stAreaId;
    }
    
	/**
     * 天
     */
    public String getStDay() {
        return this.stDay;
    }
    
    /**
     * 天
     */
    public String stDay2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDay);
    }

    /**
     * 天
     */
    public void setStDay(String stDay) {
        stDay = StringUtil.substringBySize(stDay, 50, "GB18030");
        this.stDay = stDay;
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
     * 政务服务自助终端数量
     */
    public BigDecimal getNmGovNumber() {
        return this.nmGovNumber;
    }
    
    /**
     * 政务服务自助终端数量
     */
    public String nmGovNumber2Html(int precision) {
        if (this.nmGovNumber == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmGovNumber);
        }
    }

    /**
     * 政务服务自助终端数量
     */
    public void setNmGovNumber(BigDecimal nmGovNumber) {
        this.nmGovNumber = nmGovNumber;
    }

	/**
     * 社会化自助终端数量
     */
    public BigDecimal getNmSocialNumber() {
        return this.nmSocialNumber;
    }
    
    /**
     * 社会化自助终端数量
     */
    public String nmSocialNumber2Html(int precision) {
        if (this.nmSocialNumber == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSocialNumber);
        }
    }

    /**
     * 社会化自助终端数量
     */
    public void setNmSocialNumber(BigDecimal nmSocialNumber) {
        this.nmSocialNumber = nmSocialNumber;
    }

	/**
     * 日办件数量
     */
    public BigDecimal getNmDay() {
        return this.nmDay;
    }
    
    /**
     * 日办件数量
     */
    public String nmDay2Html(int precision) {
        if (this.nmDay == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDay);
        }
    }

    /**
     * 日办件数量
     */
    public void setNmDay(BigDecimal nmDay) {
        this.nmDay = nmDay;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}