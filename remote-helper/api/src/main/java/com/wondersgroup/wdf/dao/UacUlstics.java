package com.wondersgroup.wdf.dao;

import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 办件物流
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ULSTICS")
public class UacUlstics implements Serializable {
    
    /**
     * 办件物流
     */
    public static final String UAC_ULSTICS = "UAC_ULSTICS";
    
    /**
     * 办件物流ID
     */
    public static final String ST_UNION_LOGISTICS_ID = "ST_UNION_LOGISTICS_ID";
    
    /**
     * 收货人
     */
    public static final String ST_RECEIVER = "ST_RECEIVER";
    
    /**
     * 收货人手机
     */
    public static final String ST_RECEIVER_PHONE = "ST_RECEIVER_PHONE";
    
    /**
     * 收货人邮政编码
     */
    public static final String ST_RECEIVER_ZIPCODE = "ST_RECEIVER_ZIPCODE";
    
    /**
     * 收货人省区市
     */
    public static final String ST_RECEIVER_PROVINCE = "ST_RECEIVER_PROVINCE";
    
    /**
     * 收货人城市
     */
    public static final String ST_RECEIVER_CITY = "ST_RECEIVER_CITY";
    
    /**
     * 收货人区县
     */
    public static final String ST_RECEIVER_AREA = "ST_RECEIVER_AREA";
    
    /**
     * 收货人详细地址
     */
    public static final String ST_RECEIVER_ADDRESS = "ST_RECEIVER_ADDRESS";
    
    /**
     * 发货人
     */
    public static final String ST_SENDER = "ST_SENDER";
    
    /**
     * 发货人手机
     */
    public static final String ST_SENDER_PHONE = "ST_SENDER_PHONE";
    
    /**
     * 发货人邮政编码
     */
    public static final String ST_SENDER_ZIPCODE = "ST_SENDER_ZIPCODE";
    
    /**
     * 发货人省区市
     */
    public static final String ST_SENDER_PROVINCE = "ST_SENDER_PROVINCE";
    
    /**
     * 发货人城市
     */
    public static final String ST_SENDER_CITY = "ST_SENDER_CITY";
    
    /**
     * 发货人区县
     */
    public static final String ST_SENDER_AREA = "ST_SENDER_AREA";
    
    /**
     * 发货人详细地址
     */
    public static final String ST_SENDER_ADDRESS = "ST_SENDER_ADDRESS";
    
    /**
     * 配送公司
     */
    public static final String ST_SHIP_COMPANY = "ST_SHIP_COMPANY";
    
    /**
     * 物流类型
     */
    public static final String NM_TYPE = "NM_TYPE";
    
    /**
     * 物流发起
     */
    public static final String NM_LOGISTICS_TYPE = "NM_LOGISTICS_TYPE";
    
    /**
     * 物流单号
     */
    public static final String ST_LOGISTICS_NO = "ST_LOGISTICS_NO";
    
    /**
     * 订单唯一标识
     */
    public static final String ST_ORDER_ID = "ST_ORDER_ID";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 部门代码
     */
    public static final String ST_DEPART_CODE = "ST_DEPART_CODE";
    
    /**
     * 部门名称
     */
    public static final String ST_DEPART_NAME = "ST_DEPART_NAME";
    
    /**
     * 操作用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";

    /**
     * 发证日期
     */
    public static final String DT_PORDER = "DT_PORDER";

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
    
    public UacUlstics() {
    }
    
    /**
     * 办件物流ID
     */
    @Id
    @Column(name = "ST_UNION_LOGISTICS_ID")
    private String stUnionLogisticsId;
    
    /**
     * 收货人
     */
    @Column(name = "ST_RECEIVER")
    private String stReceiver;
    
    /**
     * 收货人手机
     */
    @Column(name = "ST_RECEIVER_PHONE")
    private String stReceiverPhone;
    
    /**
     * 收货人邮政编码
     */
    @Column(name = "ST_RECEIVER_ZIPCODE")
    private String stReceiverZipcode;
    
