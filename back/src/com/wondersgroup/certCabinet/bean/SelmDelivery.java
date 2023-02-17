package com.wondersgroup.certCabinet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DELIVERY")
public class SelmDelivery implements Serializable {
	/**
	 * 证照柜信息表
	 */
	public static final String SELM_DELIVERY = "SELM_DELIVERY";
	
	/**
	 * 快递柜ID
	 */
	public static final String ST_DELIVERY_ID = "ST_DELIVERY_ID";
	
	/**
	 * 设备ID
	 */
	public static final String ST_MACHINE_ID = "ST_MACHINE_ID";
	
	/**
	 * 设备柜号
	 */
	public static final String ST_CABINET_NO = "ST_CABINET_NO";
	
	/**
	 * 取证号
	 */
	public static final String ST_CERT_FLOW_NO = "ST_CERT_FLOW_NO";
	
	/**
	 * 收件人手机号
	 */
	public static final String ST_RECEIVER_PHONE = "ST_RECEIVER_PHONE";
	
	/**
	 * 收件人姓名
	 */
	public static final String ST_RECEIVER_NAME = "ST_RECEIVER_NAME";
	
	/**
	 * 收件人身份证号
	 */
	public static final String ST_RECEIVER_IDCARD = "ST_RECEIVER_IDCARD";
	
	/**
	 * 投件人（用户ID）
	 */
	public static final String ST_SENDER_ID = "ST_SENDER_ID";
	
	/**
	 * 投件人姓名
	 */
	public static final String ST_SENDER_NAME = "ST_SENDER_NAME";
	
	/**
	 * 投件人手机号
	 */
	public static final String ST_SENDER_PHONE = "ST_SENDER_PHONE";
	
	/**
	 * 证照类型;0：证照；1：材料
	 */
	public static final String NM_CERT_TYPE = "NM_CERT_TYPE";
	
	/**
	 * 证照名称
	 */
	public static final String ST_CERT_NAME = "ST_CERT_NAME";
	
	/**
	 * 类型，0：企业；1：个人
	 */
	public static final String NM_TYPE = "NM_TYPE";
	
	/**
	 * 关联办件
	 */
	public static final String ST_APPLY_ID = "ST_APPLY_ID";
	
	/**
	 * 企业/个人 名称
	 */
	public static final String ST_NAME = "ST_NAME";
	
	/**
	 * 状态；0：待存；1：待取；2：已取
	 */
	public static final String NM_STATUS = "NM_STATUS";
	
	/**
	 * 取件码
	 */
	public static final String ST_RECEIVE_NUM = "ST_RECEIVE_NUM";
	
	/**
	 * 描述
	 */
	public static final String ST_DESC = "ST_DESC";
	
	/**
	 * 创建时间
	 */
	public static final String DT_CREATE = "DT_CREATE";
	
	/**
	 * 投放时间
	 */
	public static final String DT_STORE = "DT_STORE";
	
	/**
	 * 取走时间
	 */
	public static final String DT_TAKE = "DT_TAKE";
	
	/**
	 * 扩展字段1
	 */
	public static final String ST_EXT1 = "ST_EXT1";
	
	/**
	 * 扩展字段2
	 */
	public static final String ST_EXT2 = "ST_EXT2";
	
	@Id
	@Column(name = "ST_DELIVERY_ID")
	private String stDeliveryId;

	public String getStDeliveryId() {
		return stDeliveryId;
	}

	public void setStDeliveryId(String stDeliveryId) {
		this.stDeliveryId = stDeliveryId;
	}

	@Column(name = "ST_MACHINE_ID")
	private String stMachineId;

	public String getStMachineId() {
		return stMachineId;
	}

	public void setStMachineId(String stMachineId) {
		this.stMachineId = stMachineId;
	}

	@Column(name = "ST_CABINET_NO")
	private String stCabinetNo;

	public String getStCabinetNo() {
		return stCabinetNo;
	}

	public void setStCabinetNo(String stCabinetNo) {
		this.stCabinetNo = stCabinetNo;
	}

	@Column(name = "ST_CERT_FLOW_NO")
	private String stCertFlowNo;

	public String getStCertFlowNo() {
		return stCertFlowNo;
	}

	public void setStCertFlowNo(String stCertFlowNo) {
		this.stCertFlowNo = stCertFlowNo;
	}

