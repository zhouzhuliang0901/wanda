package com.wondersgroup.serverApply.bean;
		
		
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
 * 服务关联设备
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_SERVER_DLINK")
public class SelmServerDlink implements Serializable {
    
    /**
     * 服务关联设备
     */
    public static final String SELM_SERVER_DLINK = "SELM_SERVER_DLINK";
    
    /**
     * 申请ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_MACHINE_ID = "ST_MACHINE_ID";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 批注原因
     */
    public static final String ST_REASON = "ST_REASON";
    
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
    
    public SelmServerDlink() {
    }
    
    /**
     * 申请ID
     */
    @Id
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 设备ID
     */
    @Id
    @Column(name = "ST_MACHINE_ID")
    private String stMachineId;
    
    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 批注原因
     */
    @Column(name = "ST_REASON")
    private String stReason;
    
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
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 申请ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 申请ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
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
     * 批注原因
     */
    public String getStReason() {
        return this.stReason;
    }
    
    /**
     * 批注原因
     */
    public String stReason2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReason);
    }

    /**
     * 批注原因
     */
    public void setStReason(String stReason) {
        stReason = StringUtil.substringBySize(stReason, 100, "GB18030");
        this.stReason = stReason;
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