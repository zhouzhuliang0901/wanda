package com.wondersgroup.selfapi.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 办事指南事项部门表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ZHALL_ITEM_ORGAN")
public class SelmZhallItemOrgan implements Serializable{
	
	/**
	 * 部门唯一标识
	 */
    @Id
    @Column(name = "ST_OUGUID")
	private String stOuguid;
	
	/**
	 * 统一社会信用代码
	 */
    @Column(name = "ST_ORG_CODE")
	private String stOrgCode;
	
	/**
	 * 部门全称
	 */
    @Column(name = "ST_OUNAME")
	private String stOuname;
	
	/**
	 * 部门简称
	 */
    @Column(name = "ST_SHORT_NAME")
	private String stShortName;
	
	/**
	 * 部门编码
	 */
    @Column(name = "ST_OUCODE")
	private String stOucode;
	
	/**
	 * 上海部门编码
	 */
    @Column(name = "ST_SH_CODE")
	private String stShCode;
	
	/**
	 * 部门是否禁用
	 */
    @Column(name = "IS_ONUSE")
	private String isOnuse;
	
	/**
	 * 部门行使层级
	 */
    @Column(name = "ST_DEPT_LEVEL")
	private String stDeptLevel;
	
	/**
	 * 区划编码
	 */
    @Column(name = "ST_AREA_CODE")
	private String stAreaCode;
    
	/**
	 * 父部门标识
	 */
    @Column(name = "ST_PARENT_OUGUID")
	private String stParentOuguid;

	public String getStOuguid() {
		return stOuguid;
	}

	public void setStOuguid(String stOuguid) {
		this.stOuguid = stOuguid;
	}

	public String getStOrgCode() {
		return stOrgCode;
	}

	public void setStOrgCode(String stOrgCode) {
		this.stOrgCode = stOrgCode;
	}

	public String getStOuname() {
		return stOuname;
	}

	public void setStOuname(String stOuname) {
		this.stOuname = stOuname;
	}

	public String getStShortName() {
		return stShortName;
	}

	public void setStShortName(String stShortName) {
		this.stShortName = stShortName;
	}

	public String getStOucode() {
		return stOucode;
	}

	public void setStOucode(String stOucode) {
		this.stOucode = stOucode;
	}

	public String getStShCode() {
		return stShCode;
	}

	public void setStShCode(String stShCode) {
		this.stShCode = stShCode;
	}

	public String getIsOnuse() {
		return isOnuse;
	}

	public void setIsOnuse(String isOnuse) {
		this.isOnuse = isOnuse;
	}

	public String getStDeptLevel() {
		return stDeptLevel;
	}

	public void setStDeptLevel(String stDeptLevel) {
		this.stDeptLevel = stDeptLevel;
	}

	public String getStAreaCode() {
		return stAreaCode;
	}

	public void setStAreaCode(String stAreaCode) {
		this.stAreaCode = stAreaCode;
	}
	
	public String getStParentOuguid() {
		return stParentOuguid;
	}

	public void setStParentOuguid(String stParentOuguid) {
		this.stParentOuguid = stParentOuguid;
	}
}
