package com.wondersgroup.assistant.readCardInfo.dao;

import java.io.*;
import javax.persistence.*;

import org.apache.commons.lang.builder.*;
import coral.base.util.StringUtil;
import wfc.service.util.*;

/**
 * SSCard
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SSCard")
public class Sscard implements Serializable {
    
    /**
     * SSCard
     */
    public static final String SSCard = "SSCard";
    
    /**
     * 证件类型
     */
    public static final String Zzlx = "Zzlx";
    
    /**
     * 姓名
     */
    public static final String Name = "Name";
    
    /**
     * 性别
     */
    public static final String Sex = "Sex";
    
    /**
     * 民族
     */
    public static final String Nation = "Nation";
    
    /**
     * 出生日期
     */
    public static final String Birthday = "Birthday";
    
    /**
     * 证件号
     */
    public static final String IdCode = "IdCode";
    
    /**
     * 有效期始
     */
    public static final String StartDate = "StartDate";
    
    /**
     * 有效期止
     */
    public static final String EndDate = "EndDate";
    
    /**
     * 地址
     */
    public static final String Address = "Address";
    
    /**
     * 卡号
     */
    public static final String CardNo = "CardNo";
    
    /**
     * 卡号别码
     */
    public static final String CardIDN = "CardIDN";
    
    /**
     * 联系电话
     */
    public static final String Phone = "Phone";
    
    /**
     * 发卡地区行政划代码
     */
    public static final String CardCity = "CardCity";
    
    /**
     * 备用字段1
     */
