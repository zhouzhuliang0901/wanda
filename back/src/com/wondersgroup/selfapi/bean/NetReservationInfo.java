package com.wondersgroup.selfapi.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;
import org.jeecgframework.poi.excel.annotation.Excel;

import reindeer.base.utils.StringUtil;

import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 预约信息表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "NET_RESERVATION_INFO")
public class NetReservationInfo implements Serializable {
    
    /**
     * 预约信息表
     */
    public static final String NET_RESERVATION_INFO = "NET_RESERVATION_INFO";
    
    /**
     * 预约信息ID
     */
    public static final String ST_RESERVATION_ID = "ST_RESERVATION_ID";
    
    /**
     * 预约号
     */
    public static final String ST_RESERVATION_NO = "ST_RESERVATION_NO";
    
    /**
     * 统一审批编号
     */
    public static final String ST_BUSINESS_NO = "ST_BUSINESS_NO";
    
    /**
     * 事项信息ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_NO = "ST_ITEM_NO";
 
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 组别号
     */
    public static final String ST_GROUP_CODE = "ST_GROUP_CODE";
    
    /**
     * 预约操作时间
     */
    public static final String DT_OPERATION = "DT_OPERATION";
    
    /**
     * 预约开始时间
     */
    public static final String DT_RESERVATION_START = "DT_RESERVATION_START";
    
    /**
     * 预约结束时间
     */
    public static final String DT_RESERVATION_END = "DT_RESERVATION_END";
    
    /**
     * 用户账户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 用户姓名
     */
    public static final String ST_USER_NAME = "ST_USER_NAME";
    
    /**
     * 用户手机号
     */
    public static final String ST_MOBILE = "ST_MOBILE";
    
    /**
     * 用户证件类型
     */
    public static final String NM_IDENTITY_TYPE = "NM_IDENTITY_TYPE";
    
    /**
     * 用户证件号
     */
    public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";
    
    /**
     * 状态位
     */
    public static final String NM_REMOVED = "NM_REMOVED";
    
    /**
     * 预约规则时间详细ID
     */
    public static final String ST_DETAIL_ID = "ST_DETAIL_ID";
    
    /**
     * 预约来源
     */
    public static final String NM_DATA_SOURCE = "NM_DATA_SOURCE";
    
    /**
     * 部门ID
     */
    public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";
    
    /**
     * 部门完整名字
     */
    public static final String ST_ORGAN_NAME = "ST_ORGAN_NAME";
    
    /**
     * 部门代码
     */
    public static final String ST_ORGAN_CODE = "ST_ORGAN_CODE";
    
    /**
     * 办理点信息
     */
    public static final String ST_HALL_INFO = "ST_HALL_INFO";
    
    /**
     * 规则显示时间
     */
    public static final String ST_SHOW = "ST_SHOW";
    
    /**
     * 企业名称
     */
    public static final String ST_UNIT = "ST_UNIT";
    
    /**
     * 统一社会信用代码
     */
    public static final String ST_UNIFIED = "ST_UNIFIED";
 
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public NetReservationInfo() {
    }
    
    /**
     * 预约信息ID
     */
    @Id
    @Column(name = "ST_RESERVATION_ID")
    private String stReservationId;
    
    /**
     * 预约号
     */
    @Excel(name = "预约号",width = 15)
    @Column(name = "ST_RESERVATION_NO")
    private String stReservationNo;
    
    /**
     * 用户姓名
     */
    @Excel(name = "用户姓名",width = 10)
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 用户手机号
     */
    @Excel(name = "用户手机号",width = 15)
    @Column(name = "ST_MOBILE")
    private String stMobile;
    
    /**
     * 用户证件类型
     */
    @Column(name = "NM_IDENTITY_TYPE")
    private BigDecimal nmIdentityType;
    
    /**
     * 用户证件号
     */
    @Excel(name = "用户证件号",width = 25)
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentityNo;
    
    /**
     * 统一审批编号
     */
    @Column(name = "ST_BUSINESS_NO")
    private String stBusinessNo;
    
    /**
     * 事项信息ID
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 事项名称
     */
    @Excel(name = "事项名称",width = 40)
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 组别号
     */
    @Column(name = "ST_GROUP_CODE")
    private String stGroupCode;
    
    /**
     * 预约操作时间
     */
    @Excel(name = "预约操作时间",width = 25)
    @Column(name = "DT_OPERATION")
    private Timestamp dtOperation;
    
    /**
     * 预约开始时间
     */
    @Excel(name = "预约开始时间",width = 25)
    @Column(name = "DT_RESERVATION_START")
    private Timestamp dtReservationStart;
    
    /**
     * 预约结束时间
     */
    @Excel(name = "预约结束时间",width = 25)
    @Column(name = "DT_RESERVATION_END")
    private Timestamp dtReservationEnd;
    
    /**
     * 用户账户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    
    /**
     * 状态位
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;
    
    /**
     * 预约规则时间详细ID
     */
    @Column(name = "ST_DETAIL_ID")
    private String stDetailId;
    
