package com.wondersgroup.data.bean;

public class SqhCount {
	
	private int yl;//预览
	private int gs;//挂失
	private int sl;//申领
	private int bl;//补领
	public int getYl() {
		return yl;
	}
	public void setYl(int yl) {
		this.yl = yl;
	}
	public int getGs() {
		return gs;
	}
	public void setGs(int gs) {
		this.gs = gs;
	}
	public int getSl() {
		return sl;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	public int getBl() {
		return bl;
	}
	public void setBl(int bl) {
		this.bl = bl;
	}
	@Override
	public String toString() {
		return "SqhCount [yl=" + yl + ", gs=" + gs + ", sl=" + sl + ", bl="
				+ bl + "]";
	}
	
	

}
