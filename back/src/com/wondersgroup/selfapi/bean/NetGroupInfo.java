package com.wondersgroup.selfapi.bean;

public class NetGroupInfo {
	//组别ID
	private String groupId;
	//组别编号
	private String groupCode;
	//组别名称
	private String groupName;
	//大厅ID
	private String hallId ;
	//大厅全称
	private String hallFullName;
	//大厅首字母
	private String hallAlpha;
	//大厅地址信息
	private String hallAdress;
	//大厅描述
	private String hallDesc;
	
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
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
