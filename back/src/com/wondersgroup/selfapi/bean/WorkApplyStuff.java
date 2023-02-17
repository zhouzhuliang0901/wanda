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
 * 办理申请材料表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WORK_APPLY_STUFF")
public class WorkApplyStuff implements Serializable {
    
    /**
     * 办理申请材料表
     */
    public static final String WORK_APPLY_STUFF = "WORK_APPLY_STUFF";
    
    /**
     * 办理申请材料ID
     */
    public static final String ST_APPLY_STUFF_ID = "ST_APPLY_STUFF_ID";
    
    /**
     * 办理信息编号
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 事项信息
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 材料名称
     */
    public static final String ST_STUFF_NAME = "ST_STUFF_NAME";
    
    /**
     * 原件数
     */
    public static final String NM_ORIGINAL = "NM_ORIGINAL";
    
    /**
     * 复印件数
     */
    public static final String NM_COPY = "NM_COPY";
    
    /**
     * 展示顺序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 材料描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public WorkApplyStuff() {
    }
    
    /**
     * 办理申请材料ID
     */
    @Id
    @Column(name = "ST_APPLY_STUFF_ID")
    private String stApplyStuffId;
    
    /**
     * 办理信息编号
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 事项信息
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 材料名称
     */
    @Column(name = "ST_STUFF_NAME")
    private String stStuffName;
    
    /**
     * 原件数
     */
    @Column(name = "NM_ORIGINAL")
    private BigDecimal nmOriginal;
    
    /**
     * 复印件数
     */
    @Column(name = "NM_COPY")
    private BigDecimal nmCopy;
    
    /**
     * 展示顺序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 材料描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
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
     * 办理申请材料ID
     */
    public String getStApplyStuffId() {
        return this.stApplyStuffId;
    }
    
    /**
     * 办理申请材料ID
     */
    public String stApplyStuffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyStuffId);
    }

    /**
     * 办理申请材料ID
     */
    public void setStApplyStuffId(String stApplyStuffId) {
        stApplyStuffId = StringUtil.substringBySize(stApplyStuffId, 50, "GB18030");
        this.stApplyStuffId = stApplyStuffId;
    }
    
	/**
     * 办理信息编号
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 办理信息编号
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 办理信息编号
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }
    
	/**
     * 事项信息
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 事项信息
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项信息
     */
    public void setStItemId(String stItemId) {
        stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
        this.stItemId = stItemId;
    }
    
	/**
     * 材料名称
     */
    public String getStStuffName() {
        return this.stStuffName;
    }
    
    /**
     * 材料名称
     */
    public String stStuffName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStuffName);
    }

    /**
     * 材料名称
     */
    public void setStStuffName(String stStuffName) {
        stStuffName = StringUtil.substringBySize(stStuffName, 200, "GB18030");
        this.stStuffName = stStuffName;
    }

	/**
     * 原件数
     */
    public BigDecimal getNmOriginal() {
        return this.nmOriginal;
    }
    
    /**
     * 原件数
     */
    public String nmOriginal2Html(int precision) {
        if (this.nmOriginal == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOriginal);
        }
    }

    /**
     * 原件数
     */
    public void setNmOriginal(BigDecimal nmOriginal) {
        this.nmOriginal = nmOriginal;
    }

	/**
     * 复印件数
     */
    public BigDecimal getNmCopy() {
        return this.nmCopy;
    }
    
    /**
     * 复印件数
     */
    public String nmCopy2Html(int precision) {
        if (this.nmCopy == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCopy);
        }
    }

    /**
     * 复印件数
     */
    public void setNmCopy(BigDecimal nmCopy) {
        this.nmCopy = nmCopy;
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
     * 材料描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 材料描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 材料描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 50, "GB18030");
        this.stDesc = stDesc;
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