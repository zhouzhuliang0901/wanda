package com.wondersgroup.wdf.dao;

import com.alibaba.excel.annotation.ExcelIgnore;
import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.service.util.StringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 企业信息表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_BUSINESS")
public class UacBusiness implements Serializable {
    
    /**
     * 企业信息表
     */
    public static final String UAC_BUSINESS = "UAC_BUSINESS";
    
    /**
     * 企业信息ID
     */
    public static final String ST_BUSINESS_ID = "ST_BUSINESS_ID";
    
    /**
     * 企业名称
     */
    public static final String ST_BUSINESS_NAME = "ST_BUSINESS_NAME";
    
    /**
     * 统一社会信用代码
     */
    public static final String ST_CORPORATION_ORGID = "ST_CORPORATION_ORGID";
    
    /**
     * 法人姓名
     */
    public static final String ST_LEGAL_NAME = "ST_LEGAL_NAME";
    
    /**
     * 企业地址
     */
    public static final String ST_BUSINESS_ADDRESS = "ST_BUSINESS_ADDRESS";
    
    /**
     * 经办人姓名
     */
    public static final String ST_APPLY_USER_NAME = "ST_APPLY_USER_NAME";
    
    /**
     * 经办人手机号
     */
    public static final String ST_APPLY_USER_PHONE = "ST_APPLY_USER_PHONE";
    
    /**
     * 经办人身份证号码
     */
    public static final String ST_APPLY_USER_IDCARD = "ST_APPLY_USER_IDCARD";
    
    /**
     * 法人手机号
     */
    public static final String ST_LEGAL_PHONE = "ST_LEGAL_PHONE";
    
    /**
     * 法人身份证号码
     */
    public static final String ST_LEGAL_IDCARD = "ST_LEGAL_IDCARD";
    
    public UacBusiness() {
    }
    
    /**
     * 企业信息ID
     */
    @Id
    @Column(name = "ST_BUSINESS_ID")
    @ExcelIgnore
    private String stBusinessId;
    
    /**
     * 企业名称
     */
    @Column(name = "ST_BUSINESS_NAME")

    private String stBusinessName;
    
    /**
     * 统一社会信用代码
     */
    @Column(name = "ST_CORPORATION_ORGID")
    private String stCorporationOrgid;
    
    /**
     * 法人姓名
     */
    @Column(name = "ST_LEGAL_NAME")
    private String stLegalName;
    
    /**
     * 企业地址
     */
    @Column(name = "ST_BUSINESS_ADDRESS")
    private String stBusinessAddress;
    
    /**
     * 经办人姓名
     */
    @Column(name = "ST_APPLY_USER_NAME")
    private String stApplyUserName;
    
    /**
     * 经办人手机号
     */
    @Column(name = "ST_APPLY_USER_PHONE")
    private String stApplyUserPhone;
    
    /**
     * 经办人身份证号码
     */
    @Column(name = "ST_APPLY_USER_IDCARD")
    private String stApplyUserIdcard;
    
    /**
     * 法人手机号
     */
    @Column(name = "ST_LEGAL_PHONE")
    private String stLegalPhone;
    
    /**
     * 法人身份证号码
     */
    @Column(name = "ST_LEGAL_IDCARD")
    private String stLegalIdcard;
    
	/**
     * 企业信息ID
     */
    public String getStBusinessId() {
        return this.stBusinessId;
    }
    
