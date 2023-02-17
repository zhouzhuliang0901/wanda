package com.wondersgroup.publicService.bean;

/**
 * 
 * @author 
 * 
 */
public class ItemSet {
	// 事项编码
	private String stItemNo;
	// 十位事项编码
	private String stItemTenNo;
	// 事项名称
	private String stItemName;
	// 子事项名称
	private String stSubItemName;
	// 部门编码
	private String organCode;
	// 部门名称
	private String organName;
	// 是否为主事项子事项 0 主事项 1 子事项
	private String itemType;
	// 事項id主键
	private String stItemId;

	public String getStItemNo() {
		return stItemNo;
	}

	public void setStItemNo(String stItemNo) {
		this.stItemNo = stItemNo;
	}

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getStItemId() {
		return stItemId;
	}

	public void setStItemId(String stItemId) {
		this.stItemId = stItemId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getStItemTenNo() {
		return stItemTenNo;
	}

	public void setStItemTenNo(String stItemTenNo) {
		this.stItemTenNo = stItemTenNo;
	}

	public String getStSubItemName() {
		return stSubItemName;
	}

	public void setStSubItemName(String stSubItemName) {
		this.stSubItemName = stSubItemName;
	}
}
