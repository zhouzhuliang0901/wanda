package com.wondersgroup.publicService.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ITEM_ORGAN")
public class SelmItemOrgan implements Serializable {

public static final String SELM_ITEM_ORGAN = "SELM_ITEM_ORGAN";

	public static final String ST_OUGUID = "ST_OUGUID";

	public static final String ST_ORG_CODE = "ST_ORG_CODE";

	public static final String ST_OUNAME = "ST_OUNAME";

	public static final String ST_SHORT_NAME = "ST_SHORT_NAME";

	public static final String ST_OUCODE = "ST_OUCODE";

	public static final String ST_SH_CODE = "ST_SH_CODE";

	public static final String IS_ONUSE = "IS_ONUSE";

	public static final String ST_DEPT_LEVEL = "ST_DEPT_LEVEL";

	public static final String ST_AREA_CODE = "ST_AREA_CODE";

	public static final String ST_PARENT_OUGUID = "ST_PARENT_OUGUID";

	@Column(name = "ST_OUGUID")
	private String stOuguid;

	@Column(name = "ST_ORG_CODE")
	private String stOrgCode;

	@Column(name = "ST_OUNAME")
	private String stOuname;

	@Column(name = "ST_SHORT_NAME")
	private String stShortName;

	@Column(name = "ST_OUCODE")
	private String stOucode;

	@Column(name = "ST_SH_CODE")
	private String stShCode;

	@Column(name = "IS_ONUSE")
	private String isOnuse;

	@Column(name = "ST_DEPT_LEVEL")
	private String stDeptLevel;

	@Column(name = "ST_AREA_CODE")
	private String stAreaCode;

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