    /**
     * 预约来源
     */
    @Excel(name = "预约来源",width = 15,replace = {"微信_0","网站_1","app_2","自助终端_3","支付宝_4","中心网站_5","市民云_6"})
    @Column(name = "NM_DATA_SOURCE")
    private BigDecimal nmDataSource;
    
    /**
     * 部门ID
     */
    @Column(name = "NM_ORGAN_NODE_ID")
    private BigDecimal nmOrganNodeId;
    
    /**
     * 部门完整名字
     */
    @Column(name = "ST_ORGAN_NAME")
    private String stOrganName;
    
    /**
     * 部门代码
     */
    @Column(name = "ST_ORGAN_CODE")
    private String stOrganCode;
    
    /**
     * 办理点信息
     */
    @Column(name = "ST_HALL_INFO")
    private String stHallInfo;
    
    /**
     * 规则显示时间
     */
    @Column(name = "ST_SHOW")
    private String stShow;
    
    /**
     * 企业名称
     */
    @Excel(name = "企业名称",width = 25)
    @Column(name = "ST_UNIT")
    private String stUnit;
    
    /**
     * 统一社会信用代码
     */
    @Excel(name = "统一社会信用代码",width = 25)
    @Column(name = "ST_UNIFIED")
    private String stUnified;
    
    /**
     * 扩展字段1
     */
    @Excel(name = "预约状态",width = 15)
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    /**
     * 扩展字段2
     */
    @Column(name = "ST_EXT2")
    private String stExt2;
    
	/**
     * 预约信息ID
     */
    public String getStReservationId() {
        return this.stReservationId;
    }
    
