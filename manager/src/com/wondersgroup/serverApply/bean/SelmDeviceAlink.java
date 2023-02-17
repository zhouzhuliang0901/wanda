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
 * 接入申请关联设备
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DEVICE_ALINK")
public class SelmDeviceAlink implements Serializable {
    
    /**
     * 接入申请关联设备
     */
    public static final String SELM_DEVICE_ALINK = "SELM_DEVICE_ALINK";
    
    /**
     * 申请ID
     */
    public static final String ST_DEVICE_APPLY_ID = "ST_DEVICE_APPLY_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_MACHINE_ID = "ST_MACHINE_ID";
    
    /**
     * 设备名称
     */
    public static final String ST_DEVICE_NAME = "ST_DEVICE_NAME";
    
    /**
     * 设备编号
     */
    public static final String ST_DEVICE_CODE = "ST_DEVICE_CODE";
    
    /**
     * 设备IP
     */
    public static final String ST_DEVICE_IP = "ST_DEVICE_IP";
    
    /**
     * 设备MAC
     */
    public static final String ST_DEVICE_MAC = "ST_DEVICE_MAC";
    
    /**
     * 设备详细地址
     */
    public static final String ST_DEVICE_ADDRESS = "ST_DEVICE_ADDRESS";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 地址ID
     */
    public static final String ST_ADDRESS_ID = "ST_ADDRESS_ID";
    
    /**
     * 组织机构ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 证书唯一标识
     */
    public static final String ST_CERT_KEY = "ST_CERT_KEY";
    
    /**
     * 类型ID
     */
    public static final String ST_TYPE_ID = "ST_TYPE_ID";
    
    /**
     * 是否是主机
     */
    public static final String NM_IS_HOST = "NM_IS_HOST";
    
    /**
     * 是否有医保制册机
     */
    public static final String NM_YBZC = "NM_YBZC";
    
    /**
     * 是否有高拍仪
     */
    public static final String NM_GPY = "NM_GPY";
    
    /**
     * 是否有居住证签注机
     */
    public static final String NM_JZZQZ = "NM_JZZQZ";
    
    /**
     * 是否有居住证制卡机
     */
    public static final String NM_JZZZK = "NM_JZZZK";
    
    /**
     * 网络情况
     */
    public static final String ST_NETWORK = "ST_NETWORK";
    
    /**
     * 是否有人员值守
     */
    public static final String NM_DUTY = "NM_DUTY";
    
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
    
    /**
     * 文件内容
     */
    public static final String BL_CONTENT = "BL_CONTENT";
    
    /**
     * 是否是24小时设备
     */
    public static final String NM_24HOURS = "NM_24HOURS";
    
    
    public SelmDeviceAlink() {
    }
    
    /**
     * 申请ID
     */
    @Id
    @Column(name = "ST_DEVICE_APPLY_ID")
    private String stDeviceApplyId;
    
    /**
     * 设备ID
     */
    @Id
    @Column(name = "ST_MACHINE_ID")
    private String stMachineId;
    
    /**
     * 设备名称
     */
    @Column(name = "ST_DEVICE_NAME")
    private String stDeviceName;
    
    /**
     * 设备编号
     */
    @Column(name = "ST_DEVICE_CODE")
    private String stDeviceCode;
    
    /**
     * 设备IP
     */
    @Column(name = "ST_DEVICE_IP")
    private String stDeviceIp;
    
    /**
     * 设备MAC
     */
    @Column(name = "ST_DEVICE_MAC")
    private String stDeviceMac;
    
    /**
     * 设备详细地址
     */
    @Column(name = "ST_DEVICE_ADDRESS")
    private String stDeviceAddress;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 地址ID
     */
    @Column(name = "ST_ADDRESS_ID")
    private String stAddressId;
    
    /**
     * 组织机构ID
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 证书唯一标识
     */
    @Column(name = "ST_CERT_KEY")
    private String stCertKey;
    
    /**
     * 类型ID
     */
    @Column(name = "ST_TYPE_ID")
    private String stTypeId;
    
    /**
     * 是否是主机
     */
    @Column(name = "NM_IS_HOST")
    private BigDecimal nmIsHost;
    
    /**
     * 是否有医保制册机
     */
    @Column(name = "NM_YBZC")
    private BigDecimal nmYbzc;
    
    /**
     * 是否有高拍仪
     */
    @Column(name = "NM_GPY")
    private BigDecimal nmGpy;
    
    /**
     * 是否有居住证签注机
     */
    @Column(name = "NM_JZZQZ")
    private BigDecimal nmJzzqz;
    