    /**
     * 企业信息ID
     */
    public String stBusinessId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessId);
    }

    /**
     * 企业信息ID
     */
    public void setStBusinessId(String stBusinessId) {
        stBusinessId = StringUtil.substringBySize(stBusinessId, 50, "GB18030");
        this.stBusinessId = stBusinessId;
    }
    
	/**
     * 企业名称
     */
    public String getStBusinessName() {
        return this.stBusinessName;
    }
    
    /**
     * 企业名称
     */
    public String stBusinessName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessName);
    }

    /**
     * 企业名称
     */
    public void setStBusinessName(String stBusinessName) {
        stBusinessName = StringUtil.substringBySize(stBusinessName, 100, "GB18030");
        this.stBusinessName = stBusinessName;
    }
    
	/**
     * 统一社会信用代码
     */
    public String getStCorporationOrgid() {
        return this.stCorporationOrgid;
    }
    
    /**
     * 统一社会信用代码
     */
    public String stCorporationOrgid2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCorporationOrgid);
    }

    /**
     * 统一社会信用代码
     */
    public void setStCorporationOrgid(String stCorporationOrgid) {
        stCorporationOrgid = StringUtil.substringBySize(stCorporationOrgid, 100, "GB18030");
        this.stCorporationOrgid = stCorporationOrgid;
    }
    
	/**
     * 法人姓名
     */
    public String getStLegalName() {
        return this.stLegalName;
    }
    
    /**
     * 法人姓名
     */
    public String stLegalName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalName);
    }

    /**
     * 法人姓名
     */
    public void setStLegalName(String stLegalName) {
        stLegalName = StringUtil.substringBySize(stLegalName, 50, "GB18030");
        this.stLegalName = stLegalName;
    }
    
	/**
     * 企业地址
     */
    public String getStBusinessAddress() {
        return this.stBusinessAddress;
    }
    
    /**
     * 企业地址
     */
    public String stBusinessAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessAddress);
    }

    /**
     * 企业地址
     */
    public void setStBusinessAddress(String stBusinessAddress) {
        stBusinessAddress = StringUtil.substringBySize(stBusinessAddress, 200, "GB18030");
        this.stBusinessAddress = stBusinessAddress;
    }
    
	/**
     * 经办人姓名
     */
    public String getStApplyUserName() {
        return this.stApplyUserName;
    }
    
    /**
     * 经办人姓名
     */
    public String stApplyUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserName);
    }

    /**
     * 经办人姓名
     */
    public void setStApplyUserName(String stApplyUserName) {
        stApplyUserName = StringUtil.substringBySize(stApplyUserName, 50, "GB18030");
        this.stApplyUserName = stApplyUserName;
    }
    
	/**
     * 经办人手机号
     */
    public String getStApplyUserPhone() {
        return this.stApplyUserPhone;
    }
    
    /**
     * 经办人手机号
     */
    public String stApplyUserPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserPhone);
    }

    /**
     * 经办人手机号
     */
    public void setStApplyUserPhone(String stApplyUserPhone) {
        stApplyUserPhone = StringUtil.substringBySize(stApplyUserPhone, 20, "GB18030");
        this.stApplyUserPhone = stApplyUserPhone;
    }
    
	/**
     * 经办人身份证号码
     */
    public String getStApplyUserIdcard() {
        return this.stApplyUserIdcard;
    }
    
    /**
     * 经办人身份证号码
     */
    public String stApplyUserIdcard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserIdcard);
    }

    /**
     * 经办人身份证号码
     */
    public void setStApplyUserIdcard(String stApplyUserIdcard) {
        stApplyUserIdcard = StringUtil.substringBySize(stApplyUserIdcard, 50, "GB18030");
        this.stApplyUserIdcard = stApplyUserIdcard;
    }
    
	/**
     * 法人手机号
     */
    public String getStLegalPhone() {
        return this.stLegalPhone;
    }
    
    /**
     * 法人手机号
     */
    public String stLegalPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalPhone);
    }

    /**
     * 法人手机号
     */
    public void setStLegalPhone(String stLegalPhone) {
        stLegalPhone = StringUtil.substringBySize(stLegalPhone, 20, "GB18030");
        this.stLegalPhone = stLegalPhone;
    }
    
	/**
     * 法人身份证号码
     */
    public String getStLegalIdcard() {
        return this.stLegalIdcard;
    }
    
    /**
     * 法人身份证号码
     */
    public String stLegalIdcard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalIdcard);
    }

    /**
     * 法人身份证号码
     */
    public void setStLegalIdcard(String stLegalIdcard) {
        stLegalIdcard = StringUtil.substringBySize(stLegalIdcard, 50, "GB18030");
        this.stLegalIdcard = stLegalIdcard;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}