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
 * 设备关联事项
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DEVICE_ITEM")
public class SelmDeviceItem implements Serializable {
    
    /**
     * 设备关联事项
     */
    public static final String SELM_DEVICE_ITEM = "SELM_DEVICE_ITEM";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
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
    
    public SelmDeviceItem() {
    }
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 设备ID
     */
    @Id
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
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
     * 事项ID
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 事项ID
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项ID
     */
    public void setStItemId(String stItemId) {
        stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
        this.stItemId = stItemId;
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