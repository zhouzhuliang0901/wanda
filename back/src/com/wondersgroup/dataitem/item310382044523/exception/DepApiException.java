/** 
 * @(#)DepApiException.java 2017-8-17
 * 
 * Copyright (c) 1995-2016 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */
package com.wondersgroup.dataitem.item310382044523.exception;

/**
 * <pre>
 * 数据交换平台API调用异常
 * @author 
 * 2017-8-17
 * </pre>
 */
public class DepApiException extends RuntimeException {

	private static final long serialVersionUID = 294810751833668311L;
	private String code;
	private String msg;
	public DepApiException() {
        super();
    }
	
	public DepApiException(String code,String msg) {
    	super(msg);
        this.code = code;
    }
	public DepApiException(String msg) {
    	super(msg);
    }
	public DepApiException(String message, Throwable cause) {
	    super(message, cause);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
