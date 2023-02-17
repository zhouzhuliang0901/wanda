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
 * 组关联事项
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ITEM_GROUP_LINK")
public class UacItemGroupLinkTwo implements Serializable {
    
    /**
     * 组关联事项
     */
    public static final String UAC_ITEM_GROUP_LINK = "UAC_ITEM_GROUP_LINK";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项组ID
     */
    public static final String ST_GROUP_ID = "ST_GROUP_ID";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public UacItemGroupLinkTwo() {
    }
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项组ID
     */
    @Id
    @Column(name = "ST_GROUP_ID")
    private String stGroupId;
    
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
     * 事项组ID
     */
    public String getStGroupId() {
        return this.stGroupId;
    }
    
    /**
     * 事项组ID
     */
    public String stGroupId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stGroupId);
    }

    /**
     * 事项组ID
     */
    public void setStGroupId(String stGroupId) {
        stGroupId = StringUtil.substringBySize(stGroupId, 50, "GB18030");
        this.stGroupId = stGroupId;
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