package com.wondersgroup.selfapi.bean;

public class WindowItemStatus {

	private String stItemName;

	private String stStatusName;

	private String stItemNo;

	// 十位事项编码
	private String stItemTenNo;

	private String stItemId;

	// 部门CODE
	private String organCode;
	// 办理点CODE
	private String placeCode;
	// 部门名称
	private String organName;
	// 部门简称
	private String description;

	public String getStStatusName() {
		return stStatusName;
	}

	public void setStStatusName(String stStatusName) {
		this.stStatusName = stStatusName;
	}

	public String getStItemNo() {
		return stItemNo;
	}

	public void setStItemNo(String stItemNo) {
		this.stItemNo = stItemNo;
	}

	public String getStItemId() {
		return stItemId;
	}

	public void setStItemId(String stItemId) {
		this.stItemId = stItemId;
	}

	public String getStItemTenNo() {
		return stItemTenNo;
	}

	public void setStItemTenNo(String stItemTenNo) {
		this.stItemTenNo = stItemTenNo;
	}

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
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

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	

}
