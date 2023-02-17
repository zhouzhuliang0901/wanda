package com.wondersgroup.wdf.extPdCclist.dao;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
 * 子证归集表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "EXT_PD_CCLIST")
@ColumnWidth(25)
public class ExtPdCclist implements Serializable {
    
    /**
     * 子证归集表
     */
    public static final String EXT_PD_CCLIST = "EXT_PD_CCLIST";
    
    /**
     * 归集ID
     */
    public static final String ST_CCLIST_ID = "ST_CCLIST_ID";

    /**
     * 业务办件流水号
     */
    public static final String ST_APPLY_NO = "ST_APPLY_NO";
    
    /**
     * 单窗子办件流水号
     */
    public static final String ST_SUB_NO = "ST_SUB_NO";
    
    /**
     * 所属部门ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 所属部门名称
     */
    public static final String ST_ORGAN_NAME = "ST_ORGAN_NAME";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_CNO = "ST_ITEM_CNO";
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NO = "ST_ITEM_NO";
    
    /**
     * 证照目录编号
     */
    public static final String ST_CERT_NO = "ST_CERT_NO";
    
    /**
     * 证照名称
     */
    public static final String ST_CERT_NAME = "ST_CERT_NAME";
    
    /**
     * 证照许可编号
     */
    public static final String ST_LICENSE_NO = "ST_LICENSE_NO";
    
    /**
     * 制证日期
     */
    public static final String DT_MARK = "DT_MARK";
    
    /**
     * 统一社会信用代码
     */
    public static final String ST_CORPORATION_ORGID = "ST_CORPORATION_ORGID";
    
    /**
     * 企业名称
     */
    public static final String ST_ENAME = "ST_ENAME";
    
    /**
     * 状态
     */
    public static final String ST_STATUS = "ST_STATUS";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public ExtPdCclist() {
    }
    
    /**
     * 归集ID
     */
    @Id
    @ExcelIgnore
    @Column(name = "ST_CCLIST_ID")
    private String stCclistId;
    
    /**
     * 业务办件流水号
     */
    @ExcelProperty(value = "业务办件流水号",index = 1)
    @Column(name = "ST_APPLY_NO")
    private String stApplyNo;
    
    /**
     * 单窗子办件流水号
     */
    @ExcelProperty(value = "单窗子办件流水号",index = 0)
    @Column(name = "ST_SUB_NO")
    private String stSubNo;
    
    /**
     * 所属部门ID
     */
    @ExcelIgnore
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 所属部门名称
     */
    @ExcelProperty(value = "所属部门",index = 2)
    @Column(name = "ST_ORGAN_NAME")
    private String stOrganName;
    
    /**
     * 事项编码
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "33位编码",index = 3)
    @Column(name = "ST_ITEM_CNO")
    private String stItemCno;
    
    /**
     * 事项名称
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "事项名称",index = 4)
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 证照目录编号
     */
    @ExcelProperty(value = "证照目录编号",index = 5)
    @Column(name = "ST_CERT_NO")
    private String stCertNo;
    
    /**
     * 证照名称
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "证照名称",index = 6)
    @Column(name = "ST_CERT_NAME")
    private String stCertName;
    
    /**
     * 证照许可编号
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "证照许可编号",index = 7)
    @Column(name = "ST_LICENSE_NO")
    private String stLicenseNo;
    
    /**
     * 制证日期
     */
    @ExcelProperty(value = "制证日期",index = 8,converter = TimestampStringConverter.class)
    @Column(name = "DT_MARK")
    private Timestamp dtMark;
    
    /**
     * 统一社会信用代码
     */
    @ExcelProperty(value = "统一社会信用代码",index = 9)
    @Column(name = "ST_CORPORATION_ORGID")
    private String stCorporationOrgid;
    
    /**
     * 企业名称
     */
    @ColumnWidth(50)
    @ExcelProperty(value = "企业名称",index = 10)
    @Column(name = "ST_ENAME")
    private String stEname;
    
    /**
     * 状态
     */
    @ExcelIgnore
    @Column(name = "ST_STATUS")
    private String stStatus;
    
