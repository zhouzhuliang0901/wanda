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
 * 主题事项（综合）
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ITEMS")
public class UacItemsTwo implements Serializable {
    
    /**
     * 主题事项（综合）
     */
    public static final String UAC_ITEMS = "UAC_ITEMS";
    
    /**
     * 主题ID
     */
    public static final String ST_ITEMS_ID = "ST_ITEMS_ID";
    
    /**
     * 主题编码
     */
    public static final String ST_ITEMS_CODE = "ST_ITEMS_CODE";
    
    /**
     * 主题名称
     */
    public static final String ST_ITEMS_NAME = "ST_ITEMS_NAME";
    
    /**
     * 描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 父主题ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * PC入口页面
     */
    public static final String ST_PC_URL = "ST_PC_URL";
    
    /**
     * 移动端入口页面
     */
    public static final String ST_APP_URL = "ST_APP_URL";
    
    /**
     * 自助终端入口页面
     */
    public static final String ST_T_URL = "ST_T_URL";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 区域代码
     */
    public static final String ST_AREA_CODE = "ST_AREA_CODE";
    
    /**
     * 部门ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 部门代码
     */
    public static final String ST_DEPART_CODE = "ST_DEPART_CODE";
    
    /**
     * 部门名称
     */
    public static final String ST_DEPART_NAME = "ST_DEPART_NAME";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 业态类型
     */
    public static final String ST_TYPE = "ST_TYPE";
    
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
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";
    
    public UacItemsTwo() {
    }
    
    /**
     * 主题ID
     */
    @Id
    @Column(name = "ST_ITEMS_ID")
    private String stItemsId;
    
    /**
     * 主题编码
     */
    @Column(name = "ST_ITEMS_CODE")
    private String stItemsCode;
    
    /**
     * 主题名称
     */
    @Column(name = "ST_ITEMS_NAME")
    private String stItemsName;
    
    /**
     * 描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 父主题ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
    /**
     * PC入口页面
     */
    @Column(name = "ST_PC_URL")
    private String stPcUrl;
    
    /**
     * 移动端入口页面
     */
    @Column(name = "ST_APP_URL")
    private String stAppUrl;
    
    /**
     * 自助终端入口页面
     */
    @Column(name = "ST_T_URL")
    private String stTUrl;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 区域代码
     */
    @Column(name = "ST_AREA_CODE")
    private String stAreaCode;
    
    /**
     * 部门ID
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 部门代码
     */
    @Column(name = "ST_DEPART_CODE")
    private String stDepartCode;
    
    /**
     * 部门名称
     */
    @Column(name = "ST_DEPART_NAME")
    private String stDepartName;
    
    /**
     * 序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 业态类型
     */
    @Column(name = "ST_TYPE")
    private String stType;
    
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
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;
    
	/**
     * 主题ID
     */
    public String getStItemsId() {
        return this.stItemsId;
    }
    
