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
 * 业务统计（按天）
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_STATISTICS_DAY")
public class SelmStatisticsDay implements Serializable {
    
    /**
     * 业务统计（按天）
     */
    public static final String SELM_STATISTICS_DAY = "SELM_STATISTICS_DAY";
    
    /**
     * 统计ID
     */
    public static final String ST_STATISTICS_ID = "ST_STATISTICS_ID";
    
    /**
     * 日期字符串
     */
    public static final String ST_DATE = "ST_DATE";
    
    /**
     * 业务总数
     */
    public static final String NM_COUNT = "NM_COUNT";
    
    /**
     * 业务查询
     */
    public static final String NM_QUERY = "NM_QUERY";
    
    /**
     * 业务成功
     */
    public static final String NM_SUCCESS = "NM_SUCCESS";
    
    /**
     * 业务失败
     */
    public static final String NM_FAILD = "NM_FAILD";
    
    /**
     * 日期
     */
    public static final String DT_TIME = "DT_TIME";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SelmStatisticsDay() {
    }
    
    /**
     * 统计ID
     */
    @Id
    @Column(name = "ST_STATISTICS_ID")
    private String stStatisticsId;
    
    /**
     * 日期字符串
     */
    @Id
    @Column(name = "ST_DATE")
    private String stDate;
    
    /**
     * 业务总数
     */
    @Column(name = "NM_COUNT")
    private BigDecimal nmCount;
    
    /**
     * 业务查询
     */
    @Column(name = "NM_QUERY")
    private BigDecimal nmQuery;
    
    /**
     * 业务成功
     */
    @Column(name = "NM_SUCCESS")
    private BigDecimal nmSuccess;
    
    /**
     * 业务失败
     */
    @Column(name = "NM_FAILD")
    private BigDecimal nmFaild;
    
    /**
     * 日期
     */
    @Column(name = "DT_TIME")
    private Timestamp dtTime;
    
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
     * 统计ID
     */
    public String getStStatisticsId() {
        return this.stStatisticsId;
    }
    
    /**
     * 统计ID
     */
    public String stStatisticsId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatisticsId);
    }

    /**
     * 统计ID
     */
    public void setStStatisticsId(String stStatisticsId) {
        stStatisticsId = StringUtil.substringBySize(stStatisticsId, 50, "GB18030");
        this.stStatisticsId = stStatisticsId;
    }
    
	/**
     * 日期字符串
     */
    public String getStDate() {
        return this.stDate;
    }
    
    /**
     * 日期字符串
     */
    public String stDate2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDate);
    }

    /**
     * 日期字符串
     */
    public void setStDate(String stDate) {
        stDate = StringUtil.substringBySize(stDate, 50, "GB18030");
        this.stDate = stDate;
    }

	/**
     * 业务总数
     */
    public BigDecimal getNmCount() {
        return this.nmCount;
    }
    
    /**
     * 业务总数
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
     * 业务总数
     */
    public void setNmCount(BigDecimal nmCount) {
        this.nmCount = nmCount;
    }

	/**
     * 业务查询
     */
    public BigDecimal getNmQuery() {
        return this.nmQuery;
    }
    
    /**
     * 业务查询
     */
    public String nmQuery2Html(int precision) {
        if (this.nmQuery == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmQuery);
        }
    }

    /**
     * 业务查询
     */
    public void setNmQuery(BigDecimal nmQuery) {
        this.nmQuery = nmQuery;
    }

	/**
     * 业务成功
     */
    public BigDecimal getNmSuccess() {
        return this.nmSuccess;
    }
    
    /**
     * 业务成功
     */
    public String nmSuccess2Html(int precision) {
        if (this.nmSuccess == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSuccess);
        }
    }

    /**
     * 业务成功
     */
    public void setNmSuccess(BigDecimal nmSuccess) {
        this.nmSuccess = nmSuccess;
    }

	/**
     * 业务失败
     */
    public BigDecimal getNmFaild() {
        return this.nmFaild;
    }
    
    /**
     * 业务失败
     */
    public String nmFaild2Html(int precision) {
        if (this.nmFaild == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmFaild);
        }
    }

    /**
     * 业务失败
     */
    public void setNmFaild(BigDecimal nmFaild) {
        this.nmFaild = nmFaild;
    }

	/**
     * 日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtTime() {
        return this.dtTime;
    }
    
    /**
     * 日期
     */
    public String dtTime2Html(String pattern) {
        if (this.dtTime == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtTime);
        }
    }

    /**
     * 日期
     */
    public void setDtTime(Timestamp dtTime) {
        this.dtTime = dtTime;
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