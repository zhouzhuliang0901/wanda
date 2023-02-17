package com.wondersgroup.infopub.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备管理员
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "INFOPUB_MANAGER")
public class InfopubManager implements Serializable {
    
    /**
     * 设备管理员表
     */
    public static final String INFOPUB_MANAGER = "INFOPUB_MANAGER";
    
    /**
     * 管理人ID
     */
    public static final String ST_MANAGER_ID = "ST_MANAGER_ID";
    
    /**
     * 管理人姓名
     */
    public static final String ST_MANAGER_NAME = "ST_MANAGER_NAME";
    
    /**
     * 管理人手机号
     */
    public static final String ST_MANAGER_PHONE = "ST_MANAGER_PHONE";
    
    /**
     * 管理人身份证号
     */
    public static final String ST_MANAGER_IDCARD = "ST_MANAGER_IDCARD";
    
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
    public static final String DT_UPADTE = "DT_UPADTE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    /**
     * 管理人ID
     */
    @Id
    @Column(name = "ST_MANAGER_ID")
    private String stManagerId;
    
    /**
     * 管理人姓名
     */
    @Column(name = "ST_MANAGER_NAME")
    private String stManagerName;
    
    /**
     * 管理人手机号
     */
    @Column(name = "ST_MANAGER_PHONE")
    private String stManagerPhone;
    
    /**
     * 管理人身份证号
     */
    @Column(name = "ST_MANAGER_IDCARD")
    private String stManagerIdcard;
    
    /**
     * 排序字段
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
     * 管理设备ID（MAC）
     */
    private String stDeviceId;

	public String getStManagerId() {
		return stManagerId;
	}

	public void setStManagerId(String stManagerId) {
		this.stManagerId = stManagerId;
	}

	public String getStManagerName() {
		return stManagerName;
	}

	public void setStManagerName(String stManagerName) {
		this.stManagerName = stManagerName;
	}

	public String getStManagerPhone() {
		return stManagerPhone;
	}

	public void setStManagerPhone(String stManagerPhone) {
		this.stManagerPhone = stManagerPhone;
	}

	public String getStManagerIdcard() {
		return stManagerIdcard;
	}

	public void setStManagerIdcard(String stManagerIdcard) {
		this.stManagerIdcard = stManagerIdcard;
	}

	public BigDecimal getNmOrder() {
		return nmOrder;
	}

	public void setNmOrder(BigDecimal nmOrder) {
		this.nmOrder = nmOrder;
	}

	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Timestamp getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Timestamp dtUpdate) {
		this.dtUpdate = dtUpdate;
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
	
	public String getStDeviceId() {
		return stDeviceId;
	}

	public void setStDeviceId(String stDeviceId) {
		this.stDeviceId = stDeviceId;
	}
}