package com.wondersgroup.api.bean;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 模块
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APIDOC_MODULE")
public class ApidocModule implements Serializable {
    
    /**
     * 模块
     */
    public static final String APIDOC_MODULE = "APIDOC_MODULE";
    
    /**
     * 模块ID
     */
    public static final String ST_MODULE_ID = "ST_MODULE_ID";
    
    /**
     * 模块名称
     */
    public static final String ST_MODULE_NAME = "ST_MODULE_NAME";
    
    /**
     * 模块说明
     */
    public static final String ST_REMARK = "ST_REMARK";
    
    /**
     * 项目ID
     */
    public static final String ST_PROJECT_ID = "ST_PROJECT_ID";
    
    /**
     * 排序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 父模块ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public ApidocModule() {
    }
    
    /**
     * 模块ID
     */
    @Id
    @Column(name = "ST_MODULE_ID")
    private String stModuleId;
    
    /**
     * 模块名称
     */
    @Column(name = "ST_MODULE_NAME")
    private String stModuleName;
    
    /**
     * 模块说明
     */
    @Column(name = "ST_REMARK")
    private String stRemark;
    
    /**
     * 项目ID
     */
    @Column(name = "ST_PROJECT_ID")
    private String stProjectId;
    
    /**
     * 排序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 父模块ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
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
     * 模块名称
     */
    public String getStModuleName() {
        return this.stModuleName;
    }
    
    /**
     * 模块名称
     */
    public String stModuleName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stModuleName);
    }

    /**
     * 模块名称
     */
    public void setStModuleName(String stModuleName) {
        stModuleName = StringUtil.substringBySize(stModuleName, 50, "GB18030");
        this.stModuleName = stModuleName;
    }
    
	/**
     * 模块说明
     */
    public String getStRemark() {
        return this.stRemark;
    }
    
    /**
     * 模块说明
     */
    public String stRemark2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRemark);
    }

    /**
     * 模块说明
     */
    public void setStRemark(String stRemark) {
        stRemark = StringUtil.substringBySize(stRemark, 500, "GB18030");
        this.stRemark = stRemark;
    }
    
	/**
     * 项目ID
     */
    public String getStProjectId() {
        return this.stProjectId;
    }
    
    /**
     * 项目ID
     */
    public String stProjectId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stProjectId);
    }

    /**
     * 项目ID
     */
    public void setStProjectId(String stProjectId) {
        stProjectId = StringUtil.substringBySize(stProjectId, 50, "GB18030");
        this.stProjectId = stProjectId;
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
     * 父模块ID
     */
    public String getStParentId() {
        return this.stParentId;
    }
    
    /**
     * 父模块ID
     */
    public String stParentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentId);
    }

    /**
     * 父模块ID
     */
    public void setStParentId(String stParentId) {
        stParentId = StringUtil.substringBySize(stParentId, 50, "GB18030");
        this.stParentId = stParentId;
    }

	/**
     * 创建时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtCreate() {
        return this.dtCreate;
    }
    
    /**
     * 创建时间
     */
    public String dtCreate2Html(String pattern) {
        if (this.dtCreate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtCreate);
        }
    }

    /**
     * 创建时间
     */
    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
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