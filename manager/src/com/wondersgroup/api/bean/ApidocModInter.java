package com.wondersgroup.api.bean;
		
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
 * 模块关联接口
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APIDOC_MOD_INTER")
public class ApidocModInter implements Serializable {
    
    /**
     * 模块关联接口
     */
    public static final String APIDOC_MOD_INTER = "APIDOC_MOD_INTER";
    
    /**
     * 模块ID
     */
    public static final String ST_MODULE_ID = "ST_MODULE_ID";
    
    /**
     * 接口ID
     */
    public static final String ST_INTERFACE_ID = "ST_INTERFACE_ID";
    
    /**
     * 排序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public ApidocModInter() {
    }
    
    /**
     * 模块ID
     */
    @Id
    @Column(name = "ST_MODULE_ID")
    private String stModuleId;
    
    /**
     * 接口ID
     */
    @Id
    @Column(name = "ST_INTERFACE_ID")
    private String stInterfaceId;
    
    /**
     * 排序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
	/**
     * 模块ID
     */
    public String getStModuleId() {
        return this.stModuleId;
    }
    
    /**
     * 模块ID
     */
    public String stModuleId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stModuleId);
    }

    /**
     * 模块ID
     */
    public void setStModuleId(String stModuleId) {
        stModuleId = StringUtil.substringBySize(stModuleId, 50, "GB18030");
        this.stModuleId = stModuleId;
    }
    
	/**
     * 接口ID
     */
    public String getStInterfaceId() {
        return this.stInterfaceId;
    }
    
    /**
     * 接口ID
     */
    public String stInterfaceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInterfaceId);
    }

    /**
     * 接口ID
     */
    public void setStInterfaceId(String stInterfaceId) {
        stInterfaceId = StringUtil.substringBySize(stInterfaceId, 50, "GB18030");
        this.stInterfaceId = stInterfaceId;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}