package com.wondersgroup.infopub.bean;

import java.util.List;

import com.wondersgroup.infopub.bean.InfopubArea;
public class AreaView extends InfopubArea {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private List<AreaView> childrenList;

	public List<AreaView> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<AreaView> childrenList) {
		this.childrenList = childrenList;
	}

}
