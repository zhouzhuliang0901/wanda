package com.wondersgroup.selfapi.bean;


public class NetreservationItemHall {
	// 是否可以预约 1 可预约 0 不可预约 空默为1可预约
	private int state;
	// 事项ID
	private String itemId;
	// 事项名称
	private String itemName;
	// 事项编号
	private String itemNo;
	// 第三方预约地址
	private String url;
	// 组别ID
	private String groupId;
	// 组别编号
	private String groupCode;
	// 组别名称
	private String groupName;
	// 大厅ID
	private String hallId;
	// 大厅全称
	private String hallFullName;
	// 大厅首字母
	private String hallAlpha;
	// 大厅地址信息
	private String hallAdress;
	// 大厅描述
	private String hallDesc;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getHallFullName() {
		return hallFullName;
	}
	public void setHallFullName(String hallFullName) {
		this.hallFullName = hallFullName;
	}
	public String getHallAlpha() {
		return hallAlpha;
	}
	public void setHallAlpha(String hallAlpha) {
		this.hallAlpha = hallAlpha;
	}
	public String getHallAdress() {
		return hallAdress;
	}
	public void setHallAdress(String hallAdress) {
		this.hallAdress = hallAdress;
	}
	public String getHallDesc() {
		return hallDesc;
	}
	public void setHallDesc(String hallDesc) {
		this.hallDesc = hallDesc;
	}

}
