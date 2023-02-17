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
 * 服务开通申请
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_SERVER_APPLY")
public class SelmServerApply implements Serializable {
    
    /**
     * 服务开通申请
     */
    public static final String SELM_SERVER_APPLY = "SELM_SERVER_APPLY";
    
    /**
     * 申请ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 申请单位ID
     */
    public static final String ST_APPLY_ORGAN_ID = "ST_APPLY_ORGAN_ID";
    
    /**
     * 申请单位名称
     */
    public static final String ST_APPLY_ORGAN_NAME = "ST_APPLY_ORGAN_NAME";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 联系人
     */
    public static final String ST_SERVER_USER_NAME = "ST_SERVER_USER_NAME";
    
    /**
     * 手机
     */
    public static final String ST_SERVER_USER_PHONE = "ST_SERVER_USER_PHONE";
    
    /**
     * 固定电话
     */
    public static final String ST_SERVER_USER_MOBILE = "ST_SERVER_USER_MOBILE";
    
    /**
     * 电子邮箱
     */
    public static final String ST_SERVER_USER_EMAIL = "ST_SERVER_USER_EMAIL";
    
    /**
     * 申请情况说明
     */
    public static final String ST_SERVER_CONTENT = "ST_SERVER_CONTENT";
    
    /**
     * 计划上线时间
     */
    public static final String DT_UP_CREATE = "DT_UP_CREATE";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 所在区
     */
    public static final String ST_SERVER_DESTRICT = "ST_SERVER_DESTRICT";
    
    /**
     * 点位名称
     */
    public static final String ST_POINT_NAME = "ST_POINT_NAME";
    
    /**
     * 摆放地址
     */
    public static final String ST_PUT_ADDRESS = "ST_PUT_ADDRESS";
    
    /**
     * 承建厂商
     */
    public static final String ST_BUILD_COMPANY = "ST_BUILD_COMPANY";
    
    /**
     * 预计摆放台数
     */
    public static final String ST_PUT_NUMBER = "ST_PUT_NUMBER";
    
    /**
     * 现场网络环境
     */
    public static final String NM_NETWORK = "NM_NETWORK";
    
    /**
     * 现场有无值守
     */
    public static final String ST_WATCH_OVER = "ST_WATCH_OVER";
    
    /**
     * 附件ID
     */
    public static final String ST_ATTACH_ID = "ST_ATTACH_ID";
    
    /**
     * 事项审批结果
     */
    public static final String ST_RESULT = "ST_RESULT";
    
