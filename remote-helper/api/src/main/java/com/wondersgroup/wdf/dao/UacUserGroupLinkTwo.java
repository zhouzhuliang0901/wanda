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
 * 组关联人员
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_USER_GROUP_LINK")
public class UacUserGroupLinkTwo implements Serializable {
    
    /**
     * 组关联人员
     */
    public static final String UAC_USER_GROUP_LINK = "UAC_USER_GROUP_LINK";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 事项组ID
     */
    public static final String ST_GROUP_ID = "ST_GROUP_ID";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public UacUserGroupLinkTwo() {
    }
    
    /**
     * 用户ID
     */
    @Id
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
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
     * 用户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 用户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 用户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
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