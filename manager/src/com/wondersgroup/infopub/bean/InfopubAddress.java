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
 * 地址表（办理点）
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_ADDRESS")
public class InfopubAddress implements Serializable {
    
    /**
     * 地址表（办理点）
     */
    public static final String INFOPUB_ADDRESS = "INFOPUB_ADDRESS";
    
    /**
     * 地址ID
     */
    public static final String ST_ADDRESS_ID = "ST_ADDRESS_ID";
    
    /**
     * 地址名称
     */
    public static final String ST_ADDRESS = "ST_ADDRESS";
    
    /**
     * 地址别名
     */
    public static final String ST_LABEL = "ST_LABEL";
    
    /**
     * 经度
     */
    public static final String NM_LNG = "NM_LNG";
    
    /**
     * 纬度
     */
    public static final String NM_LAT = "NM_LAT";
    
    /**
     * 市
     */
    public static final String ST_CITY = "ST_CITY";
    
    /**
     * 区
     */
    public static final String ST_DISTRICT = "ST_DISTRICT";
    
    /**
     * 街道
     */
    public static final String ST_STREET = "ST_STREET";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public InfopubAddress() {
    }
    
    /**
     * 地址ID
     */
    @Id
    @Column(name = "ST_ADDRESS_ID")
    private String stAddressId;
    
    /**
     * 地址名称
     */
    @Column(name = "ST_ADDRESS")
    private String stAddress;
    
    /**
     * 地址别名
     */
    @Column(name = "ST_LABEL")
    private String stLabel;
    
    /**
     * 经度
     */
    @Column(name = "NM_LNG")
    private BigDecimal nmLng;
    
    /**
     * 纬度
     */
    @Column(name = "NM_LAT")
    private BigDecimal nmLat;
    
    /**
     * 市
     */
    @Column(name = "ST_CITY")
    private String stCity;
    
    /**
     * 区
     */
    @Column(name = "ST_DISTRICT")
    private String stDistrict;
    
    /**
     * 街道
     */
    @Column(name = "ST_STREET")
    private String stStreet;
    
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
     * 地址ID
     */
    public String getStAddressId() {
        return this.stAddressId;
    }
    
    /**
     * 地址ID
     */
    public String stAddressId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAddressId);
    }

    /**
     * 地址ID
     */
    public void setStAddressId(String stAddressId) {
        stAddressId = StringUtil.substringBySize(stAddressId, 50, "GB18030");
        this.stAddressId = stAddressId;
    }
    
	/**
     * 地址名称
     */
    public String getStAddress() {
        return this.stAddress;
    }
    
    /**
     * 地址名称
     */
    public String stAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAddress);
    }

    /**
     * 地址名称
     */
    public void setStAddress(String stAddress) {
        stAddress = StringUtil.substringBySize(stAddress, 200, "GB18030");
        this.stAddress = stAddress;
    }
    
	/**
     * 地址别名
     */
    public String getStLabel() {
        return this.stLabel;
    }
    
    /**
     * 地址别名
     */
    public String stLabel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLabel);
    }

    /**
     * 地址别名
     */
    public void setStLabel(String stLabel) {
        stLabel = StringUtil.substringBySize(stLabel, 200, "GB18030");
        this.stLabel = stLabel;
    }

	/**
     * 经度
     */
    public BigDecimal getNmLng() {
        return this.nmLng;
    }
    
    /**
     * 经度
     */
    public String nmLng2Html(int precision) {
        if (this.nmLng == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLng);
        }
    }

    /**
     * 经度
     */
    public void setNmLng(BigDecimal nmLng) {
        this.nmLng = nmLng;
    }

	/**
     * 纬度
     */
    public BigDecimal getNmLat() {
        return this.nmLat;
    }
    
    /**
     * 纬度
     */
    public String nmLat2Html(int precision) {
        if (this.nmLat == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLat);
        }
    }

    /**
     * 纬度
     */
    public void setNmLat(BigDecimal nmLat) {
        this.nmLat = nmLat;
    }
    
	/**
     * 市
     */
    public String getStCity() {
        return this.stCity;
    }
    
    /**
     * 市
     */
    public String stCity2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCity);
    }

    /**
     * 市
     */
    public void setStCity(String stCity) {
        stCity = StringUtil.substringBySize(stCity, 50, "GB18030");
        this.stCity = stCity;
    }
    
	/**
     * 区
     */
    public String getStDistrict() {
        return this.stDistrict;
    }
    
    /**
     * 区
     */
    public String stDistrict2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDistrict);
    }

    /**
     * 区
     */
    public void setStDistrict(String stDistrict) {
        stDistrict = StringUtil.substringBySize(stDistrict, 50, "GB18030");
        this.stDistrict = stDistrict;
    }
    
	/**
     * 街道
     */
    public String getStStreet() {
        return this.stStreet;
    }
    
    /**
     * 街道
     */
    public String stStreet2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStreet);
    }

    /**
     * 街道
     */
    public void setStStreet(String stStreet) {
        stStreet = StringUtil.substringBySize(stStreet, 50, "GB18030");
        this.stStreet = stStreet;
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