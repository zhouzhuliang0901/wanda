package com.wondersgroup.infopub.bean;
		
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
 * 外设状态结果信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_ODEVICE_RESULT")
public class InfopubOdeviceResult implements Serializable {
    
    /**
     * 外设状态结果信息
     */
    public static final String INFOPUB_ODEVICE_RESULT = "INFOPUB_ODEVICE_RESULT";
    
    /**
     * 外设状态结果ID
     */
    public static final String ST_OUT_DEVICE_RESULT_ID = "ST_OUT_DEVICE_RESULT_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 外设标识
     */
    public static final String ST_OUT_DEVICE_CODE = "ST_OUT_DEVICE_CODE";
    
    /**
     * 是否异常
     */
    public static final String NM_EXCEPTION = "NM_EXCEPTION";
    
    /**
     * 异常原因
     */
    public static final String ST_CAUSE = "ST_CAUSE";
    
    /**
     * 是否已经通知
     */
    public static final String NM_NOTICE = "NM_NOTICE";
    
    /**
     * 总量
     */
    public static final String NM_TOTAL = "NM_TOTAL";
    
    /**
     * 剩余量
     */
    public static final String NM_REMAIN = "NM_REMAIN";
    
    /**
     * 更新日期
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
    
    public InfopubOdeviceResult() {
    }
    
    /**
     * 外设状态结果ID
     */
    @Id
    @Column(name = "ST_OUT_DEVICE_RESULT_ID")
    private String stOutDeviceResultId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 外设标识
     */
    @Column(name = "ST_OUT_DEVICE_CODE")
    private String stOutDeviceCode;
    
    /**
     * 是否异常
     */
    @Column(name = "NM_EXCEPTION")
    private BigDecimal nmException;
    
    /**
     * 异常原因
     */
    @Column(name = "ST_CAUSE")
    private String stCause;
    
    /**
     * 是否已经通知
     */
    @Column(name = "NM_NOTICE")
    private BigDecimal nmNotice;
    
    /**
     * 总量
     */
    @Column(name = "NM_TOTAL")
    private BigDecimal nmTotal;
    
    /**
     * 剩余量
     */
    @Column(name = "NM_REMAIN")
    private BigDecimal nmRemain;
    
    /**
     * 更新日期
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
     * 外设状态结果ID
     */
    public String getStOutDeviceResultId() {
        return this.stOutDeviceResultId;
    }
    
    /**
     * 外设状态结果ID
     */
    public String stOutDeviceResultId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOutDeviceResultId);
    }

    /**
     * 外设状态结果ID
     */
    public void setStOutDeviceResultId(String stOutDeviceResultId) {
        stOutDeviceResultId = StringUtil.substringBySize(stOutDeviceResultId, 50, "GB18030");
        this.stOutDeviceResultId = stOutDeviceResultId;
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
     * 外设标识
     */
    public String getStOutDeviceCode() {
        return this.stOutDeviceCode;
    }
    
    /**
     * 外设标识
     */
    public String stOutDeviceCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOutDeviceCode);
    }

    /**
     * 外设标识
     */
    public void setStOutDeviceCode(String stOutDeviceCode) {
        stOutDeviceCode = StringUtil.substringBySize(stOutDeviceCode, 50, "GB18030");
        this.stOutDeviceCode = stOutDeviceCode;
    }

	/**
     * 是否异常
     */
    public BigDecimal getNmException() {
        return this.nmException;
    }
    
    /**
     * 是否异常
     */
    public String nmException2Html(int precision) {
        if (this.nmException == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmException);
        }
    }

    /**
     * 是否异常
     */
    public void setNmException(BigDecimal nmException) {
        this.nmException = nmException;
    }
    
	/**
     * 异常原因
     */
    public String getStCause() {
        return this.stCause;
    }
    
    /**
     * 异常原因
     */
    public String stCause2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCause);
    }

    /**
     * 异常原因
     */
    public void setStCause(String stCause) {
        stCause = StringUtil.substringBySize(stCause, 200, "GB18030");
        this.stCause = stCause;
    }

	/**
     * 是否已经通知
     */
    public BigDecimal getNmNotice() {
        return this.nmNotice;
    }
    
    /**
     * 是否已经通知
     */
    public String nmNotice2Html(int precision) {
        if (this.nmNotice == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmNotice);
        }
    }

    /**
     * 是否已经通知
     */
    public void setNmNotice(BigDecimal nmNotice) {
        this.nmNotice = nmNotice;
    }

	/**
     * 总量
     */
    public BigDecimal getNmTotal() {
        return this.nmTotal;
    }
    
    /**
     * 总量
     */
    public String nmTotal2Html(int precision) {
        if (this.nmTotal == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmTotal);
        }
    }

    /**
     * 总量
     */
    public void setNmTotal(BigDecimal nmTotal) {
        this.nmTotal = nmTotal;
    }

	/**
     * 剩余量
     */
    public BigDecimal getNmRemain() {
        return this.nmRemain;
    }
    
    /**
     * 剩余量
     */
    public String nmRemain2Html(int precision) {
        if (this.nmRemain == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmRemain);
        }
    }

    /**
     * 剩余量
     */
    public void setNmRemain(BigDecimal nmRemain) {
        this.nmRemain = nmRemain;
    }

	/**
     * 更新日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 更新日期
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
     * 更新日期
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