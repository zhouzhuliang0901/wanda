package com.wondersgroup.delivery.bean;
		
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
 * 快递柜
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DELIVERY")
public class SelmDelivery implements Serializable {
    
    /**
     * 快递柜
     */
    public static final String SELM_DELIVERY = "SELM_DELIVERY";
    
    /**
     * 快递柜ID
     */
    public static final String ST_DELIVERY_ID = "ST_DELIVERY_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_MACHINE_ID = "ST_MACHINE_ID";
    
    /**
     * 设备柜号
     */
    public static final String ST_CABINET_NO = "ST_CABINET_NO";
    
    /**
     * 取证号
     */
    public static final String ST_CERT_FLOW_NO = "ST_CERT_FLOW_NO";
    
    /**
     * 收件人手机号
     */
    public static final String ST_RECEIVER_PHONE = "ST_RECEIVER_PHONE";
    
    /**
     * 收件人姓名
     */
    public static final String ST_RECEIVER_NAME = "ST_RECEIVER_NAME";
    
    /**
     * 收件人身份证号
     */
    public static final String ST_RECEIVER_IDCARD = "ST_RECEIVER_IDCARD";
    
    /**
     * 投件人（用户ID）
     */
    public static final String ST_SENDER_ID = "ST_SENDER_ID";
    
    /**
     * 投件人姓名
     */
    public static final String ST_SENDER_NAME = "ST_SENDER_NAME";
    /**
     * 投件人手机号
     */
    public static final String ST_SENDER_PHONE = "ST_SENDER_PHONE";
    /**
     * 证照类型
     */
    public static final String NM_CERT_TYPE = "NM_CERT_TYPE";
    /**
     * 证照名称
     */
    public static final String ST_CERT_NAME = "ST_CERT_NAME";
    
    /**
     * 类型
     */
    public static final String NM_TYPE = "NM_TYPE";
    
    /**
     * 关联办件
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 企业/个人 名称
     */
    public static final String ST_NAME = "ST_NAME";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 取件码
     */
    public static final String ST_RECEIVE_NUM = "ST_RECEIVE_NUM";
    
    /**
     * 描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 投放时间
     */
    public static final String DT_STORE = "DT_STORE";
    
