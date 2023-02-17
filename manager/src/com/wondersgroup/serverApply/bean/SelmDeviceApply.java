package com.wondersgroup.serverApply.bean;
		
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
 * 设备接入申请
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DEVICE_APPLY")
public class SelmDeviceApply implements Serializable {
    
    /**
     * 设备接入申请
     */
    public static final String SELM_DEVICE_APPLY = "SELM_DEVICE_APPLY";
    
    /**
     * 申请ID
     */
    public static final String ST_DEVICE_APPLY_ID = "ST_DEVICE_APPLY_ID";
    
    /**
     * 申请单号
     */
    public static final String ST_DAPPLY_NO = "ST_DAPPLY_NO";
    
    /**
     * 申请单位ID
     */
    public static final String ST_APPLY_ORGAN_ID = "ST_APPLY_ORGAN_ID";
    
    /**
     * 申请单位名称
     */
    public static final String ST_APPLY_ORGAN_NAME = "ST_APPLY_ORGAN_NAME";
    
    /**
     * 保障部门ID
     */
    public static final String ST_MAIN_ORG_ID = "ST_MAIN_ORG_ID";
    
    /**
     * 保障部门名称
     */
    public static final String ST_MAIN_ORG_NAME = "ST_MAIN_ORG_NAME";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 联系人
     */
    public static final String ST_APPLY_USER_NAME = "ST_APPLY_USER_NAME";
    
    /**
     * 手机
     */
    public static final String ST_APPLY_USER_PHONE = "ST_APPLY_USER_PHONE";
    
    /**
     * 固定电话
     */
    public static final String ST_APPLY_USER_MOBILE = "ST_APPLY_USER_MOBILE";
    
    /**
     * 电子邮箱
     */
    public static final String ST_APPLY_USER_EMAIL = "ST_APPLY_USER_EMAIL";
    
    /**
     * 情况说明
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 计划接入时间
     */
    public static final String DT_PLAN_CREATE = "DT_PLAN_CREATE";
    
    /**
     * 申请人ID
     */
    public static final String ST_APPLY_USER_ID = "ST_APPLY_USER_ID";
    
    /**
     * 申请人姓名
     */
    public static final String ST_APPLY_USER_NAME2 = "ST_APPLY_USER_NAME2";
    
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
    
    /**
     * 所在区
     */
    public static final String ST_DEVICE_DESTRICT = "ST_DEVICE_DESTRICT";
    
    public SelmDeviceApply() {
    }
    
    /**
     * 申请ID
     */
    @Id
    @Column(name = "ST_DEVICE_APPLY_ID")
    private String stDeviceApplyId;
    
    /**
     * 申请单号
     */
    @Column(name = "ST_DAPPLY_NO")
    private String stDapplyNo;
    
    /**
     * 申请单位ID
     */
    @Column(name = "ST_APPLY_ORGAN_ID")
    private String stApplyOrganId;
    
    /**
     * 申请单位名称
     */
    @Column(name = "ST_APPLY_ORGAN_NAME")
    private String stApplyOrganName;
    
    /**
     * 保障部门ID
     */
    @Column(name = "ST_MAIN_ORG_ID")
    private String stMainOrgId;
    
    /**
     * 保障部门名称
     */
    @Column(name = "ST_MAIN_ORG_NAME")
    private String stMainOrgName;
    
    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 联系人
     */
    @Column(name = "ST_APPLY_USER_NAME")
    private String stApplyUserName;
    
    /**
     * 手机
     */
    @Column(name = "ST_APPLY_USER_PHONE")
    private String stApplyUserPhone;
    
    /**
     * 固定电话
     */
    @Column(name = "ST_APPLY_USER_MOBILE")
    private String stApplyUserMobile;
    
    /**
     * 电子邮箱
     */
    @Column(name = "ST_APPLY_USER_EMAIL")
    private String stApplyUserEmail;
    
    /**
     * 情况说明
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 计划接入时间
     */
    @Column(name = "DT_PLAN_CREATE")
    private Timestamp dtPlanCreate;
    
    /**
     * 申请人ID
     */
    @Column(name = "ST_APPLY_USER_ID")
    private String stApplyUserId;
    
    /**
     * 申请人姓名
     */
    @Column(name = "ST_APPLY_USER_NAME2")
    private String stApplyUserName2;
    
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
     * 所在区
     */
    @Column(name = "ST_DEVICE_DESTRICT")
    private String stDeviceDistrict;
    
	/**
     * 申请ID
     */
    public String getStDeviceApplyId() {
        return this.stDeviceApplyId;
    }
    
