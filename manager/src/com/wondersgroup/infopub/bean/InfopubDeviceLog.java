package com.wondersgroup.infopub.bean;
		
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 设备日志记录
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_DEVICE_LOG")
public class InfopubDeviceLog implements Serializable {
    
    /**
     * 设备日志记录
     */
    public static final String INFOPUB_DEVICE_LOG = "INFOPUB_DEVICE_LOG";
    
    /**
     * 设备日志ID
     */
    public static final String ST_DEVICE_LOG_ID = "ST_DEVICE_LOG_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_DEVICE_ID = "ST_DEVICE_ID";
    
    /**
     * 线程号
     */
    public static final String ST_THREAD = "ST_THREAD";
    
    /**
     * 日志级别
     */
    public static final String ST_LEVEL = "ST_LEVEL";
    
    /**
     * 日志记录类名称
     */
    public static final String ST_LOGGER = "ST_LOGGER";
    
    /**
     * 操作者
     */
    public static final String ST_OPERATOR = "ST_OPERATOR";
    
    /**
     * 操作对象
     */
    public static final String ST_OPERAND = "ST_OPERAND";
    
    /**
     * 动作类型
     */
    public static final String ST_ACTION = "ST_ACTION";
    
    /**
     * 记录位置
     */
    public static final String ST_LOCATION = "ST_LOCATION";
    
    /**
     * 行号
     */
    public static final String ST_LINE = "ST_LINE";
    
    /**
     * 方法
     */
    public static final String ST_METHOD = "ST_METHOD";
    
    /**
     * 日志消息
     */
    public static final String ST_MSG = "ST_MSG";
    
    /**
     * 异常信息
     */
    public static final String ST_EXCEPTION = "ST_EXCEPTION";
    
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
    
    public InfopubDeviceLog() {
    	this.setStDeviceLogId(UUID.randomUUID().toString());
    	this.setDtCreate(new Timestamp(System.currentTimeMillis()));
    	this.setStLevel("info");
    }
    
    /**
     * 设备日志ID
     */
    @Id
    @Column(name = "ST_DEVICE_LOG_ID")
    private String stDeviceLogId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_DEVICE_ID")
    private String stDeviceId;
    
    /**
     * 线程号
     */
    @Column(name = "ST_THREAD")
    private String stThread;
    
    /**
     * 日志级别
     */
    @Column(name = "ST_LEVEL")
    private String stLevel;
    
    /**
     * 日志记录类名称
     */
    @Column(name = "ST_LOGGER")
    private String stLogger;
    
    /**
     * 操作者
     */
    @Column(name = "ST_OPERATOR")
    private String stOperator;
    
    /**
     * 操作对象
     */
    @Column(name = "ST_OPERAND")
    private String stOperand;
    
    /**
     * 动作类型
     */
    @Column(name = "ST_ACTION")
    private String stAction;
    
    /**
     * 记录位置
     */
    @Column(name = "ST_LOCATION")
    private String stLocation;
    
    /**
     * 行号
     */
    @Column(name = "ST_LINE")
    private String stLine;
    
    /**
     * 方法
     */
    @Column(name = "ST_METHOD")
    private String stMethod;
    
    /**
     * 日志消息
     */
    @Column(name = "ST_MSG")
    private String stMsg;
    
    /**
     * 异常信息
     */
    @Column(name = "ST_EXCEPTION")
    private String stException;
    
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
     * 设备日志ID
     */
    public String getStDeviceLogId() {
        return this.stDeviceLogId;
    }
    
