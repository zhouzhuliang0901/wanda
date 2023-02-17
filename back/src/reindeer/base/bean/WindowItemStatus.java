package reindeer.base.bean;
		
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.HashMap;

import javax.persistence.*;
import org.apache.commons.lang.builder.*;

import reindeer.base.utils.StringUtil;

import wfc.service.util.*;

/**
 * 事项情形表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_ITEM_STATUS")
public class WindowItemStatus extends HashMap<String, String> implements Serializable {
    
    /**
     * 事项情形表
     */
    public static final String WINDOW_ITEM_STATUS = "WINDOW_ITEM_STATUS";
    
    /**
     * 事项情形ID，主键UUID
     */
    public static final String ST_STATUS_ID = "ST_STATUS_ID";
    
    /**
     * 事项情形编码
     */
    public static final String ST_STATUS_NO = "ST_STATUS_NO";
    
    /**
     * 办理项（情形）
     */
    public static final String ST_STATUS_NAME = "ST_STATUS_NAME";
    
    /**
     * 事项信息编号
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 子事项信息编号
     */
    public static final String ST_ITEM_SON_ID = "ST_ITEM_SON_ID";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_NO = "ST_ITEM_NO";
    
    /**
     * 部门ID，CS_ORGAN_NODE主键UUID
     */
    public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";
    
    /**
     * 事项类型
     */
    public static final String NM_TYPE = "NM_TYPE";
    
    /**
     * 综合窗口办理类型
     */
    public static final String NM_WINDOW_APPLY_TYPE = "NM_WINDOW_APPLY_TYPE";
    
    /**
     * 是否实施告知承诺
     */
    public static final String NM_NOTIFY_PROMISE = "NM_NOTIFY_PROMISE";
    
    /**
     * 审查类型
     */
    public static final String NM_EXAMINE_TYPE = "NM_EXAMINE_TYPE";
    
    /**
     * 是否当场办结
     */
    public static final String NM_ON_THE_SPOT = "NM_ON_THE_SPOT";
    
    /**
     * 是否提供提前服务
     */
    public static final String NM_ADVANCE_SERVE = "NM_ADVANCE_SERVE";
    
    /**
     * 至少跑几次
     */
    public static final String NM_LEAST_TIME = "NM_LEAST_TIME";
    
    /**
     * 收费标准
     */
    public static final String ST_CHARGE_STANDARD = "ST_CHARGE_STANDARD";
    
    /**
     * 法定时限
     */
    public static final String ST_LEGAL_LIMIT = "ST_LEGAL_LIMIT";
    
    /**
     * 承诺时限
     */
    public static final String ST_PROMISE_LIMIT = "ST_PROMISE_LIMIT";
    
    /**
     * 是否可在自助服务区网上申报
     */
    public static final String NM_ONLINE_DECLARE = "NM_ONLINE_DECLARE";
    
    /**
     * 网上申报网址
     */
    public static final String ST_URL = "ST_URL";
    
    /**
     * 是否专网
     */
    public static final String NM_PRIVATE_NETWORK = "NM_PRIVATE_NETWORK";
    
    /**
     * 该办理项在综窗办理（受理）前是否必须需要实施部门预审意见
     */
    public static final String NM_PRETRIAL = "NM_PRETRIAL";
    
    /**
     * 设定依据
     */
    public static final String ST_PRESET_BASIS = "ST_PRESET_BASIS";
    
    /**
     * 办理条件
     */
    public static final String ST_APPLY_TERMS = "ST_APPLY_TERMS";
    
    /**
     * 审查依据
     */
    public static final String ST_EXAMINE_BASIS = "ST_EXAMINE_BASIS";
    
    /**
     * 常见问题解答
     */
    public static final String ST_FAQ = "ST_FAQ";
    
    /**
     * 备注
     */
    public static final String ST_REMARKS = "ST_REMARKS";
    
    /**
     * 展示顺序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    public WindowItemStatus() {
    }
    
    /**
     * 事项情形ID，主键UUID
     */
    @Id
    @Column(name = "ST_STATUS_ID")
    private String stStatusId;
    
    /**
     * 事项情形编码
     */
    @Id
    @Column(name = "ST_STATUS_NO")
    private String stStatusNo;
    
    /**
     * 办理项（情形）
     */
    @Column(name = "ST_STATUS_NAME")
    private String stStatusName;
    
    /**
     * 事项信息编号
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 子事项信息编号
     */
    @Column(name = "ST_ITEM_SON_ID")
    private String stItemSonId;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 部门ID，CS_ORGAN_NODE主键UUID
     */
    @Column(name = "NM_ORGAN_NODE_ID")
    private BigDecimal nmOrganNodeId;
    
    /**
     * 事项类型
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
    /**
     * 综合窗口办理类型
     */
    @Column(name = "NM_WINDOW_APPLY_TYPE")
    private BigDecimal nmWindowApplyType;
    
    /**
     * 是否实施告知承诺
     */
    @Column(name = "NM_NOTIFY_PROMISE")
    private BigDecimal nmNotifyPromise;
    
    /**
     * 审查类型
     */
    @Column(name = "NM_EXAMINE_TYPE")
    private BigDecimal nmExamineType;
    
    /**
     * 是否当场办结
     */
    @Column(name = "NM_ON_THE_SPOT")
    private BigDecimal nmOnTheSpot;
    
    /**
     * 是否提供提前服务
     */
    @Column(name = "NM_ADVANCE_SERVE")
    private BigDecimal nmAdvanceServe;
    
    /**
     * 至少跑几次
     */
    @Column(name = "NM_LEAST_TIME")
    private BigDecimal nmLeastTime;
    
    /**
     * 收费标准
     */
    @Column(name = "ST_CHARGE_STANDARD")
    private String stChargeStandard;
    
    /**
     * 法定时限
     */
    @Column(name = "ST_LEGAL_LIMIT")
    private String stLegalLimit;
    
    /**
     * 承诺时限
     */
    @Column(name = "ST_PROMISE_LIMIT")
    private String stPromiseLimit;
    
    /**
     * 是否可在自助服务区网上申报
     */
    @Column(name = "NM_ONLINE_DECLARE")
    private BigDecimal nmOnlineDeclare;
    
    /**
     * 网上申报网址
     */
    @Column(name = "ST_URL")
    private String stUrl;
    
    /**
     * 是否专网
     */
    @Column(name = "NM_PRIVATE_NETWORK")
    private BigDecimal nmPrivateNetwork;
    
    /**
     * 该办理项在综窗办理（受理）前是否必须需要实施部门预审意见
     */
    @Column(name = "NM_PRETRIAL")
    private BigDecimal nmPretrial;
    
    /**
     * 设定依据
     */
    @Column(name = "ST_PRESET_BASIS")
    private String stPresetBasis;
    
    /**
     * 办理条件
     */
    @Column(name = "ST_APPLY_TERMS")
    private String stApplyTerms;
    
    /**
     * 审查依据
     */
    @Column(name = "ST_EXAMINE_BASIS")
    private String stExamineBasis;
    
    /**
     * 常见问题解答
     */
    @Column(name = "ST_FAQ")
    private String stFaq;
    
    /**
     * 备注
     */
    @Column(name = "ST_REMARKS")
    private String stRemarks;
    
    /**
     * 展示顺序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
	/**
     * 事项情形ID，主键UUID
     */
    public String getStStatusId() {
        return this.stStatusId;
    }
    
    /**
     * 事项情形ID，主键UUID
     */
    public String stStatusId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatusId);
    }

    /**
     * 事项情形ID，主键UUID
     */
    public void setStStatusId(String stStatusId) {
        stStatusId = StringUtil.substringBySize(stStatusId, 100, "GB18030");
        this.stStatusId = stStatusId;
    }
    
    /**
     * 事项情形编码
     */
    public String getStStatusNo() {
        return this.stStatusNo;
    }
    
    /**
     * 事项情形编码
     */
    public String stStatusNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatusNo);
    }

    /**
     * 事项情形编码
     */
    public void setStStatusNo(String stStatusNo) {
        this.stStatusNo = stStatusNo;
    }
    
	/**
     * 办理项（情形）
     */
    public String getStStatusName() {
        return this.stStatusName;
    }
    
    /**
     * 办理项（情形）
     */
    public String stStatusName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatusName);
    }

    /**
     * 办理项（情形）
     */
    public void setStStatusName(String stStatusName) {
        stStatusName = StringUtil.substringBySize(stStatusName, 100, "GB18030");
        this.stStatusName = stStatusName;
    }
    
	/**
     * 事项信息编号
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 事项信息编号
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项信息编号
     */
    public void setStItemId(String stItemId) {
        stItemId = StringUtil.substringBySize(stItemId, 100, "GB18030");
        this.stItemId = stItemId;
    }
    
	/**
     * 子事项信息编号
     */
    public String getStItemSonId() {
        return this.stItemSonId;
    }
    
    /**
     * 子事项信息编号
     */
    public String stItemSonId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemSonId);
    }

    /**
     * 子事项信息编号
     */
    public void setStItemSonId(String stItemSonId) {
        stItemSonId = StringUtil.substringBySize(stItemSonId, 100, "GB18030");
        this.stItemSonId = stItemSonId;
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
     * 部门ID，CS_ORGAN_NODE主键UUID
     */
    public BigDecimal getNmOrganNodeId() {
        return this.nmOrganNodeId;
    }
    
    /**
     * 部门ID，CS_ORGAN_NODE主键UUID
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
     * 部门ID，CS_ORGAN_NODE主键UUID
     */
    public void setNmOrganNodeId(BigDecimal nmOrganNodeId) {
        this.nmOrganNodeId = nmOrganNodeId;
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
     * 综合窗口办理类型
     */
    public BigDecimal getNmWindowApplyType() {
        return this.nmWindowApplyType;
    }
    
    /**
     * 综合窗口办理类型
     */
    public String nmWindowApplyType2Html(int precision) {
        if (this.nmWindowApplyType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmWindowApplyType);
        }
    }

    /**
     * 综合窗口办理类型
     */
    public void setNmWindowApplyType(BigDecimal nmWindowApplyType) {
        this.nmWindowApplyType = nmWindowApplyType;
    }

	/**
     * 是否实施告知承诺
     */
    public BigDecimal getNmNotifyPromise() {
        return this.nmNotifyPromise;
    }
    
    /**
     * 是否实施告知承诺
     */
    public String nmNotifyPromise2Html(int precision) {
        if (this.nmNotifyPromise == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNotifyPromise);
        }
    }

    /**
     * 是否实施告知承诺
     */
    public void setNmNotifyPromise(BigDecimal nmNotifyPromise) {
        this.nmNotifyPromise = nmNotifyPromise;
    }

	/**
     * 审查类型
     */
    public BigDecimal getNmExamineType() {
        return this.nmExamineType;
    }
    
    /**
     * 审查类型
     */
    public String nmExamineType2Html(int precision) {
        if (this.nmExamineType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmExamineType);
        }
    }

    /**
     * 审查类型
     */
    public void setNmExamineType(BigDecimal nmExamineType) {
        this.nmExamineType = nmExamineType;
    }

	/**
     * 是否当场办结
     */
    public BigDecimal getNmOnTheSpot() {
        return this.nmOnTheSpot;
    }
    
    /**
     * 是否当场办结
     */
    public String nmOnTheSpot2Html(int precision) {
        if (this.nmOnTheSpot == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOnTheSpot);
        }
    }

    /**
     * 是否当场办结
     */
    public void setNmOnTheSpot(BigDecimal nmOnTheSpot) {
        this.nmOnTheSpot = nmOnTheSpot;
    }

	/**
     * 是否提供提前服务
     */
    public BigDecimal getNmAdvanceServe() {
        return this.nmAdvanceServe;
    }
    
    /**
     * 是否提供提前服务
     */
    public String nmAdvanceServe2Html(int precision) {
        if (this.nmAdvanceServe == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmAdvanceServe);
        }
    }

    /**
     * 是否提供提前服务
     */
    public void setNmAdvanceServe(BigDecimal nmAdvanceServe) {
        this.nmAdvanceServe = nmAdvanceServe;
    }

	/**
     * 至少跑几次
     */
    public BigDecimal getNmLeastTime() {
        return this.nmLeastTime;
    }
    
    /**
     * 至少跑几次
     */
    public String nmLeastTime2Html(int precision) {
        if (this.nmLeastTime == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLeastTime);
        }
    }

    /**
     * 至少跑几次
     */
    public void setNmLeastTime(BigDecimal nmLeastTime) {
        this.nmLeastTime = nmLeastTime;
    }
    
	/**
     * 收费标准
     */
    public String getStChargeStandard() {
        return this.stChargeStandard;
    }
    
    /**
     * 收费标准
     */
    public String stChargeStandard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stChargeStandard);
    }

    /**
     * 收费标准
     */
    public void setStChargeStandard(String stChargeStandard) {
        stChargeStandard = StringUtil.substringBySize(stChargeStandard, 200, "GB18030");
        this.stChargeStandard = stChargeStandard;
    }
    
	/**
     * 法定时限
     */
    public String getStLegalLimit() {
        return this.stLegalLimit;
    }
    
    /**
     * 法定时限
     */
    public String stLegalLimit2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalLimit);
    }

    /**
     * 法定时限
     */
    public void setStLegalLimit(String stLegalLimit) {
        stLegalLimit = StringUtil.substringBySize(stLegalLimit, 500, "GB18030");
        this.stLegalLimit = stLegalLimit;
    }
    
	/**
     * 承诺时限
     */
    public String getStPromiseLimit() {
        return this.stPromiseLimit;
    }
    
    /**
     * 承诺时限
     */
    public String stPromiseLimit2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPromiseLimit);
    }

    /**
     * 承诺时限
     */
    public void setStPromiseLimit(String stPromiseLimit) {
        stPromiseLimit = StringUtil.substringBySize(stPromiseLimit, 500, "GB18030");
        this.stPromiseLimit = stPromiseLimit;
    }

	/**
     * 是否可在自助服务区网上申报
     */
    public BigDecimal getNmOnlineDeclare() {
        return this.nmOnlineDeclare;
    }
    
    /**
     * 是否可在自助服务区网上申报
     */
    public String nmOnlineDeclare2Html(int precision) {
        if (this.nmOnlineDeclare == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOnlineDeclare);
        }
    }

    /**
     * 是否可在自助服务区网上申报
     */
    public void setNmOnlineDeclare(BigDecimal nmOnlineDeclare) {
        this.nmOnlineDeclare = nmOnlineDeclare;
    }
    
	/**
     * 网上申报网址
     */
    public String getStUrl() {
        return this.stUrl;
    }
    
    /**
     * 网上申报网址
     */
    public String stUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUrl);
    }

    /**
     * 网上申报网址
     */
    public void setStUrl(String stUrl) {
        stUrl = StringUtil.substringBySize(stUrl, 200, "GB18030");
        this.stUrl = stUrl;
    }

	/**
     * 是否专网
     */
    public BigDecimal getNmPrivateNetwork() {
        return this.nmPrivateNetwork;
    }
    
    /**
     * 是否专网
     */
    public String nmPrivateNetwork2Html(int precision) {
        if (this.nmPrivateNetwork == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmPrivateNetwork);
        }
    }

    /**
     * 是否专网
     */
    public void setNmPrivateNetwork(BigDecimal nmPrivateNetwork) {
        this.nmPrivateNetwork = nmPrivateNetwork;
    }

	/**
     * 该办理项在综窗办理（受理）前是否必须需要实施部门预审意见
     */
    public BigDecimal getNmPretrial() {
        return this.nmPretrial;
    }
    
    /**
     * 该办理项在综窗办理（受理）前是否必须需要实施部门预审意见
     */
    public String nmPretrial2Html(int precision) {
        if (this.nmPretrial == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmPretrial);
        }
    }

    /**
     * 该办理项在综窗办理（受理）前是否必须需要实施部门预审意见
     */
    public void setNmPretrial(BigDecimal nmPretrial) {
        this.nmPretrial = nmPretrial;
    }
    
	/**
     * 设定依据
     */
    public String getStPresetBasis() {
        return this.stPresetBasis;
    }
    
    /**
     * 设定依据
     */
    public String stPresetBasis2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPresetBasis);
    }

    /**
     * 设定依据
     */
    public void setStPresetBasis(String stPresetBasis) {
        stPresetBasis = StringUtil.substringBySize(stPresetBasis, 1000000, "GB18030");
        this.stPresetBasis = stPresetBasis;
    }
    
	/**
     * 办理条件
     */
    public String getStApplyTerms() {
        return this.stApplyTerms;
    }
    
    /**
     * 办理条件
     */
    public String stApplyTerms2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyTerms);
    }

    /**
     * 办理条件
     */
    public void setStApplyTerms(String stApplyTerms) {
        stApplyTerms = StringUtil.substringBySize(stApplyTerms, 1000000, "GB18030");
        this.stApplyTerms = stApplyTerms;
    }
    
	/**
     * 审查依据
     */
    public String getStExamineBasis() {
        return this.stExamineBasis;
    }
    
    /**
     * 审查依据
     */
    public String stExamineBasis2Html() {
        return StringHelper.replaceHTMLSymbol(this.stExamineBasis);
    }

    /**
     * 审查依据
     */
    public void setStExamineBasis(String stExamineBasis) {
        stExamineBasis = StringUtil.substringBySize(stExamineBasis, 1000000, "GB18030");
        this.stExamineBasis = stExamineBasis;
    }
    
	/**
     * 常见问题解答
     */
    public String getStFaq() {
        return this.stFaq;
    }
    
    /**
     * 常见问题解答
     */
    public String stFaq2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFaq);
    }

    /**
     * 常见问题解答
     */
    public void setStFaq(String stFaq) {
        stFaq = StringUtil.substringBySize(stFaq, 1000000, "GB18030");
        this.stFaq = stFaq;
    }
    
	/**
     * 备注
     */
    public String getStRemarks() {
        return this.stRemarks;
    }
    
    /**
     * 备注
     */
    public String stRemarks2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRemarks);
    }

    /**
     * 备注
     */
    public void setStRemarks(String stRemarks) {
        stRemarks = StringUtil.substringBySize(stRemarks, 1000000, "GB18030");
        this.stRemarks = stRemarks;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}