    /**
     * 申请ID
     */
    public String stDeviceApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceApplyId);
    }

    /**
     * 申请ID
     */
    public void setStDeviceApplyId(String stDeviceApplyId) {
        stDeviceApplyId = StringUtil.substringBySize(stDeviceApplyId, 50, "GB18030");
        this.stDeviceApplyId = stDeviceApplyId;
    }
    
	/**
     * 申请单号
     */
    public String getStDapplyNo() {
        return this.stDapplyNo;
    }
    
    /**
     * 申请单号
     */
    public String stDapplyNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDapplyNo);
    }

    /**
     * 申请单号
     */
    public void setStDapplyNo(String stDapplyNo) {
        stDapplyNo = StringUtil.substringBySize(stDapplyNo, 50, "GB18030");
        this.stDapplyNo = stDapplyNo;
    }
    
	/**
     * 申请单位ID
     */
    public String getStApplyOrganId() {
        return this.stApplyOrganId;
    }
    
    /**
     * 申请单位ID
     */
    public String stApplyOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyOrganId);
    }

    /**
     * 申请单位ID
     */
    public void setStApplyOrganId(String stApplyOrganId) {
        stApplyOrganId = StringUtil.substringBySize(stApplyOrganId, 50, "GB18030");
        this.stApplyOrganId = stApplyOrganId;
    }
    
	/**
     * 申请单位名称
     */
    public String getStApplyOrganName() {
        return this.stApplyOrganName;
    }
    
    /**
     * 申请单位名称
     */
    public String stApplyOrganName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyOrganName);
    }

    /**
     * 申请单位名称
     */
    public void setStApplyOrganName(String stApplyOrganName) {
        stApplyOrganName = StringUtil.substringBySize(stApplyOrganName, 50, "GB18030");
        this.stApplyOrganName = stApplyOrganName;
    }
    
	/**
     * 保障部门ID
     */
    public String getStMainOrgId() {
        return this.stMainOrgId;
    }
    
    /**
     * 保障部门ID
     */
    public String stMainOrgId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMainOrgId);
    }

    /**
     * 保障部门ID
     */
    public void setStMainOrgId(String stMainOrgId) {
        stMainOrgId = StringUtil.substringBySize(stMainOrgId, 50, "GB18030");
        this.stMainOrgId = stMainOrgId;
    }
    
	/**
     * 保障部门名称
     */
    public String getStMainOrgName() {
        return this.stMainOrgName;
    }
    
    /**
     * 保障部门名称
     */
    public String stMainOrgName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMainOrgName);
    }

    /**
     * 保障部门名称
     */
    public void setStMainOrgName(String stMainOrgName) {
        stMainOrgName = StringUtil.substringBySize(stMainOrgName, 50, "GB18030");
        this.stMainOrgName = stMainOrgName;
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
     * 联系人
     */
    public String getStApplyUserName() {
        return this.stApplyUserName;
    }
    
    /**
     * 联系人
     */
    public String stApplyUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserName);
    }

    /**
     * 联系人
     */
    public void setStApplyUserName(String stApplyUserName) {
        stApplyUserName = StringUtil.substringBySize(stApplyUserName, 50, "GB18030");
        this.stApplyUserName = stApplyUserName;
    }
    
	/**
     * 手机
     */
    public String getStApplyUserPhone() {
        return this.stApplyUserPhone;
    }
    
    /**
     * 手机
     */
    public String stApplyUserPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserPhone);
    }

    /**
     * 手机
     */
    public void setStApplyUserPhone(String stApplyUserPhone) {
        stApplyUserPhone = StringUtil.substringBySize(stApplyUserPhone, 50, "GB18030");
        this.stApplyUserPhone = stApplyUserPhone;
    }
    
	/**
     * 固定电话
     */
    public String getStApplyUserMobile() {
        return this.stApplyUserMobile;
    }
    
    /**
     * 固定电话
     */
    public String stApplyUserMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserMobile);
    }

    /**
     * 固定电话
     */
    public void setStApplyUserMobile(String stApplyUserMobile) {
        stApplyUserMobile = StringUtil.substringBySize(stApplyUserMobile, 50, "GB18030");
        this.stApplyUserMobile = stApplyUserMobile;
    }
    
	/**
     * 电子邮箱
     */
    public String getStApplyUserEmail() {
        return this.stApplyUserEmail;
    }
    
    /**
     * 电子邮箱
     */
    public String stApplyUserEmail2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserEmail);
    }

    /**
     * 电子邮箱
     */
    public void setStApplyUserEmail(String stApplyUserEmail) {
        stApplyUserEmail = StringUtil.substringBySize(stApplyUserEmail, 50, "GB18030");
        this.stApplyUserEmail = stApplyUserEmail;
    }
    
	/**
     * 情况说明
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 情况说明
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 情况说明
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 500, "GB18030");
        this.stDesc = stDesc;
    }

	/**
     * 计划接入时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtPlanCreate() {
        return this.dtPlanCreate;
    }
    
    /**
     * 计划接入时间
     */
    public String dtPlanCreate2Html(String pattern) {
        if (this.dtPlanCreate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtPlanCreate);
        }
    }

    /**
     * 计划接入时间
     */
    public void setDtPlanCreate(Timestamp dtPlanCreate) {
        this.dtPlanCreate = dtPlanCreate;
    }
    
	/**
     * 申请人ID
     */
    public String getStApplyUserId() {
        return this.stApplyUserId;
    }
    
    /**
     * 申请人ID
     */
    public String stApplyUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserId);
    }

    /**
     * 申请人ID
     */
    public void setStApplyUserId(String stApplyUserId) {
        stApplyUserId = StringUtil.substringBySize(stApplyUserId, 50, "GB18030");
        this.stApplyUserId = stApplyUserId;
    }
    
	/**
     * 申请人姓名
     */
    public String getStApplyUserName2() {
        return this.stApplyUserName2;
    }
    
    /**
     * 申请人姓名
     */
    public String stApplyUserName22Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserName2);
    }

    /**
     * 申请人姓名
     */
    public void setStApplyUserName2(String stApplyUserName2) {
        stApplyUserName2 = StringUtil.substringBySize(stApplyUserName2, 50, "GB18030");
        this.stApplyUserName2 = stApplyUserName2;
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
    
    /**
     * 所在区
     */
    public String getStDeviceDistrict() {
        return this.stDeviceDistrict;
    }
    
    /**
     * 所在区
     */
    public String stStDeviceDistrict2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceDistrict);
    }

    /**
     * 所在区
     */
    public void setStDeviceDistrict(String stDeviceDistrict) {
    	stDeviceDistrict = StringUtil.substringBySize(stDeviceDistrict, 50, "GB18030");
        this.stDeviceDistrict = stDeviceDistrict;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}