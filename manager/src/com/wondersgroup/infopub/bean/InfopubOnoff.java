package com.wondersgroup.infopub.bean;
		
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 设备开关机
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_ONOFF")
public class InfopubOnoff implements Serializable {
    
    /**
     * 设备开关机
     */
    public static final String INFOPUB_ONOFF = "INFOPUB_ONOFF";
    
    /**
     * 设备开关机ID
     */
    public static final String ST_ONOFF_ID = "ST_ONOFF_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 类型
     */
    public static final String ST_PTYPE = "ST_PTYPE";
    
    /**
     * 开机时间
     */
    public static final String ST_ON_TIME = "ST_ON_TIME";
    
    /**
     * 关机时间
     */
    public static final String ST_OFF_TIME = "ST_OFF_TIME";
    
    /**
     * 发布周期
     */
    public static final String ST_PERIOD = "ST_PERIOD";
    
    /**
     * 定时日期
     */
    public static final String DT_ONOFF = "DT_ONOFF";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    public InfopubOnoff() {
    }
    
    /**
     * 设备开关机ID
     */
    @Id
    @Column(name = "ST_ONOFF_ID")
    private String stOnoffId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 类型
     */
    @Column(name = "ST_PTYPE")
    private String stPtype;
    
    /**
     * 开机时间
     */
    @Column(name = "ST_ON_TIME")
    private String stOnTime;
    
    /**
     * 关机时间
     */
    @Column(name = "ST_OFF_TIME")
    private String stOffTime;
    
    /**
     * 发布周期
     */
    @Column(name = "ST_PERIOD")
    private String stPeriod;
    
    /**
     * 定时日期
     */
    @Column(name = "DT_ONOFF")
    private Timestamp dtOnoff;
    
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
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
	/**
     * 设备开关机ID
     */
    public String getStOnoffId() {
        return this.stOnoffId;
    }
    
    /**
     * 设备开关机ID
     */
    public String stOnoffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOnoffId);
    }

    /**
     * 设备开关机ID
     */
    public void setStOnoffId(String stOnoffId) {
        stOnoffId = StringUtil.substringBySize(stOnoffId, 50, "GB18030");
        this.stOnoffId = stOnoffId;
    }
    
	/**
     * 设备ID
     */
    public String getStDeviceId() {
        return this.stDeviceId;
    }
    
    /**
     * 设备ID
     */
    public String stDeviceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceId);
    }

    /**
     * 设备ID
     */
    public void setStDeviceId(String stDeviceId) {
        stDeviceId = StringUtil.substringBySize(stDeviceId, 50, "GB18030");
        this.stDeviceId = stDeviceId;
    }
    
	/**
     * 类型
     */
    public String getStPtype() {
        return this.stPtype;
    }
    
    /**
     * 类型
     */
    public String stPtype2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPtype);
    }

    /**
     * 类型
     */
    public void setStPtype(String stPtype) {
        stPtype = StringUtil.substringBySize(stPtype, 50, "GB18030");
        this.stPtype = stPtype;
    }
    
	/**
     * 开机时间
     */
    public String getStOnTime() {
        return this.stOnTime;
    }
    
    /**
     * 开机时间
     */
    public String stOnTime2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOnTime);
    }

    /**
     * 开机时间
     */
    public void setStOnTime(String stOnTime) {
        stOnTime = StringUtil.substringBySize(stOnTime, 50, "GB18030");
        this.stOnTime = stOnTime;
    }
    
	/**
     * 关机时间
     */
    public String getStOffTime() {
        return this.stOffTime;
    }
    
    /**
     * 关机时间
     */
    public String stOffTime2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOffTime);
    }

    /**
     * 关机时间
     */
    public void setStOffTime(String stOffTime) {
        stOffTime = StringUtil.substringBySize(stOffTime, 50, "GB18030");
        this.stOffTime = stOffTime;
    }
    
	/**
     * 发布周期
     */
    public String getStPeriod() {
        return this.stPeriod;
    }
    
    /**
     * 发布周期
     */
    public String stPeriod2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPeriod);
    }

    /**
     * 发布周期
     */
    public void setStPeriod(String stPeriod) {
        stPeriod = StringUtil.substringBySize(stPeriod, 50, "GB18030");
        this.stPeriod = stPeriod;
    }

	/**
     * 定时日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtOnoff() {
        return this.dtOnoff;
    }
    
    /**
     * 定时日期
     */
    public String dtOnoff2Html(String pattern) {
        if (this.dtOnoff == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtOnoff);
        }
    }

    /**
     * 定时日期
     */
    public void setDtOnoff(Timestamp dtOnoff) {
        this.dtOnoff = dtOnoff;
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