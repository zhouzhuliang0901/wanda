package com.wondersgroup.dataitem.item283852484724.bean;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 好差评街道机构
 * @author wanda
 *
 */
public class Street {
	
	@Excel(name = "行政区划")
	private String streetCode;
	
	@Excel(name = "行政区划名称")
	private String streetName;
	
	@Excel(name = "简称")
	private String streetShortName;

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetShortName() {
		return streetShortName;
	}

	public void setStreetShortName(String streetShortName) {
		this.streetShortName = streetShortName;
	}
}
