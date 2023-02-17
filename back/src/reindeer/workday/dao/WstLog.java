package reindeer.workday.dao;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import reindeer.base.utils.StringUtil;

/**
 * 应用日志
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WST_LOG")
public class WstLog implements Serializable {
    
    /**
     * 应用日志
     */
    public static final String WST_LOG = "WST_LOG";
    
    /**
     * 日志编号
     */
    public static final String ST_LOG_ID = "ST_LOG_ID";
    
    /**
     * 日志级别
     */
    public static final String NM_LOG_LEVEL = "NM_LOG_LEVEL";
    
    /**
     * 功能
     */
    public static final String ST_LOG_FUNCTION = "ST_LOG_FUNCTION";
    
    /**
     * 操作
     */
    public static final String ST_LOG_OPERATION = "ST_LOG_OPERATION";
    
    /**
     * 操作对象
     */
    public static final String ST_LOG_OBJECT = "ST_LOG_OBJECT";
    
    /**
     * 操作结果
     */
    public static final String ST_LOG_RESULT = "ST_LOG_RESULT";
    
    /**
     * 描述
     */
    public static final String ST_LOG_DESC = "ST_LOG_DESC";
    
    /**
     * 操作时间
     */
    public static final String DT_LOG_TIME = "DT_LOG_TIME";
    
    /**
     * 操作用户编号
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    
    /**
     * 操作用户帐号
     */
    public static final String ST_USER_ACCOUNT = "ST_USER_ACCOUNT";
    
    /**
     * 操作用户姓名
     */
    public static final String ST_USER_NAME = "ST_USER_NAME";
    
    /**
     * 操作用户IP地址
     */
    public static final String ST_USER_IP = "ST_USER_IP";
    
    public WstLog() {
    }
    
    /**
     * 日志编号
     */
    @Id
    @Column(name = "ST_LOG_ID")
    private String stLogId;
    
    /**
     * 日志级别
     */
    @Column(name = "NM_LOG_LEVEL")
    private BigDecimal nmLogLevel;
    
    /**
     * 功能
     */
    @Column(name = "ST_LOG_FUNCTION")
    private String stLogFunction;
    
    /**
     * 操作
     */
    @Column(name = "ST_LOG_OPERATION")
    private String stLogOperation;
    
    /**
     * 操作对象
     */
    @Column(name = "ST_LOG_OBJECT")
    private String stLogObject;
    
    /**
     * 操作结果
     */
    @Column(name = "ST_LOG_RESULT")
    private String stLogResult;
    
    /**
     * 描述
     */
    @Column(name = "ST_LOG_DESC")
    private String stLogDesc;
    
    /**
     * 操作时间
     */
    @Column(name = "DT_LOG_TIME")
    private Timestamp dtLogTime;
    
    /**
     * 操作用户编号
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 操作用户帐号
     */
    @Column(name = "ST_USER_ACCOUNT")
    private String stUserAccount;
    
    /**
     * 操作用户姓名
     */
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 操作用户IP地址
     */
    @Column(name = "ST_USER_IP")
    private String stUserIp;
    
	/**
     * 日志编号
     */
    public String getStLogId() {
        return this.stLogId;
    }
    
    /**
     * 日志编号
     */
    public String stLogId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogId);
    }

    /**
     * 日志编号
     */
    public void setStLogId(String stLogId) {
        stLogId = StringUtil.substringBySize(stLogId, 50);
        this.stLogId = stLogId;
    }

	/**
     * 日志级别
     */
    public BigDecimal getNmLogLevel() {
        return this.nmLogLevel;
    }
    
    /**
     * 日志级别
     */
    public String nmLogLevel2Html(int precision) {
        if (this.nmLogLevel == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLogLevel);
        }
    }

    /**
     * 日志级别
     */
    public void setNmLogLevel(BigDecimal nmLogLevel) {
        this.nmLogLevel = nmLogLevel;
    }
    
	/**
     * 功能
     */
    public String getStLogFunction() {
        return this.stLogFunction;
    }
    
    /**
     * 功能
     */
    public String stLogFunction2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogFunction);
    }

    /**
     * 功能
     */
    public void setStLogFunction(String stLogFunction) {
        stLogFunction = StringUtil.substringBySize(stLogFunction, 100);
        this.stLogFunction = stLogFunction;
    }
    
	/**
     * 操作
     */
    public String getStLogOperation() {
        return this.stLogOperation;
    }
    
    /**
     * 操作
     */
    public String stLogOperation2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogOperation);
    }

    /**
     * 操作
     */
    public void setStLogOperation(String stLogOperation) {
        stLogOperation = StringUtil.substringBySize(stLogOperation, 100);
        this.stLogOperation = stLogOperation;
    }
    
	/**
     * 操作对象
     */
    public String getStLogObject() {
        return this.stLogObject;
    }
    
    /**
     * 操作对象
     */
    public String stLogObject2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogObject);
    }

    /**
     * 操作对象
     */
    public void setStLogObject(String stLogObject) {
        stLogObject = StringUtil.substringBySize(stLogObject, 100);
        this.stLogObject = stLogObject;
    }
    
	/**
     * 操作结果
     */
    public String getStLogResult() {
        return this.stLogResult;
    }
    
    /**
     * 操作结果
     */
    public String stLogResult2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogResult);
    }

    /**
     * 操作结果
     */
    public void setStLogResult(String stLogResult) {
        stLogResult = StringUtil.substringBySize(stLogResult, 20);
        this.stLogResult = stLogResult;
    }
    
	/**
     * 描述
     */
    public String getStLogDesc() {
        return this.stLogDesc;
    }
    
    /**
     * 描述
     */
    public String stLogDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLogDesc);
    }

    /**
     * 描述
     */
    public void setStLogDesc(String stLogDesc) {
        stLogDesc = StringUtil.substringBySize(stLogDesc, 2000);
        this.stLogDesc = stLogDesc;
    }

	/**
     * 操作时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtLogTime() {
        return this.dtLogTime;
    }
    
    /**
     * 操作时间
     */
    public String dtLogTime2Html(String pattern) {
        if (this.dtLogTime == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtLogTime);
        }
    }

    /**
     * 操作时间
     */
    public void setDtLogTime(Timestamp dtLogTime) {
        this.dtLogTime = dtLogTime;
    }
    
	/**
     * 操作用户编号
     */
    public String getStUserId() {
        return this.stUserId;
    }
    
    /**
     * 操作用户编号
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 操作用户编号
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50);
        this.stUserId = stUserId;
    }
    
	/**
     * 操作用户帐号
     */
    public String getStUserAccount() {
        return this.stUserAccount;
    }
    
    /**
     * 操作用户帐号
     */
    public String stUserAccount2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserAccount);
    }

    /**
     * 操作用户帐号
     */
    public void setStUserAccount(String stUserAccount) {
        stUserAccount = StringUtil.substringBySize(stUserAccount, 50);
        this.stUserAccount = stUserAccount;
    }
    
	/**
     * 操作用户姓名
     */
    public String getStUserName() {
        return this.stUserName;
    }
    
    /**
     * 操作用户姓名
     */
    public String stUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserName);
    }

    /**
     * 操作用户姓名
     */
    public void setStUserName(String stUserName) {
        stUserName = StringUtil.substringBySize(stUserName, 200);
        this.stUserName = stUserName;
    }
    
	/**
     * 操作用户IP地址
     */
    public String getStUserIp() {
        return this.stUserIp;
    }
    
    /**
     * 操作用户IP地址
     */
    public String stUserIp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserIp);
    }

    /**
     * 操作用户IP地址
     */
    public void setStUserIp(String stUserIp) {
        stUserIp = StringUtil.substringBySize(stUserIp, 50);
        this.stUserIp = stUserIp;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}