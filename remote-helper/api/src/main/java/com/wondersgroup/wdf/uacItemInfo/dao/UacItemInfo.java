package com.wondersgroup.wdf.uacItemInfo.dao;

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
 * 事项信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ITEM_INFO")
public class UacItemInfo implements Serializable {
    
    /**
     * 事项信息
     */
    public static final String UAC_ITEM_INFO = "UAC_ITEM_INFO";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";

    /**
     * 表单接入URL
     */
    public static final String ST_FURL = "ST_FURL";
    
    /**
     * 所属事项代码
     */
    public static final String ST_ITEM_CODE = "ST_ITEM_CODE";
    
    /**
     * 所属事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 内部唯一码
     */
    public static final String ST_INNER_NO = "ST_INNER_NO";
    
    /**
     * 继承事项ID
     */
    public static final String ST_INHERIT_ID = "ST_INHERIT_ID";
    
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
     * 展示顺序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 父事项ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * 是否有审批短信
     */
    public static final String NM_SMS = "NM_SMS";
    
    /**
     * 是否全程网办
     */
    public static final String NM_ALL_ONLINE = "NM_ALL_ONLINE";
    
    /**
     * 继承类型
     */
    public static final String NM_INHERIT_TYPE = "NM_INHERIT_TYPE";
    
    /**
     * 业态事项类型
     */
    public static final String ST_TYPE = "ST_TYPE";
    
    /**
     * 证照编码
     */
    public static final String ST_ECERT_CODE = "ST_ECERT_CODE";
    
    /**
     * 证照名称
     */
    public static final String ST_ECERT_NAME = "ST_ECERT_NAME";
    
    /**
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";

    /**
     * 是否即办件，0：否；1：是
     */
    public static final String NM_IMT = "NM_IMT";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";

    /**
     * 小事项
     */
    public static final String ST_TRANSACT_NAME = "ST_TRANSACT_NAME";

    public UacItemInfo() {
    }
    
    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;

    /**
     * 表单接入url
     */
    @Id
    @Column(name = "ST_FURL")
    private String stFurl;

    /**
     * 所属事项代码
     */
    @Column(name = "ST_ITEM_CODE")
    private String stItemCode;


    /**
     * 小事项
     */
    @Column(name = "ST_TRANSACT_NAME")
    private String stTransactName;

    /**
     * 所属事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 内部唯一码
     */
    @Column(name = "ST_INNER_NO")
    private String stInnerNo;
    
    /**
     * 继承事项ID
     */
    @Column(name = "ST_INHERIT_ID")
    private String stInheritId;
    
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
     * 展示顺序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 父事项ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
    /**
     * 是否有审批短信
     */
    @Column(name = "NM_SMS")
    private BigDecimal nmSms;
    
    /**
     * 是否全程网办
     */
    @Column(name = "NM_ALL_ONLINE")
    private BigDecimal nmAllOnline;
    
    /**
     * 继承类型
     */
    @Column(name = "NM_INHERIT_TYPE")
    private BigDecimal nmInheritType;
    
    /**
     * 业态事项类型
     */
    @Column(name = "ST_TYPE")
    private String stType;
    
    /**
     * 证照编码
     */
    @Column(name = "ST_ECERT_CODE")
    private String stEcertCode;
    
    /**
     * 证照名称
     */
    @Column(name = "ST_ECERT_NAME")
    private String stEcertName;
    
    /**
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;

    /**
     * 是否已删除
     */
    @Column(name = "NM_IMT")
    private BigDecimal nmImt;
    
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
     * 表单接入url
     */
    public String getStFurl() {
        return this.stFurl;
    }

