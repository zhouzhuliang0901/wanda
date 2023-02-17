package com.wondersgroup.app.bean;
		
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
 * OAUTH2认证客户端
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "OAUTH2_CLIENT")
public class Oauth2Client implements Serializable {
    
    /**
     * OAUTH2认证客户端
     */
    public static final String OAUTH2_CLIENT = "OAUTH2_CLIENT";
    
    /**
     * 认证客户端ID
     */
    public static final String ST_OAUTH2_ID = "ST_OAUTH2_ID";
    
    /**
     * 接口用户名
     */
    public static final String ST_INTERFACE_USER = "ST_INTERFACE_USER";
    
    /**
     * 接口密码
     */
    public static final String ST_INTERFACE_PWD = "ST_INTERFACE_PWD";
    
    /**
     * 客户端名称
     */
    public static final String ST_CLIENT_NAME = "ST_CLIENT_NAME";
    
    /**
     * 客户端ID
     */
    public static final String ST_CLIENT_ID = "ST_CLIENT_ID";
    
    /**
     * 客户端安全KEY
     */
    public static final String ST_CLIENT_SECRET = "ST_CLIENT_SECRET";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    public Oauth2Client() {
    }
    
    /**
     * 认证客户端ID
     */
    @Id
    @Column(name = "ST_OAUTH2_ID")
    private String stOauth2Id;
    
    /**
     * 接口用户名
     */
    @Column(name = "ST_INTERFACE_USER")
    private String stInterfaceUser;
    
    /**
     * 接口密码
     */
    @Column(name = "ST_INTERFACE_PWD")
    private String stInterfacePwd;
    
    /**
     * 客户端名称
     */
    @Column(name = "ST_CLIENT_NAME")
    private String stClientName;
    
    /**
     * 客户端ID
     */
    @Column(name = "ST_CLIENT_ID")
    private String stClientId;
    
    /**
     * 客户端安全KEY
     */
    @Column(name = "ST_CLIENT_SECRET")
    private String stClientSecret;
    
    /**
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
	/**
     * 认证客户端ID
     */
    public String getStOauth2Id() {
        return this.stOauth2Id;
    }
    
    /**
     * 认证客户端ID
     */
    public String stOauth2Id2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOauth2Id);
    }

    /**
     * 认证客户端ID
     */
    public void setStOauth2Id(String stOauth2Id) {
        stOauth2Id = StringUtil.substringBySize(stOauth2Id, 50, "GB18030");
        this.stOauth2Id = stOauth2Id;
    }
    
	/**
     * 接口用户名
     */
    public String getStInterfaceUser() {
        return this.stInterfaceUser;
    }
    
    /**
     * 接口用户名
     */
    public String stInterfaceUser2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInterfaceUser);
    }

    /**
     * 接口用户名
     */
    public void setStInterfaceUser(String stInterfaceUser) {
        stInterfaceUser = StringUtil.substringBySize(stInterfaceUser, 50, "GB18030");
        this.stInterfaceUser = stInterfaceUser;
    }
    
	/**
     * 接口密码
     */
    public String getStInterfacePwd() {
        return this.stInterfacePwd;
    }
    
    /**
     * 接口密码
     */
    public String stInterfacePwd2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInterfacePwd);
    }

    /**
     * 接口密码
     */
    public void setStInterfacePwd(String stInterfacePwd) {
        stInterfacePwd = StringUtil.substringBySize(stInterfacePwd, 50, "GB18030");
        this.stInterfacePwd = stInterfacePwd;
    }
    
	/**
     * 客户端名称
     */
    public String getStClientName() {
        return this.stClientName;
    }
    
    /**
     * 客户端名称
     */
    public String stClientName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stClientName);
    }

    /**
     * 客户端名称
     */
    public void setStClientName(String stClientName) {
        stClientName = StringUtil.substringBySize(stClientName, 50, "GB18030");
        this.stClientName = stClientName;
    }
    
	/**
     * 客户端ID
     */
    public String getStClientId() {
        return this.stClientId;
    }
    
    /**
     * 客户端ID
     */
    public String stClientId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stClientId);
    }

    /**
     * 客户端ID
     */
    public void setStClientId(String stClientId) {
        stClientId = StringUtil.substringBySize(stClientId, 50, "GB18030");
        this.stClientId = stClientId;
    }
    
	/**
     * 客户端安全KEY
     */
    public String getStClientSecret() {
        return this.stClientSecret;
    }
    
    /**
     * 客户端安全KEY
     */
    public String stClientSecret2Html() {
        return StringHelper.replaceHTMLSymbol(this.stClientSecret);
    }

    /**
     * 客户端安全KEY
     */
    public void setStClientSecret(String stClientSecret) {
        stClientSecret = StringUtil.substringBySize(stClientSecret, 50, "GB18030");
        this.stClientSecret = stClientSecret;
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}