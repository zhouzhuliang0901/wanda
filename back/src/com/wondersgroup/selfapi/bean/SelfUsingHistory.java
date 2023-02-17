package com.wondersgroup.selfapi.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_QUERY_HIS")
public class SelfUsingHistory implements Serializable{
	
	/**
	 * 历史记录表
	 */
	public static final String SELM_QUERY_HIS = "SELM_QUERY_HIS";
	
	/**
	 * 历史ID
	 */
	public static final String ST_QUERY_HIS_ID = "ST_QUERY_HIS_ID";
	
	/**
	 * 设备ID
	 */
	public static final String ST_MACHINE_ID = "ST_MACHINE_ID";
	
	/**
	 * 辅助人ID
	 */
	public static final String ST_ASSIST_ID = "ST_ASSIST_ID";
	
	/**
	 * 模块名称
	 */
	public static final String ST_MODULE_NAME = "ST_MODULE_NAME";
	
	/**
	 * 操作名称
	 */
	public static final String ST_MODULE_OP = "ST_MODULE_OP";
	
	/**
	 * 事项名称
	 */
	public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
	
	/**
	 * 事项编码
	 */
	public static final String ST_ITEM_NO = "ST_ITEM_NO";
	
	/**
	 * 姓名
	 */
	public static final String ST_NAME = "ST_NAME";
	
	/**
	 * 证件号
	 */
	public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";
	
	/**
	 * 手机号
	 */
	public static final String ST_MOBILE = "ST_MOBILE";
	
	/**
	 * 业务唯一标识
	 */
	public static final String ST_BUSINESS_NO = "ST_BUSINESS_NO";
	
	/**
	 * 提交的数据（查询结果）
	 */
	public static final String ST_SUBMIT_DATA_ID = "ST_SUBMIT_DATA_ID";
	
	/**
	 * 备注
	 */
	public static final String ST_DESC = "ST_DESC";
	
	/**
	 * 操作结果
	 */
	public static final String ST_OP_RESULT = "ST_OP_RESULT";
	
	/**
	 * 创建时间
	 */
	public static final String DT_CREATE = "DT_CREATE";
	
	/**
	 * 附件ID1
	 */
	public static final String ST_ATTACH_ID1 = "ST_ATTACH_ID1";
	
	/**
	 * 附件ID2
	 */
	public static final String ST_ATTACH_ID2 = "ST_ATTACH_ID2";
	
	/**
	 * 附件ID3
	 */
	public static final String ST_ATTACH_ID3 = "ST_ATTACH_ID3";
	
	/**
	 * 附件ID4
	 */
	public static final String ST_ATTACH_ID4 = "ST_ATTACH_ID4";
	
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
	
	/**
	 * 扩展字段4
	 */
	public static final String ST_EXT4 = "ST_EXT4";
	
	/**
	 * 扩展字段5
	 */
	public static final String ST_EXT5 = "ST_EXT5";
	
	/**
	 * 历史ID
	 */
    @Id
    @Column(name = "ST_QUERY_HIS_ID")
    private String stHisId;
    
	/**
	 * 设备ID
	 */
	@Column(name = "ST_MACHINE_ID")
	private String stMachineId;
	/**
	 * 辅助人ID
	 */
	@Column(name = "ST_ASSIST_ID")
	private String stAssistId;
	/**
	 * 模块名称
	 */
	@Column(name = "ST_MODULE_NAME")
	private String stModuleName;
	/**
	 * 操作名称
	 */
	@Column(name = "ST_MODULE_OP")
	private String stModuleOp;
	/**
	 * 事项名称
	 */
	@Column(name = "ST_ITEM_NAME")
	private String stItemName;
	/**
	 * 事项编码
	 */
	@Column(name = "ST_ITEM_NO")
	private String stItemNo;
	/**
	 * 姓名
	 */
	@Column(name = "ST_NAME")
	private String stName;
	/**
	 * 证件号
	 */
	@Column(name = "ST_IDENTITY_NO")
	private String stIdentityNo;
	/**
	 * 手机号
	 */
	@Column(name = "ST_MOBILE")
	private String stMobile;
	/**
	 * 业务唯一标识
	 */
	@Column(name = "ST_BUSINESS_NO")
	private String stBusinessNo;
	/**
	 * 提交的数据（查询结果）
	 */
	@Column(name = "ST_SUBMIT_DATA_ID")
	private String stSubmitDataId;
	/**
	 * 备注
	 */
	@Column(name = "ST_DESC")
	private String stDesc;
	/**
	 * 操作结果
	 */
	@Column(name = "ST_OP_RESULT")
	private String stOpResult;
	/**
	 * 创建时间
	 */
	@Column(name = "DT_CREATE")
	private Timestamp dtCreate;
	/**
	 * 附件ID1
	 */
	@Column(name = "ST_ATTACH_ID1")
	private String stAttachId1;
	/**
	 * 附件ID2
	 */
	@Column(name = "ST_ATTACH_ID2")
	private String stAttachId2;
	/**
	 * 附件ID3
	 */
	@Column(name = "ST_ATTACH_ID3")
	private String stAttachId3;
	/**
	 * 附件ID4
	 */
	@Column(name = "ST_ATTACH_ID4")
	private String stAttachId4;
	/**
	 * 扩展字段1
	 */
	@Column(name = "ST_EXT1")
	private String stExt1;
	/**
	 * 扩展字段2
	 */
	@Column(name = "ST_EXT2")
	private String stExt2;
	/**
	 * 扩展字段3
	 */
	@Column(name = "ST_EXT3")
	private String stExt3;
	/**
	 * 扩展字段4
	 */
	@Column(name = "ST_EXT4")
	private String stExt4;
	/**
	 * 扩展字段5
	 */
	@Column(name = "ST_EXT5")
	private String stExt5;
	
