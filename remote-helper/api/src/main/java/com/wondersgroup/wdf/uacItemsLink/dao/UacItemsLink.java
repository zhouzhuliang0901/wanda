package com.wondersgroup.wdf.uacItemsLink.dao;
		
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
 * 主题服务事项关联
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ITEMS_LINK")
public class UacItemsLink implements Serializable {
    
    /**
     * 主题服务事项关联
     */
    public static final String UAC_ITEMS_LINK = "UAC_ITEMS_LINK";
    
    /**
     * 主题ID
     */
    public static final String ST_ITEMS_ID = "ST_ITEMS_ID";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public UacItemsLink() {
    }
    
    /**
     * 主题ID
     */
    @Id
    @Column(name = "ST_ITEMS_ID")
    private String stItemsId;
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
	/**
     * 主题ID
     */
    public String getStItemsId() {
        return this.stItemsId;
    }
    
    /**
     * 主题ID
     */
    public String stItemsId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsId);
    }

    /**
     * 主题ID
     */
    public void setStItemsId(String stItemsId) {
        stItemsId = StringUtil.substringBySize(stItemsId, 50, "GB18030");
        this.stItemsId = stItemsId;
    }
    
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