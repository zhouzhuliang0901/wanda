package com.wondersgroup.wdf.uacCert.dao;

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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 发证信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_CERT")
public class UacCert implements Serializable {
    
    /**
     * 发证信息
     */
    public static final String UAC_CERT = "UAC_CERT";
    
    /**
     * 证书ID
     */
    public static final String ST_CERT_ID = "ST_CERT_ID";
    
    /**
     * 办件信息ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 办件反馈ID
     */
    public static final String ST_FEEDBACK_ID = "ST_FEEDBACK_ID";
    
    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_CODE = "ST_ITEM_CODE";
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 证照目录编号
     */
    public static final String ST_CERT_NO = "ST_CERT_NO";
    
    /**
     * 制证日期
     */
    public static final String DT_MARK = "DT_MARK";
    
    /**
     * 证照名称
     */
    public static final String ST_CERT_NAME = "ST_CERT_NAME";
    
    /**
     * 证照许可编号
     */
    public static final String ST_LICENSE_NO = "ST_LICENSE_NO";
    
    /**
     * 证书有效期
     */
    public static final String ST_VALID_DATE = "ST_VALID_DATE";
    
    /**
     * 经营地域
     */
    public static final String ST_AREA = "ST_AREA";
    
    /**
     * 发证机关
     */
    public static final String ST_ORGAN = "ST_ORGAN";
    
    /**
     * 从事业务内容
     */
    public static final String ST_BCONTENT = "ST_BCONTENT";
    
    /**
     * 领证人姓名
     */
    public static final String ST_LNAME = "ST_LNAME";
    
    /**
     * 领证人联系方式
     */
    public static final String ST_LCONTACT = "ST_LCONTACT";
    
    /**
     * 备注
     */
    public static final String ST_REMARK = "ST_REMARK";
    
    /**
     * 办件物流ID
     */
    public static final String ST_UNION_LOGISTICS_ID = "ST_UNION_LOGISTICS_ID";

    /**
     * 发证日期
     */
    public static final String DT_PORDER = "DT_PORDER";

    /**
     * 发证日期
     */
    public static final String DT_CERTIFICATION = "DT_CERTIFICATION";
    
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
    
    public UacCert() {
    }
    
    /**
     * 证书ID
     */
    @Id
    @Column(name = "ST_CERT_ID")
    private String stCertId;
    
    /**
     * 办件信息ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 办件反馈ID
     */
    @Column(name = "ST_FEEDBACK_ID")
    private String stFeedbackId;
    
    /**
     * 事项ID
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_CODE")
    private String stItemCode;
    
    /**
     * 事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 证照目录编号
     */
    @Column(name = "ST_CERT_NO")
    private String stCertNo;
    
    /**
     * 制证日期
     */
    @Column(name = "DT_MARK")
    private Timestamp dtMark;
    
    /**
     * 证照名称
     */
    @Column(name = "ST_CERT_NAME")
    private String stCertName;
    
    /**
     * 证照许可编号
     */
    @Column(name = "ST_LICENSE_NO")
    private String stLicenseNo;
    
    /**
     * 证书有效期
     */
    @Column(name = "ST_VALID_DATE")
    private String stValidDate;
    
    /**
     * 经营地域
     */
    @Column(name = "ST_AREA")
    private String stArea;
    
    /**
     * 发证机关
     */
    @Column(name = "ST_ORGAN")
    private String stOrgan;
    
    /**
     * 从事业务内容
     */
    @Column(name = "ST_BCONTENT")
    private String stBcontent;
    
    /**
     * 领证人姓名
     */
    @Column(name = "ST_LNAME")
    private String stLname;
    
    /**
     * 领证人联系方式
     */
    @Column(name = "ST_LCONTACT")
    private String stLcontact;
    
    /**
     * 备注
     */
    @Column(name = "ST_REMARK")
    private String stRemark;
    
    /**
     * 办件物流ID
     */
    @Column(name = "ST_UNION_LOGISTICS_ID")
    private String stUnionLogisticsId;
    
    /**
     * 发证日期
     */
    @Column(name = "DT_CERTIFICATION")
    private Timestamp dtCertification;
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
     * 证书ID
     */
    public String getStCertId() {
        return this.stCertId;
    }
    
