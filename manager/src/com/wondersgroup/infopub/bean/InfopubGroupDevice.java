package com.wondersgroup.infopub.bean;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 设备组关联
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_GROUP_DEVICE")
public class InfopubGroupDevice implements Serializable {
    
    /**
     * 设备组关联
     */
    public static final String INFOPUB_GROUP_DEVICE = "INFOPUB_GROUP_DEVICE";
    
    /**
     * 分组ID
     */
    public static final String ST_GROUP_ID = "ST_GROUP_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public InfopubGroupDevice() {
    }
    
    /**
     * 分组ID
     */
    @Id
    @Column(name = "ST_GROUP_ID")
    private String stGroupId;
    
    /**
     * 设备ID
     */
    @Id
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
	/**
     * 分组ID
     */
    public String getStGroupId() {
        return this.stGroupId;
    }
    
    /**
     * 分组ID
     */
    public String stGroupId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stGroupId);
    }

    /**
     * 分组ID
     */
    public void setStGroupId(String stGroupId) {
        stGroupId = StringUtil.substringBySize(stGroupId, 50, "GB18030");
        this.stGroupId = stGroupId;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}