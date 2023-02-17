package com.wondersgroup.selfapi.bean;

import java.io.Serializable;

public class ItemTheme implements Serializable{

	private static final long serialVersionUID = 4970226579991928489L;
	
	// 事项主题名称
	private String itemTypeName;
	
	// 事项主题CODE
	private String itemTypeCode;
	
	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getItemTypeCode() {
		return itemTypeCode;
	}

	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}
}

