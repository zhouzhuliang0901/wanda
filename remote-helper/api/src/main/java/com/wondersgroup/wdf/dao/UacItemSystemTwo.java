package com.wondersgroup.wdf.dao;
		
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
 * 事项关联系统
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ITEM_SYSTEM")
public class UacItemSystemTwo implements Serializable {
    
    /**
     * 事项关联系统
     */
    public static final String UAC_ITEM_SYSTEM = "UAC_ITEM_SYSTEM";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 系统ID
     */
    public static final String ST_WEB_SYSTEM_ID = "ST_WEB_SYSTEM_ID";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public UacItemSystemTwo() {
    }
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 系统ID
     */
    @Id
    @Column(name = "ST_WEB_SYSTEM_ID")
    private String stWebSystemId;
    
    /**
     * 序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
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
     * 系统ID
     */
    public String getStWebSystemId() {
        return this.stWebSystemId;
    }
    
    /**
     * 系统ID
     */
    public String stWebSystemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWebSystemId);
    }

    /**
     * 系统ID
     */
    public void setStWebSystemId(String stWebSystemId) {
        stWebSystemId = StringUtil.substringBySize(stWebSystemId, 50, "GB18030");
        this.stWebSystemId = stWebSystemId;
    }

	/**
     * 序号
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 序号
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
     * 序号
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}