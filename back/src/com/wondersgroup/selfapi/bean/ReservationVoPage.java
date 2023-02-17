package com.wondersgroup.selfapi.bean;

import java.io.Serializable;
import java.util.List;

public class ReservationVoPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 总页数
	private int totalPageCount;

	// 当前页
	private int currentPage;

	// 每页最大记录数
	private int commonPageSize;

	// 当前页所含记录数
	private int currentPageSize;

	// 总记录数
	private int totalItemCount;

	// 数据记录
	private List<NetReservationVo> rowList;

	public List<NetReservationVo> getRowList() {
		return rowList;
	}

	public void setRowList(List<NetReservationVo> rowList) {
		this.rowList = rowList;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCommonPageSize() {
		return commonPageSize;
	}

	public void setCommonPageSize(int commonPageSize) {
		this.commonPageSize = commonPageSize;
	}

	public int getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}
}
