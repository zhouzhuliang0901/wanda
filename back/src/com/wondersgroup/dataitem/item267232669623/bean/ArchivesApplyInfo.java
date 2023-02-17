package com.wondersgroup.dataitem.item267232669623.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ARCHIVES_APPLY_INFO")
public class ArchivesApplyInfo implements Serializable {
	
	/**
	 * 受理信息主键
	 */
	public static final String ID = "ID";
	
	/**
	 * 受理ID
	 */
	public static final String ST_APPLY_ID = "ST_APPLY_ID";
	
	/**
	 * 受理编号
	 */
	public static final String ST_APPLY_NO = "ST_APPLY_NO";
	
	/**
	 * 申请人姓名
	 */
	public static final String ST_USER_NAME = "ST_USER_NAME";
	
	/**
	 * 申请人证件号
	 */
	public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";
	
	/**
	 * 档案类型编号
	 */
	public static final String ST_ARCHIVES_NO = "ST_ARCHIVES_NO";
	
	/**
	 * 创建时间
	 */
	public static final String DT_CREAT = "DT_CREAT";
	
	/**
	 * 更新时间
	 */
	public static final String DT_UPDATE = "DT_UPDATE";
	
	/**
	 * 受理状态
	 */
	public static final String NM_STATUS = "NM_STATUS";
	
	/**
	 * 扩展字段1
	 */
	public static final String ST_EXT1 = "ST_EXT1";
	
	/**
	 * 扩展字段2
	 */
	public static final String ST_EXT2 = "ST_EXT2";
	
	/**
	 * 扩展字段3
	 */
	public static final String ST_EXT3 = "ST_EXT3";
	
	/**
	 * 受理信息主键
	 */
    @Id
    @Column(name = "ST_CALL_ID")
    private String stId;
    
    /**
     * 受理ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 受理编号
     */
    @Column(name = "ST_APPLY_NO")
    private String stApplyNo;
    
    /**
     * 申请人姓名
     */
    @Column(name = "ST_USER_NAME")
    private String stUserName;
    
    /**
     * 申请人证件号
     */
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentNo;
    
    /**
     * 档案类型编号
     */
    @Column(name = "ST_ARCHIVES_NO")
    private String stArchivesNo;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREAT")
    private Timestamp dtCreat;
    
    /**
     * 修改时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 受理状态，0：有效，1：已失效
     */
    @Column(name = "NM_STATUS")
    private int nmStatus;
    
    /**
     * 扩展字段1，配合NM_STATUS使用，
     * 0：待审核或者审核通过未打印，1：审核通过且已经打印过
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
    
	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStApplyId() {
		return stApplyId;
	}

	public void setStApplyId(String stApplyId) {
		this.stApplyId = stApplyId;
	}

	public String getStApplyNo() {
		return stApplyNo;
	}

	public void setStApplyNo(String stApplyNo) {
		this.stApplyNo = stApplyNo;
	}

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStIdentNo() {
		return stIdentNo;
	}

	public void setStIdentNo(String stIdentNo) {
		this.stIdentNo = stIdentNo;
	}

	public Timestamp getDtCreat() {
		return dtCreat;
	}

	public void setDtCreat(Timestamp dtCreat) {
		this.dtCreat = dtCreat;
	}

	public Timestamp getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Timestamp dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public int getNmStatus() {
		return nmStatus;
	}

	public void setNmStatus(int nmStatus) {
		this.nmStatus = nmStatus;
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

	public String getStArchivesNo() {
		return stArchivesNo;
	}

	public void setStArchivesNo(String stArchivesNo) {
		this.stArchivesNo = stArchivesNo;
	}
    
}
