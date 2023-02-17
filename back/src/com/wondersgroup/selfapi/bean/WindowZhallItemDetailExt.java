package com.wondersgroup.selfapi.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 事项详细表   扩展四个字段（stLegalTime、stPromiseTime、stTelConsult、stTelComplaint）
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_ZHALL_ITEM_DETAIL")
public class WindowZhallItemDetailExt implements Serializable{
    /**
     * 事项详细表
     */
    public static final String WINDOW_ZHALL_ITEM_DETAIL = "WINDOW_ZHALL_ITEM_DETAIL";
    
    /**
     * 详细事项ID
     */
    public static final String ST_DETAIL_ID = "ST_DETAIL_ID";
    
    /**
     * 报送事项ID
     */
    public static final String ST_ZHALL_ID = "ST_ZHALL_ID";
    
    /**
     * 1.适用范围
     */
    public static final String CL_RANGE = "CL_RANGE";
    
    /**
     * 2.名称和代码
     */
    public static final String CL_NAME_CODE = "CL_NAME_CODE";
    
    /**
     * 3.办理依据
     */
    public static final String CL_DEAL_ACCORDING = "CL_DEAL_ACCORDING";
    
    /**
     * 4.办理机构
     */
    public static final String CL_DEAL_ORGAN = "CL_DEAL_ORGAN";
    
    /**
     * 5.审批条件
     */
    public static final String CL_APPROVAL_CONDS = "CL_APPROVAL_CONDS";
    
    /**
     * 6.审批数量
     */
    public static final String CL_APPROVAL_COUNT = "CL_APPROVAL_COUNT";
    
    /**
     * 7.审批材料
     */
    public static final String CL_APPROVAL_MATER = "CL_APPROVAL_MATER";
    
    /**
     * 8.审批期限
     */
    public static final String CL_APPROVAL_LIMIT = "CL_APPROVAL_LIMIT";
    
    /**
     * 9.审批证件
     */
    public static final String CL_APPROVAL_CERT = "CL_APPROVAL_CERT";
    
    /**
     * 10.收费标准
     */
    public static final String CL_CHARGE_STD = "CL_CHARGE_STD";
    
    /**
     * 11.申请人权利及义务
     */
    public static final String CL_APPLY_RIGHTS_DUTIES = "CL_APPLY_RIGHTS_DUTIES";
    
    /**
     * 12.申请接收
     */
    public static final String CL_APPLY_RECEIVE = "CL_APPLY_RECEIVE";
    
    /**
     * 13.咨询途径
     */
    public static final String CL_CONSULT_WAY = "CL_CONSULT_WAY";
    
    /**
     * 14.投诉渠道
     */
    public static final String CL_COMPLAINT_CHANNEL = "CL_COMPLAINT_CHANNEL";
    
    /**
     * 15.办理方式
     */
    public static final String CL_DEAL_TYPE = "CL_DEAL_TYPE";
    
    /**
     * 16.决定公开
     */
    public static final String CL_DECIDED_OPEN = "CL_DECIDED_OPEN";
    
    /**
     * 事项流程图
     */
    public static final String BL_FLOW_CHART = "BL_FLOW_CHART";
    
    /**
     * 告知单
     */
    public static final String CL_NOTIFY = "CL_NOTIFY";
    
    public WindowZhallItemDetailExt() {
    }
    
    /**
     * 详细事项ID
     */
    @Id
    @Column(name = "ST_DETAIL_ID")
    private String stDetailId;
    
    /**
     * 报送事项ID
     */
    @Column(name = "ST_ZHALL_ID")
    private String stZhallId;
    
    /**
     * 1.适用范围
     */
    @Lob
    @Column(name = "CL_RANGE")
    private String clRange;
    
    /**
     * 2.名称和代码
     */
    @Lob
    @Column(name = "CL_NAME_CODE")
    private String clNameCode;
    
    /**
     * 3.办理依据
     */
    @Lob
    @Column(name = "CL_DEAL_ACCORDING")
    private String clDealAccording;
    
    /**
     * 4.办理机构
     */
    @Lob
    @Column(name = "CL_DEAL_ORGAN")
    private String clDealOrgan;
    
    /**
     * 5.审批条件
     */
    @Lob
    @Column(name = "CL_APPROVAL_CONDS")
    private String clApprovalConds;
    
    /**
     * 6.审批数量
     */
    @Lob
    @Column(name = "CL_APPROVAL_COUNT")
    private String clApprovalCount;
    
    /**
     * 7.审批材料
     */
    @Lob
    @Column(name = "CL_APPROVAL_MATER")
    private String clApprovalMater;
    
    /**
     * 8.审批期限
     */
    @Lob
    @Column(name = "CL_APPROVAL_LIMIT")
    private String clApprovalLimit;
    
    /**
     * 9.审批证件
     */
    @Lob
    @Column(name = "CL_APPROVAL_CERT")
    private String clApprovalCert;
    
    /**
     * 10.收费标准
     */
    @Lob
    @Column(name = "CL_CHARGE_STD")
    private String clChargeStd;
    
    /**
     * 11.申请人权利及义务
     */
    @Lob
    @Column(name = "CL_APPLY_RIGHTS_DUTIES")
    private String clApplyRightsDuties;
    
    /**
     * 12.申请接收
     */
    @Lob
    @Column(name = "CL_APPLY_RECEIVE")
    private String clApplyReceive;
    
