package com.wondersgroup.assistant.readCardInfo.dao;
		
import java.io.*;
import javax.persistence.*;

import org.apache.commons.lang.builder.*;
import coral.base.util.StringUtil;
import wfc.service.util.*;

/**
 * IDCard
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "IDCard")
public class Idcard implements Serializable {
    
    /**
     * IDCard
     */
    public static final String IDCard = "IDCard";
    
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
     * 照片
     */
    public static final String ImgBase64 = "ImgBase64";
    
    /**
     * 签发机关
     */
    public static final String Department = "Department";
    
    /**
     * 读卡机器ID
     */
    public static final String SAMID = "SAMID";
    
    /**
     * 英文名
     */
    public static final String EName = "EName";
    
    /**
     * 备用字段1
     */
    public static final String BEIYONG1 = "BEIYONG1";
    
    /**
     * 备用字段2
     */
    public static final String BEIYONG2 = "BEIYONG2";
    @Id
    private Long id;

    public Idcard() {
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
     * 照片
     */
    @Column(name = "ImgBase64")
    private String imgbase64;
    
    /**
     * 签发机关
     */
    @Column(name = "Department")
    private String department;
    
    /**
     * 读卡机器ID
     */
    @Column(name = "SAMID")
    private String samid;
    
    /**
     * 英文名
     */
    @Column(name = "EName")
    private String ename;
    
    /**
     * 备用字段1
     */
    @Column(name = "BEIYONG1")
    private String beiyong1;
    
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
        name = StringUtil.substringBySize(name, 50, "GB18030");
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
        sex = StringUtil.substringBySize(sex,50 , "GB18030");
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
        nation = StringUtil.substringBySize(nation, 50, "GB18030");
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
        birthday = StringUtil.substringBySize(birthday,50 , "GB18030");
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
        idcode = StringUtil.substringBySize(idcode,50 , "GB18030");
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
        startdate = StringUtil.substringBySize(startdate, 50, "GB18030");
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
        enddate = StringUtil.substringBySize(enddate, 50, "GB18030");
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
        address = StringUtil.substringBySize(address, 50, "GB18030");
        this.address = address;
    }
    
	/**
     * 照片
     */
    public String getImgbase64() {
        return this.imgbase64;
    }
    
    /**
     * 照片
     */
    public String imgbase642Html() {
        return StringHelper.replaceHTMLSymbol(this.imgbase64);
    }

    /**
     * 照片
     */
    public void setImgbase64(String imgbase64) {
        imgbase64 = StringUtil.substringBySize(imgbase64, 50, "GB18030");
        this.imgbase64 = imgbase64;
    }
    
	/**
     * 签发机关
     */
    public String getDepartment() {
        return this.department;
    }
    
    /**
     * 签发机关
     */
    public String department2Html() {
        return StringHelper.replaceHTMLSymbol(this.department);
    }

    /**
     * 签发机关
     */
    public void setDepartment(String department) {
        department = StringUtil.substringBySize(department,50 , "GB18030");
        this.department = department;
    }
    
	/**
     * 读卡机器ID
     */
    public String getSamid() {
        return this.samid;
    }
    
    /**
     * 读卡机器ID
     */
    public String samid2Html() {
        return StringHelper.replaceHTMLSymbol(this.samid);
    }

    /**
     * 读卡机器ID
     */
    public void setSamid(String samid) {
        samid = StringUtil.substringBySize(samid, 50, "GB18030");
        this.samid = samid;
    }
    
	/**
     * 英文名
     */
    public String getEname() {
        return this.ename;
    }
    
    /**
     * 英文名
     */
    public String ename2Html() {
        return StringHelper.replaceHTMLSymbol(this.ename);
    }

    /**
     * 英文名
     */
    public void setEname(String ename) {
        ename = StringUtil.substringBySize(ename, 50, "GB18030");
        this.ename = ename;
    }
    
	/**
     * 备用字段1
     */
    public String getBeiyong1() {
        return this.beiyong1;
    }
    
    /**
     * 备用字段1
     */
    public String beiyong12Html() {
        return StringHelper.replaceHTMLSymbol(this.beiyong1);
    }

    /**
     * 备用字段1
     */
    public void setBeiyong1(String beiyong1) {
        beiyong1 = StringUtil.substringBySize(beiyong1, 50, "GB18030");
        this.beiyong1 = beiyong1;
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
        beiyong2 = StringUtil.substringBySize(beiyong2, 50, "GB18030");
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