package com.wondersgroup.dataitem.item213072044522.bean;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

@SuppressWarnings("serial")
public class MedicalDictionary implements Serializable{
	
	@Excel(name = "Key")
	private String key;
	
	@Excel(name = "Value")
	private String value;
	
	@Excel(name = "ParentCode")
	private String parentKey;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	} 
}