    /**
     * 13.咨询途径
     */
    @Lob
    @Column(name = "CL_CONSULT_WAY")
    private String clConsultWay;
    
    /**
     * 14.投诉渠道
     */
    @Lob
    @Column(name = "CL_COMPLAINT_CHANNEL")
    private String clComplaintChannel;
    
    /**
     * 15.办理方式
     */
    @Lob
    @Column(name = "CL_DEAL_TYPE")
    private String clDealType;
    
    /**
     * 16.决定公开
     */
    @Lob
    @Column(name = "CL_DECIDED_OPEN")
    private String clDecidedOpen;
    
    /**
     * 事项流程图
     */
    @Lob
    @Column(name = "BL_FLOW_CHART")
    private byte[] blFlowChart;
    
    /**
     * 告知单
     */
    @Lob
    @Column(name = "CL_NOTIFY")
    private String clNotify;
    
    private String stLegalTime;
    private String stPromiseTime;
    private String stTelConsult;
    private String stTelComplaint;

	public String getStDetailId() {
		return stDetailId;
	}

	public void setStDetailId(String stDetailId) {
		this.stDetailId = stDetailId;
	}

	public String getStZhallId() {
		return stZhallId;
	}

	public void setStZhallId(String stZhallId) {
		this.stZhallId = stZhallId;
	}

	public String getClRange() {
		return clRange;
	}

	public void setClRange(String clRange) {
		this.clRange = clRange;
	}

	public String getClNameCode() {
		return clNameCode;
	}

	public void setClNameCode(String clNameCode) {
		this.clNameCode = clNameCode;
	}

	public String getClDealAccording() {
		return clDealAccording;
	}

	public void setClDealAccording(String clDealAccording) {
		this.clDealAccording = clDealAccording;
	}

	public String getClDealOrgan() {
		return clDealOrgan;
	}

	public void setClDealOrgan(String clDealOrgan) {
		this.clDealOrgan = clDealOrgan;
	}

	public String getClApprovalConds() {
		return clApprovalConds;
	}

	public void setClApprovalConds(String clApprovalConds) {
		this.clApprovalConds = clApprovalConds;
	}

	public String getClApprovalCount() {
		return clApprovalCount;
	}

	public void setClApprovalCount(String clApprovalCount) {
		this.clApprovalCount = clApprovalCount;
	}

	public String getClApprovalMater() {
		return clApprovalMater;
	}

	public void setClApprovalMater(String clApprovalMater) {
		this.clApprovalMater = clApprovalMater;
	}

	public String getClApprovalLimit() {
		return clApprovalLimit;
	}

	public void setClApprovalLimit(String clApprovalLimit) {
		this.clApprovalLimit = clApprovalLimit;
	}

	public String getClApprovalCert() {
		return clApprovalCert;
	}

	public void setClApprovalCert(String clApprovalCert) {
		this.clApprovalCert = clApprovalCert;
	}

	public String getClChargeStd() {
		return clChargeStd;
	}

	public void setClChargeStd(String clChargeStd) {
		this.clChargeStd = clChargeStd;
	}

	public String getClApplyRightsDuties() {
		return clApplyRightsDuties;
	}

	public void setClApplyRightsDuties(String clApplyRightsDuties) {
		this.clApplyRightsDuties = clApplyRightsDuties;
	}

	public String getClApplyReceive() {
		return clApplyReceive;
	}

	public void setClApplyReceive(String clApplyReceive) {
		this.clApplyReceive = clApplyReceive;
	}

	public String getClConsultWay() {
		return clConsultWay;
	}

	public void setClConsultWay(String clConsultWay) {
		this.clConsultWay = clConsultWay;
	}

	public String getClComplaintChannel() {
		return clComplaintChannel;
	}

	public void setClComplaintChannel(String clComplaintChannel) {
		this.clComplaintChannel = clComplaintChannel;
	}

	public String getClDealType() {
		return clDealType;
	}

	public void setClDealType(String clDealType) {
		this.clDealType = clDealType;
	}

	public String getClDecidedOpen() {
		return clDecidedOpen;
	}

	public void setClDecidedOpen(String clDecidedOpen) {
		this.clDecidedOpen = clDecidedOpen;
	}

	public byte[] getBlFlowChart() {
		return blFlowChart;
	}

	public void setBlFlowChart(byte[] blFlowChart) {
		this.blFlowChart = blFlowChart;
	}

	public String getClNotify() {
		return clNotify;
	}

	public void setClNotify(String clNotify) {
		this.clNotify = clNotify;
	}

	public String getStLegalTime() {
		return stLegalTime;
	}

	public void setStLegalTime(String stLegalTime) {
		this.stLegalTime = stLegalTime;
	}

	public String getStPromiseTime() {
		return stPromiseTime;
	}

	public void setStPromiseTime(String stPromiseTime) {
		this.stPromiseTime = stPromiseTime;
	}

	public String getStTelConsult() {
		return stTelConsult;
	}

	public void setStTelConsult(String stTelConsult) {
		this.stTelConsult = stTelConsult;
	}

	public String getStTelComplaint() {
		return stTelComplaint;
	}

	public void setStTelComplaint(String stTelComplaint) {
		this.stTelComplaint = stTelComplaint;
	}
}
