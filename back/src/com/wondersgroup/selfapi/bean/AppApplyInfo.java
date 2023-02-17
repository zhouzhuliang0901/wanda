package com.wondersgroup.selfapi.bean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 办件列表
 * @author 01053872-pc
 *
 */
public class AppApplyInfo {

	//办件id
	private String stApplyId;
	//办件编号
	private String stApplyNo;
	//事项名称
	private String stItemName;
	//申请时间
	private String stApply;
	//办件状态
	private String stFinalState;
	//办结时间
	private String stFinish;
	//个人姓名
	private String stName;
	//公司名称
	private String stUnit;
	//联系电话
	private String mobile;
	//部门名称
	private String stOrganName;
	//申请时间(日期格式)
	private String stApplyStr;
	//办结时间(日期格式)
	private String stFinishStr;

	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStApplyNo() {
		return stApplyNo;
	}
	public void setStApplyNo(String stApplyNo) {
		this.stApplyNo = stApplyNo;
	}
	public String getStOrganName() {
		return stOrganName;
	}
	public void setStOrganName(String stOrganName) {
		this.stOrganName = stOrganName;
	}
	public String getStItemName() {
		return stItemName;
	}
	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}
	public String getStFinalState() {
		return stFinalState;
	}
	public void setStFinalState(String stFinalState) {
		this.stFinalState = stFinalState;
	}
	public String getStApply() {
		return stApply;
	}
	public void setStApply(String stApply) {
		this.stApply = stApply;
	}
	public String getStFinish() {
		return stFinish;
	}
	public void setStFinish(String stFinish) {
		this.stFinish = stFinish;
	}
	public String getStApplyId() {
		return stApplyId;
	}
	public void setStApplyId(String stApplyId) {
		this.stApplyId = stApplyId;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStUnit() {
		return stUnit;
	}
	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}
	public String getStApplyStr() {
		return stApplyStr;
	}
	public void setStApplyStr(String stApplyStr) {
		this.stApplyStr = stApplyStr;
	}
	public String getStFinishStr() {
		return stFinishStr;
	}
	public void setStFinishStr(String stFinishStr) {
		this.stFinishStr = stFinishStr;
	}
	public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
	
}
