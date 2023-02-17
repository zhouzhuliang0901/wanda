package com.wondersgroup.app.bean;
		
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
 * 类别关联事项
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ITEM_LINK")
public class SelmItemLink implements Serializable {
    
    /**
     * 类别关联事项
     */
    public static final String SELM_ITEM_LINK = "SELM_ITEM_LINK";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项类别ID
     */
    public static final String ST_ITEM_TYPE_ID = "ST_ITEM_TYPE_ID";
    
    /**
     * 排序
     */
    public static final String NM_SORT = "NM_SORT";
    
    public SelmItemLink() {
    }
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项类别ID
     */
    @Id
    @Column(name = "ST_ITEM_TYPE_ID")
    private String stItemTypeId;
    
    /**
     * 排序
     */
    @Column(name = "NM_SORT")
    private BigDecimal nmSort;
    
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
     * 事项类别ID
     */
    public String getStItemTypeId() {
        return this.stItemTypeId;
    }
    
    /**
     * 事项类别ID
     */
    public String stItemTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemTypeId);
    }

    /**
     * 事项类别ID
     */
    public void setStItemTypeId(String stItemTypeId) {
        stItemTypeId = StringUtil.substringBySize(stItemTypeId, 50, "GB18030");
        this.stItemTypeId = stItemTypeId;
    }

	/**
     * 排序
     */
    public BigDecimal getNmSort() {
        return this.nmSort;
    }
    
    /**
     * 排序
     */
    public String nmSort2Html(int precision) {
        if (this.nmSort == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSort);
        }
    }

    /**
     * 排序
     */
    public void setNmSort(BigDecimal nmSort) {
        this.nmSort = nmSort;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}