    /**
     * 证书ID
     */
    public String stCertId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertId);
    }

    /**
     * 证书ID
     */
    public void setStCertId(String stCertId) {
        stCertId = StringUtil.substringBySize(stCertId, 50, "GB18030");
        this.stCertId = stCertId;
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
    public String getStItemCode() {
        return this.stItemCode;
    }
    
    /**
     * 事项编码
     */
    public String stItemCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemCode);
    }

    /**
     * 事项编码
     */
    public void setStItemCode(String stItemCode) {
        stItemCode = StringUtil.substringBySize(stItemCode, 50, "GB18030");
        this.stItemCode = stItemCode;
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
     * 证照目录编号
     */
    public String getStCertNo() {
        return this.stCertNo;
    }
    
    /**
     * 证照目录编号
     */
    public String stCertNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertNo);
    }

    /**
     * 证照目录编号
     */
    public void setStCertNo(String stCertNo) {
        stCertNo = StringUtil.substringBySize(stCertNo, 50, "GB18030");
        this.stCertNo = stCertNo;
    }

	/**
     * 制证日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtMark() {
        return this.dtMark;
    }
    
    /**
     * 制证日期
     */
    public String dtMark2Html(String pattern) {
        if (this.dtMark == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtMark);
        }
    }

    /**
     * 制证日期
     */
    public void setDtMark(Timestamp dtMark) {
        this.dtMark = dtMark;
    }
    
	/**
     * 证照名称
     */
    public String getStCertName() {
        return this.stCertName;
    }
    
    /**
     * 证照名称
     */
    public String stCertName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertName);
    }

    /**
     * 证照名称
     */
    public void setStCertName(String stCertName) {
        stCertName = StringUtil.substringBySize(stCertName, 50, "GB18030");
        this.stCertName = stCertName;
    }
    
	/**
     * 证照许可编号
     */
    public String getStLicenseNo() {
        return this.stLicenseNo;
    }
    
    /**
     * 证照许可编号
     */
    public String stLicenseNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLicenseNo);
    }

    /**
     * 证照许可编号
     */
    public void setStLicenseNo(String stLicenseNo) {
        stLicenseNo = StringUtil.substringBySize(stLicenseNo, 50, "GB18030");
        this.stLicenseNo = stLicenseNo;
    }
    
	/**
     * 证书有效期
     */
    public String getStValidDate() {
        return this.stValidDate;
    }
    
    /**
     * 证书有效期
     */
    public String stValidDate2Html() {
        return StringHelper.replaceHTMLSymbol(this.stValidDate);
    }

    /**
     * 证书有效期
     */
    public void setStValidDate(String stValidDate) {
        stValidDate = StringUtil.substringBySize(stValidDate, 50, "GB18030");
        this.stValidDate = stValidDate;
    }
    
	/**
     * 经营地域
     */
    public String getStArea() {
        return this.stArea;
    }
    
    /**
     * 经营地域
     */
    public String stArea2Html() {
        return StringHelper.replaceHTMLSymbol(this.stArea);
    }

    /**
     * 经营地域
     */
    public void setStArea(String stArea) {
        stArea = StringUtil.substringBySize(stArea, 200, "GB18030");
        this.stArea = stArea;
    }
    
	/**
     * 发证机关
     */
    public String getStOrgan() {
        return this.stOrgan;
    }
    
    /**
     * 发证机关
     */
    public String stOrgan2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrgan);
    }

    /**
     * 发证机关
     */
    public void setStOrgan(String stOrgan) {
        stOrgan = StringUtil.substringBySize(stOrgan, 50, "GB18030");
        this.stOrgan = stOrgan;
    }
    
	/**
     * 从事业务内容
     */
    public String getStBcontent() {
        return this.stBcontent;
    }
    
    /**
     * 从事业务内容
     */
    public String stBcontent2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBcontent);
    }

    /**
     * 从事业务内容
     */
    public void setStBcontent(String stBcontent) {
        stBcontent = StringUtil.substringBySize(stBcontent, 500, "GB18030");
        this.stBcontent = stBcontent;
    }
    
	/**
     * 领证人姓名
     */
    public String getStLname() {
        return this.stLname;
    }
    
    /**
     * 领证人姓名
     */
    public String stLname2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLname);
    }

    /**
     * 领证人姓名
     */
    public void setStLname(String stLname) {
        stLname = StringUtil.substringBySize(stLname, 50, "GB18030");
        this.stLname = stLname;
    }
    
	/**
     * 领证人联系方式
     */
    public String getStLcontact() {
        return this.stLcontact;
    }
    
    /**
     * 领证人联系方式
     */
    public String stLcontact2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLcontact);
    }

    /**
     * 领证人联系方式
     */
    public void setStLcontact(String stLcontact) {
        stLcontact = StringUtil.substringBySize(stLcontact, 50, "GB18030");
        this.stLcontact = stLcontact;
    }
    
	/**
     * 备注
     */
    public String getStRemark() {
        return this.stRemark;
    }
    
    /**
     * 备注
     */
    public String stRemark2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRemark);
    }

    /**
     * 备注
     */
    public void setStRemark(String stRemark) {
        stRemark = StringUtil.substringBySize(stRemark, 50, "GB18030");
        this.stRemark = stRemark;
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
     * 发证日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtCertification() {
        return this.dtCertification;
    }
    
    /**
     * 发证日期
     */
    public String dtCertification2Html(String pattern) {
        if (this.dtCertification == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtCertification);
        }
    }

    /**
     * 发证日期
     */
    public void setDtCertification(Timestamp dtCertification) {
        this.dtCertification = dtCertification;
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