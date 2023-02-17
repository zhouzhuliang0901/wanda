package com.wondersgroup.selfapi.bean;

import java.math.BigDecimal;
import java.util.List;

public class ApplyDetailInfo {
	// 办件编号
	private String stApplyNo;
	// 办件id
	private String stApplyId;
	// 申请时间
	private String stApply;
	// 办件状态
	private String stFinalState;
	// 办结时间
	private String stFinish;
	// 公司名称
	private String stUnit;
	// 联系电话
	private String mobile;
	// 部门名称
	private String stOrganName;
	// 申请时间(日期格式)
	private String stApplyStr;
	// 办结时间(日期格式)
	private String stFinishStr;
	// 事项名称
	private String stItemName;
	// 事项编码
	private String stItemNo;
	// 办理地点
	private String stPlaceName;
	// 个人姓名
	private String stUserName;
	// 个人用户id
	private String stUserId;
	// 办理窗口
	private String stWindowNo;
	// 工号
	private String stJobNumber;
	// 满意度评价
	private BigDecimal nmSatisfation;
	// 服务人员照片url地址
	private String userImageUrl;
	// 事项材料的集合
	private List<StuffInfo> stStuffNameList;
	// 申请人（材料提交人）
	private String stApplyUserName;
	// 申请人电话
	private String applyMobile;

	public String getStApplyNo() {
		return stApplyNo;
	}

	public void setStApplyNo(String stApplyNo) {
		this.stApplyNo = stApplyNo;
	}

	public String getStApplyId() {
		return stApplyId;
	}

	public void setStApplyId(String stApplyId) {
		this.stApplyId = stApplyId;
	}

	public String getStApply() {
		return stApply;
	}

	public void setStApply(String stApply) {
		this.stApply = stApply;
	}

	public String getStFinalState() {
		return stFinalState;
	}

	public void setStFinalState(String stFinalState) {
		this.stFinalState = stFinalState;
	}

	public String getStFinish() {
		return stFinish;
	}

	public void setStFinish(String stFinish) {
		this.stFinish = stFinish;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStOrganName() {
		return stOrganName;
	}

	public void setStOrganName(String stOrganName) {
		this.stOrganName = stOrganName;
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

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}

	public String getStItemNo() {
		return stItemNo;
	}

	public void setStItemNo(String stItemNo) {
		this.stItemNo = stItemNo;
	}

	public String getStPlaceName() {
		return stPlaceName;
	}

	public void setStPlaceName(String stPlaceName) {
		this.stPlaceName = stPlaceName;
	}

	public String getStUserName() {
		return stUserName;
	}

	public String getStUserId() {
		return stUserId;
	}

	public void setStUserId(String stUserId) {
		this.stUserId = stUserId;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStWindowNo() {
		return stWindowNo;
	}

	public void setStWindowNo(String stWindowNo) {
		this.stWindowNo = stWindowNo;
	}

	public String getStJobNumber() {
		return stJobNumber;
	}

	public void setStJobNumber(String stJobNumber) {
		this.stJobNumber = stJobNumber;
	}

	public BigDecimal getNmSatisfation() {
		return nmSatisfation;
	}

	public void setNmSatisfation(BigDecimal nmSatisfation) {
		this.nmSatisfation = nmSatisfation;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	
	public List<StuffInfo> getStStuffNameList() {
		return stStuffNameList;
	}

	public void setStStuffNameList(List<StuffInfo> stStuffNameList) {
		this.stStuffNameList = stStuffNameList;
	}

	public String getStApplyUserName() {
		return stApplyUserName;
	}

	public void setStApplyUserName(String stApplyUserName) {
		this.stApplyUserName = stApplyUserName;
	}

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
	}

}
