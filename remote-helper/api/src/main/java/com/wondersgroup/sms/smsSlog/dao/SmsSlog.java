package com.wondersgroup.sms.smsSlog.dao;
		
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
 * 日志表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_SLOG")
public class SmsSlog implements Serializable {
    
    /**
     * 日志表
     */
    public static final String SMS_SLOG = "SMS_SLOG";
    
    /**
     * 日志ID
     */
    public static final String ST_LOG_ID = "ST_LOG_ID";
    
    /**
     * 开始时间
     */
    public static final String DT_START = "DT_START";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 用户名称
     */
    public static final String ST_USER_NAME = "ST_USER_NAME";
    
    /**
     * 请求地址
     */
    public static final String ST_URL = "ST_URL";
    
    /**
     * 业务模块
     */
    public static final String ST_MODULE = "ST_MODULE";
    
    /**
     * 日志类型
     */
    public static final String ST_LOG_TYPE = "ST_LOG_TYPE";
    
    /**
     * 响应状态
     */
    public static final String NM_HTTP_STATUS = "NM_HTTP_STATUS";
    
    /**
     * 时长
     */
    public static final String NM_ELAPSEDTIME = "NM_ELAPSEDTIME";
    
    /**
     * 请求参数
     */
    public static final String ST_REQ_ID = "ST_REQ_ID";
    
    /**
     * 响应参数
     */
    public static final String ST_RES_ID = "ST_RES_ID";
    
    /**
     * 异常信息
     */
    public static final String ST_EXCEPTION_ID = "ST_EXCEPTION_ID";
    
    /**
     * 用户IP
     */
    public static final String ST_IP = "ST_IP";
    
    /**
     * 是否成功
     */
    public static final String NM_SUCCESS = "NM_SUCCESS";
    
    /**
     * 服务器IP
     */
    public static final String ST_SERVER_ID = "ST_SERVER_ID";
    
    /**
     * 浏览器类型
     */
    public static final String ST_BROWSER_TYPE = "ST_BROWSER_TYPE";
    
    /**
     * 结束时间
     */
    public static final String DT_END = "DT_END";
    
    public SmsSlog() {
    }
    
    /**
     * 日志ID
     */
    @Id
    @Column(name = "ST_LOG_ID")
    private String stLogId;
    
    /**
     * 开始时间
     */
    @Column(name = "DT_START")
    private Timestamp dtStart;
    
    /**
     * 用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 用户名称
     */
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 请求地址
     */
    @Column(name = "ST_URL")
    private String stUrl;
    
    /**
     * 业务模块
     */
    @Column(name = "ST_MODULE")
    private String stModule;
    
    /**
     * 日志类型
     */
    @Column(name = "ST_LOG_TYPE")
    private String stLogType;
    
    /**
     * 响应状态
     */
    @Column(name = "NM_HTTP_STATUS")
    private BigDecimal nmHttpStatus;
    
    /**
     * 时长
     */
    @Column(name = "NM_ELAPSEDTIME")
    private BigDecimal nmElapsedtime;
    
    /**
     * 请求参数
     */
    @Column(name = "ST_REQ_ID")
    private String stReqId;
    
    /**
     * 响应参数
     */
    @Column(name = "ST_RES_ID")
    private String stResId;
    
    /**
     * 异常信息
     */
    @Column(name = "ST_EXCEPTION_ID")
    private String stExceptionId;
    
    /**
     * 用户IP
     */
    @Column(name = "ST_IP")
    private String stIp;
    
    /**
     * 是否成功
     */
    @Column(name = "NM_SUCCESS")
    private BigDecimal nmSuccess;
    
    /**
     * 服务器IP
     */
    @Column(name = "ST_SERVER_ID")
    private String stServerId;
    
    /**
     * 浏览器类型
     */
    @Column(name = "ST_BROWSER_TYPE")
    private String stBrowserType;
    
    /**
     * 结束时间
     */
    @Column(name = "DT_END")
    private Timestamp dtEnd;
    
	/**
     * 日志ID
     */
    public String getStLogId() {
        return this.stLogId;
    }
    
