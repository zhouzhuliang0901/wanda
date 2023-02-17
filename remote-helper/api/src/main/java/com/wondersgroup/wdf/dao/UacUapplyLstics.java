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
 * 办件关联物流
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_LSTICS")
public class UacUapplyLstics implements Serializable {
    
    /**
     * 办件关联物流
     */
    public static final String UAC_UAPPLY_LSTICS = "UAC_UAPPLY_LSTICS";
    
    /**
     * 办件物流ID
     */
    public static final String ST_UNION_LOGISTICS_ID = "ST_UNION_LOGISTICS_ID";
    
    /**
     * 办件信息ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 环节
     */
    public static final String ST_NODE = "ST_NODE";
    
    public UacUapplyLstics() {
    }
    
    /**
     * 办件物流ID
     */
    @Id
    @Column(name = "ST_UNION_LOGISTICS_ID")
    private String stUnionLogisticsId;
    
    /**
     * 办件信息ID
     */
    @Id
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 环节
     */
    @Column(name = "ST_NODE")
    private String stNode;
    
	/**
     * 办件物流ID
     */
    public String getStUnionLogisticsId() {
        return this.stUnionLogisticsId;
    }
    
    /**
     * 办件物流ID
     */
    public String stUnionLogisticsId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnionLogisticsId);
    }

    /**
     * 办件物流ID
     */
    public void setStUnionLogisticsId(String stUnionLogisticsId) {
        stUnionLogisticsId = StringUtil.substringBySize(stUnionLogisticsId, 50, "GB18030");
        this.stUnionLogisticsId = stUnionLogisticsId;
    }
    
	/**
     * 办件信息ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 办件信息ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 办件信息ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
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
     * 环节
     */
    public String getStNode() {
        return this.stNode;
    }
    
    /**
     * 环节
     */
    public String stNode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stNode);
    }

    /**
     * 环节
     */
    public void setStNode(String stNode) {
        stNode = StringUtil.substringBySize(stNode, 50, "GB18030");
        this.stNode = stNode;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}