    /**
     * 设备日志ID
     */
    public String stDeviceLogId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDeviceLogId);
    }

    /**
     * 设备日志ID
     */
    public void setStDeviceLogId(String stDeviceLogId) {
        stDeviceLogId = StringUtil.substringBySize(stDeviceLogId, 50, "GB18030");
        this.stDeviceLogId = stDeviceLogId;
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
     * 线程号
     */
    public String getStThread() {
        return this.stThread;
    }
    
    /**
     * 线程号
     */
    public String stThread2Html() {
        return StringHelper.replaceHTMLSymbol(this.stThread);
    }

    /**
     * 线程号
     */
    public void setStThread(String stThread) {
        stThread = StringUtil.substringBySize(stThread, 50, "GB18030");
        this.stThread = stThread;
    }
    
	/**
     * 日志级别
     */
    public String getStLevel() {
        return this.stLevel;
    }
    
    /**
     * 日志级别
     */
    public String stLevel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLevel);
    }

    /**
     * 日志级别
     */
    public void setStLevel(String stLevel) {
        stLevel = StringUtil.substringBySize(stLevel, 50, "GB18030");
        this.stLevel = stLevel;
    }
    
	/**
     * 日志记录类名称
     */
    public String getStLogger() {
        return this.stLogger;
    }
    
    /**
     * 日志记录类名称
     */
    public String stLogger2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogger);
    }

    /**
     * 日志记录类名称
     */
    public void setStLogger(String stLogger) {
        stLogger = StringUtil.substringBySize(stLogger, 100, "GB18030");
        this.stLogger = stLogger;
    }
    
	/**
     * 操作者
     */
    public String getStOperator() {
        return this.stOperator;
    }
    
    /**
     * 操作者
     */
    public String stOperator2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOperator);
    }

    /**
     * 操作者
     */
    public void setStOperator(String stOperator) {
        stOperator = StringUtil.substringBySize(stOperator, 50, "GB18030");
        this.stOperator = stOperator;
    }
    
	/**
     * 操作对象
     */
    public String getStOperand() {
        return this.stOperand;
    }
    
    /**
     * 操作对象
     */
    public String stOperand2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOperand);
    }

    /**
     * 操作对象
     */
    public void setStOperand(String stOperand) {
        stOperand = StringUtil.substringBySize(stOperand, 50, "GB18030");
        this.stOperand = stOperand;
    }
    
	/**
     * 动作类型
     */
    public String getStAction() {
        return this.stAction;
    }
    
    /**
     * 动作类型
     */
    public String stAction2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAction);
    }

    /**
     * 动作类型
     */
    public void setStAction(String stAction) {
        stAction = StringUtil.substringBySize(stAction, 50, "GB18030");
        this.stAction = stAction;
    }
    
	/**
     * 记录位置
     */
    public String getStLocation() {
        return this.stLocation;
    }
    
    /**
     * 记录位置
     */
    public String stLocation2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLocation);
    }

    /**
     * 记录位置
     */
    public void setStLocation(String stLocation) {
        stLocation = StringUtil.substringBySize(stLocation, 100, "GB18030");
        this.stLocation = stLocation;
    }
    
	/**
     * 行号
     */
    public String getStLine() {
        return this.stLine;
    }
    
    /**
     * 行号
     */
    public String stLine2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLine);
    }

    /**
     * 行号
     */
    public void setStLine(String stLine) {
        stLine = StringUtil.substringBySize(stLine, 50, "GB18030");
        this.stLine = stLine;
    }
    
	/**
     * 方法
     */
    public String getStMethod() {
        return this.stMethod;
    }
    
    /**
     * 方法
     */
    public String stMethod2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMethod);
    }

    /**
     * 方法
     */
    public void setStMethod(String stMethod) {
        stMethod = StringUtil.substringBySize(stMethod, 50, "GB18030");
        this.stMethod = stMethod;
    }
    
	/**
     * 日志消息
     */
    public String getStMsg() {
        return this.stMsg;
    }
    
    /**
     * 日志消息
     */
    public String stMsg2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMsg);
    }

    /**
     * 日志消息
     */
    public void setStMsg(String stMsg) {
        stMsg = StringUtil.substringBySize(stMsg, 1000, "GB18030");
        this.stMsg = stMsg;
    }
    
	/**
     * 异常信息
     */
    public String getStException() {
        return this.stException;
    }
    
    /**
     * 异常信息
     */
    public String stException2Html() {
        return StringHelper.replaceHTMLSymbol(this.stException);
    }

    /**
     * 异常信息
     */
    public void setStException(String stException) {
        stException = StringUtil.substringBySize(stException, 1000, "GB18030");
        this.stException = stException;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}