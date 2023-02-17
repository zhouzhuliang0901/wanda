package com.wondersgroup.sms.user.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.*;
import coral.base.util.StringUtil;
import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 用户表
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_USER")

public class SmsUser implements Serializable {
    
    /**
     * 用户表
     */
    public static final String SMS_USER = "SMS_USER";
    
    /**
     * 用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";
    /**
     * 用户ID
     */
    public static final String ST_USER_IDS = "ST_USER_ID[]";
    
    /**
     * 登录名
     */
    public static final String ST_LOGIN_NAME = "ST_LOGIN_NAME";
    
    /**
     * 工号
     */
    public static final String ST_USER_CODE = "ST_USER_CODE";
    
    /**
     * 姓名
     */
    public static final String ST_USER_NAME = "ST_USER_NAME";
    
    /**
     * 密码
     */
    public static final String ST_PASSWORD = "ST_PASSWORD";
    
    /**
     * 拼音
     */
    public static final String ST_PINYIN = "ST_PINYIN";
    
    /**
     * 所属部门
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    /**
     * 账号所属业务单位
     */
    public static final String ST_ORG_NAME = "ST_ORG_NAME";
    
    /**
     * 身份证
     */
    public static final String ST_IDCARD = "ST_IDCARD";
    
    /**
     * 性别
     */
    public static final String ST_SEX = "ST_SEX";
    
    /**
     * 邮箱
     */
    public static final String ST_EMAIL = "ST_EMAIL";
    
    /**
     * 手机
     */
    public static final String ST_MOBILE = "ST_MOBILE";
    
    /**
     * 是否接收系统邮件
     */
    public static final String NM_RECEIVE_EMAIL = "NM_RECEIVE_EMAIL";
    
    /**
     * 界面主题
     */
    public static final String ST_THEME_NAME = "ST_THEME_NAME";
    
    /**
     * 账号是否被锁定
     */
    public static final String NM_LOCKED = "NM_LOCKED";
    
    /**
     * 加密盐
     */
    public static final String ST_SALT = "ST_SALT";
    
    /**
     * 关联拓展用户表
     */
    public static final String ST_EXT_ID = "ST_EXT_ID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 修改时间
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

    /**
     * 所属部门2
     */
    public static final String ST_ORGAN_ID_TWO = "ST_ORGAN_ID_TWO";
    
    public SmsUser() {
    }
    
    /**
     * 用户ID
     */
    @Id
    @Column(name = "ST_USER_ID")
    private String stUserId;
    
    /**
     * 登录名
     */
    @Column(name = "ST_LOGIN_NAME")
    private String stLoginName;
    
    /**
     * 工号
     */
    @Column(name = "ST_USER_CODE")
    private String stUserCode;
    
    /**
     * 姓名
     */
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 密码
     */
    @Column(name = "ST_PASSWORD")
    private String stPassword;
    
    /**
     * 拼音
     */
    @Column(name = "ST_PINYIN")
    private String stPinyin;
    
    /**
     * 所属部门
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 账号所属业务单位
     */
    @Column(name = "ST_ORG_NAME")
    private String stOrgName;
    
    /**
     * 身份证
     */
    @Column(name = "ST_IDCARD")
    private String stIdcard;
    
    /**
     * 性别
     */
    @Column(name = "ST_SEX")
    private BigDecimal stSex;
    
    /**
     * 邮箱
     */
    @Column(name = "ST_EMAIL")
    private String stEmail;
    
    /**
     * 手机
     */
    @Column(name = "ST_MOBILE")
    private String stMobile;
    
    /**
     * 是否接收系统邮件
     */
    @Column(name = "NM_RECEIVE_EMAIL")
    private BigDecimal nmReceiveEmail;
    
    /**
     * 界面主题
     */
    @Column(name = "ST_THEME_NAME")
    private String stThemeName;
    
    /**
     * 账号是否被锁定
     */
    @Column(name = "NM_LOCKED")
    private BigDecimal nmLocked;
    
    /**
     * 加密盐
     */
    @Column(name = "ST_SALT")
    private String stSalt;
    
    /**
     * 关联拓展用户表
     */
    @Column(name = "ST_EXT_ID")
    private String stExtId;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 修改时间
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
     * 所属部门2
     */
    @Column(name = "ST_ORGAN_ID_TWO")
    private String stOrganIdTwo;

    public String getStOrganIdTwo() {
        return stOrganIdTwo;
    }

