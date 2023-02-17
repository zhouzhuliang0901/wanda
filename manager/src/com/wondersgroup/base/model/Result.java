package com.wondersgroup.base.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class Result implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSuccess;
	private String errorCode = "";
	private String errorMsg = "";
	private String infoMsg = "";
	private String data = "";

	public static Result getResult() {
		return new Result();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public Result setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
		return this;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Result setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Result setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		return this;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public Result setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
		return this;
	}

	public String getData() {
		return data;
	}

	public Result setData(String data) {
		this.data = data;
		return this;
	}

	public String toString() {
		JSONObject jso = new JSONObject();
		jso.put("isSuccess", this.isSuccess);
		jso.put("errorCode", this.errorCode);
		jso.put("errorMsg", this.errorMsg);
		jso.put("infoMsg", this.infoMsg);
		jso.put("data", this.data);
		return jso.toString();
	}
}