    /**
     * 取走时间
     */
    public static final String DT_TAKE = "DT_TAKE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SelmDelivery() {
    }
    
    /**
     * 快递柜ID
     */
    @Id
    @Column(name = "ST_DELIVERY_ID")
    private String stDeliveryId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_MACHINE_ID")
    private String stMachineId;
    
    /**
     * 设备柜号
     */
    @Column(name = "ST_CABINET_NO")
    private String stCabinetNo;
    
    /**
     * 取证号
     */
    @Column(name = "ST_CERT_FLOW_NO")
    private String stCertFlowNo;
    
    /**
     * 收件人手机号
     */
    @Column(name = "ST_RECEIVER_PHONE")
    private String stReceiverPhone;
    
    /**
     * 收件人姓名
     */
    @Column(name = "ST_RECEIVER_NAME")
    private String stReceiverName;
    
    /**
     * 收件人身份证号
     */
    @Column(name = "ST_RECEIVER_IDCARD")
    private String stReceiverIdcard;
    
    /**
     * 投件人（用户ID）
     */
    @Column(name = "ST_SENDER_ID")
    private String stSenderId;
    
    /**
     * 投件人姓名
     */
    @Column(name = "ST_SENDER_NAME")
    private String stSenderName;
    /**
     * 投件人手机号
     */
    @Column(name = "ST_SENDER_PHONE")
    private String stSenderPhone;
    /**
     * 证照类型
     */
    @Column(name = "NM_CERT_TYPE")
    private BigDecimal nmCertType;
    /**
     * 证照名称
     */
    @Column(name = "ST_CERT_NAME")
    private String stCertName;
    
    /**
     * 类型
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
    /**
     * 关联办件
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 企业/个人 名称
     */
    @Column(name = "ST_NAME")
    private String stName;
    
    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 取件码
     */
    @Column(name = "ST_RECEIVE_NUM")
    private String stReceiveNum;
    
    /**
     * 描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 投放时间
     */
    @Column(name = "DT_STORE")
    private Timestamp dtStore;
    
    /**
     * 取走时间
     */
    @Column(name = "DT_TAKE")
    private Timestamp dtTake;
    
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
     * 快递柜ID
     */
    public String getStDeliveryId() {
        return this.stDeliveryId;
    }
    
    /**
     * 快递柜ID
     */
    public String stDeliveryId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeliveryId);
    }

    /**
     * 快递柜ID
     */
    public void setStDeliveryId(String stDeliveryId) {
        stDeliveryId = StringUtil.substringBySize(stDeliveryId, 50, "GB18030");
        this.stDeliveryId = stDeliveryId;
    }
    
	/**
     * 设备ID
     */
    public String getStMachineId() {
        return this.stMachineId;
    }
    
    /**
     * 设备ID
     */
    public String stMachineId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMachineId);
    }

    /**
     * 设备ID
     */
    public void setStMachineId(String stMachineId) {
        stMachineId = StringUtil.substringBySize(stMachineId, 50, "GB18030");
        this.stMachineId = stMachineId;
    }
    
	/**
     * 设备柜号
     */
    public String getStCabinetNo() {
        return this.stCabinetNo;
    }
    
    /**
     * 设备柜号
     */
    public String stCabinetNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCabinetNo);
    }

    /**
     * 设备柜号
     */
    public void setStCabinetNo(String stCabinetNo) {
        stCabinetNo = StringUtil.substringBySize(stCabinetNo, 50, "GB18030");
        this.stCabinetNo = stCabinetNo;
    }
    
	/**
     * 取证号
     */
    public String getStCertFlowNo() {
        return this.stCertFlowNo;
    }
    
    /**
     * 取证号
     */
    public String stCertFlowNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertFlowNo);
    }

    /**
     * 取证号
     */
    public void setStCertFlowNo(String stCertFlowNo) {
        stCertFlowNo = StringUtil.substringBySize(stCertFlowNo, 50, "GB18030");
        this.stCertFlowNo = stCertFlowNo;
    }
    
	/**
     * 收件人手机号
     */
    public String getStReceiverPhone() {
        return this.stReceiverPhone;
    }
    
    /**
     * 收件人手机号
     */
    public String stReceiverPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverPhone);
    }

    /**
     * 收件人手机号
     */
    public void setStReceiverPhone(String stReceiverPhone) {
        stReceiverPhone = StringUtil.substringBySize(stReceiverPhone, 50, "GB18030");
        this.stReceiverPhone = stReceiverPhone;
    }
    
	/**
     * 收件人姓名
     */
    public String getStReceiverName() {
        return this.stReceiverName;
    }
    
    /**
     * 收件人姓名
     */
    public String stReceiverName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverName);
    }

    /**
     * 收件人姓名
     */
    public void setStReceiverName(String stReceiverName) {
        stReceiverName = StringUtil.substringBySize(stReceiverName, 50, "GB18030");
        this.stReceiverName = stReceiverName;
    }
    
	/**
     * 收件人身份证号
     */
    public String getStReceiverIdcard() {
        return this.stReceiverIdcard;
    }
    
    /**
     * 收件人身份证号
     */
    public String stReceiverIdcard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverIdcard);
    }

    /**
     * 收件人身份证号
     */
    public void setStReceiverIdcard(String stReceiverIdcard) {
        stReceiverIdcard = StringUtil.substringBySize(stReceiverIdcard, 50, "GB18030");
        this.stReceiverIdcard = stReceiverIdcard;
    }
    
	/**
     * 投件人（用户ID）
     */
    public String getStSenderId() {
        return this.stSenderId;
    }
    
    /**
     * 投件人（用户ID）
     */
    public String stSenderId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderId);
    }

    /**
     * 投件人（用户ID）
     */
    public void setStSenderId(String stSenderId) {
        stSenderId = StringUtil.substringBySize(stSenderId, 50, "GB18030");
        this.stSenderId = stSenderId;
    }
    
	/**
     * 投件人姓名
     */
    public String getStSenderName() {
        return this.stSenderName;
    }
    
    /**
     * 投件人姓名
     */
    public String stSenderName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderName);
    }

    /**
     * 投件人姓名
     */
    public void setStSenderName(String stSenderName) {
        stSenderName = StringUtil.substringBySize(stSenderName, 50, "GB18030");
        this.stSenderName = stSenderName;
    }
    /**
     * 投件人手机号
     */
    public String getStSenderPhone() {
        return this.stSenderPhone;
    }
    
    /**
     * 投件人手机号
     */
    public String stSenderPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderPhone);
    }

    /**
     *投件人手机号
     */
    public void setStSenderPhone(String stSenderPhone) {
        stSenderPhone = StringUtil.substringBySize(stSenderPhone, 50, "GB18030");
        this.stSenderPhone = stSenderPhone;
    }
    /**
     * 证照类型
     */
    public BigDecimal getNmCertType() {
        return this.nmType;
    }
    
    /**
     * 证照类型
     */
    public String nmCertType2Html(int precision) {
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
            return new DecimalFormat(pattern).format(this.nmCertType);
        }
    }

    /**
     * 证照类型
     */
    public void setNmCertType(BigDecimal nmCertType) {
        this.nmCertType = nmCertType;
    }
    
	/**
     * 证照名称
     */
    public String getStCertName() {
        return this.stCertName;
    }
    
    /**
     * 证照名称
     */
    public String stCertName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertName);
    }

    /**
     * 证照名称
     */
    public void setStCertName(String stCertName) {
        stCertName = StringUtil.substringBySize(stCertName, 50, "GB18030");
        this.stCertName = stCertName;
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
     * 关联办件
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 关联办件
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 关联办件
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }
    
	/**
     * 企业/个人 名称
     */
    public String getStName() {
        return this.stName;
    }
    
    /**
     * 企业/个人 名称
     */
    public String stName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stName);
    }

    /**
     * 企业/个人 名称
     */
    public void setStName(String stName) {
        stName = StringUtil.substringBySize(stName, 100, "GB18030");
        this.stName = stName;
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
     * 取件码
     */
    public String getStReceiveNum() {
        return this.stReceiveNum;
    }
    
    /**
     * 取件码
     */
    public String stReceiveNum2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiveNum);
    }

    /**
     * 取件码
     */
    public void setStReceiveNum(String stReceiveNum) {
        stReceiveNum = StringUtil.substringBySize(stReceiveNum, 50, "GB18030");
        this.stReceiveNum = stReceiveNum;
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
        stDesc = StringUtil.substringBySize(stDesc, 200, "GB18030");
        this.stDesc = stDesc;
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
     * 投放时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtStore() {
        return this.dtStore;
    }
    
    /**
     * 投放时间
     */
    public String dtStore2Html(String pattern) {
        if (this.dtStore == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtStore);
        }
    }

    /**
     * 投放时间
     */
    public void setDtStore(Timestamp dtStore) {
        this.dtStore = dtStore;
    }

	/**
     * 取走时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtTake() {
        return this.dtTake;
    }
    
    /**
     * 取走时间
     */
    public String dtTake2Html(String pattern) {
        if (this.dtTake == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtTake);
        }
    }

    /**
     * 取走时间
     */
    public void setDtTake(Timestamp dtTake) {
        this.dtTake = dtTake;
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