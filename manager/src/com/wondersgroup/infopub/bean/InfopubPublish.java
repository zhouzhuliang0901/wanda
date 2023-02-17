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
 * 设备发布
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_PUBLISH")
public class InfopubPublish implements Serializable {
    
    /**
     * 设备发布
     */
    public static final String INFOPUB_PUBLISH = "INFOPUB_PUBLISH";
    
    /**
     * 发布ID
     */
    public static final String ST_PUBLISH_ID = "ST_PUBLISH_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 发布源ID
     */
    public static final String ST_PSOURCE_ID = "ST_PSOURCE_ID";
    
    /**
     * 发布名称
     */
    public static final String ST_PUBLISH_NAME = "ST_PUBLISH_NAME";
    
    /**
     * 优先级
     */
    public static final String NM_PRIORITY = "NM_PRIORITY";
    
    /**
     * 类型
     */
    public static final String ST_PTYPE = "ST_PTYPE";
    
    /**
     * 发布开始时间
     */
    public static final String ST_PSTART = "ST_PSTART";
    
    /**
     * 发布结束时间
     */
    public static final String ST_PEND = "ST_PEND";
    
    /**
     * 发布周期
     */
    public static final String ST_PERIOD = "ST_PERIOD";
    
    /**
     * 发布日期
     */
    public static final String DT_PUBLISH = "DT_PUBLISH";
    
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
    
    public InfopubPublish() {
    }
    
    /**
     * 发布ID
     */
    @Id
    @Column(name = "ST_PUBLISH_ID")
    private String stPublishId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 发布源ID
     */
    @Column(name = "ST_PSOURCE_ID")
    private String stPsourceId;
    
    /**
     * 发布名称
     */
    @Column(name = "ST_PUBLISH_NAME")
    private String stPublishName;
    
    /**
     * 优先级
     */
    @Column(name = "NM_PRIORITY")
    private BigDecimal nmPriority;
    
    /**
     * 类型
     */
    @Column(name = "ST_PTYPE")
    private String stPtype;
    
    /**
     * 发布开始时间
     */
    @Column(name = "ST_PSTART")
    private String stPstart;
    
    /**
     * 发布结束时间
     */
    @Column(name = "ST_PEND")
    private String stPend;
    
    /**
     * 发布周期
     */
    @Column(name = "ST_PERIOD")
    private String stPeriod;
    
    /**
     * 发布日期
     */
    @Column(name = "DT_PUBLISH")
    private Timestamp dtPublish;
    
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
     * 发布ID
     */
    public String getStPublishId() {
        return this.stPublishId;
    }
    
    /**
     * 发布ID
     */
    public String stPublishId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPublishId);
    }

    /**
     * 发布ID
     */
    public void setStPublishId(String stPublishId) {
        stPublishId = StringUtil.substringBySize(stPublishId, 50, "GB18030");
        this.stPublishId = stPublishId;
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
     * 发布源ID
     */
    public String getStPsourceId() {
        return this.stPsourceId;
    }
    
    /**
     * 发布源ID
     */
    public String stPsourceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPsourceId);
    }

    /**
     * 发布源ID
     */
    public void setStPsourceId(String stPsourceId) {
        stPsourceId = StringUtil.substringBySize(stPsourceId, 50, "GB18030");
        this.stPsourceId = stPsourceId;
    }
    
	/**
     * 发布名称
     */
    public String getStPublishName() {
        return this.stPublishName;
    }
    
    /**
     * 发布名称
     */
    public String stPublishName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPublishName);
    }

    /**
     * 发布名称
     */
    public void setStPublishName(String stPublishName) {
        stPublishName = StringUtil.substringBySize(stPublishName, 50, "GB18030");
        this.stPublishName = stPublishName;
    }

	/**
     * 优先级
     */
    public BigDecimal getNmPriority() {
        return this.nmPriority;
    }
    
    /**
     * 优先级
     */
    public String nmPriority2Html(int precision) {
        if (this.nmPriority == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmPriority);
        }
    }

    /**
     * 优先级
     */
    public void setNmPriority(BigDecimal nmPriority) {
        this.nmPriority = nmPriority;
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
     * 发布开始时间
     */
    public String getStPstart() {
        return this.stPstart;
    }
    
    /**
     * 发布开始时间
     */
    public String stPstart2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPstart);
    }

    /**
     * 发布开始时间
     */
    public void setStPstart(String stPstart) {
        stPstart = StringUtil.substringBySize(stPstart, 50, "GB18030");
        this.stPstart = stPstart;
    }
    
	/**
     * 发布结束时间
     */
    public String getStPend() {
        return this.stPend;
    }
    
    /**
     * 发布结束时间
     */
    public String stPend2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPend);
    }

    /**
     * 发布结束时间
     */
    public void setStPend(String stPend) {
        stPend = StringUtil.substringBySize(stPend, 50, "GB18030");
        this.stPend = stPend;
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
     * 发布日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtPublish() {
        return this.dtPublish;
    }
    
    /**
     * 发布日期
     */
    public String dtPublish2Html(String pattern) {
        if (this.dtPublish == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtPublish);
        }
    }

    /**
     * 发布日期
     */
    public void setDtPublish(Timestamp dtPublish) {
        this.dtPublish = dtPublish;
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