package com.wondersgroup.api.bean;

import java.util.List;

public class ApidocModuleView extends ApidocModule {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ApidocInterface> apidocInterfaces;

	private List<ApidocModuleView> sonApidocModuleView;

	public List<ApidocModuleView> getSonApidocModuleView() {
		return sonApidocModuleView;
	}

	public void setSonApidocModuleView(
			List<ApidocModuleView> sonApidocModuleView) {
		this.sonApidocModuleView = sonApidocModuleView;
	}

	public List<ApidocInterface> getApidocInterfaces() {
		return apidocInterfaces;
	}

	public void setApidocInterfaces(List<ApidocInterface> apidocInterfaces) {
		this.apidocInterfaces = apidocInterfaces;
	}

}
