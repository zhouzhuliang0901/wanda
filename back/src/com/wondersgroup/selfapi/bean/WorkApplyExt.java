package com.wondersgroup.selfapi.bean;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import reindeer.base.utils.StringUtil;

import wfc.service.util.StringHelper;


/**
 * 办理信息扩展字段表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WORK_APPLY_EXT")
public class WorkApplyExt implements Serializable {
    
    /**
     * 办理信息扩展字段表
     */
    public static final String WORK_APPLY_EXT = "WORK_APPLY_EXT";
    
    /**
     * 主键UUID
     */
    public static final String ST_EXT_ID = "ST_EXT_ID";
    
    /**
     * 关联表
     */
    public static final String ST_TABLE = "ST_TABLE";
    
    /**
     * 外键
     */
    public static final String ST_ENTITY_ID = "ST_ENTITY_ID";
    
    /**
     * 信息英文名称
     */
    public static final String ST_INFO_NAME_EN = "ST_INFO_NAME_EN";
    
    /**
     * 信息名称
     */
    public static final String ST_INFO_NAME = "ST_INFO_NAME";
    
    /**
     * 信息值
     */
    public static final String ST_INFO_VALUE = "ST_INFO_VALUE";
    
    /**
     * 展示顺序
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
    
    public WorkApplyExt() {
    }
    
    /**
     * 主键UUID
     */
    @Id
    @Column(name = "ST_EXT_ID")
    private String stExtId;
    
    /**
     * 关联表
     */
    @Column(name = "ST_TABLE")
    private String stTable;
    
    /**
     * 外键
     */
    @Column(name = "ST_ENTITY_ID")
    private String stEntityId;
    
    /**
     * 信息英文名称
     */
    @Column(name = "ST_INFO_NAME_EN")
    private String stInfoNameEn;
    
    /**
     * 信息名称
     */
    @Column(name = "ST_INFO_NAME")
    private String stInfoName;
    
    /**
     * 信息值
     */
    @Column(name = "ST_INFO_VALUE")
    private String stInfoValue;
    
    /**
     * 展示顺序
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
     * 主键UUID
     */
    public String getStExtId() {
        return this.stExtId;
    }
    
    /**
     * 主键UUID
     */
    public String stExtId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stExtId);
    }

    /**
     * 主键UUID
     */
    public void setStExtId(String stExtId) {
        stExtId = StringUtil.substringBySize(stExtId, 50, "GB18030");
        this.stExtId = stExtId;
    }
    
	/**
     * 关联表
     */
    public String getStTable() {
        return this.stTable;
    }
    
    /**
     * 关联表
     */
    public String stTable2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTable);
    }

    /**
     * 关联表
     */
    public void setStTable(String stTable) {
        stTable = StringUtil.substringBySize(stTable, 50, "GB18030");
        this.stTable = stTable;
    }
    
	/**
     * 外键
     */
    public String getStEntityId() {
        return this.stEntityId;
    }
    
    /**
     * 外键
     */
    public String stEntityId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEntityId);
    }

    /**
     * 外键
     */
    public void setStEntityId(String stEntityId) {
        stEntityId = StringUtil.substringBySize(stEntityId, 50, "GB18030");
        this.stEntityId = stEntityId;
    }
    
	/**
     * 信息英文名称
     */
    public String getStInfoNameEn() {
        return this.stInfoNameEn;
    }
    
    /**
     * 信息英文名称
     */
    public String stInfoNameEn2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInfoNameEn);
    }

    /**
     * 信息英文名称
     */
    public void setStInfoNameEn(String stInfoNameEn) {
        stInfoNameEn = StringUtil.substringBySize(stInfoNameEn, 100, "GB18030");
        this.stInfoNameEn = stInfoNameEn;
    }
    
	/**
     * 信息名称
     */
    public String getStInfoName() {
        return this.stInfoName;
    }
    
    /**
     * 信息名称
     */
    public String stInfoName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInfoName);
    }

    /**
     * 信息名称
     */
    public void setStInfoName(String stInfoName) {
        stInfoName = StringUtil.substringBySize(stInfoName, 100, "GB18030");
        this.stInfoName = stInfoName;
    }
    
	/**
     * 信息值
     */
    public String getStInfoValue() {
        return this.stInfoValue;
    }
    
    /**
     * 信息值
     */
    public String stInfoValue2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInfoValue);
    }

    /**
     * 信息值
     */
    public void setStInfoValue(String stInfoValue) {
        stInfoValue = StringUtil.substringBySize(stInfoValue, 500, "GB18030");
        this.stInfoValue = stInfoValue;
    }

	/**
     * 展示顺序
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 展示顺序
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
     * 展示顺序
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