package com.wondersgroup.dataitem.item236012130732.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "RECORD_BOOK_INFO")
public class RecordBookInfo implements Serializable{
	
	/**
	 * 主键ID
	 */
	@Id
    @Column(name = "ST_BOOK_ID")
	private String id;
	
	/**
	 * 记录册号
	 */
	@Column(name = "ST_BOOK_NO")
	private String recordBookNo;
	
	/**
	 * 持证人姓名
	 */
	@Column(name = "ST_NAME")
	private String name;
	
	/**
	 * 持证人身份证
	 */
	@Column(name = "ST_IDENTITY_NO")
	private String idCard;
	
	/**
	 * 持证人性别
	 */
	@Column(name = "ST_SEX")
	private String sex;
	
	/**
	 * 卡号
	 */
	@Column(name = "ST_CARD_NO")
	private String cardNo;
	
	/**
	 * 打印时间
	 */
	@Column(name = "DT_CREATE")
	private Timestamp dtCreate;
	
	/**
	 * 设备MAC
	 */
	@Column(name = "ST_MACHINE_MAC")
	private String stMachineId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordBookNo() {
		return recordBookNo;
	}

	public void setRecordBookNo(String recordBookNo) {
		this.recordBookNo = recordBookNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}

	public String getStMachineId() {
		return stMachineId;
	}

	public void setStMachineId(String stMachineId) {
		this.stMachineId = stMachineId;
	}
}
