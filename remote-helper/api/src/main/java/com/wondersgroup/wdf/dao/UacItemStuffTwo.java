package com.wondersgroup.wdf.dao;

import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.service.util.StringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 事项材料
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ITEM_STUFF")
public class UacItemStuffTwo implements Serializable {
    
    /**
     * 事项材料
     */
    public static final String UAC_ITEM_STUFF = "UAC_ITEM_STUFF";
    
    /**
     * 事项材料ID
     */
    public static final String ST_ITEM_STUFF_ID = "ST_ITEM_STUFF_ID";
    
    /**
     * 申请材料ID
     */
    public static final String ST_STUFF_ID = "ST_STUFF_ID";
    
    /**
     * 综合事项ID
     */
    public static final String ST_CITEM_ID = "ST_CITEM_ID";
    
    /**
     * 所属事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 材料归属部门ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 材料归属部门代码
     */
    public static final String ST_DEPART_CODE = "ST_DEPART_CODE";
    
    /**
     * 材料归属部门名称
     */
    public static final String ST_DEPART_NAME = "ST_DEPART_NAME";
    
    /**
     * 是否必须
     */
    public static final String NM_MUST = "NM_MUST";
    
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
    public static final String NM_ORDER2 = "NM_ORDER2";
    
    /**
     * 是否免交
     */
    public static final String NM_OVER = "NM_OVER";
    
    /**
     * 是否需要样张
     */
    public static final String NM_SAMPLE = "NM_SAMPLE";
    
    /**
     * 是否需要拍照上传
     */
    public static final String NM_UPLOAD = "NM_UPLOAD";
    
    /**
     * 材料展示
     */
    public static final String ST_SHOW = "ST_SHOW";
    
    /**
     * 材料形式
     */
    public static final String ST_NTYPE = "ST_NTYPE";
    
    /**
     * 材料别名
     */
    public static final String ST_LAB_NAME = "ST_LAB_NAME";
    
    /**
     * 材料来源
     */
    public static final String ST_SOURCE = "ST_SOURCE";
    
    /**
     * 备注
     */
    public static final String ST_REMARK = "ST_REMARK";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";
    
    public UacItemStuffTwo() {
    }
    
    /**
     * 事项材料ID
     */
    @Id
    @Column(name = "ST_ITEM_STUFF_ID")
    private String stItemStuffId;
    
    /**
     * 申请材料ID
     */
    @Column(name = "ST_STUFF_ID")
    private String stStuffId;
    
    /**
     * 综合事项ID
     */
    @Column(name = "ST_CITEM_ID")
    private String stCitemId;
    
    /**
     * 所属事项ID
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 材料归属部门ID
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 材料归属部门代码
     */
    @Column(name = "ST_DEPART_CODE")
    private String stDepartCode;
    
    /**
     * 材料归属部门名称
     */
    @Column(name = "ST_DEPART_NAME")
    private String stDepartName;
    
    /**
     * 是否必须
     */
    @Column(name = "NM_MUST")
    private BigDecimal nmMust;
    
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
    @Column(name = "NM_ORDER2")
    private BigDecimal nmOrder2;
    
    /**
     * 是否免交
     */
    @Column(name = "NM_OVER")
    private BigDecimal nmOver;
    
    /**
     * 是否需要样张
     */
    @Column(name = "NM_SAMPLE")
    private BigDecimal nmSample;
    
    /**
     * 是否需要拍照上传
     */
    @Column(name = "NM_UPLOAD")
    private BigDecimal nmUpload;
    
    /**
     * 材料展示
     */
    @Column(name = "ST_SHOW")
    private String stShow;
    
    /**
     * 材料形式
     */
    @Column(name = "ST_NTYPE")
    private String stNtype;
    
    /**
     * 材料别名
     */
    @Column(name = "ST_LAB_NAME")
    private String stLabName;
    
    /**
     * 材料来源
     */
    @Column(name = "ST_SOURCE")
    private String stSource;
    
    /**
     * 备注
     */
    @Column(name = "ST_REMARK")
    private String stRemark;
    
    /**
     * 序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;
    
	/**
     * 事项材料ID
     */
    public String getStItemStuffId() {
        return this.stItemStuffId;
    }
    
