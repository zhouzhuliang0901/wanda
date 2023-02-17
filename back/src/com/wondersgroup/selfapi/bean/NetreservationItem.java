package com.wondersgroup.selfapi.bean;

import java.util.List;

public class NetreservationItem {
	//是否可以预约  1 可预约 0 不可预约 空默为1可预约
	private int state;
	//事项ID
	private String itemId;
	//事项名称
	private String itemName;
	//事项编号
	private String itemNo;
	//第三方预约地址
	private String url;
	//组别信息
	private List<NetGroupInfo> groupList;

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

	public List<NetGroupInfo> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<NetGroupInfo> groupList) {
		this.groupList = groupList;
	}

}
