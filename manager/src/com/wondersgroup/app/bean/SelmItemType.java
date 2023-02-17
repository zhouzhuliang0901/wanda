package com.wondersgroup.app.bean;
		
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
 * 事项类别
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ITEM_TYPE")
public class SelmItemType implements Serializable {
    
    /**
     * 事项类别
     */
    public static final String SELM_ITEM_TYPE = "SELM_ITEM_TYPE";
    
    /**
     * 事项类别ID
     */
    public static final String ST_ITEM_TYPE_ID = "ST_ITEM_TYPE_ID";
    
    /**
     * 事项类别名称
     */
    public static final String ST_ITEM_TYPE_NAME = "ST_ITEM_TYPE_NAME";
    
    /**
     * 排序
     */
    public static final String NM_SORT = "NM_SORT";
    
    /**
     * 父事项ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SelmItemType() {
    }
    
    /**
     * 事项类别ID
     */
    @Id
    @Column(name = "ST_ITEM_TYPE_ID")
    private String stItemTypeId;
    
    /**
     * 事项类别名称
     */
    @Column(name = "ST_ITEM_TYPE_NAME")
    private String stItemTypeName;
    
    /**
     * 排序
     */
    @Column(name = "NM_SORT")
    private BigDecimal nmSort;
    
    /**
     * 父事项ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
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
     * 事项类别ID
     */
    public String getStItemTypeId() {
        return this.stItemTypeId;
    }
    
    /**
     * 事项类别ID
     */
    public String stItemTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemTypeId);
    }

    /**
     * 事项类别ID
     */
    public void setStItemTypeId(String stItemTypeId) {
        stItemTypeId = StringUtil.substringBySize(stItemTypeId, 50, "GB18030");
        this.stItemTypeId = stItemTypeId;
    }
    
	/**
     * 事项类别名称
     */
    public String getStItemTypeName() {
        return this.stItemTypeName;
    }
    
    /**
     * 事项类别名称
     */
    public String stItemTypeName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemTypeName);
    }

    /**
     * 事项类别名称
     */
    public void setStItemTypeName(String stItemTypeName) {
        stItemTypeName = StringUtil.substringBySize(stItemTypeName, 50, "GB18030");
        this.stItemTypeName = stItemTypeName;
    }

	/**
     * 排序
     */
    public BigDecimal getNmSort() {
        return this.nmSort;
    }
    
    /**
     * 排序
     */
    public String nmSort2Html(int precision) {
        if (this.nmSort == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSort);
        }
    }

    /**
     * 排序
     */
    public void setNmSort(BigDecimal nmSort) {
        this.nmSort = nmSort;
    }
    
	/**
     * 父事项ID
     */
    public String getStParentId() {
        return this.stParentId;
    }
    
    /**
     * 父事项ID
     */
    public String stParentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentId);
    }

    /**
     * 父事项ID
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