    /**
     * 预约信息ID
     */
    public String stReservationId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReservationId);
    }

    /**
     * 预约信息ID
     */
    public void setStReservationId(String stReservationId) {
        stReservationId = StringUtil.substringBySize(stReservationId, 50, "GB18030");
        this.stReservationId = stReservationId;
    }
    
	/**
     * 预约号
     */
    public String getStReservationNo() {
        return this.stReservationNo;
    }
    
    /**
     * 预约号
     */
    public String stReservationNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReservationNo);
    }

    /**
     * 预约号
     */
    public void setStReservationNo(String stReservationNo) {
        stReservationNo = StringUtil.substringBySize(stReservationNo, 50, "GB18030");
        this.stReservationNo = stReservationNo;
    }
    
	/**
     * 统一审批编号
     */
    public String getStBusinessNo() {
        return this.stBusinessNo;
    }
    
    /**
     * 统一审批编号
     */
    public String stBusinessNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessNo);
    }

    /**
     * 统一审批编号
     */
    public void setStBusinessNo(String stBusinessNo) {
        stBusinessNo = StringUtil.substringBySize(stBusinessNo, 50, "GB18030");
        this.stBusinessNo = stBusinessNo;
    }
    
	/**
     * 事项信息ID
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 事项信息ID
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项信息ID
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
     * 组别号
     */
    public String getStGroupCode() {
        return this.stGroupCode;
    }
    
    /**
     * 组别号
     */
    public String stGroupCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stGroupCode);
    }

    /**
     * 组别号
     */
    public void setStGroupCode(String stGroupCode) {
        stGroupCode = StringUtil.substringBySize(stGroupCode, 50, "GB18030");
        this.stGroupCode = stGroupCode;
    }

	/**
     * 预约操作时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtOperation() {
        return this.dtOperation;
    }
    
    /**
     * 预约操作时间
     */
    public String dtOperation2Html(String pattern) {
        if (this.dtOperation == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtOperation);
        }
    }

    /**
     * 预约操作时间
     */
    public void setDtOperation(Timestamp dtOperation) {
        this.dtOperation = dtOperation;
    }

	/**
     * 预约开始时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtReservationStart() {
        return this.dtReservationStart;
    }
    
    /**
     * 预约开始时间
     */
    public String dtReservationStart2Html(String pattern) {
        if (this.dtReservationStart == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtReservationStart);
        }
    }

    /**
     * 预约开始时间
     */
    public void setDtReservationStart(Timestamp dtReservationStart) {
        this.dtReservationStart = dtReservationStart;
    }

	/**
     * 预约结束时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtReservationEnd() {
        return this.dtReservationEnd;
    }
    
    /**
     * 预约结束时间
     */
    public String dtReservationEnd2Html(String pattern) {
        if (this.dtReservationEnd == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtReservationEnd);
        }
    }

    /**
     * 预约结束时间
     */
    public void setDtReservationEnd(Timestamp dtReservationEnd) {
        this.dtReservationEnd = dtReservationEnd;
    }
    
	/**
     * 用户账户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 用户账户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 用户账户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }
    
	/**
     * 用户姓名
     */
    public String getStUserName() {
        return this.stUserName;
    }
    
    /**
     * 用户姓名
     */
    public String stUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserName);
    }

    /**
     * 用户姓名
     */
    public void setStUserName(String stUserName) {
        stUserName = StringUtil.substringBySize(stUserName, 50, "GB18030");
        this.stUserName = stUserName;
    }
    
	/**
     * 用户手机号
     */
    public String getStMobile() {
        return this.stMobile;
    }
    
    /**
     * 用户手机号
     */
    public String stMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMobile);
    }

    /**
     * 用户手机号
     */
    public void setStMobile(String stMobile) {
        stMobile = StringUtil.substringBySize(stMobile, 50, "GB18030");
        this.stMobile = stMobile;
    }

	/**
     * 用户证件类型
     */
    public BigDecimal getNmIdentityType() {
        return this.nmIdentityType;
    }
    
    /**
     * 用户证件类型
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
     * 用户证件类型
     */
    public void setNmIdentityType(BigDecimal nmIdentityType) {
        this.nmIdentityType = nmIdentityType;
    }
    
	/**
     * 用户证件号
     */
    public String getStIdentityNo() {
        return this.stIdentityNo;
    }
    
    /**
     * 用户证件号
     */
    public String stIdentityNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIdentityNo);
    }

    /**
     * 用户证件号
     */
    public void setStIdentityNo(String stIdentityNo) {
        stIdentityNo = StringUtil.substringBySize(stIdentityNo, 50, "GB18030");
        this.stIdentityNo = stIdentityNo;
    }

	/**
     * 状态位
     */
    public BigDecimal getNmRemoved() {
        return this.nmRemoved;
    }
    
    /**
     * 状态位
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
     * 状态位
     */
    public void setNmRemoved(BigDecimal nmRemoved) {
        this.nmRemoved = nmRemoved;
    }
    
	/**
     * 预约规则时间详细ID
     */
    public String getStDetailId() {
        return this.stDetailId;
    }
    
    /**
     * 预约规则时间详细ID
     */
    public String stDetailId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDetailId);
    }

    /**
     * 预约规则时间详细ID
     */
    public void setStDetailId(String stDetailId) {
        stDetailId = StringUtil.substringBySize(stDetailId, 50, "GB18030");
        this.stDetailId = stDetailId;
    }

	/**
     * 预约来源
     */
    public BigDecimal getNmDataSource() {
        return this.nmDataSource;
    }
    
    /**
     * 预约来源
     */
    public String nmDataSource2Html(int precision) {
        if (this.nmDataSource == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDataSource);
        }
    }

    /**
     * 预约来源
     */
    public void setNmDataSource(BigDecimal nmDataSource) {
        this.nmDataSource = nmDataSource;
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
     * 部门完整名字
     */
    public String getStOrganName() {
        return this.stOrganName;
    }
    
    /**
     * 部门完整名字
     */
    public String stOrganName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganName);
    }

    /**
     * 部门完整名字
     */
    public void setStOrganName(String stOrganName) {
        stOrganName = StringUtil.substringBySize(stOrganName, 50, "GB18030");
        this.stOrganName = stOrganName;
    }
    
	/**
     * 部门代码
     */
    public String getStOrganCode() {
        return this.stOrganCode;
    }
    
    /**
     * 部门代码
     */
    public String stOrganCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganCode);
    }

    /**
     * 部门代码
     */
    public void setStOrganCode(String stOrganCode) {
        stOrganCode = StringUtil.substringBySize(stOrganCode, 50, "GB18030");
        this.stOrganCode = stOrganCode;
    }
    
	/**
     * 办理点信息
     */
    public String getStHallInfo() {
        return this.stHallInfo;
    }
    
    /**
     * 办理点信息
     */
    public String stHallInfo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stHallInfo);
    }

    /**
     * 办理点信息
     */
    public void setStHallInfo(String stHallInfo) {
        stHallInfo = StringUtil.substringBySize(stHallInfo, 100, "GB18030");
        this.stHallInfo = stHallInfo;
    }
    
	/**
     * 规则显示时间
     */
    public String getStShow() {
        return this.stShow;
    }
    
    /**
     * 规则显示时间
     */
    public String stShow2Html() {
        return StringHelper.replaceHTMLSymbol(this.stShow);
    }

    /**
     * 规则显示时间
     */
    public void setStShow(String stShow) {
        stShow = StringUtil.substringBySize(stShow, 50, "GB18030");
        this.stShow = stShow;
    }
    
	/**
     * 企业名称
     */
    public String getStUnit() {
        return this.stUnit;
    }
    
    /**
     * 企业名称
     */
    public String stUnit2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnit);
    }

    /**
     * 企业名称
     */
    public void setStUnit(String stUnit) {
        stUnit = StringUtil.substringBySize(stUnit, 50, "GB18030");
        this.stUnit = stUnit;
    }
    
	/**
     * 统一社会信用代码
     */
    public String getStUnified() {
        return this.stUnified;
    }
    
    /**
     * 统一社会信用代码
     */
    public String stUnified2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUnified);
    }

    /**
     * 统一社会信用代码
     */
    public void setStUnified(String stUnified) {
        stUnified = StringUtil.substringBySize(stUnified, 50, "GB18030");
        this.stUnified = stUnified;
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