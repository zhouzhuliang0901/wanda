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
 * 综合办件反馈
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_FEEDBACK")
public class UacUapplyFeedback implements Serializable {
    
    /**
     * 综合办件反馈
     */
    public static final String UAC_UAPPLY_FEEDBACK = "UAC_UAPPLY_FEEDBACK";
    
    /**
     * 办件反馈ID
     */
    public static final String ST_FEEDBACK_ID = "ST_FEEDBACK_ID";
    
    /**
     * 办件流水号
     */
    public static final String ST_SUB_SNO = "ST_SUB_SNO";
    
    /**
     * 事项ID
     */
    public static final String ST_CITEM_ID = "ST_CITEM_ID";

    /**
     * 事项名称
     */
    public static final String ST_CITEM_NAME = "ST_CITEM_NAME";

    /**
     * 事项编码
     */
    public static final String ST_CITEM_CODE = "ST_CITEM_CODE";
    
    /**
     * 办件信息ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 办件反馈状态
     */
    public static final String ST_BACK_STATE = "ST_BACK_STATE";
    
    /**
     * 是否已终止或完成
     */
    public static final String NM_IS_OVER = "NM_IS_OVER";
    
    /**
     * 统一审批编码
     */
    public static final String ST_WF_ID = "ST_WF_ID";
    
    /**
     * 综合办件ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";
    
    /**
     * 是否需要补正
     */
    public static final String NM_CORRECT = "NM_CORRECT";
    
    /**
     * 预审状态
     */
    public static final String NM_PRE_STATUS = "NM_PRE_STATUS";
    
    /**
     * 提前服务状态
     */
    public static final String NM_ADS_STATUS = "NM_ADS_STATUS";
    
    /**
     * 子状态
     */
    public static final String ST_SUB_STATUS = "ST_SUB_STATUS";
    
