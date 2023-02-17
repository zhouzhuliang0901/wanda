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
 * 综合办件环节跟踪信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_NODE_TRACK")
public class UacUapplyNodeTrack implements Serializable {
    
    /**
     * 综合办件环节跟踪信息
     */
    public static final String UAC_UAPPLY_NODE_TRACK = "UAC_UAPPLY_NODE_TRACK";
    
    /**
     * 环节跟踪信息ID
     */
    public static final String ST_NODE_TRACK_ID = "ST_NODE_TRACK_ID";
    
    /**
     * 跟踪信息ID
     */
    public static final String ST_TRACK_ID = "ST_TRACK_ID";
    
    /**
     * 所属办件ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 操作部门
     */
    public static final String ST_OP_DEPART = "ST_OP_DEPART";
    
    /**
     * 操作环节
     */
    public static final String ST_OP_NODE = "ST_OP_NODE";
    
    /**
     * 操作人员
     */
    public static final String ST_OP_USER = "ST_OP_USER";
    
    /**
     * 操作类型
     */
    public static final String ST_OP_TYPE = "ST_OP_TYPE";
    
    /**
     * 操作备注
     */
    public static final String ST_OP_DESC = "ST_OP_DESC";
    
    /**
     * 操作结果
     */
    public static final String ST_OP_RESULT = "ST_OP_RESULT";
    
    /**
     * 操作时间
     */
    public static final String DT_OP_TIME = "DT_OP_TIME";
    
    /**
     * 环节开始时间
     */
    public static final String DT_START = "DT_START";
    
    /**
     * 环节结束时间
     */
    public static final String DT_END = "DT_END";
    
    /**
     * 结果证照编号
     */
    public static final String ST_CERT_NO = "ST_CERT_NO";
    
    /**
     * 办件结果描述
     */
    public static final String ST_RESULT_EXPLAIN = "ST_RESULT_EXPLAIN";
    
    /**
     * 是否需要快递
     */
    public static final String NM_DELIVERY = "NM_DELIVERY";
    
    /**
     * 满意度
     */
    public static final String ST_SATISFACTION = "ST_SATISFACTION";
    
    /**
     * 特别程序种类
     */
    public static final String ST_SPECIAL_TYPE = "ST_SPECIAL_TYPE";
    
    /**
     * 特别程序种类名称
     */
    public static final String ST_SPECIAL_NAME = "ST_SPECIAL_NAME";
    
    /**
     * 特别程序启动理由或依据
     */
    public static final String ST_SPECIAL_REASON = "ST_SPECIAL_REASON";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public UacUapplyNodeTrack() {
    }
    
    /**
     * 环节跟踪信息ID
     */
    @Id
    @Column(name = "ST_NODE_TRACK_ID")
    private String stNodeTrackId;
    
    /**
     * 跟踪信息ID
     */
    @Column(name = "ST_TRACK_ID")
    private String stTrackId;
    
    /**
     * 所属办件ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 操作部门
     */
    @Column(name = "ST_OP_DEPART")
    private String stOpDepart;
    
    /**
     * 操作环节
     */
    @Column(name = "ST_OP_NODE")
    private String stOpNode;
    
    /**
     * 操作人员
     */
    @Column(name = "ST_OP_USER")
    private String stOpUser;
    
    /**
     * 操作类型
     */
    @Column(name = "ST_OP_TYPE")
    private String stOpType;
    
    /**
     * 操作备注
     */
    @Column(name = "ST_OP_DESC")
    private String stOpDesc;
    
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
     * 环节开始时间
     */
    @Column(name = "DT_START")
    private Timestamp dtStart;
    
    /**
     * 环节结束时间
     */
    @Column(name = "DT_END")
    private Timestamp dtEnd;
    
    /**
     * 结果证照编号
     */
    @Column(name = "ST_CERT_NO")
    private String stCertNo;
    
    /**
     * 办件结果描述
     */
    @Column(name = "ST_RESULT_EXPLAIN")
    private String stResultExplain;
    
    /**
     * 是否需要快递
     */
    @Column(name = "NM_DELIVERY")
    private BigDecimal nmDelivery;
    
    /**
     * 满意度
     */
    @Column(name = "ST_SATISFACTION")
    private String stSatisfaction;
    
    /**
     * 特别程序种类
     */
    @Column(name = "ST_SPECIAL_TYPE")
    private String stSpecialType;
    
    /**
     * 特别程序种类名称
     */
    @Column(name = "ST_SPECIAL_NAME")
    private String stSpecialName;
    
    /**
     * 特别程序启动理由或依据
     */
    @Column(name = "ST_SPECIAL_REASON")
    private String stSpecialReason;
    
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
     * 环节跟踪信息ID
     */
    public String getStNodeTrackId() {
        return this.stNodeTrackId;
    }
    