//    public static final String BEIYONG1 = "BEIYONG1";
    public static final String CardVer = "CardVer";

    /**
     * 备用字段2
     */
    public static final String BEIYONG2 = "BEIYONG2";
    @Id
    private Long id;

    public Sscard() {
    }
    
    /**
     * 证件类型
     */
    @Column(name = "Zzlx")
    private String zzlx;
    
    /**
     * 姓名
     */
    @Column(name = "Name")
    private String name;
    
    /**
     * 性别
     */
    @Column(name = "Sex")
    private String sex;
    
    /**
     * 民族
     */
    @Column(name = "Nation")
    private String nation;
    
    /**
     * 出生日期
     */
    @Column(name = "Birthday")
    private String birthday;
    
    /**
     * 证件号
     */
    @Column(name = "IdCode")
    private String idcode;
    
    /**
     * 有效期始
     */
    @Column(name = "StartDate")
    private String startdate;
    
    /**
     * 有效期止
     */
    @Column(name = "EndDate")
    private String enddate;
    
    /**
     * 地址
     */
    @Column(name = "Address")
    private String address;
    
    /**
     * 卡号
     */
    @Column(name = "CardNo")
    private String cardno;
    
    /**
     * 卡号别码
     */
    @Column(name = "CardIDN")
    private String cardidn;
    
    /**
     * 联系电话
     */
    @Column(name = "Phone")
    private String phone;
    
    /**
     * 发卡地区行政划代码
     */
    @Column(name = "CardCity")
    private String cardcity;
    
    /**
     * 备用字段1
     */
    @Column(name = "CardVer")
    private String cardver;
    
    /**
     * 备用字段2
     */
    @Column(name = "BEIYONG2")
    private String beiyong2;
    
	/**
     * 证件类型
     */
    public String getZzlx() {
        return this.zzlx;
    }
    
    /**
     * 证件类型
     */
    public String zzlx2Html() {
        return StringHelper.replaceHTMLSymbol(this.zzlx);
    }

    /**
     * 证件类型
     */
    public void setZzlx(String zzlx) {
        zzlx = StringUtil.substringBySize(zzlx,50 , "GB18030");
        this.zzlx = zzlx;
    }
    
	/**
     * 姓名
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 姓名
     */
    public String name2Html() {
        return StringHelper.replaceHTMLSymbol(this.name);
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        name = StringUtil.substringBySize(name,50  , "GB18030");
        this.name = name;
    }
    
	/**
     * 性别
     */
    public String getSex() {
        return this.sex;
    }
    
    /**
     * 性别
     */
    public String sex2Html() {
        return StringHelper.replaceHTMLSymbol(this.sex);
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
        sex = StringUtil.substringBySize(sex,50  , "GB18030");
        this.sex = sex;
    }
    
	/**
     * 民族
     */
    public String getNation() {
        return this.nation;
    }
    
    /**
     * 民族
     */
    public String nation2Html() {
        return StringHelper.replaceHTMLSymbol(this.nation);
    }

    /**
     * 民族
     */
    public void setNation(String nation) {
        nation = StringUtil.substringBySize(nation, 50 , "GB18030");
        this.nation = nation;
    }
    
	/**
     * 出生日期
     */
    public String getBirthday() {
        return this.birthday;
    }
    
    /**
     * 出生日期
     */
    public String birthday2Html() {
        return StringHelper.replaceHTMLSymbol(this.birthday);
    }

    /**
     * 出生日期
     */
    public void setBirthday(String birthday) {
        birthday = StringUtil.substringBySize(birthday,50  , "GB18030");
        this.birthday = birthday;
    }
    
	/**
     * 证件号
     */
    public String getIdcode() {
        return this.idcode;
    }
    
    /**
     * 证件号
     */
    public String idcode2Html() {
        return StringHelper.replaceHTMLSymbol(this.idcode);
    }

    /**
     * 证件号
     */
    public void setIdcode(String idcode) {
        idcode = StringUtil.substringBySize(idcode,50  , "GB18030");
        this.idcode = idcode;
    }
    
	/**
     * 有效期始
     */
    public String getStartdate() {
        return this.startdate;
    }
    
    /**
     * 有效期始
     */
    public String startdate2Html() {
        return StringHelper.replaceHTMLSymbol(this.startdate);
    }

    /**
     * 有效期始
     */
    public void setStartdate(String startdate) {
        startdate = StringUtil.substringBySize(startdate, 50 , "GB18030");
        this.startdate = startdate;
    }
    
	/**
     * 有效期止
     */
    public String getEnddate() {
        return this.enddate;
    }
    
    /**
     * 有效期止
     */
    public String enddate2Html() {
        return StringHelper.replaceHTMLSymbol(this.enddate);
    }

    /**
     * 有效期止
     */
    public void setEnddate(String enddate) {
        enddate = StringUtil.substringBySize(enddate,50  , "GB18030");
        this.enddate = enddate;
    }
    
	/**
     * 地址
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * 地址
     */
    public String address2Html() {
        return StringHelper.replaceHTMLSymbol(this.address);
    }

    /**
     * 地址
     */
    public void setAddress(String address) {
        address = StringUtil.substringBySize(address, 50 , "GB18030");
        this.address = address;
    }
    
	/**
     * 卡号
     */
    public String getCardno() {
        return this.cardno;
    }
    
    /**
     * 卡号
     */
    public String cardno2Html() {
        return StringHelper.replaceHTMLSymbol(this.cardno);
    }

    /**
     * 卡号
     */
    public void setCardno(String cardno) {
        cardno = StringUtil.substringBySize(cardno,50  , "GB18030");
        this.cardno = cardno;
    }
    
	/**
     * 卡号别码
     */
    public String getCardidn() {
        return this.cardidn;
    }
    
    /**
     * 卡号别码
     */
    public String cardidn2Html() {
        return StringHelper.replaceHTMLSymbol(this.cardidn);
    }

    /**
     * 卡号别码
     */
    public void setCardidn(String cardidn) {
        cardidn = StringUtil.substringBySize(cardidn, 50 , "GB18030");
        this.cardidn = cardidn;
    }
    
	/**
     * 联系电话
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * 联系电话
     */
    public String phone2Html() {
        return StringHelper.replaceHTMLSymbol(this.phone);
    }

    /**
     * 联系电话
     */
    public void setPhone(String phone) {
        phone = StringUtil.substringBySize(phone,50  , "GB18030");
        this.phone = phone;
    }
    
	/**
     * 发卡地区行政划代码
     */
    public String getCardcity() {
        return this.cardcity;
    }
    
    /**
     * 发卡地区行政划代码
     */
    public String cardcity2Html() {
        return StringHelper.replaceHTMLSymbol(this.cardcity);
    }

    /**
     * 发卡地区行政划代码
     */
    public void setCardcity(String cardcity) {
        cardcity = StringUtil.substringBySize(cardcity,50 , "GB18030");
        this.cardcity = cardcity;
    }
    
	/**
     * 备用字段1
     */
    public String getCardVer() {
        return this.cardver;
    }
    
    /**
     * 备用字段1
     */
//    public String beiyong12Html() {
//        return StringHelper.replaceHTMLSymbol(this.beiyong1);
//    }

    /**
     * 备用字段1
     */
    public void setCardVer(String cardver) {
        cardver = StringUtil.substringBySize(cardver, 50, "GB18030");
        this.cardver = cardver;
    }
    
	/**
     * 备用字段2
     */
    public String getBeiyong2() {
        return this.beiyong2;
    }
    
    /**
     * 备用字段2
     */
    public String beiyong22Html() {
        return StringHelper.replaceHTMLSymbol(this.beiyong2);
    }

    /**
     * 备用字段2
     */
    public void setBeiyong2(String beiyong2) {
        beiyong2 = StringUtil.substringBySize(beiyong2,50 , "GB18030");
        this.beiyong2 = beiyong2;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}