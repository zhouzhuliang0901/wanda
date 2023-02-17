package com.wondersgroup.infopub.bean;
		
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
 * 设备厂商
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_COMPANY")
public class InfopubCompany implements Serializable {
    
    /**
     * 设备厂商
     */
    public static final String INFOPUB_COMPANY = "INFOPUB_COMPANY";
    
    /**
     * 厂商ID
     */
    public static final String ST_COMPANY_ID = "ST_COMPANY_ID";
    
    /**
     * 厂商编码
     */
    public static final String ST_COMPANY_CODE = "ST_COMPANY_CODE";
    
    /**
     * 厂商名称
     */
    public static final String ST_COMPANY_NAME = "ST_COMPANY_NAME";
    
    /**
     * 厂商负责人姓名
     */
    public static final String ST_CONTACT_NAME = "ST_CONTACT_NAME";
    
    /**
     * 厂商负责人联系方式
     */
    public static final String ST_CONTACT_TEL = "ST_CONTACT_TEL";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public InfopubCompany() {
    }
    
    /**
     * 厂商ID
     */
    @Id
    @Column(name = "ST_COMPANY_ID")
    private String stCompanyId;
    
    /**
     * 厂商编码
     */
    @Column(name = "ST_COMPANY_CODE")
    private String stCompanyCode;
    
    /**
     * 厂商名称
     */
    @Column(name = "ST_COMPANY_NAME")
    private String stCompanyName;
    
    /**
     * 厂商负责人姓名
     */
    @Column(name = "ST_CONTACT_NAME")
    private String stContactName;
    
    /**
     * 厂商负责人联系方式
     */
    @Column(name = "ST_CONTACT_TEL")
    private String stContactTel;
    
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
     * 更新时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
	/**
     * 厂商ID
     */
    public String getStCompanyId() {
        return this.stCompanyId;
    }
    
    /**
     * 厂商ID
     */
    public String stCompanyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCompanyId);
    }

    /**
     * 厂商ID
     */
    public void setStCompanyId(String stCompanyId) {
        stCompanyId = StringUtil.substringBySize(stCompanyId, 50, "GB18030");
        this.stCompanyId = stCompanyId;
    }
    
	/**
     * 厂商编码
     */
    public String getStCompanyCode() {
        return this.stCompanyCode;
    }
    
    /**
     * 厂商编码
     */
    public String stCompanyCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCompanyCode);
    }

    /**
     * 厂商编码
     */
    public void setStCompanyCode(String stCompanyCode) {
        stCompanyCode = StringUtil.substringBySize(stCompanyCode, 50, "GB18030");
        this.stCompanyCode = stCompanyCode;
    }
    
	/**
     * 厂商名称
     */
    public String getStCompanyName() {
        return this.stCompanyName;
    }
    
    /**
     * 厂商名称
     */
    public String stCompanyName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCompanyName);
    }

    /**
     * 厂商名称
     */
    public void setStCompanyName(String stCompanyName) {
        stCompanyName = StringUtil.substringBySize(stCompanyName, 50, "GB18030");
        this.stCompanyName = stCompanyName;
    }
    
	/**
     * 厂商负责人姓名
     */
    public String getStContactName() {
        return this.stContactName;
    }
    
    /**
     * 厂商负责人姓名
     */
    public String stContactName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stContactName);
    }

    /**
     * 厂商负责人姓名
     */
    public void setStContactName(String stContactName) {
        stContactName = StringUtil.substringBySize(stContactName, 50, "GB18030");
        this.stContactName = stContactName;
    }
    
	/**
     * 厂商负责人联系方式
     */
    public String getStContactTel() {
        return this.stContactTel;
    }
    
    /**
     * 厂商负责人联系方式
     */
    public String stContactTel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stContactTel);
    }

    /**
     * 厂商负责人联系方式
     */
    public void setStContactTel(String stContactTel) {
        stContactTel = StringUtil.substringBySize(stContactTel, 50, "GB18030");
        this.stContactTel = stContactTel;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}