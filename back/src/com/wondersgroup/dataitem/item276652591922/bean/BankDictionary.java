package com.wondersgroup.dataitem.item276652591922.bean;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

@SuppressWarnings("serial")
public class BankDictionary implements Serializable{
	
	@Excel(name = "id")
	private String id;
	
	@Excel(name = "key")
	private String key;
	
	@Excel(name = "value")
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
}
