package com.wondersgroup.selfapi.bean;

/**
 * 根据大厅Code获取所有可预约的事项
 * 
 * @author 01053872-pc
 * 
 */
public class ItemSet {
	// 事项编码
	private String stItemNo;
	// 十位事项编码
	private String stItemTenNo;
	// 办理点的ID
	private String stPlaceId;
	// 事项名称
	private String stItemName;
	// 部门编码
	private String organCode;
	// 部门编码
	private String placeCode;
	// 部门名称
	private String organName;
	// 其下是否有子事项的类型判断 0 没有子事项 1 有子事项
	// 是否申报预约快速预约  （0为快速预约  1 为申报预约）
	private String nmType;
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

	public String getStPlaceId() {
		return stPlaceId;
	}

	public void setStPlaceId(String stPlaceId) {
		this.stPlaceId = stPlaceId;
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

	public String getNmType() {
		return nmType;
	}

	public void setNmType(String nmType) {
		this.nmType = nmType;
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

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

}
