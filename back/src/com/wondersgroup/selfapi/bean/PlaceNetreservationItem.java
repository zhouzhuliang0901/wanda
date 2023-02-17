package com.wondersgroup.selfapi.bean;

import java.util.List;

public class PlaceNetreservationItem {
	//事项名称
	private String itemName;
	//事项编号
	private String itemNo;
	//办理点信息
	private List<PlaceNetReservationInfo> placeList ;
	
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
	public List<PlaceNetReservationInfo> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<PlaceNetReservationInfo> placeList) {
		this.placeList = placeList;
	}
	
	
	
}
