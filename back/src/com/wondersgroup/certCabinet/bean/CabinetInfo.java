package com.wondersgroup.certCabinet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_CABINET_INFO")
public class CabinetInfo implements Serializable{
	
	public static String SELM_CABINET_INFO = "SELM_CABINET_INFO";
	
	public static String ST_CABINET_ID = "ST_CABINET_ID";
	
	public static String ST_CABINET_MAC = "ST_CABINET_MAC";
	
	public static String ST_CABINET_NUM = "ST_CABINET_NUM";
	
	public static String ST_CABINET_NO = "ST_CABINET_NO";
	
	public static String NM_CABINET = "NM_CABINET";
	
	public static String ST_CABINET_STREET = "ST_CABINET_STREET";
	
	public static String ST_STREET_ADDRESS = "ST_STREET_ADDRESS";
	
	public static String ST_CONTACTS = "ST_CONTACTS";
	
	public static String ST_PHONE = "ST_PHONE";
	
	public static String DT_CREAT = "DT_CREAT";
	
	public static String ST_EXT1 = "ST_EXT1";
	
	public static String ST_EXT2 = "ST_EXT2";
	
	public static String ST_EXT3 = "ST_EXT3";
	
	public static String ST_EXT4 = "ST_EXT4";
	
	/**
	 * 主键ID
	 */
	@Id
	@Column(name = "ST_DELIVERY_ID")
	private String stCabinetId;
	
	/**
	 * 设备MAC
	 */
	@Column(name = "ST_CABINET_MAC")
	private String stCabinetMac;
	
	/**
	 * 证照柜柜号
	 */
	@Column(name = "ST_CABINET_NUM")
	private String stCabinetNum;
	
	/**
	 * 证照柜编号
	 */
	@Column(name = "ST_CABINET_NO")
	private String stCabinetNo;
	
	/**
	 * 证照柜柜子数
	 */
	@Column(name = "NM_CABINET")
	private BigDecimal nmCabinet;
	
	/**
	 * 证照柜所属街道
	 */
	@Column(name = "ST_CABINET_STREET")
	private String stCabinetStreet;
	
	/**
	 * 街道地址
	 */
	@Column(name = "ST_STREET_ADDRESS")
	private String stStreetAddress;
	
	/**
	 * 联系人
	 */
	@Column(name = "ST_CONTACTS")
	private String stContacts;
	
	/**
	 * 联系电话
	 */
	@Column(name = "ST_PHONE")
	private String stPhone;
	
	/**
	 * 创建时间
	 */
	@Column(name = "DT_CREAT")
	private Timestamp dtCreat;
	
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
	 * 扩展字段3
	 */
	@Column(name = "ST_EXT3")
	private String stExt3;
	
	/**
	 * 扩展字段4
	 */
	@Column(name = "ST_EXT4")
	private String stExt4;

	public String getStCabinetId() {
		return stCabinetId;
	}

	public void setStCabinetId(String stCabinetId) {
		this.stCabinetId = stCabinetId;
	}

	public String getStCabinetMac() {
		return stCabinetMac;
	}

	public void setStCabinetMac(String stCabinetMac) {
		this.stCabinetMac = stCabinetMac;
	}

	public String getStCabinetNum() {
		return stCabinetNum;
	}

	public void setStCabinetNum(String stCabinetNum) {
		this.stCabinetNum = stCabinetNum;
	}

	public String getStCabinetNo() {
		return stCabinetNo;
	}

	public void setStCabinetNo(String stCabinetNo) {
		this.stCabinetNo = stCabinetNo;
	}

	public BigDecimal getNmCabinet() {
		return nmCabinet;
	}

	public void setNmCabinet(BigDecimal nmCabinet) {
		this.nmCabinet = nmCabinet;
	}

	public String getStCabinetStreet() {
		return stCabinetStreet;
	}

	public void setStCabinetStreet(String stCabinetStreet) {
		this.stCabinetStreet = stCabinetStreet;
	}

	public String getStStreetAddress() {
		return stStreetAddress;
	}

	public void setStStreetAddress(String stStreetAddress) {
		this.stStreetAddress = stStreetAddress;
	}

	public String getStContacts() {
		return stContacts;
	}

	public void setStContacts(String stContacts) {
		this.stContacts = stContacts;
	}

	public String getStPhone() {
		return stPhone;
	}

	public void setStPhone(String stPhone) {
		this.stPhone = stPhone;
	}

	public Timestamp getDtCreat() {
		return dtCreat;
	}

	public void setDtCreat(Timestamp dtCreat) {
		this.dtCreat = dtCreat;
	}

	public String getStExt1() {
		return stExt1;
	}

	public void setStExt1(String stExt1) {
		this.stExt1 = stExt1;
	}

	public String getStExt2() {
		return stExt2;
	}

	public void setStExt2(String stExt2) {
		this.stExt2 = stExt2;
	}

	public String getStExt3() {
		return stExt3;
	}

	public void setStExt3(String stExt3) {
		this.stExt3 = stExt3;
	}

	public String getStExt4() {
		return stExt4;
	}

	public void setStExt4(String stExt4) {
		this.stExt4 = stExt4;
	}
}
