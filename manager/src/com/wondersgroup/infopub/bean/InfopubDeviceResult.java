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
 * 设备状态结果信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_DEVICE_RESULT")
public class InfopubDeviceResult implements Serializable {
    
    /**
     * 设备状态结果信息
     */
    public static final String INFOPUB_DEVICE_RESULT = "INFOPUB_DEVICE_RESULT";
    
    /**
     * 设备状态结果ID
     */
    public static final String ST_DEVICE_RESULT_ID = "ST_DEVICE_RESULT_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 内存使用率
     */
    public static final String NM_MEM_USED = "NM_MEM_USED";
    
    /**
     * CPU使用率
     */
    public static final String NM_CPU_USED = "NM_CPU_USED";
    
    /**
     * 磁盘使用情况
     */
    public static final String CL_HD_USED = "CL_HD_USED";
    
    /**
     * 网络使用情况
     */
    public static final String CL_NET_USED = "CL_NET_USED";
    
    /**
     * 服务使用情况
     */
    public static final String CL_SERVICE_USED = "CL_SERVICE_USED";
    
    /**
     * 创建日期
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public InfopubDeviceResult() {
    }
    
    /**
     * 设备状态结果ID
     */
    @Id
    @Column(name = "ST_DEVICE_RESULT_ID")
    private String stDeviceResultId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 内存使用率
     */
    @Column(name = "NM_MEM_USED")
    private BigDecimal nmMemUsed;
    
    /**
     * CPU使用率
     */
    @Column(name = "NM_CPU_USED")
    private BigDecimal nmCpuUsed;
    
    /**
     * 磁盘使用情况
     */
    @Lob
    @Column(name = "CL_HD_USED")
    private Clob clHdUsed;
    
    /**
     * 网络使用情况
     */
    @Lob
    @Column(name = "CL_NET_USED")
    private Clob clNetUsed;
    
    /**
     * 服务使用情况
     */
    @Lob
    @Column(name = "CL_SERVICE_USED")
    private Clob clServiceUsed;
    
    /**
     * 创建日期
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
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
     * 设备状态结果ID
     */
    public String getStDeviceResultId() {
        return this.stDeviceResultId;
    }
    
    /**
     * 设备状态结果ID
     */
    public String stDeviceResultId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceResultId);
    }

    /**
     * 设备状态结果ID
     */
    public void setStDeviceResultId(String stDeviceResultId) {
        stDeviceResultId = StringUtil.substringBySize(stDeviceResultId, 50, "GB18030");
        this.stDeviceResultId = stDeviceResultId;
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
     * 内存使用率
     */
    public BigDecimal getNmMemUsed() {
        return this.nmMemUsed;
    }
    
    /**
     * 内存使用率
     */
    public String nmMemUsed2Html(int precision) {
        if (this.nmMemUsed == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmMemUsed);
        }
    }

    /**
     * 内存使用率
     */
    public void setNmMemUsed(BigDecimal nmMemUsed) {
        this.nmMemUsed = nmMemUsed;
    }

	/**
     * CPU使用率
     */
    public BigDecimal getNmCpuUsed() {
        return this.nmCpuUsed;
    }
    
    /**
     * CPU使用率
     */
    public String nmCpuUsed2Html(int precision) {
        if (this.nmCpuUsed == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCpuUsed);
        }
    }

    /**
     * CPU使用率
     */
    public void setNmCpuUsed(BigDecimal nmCpuUsed) {
        this.nmCpuUsed = nmCpuUsed;
    }
    
    

	/**
     * 创建日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtCreate() {
        return this.dtCreate;
    }
    
    /**
     * 创建日期
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
     * 创建日期
     */
    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
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
    

    public Clob getClHdUsed() {
		return clHdUsed;
	}

	public void setClHdUsed(Clob clHdUsed) {
		this.clHdUsed = clHdUsed;
	}
	 public Clob getClNetUsed() {
		return clNetUsed;
	}

	public void setClNetUsed(Clob clNetUsed) {
		this.clNetUsed = clNetUsed;
	}
	 public Clob getClServiceUsed() {
		return clServiceUsed;
	}

	public void setClServiceUsed(Clob clServiceUsed) {
		this.clServiceUsed = clServiceUsed;
	}
		

	public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}