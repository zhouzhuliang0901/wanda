package com.wondersgroup.infopub.bean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;

import reindeer.base.utils.StringUtil;

import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 大屏统计缓存表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_BIGSCREEN_CACHE")
public class SelmBigscreenCache implements Serializable {
    
    /**
     * 大屏统计缓存表
     */
    public static final String SELM_BIGSCREEN_CACHE = "SELM_BIGSCREEN_CACHE";
    
    /**
     * 缓存ID
     */
    public static final String ST_BIGSCREEN_CACHE_ID = "ST_BIGSCREEN_CACHE_ID";
    
    /**
     * 框架标识
     */
    public static final String ST_FRAME = "ST_FRAME";
    
    /**
     * 一级标识
     */
    public static final String ST_FCODE = "ST_FCODE";
    
    /**
     * 二级标识
     */
    public static final String ST_SCODE = "ST_SCODE";
    
    /**
     * 三级标识
     */
    public static final String ST_TCODE = "ST_TCODE";
    
    /**
     * JSON数据
     */
    public static final String ST_JSON = "ST_JSON";
    
    /**
     * 超大数据
     */
    public static final String ST_CLOB_ID = "ST_CLOB_ID";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";
    
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
    
    public SelmBigscreenCache() {
    }
    
    /**
     * 缓存ID
     */
    @Id
    @Column(name = "ST_BIGSCREEN_CACHE_ID")
    private String stBigscreenCacheId;
    
    /**
     * 框架标识
     */
    @Column(name = "ST_FRAME")
    private String stFrame;
    
    /**
     * 一级标识
     */
    @Column(name = "ST_FCODE")
    private String stFcode;
    
    /**
     * 二级标识
     */
    @Column(name = "ST_SCODE")
    private String stScode;
    
    /**
     * 三级标识
     */
    @Column(name = "ST_TCODE")
    private String stTcode;
    
    /**
     * JSON数据
     */
    @Column(name = "ST_JSON")
    private String stJson;
    
    /**
     * 超大数据
     */
    @Column(name = "ST_CLOB_ID")
    private String stClobId;
    
    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
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
     * 缓存ID
     */
    public String getStBigscreenCacheId() {
        return this.stBigscreenCacheId;
    }
    
    /**
     * 缓存ID
     */
    public String stBigscreenCacheId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBigscreenCacheId);
    }

    /**
     * 缓存ID
     */
    public void setStBigscreenCacheId(String stBigscreenCacheId) {
        stBigscreenCacheId = StringUtil.substringBySize(stBigscreenCacheId, 50, "GB18030");
        this.stBigscreenCacheId = stBigscreenCacheId;
    }
    
	/**
     * 框架标识
     */
    public String getStFrame() {
        return this.stFrame;
    }
    
    /**
     * 框架标识
     */
    public String stFrame2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFrame);
    }

    /**
     * 框架标识
     */
    public void setStFrame(String stFrame) {
        stFrame = StringUtil.substringBySize(stFrame, 50, "GB18030");
        this.stFrame = stFrame;
    }
    
	/**
     * 一级标识
     */
    public String getStFcode() {
        return this.stFcode;
    }
    
    /**
     * 一级标识
     */
    public String stFcode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFcode);
    }

    /**
     * 一级标识
     */
    public void setStFcode(String stFcode) {
        stFcode = StringUtil.substringBySize(stFcode, 50, "GB18030");
        this.stFcode = stFcode;
    }
    
	/**
     * 二级标识
     */
    public String getStScode() {
        return this.stScode;
    }
    
    /**
     * 二级标识
     */
    public String stScode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stScode);
    }

    /**
     * 二级标识
     */
    public void setStScode(String stScode) {
        stScode = StringUtil.substringBySize(stScode, 50, "GB18030");
        this.stScode = stScode;
    }
    
	/**
     * 三级标识
     */
    public String getStTcode() {
        return this.stTcode;
    }
    
    /**
     * 三级标识
     */
    public String stTcode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTcode);
    }

    /**
     * 三级标识
     */
    public void setStTcode(String stTcode) {
        stTcode = StringUtil.substringBySize(stTcode, 50, "GB18030");
        this.stTcode = stTcode;
    }
    
	/**
     * JSON数据
     */
    public String getStJson() {
        return this.stJson;
    }
    
    /**
     * JSON数据
     */
    public String stJson2Html() {
        return StringHelper.replaceHTMLSymbol(this.stJson);
    }

    /**
     * JSON数据
     */
    public void setStJson(String stJson) {
        stJson = StringUtil.substringBySize(stJson, 5000, "GB18030");
        this.stJson = stJson;
    }
    
	/**
     * 超大数据
     */
    public String getStClobId() {
        return this.stClobId;
    }
    
    /**
     * 超大数据
     */
    public String stClobId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stClobId);
    }

    /**
     * 超大数据
     */
    public void setStClobId(String stClobId) {
        stClobId = StringUtil.substringBySize(stClobId, 50, "GB18030");
        this.stClobId = stClobId;
    }

	/**
     * 排序
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序
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
     * 排序
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}