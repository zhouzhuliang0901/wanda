package com.wondersgroup.api.bean;

import java.util.List;

public class ApidocAllInfoView extends ApidocProject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ApidocModuleView> apidocModuleViewList;

	public List<ApidocModuleView> getApidocModuleViewList() {
		return apidocModuleViewList;
	}

	public void setApidocModuleViewList(List<ApidocModuleView> apidocModuleViewList) {
		this.apidocModuleViewList = apidocModuleViewList;
	}

}
