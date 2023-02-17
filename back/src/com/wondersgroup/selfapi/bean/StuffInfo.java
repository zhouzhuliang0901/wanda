package com.wondersgroup.selfapi.bean;

import java.math.BigDecimal;

public class StuffInfo {

	//材料的名称
	private String stStuffName ;
	//复印数
	private BigDecimal copyNumber ;
	//原件数
	private BigDecimal originalNumber ;
	//备注描述
	private String stDesc ;
	public String getStStuffName() {
		return stStuffName;
	}
	public void setStStuffName(String stStuffName) {
		this.stStuffName = stStuffName;
	}
	public BigDecimal getCopyNumber() {
		return copyNumber;
	}
	public void setCopyNumber(BigDecimal copyNumber) {
		this.copyNumber = copyNumber;
	}
	public BigDecimal getOriginalNumber() {
		return originalNumber;
	}
	public void setOriginalNumber(BigDecimal originalNumber) {
		this.originalNumber = originalNumber;
	}
	public String getStDesc() {
		return stDesc;
	}
	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}
	
	
	
}