    /**
     * 收货人省区市
     */
    @Column(name = "ST_RECEIVER_PROVINCE")
    private String stReceiverProvince;
    
    /**
     * 收货人城市
     */
    @Column(name = "ST_RECEIVER_CITY")
    private String stReceiverCity;
    
    /**
     * 收货人区县
     */
    @Column(name = "ST_RECEIVER_AREA")
    private String stReceiverArea;
    
    /**
     * 收货人详细地址
     */
    @Column(name = "ST_RECEIVER_ADDRESS")
    private String stReceiverAddress;
    
    /**
     * 发货人
     */
    @Column(name = "ST_SENDER")
    private String stSender;
    
    /**
     * 发货人手机
     */
    @Column(name = "ST_SENDER_PHONE")
    private String stSenderPhone;
    
    /**
     * 发货人邮政编码
     */
    @Column(name = "ST_SENDER_ZIPCODE")
    private String stSenderZipcode;
    
    /**
     * 发货人省区市
     */
    @Column(name = "ST_SENDER_PROVINCE")
    private String stSenderProvince;
    
    /**
     * 发货人城市
     */
    @Column(name = "ST_SENDER_CITY")
    private String stSenderCity;
    
    /**
     * 发货人区县
     */
    @Column(name = "ST_SENDER_AREA")
    private String stSenderArea;
    
    /**
     * 发货人详细地址
     */
    @Column(name = "ST_SENDER_ADDRESS")
    private String stSenderAddress;
    
    /**
     * 配送公司
     */
    @Column(name = "ST_SHIP_COMPANY")
    private String stShipCompany;
    
    /**
     * 物流类型
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
    /**
     * 物流发起
     */
    @Column(name = "NM_LOGISTICS_TYPE")
    private BigDecimal nmLogisticsType;
    
    /**
     * 物流单号
     */
    @Column(name = "ST_LOGISTICS_NO")
    private String stLogisticsNo;
    
    /**
     * 订单唯一标识
     */
    @Column(name = "ST_ORDER_ID")
    private String stOrderId;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
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
     * 操作用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;

    /**
     * 下单时间
     */
    @Column(name = "DT_PORDER")
    private Timestamp dtPorder;

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
     * 办件物流ID
     */
    public String getStUnionLogisticsId() {
        return this.stUnionLogisticsId;
    }
    