    /**
     * 事项材料ID
     */
    public String stItemStuffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemStuffId);
    }

    /**
     * 事项材料ID
     */
    public void setStItemStuffId(String stItemStuffId) {
        stItemStuffId = StringUtil.substringBySize(stItemStuffId, 50, "GB18030");
        this.stItemStuffId = stItemStuffId;
    }
    
	/**
     * 申请材料ID
     */
    public String getStStuffId() {
        return this.stStuffId;
    }
    
    /**
     * 申请材料ID
     */
    public String stStuffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStuffId);
    }

    /**
     * 申请材料ID
     */
    public void setStStuffId(String stStuffId) {
        stStuffId = StringUtil.substringBySize(stStuffId, 50, "GB18030");
        this.stStuffId = stStuffId;
    }
    
	/**
     * 综合事项ID
     */
    public String getStCitemId() {
        return this.stCitemId;
    }
    
    /**
     * 综合事项ID
     */
    public String stCitemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemId);
    }

    /**
     * 综合事项ID
     */
    public void setStCitemId(String stCitemId) {
        stCitemId = StringUtil.substringBySize(stCitemId, 50, "GB18030");
        this.stCitemId = stCitemId;
    }
    
	/**
     * 所属事项ID
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 所属事项ID
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 所属事项ID
     */
    public void setStItemId(String stItemId) {
        stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
        this.stItemId = stItemId;
    }
    
	/**
     * 材料归属部门ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 材料归属部门ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 材料归属部门ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 材料归属部门代码
     */
    public String getStDepartCode() {
        return this.stDepartCode;
    }
    
    /**
     * 材料归属部门代码
     */
    public String stDepartCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartCode);
    }

    /**
     * 材料归属部门代码
     */
    public void setStDepartCode(String stDepartCode) {
        stDepartCode = StringUtil.substringBySize(stDepartCode, 50, "GB18030");
        this.stDepartCode = stDepartCode;
    }
    
	/**
     * 材料归属部门名称
     */
    public String getStDepartName() {
        return this.stDepartName;
    }
    
    /**
     * 材料归属部门名称
     */
    public String stDepartName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartName);
    }

    /**
     * 材料归属部门名称
     */
    public void setStDepartName(String stDepartName) {
        stDepartName = StringUtil.substringBySize(stDepartName, 50, "GB18030");
        this.stDepartName = stDepartName;
    }

	/**
     * 是否必须
     */
    public BigDecimal getNmMust() {
        return this.nmMust;
    }
    
    /**
     * 是否必须
     */
    public String nmMust2Html(int precision) {
        if (this.nmMust == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmMust);
        }
    }

    /**
     * 是否必须
     */
    public void setNmMust(BigDecimal nmMust) {
        this.nmMust = nmMust;
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
    public BigDecimal getNmOrder2() {
        return this.nmOrder2;
    }
    
    /**
     * 展示顺序
     */
    public String nmOrder22Html(int precision) {
        if (this.nmOrder2 == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOrder2);
        }
    }

    /**
     * 展示顺序
     */
    public void setNmOrder2(BigDecimal nmOrder2) {
        this.nmOrder2 = nmOrder2;
    }

	/**
     * 是否免交
     */
    public BigDecimal getNmOver() {
        return this.nmOver;
    }
    
    /**
     * 是否免交
     */
    public String nmOver2Html(int precision) {
        if (this.nmOver == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOver);
        }
    }

    /**
     * 是否免交
     */
    public void setNmOver(BigDecimal nmOver) {
        this.nmOver = nmOver;
    }

	/**
     * 是否需要样张
     */
    public BigDecimal getNmSample() {
        return this.nmSample;
    }
    
    /**
     * 是否需要样张
     */
    public String nmSample2Html(int precision) {
        if (this.nmSample == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSample);
        }
    }

    /**
     * 是否需要样张
     */
    public void setNmSample(BigDecimal nmSample) {
        this.nmSample = nmSample;
    }

	/**
     * 是否需要拍照上传
     */
    public BigDecimal getNmUpload() {
        return this.nmUpload;
    }
    
    /**
     * 是否需要拍照上传
     */
    public String nmUpload2Html(int precision) {
        if (this.nmUpload == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmUpload);
        }
    }

    /**
     * 是否需要拍照上传
     */
    public void setNmUpload(BigDecimal nmUpload) {
        this.nmUpload = nmUpload;
    }
    
	/**
     * 材料展示
     */
    public String getStShow() {
        return this.stShow;
    }
    
    /**
     * 材料展示
     */
    public String stShow2Html() {
        return StringHelper.replaceHTMLSymbol(this.stShow);
    }

    /**
     * 材料展示
     */
    public void setStShow(String stShow) {
        stShow = StringUtil.substringBySize(stShow, 50, "GB18030");
        this.stShow = stShow;
    }
    
	/**
     * 材料形式
     */
    public String getStNtype() {
        return this.stNtype;
    }
    
    /**
     * 材料形式
     */
    public String stNtype2Html() {
        return StringHelper.replaceHTMLSymbol(this.stNtype);
    }

    /**
     * 材料形式
     */
    public void setStNtype(String stNtype) {
        stNtype = StringUtil.substringBySize(stNtype, 50, "GB18030");
        this.stNtype = stNtype;
    }
    
	/**
     * 材料别名
     */
    public String getStLabName() {
        return this.stLabName;
    }
    
    /**
     * 材料别名
     */
    public String stLabName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLabName);
    }

    /**
     * 材料别名
     */
    public void setStLabName(String stLabName) {
        stLabName = StringUtil.substringBySize(stLabName, 200, "GB18030");
        this.stLabName = stLabName;
    }
    
	/**
     * 材料来源
     */
    public String getStSource() {
        return this.stSource;
    }
    
    /**
     * 材料来源
     */
    public String stSource2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSource);
    }

    /**
     * 材料来源
     */
    public void setStSource(String stSource) {
        stSource = StringUtil.substringBySize(stSource, 50, "GB18030");
        this.stSource = stSource;
    }
    
	/**
     * 备注
     */
    public String getStRemark() {
        return this.stRemark;
    }
    
    /**
     * 备注
     */
    public String stRemark2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRemark);
    }

    /**
     * 备注
     */
    public void setStRemark(String stRemark) {
        stRemark = StringUtil.substringBySize(stRemark, 500, "GB18030");
        this.stRemark = stRemark;
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

	/**
     * 是否已删除
     */
    public BigDecimal getNmRemoved() {
        return this.nmRemoved;
    }
    
    /**
     * 是否已删除
     */
    public String nmRemoved2Html(int precision) {
        if (this.nmRemoved == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmRemoved);
        }
    }

    /**
     * 是否已删除
     */
    public void setNmRemoved(BigDecimal nmRemoved) {
        this.nmRemoved = nmRemoved;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}