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
 * 事项表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ITEM")
public class SelmItem implements Serializable {
    
    /**
     * 事项表
     */
    public static final String SELM_ITEM = "SELM_ITEM";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_NO = "ST_ITEM_NO";
    
    /**
     * 其他编码
     */
    public static final String ST_TEN_CODE = "ST_TEN_CODE";
    
    /**
     * 主名称（主事项）
     */
    public static final String ST_MAIN_NAME = "ST_MAIN_NAME";
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 事项所属
     */
    public static final String NM_BELONG = "NM_BELONG";
    
    /**
     * 事项类型
     */
    public static final String ST_ITEM_TYPE = "ST_ITEM_TYPE";
    
    /**
     * 法定时限
     */
    public static final String ST_LEGAL_TIME = "ST_LEGAL_TIME";
    
    /**
     * 承诺时限
     */
    public static final String ST_PROMISE_TIME = "ST_PROMISE_TIME";
    
    /**
     * 所属部门
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 事项分类
     */
    public static final String ST_WORK_TYPE = "ST_WORK_TYPE";
    
    /**
     * 排序
     */
    public static final String NM_SORT = "NM_SORT";
    
    /**
     * 事项办事指南
     */
    public static final String ST_ITEM_GUIDE_ID = "ST_ITEM_GUIDE_ID";
    
    /**
     * 类型
     */
    public static final String NM_TYPE = "NM_TYPE";
    
    /**
     * 父事项ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * 显示类别
     */
    public static final String NM_SHOW_TYPE = "NM_SHOW_TYPE";
    
    /**
     * 办理跳转链接
     */
    public static final String ST_WORK_URL = "ST_WORK_URL";
    
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
    
    /**
     * 扩展字段3
     */
    public static final String ST_EXT3 = "ST_EXT3";
    
    /**
     * 扩展字段4
     */
    public static final String ST_EXT4 = "ST_EXT4";
    
    
    /**
     * 是否删除，0删除，1不删除
     */
    public static final String ST_STATE = "ST_STATE";
    
    public SelmItem() {
    }
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 其他编码
     */
    @Column(name = "ST_TEN_CODE")
    private String stTenCode;
    
    /**
     * 主名称（主事项）
     */
    @Column(name = "ST_MAIN_NAME")
    private String stMainName;
    
    /**
     * 事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 事项所属
     */
    @Column(name = "NM_BELONG")
    private BigDecimal nmBelong;
    
    /**
     * 事项类型
     */
    @Column(name = "ST_ITEM_TYPE")
    private String stItemType;
    
    /**
     * 法定时限
     */
    @Column(name = "ST_LEGAL_TIME")
    private String stLegalTime;
    
    /**
     * 承诺时限
     */
    @Column(name = "ST_PROMISE_TIME")
    private String stPromiseTime;
    
    /**
     * 所属部门
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 事项分类
     */
    @Column(name = "ST_WORK_TYPE")
    private String stWorkType;
    
    /**
     * 排序
     */
    @Column(name = "NM_SORT")
    private BigDecimal nmSort;
    
    /**
     * 事项办事指南
     */
    @Column(name = "ST_ITEM_GUIDE_ID")
    private String stItemGuideId;
    
    /**
     * 类型
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
    /**
     * 父事项ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
    /**
     * 显示类别
     */
    @Column(name = "NM_SHOW_TYPE")
    private BigDecimal nmShowType;
    
    /**
     * 办理跳转链接
     */
    @Column(name = "ST_WORK_URL")
    private String stWorkUrl;
    
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
     * 扩展字段3
     */
    @Column(name = "ST_EXT3")
    private String stExt3;
    
    /**
     * 扩展字段4
     */
    @Column(name = "ST_EXT4")
    private String stExt4;
    
    /**
     * 是否删除
     */
    @Column(name = "ST_STATE")
    private BigDecimal stState;
    
	/**
     * 事项ID
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 事项ID
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项ID
     */
    public void setStItemId(String stItemId) {
        stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
        this.stItemId = stItemId;
    }
    
	/**
     * 事项编码
     */
    public String getStItemNo() {
        return this.stItemNo;
    }
    
