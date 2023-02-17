package com.wondersgroup.publicService.bean;

/**
 * 部门信息
 * @author 
 */
public class OrganNodeInfo {
	
	//部门ID
	private String organId ;
	
	//部门CODE
	private String organCode ;
	
	//部门名称
	private String organName ;
	
	//部门简称
	private String description ;
	
	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
