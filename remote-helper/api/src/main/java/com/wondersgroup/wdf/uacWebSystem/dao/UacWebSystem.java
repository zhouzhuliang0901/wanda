package com.wondersgroup.wdf.uacWebSystem.dao;
		
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
 * 系统信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_WEB_SYSTEM")
public class UacWebSystem implements Serializable {
    
    /**
     * 系统信息
     */
    public static final String UAC_WEB_SYSTEM = "UAC_WEB_SYSTEM";
    
    /**
     * 系统ID
     */
    public static final String ST_WEB_SYSTEM_ID = "ST_WEB_SYSTEM_ID";
    
    /**
     * 系统名称
     */
    public static final String ST_SYSTEM_NAME = "ST_SYSTEM_NAME";
    
    /**
     * 系统地址
     */
    public static final String ST_SYSTEM_URL = "ST_SYSTEM_URL";
    
    /**
     * 部门ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 主管单位
     */
    public static final String ST_MORGAN = "ST_MORGAN";
    
    /**
     * 联系人
     */
    public static final String ST_CONTACTOR = "ST_CONTACTOR";
    
    /**
     * 联系方式
     */
    public static final String ST_TEL = "ST_TEL";
    
    /**
     * 系统类型
     */
    public static final String ST_TYPE = "ST_TYPE";
    
    /**
     * 对接状态
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
    
    /**
     * 对接情况
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
    
    public UacWebSystem() {
    }
    
    /**
     * 系统ID
     */
    @Id
    @Column(name = "ST_WEB_SYSTEM_ID")
    private String stWebSystemId;
    
    /**
     * 系统名称
     */
    @Column(name = "ST_SYSTEM_NAME")
    private String stSystemName;
    
    /**
     * 系统地址
     */
    @Column(name = "ST_SYSTEM_URL")
    private String stSystemUrl;
    
    /**
     * 部门ID
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 主管单位
     */
    @Column(name = "ST_MORGAN")
    private String stMorgan;
    
    /**
     * 联系人
     */
    @Column(name = "ST_CONTACTOR")
    private String stContactor;
    
    /**
     * 联系方式
     */
    @Column(name = "ST_TEL")
    private String stTel;
    
    /**
     * 系统类型
     */
    @Column(name = "ST_TYPE")
    private String stType;
    
    /**
     * 对接状态
     */
    @Column(name = "ST_STATUS")
    private String stStatus;
    
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
     * 对接情况
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
     * 系统ID
     */
    public String getStWebSystemId() {
        return this.stWebSystemId;
    }
    
    /**
     * 系统ID
     */
    public String stWebSystemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stWebSystemId);
    }

    /**
     * 系统ID
     */
    public void setStWebSystemId(String stWebSystemId) {
        stWebSystemId = StringUtil.substringBySize(stWebSystemId, 50, "GB18030");
        this.stWebSystemId = stWebSystemId;
    }
    
	/**
     * 系统名称
     */
    public String getStSystemName() {
        return this.stSystemName;
    }
    
    /**
     * 系统名称
     */
    public String stSystemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSystemName);
    }

    /**
     * 系统名称
     */
    public void setStSystemName(String stSystemName) {
        stSystemName = StringUtil.substringBySize(stSystemName, 50, "GB18030");
        this.stSystemName = stSystemName;
    }
    
	/**
     * 系统地址
     */
    public String getStSystemUrl() {
        return this.stSystemUrl;
    }
    
    /**
     * 系统地址
     */
    public String stSystemUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSystemUrl);
    }

    /**
     * 系统地址
     */
    public void setStSystemUrl(String stSystemUrl) {
        stSystemUrl = StringUtil.substringBySize(stSystemUrl, 200, "GB18030");
        this.stSystemUrl = stSystemUrl;
    }
    
	/**
     * 部门ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 部门ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 部门ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 主管单位
     */
    public String getStMorgan() {
        return this.stMorgan;
    }
    
    /**
     * 主管单位
     */
    public String stMorgan2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMorgan);
    }

    /**
     * 主管单位
     */
    public void setStMorgan(String stMorgan) {
        stMorgan = StringUtil.substringBySize(stMorgan, 50, "GB18030");
        this.stMorgan = stMorgan;
    }
    
	/**
     * 联系人
     */
    public String getStContactor() {
        return this.stContactor;
    }
    
    /**
     * 联系人
     */
    public String stContactor2Html() {
        return StringHelper.replaceHTMLSymbol(this.stContactor);
    }

    /**
     * 联系人
     */
    public void setStContactor(String stContactor) {
        stContactor = StringUtil.substringBySize(stContactor, 50, "GB18030");
        this.stContactor = stContactor;
    }
    
	/**
     * 联系方式
     */
    public String getStTel() {
        return this.stTel;
    }
    
    /**
     * 联系方式
     */
    public String stTel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTel);
    }

    /**
     * 联系方式
     */
    public void setStTel(String stTel) {
        stTel = StringUtil.substringBySize(stTel, 50, "GB18030");
        this.stTel = stTel;
    }
    
	/**
     * 系统类型
     */
    public String getStType() {
        return this.stType;
    }
    
    /**
     * 系统类型
     */
    public String stType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stType);
    }

    /**
     * 系统类型
     */
    public void setStType(String stType) {
        stType = StringUtil.substringBySize(stType, 50, "GB18030");
        this.stType = stType;
    }
    
	/**
     * 对接状态
     */
    public String getStStatus() {
        return this.stStatus;
    }
    
    /**
     * 对接状态
     */
    public String stStatus2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStatus);
    }

    /**
     * 对接状态
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
    
	/**
     * 对接情况
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 对接情况
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 对接情况
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 500, "GB18030");
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