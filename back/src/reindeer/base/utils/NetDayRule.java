package reindeer.base.utils;
		
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
 * 预约规则表-按天设置
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NET_DAY_RULE")
public class NetDayRule implements Serializable {
    
    /**
     * 预约规则表-按天设置
     */
    public static final String NET_DAY_RULE = "NET_DAY_RULE";
    
    /**
     * 天规则ID
     */
    public static final String ST_DAY_RULE_ID = "ST_DAY_RULE_ID";
    
    /**
     * 规则列表ID
     */
    public static final String ST_LIST_ID = "ST_LIST_ID";
    
    /**
     * 日期
     */
    public static final String DT_DAY = "DT_DAY";
    
    /**
     * 日期字符串
     */
    public static final String ST_DAY = "ST_DAY";
    
    /**
     * 设置
     */
    public static final String NM_RULE = "NM_RULE";
    
    public NetDayRule() {
    }
    
    /**
     * 天规则ID
     */
    @Id
    @Column(name = "ST_DAY_RULE_ID")
    private String stDayRuleId;
    
    /**
     * 规则列表ID
     */
    @Column(name = "ST_LIST_ID")
    private String stListId;
    
    /**
     * 日期
     */
    @Column(name = "DT_DAY")
    private Timestamp dtDay;
    
    /**
     * 日期字符串
     */
    @Column(name = "ST_DAY")
    private String stDay;
    
    /**
     * 设置
     */
    @Column(name = "NM_RULE")
    private BigDecimal nmRule;
    
	/**
     * 天规则ID
     */
    public String getStDayRuleId() {
        return this.stDayRuleId;
    }
    
    /**
     * 天规则ID
     */
    public String stDayRuleId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDayRuleId);
    }

    /**
     * 天规则ID
     */
    public void setStDayRuleId(String stDayRuleId) {
        stDayRuleId = StringUtil.substringBySize(stDayRuleId, 50, "GB18030");
        this.stDayRuleId = stDayRuleId;
    }
    
	/**
     * 规则列表ID
     */
    public String getStListId() {
        return this.stListId;
    }
    
    /**
     * 规则列表ID
     */
    public String stListId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stListId);
    }

    /**
     * 规则列表ID
     */
    public void setStListId(String stListId) {
        stListId = StringUtil.substringBySize(stListId, 50, "GB18030");
        this.stListId = stListId;
    }

	/**
     * 日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtDay() {
        return this.dtDay;
    }
    
    /**
     * 日期
     */
    public String dtDay2Html(String pattern) {
        if (this.dtDay == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDay);
        }
    }

    /**
     * 日期
     */
    public void setDtDay(Timestamp dtDay) {
        this.dtDay = dtDay;
    }
    
	/**
     * 日期字符串
     */
    public String getStDay() {
        return this.stDay;
    }
    
    /**
     * 日期字符串
     */
    public String stDay2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDay);
    }

    /**
     * 日期字符串
     */
    public void setStDay(String stDay) {
        stDay = StringUtil.substringBySize(stDay, 50, "GB18030");
        this.stDay = stDay;
    }

	/**
     * 设置
     */
    public BigDecimal getNmRule() {
        return this.nmRule;
    }
    
    /**
     * 设置
     */
    public String nmRule2Html(int precision) {
        if (this.nmRule == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmRule);
        }
    }

    /**
     * 设置
     */
    public void setNmRule(BigDecimal nmRule) {
        this.nmRule = nmRule;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}