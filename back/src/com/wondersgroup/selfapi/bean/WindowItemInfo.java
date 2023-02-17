package com.wondersgroup.selfapi.bean;
		
import java.io.*;
import java.math.*;
import java.text.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;

import reindeer.base.utils.StringUtil;

import wfc.service.util.*;

/**
 * 事项信息表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_ITEM_INFO")
public class WindowItemInfo implements Serializable {
    
    /**
     * 事项信息表
     */
    public static final String WINDOW_ITEM_INFO = "WINDOW_ITEM_INFO";
    
    /**
     * 事项信息ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 生成统一审批编码时的前缀
     */
    public static final String ST_ITEM_PREFIX = "ST_ITEM_PREFIX";
    
    /**
     * 内嵌页面地址
     */
    public static final String ST_INCLUDE_PATH = "ST_INCLUDE_PATH";
    
    /**
     * 事项类型
     */
    public static final String NM_TYPE = "NM_TYPE";
    
    /**
     * 打印类型
     */
    public static final String NM_PRINT_TYPE = "NM_PRINT_TYPE";
    
    /**
     * 是否需要受理编码
     */
    public static final String NM_NEED_BUSINESS_CODE = "NM_NEED_BUSINESS_CODE";
    
    /**
     * 是否需要预约
     */
    public static final String NM_NEED_RESERVATION = "NM_NEED_RESERVATION";
    
    /**
     * 第三方预约地址
     */
    public static final String ST_RESERVATION_URL = "ST_RESERVATION_URL";
    
    /**
     * 展示顺序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 网站展示顺序
     */
    public static final String NM_NET_ORDER = "NM_NET_ORDER";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_NO = "ST_ITEM_NO";
    
    /**
     * 是否收费
     */
    public static final String NM_COST = "NM_COST";
    
    /**
     * 部门完整的名字
     */
    public static final String ST_GROUP_COMPLETE_NAME = "ST_GROUP_COMPLETE_NAME";
    
    /**
     * 凭证出处名称
     */
    public static final String ST_CREDENTAIALS_OUTS = "ST_CREDENTAIALS_OUTS";
    
    /**
     * 补正工作日
     */
    public static final String NM_CORRECTION_DATE = "NM_CORRECTION_DATE";
    
    /**
     * 法定时限
     */
    public static final String ST_LEGAL_TIME = "ST_LEGAL_TIME";
    
    /**
     * 承诺时限
     */
    public static final String ST_PROMISE_TIME = "ST_PROMISE_TIME";
    
    /**
     * 是否在PAD上显示
     */
    public static final String NM_DISPLAY = "NM_DISPLAY";
    
    /**
     * 部门ID
     */
    public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";
    
    /**
     * 部门NAME
     */
    public static final String NM_ORGAN_NODE_NAME = "NM_ORGAN_NODE_NAME";
    
    /**
     * 事项来源
     */
    public static final String NM_ITEM_SOURCE = "NM_ITEM_SOURCE";
    
    /**
     * 事项类别:便民服务,行政审批
     */
    public static final String ST_ITEM_TYPE = "ST_ITEM_TYPE";
    
    /**
     * 与审改事项关联
     */
    public static final String ST_LINK_ID = "ST_LINK_ID";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public WindowItemInfo() {
    }
    
    /**
     * 事项信息ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 生成统一审批编码时的前缀
     */
    @Column(name = "ST_ITEM_PREFIX")
    private String stItemPrefix;
    
    /**
     * 内嵌页面地址
     */
    @Column(name = "ST_INCLUDE_PATH")
    private String stIncludePath;
    
    /**
     * 事项类型
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
    /**
     * 打印类型
     */
    @Column(name = "NM_PRINT_TYPE")
    private BigDecimal nmPrintType;
    
    /**
     * 是否需要受理编码
     */
    @Column(name = "NM_NEED_BUSINESS_CODE")
    private BigDecimal nmNeedBusinessCode;
    
    /**
     * 是否需要预约
     */
    @Column(name = "NM_NEED_RESERVATION")
    private BigDecimal nmNeedReservation;
    
    /**
     * 第三方预约地址
     */
    @Column(name = "ST_RESERVATION_URL")
    private String stReservationUrl;
    
    /**
     * 展示顺序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 网站展示顺序
     */
    @Column(name = "NM_NET_ORDER")
    private BigDecimal nmNetOrder;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 是否收费
     */
    @Column(name = "NM_COST")
    private BigDecimal nmCost;
    
    /**
     * 部门完整的名字
     */
    @Column(name = "ST_GROUP_COMPLETE_NAME")
    private String stGroupCompleteName;
    
    /**
     * 凭证出处名称
     */
    @Column(name = "ST_CREDENTAIALS_OUTS")
    private String stCredentaialsOuts;
    
    /**
     * 补正工作日
     */
    @Column(name = "NM_CORRECTION_DATE")
    private BigDecimal nmCorrectionDate;
    
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
     * 是否在PAD上显示
     */
    @Column(name = "NM_DISPLAY")
    private BigDecimal nmDisplay;
    
    /**
     * 部门ID
     */
    @Column(name = "NM_ORGAN_NODE_ID")
    private BigDecimal nmOrganNodeId;
    
    /**
     * 部门NAME
     */
    @Column(name = "NM_ORGAN_NODE_NAME")
    private String nmOrganNodeName;
    
    /**
     * 事项来源
     */
    @Column(name = "NM_ITEM_SOURCE")
    private BigDecimal nmItemSource;
    
    /**
     * 事项类别:便民服务,行政审批
     */
    @Column(name = "ST_ITEM_TYPE")
    private String stItemType;
    
    /**
     * 与审改事项关联
     */
    @Column(name = "ST_LINK_ID")
    private String stLinkId;
    
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
     * 生成统一审批编码时的前缀
     */
    public String getStItemPrefix() {
        return this.stItemPrefix;
    }
    
    /**
     * 生成统一审批编码时的前缀
     */
    public String stItemPrefix2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemPrefix);
    }

    /**
     * 生成统一审批编码时的前缀
     */
    public void setStItemPrefix(String stItemPrefix) {
        stItemPrefix = StringUtil.substringBySize(stItemPrefix, 10, "GB18030");
        this.stItemPrefix = stItemPrefix;
    }
    
	/**
     * 内嵌页面地址
     */
    public String getStIncludePath() {
        return this.stIncludePath;
    }
    
    /**
     * 内嵌页面地址
     */
    public String stIncludePath2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIncludePath);
    }

    /**
     * 内嵌页面地址
     */
    public void setStIncludePath(String stIncludePath) {
        stIncludePath = StringUtil.substringBySize(stIncludePath, 100, "GB18030");
        this.stIncludePath = stIncludePath;
    }

	/**
     * 事项类型
     */
    public BigDecimal getNmType() {
        return this.nmType;
    }
    
    /**
     * 事项类型
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
     * 事项类型
     */
    public void setNmType(BigDecimal nmType) {
        this.nmType = nmType;
    }

	/**
     * 打印类型
     */
    public BigDecimal getNmPrintType() {
        return this.nmPrintType;
    }
    
    /**
     * 打印类型
     */
    public String nmPrintType2Html(int precision) {
        if (this.nmPrintType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmPrintType);
        }
    }

    /**
     * 打印类型
     */
    public void setNmPrintType(BigDecimal nmPrintType) {
        this.nmPrintType = nmPrintType;
    }

	/**
     * 是否需要受理编码
     */
    public BigDecimal getNmNeedBusinessCode() {
        return this.nmNeedBusinessCode;
    }
    
    /**
     * 是否需要受理编码
     */
    public String nmNeedBusinessCode2Html(int precision) {
        if (this.nmNeedBusinessCode == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNeedBusinessCode);
        }
    }

    /**
     * 是否需要受理编码
     */
    public void setNmNeedBusinessCode(BigDecimal nmNeedBusinessCode) {
        this.nmNeedBusinessCode = nmNeedBusinessCode;
    }

	/**
     * 是否需要预约
     */
    public BigDecimal getNmNeedReservation() {
        return this.nmNeedReservation;
    }
    
    /**
     * 是否需要预约
     */
    public String nmNeedReservation2Html(int precision) {
        if (this.nmNeedReservation == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNeedReservation);
        }
    }

    /**
     * 是否需要预约
     */
    public void setNmNeedReservation(BigDecimal nmNeedReservation) {
        this.nmNeedReservation = nmNeedReservation;
    }
    
	/**
     * 第三方预约地址
     */
    public String getStReservationUrl() {
        return this.stReservationUrl;
    }
    
    /**
     * 第三方预约地址
     */
    public String stReservationUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReservationUrl);
    }

    /**
     * 第三方预约地址
     */
    public void setStReservationUrl(String stReservationUrl) {
        stReservationUrl = StringUtil.substringBySize(stReservationUrl, 100, "GB18030");
        this.stReservationUrl = stReservationUrl;
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
     * 网站展示顺序
     */
    public BigDecimal getNmNetOrder() {
        return this.nmNetOrder;
    }
    
    /**
     * 网站展示顺序
     */
    public String nmNetOrder2Html(int precision) {
        if (this.nmNetOrder == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNetOrder);
        }
    }

    /**
     * 网站展示顺序
     */
    public void setNmNetOrder(BigDecimal nmNetOrder) {
        this.nmNetOrder = nmNetOrder;
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
     * 部门完整的名字
     */
    public String getStGroupCompleteName() {
        return this.stGroupCompleteName;
    }
    
    /**
     * 部门完整的名字
     */
    public String stGroupCompleteName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stGroupCompleteName);
    }

    /**
     * 部门完整的名字
     */
    public void setStGroupCompleteName(String stGroupCompleteName) {
        stGroupCompleteName = StringUtil.substringBySize(stGroupCompleteName, 100, "GB18030");
        this.stGroupCompleteName = stGroupCompleteName;
    }
    
	/**
     * 凭证出处名称
     */
    public String getStCredentaialsOuts() {
        return this.stCredentaialsOuts;
    }
    
    /**
     * 凭证出处名称
     */
    public String stCredentaialsOuts2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCredentaialsOuts);
    }

    /**
     * 凭证出处名称
     */
    public void setStCredentaialsOuts(String stCredentaialsOuts) {
        stCredentaialsOuts = StringUtil.substringBySize(stCredentaialsOuts, 100, "GB18030");
        this.stCredentaialsOuts = stCredentaialsOuts;
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
     * 补正工作日
     */
    public BigDecimal getNmCorrectionDate() {
        return this.nmCorrectionDate;
    }
    
    /**
     * 补正工作日
     */
    public String nmCorrectionDate2Html(int precision) {
        if (this.nmCorrectionDate == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCorrectionDate);
        }
    }

    /**
     * 补正工作日
     */
    public void setNmCorrectionDate(BigDecimal nmCorrectionDate) {
        this.nmCorrectionDate = nmCorrectionDate;
    }

	/**
     * 是否在PAD上显示
     */
    public BigDecimal getNmDisplay() {
        return this.nmDisplay;
    }
    
    /**
     * 是否在PAD上显示
     */
    public String nmDisplay2Html(int precision) {
        if (this.nmDisplay == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDisplay);
        }
    }

    /**
     * 是否在PAD上显示
     */
    public void setNmDisplay(BigDecimal nmDisplay) {
        this.nmDisplay = nmDisplay;
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
     * 事项来源
     */
    public BigDecimal getNmItemSource() {
        return this.nmItemSource;
    }
    
    /**
     * 事项来源
     */
    public String nmItemSource2Html(int precision) {
        if (this.nmItemSource == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmItemSource);
        }
    }

    /**
     * 事项来源
     */
    public void setNmItemSource(BigDecimal nmItemSource) {
        this.nmItemSource = nmItemSource;
    }
    
    /**
     * 部门名称nmOrganNodeName
     */
    public String getNmOrganNodeName() {
        return this.nmOrganNodeName;
    }
    
    /**
     * 部门名称nmOrganNodeName
     */
    public String nmOrganNodeName2Html() {
        return StringHelper.replaceHTMLSymbol(this.nmOrganNodeName);
    }

    /**
     * 部门名称nmOrganNodeName
     */
    public void setNmOrganNodeName(String nmOrganNodeName) {
    	nmOrganNodeName = StringUtil.substringBySize(nmOrganNodeName, 50, "GB18030");
        this.nmOrganNodeName = nmOrganNodeName;
    }
    
    
	/**
     * 事项类别:便民服务,行政审批
     */
    public String getStItemType() {
        return this.stItemType;
    }
    
    /**
     * 事项类别:便民服务,行政审批
     */
    public String stItemType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemType);
    }

    /**
     * 事项类别:便民服务,行政审批
     */
    public void setStItemType(String stItemType) {
        this.stItemType = stItemType;
    }
    
	/**
     * 与审改事项关联
     */
    public String getStLinkId() {
        return this.stLinkId;
    }
    
    /**
     * 与审改事项关联
     */
    public String stLinkId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLinkId);
    }

    /**
     * 与审改事项关联
     */
    public void setStLinkId(String stLinkId) {
        this.stLinkId = stLinkId;
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
        this.stExt2 = stExt2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}