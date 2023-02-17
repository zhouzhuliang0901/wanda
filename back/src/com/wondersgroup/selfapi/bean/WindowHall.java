package com.wondersgroup.selfapi.bean;
		
import java.io.*;
import java.math.*;
import java.text.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;
import reindeer.base.utils.StringUtil;
import wfc.service.util.*;

/**
 * 大厅标识
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_HALL")
public class WindowHall implements Serializable {
    
    /**
     * 大厅标识
     */
    public static final String WINDOW_HALL = "WINDOW_HALL";
    
    /**
     * 大厅ID
     */
    public static final String ST_HALL_ID = "ST_HALL_ID";
    
    /**
     * 大厅代码
     */
    public static final String ST_HALL_CODE = "ST_HALL_CODE";
    
    /**
     * 大厅首字母
     */
    public static final String ST_ALPHA = "ST_ALPHA";
    
    /**
     * 大厅全称
     */
    public static final String ST_FULL_NAME = "ST_FULL_NAME";
    
    /**
     * 大厅简称
     */
    public static final String ST_NAME = "ST_NAME";
    
    /**
     * 是否是大厅
     */
    public static final String NM_HALL = "NM_HALL";
    
    /**
     * 序号
     */
    public static final String NM_ORDER = "NM_ORDER";
    
    /**
     * 大厅地址信息
     */
    public static final String ST_ADDRESS = "ST_ADDRESS";
    
    /**
     * 大厅描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 办理点ID
     */
    public static final String ST_PLACE_ID = "ST_PLACE_ID";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public WindowHall() {
    }
    
    /**
     * 大厅ID
     */
    @Id
    @Column(name = "ST_HALL_ID")
    private String stHallId;
    
    /**
     * 大厅代码
     */
    @Column(name = "ST_HALL_CODE")
    private String stHallCode;
    
    /**
     * 大厅首字母
     */
    @Column(name = "ST_ALPHA")
    private String stAlpha;
    
    /**
     * 大厅全称
     */
    @Column(name = "ST_FULL_NAME")
    private String stFullName;
    
    /**
     * 大厅简称
     */
    @Column(name = "ST_NAME")
    private String stName;
    
    /**
     * 是否是大厅
     */
    @Column(name = "NM_HALL")
    private BigDecimal nmHall;
    
    /**
     * 序号
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
    /**
     * 大厅地址信息
     */
    @Column(name = "ST_ADDRESS")
    private String stAddress;
    
    /**
     * 大厅描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 办理点ID
     */
    @Column(name = "ST_PLACE_ID")
    private String stPlaceId;
    
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
     * 大厅ID
     */
    public String getStHallId() {
        return this.stHallId;
    }
    
    /**
     * 大厅ID
     */
    public String stHallId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stHallId);
    }

    /**
     * 大厅ID
     */
    public void setStHallId(String stHallId) {
        stHallId = StringUtil.substringBySize(stHallId, 50, "GB18030");
        this.stHallId = stHallId;
    }
    
	/**
     * 大厅代码
     */
    public String getStHallCode() {
        return this.stHallCode;
    }
    
    /**
     * 大厅代码
     */
    public String stHallCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stHallCode);
    }

    /**
     * 大厅代码
     */
    public void setStHallCode(String stHallCode) {
        stHallCode = StringUtil.substringBySize(stHallCode, 50, "GB18030");
        this.stHallCode = stHallCode;
    }
    
	/**
     * 大厅首字母
     */
    public String getStAlpha() {
        return this.stAlpha;
    }
    
    /**
     * 大厅首字母
     */
    public String stAlpha2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAlpha);
    }

    /**
     * 大厅首字母
     */
    public void setStAlpha(String stAlpha) {
        stAlpha = StringUtil.substringBySize(stAlpha, 1, "GB18030");
        this.stAlpha = stAlpha;
    }
    
	/**
     * 大厅全称
     */
    public String getStFullName() {
        return this.stFullName;
    }
    
    /**
     * 大厅全称
     */
    public String stFullName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFullName);
    }

    /**
     * 大厅全称
     */
    public void setStFullName(String stFullName) {
        stFullName = StringUtil.substringBySize(stFullName, 50, "GB18030");
        this.stFullName = stFullName;
    }
    
	/**
     * 大厅简称
     */
    public String getStName() {
        return this.stName;
    }
    
    /**
     * 大厅简称
     */
    public String stName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stName);
    }

    /**
     * 大厅简称
     */
    public void setStName(String stName) {
        stName = StringUtil.substringBySize(stName, 50, "GB18030");
        this.stName = stName;
    }

	/**
     * 是否是大厅
     */
    public BigDecimal getNmHall() {
        return this.nmHall;
    }
    
    /**
     * 是否是大厅
     */
    public String nmHall2Html(int precision) {
        if (this.nmHall == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmHall);
        }
    }

    /**
     * 是否是大厅
     */
    public void setNmHall(BigDecimal nmHall) {
        this.nmHall = nmHall;
    }

	/**
     * 序号
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 序号
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
     * 序号
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
    }
    
	/**
     * 大厅地址信息
     */
    public String getStAddress() {
        return this.stAddress;
    }
    
    /**
     * 大厅地址信息
     */
    public String stAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAddress);
    }

    /**
     * 大厅地址信息
     */
    public void setStAddress(String stAddress) {
        stAddress = StringUtil.substringBySize(stAddress, 50, "GB18030");
        this.stAddress = stAddress;
    }
    
	/**
     * 大厅描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 大厅描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 大厅描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }
    
	public String getStPlaceId() {
		return stPlaceId;
	}

    public String stPlaceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPlaceId);
    }

	public void setStPlaceId(String stPlaceId) {
	    stPlaceId = StringUtil.substringBySize(stPlaceId, 50, "GB18030");
	    this.stPlaceId = stPlaceId;
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
        stExt2 = StringUtil.substringBySize(stExt2, 200, "GB18030");
        this.stExt2 = stExt2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}