    /**
     * 创建时间
     */
    @ExcelIgnore
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 更新时间
     */
    @ExcelIgnore
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
	/**
     * 归集ID
     */
    public String getStCclistId() {
        return this.stCclistId;
    }
    
    /**
     * 归集ID
     */
    public String stCclistId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCclistId);
    }

    /**
     * 归集ID
     */
    public void setStCclistId(String stCclistId) {
        stCclistId = StringUtil.substringBySize(stCclistId, 50, "GB18030");
        this.stCclistId = stCclistId;
    }
    
	/**
     * 业务办件流水号
     */
    public String getStApplyNo() {
        return this.stApplyNo;
    }
    
    /**
     * 业务办件流水号
     */
    public String stApplyNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyNo);
    }

    /**
     * 业务办件流水号
     */
    public void setStApplyNo(String stApplyNo) {
        stApplyNo = StringUtil.substringBySize(stApplyNo, 50, "GB18030");
        this.stApplyNo = stApplyNo;
    }
    
	/**
     * 单窗子办件流水号
     */
    public String getStSubNo() {
        return this.stSubNo;
    }
    
    /**
     * 单窗子办件流水号
     */
    public String stSubNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSubNo);
    }

    /**
     * 单窗子办件流水号
     */
    public void setStSubNo(String stSubNo) {
        stSubNo = StringUtil.substringBySize(stSubNo, 50, "GB18030");
        this.stSubNo = stSubNo;
    }
    
	/**
     * 所属部门ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 所属部门ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 所属部门ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 所属部门名称
     */
    public String getStOrganName() {
        return this.stOrganName;
    }
    
    /**
     * 所属部门名称
     */
    public String stOrganName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganName);
    }

    /**
     * 所属部门名称
     */
    public void setStOrganName(String stOrganName) {
        stOrganName = StringUtil.substringBySize(stOrganName, 50, "GB18030");
        this.stOrganName = stOrganName;
    }
    
	/**
     * 事项编码
     */
    public String getStItemCno() {
        return this.stItemCno;
    }
    
    /**
     * 事项编码
     */
    public String stItemCno2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemCno);
    }

    /**
     * 事项编码
     */
    public void setStItemCno(String stItemCno) {
        stItemCno = StringUtil.substringBySize(stItemCno, 50, "GB18030");
        this.stItemCno = stItemCno;
    }
    
	/**
     * 事项名称
     */
    public String getStItemNo() {
        return this.stItemNo;
    }
    
    /**
     * 事项名称
     */
    public String stItemNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemNo);
    }

    /**
     * 事项名称
     */
    public void setStItemNo(String stItemNo) {
        stItemNo = StringUtil.substringBySize(stItemNo, 200, "GB18030");
        this.stItemNo = stItemNo;
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
     * 统一社会信用代码
     */
    public String getStCorporationOrgid() {
        return this.stCorporationOrgid;
    }
    
    /**
     * 统一社会信用代码
     */
    public String stCorporationOrgid2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCorporationOrgid);
    }

    /**
     * 统一社会信用代码
     */
    public void setStCorporationOrgid(String stCorporationOrgid) {
        stCorporationOrgid = StringUtil.substringBySize(stCorporationOrgid, 50, "GB18030");
        this.stCorporationOrgid = stCorporationOrgid;
    }
    
	/**
     * 企业名称
     */
    public String getStEname() {
        return this.stEname;
    }
    
    /**
     * 企业名称
     */
    public String stEname2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEname);
    }

    /**
     * 企业名称
     */
    public void setStEname(String stEname) {
        stEname = StringUtil.substringBySize(stEname, 50, "GB18030");
        this.stEname = stEname;
    }
    
	/**
     * 状态
     */
    public String getStStatus() {
        return this.stStatus;
    }
    
    /**
     * 状态
     */
    public String stStatus2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatus);
    }

    /**
     * 状态
     */
    public void setStStatus(String stStatus) {
        stStatus = StringUtil.substringBySize(stStatus, 50, "GB18030");
        this.stStatus = stStatus;
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