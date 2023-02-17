package com.wondersgroup.api.bean;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 接口
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "APIDOC_INTERFACE")
public class ApidocInterface implements Serializable {
    
    /**
     * 接口
     */
    public static final String APIDOC_INTERFACE = "APIDOC_INTERFACE";
    
    /**
     * 接口ID
     */
    public static final String ST_INTERFACE_ID = "ST_INTERFACE_ID";
    
    /**
     * 接口名
     */
    public static final String ST_INTERFACE_NAME = "ST_INTERFACE_NAME";
    
    /**
     * 接口链接
     */
    public static final String ST_URL = "ST_URL";
    
    /**
     * 接口说明
     */
    public static final String CL_REMARK = "CL_REMARK";
    
    /**
     * 请求方式
     */
    public static final String ST_METHOD = "ST_METHOD";
    
    /**
     * 请求参数说明
     */
    public static final String CL_REQUEST_PARAM = "CL_REQUEST_PARAM";
    
    /**
     * 请求示例
     */
    public static final String CL_REQUEST_EXAM = "CL_REQUEST_EXAM";
    
    /**
     * 返回参数说明
     */
    public static final String CL_RESPONSE_PARAM = "CL_RESPONSE_PARAM";
    
    /**
     * 返回示例
     */
    public static final String CL_RESPONSE_EXAM = "CL_RESPONSE_EXAM";
    
    /**
     * 所属模块ID
     */
    public static final String ST_MODULE_ID = "ST_MODULE_ID";
    
    /**
     * 是否可用
     */
    public static final String NM_STATUS = "NM_STATUS";
    
    /**
     * 排序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 版本号
     */
    public static final String NM_VERSION = "NM_VERSION";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public ApidocInterface() {
    }
    
    /**
     * 接口ID
     */
    @Id
    @Column(name = "ST_INTERFACE_ID")
    private String stInterfaceId;
    
    /**
     * 接口名
     */
    @Column(name = "ST_INTERFACE_NAME")
    private String stInterfaceName;
    
    /**
     * 接口链接
     */
    @Column(name = "ST_URL")
    private String stUrl;
    
    /**
     * 接口说明
     */
    @Lob
    @Column(name = "CL_REMARK")
    private String clRemark;
    
    /**
     * 请求方式
     */
    @Column(name = "ST_METHOD")
    private String stMethod;
    
    /**
     * 请求参数说明
     */
    @Lob
    @Column(name = "CL_REQUEST_PARAM")
    private String clRequestParam;
    
    /**
     * 请求示例
     */
    @Lob
    @Column(name = "CL_REQUEST_EXAM")
    private String clRequestExam;
    
    /**
     * 返回参数说明
     */
    @Lob
    @Column(name = "CL_RESPONSE_PARAM")
    private String clResponseParam;
    
    /**
     * 返回示例
     */
    @Lob
    @Column(name = "CL_RESPONSE_EXAM")
    private String clResponseExam;
    
    /**
     * 所属模块ID
     */
    @Column(name = "ST_MODULE_ID")
    private String stModuleId;
    
    /**
     * 是否可用
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;
    
    /**
     * 排序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
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
     * 版本号
     */
    @Column(name = "NM_VERSION")
    private BigDecimal nmVersion;
    
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
     * 接口ID
     */
    public String getStInterfaceId() {
        return this.stInterfaceId;
    }
    
