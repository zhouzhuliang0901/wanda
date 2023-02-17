package com.wondersgroup.sms.roleMenu.bean;
		
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
 * 角色菜单
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_ROLE_MENU")
public class SmsRoleMenu implements Serializable {
    
    /**
     * 角色菜单
     */
    public static final String SMS_ROLE_MENU = "SMS_ROLE_MENU";
    
    /**
     * 角色ID
     */
    public static final String ST_ROLE_ID = "ST_ROLE_ID";
    
    /**
     * 菜单ID
     */
    public static final String ST_MENU_ID = "ST_MENU_ID";
    
    /**
     * 排序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SmsRoleMenu() {
    }
    
    /**
     * 角色ID
     */
    @Id
    @Column(name = "ST_ROLE_ID")
    private String stRoleId;
    
    /**
     * 菜单ID
     */
    @Id
    @Column(name = "ST_MENU_ID")
    private String stMenuId;
    
    /**
     * 排序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 扩展字段1
     */
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    /**
     * 扩展字段2
     */
    @Column(name = "ST_EXT2")
    private String stExt2;
    
	/**
     * 角色ID
     */
    public String getStRoleId() {
        return this.stRoleId;
    }
    
    /**
     * 角色ID
     */
    public String stRoleId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRoleId);
    }

    /**
     * 角色ID
     */
    public void setStRoleId(String stRoleId) {
        stRoleId = StringUtil.substringBySize(stRoleId, 50, "GB18030");
        this.stRoleId = stRoleId;
    }
    
	/**
     * 菜单ID
     */
    public String getStMenuId() {
        return this.stMenuId;
    }
    
    /**
     * 菜单ID
     */
    public String stMenuId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMenuId);
    }

    /**
     * 菜单ID
     */
    public void setStMenuId(String stMenuId) {
        stMenuId = StringUtil.substringBySize(stMenuId, 50, "GB18030");
        this.stMenuId = stMenuId;
    }

	/**
     * 排序号
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序号
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
     * 排序号
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
    }
    
	/**
     * 扩展字段1
     */
    public String getStExt1() {
        return this.stExt1;
    }
    
    /**
     * 扩展字段1
     */
    public String stExt12Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt1);
    }

    /**
     * 扩展字段1
     */
    public void setStExt1(String stExt1) {
        stExt1 = StringUtil.substringBySize(stExt1, 50, "GB18030");
        this.stExt1 = stExt1;
    }
    
	/**
     * 扩展字段2
     */
    public String getStExt2() {
        return this.stExt2;
    }
    
    /**
     * 扩展字段2
     */
    public String stExt22Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt2);
    }

    /**
     * 扩展字段2
     */
    public void setStExt2(String stExt2) {
        stExt2 = StringUtil.substringBySize(stExt2, 50, "GB18030");
        this.stExt2 = stExt2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}