    /**
     * 办件物流ID
     */
    public String stUnionLogisticsId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnionLogisticsId);
    }

    /**
     * 办件物流ID
     */
    public void setStUnionLogisticsId(String stUnionLogisticsId) {
        stUnionLogisticsId = StringUtil.substringBySize(stUnionLogisticsId, 50, "GB18030");
        this.stUnionLogisticsId = stUnionLogisticsId;
    }
    
	/**
     * 收货人
     */
    public String getStReceiver() {
        return this.stReceiver;
    }
    
    /**
     * 收货人
     */
    public String stReceiver2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiver);
    }

    /**
     * 收货人
     */
    public void setStReceiver(String stReceiver) {
        stReceiver = StringUtil.substringBySize(stReceiver, 50, "GB18030");
        this.stReceiver = stReceiver;
    }
    
	/**
     * 收货人手机
     */
    public String getStReceiverPhone() {
        return this.stReceiverPhone;
    }
    
    /**
     * 收货人手机
     */
    public String stReceiverPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverPhone);
    }

    /**
     * 收货人手机
     */
    public void setStReceiverPhone(String stReceiverPhone) {
        stReceiverPhone = StringUtil.substringBySize(stReceiverPhone, 50, "GB18030");
        this.stReceiverPhone = stReceiverPhone;
    }
    
	/**
     * 收货人邮政编码
     */
    public String getStReceiverZipcode() {
        return this.stReceiverZipcode;
    }
    
    /**
     * 收货人邮政编码
     */
    public String stReceiverZipcode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverZipcode);
    }

    /**
     * 收货人邮政编码
     */
    public void setStReceiverZipcode(String stReceiverZipcode) {
        stReceiverZipcode = StringUtil.substringBySize(stReceiverZipcode, 20, "GB18030");
        this.stReceiverZipcode = stReceiverZipcode;
    }
    
	/**
     * 收货人省区市
     */
    public String getStReceiverProvince() {
        return this.stReceiverProvince;
    }
    
    /**
     * 收货人省区市
     */
    public String stReceiverProvince2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverProvince);
    }

    /**
     * 收货人省区市
     */
    public void setStReceiverProvince(String stReceiverProvince) {
        stReceiverProvince = StringUtil.substringBySize(stReceiverProvince, 20, "GB18030");
        this.stReceiverProvince = stReceiverProvince;
    }
    
	/**
     * 收货人城市
     */
    public String getStReceiverCity() {
        return this.stReceiverCity;
    }
    
    /**
     * 收货人城市
     */
    public String stReceiverCity2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverCity);
    }

    /**
     * 收货人城市
     */
    public void setStReceiverCity(String stReceiverCity) {
        stReceiverCity = StringUtil.substringBySize(stReceiverCity, 50, "GB18030");
        this.stReceiverCity = stReceiverCity;
    }
    
	/**
     * 收货人区县
     */
    public String getStReceiverArea() {
        return this.stReceiverArea;
    }
    
    /**
     * 收货人区县
     */
    public String stReceiverArea2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverArea);
    }

    /**
     * 收货人区县
     */
    public void setStReceiverArea(String stReceiverArea) {
        stReceiverArea = StringUtil.substringBySize(stReceiverArea, 50, "GB18030");
        this.stReceiverArea = stReceiverArea;
    }
    
	/**
     * 收货人详细地址
     */
    public String getStReceiverAddress() {
        return this.stReceiverAddress;
    }
    
    /**
     * 收货人详细地址
     */
    public String stReceiverAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverAddress);
    }

    /**
     * 收货人详细地址
     */
    public void setStReceiverAddress(String stReceiverAddress) {
        stReceiverAddress = StringUtil.substringBySize(stReceiverAddress, 200, "GB18030");
        this.stReceiverAddress = stReceiverAddress;
    }
    
	/**
     * 发货人
     */
    public String getStSender() {
        return this.stSender;
    }
    
    /**
     * 发货人
     */
    public String stSender2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSender);
    }

    /**
     * 发货人
     */
    public void setStSender(String stSender) {
        stSender = StringUtil.substringBySize(stSender, 20, "GB18030");
        this.stSender = stSender;
    }
    
	/**
     * 发货人手机
     */
    public String getStSenderPhone() {
        return this.stSenderPhone;
    }
    
    /**
     * 发货人手机
     */
    public String stSenderPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderPhone);
    }

    /**
     * 发货人手机
     */
    public void setStSenderPhone(String stSenderPhone) {
        stSenderPhone = StringUtil.substringBySize(stSenderPhone, 20, "GB18030");
        this.stSenderPhone = stSenderPhone;
    }
    
	/**
     * 发货人邮政编码
     */
    public String getStSenderZipcode() {
        return this.stSenderZipcode;
    }
    
    /**
     * 发货人邮政编码
     */
    public String stSenderZipcode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderZipcode);
    }

    /**
     * 发货人邮政编码
     */
    public void setStSenderZipcode(String stSenderZipcode) {
        stSenderZipcode = StringUtil.substringBySize(stSenderZipcode, 10, "GB18030");
        this.stSenderZipcode = stSenderZipcode;
    }
    
	/**
     * 发货人省区市
     */
    public String getStSenderProvince() {
        return this.stSenderProvince;
    }
    
    /**
     * 发货人省区市
     */
    public String stSenderProvince2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderProvince);
    }

    /**
     * 发货人省区市
     */
    public void setStSenderProvince(String stSenderProvince) {
        stSenderProvince = StringUtil.substringBySize(stSenderProvince, 20, "GB18030");
        this.stSenderProvince = stSenderProvince;
    }
    
	/**
     * 发货人城市
     */
    public String getStSenderCity() {
        return this.stSenderCity;
    }
    
    /**
     * 发货人城市
     */
    public String stSenderCity2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderCity);
    }

    /**
     * 发货人城市
     */
    public void setStSenderCity(String stSenderCity) {
        stSenderCity = StringUtil.substringBySize(stSenderCity, 50, "GB18030");
        this.stSenderCity = stSenderCity;
    }
    
	/**
     * 发货人区县
     */
    public String getStSenderArea() {
        return this.stSenderArea;
    }
    
    /**
     * 发货人区县
     */
    public String stSenderArea2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderArea);
    }

    /**
     * 发货人区县
     */
    public void setStSenderArea(String stSenderArea) {
        stSenderArea = StringUtil.substringBySize(stSenderArea, 50, "GB18030");
        this.stSenderArea = stSenderArea;
    }
    
	/**
     * 发货人详细地址
     */
    public String getStSenderAddress() {
        return this.stSenderAddress;
    }
    
    /**
     * 发货人详细地址
     */
    public String stSenderAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSenderAddress);
    }

    /**
     * 发货人详细地址
     */
    public void setStSenderAddress(String stSenderAddress) {
        stSenderAddress = StringUtil.substringBySize(stSenderAddress, 200, "GB18030");
        this.stSenderAddress = stSenderAddress;
    }
    
	/**
     * 配送公司
     */
    public String getStShipCompany() {
        return this.stShipCompany;
    }
    
    /**
     * 配送公司
     */
    public String stShipCompany2Html() {
        return StringHelper.replaceHTMLSymbol(this.stShipCompany);
    }

    /**
     * 配送公司
     */
    public void setStShipCompany(String stShipCompany) {
        stShipCompany = StringUtil.substringBySize(stShipCompany, 50, "GB18030");
        this.stShipCompany = stShipCompany;
    }

	/**
     * 物流类型
     */
    public BigDecimal getNmType() {
        return this.nmType;
    }
    
    /**
     * 物流类型
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
     * 物流类型
     */
    public void setNmType(BigDecimal nmType) {
        this.nmType = nmType;
    }

	/**
     * 物流发起
     */
    public BigDecimal getNmLogisticsType() {
        return this.nmLogisticsType;
    }
    
    /**
     * 物流发起
     */
    public String nmLogisticsType2Html(int precision) {
        if (this.nmLogisticsType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLogisticsType);
        }
    }

    /**
     * 物流发起
     */
    public void setNmLogisticsType(BigDecimal nmLogisticsType) {
        this.nmLogisticsType = nmLogisticsType;
    }
    
	/**
     * 物流单号
     */
    public String getStLogisticsNo() {
        return this.stLogisticsNo;
    }
    
    /**
     * 物流单号
     */
    public String stLogisticsNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogisticsNo);
    }

    /**
     * 物流单号
     */
    public void setStLogisticsNo(String stLogisticsNo) {
        stLogisticsNo = StringUtil.substringBySize(stLogisticsNo, 50, "GB18030");
        this.stLogisticsNo = stLogisticsNo;
    }
    
	/**
     * 订单唯一标识
     */
    public String getStOrderId() {
        return this.stOrderId;
    }
    
    /**
     * 订单唯一标识
     */
    public String stOrderId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrderId);
    }

    /**
     * 订单唯一标识
     */
    public void setStOrderId(String stOrderId) {
        stOrderId = StringUtil.substringBySize(stOrderId, 50, "GB18030");
        this.stOrderId = stOrderId;
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
     * 操作用户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 操作用户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 操作用户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }

    /**
     * 下单时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtPorder() {
        return this.dtPorder;
    }

    /**
     * 下单时间
     */
    public String dtPorder2Html(String pattern) {
        if (this.dtPorder == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtPorder);
        }
    }

    /**
     * 下单时间
     */
    public void setDtPorder(Timestamp dtPorder) {
        this.dtPorder = dtPorder;
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