    /**
     * 是否可修改
     */
    public static final String NM_UPDATE = "NM_UPDATE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SelmServerApply() {
    }
    
    /**
     * 申请ID
     */
    @Id
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
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
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 联系人
     */
    @Column(name = "ST_SERVER_USER_NAME")
    private String stServerUserName;
    
    /**
     * 手机
     */
    @Column(name = "ST_SERVER_USER_PHONE")
    private String stServerUserPhone;
    
    /**
     * 固定电话
     */
    @Column(name = "ST_SERVER_USER_MOBILE")
    private String stServerUserMobile;
    
    /**
     * 电子邮箱
     */
    @Column(name = "ST_SERVER_USER_EMAIL")
    private String stServerUserEmail;
    
    /**
     * 申请情况说明
     */
    @Column(name = "ST_SERVER_CONTENT")
    private String stServerContent;
    
    /**
     * 计划上线时间
     */
    @Column(name = "DT_UP_CREATE")
    private Timestamp dtUpCreate;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 所在区
     */
    @Column(name = "ST_SERVER_DESTRICT")
    private String stServerDestrict;
    
    /**
     * 点位名称
     */
    @Column(name = "ST_POINT_NAME")
    private String stPointName;
    
    /**
     * 摆放地址
     */
    @Column(name = "ST_PUT_ADDRESS")
    private String stPutAddress;
    
    /**
     * 承建厂商
     */
    @Column(name = "ST_BUILD_COMPANY")
    private String stBuildCompany;
    
    /**
     * 预计摆放台数
     */
    @Column(name = "ST_PUT_NUMBER")
    private String stPutNumber;
    
    /**
     * 现场网络环境
     */
    @Column(name = "NM_NETWORK")
    private BigDecimal nmNetwork;
    
    /**
     * 现场有无值守
     */
    @Column(name = "ST_WATCH_OVER")
    private BigDecimal stWatchOver;
    
    /**
     * 附件ID
     */
    @Column(name = "ST_ATTACH_ID")
    private String stAttachId;
    
    /**
     * 事项审批结果
     */
    @Column(name = "ST_RESULT")
    private String stResult;
    
    /**
     * 是否可修改
     */
    @Column(name = "NM_UPDATE")
    private BigDecimal nmUpdate;
    
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
     * 申请ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 申请ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 申请ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
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
    public String getStServerUserName() {
        return this.stServerUserName;
    }
    
    /**
     * 联系人
     */
    public String stServerUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerUserName);
    }

    /**
     * 联系人
     */
    public void setStServerUserName(String stServerUserName) {
        stServerUserName = StringUtil.substringBySize(stServerUserName, 50, "GB18030");
        this.stServerUserName = stServerUserName;
    }
    
	/**
     * 手机
     */
    public String getStServerUserPhone() {
        return this.stServerUserPhone;
    }
    
    /**
     * 手机
     */
    public String stServerUserPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerUserPhone);
    }

    /**
     * 手机
     */
    public void setStServerUserPhone(String stServerUserPhone) {
        stServerUserPhone = StringUtil.substringBySize(stServerUserPhone, 50, "GB18030");
        this.stServerUserPhone = stServerUserPhone;
    }
    
	/**
     * 固定电话
     */
    public String getStServerUserMobile() {
        return this.stServerUserMobile;
    }
    
    /**
     * 固定电话
     */
    public String stServerUserMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerUserMobile);
    }

    /**
     * 固定电话
     */
    public void setStServerUserMobile(String stServerUserMobile) {
        stServerUserMobile = StringUtil.substringBySize(stServerUserMobile, 50, "GB18030");
        this.stServerUserMobile = stServerUserMobile;
    }
    
	/**
     * 电子邮箱
     */
    public String getStServerUserEmail() {
        return this.stServerUserEmail;
    }
    
    /**
     * 电子邮箱
     */
    public String stServerUserEmail2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerUserEmail);
    }

    /**
     * 电子邮箱
     */
    public void setStServerUserEmail(String stServerUserEmail) {
        stServerUserEmail = StringUtil.substringBySize(stServerUserEmail, 50, "GB18030");
        this.stServerUserEmail = stServerUserEmail;
    }
    
	/**
     * 申请情况说明
     */
    public String getStServerContent() {
        return this.stServerContent;
    }
    
    /**
     * 申请情况说明
     */
    public String stServerContent2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerContent);
    }

    /**
     * 申请情况说明
     */
    public void setStServerContent(String stServerContent) {
        stServerContent = StringUtil.substringBySize(stServerContent, 500, "GB18030");
        this.stServerContent = stServerContent;
    }

	/**
     * 计划上线时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpCreate() {
        return this.dtUpCreate;
    }
    
    /**
     * 计划上线时间
     */
    public String dtUpCreate2Html(String pattern) {
        if (this.dtUpCreate == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtUpCreate);
        }
    }

    /**
     * 计划上线时间
     */
    public void setDtUpCreate(Timestamp dtUpCreate) {
        this.dtUpCreate = dtUpCreate;
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
     * 所在区
     */
    public String getStServerDestrict() {
        return this.stServerDestrict;
    }
    
    /**
     * 所在区
     */
    public String stServerDestrict2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerDestrict);
    }

    /**
     * 所在区
     */
    public void setStServerDestrict(String stServerDestrict) {
        stServerDestrict = StringUtil.substringBySize(stServerDestrict, 50, "GB18030");
        this.stServerDestrict = stServerDestrict;
    }
    
	/**
     * 点位名称
     */
    public String getStPointName() {
        return this.stPointName;
    }
    
    /**
     * 点位名称
     */
    public String stPointName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPointName);
    }

    /**
     * 点位名称
     */
    public void setStPointName(String stPointName) {
        stPointName = StringUtil.substringBySize(stPointName, 50, "GB18030");
        this.stPointName = stPointName;
    }
    
	/**
     * 摆放地址
     */
    public String getStPutAddress() {
        return this.stPutAddress;
    }
    
    /**
     * 摆放地址
     */
    public String stPutAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPutAddress);
    }

    /**
     * 摆放地址
     */
    public void setStPutAddress(String stPutAddress) {
        stPutAddress = StringUtil.substringBySize(stPutAddress, 100, "GB18030");
        this.stPutAddress = stPutAddress;
    }
    
	/**
     * 承建厂商
     */
    public String getStBuildCompany() {
        return this.stBuildCompany;
    }
    
    /**
     * 承建厂商
     */
    public String stBuildCompany2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBuildCompany);
    }

    /**
     * 承建厂商
     */
    public void setStBuildCompany(String stBuildCompany) {
        stBuildCompany = StringUtil.substringBySize(stBuildCompany, 50, "GB18030");
        this.stBuildCompany = stBuildCompany;
    }
    
	/**
     * 预计摆放台数
     */
    public String getStPutNumber() {
        return this.stPutNumber;
    }
    
    /**
     * 预计摆放台数
     */
    public String stPutNumber2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPutNumber);
    }

    /**
     * 预计摆放台数
     */
    public void setStPutNumber(String stPutNumber) {
        stPutNumber = StringUtil.substringBySize(stPutNumber, 50, "GB18030");
        this.stPutNumber = stPutNumber;
    }

	/**
     * 现场网络环境
     */
    public BigDecimal getNmNetwork() {
        return this.nmNetwork;
    }
    
    /**
     * 现场网络环境
     */
    public String nmNetwork2Html(int precision) {
        if (this.nmNetwork == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNetwork);
        }
    }

    /**
     * 现场网络环境
     */
    public void setNmNetwork(BigDecimal nmNetwork) {
        this.nmNetwork = nmNetwork;
    }

	/**
     * 现场有无值守
     */
    public BigDecimal getStWatchOver() {
        return this.stWatchOver;
    }
    
    /**
     * 现场有无值守
     */
    public String stWatchOver2Html(int precision) {
        if (this.stWatchOver == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.stWatchOver);
        }
    }

    /**
     * 现场有无值守
     */
    public void setStWatchOver(BigDecimal stWatchOver) {
        this.stWatchOver = stWatchOver;
    }
    
	/**
     * 附件ID
     */
    public String getStAttachId() {
        return this.stAttachId;
    }
    
    /**
     * 附件ID
     */
    public String stAttachId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId);
    }

    /**
     * 附件ID
     */
    public void setStAttachId(String stAttachId) {
        stAttachId = StringUtil.substringBySize(stAttachId, 50, "GB18030");
        this.stAttachId = stAttachId;
    }
    
	/**
     * 事项审批结果
     */
    public String getStResult() {
        return this.stResult;
    }
    
    /**
     * 事项审批结果
     */
    public String stResult2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResult);
    }

    /**
     * 事项审批结果
     */
    public void setStResult(String stResult) {
        stResult = StringUtil.substringBySize(stResult, 1000, "GB18030");
        this.stResult = stResult;
    }

	/**
     * 是否可修改
     */
    public BigDecimal getNmUpdate() {
        return this.nmUpdate;
    }
    
    /**
     * 是否可修改
     */
    public String nmUpdate2Html(int precision) {
        if (this.nmUpdate == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmUpdate);
        }
    }

    /**
     * 是否可修改
     */
    public void setNmUpdate(BigDecimal nmUpdate) {
        this.nmUpdate = nmUpdate;
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