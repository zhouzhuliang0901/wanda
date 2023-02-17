package com.wondersgroup.selmAssist.bean;
		
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
 * 设备关联人员
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DEVICE_ASSIST")
public class SelmDeviceAssist implements Serializable {
    
    /**
     * 设备关联人员
     */
    public static final String SELM_DEVICE_ASSIST = "SELM_DEVICE_ASSIST";
    
    /**
     * 辅助人ID
     */
    public static final String ST_ASSIST_ID = "ST_ASSIST_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public SelmDeviceAssist() {
    }
    
    /**
     * 辅助人ID
     */
    @Id
    @Column(name = "ST_ASSIST_ID")
    private String stAssistId;
    
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