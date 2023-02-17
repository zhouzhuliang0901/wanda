package com.wondersgroup.data.bean;

public class OfflineCount {
	private int wd;//万达
	private int zf;//卓繁
	private int xd;//新点
	private int xz;//熙重
	private int jf;//金赋
	private int countYh;//银行
	private int count;//总量
	public int getWd() {
		return wd;
	}
	public void setWd(int wd) {
		this.wd = wd;
	}
	public int getZf() {
		return zf;
	}
	public void setZf(int zf) {
		this.zf = zf;
	}
	public int getXd() {
		return xd;
	}
	public void setXd(int xd) {
		this.xd = xd;
	}
	public int getXz() {
		return xz;
	}
	public void setXz(int xz) {
		this.xz = xz;
	}
	public int getJf() {
		return jf;
	}
	public void setJf(int jf) {
		this.jf = jf;
	}
	public int getCountYh() {
		return countYh;
	}
	public void setCountYh(int countYh) {
		this.countYh = countYh;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "SqhCount [wd=" + wd + ", zf=" + zf + ", xd=" + xd + ", xz="
				+ xz + ", jf=" + jf + ", countYh=" + countYh + ", count=" + count +"]";
	}
}