    /**
     * 主题ID
     */
    public String stItemsId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsId);
    }

    /**
     * 主题ID
     */
    public void setStItemsId(String stItemsId) {
        stItemsId = StringUtil.substringBySize(stItemsId, 50, "GB18030");
        this.stItemsId = stItemsId;
    }
    
	/**
     * 主题编码
     */
    public String getStItemsCode() {
        return this.stItemsCode;
    }
    
    /**
     * 主题编码
     */
    public String stItemsCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsCode);
    }

    /**
     * 主题编码
     */
    public void setStItemsCode(String stItemsCode) {
        stItemsCode = StringUtil.substringBySize(stItemsCode, 50, "GB18030");
        this.stItemsCode = stItemsCode;
    }
    
	/**
     * 主题名称
     */
    public String getStItemsName() {
        return this.stItemsName;
    }
    
    /**
     * 主题名称
     */
    public String stItemsName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsName);
    }

    /**
     * 主题名称
     */
    public void setStItemsName(String stItemsName) {
        stItemsName = StringUtil.substringBySize(stItemsName, 50, "GB18030");
        this.stItemsName = stItemsName;
    }
    
	/**
     * 描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }

	/**
     * 状态
     */
    public BigDecimal getNmStatus() {
        return this.nmStatus;
    }
    
    /**
     * 状态
     */
    public String nmStatus2Html(int precision) {
        if (this.nmStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmStatus);
        }
    }

    /**
     * 状态
     */
    public void setNmStatus(BigDecimal nmStatus) {
        this.nmStatus = nmStatus;
    }
    
	/**
     * 父主题ID
     */
    public String getStParentId() {
        return this.stParentId;
    }
    
    /**
     * 父主题ID
     */
    public String stParentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentId);
    }

    /**
     * 父主题ID
     */
    public void setStParentId(String stParentId) {
        stParentId = StringUtil.substringBySize(stParentId, 50, "GB18030");
        this.stParentId = stParentId;
    }
    
	/**
     * PC入口页面
     */
    public String getStPcUrl() {
        return this.stPcUrl;
    }
    
    /**
     * PC入口页面
     */
    public String stPcUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPcUrl);
    }

    /**
     * PC入口页面
     */
    public void setStPcUrl(String stPcUrl) {
        stPcUrl = StringUtil.substringBySize(stPcUrl, 200, "GB18030");
        this.stPcUrl = stPcUrl;
    }
    
	/**
     * 移动端入口页面
     */
    public String getStAppUrl() {
        return this.stAppUrl;
    }
    
    /**
     * 移动端入口页面
     */
    public String stAppUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAppUrl);
    }

    /**
     * 移动端入口页面
     */
    public void setStAppUrl(String stAppUrl) {
        stAppUrl = StringUtil.substringBySize(stAppUrl, 200, "GB18030");
        this.stAppUrl = stAppUrl;
    }
    
	/**
     * 自助终端入口页面
     */
    public String getStTUrl() {
        return this.stTUrl;
    }
    
    /**
     * 自助终端入口页面
     */
    public String stTUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTUrl);
    }

    /**
     * 自助终端入口页面
     */
    public void setStTUrl(String stTUrl) {
        stTUrl = StringUtil.substringBySize(stTUrl, 200, "GB18030");
        this.stTUrl = stTUrl;
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
     * 区域代码
     */
    public String getStAreaCode() {
        return this.stAreaCode;
    }
    
    /**
     * 区域代码
     */
    public String stAreaCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaCode);
    }

    /**
     * 区域代码
     */
    public void setStAreaCode(String stAreaCode) {
        stAreaCode = StringUtil.substringBySize(stAreaCode, 50, "GB18030");
        this.stAreaCode = stAreaCode;
    }
    
	/**
     * 部门ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 部门ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 部门ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 部门代码
     */
    public String getStDepartCode() {
        return this.stDepartCode;
    }
    
    /**
     * 部门代码
     */
    public String stDepartCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartCode);
    }

    /**
     * 部门代码
     */
    public void setStDepartCode(String stDepartCode) {
        stDepartCode = StringUtil.substringBySize(stDepartCode, 50, "GB18030");
        this.stDepartCode = stDepartCode;
    }
    
	/**
     * 部门名称
     */
    public String getStDepartName() {
        return this.stDepartName;
    }
    
    /**
     * 部门名称
     */
    public String stDepartName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartName);
    }

    /**
     * 部门名称
     */
    public void setStDepartName(String stDepartName) {
        stDepartName = StringUtil.substringBySize(stDepartName, 50, "GB18030");
        this.stDepartName = stDepartName;
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
     * 业态类型
     */
    public String getStType() {
        return this.stType;
    }
    
    /**
     * 业态类型
     */
    public String stType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stType);
    }

    /**
     * 业态类型
     */
    public void setStType(String stType) {
        stType = StringUtil.substringBySize(stType, 50, "GB18030");
        this.stType = stType;
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