    /**
     * 环节跟踪信息ID
     */
    public String stNodeTrackId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stNodeTrackId);
    }

    /**
     * 环节跟踪信息ID
     */
    public void setStNodeTrackId(String stNodeTrackId) {
        stNodeTrackId = StringUtil.substringBySize(stNodeTrackId, 50, "GB18030");
        this.stNodeTrackId = stNodeTrackId;
    }
    
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
     * 操作部门
     */
    public String getStOpDepart() {
        return this.stOpDepart;
    }
    
    /**
     * 操作部门
     */
    public String stOpDepart2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpDepart);
    }

    /**
     * 操作部门
     */
    public void setStOpDepart(String stOpDepart) {
        stOpDepart = StringUtil.substringBySize(stOpDepart, 50, "GB18030");
        this.stOpDepart = stOpDepart;
    }
    
	/**
     * 操作环节
     */
    public String getStOpNode() {
        return this.stOpNode;
    }
    
    /**
     * 操作环节
     */
    public String stOpNode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpNode);
    }

    /**
     * 操作环节
     */
    public void setStOpNode(String stOpNode) {
        stOpNode = StringUtil.substringBySize(stOpNode, 50, "GB18030");
        this.stOpNode = stOpNode;
    }
    
	/**
     * 操作人员
     */
    public String getStOpUser() {
        return this.stOpUser;
    }
    
    /**
     * 操作人员
     */
    public String stOpUser2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpUser);
    }

    /**
     * 操作人员
     */
    public void setStOpUser(String stOpUser) {
        stOpUser = StringUtil.substringBySize(stOpUser, 50, "GB18030");
        this.stOpUser = stOpUser;
    }
    
	/**
     * 操作类型
     */
    public String getStOpType() {
        return this.stOpType;
    }
    
    /**
     * 操作类型
     */
    public String stOpType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpType);
    }

    /**
     * 操作类型
     */
    public void setStOpType(String stOpType) {
        stOpType = StringUtil.substringBySize(stOpType, 50, "GB18030");
        this.stOpType = stOpType;
    }
    
	/**
     * 操作备注
     */
    public String getStOpDesc() {
        return this.stOpDesc;
    }
    
    /**
     * 操作备注
     */
    public String stOpDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpDesc);
    }

    /**
     * 操作备注
     */
    public void setStOpDesc(String stOpDesc) {
        stOpDesc = StringUtil.substringBySize(stOpDesc, 50, "GB18030");
        this.stOpDesc = stOpDesc;
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
     * 环节开始时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtStart() {
        return this.dtStart;
    }
    
    /**
     * 环节开始时间
     */
    public String dtStart2Html(String pattern) {
        if (this.dtStart == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtStart);
        }
    }

    /**
     * 环节开始时间
     */
    public void setDtStart(Timestamp dtStart) {
        this.dtStart = dtStart;
    }

	/**
     * 环节结束时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtEnd() {
        return this.dtEnd;
    }
    
    /**
     * 环节结束时间
     */
    public String dtEnd2Html(String pattern) {
        if (this.dtEnd == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtEnd);
        }
    }

    /**
     * 环节结束时间
     */
    public void setDtEnd(Timestamp dtEnd) {
        this.dtEnd = dtEnd;
    }
    
	/**
     * 结果证照编号
     */
    public String getStCertNo() {
        return this.stCertNo;
    }
    
    /**
     * 结果证照编号
     */
    public String stCertNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertNo);
    }

    /**
     * 结果证照编号
     */
    public void setStCertNo(String stCertNo) {
        stCertNo = StringUtil.substringBySize(stCertNo, 50, "GB18030");
        this.stCertNo = stCertNo;
    }
    
	/**
     * 办件结果描述
     */
    public String getStResultExplain() {
        return this.stResultExplain;
    }
    
    /**
     * 办件结果描述
     */
    public String stResultExplain2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResultExplain);
    }

    /**
     * 办件结果描述
     */
    public void setStResultExplain(String stResultExplain) {
        stResultExplain = StringUtil.substringBySize(stResultExplain, 200, "GB18030");
        this.stResultExplain = stResultExplain;
    }

	/**
     * 是否需要快递
     */
    public BigDecimal getNmDelivery() {
        return this.nmDelivery;
    }
    
    /**
     * 是否需要快递
     */
    public String nmDelivery2Html(int precision) {
        if (this.nmDelivery == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDelivery);
        }
    }

    /**
     * 是否需要快递
     */
    public void setNmDelivery(BigDecimal nmDelivery) {
        this.nmDelivery = nmDelivery;
    }
    
	/**
     * 满意度
     */
    public String getStSatisfaction() {
        return this.stSatisfaction;
    }
    
    /**
     * 满意度
     */
    public String stSatisfaction2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSatisfaction);
    }

    /**
     * 满意度
     */
    public void setStSatisfaction(String stSatisfaction) {
        stSatisfaction = StringUtil.substringBySize(stSatisfaction, 50, "GB18030");
        this.stSatisfaction = stSatisfaction;
    }
    
	/**
     * 特别程序种类
     */
    public String getStSpecialType() {
        return this.stSpecialType;
    }
    
    /**
     * 特别程序种类
     */
    public String stSpecialType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSpecialType);
    }

    /**
     * 特别程序种类
     */
    public void setStSpecialType(String stSpecialType) {
        stSpecialType = StringUtil.substringBySize(stSpecialType, 50, "GB18030");
        this.stSpecialType = stSpecialType;
    }
    
	/**
     * 特别程序种类名称
     */
    public String getStSpecialName() {
        return this.stSpecialName;
    }
    
    /**
     * 特别程序种类名称
     */
    public String stSpecialName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSpecialName);
    }

    /**
     * 特别程序种类名称
     */
    public void setStSpecialName(String stSpecialName) {
        stSpecialName = StringUtil.substringBySize(stSpecialName, 50, "GB18030");
        this.stSpecialName = stSpecialName;
    }
    
	/**
     * 特别程序启动理由或依据
     */
    public String getStSpecialReason() {
        return this.stSpecialReason;
    }
    
    /**
     * 特别程序启动理由或依据
     */
    public String stSpecialReason2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSpecialReason);
    }

    /**
     * 特别程序启动理由或依据
     */
    public void setStSpecialReason(String stSpecialReason) {
        stSpecialReason = StringUtil.substringBySize(stSpecialReason, 200, "GB18030");
        this.stSpecialReason = stSpecialReason;
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