    /**
     * 日志ID
     */
    public String stLogId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogId);
    }

    /**
     * 日志ID
     */
    public void setStLogId(String stLogId) {
        stLogId = StringUtil.substringBySize(stLogId, 50, "GB18030");
        this.stLogId = stLogId;
    }

	/**
     * 开始时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtStart() {
        return this.dtStart;
    }
    
    /**
     * 开始时间
     */
    public String dtStart2Html(String pattern) {
        if (this.dtStart == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtStart);
        }
    }

    /**
     * 开始时间
     */
    public void setDtStart(Timestamp dtStart) {
        this.dtStart = dtStart;
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
     * 用户名称
     */
    public String getStUserName() {
        return this.stUserName;
    }
    
    /**
     * 用户名称
     */
    public String stUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserName);
    }

    /**
     * 用户名称
     */
    public void setStUserName(String stUserName) {
        stUserName = StringUtil.substringBySize(stUserName, 50, "GB18030");
        this.stUserName = stUserName;
    }
    
	/**
     * 请求地址
     */
    public String getStUrl() {
        return this.stUrl;
    }
    
    /**
     * 请求地址
     */
    public String stUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUrl);
    }

    /**
     * 请求地址
     */
    public void setStUrl(String stUrl) {
        stUrl = StringUtil.substringBySize(stUrl, 2000, "GB18030");
        this.stUrl = stUrl;
    }
    
	/**
     * 业务模块
     */
    public String getStModule() {
        return this.stModule;
    }
    
    /**
     * 业务模块
     */
    public String stModule2Html() {
        return StringHelper.replaceHTMLSymbol(this.stModule);
    }

    /**
     * 业务模块
     */
    public void setStModule(String stModule) {
        stModule = StringUtil.substringBySize(stModule, 50, "GB18030");
        this.stModule = stModule;
    }
    
	/**
     * 日志类型
     */
    public String getStLogType() {
        return this.stLogType;
    }
    
    /**
     * 日志类型
     */
    public String stLogType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogType);
    }

    /**
     * 日志类型
     */
    public void setStLogType(String stLogType) {
        stLogType = StringUtil.substringBySize(stLogType, 50, "GB18030");
        this.stLogType = stLogType;
    }

	/**
     * 响应状态
     */
    public BigDecimal getNmHttpStatus() {
        return this.nmHttpStatus;
    }
    
    /**
     * 响应状态
     */
    public String nmHttpStatus2Html(int precision) {
        if (this.nmHttpStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmHttpStatus);
        }
    }

    /**
     * 响应状态
     */
    public void setNmHttpStatus(BigDecimal nmHttpStatus) {
        this.nmHttpStatus = nmHttpStatus;
    }

	/**
     * 时长
     */
    public BigDecimal getNmElapsedtime() {
        return this.nmElapsedtime;
    }
    
    /**
     * 时长
     */
    public String nmElapsedtime2Html(int precision) {
        if (this.nmElapsedtime == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmElapsedtime);
        }
    }

    /**
     * 时长
     */
    public void setNmElapsedtime(BigDecimal nmElapsedtime) {
        this.nmElapsedtime = nmElapsedtime;
    }
    
	/**
     * 请求参数
     */
    public String getStReqId() {
        return this.stReqId;
    }
    
    /**
     * 请求参数
     */
    public String stReqId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReqId);
    }

    /**
     * 请求参数
     */
    public void setStReqId(String stReqId) {
        stReqId = StringUtil.substringBySize(stReqId, 200, "GB18030");
        this.stReqId = stReqId;
    }
    
	/**
     * 响应参数
     */
    public String getStResId() {
        return this.stResId;
    }
    
    /**
     * 响应参数
     */
    public String stResId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResId);
    }

    /**
     * 响应参数
     */
    public void setStResId(String stResId) {
        stResId = StringUtil.substringBySize(stResId, 200, "GB18030");
        this.stResId = stResId;
    }
    
	/**
     * 异常信息
     */
    public String getStExceptionId() {
        return this.stExceptionId;
    }
    
    /**
     * 异常信息
     */
    public String stExceptionId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stExceptionId);
    }

    /**
     * 异常信息
     */
    public void setStExceptionId(String stExceptionId) {
        stExceptionId = StringUtil.substringBySize(stExceptionId, 200, "GB18030");
        this.stExceptionId = stExceptionId;
    }
    
	/**
     * 用户IP
     */
    public String getStIp() {
        return this.stIp;
    }
    
    /**
     * 用户IP
     */
    public String stIp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIp);
    }

    /**
     * 用户IP
     */
    public void setStIp(String stIp) {
        stIp = StringUtil.substringBySize(stIp, 50, "GB18030");
        this.stIp = stIp;
    }

	/**
     * 是否成功
     */
    public BigDecimal getNmSuccess() {
        return this.nmSuccess;
    }
    
    /**
     * 是否成功
     */
    public String nmSuccess2Html(int precision) {
        if (this.nmSuccess == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSuccess);
        }
    }

    /**
     * 是否成功
     */
    public void setNmSuccess(BigDecimal nmSuccess) {
        this.nmSuccess = nmSuccess;
    }
    
	/**
     * 服务器IP
     */
    public String getStServerId() {
        return this.stServerId;
    }
    
    /**
     * 服务器IP
     */
    public String stServerId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stServerId);
    }

    /**
     * 服务器IP
     */
    public void setStServerId(String stServerId) {
        stServerId = StringUtil.substringBySize(stServerId, 50, "GB18030");
        this.stServerId = stServerId;
    }
    
	/**
     * 浏览器类型
     */
    public String getStBrowserType() {
        return this.stBrowserType;
    }
    
    /**
     * 浏览器类型
     */
    public String stBrowserType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBrowserType);
    }

    /**
     * 浏览器类型
     */
    public void setStBrowserType(String stBrowserType) {
        stBrowserType = StringUtil.substringBySize(stBrowserType, 200, "GB18030");
        this.stBrowserType = stBrowserType;
    }

	/**
     * 结束时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtEnd() {
        return this.dtEnd;
    }
    
    /**
     * 结束时间
     */
    public String dtEnd2Html(String pattern) {
        if (this.dtEnd == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtEnd);
        }
    }

    /**
     * 结束时间
     */
    public void setDtEnd(Timestamp dtEnd) {
        this.dtEnd = dtEnd;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}