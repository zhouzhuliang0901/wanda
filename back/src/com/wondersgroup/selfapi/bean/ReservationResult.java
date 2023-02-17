package com.wondersgroup.selfapi.bean;

public class ReservationResult {
	
	public static final String SUCCESS_CODE_0 ="0";
	
	public static final String ERROR_CODE_1 ="1";
	public static final String ERROR_CODE_1_VALUE ="预约失败，请重新预约";
	
	public static final String ERROR_CODE_2 ="2";
	public static final String ERROR_CODE_2_VALUE ="预约人数已满，预约失败";
	
	public static final String ERROR_CODE_3 ="3";
	public static final String ERROR_CODE_3_VALUE ="您已经达到本天最大预约量，不能再预约了";

	public static final String ERROR_CODE_4 ="4";
	public static final String ERROR_CODE_4_VALUE ="你已经被列为黑名单了，不能进行事项预约";
	
	private String reservationNo;
	
	public String getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	private boolean isSuccess;
	
	private String errorCode;
	
	private String value;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