	@Column(name = "ST_RECEIVER_PHONE")
	private String stReceiverPhone;

	public String getStReceiverPhone() {
		return stReceiverPhone;
	}

	public void setStReceiverPhone(String stReceiverPhone) {
		this.stReceiverPhone = stReceiverPhone;
	}

	@Column(name = "ST_RECEIVER_NAME")
	private String stReceiverName;

	public String getStReceiverName() {
		return stReceiverName;
	}

	public void setStReceiverName(String stReceiverName) {
		this.stReceiverName = stReceiverName;
	}

	@Column(name = "ST_RECEIVER_IDCARD")
	private String stReceiverIdcard;

	public String getStReceiverIdcard() {
		return stReceiverIdcard;
	}

	public void setStReceiverIdcard(String stReceiverIdcard) {
		this.stReceiverIdcard = stReceiverIdcard;
	}

	@Column(name = "ST_SENDER_ID")
	private String stSenderId;

	public String getStSenderId() {
		return stSenderId;
	}

	public void setStSenderId(String stSenderId) {
		this.stSenderId = stSenderId;
	}

	@Column(name = "ST_SENDER_NAME")
	private String stSenderName;

	public String getStSenderName() {
		return stSenderName;
	}

	public void setStSenderName(String stSenderName) {
		this.stSenderName = stSenderName;
	}

	@Column(name = "ST_SENDER_PHONE")
	private String stSenderPhone;

	public String getStSenderPhone() {
		return stSenderPhone;
	}

	public void setStSenderPhone(String stSenderPhone) {
		this.stSenderPhone = stSenderPhone;
	}

	@Column(name = "NM_CERT_TYPE")
	private BigDecimal nmCertType;

	public BigDecimal getNmCertType() {
		return nmCertType;
	}

	public void setNmCertType(BigDecimal nmCertType) {
		this.nmCertType = nmCertType;
	}

	@Column(name = "ST_CERT_NAME")
	private String stCertName;

	public String getStCertName() {
		return stCertName;
	}

	public void setStCertName(String stCertName) {
		this.stCertName = stCertName;
	}

	@Column(name = "NM_TYPE")
	private BigDecimal nmType;

	public BigDecimal getNmType() {
		return nmType;
	}

	public void setNmType(BigDecimal nmType) {
		this.nmType = nmType;
	}

	@Column(name = "ST_APPLY_ID")
	private String stApplyId;

	public String getStApplyId() {
		return stApplyId;
	}

	public void setStApplyId(String stApplyId) {
		this.stApplyId = stApplyId;
	}

	@Column(name = "ST_NAME")
	private String stName;

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	@Column(name = "NM_STATUS")
	private BigDecimal nmStatus;

	public BigDecimal getNmStatus() {
		return nmStatus;
	}

	public void setNmStatus(BigDecimal nmStatus) {
		this.nmStatus = nmStatus;
	}

	@Column(name = "ST_RECEIVE_NUM")
	private String stReceiveNum;

	public String getStReceiveNum() {
		return stReceiveNum;
	}

	public void setStReceiveNum(String stReceiveNum) {
		this.stReceiveNum = stReceiveNum;
	}

	@Column(name = "ST_DESC")
	private String stDesc;

	public String getStDesc() {
		return stDesc;
	}

	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}

	@Column(name = "DT_CREATE")
	private Timestamp dtCreate;

	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}

	@Column(name = "DT_STORE")
	private Timestamp dtStore;

	public Timestamp getDtStore() {
		return dtStore;
	}

	public void setDtStore(Timestamp dtStore) {
		this.dtStore = dtStore;
	}

	@Column(name = "DT_TAKE")
	private Timestamp dtTake;

	public Timestamp getDtTake() {
		return dtTake;
	}

	public void setDtTake(Timestamp dtTake) {
		this.dtTake = dtTake;
	}

	@Column(name = "ST_EXT1")
	private String stExt1;

	public String getStExt1() {
		return stExt1;
	}

	public void setStExt1(String stExt1) {
		this.stExt1 = stExt1;
	}

	@Column(name = "ST_EXT2")
	private String stExt2;

	public String getStExt2() {
		return stExt2;
	}

	public void setStExt2(String stExt2) {
		this.stExt2 = stExt2;
	}
}