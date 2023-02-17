package com.wondersgroup.applyInfo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_APPLY_INFO")
public class SelmApplyInfo implements Serializable {

public static final String SELM_APPLY_INFO = "SELM_APPLY_INFO";

	public static final String ST_QUERY_HIS_ID = "ST_QUERY_HIS_ID";

	public static final String ST_MACHINE_ID = "ST_MACHINE_ID";

	public static final String ST_MODULE_NAME = "ST_MODULE_NAME";

	public static final String ST_MODULE_OP = "ST_MODULE_OP";

	public static final String ST_ITEM_NAME = "ST_ITEM_NAME";

	public static final String ST_ITEM_NO = "ST_ITEM_NO";

	public static final String ST_NAME = "ST_NAME";

	public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";

	public static final String ST_MOBILE = "ST_MOBILE";

	public static final String ST_BUSINESS_NO = "ST_BUSINESS_NO";

	public static final String ST_SUBMIT_DATA_ID = "ST_SUBMIT_DATA_ID";

	public static final String ST_DESC = "ST_DESC";

	public static final String ST_OP_RESULT = "ST_OP_RESULT";

	public static final String DT_CREATE = "DT_CREATE";

	public static final String ST_EXT1 = "ST_EXT1";

	public static final String ST_EXT2 = "ST_EXT2";

	public static final String ST_EXT3 = "ST_EXT3";

	public static final String ST_EXT4 = "ST_EXT4";

	public static final String ST_EXT5 = "ST_EXT5";

	@Column(name = "ST_QUERY_HIS_ID")
	private String stQueryHisId;

	@Column(name = "ST_MACHINE_ID")
	private String stMachineId;

	@Column(name = "ST_MODULE_NAME")
	private String stModuleName;

	@Column(name = "ST_MODULE_OP")
	private String stModuleOp;

	@Column(name = "ST_ITEM_NAME")
	private String stItemName;

	@Column(name = "ST_ITEM_NO")
	private String stItemNo;

	@Column(name = "ST_NAME")
	private String stName;

	@Column(name = "ST_IDENTITY_NO")
	private String stIdentityNo;

	@Column(name = "ST_MOBILE")
	private String stMobile;

	@Column(name = "ST_BUSINESS_NO")
	private String stBusinessNo;

	@Column(name = "ST_SUBMIT_DATA_ID")
	private String stSubmitDataId;

	@Column(name = "ST_DESC")
	private String stDesc;

	@Column(name = "ST_OP_RESULT")
	private String stOpResult;

	@Column(name = "DT_CREATE")
	private Timestamp dtCreate;

	@Column(name = "ST_EXT1")
	private String stExt1;

	@Column(name = "ST_EXT2")
	private String stExt2;

	@Column(name = "ST_EXT3")
	private String stExt3;

	@Column(name = "ST_EXT4")
	private String stExt4;

	@Column(name = "ST_EXT5")
	private String stExt5;

	public String getStQueryHisId() {
		return stQueryHisId;
	}

	public void setStQueryHisId(String stQueryHisId) {
		this.stQueryHisId = stQueryHisId;
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

	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
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
}