    public void setStOrganIdTwo(String stOrganIdTwo) {
        this.stOrganIdTwo = stOrganIdTwo;
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
     * 登录名
     */
    public String getStLoginName() {
        return this.stLoginName;
    }
    
    /**
     * 登录名
     */
    public String stLoginName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLoginName);
    }

    /**
     * 登录名
     */
    public void setStLoginName(String stLoginName) {
        stLoginName = StringUtil.substringBySize(stLoginName, 50, "GB18030");
        this.stLoginName = stLoginName;
    }
    
	/**
     * 工号
     */
    public String getStUserCode() {
        return this.stUserCode;
    }
    
    /**
     * 工号
     */
    public String stUserCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserCode);
    }

    /**
     * 工号
     */
    public void setStUserCode(String stUserCode) {
        stUserCode = StringUtil.substringBySize(stUserCode, 50, "GB18030");
        this.stUserCode = stUserCode;
    }
    
	/**
     * 姓名
     */
    public String getStUserName() {
        return this.stUserName;
    }
    
    /**
     * 姓名
     */
    public String stUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserName);
    }

    /**
     * 姓名
     */
    public void setStUserName(String stUserName) {
        stUserName = StringUtil.substringBySize(stUserName, 50, "GB18030");
        this.stUserName = stUserName;
    }
    
	/**
     * 密码
     */
    public String getStPassword() {
        return this.stPassword;
    }
    
    /**
     * 密码
     */
    public String stPassword2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPassword);
    }

    /**
     * 密码
     */
    public void setStPassword(String stPassword) {
        stPassword = StringUtil.substringBySize(stPassword, 50, "GB18030");
        this.stPassword = stPassword;
    }
    
	/**
     * 拼音
     */
    public String getStPinyin() {
        return this.stPinyin;
    }
    
    /**
     * 拼音
     */
    public String stPinyin2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPinyin);
    }

    /**
     * 拼音
     */
    public void setStPinyin(String stPinyin) {
        stPinyin = StringUtil.substringBySize(stPinyin, 50, "GB18030");
        this.stPinyin = stPinyin;
    }
    
	/**
     * 所属部门
     */
    public String getStOrganId() {
        return this.stOrganId;
    }
    
    /**
     * 所属部门
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 所属部门
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
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
     * 账号所属业务单位
     */
    public String getStOrgName() {
        return this.stOrgName;
    }
    
    /**
     * 账号所属业务单位
     */
    public String stOrgName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrgName);
    }

    /**
     * 账号所属业务单位
     */
    public void setStOrgName(String stOrgName) {
        stOrgName = StringUtil.substringBySize(stOrgName, 50, "GB18030");
        this.stOrgName = stOrgName;
    }
    
	/**
     * 身份证
     */
    public String getStIdcard() {
        return this.stIdcard;
    }
    
    /**
     * 身份证
     */
    public String stIdcard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIdcard);
    }

    /**
     * 身份证
     */
    public void setStIdcard(String stIdcard) {
        stIdcard = StringUtil.substringBySize(stIdcard, 50, "GB18030");
        this.stIdcard = stIdcard;
    }
    /**
     * 性别
     */
    public BigDecimal getStSex() {
        return this.stSex;
    }
    
    /**
     * 性别
     */
    public String stSex2Html(int precision) {
        if (this.stSex == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.stSex);
        }
    }

    /**
     * 性别
     */
    public void setStSex(BigDecimal stSex) {
        this.stSex = stSex;
    }
    
	/**
     * 邮箱
     */
    public String getStEmail() {
        return this.stEmail;
    }
    
    /**
     * 邮箱
     */
    public String stEmail2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEmail);
    }

    /**
     * 邮箱
     */
    public void setStEmail(String stEmail) {
        stEmail = StringUtil.substringBySize(stEmail, 50, "GB18030");
        this.stEmail = stEmail;
    }
    
	/**
     * 手机
     */
    public String getStMobile() {
        return this.stMobile;
    }
    
    /**
     * 手机
     */
    public String stMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMobile);
    }

    /**
     * 手机
     */
    public void setStMobile(String stMobile) {
        stMobile = StringUtil.substringBySize(stMobile, 50, "GB18030");
        this.stMobile = stMobile;
    }

	/**
     * 是否接收系统邮件
     */
    public BigDecimal getNmReceiveEmail() {
        return this.nmReceiveEmail;
    }
    
    /**
     * 是否接收系统邮件
     */
    public String nmReceiveEmail2Html(int precision) {
        if (this.nmReceiveEmail == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmReceiveEmail);
        }
    }

    /**
     * 是否接收系统邮件
     */
    public void setNmReceiveEmail(BigDecimal nmReceiveEmail) {
        this.nmReceiveEmail = nmReceiveEmail;
    }
    
	/**
     * 界面主题
     */
    public String getStThemeName() {
        return this.stThemeName;
    }
    
    /**
     * 界面主题
     */
    public String stThemeName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stThemeName);
    }

    /**
     * 界面主题
     */
    public void setStThemeName(String stThemeName) {
        stThemeName = StringUtil.substringBySize(stThemeName, 50, "GB18030");
        this.stThemeName = stThemeName;
    }

	/**
     * 账号是否被锁定
     */
    public BigDecimal getNmLocked() {
        return this.nmLocked;
    }
    
    /**
     * 账号是否被锁定
     */
    public String nmLocked2Html(int precision) {
        if (this.nmLocked == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLocked);
        }
    }

    /**
     * 账号是否被锁定
     */
    public void setNmLocked(BigDecimal nmLocked) {
        this.nmLocked = nmLocked;
    }
    
	/**
     * 加密盐
     */
    public String getStSalt() {
        return this.stSalt;
    }
    
    /**
     * 加密盐
     */
    public String stSalt2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSalt);
    }

    /**
     * 加密盐
     */
    public void setStSalt(String stSalt) {
        stSalt = StringUtil.substringBySize(stSalt, 50, "GB18030");
        this.stSalt = stSalt;
    }
    
	/**
     * 关联拓展用户表
     */
    public String getStExtId() {
        return this.stExtId;
    }
    
    /**
     * 关联拓展用户表
     */
    public String stExtId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stExtId);
    }

    /**
     * 关联拓展用户表
     */
    public void setStExtId(String stExtId) {
        stExtId = StringUtil.substringBySize(stExtId, 50, "GB18030");
        this.stExtId = stExtId;
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
     * 修改时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 修改时间
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
     * 修改时间
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

    /**
     * 事项组名称
     */
    @Column(name = "ST_GROUP_NAME")
    private String stGroupName;

    public String getStGroupName() {
        return stGroupName;
    }

    public void setStGroupName(String stGroupName) {
        this.stGroupName = stGroupName;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}