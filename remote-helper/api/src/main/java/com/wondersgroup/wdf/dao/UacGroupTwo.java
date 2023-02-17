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
 * 事项组
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_GROUP")
public class UacGroupTwo implements Serializable {
    
    /**
     * 事项组
     */
    public static final String UAC_GROUP = "UAC_GROUP";
    
    /**
     * 事项组ID
     */
    public static final String ST_GROUP_ID = "ST_GROUP_ID";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 事项组编码
     */
    public static final String ST_GROUP_CODE = "ST_GROUP_CODE";
    
    /**
     * 事项组名称
     */
    public static final String ST_GROUP_NAME = "ST_GROUP_NAME";
    
    /**
     * 排序字段
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 修改时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 组描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public UacGroupTwo() {
    }
    
    /**
     * 事项组ID
     */
    @Id
    @Column(name = "ST_GROUP_ID")
    private String stGroupId;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 事项组编码
     */
    @Column(name = "ST_GROUP_CODE")
    private String stGroupCode;
    
    /**
     * 事项组名称
     */
    @Column(name = "ST_GROUP_NAME")
    private String stGroupName;
    
    /**
     * 排序字段
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 修改时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 组描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;
    
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
     * 区域ID
     */
    public String getStAreaId() {
        return this.stAreaId;
    }
    
    /**
     * 区域ID
     */
    public String stAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaId);
    }

    /**
     * 区域ID
     */
    public void setStAreaId(String stAreaId) {
        stAreaId = StringUtil.substringBySize(stAreaId, 50, "GB18030");
        this.stAreaId = stAreaId;
    }
    
	/**
     * 事项组编码
     */
    public String getStGroupCode() {
        return this.stGroupCode;
    }
    
    /**
     * 事项组编码
     */
    public String stGroupCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stGroupCode);
    }

    /**
     * 事项组编码
     */
    public void setStGroupCode(String stGroupCode) {
        stGroupCode = StringUtil.substringBySize(stGroupCode, 50, "GB18030");
        this.stGroupCode = stGroupCode;
    }
    
	/**
     * 事项组名称
     */
    public String getStGroupName() {
        return this.stGroupName;
    }
    
    /**
     * 事项组名称
     */
    public String stGroupName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stGroupName);
    }

    /**
     * 事项组名称
     */
    public void setStGroupName(String stGroupName) {
        stGroupName = StringUtil.substringBySize(stGroupName, 50, "GB18030");
        this.stGroupName = stGroupName;
    }

	/**
     * 排序字段
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序字段
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
     * 排序字段
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
     * 修改时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 修改时间
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
     * 修改时间
     */
    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
    
	/**
     * 组描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 组描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 组描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 50, "GB18030");
        this.stDesc = stDesc;
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