    /**
     * 是否有居住证制卡机
     */
    @Column(name = "NM_JZZZK")
    private BigDecimal nmJzzzk;
    
    /**
     * 网络情况
     */
    @Column(name = "ST_NETWORK")
    private String stNetwork;
    
    /**
     * 是否有人员值守
     */
    @Column(name = "NM_DUTY")
    private BigDecimal nmDuty;
    
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
     * 文件内容
     */
    @Lob
    @Column(name = "BL_CONTENT")
    private byte[] blContent;
    
    /**
     * 状态
     */
    @Column(name = "NM_24HOURS")
    private BigDecimal nm24Hours;
    
	/**
     * 申请ID
     */
    public String getStDeviceApplyId() {
        return this.stDeviceApplyId;
    }
    
    /**
     * 申请ID
     */
    public String stDeviceApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceApplyId);
    }

    /**
     * 申请ID
     */
    public void setStDeviceApplyId(String stDeviceApplyId) {
        stDeviceApplyId = StringUtil.substringBySize(stDeviceApplyId, 50, "GB18030");
        this.stDeviceApplyId = stDeviceApplyId;
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
     * 设备名称
     */
    public String getStDeviceName() {
        return this.stDeviceName;
    }
    
    /**
     * 设备名称
     */
    public String stDeviceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceName);
    }

    /**
     * 设备名称
     */
    public void setStDeviceName(String stDeviceName) {
        stDeviceName = StringUtil.substringBySize(stDeviceName, 100, "GB18030");
        this.stDeviceName = stDeviceName;
    }
    
	/**
     * 设备编号
     */
    public String getStDeviceCode() {
        return this.stDeviceCode;
    }
    
    /**
     * 设备编号
     */
    public String stDeviceCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceCode);
    }

    /**
     * 设备编号
     */
    public void setStDeviceCode(String stDeviceCode) {
        stDeviceCode = StringUtil.substringBySize(stDeviceCode, 50, "GB18030");
        this.stDeviceCode = stDeviceCode;
    }
    
	/**
     * 设备IP
     */
    public String getStDeviceIp() {
        return this.stDeviceIp;
    }
    
    /**
     * 设备IP
     */
    public String stDeviceIp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceIp);
    }

    /**
     * 设备IP
     */
    public void setStDeviceIp(String stDeviceIp) {
        stDeviceIp = StringUtil.substringBySize(stDeviceIp, 50, "GB18030");
        this.stDeviceIp = stDeviceIp;
    }
    
	/**
     * 设备MAC
     */
    public String getStDeviceMac() {
        return this.stDeviceMac;
    }
    
    /**
     * 设备MAC
     */
    public String stDeviceMac2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceMac);
    }

    /**
     * 设备MAC
     */
    public void setStDeviceMac(String stDeviceMac) {
        stDeviceMac = StringUtil.substringBySize(stDeviceMac, 50, "GB18030");
        this.stDeviceMac = stDeviceMac;
    }
    
	/**
     * 设备详细地址
     */
    public String getStDeviceAddress() {
        return this.stDeviceAddress;
    }
    
    /**
     * 设备详细地址
     */
    public String stDeviceAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceAddress);
    }

    /**
     * 设备详细地址
     */
    public void setStDeviceAddress(String stDeviceAddress) {
        stDeviceAddress = StringUtil.substringBySize(stDeviceAddress, 100, "GB18030");
        this.stDeviceAddress = stDeviceAddress;
    }
    
	/**
     * 区域ID
     */
    public String getStAreaId() {
        return this.stAreaId;
    }
    
    /**
     * 区域ID
     */
    public String stAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaId);
    }

    /**
     * 区域ID
     */
    public void setStAreaId(String stAreaId) {
        stAreaId = StringUtil.substringBySize(stAreaId, 50, "GB18030");
        this.stAreaId = stAreaId;
    }
    
	/**
     * 用户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 用户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 用户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }
    
	/**
     * 地址ID
     */
    public String getStAddressId() {
        return this.stAddressId;
    }
    
    /**
     * 地址ID
     */
    public String stAddressId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAddressId);
    }

    /**
     * 地址ID
     */
    public void setStAddressId(String stAddressId) {
        stAddressId = StringUtil.substringBySize(stAddressId, 50, "GB18030");
        this.stAddressId = stAddressId;
    }
    
	/**
     * 组织机构ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 组织机构ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 组织机构ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }
    
	/**
     * 证书唯一标识
     */
    public String getStCertKey() {
        return this.stCertKey;
    }
    
    /**
     * 证书唯一标识
     */
    public String stCertKey2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertKey);
    }

    /**
     * 证书唯一标识
     */
    public void setStCertKey(String stCertKey) {
        stCertKey = StringUtil.substringBySize(stCertKey, 50, "GB18030");
        this.stCertKey = stCertKey;
    }
    
	/**
     * 类型ID
     */
    public String getStTypeId() {
        return this.stTypeId;
    }
    
    /**
     * 类型ID
     */
    public String stTypeId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTypeId);
    }

    /**
     * 类型ID
     */
    public void setStTypeId(String stTypeId) {
        stTypeId = StringUtil.substringBySize(stTypeId, 50, "GB18030");
        this.stTypeId = stTypeId;
    }

	/**
     * 是否是主机
     */
    public BigDecimal getNmIsHost() {
        return this.nmIsHost;
    }
    
    /**
     * 是否是主机
     */
    public String nmIsHost2Html(int precision) {
        if (this.nmIsHost == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIsHost);
        }
    }

    /**
     * 是否是主机
     */
    public void setNmIsHost(BigDecimal nmIsHost) {
        this.nmIsHost = nmIsHost;
    }

	/**
     * 是否有医保制册机
     */
    public BigDecimal getNmYbzc() {
        return this.nmYbzc;
    }
    
    /**
     * 是否有医保制册机
     */
    public String nmYbzc2Html(int precision) {
        if (this.nmYbzc == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmYbzc);
        }
    }

    /**
     * 是否有医保制册机
     */
    public void setNmYbzc(BigDecimal nmYbzc) {
        this.nmYbzc = nmYbzc;
    }

	/**
     * 是否有高拍仪
     */
    public BigDecimal getNmGpy() {
        return this.nmGpy;
    }
    
    /**
     * 是否有高拍仪
     */
    public String nmGpy2Html(int precision) {
        if (this.nmGpy == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmGpy);
        }
    }

    /**
     * 是否有高拍仪
     */
    public void setNmGpy(BigDecimal nmGpy) {
        this.nmGpy = nmGpy;
    }

	/**
     * 是否有居住证签注机
     */
    public BigDecimal getNmJzzqz() {
        return this.nmJzzqz;
    }
    
    /**
     * 是否有居住证签注机
     */
    public String nmJzzqz2Html(int precision) {
        if (this.nmJzzqz == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmJzzqz);
        }
    }

    /**
     * 是否有居住证签注机
     */
    public void setNmJzzqz(BigDecimal nmJzzqz) {
        this.nmJzzqz = nmJzzqz;
    }

	/**
     * 是否有居住证制卡机
     */
    public BigDecimal getNmJzzzk() {
        return this.nmJzzzk;
    }
    
    /**
     * 是否有居住证制卡机
     */
    public String nmJzzzk2Html(int precision) {
        if (this.nmJzzzk == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmJzzzk);
        }
    }

    /**
     * 是否有居住证制卡机
     */
    public void setNmJzzzk(BigDecimal nmJzzzk) {
        this.nmJzzzk = nmJzzzk;
    }
    
	/**
     * 网络情况
     */
    public String getStNetwork() {
        return this.stNetwork;
    }
    
    /**
     * 网络情况
     */
    public String stNetwork2Html() {
        return StringHelper.replaceHTMLSymbol(this.stNetwork);
    }

    /**
     * 网络情况
     */
    public void setStNetwork(String stNetwork) {
        stNetwork = StringUtil.substringBySize(stNetwork, 50, "GB18030");
        this.stNetwork = stNetwork;
    }

	/**
     * 是否有人员值守
     */
    public BigDecimal getNmDuty() {
        return this.nmDuty;
    }
    
    /**
     * 是否有人员值守
     */
    public String nmDuty2Html(int precision) {
        if (this.nmDuty == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDuty);
        }
    }

    /**
     * 是否有人员值守
     */
    public void setNmDuty(BigDecimal nmDuty) {
        this.nmDuty = nmDuty;
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
    
    /**
     * 文件内容
     */
    public byte[] getBlContent() {
        return this.blContent;
    }
    
    /**
     * 文件内容
     */
    public byte[] blContent2Html() {
        return this.blContent;
    }

    /**
     * 文件内容
     */
    public void setBlContent(byte[] blContent) {
        this.blContent = blContent;
    }
    
    /**
     * 是否是24小时设备
     */
    public BigDecimal getNm24Hours() {
        return this.nm24Hours;
    }
    
    /**
     * 是否是24小时设备
     */
    public String nm24Hours2Html(int precision) {
        if (this.nm24Hours == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nm24Hours);
        }
    }

    /**
     * 是否是24小时设备
     */
    public void setNm24Hours(BigDecimal nm24Hours) {
        this.nm24Hours = nm24Hours;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}