    /**
     * 接口ID
     */
    public String stInterfaceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInterfaceId);
    }

    /**
     * 接口ID
     */
    public void setStInterfaceId(String stInterfaceId) {
        stInterfaceId = StringUtil.substringBySize(stInterfaceId, 50, "GB18030");
        this.stInterfaceId = stInterfaceId;
    }
    
	/**
     * 接口名
     */
    public String getStInterfaceName() {
        return this.stInterfaceName;
    }
    
    /**
     * 接口名
     */
    public String stInterfaceName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInterfaceName);
    }

    /**
     * 接口名
     */
    public void setStInterfaceName(String stInterfaceName) {
        stInterfaceName = StringUtil.substringBySize(stInterfaceName, 250, "GB18030");
        this.stInterfaceName = stInterfaceName;
    }
    
	/**
     * 接口链接
     */
    public String getStUrl() {
        return this.stUrl;
    }
    
    /**
     * 接口链接
     */
    public String stUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUrl);
    }

    /**
     * 接口链接
     */
    public void setStUrl(String stUrl) {
        stUrl = StringUtil.substringBySize(stUrl, 200, "GB18030");
        this.stUrl = stUrl;
    }
    
	/**
     * 请求方式
     */
    public String getStMethod() {
        return this.stMethod;
    }
    
    /**
     * 请求方式
     */
    public String stMethod2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMethod);
    }

    /**
     * 请求方式
     */
    public void setStMethod(String stMethod) {
        stMethod = StringUtil.substringBySize(stMethod, 50, "GB18030");
        this.stMethod = stMethod;
    }
    
	/**
     * 所属模块ID
     */
    public String getStModuleId() {
        return this.stModuleId;
    }
    
    /**
     * 所属模块ID
     */
    public String stModuleId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stModuleId);
    }

    /**
     * 所属模块ID
     */
    public void setStModuleId(String stModuleId) {
        stModuleId = StringUtil.substringBySize(stModuleId, 200, "GB18030");
        this.stModuleId = stModuleId;
    }

	/**
     * 是否可用
     */
    public BigDecimal getNmStatus() {
        return this.nmStatus;
    }
    
    /**
     * 是否可用
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
     * 是否可用
     */
    public void setNmStatus(BigDecimal nmStatus) {
        this.nmStatus = nmStatus;
    }

	/**
     * 排序号
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序号
     */
    public String nmOrder2Html(int precision) {
        if (this.nmOrder == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOrder);
        }
    }

    /**
     * 排序号
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
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
     * 版本号
     */
    public BigDecimal getNmVersion() {
        return this.nmVersion;
    }
    
    /**
     * 版本号
     */
    public String nmVersion2Html(int precision) {
        if (this.nmVersion == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmVersion);
        }
    }

    /**
     * 版本号
     */
    public void setNmVersion(BigDecimal nmVersion) {
        this.nmVersion = nmVersion;
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

    public String clRemark2Html() {
	    return StringHelper.replaceHTMLSymbol(this.clRemark);
	}
    
	public String getClRemark() {
		return clRemark;
	}

	public void setClRemark(String clRemark) {
		this.clRemark = clRemark;
	}
	
	/**
     * 请求示例
     */
	public String getClRequestExam() {
		return clRequestExam;
	}
	
	public String clRequestExam2Html() {
	    return StringHelper.replaceHTMLSymbol(this.clRequestExam);
	}
	public void setClRequestExam(String clRequestExam) {
		this.clRequestExam = clRequestExam;
	}
	
	/**
	 * 返回参数说明
	 */
	public String getClResponseParam() {
		return clResponseParam;
	}
	
	public String clResponseParam2Html() {
	    return StringHelper.replaceHTMLSymbol(this.clResponseParam);
	}
	
	public void setClResponseParam(String clResponseParam) {
		this.clResponseParam = clResponseParam;
	}
	/**
	 * 返回参数示例
	 */
	public String getClResponseExam() {
		return clResponseExam;
	}

	public String clResponseExam2Html() {
	    return StringHelper.replaceHTMLSymbol(this.clResponseExam);
	}
	
	public void setClResponseExam(String clResponseExam) {
		this.clResponseExam = clResponseExam;
	}

	/**
     * 请求参数
     */
	public String getClRequestParam() {
		return clRequestParam;
	}

	public String clRequestParam2Html() {
	    return StringHelper.replaceHTMLSymbol(this.clRequestParam);
	}
	
	public void setClRequestParam(String clRequestParam) {
		this.clRequestParam = clRequestParam;
	}

}