package com.wondersgroup.statistics.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 满意度调查表
 * @author biany
 *
 */
@Entity
@Table(name = "SELM_SATISFACTION_INFO")
public class SelmSatisfactionInfo implements Serializable{
	
	private static final long serialVersionUID = -8714291339327685308L;
	
	/**
	 * 满意度评价表
	 */
	public static final String SELM_SATISFACTION_INFO = "SELM_SATISFACTION_INFO";
	
	/**
	 * 评价信息主键
	 */
	public static final String ST_SATISFACTION_ID = "ST_SATISFACTION_ID";
	
	/**
	 * 设备满意度
	 */
	public static final String NM_SATISFACTION_MACHINE = "NM_SATISFACTION_MACHINE"; 
	
	/**
	 * 外观满意度
	 */
	public static final String NM_SATISFACTION_APPEARANCE = "NM_SATISFACTION_APPEARANCE";
	
	/**
	 * 操作便携满意度
	 */
	public static final String NM_SATISFACTION_OPERATION = "NM_SATISFACTION_OPERATION";
	
	/**
	 * 屏幕角度满意度
	 */
	public static final String NM_SATISFACTION_SCREEN = "NM_SATISFACTION_SCREEN";
	
	/**
	 * 其他补充
	 */
	public static final String ST_SATISFACTION_CONTEXT = "ST_SATISFACTION_CONTEXT";
	
	/**
	 * 评价设备MAC
	 */
	public static final String ST_EVALUATE_MACHINE_MAC = "ST_EVALUATE_MACHINE_MAC";
	
	/**
	 * 评价人姓名
	 */
	public static final String ST_EVALUATE_NAME = "ST_EVALUATE_NAME";
	
	/**
	 * 评价人证件号
	 */
	public static final String ST_EVALUATE_IDCARD = "ST_EVALUATE_IDCARD";
	
	/**
	 * 评价人联系方式
	 */
	public static final String ST_EVALUATE_PHONE = "ST_EVALUATE_PHONE";
	
	/**
	 * 评价时间
	 */
	public static final String DT_EVALUATE_TIME = "DT_EVALUATE_TIME";
	
	/**
	 * 扩展字段1
	 */
	public static final String ST_EXT1 = "ST_EXT1";
	
	/**
	 * 扩展字段2
	 */
	public static final String ST_EXT2 = "ST_EXT2";
	
	/**
	 * 扩展字段3
	 */
	public static final String ST_EXT3 = "ST_EXT3";
	
	// 评价信息主键
    @Id
    @Column(name = "ST_SATISFACTION_ID")
	private String stSatisfactionId;
    
    // 设备满意度
    @Column(name = "NM_SATISFACTION_MACHINE")
    private BigDecimal nmSatisfactionMachine;
    
    // 外观满意度
    @Column(name = "NM_SATISFACTION_APPEARANCE")
    private BigDecimal nmSatisfactionAppearacne;
    
    // 操作便携满意度
    @Column(name = "NM_SATISFACTION_OPERATION")
    private BigDecimal nmSatisfactionOperation;
    
    // 屏幕角度满意度
    @Column(name = "NM_SATISFACTION_SCREEN")
    private BigDecimal nmSatisfactionScreen;
    
    // 其他补充
    @Column(name = "ST_SATISFACTION_CONTEXT")
    private String stSatisfactionContext;
    
    // 评价设备MAC
    @Column(name = "ST_EVALUATE_MACHINE_MAC")
    private String stEvaluateMachineMAC;
    
    // 评价人姓名
    @Column(name = "ST_EVALUATE_NAME")
    private String stEvaluateName;
    
    // 评价人证件号
    @Column(name = "ST_EVALUATE_IDCARD")
    private String stEvaluateIdCard;
    
    // 评价人联系方式
    @Column(name = "ST_EVALUATE_PHONE")
    private String stEvaluatePhone;
    
    // 评价时间
    @Column(name = "DT_EVALUATE_TIME")
    private Timestamp dtEvaluateTime;
    
    // 扩展字段1
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    // 扩展字段2
    @Column(name = "ST_EXT2")
    private String stExt2;
    
    // 扩展字段3
    @Column(name = "ST_EXT3")
    private String stExt3;

	public String getStSatisfactionId() {
		return stSatisfactionId;
	}

	public void setStSatisfactionId(String stSatisfactionId) {
		this.stSatisfactionId = stSatisfactionId;
	}

	public BigDecimal getNmSatisfactionMachine() {
		return nmSatisfactionMachine;
	}

	public void setNmSatisfactionMachine(BigDecimal nmSatisfactionMachine) {
		this.nmSatisfactionMachine = nmSatisfactionMachine;
	}

	public BigDecimal getNmSatisfactionAppearacne() {
		return nmSatisfactionAppearacne;
	}

	public void setNmSatisfactionAppearacne(BigDecimal nmSatisfactionAppearacne) {
		this.nmSatisfactionAppearacne = nmSatisfactionAppearacne;
	}

	public BigDecimal getNmSatisfactionOperation() {
		return nmSatisfactionOperation;
	}

	public void setNmSatisfactionOperation(BigDecimal nmSatisfactionOperation) {
		this.nmSatisfactionOperation = nmSatisfactionOperation;
	}

	public BigDecimal getNmSatisfactionScreen() {
		return nmSatisfactionScreen;
	}

	public void setNmSatisfactionScreen(BigDecimal nmSatisfactionScreen) {
		this.nmSatisfactionScreen = nmSatisfactionScreen;
	}

	public String getStSatisfactionContext() {
		return stSatisfactionContext;
	}

	public void setStSatisfactionContext(String stSatisfactionContext) {
		this.stSatisfactionContext = stSatisfactionContext;
	}

	public String getStEvaluateMachineMAC() {
		return stEvaluateMachineMAC;
	}

	public void setStEvaluateMachineMAC(String stEvaluateMachineMAC) {
		this.stEvaluateMachineMAC = stEvaluateMachineMAC;
	}

	public String getStEvaluateName() {
		return stEvaluateName;
	}

	public void setStEvaluateName(String stEvaluateName) {
		this.stEvaluateName = stEvaluateName;
	}

	public String getStEvaluateIdCard() {
		return stEvaluateIdCard;
	}

	public void setStEvaluateIdCard(String stEvaluateIdCard) {
		this.stEvaluateIdCard = stEvaluateIdCard;
	}

	public String getStEvaluatePhone() {
		return stEvaluatePhone;
	}

	public void setStEvaluatePhone(String stEvaluatePhone) {
		this.stEvaluatePhone = stEvaluatePhone;
	}

	public Timestamp getDtEvaluateTime() {
		return dtEvaluateTime;
	}

	public void setDtEvaluateTime(Timestamp dtEvaluateTime) {
		this.dtEvaluateTime = dtEvaluateTime;
	}

	public String getStExt1() {
		return stExt1;
	}

	public void setStExt1(String stExt1) {
		this.stExt1 = stExt1;
	}

	public String getStExt2() {
		return stExt2;
	}

	public void setStExt2(String stExt2) {
		this.stExt2 = stExt2;
	}

	public String getStExt3() {
		return stExt3;
	}

	public void setStExt3(String stExt3) {
		this.stExt3 = stExt3;
	}
}
