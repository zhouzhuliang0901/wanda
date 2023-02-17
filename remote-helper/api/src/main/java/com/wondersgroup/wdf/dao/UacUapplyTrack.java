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
 * 综合办件跟踪信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_TRACK")
public class UacUapplyTrack implements Serializable {
    
    /**
     * 综合办件跟踪信息
     */
    public static final String UAC_UAPPLY_TRACK = "UAC_UAPPLY_TRACK";
    
    /**
     * 跟踪信息ID
     */
    public static final String ST_TRACK_ID = "ST_TRACK_ID";
    
    /**
     * 办件反馈ID
     */
    public static final String ST_FEEDBACK_ID = "ST_FEEDBACK_ID";
    
    /**
     * 所属办件ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 操作名
     */
    public static final String ST_OP = "ST_OP";
    
    /**
     * 操作结果
     */
    public static final String ST_OP_RESULT = "ST_OP_RESULT";
    
    /**
     * 操作时间
     */
    public static final String DT_OP_TIME = "DT_OP_TIME";
    
    /**
     * 业务类型
     */
    public static final String ST_BTYPE = "ST_BTYPE";
    
    /**
     * 跟踪信息来源类型
     */
    public static final String NM_FROM_TYPE = "NM_FROM_TYPE";
    
    /**
     * 办件物流ID
     */
    public static final String ST_UNION_LOGISTICS_ID = "ST_UNION_LOGISTICS_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public UacUapplyTrack() {
    }
    
    /**
     * 跟踪信息ID
     */
    @Id
    @Column(name = "ST_TRACK_ID")
    private String stTrackId;
    
    /**
     * 办件反馈ID
     */
    @Column(name = "ST_FEEDBACK_ID")
    private String stFeedbackId;
    
    /**
     * 所属办件ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 操作名
     */
    @Column(name = "ST_OP")
    private String stOp;
    
    /**
     * 操作结果
     */
    @Column(name = "ST_OP_RESULT")
    private String stOpResult;
    
    /**
     * 操作时间
     */
    @Column(name = "DT_OP_TIME")
    private Timestamp dtOpTime;
    
    /**
     * 业务类型
     */
    @Column(name = "ST_BTYPE")
    private String stBtype;
    
    /**
     * 跟踪信息来源类型
     */
    @Column(name = "NM_FROM_TYPE")
    private BigDecimal nmFromType;
    
    /**
     * 办件物流ID
     */
    @Column(name = "ST_UNION_LOGISTICS_ID")
    private String stUnionLogisticsId;
    
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
     * 跟踪信息ID
     */
    public String getStTrackId() {
        return this.stTrackId;
    }
    
    /**
     * 跟踪信息ID
     */
    public String stTrackId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTrackId);
    }

    /**
     * 跟踪信息ID
     */
    public void setStTrackId(String stTrackId) {
        stTrackId = StringUtil.substringBySize(stTrackId, 50, "GB18030");
        this.stTrackId = stTrackId;
    }
    
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
     * 所属办件ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 所属办件ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 所属办件ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }
    
	/**
     * 操作名
     */
    public String getStOp() {
        return this.stOp;
    }
    
    /**
     * 操作名
     */
    public String stOp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOp);
    }

    /**
     * 操作名
     */
    public void setStOp(String stOp) {
        stOp = StringUtil.substringBySize(stOp, 50, "GB18030");
        this.stOp = stOp;
    }
    
	/**
     * 操作结果
     */
    public String getStOpResult() {
        return this.stOpResult;
    }
    
    /**
     * 操作结果
     */
    public String stOpResult2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpResult);
    }

    /**
     * 操作结果
     */
    public void setStOpResult(String stOpResult) {
        stOpResult = StringUtil.substringBySize(stOpResult, 50, "GB18030");
        this.stOpResult = stOpResult;
    }

	/**
     * 操作时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtOpTime() {
        return this.dtOpTime;
    }
    
    /**
     * 操作时间
     */
    public String dtOpTime2Html(String pattern) {
        if (this.dtOpTime == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtOpTime);
        }
    }

    /**
     * 操作时间
     */
    public void setDtOpTime(Timestamp dtOpTime) {
        this.dtOpTime = dtOpTime;
    }
    
	/**
     * 业务类型
     */
    public String getStBtype() {
        return this.stBtype;
    }
    
    /**
     * 业务类型
     */
    public String stBtype2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBtype);
    }

    /**
     * 业务类型
     */
    public void setStBtype(String stBtype) {
        stBtype = StringUtil.substringBySize(stBtype, 50, "GB18030");
        this.stBtype = stBtype;
    }

	/**
     * 跟踪信息来源类型
     */
    public BigDecimal getNmFromType() {
        return this.nmFromType;
    }
    
    /**
     * 跟踪信息来源类型
     */
    public String nmFromType2Html(int precision) {
        if (this.nmFromType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmFromType);
        }
    }

    /**
     * 跟踪信息来源类型
     */
    public void setNmFromType(BigDecimal nmFromType) {
        this.nmFromType = nmFromType;
    }
    
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