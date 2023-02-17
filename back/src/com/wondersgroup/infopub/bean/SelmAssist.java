package com.wondersgroup.infopub.bean;
		
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
 * 设备辅助人员
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ASSIST")
public class SelmAssist implements Serializable {
    
    /**
     * 设备辅助人员
     */
    public static final String SELM_ASSIST = "SELM_ASSIST";
    
    /**
     * 辅助人ID
     */
    public static final String ST_ASSIST_ID = "ST_ASSIST_ID";
    
    /**
     * 辅助人姓名
     */
    public static final String ST_ASSIST_NAME = "ST_ASSIST_NAME";
    
    /**
     * 辅助人手机号
     */
    public static final String ST_ASSIST_PHONE = "ST_ASSIST_PHONE";
    
    /**
     * 辅助人身份证号
     */
    public static final String ST_ASSIST_IDCARD = "ST_ASSIST_IDCARD";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 修改时间
     */
    public static final String DT_UPADTE = "DT_UPADTE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SelmAssist() {
    }
    
    /**
     * 辅助人ID
     */
    @Id
    @Column(name = "ST_ASSIST_ID")
    private String stAssistId;
    
    /**
     * 辅助人姓名
     */
    @Column(name = "ST_ASSIST_NAME")
    private String stAssistName;
    
    /**
     * 辅助人手机号
     */
    @Column(name = "ST_ASSIST_PHONE")
    private String stAssistPhone;
    
    /**
     * 辅助人身份证号
     */
    @Column(name = "ST_ASSIST_IDCARD")
    private String stAssistIdcard;
    
    /**
     * 排序
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
    @Column(name = "DT_UPADTE")
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
     * 辅助人ID
     */
    public String getStAssistId() {
        return this.stAssistId;
    }
    
    /**
     * 辅助人ID
     */
    public String stAssistId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAssistId);
    }

    /**
     * 辅助人ID
     */
    public void setStAssistId(String stAssistId) {
        stAssistId = StringUtil.substringBySize(stAssistId, 50, "GB18030");
        this.stAssistId = stAssistId;
    }
    
	/**
     * 辅助人姓名
     */
    public String getStAssistName() {
        return this.stAssistName;
    }
    
    /**
     * 辅助人姓名
     */
    public String stAssistName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAssistName);
    }

    /**
     * 辅助人姓名
     */
    public void setStAssistName(String stAssistName) {
        stAssistName = StringUtil.substringBySize(stAssistName, 50, "GB18030");
        this.stAssistName = stAssistName;
    }
    
	/**
     * 辅助人手机号
     */
    public String getStAssistPhone() {
        return this.stAssistPhone;
    }
    
    /**
     * 辅助人手机号
     */
    public String stAssistPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAssistPhone);
    }

    /**
     * 辅助人手机号
     */
    public void setStAssistPhone(String stAssistPhone) {
        stAssistPhone = StringUtil.substringBySize(stAssistPhone, 50, "GB18030");
        this.stAssistPhone = stAssistPhone;
    }
    
	/**
     * 辅助人身份证号
     */
    public String getStAssistIdcard() {
        return this.stAssistIdcard;
    }
    
    /**
     * 辅助人身份证号
     */
    public String stAssistIdcard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAssistIdcard);
    }

    /**
     * 辅助人身份证号
     */
    public void setStAssistIdcard(String stAssistIdcard) {
        stAssistIdcard = StringUtil.substringBySize(stAssistIdcard, 50, "GB18030");
        this.stAssistIdcard = stAssistIdcard;
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
    public String dtUpadte2Html(String pattern) {
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