package com.wondersgroup.outdevicestatus.bean;
		
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

import reindeer.base.utils.StringUtil;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;

/**
 * 设备（外设）分类
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_DEVICE_TYPE")
public class InfopubDeviceType implements Serializable {
    
    /**
     * 设备（外设）分类
     */
    public static final String INFOPUB_DEVICE_TYPE = "INFOPUB_DEVICE_TYPE";
    
    /**
     * 类型ID
     */
    public static final String ST_TYPE_ID = "ST_TYPE_ID";
    
    /**
     * 分类名称
     */
    public static final String ST_TYPE_NAME = "ST_TYPE_NAME";
    
    /**
     * 分类代码
     */
    public static final String ST_TYPE_CODE = "ST_TYPE_CODE";
    
    /**
     * 设备图标
     */
    public static final String ST_ICON = "ST_ICON";
    
    /**
     * 设备样式
     */
    public static final String ST_CLASS = "ST_CLASS";
    
    /**
     * 父类型
     */
    public static final String ST_PARENT_TYPE_ID = "ST_PARENT_TYPE_ID";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 关机类型
     */
   // public static final String ST_SHUTDOWN_TYPE = "ST_SHUTDOWN_TYPE";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 空间ID
     */
    //public static final String ST_WORKSPACE_ID = "ST_WORKSPACE_ID";
    
    public InfopubDeviceType() {
    }
    
    /**
     * 类型ID
     */
    @Id
    @Column(name = "ST_TYPE_ID")
    private String stTypeId;
    
    /**
     * 分类名称
     */
    @Column(name = "ST_TYPE_NAME")
    private String stTypeName;
    
    /**
     * 分类代码
     */
    @Column(name = "ST_TYPE_CODE")
    private String stTypeCode;
    
    /**
     * 设备图标
     */
    @Column(name = "ST_ICON")
    private String stIcon;
    
    /**
     * 设备样式
     */
    @Column(name = "ST_CLASS")
    private String stClass;
    
    /**
     * 父类型
     */
    @Column(name = "ST_PARENT_TYPE_ID")
    private String stParentTypeId;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 更新时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 关机类型
     */
   /* @Column(name = "ST_SHUTDOWN_TYPE")
    private String stShutdownType;
    */
    /**
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 空间ID
     */
  /*  @Column(name = "ST_WORKSPACE_ID")
    private String stWorkspaceId;
    */
	/**
     * 类型ID
     */
    public String getStTypeId() {
        return this.stTypeId;
    }
    
    /**
     * 类型ID
     */
    public String stTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTypeId);
    }

    /**
     * 类型ID
     */
    public void setStTypeId(String stTypeId) {
        stTypeId = StringUtil.substringBySize(stTypeId, 50, "GB18030");
        this.stTypeId = stTypeId;
    }
    
	/**
     * 分类名称
     */
    public String getStTypeName() {
        return this.stTypeName;
    }
    
    /**
     * 分类名称
     */
    public String stTypeName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTypeName);
    }

    /**
     * 分类名称
     */
    public void setStTypeName(String stTypeName) {
        stTypeName = StringUtil.substringBySize(stTypeName, 50, "GB18030");
        this.stTypeName = stTypeName;
    }
    
	/**
     * 分类代码
     */
    public String getStTypeCode() {
        return this.stTypeCode;
    }
    
    /**
     * 分类代码
     */
    public String stTypeCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTypeCode);
    }

    /**
     * 分类代码
     */
    public void setStTypeCode(String stTypeCode) {
        stTypeCode = StringUtil.substringBySize(stTypeCode, 50, "GB18030");
        this.stTypeCode = stTypeCode;
    }
    
	/**
     * 设备图标
     */
    public String getStIcon() {
        return this.stIcon;
    }
    
    /**
     * 设备图标
     */
    public String stIcon2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIcon);
    }

    /**
     * 设备图标
     */
    public void setStIcon(String stIcon) {
        stIcon = StringUtil.substringBySize(stIcon, 50, "GB18030");
        this.stIcon = stIcon;
    }
    
	/**
     * 设备样式
     */
    public String getStClass() {
        return this.stClass;
    }
    
    /**
     * 设备样式
     */
    public String stClass2Html() {
        return StringHelper.replaceHTMLSymbol(this.stClass);
    }

    /**
     * 设备样式
     */
    public void setStClass(String stClass) {
        stClass = StringUtil.substringBySize(stClass, 50, "GB18030");
        this.stClass = stClass;
    }
    
	/**
     * 父类型
     */
    public String getStParentTypeId() {
        return this.stParentTypeId;
    }
    
    /**
     * 父类型
     */
    public String stParentTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentTypeId);
    }

    /**
     * 父类型
     */
    public void setStParentTypeId(String stParentTypeId) {
        stParentTypeId = StringUtil.substringBySize(stParentTypeId, 50, "GB18030");
        this.stParentTypeId = stParentTypeId;
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
     * 更新时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 更新时间
     */
    public String dtUpdate2Html(String pattern) {
        if (this.dtUpdate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtUpdate);
        }
    }

    /**
     * 更新时间
     */
    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
   /* 
	*//**
     * 关机类型
     *//*
    public String getStShutdownType() {
        return this.stShutdownType;
    }
    
    *//**
     * 关机类型
     *//*
    public String stShutdownType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stShutdownType);
    }

    *//**
     * 关机类型
     *//*
    public void setStShutdownType(String stShutdownType) {
        stShutdownType = StringUtil.substringBySize(stShutdownType, 50, "GB18030");
        this.stShutdownType = stShutdownType;
    }
    */
	/**
     * 备注
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 备注
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 备注
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }
  /*  
	*//**
     * 空间ID
     *//*
    public String getStWorkspaceId() {
        return this.stWorkspaceId;
    }
    
    *//**
     * 空间ID
     *//*
    public String stWorkspaceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWorkspaceId);
    }

    *//**
     * 空间ID
     *//*
    public void setStWorkspaceId(String stWorkspaceId) {
        stWorkspaceId = StringUtil.substringBySize(stWorkspaceId, 50, "GB18030");
        this.stWorkspaceId = stWorkspaceId;
    }
*/
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}