    /**
     * 子状态附加信息
     */
    public static final String ST_SUB_CONTENT_ID = "ST_SUB_CONTENT_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public UacUapplyFeedback() {
    }
    
    /**
     * 办件反馈ID
     */
    @Id
    @Column(name = "ST_FEEDBACK_ID")
    private String stFeedbackId;
    
    /**
     * 办件流水号
     */
    @Column(name = "ST_SUB_SNO")
    private String stSubSno;
    
    /**
     * 事项ID
     */
    @Column(name = "ST_CITEM_ID")
    private String stCitemId;

    /**
     * 事项ID
     */
    @Column(name = "ST_CITEM_NAME")
    private String stCitemName;

    /**
     * 事项编码
     */
    @Column(name = "ST_CITEM_CODE")
    private String stCitemCode;
    
    /**
     * 办件信息ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 办件反馈状态
     */
    @Column(name = "ST_BACK_STATE")
    private String stBackState;
    
    /**
     * 是否已终止或完成
     */
    @Column(name = "NM_IS_OVER")
    private BigDecimal nmIsOver;
    
    /**
     * 统一审批编码
     */
    @Column(name = "ST_WF_ID")
    private String stWfId;
    
    /**
     * 综合办件ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;
    
    /**
     * 是否需要补正
     */
    @Column(name = "NM_CORRECT")
    private BigDecimal nmCorrect;
    
    /**
     * 预审状态
     */
    @Column(name = "NM_PRE_STATUS")
    private BigDecimal nmPreStatus;
    
    /**
     * 提前服务状态
     */
    @Column(name = "NM_ADS_STATUS")
    private BigDecimal nmAdsStatus;
    
    /**
     * 子状态
     */
    @Column(name = "ST_SUB_STATUS")
    private String stSubStatus;
    
    /**
     * 子状态附加信息
     */
    @Column(name = "ST_SUB_CONTENT_ID")
    private String stSubContentId;
    
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
     * 办件反馈ID
     */
    public String getStFeedbackId() {
        return this.stFeedbackId;
    }
    
    /**
     * 办件反馈ID
     */
    public String stFeedbackId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFeedbackId);
    }

    /**
     * 办件反馈ID
     */
    public void setStFeedbackId(String stFeedbackId) {
        stFeedbackId = StringUtil.substringBySize(stFeedbackId, 50, "GB18030");
        this.stFeedbackId = stFeedbackId;
    }
    
	/**
     * 办件流水号
     */
    public String getStSubSno() {
        return this.stSubSno;
    }
    
    /**
     * 办件流水号
     */
    public String stSubSno2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSubSno);
    }

    /**
     * 办件流水号
     */
    public void setStSubSno(String stSubSno) {
        stSubSno = StringUtil.substringBySize(stSubSno, 50, "GB18030");
        this.stSubSno = stSubSno;
    }
    
	/**
     * 事项ID
     */
    public String getStCitemId() {
        return this.stCitemId;
    }
    
    /**
     * 事项ID
     */
    public String stCitemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemId);
    }

    /**
     * 事项ID
     */
    public void setStCitemId(String stCitemId) {
        stCitemId = StringUtil.substringBySize(stCitemId, 50, "GB18030");
        this.stCitemId = stCitemId;
    }

	/**
     * 事项ID
     */
    public String getStCitemName() {
        return this.stCitemName;
    }

    /**
     * 事项ID
     */
    public String stCitemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemName);
    }

    /**
     * 事项ID
     */
    public void setStCitemName(String stCitemName) {
        stCitemName = StringUtil.substringBySize(stCitemName, 50, "GB18030");
        this.stCitemName = stCitemName;
    }

	/**
     * 事项编码
     */
    public String getStCitemCode() {
        return this.stCitemCode;
    }
    
    /**
     * 事项编码
     */
    public String stCitemCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemCode);
    }

    /**
     * 事项编码
     */
    public void setStCitemCode(String stCitemCode) {
        stCitemCode = StringUtil.substringBySize(stCitemCode, 50, "GB18030");
        this.stCitemCode = stCitemCode;
    }
    
	/**
     * 办件信息ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 办件信息ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 办件信息ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }
    
	/**
     * 办件反馈状态
     */
    public String getStBackState() {
        return this.stBackState;
    }
    
    /**
     * 办件反馈状态
     */
    public String stBackState2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBackState);
    }

    /**
     * 办件反馈状态
     */
    public void setStBackState(String stBackState) {
        stBackState = StringUtil.substringBySize(stBackState, 50, "GB18030");
        this.stBackState = stBackState;
    }

	/**
     * 是否已终止或完成
     */
    public BigDecimal getNmIsOver() {
        return this.nmIsOver;
    }
    
    /**
     * 是否已终止或完成
     */
    public String nmIsOver2Html(int precision) {
        if (this.nmIsOver == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIsOver);
        }
    }

    /**
     * 是否已终止或完成
     */
    public void setNmIsOver(BigDecimal nmIsOver) {
        this.nmIsOver = nmIsOver;
    }
    
	/**
     * 统一审批编码
     */
    public String getStWfId() {
        return this.stWfId;
    }
    
    /**
     * 统一审批编码
     */
    public String stWfId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWfId);
    }

    /**
     * 统一审批编码
     */
    public void setStWfId(String stWfId) {
        stWfId = StringUtil.substringBySize(stWfId, 50, "GB18030");
        this.stWfId = stWfId;
    }
    
	/**
     * 综合办件ID
     */
    public String getStParentId() {
        return this.stParentId;
    }
    
    /**
     * 综合办件ID
     */
    public String stParentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentId);
    }

    /**
     * 综合办件ID
     */
    public void setStParentId(String stParentId) {
        stParentId = StringUtil.substringBySize(stParentId, 50, "GB18030");
        this.stParentId = stParentId;
    }

	/**
     * 是否需要补正
     */
    public BigDecimal getNmCorrect() {
        return this.nmCorrect;
    }
    
    /**
     * 是否需要补正
     */
    public String nmCorrect2Html(int precision) {
        if (this.nmCorrect == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCorrect);
        }
    }

    /**
     * 是否需要补正
     */
    public void setNmCorrect(BigDecimal nmCorrect) {
        this.nmCorrect = nmCorrect;
    }

	/**
     * 预审状态
     */
    public BigDecimal getNmPreStatus() {
        return this.nmPreStatus;
    }
    
    /**
     * 预审状态
     */
    public String nmPreStatus2Html(int precision) {
        if (this.nmPreStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmPreStatus);
        }
    }

    /**
     * 预审状态
     */
    public void setNmPreStatus(BigDecimal nmPreStatus) {
        this.nmPreStatus = nmPreStatus;
    }

	/**
     * 提前服务状态
     */
    public BigDecimal getNmAdsStatus() {
        return this.nmAdsStatus;
    }
    
    /**
     * 提前服务状态
     */
    public String nmAdsStatus2Html(int precision) {
        if (this.nmAdsStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmAdsStatus);
        }
    }

    /**
     * 提前服务状态
     */
    public void setNmAdsStatus(BigDecimal nmAdsStatus) {
        this.nmAdsStatus = nmAdsStatus;
    }
    
	/**
     * 子状态
     */
    public String getStSubStatus() {
        return this.stSubStatus;
    }
    
    /**
     * 子状态
     */
    public String stSubStatus2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSubStatus);
    }

    /**
     * 子状态
     */
    public void setStSubStatus(String stSubStatus) {
        stSubStatus = StringUtil.substringBySize(stSubStatus, 50, "GB18030");
        this.stSubStatus = stSubStatus;
    }
    
	/**
     * 子状态附加信息
     */
    public String getStSubContentId() {
        return this.stSubContentId;
    }
    
    /**
     * 子状态附加信息
     */
    public String stSubContentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSubContentId);
    }

    /**
     * 子状态附加信息
     */
    public void setStSubContentId(String stSubContentId) {
        stSubContentId = StringUtil.substringBySize(stSubContentId, 50, "GB18030");
        this.stSubContentId = stSubContentId;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}