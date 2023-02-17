package com.wondersgroup.dataitem.item382711997735.bean;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

@SuppressWarnings("serial")
public class ProvidentFundCenter implements Serializable{
	
	@Excel(name = "省")
	private String province;
	@Excel(name = "市")
	private String city;
	@Excel(name = "机构名称")
	private String centerName;
	@Excel(name = "机构代码")
	private String centerCode;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
}
