package com.wondersgroup.infopub.bean;
		
import java.io.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;

import reindeer.base.utils.StringUtil;
import wfc.service.util.*;

/**
 * 网站统计详细
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ANALYTICS_VISITED_DETAIL")
public class AnalyticsVisitedDetail implements Serializable {
    
    /**
     * 网站统计详细
     */
    public static final String ANALYTICS_VISITED_DETAIL = "ANALYTICS_VISITED_DETAIL";
    
    /**
     * 统计ID
     */
    public static final String ST_DETAIL_ID = "ST_DETAIL_ID";
    
    /**
     * 应用ID
     */
    public static final String ST_APP = "ST_APP";
    
    /**
     * IP地址
     */
    public static final String ST_IP = "ST_IP";
    
    /**
     * 域名
     */
    public static final String ST_DOMAIN = "ST_DOMAIN";
    
    /**
     * URL
     */
    public static final String ST_URL = "ST_URL";
    
    /**
     * 页面标题
     */
    public static final String ST_TITLE = "ST_TITLE";
    
    /**
     * 分辨率宽度
     */
    public static final String ST_SCREEN_W = "ST_SCREEN_W";
    
    /**
     * 分辨率高度
     */
    public static final String ST_SCREEN_H = "ST_SCREEN_H";
    
    /**
     * Referrer
     */
    public static final String ST_REFERRER = "ST_REFERRER";
    
    /**
     * 操作系统
     */
    public static final String ST_OS = "ST_OS";
    
    /**
     * 浏览器名称
     */
    public static final String ST_CLIENT = "ST_CLIENT";
    
    /**
     * 客户端语言
     */
    public static final String ST_LANGUAGE = "ST_LANGUAGE";
    
    /**
     * UA
     */
    public static final String ST_UA = "ST_UA";
    
    /**
     * 访客标识
     */
    public static final String ST_COOKIE = "ST_COOKIE";
    
    /**
     * 业务标识
     */
    public static final String ST_NET_FLAG = "ST_NET_FLAG";
    
    /**
     * 业务子标识
     */
    public static final String ST_NET_SUB_FLAG = "ST_NET_SUB_FLAG";
    
    /**
     * 访问的时间
     */
    public static final String DT_VISITED = "DT_VISITED";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public AnalyticsVisitedDetail() {
    }
    
    /**
     * 统计ID
     */
    @Id
    @Column(name = "ST_DETAIL_ID")
    private String stDetailId;
    
    /**
     * 应用ID
     */
    @Column(name = "ST_APP")
    private String stApp;
    
    /**
     * IP地址
     */
    @Column(name = "ST_IP")
    private String stIp;
    
    /**
     * 域名
     */
    @Column(name = "ST_DOMAIN")
    private String stDomain;
    
    /**
     * URL
     */
    @Column(name = "ST_URL")
    private String stUrl;
    
    /**
     * 页面标题
     */
    @Column(name = "ST_TITLE")
    private String stTitle;
    
    /**
     * 分辨率宽度
     */
    @Column(name = "ST_SCREEN_W")
    private String stScreenW;
    
    /**
     * 分辨率高度
     */
    @Column(name = "ST_SCREEN_H")
    private String stScreenH;
    
    /**
     * Referrer
     */
    @Column(name = "ST_REFERRER")
    private String stReferrer;
    
    /**
     * 操作系统
     */
    @Column(name = "ST_OS")
    private String stOs;
    
    /**
     * 浏览器名称
     */
    @Column(name = "ST_CLIENT")
    private String stClient;
    
    /**
     * 客户端语言
     */
    @Column(name = "ST_LANGUAGE")
    private String stLanguage;
    
    /**
     * UA
     */
    @Column(name = "ST_UA")
    private String stUa;
    
    /**
     * 访客标识
     */
    @Column(name = "ST_COOKIE")
    private String stCookie;
    
    /**
     * 业务标识
     */
    @Column(name = "ST_NET_FLAG")
    private String stNetFlag;
    
    /**
     * 业务子标识
     */
    @Column(name = "ST_NET_SUB_FLAG")
    private String stNetSubFlag;
    
    /**
     * 访问的时间
     */
    @Column(name = "DT_VISITED")
    private String dtVisited;
    
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
     * 统计ID
     */
    public String getStDetailId() {
        return this.stDetailId;
    }
    
    /**
     * 统计ID
     */
    public String stDetailId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDetailId);
    }

    /**
     * 统计ID
     */
    public void setStDetailId(String stDetailId) {
        stDetailId = StringUtil.substringBySize(stDetailId, 50, "GB18030");
        this.stDetailId = stDetailId;
    }
    
	/**
     * 应用ID
     */
    public String getStApp() {
        return this.stApp;
    }
    
    /**
     * 应用ID
     */
    public String stApp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApp);
    }

    /**
     * 应用ID
     */
    public void setStApp(String stApp) {
        stApp = StringUtil.substringBySize(stApp, 50, "GB18030");
        this.stApp = stApp;
    }
    
	/**
     * IP地址
     */
    public String getStIp() {
        return this.stIp;
    }
    
    /**
     * IP地址
     */
    public String stIp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIp);
    }

    /**
     * IP地址
     */
    public void setStIp(String stIp) {
        stIp = StringUtil.substringBySize(stIp, 50, "GB18030");
        this.stIp = stIp;
    }
    
	/**
     * 域名
     */
    public String getStDomain() {
        return this.stDomain;
    }
    
    /**
     * 域名
     */
    public String stDomain2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDomain);
    }

    /**
     * 域名
     */
    public void setStDomain(String stDomain) {
        stDomain = StringUtil.substringBySize(stDomain, 50, "GB18030");
        this.stDomain = stDomain;
    }
    
	/**
     * URL
     */
    public String getStUrl() {
        return this.stUrl;
    }
    
    /**
     * URL
     */
    public String stUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUrl);
    }

    /**
     * URL
     */
    public void setStUrl(String stUrl) {
        stUrl = StringUtil.substringBySize(stUrl, 300, "GB18030");
        this.stUrl = stUrl;
    }
    
	/**
     * 页面标题
     */
    public String getStTitle() {
        return this.stTitle;
    }
    
    /**
     * 页面标题
     */
    public String stTitle2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTitle);
    }

    /**
     * 页面标题
     */
    public void setStTitle(String stTitle) {
        stTitle = StringUtil.substringBySize(stTitle, 50, "GB18030");
        this.stTitle = stTitle;
    }
    
	/**
     * 分辨率宽度
     */
    public String getStScreenW() {
        return this.stScreenW;
    }
    
    /**
     * 分辨率宽度
     */
    public String stScreenW2Html() {
        return StringHelper.replaceHTMLSymbol(this.stScreenW);
    }

    /**
     * 分辨率宽度
     */
    public void setStScreenW(String stScreenW) {
        stScreenW = StringUtil.substringBySize(stScreenW, 50, "GB18030");
        this.stScreenW = stScreenW;
    }
    
	/**
     * 分辨率高度
     */
    public String getStScreenH() {
        return this.stScreenH;
    }
    
    /**
     * 分辨率高度
     */
    public String stScreenH2Html() {
        return StringHelper.replaceHTMLSymbol(this.stScreenH);
    }

    /**
     * 分辨率高度
     */
    public void setStScreenH(String stScreenH) {
        stScreenH = StringUtil.substringBySize(stScreenH, 50, "GB18030");
        this.stScreenH = stScreenH;
    }
    
	/**
     * Referrer
     */
    public String getStReferrer() {
        return this.stReferrer;
    }
    
    /**
     * Referrer
     */
    public String stReferrer2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReferrer);
    }

    /**
     * Referrer
     */
    public void setStReferrer(String stReferrer) {
        stReferrer = StringUtil.substringBySize(stReferrer, 300, "GB18030");
        this.stReferrer = stReferrer;
    }
    
	/**
     * 操作系统
     */
    public String getStOs() {
        return this.stOs;
    }
    
    /**
     * 操作系统
     */
    public String stOs2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOs);
    }

    /**
     * 操作系统
     */
    public void setStOs(String stOs) {
        stOs = StringUtil.substringBySize(stOs, 50, "GB18030");
        this.stOs = stOs;
    }
    
	/**
     * 浏览器名称
     */
    public String getStClient() {
        return this.stClient;
    }
    
    /**
     * 浏览器名称
     */
    public String stClient2Html() {
        return StringHelper.replaceHTMLSymbol(this.stClient);
    }

    /**
     * 浏览器名称
     */
    public void setStClient(String stClient) {
        stClient = StringUtil.substringBySize(stClient, 50, "GB18030");
        this.stClient = stClient;
    }
    
	/**
     * 客户端语言
     */
    public String getStLanguage() {
        return this.stLanguage;
    }
    
    /**
     * 客户端语言
     */
    public String stLanguage2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLanguage);
    }

    /**
     * 客户端语言
     */
    public void setStLanguage(String stLanguage) {
        stLanguage = StringUtil.substringBySize(stLanguage, 50, "GB18030");
        this.stLanguage = stLanguage;
    }
    
	/**
     * UA
     */
    public String getStUa() {
        return this.stUa;
    }
    
    /**
     * UA
     */
    public String stUa2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUa);
    }

    /**
     * UA
     */
    public void setStUa(String stUa) {
        stUa = StringUtil.substringBySize(stUa, 500, "GB18030");
        this.stUa = stUa;
    }
    
	/**
     * 访客标识
     */
    public String getStCookie() {
        return this.stCookie;
    }
    
    /**
     * 访客标识
     */
    public String stCookie2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCookie);
    }

    /**
     * 访客标识
     */
    public void setStCookie(String stCookie) {
        stCookie = StringUtil.substringBySize(stCookie, 50, "GB18030");
        this.stCookie = stCookie;
    }
    
	/**
     * 业务标识
     */
    public String getStNetFlag() {
        return this.stNetFlag;
    }
    
    /**
     * 业务标识
     */
    public String stNetFlag2Html() {
        return StringHelper.replaceHTMLSymbol(this.stNetFlag);
    }

    /**
     * 业务标识
     */
    public void setStNetFlag(String stNetFlag) {
        stNetFlag = StringUtil.substringBySize(stNetFlag, 50, "GB18030");
        this.stNetFlag = stNetFlag;
    }
    
	/**
     * 业务子标识
     */
    public String getStNetSubFlag() {
        return this.stNetSubFlag;
    }
    
    /**
     * 业务子标识
     */
    public String stNetSubFlag2Html() {
        return StringHelper.replaceHTMLSymbol(this.stNetSubFlag);
    }

    /**
     * 业务子标识
     */
    public void setStNetSubFlag(String stNetSubFlag) {
        stNetSubFlag = StringUtil.substringBySize(stNetSubFlag, 50, "GB18030");
        this.stNetSubFlag = stNetSubFlag;
    }
    
	/**
     * 访问的时间
     */
    public String getDtVisited() {
        return this.dtVisited;
    }
    
    /**
     * 访问的时间
     */
    public String dtVisited2Html() {
        return StringHelper.replaceHTMLSymbol(this.dtVisited);
    }

    /**
     * 访问的时间
     */
    public void setDtVisited(String dtVisited) {
        dtVisited = StringUtil.substringBySize(dtVisited, 50, "GB18030");
        this.dtVisited = dtVisited;
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
        stExt2 = StringUtil.substringBySize(stExt2, 100, "GB18030");
        this.stExt2 = stExt2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}