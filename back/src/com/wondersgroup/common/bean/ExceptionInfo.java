package com.wondersgroup.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_EXCEPTION_INFO")
public class ExceptionInfo implements Serializable{
	
	/**
	 * 一网通办中台接口访问异常记录表
	 */
	public static final String SELM_EXCEPTION_INFO = "SELM_EXCEPTION_INFO";
	
	/**
	 * 主键
	 */
	public static final String ST_ID = "ST_ID";
	
	/**
	 * 方法名
	 */
	public static final String ST_EXCEPTION_METHOD = "ST_EXCEPTION_METHOD";	
	
	/**
	 * 包名
	 */
	public static final String ST_EXCEPTION_PACKAGE = "ST_EXCEPTION_PACKAGE";
	
	/**
	 * 异常原因
	 */
	public static final String ST_EXCEPTION_CAUSE = "ST_EXCEPTION_CAUSE";
	
	/**
	 * 异常位置
	 */
	public static final String ST_EXCEPTION_LINE = "ST_EXCEPTION_LINE";
	
	/**
	 * 异常文件
	 */
	public static final String ST_EXCEPTION_FILE = "ST_EXCEPTION_FILE";
	
	/**
	 * 发生时间
	 */
	public static final String DT_EXCEPTION_TIME = "DT_EXCEPTION_TIME";
	
	/**
	 * 请求地址
	 */
	public static final String ST_REQUEST_URL = "ST_REQUEST_URL";
	
	/**
	 * 请求方法
	 */
	public static final String ST_REQUEST_METHOD = "ST_REQUEST_METHOD";
	
	/**
	 * 请求参数
	 */
	public static final String ST_REQUEST_PARAM = "ST_REQUEST_PARAM";
	
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
	 * 主键
	 */
	@Id
	@Column(name = "ST_ID")
	private String stId;
	
	/**
	 * 方法名
	 */
	@Column(name = "ST_EXCEPTION_METHOD")
	private String stExceptionMethod;
	
	/**
	 * 包名
	 */
	@Column(name = "ST_EXCEPTION_PACKAGE")
	private String stExceptionPackage;
	
	/**
	 * 异常原因
	 */
	@Column(name = "ST_EXCEPTION_CAUSE")
	private String stExceptionCause;
	
	/**
	 * 异常位置
	 */
	@Column(name = "ST_EXCEPTION_LINE")
	private BigDecimal stExceptionLine;
	
	/**
	 * 异常文件
	 */
	@Column(name = "ST_EXCEPTION_FILE")
	private String stExceptionFile;
	
	/**
	 * 发生时间
	 */
	 @Column(name = "DT_EXCEPTION_TIME")
	private Timestamp dtExceptionTime;
	
	/**
	 * 请求地址
	 */
	@Column(name = "ST_REQUEST_URL")
	private String stRequestUrl;
	
	/**
	 * 请求方法
	 */
	@Column(name = "ST_REQUEST_METHOD")
	private String stRequestMethod;
	
	/**
	 * 请求参数
	 */
	@Column(name = "ST_REQUEST_PARAM")
	private String stRequestParam;
	
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
	
	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStExceptionMethod() {
		return stExceptionMethod;
	}

	public void setStExceptionMethod(String stExceptionMethod) {
		this.stExceptionMethod = stExceptionMethod;
	}

	public String getStExceptionPackage() {
		return stExceptionPackage;
	}

	public void setStExceptionPackage(String stExceptionPackage) {
		this.stExceptionPackage = stExceptionPackage;
	}

	public String getStExceptionCause() {
		return stExceptionCause;
	}

	public void setStExceptionCause(String stExceptionCause) {
		this.stExceptionCause = stExceptionCause;
	}

	public BigDecimal getStExceptionLine() {
		return stExceptionLine;
	}

	public void setStExceptionLine(BigDecimal stExceptionLine) {
		this.stExceptionLine = stExceptionLine;
	}

	public String getStExceptionFile() {
		return stExceptionFile;
	}

	public void setStExceptionFile(String stExceptionFile) {
		this.stExceptionFile = stExceptionFile;
	}

	public Timestamp getDtExceptionTime() {
		return dtExceptionTime;
	}

	public void setDtExceptionTime(Timestamp dtExceptionTime) {
		this.dtExceptionTime = dtExceptionTime;
	}

	public String getStRequestUrl() {
		return stRequestUrl;
	}

	public void setStRequestUrl(String stRequestUrl) {
		this.stRequestUrl = stRequestUrl;
	}
	
	public String getStRequestMethod() {
		return stRequestMethod;
	}

	public void setStRequestMethod(String stRequestMethod) {
		this.stRequestMethod = stRequestMethod;
	}
	
	public String getStRequestParam() {
		return stRequestParam;
	}

	public void setStRequestParam(String stRequestParam) {
		this.stRequestParam = stRequestParam;
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
	
}
