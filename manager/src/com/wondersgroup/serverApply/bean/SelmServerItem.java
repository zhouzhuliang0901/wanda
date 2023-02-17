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
 * 服务（设备）关联事项
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_SERVER_ITEM")
public class SelmServerItem implements Serializable {
    
    /**
     * 服务（设备）关联事项
     */
    public static final String SELM_SERVER_ITEM = "SELM_SERVER_ITEM";
    
    /**
     * 关联ID
     */
    public static final String ST_LINKS_ID = "ST_LINKS_ID";
    
    /**
     * 申请ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 事项类别ID
     */
    public static final String ST_ITEM_TYPE_ID = "ST_ITEM_TYPE_ID";
    
    /**
     * 事项ID
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
     * 所属部门
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 是否通过
     */
    public static final String NM_PASS = "NM_PASS";
    
    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 批注原因
     */
    public static final String ST_REASON = "ST_REASON";
    
    /**
     * 关联类别
     */
    public static final String NM_TYPE = "NM_TYPE";
    
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
    
    public SelmServerItem() {
    }
    
    /**
     * 关联ID
     */
    @Id
    @Column(name = "ST_LINKS_ID")
    private String stLinksId;
    
    /**
     * 申请ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 事项类别ID
     */
    @Column(name = "ST_ITEM_TYPE_ID")
    private String stItemTypeId;
    
    /**
     * 事项ID
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
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 所属部门
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 是否通过
     */
    @Column(name = "NM_PASS")
    private BigDecimal nmPass;
    
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
     * 关联类别
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
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
     * 关联ID
     */
    public String getStLinksId() {
        return this.stLinksId;
    }
    
    /**
     * 关联ID
     */
    public String stLinksId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLinksId);
    }

    /**
     * 关联ID
     */
    public void setStLinksId(String stLinksId) {
        stLinksId = StringUtil.substringBySize(stLinksId, 50, "GB18030");
        this.stLinksId = stLinksId;
    }
    
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
    public String getStDeviceId() {
        return this.stDeviceId;
    }
    
    /**
     * 设备ID
     */
    public String stDeviceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceId);
    }

    /**
     * 设备ID
     */
    public void setStDeviceId(String stDeviceId) {
        stDeviceId = StringUtil.substringBySize(stDeviceId, 50, "GB18030");
        this.stDeviceId = stDeviceId;
    }
    
	/**
     * 事项类别ID
     */
    public String getStItemTypeId() {
        return this.stItemTypeId;
    }
    
    /**
     * 事项类别ID
     */
    public String stItemTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemTypeId);
    }

    /**
     * 事项类别ID
     */
    public void setStItemTypeId(String stItemTypeId) {
        stItemTypeId = StringUtil.substringBySize(stItemTypeId, 50, "GB18030");
        this.stItemTypeId = stItemTypeId;
    }
    
	/**
     * 事项ID
     */
    public String getStItemId() {
        return this.stItemId;
    }
    
    /**
     * 事项ID
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项ID
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
     * 所属部门
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 所属部门
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 所属部门
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }

	/**
     * 是否通过
     */
    public BigDecimal getNmPass() {
        return this.nmPass;
    }
    
    /**
     * 是否通过
     */
    public String nmPass2Html(int precision) {
        if (this.nmPass == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmPass);
        }
    }

    /**
     * 是否通过
     */
    public void setNmPass(BigDecimal nmPass) {
        this.nmPass = nmPass;
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
     * 关联类别
     */
    public BigDecimal getNmType() {
        return this.nmType;
    }
    
    /**
     * 关联类别
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
     * 关联类别
     */
    public void setNmType(BigDecimal nmType) {
        this.nmType = nmType;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}