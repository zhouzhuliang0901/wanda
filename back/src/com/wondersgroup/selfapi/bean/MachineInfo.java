package com.wondersgroup.selfapi.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MachineInfo implements Serializable{
	
	// 设备ID
	private String stMachineId;
	// 设备MAC
	private String stMachineMAC;
	// 设备详细地址
	private String stMachineAddress;
	// 设备别名（管理单位）
	private String stLabel;
	// 市
	private String stCity;
	// 区
	private String stDistrict;
	// 街道
	private String stStreet;
	// 详细地址
	private String stAddress;
	
	public String getStMachineId() {
		return stMachineId;
	}
	public void setStMachineId(String stMachineId) {
		this.stMachineId = stMachineId;
	}
	public String getStMachineMAC() {
		return stMachineMAC;
	}
	public void setStMachineMAC(String stMachineMAC) {
		this.stMachineMAC = stMachineMAC;
	}
	public String getStMachineAddress() {
		return stMachineAddress;
	}
	public void setStMachineAddress(String stMachineAddress) {
		this.stMachineAddress = stMachineAddress;
	}
	public String getStLabel() {
		return stLabel;
	}
	public void setStLabel(String stLabel) {
		this.stLabel = stLabel;
	}
	public String getStCity() {
		return stCity;
	}
	public void setStCity(String stCity) {
		this.stCity = stCity;
	}
	public String getStDistrict() {
		return stDistrict;
	}
	public void setStDistrict(String stDistrict) {
		this.stDistrict = stDistrict;
	}
	public String getStStreet() {
		return stStreet;
	}
	public void setStStreet(String stStreet) {
		this.stStreet = stStreet;
	}
	public String getStAddress() {
		return stAddress;
	}
	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
	}
}