    /**
     * 表单接入url
     */
    public String stFurl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFurl);
    }

    /**
     * 表单接入url
     */
    public void setStFurl(String stFurl) {
        stFurl = StringUtil.substringBySize(stFurl, 50, "GB18030");
        this.stFurl = stFurl;
    }
	/**
     * 所属事项代码
     */
    public String getStItemCode() {
        return this.stItemCode;
    }
    
    /**
     * 所属事项代码
     */
    public String stItemCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemCode);
    }

    /**
     * 所属事项代码
     */
    public void setStItemCode(String stItemCode) {
        stItemCode = StringUtil.substringBySize(stItemCode, 50, "GB18030");
        this.stItemCode = stItemCode;
    }

    /**
     * 所属事项代码
     */
    public String getStTransactName() {
        return this.stTransactName;
    }

    /**
     * 所属事项代码
     */
    public void setStTransactName(String stTransactName) {
        stTransactName = StringUtil.substringBySize(stTransactName, 50, "GB18030");
        this.stTransactName = stTransactName;
    }

	/**
     * 所属事项名称
     */
    public String getStItemName() {
        return this.stItemName;
    }
    
    /**
     * 所属事项名称
     */
    public String stItemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemName);
    }

    /**
     * 所属事项名称
     */
    public void setStItemName(String stItemName) {
        stItemName = StringUtil.substringBySize(stItemName, 200, "GB18030");
        this.stItemName = stItemName;
    }
    
	/**
     * 内部唯一码
     */
    public String getStInnerNo() {
        return this.stInnerNo;
    }
    
    /**
     * 内部唯一码
     */
    public String stInnerNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInnerNo);
    }

    /**
     * 内部唯一码
     */
    public void setStInnerNo(String stInnerNo) {
        stInnerNo = StringUtil.substringBySize(stInnerNo, 50, "GB18030");
        this.stInnerNo = stInnerNo;
    }
    
	/**
     * 继承事项ID
     */
    public String getStInheritId() {
        return this.stInheritId;
    }
    
    /**
     * 继承事项ID
     */
    public String stInheritId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInheritId);
    }

    /**
     * 继承事项ID
     */
    public void setStInheritId(String stInheritId) {
        stInheritId = StringUtil.substringBySize(stInheritId, 50, "GB18030");
        this.stInheritId = stInheritId;
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
     * 是否有审批短信
     */
    public BigDecimal getNmSms() {
        return this.nmSms;
    }
    
    /**
     * 是否有审批短信
     */
    public String nmSms2Html(int precision) {
        if (this.nmSms == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSms);
        }
    }

    /**
     * 是否有审批短信
     */
    public void setNmSms(BigDecimal nmSms) {
        this.nmSms = nmSms;
    }

	/**
     * 是否全程网办
     */
    public BigDecimal getNmAllOnline() {
        return this.nmAllOnline;
    }
    
    /**
     * 是否全程网办
     */
    public String nmAllOnline2Html(int precision) {
        if (this.nmAllOnline == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmAllOnline);
        }
    }

    /**
     * 是否全程网办
     */
    public void setNmAllOnline(BigDecimal nmAllOnline) {
        this.nmAllOnline = nmAllOnline;
    }

	/**
     * 继承类型
     */
    public BigDecimal getNmInheritType() {
        return this.nmInheritType;
    }
    
    /**
     * 继承类型
     */
    public String nmInheritType2Html(int precision) {
        if (this.nmInheritType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmInheritType);
        }
    }

    /**
     * 继承类型
     */
    public void setNmInheritType(BigDecimal nmInheritType) {
        this.nmInheritType = nmInheritType;
    }
    
	/**
     * 业态事项类型
     */
    public String getStType() {
        return this.stType;
    }
    
    /**
     * 业态事项类型
     */
    public String stType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stType);
    }

    /**
     * 业态事项类型
     */
    public void setStType(String stType) {
        stType = StringUtil.substringBySize(stType, 50, "GB18030");
        this.stType = stType;
    }
    
	/**
     * 证照编码
     */
    public String getStEcertCode() {
        return this.stEcertCode;
    }
    
    /**
     * 证照编码
     */
    public String stEcertCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEcertCode);
    }

    /**
     * 证照编码
     */
    public void setStEcertCode(String stEcertCode) {
        stEcertCode = StringUtil.substringBySize(stEcertCode, 50, "GB18030");
        this.stEcertCode = stEcertCode;
    }
    
	/**
     * 证照名称
     */
    public String getStEcertName() {
        return this.stEcertName;
    }
    
    /**
     * 证照名称
     */
    public String stEcertName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEcertName);
    }

    /**
     * 证照名称
     */
    public void setStEcertName(String stEcertName) {
        stEcertName = StringUtil.substringBySize(stEcertName, 50, "GB18030");
        this.stEcertName = stEcertName;
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
     * 是否即办件，0：否；1：是
     */
    public BigDecimal getNmImt() {
        return this.nmImt;
    }

    /**
     * 是否即办件，0：否；1：是
     */
    public String nmImt2Html(int precision) {
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
            return new DecimalFormat(pattern).format(this.nmImt);
        }
    }

    /**
     * 是否即办件，0：否；1：是
     */
    public void setNmImt(BigDecimal nmImt) {
        this.nmImt = nmImt;
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