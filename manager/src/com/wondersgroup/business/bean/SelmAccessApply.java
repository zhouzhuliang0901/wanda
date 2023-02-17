package com.wondersgroup.business.bean;
		
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
 * 接入申请
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ACCESS_APPLY")
public class SelmAccessApply implements Serializable {
    
    /**
     * 接入申请
     */
    public static final String SELM_ACCESS_APPLY = "SELM_ACCESS_APPLY";
    
    /**
     * 申请ID
     */
    public static final String ST_ACCESS_APPLY_ID = "ST_ACCESS_APPLY_ID";
    
    /**
     * 申请标题
     */
    public static final String ST_APPLY_TITLE = "ST_APPLY_TITLE";
    
    /**
     * 申请内容
     */
    public static final String ST_APPLY_CONTENT = "ST_APPLY_CONTENT";
    
    /**
     * 附件ID
     */
    public static final String ST_ATTACH_ID = "ST_ATTACH_ID";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 申请人ID
     */
    public static final String ST_APPLY_USER_ID = "ST_APPLY_USER_ID";
    
    /**
     * 申请人姓名
     */
    public static final String ST_APPLY_USER_NAME = "ST_APPLY_USER_NAME";
    
    /**
     * 申请时间
     */
    public static final String DT_APPLY = "DT_APPLY";
    
    /**
     * 审核人ID
     */
    public static final String ST_AUDIT_USER_ID = "ST_AUDIT_USER_ID";
    
    /**
     * 审核人姓名
     */
    public static final String ST_AUDIT_USER_NAME = "ST_AUDIT_USER_NAME";
    
    /**
     * 审核时间
     */
    public static final String DT_AUDIT = "DT_AUDIT";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public SelmAccessApply() {
    }
    
    /**
     * 申请ID
     */
    @Id
    @Column(name = "ST_ACCESS_APPLY_ID")
    private String stAccessApplyId;
    
    /**
     * 申请标题
     */
    @Column(name = "ST_APPLY_TITLE")
    private String stApplyTitle;
    
    /**
     * 申请内容
     */
    @Column(name = "ST_APPLY_CONTENT")
    private String stApplyContent;
    
    /**
     * 附件ID
     */
    @Column(name = "ST_ATTACH_ID")
    private String stAttachId;
    
    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 申请人ID
     */
    @Column(name = "ST_APPLY_USER_ID")
    private String stApplyUserId;
    
    /**
     * 申请人姓名
     */
    @Column(name = "ST_APPLY_USER_NAME")
    private String stApplyUserName;
    
    /**
     * 申请时间
     */
    @Column(name = "DT_APPLY")
    private Timestamp dtApply;
    
    /**
     * 审核人ID
     */
    @Column(name = "ST_AUDIT_USER_ID")
    private String stAuditUserId;
    
    /**
     * 审核人姓名
     */
    @Column(name = "ST_AUDIT_USER_NAME")
    private String stAuditUserName;
    
    /**
     * 审核时间
     */
    @Column(name = "DT_AUDIT")
    private Timestamp dtAudit;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
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
    public String getStAccessApplyId() {
        return this.stAccessApplyId;
    }
    
    /**
     * 申请ID
     */
    public String stAccessApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAccessApplyId);
    }

    /**
     * 申请ID
     */
    public void setStAccessApplyId(String stAccessApplyId) {
        stAccessApplyId = StringUtil.substringBySize(stAccessApplyId, 50, "GB18030");
        this.stAccessApplyId = stAccessApplyId;
    }
    
	/**
     * 申请标题
     */
    public String getStApplyTitle() {
        return this.stApplyTitle;
    }
    
    /**
     * 申请标题
     */
    public String stApplyTitle2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyTitle);
    }

    /**
     * 申请标题
     */
    public void setStApplyTitle(String stApplyTitle) {
        stApplyTitle = StringUtil.substringBySize(stApplyTitle, 200, "GB18030");
        this.stApplyTitle = stApplyTitle;
    }
    
	/**
     * 申请内容
     */
    public String getStApplyContent() {
        return this.stApplyContent;
    }
    
    /**
     * 申请内容
     */
    public String stApplyContent2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyContent);
    }

    /**
     * 申请内容
     */
    public void setStApplyContent(String stApplyContent) {
        stApplyContent = StringUtil.substringBySize(stApplyContent, 2000, "GB18030");
        this.stApplyContent = stApplyContent;
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
     * 申请人ID
     */
    public String getStApplyUserId() {
        return this.stApplyUserId;
    }
    
    /**
     * 申请人ID
     */
    public String stApplyUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserId);
    }

    /**
     * 申请人ID
     */
    public void setStApplyUserId(String stApplyUserId) {
        stApplyUserId = StringUtil.substringBySize(stApplyUserId, 50, "GB18030");
        this.stApplyUserId = stApplyUserId;
    }
    
	/**
     * 申请人姓名
     */
    public String getStApplyUserName() {
        return this.stApplyUserName;
    }
    
    /**
     * 申请人姓名
     */
    public String stApplyUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserName);
    }

    /**
     * 申请人姓名
     */
    public void setStApplyUserName(String stApplyUserName) {
        stApplyUserName = StringUtil.substringBySize(stApplyUserName, 50, "GB18030");
        this.stApplyUserName = stApplyUserName;
    }

	/**
     * 申请时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtApply() {
        return this.dtApply;
    }
    
    /**
     * 申请时间
     */
    public String dtApply2Html(String pattern) {
        if (this.dtApply == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtApply);
        }
    }

    /**
     * 申请时间
     */
    public void setDtApply(Timestamp dtApply) {
        this.dtApply = dtApply;
    }
    
	/**
     * 审核人ID
     */
    public String getStAuditUserId() {
        return this.stAuditUserId;
    }
    
    /**
     * 审核人ID
     */
    public String stAuditUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAuditUserId);
    }

    /**
     * 审核人ID
     */
    public void setStAuditUserId(String stAuditUserId) {
        stAuditUserId = StringUtil.substringBySize(stAuditUserId, 50, "GB18030");
        this.stAuditUserId = stAuditUserId;
    }
    
	/**
     * 审核人姓名
     */
    public String getStAuditUserName() {
        return this.stAuditUserName;
    }
    
    /**
     * 审核人姓名
     */
    public String stAuditUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAuditUserName);
    }

    /**
     * 审核人姓名
     */
    public void setStAuditUserName(String stAuditUserName) {
        stAuditUserName = StringUtil.substringBySize(stAuditUserName, 50, "GB18030");
        this.stAuditUserName = stAuditUserName;
    }

	/**
     * 审核时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtAudit() {
        return this.dtAudit;
    }
    
    /**
     * 审核时间
     */
    public String dtAudit2Html(String pattern) {
        if (this.dtAudit == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtAudit);
        }
    }

    /**
     * 审核时间
     */
    public void setDtAudit(Timestamp dtAudit) {
        this.dtAudit = dtAudit;
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
     * 备注
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 备注
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 备注
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
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