    /**
     * 事项编码
     */
    public String stItemNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemNo);
    }

    /**
     * 事项编码
     */
    public void setStItemNo(String stItemNo) {
        stItemNo = StringUtil.substringBySize(stItemNo, 50, "GB18030");
        this.stItemNo = stItemNo;
    }
    
	/**
     * 其他编码
     */
    public String getStTenCode() {
        return this.stTenCode;
    }
    
    /**
     * 其他编码
     */
    public String stTenCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTenCode);
    }

    /**
     * 其他编码
     */
    public void setStTenCode(String stTenCode) {
        stTenCode = StringUtil.substringBySize(stTenCode, 50, "GB18030");
        this.stTenCode = stTenCode;
    }
    
	/**
     * 主名称（主事项）
     */
    public String getStMainName() {
        return this.stMainName;
    }
    
    /**
     * 主名称（主事项）
     */
    public String stMainName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMainName);
    }

    /**
     * 主名称（主事项）
     */
    public void setStMainName(String stMainName) {
        stMainName = StringUtil.substringBySize(stMainName, 200, "GB18030");
        this.stMainName = stMainName;
    }
    
	/**
     * 事项名称
     */
    public String getStItemName() {
        return this.stItemName;
    }
    
    /**
     * 事项名称
     */
    public String stItemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemName);
    }

    /**
     * 事项名称
     */
    public void setStItemName(String stItemName) {
        stItemName = StringUtil.substringBySize(stItemName, 200, "GB18030");
        this.stItemName = stItemName;
    }

	/**
     * 事项所属
     */
    public BigDecimal getNmBelong() {
        return this.nmBelong;
    }
    
    /**
     * 事项所属
     */
    public String nmBelong2Html(int precision) {
        if (this.nmBelong == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmBelong);
        }
    }

    /**
     * 事项所属
     */
    public void setNmBelong(BigDecimal nmBelong) {
        this.nmBelong = nmBelong;
    }
    
	/**
     * 事项类型
     */
    public String getStItemType() {
        return this.stItemType;
    }
    
    /**
     * 事项类型
     */
    public String stItemType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemType);
    }

    /**
     * 事项类型
     */
    public void setStItemType(String stItemType) {
        stItemType = StringUtil.substringBySize(stItemType, 50, "GB18030");
        this.stItemType = stItemType;
    }
    
	/**
     * 法定时限
     */
    public String getStLegalTime() {
        return this.stLegalTime;
    }
    
    /**
     * 法定时限
     */
    public String stLegalTime2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalTime);
    }

    /**
     * 法定时限
     */
    public void setStLegalTime(String stLegalTime) {
        stLegalTime = StringUtil.substringBySize(stLegalTime, 50, "GB18030");
        this.stLegalTime = stLegalTime;
    }
    
	/**
     * 承诺时限
     */
    public String getStPromiseTime() {
        return this.stPromiseTime;
    }
    
    /**
     * 承诺时限
     */
    public String stPromiseTime2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPromiseTime);
    }

    /**
     * 承诺时限
     */
    public void setStPromiseTime(String stPromiseTime) {
        stPromiseTime = StringUtil.substringBySize(stPromiseTime, 50, "GB18030");
        this.stPromiseTime = stPromiseTime;
    }
    
	/**
     * 所属部门
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 所属部门
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 所属部门
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 事项分类
     */
    public String getStWorkType() {
        return this.stWorkType;
    }
    
    /**
     * 事项分类
     */
    public String stWorkType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWorkType);
    }

    /**
     * 事项分类
     */
    public void setStWorkType(String stWorkType) {
        stWorkType = StringUtil.substringBySize(stWorkType, 50, "GB18030");
        this.stWorkType = stWorkType;
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
     * 事项办事指南
     */
    public String getStItemGuideId() {
        return this.stItemGuideId;
    }
    
    /**
     * 事项办事指南
     */
    public String stItemGuideId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemGuideId);
    }

    /**
     * 事项办事指南
     */
    public void setStItemGuideId(String stItemGuideId) {
        stItemGuideId = StringUtil.substringBySize(stItemGuideId, 50, "GB18030");
        this.stItemGuideId = stItemGuideId;
    }

	/**
     * 类型
     */
    public BigDecimal getNmType() {
        return this.nmType;
    }
    
    /**
     * 类型
     */
    public String nmType2Html(int precision) {
        if (this.nmType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmType);
        }
    }

    /**
     * 类型
     */
    public void setNmType(BigDecimal nmType) {
        this.nmType = nmType;
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
     * 显示类别
     */
    public BigDecimal getNmShowType() {
        return this.nmShowType;
    }
    
    /**
     * 显示类别
     */
    public String nmShowType2Html(int precision) {
        if (this.nmShowType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmShowType);
        }
    }

    /**
     * 显示类别
     */
    public void setNmShowType(BigDecimal nmShowType) {
        this.nmShowType = nmShowType;
    }
    
	/**
     * 办理跳转链接
     */
    public String getStWorkUrl() {
        return this.stWorkUrl;
    }
    
    /**
     * 办理跳转链接
     */
    public String stWorkUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWorkUrl);
    }

    /**
     * 办理跳转链接
     */
    public void setStWorkUrl(String stWorkUrl) {
        stWorkUrl = StringUtil.substringBySize(stWorkUrl, 200, "GB18030");
        this.stWorkUrl = stWorkUrl;
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
    
	/**
     * 扩展字段3
     */
    public String getStExt3() {
        return this.stExt3;
    }
    
    /**
     * 扩展字段3
     */
    public String stExt32Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt3);
    }

    /**
     * 扩展字段3
     */
    public void setStExt3(String stExt3) {
        stExt3 = StringUtil.substringBySize(stExt3, 100, "GB18030");
        this.stExt3 = stExt3;
    }
    
	/**
     * 扩展字段4
     */
    public String getStExt4() {
        return this.stExt4;
    }
    
    /**
     * 扩展字段4
     */
    public String stExt42Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt4);
    }

    /**
     * 扩展字段4
     */
    public void setStExt4(String stExt4) {
        stExt4 = StringUtil.substringBySize(stExt4, 200, "GB18030");
        this.stExt4 = stExt4;
    }
    
    /**
     * 是否删除
     */
    public BigDecimal getStState() {
        return this.stState;
    }
    
    /**
     * 是否删除
     */
    public String stState2Html(int precision) {
        if (this.stState == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.stState);
        }
    }

    /**
     * 是否删除
     */
    public void setStState(BigDecimal stState) {
        this.stState = stState;
    }
    

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}