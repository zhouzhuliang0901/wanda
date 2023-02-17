package com.wondersgroup.selfapi.bean;
		
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

import reindeer.base.utils.StringUtil;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;


/**
 * 办理信息表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WORK_APPLY_INFO")
public class WorkApplyInfo implements Serializable {
    
    /**
     * 办理信息表
     */
    public static final String WORK_APPLY_INFO = "WORK_APPLY_INFO";
    
    /**
     * 办理信息ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 受理办件编号
     */
    public static final String ST_APPLY_NO = "ST_APPLY_NO";
    
    /**
     * 业务系统办件编号
     */
    public static final String ST_BUSINESS_NO = "ST_BUSINESS_NO";
    
    /**
     * 业务系统唯一键
     */
    public static final String ST_BUSINESS_ID = "ST_BUSINESS_ID";
    
    /**
     * 办理事项编号
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 处理人ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 处理人登录名
     */
    public static final String ST_LOGIN_NAME = "ST_LOGIN_NAME";
    
    /**
     * 处理窗口编号
     */
    public static final String ST_WINDOW_NO = "ST_WINDOW_NO";
    
    /**
     * 申请人姓名
     */
    public static final String ST_USER_NAME = "ST_USER_NAME";
    
    /**
     * 申请人手机号
     */
    public static final String ST_MOBILE = "ST_MOBILE";
    
    /**
     * 申请人证件类型
     */
    public static final String NM_IDENTITY_TYPE = "NM_IDENTITY_TYPE";
    
    /**
     * 申请人证件号
     */
    public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";
    
    /**
     * 经办人姓名
     */
    public static final String ST_DEAL_USER_NAME = "ST_DEAL_USER_NAME";
    
    /**
     * 经办人手机号
     */
    public static final String ST_DEAL_MOBILE = "ST_DEAL_MOBILE";
    
    /**
     * 经办人证件类型
     */
    public static final String NM_DEAL_IDENTITY_TYPE = "NM_DEAL_IDENTITY_TYPE";
    
    /**
     * 经办人证件号
     */
    public static final String ST_DEAL_IDENTITY_NO = "ST_DEAL_IDENTITY_NO";
    
    /**
     * 开始时间
     */
    public static final String DT_START_TIME = "DT_START_TIME";
    
    /**
     * 申请人确认时间
     */
    public static final String DT_CONFIRM_TIME = "DT_CONFIRM_TIME";
    
    /**
     * 是否收费
     */
    public static final String NM_COST = "NM_COST";
    
    /**
     * 收费
     */
    public static final String NM_TOLL = "NM_TOLL";
    
    /**
     * 申请单位
     */
    public static final String ST_UNIT = "ST_UNIT";
    
    /**
     * 组织机构代码
     */
    public static final String ST_ORGANIZATION_CODE = "ST_ORGANIZATION_CODE";
    
    /**
     * 联系电话
     */
    public static final String ST_CONTACT_PHONE = "ST_CONTACT_PHONE";
    
    /**
     * 提交时间
     */
    public static final String DT_FINISH = "DT_FINISH";
    
    /**
     * 状态：审批通过，不通过，终止，等等
     */
    public static final String ST_STATUS = "ST_STATUS";
    
    /**
     * 项目信息
     */
    public static final String ST_INFORMATION = "ST_INFORMATION";
    
    /**
     * 最终状态：1：办结
     */
    public static final String NM_FINAL_STATE = "NM_FINAL_STATE";
    
    /**
     * 间隔时长
     */
    public static final String NM_INTERVAL = "NM_INTERVAL";
    
    /**
     * 剩余等待人数
     */
    public static final String NM_WAIT_COUNT = "NM_WAIT_COUNT";
    
    /**
     * 部门ID
     */
    public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";
    
    /**
     * 是否打印了凭证
     */
    public static final String NM_IS_PRINT = "NM_IS_PRINT";
    
    /**
     * 收件凭证备注
     */
    public static final String ST_RECEIPT_REMARK = "ST_RECEIPT_REMARK";
    
    /**
     * 叫号办理主键
     */
    public static final String ST_CALL_ID = "ST_CALL_ID";
    
    /**
     * 后台办理人ID
     */
    public static final String ST_DEAL_SERVICE_ID = "ST_DEAL_SERVICE_ID";
    /**
     * 后台办理人姓名
     */
    public static final String ST_DEAL_SERVICE_NAME = "ST_DEAL_SERVICE_NAME";
    /**
     * 后台办理时间
     */
    public static final String DT_DEAL_SERVICE = "DT_DEAL_SERVICE";
    
    /**
     * 完成办事时间开始
     */
    public static final String DT_ASSESS_START = "DT_ASSESS_START";
    
    /**
     * 完成办事时间结束
     */
    public static final String DT_ASSESS_END = "DT_ASSESS_END";
    
    /**
     * 满意度评价
     */
    public static final String NM_SATISFATION = "NM_SATISFATION";
    
    /**
     * 满意度评价备注
     */
    public static final String ST_SATISFATION_REMARK = "ST_SATISFATION_REMARK";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public WorkApplyInfo() {
    }
    
    /**
     * 办理信息ID
     */
    @Id
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 受理办件编号
     */
    @Column(name = "ST_APPLY_NO")
    private String stApplyNo;
    
    /**
     * 业务系统办件编号
     */
    @Column(name = "ST_BUSINESS_NO")
    private String stBusinessNo;
    
    /**
     * 业务系统唯一键
     */
    @Column(name = "ST_BUSINESS_ID")
    private String stBusinessId;
    
    /**
     * 办理事项编号
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 处理人ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 处理人登录名
     */
    @Column(name = "ST_LOGIN_NAME")
    private String stLoginName;
    
    /**
     * 处理窗口编号
     */
    @Column(name = "ST_WINDOW_NO")
    private String stWindowNo;
    
    /**
     * 申请人姓名
     */
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 申请人手机号
     */
    @Column(name = "ST_MOBILE")
    private String stMobile;
    
    /**
     * 申请人证件类型
     */
    @Column(name = "NM_IDENTITY_TYPE")
    private BigDecimal nmIdentityType;
    
    /**
     * 申请人证件号
     */
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentityNo;
    
    /**
     * 经办人姓名
     */
    @Column(name = "ST_DEAL_USER_NAME")
    private String stDealUserName;
    
    /**
     * 经办人手机号
     */
    @Column(name = "ST_DEAL_MOBILE")
    private String stDealMobile;
    
    /**
     * 经办人证件类型
     */
    @Column(name = "NM_DEAL_IDENTITY_TYPE")
    private BigDecimal nmDealIdentityType;
    
    /**
     * 经办人证件号
     */
    @Column(name = "ST_DEAL_IDENTITY_NO")
    private String stDealIdentityNo;
    
    /**
     * 开始时间
     */
    @Column(name = "DT_START_TIME")
    private Timestamp dtStartTime;
    
    /**
     * 申请人确认时间
     */
    @Column(name = "DT_CONFIRM_TIME")
    private Timestamp dtConfirmTime;
    
    /**
     * 是否收费
     */
    @Column(name = "NM_COST")
    private BigDecimal nmCost;
    
    /**
     * 收费
     */
    @Column(name = "NM_TOLL")
    private BigDecimal nmToll;
    
    /**
     * 申请单位
     */
    @Column(name = "ST_UNIT")
    private String stUnit;
    
    /**
     * 组织机构代码
     */
    @Column(name = "ST_ORGANIZATION_CODE")
    private String stOrganizationCode;
    
    /**
     * 联系电话
     */
    @Column(name = "ST_CONTACT_PHONE")
    private String stContactPhone;
    
    /**
     * 提交时间
     */
    @Column(name = "DT_FINISH")
    private Timestamp dtFinish;
    
    /**
     * 状态：审批通过，不通过，终止，等等
     */
    @Column(name = "ST_STATUS")
    private String stStatus;
    
    /**
     * 项目信息
     */
    @Column(name = "ST_INFORMATION")
    private String stInformation;
    
    /**
     * 最终状态：1：办结
     */
    @Column(name = "NM_FINAL_STATE")
    private BigDecimal nmFinalState;
    
    /**
     * 间隔时长
     */
    @Column(name = "NM_INTERVAL")
    private BigDecimal nmInterval;
    
    /**
     * 剩余等待人数
     */
    @Column(name = "NM_WAIT_COUNT")
    private BigDecimal nmWaitCount;
    
    /**
     * 部门ID
     */
    @Column(name = "NM_ORGAN_NODE_ID")
    private BigDecimal nmOrganNodeId;
    
    /**
     * 是否打印了凭证
     */
    @Column(name = "NM_IS_PRINT")
    private BigDecimal nmIsPrint;
    
    /**
     * 收件凭证备注
     */
    @Column(name = "ST_RECEIPT_REMARK")
    private String stReceiptRemark;
    
    /**
     * 叫号办理主键
     */
    @Column(name = "ST_CALL_ID")
    private String stCallId;
    
    /**
     * 后台办理人ID
     */
    @Column(name = "ST_DEAL_SERVICE_ID")
    private String stDealServiceId;
    
    /**
     * 后台办理人姓名
     */
    @Column(name = "ST_DEAL_SERVICE_NAME")
    private String stDealServiceName;
    
    /**
     * 后台办理时间
     */
    @Column(name = "DT_DEAL_SERVICE")
    private Timestamp dtDealService;
    
    /**
     * 完成办事时间开始
     */
    @Column(name = "DT_ASSESS_START")
    private Timestamp dtAssessStart;
    
    /**
     * 完成办事时间结束
     */
    @Column(name = "DT_ASSESS_END")
    private Timestamp dtAssessEnd;
    
    /**
     * 满意度评价
     */
    @Column(name = "NM_SATISFATION")
    private BigDecimal nmSatisfation;
    
    /**
     * 满意度评价备注
     */
    @Column(name = "ST_SATISFATION_REMARK")
    private String stSatisfationRemark;
    
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
     * 办理信息ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 办理信息ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 办理信息ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }
    
	/**
     * 受理办件编号
     */
    public String getStApplyNo() {
        return this.stApplyNo;
    }
    
    /**
     * 受理办件编号
     */
    public String stApplyNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyNo);
    }

    /**
     * 受理办件编号
     */
    public void setStApplyNo(String stApplyNo) {
        stApplyNo = StringUtil.substringBySize(stApplyNo, 50, "GB18030");
        this.stApplyNo = stApplyNo;
    }
    
	/**
     * 业务系统办件编号
     */
    public String getStBusinessNo() {
        return this.stBusinessNo;
    }
    
    /**
     * 业务系统办件编号
     */
    public String stBusinessNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessNo);
    }

    /**
     * 业务系统办件编号
     */
    public void setStBusinessNo(String stBusinessNo) {
        stBusinessNo = StringUtil.substringBySize(stBusinessNo, 50, "GB18030");
        this.stBusinessNo = stBusinessNo;
    }
    
	/**
     * 业务系统唯一键
     */
    public String getStBusinessId() {
        return this.stBusinessId;
    }
    
    /**
     * 业务系统唯一键
     */
    public String stBusinessId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessId);
    }

    /**
     * 业务系统唯一键
     */
    public void setStBusinessId(String stBusinessId) {
        stBusinessId = StringUtil.substringBySize(stBusinessId, 50, "GB18030");
        this.stBusinessId = stBusinessId;
    }
    
	/**
     * 办理事项编号
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 办理事项编号
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 办理事项编号
     */
    public void setStItemId(String stItemId) {
        //stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
        this.stItemId = stItemId;
    }
    
	/**
     * 处理人ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 处理人ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 处理人ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }
    
	/**
     * 处理人登录名
     */
    public String getStLoginName() {
        return this.stLoginName;
    }
    
    /**
     * 处理人登录名
     */
    public String stLoginName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLoginName);
    }

    /**
     * 处理人登录名
     */
    public void setStLoginName(String stLoginName) {
        stLoginName = StringUtil.substringBySize(stLoginName, 50, "GB18030");
        this.stLoginName = stLoginName;
    }
    
	/**
     * 处理窗口编号
     */
    public String getStWindowNo() {
        return this.stWindowNo;
    }
    
    /**
     * 处理窗口编号
     */
    public String stWindowNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWindowNo);
    }

    /**
     * 处理窗口编号
     */
    public void setStWindowNo(String stWindowNo) {
        stWindowNo = StringUtil.substringBySize(stWindowNo, 50, "GB18030");
        this.stWindowNo = stWindowNo;
    }
    
	/**
     * 申请人姓名
     */
    public String getStUserName() {
        return this.stUserName;
    }
    
    /**
     * 申请人姓名
     */
    public String stUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserName);
    }

    /**
     * 申请人姓名
     */
    public void setStUserName(String stUserName) {
        stUserName = StringUtil.substringBySize(stUserName, 50, "GB18030");
        this.stUserName = stUserName;
    }
    
	/**
     * 申请人手机号
     */
    public String getStMobile() {
        return this.stMobile;
    }
    
    /**
     * 申请人手机号
     */
    public String stMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMobile);
    }

    /**
     * 申请人手机号
     */
    public void setStMobile(String stMobile) {
        stMobile = StringUtil.substringBySize(stMobile, 50, "GB18030");
        this.stMobile = stMobile;
    }

	/**
     * 申请人证件类型
     */
    public BigDecimal getNmIdentityType() {
        return this.nmIdentityType;
    }
    
    /**
     * 申请人证件类型
     */
    public String nmIdentityType2Html(int precision) {
        if (this.nmIdentityType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIdentityType);
        }
    }

    /**
     * 申请人证件类型
     */
    public void setNmIdentityType(BigDecimal nmIdentityType) {
        this.nmIdentityType = nmIdentityType;
    }
    
	/**
     * 申请人证件号
     */
    public String getStIdentityNo() {
        return this.stIdentityNo;
    }
    
    /**
     * 申请人证件号
     */
    public String stIdentityNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIdentityNo);
    }

    /**
     * 申请人证件号
     */
    public void setStIdentityNo(String stIdentityNo) {
        stIdentityNo = StringUtil.substringBySize(stIdentityNo, 50, "GB18030");
        this.stIdentityNo = stIdentityNo;
    }
    
	/**
     * 经办人姓名
     */
    public String getStDealUserName() {
        return this.stDealUserName;
    }
    
    /**
     * 经办人姓名
     */
    public String stDealUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDealUserName);
    }

    /**
     * 经办人姓名
     */
    public void setStDealUserName(String stDealUserName) {
        stDealUserName = StringUtil.substringBySize(stDealUserName, 50, "GB18030");
        this.stDealUserName = stDealUserName;
    }
    
	/**
     * 经办人手机号
     */
    public String getStDealMobile() {
        return this.stDealMobile;
    }
    
    /**
     * 经办人手机号
     */
    public String stDealMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDealMobile);
    }

    /**
     * 经办人手机号
     */
    public void setStDealMobile(String stDealMobile) {
        stDealMobile = StringUtil.substringBySize(stDealMobile, 50, "GB18030");
        this.stDealMobile = stDealMobile;
    }

	/**
     * 经办人证件类型
     */
    public BigDecimal getNmDealIdentityType() {
        return this.nmDealIdentityType;
    }
    
    /**
     * 经办人证件类型
     */
    public String nmDealIdentityType2Html(int precision) {
        if (this.nmDealIdentityType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDealIdentityType);
        }
    }

    /**
     * 经办人证件类型
     */
    public void setNmDealIdentityType(BigDecimal nmDealIdentityType) {
        this.nmDealIdentityType = nmDealIdentityType;
    }
    
	/**
     * 经办人证件号
     */
    public String getStDealIdentityNo() {
        return this.stDealIdentityNo;
    }
    
    /**
     * 经办人证件号
     */
    public String stDealIdentityNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDealIdentityNo);
    }

    /**
     * 经办人证件号
     */
    public void setStDealIdentityNo(String stDealIdentityNo) {
        stDealIdentityNo = StringUtil.substringBySize(stDealIdentityNo, 50, "GB18030");
        this.stDealIdentityNo = stDealIdentityNo;
    }

	/**
     * 开始时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtStartTime() {
        return this.dtStartTime;
    }
    
    /**
     * 开始时间
     */
    public String dtStartTime2Html(String pattern) {
        if (this.dtStartTime == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtStartTime);
        }
    }

    /**
     * 开始时间
     */
    public void setDtStartTime(Timestamp dtStartTime) {
        this.dtStartTime = dtStartTime;
    }

	/**
     * 申请人确认时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtConfirmTime() {
        return this.dtConfirmTime;
    }
    
    /**
     * 申请人确认时间
     */
    public String dtConfirmTime2Html(String pattern) {
        if (this.dtConfirmTime == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtConfirmTime);
        }
    }

    /**
     * 申请人确认时间
     */
    public void setDtConfirmTime(Timestamp dtConfirmTime) {
        this.dtConfirmTime = dtConfirmTime;
    }

	/**
     * 是否收费
     */
    public BigDecimal getNmCost() {
        return this.nmCost;
    }
    
    /**
     * 是否收费
     */
    public String nmCost2Html(int precision) {
        if (this.nmCost == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCost);
        }
    }

    /**
     * 是否收费
     */
    public void setNmCost(BigDecimal nmCost) {
        this.nmCost = nmCost;
    }

	/**
     * 收费
     */
    public BigDecimal getNmToll() {
        return this.nmToll;
    }
    
    /**
     * 收费
     */
    public String nmToll2Html(int precision) {
        if (this.nmToll == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmToll);
        }
    }

    /**
     * 收费
     */
    public void setNmToll(BigDecimal nmToll) {
        this.nmToll = nmToll;
    }
    
	/**
     * 申请单位
     */
    public String getStUnit() {
        return this.stUnit;
    }
    
    /**
     * 申请单位
     */
    public String stUnit2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnit);
    }

    /**
     * 申请单位
     */
    public void setStUnit(String stUnit) {
        stUnit = StringUtil.substringBySize(stUnit, 100, "GB18030");
        this.stUnit = stUnit;
    }
    
	/**
     * 组织机构代码
     */
    public String getStOrganizationCode() {
        return this.stOrganizationCode;
    }
    
    /**
     * 组织机构代码
     */
    public String stOrganizationCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganizationCode);
    }

    /**
     * 组织机构代码
     */
    public void setStOrganizationCode(String stOrganizationCode) {
        stOrganizationCode = StringUtil.substringBySize(stOrganizationCode, 100, "GB18030");
        this.stOrganizationCode = stOrganizationCode;
    }
    
	/**
     * 联系电话
     */
    public String getStContactPhone() {
        return this.stContactPhone;
    }
    
    /**
     * 联系电话
     */
    public String stContactPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stContactPhone);
    }

    /**
     * 联系电话
     */
    public void setStContactPhone(String stContactPhone) {
        stContactPhone = StringUtil.substringBySize(stContactPhone, 100, "GB18030");
        this.stContactPhone = stContactPhone;
    }

	/**
     * 提交时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtFinish() {
        return this.dtFinish;
    }
    
    /**
     * 提交时间
     */
    public String dtFinish2Html(String pattern) {
        if (this.dtFinish == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtFinish);
        }
    }

    /**
     * 提交时间
     */
    public void setDtFinish(Timestamp dtFinish) {
        this.dtFinish = dtFinish;
    }
    
	/**
     * 状态：审批通过，不通过，终止，等等
     */
    public String getStStatus() {
        return this.stStatus;
    }
    
    /**
     * 状态：审批通过，不通过，终止，等等
     */
    public String stStatus2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatus);
    }

    /**
     * 状态：审批通过，不通过，终止，等等
     */
    public void setStStatus(String stStatus) {
        stStatus = StringUtil.substringBySize(stStatus, 50, "GB18030");
        this.stStatus = stStatus;
    }
    
	/**
     * 项目信息
     */
    public String getStInformation() {
        return this.stInformation;
    }
    
    /**
     * 项目信息
     */
    public String stInformation2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInformation);
    }

    /**
     * 项目信息
     */
    public void setStInformation(String stInformation) {
        stInformation = StringUtil.substringBySize(stInformation, 50, "GB18030");
        this.stInformation = stInformation;
    }

	/**
     * 最终状态：1：办结
     */
    public BigDecimal getNmFinalState() {
        return this.nmFinalState;
    }
    
    /**
     * 最终状态：1：办结
     */
    public String nmFinalState2Html(int precision) {
        if (this.nmFinalState == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmFinalState);
        }
    }

    /**
     * 最终状态：1：办结
     */
    public void setNmFinalState(BigDecimal nmFinalState) {
        this.nmFinalState = nmFinalState;
    }

	/**
     * 间隔时长
     */
    public BigDecimal getNmInterval() {
        return this.nmInterval;
    }
    
    /**
     * 间隔时长
     */
    public String nmInterval2Html(int precision) {
        if (this.nmInterval == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmInterval);
        }
    }

    /**
     * 间隔时长
     */
    public void setNmInterval(BigDecimal nmInterval) {
        this.nmInterval = nmInterval;
    }

	/**
     * 剩余等待人数
     */
    public BigDecimal getNmWaitCount() {
        return this.nmWaitCount;
    }
    
    /**
     * 剩余等待人数
     */
    public String nmWaitCount2Html(int precision) {
        if (this.nmWaitCount == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmWaitCount);
        }
    }

    /**
     * 剩余等待人数
     */
    public void setNmWaitCount(BigDecimal nmWaitCount) {
        this.nmWaitCount = nmWaitCount;
    }

	/**
     * 部门ID
     */
    public BigDecimal getNmOrganNodeId() {
        return this.nmOrganNodeId;
    }
    
    /**
     * 部门ID
     */
    public String nmOrganNodeId2Html(int precision) {
        if (this.nmOrganNodeId == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOrganNodeId);
        }
    }

    /**
     * 部门ID
     */
    public void setNmOrganNodeId(BigDecimal nmOrganNodeId) {
        this.nmOrganNodeId = nmOrganNodeId;
    }

	/**
     * 是否打印了凭证
     */
    public BigDecimal getNmIsPrint() {
        return this.nmIsPrint;
    }
    
    /**
     * 是否打印了凭证
     */
    public String nmIsPrint2Html(int precision) {
        if (this.nmIsPrint == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIsPrint);
        }
    }

    /**
     * 是否打印了凭证
     */
    public void setNmIsPrint(BigDecimal nmIsPrint) {
        this.nmIsPrint = nmIsPrint;
    }
    
	/**
     * 收件凭证备注
     */
    public String getStReceiptRemark() {
        return this.stReceiptRemark;
    }
    
    /**
     * 收件凭证备注
     */
    public String stReceiptRemark2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiptRemark);
    }

    /**
     * 收件凭证备注
     */
    public void setStReceiptRemark(String stReceiptRemark) {
        stReceiptRemark = StringUtil.substringBySize(stReceiptRemark, 500, "GB18030");
        this.stReceiptRemark = stReceiptRemark;
    }
    
	/**
     * 叫号办理主键
     */
    public String getStCallId() {
        return this.stCallId;
    }
    
    /**
     * 叫号办理主键
     */
    public String stCallId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCallId);
    }

    /**
     * 叫号办理主键
     */
    public void setStCallId(String stCallId) {
        stCallId = StringUtil.substringBySize(stCallId, 50, "GB18030");
        this.stCallId = stCallId;
    }
    
    
    /**
     * 后台办理人ID
     */
	public String getStDealServiceId() {
		return stDealServiceId;
	}
	
	/**
     * 后台办理人ID
     */
	public String stDealServiceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDealServiceId);
    }
	/**
     * 后台办理人ID
     */
	public void setStDealServiceId(String stDealServiceId) {
		stDealServiceId = StringUtil.substringBySize(stDealServiceId, 50, "GB18030");
		this.stDealServiceId = stDealServiceId;
	}

	/**
     * 后台办理人姓名
     */
	public String getStDealServiceName() {
		return stDealServiceName;
	}
	
	/**
     * 后台办理人姓名
     */
	public String stDealServiceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDealServiceName);
    }
	
	/**
     * 后台办理人姓名
     */
	public void setStDealServiceName(String stDealServiceName) {
		stDealServiceName = StringUtil.substringBySize(stDealServiceName, 50, "GB18030");
		this.stDealServiceName = stDealServiceName;
	}
	
	/**
     * 后台办理时间
     */
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtDealService() {
		return dtDealService;
	}
	
	/**
     * 后台办理时间
     */
	public String dtDealService2Html(String pattern) {
        if (this.dtDealService == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDealService);
        }
    }
	
	/**
     * 后台办理时间
     */
	public void setDtDealService(Timestamp dtDealService) {
		this.dtDealService = dtDealService;
	}
	
	/**
     * 完成办事时间开始
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtAssessStart() {
        return this.dtAssessStart;
    }
    
    /**
     * 完成办事时间开始
     */
    public String dtAssessStart2Html(String pattern) {
        if (this.dtAssessStart == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtAssessStart);
        }
    }

    /**
     * 完成办事时间开始
     */
    public void setDtAssessStart(Timestamp dtAssessStart) {
        this.dtAssessStart = dtAssessStart;
    }

	/**
     * 完成办事时间结束
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtAssessEnd() {
        return this.dtAssessEnd;
    }
    
    /**
     * 完成办事时间结束
     */
    public String dtAssessEnd2Html(String pattern) {
        if (this.dtAssessEnd == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtAssessEnd);
        }
    }

    /**
     * 完成办事时间结束
     */
    public void setDtAssessEnd(Timestamp dtAssessEnd) {
        this.dtAssessEnd = dtAssessEnd;
    }

	/**
     * 满意度评价
     */
    public BigDecimal getNmSatisfation() {
        return this.nmSatisfation;
    }
    
    /**
     * 满意度评价
     */
    public String nmSatisfation2Html(int precision) {
        if (this.nmSatisfation == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSatisfation);
        }
    }

    /**
     * 满意度评价
     */
    public void setNmSatisfation(BigDecimal nmSatisfation) {
        this.nmSatisfation = nmSatisfation;
    }
    
	/**
     * 满意度评价备注
     */
    public String getStSatisfationRemark() {
        return this.stSatisfationRemark;
    }
    
    /**
     * 满意度评价备注
     */
    public String stSatisfationRemark2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSatisfationRemark);
    }

    /**
     * 满意度评价备注
     */
    public void setStSatisfationRemark(String stSatisfationRemark) {
        stSatisfationRemark = StringUtil.substringBySize(stSatisfationRemark, 500, "GB18030");
        this.stSatisfationRemark = stSatisfationRemark;
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

    

}