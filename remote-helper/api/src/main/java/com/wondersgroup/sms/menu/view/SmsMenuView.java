package com.wondersgroup.sms.menu.view;

import java.util.List;

import com.wondersgroup.sms.menu.bean.SmsMenu;

public class SmsMenuView extends SmsMenu {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private List<SmsMenuView> childrenList;

	public List<SmsMenuView> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<SmsMenuView> childrenList) {
		this.childrenList = childrenList;
	}

}