	private String dtCreaterStr;
	
	public String getStHisId() {
		return stHisId;
	}
	public void setStHisId(String stHisId) {
		this.stHisId = stHisId;
	}
	public String getStMachineId() {
		return stMachineId;
	}
	public void setStMachineId(String stMachineId) {
		this.stMachineId = stMachineId;
	}
	public String getStModuleName() {
		return stModuleName;
	}
	public void setStModuleName(String stModuleName) {
		this.stModuleName = stModuleName;
	}
	public String getStModuleOp() {
		return stModuleOp;
	}
	public void setStModuleOp(String stModuleOp) {
		this.stModuleOp = stModuleOp;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStIdentityNo() {
		return stIdentityNo;
	}
	public void setStIdentityNo(String stIdentityNo) {
		this.stIdentityNo = stIdentityNo;
	}
	public String getStMobile() {
		return stMobile;
	}
	public void setStMobile(String stMobile) {
		this.stMobile = stMobile;
	}
	public Timestamp getDtCreate() {
		return dtCreate;
	}
	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}
	public String getStAttachId1() {
		return stAttachId1;
	}
	public void setStAttachId1(String stAttachId1) {
		this.stAttachId1 = stAttachId1;
	}
	public String getStAttachId2() {
		return stAttachId2;
	}
	public void setStAttachId2(String stAttachId2) {
		this.stAttachId2 = stAttachId2;
	}
	public String getStAttachId3() {
		return stAttachId3;
	}
	public void setStAttachId3(String stAttachId3) {
		this.stAttachId3 = stAttachId3;
	}
	public String getStAttachId4() {
		return stAttachId4;
	}
	public void setStAttachId4(String stAttachId4) {
		this.stAttachId4 = stAttachId4;
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
	public String getStExt4() {
		return stExt4;
	}
	public void setStExt4(String stExt4) {
		this.stExt4 = stExt4;
	}
	public String getStExt5() {
		return stExt5;
	}
	public void setStExt5(String stExt5) {
		this.stExt5 = stExt5;
	}
	public String getStItemName() {
		return stItemName;
	}
	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}
	public String getStBusinessNo() {
		return stBusinessNo;
	}
	public void setStBusinessNo(String stBusinessNo) {
		this.stBusinessNo = stBusinessNo;
	}
	public String getStSubmitDataId() {
		return stSubmitDataId;
	}
	public void setStSubmitDataId(String stSubmitDataId) {
		this.stSubmitDataId = stSubmitDataId;
	}
	public String getStDesc() {
		return stDesc;
	}
	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}
	public String getStOpResult() {
		return stOpResult;
	}
	public void setStOpResult(String stOpResult) {
		this.stOpResult = stOpResult;
	}
	public String getDtCreaterStr() {
		return dtCreaterStr;
	}
	public void setDtCreaterStr(String dtCreaterStr) {
		this.dtCreaterStr = dtCreaterStr;
	}
	public String getStItemNo() {
		return stItemNo;
	}
	public void setStItemNo(String stItemNo) {
		this.stItemNo = stItemNo;
	}
	public String getStAssistId() {
		return stAssistId;
	}
	public void setStAssistId(String stAssistId) {
		this.stAssistId = stAssistId;
	}
}
