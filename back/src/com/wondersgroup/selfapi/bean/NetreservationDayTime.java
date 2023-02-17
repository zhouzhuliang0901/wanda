package com.wondersgroup.selfapi.bean;


public class NetreservationDayTime  {
	private String stDetailId;//时间段主键Id
	private String stRuleId;//预约规则主键Id
	private String startTime;//时间段开始时间(HH:mm:ss)
	private String endTime; //时间段结束时间(HH:mm:ss)
	private String stShow;//显示(上午或下午)
	private Integer totalCount;//可预约的总人数
	private Integer surplusCount; //可预约的剩余人数
	
	public String getStDetailId() {
		return stDetailId;
	}
	public void setStDetailId(String stDetailId) {
		this.stDetailId = stDetailId;
	}
	public String getStRuleId() {
		return stRuleId;
	}
	public void setStRuleId(String stRuleId) {
		this.stRuleId = stRuleId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStShow() {
		return stShow;
	}
	public void setStShow(String stShow) {
		this.stShow = stShow;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSurplusCount() {
		return surplusCount;
	}
	public void setSurplusCount(Integer surplusCount) {
		this.surplusCount = surplusCount;
	}
	

}
