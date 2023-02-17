package com.wondersgroup.sms.resource.bean;
		
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 资源权限访问列表
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_RESOURCE_ACCESS_LIST")
public class SmsResourceAccessList implements Serializable {
    
    /**
     * 资源权限访问列表
     */
    public static final String SMS_RESOURCE_ACCESS_LIST = "SMS_RESOURCE_ACCESS_LIST";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 资源ID
     */
    public static final String ST_RESOURCE_ID = "ST_RESOURCE_ID";
    
    /**
     * 资源类型ID
     */
    public static final String ST_RESOURCE_TYPE_ID = "ST_RESOURCE_TYPE_ID";
    
    /**
     * 资源唯一值
     */
    public static final String ST_UNIQUE_VALUE = "ST_UNIQUE_VALUE";
    
    public SmsResourceAccessList() {
    }
    
    /**
     * 用户ID
     */
    @Id
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 资源ID
     */
    @Id
    @Column(name = "ST_RESOURCE_ID")
    private String stResourceId;
    
    /**
     * 资源类型ID
     */
    @Id
    @Column(name = "ST_RESOURCE_TYPE_ID")
    private String stResourceTypeId;
    
    /**
     * 资源唯一值
     */
    @Column(name = "ST_UNIQUE_VALUE")
    private String stUniqueValue;
    
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
     * 资源ID
     */
    public String getStResourceId() {
        return this.stResourceId;
    }
    
    /**
     * 资源ID
     */
    public String stResourceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResourceId);
    }

    /**
     * 资源ID
     */
    public void setStResourceId(String stResourceId) {
        stResourceId = StringUtil.substringBySize(stResourceId, 50, "GB18030");
        this.stResourceId = stResourceId;
    }
    
	/**
     * 资源类型ID
     */
    public String getStResourceTypeId() {
        return this.stResourceTypeId;
    }
    
    /**
     * 资源类型ID
     */
    public String stResourceTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResourceTypeId);
    }

    /**
     * 资源类型ID
     */
    public void setStResourceTypeId(String stResourceTypeId) {
        stResourceTypeId = StringUtil.substringBySize(stResourceTypeId, 50, "GB18030");
        this.stResourceTypeId = stResourceTypeId;
    }
    
	/**
     * 资源唯一值
     */
    public String getStUniqueValue() {
        return this.stUniqueValue;
    }
    
    /**
     * 资源唯一值
     */
    public String stUniqueValue2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUniqueValue);
    }

    /**
     * 资源唯一值
     */
    public void setStUniqueValue(String stUniqueValue) {
        stUniqueValue = StringUtil.substringBySize(stUniqueValue, 100, "GB18030");
        this.stUniqueValue = stUniqueValue;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}